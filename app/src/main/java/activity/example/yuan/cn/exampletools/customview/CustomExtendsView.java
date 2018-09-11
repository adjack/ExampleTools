package activity.example.yuan.cn.exampletools.customview;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 123 on 2018/9/5.
 * 继承现有控件拓展-实现防止不间断点击按钮
 */

public class CustomExtendsView extends AppCompatButton{
    private long intervalTime = 2000;//点击的间隔时间
    public CustomExtendsView(Context context) {
        super(context);
    }

    public CustomExtendsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomExtendsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
