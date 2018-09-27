package com.yuan.lifefinance.tool.tools;

import android.util.Log;

import com.yuan.lifefinance.tool.MyConfiguration;

/**
 * Created by jack on 2016/9/21.
 */
public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;

    public static final int NORMAL = 6;// on line
    public static final int LEVEL  = VERBOSE;

    public static boolean ifCloseLog(){
//        if(WyjrConfiguration.isTest != 1){
//            return true;
//        }
//        return false;
        return MyConfiguration.openLog;
    }

    public static void v(String tag,String msg){
        if(LEVEL <= VERBOSE && ifCloseLog()){
            Log.v(tag,msg);
        }

    }

    public static void d(String tag,String msg){
        if(LEVEL <= DEBUG && ifCloseLog()){
            Log.d(tag,msg);
        }

    }

    public static void i(String tag,String msg){
        if(LEVEL <= INFO && ifCloseLog()){
            Log.i(tag,msg);
        }

    }

    public static void w(String tag,String msg){
        if(LEVEL <= WARN && ifCloseLog()){
            Log.w(tag,msg);
        }

    }

    public static void e(String tag,String msg){
        if(LEVEL <= ERROR && ifCloseLog()){
            Log.e(tag,msg);
        }
    }

}
