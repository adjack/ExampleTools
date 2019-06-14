package com.yuan.lifefinance.tool;

public class DicText {
    //测试
    //6月备注【81889】
    public static String[] getMonth6NameArray(){

        String[] value = {
                "2019-06-[11-亚盛股份]",//目标价格【5.10、3.91】止损价格【3.64】  最终收益：
//               6-12&止损[3.62 5分钟3买位置]：早盘大盘低开，农业板块依旧有低位股补涨，亚盛依旧有上涨补涨概率，临近午盘农业股爆发，全线大涨
//               总结：涨幅已经达到目标价位，关注是否能突破4.30,一旦突破可继续持有,明天要是高开会有前高压力4.30压制，明天需要高抛采取做T策略,
//                     筹码已经突破成功，修改止损价为3.64，当前属于2日线2买上涨，60分钟类2买突破中枢趋势，关注15分钟级别能否形成三买，参考做T

//               6-13&止损[关注15分钟3买]：12日晚：农业板块月底有事件利好，周线不放巨量暂时不考虑出货；开盘高开后持续杀跌，大盘5分钟走弱趋势，10点钟杀跌无量依旧看做5分钟
//                    高位震荡，等待5分钟中枢整理的突破考虑离场，4.00价位为中枢下轨,10点半，筹码未松动，继续关注5分钟高位中枢整理后的方向；11:10压力位置回调，5分钟三买失败
//                    因为是开盘高开且没有上拉动作，考虑这波杀跌不是出货，因此30分钟20均线位置考虑做T，11:20买入亚盛集团做T下午出掉，中午收盘大盘有回拉动作，亚盛60分钟依旧未确认顶部，
//                    开始关注15分钟形成3买的机会，农业板块应该是短期趋势，可关注着重做,当前位置面临前期套牢盘抛压，获利盘暂时未大幅出货，尾盘卖掉买入部分做T成功
//                    明天如果大幅杀跌可以买入继续做T，当前股价相对板块来说涨幅较小，有大幅补涨机会，收盘成交量维持上量标准

//               6-14&止损[关注15分钟3买]：开盘后板块继续上涨，龙头登海继续上冲 关注亚盛15分钟的3买位置，适量准备入场做T，13:15:15分钟有机会形成3买，关注3.92的止损位置，不跌破此位
//                    看做强势洗盘,2点农业板块继续强势,尾盘农业板块砸盘，前期部分龙头开始筹码松动，关注风险

                "2019-06-[11-综艺股份]",//目标价格【9.70、8.00】止损价格【7.00】 最终收益：
//               6-11&：半导体板块，国产芯片，多概念龙头，周线符合成交量形态个股，当前涨幅不大，可以适当低吸，今天跑输板块，处于板块下游，明天有希望领涨板块

//               6-12&止损：7.38：午盘有杀跌趋势，先卖出做T尾盘需要买回，2点股价开始下杀，证券拉盘后发现情况不对，又买回了部分[粗心导致]，发现半导体板块异动，需要酌情加仓，加仓完毕
//               总结：今天跑输板块，走势上看属于垫底，但由于前些天涨幅不小，可以理解为洗获利盘，明天依旧关注高位15分钟的中枢震荡，守住止损后可适当T，收盘成交量维持上量标准

//               6-13&止损[关注15分钟中枢震荡7.34止损]：60分钟中枢上轨附近，突破有难度，关注15分钟中枢走势，开盘依旧处于中枢震荡，10点开始震荡向下跌破15的趋势，一旦成立60分钟
//                    确定调整，需要关注15分钟是否能回拉成功，当前大盘继续缩量砸盘，关注1分钟杀跌是否背驰，再跌即将到达支撑，考虑T,10点半半导体板块开始异动买入部分做T，11点卖出半仓，5分钟反弹有终结趋势
//                    尾盘继续减仓，5分钟趋势杀跌可能还有机会破新低，明天5分钟背驰位有低位入场机会，已经回调多日有上涨需求，板块继续有涨停个股，继续看好此股机会，收盘成交量维持上量标准

//               6-14&止损[关注15分钟1买]：开盘高开之后依旧处于5分钟下跌中枢，等待5分钟跌破机会，继续回调，当前60分钟杀跌一笔有机会形成类2买，7.07买入部分仓位做T，2点弱势杀跌，尾盘放量杀跌，退出观望
//

                "",
                "",
                "",
                "2019-06-[14-？]四维图新"
//               6-12&四维图新需要等待30分钟的3买介入，13日开盘继续上冲延续30分钟上涨，继续等待
//               6-14&今天杀跌之后，30分钟回调有3买概率，早盘杀跌-3%杀入部分仓位，下午如果5分钟低位出现背驰，可以继续加仓，尾盘继续杀跌，继续买入部分仓位，依旧有3买概率，因为这个位置不买，一旦
//                   周一再上冲就不会有买点了，所以此时进场主动买套，
        };
        return value;
    }

    //总结日志
    public void getSummaryDiary(){
        /*
        &：定义行情的可操作性需要考虑两点，针对长线如果我们要提高成功率，我们要定义好进场的先决条件，这样才能把握一段完整的上升趋势，一般认为周线的低点适合长线布局，日线的低点适合短线布局
           1.针对长线必须要等到30分钟出三买才可以进场，当然30分钟的三买是是第一个进场点，后面最安全的买点是日线的2买，也就是30分钟的背驰1买，关注这些点的形成。对于离场，重点关注30分钟的3卖形成
           2.针对短线，首先成交量要上量标准，参考周线成交量形态，第二根据30分钟或者60分钟准确锁定介入点，操作过程中设置好目标位，严格关注基本面，垃圾票万不能碰

        20190612:当天大盘虽然放量，但不属于上量标准，明天要不能放量，短线需要注意风险出局，5分钟走势看并没有出3买，明天需要关注2900位置是否跌破，若不跌破大盘依旧
                可以看做强势震荡整理，短期会有继续上冲概率。若跌破，日线依旧不能确定企稳，慎重短线操作，当前位置不能追涨，如果维持5分钟强势震荡，持股待涨，个股会有轮动机会
        &:需要找到自己的操作节奏，不一定要时时操作，没有放量的回调机会不需要操作，农业板块可以做到7月初，着重对待

        20190613：大盘开盘依旧弱势杀跌，5分钟走势没有出现3买，短线需要慎重，当前30分钟回调，关注20日均线是否能止跌，5分看杀跌未背驰，多看少动，10点半证券开始拉盘，关注是否能持续放量
                中午收盘大盘5分钟拉回高位震荡区间，属于强势震荡，15分钟位置中枢震荡整理，关注下午是否量能能跟上,2:15大盘依旧无量，弱势震荡。收盘：全天依旧相对缩量，个股没有大机会，
                继续持股待涨，关注大盘15分钟维持震荡格局，多看少动

        &:买卖不悔，不能因为害怕踏空而时刻保持重要仓位，要学会取舍，坚信无量不会有大行情，A股不存在踏空，极少数的独立个股行情不需要抓。

        20190614：早盘翻找涨停个股发现，次新板块出现批量拉伸，可关注形态符合的次新股，海量数据，午后大盘依旧无量，15分钟中枢整理趋势，个股操作注重做T,控制仓位，尾盘大盘砸盘，依旧无量，
                短期风险加大


        */
    }

    //大事件列表
    public void getMajorEvents(){
        //20190627-20190629：中非经贸博览会【湖南发展、隆平高科】另外对农业种植有影响
        //20190630:漳州核电厂1号组开工【沃尔核材、中核科技、台海核电、中广核级、兰石重装】

    }
}


















