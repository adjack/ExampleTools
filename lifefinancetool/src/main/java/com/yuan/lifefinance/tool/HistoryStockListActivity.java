package com.yuan.lifefinance.tool;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.yuan.lifefinance.tool.adapter.StockHistoryAdapter;
import com.yuan.lifefinance.tool.greendao.DBManager;
import com.yuan.lifefinance.tool.greendao.StockInfo;
import com.yuan.lifefinance.tool.tools.LogUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 正在买的个股列表
 */
public class HistoryStockListActivity extends BaseActivity{
    List<StockInfo> stockInfos = new ArrayList<>();
    int pageIndex = 1;
    int pageSize = 10;
    int maxNum=0;
    private boolean onFall = true;
    @BindView(R.id.lrecycle_list)
    LRecyclerView lrecycle_list;
    private LRecyclerViewAdapter mAdapter;
    private StockHistoryAdapter mDataAdapter;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LoadData(1,pageSize);
    }

    @Override
    int bindLayout() {
        return R.layout.activity_history_stocklist;
    }

    @Override
    void initData() {
        findViewById(R.id.iv_return).setOnClickListener(v->finish());
        myHandler = new MyHandler(this);

        mDataAdapter = new StockHistoryAdapter(this);
        mDataAdapter.addAll(stockInfos);
        mAdapter = new LRecyclerViewAdapter(mDataAdapter);
        initRecyclerView(lrecycle_list,mAdapter);

        lrecycle_list.setOnRefreshListener(()->{
                onFall = true;
                pageIndex = 1;
                LoadData(pageIndex,pageSize);
        });

        lrecycle_list.setOnLoadMoreListener(()->{
                LogUtil.d("onLoadMore",maxNum+"//"+getMaxPage(maxNum,pageSize)+"//"+pageIndex);
                if(getMaxPage(maxNum,pageSize) != pageIndex){
                    onFall=false;
                    pageIndex = pageIndex+1;
                    LoadData(pageIndex,pageSize);
                }
                else{
                    lrecycle_list.setNoMore(true);
                }
        });
//        mAdapter.setOnItemClickListener((View view, int position)-> {
//            try {
//                LogUtil.d("HistoryStockListActivity",position+"/"+stockInfos.size());
//                startActivity(new Intent(HistoryStockListActivity.this,SaleDetailActivity.class)
//                        .putExtra("stokeName",stockInfos.get(position).getStokeName())
//                        .putExtra("cost",stockInfos.get(position).getCost()+"")
//                        .putExtra("stopLoss",stockInfos.get(position).getStopLoss()+"")
//                        .putExtra("mostPrice",stockInfos.get(position).getMostPrice()+"")
//                        .putExtra("rValue",stockInfos.get(position).getRValue())
//                        .putExtra("timeInfoBuy",stockInfos.get(position).getTimeInfoBuy()));
//            }
//            catch (Exception ex){}
//        });

        LoadData(1,pageSize);
    }

    private void LoadData(int page,int pageSize){
        try {
            new Thread(){
                @Override
                public void run() {
                    SystemClock.sleep(100);
                    if(maxNum == 0){
                        maxNum = DBManager.getInstance().getStockInfoHistoryNum();
                        LogUtil.d("HistoryInfoActivity_log","总数："+maxNum);
                    }

                    if(onFall){
                        stockInfos = new ArrayList<>();
                    }
                    List<StockInfo> stockInfosTemp =  DBManager.getInstance().selectStockInfo_history(page,pageSize);
                    for (StockInfo stockInfo:stockInfosTemp){
                        stockInfos.add(stockInfo);
                    }
                    LogUtil.d("HistoryInfoActivity_log","stockInfos.size:"+stockInfos.size());
                    myHandler.sendEmptyMessage(1);
                }
            }.start();
        }
        catch (Exception ex){}

    }

    private MyHandler myHandler;
    private static class MyHandler extends Handler {
        WeakReference<HistoryStockListActivity> weakReference ;
        public MyHandler(HistoryStockListActivity activity ){
            weakReference  = new WeakReference<>(activity) ;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if ( weakReference.get() != null ){
                HistoryStockListActivity instance = weakReference.get();
                LogUtil.d("HistoryStockListActivity","前："+instance.pageIndex+"/"+instance.stockInfos.size());
                instance.mDataAdapter.clear();
                instance.mDataAdapter.addAll(instance.stockInfos);
                instance.lrecycle_list.refreshComplete(instance.pageSize);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDataAdapter != null){
            mDataAdapter.cleanCountDownTimer();
        }
    }
}
