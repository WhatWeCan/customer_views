package com.tjstudy.customerviews.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tjstudy.customerviews.R;

/**
 * 动态图
 * 三张图组成长图，点击一次 更换一张，到最后一张时，重头开始显示--
 * 1、确定控件的大小
 */

public class PicView extends View {

    private Paint mPaint;
    private Context mContext;
    private Bitmap bitmap;
    private int width;
    private int height;
    private int currentPic = 0;//三张图 0 1 2
    private int totalPic = 3;

    public PicView(Context context) {
        this(context, null);
    }

    public PicView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initPic();
        initPaint();
    }

    private void initPic() {
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.pic);
    }

    private void initPaint() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int left = currentPic * width;
        int top = 0;
        int right = (currentPic + 1) * width;
        int bottom = width;
        //图像选取
        Rect src = new Rect(left, top, right, bottom);
        RectF dsc = new RectF(0, 0, width, height);

        canvas.drawBitmap(bitmap, src, dsc, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取图片的大小
        int picHeight = bitmap.getHeight();
        int picWidth = bitmap.getWidth();
        width = picWidth / totalPic;
        height = picHeight;
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            //处理点击事件
            if (currentPic == totalPic - 1) {
                currentPic = -1;
            }
            currentPic++;
            postInvalidate();
        }
        return true;
    }
}
