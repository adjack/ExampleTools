package com.yuan.lifefinance.tool.tools;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 123 on 2018/9/27.
 */

public class TimeTools {

    /**
     * 判断是否是开盘时间
     * @return
     */
    public static boolean canSendNotif() {
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
                if (hour >= 1 && hour <= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是星期几
     * @param c
     * @return
     */
    public static int getDayOfWeek(Calendar c) {
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

    //转换时间
    public static String dealTime(String timeV){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH:mm:ss");
        SimpleDateFormat formatNew = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = format.parse(timeV);
        } catch (ParseException e) {
        }
        return formatNew.format(date);
    }
}
