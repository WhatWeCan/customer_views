package com.tjstudy.customerviews.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tjstudy.customerviews.R;
import com.tjstudy.customerviews.utils.TranslateDimensionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 验证码操作
 * 1、干扰项 画点 画线
 */

public class YZMView extends View {

    private Paint mPointPaint;
    private Paint mLinePaint;
    private int height;
    private int width;
    private ArrayList<PointF> pointFs;
    private List<PointF> pointLines;
    private int mDefaultColor = Color.BLACK;
    private int mDefaultTxtSize =20;
    private int mDefaultDisPointSize =4;
    //上面的mDefaultTxtSize mDefaultDisPointSize是px值
    //private int mDefaultTxtSize = TranslateDimensionUtils.sp2px(context, 20);
    //private int mDefaultDisPointSize = TranslateDimensionUtils.sp2px(context, 4);
    private int mDefaultDisLineCount = 4;
    private int mDefaultDisPointCount = 240;
    private int mTxtColor;
    private float mTxtSize;
    private float mDisPointSize;
    private int mDisPointColor;
    private float mDisLineCount;
    private float mDisPointCount;
    private int mDisLineColor;
    private Paint mPaint;
    private String mVerificationTxt;
    private int mTxtWidth;
    private int mTxtHeight;
    private float mDisLineSize;

    public YZMView(Context context) {
        this(context, null);
    }

    public YZMView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YZMView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.YZMView, defStyleAttr, 0);
        //获取自定义属性
        mTxtColor = typedArray.getColor(R.styleable.YZMView_txtColor, mDefaultColor);
        mTxtSize = typedArray.getDimension(R.styleable.YZMView_txtSize, mDefaultTxtSize);
        mDisPointSize = typedArray.getDimension(R.styleable.YZMView_disturbPointSize, mDefaultDisPointSize);
        mDisPointColor = typedArray.getColor(R.styleable.YZMView_disturbPointColor, mDefaultColor);
        mDisPointCount = typedArray.getInteger(R.styleable.YZMView_disturbPointCount, mDefaultDisPointCount);
        mDisLineCount = typedArray.getInteger(R.styleable.YZMView_disturbLineCount, mDefaultDisLineCount);
        mDisLineColor = typedArray.getColor(R.styleable.YZMView_disturbLineColor, mDefaultColor);
        mDisLineSize = typedArray.getDimension(R.styleable.YZMView_disturbLineSize, mDefaultDisPointSize);
		typedArray.recycle();

        initPaint();
    }

    private void initPaint() {
        //画点
        mPointPaint = new Paint();
        mPointPaint.setStrokeWidth(mDisPointSize);
        mPointPaint.setColor(mDisPointColor);
        mPointPaint.setStyle(Paint.Style.STROKE);

        //画线
        mLinePaint = new Paint();
        mLinePaint.setColor(mDisLineColor);
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setStrokeWidth(mDisLineSize);

        //画txt
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mTxtColor);

        mPaint.setTextSize(mTxtSize);
        //获取文字的宽度和高度
        Rect textRect = new Rect();

        mVerificationTxt = getRandomCode();
        mPaint.getTextBounds(mVerificationTxt, 0, mVerificationTxt.length(), textRect);
        mTxtWidth = textRect.width();
        mTxtHeight = textRect.height();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取控件的宽高
        //获取控件的大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {//处理wrap_content的情况
            width = 200;//默认宽度是200px
        } else {//有确切值的情况
            width = MeasureSpec.getSize(widthMeasureSpec);
        }
        //获取控件的高度
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            height = 100;
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec);
        }
        setMeasuredDimension(width, height);
    }

    /**
     * 干扰点
     */
    private void setPoint() {
        pointFs = new ArrayList<>();
        for (int i = 0; i < mDisPointCount; i++) {
            pointFs.add(new PointF(new Random().nextInt(width) + 6,
                    new Random().nextInt(height) + 6));
        }
    }

    /**
     * 干扰线
     */
    private void setLines() {
        pointLines = new ArrayList<>();
        //随意生成8个点 最后将这八个点进行连线
        for (int i = 0; i < mDisLineCount * 2; i++) {
            //随机两个点
            int x = new Random().nextInt(width);
            int y = new Random().nextInt(height);
            PointF pointF = new PointF(x, y);
            pointLines.add(pointF);
        }
    }

    private String getRandomCode() {
        String randomCode = "";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            randomCode += random.nextInt(10);//生成包含0 不包含10的随机数
        }
        return randomCode;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取 干扰数据
        setPoint();
        setLines();
        //画txt
        mVerificationTxt = getRandomCode();
        float x = (width - mTxtWidth) / 2;
        float y = (height + mTxtHeight) / 2;
        canvas.drawText(mVerificationTxt, x, y, mPaint);

        //画干扰点
        for (PointF point : pointFs) {
            canvas.drawPoint(point.x, point.y, mPointPaint);
        }
        for (int i = 0; i < pointLines.size() - 1; i++) {
            PointF pointStart = pointLines.get(i);
            PointF pointEnd = pointLines.get(++i);
            canvas.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y, mLinePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        setPoint();
        setLines();
        mVerificationTxt = getRandomCode();
        postInvalidate();
        return false;
    }
}
