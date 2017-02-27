###效果

![这里写图片描述](http://img.blog.csdn.net/20170227143651971?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

>主要涉及：画圆弧、画文字

###1、画圆弧：

```
RectF rect = new RectF(0,0,300,300);
canvas.drawArc(RectF rect,float startAngle,float swipAngle,boolean userCenter,Paint paint);
```
记录每一个结束时的角度，下一次开始的角度即为上次结束时的角度。

主要代码：
```
//计算每一个pie的角度
float startAngle = 0;//开始的角度
for (PieData data : pieData) {
    //将每一部分的圆弧画出来
    mPaint.setColor(data.getPieColor());//每一块的颜色
    float swipeAngle = data.getPieProportion() * 360;//每一块占多少角度
    canvas.drawArc(bigRect, startAngle, swipeAngle, true, mPaint);
   ...
}
```

###2、画文字：
计算文本的位置
小圆半径 设为大圆半径的2/3

![这里写图片描述](http://img.blog.csdn.net/20170227143840818?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

主要代码：
```
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
```

