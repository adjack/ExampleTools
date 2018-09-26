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
     * 查询个股历史数据
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
    }
    public List<StockInfo> selectBuyingStockInfo(){
        try {
            StockInfoDao stockInfoDao =  DBManager.getInstance().getSession().getStockInfoDao();
            List<StockInfo> stockInfos = stockInfoDao.queryBuilder().where(StockInfoDao.Properties.TimeInfoSale.isNull()).build().list();
            Log.d("selectBuyingStockInfo",stockInfos.size()+"");
            return stockInfos;
        }
        catch (Exception ex){}
        return new ArrayList<>();
    }

    public List<TempStockInfo> selectTempStockInfo(int page,int pageSize){
        try {
            TempStockInfoDao tmepstockInfoDao =  DBManager.getInstance().getSession().getTempStockInfoDao();
            List<TempStockInfo> tempStockInfo = tmepstockInfoDao.queryBuilder().offset((page-1)*pageSize).limit(pageSize).list();
//            List<StockInfo> stockInfos = stockInfoDao.queryBuilder().build().list().s;
            return tempStockInfo;
        }
        catch (Exception ex){}
        return new ArrayList<>();
    }

    public void deleteTempStockInfoFromName(String name){
        TempStockInfoDao tmepstockInfoDao =  DBManager.getInstance().getSession().getTempStockInfoDao();
        TempStockInfo tempStockInfo = tmepstockInfoDao.queryBuilder().where(TempStockInfoDao.Properties.StokeName.eq(name)).build().unique();
        tmepstockInfoDao.delete(tempStockInfo);
    }

    public int getStockInfoNum(){
        try {
            StockInfoDao stockInfoDao =  DBManager.getInstance().getSession().getStockInfoDao();
            return stockInfoDao.queryBuilder().build().list().size();
        }
        catch (Exception ex){}
        return 0;
    }

    //获取临时个股列表
    public int getTempStockInfoNum(){
        try {
            TempStockInfoDao stockInfoDao =  DBManager.getInstance().getSession().getTempStockInfoDao();
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
    public synchronized int savaStockInfo(String stokeName,String stokeCode,String cost,double stopLoss,double mostPrice,double rValue,String timeInfoBug){
        int result = 0;
        try {
            StockInfoDao stockInfoDao =  mDaoSession.getStockInfoDao();
            StockInfo stockInfo = new StockInfo();
            stockInfo.setStokeName(stokeName);
            stockInfo.setDiscrib1(stokeCode);
            stockInfo.setCost(cost);
            stockInfo.setStopLoss(stopLoss);
            stockInfo.setMostPrice(mostPrice);
            stockInfo.setRValue(rValue);
            stockInfo.setTimeInfoBuy(timeInfoBug);
            stockInfoDao.insert(stockInfo);
            result = 1;
        }
        catch (Exception ex){
            Log.d("savaStockInfo","保存信息："+ex.toString());
        }
        return result;
    }

    public synchronized int savaTempStockInfo(String stokeName,String stokeCode,String cost,double stopLoss,double mostPrice,double rValue){
        int result = 0;
        try {
            TempStockInfoDao stockInfoDao =  mDaoSession.getTempStockInfoDao();
            TempStockInfo stockInfo = new TempStockInfo();
            stockInfo.setStokeName(stokeName);
            stockInfo.setDiscrib1(stokeCode);
            stockInfo.setCostValue(cost);
            stockInfo.setStopLoss(stopLoss);
            stockInfo.setMostPrice(mostPrice);
            stockInfo.setRValue(rValue);
            stockInfoDao.insert(stockInfo);
            result = 1;
        }
        catch (Exception ex){
            Log.d("savaStockInfo","保存信息："+ex.toString());
        }
        return result;
    }

    /**
     * 提交卖单.更新数据
     * @param timeInfoSale
     * @param salePrice
     * @param income
     * @param timeInfoBug
     */
    public synchronized StockInfo updateStockInfo(String timeInfoSale,String salePrice,String income,String timeInfoBug){
        StockInfo result = null;
        try {
            StockInfoDao stockInfoDao =  mDaoSession.getStockInfoDao();
            List<StockInfo> stockInfos = stockInfoDao.queryBuilder().where(StockInfoDao.Properties.TimeInfoBuy.eq(timeInfoBug)).build().list();
            if(stockInfos.size() > 0){
                StockInfo stockInfo = stockInfos.get(0);
                stockInfo.setTimeInfoSale(timeInfoSale);
                stockInfo.setSalePrice(salePrice);
                stockInfo.setIncome(income);
                stockInfoDao.update(stockInfo);
                result = stockInfo;
            }
        }
        catch (Exception ex){
            Log.d("savaStockInfo","更新信息："+ex.toString());
        }
        return result;
    }

}
