package activity.example.yuan.cn.test_xrecyclerview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * XRecyclerView事例[评论列表]
 */
public class RecyclerChatActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager viewpager;
    private New1Fragment new1Fragment;
    private New2Fragment new2Fragment;

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private Button bt_rooftop,bt_News;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        bt_rooftop  = (Button) findViewById(R.id.bt_rooftop);
        bt_News  = (Button)  findViewById(R.id.bt_News);

        bt_rooftop.setOnClickListener(this);
        bt_News.setOnClickListener(this);

        new1Fragment = new New1Fragment();
        new2Fragment = new New2Fragment();

        fragmentList.add(new1Fragment);
        fragmentList.add(new2Fragment);

        viewpager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.bt_rooftop){
            setNavigationBg(1);
            viewpager.setCurrentItem(0);
        }
        else if(view.getId() == R.id.bt_News){
            setNavigationBg(3);
            viewpager.setCurrentItem(1);
        }
    }

    private void setNavigationBg(final int position){
        bt_rooftop.setTextColor(getColorValue(R.color.red1));//6.0 getColor方法过期
        bt_News.setTextColor(getColorValue(R.color.red1));

        bt_rooftop.setBackgroundResource(R.drawable.shape_button_combinerbox_normal);
        bt_News.setBackgroundResource(R.drawable.shape_button_report_normal);

        if(position == 1){
            bt_rooftop.setTextColor(getColorValue(R.color.white));
            bt_rooftop.setBackgroundResource(R.drawable.shape_button_combinerbox_select);
        }
        else if(position == 3){
            bt_News.setTextColor(getColorValue(R.color.white));
            bt_News.setBackgroundResource(R.drawable.shape_button_report_select);
        }

    }

    @SuppressWarnings("deprecation")
    private int getColorValue(int value){
        if(Build.VERSION.SDK_INT >= 23){
            return getResources().getColor(value,null);
        }
        else{
            return getResources().getColor(value);
        }
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm,
                                      ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }

    }
}

