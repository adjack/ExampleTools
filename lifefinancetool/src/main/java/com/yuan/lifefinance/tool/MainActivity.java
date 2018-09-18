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
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yuan.lifefinance.tool.greendao.DBManager;
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

public class MainActivity extends Activity implements PermissionTools.PermissionDealListener{


    HashMap<String, String> permissionlist = new HashMap<>();

    {
//		permissionlist.put(Manifest.permission.READ_PHONE_STATE,"获取手机版本信息");
//		permissionlist.put(Manifest.permission.ACCESS_COARSE_LOCATION,"获取定位信息");
        permissionlist.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, "获取存储权限");
    }
    PermissionTools permissionTools;
    private boolean permissionIsOk;
    private TextView tv_time;

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

    private EditText et_name,et_cost,et_stopLoss,et_mostPrice;
    private TextView tv_rValue,tv_refreshRValue;


    double rvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
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
//                    int result = DBManager.getInstance().savaStockInfo(stokeName,cost,stopLoss,mostPrice,rValue,nowDate);
//                    Log.d("savaStockInfo","result:"+result);
//                }
//            }
//        }.start();


        tv_refreshRValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double value1 =  Double.valueOf(StringInputUtils.value(et_cost)) - Double.valueOf(StringInputUtils.value(et_stopLoss));
                    double value2 =  Double.valueOf(StringInputUtils.value(et_mostPrice)) - Double.valueOf(StringInputUtils.value(et_cost));
                    NumberFormat nf = NumberFormat.getNumberInstance();
                    nf.setMaximumFractionDigits(2);
                    rvalue = value2/value1;
                    if(rvalue < 3){
                        tv_rValue.setTextColor(Color.parseColor("#008B45"));
                    }
                    else{
                        tv_rValue.setTextColor(Color.RED);
                    }
                    tv_rValue.setText(nf.format(rvalue));
                }
                catch (Exception ex){}
            }
        });
    }

    private void initView(){
        tv_time = findViewById(R.id.tv_time);
        et_name = findViewById(R.id.et_name);
        et_cost = findViewById(R.id.et_cost);
        et_stopLoss = findViewById(R.id.et_stopLoss);
        et_mostPrice = findViewById(R.id.et_mostPrice);

        tv_rValue = findViewById(R.id.tv_rValue);
        tv_refreshRValue = findViewById(R.id.tv_refreshRValue);
    }

    public void onclickSell(View view){
        startActivity(new Intent(MainActivity.this,HistoryInfoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(tv_time != null){
            nowDate = "";
            tv_time.setText("日期："+getNowDate());
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


    public void onclick(View view){
//        if(!permissionIsOk){
//            Toast.makeText(MainActivity.this,"请先获取权限",Toast.LENGTH_SHORT).show();
//            return;
//        }

        if(StringInputUtils.valueIsEmpty(et_name)){
            Toast.makeText(MainActivity.this,"请输入名称！",Toast.LENGTH_SHORT).show();
            return;
        }

        if(StringInputUtils.valueIsEmpty(et_cost)){
            Toast.makeText(MainActivity.this,"买入价格为空！",Toast.LENGTH_SHORT).show();
            return;
        }

        if(StringInputUtils.valueIsEmpty(et_stopLoss)){
            Toast.makeText(MainActivity.this,"止损价格为空！",Toast.LENGTH_SHORT).show();
            return;
        }

        if(StringInputUtils.valueIsEmpty(et_mostPrice)){
            Toast.makeText(MainActivity.this,"目标价格为空！",Toast.LENGTH_SHORT).show();
            return;
        }

        if(StringInputUtils.valueIsEmpty(tv_rValue)){
            Toast.makeText(MainActivity.this,"R比率为空！",Toast.LENGTH_SHORT).show();
            return;
        }

        String value = rvalue < 3?"R比率很低哦，谨慎下单哦！":"确定下单！";
        new CustomHintDialog(MainActivity.this, ()->savaData(),value,"取消", "下单",CustomHintDialog.Dialog_TYPE_1);


    }

    public void savaData(){
        try {

            //保存数据库信息
            String stokeName = StringInputUtils.value(et_name);
            String cost = StringInputUtils.value(et_cost);
            double stopLoss = Double.valueOf(StringInputUtils.value(et_stopLoss));
            double mostPrice = Double.valueOf(StringInputUtils.value(et_mostPrice));
            double rValue = Double.valueOf(StringInputUtils.value(tv_rValue));
            int result = DBManager.getInstance().savaStockInfo(stokeName,cost,stopLoss,mostPrice,rValue,nowDate);
            Log.d("savaStockInfo","result:"+result);
            //截图保存
            String nowdate = getNowDate().replace(" ","");
            saveToSD(myShot(MainActivity.this),et_name.getText().toString()+"_"+nowdate);
            Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            Toast.makeText(MainActivity.this,ex.toString(),Toast.LENGTH_SHORT).show();
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

    private void saveToSD(Bitmap bmp,String fileName) throws IOException {
        String dirName = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator + "finance"+ File.separator;
        // 判断sd卡是否存在
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d("saveToSDloo",dirName+fileName);
            File dir1 = new File(dirName+fileName);
            // 判断文件夹是否存在，不存在则创建
            if(!dir1.exists()){
                dir1.mkdir();
            }
//            Log.d("saveToSD",dirName+fileName);
//            File dir2 = new File(dirName+fileName);
//            // 判断文件夹是否存在，不存在则创建
//            if(!dir2.exists()){
//                dir2.mkdir();
//            }
            Log.d("saveToSD",dirName +fileName+ File.separator+fileName+".jpg");
            File file = new File(dirName +fileName+ File.separator+fileName+".jpg");
            // 判断文件是否存在，不存在则创建
            if (!file.exists()) {
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

}
