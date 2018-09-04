package activity.example.yuan.cn.exampletools;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import activity.example.yuan.cn.exampletools.utils.ScreenSizeUtil;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/4/18.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    private static List<BaseActivity> activityList = new ArrayList<>();
    /**
     * 手势监听
     */
    private GestureDetector gestureDetector;
    private View rootView;
    private boolean isScroll = false;
    /**
     * 移动距离
     */
    private float windowWidth;
    private BaseActivity beforeActivity;
    /**
     * 上一个Activity偏移量
     */
    private float offsetX;
    /**
     * 上一个页面移出的位置
     */
    private float outsideWidth;
    private boolean canScrollBack = true;
    private boolean canScroll = false;
    private float downX;
    private float downY;
    private VelocityTracker mVelocityTracker = null;
    private boolean isScrollNow = true;
    private boolean isFinishAnimate = false;

    @SuppressLint({"RtlHardcoded", "InlinedApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Slide(Gravity.RIGHT));
        }
//        setContentView(getLayoutId());
        //把当前Activity加到列表里面
        activityList.add(this);
        //初始化绑定id
        ButterKnife.bind(this);
        initScrollBack();
    }

    @Override
    public void startActivity(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            super.startActivity(intent);
        }
    }


    /**
     * 初始化左滑退出功能
     */
    private void initScrollBack() {
        windowWidth = getWindowManager().getDefaultDisplay().getWidth();
        outsideWidth = -windowWidth / 4;
        offsetX = outsideWidth;
        gestureDetector = new GestureDetector(this, new GestureListener());
        rootView = getWindow().getDecorView();

    }

    /**
     * 控制上一个Activity移动
     */
    private void beforeActivityTranslationX(float translationX) {
        if (beforeActivity != null) {
            beforeActivity.getRootView().setTranslationX(translationX);
        }
    }

    /**
     * 设置是否能滑动
     *
     * @param canScrollBack true 可以滑动
     */
    protected void setCanScrollBack(boolean canScrollBack) {
        this.canScrollBack = canScrollBack;
    }

    public View getRootView() {
        return rootView;
    }

    @Override
    public void finish() {
        activityList.remove(this);
        if (offsetX < 0.0001 || offsetX > 0.0001) {
            beforeActivityTranslationX(0);
        }
        super.finish();
    }

    /**
     * 控制分发事件
     */
    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        if (canScrollBack && ev.getX() < windowWidth / 14) {
            if (activityList.size() > 1) {
                beforeActivity = activityList.get(activityList.size() - 2);
                beforeActivityTranslationX(outsideWidth);
            }
            canScroll = true;
            return onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isFinishAnimate) {
            return false;
        }
        addVelocityTrackerEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = event.getY();
                downX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                //当手指离开的时候
                float moveX = event.getX();
                if (moveX - downX < 1 && moveX < ScreenSizeUtil.dp2px(100, BaseActivity.this) && downY < ScreenSizeUtil.dp2px(100, BaseActivity.this) && canScrollBack) {//向右
                    isFinishAnimate = true;
                    if (beforeActivity != null) {
                        ObjectAnimator.ofFloat(beforeActivity.getRootView(), "translationX", offsetX, 0).setDuration(500).start();
                    }
                    ObjectAnimator moveIn = ObjectAnimator.ofFloat(rootView, "translationX", event.getX(), windowWidth);
                    moveIn.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            finish();
                            isFinishAnimate = false;
                        }
                    });
                    moveIn.setDuration(500).start();
                    return false;
                }
                break;
        }

        if (canScrollBack && canScroll && isScrollNow) {
            if (event.getAction() == MotionEvent.ACTION_UP && isScroll) {
                isScroll = false;
                canScroll = false;

                Log.i(TAG, "速度：" + getTouchVelocityX());
                float moveX = event.getX();
                if (getTouchVelocityX() > 1000 && downX - moveX < 1) {
                    isFinishAnimate = true;
                    ObjectAnimator.ofFloat(beforeActivity.getRootView(), "translationX", offsetX, 0).setDuration(500).start();
                    ObjectAnimator moveIn = ObjectAnimator.ofFloat(rootView, "translationX", event.getX(), windowWidth);
                    moveIn.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            finish();
                            isScrollNow = true;
                            isFinishAnimate = false;
                        }
                    });
                    moveIn.setDuration(500).start();
                    isScrollNow = false;
                    return true;
                }
                //退出当前Activity
                if (event.getX() > windowWidth / 2) {
                    isFinishAnimate = true;
                    if (beforeActivity != null) {
                        ObjectAnimator.ofFloat(beforeActivity.getRootView(), "translationX", offsetX, 0).setDuration(500).start();
                    }
                    ObjectAnimator moveIn = ObjectAnimator.ofFloat(rootView, "translationX", event.getX(), windowWidth);
                    moveIn.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            finish();
                            isScrollNow = true;
                            isFinishAnimate = false;
                        }
                    });
                    moveIn.setDuration(500).start();
                    isScrollNow = false;
                } else if (event.getX() < windowWidth / 2) {//反弹回来
                    ObjectAnimator.ofFloat(rootView, "translationX", event.getX(), 0).setDuration(500).start();
                    if (beforeActivity != null) {
                        ObjectAnimator.ofFloat(beforeActivity.getRootView(), "translationX", offsetX, outsideWidth).setDuration(500).start();
                    }
                    offsetX = outsideWidth;
                }
            } else {
                gestureDetector.onTouchEvent(event);
            }
        }
        return true;
    }

    private void addVelocityTrackerEvent(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    // 获得横向的手速
    private int getTouchVelocityX() {
        if (mVelocityTracker == null)
            return 0;
        mVelocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) mVelocityTracker.getXVelocity();
        return Math.abs(velocity);
    }

    /**
     * 手势监听
     */
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (e1 != null) {
                handlerCurrentActivityScroll(e2);
                handleBeforeActivityScroll(e2, distanceX);
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        /**
         * 处理当前页面滑动
         */
        private void handlerCurrentActivityScroll(MotionEvent e2) {
            isScroll = true;
            rootView.setTranslationX(e2.getX());
            if (e2.getX() > windowWidth - 20) {
                finish();
            }
        }

        /**
         * 处理上一个页面滑动
         */
        private void handleBeforeActivityScroll(MotionEvent e2, float distanceX) {
            if (beforeActivity != null) {
                offsetX = distanceX < 0 ? offsetX + Math.abs(distanceX) / 4 : offsetX - Math.abs(distanceX) / 4;
                if (offsetX > 0.0001) {
                    offsetX = 0f;
                }
                beforeActivity.getRootView().setTranslationX(offsetX);
            }
        }
    }

    public void finishAll() {
        try {
            for (Activity activity : activityList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}