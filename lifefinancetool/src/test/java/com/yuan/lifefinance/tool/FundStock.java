package com.yuan.lifefinance.tool;

import org.junit.Test;

/**
 * copyright:华润创业(深圳)有限公司
 * author:admin
 * create_date:2019/7/29 10:03
 * <p>
 * describe:基金操作
 */
public class FundStock {
//========================================================================================================
//计算机板块基金[持股不动原则]
//      20190410建仓0.7756-》+66000
//      20190822卖出0.7008-》-20000
//      20190902卖出0.7368-》-5643
//      20190917买入0.7741-》+5600
//      20190926买入0.7365-》+10000

//创业板指数基金[围绕周线趋势持股，日线中枢上下轨买卖原则]
//      20191223买入》+15000  博日线多日杀跌后的反弹
//      20191231卖出》-15495  日线前高、周线前高附近卖出，等待回调
//========================================================================================================


















//========================================================================================================
//模拟盘：
//买卖原则：1.大盘日线上涨过程中，个股买周线买点附近的，2.周线杀跌是要回避【30分钟3卖出现、日线无量】
//个人基金建仓  总资金：141000  &:每一个月需要检查替换质地较差个股
//---------------------------------第一批20190729【8月】剩余本金141000？收益率：0%
//科大讯飞39.40//万向德农15.01//葛洲坝7.83//中国联通7.30//东方证券12.75//中国国航11.80//中科曙光39.40//万象德农14.40
//    20191017:盐津铺子41.35卖出300股 理由：离周线压力不远了
//    20191023:盐津铺子38.92卖出400股 理由：筹码松动了                                              出局换股【盈利：6085】目标：盐津铺子42.90
//             买入京东方A股价3.64-》8000股
//    20191209:1.卖出京东方A半仓，理由是里中枢上轨不远
//             2.买入万向德农，预期周线3买
//    20191212:卖出京东方A 剩余仓位                                                                        出局换股【盈利：3700】目标：未达目标
//    20191213:卖出部分同花顺
//    20191213:卖出全部同花顺                                                                              出局换股【盈利：4500】目标：未达目标
//---------------------------------第二批20190901【】剩余本金：？收益率：？
//========================================================================================================
    @Test
    public void test012(){
        double[] value = {0.03,-0.02};
        byte[] va = unsignedShortToByte2(2019);
        String str = toHexString4(va);
        byte[] bytes = fromHexString(str);
        System.out.println(byte2ToUnsignedShort(va));
        System.out.println(bytes[0]+"-"+bytes[1]);
    }

    /**
     * byte[]转换成16进制数组
     * @param bytes
     * @return
     */
    public String toHexString4(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 使用String的format方法进行转换
        for (byte b : bytes) {
            sb.append(String.format("%02x", new Integer(b & 0xff)));
        }

        return sb.toString();
    }

    /**
     * 将16进制字符串转换为byte[]
     * @explain 16进制字符串不区分大小写，返回的数组相同
     * @param hexString
     *            16进制字符串
     * @return byte[]
     */
    public byte[] fromHexString(String hexString) {
        if (null == hexString || "".equals(hexString.trim())) {
            return new byte[0];
        }

        byte[] bytes = new byte[hexString.length() / 2];
        // 16进制字符串
        String hex;
        for (int i = 0; i < hexString.length() / 2; i++) {
            // 每次截取2位
            hex = hexString.substring(i * 2, i * 2 + 2);
            // 16进制-->十进制
            bytes[i] = (byte) Integer.parseInt(hex, 16);
        }

        return bytes;
    }

    public byte[] unsignedShortToByte2(int s) {
        byte[] targets = new byte[2];
        targets[0] = (byte) (s >> 8 & 0xFF);
        targets[1] = (byte) (s & 0xFF);
        return targets;
    }
    public static int byte2ToUnsignedShort(byte[] bytes) {
        return byte2ToUnsignedShort(bytes, 0);
    }
    public static int byte2ToUnsignedShort(byte[] bytes, int off) {
        int high = bytes[off];
        int low = bytes[off + 1];
        return (high << 8 & 0xFF00) | (low & 0xFF);
    }



}
