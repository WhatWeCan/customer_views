Ч��

![����дͼƬ����](http://img.blog.csdn.net/20170227121433709?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


>��Ҫ�漰�����㡢�ߡ��ı�
>��ƣ��Ȼ��ı����ؼ����м�λ�ã����Ż�����㣬�������ߣ�
>����¼������ã����һ�Σ�������ͼ�����������

###1�� ��Txt�ı�
   
```
     canvas.drawText(String text,float x,float y,Paint paint);
```
     �ı�λ��(x,y)���㣺
     
![����дͼƬ����](http://img.blog.csdn.net/20170227121726309?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
     
  **�ؼ���ߵĻ�ȡ**�� 
     ������onMeasure�����л�ȡ�ؼ��Ŀ�ߣ�����ڳ�ʼ��ʱ��ֱ�ӻ�ȡ��� ���ܻᵼ�»�ȡ��ֵΪ0
     **�����ߵĻ�ȡ**��
     ʹ��paint.getTextBounds(String text,int start,int end,Rect bounds)��
     ����ߴ洢��bounds�У�ͨ��bounds����ȡ���

��Ҫ���룺
```
//��txt
mVerificationTxt = getRandomCode();
float x = (width - mTxtWidth) / 2;
float y = (height + mTxtHeight) / 2;
canvas.drawText(mVerificationTxt, x, y, mPaint);

private String getRandomCode() {
    String randomCode = "";
    Random random = new Random();
    for (int i = 0; i < 4; i++) {
        randomCode += random.nextInt(10);//���ɰ���0 ������10�������
    }
    return randomCode;
}
```

###2�������ŵ�

   `canvas.drawPoint(float x,float y,Paint paint);`
    
    �㣬��ô�����
    ʹ��Random ��ȡ��߷�Χ�ڵ�x��yֵ

��Ҫ���룺
```
//�����ŵ�
for (PointF point : pointFs) {
    canvas.drawPoint(point.x, point.y, mPointPaint);
}

/**
 * ���ŵ�
 */
private void setPoint() {
    pointFs = new ArrayList<>();
    for (int i = 0; i < mDisPointCount; i++) {
        pointFs.add(new PointF(new Random().nextInt(width) + 6,
                new Random().nextInt(height) + 6));
    }
}
```

###3����������

```
    canvas.drawLine(float startX,float startY,float endX,float endY,Paint paint);
```
     �ߣ���ô�����
     ʹ��Random ������������㣬��������Ϊ�ߡ�

��Ҫ���룺
```
for (int i = 0; i < pointLines.size() - 1; i++) {
    PointF pointStart = pointLines.get(i);
    PointF pointEnd = pointLines.get(++i);
    canvas.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y, mLinePaint);
}

/**
 * ������
 */
private void setLines() {
    pointLines = new ArrayList<>();
    //��������8���� �����˸����������
    for (int i = 0; i < mDisLineCount * 2; i++) {
        //���������
        int x = new Random().nextInt(width);
        int y = new Random().nextInt(height);
        PointF pointF = new PointF(x, y);
        pointLines.add(pointF);
    }
}
```

###4���Զ�������
ʹ�þ�̬���ݣ������幦�����֮�󣬶��ⲿ�־�̬���ݽ������Ե���ȡ����ȡΪ�Զ������ԡ�
**�Զ�������**���λ�ã�res/values/styles
�Զ������Ը�ʽ��
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

������Զ���ؼ��л�ȡ�Զ������ԣ�
```
TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
        R.styleable.YZMView, defStyleAttr, 0);
//��ȡ�Զ�������
mTxtColor = typedArray.getColor(R.styleable.YZMView_txtColor, mDefaultColor);
mTxtSize = typedArray.getDimension(R.styleable.YZMView_txtSize, mDefaultTxtSize);
mTxtSize = TranslateDimensionUtils.sp2px(context, mTxtSize);
mDisPointSize = typedArray.getDimension(R.styleable.YZMView_disturbPointSize, mDefaultDisPointSize);

```

ע�⣺�����ȡ�������dimission��λ����px,���Ҫ���пؼ���setSize�Ȳ�������Ҫ��px������Ӧ��ת����