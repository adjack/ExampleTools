package activity.example.yuan.cn.exampletools;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import activity.example.yuan.cn.exampletools.view.MyScrollView;
import activity.example.yuan.cn.exampletools.view.ScrollView;
import activity.example.yuan.cn.exampletools.view.ScroolView2;

public class AnimalActivity extends BaseActivity {
    ImageView iv_01;
    FrameLayout frameLayout;
    Animation animation_in,animation_out;
    int[] imageIds = {R.drawable.icon2,R.drawable.icon3};
    List<ImageView> imageViewList = new ArrayList<>();

    public void clickView(View view){
        Toast.makeText(AnimalActivity.this,"click",Toast.LENGTH_SHORT).show();
    }


    private MyScrollView linear_mune_content;
    private ImageButton ib_function_open;

    public void onClickSlide(View view){
        startActivity(new Intent(this, SlideActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        setCanScrollBack(false);
//        linear_mune_content = (MyScrollView) findViewById(R.id.linear_mune_content);
//        ib_function_open = (ImageButton)findViewById(R.id.ib_function_open);
//
//        linear_mune_content.setScrollOnclick(ib_function_open);


//        iv_01 = (ImageView) findViewById(R.id.iv_01);
//        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
//        animation_in = AnimationUtils.loadAnimation(this,R.anim.right_in);
//        animation_in.setFillAfter(true);
//
//        animation_out = AnimationUtils.loadAnimation(this,R.anim.left_out);
//        animation_out.setFillAfter(true);

//        ((ScrollView)findViewById(R.id.myView)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(AnimalActivity.this,"click",Toast.LENGTH_SHORT).show();
//            }
//        });
//        scroolView2 = (ScroolView2) findViewById(R.id.myView);
//        scroolView2.setOnViewClickListener(new ScroolView2.OnViewClickListener(){
//            @Override
//            public void click() {
//                Toast.makeText(AnimalActivity.this,"click",Toast.LENGTH_SHORT).show();
//            }
//        });



    }
    int num = 20;
    ScroolView2 scroolView2;
    public void testClick(View view){
        num = num + 10;
        scroolView2.smoothScrollBy(num,10);
    }

    public void onClickAnim(View view){
        startAnimal();
    }

    //开始执行动画
    public void startAnimal(){
        frameLayout.removeAllViews();
        imageViewList.clear();
        handler.removeCallbacks(runnable);
        flag = 0;
        handler.postDelayed(runnable, 300);
    }


    int flag = 0;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            if(flag < imageIds.length){
                if(flag > 0){
                    exitView(flag-1);
                }
                addView(imageIds[flag]);
                flag++;
                handler.postDelayed(this, 1500);
            }
        }
    };

    private void addView(int id){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(id);
        frameLayout.addView(imageView);
        imageViewList.add(imageView);
        imageView.startAnimation(animation_in);
    }

    private void exitView(int flag){
        imageViewList.get(flag).startAnimation(animation_out);
    }

}
