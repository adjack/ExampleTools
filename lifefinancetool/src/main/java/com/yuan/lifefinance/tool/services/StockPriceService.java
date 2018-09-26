package com.yuan.lifefinance.tool.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.yuan.lifefinance.tool.MainActivity;
import com.yuan.lifefinance.tool.R;
import com.yuan.lifefinance.tool.greendao.DBManager;
import com.yuan.lifefinance.tool.greendao.StockInfo;
import com.yuan.lifefinance.tool.httptools.NetworkFactory;
import com.yuan.lifefinance.tool.httptools.ResponseCallBack;
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
public class StockPriceService extends IntentService {
    private static final String EXTRA_PARAM2 = "com.yuan.lifefinance.tool.services.extra.PARAM2";

    public StockPriceService() {
        super("StockPriceService");
    }

    private void getAllBuyStockList() {
        List<StockInfo> stockInfos = DBManager.getInstance().selectStockInfo(1, 20);
    }

    protected void onHandleIntent(Intent intent) {
        String url = "";
        while (true) {
            if (canSendNotif()) {//服务时间段
                //查找已买个股的列表
                List<StockInfo> stockInfos = DBManager.getInstance().selectBuyingStockInfo();
                for (int i = 0; i < stockInfos.size(); i++) {
                    String code = stockInfos.get(i).getDiscrib1();
                    code = (code.startsWith("0") || code.startsWith("3")) ? "sz" + code : "sh" + code;
                    url = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=" + code + "&datalen=1&scale=5";
                    NetworkFactory.getInstance().get(stockInfos.get(i), url, new ResponseCallBack<JSONObject>() {
                        @Override
                        public void onSuccess(Object requestOb, boolean dataCheck, int totalNum, JSONObject response, String rawData) {

                            try {
                                JSONArray jsonArray = new JSONArray(rawData);
                                JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                                StockInfo stockInfo = (StockInfo) requestOb;
                                Log.d("ResponseCallBack", jsonObject.toString() + "//");
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
                            } catch (Exception ex) {
                            }
                        }

                        @Override
                        public void onFailure(Object requestOb, Throwable t, String rawData) {

                        }
                    });
                    SystemClock.sleep(500);
                }

                SystemClock.sleep(1000 * 10);
            }
        }


    }

    private boolean canSendNotif() {
//        Calendar calendar = Calendar.getInstance();
//        if (getDayOfWeek(calendar) <= 5) {//周一到周五
//            int hour = calendar.get(Calendar.HOUR);
//            if (calendar.get(Calendar.AM_PM) == Calendar.AM) {//上午
//                if (hour >= 9 && hour <= 11) {
//                    if (hour == 9 || hour == 11) {
//                        if (hour == 9) {
//                            if (calendar.get(Calendar.MINUTE) >= 30) {
//                                return true;
//                            }
//                        }
//
//                        if (hour == 11) {
//                            if (calendar.get(Calendar.MINUTE) <= 30) {
//                                return true;
//                            }
//                        }
//                    } else {
//                        return true;
//                    }
//                }
//            } else {
//                if (hour >= 1 && hour <= 3) {
//                    return true;
//                }
//            }
//        }
        return true;
    }

    private void sendNotif(int id, String title,String contentText) {
        if (!canSendNotif()) {
            Log.d("canSendNotif", "不在服务时间段");
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
                Log.i("MainActivityFilter", "今天是周一");
                return 1;
            case Calendar.TUESDAY:
                Log.i("MainActivityFilter", "今天是周二");
                return 2;
            case Calendar.WEDNESDAY:
                Log.i("MainActivityFilter", "今天是周三");
                return 3;
            case Calendar.THURSDAY:
                Log.i("MainActivityFilter", "今天是周四");
                return 4;
            case Calendar.FRIDAY:
                Log.i("MainActivityFilter", "今天是周五");
                return 5;
            case Calendar.SATURDAY:
                Log.i("MainActivityFilter", "今天是周六");
                return 6;
            default:
                break;
        }
        return -1;
    }
}