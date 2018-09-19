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

    @Generated(hash = 1717630638)
    public TempStockInfo(Long id, String stokeName, String costValue,
            double stopLoss, double rValue, String income, double mostPrice) {
        this.id = id;
        this.stokeName = stokeName;
        this.costValue = costValue;
        this.stopLoss = stopLoss;
        this.rValue = rValue;
        this.income = income;
        this.mostPrice = mostPrice;
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

}
