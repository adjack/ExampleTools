package activity.example.yuan.cn.exampletools.greendao;

import java.util.List;

/**
 * Created by 123 on 2018/4/18.
 */

public class EntityManager {
    private final int cachTime = 60*1000;//缓存时间
    private final String[] cachMethodNames = {"","","","",""};
    private static EntityManager entityManager;
    public static EntityManager getInstance() {
        if (entityManager == null) {
            entityManager = new EntityManager();
        }
        return entityManager;
    }

    /**
     * 更新保存数据
     * @param methodName
     * @param data
     */
    public void updateHistoryInfo(String methodName,String data){
        HistoryInfoDao historyInfoDao =  DBManager.getInstance().getSession().getHistoryInfoDao();
        List<HistoryInfo> historyInfos = historyInfoDao.queryBuilder().where(HistoryInfoDao.Properties.MethodName.eq(methodName)).build().list();
        if(historyInfos.size() > 0){
            HistoryInfo historyInfo = historyInfos.get(0);
            historyInfo.setJsonData(data);
            historyInfoDao.update(historyInfo);
        }
        else{
            historyInfoDao.insert(new HistoryInfo(null,methodName,data,System.currentTimeMillis(),""));
        }
    }

    /**
     * 判断是否获取缓存
     * @param methodName
     * @return
     */
    public boolean canGetLocalData(String methodName){
        boolean result = false;
        for(int i = 0; i < cachMethodNames.length; i++){
            if(methodName.equals(cachMethodNames[i])){
                result =  true;
                break;
            }
        }
        return result;
    }

    /**
     * 获取缓存数据
     * @param methodName
     * @return
     */
    public String getHistoryData(String methodName){
        String result = "";
        HistoryInfoDao historyInfoDao =  DBManager.getInstance().getSession().getHistoryInfoDao();
        List<HistoryInfo> historyInfos = historyInfoDao.queryBuilder().where(HistoryInfoDao.Properties.MethodName.eq(methodName)).build().list();
        if(historyInfos.size() > 0){
            HistoryInfo historyInfo = historyInfos.get(0);
            if(System.currentTimeMillis() - historyInfo.getRequestTime() < cachTime){
                result = historyInfo.getJsonData();
            }
        }
        return result;
    }

}
