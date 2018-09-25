package com.yuan.lifefinance.tool.httptools;

/**
 * Created by 123 on 2018/4/11.
 */

public abstract class ResponseCallBack<T> {
    /**
     * 成功
     * @param dataCheck data is ok？
     * @param totalNum data num
     * @param response
     * @param rawData
     */
    public abstract void onSuccess(boolean dataCheck,int totalNum,T response,String rawData);

    /**
     * 失败
     * @param t
     * @param rawData
     */
    public abstract void onFailure(Throwable t,String rawData);
}
