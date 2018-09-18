package com.yuan.lifefinance.tool;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.yuan.lifefinance.tool.adapter.StockHistoryAdapter;
import com.yuan.lifefinance.tool.greendao.DBManager;
import com.yuan.lifefinance.tool.greendao.StockInfo;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class HistoryInfoActivity extends Activity {
    List<StockInfo> stockInfos = new ArrayList<>();
    int pageIndex = 1;
    int pageSize = 10;
    int maxNum=0;
    private boolean onFall = true;
    LRecyclerView lrecycle_list;
    private LRecyclerViewAdapter mAdapter;
    private StockHistoryAdapter mDataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_info);
        myHandler = new MyHandler(this);

        lrecycle_list = findViewById(R.id.lrecycle_list);
        mDataAdapter = new StockHistoryAdapter(this);
        mDataAdapter.addAll(stockInfos);
        mAdapter = new LRecyclerViewAdapter(mDataAdapter);
        lrecycle_list.setLayoutManager(new LinearLayoutManager(this));
        lrecycle_list.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        lrecycle_list.setArrowImageView(R.mipmap.iconfont_downgrey);
        lrecycle_list.setAdapter(mAdapter);

        lrecycle_list.setOnRefreshListener(new com.github.jdsjlzx.interfaces.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFall = true;
                pageIndex = 1;
                LoadData(pageIndex,pageSize);
            }
        });

        lrecycle_list.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.d("onLoadMore",maxNum+"//"+getMaxPage()+"//"+pageIndex);
                if(getMaxPage() != pageIndex){
                    onFall=false;
                    pageIndex = pageIndex+1;
                    LoadData(pageIndex,pageSize);
                }
                else{
                    lrecycle_list.setNoMore(true);
                }
            }
        });

        LoadData(1,pageSize);

    }

    private int getMaxPage(){
        int value1 = maxNum / pageSize;
        int value2 = maxNum % pageSize;
        if(value2 == 0){
            return value1 + 1;
        }
        else{
            return value1+2;
        }
    }

    private void LoadData(int page,int pageSize){
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(1000);
                if(maxNum == 0){
                    maxNum = DBManager.getInstance().getStockInfoNum();
                    Log.d("HistoryInfoActivity_log","总数："+maxNum);
                }

                if(onFall){
                    stockInfos = new ArrayList<>();
                }
                stockInfos =  DBManager.getInstance().selectStockInfo(page,pageSize);
                Log.d("HistoryInfoActivity_log","stockInfos.size:"+stockInfos.size());
                myHandler.sendEmptyMessage(1);
            }
        }.start();
    }

    private MyHandler myHandler;
    private static class MyHandler extends Handler {
        WeakReference<HistoryInfoActivity> weakReference ;
        public MyHandler(HistoryInfoActivity activity ){
            weakReference  = new WeakReference<>(activity) ;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if ( weakReference.get() != null ){
                HistoryInfoActivity instance = weakReference.get();
                if(instance.pageIndex == 1) instance.mDataAdapter.clear();
//                Log.d("HistoryInfoActivity_log",)
                instance.mDataAdapter.addAll(instance.stockInfos);
//                for (StockInfo stockInfo:instance.stockInfos){
//                    Log.d("HistoryInfoActivity_log",stockInfo.toString());
//                }
                instance.lrecycle_list.refreshComplete(instance.pageSize);
            }
        }
    }
}