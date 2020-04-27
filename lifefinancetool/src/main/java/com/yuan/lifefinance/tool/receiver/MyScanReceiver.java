package com.yuan.lifefinance.tool.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * copyright:华润创业(深圳)有限公司
 * author:admin
 * create_date:2019/12/26 15:30
 * <p>
 * describe:TODO
 */
public class MyScanReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, final Intent intent) {
        Toast.makeText(context,"收到条码信息", Toast.LENGTH_SHORT).show();
        String code = intent.getStringExtra("data");

    }
}
