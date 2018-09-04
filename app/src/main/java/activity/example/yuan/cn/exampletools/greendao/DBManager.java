package activity.example.yuan.cn.exampletools.greendao;

import activity.example.yuan.cn.exampletools.MyApplication;

/**
 * Created by 123 on 2018/4/18.
 */

public class DBManager {
    private static DBManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DBManager() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getContext(), "wyjr-db", null);
        DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public static DBManager getInstance() {
        if (mInstance == null) {
            mInstance = new DBManager();
        }
        return mInstance;
    }
}
