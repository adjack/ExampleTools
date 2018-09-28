package com.yuan.lifefinance.tool;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yuan.lifefinance.tool.greendao.DBManager;
import com.yuan.lifefinance.tool.services.StockPriceService;
import com.yuan.lifefinance.tool.tools.ActivityUtils;
import com.yuan.lifefinance.tool.tools.DoubleTools;
import com.yuan.lifefinance.tool.tools.FileTools;
import com.yuan.lifefinance.tool.tools.LogUtil;
import com.yuan.lifefinance.tool.tools.PermissionTools;
import com.yuan.lifefinance.tool.tools.StringInputUtils;
import com.yuan.lifefinance.tool.tools.TimeTools;
import com.yuan.lifefinance.tool.view.CustomHintDialog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends BaseActivity implements PermissionTools.PermissionDealListener{


    HashMap<String, String> permissionlist = new HashMap<>();

    {
        permissionlist.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, "获取存储权限");
    }
    PermissionTools permissionTools;
    private boolean permissionIsOk;

    @Override
    public void permissionForbidden(ArrayList<String> noGrantpermissionDislist) {
        permissionTools.showPermissionDialog(noGrantpermissionDislist);
    }

    @Override
    public void permissionRefuse(){
    }

    @Override
    public void permissionPass() {
        permissionIsOk = true;
    }

    private EditText et_name,et_code,et_cost,et_stopLoss,et_mostPrice;
    private TextView tv_rValue,tv_refreshRValue,tv_income,tv_time;
    double rvalue;

    @Override
    int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    void initData() {
        initView();
        permissionTools = PermissionTools.getInstance(this, permissionlist, this);
        permissionTools.requestRunPermission(false);
        setDate();

//        new Thread(){
//            @Override
//            public void run() {
//                for(int i = 0; i <50; i++){
//                    String stokeName = "乱七八糟"+i;
//                    String cost = "10.23";
//                    double stopLoss = 10*i;
//                    double mostPrice = 15.3*i;
//                    double rValue = 0.005*i;
//                    String stockCode = "002121";
//                    int result = DBManager.getInstance().savaStockInfo(stokeName,stockCode,cost,stopLoss,mostPrice,rValue,nowDate);
//                    LogUtil.d("savaStockInfo","result:"+result);
//                }
//            }
//        }.start();
        startService(new Intent(this, StockPriceService.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tv_refreshRValue.setOnClickListener((View v) ->{
                try {
                    double value1 =  Double.valueOf(StringInputUtils.value(et_cost)) - Double.valueOf(StringInputUtils.value(et_stopLoss));
                    double value2 =  Double.valueOf(StringInputUtils.value(et_mostPrice)) - Double.valueOf(StringInputUtils.value(et_cost));
                    rvalue = value2/value1;
                    tv_rValue.setTextColor(rvalue < 3?Color.parseColor("#008B45"):Color.RED);
                    tv_rValue.setText(DoubleTools.dealMaximumFractionDigits(rvalue,2));

                    double value3 = Double.valueOf(DoubleTools.dealMaximumFractionDigits(value2/Double.valueOf(StringInputUtils.value(et_cost))*100,2));
                    tv_income.setText(DoubleTools.dealMaximumFractionDigits(value3,2)+"%");

                }
                catch (Exception ex){}
        });
    }

    private void initView(){
        tv_time = findViewById(R.id.tv_time);
        et_name = findViewById(R.id.et_name);
        et_code = findViewById(R.id.et_code);
        et_cost = findViewById(R.id.et_cost);
        et_stopLoss = findViewById(R.id.et_stopLoss);
        et_mostPrice = findViewById(R.id.et_mostPrice);


        tv_rValue = findViewById(R.id.tv_rValue);
        tv_refreshRValue = findViewById(R.id.tv_refreshRValue);
        tv_income = findViewById(R.id.tv_income);
    }

    public void onclickSell(View view){
        startActivity(new Intent(MainActivity.this,HistoryInfoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(tv_time != null){
            nowDate = "";
            tv_time.setText("日期："+TimeTools.dealTime(getNowDate()));
        }
    }

    private void setDate(){
        tv_time.setText("日期："+getNowDate());
    }

    String nowDate = "";
    private String getNowDate(){
        if(TextUtils.isEmpty(nowDate)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH:mm:ss");// HH:mm:ss
            //获取当前时间
            Date date = new Date(System.currentTimeMillis());
            nowDate = simpleDateFormat.format(date);
        }
        return nowDate;
    }

    private boolean isDataOk(){
        if(StringInputUtils.valueIsEmpty(et_name)){
            Toast.makeText(MainActivity.this,"请输入名称！",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(StringInputUtils.valueIsEmpty(et_code)&& StringInputUtils.value(et_code).length() == 6){
            Toast.makeText(MainActivity.this,"请输入个股代码！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StringInputUtils.valueIsEmpty(et_cost)){
            Toast.makeText(MainActivity.this,"买入价格为空！",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(StringInputUtils.valueIsEmpty(et_stopLoss)){
            Toast.makeText(MainActivity.this,"止损价格为空！",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(StringInputUtils.valueIsEmpty(et_mostPrice)){
            Toast.makeText(MainActivity.this,"目标价格为空！",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(StringInputUtils.valueIsEmpty(tv_rValue)){
            Toast.makeText(MainActivity.this,"R比率为空！",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void onclick(View view){
        if(isDataOk()){
            String value = rvalue < 3?"R比率很低哦，谨慎下单哦！":"确定下单！";
            new CustomHintDialog(MainActivity.this, ()->savaData(),value,"取消", "下单",CustomHintDialog.Dialog_TYPE_1);
        }

    }

    public void onclickCompare(View view){
//        if(isDataOk()){
//            new CustomHintDialog(MainActivity.this, ()->savaDataToTempStockInfo(),"添加到比对池","取消", "添加",CustomHintDialog.Dialog_TYPE_1);
//        }
        startActivity(new Intent(MainActivity.this,TempStockInfoActivity.class));
    }

    public void onclickOpenFile(View view){

        // 判断sd卡是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String dirName = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "finance";
            File file = new File(dirName);
            if(null==file || !file.exists()){
                return;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
            Uri contentUri = FileProvider.getUriForFile(MainActivity.this,"com.yuan.lifefinance.tool.fileprovider",file);
            intent.setDataAndType(contentUri, "*/*");
            startActivity(intent);

//            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            Uri contentUri = FileProvider.getUriForFile(MainActivity.this,"com.yuan.lifefinance.tool.fileprovider",file);
//            intent.setDataAndType(contentUri, "*/*");
//            try {
//                startActivity(intent);
//            } catch (ActivityNotFoundException e) {
//                e.printStackTrace();
//            }
        }
    }

    //下单保存
    public void savaData(){
        try {
            //保存数据库信息
            String stokeName = StringInputUtils.value(et_name);
            String cost = StringInputUtils.value(et_cost);
            double stopLoss = Double.valueOf(StringInputUtils.value(et_stopLoss));
            double mostPrice = Double.valueOf(StringInputUtils.value(et_mostPrice));
            double rValue = Double.valueOf(StringInputUtils.value(tv_rValue));
            int result = DBManager.getInstance().savaStockInfo(stokeName,StringInputUtils.value(et_code),cost,stopLoss,mostPrice,rValue,nowDate);
            LogUtil.d("savaStockInfo","result:"+result);
            //截图保存
            String nowdate = getNowDate().replace(" ","");
            FileTools.saveToSD(ActivityUtils.activityShot(MainActivity.this),et_name.getText().toString()+"_"+nowdate);
            Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();

            clearData();
        }
        catch (Exception ex){
            Toast.makeText(MainActivity.this,ex.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    private void clearData(){
        et_name.setText("");
        et_code.setText("");
        et_cost.setText("");
        et_stopLoss.setText("");
        et_mostPrice.setText("");
        tv_rValue.setText("");
        nowDate = "";
        nowDate = getNowDate();
        tv_time.setText("日期："+ TimeTools.dealTime(getNowDate()));
    }






}
