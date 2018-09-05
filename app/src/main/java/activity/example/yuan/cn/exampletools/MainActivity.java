package activity.example.yuan.cn.exampletools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import activity.example.yuan.cn.exampletools.http.GetRequest_Interface;
import activity.example.yuan.cn.exampletools.view.DrawView;
import activity.example.yuan.cn.test_webview.WebActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public void recyclerviewOnclick(View view){
        Toast.makeText(this,"--",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,WebActivity.class));
    }

    class CustomView1 extends View{

        Paint paint;

        public CustomView1(Context context) {
            super(context);
            paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
            paint.setColor(Color.YELLOW);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(3);
        }

        //在这里我们将测试canvas提供的绘制图形方法
        @Override
        protected void onDraw(Canvas canvas) {

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Log.d("displayMetrics",displayMetrics.densityDpi+"/"+displayMetrics.heightPixels);

//        iv_draw = (ImageView) findViewById(R.id.iv_draw);

        initCaoGaoPop();
        iv_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"start",Toast.LENGTH_SHORT).show();
            }
        });
//        setNetRequest("https://webapi.wyjr168.com/API/WebAPI/UserService");
    }

    public void onclickSHU(View view){
//        startActivity(new Intent(this,PtrFrameActivity.class));
//        startActivity(new Intent(this,AdRunActivity.class));
//        startActivity(new Intent(this,GreenDaoActivity.class));
//        startActivity(new Intent(this,WebViewActivity.class));
//        startActivity(new Intent(this,AnimalActivity.class));
        startActivity(new Intent(this,OnclickStyleActivity.class));
    }

    public void onclickMore(View view){
        startActivity(new Intent(this,OnclickStyleActivity.class));
    }


    //测试网络请求
    public void setNetRequest(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Call<Object> call =  retrofit.create(GetRequest_Interface.class).getCall();
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

    }

    @BindView(R.id.iv_draw)
    ImageView iv_draw;

    public void onClickDraw(View view){
        if (pop != null && !pop.isShowing()) {
            pop.showAsDropDown(findViewById(R.id.button));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    PopupWindow pop = null;

    private void initCaoGaoPop() {
        View viewMenu = getLayoutInflater().inflate(R.layout.pop_caogao_view,
                null);
        LinearLayout linear_title = (LinearLayout) viewMenu
                .findViewById(R.id.linear_title);
        LinearLayout linear = (LinearLayout) viewMenu.findViewById(R.id.linear);
        final DrawView view = new DrawView(this);
        linear.addView(view);
        view.setBackgroundColor(getResources().getColor(R.color.gray));
        linear_title.setBackgroundColor(getResources().getColor(R.color.gray));
        linear_title.getBackground().setAlpha(200);
        view.getBackground().setAlpha(200);

        TextView tv_clean = viewMenu.findViewById(R.id.tv_clean);
        TextView tv_up =viewMenu.findViewById(R.id.tv_up);
        TextView tv_down = viewMenu.findViewById(R.id.tv_down);
        TextView tv_newcreate = viewMenu.findViewById(R.id.tv_newcreate);
        pop = new PopupWindow(viewMenu, DrawerLayout.LayoutParams.MATCH_PARENT,
                DrawerLayout.LayoutParams.WRAP_CONTENT);
        pop.setOutsideTouchable(true);
        pop.setTouchable(true);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setFocusable(true);

        pop.getContentView().setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (pop.isShowing()) {
                    pop.dismiss();
                }
                return false;
            }

        });
        tv_clean.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                view.clean();
            }
        });

        tv_up.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                view.updo();
            }
        });

        tv_down.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                view.downdo();

                iv_draw.setImageBitmap(view.getDrawBitmap());
            }
        });

        tv_newcreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                view.newCreate();
            }
        });
    }
}
