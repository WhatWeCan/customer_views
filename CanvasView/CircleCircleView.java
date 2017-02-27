package com.tjstudy.customerviews.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 让你晕眩的圆 圆 圆
 */

public class CircleCircleView extends View {

    private Paint mPaint;
    private int widh;
    private int height;

    public CircleCircleView(Context context) {
        this(context, null);
    }

    public CircleCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽度
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {//wrap_content时的处理
            widh = 200;//200px
        } else {
            widh = MeasureSpec.getSize(widthMeasureSpec);
        }
        //获取高度
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {//wrap_content时的处理
            height = 200;//200px
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec);
        }
        setMeasuredDimension(widh, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(widh/2,height/2);//将画布移到中间
        canvas.drawCircle(0, 0, widh / 2, mPaint);

        for(int i=0;i<30;i++){
            canvas.scale(0.86f,0.86f);
            int radius = widh / 2;
            canvas.drawCircle(0, 0, radius, mPaint);
        }
    }
}
