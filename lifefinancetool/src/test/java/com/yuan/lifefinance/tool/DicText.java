package com.yuan.lifefinance.tool;

import com.yuan.lifefinance.tool.greendao.StockInfo;

import java.util.ArrayList;
import java.util.List;


public class DicText {


    public static String[] getMonth6NameArray(){

        String[] value = {
                "2019-06-[11-20亚盛股份]",//目标价格【5.10、3.91】止损价格【3.64】  最终收益：40
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

//               6-17&整体农业板块早盘杀跌，关注最终止损3.64位置，当前跌破到筹码突破位置，有小支撑,午盘：筹码看依旧属于洗盘，技术形态看，5分钟处于中枢支撑的震荡
//                    暂时持股不动，尾盘板块杀跌，筹码依旧未松动看着洗盘，亚盛走势相对板块来说叫抗跌，明天关注低位机会入场T,

//               6-18&早盘继续维持5分钟下跌中枢整理，有破位杀跌概率，但当前依旧看是洗盘，暂时观望，等待跌破后再操作,10.40：5分钟跌破中枢杀跌，关注最终止损3.64，当前大盘5分钟破位后拉回，
//                    盘面有短线反弹概率，暂时持股待反弹,2点:53.69买入部分股票做T，尾盘依旧保持开盘同等仓位，继续等待反弹，当前处于止损位置附近徘徊
//               总结：一天不要随便T，不能卖出之后出错了，又马上买回，这样会导致频繁犯错
//               6-19&早上平开，10点之后板块有涨停个股出现继续持股待反弹,午盘发现筹码松动卖出3分之一仓位，尾盘有异动，继续买入早上卖出的仓位，等待反弹，今天开始出现加仓，慎重
//               6-20&砸盘持续杀跌，筹码松动之后有破位趋势，关注3.64的止损,因为板块强势股持续杀跌，关注反弹后的出局机会，3.64全仓卖出结束本轮操作

                "2019-06-[20-21中钨高新]",//目标价格【8.28】止损价格【7.33】 最终收益：-500
//               6-20&板块看尾盘龙头个股继续涨停，筹码看属于加仓后的整理，博跟涨
//               6-21&上午：大盘上涨，未跟涨，当前处于5分钟弱势震荡，继续持股，大盘5分钟要背驰趋势，注意风险，13:50->30分钟有下杀趋势7.47卖出全仓

                "2019-06-[11-24综艺股份]",//目标价格【9.70、8.00】止损价格【7.00】 最终收益：-2268
//               6-11&：半导体板块，国产芯片，多概念龙头，周线符合成交量形态个股，当前涨幅不大，可以适当低吸，今天跑输板块，处于板块下游，明天有希望领涨板块

//               6-12&止损：7.38：午盘有杀跌趋势，先卖出做T尾盘需要买回，2点股价开始下杀，证券拉盘后发现情况不对，又买回了部分[粗心导致]，发现半导体板块异动，需要酌情加仓，加仓完毕
//               总结：今天跑输板块，走势上看属于垫底，但由于前些天涨幅不小，可以理解为洗获利盘，明天依旧关注高位15分钟的中枢震荡，守住止损后可适当T，收盘成交量维持上量标准

//               6-13&止损[关注15分钟中枢震荡7.34止损]：60分钟中枢上轨附近，突破有难度，关注15分钟中枢走势，开盘依旧处于中枢震荡，10点开始震荡向下跌破15的趋势，一旦成立60分钟
//                    确定调整，需要关注15分钟是否能回拉成功，当前大盘继续缩量砸盘，关注1分钟杀跌是否背驰，再跌即将到达支撑，考虑T,10点半半导体板块开始异动买入部分做T，11点卖出半仓，5分钟反弹有终结趋势
//                    尾盘继续减仓，5分钟趋势杀跌可能还有机会破新低，明天5分钟背驰位有低位入场机会，已经回调多日有上涨需求，板块继续有涨停个股，继续看好此股机会，收盘成交量维持上量标准

//               6-14&止损[关注15分钟1买]：开盘高开之后依旧处于5分钟下跌中枢，等待5分钟跌破机会，继续回调，当前60分钟杀跌一笔有机会形成类2买，7.07买入部分仓位做T，2点弱势杀跌，尾盘放量杀跌，退出观望，
//               6-17&早上小幅上冲后午盘回落，午盘：走势较差，60分钟继续调整，5分钟大概率形成3卖，一旦形成日线确认成笔杀跌，下午出局回避，尾盘全部卖出，等待低点再介入
//               6-18&早盘低开后继续杀跌，有机会6.5左右支撑位置反弹，但是因为无量，慎重关注不入场，因为短线操作必须要有量才行，这是原则，

                "2019-06-[24-亚盛集团]",//目标价格【4.30】止损价格【3.78】-225
//               6-24&大盘有机会转日线回调，当前农业股逆势上涨，结合临近本周的农业消息利好，有望反弹一波，所以买入。走势看5分钟属于类2买突破
//               6-35&5分钟类2买再次拉回中枢，走势较弱，等待5分钟反弹出局，走势跟预期不符合，3.73卖出全部

                "2019-06-[18-中曼石油]",//目标价格【23.00】止损价格【17.75】
//               6-18&次新板块，当前处于筹码突破后的洗盘阶段，配合大盘缩量杀跌，关注5分钟的背驰机会
//               6-21&最近两天筹码松动，属于加仓后的继续上涨，关注回调机会
            
                "",
              };
        return value;
    }
    public static String[] getMonth7NameArray(){

        String[] value = {

                "2019-07-[17-22]深天马A",//目标价格【15.87】止损价格【14.90】 最终收益：-782
//               7-17&华为手机出货时间点风口面板热点oled，已经关注几天，之前顾忌今天解禁有压力，开盘并没有杀跌，所以开盘14.86买入1700股
//               7-18&60分钟的中枢震荡回调，相对于板块涨幅较小，等待30分钟回调反弹选择方向，14.4左右有支撑，猜测大盘不会大跌，继续持股
//               7-19&大盘拉伸的情况下，依旧处于5分钟下跌中枢，有向下破位趋势，关注14.57的出局机机会,5分钟破位砸盘14,41止损出局，尾盘5分钟有背驰趋势买回卖出股份
//               7-22&大盘破位杀跌，出局观望
                "2019-07-[26-？]中国人寿",//目标价格【32.60】止损价格【30.36】 最终收益：
//               7-26&：大盘5分钟类二买突破成功，博反弹
//               7-29&：破止损出局，尾盘买入一手
//               8-01&：早盘跟随大盘一路杀跌，十点半出现万手托单
//               7-31&：加仓一手
//               8-02&：杀跌继续加仓
//               8-05&：如果今天继续杀跌，低位继续加仓
                "2019-07-[23-？]深天马A",//目标价格【16.00、15.73】止损价格【14.92】 最终收益：
//               7-24&：大盘1分钟出3买，60分钟大概率反弹，14.67介入2300股，光学光电子板块尾盘5分钟出现3买，短线个股看补涨
//               7-25&：早盘京东方A放量，有机会板块补涨，持股待涨，光学光电子，涨停个股属于补涨，持股等待补涨
//                      尾盘京东方A出现大卖单，板块当前60分钟面临压力，明天可能面临回调，个股处于5分钟突破成功，以突破价格作为止损
//               7-26&：早盘高开后一直出现大托单，个股也处于中枢上轨附近，卖出半仓,尾盘买回部分仓位，大盘5分钟类二买突破成功，板块中又有个股直接拉板补涨，关注补涨机会
//               7-29&：早盘拉伸后随后1分钟向下破位，1分钟出3卖概率，减仓观望，等支撑14.60，10:50-》1分钟背驰后，重新放量拉回中枢平台，关注是否有机会放量上攻出1分钟3买，有机会要补涨
//               7-30&：当前个股依旧处于15分钟中枢震荡，慎重操作，震荡过程操作容易做反，2点卖出部分仓位，当前需要关注是否形成60分钟回调
//               7-31&：早盘杀跌，60分钟确认回调，关注企稳，持股等待低位加仓，当前需要关注半年报业绩，10点有放量企稳拉升趋势，买入部分，当前处于20日均线支撑处，回调多日有望企稳反弹
//                      尾盘开始杀跌，当前60分钟未企稳，15分钟大概率执行向下的杀跌线段，明天有新低机会，14.40支撑位
//               8-01&：公司有成立子公司利好，高开后一路走低，大盘早盘低开后继续维持杀跌趋势，当前处于30分钟震荡，直接破位杀跌概率不大，
//               8-02&。减仓，日线走势较差，等待中报出来
                "2019-08-[01-？]士兰微",
//               8-02&：月线属于类2买突破的回调，半导体行业，短期几个月趋势应该看涨，2日线布局类二买走势，
                "",
                "",
        };
        return value;
    }

    //长线跟踪
    public static List<StockInfo> longTermTrack1_6(){//"2019-06-[14-？]四维图新"//目标价格【/20.00、17.50、16.90】止损价格【16.45】 最终收益：
//               6-12&四维图新需要等待30分钟的3买介入，13日开盘继续上冲延续30分钟上涨，继续等待
//               6-14&今天杀跌之后，30分钟回调有3买概率，早盘杀跌-3%杀入部分仓位，下午如果5分钟低位出现背驰，可以继续加仓，尾盘继续杀跌，继续买入部分仓位，依旧有3买概率，因为这个位置不买，一旦
//                   周一再上冲就不会有买点了，所以此时进场主动买套
//               6-17&当天派息除权，走势看30分钟依旧处于3买位置，5分钟跌破15.79后当前处于反弹走势，关注15.79的做T机会，15.69卖出半仓,等待明天杀跌,尾盘看到有放量企稳止跌趋势，再买回早上出局的仓位
//               6-18&早盘小幅高开后，开启3买上涨16.05卖出部分仓位，因为个股当前位置结合大盘极有可能3买难突破，有概率形成中枢震荡，所以压力位卖出部分,11点：当前处于压力位跟1分钟背驰位置
//                   16.20价格再次卖出部分仓位做T,如果下午5分杀跌需要再买回,16.02买回部分，尾盘15.99再买回全天卖出的仓位，做T
//               总结：做T需要满足一个原则，卖出多少就要买回多少，保持买卖的一致性
//               6-19&早盘高开，基于计算机板块全线大涨，5分钟回调后继续16.25价格买入700股，准备做T，午盘继续处于5分钟震荡，30分钟上涨有压力，关注5分钟走势是否补缺，暂时持股不动，
//                   明确操作越多，犯错越多，尾盘补缺成功，明天可能会低开，需要反省高开的压力位置为何不减仓
//               总结：高开后的压力位置没有减仓，操作的纪律不够，反省：明确个股需要关注走势本身，消息面的影响也仅仅是影响开盘半小时
//               6-20&30分钟有类2买结构，计算机板块继续强势，10:36->大盘30分钟有突破趋势16.25买入1900股做T，2点半：16.61卖出1900股
//               6-21&创业板利好，大盘继续5分钟上涨，10:40-》16.82卖出1000股做T，大盘5分钟要背驰趋势，注意风险，11点：16.86卖出600股做T，当前1分钟依旧处于中枢震荡，等待方向选择
//                   当前30分钟属于3买突破趋势延续一笔，筹码看当前位置处于前期压力位，5分钟看，处于高位震荡，需要放量突破才能打开上行空间，否则，30分钟回调概率大概率，板块依旧处于强势
//                   13:45-》16.40买入600股T回来，2点关注大盘30分钟的方向选择，5分钟有机会选择向下，四维图新因为是长线标的，所以只要做T降低成本就好，不需要想着压力位全部抛掉
//                   必须留底仓，除非大盘大跌趋势向下,16.56买回1000股T成功，当前处于1分钟背驰位置，2点半：16.45继续买入1000股T，尾盘考虑到，整个板块大涨，今天此股涨幅垫底，所以继续持股下周再操作
/////////////////当前主力成本14.36，最近一周新主力仓位成本为15.90，筹码看有可能是主力两个账户对倒
//               6-24&16.41属于类2买突破的压力位，如果跌破，虽然有机会继续形类2买，但是必须减仓防范杀跌风险，跌破要减仓,早盘一路杀跌，有机会转变成日线杀跌，中午没有把握出局机会
//                   当前日线涨幅不大，筹码出现加仓迹象，短线有可能出现震荡走势，尾盘大盘依旧维持5分钟高位震荡，不排除继续挑战新高，关注5分钟是否会盘背，因为错过了1分钟背驰的做T位置
//                   继续持股
//               6-25&早盘大盘杀跌，5分钟有趋势反转概率，等待跌破信号出现，暂时持股不动，10:50->5分钟趋势结束，当前执行30分钟杀跌一笔，当前1分钟有背驰趋势，
//                   5分钟有机会形成下跌后的背驰1买，关注做T机会,11点15.86买入1000股做T，当前大盘出现5分钟3卖，日线可能会杀跌很大
//                   午盘之后大盘又回拉动作，但是1分钟并没有形成3买，四维图新依旧维持5分钟弱势震荡，15.92卖出1000股，防止5分钟杀跌时 仓位过重
//                   当前30分钟继续回调，5分钟处于低位中枢震荡，14:38-》大盘1分钟有3买趋势，15.95继续买入1000股做T，14:50->16.04卖出1000股做T完成
//                   尾盘5分钟级别有放量，30分钟杀跌有机会企稳,今天出现了5分钟3卖，这是日线概率见顶的信号，15.80位置应该守住，不然要减仓
//               6-26&最近整理了新的操作流程ABCDE，今天开始开始用流程来描述操作过程，早盘受利空影响，大盘跌开，当前走势处于C阶段，当前关注15.77的止损位置,持股等待5分钟3买的出现，10点->
//                    计算机板块叫强势，结合四维跌了几天了，今天有机会5分钟出3买，可以回调买做T,10:30->计算计算板块上涨，有机会出现3买，暂时持股四维图新不操作，等待5分钟出3买
//                    5分钟放量上攻30分钟中枢高位16.43认为有压力，16.46卖出1000股做T完成，当前C阶段只考虑买和关注5分钟3卖出现，继续等待5分钟是否能出3买，
//                    13:30->午后维持5分钟震荡概率大，5分钟3买今天应该出不了了,继续低位买入16.12买入1000股做T，大盘日线依旧上升走势，没有走坏，继续持股，C阶段需要根据30分钟底分型设置止损，
//                    同时需要关注1分钟是否出3卖，尾盘16.17卖出1000股结束做T，1分钟维持低位震荡，有向下杀跌趋势，一旦如此就可能挑战止损点，为了降低风险再减仓1000股
//              6-27&关注止损点15.81，早盘1分钟走势中枢破位后继续拉回整理，15.96买入1000做T,由于板块个股继续强势，大盘30分钟3买概率形成加大，16.24继续买入1000股，午后1:20->16.30卖出1000股，
//                  理由是依旧无量。1:30->5分钟开始放量，16.45位置有中枢压力，60分钟依旧围绕这个位置有震荡。当前大盘1分钟杀跌是有风险的，16.37卖出1000股今天T成功
//              6-28&开盘后5分钟继续维持中枢震荡，趋势依旧没坏，16.27买入1000股做T，这个位置需要关注1分钟的3卖是否出现，11点出现1分钟3卖，接下来可能会出现5分钟5卖，关注5分钟的反弹减仓出局，因为
//                  无法确认5分钟的反弹高度，所以16.06卖出1400股，16.09卖出700股，减少持仓是为了防守，虽然暂时没有出现5分钟3卖，但必须要提前减仓去防守，纠正：1分钟暂时是没有3卖的，但是有概率，
//                  5分钟依旧处于杀跌，3卖暂时没出现。这个位置需要谨慎观察，14:26->当前处于30分钟震荡，5分钟依旧可以看做一笔杀跌还未反弹，16.04买入900股做T，这个位置有风险，根据30分钟底部要防范破
//                  止损。整板块有回拉动作，所以买入部分仓位，风险可控，一旦买错，依旧可以出局，当前等待大盘1分钟是否能出3买，一旦1分钟再拉回5分钟出3卖概率加大
        List<StockInfo> list = new ArrayList<>();
        StockInfo stockInfo = new StockInfo("四维图新");
        list.add(setStockInfo(stockInfo,16.25,1900,16.61,3.0,"20190616  10:21","II"));
        list.add(setStockInfo(stockInfo,16.56,1000,16.82,2.0,"20190621  10:40","II"));
        list.add(setStockInfo(stockInfo,16.41,600,16.86,1.0,"20190621  10:50","II"));
        list.add(setStockInfo(stockInfo,15.86,1000,15.92,1.0,"20190625  11:00","II"));
        list.add(setStockInfo(stockInfo,15.95,1000,16.04,1.0,"20190625  14:00","II"));
        list.add(setStockInfo(stockInfo,16.12,1000,16.46,1.0,"20190626  10:30","II"));
        list.add(setStockInfo(stockInfo,15.96,1000,16.31,1.0,"20190627  10:30","II"));
        list.add(setStockInfo(stockInfo,16.24,1000,16.37,1.0,"20190627  10:40","II"));
        list.add(setStockInfo(stockInfo,16.27,1000,16.06,1.0,"20190628  10:30","II"));
        return list;
    }

    public static List<StockInfo> longTermTrack1_7(){
//================="2019-06-[14-？]四维图新"//级别：周线一笔，目标价格【17.29】止损价格【14.15】 最终收益：-3908
//                  7-01&早盘收到消息面影响高开，接下来关注30分钟是否有类2买向上突破走势，开盘后高开到30分钟中枢上轨遇到抛压，需要等待是否能出现5分钟3买，当前计算计算机板块强势，买入2000股做T
//                      11:02->30分钟的压力位置，卖出2000股做T的股份,午后，5分钟有回调趋势，但当前位置的回调是有可能出现5分钟3买的，买入700股做T，临近尾盘，5分钟3买成功，当前位置5分钟1卖卖出700
//                      股做T成功,当前需要关注30分钟突破之后5分钟的线段力度，当前进入到D阶段，收盘全线大涨，筹码有突破趋势
//                  7-02&早盘维持1分钟高位震荡，日线17.17附近有压力，卖出1000股T，判断当前5分钟3买区域是可能有支撑的，所以随后再买回1000股，纠正，当前5分钟处于高位震荡，5分钟回调是有机会出现类2买的
//                      当前大盘5分如果要回抽0轴，应该还有波急跌，买入1000股做T,11点，分钟有盘背趋势里支撑较近，继续买入900股做T，14:00关注大盘5分钟是否能回拉出3买，一旦发生，这个位置要考虑减仓离场
//                      收盘依旧处于30分钟上涨，需要等待大盘5分钟上攻之后的背驰情景，继续持股，纠正：当前级别对应60分钟突破，关注15分钟是否能出现3买
//                  7-03&早盘买入1000股做T,临近午盘60分钟继续拉回中枢，突破可以看做失败，继续做T原则，继续买入800股做T，当前需要关注5分钟是否出3卖，尾盘大盘出现5分钟3卖，减仓本金500股控制风险
//                  7-04&隔夜消息面上外国股指创新高，这对于A股是个积极影响，继续做买入2300股做T，午后：5分钟继续低位杀跌，关注反弹,尾盘回补缺口成功，暂时持股不卖，等反弹出局，大盘重新拉回30分钟
//                      中枢震荡，因为已经出现了5分钟3卖，需要关注30分钟反弹出局
//                  总结：无量不做T,不达到上量标准做T失败概率大
//                  7-05&开盘低开继续维持5分钟杀跌趋势，关注30分钟反弹出局机会，早盘计算机板块相对较强，重点等30分钟反弹，阶段E，午后反弹为了避免风险，卖出昨天部分仓位T，当前位置有可能趋势终结，
//                      16.30止损，收盘继续弱势回调，30分钟已经成笔，关注5分钟是否能破位杀跌的风险，严格关注止损
//                  7-08&受利空影响开盘继续砸盘，等待30分钟反弹出局，11点1分钟背驰位置买入做T，午后继续杀跌，但杀跌幅度相对较小，关注5分钟背驰后做T博30分钟反弹，尾盘保持仓位关注明天30分钟反弹
//                  总结：跌破重要点位马上出局，不能因为利好而迟疑，今天无量，应该不做T，错误操作，没有明确的目标，执行力不够，每个操作阶段应该需要设置日线目标，这点没有做到
//                  7-09&【17.29-14.15】关注30分钟反弹，止损14.90，当前处于F阶段，早盘开盘后继续5分钟砸盘，当前属于30分钟急跌，可以等5分钟背驰位置博反弹
//                  7-10&等待5分钟1买背驰
//                  7-11&等待5分钟1买背驰
//                  7-15&最近相对于板块整体跌幅巨大，猜测当大盘企稳反弹，应该有波大反弹，当前关注30分钟是否出3卖,尾盘增加持仓
//                  7-16&等待5分钟的方向选择

        List<StockInfo> list = new ArrayList<>();
        StockInfo stockInfo = new StockInfo("四维图新");
//------[开店买股2000]  20190701-》16.45
        list.add(setStockInfo(stockInfo,16.45,2000,16.79,1.0,"20190701  09:42","II"));
        list.add(setStockInfo(stockInfo,16.70,700,17.08,1.0,"20190701  13:44","II"));
        list.add(setStockInfo(stockInfo,17.02,1000,17.22,0.4,"20190702  09:35","II"));
        list.add(setStockInfo(stockInfo,16.91,900,16.85,3.0,"20190702  10:08","II"));
        list.add(setStockInfo(stockInfo,16.85,900,16.85,2.0,"20190702  11:01","II"));
        list.add(setStockInfo(stockInfo,16.77,1000,16.52,2.0,"20190703  09:51","II"));
        list.add(setStockInfo(stockInfo,16.59,800,16.52,2.0,"20190703  10:36","II"));
        list.add(setStockInfo(stockInfo,16.64,1200,16.47,5.0,"20190704  09:49","II"));
        list.add(setStockInfo(stockInfo,15.49,1300,15.25,2.0,"20190708  11:00","II"));
// -----[卖店卖股2000]  14.56 &总结：不碰半年内除权个股

        return list;
    }

    public static List<StockInfo> longTermTrack2_7(){
        //================="2019-07-[09-？]天齐锂业"//级别：周线一笔，目标价格【27.80】止损价格【26.07】 最终收益：
//                  7-09&买入部分天齐锂业，打点底仓，长线关注
//                  7-12&等待5分钟出2类买的机会,买入800做T，继续等待
//                  7-15&早盘一破止损出局观望，但当前破位杀跌正好处于回补前期跳空缺口，回补后应该有希望短线反弹，暂时观望，等待15分钟背驰信号，关注
//                      60分钟底部中枢的构建过程
//                  7-16&关注大盘30分钟反弹的力度，关注1分钟出3卖的危险点，大盘无量只做T
//                  7-17&等待15分钟级别出1买
//                  7-18&等待15分钟级别出1买
//               ***7-19&有出现反弹迹象，但是60分钟没有确认止跌，因为1分钟没有出3买，30分钟26.12有压力，收盘最后一个小时，继续弱势杀跌，60分钟继续寻底，当前点位非常关键，一旦跌破下跌空间大，卖出半仓900股
//                  7-22&破位杀跌，观望等待5分钟背驰
//               ***7-23&早盘23.94价位买入900股，先博5分钟反弹【板块有反弹】，午盘5分钟级别有再探底趋势，卖出半仓400股，尾盘依旧维持5分震荡，为避免杀跌，卖出剩余500股
//               ***7-24&早盘看到大盘5分钟拉伸有3买趋势，买入900股，收盘留下400股补充开店资金【现仓位1300股】，当前日线跌幅巨大，60分钟的背驰位，是不应该恐慌的
//               ***7-25&15分钟跌破，大幅杀跌。卖出出局观望，需要重新定义止损
//               ***7-30&15分钟靠近零轴，当前有机会形成15分钟2买，买回部分开店资金【现仓位600股】，止损23.30
        List<StockInfo> list = new ArrayList<>();
        StockInfo stockInfo1 = new StockInfo("天齐锂业");
//------[开店买股1700]  20190716-》26.98
//        list.add(setStockInfo(stockInfo1,26.98,1700,--,2.0,"20190712  10:00","无"));
//      阶段目标 60分钟一笔反弹27.80   前提：大盘不能出3卖   止损25.30
        list.add(setStockInfo(stockInfo1,23.94,400,23.92,1.0,"20190723  10:00","无"));
        list.add(setStockInfo(stockInfo1,23.94,500,23.95,1.0,"20190723  10:00","无"));
        list.add(setStockInfo(stockInfo1,24.16,500,24.00,2.0,"20190724  10:00","无"));
//        list.add(setStockInfo(stockInfo1,24.16,400,--,2.0,"20190724  10:00","无"));
//      开始做买卖

// -----[卖店卖股？]  待定？
        return list;
    }

    public static List<StockInfo> longTermTrack1_8(){
        //================="2019-08-[01-？]士兰微"//级别：2日线一笔，目标价格【】止损价格【】 最终收益：
//                  8-02&买入部分士兰微，目标2日线级别，月线延续突破反弹
        List<StockInfo> list = new ArrayList<>();
        StockInfo stockInfo1 = new StockInfo("士兰微");
//        list.add(setStockInfo(stockInfo1,23.94,400,23.92,1.0,"20190723  10:00","无"));

// -----[卖店卖股？]  待定？
        return list;
    }

    public static List<StockInfo> longTermTrack3_7(){
        //================="2019-07-[15-16]中国人寿"//级别：周线一笔  结果：-788
//                  7-15&今天属于高位横盘，并未跟涨大盘，当前处于月线突破趋势，今天买入博明天安全上升的反弹
//                  7-16&早盘杀跌回调，考虑到60分钟级别涨幅已经过大，关注15分钟是否能形成3买，
        List<StockInfo> list = new ArrayList<>();
        StockInfo stockInfo1 = new StockInfo("中国人寿");
//      阶段目标【博新高】   止损30.40
//      [开店买股1100]  20190715-》30.67
        list.add(setStockInfo(stockInfo1,30.67,1100,0,0,"20190715  14:50","I"));
        list.add(setStockInfo(stockInfo1,30.67,1100,0,1,"20190806  10:50","I"));
//      [卖店卖股1100]  20190716-》30.01

        return list;
    }

    public static List<List<StockInfo>> longTermTrackAll_8(){
        List<List<StockInfo>> list = new ArrayList<>();
        List<StockInfo> subList1 = new ArrayList<>();
        List<StockInfo> subList2= new ArrayList<>();
        List<StockInfo> subList3 = new ArrayList<>();
//        subList1.add(new StockInfo("<>士兰微",13.47,200,13.86,1,"20190806  10:15","I"));
//        subList1.add(new StockInfo("<>士兰微",13.50,200,13.86,6,"20190806  13:15","I"));
//        subList1.add(new StockInfo("<>士兰微",13.67,700,13.80,3,"20190808  09:45","I"));
//        subList1.add(new StockInfo("<>士兰微",13.42,200,13.57,2,"20190809  09:45","I"));
//        subList1.add(new StockInfo("<>士兰微",13.61,300,13.39,3,"20190809  09:45","I"));
//        subList1.add(new StockInfo("<>士兰微",13.44,200,13.67,3,"20190812  09:45","I"));
//        subList1.add(new StockInfo("<>士兰微",13.46,200,13.78,3,"20190812  09:45","I"));
//        subList1.add(new StockInfo("<>士兰微",13.61,200,13.82,1,"20190812  14:45","I"));
//        list.add(subList1);
//
//        subList2.add(new StockInfo("中国人寿",28.24,100,28.26,1,"20190806  10:15","I"));
//        subList2.add(new StockInfo("中国人寿",27.72,100,28.38,1,"20190806  10:15","I"));
//        subList2.add(new StockInfo("中国人寿",28.11,200,27.94,1,"20190807  10:45","I"));
//        subList2.add(new StockInfo("中国人寿",28.34,400,28.04,4,"20190812  10:45","I"));
//        subList2.add(new StockInfo("中国人寿",27.72,300,28.04,4,"20190813  10:45","I"));
//        list.add(subList2);
//
//        subList3.add(new StockInfo("天齐锂业",23.88,200,24.29,1,"20190807  09:38","I"));
//        subList3.add(new StockInfo("天齐锂业",23.84,200,23.91,2,"20190807  10:38","I"));
//        subList3.add(new StockInfo("天齐锂业",23.44,400,23.20,3,"20190813  09:38","I"));
//        list.add(subList3);
        //总结：每次交易做T应该操作买入/卖出等同持有的个股数量，，至少1万元起，否则成功率大也无法加大收益--------------
        subList1.add(new StockInfo("<>士兰微",13.96,700,14.19,3,"20190814  11:05","I"));
        subList1.add(new StockInfo("<>士兰微",13.87,700,13.99,5,"20190814  09:55","I"));
        subList1.add(new StockInfo("<>士兰微",14.03,700,13.81,4,"20190815  10:44","I"));
        subList1.add(new StockInfo("<>士兰微",13.98,700,14.08,4,"20190815  14:30","I"));
        subList1.add(new StockInfo("<>士兰微",14.18,700,14.26,4,"20190816  14:01","15分钟压力位卖空"));
        list.add(subList1);

        subList2.add(new StockInfo("中国人寿",27.38,400,27.79,4,"20190815  09:45","I"));
        subList2.add(new StockInfo("中国人寿",28.05,400,28.26,4,"20190816  10:33","博15分钟反弹。目标28.47"));

        list.add(subList2);

        subList3.add(new StockInfo("天齐锂业",23.43,400,23.49,2,"20190813  11:08","I"));
        subList3.add(new StockInfo("天齐锂业",23.23,500,22.94,2,"20190815  10:08","I"));
        list.add(subList3);

        return list;
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
                短期风险加大,明确当前大盘并没有出现企稳止跌信号，关注周一15分钟是否能再维持中枢震荡，当前环境下只能做T,不能过度持股。

        20190617：大盘继续无量回落，短线没有机会，尾盘继续无量，关注放量机会，当前行情只能T,注意止损

        20190618：早盘大盘依旧无量，5分钟中枢跌破趋势，短线注意风险，观望不买上涨个股，关注背驰位，尾盘大盘依旧无量，关注风险不开新仓。
        20190620：最近两天大盘持续上量，短线进入强势行情，关注短线个股机会
        20190624：大盘延续30分钟上涨，5分钟有希望再突破新高，3057处于缺口压力位
        20190625：早盘大盘5分钟有向下杀跌趋势，暂时没有走弱，需要跌破确认，11点跌破确认，结合大盘无量，短线不参与操作，只关注长线个股做T
                大盘尾盘1分钟出现3买，明天依旧有机会形成3买继续关注做T机会
        &:长线需要明确一种趋势的入口和出口，比如我们要做周线的一笔趋势，通常入口信号就是30分钟3买的形成，出口信号是30分钟的三卖形成
          但是为了避免日线一笔上涨过后就马上变成周线的杀跌，我们需要定义其他一些信号来确保我们不会后知后觉，因为后知后觉会导致我们整个趋势白做，
          为了确保不遗漏整个上涨过程的关键离场点，我统计了如下几条操作流程
          前提：我们需要关注日线跌幅是否过大，只有过大这样的反转趋势才有利润空间，操作过程如下：

          一：日线1买反弹阶段I
          A.【关注30分钟3买准备买入，条件：1.等待5分钟1买入场，参考黄金分割位  2.底分型设置止损】这个过程是为了确定周线开始上涨一笔
            【防范：要严格止损，仓位3层】
          B.【关注30分钟的一卖出局，可参考压力位 条件：1.等待5分钟的一卖  2.1分钟出现3卖】这个阶段有可能形成中枢震荡，前提是不能出现1分钟1卖
            【防范：1分钟出现3卖就要出局】************B阶段这个过程可能会出现反复震荡，需要参考5分钟是否出现3卖*****************

          二：2买/类2买震荡阶段II
          C.【等待日线杀跌完成买入， 条件：1.30分钟的1买 2.或者30分钟一笔跌到位参考5分钟1买  3.5分钟出3买后的30分钟2买】这个位置要重点关注日线的止损位置，一旦跌破可能也是整个趋势的终结
            【防范：要严格防范30分钟出现3卖，仓位8层，严格止损】这个过程是重仓的买入阶段，但要谨慎关注30分钟3卖，一旦出现整段趋势可能就终结了
            【防范：5分钟一旦出现3卖，如果杀跌幅度不大且依旧处于30分钟中枢内部，是有可能继续形成30分钟类2买的，但是必须保证不能再破5分钟新低，不然要出局】
            【重点：如果维持30分钟中枢震荡，需要根据30分钟底分型设置止损，参考1分钟3卖的出现】这个位置是存在跌破并趋势终结的可能
          D.【关注30分钟一卖   条件：1.参考日线中枢上轨压力，2.5分钟1卖要T】
            【防范：1分钟的3卖要减仓观望，但跟B阶段不同的是这个位置杀跌可能有30分钟类2买机会，重点关注T】
          E【CD步骤有可能反复震荡，重点关注30分钟是否能出3买】这个过程可以针对30分钟震荡做T
            【防范：30分钟3卖】

          三：日线突破中枢阶段III
          F【关注30分钟的一卖减仓】这个过程一旦日线回调是不能T的，是出货后等待日线3买的阶段，只能做下日线3买
           【防范：5分钟出3卖后是可能跌回日线中枢趋势终结的，一旦发生要出局，然后等待日线3买】
           【防范：30分钟出3卖后一旦发生要出局，整段趋势终结】



        */
    }

    //大事件列表
    public void getMajorEvents(){
        //20190627-20190629：中非经贸博览会【湖南发展、隆平高科】另外对农业种植有影响
        //20190630:漳州核电厂1号组开工【沃尔核材、中核科技、台海核电、中广核级、兰石重装】

    }

    private static StockInfo setStockInfo(StockInfo stockInfo,double cost, int stockNum, double salePrice, double buyHour,String date,String disc){
        StockInfo stockInfo1 = new StockInfo(cost,stockNum,salePrice,buyHour,date,disc);
        stockInfo1.setStokeName(stockInfo.getStokeName());
        return stockInfo1;
    };
    static class StockInfo{
        private String stokeName;//名称
        private double cost;//成本
        private int stockNum;//数量
        private double salePrice;//出售价格
        private double buyHour;//持股时间(h)
        String date;//buyTime
        String disc;//阶段描述

        public StockInfo(String stokeName) {
            this.stokeName = stokeName;
        }
        public StockInfo(String stokeName,double cost, int stockNum, double salePrice, double buyHour,String date,String disc) {
            this.stokeName = stokeName;
            this.cost = cost;
            this.stockNum = stockNum;
            this.salePrice = salePrice;
            this.buyHour = buyHour;
            this.date = date;
            this.disc = disc;
        }
        public StockInfo(double cost, int stockNum, double salePrice, double buyHour,String date,String disc) {
            this.cost = cost;
            this.stockNum = stockNum;
            this.salePrice = salePrice;
            this.buyHour = buyHour;
            this.date = date;
            this.disc = disc;
        }

        public String getDate() {
            return date;
        }

        public String getDisc() {
            return disc;
        }

        public void setDisc(String disc) {
            this.disc = disc;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getStokeName() {
            return stokeName;
        }

        public void setStokeName(String stokeName) {
            this.stokeName = stokeName;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public int getStockNum() {
            return stockNum;
        }

        public void setStockNum(int stockNum) {
            this.stockNum = stockNum;
        }

        public double getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(double salePrice) {
            this.salePrice = salePrice;
        }

        public double getBuyHour() {
            return buyHour;
        }

        public void setBuyHour(double buyHour) {
            this.buyHour = buyHour;
        }
    }
}




/**
 * 针对形成自己交易规则的几点思考方向
 * 1.需要形成规则
 *   短线规则：关注成交量必须上量，要符合成交量形态，要足够安全离止损位要近，目标空间需要10个点以上，尽量优先选龙头,控制时间，买入必须要有反应，守住本金，关注压力位，快进快出
 *              短线讲究的是快准稳，所以不建议T，要建好就收，如果有盈利，个股继续强势可以相对提高止损位，其中参考以下几个方面
 *              筹码突破、止损要执行、上涨过程要上移止盈点
 *   长线规则：个股必须是未来主流方向，比如科技5G，操作以周线级别操作，周线一笔中可以反复T，明确关注日线的压力与支撑,30分钟3买全力看多，30分钟3卖清仓，需要每天关注是否趋势已经形成终结
 *              做T优先选择1卖的位置，当出现2卖时需要关注是否有机会形成3卖，需要关注风险
 *              1分钟形成3卖：30分钟开始转向下跌一笔
 *              1分钟形成3买：30分钟开始转向上涨一笔
 *              5分钟形成3卖：日线开始转向下跌一笔
 *              5分钟形成3买：日线开始转向上涨一笔
 * 2.坚定遵守规则
 *           需要注意盈利要卖股，通过卖股来抵消达到涨跌同步【涨10个点只需要跌9个点即可回到原点】，这是重点需要关注的
 * 3.执行规则
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */


















