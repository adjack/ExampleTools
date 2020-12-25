package com.yuan.lifefinance.tool.plan;

import com.yuan.lifefinance.tool.ExampleUnitTest;
import com.yuan.lifefinance.tool.util.StockRecordUtil;

import org.junit.Test;

import java.util.Vector;

/**
 * copyright:
 * author:admin
 * create_date:2020/9/27 14:09
 * <p>
 * describe:TODO
 */
public class PlanTest2020_1210_0110 {

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


        System.out.println("");
        StockRecordUtil.print("20201218","DBL*",9.35,9.34,8300);
        StockRecordUtil.print("20201218","SGGF",4.20,4.69,10100);
        StockRecordUtil.print("20201218","CCRQ",0,0,100);
        System.out.println("");
        StockRecordUtil.print("20201221","DBL*",7.10,9.98,2000);
        StockRecordUtil.print("20201221","SGGF",4.20,4.80,10100);
        StockRecordUtil.print("20201221","CCRQ",4.93,5.76,4500);
        StockRecordUtil.print("20201221","YSJT",3.63,3.62,11000);
        System.out.println("");
        StockRecordUtil.print("20201222","ZGZQ",32.18,31.62,1900);
        StockRecordUtil.print("20201222","GZRQ",12.48,12.40,4100);
        StockRecordUtil.print("20201222","CCRQ",5.26,5.65,6600);
        System.out.println("");
        StockRecordUtil.print("20201223","DBL*",9.66,9.84,5400);
        StockRecordUtil.print("20201223","CCRQ",5.22,5.75,5500);
        StockRecordUtil.print("20201223","GZRQ",12.43,12.51,2300);
        StockRecordUtil.print("20201223","YNGF",7.67,7.59,3300);
        StockRecordUtil.print("20201223","QMXC",29.93,29.91,400);
        System.out.println("");
        StockRecordUtil.print("20201224","DBL*",9.76,9.60,5300);
        StockRecordUtil.print("20201224","CCRQ",5.45,6.33,7700);
        StockRecordUtil.print("20201224","SGGF",4.85,4.88,4300);
        System.out.println("");
        StockRecordUtil.print("20201225","DBL*",10.23,9.49,2500);
        StockRecordUtil.print("20201225","CCRQ",5.45,6.97,7700);
        StockRecordUtil.print("20201225","YNGF",7.67,7.84,4400);
        StockRecordUtil.print("20201225","LAHN",6.71,6.81,2200);
        StockRecordUtil.print("20201225","YXML",6.22,6.23,5100);

    }

    @Test
    public void runPlanResult(){
        double value =141000;
        double totalSum = value;
        double rate = 0.20;
        int month = 1*1;
        for(int i=0;i<month;i++){
            totalSum = totalSum + totalSum*rate;
        }
        System.out.println("totalSum："+ value+"----->"+ExampleUnitTest.dealDouble(totalSum,2)+"  ["+ExampleUnitTest.dealDouble(totalSum-value,2)+"]");
        Vector<String> list = new Vector<>();
        list.add(StockRecordUtil.print("20201210","GZRQ",0,0,0,500));
        list.add(StockRecordUtil.print("20201214","DBN*",0,0,0,3000));
        list.add(StockRecordUtil.print("20201215","NPGK",0,0,0,-1500));
        list.add(StockRecordUtil.print("20201217","FLZY",0,0,0,-1500));
        list.add(StockRecordUtil.print("20201218","YLGF",0,0,0,1700));
        list.add(StockRecordUtil.print("20201222","DBL*",7.10,9.98,2000,6100));
        list.add(StockRecordUtil.print("20201222","YSJT",3.63,3.62,11000,-700));
        list.add(StockRecordUtil.print("20201222","SGGF",4.20,4.80,10100,5200));
        list.add(StockRecordUtil.print("20201223","ZGZQ",32.18,31.62,1900,-1000));
        list.add(StockRecordUtil.print("20201224","QMXC",29.93,29.91,400,-200));
        list.add(StockRecordUtil.print("20201224","GZRQ",12.43,12.51,2300,2100));
        list.add(StockRecordUtil.print("20201224","YNGF",7.67,7.59,3300,-400));
        list.add(StockRecordUtil.print("20201225","SGGF",4.85,4.88,4300,-400));
        double totalSums = 0;
        int failNum = 0;
        for(int i=0;i<list.size();i++){
            totalSums = totalSums+Double.valueOf(list.get(i));
            if(Double.valueOf(list.get(i))>0){
                failNum++;
            }
        }
        System.err.println("doing："+ ExampleUnitTest.dealDouble(totalSums,2)+"    "+failNum+"/"+list.size());
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

    }
}






























