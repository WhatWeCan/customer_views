###Ч��

![����дͼƬ����](http://img.blog.csdn.net/20170227143651971?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

>��Ҫ�漰����Բ����������

###1����Բ����

```
RectF rect = new RectF(0,0,300,300);
canvas.drawArc(RectF rect,float startAngle,float swipAngle,boolean userCenter,Paint paint);
```
��¼ÿһ������ʱ�ĽǶȣ���һ�ο�ʼ�ĽǶȼ�Ϊ�ϴν���ʱ�ĽǶȡ�

��Ҫ���룺
```
//����ÿһ��pie�ĽǶ�
float startAngle = 0;//��ʼ�ĽǶ�
for (PieData data : pieData) {
    //��ÿһ���ֵ�Բ��������
    mPaint.setColor(data.getPieColor());//ÿһ�����ɫ
    float swipeAngle = data.getPieProportion() * 360;//ÿһ��ռ���ٽǶ�
    canvas.drawArc(bigRect, startAngle, swipeAngle, true, mPaint);
   ...
}
```

###2�������֣�
�����ı���λ��
СԲ�뾶 ��Ϊ��Բ�뾶��2/3

![����дͼƬ����](http://img.blog.csdn.net/20170227143840818?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

��Ҫ���룺
```
//���ı�
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

