package com.yuan.lifefinance.tool.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

import com.yuan.lifefinance.tool.R;
import com.yuan.lifefinance.tool.greendao.DBManager;
import com.yuan.lifefinance.tool.greendao.StockInfo;
import com.yuan.lifefinance.tool.httptools.NetworkFactory;
import com.yuan.lifefinance.tool.httptools.ResponseCallBack;
import com.yuan.lifefinance.tool.tools.LogUtil;
import com.yuan.lifefinance.tool.tools.StringInputUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import static com.yuan.lifefinance.tool.tools.TimeTools.canSendNotif;

/**
 * 监听股票报警信息跟已买个股实时价格
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class StockPriceService extends IntentService {
    String url = "";
    public StockPriceService() {
        super("StockPriceService");
    }

    protected void onHandleIntent(Intent intent) {

        while (true) {
            if (canSendNotif()) {//服务时间段
                //查找已买个股的列表
                List<StockInfo> stockInfos = DBManager.getInstance().selectBuyingStockInfo();
                for (int i = 0; i < stockInfos.size(); i++) {
                    String code = stockInfos.get(i).getDiscrib1();
                    code = (code.startsWith("0") || code.startsWith("3")) ? "sz" + code : "sh" + code;
                    method2(stockInfos.get(i),code);
                    SystemClock.sleep(200);
                }

                SystemClock.sleep(1000 * 5);
            }
            else{
                LogUtil.d("ResponseCallBack","不在服务时间段");
                SystemClock.sleep(1000 * 30);
            }
        }
    }

    private void method1(StockInfo stockInfo,String code){
        url = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=" + code + "&datalen=1&scale=5";
        NetworkFactory.getInstance().get(stockInfo, url, new ResponseCallBack<JSONObject>() {
            @Override
            public void onSuccess(Object requestOb, boolean dataCheck, int totalNum, JSONObject response, String rawData) {
                try {
                    JSONArray jsonArray = new JSONArray(rawData);
                    JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                    StockInfo stockInfo = (StockInfo) requestOb;
                    LogUtil.d("ResponseCallBack", rawData + "//");
                    Double closePrice = Double.valueOf(StringInputUtils.value(jsonObject.get("close")));
//                                closePrice = 20.0;
                    int id = Math.toIntExact(stockInfo.getId());
                    if (closePrice <= stockInfo.getStopLoss()) {
                        //发送通知
                        sendNotif(id, "个股警报","个股[" + stockInfo.getStokeName() + "]跌破止损价：" + stockInfo.getStopLoss());
                    }

                    if (closePrice >= stockInfo.getMostPrice()) {
                        //发送通知
                        sendNotif(id, "个股喜报","恭喜个股[" + stockInfo.getStokeName() + "]涨幅达到目标位：" + stockInfo.getMostPrice());
                    }

                    //更新数据库
                    DBManager.getInstance().updateBuyingStockInfo(stockInfo.getId(),closePrice+"");

                } catch (Exception ex) {
                }
            }

            @Override
            public void onFailure(Object requestOb, Throwable t, String rawData) {

            }
        });
    }

    private void method2(StockInfo stockInfo,String code){
        url = "http://hq.sinajs.cn/list=" + code;
        NetworkFactory.getInstance().get(stockInfo, url, new ResponseCallBack<JSONObject>() {
            @Override
            public void onSuccess(Object requestOb, boolean dataCheck, int totalNum, JSONObject response, String rawData) {
                try {
                    StockInfo stockInfo = (StockInfo) requestOb;
                    LogUtil.d("ResponseCallBack", rawData.toString() + "//");
                    String[] value = rawData.split("=")[1].substring(1).split(",");
                    LogUtil.d("ResponseCallBack","最新价格："+value[3]);
                    Double openPrice = Double.valueOf(StringInputUtils.value(value[1]));
                    Double closePrice = Double.valueOf(StringInputUtils.value(value[3]));
                    int id = Math.toIntExact(stockInfo.getId());
                    if (closePrice <= stockInfo.getStopLoss()) {
                        //发送通知
                        sendNotif(id, "个股警报","个股[" + stockInfo.getStokeName() + "]跌破止损价：" + stockInfo.getStopLoss());
                    }

                    if (closePrice >= stockInfo.getMostPrice()) {
                        //发送通知
                        sendNotif(id, "个股喜报","恭喜个股[" + stockInfo.getStokeName() + "]涨幅达到目标位：" + stockInfo.getMostPrice());
                    }

                    //更新数据库
                    DBManager.getInstance().updateBuyingStockInfo(stockInfo.getId(),openPrice+"_"+closePrice);

//                    Intent intent1 = new Intent();
//                    intent1.setAction("com.yuan.action.stock.buy.price.change");
//                    intent1.putExtra("stockName",stockInfo.getStokeName());
//                    intent1.putExtra("closePrice",closePrice);
//                    intent1.putExtra("openPrice",openPrice);
//                    sendBroadcast(intent1);


                } catch (Exception ex) {
                }
            }

            @Override
            public void onFailure(Object requestOb, Throwable t, String rawData) {

            }
        });
    }


    private void sendNotif(int id, String title,String contentText) {
        if (!canSendNotif()) {
            LogUtil.d("canSendNotif", "不在服务时间段");
            return;
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.icon_launch_new3)
                .setContentTitle(title)
                .setContentText(contentText);
        //设置点击通知之后的响应，启动SettingActivity类
//        Intent resultIntent = new Intent(this,MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        notification.sound = uri;
        long[] vibrates = {0, 1000, 1000, 1000};
        notification.vibrate = vibrates;

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification);
    }

}