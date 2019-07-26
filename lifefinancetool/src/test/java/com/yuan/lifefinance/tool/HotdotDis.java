package com.yuan.lifefinance.tool;

import com.yuan.lifefinance.tool.bean.HotdotDisBean;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * copyright:华润创业(深圳)有限公司
 * author:admin
 * create_date:2019/7/24 11:07
 * <p>
 * describe:TODO
 */
public class HotdotDis {
    @Test
    public void test012(){
        double a = 576000 - 100000;//贷款额
        double i = 0.0562/12;//月利率
        int n=12*20;//月数

        //每月还款额
        double y = a*i*Math.pow((1+i),n)/(Math.pow(1+i,n)-1);
        System.err.println("提前还贷后最终月供："+y);

        //总利息//632000
        double x = a*n*i*Math.pow((1+i),n)/(Math.pow(1+i,n)-1)-a;
        System.err.println("提前还款剩余总利息："+x);

        System.err.println("未提前还款剩余利息："+(632000-(20*2761+700)));
    }
    @Test
    public void test01(){
        List<HotdotDisBean> list = dis();

        System.out.println("       时间                 日线描述            5分钟描述                                  板块热点                               成交量    操作建议");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getDate()+"       "+dealStr(list.get(i).getDayBuyDisc(),15)+
                    dealStr(list.get(i).getMinuteBuy(),15)+
                            dealStr(list.get(i).getHotdotArea1(),45)+
                                    dealStr(list.get(i).getSizeDisc(),5)+"   "+dealStr(list.get(i).getDoDics(),20));
        }
    }

    public List<HotdotDisBean> dis(){
        List<HotdotDisBean> list=new ArrayList<>();
        list.add(new HotdotDisBean("20190724 15:00 周三","日线2买-失败","5分钟3买-失败",
                "光学光电子3.30/非汽车交运3.00/计算机设备2.79/半导体及元件2.68","无","不操作"));
        list.add(new HotdotDisBean("20190725 15:00 周四","日线2买-失败","5分钟3买-失败",
                "电子制造2.53/半导体及元件1.99/食品加工1.77/物流1.63","无","做日线反弹"));
        return list;
    }

    private String dealStr(String str,int spaceLength){
        String spacestr = "";
        for(int i=0;i<spaceLength-str.length();i++){
            spacestr = spacestr+" ";
        }
//        System.err.println(str.length()+"/"+spacestr.length());
        return str+spacestr;
    }
}
