package activity.example.yuan.cn.exampletools.view;

import android.content.Context;
import android.os.SystemClock;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by 123 on 2018/5/15.
 */

public class ScrollView extends AppCompatButton implements View.OnTouchListener {
    Scroller mScroller = null;
    private Context context;
    private int mTouchSlop;
    private int screenWidth;
    private int screenHeight;
    private int viewWidth;

    public ScrollView(Context context) {
        super(context);
        init(context);
    }

    public ScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    OnViewClickListener onViewClickListener;
    public interface OnViewClickListener{
        void click();
    }

    public void setOnViewClickListener(OnViewClickListener onViewClickListener){
        this.onViewClickListener = onViewClickListener;
    }

    private void init(Context context) {
        this.context = context;
        mScroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        setOnTouchListener(this);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("refreshLoadingViewsSize", "===");
//            	Toast.makeText(context, getChildCount()+"", 1000).show();
//            	bt_normal = (Button) getChildAt(1);
//				getViewTreeObserver().removeGlobalOnLayoutListener(this);
                viewWidth = getWidth();
//				Toast.makeText(context,getTop()+"-"+getLeft()+"-"+dms.widthPixels+"--"+getWidth()+"--"+getChildAt(0).getWidth(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private int lastY = -1, lastX = -1;
    private long upClickTime = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                upClickTime = System.currentTimeMillis();
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;

                int left = v.getLeft() + dx;
                int top = v.getTop() + dy;
                int right = v.getRight() + dx;
                int bottom = v.getBottom() + dy;

                if (left < 0) {
                    left = 0;
                    right = left + v.getWidth();
                }

                if (right > screenWidth) {
                    right = screenWidth;
                    left = right - v.getWidth();
                }

                if (top < 0) {
                    top = 0;
                    bottom = top + v.getHeight();
                }

                if (bottom > screenHeight-200) {
                    bottom = screenHeight-200;
                    top = bottom - v.getHeight();
                }

                v.layout(left, top, right, bottom);
//                layout(getLeft(), top2, getRight(), bottom2);

//                    RelativeLayout.LayoutParams param = (android.widget.RelativeLayout.LayoutParams) getLayoutParams();
//                    if(param.topMargin+dy > 0 && param.topMargin+dy < screenHeight- 800){
//                        param.topMargin = param.topMargin+dy;
//                    }
//                    setLayoutParams(param);
//						Log.i("", "position：" + left +", " + top + ", " + right + ", " + bottom);

                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
//                if(System.currentTimeMillis() - upClickTime < 800){
//                    if(onViewClickListener != null){
//                        onViewClickListener.click();
//                    }
//                }
                Log.i("currentThreadTimeMillis",getLeft()+"--"+screenWidth/2);
//                if(getLeft() < screenWidth/2){//左移
//                    mScroller.startScroll(0, 0, 100, 0, 1000);
//                    invalidate();
//                }
//                else{
//
//                }
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();

        }
    }
}
