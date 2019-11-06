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
        List<StockInfoBuyBean> stockInfoBuyBeans = new ArrayList<>();
        stockInfoBuyBeans.add(new StockInfoBuyBean("20190923",15.14,"睿能科技",15.36,1100,18.35,"20190926",15.15));
        stockInfoBuyBeans.add(new StockInfoBuyBean("20190905",17.05,"四维图新",16.56,1200,22.41,"20190927",17.26));
        stockInfoBuyBeans.add(new StockInfoBuyBean("20190925",34.30,"饕数据港",34.61,700,43.01,"20191025",32.61));
        stockInfoBuyBeans.add(new StockInfoBuyBean("20191025",4.51,"广博股份",4.65,3300,5.33,"20191030",5.29));
        stockInfoBuyBeans.add(new StockInfoBuyBean("20191028",3.63,"乂京东方",3.74,4500,4.18,"20191030",3.66));
        stockInfoBuyBeans.add(new StockInfoBuyBean("20191030",29.78,"饕数据港",31.82,700,43.01,"20191106",32.49));
//        stockInfoBuyBeans.add(new StockInfoBuyBean("20190912",56.56,"紫光国威-做T降低成本阶段",57.65,500,73.07,"",0));
//        stockInfoBuyBeans.add(new StockInfoBuyBean("20191106",3.59,"乂京东方",3.62,6700,4.01,"",0));

//        ========================================================================================================================
//        stockInfoBuyBeans.add(new StockInfoBuyBean("20191107",8.71,"中国国航-等30分钟3买",8.77,2000,9.63,"",0));
//        stockInfoBuyBeans.add(new StockInfoBuyBean("20191108",29.78,"饕数据港-等60分钟杀跌的1买或者2买",0,700,43.01,"",));


        return stockInfoBuyBeans;
}

}
