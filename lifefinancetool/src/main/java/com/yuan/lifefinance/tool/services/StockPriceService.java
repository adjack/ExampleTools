package com.yuan.lifefinance.tool.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.yuan.lifefinance.tool.httptools.NetworkFactory;
import com.yuan.lifefinance.tool.httptools.ResponseCallBack;

import org.json.JSONObject;

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


    protected void onHandleIntent(Intent intent) {
        String url = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sz002095&datalen=1&scale=5";
        while (true){
            NetworkFactory.getInstance().get(url,new ResponseCallBack<JSONObject>(){
                @Override
                public void onSuccess(boolean dataCheck, int totalNum, JSONObject response, String rawData) {
                    Log.d("ResponseCallBack",rawData);
                }

                @Override
                public void onFailure(Throwable t, String rawData) {

                }
            });
            SystemClock.sleep(1000);
        }

    }

}