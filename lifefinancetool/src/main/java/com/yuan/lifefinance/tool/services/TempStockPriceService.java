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
import com.yuan.lifefinance.tool.greendao.TempStockInfo;
import com.yuan.lifefinance.tool.httptools.NetworkFactory;
import com.yuan.lifefinance.tool.httptools.ResponseCallBack;
import com.yuan.lifefinance.tool.tools.LogUtil;
import com.yuan.lifefinance.tool.tools.StringInputUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class TempStockPriceService extends IntentService {
    private static final String EXTRA_PARAM2 = "com.yuan.lifefinance.tool.services.extra.PARAM2";

    public TempStockPriceService() {
        super("StockPriceService");
    }

//    private void getReadyBuyStockList() {
//        List<StockInfo> stockInfos = DBManager.getInstance().selectStockInfo(1, 20);
//    }
    String url = "";
    protected void onHandleIntent(Intent intent) {

        while (true) {
            if (canSendNotif()) {//服务时间段
                //查找已买个股的列表
                List<TempStockInfo> tempStockInfos = DBManager.getInstance().selectTempStockInfo(1,200);
                for (int i = 0; i < tempStockInfos.size(); i++) {
                    tempStockInfos.get(i).setDiscrib4(""+i);
                    String code = tempStockInfos.get(i).getDiscrib1();
                    code = (code.startsWith("0") || code.startsWith("3")) ? "sz" + code : "sh" + code;
                    method2(tempStockInfos.get(i),code);
                    SystemClock.sleep(500);
                }

                SystemClock.sleep(1000 * 4);
            }
            else{
                LogUtil.d("ResponseCallBack","不在服务时间段");
                SystemClock.sleep(1000 * 30);
            }
        }
    }

    private void method1(TempStockInfo tempStockInfo,String code){
        url = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=" + code + "&datalen=1&scale=5";
        NetworkFactory.getInstance().get(tempStockInfo, url, new ResponseCallBack<JSONObject>() {
            @Override
            public void onSuccess(Object requestOb, boolean dataCheck, int totalNum, JSONObject response, String rawData) {

                try {
                    JSONArray jsonArray = new JSONArray(rawData);
                    JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                    TempStockInfo tempStockInfo = (TempStockInfo) requestOb;
                    LogUtil.d("ResponseCallBack", jsonObject.toString() + "//");
                    Double closePrice = Double.valueOf(StringInputUtils.value(jsonObject.get("close")));
//                              //发送广播
                    Intent intent1 = new Intent();
                    intent1.setAction("com.yuan.action.stock.price.change");
                    intent1.putExtra("stockName",tempStockInfo.getStokeName());
                    intent1.putExtra("closePrice",closePrice);
                    intent1.putExtra("position",tempStockInfo.getDiscrib4());
                    sendBroadcast(intent1);

                } catch (Exception ex) {
                }
            }

            @Override
            public void onFailure(Object requestOb, Throwable t, String rawData) {

            }
        });
    }

    private void method2(TempStockInfo tempStockInfo,String code){
        url = "http://hq.sinajs.cn/list=" + code;
        NetworkFactory.getInstance().get(tempStockInfo, url, new ResponseCallBack<JSONObject>() {
            @Override
            public void onSuccess(Object requestOb, boolean dataCheck, int totalNum, JSONObject response, String rawData) {

                try {
                    TempStockInfo tempStockInfo = (TempStockInfo) requestOb;
                    String[] value = rawData.split("=")[1].substring(1).split(",");
                    Double openPrice = Double.valueOf(StringInputUtils.value(value[1]));
                    Double closePrice = Double.valueOf(StringInputUtils.value(value[3]));
                    //发送广播
                    Intent intent1 = new Intent();
                    intent1.setAction("com.yuan.action.stock.price.change");
                    intent1.putExtra("stockName",tempStockInfo.getStokeName());
                    intent1.putExtra("closePrice",closePrice);
                    intent1.putExtra("openPrice",openPrice);
                    intent1.putExtra("position",tempStockInfo.getDiscrib4());
                    sendBroadcast(intent1);

                } catch (Exception ex) {
                }
            }

            @Override
            public void onFailure(Object requestOb, Throwable t, String rawData) {

            }
        });
    }


    private boolean canSendNotif() {
        Calendar calendar = Calendar.getInstance();
        if (getDayOfWeek(calendar) <= 5) {//周一到周五
            int hour = calendar.get(Calendar.HOUR);
            if (calendar.get(Calendar.AM_PM) == Calendar.AM) {//上午
                if (hour >= 9 && hour <= 11) {
                    if (hour == 9 || hour == 11) {
                        if (hour == 9) {
                            if (calendar.get(Calendar.MINUTE) >= 30) {
                                return true;
                            }
                        }

                        if (hour == 11) {
                            if (calendar.get(Calendar.MINUTE) <= 30) {
                                return true;
                            }
                        }
                    } else {
                        return true;
                    }
                }
            } else {
                if (hour >= 1 && hour <= 3) {
                    return true;
                }
            }
        }
        return false;
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

    private int getDayOfWeek(Calendar c) {
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                return 7;
            case Calendar.MONDAY:
                LogUtil.i("MainActivityFilter", "今天是周一");
                return 1;
            case Calendar.TUESDAY:
                LogUtil.i("MainActivityFilter", "今天是周二");
                return 2;
            case Calendar.WEDNESDAY:
                LogUtil.i("MainActivityFilter", "今天是周三");
                return 3;
            case Calendar.THURSDAY:
                LogUtil.i("MainActivityFilter", "今天是周四");
                return 4;
            case Calendar.FRIDAY:
                LogUtil.i("MainActivityFilter", "今天是周五");
                return 5;
            case Calendar.SATURDAY:
                LogUtil.i("MainActivityFilter", "今天是周六");
                return 6;
            default:
                break;
        }
        return -1;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("onReceive_log","service onDestroy");
    }
}