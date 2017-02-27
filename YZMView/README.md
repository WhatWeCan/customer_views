效果

![这里写图片描述](http://img.blog.csdn.net/20170227121433709?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


>主要涉及：画点、线、文本
>设计：先画文本到控件的中间位置，接着画随机点，最后画随机线；
>点击事件的设置，点击一次，重新作图（上面操作）

###1、 画Txt文本
   
```
     canvas.drawText(String text,float x,float y,Paint paint);
```
     文本位置(x,y)计算：
     
![这里写图片描述](http://img.blog.csdn.net/20170227121726309?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
     
  **控件宽高的获取**： 
     可以在onMeasure方法中获取控件的宽高，如果在初始化时，直接获取宽高 可能会导致获取的值为0
     **字体宽高的获取**：
     使用paint.getTextBounds(String text,int start,int end,Rect bounds)，
     将宽高存储到bounds中，通过bounds来获取宽高

主要代码：
```
//画txt
mVerificationTxt = getRandomCode();
float x = (width - mTxtWidth) / 2;
float y = (height + mTxtHeight) / 2;
canvas.drawText(mVerificationTxt, x, y, mPaint);

private String getRandomCode() {
    String randomCode = "";
    Random random = new Random();
    for (int i = 0; i < 4; i++) {
        randomCode += random.nextInt(10);//生成包含0 不包含10的随机数
    }
    return randomCode;
}
```

###2、画干扰点

   `canvas.drawPoint(float x,float y,Paint paint);`
    
    点，怎么随机？
    使用Random 获取宽高范围内的x、y值

主要代码：
```
//画干扰点
for (PointF point : pointFs) {
    canvas.drawPoint(point.x, point.y, mPointPaint);
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
```

###3、画干扰线

```
    canvas.drawLine(float startX,float startY,float endX,float endY,Paint paint);
```
     线，怎么随机？
     使用Random 随机生成两个点，两点连接为线。

主要代码：
```
for (int i = 0; i < pointLines.size() - 1; i++) {
    PointF pointStart = pointLines.get(i);
    PointF pointEnd = pointLines.get(++i);
    canvas.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y, mLinePaint);
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
```

###4、自定义属性
使用静态数据，将大体功能完成之后，对这部分静态数据进行属性的提取，提取为自定义属性。
**自定义属性**存放位置：res/values/styles
自定义属性格式：
```
<declare-styleable name="YZMView">
    <attr name="txtColor" format="color|reference" />
    <attr name="txtSize" format="dimension|reference" />
    <attr name="disturbPointColor" format="color|reference" />
    <attr name="disturbPointSize" format="dimension|reference" />
    <attr name="disturbLineSize" format="dimension|reference" />
    <attr name="disturbPointCount" format="reference|integer" />
    <attr name="disturbLineColor" format="color|reference" />
    <attr name="disturbLineCount" format="integer|reference" />
</declare-styleable>
```

如何在自定义控件中获取自定义属性？
```
TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
        R.styleable.YZMView, defStyleAttr, 0);
//获取自定义属性
mTxtColor = typedArray.getColor(R.styleable.YZMView_txtColor, mDefaultColor);
mTxtSize = typedArray.getDimension(R.styleable.YZMView_txtSize, mDefaultTxtSize);
mTxtSize = TranslateDimensionUtils.sp2px(context, mTxtSize);
mDisPointSize = typedArray.getDimension(R.styleable.YZMView_disturbPointSize, mDefaultDisPointSize);

```

注意：这里获取到的相关dimission单位都是px,如果要进行控件的setSize等操作，需要将px进行相应的转化。