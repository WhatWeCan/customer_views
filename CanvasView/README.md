�漰����Բ��������
     canvas��ƽ�ơ�����
     ���Զ���

Ч����ʾ
**������ѣ��Բ**

![����дͼƬ����](http://img.blog.csdn.net/20170227144647204?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**"����Ȧ"**

![����дͼƬ����](http://img.blog.csdn.net/20170227144728048?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**"С�糵"**
![����дͼƬ����](http://img.blog.csdn.net/20170227144839628?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

�����View �������Զ������ٴε��ʱ�رն���

##1����ѣԲ��ʵ�� CircleCircleView
>��Ҫ�漰��canvas��translate��scale����
>��ƣ���������Բ����translate���ؼ�������λ�ã���һ����Բ��
��������С��ԭ����0.86����Բ�����Բ��ͬ�����Բ����һ���ģ����γ�СԲ���Դ�����

��Ҫ������
```
canvas.translate(widh/2,height/2);//�������Ƶ��м�
canvas.drawCircle(0, 0, widh / 2, mPaint);

for(int i=0;i<30;i++){
    canvas.scale(0.86f,0.86f);
    int radius = widh / 2;
    canvas.drawCircle(0, 0, radius, mPaint);
}
```

##2��"����Ȧ"��ʵ�� CircleTwo
>��Ҫ�漰:canvas��translate��rotate����
��ƣ���������Բ��translate���ؼ�������λ�ã���һ��һС������Բ��
����Բ֮��ļ����ͨ��ֱ�߻����������߿�ʼ��λ�ã�width/2,0�� ����λ��(width/2 +���,0)
�����Ѿ��ܹ�����һ���ߣ���������ʹ��rotate��ת������������������ߣ�λ��һ���������仯��
ÿrotateһ�Σ���һ�Ρ�

��Ҫ������
```
canvas.translate(width / 2, height / 2);
int radius = width / 2;
canvas.drawCircle(0, 0, radius, mPaint);
//��СԲ ���14px
canvas.drawCircle(0, 0, radius - 14, mPaint);

for (int i = 0; i < 360; i += 10) {
    //ÿ��10����һ��
    canvas.drawLine(0, radius - 14, 0, radius, mPaint);
    canvas.rotate(10);
}
```

##3��"С�糵"��ʵ�� SkewView
>��Ҫ�漰canvas��translate �� skew���Լ�save��restore����
��ƣ��������ƶ��м�λ�ã�����һ�»�����״̬��skew(1,0)��������x������б45�� tan45=1,�������Խǵ������Σ���ʱ�Ѿ�������б��
�ָ�������״̬����ʱ����ת������90�ȣ������������ skew,�������Խǵ������Σ����� λ��ͬ����һ�£��������˱仯��

>��������ƣ�����Ч���ǻ�ȡ�ؼ���id ���Կؼ��������õ����Զ���������ؼ����ؼ���ʼ������ת���ٴε����رն������ٵ��ֿ�ʼ�������Դ����ơ�

��С�糵����Ҫ���룺
```
canvas.translate(width / 2, height / 2);
canvas.save();

canvas.skew(1, 0);
canvas.drawRect(0, 0, width / 5, height / 5, mPaint);
canvas.drawRect(-width / 5, -height / 5, 0, 0, mPaint);
canvas.restore();

canvas.rotate(90);
canvas.skew(1, 0);
canvas.drawRect(0, 0, width / 5, height / 5, mPaint);
canvas.drawRect(-width / 5, -height / 5, 0, 0, mPaint);

���Զ���ʵ�ֵ���Ҫ���룺
//�÷糵������
skewView = (SkewView) findViewById(R.id.skewView);
rotation = ObjectAnimator.ofFloat(skewView, "rotation", 0, 360);
rotation.setDuration(2000);
rotation.setRepeatCount(ValueAnimator.INFINITE);//���ö���һֱ����
rotation.setRepeatMode(ValueAnimator.RESTART);//���ö���repeatʱ��ģʽ
rotation.setInterpolator(new LinearInterpolator());
skewView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (isSkewStart) {
            rotation.cancel();
            isSkewStart = false;
        } else {
            rotation.start();
            isSkewStart = true;
        }
    }
});
```
���Խ�����Ч�����õ��Զ���ؼ���

