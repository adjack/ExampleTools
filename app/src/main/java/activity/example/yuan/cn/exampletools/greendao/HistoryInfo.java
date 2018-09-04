package activity.example.yuan.cn.exampletools.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 历史信息记录表
 * Created by jack on 2018/4/18.
 */

@Entity
public class HistoryInfo {
    @Id(autoincrement = true)
    private Long id;

    //方法名
    private String methodName;

    //json数据
    private String jsonData;

    //获取的时间
    private long requestTime;

    //备注消息
    private String disc;

    @Generated(hash = 1397428351)
    public HistoryInfo(Long id, String methodName, String jsonData,
            long requestTime, String disc) {
        this.id = id;
        this.methodName = methodName;
        this.jsonData = jsonData;
        this.requestTime = requestTime;
        this.disc = disc;
    }

    @Generated(hash = 1690888989)
    public HistoryInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getJsonData() {
        return this.jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public long getRequestTime() {
        return this.requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public String getDisc() {
        return this.disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }
}
