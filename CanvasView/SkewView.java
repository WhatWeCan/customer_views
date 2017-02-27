package com.tjstudy.customerviews.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 错切
 */

public class SkewView extends View {

    private Paint mPaint;
    private ObjectAnimator rotation;

    public SkewView(Context context) {
        this(context, null);
    }

    public SkewView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkewView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        canvas.translate(width / 2, height / 2);
        canvas.save();

        canvas.skew(1, 0);
        canvas.drawRect(0, 0, width / 5, height / 5, mPaint);
        canvas.drawRect(-width / 5, -height / 5, 0, 0, mPaint);
        canvas.restore();

        canvas.rotate(90);
        canvas.skew(1, 0);
        canvas.drawRect(0, 0, width / 5, height / 5, mPaint);
        canvas.drawRect(-width / 5, -height / 5, 0, 0, mPaint);
    }
}
