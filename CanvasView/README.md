涉及：画圆、画矩形
     canvas的平移、错切
     属性动画

效果显示
**让你晕眩的圆**

![这里写图片描述](http://img.blog.csdn.net/20170227144647204?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**"呼啦圈"**

![这里写图片描述](http://img.blog.csdn.net/20170227144728048?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**"小风车"**
![这里写图片描述](http://img.blog.csdn.net/20170227144839628?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjM5MTg3Ng==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

鼠标点击View 开启属性动画，再次点击时关闭动画

##1、晕眩圆的实现 CircleCircleView
>主要涉及，canvas的translate和scale操作
>设计，将画布的圆心先translate到控件的中心位置，画一个大圆；
将画布缩小到原来的0.86，画圆（这个圆，同上面的圆参数一样的），形成小圆，以此类推

主要操作：
```
canvas.translate(widh/2,height/2);//将画布移到中间
canvas.drawCircle(0, 0, widh / 2, mPaint);

for(int i=0;i<30;i++){
    canvas.scale(0.86f,0.86f);
    int radius = widh / 2;
    canvas.drawCircle(0, 0, radius, mPaint);
}
```

##2、"呼啦圈"的实现 CircleTwo
>主要涉及:canvas的translate和rotate操作
设计：将画布的圆心translate到控件的中心位置，画一个一小的两个圆，
两个圆之间的间隔，通过直线画出来，画线开始的位置（width/2,0） 结束位置(width/2 +间隔,0)
上面已经能够画得一条线，接下来，使用rotate旋转画布，继续画上面的线（位置一样，画布变化）
每rotate一次，画一次。

主要操作：
```
canvas.translate(width / 2, height / 2);
int radius = width / 2;
canvas.drawCircle(0, 0, radius, mPaint);
//大小圆 相隔14px
canvas.drawCircle(0, 0, radius - 14, mPaint);

for (int i = 0; i < 360; i += 10) {
    //每隔10度切一下
    canvas.drawLine(0, radius - 14, 0, radius, mPaint);
    canvas.rotate(10);
}
```

##3、"小风车"的实现 SkewView
>主要涉及canvas的translate 和 skew、以及save和restore操作
设计：将画布移动中间位置，保存一下画布的状态，skew(1,0)将画布在x方向倾斜45度 tan45=1,画两个对角的正方形（此时已经有了倾斜）
恢复画布的状态，逆时针旋转画布（90度），依照上面的 skew,画两个对角的正方形（参数 位置同上面一致，画布有了变化）

>动画的设计：动画效果是获取控件的id ，对控件整体设置的属性动画，点击控件，控件开始进行旋转，再次点击则关闭动画，再点又开始动画，以此类推。

画小风车的主要代码：
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

属性动画实现的主要代码：
//让风车动起来
skewView = (SkewView) findViewById(R.id.skewView);
rotation = ObjectAnimator.ofFloat(skewView, "rotation", 0, 360);
rotation.setDuration(2000);
rotation.setRepeatCount(ValueAnimator.INFINITE);//设置动画一直进行
rotation.setRepeatMode(ValueAnimator.RESTART);//设置动画repeat时的模式
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
可以将动画效果设置到自定义控件中

