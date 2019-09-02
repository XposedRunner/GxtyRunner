package com.github.mikephil.charting.components;

import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import com.github.mikephil.charting.utils.Utils;

public abstract class ComponentBase {
    protected boolean mEnabled = true;
    protected int mTextColor = ViewCompat.MEASURED_STATE_MASK;
    protected float mTextSize = Utils.convertDpToPixel(10.0f);
    protected Typeface mTypeface = null;
    protected float mXOffset = 5.0f;
    protected float mYOffset = 5.0f;

    public float getXOffset() {
        return this.mXOffset;
    }

    public void setXOffset(float f) {
        this.mXOffset = Utils.convertDpToPixel(f);
    }

    public float getYOffset() {
        return this.mYOffset;
    }

    public void setYOffset(float f) {
        this.mYOffset = Utils.convertDpToPixel(f);
    }

    public Typeface getTypeface() {
        return this.mTypeface;
    }

    public void setTypeface(Typeface typeface) {
        this.mTypeface = typeface;
    }

    public void setTextSize(float f) {
        float f2 = 24.0f;
        float f3 = 6.0f;
        if (f <= 24.0f) {
            f2 = f;
        }
        if (f2 >= 6.0f) {
            f3 = f2;
        }
        this.mTextSize = Utils.convertDpToPixel(f3);
    }

    public float getTextSize() {
        return this.mTextSize;
    }

    public void setTextColor(int i) {
        this.mTextColor = i;
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }
}
