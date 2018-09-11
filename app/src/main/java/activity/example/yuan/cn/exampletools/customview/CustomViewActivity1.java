package activity.example.yuan.cn.exampletools.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import activity.example.yuan.cn.exampletools.R;

public class CustomViewActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view1);
    }
    public void click1(View view ){
        Log.d("click1,","2---");
    }

}
