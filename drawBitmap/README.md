��ͼ��

![����дͼƬ����](http://img.blog.csdn.net/20170227150040334?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**Ч��**��

![����дͼƬ����](http://img.blog.csdn.net/20170227150206380?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

>��Ҫ�漰��canvas.drawBitmap(Bitmap bitmap,Rect src,Rect dsc,Paint paint);
��ƣ�ʹ��drawBitmap ��������������ÿһ�λ���ͼ��һ��item�������ϡ�
��Ƶ���¼������һ�ν���ͼƬ�ĸ��£���ʾ��һ��ͼƬ����������һ�ţ������¿�ʼ��ʾ
����ã�ͼƬ�Ŀ�ߣ����ؼ��Ŀ������ΪСͼƬ�Ŀ�ߡ�

��Ҫ���룺
```
@Override
protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int left = currentPic * width;
    int top = 0;
    int right = (currentPic + 1) * width;
    int bottom = width;
    //ͼ��ѡȡ
    Rect src = new Rect(left, top, right, bottom);
    RectF dsc = new RectF(0, 0, width, height);

    canvas.drawBitmap(bitmap, src, dsc, null);
}

@Override
protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    //��ȡͼƬ�Ĵ�С
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
        //�������¼�
        if (currentPic == totalPic - 1) {
            currentPic = -1;
        }
        currentPic++;
        postInvalidate();
    }
    return true;
}
```