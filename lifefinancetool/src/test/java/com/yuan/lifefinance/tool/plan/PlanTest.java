package com.yuan.lifefinance.tool.plan;

import com.yuan.lifefinance.tool.ExampleUnitTest;
import com.yuan.lifefinance.tool.util.ThreadPoolFactory;
import com.yuan.lifefinance.tool.util.ThreadPoolProxy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * copyright:
 * author:admin
 * create_date:2020/9/27 14:09
 * <p>
 * describe:TODO
 */
public class PlanTest {


    @Test
    public void runPlan(){
        double[] value={147600,159000};//
        System.out.println("value："+getRate(value[0],value[1]));//11->12[7.7%]

        double totalSum = 141000;
        double rate = 0.15;
        int month = 6;
        for(int i=0;i<month;i++){
            totalSum = totalSum + totalSum*rate;
        }
        System.out.println("totalSum："+ ExampleUnitTest.dealDouble(totalSum,2) );
    }

    public String getRate(double value1,double value2){
        double temp = (value2-value1)/value1;
        return ExampleUnitTest.dealDouble(temp*100,1)+"%";
    }

    @Test
    public void runPlan1(){
        List<String> oldValue = new ArrayList<>();
        for(int i=0;i<8;i++){
            oldValue.add((i+1)+"");
        }

        List<String> newValue = new ArrayList<>();
        for(int i=0;i<8;i++){
            newValue.add((i+1+2000)+"");
        }
        List<String> valuet = dealListData(oldValue,newValue);
        System.out.println("totalSum："+ valuet.size());
        for(int i=0;i<valuet.size();i++){
            System.out.print(""+ valuet.get(i)+" ");
        }

    }

    int saveSize = 12;
    private List<String> dealListData(List<String> oldValue,List<String> newValue){
        try {
//            oldValue.addAll(newValue);//直接添加新的集合
            for(int i=0;i<newValue.size();i++){
                oldValue.add(newValue.get(i)+"");
            }
            if(oldValue.size() > saveSize){//判断总集合是否大于1000.大于直接截断处理
                return oldValue.subList(oldValue.size()-saveSize,oldValue.size());
            }
        }
        catch (Exception ex){}
        return oldValue;
    }






    @Test
    public void runThread(){
        List<Object> data = new ArrayList<>();
        for(int i=0;i<10700;i++){
            data.add((i+1)+"");
        }

        long time1 = new Date().getTime();
//        System.out.println(time1);
//        for(int i=0;i<data.size();i++){
//            dealObject(data.get(i)+"");
//        }
//        System.out.println((new Date().getTime()-time1)/1000);
        runMoreThread(data, 3000, new ThreadPoolFactory.ThreadDealContent() {
            @Override
            public void doThing(Object object) {
                dealObject(object);
            }

            @Override
            public void finishProcess() {
                System.out.println("总数"+""+objects.size()+"   "+(new Date().getTime()-time1)/1000);
            }
        });
    }
    volatile LinkedList<Object> objects = new LinkedList<>();
    private synchronized void dealObject(Object ob){
        objects.add(ob);
//        System.out.println(num+"");
    }
    public void runMoreThread(List<Object> data,int oneRunNum,ThreadPoolFactory.ThreadDealContent threadDealContent){
        final int size = data.size()/oneRunNum;//取整
        final int value = data.size()%oneRunNum;//取余
        final int totalNum = size+(value>0?1:0);
        System.out.println("totalNum===="+totalNum);
        Object sycObj = new Object();
        for(int threadNum=0;threadNum<totalNum;threadNum++){
            final int num = threadNum;
            List<Object> oblist = new ArrayList<>();
//            System.out.println("最后一组===="+num +"//"+(totalNum-1));
            if(num == totalNum-1){//最后一组
                List<Object> tempList = data.subList((totalNum-1)*oneRunNum,data.size());
                for(int i=0;i<tempList.size();i++){
                    oblist.add(tempList.get(i));
                }
                System.out.println("最后一组===="+oblist.size());
                ThreadPoolFactory.getNormalPool().execute(new MyRunnable(threadDealContent,oblist,threadNum));
//                System.out.println((totalNum-1)*oneRunNum+"$$$"+data.size());
            }
            else{
                List<Object> tempList = data.subList(num*oneRunNum,(num+1)*oneRunNum);
                for(int i=0;i<tempList.size();i++){
                    oblist.add(tempList.get(i));
                }
                System.out.println("一组===="+oblist.size());
                ThreadPoolFactory.getNormalPool().execute(new MyRunnable(threadDealContent,oblist,threadNum));
//                System.out.println(num*oneRunNum+"//"+(num+1)*oneRunNum  +"       "+ oblist.size());
            }

//            if(totalNum == 1){
//                for(int i=0;i<data.size();i++){
//                    threadDealContent.doThing(data.get(i));
//                    if(data.size()-1 == i){
//                        threadDealContent.finishProcess();
//                    }
//
//                }
//            }
        }

    }

    class MyRunnable implements Runnable{
        volatile ThreadPoolFactory.ThreadDealContent threadDealContent;
        volatile List<Object> oblist;
        volatile int index;

        public  MyRunnable(ThreadPoolFactory.ThreadDealContent threadDealContent,List<Object> objects,int index){
            this.threadDealContent = threadDealContent;
            this.oblist = objects;
            this.index = index;
        }
        @Override
        public void run() {
            System.out.println("执行线程：-------------->"+oblist.size());
//            List<Object> oblist = new ArrayList<>();
            for(int i=0;i<10;i++){
                oblist.add((i+1)+"");
            }
            for(int i=0;i<oblist.size();i++){
                System.out.println(index+"过程：-------------->"+i);
                threadDealContent.doThing(oblist.get(i));
                if(oblist.size()-1 == i){
                    threadDealContent.finishProcess();
                }

            }
        }
    }



}













