package com.yuan.lifefinance.tool;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.yuan.lifefinance.tool.services.TempStockPriceService;
import com.yuan.lifefinance.tool.tools.ActivityUtils;
import com.yuan.lifefinance.tool.tools.DoubleTools;
import com.yuan.lifefinance.tool.view.CustomHintDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class TempStockInfoActivity extends BaseActivity {
    List<TempStockInfo> stockInfos = new ArrayList<>();
    int pageIndex = 1;
    int pageSize = 20;
    int maxNum=0;
    private boolean onFall = true;
    LRecyclerView lrecycle_list;
    private LRecyclerViewAdapter mAdapter;
    private TempStockHistoryAdapter mDataAdapter;

    Intent serviceIntent;
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        try {
            onFall = true;
            mDataAdapter.clear();
            stockInfos.clear();
            LoadData(1,pageSize);
            startService(serviceIntent);
        }
        catch (Exception ex){}

    }
    @Override
    int bindLayout() {
        return R.layout.activity_tempstock_info;
    }

    @Override
    void initData() {
        serviceIntent = new Intent(this, TempStockPriceService.class);
        findViewById(R.id.iv_return).setOnClickListener(v->finish());
        myHandler = new MyHandler(this);

        lrecycle_list = findViewById(R.id.lrecycle_list);
        mDataAdapter = new TempStockHistoryAdapter(this);
        mDataAdapter.addAll(stockInfos);
        mAdapter = new LRecyclerViewAdapter(mDataAdapter);
        initRecyclerView(lrecycle_list,mAdapter);
//        lrecycle_list.setLayoutManager(new LinearLayoutManager(this));
//        lrecycle_list.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        lrecycle_list.setArrowImageView(R.mipmap.iconfont_downgrey);
//        lrecycle_list.setAdapter(mAdapter);


        lrecycle_list.setOnRefreshListener(()->{
                onFall = true;
                pageIndex = 1;
                LoadData(pageIndex,pageSize);
        });

        lrecycle_list.setOnLoadMoreListener(()->{
                Log.d("onLoadMore",maxNum+"//"+getMaxPage(maxNum,pageSize)+"//"+pageIndex);
                if(getMaxPage(maxNum,pageSize) != pageIndex){
                    onFall=false;
                    pageIndex = pageIndex+1;
                    LoadData(pageIndex,pageSize);
                }
                else{
                    lrecycle_list.setNoMore(true);
                }
        });
        mAdapter.setOnItemLongClickListener((View view, int position)-> {
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

        //开启更新服务
        startService(serviceIntent);

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.yuan.action.stock.price.change");
        registerReceiver(myReceiver, filter);
    }

//    private int getMaxPage(){
//        int value1 = maxNum / pageSize;
//        int value2 = maxNum % pageSize;
//        if(value2 == 0){
//            return value1 + 1;
//        }
//        else{
//            return value1+2;
//        }
//    }

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
                List<TempStockInfo> stockInfosTemp =  DBManager.getInstance().selectTempStockInfo(page,pageSize);
                for (TempStockInfo tempStockInfo:stockInfosTemp){
                    stockInfos.add(tempStockInfo);
                }
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
                instance.mDataAdapter.clear();
//                Log.d("HistoryInfoActivity_log",)
                instance.mDataAdapter.addAll(instance.stockInfos);
//                for (StockInfo stockInfo:instance.stockInfos){
//                    Log.d("HistoryInfoActivity_log",stockInfo.toString());
//                }
                instance.lrecycle_list.refreshComplete(instance.pageSize);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myReceiver != null){
            unregisterReceiver(myReceiver);
        }
        if(serviceIntent != null) stopService(serviceIntent);
    }

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null){
                try {
                    String stockName = intent.getStringExtra("stockName");
                    double closePrice = intent.getDoubleExtra("closePrice",-1);
                    double openPrice = intent.getDoubleExtra("openPrice",-1);
                    String position = intent.getStringExtra("position");
                    Log.d("onReceive_log",stockName+"/"+closePrice+"/"+position);
                    TempStockInfo tempStockInfo = stockInfos.get(Integer.valueOf(position));
                    if(tempStockInfo.getStokeName().equals(stockName)){//防止数据紊乱
                        tempStockInfo.setDiscrib2(DoubleTools.dealMaximumFractionDigits(closePrice,2));
                        tempStockInfo.setDiscrib3(DoubleTools.dealMaximumFractionDigits(openPrice,2));
                        mDataAdapter.notifyDataSetChanged();
                    }
                }catch (Exception ex){}

            }
        }

    };


}
