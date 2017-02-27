package com.tjstudy.customerviews.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.tjstudy.customerviews.bean.PieData;
import com.tjstudy.customerviews.utils.TranslateDimensionUtils;

import java.util.List;

/**
 * 百分比饼状图
 */

public class PieView extends View {

    private List<PieData> pieData;
    private Paint mPaint;
    private int width;
    private int height;
    private RectF bigRect;
    private Context mContext;

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(false);
        mPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 对外提供接口显示数据
     *
     * @param datas
     */
    public void setPieData(List<PieData> datas) {
        this.pieData = datas;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {//处理wrap_content的情况
            width = 300;//300px
        } else {
            width = MeasureSpec.getSize(widthMeasureSpec);
        }
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {//处理wrap_content的情况
            height = 300;//300px
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec);
        }

        bigRect = new RectF(0, 0, width, height);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //计算每一个pie的角度
        float startAngle = 0;
        float smallRadius = (width / 2) * 2 / 3;
        for (PieData data : pieData) {
            //将每一部分的圆弧画出来
            mPaint.setColor(data.getPieColor());
            float swipeAngle = data.getPieProportion() * 360;
            canvas.drawArc(bigRect, startAngle, swipeAngle, true, mPaint);

            //画文本
            float x0 = width / 2;
            float y0 = height / 2;
            float txtAngle = startAngle + swipeAngle / 2;
            double radians = Math.toRadians(txtAngle);
            double x1 = x0 + Math.cos(radians) * smallRadius;
            double y1 = y0 + Math.sin(radians) * smallRadius;
            Rect textRect = new Rect();
            String pieName = data.getPieName();
            mPaint.getTextBounds(pieName, 0, pieName.length(), textRect);

            x1 = x1 - textRect.width() / 2;
            y1 = y1 + textRect.height() / 2;

            mPaint.setColor(data.getPieTxtColor());
            mPaint.setTextSize(TranslateDimensionUtils.sp2px(mContext, data.getPieTextSize()));
            canvas.drawText(pieName, (float) x1, (float) y1, mPaint);
            startAngle += swipeAngle;
        }
    }
}