package activity.example.yuan.cn.exampletools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import activity.example.yuan.cn.exampletools.greendao.DaoMaster;
import activity.example.yuan.cn.exampletools.greendao.DaoSession;
import activity.example.yuan.cn.exampletools.greendao.HistoryInfo;
import activity.example.yuan.cn.exampletools.greendao.HistoryInfoDao;

public class GreenDaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);

//        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "my-db", null);
//        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
//        DaoSession daoSession = daoMaster.newSession();
//        HistoryInfoDao historyInfoDao = daoSession.getHistoryInfoDao();
//
//
//        List<HistoryInfo> list =  historyInfoDao.queryBuilder().build().list();
//        Log.d("DevOpenHelper",list.size()+"");
//        if(list.size() == 0){//增加
//            HistoryInfo historyInfo = new HistoryInfo(null,"method1","data1", System.currentTimeMillis(),"");
//            historyInfoDao.insert(historyInfo);
//            historyInfo = new HistoryInfo(null,"method2","data2", System.currentTimeMillis(),"");
//            historyInfoDao.insert(historyInfo);
//        }
//
//
//        if(list.size() > 0){
//            List<HistoryInfo> lists =  historyInfoDao.queryBuilder().where(
//                    HistoryInfoDao.Properties.MethodName.eq("method1"),
//                    HistoryInfoDao.Properties.Id.eq(1))
//                    .build().list();
//            lists.get(0).setMethodName("method4");
//            historyInfoDao.update(lists.get(0));
//
//        }
//        list =  historyInfoDao.queryBuilder().build().list();
//
//        for(int i = 0; i < list.size(); i++){
//            Log.d("DevOpenHelper","查询数据到："+list.get(i).getId()+"/"+list.get(i).getMethodName()+"/"+list.get(i).getJsonData()+"/"+list.get(i).getRequestTime()+"/"+list.get(i).getDisc());
//        }






    }


}
