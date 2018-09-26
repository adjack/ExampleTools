package com.yuan.lifefinance.tool.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 123 on 2018/9/19.
 */

@Entity
public class TempStockInfo {
    @Id(autoincrement = true)
    private Long id;

    //名称
    private String stokeName;

    //成本区间
    private String costValue;

    //止损价格
    private double stopLoss;

    //R比率
    private double rValue;

    //预期收益
    private String income;

    //最高价格
    private double mostPrice;

    //备注1
    private String discrib1;//股票编码

    //备注2
    private String discrib2;

    //备注3
    private String discrib3;

    //备注4
    private String discrib4;

    @Generated(hash = 1754002871)
    public TempStockInfo(Long id, String stokeName, String costValue,
            double stopLoss, double rValue, String income, double mostPrice,
            String discrib1, String discrib2, String discrib3, String discrib4) {
        this.id = id;
        this.stokeName = stokeName;
        this.costValue = costValue;
        this.stopLoss = stopLoss;
        this.rValue = rValue;
        this.income = income;
        this.mostPrice = mostPrice;
        this.discrib1 = discrib1;
        this.discrib2 = discrib2;
        this.discrib3 = discrib3;
        this.discrib4 = discrib4;
    }

    @Generated(hash = 1657372371)
    public TempStockInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStokeName() {
        return this.stokeName;
    }

    public void setStokeName(String stokeName) {
        this.stokeName = stokeName;
    }

    public String getCostValue() {
        return this.costValue;
    }

    public void setCostValue(String costValue) {
        this.costValue = costValue;
    }

    public double getStopLoss() {
        return this.stopLoss;
    }

    public void setStopLoss(double stopLoss) {
        this.stopLoss = stopLoss;
    }

    public double getRValue() {
        return this.rValue;
    }

    public void setRValue(double rValue) {
        this.rValue = rValue;
    }

    public String getIncome() {
        return this.income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public double getMostPrice() {
        return this.mostPrice;
    }

    public void setMostPrice(double mostPrice) {
        this.mostPrice = mostPrice;
    }

    public String getDiscrib1() {
        return this.discrib1;
    }

    public void setDiscrib1(String discrib1) {
        this.discrib1 = discrib1;
    }

    public String getDiscrib2() {
        return this.discrib2;
    }

    public void setDiscrib2(String discrib2) {
        this.discrib2 = discrib2;
    }

    public String getDiscrib3() {
        return this.discrib3;
    }

    public void setDiscrib3(String discrib3) {
        this.discrib3 = discrib3;
    }

    public String getDiscrib4() {
        return this.discrib4;
    }

    public void setDiscrib4(String discrib4) {
        this.discrib4 = discrib4;
    }

}
