长图：

![这里写图片描述](http://img.blog.csdn.net/20170227150040334?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**效果**：

![这里写图片描述](http://img.blog.csdn.net/20170227150206380?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

>主要涉及：canvas.drawBitmap(Bitmap bitmap,Rect src,Rect dsc,Paint paint);
设计：使用drawBitmap 进行区域作画，每一次画长图的一个item到界面上。
设计点击事件，点击一次进行图片的更新，显示下一张图片，如果到最后一张，则重新开始显示
计算得，图片的宽高，将控件的宽高设置为小图片的宽高。

主要代码：
```
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
```