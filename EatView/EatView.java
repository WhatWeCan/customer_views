package com.tjstudy.customerviews.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.tjstudy.customerviews.R;
import com.tjstudy.customerviews.utils.TranslateDimensionUtils;

/**
 * eatView
 */
public class EatView extends View {

    private Paint mArcPaint;
    private int currentProgress;
    private Paint mTxtPaint;
    //记录“怪物”的中心距离到圆心的距离---即表示要移动的距离
    private Paint mRightLinePaint;
    private Paint mLeftLinePaint;
    private Paint mCoverPaint;
    String mCurrentFinishTxt = "";
    private ObjectAnimator finishAnimator;
    private int width;
    private int height;
    private float mCurrentMouthDegree;
    private float mEyeRadius;
    private TypedArray typedArray;
    private Context mContext;
    private int mEatRadius;
    private float mMouthDegree;
    private int mOnceProgress;
    private int mEatColor;
    private int mRtLineColor;
    private int mRtLineHeight;
    private int mLtLineColor;
    private int mLtLineWidth;
    private int mLtLineGap;
    private int mProgressTxtColor;
    private int mProgressTxtSize;
    private int mFinishTxtSize;
    private int mFinishTxtColor;
    private int mEatTime;
    private String mFinishTxt;
    private int mLoadFinishTime;
    private int mEyeColor;

    public EatView(Context context) {
        this(context, null);
    }

    public EatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.EatView, defStyleAttr, 0);
        mContext = context;
        initData();

        initPaint();
        doAnim();
    }

    /**
     * 从styleable中获取属性
     */
    private void initData() {
        mEatRadius = (int) typedArray.getDimension(R.styleable.EatView_eatRadius, TranslateDimensionUtils.dip2px(mContext, 20));
        mMouthDegree = typedArray.getFloat(R.styleable.EatView_mouthDegree, 60);
        mCurrentMouthDegree = mMouthDegree;
        mEyeRadius = mEatRadius / 6;
        mOnceProgress = typedArray.getInteger(R.styleable.EatView_onceProgress, 2);
        mEatColor = typedArray.getColor(R.styleable.EatView_eatColor, Color.BLACK);
        mRtLineColor = typedArray.getColor(R.styleable.EatView_rightLineColor, Color.BLACK);
        mRtLineHeight = (int) typedArray.getDimension(R.styleable.EatView_rightLineHeight, TranslateDimensionUtils.dip2px(mContext,4));
        mLtLineColor = typedArray.getColor(R.styleable.EatView_leftLineColor, Color.BLACK);
        mLtLineWidth = (int) typedArray.getDimension(R.styleable.EatView_leftLineHeight, TranslateDimensionUtils.dip2px(mContext,4));
        mLtLineGap = (int) typedArray.getDimension(R.styleable.EatView_leftLineGap, TranslateDimensionUtils.dip2px(mContext,4));
        mProgressTxtColor = typedArray.getColor(R.styleable.EatView_progressTxtColor, Color.BLACK);

        mProgressTxtSize = (int) typedArray.getDimension(R.styleable.EatView_progressTxtSize, TranslateDimensionUtils.sp2px(mContext,12));
        mFinishTxtSize = (int) typedArray.getDimension(R.styleable.EatView_finishTxtSize, TranslateDimensionUtils.sp2px(mContext,20));
        mFinishTxtColor = typedArray.getColor(R.styleable.EatView_finishTxtColor, Color.BLACK);
        mEyeColor = typedArray.getColor(R.styleable.EatView_eyeColor, Color.RED);

        mEatTime = typedArray.getInteger(R.styleable.EatView_eatTime, 10000);
        mLoadFinishTime = typedArray.getInteger(R.styleable.EatView_loadFinishTime, 3000);
        mFinishTxt = typedArray.getString(R.styleable.EatView_finishTxt);
        if (mFinishTxt == null) {
            mFinishTxt = "load finished";
        }
    }

    private void initPaint() {
        //弧线
        mArcPaint = new Paint();
        mArcPaint.setColor(mEatColor);
        mArcPaint.setStyle(Paint.Style.FILL);
        mArcPaint.setStrokeWidth(4);
        mArcPaint.setAntiAlias(true);

        //文本
        mTxtPaint = new Paint();
        mTxtPaint.setStyle(Paint.Style.STROKE);
        mTxtPaint.setAntiAlias(true);

        //右侧线条
        mRightLinePaint = new Paint();
        mRightLinePaint.setColor(mRtLineColor);
        mRightLinePaint.setStyle(Paint.Style.STROKE);
        mRightLinePaint.setStrokeWidth(mRtLineHeight);
        mRightLinePaint.setAntiAlias(true);

        //左侧线条
        mLeftLinePaint = new Paint();
        mLeftLinePaint.setColor(mLtLineColor);
        mLeftLinePaint.setStyle(Paint.Style.STROKE);
        mLeftLinePaint.setStrokeWidth(mLtLineWidth);
        mLeftLinePaint.setAntiAlias(true);
        PathEffect effect = new DashPathEffect(new float[]{mLtLineGap, mLtLineGap, mLtLineGap, mLtLineGap}, 1);
        mLeftLinePaint.setPathEffect(effect);

        //覆盖
        mCoverPaint = new Paint();
        mCoverPaint.setColor(Color.WHITE);
        mCoverPaint.setStyle(Paint.Style.FILL);
        mCoverPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {//wrap_content的处理
            width = 400;
        } else {
            width = MeasureSpec.getSize(widthMeasureSpec);
        }
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {//wrap_content的处理
            height = 100;
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec);
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(mEatRadius, height / 2);//将画布移动到左侧中间
        //1、根据progress计算圆心的位置
        float x = currentProgress * (width - 3 * mEatRadius / 2) / 100;

        //2、计算两条线的夹角
        int lineState = currentProgress % mOnceProgress;
        //lineState有两个类型的值，边界值和一般值 上边界（下边界）和中间值
        if (lineState != 0) {//表示中间值
            mCurrentMouthDegree = lineState * mMouthDegree * 2 / (mOnceProgress * 2);
        } else if (currentProgress % (mOnceProgress * 2) == 0) {//表示上边界
            mCurrentMouthDegree = mMouthDegree;
        } else {//下边界
            mCurrentMouthDegree = 0;
        }

        //3、根据圆心和夹角画圆弧
        mArcPaint.setColor(mEatColor);
        RectF mArcRect = new RectF(x - mEatRadius, (float) -mEatRadius, x + mEatRadius, (float) mEatRadius);
        canvas.drawArc(mArcRect, mCurrentMouthDegree / 2, 360 - mCurrentMouthDegree, true, mArcPaint);

        //5、画眼睛
        float eyeDegree = -(45 + mMouthDegree / 4);
        float eyeToCenter = 2 * mEatRadius / 3;
        mArcPaint.setColor(mEyeColor);
        canvas.drawCircle((float) (eyeToCenter * Math.cos(Math.toRadians(eyeDegree)) + x),
                (float) (eyeToCenter * Math.sin(Math.toRadians(eyeDegree))), mEyeRadius, mArcPaint);

        //6、画文本
        String txt = currentProgress + "%";
        Rect txtRect = new Rect();
        mTxtPaint.setColor(mProgressTxtColor);
        mTxtPaint.setTextSize(mProgressTxtSize);
        mTxtPaint.getTextBounds(txt, 0, txt.length(), txtRect);
        canvas.drawText(txt, -txtRect.width() / 2 + x, -mEatRadius - 8, mTxtPaint);

        //7.1、右侧线条
        canvas.drawLine(x + mEatRadius / 2, 0, width - 3 * mEatRadius / 2, 0, mRightLinePaint);
        //7.2、左侧虚线线条
        Path path = new Path();
        path.moveTo(-mEatRadius, 0);
        path.lineTo(x - mEatRadius, 0);
        canvas.drawPath(path, mLeftLinePaint);

        if (currentProgress == (100 - (mOnceProgress * 2))) {
            //加载完成之后要进行怎样的操作
            canvas.restore();
            canvas.drawRect(0, 0, width, height, mCoverPaint);

            //显示加载完成
            mTxtPaint.setColor(mFinishTxtColor);
            mTxtPaint.setTextSize(mFinishTxtSize);
            Rect rect = new Rect();
            mTxtPaint.getTextBounds(mFinishTxt, 0, mFinishTxt.length(), rect);
            canvas.drawText(mCurrentFinishTxt, (width - rect.width()) / 2, (height + rect.height()) / 2, mTxtPaint);
        }
    }

    private void doAnim() {
        ObjectAnimator progressAnimator = ObjectAnimator.ofFloat(this, "progress", 0, (100 - (mOnceProgress * 2)));
        progressAnimator.setDuration(mEatTime);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();

        finishAnimator = ObjectAnimator.ofInt(this, "finishTxt", 0, mFinishTxt.length());
        finishAnimator.setDuration(mLoadFinishTime);
        finishAnimator.setInterpolator(new LinearInterpolator());
        finishAnimator.setStartDelay(mEatTime);
        finishAnimator.start();
    }

    private void setProgress(float progress) {
        currentProgress = (int) progress;
        postInvalidate();
    }

    private void setFinishTxt(int index) {
        mCurrentFinishTxt = mFinishTxt.substring(0, index);
        postInvalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            doAnim();
        }
        return true;
    }
}
