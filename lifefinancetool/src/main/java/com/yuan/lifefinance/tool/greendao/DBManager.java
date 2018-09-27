package com.yuan.lifefinance.tool.greendao;

import com.yuan.lifefinance.tool.MyApplication;
import com.yuan.lifefinance.tool.tools.LogUtil;

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
     * 查询个股历史数据[分页]
     * @param
     * @param
     */
    public List<StockInfo> selectStockInfo_history(int page,int pageSize){
        try {
            StockInfoDao stockInfoDao =  DBManager.getInstance().getSession().getStockInfoDao();
            List<StockInfo> stockInfos = stockInfoDao.queryBuilder().where(StockInfoDao.Properties.TimeInfoSale.isNotNull()).offset((page-1)*pageSize).limit(pageSize).list();
//            List<StockInfo> stockInfos = stockInfoDao.queryBuilder().build().list().s;
            return stockInfos;
        }
        catch (Exception ex){}
        return new ArrayList<>();
    }

    /**
     * 查询持仓个股数据[分页]
     * @param
     * @param
     */
    public List<StockInfo> selectStockInfo_buying(int page,int pageSize){
        try {
            StockInfoDao stockInfoDao =  DBManager.getInstance().getSession().getStockInfoDao();
            List<StockInfo> stockInfos = stockInfoDao.queryBuilder().where(StockInfoDao.Properties.TimeInfoSale.isNull()).offset((page-1)*pageSize).limit(pageSize).list();
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
            LogUtil.d("selectBuyingStockInfo",stockInfos.size()+"");
            return stockInfos;
        }
        catch (Exception ex){}
        return new ArrayList<>();
    }

    public StockInfo selectStockInfoById(long id){
        try {
            StockInfoDao stockInfoDao =  DBManager.getInstance().getSession().getStockInfoDao();
            StockInfo stockInfo = stockInfoDao.queryBuilder().where(StockInfoDao.Properties.Id.eq(id)).build().unique();
            return stockInfo;
        }
        catch (Exception ex){}
        return null;
    }
    public void updateBuyingStockInfo(long id,String price){
        try {
            StockInfoDao stockInfoDao =  DBManager.getInstance().getSession().getStockInfoDao();
            StockInfo stockInfo = stockInfoDao.queryBuilder().where(StockInfoDao.Properties.Id.eq(id)).build().unique();
            stockInfo.setDiscrib2(price);
            stockInfoDao.update(stockInfo);
        }
        catch (Exception ex){}
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

    public int getStockInfoBuyingNum(){
        try {
            StockInfoDao stockInfoDao =  DBManager.getInstance().getSession().getStockInfoDao();
            return stockInfoDao.queryBuilder().where(StockInfoDao.Properties.TimeInfoSale.isNull()).build().list().size();
        }
        catch (Exception ex){}
        return 0;
    }

    public int getStockInfoHistoryNum(){
        try {
            StockInfoDao stockInfoDao =  DBManager.getInstance().getSession().getStockInfoDao();
            return stockInfoDao.queryBuilder().where(StockInfoDao.Properties.TimeInfoSale.isNotNull()).build().list().size();
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
            if(mostPrice > 300){
                stockInfo.setTimeInfoSale(timeInfoBug);
            }
            stockInfoDao.insert(stockInfo);
            result = 1;
        }
        catch (Exception ex){
            LogUtil.d("savaStockInfo","保存信息："+ex.toString());
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
            LogUtil.d("savaStockInfo","保存信息："+ex.toString());
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
            LogUtil.d("savaStockInfo","更新信息："+ex.toString());
        }
        return result;
    }

}
