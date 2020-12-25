package com.yuan.lifefinance.tool.util;

import org.junit.Test;

import java.text.DecimalFormat;

/**
 * @author huangwz
 * @des 线程任务的工厂类
 */
public class StockRecordUtil {

	public static String print(String date,String name,double cost,double price,int stockNum){
		System.out.println(date+"    "+name+"   "+dealDouble(cost,2)+"   "+dealDouble(price,2)+"   "+printLongth(stockNum,20)+dealDouble((price-cost)*stockNum,2));
		return dealDouble((price-cost)*stockNum,2);

	}

	public static String print(String date,String name,double cost,double price,int stockNum,double result){
		String flag = result<0?"       ********":"       >>>>>>>>";
		System.out.println(date+"    "+name+"   "+dealDouble(cost,2)+"   "+printLongth(stockNum,9)+" "+dealDouble(result,2)+flag);
		return dealDouble(result,2);
	}
	public static String err(String date,String name,double cost,double price,int stockNum,double result){
		System.err.print(date+"    "+name+"   "+dealDouble(cost,2)+"   "+printLongth(stockNum,9)+" "+dealDouble(result,2));
		return dealDouble(result,2);
	}

	public static String printLongth(int stockNum,int length){
		int spaceLength = length-String.valueOf(stockNum).length();
		String spaceValue = "";
		for(int i=0;i<spaceLength;i++){
			spaceValue = spaceValue + " ";
		}
		return stockNum+""+spaceValue;
	}


	public static String dealDouble(double data,int newScale){
		try {
			if(data == 0) return "00.00";
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
			if(df.format(data).charAt(1)=='.'){
				return "0"+df.format(data);
			}
			return df.format(data);
		} catch (Exception e) {
			return data+"";
		}
	}

}
