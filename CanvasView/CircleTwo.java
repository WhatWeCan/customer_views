package com.tjstudy.customerviews.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 环形
 */

public class CircleTwo extends View {

    private Paint mPaint;

    public CircleTwo(Context context) {
        this(context, null);
    }

    public CircleTwo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleTwo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(false);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        canvas.translate(width / 2, height / 2);
        int radius = width / 2;
        canvas.drawCircle(0, 0, radius, mPaint);
        //大小圆 相隔14px
        canvas.drawCircle(0, 0, radius - 14, mPaint);

        for (int i = 0; i < 360; i += 10) {
            //每隔10度切一下
            canvas.drawLine(0, radius - 14, 0, radius, mPaint);
            canvas.rotate(10);
        }
    }
}
