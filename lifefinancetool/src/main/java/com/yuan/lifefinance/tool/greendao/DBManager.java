package com.yuan.lifefinance.tool.greendao;

import android.graphics.Bitmap;


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
//        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.gainContext(), "wyjr-db", null);
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(MyApplication.gainContext(), "wyjr-db",null);
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
     * 更新保存接口数据
     * @param methodName
     * @param data
     */
    public void updateHistoryInfo(String methodName,String data,String param){
//        try {
//            HistoryInfoDao historyInfoDao =  DBManager.getInstance().getSession().getHistoryInfoDao();
//            List<HistoryInfo> historyInfos = historyInfoDao.queryBuilder().where(
//                    HistoryInfoDao.Properties.MethodName.eq(methodName),
//                    HistoryInfoDao.Properties.Param.eq(getMsgJson(param))).build().list();
//            if(historyInfos.size() > 0){
//                HistoryInfo historyInfo = historyInfos.get(0);
//                historyInfo.setJsonData(data);
//                historyInfo.setRequestTime(System.currentTimeMillis());
//                historyInfoDao.update(historyInfo);
//            }
//            else{
//                historyInfoDao.insert(new HistoryInfo(null,methodName,data,System.currentTimeMillis(),getMsgJson(param),""));
//            }
//        }
//        catch (Exception ex){}
//        LogUtil.e("MycallBack",methodName+"--->开始更新:"+getMsgJson(param));
    }

    /**
     * 保存图片数据
     * @param imageUrl
     * @param bitmap
     */
    public synchronized void updatePictureInfo(String imageUrl, Bitmap bitmap){
//        try {
//            PictureInfoDao pictureInfoDao =  DBManager.getInstance().getSession().getPictureInfoDao();
//            List<PictureInfo> pictureInfos = pictureInfoDao.queryBuilder().where(
//                    PictureInfoDao.Properties.ImageUrl.eq(imageUrl)).build().list();
//            if(pictureInfos.size() > 0){
//                PictureInfo pictureInfo = pictureInfos.get(0);
//                pictureInfo.setPicData(Base64.encode(ImageUtil.formatBitmapToByte(bitmap)));
//                pictureInfo.setRequestTime(System.currentTimeMillis());
//                pictureInfoDao.update(pictureInfo);
//            }
//            else{
//                ToolFile.saveCrashInfoToFile("保存图片："+imageUrl);
//                pictureInfoDao.insert(new PictureInfo(null,imageUrl,Base64.encode(ImageUtil.formatBitmapToByte(bitmap)),System.currentTimeMillis(),0,""));
//            }
//        }
//        catch (Exception ex){
//            ToolFile.saveCrashInfoToFile("保存图片error："+ex.toString());
//            LogUtil.d("requestGetHotActivityList","保存图片error："+ex.toString());
//        }

    }

}
