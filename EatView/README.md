���Զ���ؼ���eat loading �Զ���ؼ�ʵ��
Ч����

![����дͼƬ����](http://img.blog.csdn.net/20170227170717940?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

ʵ�ֲ��裺

![����дͼƬ����](http://img.blog.csdn.net/20170227170735928?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![����дͼƬ����](http://img.blog.csdn.net/20170227170750351?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


�����Ӧ��
```
canvas.translate(mEatRadius, height / 2);//�������ƶ�������м�
//1������progress����Բ�ĵ�λ��
float x = currentProgress * (width - 3 * mEatRadius / 2) / 100;

//2�����������ߵļн�
int lineState = currentProgress % mOnceProgress;
//lineState���������͵�ֵ���߽�ֵ��һ��ֵ �ϱ߽磨�±߽磩���м�ֵ
if (lineState != 0) {//��ʾ�м�ֵ
    mCurrentMouthDegree = lineState * mMouthDegree * 2 / (mOnceProgress * 2);
} else if (currentProgress % (mOnceProgress * 2) == 0) {//��ʾ�ϱ߽�
    mCurrentMouthDegree = mMouthDegree;
} else {//�±߽�
    mCurrentMouthDegree = 0;
}

//3������Բ�ĺͼнǻ�Բ��
mArcPaint.setColor(mEatColor);
RectF mArcRect = new RectF(x - mEatRadius, (float) -mEatRadius, x + mEatRadius, (float) mEatRadius);
canvas.drawArc(mArcRect, mCurrentMouthDegree / 2, 360 - mCurrentMouthDegree, true, mArcPaint);

//5�����۾�
float eyeDegree = -(45 + mMouthDegree / 4);
float eyeToCenter = 2 * mEatRadius / 3;
mArcPaint.setColor(mEyeColor);
canvas.drawCircle((float) (eyeToCenter * Math.cos(Math.toRadians(eyeDegree)) + x),
        (float) (eyeToCenter * Math.sin(Math.toRadians(eyeDegree))), mEyeRadius, mArcPaint);

//6�����ı�
String txt = currentProgress + "%";
Rect txtRect = new Rect();
mTxtPaint.setColor(mProgressTxtColor);
mTxtPaint.setTextSize(mProgressTxtSize);
mTxtPaint.getTextBounds(txt, 0, txt.length(), txtRect);
canvas.drawText(txt, -txtRect.width() / 2 + x, -mEatRadius - 8, mTxtPaint);

//7.1���Ҳ�����
canvas.drawLine(x + mEatRadius / 2, 0, width - 3 * mEatRadius / 2, 0, mRightLinePaint);
//7.2�������������
Path path = new Path();
path.moveTo(-mEatRadius, 0);
path.lineTo(x - mEatRadius, 0);
canvas.drawPath(path, mLeftLinePaint);
```


�Զ��������б�
<table>
    <tr>
        <td>eatRadius</td>
        <td>Բ�뾶</td>
    </tr>
	 <tr>
        <td>mouthDegree</td>
        <td>�нǶ���</td>
    </tr> <tr>
        <td>onceProgress</td>
        <td>һ�ڶ��ٸ�����</td>
    </tr> <tr>
        <td>eatColor</td>
        <td>Բ������ɫ</td>
    </tr> <tr>
        <td>rightLineColor</td>
        <td>�Ҳ���������ɫ</td>
    </tr> <tr>
        <td>rightLineHeight</td>
        <td>�Ҳ������ĸ߶�</td>
    </tr> <tr>
        <td>leftLineColor</td>
        <td>�����������ɫ</td>
    </tr> <tr>
        <td>leftLineHeight</td>
        <td>��������ĸ߶�</td>
    </tr> <tr>
        <td>leftLineGap</td>
        <td>����������ߵļ��</td>
    </tr> <tr>
        <td>progressTxtColor</td>
        <td>�����ı�����ɫ</td>
    </tr> <tr>
        <td>progressTxtSize</td>
        <td>�����ı������С</td>
    </tr><tr>
        <td>finishTxtSize</td>
        <td>�����ı������С</td>
    </tr>
<tr>
        <td>finishTxtColor</td>
        <td>�����ı�������ɫ</td>
    </tr><tr>
        <td>eyeColor</td>
        <td>�۾���ɫ</td>
    </tr><tr>
        <td>eatTime</td>
        <td>�Ե�ʱ��</td>
    </tr><tr>
        <td>finishTxt</td>
        <td>����ʱ���ı�</td>
    </tr><tr>
        <td>loadFinishTime</td>
        <td>����ʱ��ʾ�ı���ʱ��</td>
    </tr></table>
