package client.pay.renren.com.scanusb;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

import com.google.zxing.ReaderException;
import com.google.zxing.qrcode.QRCodeReader;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.transform.Result;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        QRCodeReader qrCodeReader=new QRCodeReader();
//        try {
//            Result rawResult = qrCodeReader.decode(bitmap);
//        }catch (ReaderException re){
//            //Log.i("解码异常",re.toString());
//        }finally {
//            qrCodeReader.reset();
//        }
    }



}
