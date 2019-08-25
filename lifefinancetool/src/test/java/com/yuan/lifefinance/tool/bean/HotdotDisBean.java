package com.yuan.lifefinance.tool.bean;

/**
 * copyright:华润创业(深圳)有限公司
 * author:admin
 * create_date:2019/7/24 11:10
 * <p>
 * describe:TODO
 */
public class HotdotDisBean {
    public String date;//时间
    public String dayBuyDisc;//日线买卖点描述
    public String minuteBuy;//5分钟买卖点
    public String hotdotArea1;//板块涨幅备注
    public String sizeDisc;//成交量上量/无
    public String doDics;//操作建议

    public HotdotDisBean(String date, String dayBuyDisc, String minuteBuy, String hotdotArea1, String sizeDisc, String doDics) {
        this.date = date;
        this.dayBuyDisc = dayBuyDisc;
        this.minuteBuy = minuteBuy;
        this.hotdotArea1 = hotdotArea1;
        this.sizeDisc = sizeDisc;
        this.doDics = doDics;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayBuyDisc() {
        return dayBuyDisc;
    }

    public void setDayBuyDisc(String dayBuyDisc) {
        this.dayBuyDisc = dayBuyDisc;
    }

    public String getMinuteBuy() {
        return minuteBuy;
    }

    public void setMinuteBuy(String minuteBuy) {
        this.minuteBuy = minuteBuy;
    }

    public String getHotdotArea1() {
        return hotdotArea1;
    }

    public void setHotdotArea1(String hotdotArea1) {
        this.hotdotArea1 = hotdotArea1;
    }

    public String getSizeDisc() {
        return sizeDisc;
    }

    public void setSizeDisc(String sizeDisc) {
        this.sizeDisc = sizeDisc;
    }

    public String getDoDics() {
        return doDics;
    }

    public void setDoDics(String doDics) {
        this.doDics = doDics;
    }
}
