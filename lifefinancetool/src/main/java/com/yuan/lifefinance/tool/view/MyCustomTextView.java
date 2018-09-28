package com.yuan.lifefinance.tool.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.yuan.lifefinance.tool.tools.ActivityUtils;


/**
 * Created by jack on 2018/8/15.
 */

public class MyCustomTextView extends AppCompatTextView {
    public MyCustomTextView(Context context) {
        super(context);
        init(context);
    }

    public MyCustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyCustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        setTypeface(ActivityUtils.getTextFontResPingfang(context));
    }
}
