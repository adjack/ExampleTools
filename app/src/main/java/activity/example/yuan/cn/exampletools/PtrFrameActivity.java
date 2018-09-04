package activity.example.yuan.cn.exampletools;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import activity.example.yuan.cn.exampletools.view.PtrMDHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

import static java.security.AccessController.getContext;

public class PtrFrameActivity extends AppCompatActivity {
    private PtrFrameLayout mFoodRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptrframe);

        mFoodRefreshLayout = (PtrFrameLayout) findViewById(R.id.food_refreshLayout);
        final PtrMDHeader header = new PtrMDHeader(this);

        mFoodRefreshLayout.setHeaderView(header);
        mFoodRefreshLayout.addPtrUIHandler(header);
        mFoodRefreshLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                //在这里写自己下拉刷新数据的请求   //需要结束刷新头
                new Handler(getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 模仿延时3000毫秒
                        boolean refreshResult = false;
                        header.refreshComplete(refreshResult, frame);
                    }
                }, 3000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {    // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }


}
