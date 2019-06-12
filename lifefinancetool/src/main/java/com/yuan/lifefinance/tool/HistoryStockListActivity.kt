package com.yuan.lifefinance.tool

import android.content.Intent
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.view.View

import com.github.jdsjlzx.recyclerview.LRecyclerView
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.yuan.lifefinance.tool.adapter.StockHistoryAdapter
import com.yuan.lifefinance.tool.greendao.DBManager
import com.yuan.lifefinance.tool.greendao.StockInfo
import com.yuan.lifefinance.tool.tools.LogUtil

import java.lang.ref.WeakReference
import java.util.ArrayList

import butterknife.BindView

/**
 * 正在买的个股列表
 */
class HistoryStockListActivity : BaseActivity() {
    internal var stockInfos: MutableList<StockInfo> = ArrayList()
    internal var pageIndex = 1
    internal var pageSize = 10
    internal var maxNum = 0
    private var onFall = true
    @BindView(R.id.lrecycle_list)
    internal var lrecycle_list: LRecyclerView? = null
    private var mAdapter: LRecyclerViewAdapter? = null
    private var mDataAdapter: StockHistoryAdapter? = null

    private var myHandler: MyHandler? = null

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        LoadData(1, pageSize)
    }

    internal override fun bindLayout(): Int {
        return R.layout.activity_history_stocklist
    }

    internal override fun initData() {
        findViewById<View>(R.id.iv_return).setOnClickListener { v -> finish() }
        myHandler = MyHandler(this)

        mDataAdapter = StockHistoryAdapter(this)
        mDataAdapter!!.addAll(stockInfos)
        mAdapter = LRecyclerViewAdapter(mDataAdapter)
        initRecyclerView(lrecycle_list, mAdapter)

        lrecycle_list!!.setOnRefreshListener {
            onFall = true
            pageIndex = 1
            LoadData(pageIndex, pageSize)
        }

        lrecycle_list!!.setOnLoadMoreListener {
            LogUtil.d("onLoadMore", maxNum.toString() + "//" + getMaxPage(maxNum, pageSize) + "//" + pageIndex)
            if (getMaxPage(maxNum, pageSize) != pageIndex) {
                onFall = false
                pageIndex = pageIndex + 1
                LoadData(pageIndex, pageSize)
            } else {
                lrecycle_list!!.setNoMore(true)
            }
        }
        LoadData(1, pageSize)
    }

    private fun LoadData(page: Int, pageSize: Int) {
        try {
            object : Thread() {
                override fun run() {
                    SystemClock.sleep(100)
                    if (maxNum == 0) {
                        maxNum = DBManager.getInstance().stockInfoHistoryNum
                        LogUtil.d("HistoryInfoActivity_log", "总数：$maxNum")
                    }

                    if (onFall) {
                        stockInfos = ArrayList()
                    }
                    val stockInfosTemp = DBManager.getInstance().selectStockInfo_history(page, pageSize)
                    for (stockInfo in stockInfosTemp) {
                        stockInfos.add(stockInfo)
                    }
                    LogUtil.d("HistoryInfoActivity_log", "stockInfos.size:" + stockInfos.size)
                    myHandler!!.sendEmptyMessage(1)
                }
            }.start()
        } catch (ex: Exception) {
        }

    }

    private class MyHandler(activity: HistoryStockListActivity) : Handler() {
        internal var weakReference: WeakReference<HistoryStockListActivity>

        init {
            weakReference = WeakReference(activity)
        }

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (weakReference.get() != null) {
                val instance = weakReference.get()
                LogUtil.d("HistoryStockListActivity", "前：" + instance.pageIndex + "/" + instance.stockInfos.size)
                instance.mDataAdapter!!.clear()
                instance.mDataAdapter!!.addAll(instance.stockInfos)
                instance.lrecycle_list!!.refreshComplete(instance.pageSize)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mDataAdapter != null) {
            mDataAdapter!!.cleanCountDownTimer()
        }
    }
}
