package com.yuan.lifefinance.tool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private TextView textView;
    private IntentFilter intentFilter;
//    private BarcodeDataReceiver barcodeDataReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView = (TextView) findViewById(R.id.tv_code);

//        intentFilter = new IntentFilter();
//        /*"honeywell.com.BARCODEDATA"为自定义Data Intent Action，用户可以自行修改
//        设置方法：
//        1、进入：设置——Honeywell Setting——扫描设置——Internal Scanner——Default profile——Data Processing Settings
//        2、去掉 Wedge 勾选；
//        3、勾选上 Scan to Intent 和 Data Intent
//        4、点击 Data Intent，点击 Action 输入自定义名称，本例：honeywell.com.BARCODEDATA
//         */
//        intentFilter.addAction("honeywell.com.BARCODEDATA");
//        barcodeDataReceiver = new BarcodeDataReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        registerReceiver(barcodeDataReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        unregisterReceiver(barcodeDataReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(barcodeDataReceiver);
    }

//    class BarcodeDataReceiver extends BroadcastReceiver{
//        @Override
//        public void onReceive(Context context,final Intent intent) {
//            Toast.makeText(context,"收到条码信息", Toast.LENGTH_SHORT).show();
//            String code = intent.getStringExtra("data");
//            textView.setText(code);
//        }
//    }
}