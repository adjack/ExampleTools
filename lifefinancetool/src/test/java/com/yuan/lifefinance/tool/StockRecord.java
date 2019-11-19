package com.yuan.lifefinance.tool;

import com.yuan.lifefinance.tool.bean.StockInfoBuyBean;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * copyright:华润创业(深圳)有限公司
 * author:admin
 * create_date:2019/8/5 10:10
 * <p>
 * describe:个股操作记录
 * 原则：2只以上，分仓操作，周期一个月以上，不追涨，只买跌
 */
public class StockRecord {


//    操作模式：1.大级别【日线级别以上】出现买点，买入底仓，中途用额外资金做T操作,但一旦达到目标，底仓卖完，就没有操作机会
//              2.大级别没确认买点之前，要随时快进快出
//    20190805：第一批
//            &士兰微 ->：理由：个股月线突破的回调，半导体行业有想象空间  【成本：14.33->1400  止损：14.00】【目标->17.30目标->目标->】【支撑：15.83】
//                        第一阶段[目标冲击17.30]最终盈利：+5000[20190802--20190916][止损：13.25][注：可保持少量仓位持股博新高，17.30止损]
//                        第二阶段[目标冲击20.21]最终盈利：[]
//            四维图新->: 理由：计算机板块强势行情，个股处于车联网龙头，做周线2买反弹【成本：16.56->1500止损：16.00】【目标->20.30目标->目标->】
//                        第一阶段[目标冲击20.30]最终盈利：[20190905--]
//            紫光国微->：理由：半导体行业突破，个股走势有月线突破趋势，逢低建仓原则 【成本:51.20->500 止损：51.61】【目标->60.65目标->目标->】
//                        第一阶段[目标冲击60.65]最终盈利：+1700[20190820--20190904]
//                        第二阶段[目标冲击60.65新高后60分钟1卖]最终盈利：[20190912--][止损：53.22]
//            中国人寿->：理由：权重蓝筹，月线有突破趋势 【成本：30.29->700 止损：28.27】【目标->31目标->34.80目标->】
//                        2019-09-23卖出：最终盈利：-1400[走势太纠结，退出观望，短期重点操作半导体]
//            天齐锂业->：理由：周线有1买背驰趋势，跌幅巨大，公司属于锂矿龙头 【成本:26.98->1700 止损：23】【目标->25.30目标->27.60目标->35.00】

    public static List<StockInfoBuyBean> getStockInfoBuyList(){
        //注意：只关注年线之上的票
        List<StockInfoBuyBean> stock = new ArrayList<>();
        stock.add(
                new StockInfoBuyBean("20190709",25.94,"天齐锂业",27.14,1200,27.61,"20191111",21.45));
        stock.add(
                new StockInfoBuyBean("20190923",15.14,"睿能科技",15.36,1100,18.35,"20190926",15.15));
        stock.add(
                new StockInfoBuyBean("20190905",17.05,"四维图新",16.56,1200,22.41,"20190927",17.26));
        stock.add(
                new StockInfoBuyBean("20190925",34.30,"饕数据港",34.61,700,43.01,"20191025",32.61));
        stock.add(
                new StockInfoBuyBean("20191025",4.51,"广博股份",4.65,3300,5.33,"20191030",5.29));
        stock.add(
                new StockInfoBuyBean("20191028",3.63,"乂京东方",3.74,4500,4.18,"20191030",3.66));
        stock.add(
                new StockInfoBuyBean("20191030",29.78,"饕数据港",31.82,700,43.01,"20191106",32.49));
        stock.add(
                new StockInfoBuyBean("20190912",56.56,"紫光国威",57.65,500,73.07,"20191111",45.81));
        stock.add(
                new StockInfoBuyBean("20191106",3.59,"乂京东方",3.62,7600,4.01,"20191111",3.61));
        stock.add(
                new StockInfoBuyBean("20191108",14.76,"乂士兰微",15.30,1000,17.40,"20191112",14.72,"年线上方运行"));
        stock.add(
                new StockInfoBuyBean("20191112",44.30,"紫光国威",44.40,300,46.80,"20191114",45.70,""));
        stock.add(
                new StockInfoBuyBean("20191114",24.82,"天齐锂业",27.02,800,29.01,"20191119",27.04,"攻击年线后的回调"));
        stock.add(
                new StockInfoBuyBean("20191107",8.40,"中国国航",8.78,3300,9.99,"30分钟3买-买入博周线反弹",0,"当前攻击年线"));
        stock.add(
                new StockInfoBuyBean("20191119",45.90,"紫光国威",48.25,400,46.80,"博日线反弹",0,""));
        //        ========================================================================================================================
//        stock.add(new StockInfoBuyBean("20191113",29.78,"饕数据港",0,0,43.01,"准备阶段-等60分钟杀跌的1买或者2买//15分钟有机会出3买，当前周线处于类2买止跌反弹",0,"跌到年线支撑位"));


        return stock;
}
//        【月线买点】：最保险的是做周线一笔反弹，[需要30分钟出3买,日线回调背驰买入最稳妥]
//        【周线买点】：最保险的是做日线一笔反弹，[需要5分钟出3买,30分钟回调背驰买入最稳妥]
//        【日线买点】：最保险的是做30/60分钟一笔反弹，[需要1分钟出3买,5分钟回调背驰买入最稳妥]

//        【 1分钟出3卖】：可能是30分钟杀跌，5分钟反弹要卖
//        【 5分钟出3卖】：可能是日线杀跌，30分钟反弹要卖
//        【30分钟出3卖】：可能是周线杀跌，日线反弹要卖
//        【日线出3卖】：可能是月线杀跌，周线反弹要卖

}
