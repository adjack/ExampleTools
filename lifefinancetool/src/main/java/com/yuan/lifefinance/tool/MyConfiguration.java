package com.yuan.lifefinance.tool;

/**
 * Created by 123 on 2018/9/27.
 */

public class MyConfiguration {
    public static final int isTest = 0;//1:正式  0：测试 2：临时验证 3：UAT  4:本地
    public static final boolean openLog = true;

    private static MyConfiguration wyjrConfiguration;

    public static void init(){
        if(wyjrConfiguration == null){
            synchronized (MyConfiguration.class){
                if(wyjrConfiguration == null){
                    wyjrConfiguration = new MyConfiguration();
                }
            }
        }
    }
}
