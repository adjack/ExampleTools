package activity.example.yuan.cn.exampletools.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @category: View实现涂鸦、撤销以及重做功能
 * @author: 锋翼
 * @link: www.apkstory.com
 * @date: 2012.1.4
 * 
 */
public class DrawView extends View {

	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Path mPath;
	private Paint mBitmapPaint;// 画布的画笔
	private Paint mPaint;// 真实的画笔
	private float mX, mY;// 临时点坐标
	private static final float TOUCH_TOLERANCE = 4;

	// 保存Path路径的集合,用List集合来模拟栈
	private static List<DrawPath> savePath;
	private static List<DrawPath> savePath_temp;
	private int current_operate = 0;//当期的操作编号
	private boolean if_operate = false;//判断用户是否操作了画布
	
	// 记录Path路径的对象
	private DrawPath dp;

	private int screenWidth, screenHeight;// 屏幕長寬

	private class DrawPath {
		public Path path;// 路径
		public Paint paint;// 画笔
	}

	public DrawView(Context context) {
		super(context);
		DisplayMetrics dm = new DisplayMetrics();
		((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;

		mBitmap = Bitmap.createBitmap(screenWidth, screenHeight,
				Bitmap.Config.ARGB_8888);
		// 保存一次一次绘制出来的图形
		mCanvas = new Canvas(mBitmap);

		//mBitmapPaint = new Paint(Paint.DITHER_FLAG);
		mBitmapPaint = new Paint(Paint.DITHER_FLAG);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);// 设置外边缘
		mPaint.setStrokeCap(Paint.Cap.SQUARE);// 形状
		mPaint.setStrokeWidth(5);// 画笔宽度

		savePath = new ArrayList<DrawPath>();
	}

	@Override
	public void onDraw(Canvas canvas) {
		//canvas.drawColor(0xFFAAAAAA);
		// 将前面已经画过得显示出来
		canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
		if (mPath != null) {
			// 实时的显示
			canvas.drawPath(mPath, mPaint);
		}
	}

	private void touch_start(float x, float y) {
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
	}

	private void touch_move(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(mY - y);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			// 从x1,y1到x2,y2画一条贝塞尔曲线，更平滑(直接用mPath.lineTo也是可以的)
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;
		}
	}

	private void touch_up() {
		mPath.lineTo(mX, mY);
		mCanvas.drawPath(mPath, mPaint);
		// 将一条完整的路径保存下来(相当于入栈操作)
		if(!if_operate){
			savePath.add(dp);
		}
		else{
			savePath = savePath.subList(0, current_operate);
			savePath.add(dp);
		}
		current_operate = savePath.size();
		mPath = null;// 重新置空
	}
	
	public void newCreate(){
		savePath.clear();
		current_operate = 0;
		mBitmap = Bitmap.createBitmap(screenWidth, screenHeight,
				Bitmap.Config.ARGB_8888);
		mCanvas.setBitmap(mBitmap);// 重新设置画布，相当于清空画布
		invalidate();// 刷新
	}

	public Bitmap getDrawBitmap(){
			return mBitmap;
	}

	/**
	 * 撤销的核心思想就是将画布清空， 将保存下来的Path路径最后一个移除掉， 重新将路径画在画布上面。
	 */
	public void updo() {
		if_operate = true;
		mBitmap = Bitmap.createBitmap(screenWidth, screenHeight,
				Bitmap.Config.ARGB_8888);
		mCanvas.setBitmap(mBitmap);// 重新设置画布，相当于清空画布
		// 清空画布，但是如果图片有背景的话，则使用上面的重新初始化的方法，用该方法会将背景清空掉...
		if (savePath != null && savePath.size() > 0 && current_operate > 0) {
			// 移除最后一个path,相当于出栈操作
			//savePath.remove(savePath.size() - 1);
			current_operate--;
			savePath_temp = savePath.subList(0, current_operate);
			Iterator<DrawPath> iter = savePath_temp.iterator();
			while (iter.hasNext()) {
				DrawPath drawPath = iter.next();
				mCanvas.drawPath(drawPath.path, drawPath.paint);
			}
			invalidate();// 刷新

			/* 在这里保存图片纯粹是为了方便,保存图片进行验证 */
//			String fileUrl = Environment.getExternalStorageDirectory()
//					.toString() + "/android/data/test.png";
//			try {
//				FileOutputStream fos = new FileOutputStream(new File(fileUrl));
//				mBitmap.compress(CompressFormat.PNG, 100, fos);
//				fos.flush();
//				fos.close();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

		}
	}

	/**
	 * 重做的核心思想就是将撤销的路径保存到另外一个集合里面(栈)， 然后从redo的集合里面取出最顶端对象， 画在画布上面即可。
	 */
	public void downdo() {
		if_operate = true;
		mBitmap = Bitmap.createBitmap(screenWidth, screenHeight,
				Bitmap.Config.ARGB_8888);
		mCanvas.setBitmap(mBitmap);// 重新设置画布，相当于清空画布
		// 清空画布，但是如果图片有背景的话，则使用上面的重新初始化的方法，用该方法会将背景清空掉...
		if (savePath != null && savePath.size() > 0 && current_operate < savePath.size()) {
			// 移除最后一个path,相当于出栈操作
			//savePath.remove(savePath.size() - 1);
			current_operate++;
			savePath_temp = savePath.subList(0, current_operate);
			Iterator<DrawPath> iter = savePath_temp.iterator();
			while (iter.hasNext()) {
				DrawPath drawPath = iter.next();
				mCanvas.drawPath(drawPath.path, drawPath.paint);
			}
			invalidate();// 刷新
		}
	}
	
	public void clean(){
		if_operate = true;
		mBitmap = Bitmap.createBitmap(screenWidth, screenHeight,
				Bitmap.Config.ARGB_8888);
		mCanvas.setBitmap(mBitmap);// 重新设置画布，相当于清空画布
		invalidate();// 刷新
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 每次down下去重新new一个Path
			mPath = new Path();
			// 每一次记录的路径对象是不一样的
			dp = new DrawPath();
			dp.path = mPath;
			dp.paint = mPaint;
			touch_start(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			touch_move(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			touch_up();
			invalidate();
			break;
		}
		return true;
	}
}
