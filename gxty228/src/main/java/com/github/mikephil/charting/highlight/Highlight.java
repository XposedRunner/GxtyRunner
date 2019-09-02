package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.components.YAxis.AxisDependency;

public class Highlight {
    private AxisDependency axis;
    private int mDataIndex;
    private int mDataSetIndex;
    private float mDrawX;
    private float mDrawY;
    private int mStackIndex;
    private float mX;
    private float mXPx;
    private float mY;
    private float mYPx;

    public Highlight(float f, float f2, int i) {
        this.mX = Float.NaN;
        this.mY = Float.NaN;
        this.mDataIndex = -1;
        this.mStackIndex = -1;
        this.mX = f;
        this.mY = f2;
        this.mDataSetIndex = i;
    }

    public Highlight(float f, int i, int i2) {
        this(f, Float.NaN, i);
        this.mStackIndex = i2;
    }

    public Highlight(float f, float f2, float f3, float f4, int i, AxisDependency axisDependency) {
        this.mX = Float.NaN;
        this.mY = Float.NaN;
        this.mDataIndex = -1;
        this.mStackIndex = -1;
        this.mX = f;
        this.mY = f2;
        this.mXPx = f3;
        this.mYPx = f4;
        this.mDataSetIndex = i;
        this.axis = axisDependency;
    }

    public Highlight(float f, float f2, float f3, float f4, int i, int i2, AxisDependency axisDependency) {
        this(f, f2, f3, f4, i, axisDependency);
        this.mStackIndex = i2;
    }

    public float getX() {
        return this.mX;
    }

    public float getY() {
        return this.mY;
    }

    public float getXPx() {
        return this.mXPx;
    }

    public float getYPx() {
        return this.mYPx;
    }

    public int getDataIndex() {
        return this.mDataIndex;
    }

    public void setDataIndex(int i) {
        this.mDataIndex = i;
    }

    public int getDataSetIndex() {
        return this.mDataSetIndex;
    }

    public int getStackIndex() {
        return this.mStackIndex;
    }

    public boolean isStacked() {
        return this.mStackIndex >= 0;
    }

    public AxisDependency getAxis() {
        return this.axis;
    }

    public void setDraw(float f, float f2) {
        this.mDrawX = f;
        this.mDrawY = f2;
    }

    public float getDrawX() {
        return this.mDrawX;
    }

    public float getDrawY() {
        return this.mDrawY;
    }

    public boolean equalTo(Highlight highlight) {
        if (highlight != null && this.mDataSetIndex == highlight.mDataSetIndex && this.mX == highlight.mX && this.mStackIndex == highlight.mStackIndex && this.mDataIndex == highlight.mDataIndex) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Highlight, x: " + this.mX + ", y: " + this.mY + ", dataSetIndex: " + this.mDataSetIndex + ", stackIndex (only stacked barentry): " + this.mStackIndex;
    }
}
