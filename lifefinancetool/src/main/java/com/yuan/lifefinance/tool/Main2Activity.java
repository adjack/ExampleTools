package com.yuan.lifefinance.tool;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.yuan.lifefinance.tool.tools.CodeUtils;
import com.yuan.lifefinance.tool.tools.DesUtil;

import java.io.File;
import java.util.Random;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    private ImageView image;
    private EditText  et;
    private Button    btn, submit;
    private String    codeStr;
    private CodeUtils codeUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        image = findViewById(R.id.image);
        et = findViewById(R.id.et);
        btn = findViewById(R.id.btn);
        submit = findViewById(R.id.btn_submit);
        btn.setOnClickListener(this);
        submit.setOnClickListener(this);
        getCode();

        File[] files;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            files = getExternalFilesDirs(Environment.MEDIA_MOUNTED);
            for(File file:files){
                Log.e("mainsss",file+"");
            }
        }
    }

    //获取验证码
    private void getCode() {
        codeUtils = CodeUtils.getInstance();
        Bitmap bitmap = codeUtils.createBitmap();
        image.setImageBitmap(bitmap);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                getCode();
                break;
            case R.id.btn_submit:
                codeStr = et.getText().toString().trim();
                Log.e("codeStr", codeStr);
                if (null == codeStr || TextUtils.isEmpty(codeStr)) {
                    Toast.makeText(Main2Activity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = codeUtils.getCode();
                Log.e("code", code);
                if (code.equalsIgnoreCase(codeStr)) {
                    Toast.makeText(Main2Activity.this, "验证码正确", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Main2Activity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    getCode();
                }
                break;
            default:
                break;
        }
    }


    public void encryClick(View view){
        try {
//            byte[] a = {1,2,3,4,5,6,7,8};
//            byte[] a1a = {0,1,3,0,1,3,0,1,3,0,1,2,3,4,5,6};
//            byte[] a1 = com.blankj.utilcode.util.EncryptUtils.encryptDES(a1a,a);
//            for (int i=0; i<a1.length; i++){
//                Log.d("daafe",a1[i]+"");
//            }
//
//            byte[] a2 = EncryptUtils.decryptDES(a1,a);
//            Log.d("daafe",a1.length+"-------------------"+a2.length);
//            for (int i=0; i<a2.length; i++){
//                Log.d("daafe",a2[i]+"");
//            }


            DesUtil tools = new DesUtil();
            byte[] adada = new byte[6];
            for(int i=0; i<adada.length; i++){
                int ce = new Random().nextInt(9);
                adada[i] = (byte) ce;
            }
            String data = new String(adada);
            Log.d("daafe","加密:" + tools.encode(data));
            String data1 = tools.encode(data);
            Log.d("daafe","解密:" + tools.decode(data1));

            byte[] daas = tools.decode(data1).getBytes();//还原
            Log.d("daafe",new String(daas)+"");
//            for (int i=0; i<a1.length; i++){
//                Log.d("daafe",a1[i]+"");
//            }
        }
        catch (Exception ex){
            Log.d("daafe",ex.toString());
        }

    }
}
