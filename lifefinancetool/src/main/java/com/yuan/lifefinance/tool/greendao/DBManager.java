package com.yuan.lifefinance.tool.greendao;

import android.graphics.Bitmap;
import android.util.Log;

import com.yuan.lifefinance.tool.MyApplication;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jack on 2018/4/18.
 */

public class DBManager {

    private static DBManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static DBManager getInstance() {
        if (mInstance == null) {
            mInstance = new DBManager();
        }
        return mInstance;
    }
    private DBManager() {
////        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.gainContext(), "wyjr-db", null);
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(MyApplication.gainContext(), "finance-db",null);
        DaoMaster mDaoMaster = new DaoMaster(helper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    /**
     * 查询历史数据
     * @param
     * @param
     */
    public List<StockInfo> selectStockInfo(int page,int pageSize){
        try {
            StockInfoDao stockInfoDao =  DBManager.getInstance().getSession().getStockInfoDao();
            List<StockInfo> stockInfos = stockInfoDao.queryBuilder().offset((page-1)*pageSize).limit(pageSize).list();
//            List<StockInfo> stockInfos = stockInfoDao.queryBuilder().build().list().s;
            return stockInfos;
        }
        catch (Exception ex){}
        return new ArrayList<>();
//        LogUtil.e("MycallBack",methodName+"--->开始更新:"+getMsgJson(param));
    }

    public int getStockInfoNum(){
        try {
            StockInfoDao stockInfoDao =  DBManager.getInstance().getSession().getStockInfoDao();
            return stockInfoDao.queryBuilder().build().list().size();
        }
        catch (Exception ex){}
        return 0;
    }

    /**
     * 保存个股信息
     * @param
     * @param
     */
    public synchronized int savaStockInfo(String stokeName,String cost,double stopLoss,double mostPrice,double rValue,String timeInfo){
        int result = 0;
        try {
            StockInfoDao stockInfoDao =  mDaoSession.getStockInfoDao();
            StockInfo stockInfo = new StockInfo();
            stockInfo.setStokeName(stokeName);
            stockInfo.setCost(cost);
            stockInfo.setStopLoss(stopLoss);
            stockInfo.setMostPrice(mostPrice);
            stockInfo.setRValue(rValue);
            stockInfo.setTimeInfo(timeInfo);
            stockInfoDao.insert(stockInfo);
            result = 1;
        }
        catch (Exception ex){
            Log.d("savaStockInfo","保存信息："+ex.toString());
        }
        return result;

    }

}
