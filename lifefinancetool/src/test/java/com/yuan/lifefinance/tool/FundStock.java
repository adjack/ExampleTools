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
//实盘：20190410建仓0.7756-》+66000
//      20190822卖出0.7008-》-20000
//      20190902卖出0.7368-》-5643
//========================================================================================================

//========================================================================================================
//模拟盘：
//买卖原则：1.大盘日线上涨过程中，个股买周线买点附近的，2.周线杀跌是要回避【30分钟3卖出现、日线无量】
//个人基金建仓  总资金：141000  &:每一个月需要检查替换质地较差个股
//---------------------------------第一批20190729【8月】剩余本金141000？收益率：0%
//科大讯飞39.40//盐津铺子42.90//同花顺133//中国联通7.30//东89方证券12.75//中国国航11.80//中科曙光39.40
//---------------------------------第二批20190901【9月】剩余本金：？收益率：？
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
