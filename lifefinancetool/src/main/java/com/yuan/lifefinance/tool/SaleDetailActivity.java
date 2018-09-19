package com.yuan.lifefinance.tool;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yuan.lifefinance.tool.greendao.DBManager;
import com.yuan.lifefinance.tool.greendao.StockInfo;
import com.yuan.lifefinance.tool.tools.PermissionTools;
import com.yuan.lifefinance.tool.tools.StringInputUtils;
import com.yuan.lifefinance.tool.view.CustomHintDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SaleDetailActivity extends Activity{

    private TextView tv_time,tv_name,tv_cost,tv_stopLoss,tv_mostPrice,tv_income;
    private TextView tv_rValue,tv_refreshRValue;
    private EditText et_salePrice;


    private double rvalue;
    private String income = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_saledetail);
        findViewById(R.id.iv_return).setOnClickListener(v->finish());
        initView();
        setData();

        tv_refreshRValue.setOnClickListener((View v)-> {
            //计算受益
            try {
                double value =  Double.valueOf(StringInputUtils.value(et_salePrice)) - Double.valueOf(StringInputUtils.value(tv_cost));
                double value2 = value/Double.valueOf(StringInputUtils.value(tv_cost))*100;
                NumberFormat nf = NumberFormat.getNumberInstance();
                nf.setMaximumFractionDigits(2);

                Log.d("setMaximumFractions",nf.format(value2)+"");

                value2 = Double.valueOf(nf.format(value2));
                income = nf.format(value2)+"%";
                tv_income.setText(income);
                tv_income.setTextColor(value2*100 > 0?Color.RED:Color.parseColor("#008B45"));
            }
            catch (Exception ex){}
        });
    }

    private void initView(){
        tv_time = findViewById(R.id.tv_time);
        tv_name = findViewById(R.id.tv_name);
        tv_cost = findViewById(R.id.tv_cost);
        tv_stopLoss = findViewById(R.id.tv_stopLoss);
        tv_mostPrice = findViewById(R.id.tv_mostPrice);
        et_salePrice = findViewById(R.id.et_salePrice);
        tv_rValue = findViewById(R.id.tv_rValue);

        tv_income = findViewById(R.id.tv_income);
        tv_refreshRValue = findViewById(R.id.tv_refreshRValue);
    }

    public void onclickSell(View view){
        startActivity(new Intent(SaleDetailActivity.this,HistoryInfoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(tv_time != null){
            nowDate = "";
            tv_time.setText("日期："+getNowDate());
        }
    }

    private String timeInfoBuy;//买的时间;
    private void setData(){
        try {
            tv_name.setText(getIntent().getStringExtra("stokeName"));
            tv_cost.setText(getIntent().getStringExtra("cost"));
            tv_stopLoss.setText(getIntent().getStringExtra("stopLoss"));
            tv_mostPrice.setText(getIntent().getStringExtra("mostPrice"));

            tv_rValue.setText(getIntent().getDoubleExtra("rValue",0)+"");
            rvalue = getIntent().getDoubleExtra("rValue",0);
            tv_rValue.setTextColor(rvalue < 3?Color.parseColor("#008B45"):Color.RED);

            timeInfoBuy = getIntent().getStringExtra("timeInfoBuy");
            tv_time.setText("日期："+getNowDate());
        }
        catch (Exception ex){}
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


    public void onclick(View view){
//        if(!permissionIsOk){
//            Toast.makeText(MainActivity.this,"请先获取权限",Toast.LENGTH_SHORT).show();
//            return;
//        }

        if(StringInputUtils.valueIsEmpty(et_salePrice)){
            Toast.makeText(SaleDetailActivity.this,"卖出价格不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }

        if(StringInputUtils.valueIsEmpty(tv_income)){
            Toast.makeText(SaleDetailActivity.this,"最终收益不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }

        new CustomHintDialog(SaleDetailActivity.this, ()->updateData(),"出卖股票?","取消", "卖出",CustomHintDialog.Dialog_TYPE_1);


    }

    public void updateData(){
        try {
            //更新数据库信息
            StockInfo stockInfo = DBManager.getInstance().updateStockInfo(nowDate,StringInputUtils.value(et_salePrice),StringInputUtils.value(tv_income),timeInfoBuy);

            //截图保存
            String nowdate = getNowDate().replace(" ","");
            saveToSD(myShot(SaleDetailActivity.this),timeInfoBuy,nowdate,tv_name.getText().toString());
            Toast.makeText(SaleDetailActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SaleDetailActivity.this,HistoryInfoActivity.class));
        }
        catch (Exception ex){
            Toast.makeText(SaleDetailActivity.this,ex.toString(),Toast.LENGTH_SHORT).show();
        }
    }



    public Bitmap myShot(Activity activity) {
        // 获取windows中最顶层的view
        View view = activity.getWindow().getDecorView();
        view.buildDrawingCache();

        // 获取状态栏高度
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int statusBarHeights = rect.top;
        Display display = activity.getWindowManager().getDefaultDisplay();

        // 获取屏幕宽和高
        int widths = display.getWidth();
        int heights = display.getHeight();

        // 允许当前窗口保存缓存信息
        view.setDrawingCacheEnabled(true);

        // 去掉状态栏
        Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0,
                statusBarHeights, widths, heights - statusBarHeights-200);

        // 销毁缓存信息
        view.destroyDrawingCache();
        return bmp;
    }

    private void saveToSD(Bitmap bmp,String timeInfoBuy,String nowDate,String stockName) throws IOException {
        try {
            String dirName = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + "finance"+ File.separator;
            // 判断sd卡是否存在
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File file = new File(dirName+timeInfoBuy+"_"+stockName+File.separator+stockName+"_"+nowDate+".jpg");
                if(!file.exists()){
                    file.createNewFile();
                }
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    if (fos != null) {
                        // 第一参数是图片格式，第二个是图片质量，第三个是输出流
                        bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        // 用完关闭
                        fos.flush();
                        fos.close();
                    }
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        catch(Exception ex){
            Toast.makeText(SaleDetailActivity.this,"error:"+ex.toString(),Toast.LENGTH_SHORT).show();
        }
    }

}
