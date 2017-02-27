package com.tjstudy.customerviews.bean;

import android.graphics.Color;

/**
 * 饼状图 实体类
 */
public class PieData {
    private String pieName;//名字
    private int pieColor;//颜色
    private float pieProportion;//比例
    private int pieTxtColor;//文字颜色
    private int pieTextSize;//文字的大小
    private int defaultTextColor = Color.WHITE;
    private int defaultTextSize = 16;


    public PieData(String pieName, int pieColor, float pieProportion, int pieTxtColor, int pieTextSize) {
        this.pieName = pieName;
        this.pieColor = pieColor;
        this.pieProportion = pieProportion;
        this.pieTxtColor = pieTxtColor;
        this.pieTextSize = pieTextSize;
    }

    public PieData(int pieTxtColor, float pieProportion, int pieColor, String pieName) {
        this.pieTxtColor = pieTxtColor;
        this.pieProportion = pieProportion;
        this.pieColor = pieColor;
        this.pieName = pieName;
        this.pieTextSize = defaultTextSize;//默认16sp
    }

    public PieData(String pieName, int pieColor, float pieProportion) {
        this.pieName = pieName;
        this.pieColor = pieColor;
        this.pieProportion = pieProportion;
        this.pieTextSize = defaultTextSize;//默认16sp
        this.pieTxtColor = defaultTextColor;
    }

    public String getPieName() {

        return pieName;
    }

    public void setPieName(String pieName) {
        this.pieName = pieName;
    }

    public int getPieColor() {
        return pieColor;
    }

    public void setPieColor(int pieColor) {
        this.pieColor = pieColor;
    }

    public float getPieProportion() {
        return pieProportion;
    }

    public void setPieProportion(float pieProportion) {
        this.pieProportion = pieProportion;
    }

    public int getPieTxtColor() {
        return pieTxtColor;
    }

    public void setPieTxtColor(int pieTxtColor) {
        this.pieTxtColor = pieTxtColor;
    }

    public int getPieTextSize() {
        return pieTextSize;
    }

    public void setPieTextSize(int pieTextSize) {
        this.pieTextSize = pieTextSize;
    }
}