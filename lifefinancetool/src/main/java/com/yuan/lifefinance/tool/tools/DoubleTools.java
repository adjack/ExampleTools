package com.yuan.lifefinance.tool.tools;

import java.text.NumberFormat;

/**
 * Created by 123 on 2018/9/20.
 */

public class DoubleTools {

    //保留少数点的位数
    public static String dealMaximumFractionDigits(double value,int maximum){
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(maximum);
        return nf.format(value);

    }
}
