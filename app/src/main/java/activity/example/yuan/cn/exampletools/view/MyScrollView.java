package activity.example.yuan.cn.exampletools.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import activity.example.yuan.cn.exampletools.R;


public class MyScrollView extends LinearLayout implements OnClickListener{

	private ImageButton ib_function_open;
	private LinearLayout linear_content;

	Scroller mScroller = null;

	private int lastY = -1;

	/**
	 * 右边菜单收缩的距离
	 */
	private int scrollX = 350;

	public MenuState state = MenuState.open;

	/** 移动点的保护范围 */
	private int mTouchSlop;

	private static final float OFFSET_RADIO = 2.5f;
	private Context context;

//	private Button bt_normal;

	public enum MenuState {
		close, open;
	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		mScroller = new Scroller(context);
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

		init();

	}

	private void init(){
//		linear_content = (LinearLayout) getChildAt(1);
//		linear_content.getBackground().setAlpha(100);
		final DisplayMetrics dms = getResources().getDisplayMetrics();
		getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				Log.i("refreshLoadingViewsSize", "===");
//            	Toast.makeText(context, getChildCount()+"", 1000).show();
//            	bt_normal = (Button) getChildAt(1);
//				getViewTreeObserver().removeGlobalOnLayoutListener(this);
				scrollX = getWidth()-getChildAt(0).getWidth();
//				Toast.makeText(context,getTop()+"-"+getLeft()+"-"+dms.widthPixels+"--"+getWidth()+"--"+getChildAt(0).getWidth(),Toast.LENGTH_LONG).show();
			}
		});
	}

	private boolean mIsHandledTouchEvent;
	int screenWidth,screenHeight;

	public void setScrollOnclick(ImageButton view) {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels - 50;
		ib_function_open = view;
		ib_function_open.setOnClickListener(this);
//		ib_function_open.setOnTouchListener(this);

		setOnTouch();



	}

	private void setOnTouch(){
		ib_function_open.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action=event.getAction();
				Log.i("TAG", "Touch:"+action);

				//Toast.makeText(DraftTest.this, "位置："+x+","+y, Toast.LENGTH_SHORT).show();

				switch(action){
					case MotionEvent.ACTION_DOWN:
//					lastX = (int) event.getRawX();
						lastY = (int) event.getRawY();
						break;
					/**
					 * layout(l,t,r,b)
					 * l  Left position, relative to parent
					 t  Top position, relative to parent
					 r  Right position, relative to parent
					 b  Bottom position, relative to parent
					 * */
					case MotionEvent.ACTION_MOVE:
						if(state == MenuState.close){
//						int dx =(int)event.getRawX() - lastX;
							int dy =(int)event.getRawY() - lastY;

//						int left = v.getLeft() + dx;
							int top = v.getTop() + dy;
//						int right = v.getRight() + dx;
							int bottom = v.getBottom() + dy;

//						if(left < 0){
//							left = 0;
//							right = left + v.getWidth();
//						}

//						if(right > screenWidth){
//							right = screenWidth;
//							left = right - v.getWidth();
//						}

							int top2 = getTop() + dy;
							int bottom2 = getBottom() + dy;


							if(top < 0){
								top = 0;
								top2 = 0;
								bottom = top + v.getHeight();
								bottom2 = top2 + getHeight();
							}

							if(bottom > screenHeight){
								bottom = screenHeight;
								top = bottom - v.getHeight();

								bottom2 = screenHeight;
								top2 = bottom2 - getHeight();
							}

							v.layout(v.getLeft(), top, v.getRight(), bottom);
							layout(getLeft(), top2, getRight(), bottom2);

							RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) getLayoutParams();
							if(param.topMargin+dy > 0 && param.topMargin+dy < screenHeight- 800){
								param.topMargin = param.topMargin+dy;
							}
							setLayoutParams(param);
//						Log.i("", "position：" + left +", " + top + ", " + right + ", " + bottom);

//						lastX = (int) event.getRawX();  
							lastY = (int) event.getRawY();
						}
//					

						break;
					case MotionEvent.ACTION_UP:
						break;
				}
				return false;
			}});
	}

	public void scrollH(int absDiff) {
		// scrollBy(0,absDiff);
		// if (absDiff > 0 && absDiff < 900) {
		android.widget.FrameLayout.LayoutParams param = (android.widget.FrameLayout.LayoutParams) getLayoutParams();
		param.topMargin = absDiff;
		setLayoutParams(param);
		invalidate();
		// }

	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();

		}
	}

	public void closeMenu() {
		mScroller.startScroll(0, 0, -scrollX, 0, 1000);
		invalidate();
		state = MenuState.close;
	}

	public void openMenu() {
		mScroller.startScroll(0, 0, 0, 0, 1000);
		invalidate();
		state = MenuState.open;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.ib_function_open:
				if (state == MenuState.close) {
					openMenu();

				} else {
					closeMenu();
				}
				break;

			default:
				break;
		}

	}

}
