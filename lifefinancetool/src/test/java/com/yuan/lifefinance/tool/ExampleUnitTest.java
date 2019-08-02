package com.yuan.lifefinance.tool;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorredasd(){
        System.out.println(dealDouble(-1.12,2));
        System.out.println(dealDouble(.12,2));
        System.out.println(dealDouble(0.0,2));
        System.out.println(dealDouble(0.1,2));
        System.out.println(dealDouble(-0.12,2));
        System.out.println(dealDouble(12.12,2));
        System.out.println(dealDouble(-1.12,2));
    }

    private String dealDouble(double data,int newScale){
        try {
            DecimalFormat df = null;
            if(newScale == 2){
                df = new DecimalFormat("#.00");
                if(Double.valueOf(df.format(data)) == 0){
                    return "0.00";
                }
            }
            else{
                df = new DecimalFormat("#.0");
                if(Double.valueOf(df.format(data)) == 0){
                    return "0.0";
                }
            }
            if(data > 0 && data < 1 &&!df.format(data).startsWith("0")){
                return "0"+df.format(data);
            }

            if(data < 0 && Math.abs(data) < 1 &&!df.format(data).startsWith("-0")){
                return "-"+Math.abs(data);
            }

            return df.format(data);
        } catch (Exception e) {
            e.printStackTrace();
            return data+"";
        }
    }
    @Test
    public void addition_isCorrect1(){
        //2019-3-17/2024-3-17
        double totalSum = 2200;
        int yearNum = 1;
        double values = 0.30;
        yearNum = yearNum*12;
        yearNum = 18;
        double[] temps = new double[yearNum];
        for (int i = 0; i < yearNum;i++){
//            values = 0.05+(new Random().nextInt(6)/100d);
//            temps[i] = 0.05;
//            //第一阶段【活跃前期】
//            if(i<13 && i>4){
//                temps[i] = 0.05;
//            }
//            //第二阶段【市场活跃.牛市】
//            if(i<24 && i>13){
//                temps[i] = 0.16;
//            }
//            //第三阶段【市场理性.正常回调】
//            if(i<37 && i>24){
//                temps[i] = 0.05;
//            }
//            //第四阶段【稳定上涨】
//            if(i>37 && i< 48){
//                temps[i] = 0.08;
//            }
//            //第五阶段【合理投资】
//            if(i>48){
//                temps[i] = 0.10;
//            }

//            System.out.println(""+temps[i]*100);
            totalSum = totalSum*(1+values);
        }
        System.err.println("最终值："+totalSum);
        assertEquals(4, 2 + 2);

    }

    @Test
    public void addTestss(){
//        int a =0;
//        int b =0;
//        System.out.println(getVisitRate(a,b));

//        double degrees = 10;
//        double radians = Math.toRadians(degrees);
//
//        System.out.println( Math.sin(Math.toRadians(degrees)));
//        System.out.println( Math.sin(degrees));

//        System.out.println( Math.cos(5));

        double value1= 50000;//初始金额
        double rate= 0.30;//利润
        System.out.println("实际："+getValue(value1));
        double temp1 = Double.valueOf(getVisitRate(value1*rate,(value1*(1+rate))));
//        System.out.println( "盈利："+rate*100+"%后亏损 "+temp1*100+"% 既可回到原位 ");
        //
    }

    private double getValue(double value1){
        double num = value1;
        double temp = value1;
        double values = 0;
        for (int i=0;i<3;i++){
            num = num + num*0.1;
            temp = temp + temp*0.1;
            double temp1 = num*0.1 - value1*0.1;
            num = num - temp1;
            values = values + temp1;
            System.out.println( "本盈利阶段需卖出："+temp1);
        }
        System.out.println( "总共："+temp+"    额外："+values);
        return  num;
    }

    private String getVisitRate(double value1,double value2){
        try {
            if(value1 == 0|| value2 == 0){
                return "0";
            }
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(3);
//            System.out.println(nf.format(value1/value2));
            return nf.format(value1/value2);
        }catch (Exception ex){}
        return  "";
    }

    @Test
    public void addTestNumeqe(){
//        System.err.println(returnDouble(12.0,2));
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(Double.valueOf(df.format(.0))==0);

        String[] vakv = {"fdsfw","gfs是发DVD","fefwc","fevefca","ewc","cahw华东师范"};
        for(int i = 0;i<vakv.length;i++){
            System.out.println(dealValueMoreSpace(vakv[i])+"[3029-281-2e726]");
        }





    }

    private String dealValueMoreSpace(String str){
        String result = str;
        for(int i = 0;i<70-str.length();i++){
            result = result + " ";
        }//0004574049
        return result;
    }
    public static double returnDouble(double data,int newScale) {
        try {
            BigDecimal b = new BigDecimal(data);
            double f1 = b.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
            return f1;
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    @Test
    public void testlongTermTrackT(){
        List<DicText.StockInfo> value = DicText.longTermTrack2_7();//获取7月份操作     edf
        System.out.println("名称          买入       数量       卖出      操作时间           持股时间       盈利        金额[手续费]           阶段    [7月操作记录]");
//        System.err.println("----------------------------------------------------------------------------------");
        double resultValue = 0;
        for(int i=0; i<value.size(); i++){
            System.out.println(value.get(i).getStokeName()+"      "
                    +setStockPriceShow(value.get(i).getCost())+"      "
                    +setStockNumShow(value.get(i).getStockNum())+""
                    +setStockPriceShow(value.get(i).getSalePrice())+"      "
                    +value.get(i).getDate()+"      "
                    +value.get(i).getBuyHour()+"(h)      "
                    +getPriceRateValue(value.get(i).getCost(),value.get(i).getSalePrice())+"      "
                    +getPriceValue(value.get(i).getCost(),value.get(i).getSalePrice(),value.get(i).getStockNum())+"    "
                    +dealDisc(value.get(i).getDisc())+"      ");
            resultValue = resultValue + getPriceValueTotal(value.get(i).getCost(),value.get(i).getSalePrice(),value.get(i).getStockNum());
        }
        System.out.println("                                                                                                    Total:"+dealNum2(resultValue));
    }

    @Test
    public void otherValue(){
        System.out.println(getOtherValue(7.33,7.50,2000));
    }

    private String getOtherValue(double cost,double sale,double num){
        double value1 = (num/10000)*2  + cost*num/10000*0.487  + cost*num/10000*3;
        double value2 = (num/10000)*2  + sale*num/10000*0.487  + sale*num/10000*3 +sale*num/10000*10;
        return dealNum2(value1+value2);
    }

    private String dealDisc(String disc){//3.28
        String other = "";
        if("I".equals(disc)){
            other = " [阶段：安全反弹]";
        }
        else if("II".equals(disc)){
            other = " [阶段：可上可下]";
        }
        else if("III".equals(disc)){
            other = " [阶段：危险出货]*";
        }
        return disc + other;
    }

    private String setStockNumShow(int num){
        if(num>=1000){
            return num+"      ";
        }
        else{
            return num+"       ";
        }
    }
    private String setStockPriceShow(double value){
        String[] strs = (value+"").replace(".","#").split("#");
        String result = "";
        if(strs[1].length() >1){
            result =  value+"";
        }
        else{
            result =  value+"0";
        }

        return result.length() < 5?" "+result:result+"";
    }

    private String getPriceRateValue(double cost,double salePrice){
        double value = (salePrice - cost)/cost*100;
        String result = "";
        if(value == 0){
            result =  "+0.00%";
        }
        if((value+"").contains("-") || (value+"").contains("+")){
            result =  dealNum2(value)+"%";
        }
        else {
            result =  (value>=0?"+":"-")+dealNum2(value)+"%";
        }

        String temp = "";
        for(int i=0;i<7-result.length();i++){
            temp = temp + " ";
        }

        return result+temp;
    }

    private String getPriceValue(double cost,double salePrice,int stockNum){
        String result="";
        double value = (salePrice - cost)*stockNum;
        if(value >= 0){
            value = value - Double.valueOf(getOtherValue(cost,salePrice,stockNum));
        }
        else{
            value = Math.abs(value) + Double.valueOf(getOtherValue(cost,salePrice,stockNum));
            value = value - value*2;
        }
        if((value+"").contains("-") || (value+"").contains("+")){
            result =  dealNum2(value)+"";
        }
        else {
            result =  (value>=0?"+":"-")+dealNum2(value)+"";
        }
        result = result+" ["+getOtherValue(cost,salePrice,stockNum)+"]";
        String temp = "";
        for(int i=0;i<20-result.length();i++){
            temp = temp + " ";
        }
        return result+temp;
    }

    private double getPriceValueTotal(double cost,double salePrice,int stockNum){
        double value = (salePrice - cost)*stockNum - Double.valueOf(getOtherValue(cost,salePrice,stockNum));
        return value;
    }


    @Test
    public void addTestNum(){
        double targetValue = 0.06;//每次目标
        double warehousePosition = 0.33;//仓位
        double sum = 80200;
        double tempSum = 80200;
        int monthNum = 1;//12*2;

        int[] month_Num = new int[monthNum];
        double[][] value =new double[monthNum][];
        String[][] strlist =new String[monthNum][];
        //======================================================
        month_Num[0] = 6;//201905开始[长期占用资金1300]//-243
        value[0] = new double[]{0.01,-0.75,-2.7,-0.28,0,0.0};//81889 getMonth6NameArray
        value[0] = new double[]{0.00,-0.0,-0.0,-0.0,0,0.0};//getMonth7NameArray
        strlist[0] = DicText.getMonth7NameArray();
        //======================================================

        for(int j=0;j<monthNum;j++){
            for(int i=0;i<month_Num[j];i++){
                tempSum = tempSum + tempSum*targetValue*warehousePosition;
            }
            System.err.println("-----------------------------------");
            System.err.println(getDate(j));
            System.err.println("第"+(j+1)+"阶段预测目标[总共有"+month_Num[j]+"次机会]--->"+dealNum(tempSum));
            int tempNum = 0;
            for(int i=0;i<month_Num[j];i++){
                sum = sum + sum*value[j][i]/100;
                if(value[j][i]!=0){
                    tempNum++;
                    System.err.println(dealValueMoreSpace("     阶段"+(tempNum)+"结果 ---> "+dealNum(sum)+"   ("+(dealNum2(value[j][i]))+"%)     ")+strlist[j][i]);
                }
            }
            System.err.println("第"+(j+1)+"阶段实际结果[进行到"+tempNum+"次机会]--->"+dealNum(sum));
            System.err.println("-----------------------------------");
//            try {Thread.sleep(10);}catch (Exception ex){}

        }
        System.err.println("总共"+monthNum+"个月最终预测目标值->"+dealNum(tempSum));
    }

    private String getDate(int index){
        int iniMonth = 6+index;//初始月份
        return "[2019-"+String.format("%02d",iniMonth)+"开始]";
    }

    @Test
    public void addTest(){
//        byte[] value = {(byte)0xAA,0x00,0x19,0x00,(byte)0xBB,0x00,(byte)0xCC,0x00,(byte)0xDD,0x00,0x00};
//        byte[] value = hexStringToByteArray("4001000029010000425047FB20005A6400039247404401C17181120000012601AF0E60FD5E0E24069E9EA2A0BFF796C63ACB242B6CC90EED8873A11162F6C719A1A6F208741995EC221DDFD0311974A40E256AFFD5397318B308828693B18B22CAFB97844E25DE65847401C36DD21464495D22350BF1E4E937A7681467306E23FA719B205D2A6579E93C43B484B9092818D28BFD7A99AF21B032F0D076ABFEE8604725F09A8D5480961BAA77B652EAAD426D7E1A4E8E2B17904DFF915C92E30036E0376C2747FED3EF9372C0E42452D1CED516970915747111B3092F722A2B4F68AD9E316CCAF655561F95DB6606BEA243CBD539C1CF70AC588ED182AB60DCCAB8ED9A531509B711FA39E5647ED6952B49C615813D896F6D7A1CB791208F94E3E3636376BB563D103BF181DC41075D7161000000000000000000000000000000");
//        byte[] result = changgeBytes(value,200);
//        System.out.println(result.length+"-->"+bytesToHexString(result));
//
//        byte[] value2 = hexStringToByteArray("0911030407080e111c1d5ccecfd0d1d2d3d4d5d6d7d8d9dadbdc40012901425047FB205A64039247404401C1718112012601AF0E60FD5E0E24069E9EA2A0BFF796C63ACB242B6CC90EED8873A11162F6C719A1A6F208741995EC221DDFD0311974A40E256AFFD5397318B308828693B18B22CAFB97844E25DE65847401C36DD21464495D22350BF1E4E937A7681467306E23FA719B205D2A6579E93C43B484B9092818D28BFD7A99AF21B032F0D076ABFEE8604725F09A8D5480961BAA77B652EAAD426D7E1A4E8E2B17904DFF915C92E336E0376C2747FED3EF9372C0E42452D1CED516970915747111B3092F722A2B4F68AD9E316CCAF655561F95DB6606BEA243CBD539C1CF70AC588ED182AB60DCCAB8ED9A531509B711FA39E5647ED6952B49C615813D896F6D7A1CB791208F94E3E3636376BB563D103BF181DC41075D7161");
//        byte[] results = changgeBytesReturn(value2,200);
//        System.out.println(results.length+"-->"+bytesToHexString(results));


//        byte[] buff = new byte[2300010];
//        int num = 0;
//        for(int i=0;i<buff.length;i++){
//            num++;
//            buff[i] = (byte) num;
//            if (num>100) num = 0;
//        }
//        Object[] objdata = splitAry(buff, 50*1024);
//        System.out.println("num:"+objdata.length);
//        for(Object obj : objdata) {
//            byte[] bytedata = (byte[]) obj;
//            System.out.println("-->"+bytesToHexString(bytedata));
//        }


    }

//    private List<byte[]> dealByteArray(byte[] array){
//        int maxlength=100;//50*1024;
//        int size = array.length/maxlength;
//        List<byte[]> newByteArray = new ArrayList<>();
//        for(int i=0;i<size;i++){
//            array.
//        }
//
//        return newByteArray;
//    }

    public static Object[] splitAry(byte[] ary, int subSize) {
        int count = ary.length % subSize == 0 ? ary.length / subSize: ary.length / subSize + 1;

        List<List<Byte>> subAryList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int index = i * subSize;
            List<Byte> list = new ArrayList<>();
            int j = 0;
            while (j < subSize && index < ary.length) {
                list.add(ary[index++]);
                j++;
            }
            subAryList.add(list);
        }
        Object[] subAry = new Object[subAryList.size()];
        for(int i = 0; i < subAryList.size(); i++){
            List<Byte> subList = subAryList.get(i);
            byte[] subAryItem = new byte[subList.size()];
            for(int j = 0; j < subList.size(); j++){
                subAryItem[j] = subList.get(j);
            }
            subAry[i] = subAryItem;
        }
        return subAry;
    }

    @Test
    public void addTestdwf(){
        byte[] values = changgeBytesReturn(null,1000);
        System.out.println(values.length);

    }

    public byte[] changgeBytesReturn(byte[] inputValue,int index){
//        String str= bytesToHexString(inputValue);
        String str="383973646673646A6B6A6B646673613265393261686864666166647339383736756862657226262626252535627364736F6F6470736F736B61733132333536343634357364663634367336343835613535363464663466353435343536353434363536353461667364647366383973646673646A6B6A6B646673613265393261686864666166647339383736756862657226262626252535627364736F6F6470736F736B61733132333536343634357364663634367336343835613535363464663466353435343536353434363536353461667364647366383973646673646A6B6A6B646673613265393261686864666166647339383736756862657226262626252535627364736F6F6470736F736B61733132333536343634357364663634367336343835613535363464663466353435343536353434363536353461667364647366383973646673646A6B6A6B646673613265393261686864666166647339383736756862657226262626252535627364736F6F6470736F736B6173313233353634363435736466363436733634383561353536346466346635";

        int value1 = (int)Long.parseLong(str.substring(0,2),16);//55
        byte[] tempValue1_bytes = hexStringToByteArray(str.substring(4,4+(value1-1)*2));

        int value2 = (int)Long.parseLong(str.substring(2,4),16);//56
        byte[] tempValue2_bytes = hexStringToByteArray(str.substring(4+(value1-1)*2,4+(value1-1)*2+(value2-1)*2));

        String tempValue = str.substring((value1+value2)*2);//非零的数值

        String[] realValue = new String[value1+value2-2+tempValue.length()/2];//真实数据的总长度

        for(int i=0;i<tempValue1_bytes.length;i++){//前部分
            int temp_index = tempValue1_bytes[i] & 0xFF;
            realValue[temp_index-1]="00";
        }
        for(int i=0;i<tempValue2_bytes.length;i++){//后部分
            int temp_index = tempValue2_bytes[i] & 0xFF;
            realValue[index/2+temp_index-1]="00";
        }

        String result = "";
        int loopIndex = 0;
        for(int i=0;i<realValue.length;i++){
            if(realValue[i] == null){
                realValue[i] = tempValue.substring(loopIndex*2,loopIndex*2+2);
                loopIndex++;
            }
            result = result + realValue[i];
        }
        return hexStringToByteArray(result);
    }


    public byte[] changgeBytes(byte[] inputValue,int index){
        try {
            String str = bytesToHexString(inputValue);
            String[] tempValue1 = stringZeroNum(str.substring(0,index));
            String[] tempValue2 = stringZeroNum(str.substring(index));
            String newStr = tempValue1[0]+tempValue2[0]+tempValue1[1]+tempValue2[1];
            newStr = newStr+tempValue1[2]+tempValue2[2];
            return hexStringToByteArray(newStr);
//            return hexStringToByte(newStr);
        }
        catch (Exception ex){
            return inputValue;
        }
    }

    public byte[] hexStringToByteArray(String var0) {
        int var1;
        byte[] var2 = new byte[(var1 = var0.length()) / 2];

        for(int var3 = 0; var3 < var1; var3 += 2) {
            var2[var3 / 2] = (byte)(a(var0.charAt(var3)) << 4 | a(var0.charAt(var3 + 1)));
        }

        return var2;
    }

    private int a(char var0) {
        if (var0 >= '0' && var0 <= '9') {
            return var0 - 48;
        } else if (var0 >= 'A' && var0 <= 'F') {
            return var0 - 65 + 10;
        } else if (var0 >= 'a' && var0 <= 'f') {
            return var0 - 97 + 10;
        } else {
            throw new RuntimeException("Invalid hex char '" + var0 + "'");
        }
    }

    //得到00的数量和位置
    public String[] stringZeroNum(String value){
//        System.err.println("->"+value);
        String[] disc = new String[3];//1:00的数量 2:00的位置串
        String[] strs = new String[value.length()/2];
        String positionStr="";//00的位置
        int num=1;
        String nozeroValue = "";
        for(int i=0;i<strs.length;i++){
            strs[i]= value.substring(2*i,(i+1)*2);
            if(strs[i].equals("00")){
                num++;
                positionStr = positionStr + formatStr(Integer.toHexString(i+1));
            }
            else{
                nozeroValue = nozeroValue+strs[i];
            }
        }
        disc[0] = formatStr(Integer.toHexString(num));
        disc[1] = positionStr;
        disc[2] = nozeroValue;
        return disc;
    }

    private String formatStr(String str){
        if(str.length() < 2){
            str = "0"+str;
        }
        return str;
    }

    public final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    @Test
    public void addition_isCorrect2(){
        //<<2019-3-17/2024-03-17  ：初始：60000>>预计：30%
        //时间节点：2019-03-30  ：待定-------------------[78,000][61800[1]--64100[2]--66000[3]--64333[1]--64750[2]--65500[3]----58500[4]]
        //时间节点：2019-06-30  ：待定-------------------[101,400][106200[1]--[102880[2]--[100800[3]--[96200[4]--[92500[1]--[87503[2]--[88087[3]--[84700[4]--[84814[5]--[80600[1]--[81100[2]--[81367[3]--[80285[4]]
        //时间节点：2019-09-30  ：待定-------------------[131,820][78500[1]--[71800[2]--[67850[3]--[65000[4]--[62000[1]--[[2]--[[3]--[[4]--[[5]--[1]--[[2]--[[3]--[[4]]
        //时间节点：2019-12-31  ：待定-------------------[171,366][[1]--[[2]--[[3]--[[4]--[[1]--[[2]--[[3]--[[4]--[[5]--[1]--[[2]--[[3]--[[4]]

        //时间节点：2020-03-31  ：待定-------------------[222,776]
        //时间节点：2020-06-30  ：待定-------------------[289,609]
        //时间节点：2020-09-30  ：待定-------------------[376,491]
        //时间节点：2020-12-31  ：待定-------------------[489,438]

        //时间节点：2021-03-31  ：待定-------------------[636,270]
        //时间节点：2021-06-31  ：待定-------------------[827,151]
        //时间节点：2021-09-31  ：待定-------------------[1,075,296]
        //时间节点：2021-12-31  ：待定-------------------[1,397,885]

        //时间节点：2022-03-31  ：待定-------------------[1,817,251]
        //时间节点：2022-06-31  ：待定-------------------[2,362,426]
        //时间节点：2022-09-31  ：待定-------------------[3,071,154]
        //时间节点：2022-12-31  ：待定-------------------[3,992,500]

        //时间节点：2023-03-31  ：待定-------------------[5,190,250]
        //时间节点：2023-06-31  ：待定-------------------[6,747,324]
        //时间节点：2023-09-31  ：待定-------------------[8,771,522]
        //时间节点：2023-12-31  ：待定-------------------[11,402,978]


        double totalSum = 87000;
        int yearNum = 5;
        double values = 0.10;
        yearNum = yearNum*4;
//        yearNum = 12;
        double[] temps = new double[yearNum];
        double[] temps1 = {0.12,0.30,0.12,0.30,0.18,0.22};

        for (int i = 0; i < yearNum;i++){
//            values = 0.05+(new Random().nextInt(6)/100d);
            temps[i] = 0.10;
            //第一阶段【活跃前期】
            if(i<7 && i>2){
                temps[i] = 0.12;
            }
            //第二阶段【市场活跃.牛市】
            if(i<13 && i>7){
                temps[i] = 0.30;
            }
//            //第三阶段【市场理性.正常回调】
            if(i<19 && i>12){
                temps[i] = 0.12;
            }
            //第四阶段【稳定上涨】
            if(i>18 && i< 25){
                temps[i] = 0.18;
            }
            //第五阶段【合理投资】
            if(i>24){
                temps[i] = 0.15;
            }
//            temps[i]= temps1[new Random().nextInt(6)];
//            System.out.println(""+temps[i]*100);
            temps[i] = 0.30;
            totalSum = totalSum*(1+temps[i]);
            System.out.println("---->"+dealNum(totalSum));
        }
        System.err.println("最终值："+dealNum(totalSum));
        assertEquals(4, 2 + 2);


//        int[] v = {0xa0,0xfe};
//        System.err.println(v[0]);

    }

    public String dealNum(double d){
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(0);
        return ""+nf.format(d);
    }

    public String dealNum2(double d){
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        return nf.format(d);
    }

    @Test
    public void addition_isCorrect3(){
        int[] value = dealPoit(200,190,10,10,80,80);
        System.err.println("最终值："+value[0]+"/"+value[1]+"/"+value[2]+"/"+value[3]);

        value = dealPoit(199,190,119,110,80,80);
        System.err.println("最终值："+value[0]+"/"+value[1]+"/"+value[2]+"/"+value[3]);

        value = dealPoit(119,110,35,11,80,80);
        System.err.println("最终值："+value[0]+"/"+value[1]+"/"+value[2]+"/"+value[3]);
    }

    @Test
    public void addition_isCorrect4(){
        System.err.println("KAISHI");
        int num = 0;
        while(true){
//            num++;
//            if(num == 10){
//                return;
//            }
            int vaule = xxloop();
            System.err.println("测试:"+vaule);
        }

    }

    private int xxloop(){
        int num = 0;
        while(true){
            num++;
            if(num == 10){                return 12;            }
            System.err.println("KAISHI---"+num);
        }
    }


    public int[] dealPoit(int picW,int picH,int x,int y,int w,int h){
        int[] value = {x,y,w,h};
        int maxW = 120;
        int maxH = 140;
        if(w < maxW){//如果脸部宽小于120
            if(picW-x-maxW >= 0){//如果原图宽去掉左边距和最大宽120，剩下的空间大于0，说明直接放大脸部宽度到120没问题，不会越界
                int leftV = (maxW-w)/2;//计算需要放大到120宽的扩大距离
                value[0] = leftV <= x?x -leftV:0;//扩大的距离跟实际的左边距比较，赋值新的左边距
                value[2] = leftV <= x?maxW:w+x*2;//根据上面条件计算脸部的宽度应该增加多少
            }
            else{//说明直接扩大图片到120会越界，需要根据右边距去计算脸部的宽度
                int rightV = picW-x-w;//计算右边的剩余空间
                value[0] = x>rightV? x -rightV:0;//右边剩余空间跟左边的边距比较
                value[2] = x>rightV? w+rightV*2:w+x*2;
            }
        }
        if(h< maxH){//如果脸部高小于140
            if(picH-y-maxH>=0){
                int topV = (maxH-h)/2;
                value[1] = topV <= y?y -topV:0;
                value[3] = topV <= y?maxH:h+y*2;
            }
            else{
                int bottomV = (picH-y-h)/2;
                value[1] = y>bottomV? y -bottomV:0;
                value[3] = y>bottomV?h+bottomV*2:h+y*2;
            }
        }
        return value;

    }

}