package activity.example.yuan.cn.exampletools.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by 123 on 2018/5/15.
 */

public class ScroolView2 extends LinearLayout {
    private int lastX,lastY,screenWidth,screenHeight;

    Scroller mScroller = null;
    private int mTouchSlop;

    public ScroolView2(Context context) {
        this(context, null);
    }

    public ScroolView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScroolView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels-50;//减去下边的高度

        mScroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }
    //定位
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //可以在这里确定这个viewGroup的：宽 = r-l.高 = b - t
    }

    OnViewClickListener onViewClickListener;
    public interface OnViewClickListener{
        void click();
    }

    public void setOnViewClickListener(OnViewClickListener onViewClickListener){
        this.onViewClickListener = onViewClickListener;
    }

    int left,top,right,bottom;
    int left2,top2,right2,bottom2;
    private long upClickTime = 0;
    //拦截touch事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        LogTool.e("onInterceptTouchEvent");
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                upClickTime = System.currentTimeMillis();
                lastX = (int) ev.getRawX();//设定移动的初始位置相对位置
                lastY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE://移动
                //event.getRawX()事件点距离屏幕左上角的距离
                int dx = (int) ev.getRawX() - lastX;
                int dy = (int) ev.getRawY() - lastY;

                left = getLeft() + dx;
                top = getTop() + dy;
                right = getRight() + dx;
                bottom = getBottom() + dy;

                left2 = getChildAt(0).getLeft() + dx;
                top2 = getChildAt(0).getTop() + dy;
                right2 = getChildAt(0).getRight() + dx;
                bottom2 = getChildAt(0).getBottom() + dy;

                if (left < 0) { //最左边
                    left = 0;
                    right = left + getWidth();

                    left2 = 0;
                    right2 = left2 + getChildAt(0).getWidth();
                }
                if (right > screenWidth) { //最右边
                    right = screenWidth;
                    left = right - getWidth();

                    right2 = screenWidth;
                    left2 = right2 - getChildAt(0).getWidth();
                }
                if (top < 0) {  //最上边
                    top = 0;
                    bottom = top + getHeight();

                    top2 = 0;
                    bottom2 = top2 + getChildAt(0).getHeight();
                }
                if (bottom > screenHeight-getHeight()) {//最下边
                    bottom = screenHeight-getHeight();
                    top = bottom - getHeight();

                    bottom2 = screenHeight-getChildAt(0).getHeight();
                    top2 = bottom2 - getChildAt(0).getHeight();
                }
                layout(left, top, right, bottom);
//                getChildAt(0).layout(left2, top2, right2, bottom2);//设置控件的新位置
//                LogTool.e("position:" + left + ", " + top + ", " + right + ", " + bottom);
                lastX = (int) ev.getRawX();//再次将滑动其实位置定位
                lastY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if(System.currentTimeMillis() - upClickTime < 500){
                    if(onViewClickListener != null){
                        onViewClickListener.click();
                    }
                }
                Log.d("smoothScrollBy",getChildAt(0).getLeft()+":"+screenWidth/2);
                if(getChildAt(0).getLeft() < screenWidth/2){
                    smoothScrollBy(100,0);
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);//super.onInterceptTouchEvent(ev)
    }

    public void smoothScrollBy(int dx, int dy) {

        //设置mScroller的滚动偏移量
        mScroller.startScroll(0, 0, dx, dy,1000);
//        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy,1000);
        invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();

        }
    }
}