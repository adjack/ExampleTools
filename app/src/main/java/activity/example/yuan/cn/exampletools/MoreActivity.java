package activity.example.yuan.cn.exampletools;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import activity.example.yuan.cn.exampletools.customview.CustomViewActivity1;
import activity.example.yuan.cn.exampletools.view.CustomView;

public class MoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_view);
    }

//    public void clickCustomView(View view){
//        startActivity(new Intent(this, CustomViewActivity1.class));
//    }
//
//    public void moveViewClick(View view){
//        startActivity(new Intent(this, MoveViewActivity.class));
//    }


}
