package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.highlight.Range;

@SuppressLint({"ParcelCreator"})
public class BarEntry extends Entry {
    private float mNegativeSum;
    private float mPositiveSum;
    private Range[] mRanges;
    private float[] mYVals;

    public BarEntry(float f, float f2) {
        super(f, f2);
    }

    public BarEntry(float f, float f2, Object obj) {
        super(f, f2, obj);
    }

    public BarEntry(float f, float f2, Drawable drawable) {
        super(f, f2, drawable);
    }

    public BarEntry(float f, float f2, Drawable drawable, Object obj) {
        super(f, f2, drawable, obj);
    }

    public BarEntry(float f, float[] fArr) {
        super(f, calcSum(fArr));
        this.mYVals = fArr;
        calcPosNegSum();
        calcRanges();
    }

    public BarEntry(float f, float[] fArr, Object obj) {
        super(f, calcSum(fArr), obj);
        this.mYVals = fArr;
        calcPosNegSum();
        calcRanges();
    }

    public BarEntry(float f, float[] fArr, Drawable drawable) {
        super(f, calcSum(fArr), drawable);
        this.mYVals = fArr;
        calcPosNegSum();
        calcRanges();
    }

    public BarEntry(float f, float[] fArr, Drawable drawable, Object obj) {
        super(f, calcSum(fArr), drawable, obj);
        this.mYVals = fArr;
        calcPosNegSum();
        calcRanges();
    }

    public BarEntry copy() {
        BarEntry barEntry = new BarEntry(getX(), getY(), getData());
        barEntry.setVals(this.mYVals);
        return barEntry;
    }

    public float[] getYVals() {
        return this.mYVals;
    }

    public void setVals(float[] fArr) {
        setY(calcSum(fArr));
        this.mYVals = fArr;
        calcPosNegSum();
        calcRanges();
    }

    public float getY() {
        return super.getY();
    }

    public Range[] getRanges() {
        return this.mRanges;
    }

    public boolean isStacked() {
        return this.mYVals != null;
    }

    @Deprecated
    public float getBelowSum(int i) {
        return getSumBelow(i);
    }

    public float getSumBelow(int i) {
        float f = 0.0f;
        if (this.mYVals != null) {
            int length = this.mYVals.length - 1;
            while (length > i && length >= 0) {
                float f2 = this.mYVals[length] + f;
                length--;
                f = f2;
            }
        }
        return f;
    }

    public float getPositiveSum() {
        return this.mPositiveSum;
    }

    public float getNegativeSum() {
        return this.mNegativeSum;
    }

    private void calcPosNegSum() {
        if (this.mYVals == null) {
            this.mNegativeSum = 0.0f;
            this.mPositiveSum = 0.0f;
            return;
        }
        float f = 0.0f;
        float f2 = 0.0f;
        for (float f3 : this.mYVals) {
            if (f3 <= 0.0f) {
                f += Math.abs(f3);
            } else {
                f2 += f3;
            }
        }
        this.mNegativeSum = f;
        this.mPositiveSum = f2;
    }

    private static float calcSum(float[] fArr) {
        float f = 0.0f;
        if (fArr != null) {
            int i = 0;
            while (i < fArr.length) {
                float f2 = fArr[i] + f;
                i++;
                f = f2;
            }
        }
        return f;
    }

    protected void calcRanges() {
        float[] yVals = getYVals();
        if (yVals != null && yVals.length != 0) {
            this.mRanges = new Range[yVals.length];
            float f = -getNegativeSum();
            float f2 = 0.0f;
            for (int i = 0; i < this.mRanges.length; i++) {
                float f3 = yVals[i];
                if (f3 < 0.0f) {
                    this.mRanges[i] = new Range(f, f - f3);
                    f -= f3;
                } else {
                    this.mRanges[i] = new Range(f2, f2 + f3);
                    f2 += f3;
                }
            }
        }
    }
}
