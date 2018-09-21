package com.yuan.lifefinance.tool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.interfaces.OnItemLongClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.yuan.lifefinance.tool.adapter.StockHistoryAdapter;
import com.yuan.lifefinance.tool.adapter.TempStockHistoryAdapter;
import com.yuan.lifefinance.tool.greendao.DBManager;
import com.yuan.lifefinance.tool.greendao.StockInfo;
import com.yuan.lifefinance.tool.greendao.TempStockInfo;
import com.yuan.lifefinance.tool.view.CustomHintDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class TempStockInfoActivity extends Activity {
    List<TempStockInfo> stockInfos = new ArrayList<>();
    int pageIndex = 1;
    int pageSize = 20;
    int maxNum=0;
    private boolean onFall = true;
    LRecyclerView lrecycle_list;
    private LRecyclerViewAdapter mAdapter;
    private TempStockHistoryAdapter mDataAdapter;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        try {
            onFall = true;
            mDataAdapter.clear();
            stockInfos.clear();
            LoadData(1,pageSize);
        }
        catch (Exception ex){}

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempstock_info);
        findViewById(R.id.iv_return).setOnClickListener(v->finish());
        myHandler = new MyHandler(this);

        lrecycle_list = findViewById(R.id.lrecycle_list);
        mDataAdapter = new TempStockHistoryAdapter(this);
        mDataAdapter.addAll(stockInfos);
        mAdapter = new LRecyclerViewAdapter(mDataAdapter);
        lrecycle_list.setLayoutManager(new LinearLayoutManager(this));
//        DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(this,R.drawable.custom_divider));
//        lrecycle_list.addItemDecoration(divider);
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
        mAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                try {
                    new CustomHintDialog(TempStockInfoActivity.this, ()->{
                        DBManager.getInstance().deleteTempStockInfoFromName(stockInfos.get(position).getStokeName());
                        stockInfos.remove(position);
                        mDataAdapter.clear();
                        mDataAdapter.addAll(stockInfos);
                        mDataAdapter.notifyDataSetChanged();
                        Toast.makeText(TempStockInfoActivity.this,"删除成功!",Toast.LENGTH_SHORT).show();
                    },"删除本条信息？","取消", "删除",CustomHintDialog.Dialog_TYPE_1);
                }
                catch (Exception ex){}

            }
        });
//        mAdapter.setOnItemClickListener((View view, int position)-> {
//                startActivity(new Intent(TempStockInfoActivity.this,SaleDetailActivity.class)
//                .putExtra("stokeName",stockInfos.get(position).getStokeName())
//                .putExtra("cost",stockInfos.get(position).getCost()+"")
//                .putExtra("stopLoss",stockInfos.get(position).getStopLoss()+"")
//                .putExtra("mostPrice",stockInfos.get(position).getMostPrice()+"")
//                .putExtra("rValue",stockInfos.get(position).getRValue())
//                .putExtra("timeInfoBuy",stockInfos.get(position).getTimeInfoBuy()));
////                new CustomHintDialog(HistoryInfoActivity.this, str->{
////                    Toast.makeText(HistoryInfoActivity.this,stockInfos.get(position).getRValue()+"",Toast.LENGTH_SHORT).show();
////                },"取消", "卖出");
//        });

        LoadData(1,pageSize);

        findViewById(R.id.tv_add).setOnClickListener(v->startActivity(new Intent(TempStockInfoActivity.this,CompareStockActivity.class)));

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
                SystemClock.sleep(100);
                if(maxNum == 0){
                    maxNum = 18;
                    Log.d("HistoryInfoActivity_log","总数："+maxNum);
                }

                if(onFall){
                    stockInfos = new ArrayList<>();
                }
                stockInfos =  DBManager.getInstance().selectTempStockInfo(page,pageSize);
                Log.d("HistoryInfoActivity_log","stockInfos.size:"+stockInfos.size());
                myHandler.sendEmptyMessage(1);
            }
        }.start();
    }

    private MyHandler myHandler;
    private static class MyHandler extends Handler {
        WeakReference<TempStockInfoActivity> weakReference ;
        public MyHandler(TempStockInfoActivity activity ){
            weakReference  = new WeakReference<>(activity) ;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if ( weakReference.get() != null ){
                TempStockInfoActivity instance = weakReference.get();
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
