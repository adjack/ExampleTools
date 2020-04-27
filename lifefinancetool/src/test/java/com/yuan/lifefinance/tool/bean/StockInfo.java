package com.yuan.lifefinance.tool.bean;

/**
 * copyright:华润创业(深圳)有限公司
 * author:admin
 * create_date:2020/2/18 16:33
 * <p>
 * describe:TODO
 */
public class StockInfo {
    private String stokeName;//名称
    private double cost;//成本
    private int stockNum;//数量
    private double salePrice;//出售价格
    private double buyHour;//持股时间(h)
    String date;//buyTime
    String disc;//阶段描述

    public StockInfo(String stokeName) {
        this.stokeName = stokeName;
    }
    public StockInfo(String stokeName,double cost, int stockNum, double salePrice, double buyHour,String date,String disc) {
        this.stokeName = stokeName;
        this.cost = cost;
        this.stockNum = stockNum;
        this.salePrice = salePrice;
        this.buyHour = buyHour;
        this.date = date;
        this.disc = disc;
    }
    public StockInfo(double cost, int stockNum, double salePrice, double buyHour,String date,String disc) {
        this.cost = cost;
        this.stockNum = stockNum;
        this.salePrice = salePrice;
        this.buyHour = buyHour;
        this.date = date;
        this.disc = disc;
    }

    public String getDate() {
        return date;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public void setDate(String date) {
        this.date = date;
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

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getBuyHour() {
        return buyHour;
    }

    public void setBuyHour(double buyHour) {
        this.buyHour = buyHour;
    }

}
