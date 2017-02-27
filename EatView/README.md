【自定义控件】eat loading 自定义控件实现
效果：

![这里写图片描述](http://img.blog.csdn.net/20170227170717940?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实现步骤：

![这里写图片描述](http://img.blog.csdn.net/20170227170735928?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170227170750351?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


代码对应：
```
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
```


自定义属性列表：
<table>
    <tr>
        <td>eatRadius</td>
        <td>圆半径</td>
    </tr>
	 <tr>
        <td>mouthDegree</td>
        <td>夹角度数</td>
    </tr> <tr>
        <td>onceProgress</td>
        <td>一口多少个进度</td>
    </tr> <tr>
        <td>eatColor</td>
        <td>圆弧的颜色</td>
    </tr> <tr>
        <td>rightLineColor</td>
        <td>右侧线条的颜色</td>
    </tr> <tr>
        <td>rightLineHeight</td>
        <td>右侧线条的高度</td>
    </tr> <tr>
        <td>leftLineColor</td>
        <td>左侧线条的颜色</td>
    </tr> <tr>
        <td>leftLineHeight</td>
        <td>左侧线条的高度</td>
    </tr> <tr>
        <td>leftLineGap</td>
        <td>左侧线条虚线的间隔</td>
    </tr> <tr>
        <td>progressTxtColor</td>
        <td>进度文本的颜色</td>
    </tr> <tr>
        <td>progressTxtSize</td>
        <td>进度文本字体大小</td>
    </tr><tr>
        <td>finishTxtSize</td>
        <td>结束文本字体大小</td>
    </tr>
<tr>
        <td>finishTxtColor</td>
        <td>结束文本字体颜色</td>
    </tr><tr>
        <td>eyeColor</td>
        <td>眼睛颜色</td>
    </tr><tr>
        <td>eatTime</td>
        <td>吃的时间</td>
    </tr><tr>
        <td>finishTxt</td>
        <td>结束时的文本</td>
    </tr><tr>
        <td>loadFinishTime</td>
        <td>结束时显示文本的时间</td>
    </tr></table>
