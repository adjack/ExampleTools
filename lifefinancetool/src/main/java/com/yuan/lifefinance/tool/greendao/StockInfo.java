package com.yuan.lifefinance.tool.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 123 on 2018/9/17.
 */
@Entity
public class StockInfo {
    @Id(autoincrement = true)
    private Long id;

    //名称
    private String stokeName;

    //成本
    private String cost;

    //止损价格
    private double stopLoss;

    //最高价格
    private double mostPrice;

    //出售价格
    private String salePrice;

    //购买时间信息
    private String timeInfoBuy;

    //出卖时间信息
    private String timeInfoSale;

    //最终收益
    private String income;

    //R比率
    private double rValue;

    //备注1
    private String discrib1;

    //备注2
    private String discrib2;

    //备注3
    private String discrib3;

    //备注4
    private String discrib4;

    @Generated(hash = 643845246)
    public StockInfo(Long id, String stokeName, String cost, double stopLoss, double mostPrice, String salePrice, String timeInfoBuy,
            String timeInfoSale, String income, double rValue, String discrib1, String discrib2, String discrib3, String discrib4) {
        this.id = id;
        this.stokeName = stokeName;
        this.cost = cost;
        this.stopLoss = stopLoss;
        this.mostPrice = mostPrice;
        this.salePrice = salePrice;
        this.timeInfoBuy = timeInfoBuy;
        this.timeInfoSale = timeInfoSale;
        this.income = income;
        this.rValue = rValue;
        this.discrib1 = discrib1;
        this.discrib2 = discrib2;
        this.discrib3 = discrib3;
        this.discrib4 = discrib4;
    }

    @Generated(hash = 2120015259)
    public StockInfo() {
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

    public String getCost() {
        return this.cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public double getMostPrice() {
        return this.mostPrice;
    }

    public void setMostPrice(double mostPrice) {
        this.mostPrice = mostPrice;
    }

    public String getSalePrice() {
        return this.salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getIncome() {
        return this.income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public double getRValue() {
        return this.rValue;
    }

    public void setRValue(double rValue) {
        this.rValue = rValue;
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

    public double getStopLoss() {
        return this.stopLoss;
    }

    public void setStopLoss(double stopLoss) {
        this.stopLoss = stopLoss;
    }

    @Override
    public String toString() {
        return "id:"+getId()+"  stokeName:"+getStokeName()+"  cost:"+getCost()+"  stopLoss:"+getStopLoss()+"  mostPrice:"+getMostPrice()+
                "  salePrice:"+getSalePrice()+"  timeInfobug:"+getTimeInfoBuy()+"  timeInfoSale:"+getTimeInfoSale()+"  income:"+getIncome()+"  rValue:"+getRValue()+
                "  discrib1:"+getDiscrib1()+"  discrib2:"+getDiscrib2()+"  discrib3:"+getDiscrib3()+"  discrib4:"+getDiscrib4();
    }

    public String getTimeInfoBuy() {
        return this.timeInfoBuy;
    }

    public void setTimeInfoBuy(String timeInfoBuy) {
        this.timeInfoBuy = timeInfoBuy;
    }

    public String getTimeInfoSale() {
        return this.timeInfoSale;
    }

    public void setTimeInfoSale(String timeInfoSale) {
        this.timeInfoSale = timeInfoSale;
    }
}
