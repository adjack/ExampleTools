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
    public void test01(){
        List<HotdotDisBean> list = dis();
//        System.err.println(dealStr(list.get(0).getHotdotArea1(),45)+"#");
//        System.err.println(dealStr(list.get(1).getHotdotArea1(),45)+"#");

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
                "光学光电子3.3/非汽车交运3.0/计算机设备2.79/半导体及元件2.68","无","不操作"));
        list.add(new HotdotDisBean("20190724 10:00 周四","日线2买-成功","5分钟3买-成功",
                "会飞的俗话发3.3/都是的3.0/为房地产2.79/嗯嗯擦2.68","无","做日线一笔反弹"));
        return list;
    }

    private String dealStr(String str,int spaceLength){
        String spacestr = "";
        for(int i=0;i<spaceLength-str.length();i++){
            spacestr = spacestr+" ";
        }
        System.err.println(str.length()+"/"+spacestr.length());
        return str+spacestr;
    }
}
