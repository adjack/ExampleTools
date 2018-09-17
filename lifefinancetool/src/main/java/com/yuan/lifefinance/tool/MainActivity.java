package com.yuan.lifefinance.tool;

import android.Manifest;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yuan.lifefinance.tool.tools.PermissionTools;
import com.yuan.lifefinance.tool.tools.StringInputUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private TextView tv_time,et_name;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        tv_time = findViewById(R.id.tv_time);
        et_name = findViewById(R.id.et_name);

        permissionTools = PermissionTools.getInstance(this, permissionlist, this);
        permissionTools.requestRunPermission(false);
        setDate();
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
        if(!permissionIsOk){
            Toast.makeText(MainActivity.this,"请先获取权限",Toast.LENGTH_SHORT).show();
            return;
        }

        if(StringInputUtils.valueIsEmpty(tv_time)){
            Toast.makeText(MainActivity.this,"请输入名称！",Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            String nowdate = getNowDate().replace(" ","");
            saveToSD(myShot(MainActivity.this),et_name.toString()+"-"+nowdate);


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
            File dir = new File(dirName+fileName);
            // 判断文件夹是否存在，不存在则创建
            if(!dir.exists()){
                dir.mkdir();
            }

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
