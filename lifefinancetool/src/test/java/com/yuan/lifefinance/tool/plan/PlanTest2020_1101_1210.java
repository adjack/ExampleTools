package com.yuan.lifefinance.tool.plan;

import com.yuan.lifefinance.tool.ExampleUnitTest;
import com.yuan.lifefinance.tool.util.StockRecordUtil;
import com.yuan.lifefinance.tool.util.ThreadPoolFactory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * copyright:
 * author:admin
 * create_date:2020/9/27 14:09
 * <p>
 * describe:TODO
 */
public class PlanTest2020_1101_1210 {

    //K线倍量时变盘信号
    //社保进场估算持仓成本
    //60日线多头排列
    //盘子相对要小
    //1买之前均线要回抽零轴才可靠

    //操作个股长线
    //第一创业、紫光股份、林洋能源、冀东水泥
    //操作个股趋势
    //林洋能源、荃银高科、四方达


    @Test
    public void runPlanLong(){

        StockRecordUtil.print("20201103","DYCY",9.71,10.26,3800);
        StockRecordUtil.print("20201103","ZGGF",29.92,22.33,1400);
        StockRecordUtil.print("20201103","LYNY",8.07,8.10,4400);
        StockRecordUtil.print("20201103","JDSN",14.65,15.58,1100);
        System.out.println("");
        StockRecordUtil.print("20201104","DYCY",9.65,10.20,3000);
        StockRecordUtil.print("20201104","ZGGF",27.67,22.23,2000);
        StockRecordUtil.print("20201104","LYNY",8.07,8.05,5600);
        StockRecordUtil.print("20201104","JDSN",14.65,15.83,0,1000);
        System.out.println("");
        StockRecordUtil.print("20201106","DYCY",10.14,10.33,9100);
        StockRecordUtil.print("20201106","ZGGF",33.79,23.67,800);
        StockRecordUtil.print("20201106","LYNY",8.07,8.27,0,650);
        StockRecordUtil.print("20201106","YLGF",6.11,6.10,4900);
        System.out.println("");
        StockRecordUtil.print("20201111","DYCY",9.63,10.11,6000);
        StockRecordUtil.print("20201111","ZGGF",100.07,22.66,100);
        StockRecordUtil.print("20201111","JDSN",15.79,16.15,3800);
        StockRecordUtil.print("20201111","YLGF",6.11,6.10,0,3200);
        System.out.println("");
        StockRecordUtil.print("20201112","DYCY",9.75,10.14,8100);
        StockRecordUtil.print("20201112","ZGGF",100.07,22.85,100);
        StockRecordUtil.print("20201112","JDSN",15.85,16.11,4500);

        System.out.println("");
        StockRecordUtil.print("20201117","XYGF",0,0,0,2000);
        StockRecordUtil.print("20201117","CCJG",0,0,0,-630);

        StockRecordUtil.print("20201117","DYCY",9.80,10.19,9800);
        StockRecordUtil.print("20201117","ZGGF",29.54,22.56,1100);
        StockRecordUtil.print("20201117","JDSN",15.85,16.11,4500);


        System.out.println("");
        StockRecordUtil.print("20201120","JDSN",15.85,16.11,4500,-350);
        StockRecordUtil.print("20201120","DYCY",9.24,10.37,3000);
        StockRecordUtil.print("20201120","ZGGF",34.14,22.33,700);
        StockRecordUtil.print("20201120","BYYS",2.88,3.07,10800);
        StockRecordUtil.print("20201120","CNGF",4.28,4.37,7900);
        StockRecordUtil.print("20201120","YXML",5.90,5.82,4200);

        System.out.println("");
        StockRecordUtil.print("20201123","WWGF",4.26,4.27,5900);
        StockRecordUtil.print("20201123","BYYS",2.88,3.07,10800,3000);
        StockRecordUtil.print("20201123","YXML",5.90,0,4200,-600);

        System.out.println("");
        StockRecordUtil.print("20201124","SGGF",4.91,4.89,7200);
        StockRecordUtil.print("20201124","DYCY",9.44,10.39,3200);
        StockRecordUtil.print("20201124","CLGF",4.05,4.71,6900);
        StockRecordUtil.print("20201124","ZGGF",28.37,22.58,1400);
        StockRecordUtil.print("20201124","WWGF",4.26,4.27,5900,-1050);

        System.out.println("");
        StockRecordUtil.print("20201125","SGGF",4.86,5.25,6000);
        StockRecordUtil.print("20201125","DYCY",9.76,10.16,5200);
        StockRecordUtil.print("20201125","CLGF",4.07,4.54,7800);
        StockRecordUtil.print("20201125","ZGGF",105.81,22.22,100);
        StockRecordUtil.print("20201125","ZLZY",2.75,2.76,14700);

        System.out.println("");
        StockRecordUtil.print("20201126","SGGF",4.72,4.73,4700);
        StockRecordUtil.print("20201126","CLGF",4.16,4.43,8300);
        StockRecordUtil.print("20201126","ZGGF",105.81,22.22,100);
        StockRecordUtil.print("20201126","ZBHJ",9.93,9.89,5300);

        System.out.println("");
        StockRecordUtil.print("20201201","ZGGF",105.81,22.22,100);
        StockRecordUtil.print("20201201","SGGF",4.30,5.07,9700);
        StockRecordUtil.print("20201201","XYGJ",31.12,31.20,1300);
        StockRecordUtil.print("20201201","DFZQ",11.91,11.90,2600);
        StockRecordUtil.print("20201201","DHZY",18.76,17.24,1200);

        System.out.println("");
        StockRecordUtil.print("20201209","SGGF",4.22,4.72,7600);
        StockRecordUtil.print("20201209","GZRQ",11.81,12.55,2600);
        StockRecordUtil.print("20201209","DBNN",9.16,9.22,4800);

    }

    @Test
    public void runPlanResult(){
        double value =147600;
        double totalSum = value;
        double rate = 0.15;
        int month = 1*1;
        for(int i=0;i<month;i++){
            totalSum = totalSum + totalSum*rate;
        }
        System.out.println("totalSum："+ value+"----->"+ExampleUnitTest.dealDouble(totalSum,2)+"  ["+ExampleUnitTest.dealDouble(totalSum-value,2)+"]");
        Vector<String> list = new Vector<>();
        list.add(StockRecordUtil.print("20201104","JDSN",14.65,15.83,0,1000));
        list.add(StockRecordUtil.print("20201106","LYNY",8.07,8.27,0,650));
        list.add(StockRecordUtil.print("20201111","YLGF",6.11,6.10,0,3200));
        list.add(StockRecordUtil.print("20201117","XYGF",0,0,0,2000));
        list.add(StockRecordUtil.print("20201117","CCJG",0,0,0,-630));
        list.add(StockRecordUtil.print("20201120","JDSN",15.85,16.11,4500,-350));
        list.add(StockRecordUtil.print("20201123","BYYS",2.88,3.07,10800,3000));
        list.add(StockRecordUtil.print("20201123","YXML",5.90,0,4200,-600));
        list.add(StockRecordUtil.print("20201124","WWGF",4.26,4.27,5900,-1050));
        list.add(StockRecordUtil.print("20201125","ZLZY",2.75,2.73,14700,-430));
        list.add(StockRecordUtil.print("20201126","DYCY",9.76,10.16,5200,2100));
        list.add(StockRecordUtil.print("20201130","ZBHJ",9.93,10.20,5300,1500));
        list.add(StockRecordUtil.print("20201201","CLGF",4.16,4.43,8300,1200));
        list.add(StockRecordUtil.print("20201202","XYGJ",31.12,31.20,1300,-600));
        list.add(StockRecordUtil.print("20201202","DFZQ",11.91,11.90,2600,-2000));
        list.add(StockRecordUtil.print("20201203","CLGF",0,0,0,-3900));
        list.add(StockRecordUtil.print("20201203","FLZY",0,0,0,1500));
        list.add(StockRecordUtil.print("20201209","DFZQ",11.91,11.90,2600,-1500));
        list.add(StockRecordUtil.print("20201209","AYMY",3.30,3.50,0,700));
        double totalSums = 0;
        int failNum = 0;
        for(int i=0;i<list.size();i++){
            totalSums = totalSums+Double.valueOf(list.get(i));
            if(Double.valueOf(list.get(i))<0){
                failNum++;
            }
        }
        System.out.println("doing："+ ExampleUnitTest.dealDouble(totalSums,2)+"    "+failNum+"/"+list.size());
    }

    @Test
    public void runPlanShort(){
        double totalSum = 55670;
        double rate = 0.20;
        int month = 1*1;
        for(int i=0;i<month;i++){
            totalSum = totalSum + totalSum*rate;
        }
        System.out.println("totalSum："+ ExampleUnitTest.dealDouble(totalSum,2));

        StockRecordUtil.print("20201103","QYGK",22.58,20.31,1200);
        StockRecordUtil.print("20201103","SFDD",9.79,10.00,2300);
        StockRecordUtil.print("20201103","LYNY",8.09,8.10,1000);
        System.out.println("");
        StockRecordUtil.print("20201104","QYGK",22.58,20.31,1200);
        StockRecordUtil.print("20201104","SFDD",9.45,10.52,2100);
        StockRecordUtil.print("20201104","LYNY",8.09,8.05,0,-100);

        System.out.println("");
        StockRecordUtil.print("20201104","SFDD",9.88,9.18,4200);
        StockRecordUtil.print("20201104","QYGK",55.86,19.18,100);
    }
}






























