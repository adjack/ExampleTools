package com.yuan.lifefinance.tool.bean;

/**
 * copyright:华润创业(深圳)有限公司
 * author:admin
 * create_date:2019/9/23 11:10
 * <p>
 * describe:TODO
 */
public class StockInfoBuyBean {
    private String buyDate;//买入时间
    private String stokeName;//名称
    private double cost;//成本
    private int stockNum;//数量
//    private double buyPrice;//买入价格
    private double failPrice;//止损价格
    private double targetPrice;//最高预期价格
    private double salePrice = 0;//卖出价格
    private String saleDate;//卖出时间

    public StockInfoBuyBean(String buyDate,double failPrice, String stokeName, double cost,int stockNum, double targetPrice, String saleDate,double salePrice) {
        this.buyDate = buyDate;
        this.stokeName = stokeName;
        this.cost = cost;
        this.stockNum = stockNum;
        this.failPrice = failPrice;
        this.targetPrice = targetPrice;
        this.salePrice = salePrice;
        this.saleDate = saleDate;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getStokeName() {
        return stokeName;
    }

    public void setStokeName(String stokeName) {
        this.stokeName = stokeName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public double getFailPrice() {
        return failPrice;
    }

    public void setFailPrice(double failPrice) {
        this.failPrice = failPrice;
    }

    public double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(double targetPrice) {
        this.targetPrice = targetPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }
}
