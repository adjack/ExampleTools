package com.yuan.lifefinance.tool.httptools;

import android.util.Log;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by jack on 2018/6/5.
 * 拦截网络请求类
 */

public class HttpLogger implements HttpLoggingInterceptor.Logger{
    @Override
    public void log(String message) {
        Log.d("ResponseBodyError", message);
    }

    public static Interceptor getHttpInterceptor(){
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logInterceptor;
    }
}
