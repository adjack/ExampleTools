package com.yuan.lifefinance.tool.tools;

import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by jack on 2018/7/10.
 */

public class StringInputUtils {
    /**
     * EditText 控件
     *
     * @param hintContent hint 内容
     * @param hintSize    hint 内容size
     */
    public static void setEditTextHintSize(EditText editText, String hintContent, int hintSize) {
        try {
            // 设置hint字体大小
            SpannableString ss = new SpannableString(hintContent);
            AbsoluteSizeSpan ass = new AbsoluteSizeSpan(hintSize, true);
            ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            // 设置hint
            editText.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
        }
        catch (Exception ex){}
    }

    /**
     * 判断是否为空
     * @param textView
     * @return
     */
    public static String value(TextView textView){
        String value = textView.getText().toString();
        return TextUtils.isEmpty(value)?"":value;
    }

    public static boolean valueIsEmpty(@NonNull TextView textView){
        String value = textView.getText().toString();
        return TextUtils.isEmpty(value);
    }

    /**
     * 处理字符串对象
     * @param ob
     * @return
     */
    public static String getStrObj(Object ob){
        try {
            String value = ob.toString();
            return TextUtils.isEmpty(value)?"":value;
        }
        catch (Exception ex){
            return "";
        }
    }

    public static String getStrObjNoNull(Object ob){
        try {
            String value = ob.toString();
            if(value.equalsIgnoreCase("null")) value = "";
            return TextUtils.isEmpty(value)?"":value;
        }
        catch (Exception ex){
            return "";
        }

    }

    public static String getIntObj(Object ob){
        try {
            String value = ob.toString();
            if(value.equals("null")) value = "0";
            return TextUtils.isEmpty(value)?"0":value;
        }
        catch (Exception ex){
            return "0";
        }

    }

}
