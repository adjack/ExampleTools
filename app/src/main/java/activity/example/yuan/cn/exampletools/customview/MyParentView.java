package activity.example.yuan.cn.exampletools.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by 123 on 2018/9/5.
 */

public class MyParentView extends LinearLayout {

    private int mMove;
    private int yDown, yMove;
    private int i = 0;


    public MyParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                yDown = y;
                break;
            case MotionEvent.ACTION_MOVE:
                yMove = y;
                if ((yMove - yDown) > 0) {
                    mMove = yMove - yDown;
                    i += mMove;
                    layout(getLeft(), getTop() + mMove, getRight(), getBottom() + mMove);
                }
                break;
            case MotionEvent.ACTION_UP:
                layout(getLeft(), getTop() - i, getRight(), getBottom() - i);
                i = 0;
                break;
        }
        return true;
    }
}
