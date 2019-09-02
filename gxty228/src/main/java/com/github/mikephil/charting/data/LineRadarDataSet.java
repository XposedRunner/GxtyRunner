package com.github.mikephil.charting.data;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

public abstract class LineRadarDataSet<T extends Entry> extends LineScatterCandleRadarDataSet<T> implements ILineRadarDataSet<T> {
    private boolean mDrawFilled = false;
    private int mFillAlpha = 85;
    private int mFillColor = Color.rgb(140, 234, 255);
    protected Drawable mFillDrawable;
    private float mLineWidth = 2.5f;

    public LineRadarDataSet(List<T> list, String str) {
        super(list, str);
    }

    public int getFillColor() {
        return this.mFillColor;
    }

    public void setFillColor(int i) {
        this.mFillColor = i;
        this.mFillDrawable = null;
    }

    public Drawable getFillDrawable() {
        return this.mFillDrawable;
    }

    @TargetApi(18)
    public void setFillDrawable(Drawable drawable) {
        this.mFillDrawable = drawable;
    }

    public int getFillAlpha() {
        return this.mFillAlpha;
    }

    public void setFillAlpha(int i) {
        this.mFillAlpha = i;
    }

    public void setLineWidth(float f) {
        float f2 = 10.0f;
        float f3 = 0.0f;
        if (f >= 0.0f) {
            f3 = f;
        }
        if (f3 <= 10.0f) {
            f2 = f3;
        }
        this.mLineWidth = Utils.convertDpToPixel(f2);
    }

    public float getLineWidth() {
        return this.mLineWidth;
    }

    public void setDrawFilled(boolean z) {
        this.mDrawFilled = z;
    }

    public boolean isDrawFilledEnabled() {
        return this.mDrawFilled;
    }
}
