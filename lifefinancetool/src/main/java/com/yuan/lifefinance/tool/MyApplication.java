package com.yuan.lifefinance.tool;

import android.app.Application;
import android.content.Context;

/**
 * Created by 123 on 2018/4/18.
 */

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        MyConfiguration.init();
        setDefaultUncaughtExceptionHandler();
    }

    public static Context getContext() {
        return mContext;
    }

    private void setDefaultUncaughtExceptionHandler() {
//        CrashHandler handler = CrashHandler.getInstance();
//        handler.init(getApplicationContext());
//        Thread.setDefaultUncaughtExceptionHandler(handler);
    }

    public static Context gainContext(){
        return mContext;
    }

}