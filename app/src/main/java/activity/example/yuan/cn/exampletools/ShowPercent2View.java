package activity.example.yuan.cn.exampletools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class ShowPercent2View extends View {
    private Context context;
    private int lineHeight = 30;
    private int percent = 90;

    public ShowPercent2View(Context context) {
        super(context);
        init(context,null, 0);
    }

    public ShowPercent2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs, 0);
    }

    public ShowPercent2View(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context,attrs, defStyle);
    }

    private void init(Context context,AttributeSet attrs, int defStyle) {
        this.context = context;
        paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(1);
    }

    Paint paint;

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        canvas.translate(getWidth()/2, getHeight()/2); //将位置移动画纸的坐标点:150,150

        int count = 100; //总刻度数
        float degrees = (float) (360.0 / count);
        for(int i=0 ; i <count ; i++){
            canvas.drawLine(0f, getWidth()/5, 0, getWidth()/5+lineHeight, paint);
            canvas.rotate(degrees,0f,0f); //旋转画纸
        }


        Paint tmpPaint = new Paint();
        tmpPaint.setStyle(Paint.Style.STROKE);
        tmpPaint.setStrokeWidth(lineHeight+5);
        tmpPaint.setAntiAlias(true);
        tmpPaint.setColor(Color.parseColor("#DB8E0D"));
        RectF oval=new RectF();                     //RectF对象
        oval.left=-(getWidth()/5+lineHeight/2);                              //左边
        oval.top=-(getWidth()/5+lineHeight/2);                                     //上边
        oval.right=(getWidth()/5+lineHeight/2);                             //右边
        oval.bottom=(getWidth()/5+lineHeight/2);
        canvas.drawArc(oval, -90, percent, false, tmpPaint);

//        for(int i=0 ; i <count ; i++){
//            if(i%5 == 0){
//                canvas.drawLine(0f, y, 0, y+12f, paint);
//                canvas.drawText(String.valueOf(i/5+1), -4f, y+25f, tmpPaint);
//
//            }else{
//                canvas.drawLine(0f, y, 0f, y +5f, tmpPaint);
//            }
//            canvas.rotate(360/count,0f,0f); //旋转画纸
//        }

//        //绘制指针
//        tmpPaint.setColor(Color.GRAY);
//        tmpPaint.setStrokeWidth(4);
//        canvas.drawCircle(0, 0, 7, tmpPaint);
//        tmpPaint.setStyle(Paint.Style.FILL);
//        tmpPaint.setColor(Color.YELLOW);
//        canvas.drawCircle(0, 0, 5, tmpPaint);
//        canvas.drawLine(0, 10, 0, -65, paint);


    }

    public void setPercent(int percent) {
        this.percent = percent;
        postInvalidate();
    }

}
