package activity.example.yuan.cn.exampletools.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ToogleButton extends View {

	private static final String Tag = "ToogleButton";
	private int width;
	private int height;
	private Paint mPaint;

	public ToogleButton(Context context) {
		super(context);
		init();
	}

	public ToogleButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ToogleButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setColor(Color.GRAY);
		mPaint.setAlpha(255);
		mPaint.setAntiAlias(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// draw rect
		RectF rectF = new RectF();
		width = 200;
		height = 50;
		rectF.set(0, 0, width, height);
		canvas.drawRoundRect(rectF, 6, 6, mPaint);
		// draw half
		mPaint.setColor(Color.rgb(56, 189, 254));
		RectF tButton = new RectF();
		tButton.set(0, 0, width / 2, height);
		canvas.drawRoundRect(tButton, 6, 6, mPaint);
		// draw text
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(21);
		mPaint.setTextAlign(Align.CENTER);
		mPaint.setTypeface(Typeface.DEFAULT);
		canvas.drawText("是", 15, 25, mPaint);
		canvas.drawText("否", 57, 25, mPaint);
		// draw half
		mPaint.setColor(Color.rgb(56, 189, 254));
		RectF button = new RectF();
		button.set(0, 0, width / 2, height);
		canvas.drawRoundRect(button, 6, 6, mPaint);
		super.onDraw(canvas);
	}

//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		width = MeasureSpec.getSize(widthMeasureSpec);
//		height = MeasureSpec.getSize(heightMeasureSpec);
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			int eventX = (int) event.getX();
			int eventY = (int) event.getY();

			if (eventX < (width / 2)) {

			} else {

			}
		}
		return true;
	}
}
