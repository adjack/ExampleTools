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
    public void test011(){
//        byte[] a = {1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,
//                1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8};
//        byte[] a1 = EncryptUtils.encryptAES2Base64(a,a);
        double[] point1 = {100,200,340,300};//左上角坐标   右下角坐标
        double a = 500;//图片宽
        double b = 1000;//图片长

        double[] point2 = XXXXX(a,b,point1);
        point2 = YYYYY(point1);
        for (int i=0; i<point2.length; i++){
            System.err.print(point2[i]+"     ");
        }

    }

    private double[] YYYYY(double[] point) {
        double height = point[3]-point[1];//框的高度
        if(point[1] >= height){
            point[1] = point[1] - height;
        }
        else{
            point[1] = 0;
        }
        return  point;
    }

    private double[] XXXXX(double picw,double pich,double[] point1){
        double[] point2 = {0,0,0,0};//扩大后新的左上角坐标   右下角坐标
        try {
            double value1 = picw- point1[2];//右边距大小
            double value2 = pich- point1[3];//下边距的大小

            double width = point1[2]-point1[0];//内框宽
            double height = point1[3]-point1[1];//内框高

            double va1 = width/4;//水平倍数
            double va2 = height/2;//垂直倍数
            if(value1>=point1[0]){//判断保证水平方向扩大有足够的空间
                //扩大一倍需要两个坐标分别增加一半大小
                if(point1[0]>=va1){
                    point2[0] = point1[0]-va1;
                    point2[2] = point1[2]+va1;
                }
                else{//左上角坐标x边距不够
                    point2[0] = 0;
                    point2[2] = point1[2] + point1[0];
                }
            }else{
                if(value1>=va1){
                    point2[0] = point1[0]-va1;
                    point2[2] = point1[2]+va1;
                }
                else{//左上角坐标x边距不够
                    point2[0] = point1[0] - value1;
                    point2[2] = point1[2] + value1;
                }
            }

            if(value2>=point1[1]){//判断保证垂直方向扩大有足够的空间
                //扩大一倍需要两个坐标分别增加一半大小
                if(point1[1]>=va2){
                    point2[1] = point1[1]-va2;
                    point2[3] = point1[3]+va2;
                }
                else{//左上角坐标y边距不够
                    point2[1] = 0;
                    point2[3] = point1[3] + point1[1];
                }
            }else{
                if(value1>=va2){
                    point2[1] = point1[1]-va2;
                    point2[3] = point1[3]+va2;
                }
                else{//左上角坐标y边距不够
                    point2[1] = point1[1] - value2;
                    point2[3] = point1[3] + value2;
                }
            }
        }
        catch (Exception ex){
        }

        return point2;
    }
    @Test
    public void test012(){
        double vlu = 200000;
        double a = 572000 - vlu;//贷款额
        double i = 0.0562/12;//月利率
        int n=12*30;//月数
        System.err.println("    提前还款的金额："+vlu);
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
