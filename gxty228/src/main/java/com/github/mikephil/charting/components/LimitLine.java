package com.github.mikephil.charting.components;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint.Style;
import com.github.mikephil.charting.utils.Utils;

public class LimitLine extends ComponentBase {
    private DashPathEffect mDashPathEffect = null;
    private String mLabel = "";
    private LimitLabelPosition mLabelPosition = LimitLabelPosition.RIGHT_TOP;
    private float mLimit = 0.0f;
    private int mLineColor = Color.rgb(237, 91, 91);
    private float mLineWidth = 2.0f;
    private Style mTextStyle = Style.FILL_AND_STROKE;

    public enum LimitLabelPosition {
        LEFT_TOP,
        LEFT_BOTTOM,
        RIGHT_TOP,
        RIGHT_BOTTOM
    }

    public LimitLine(float f) {
        this.mLimit = f;
    }

    public LimitLine(float f, String str) {
        this.mLimit = f;
        this.mLabel = str;
    }

    public float getLimit() {
        return this.mLimit;
    }

    public void setLineWidth(float f) {
        float f2 = 12.0f;
        float f3 = 0.2f;
        if (f >= 0.2f) {
            f3 = f;
        }
        if (f3 <= 12.0f) {
            f2 = f3;
        }
        this.mLineWidth = Utils.convertDpToPixel(f2);
    }

    public float getLineWidth() {
        return this.mLineWidth;
    }

    public void setLineColor(int i) {
        this.mLineColor = i;
    }

    public int getLineColor() {
        return this.mLineColor;
    }

    public void enableDashedLine(float f, float f2, float f3) {
        this.mDashPathEffect = new DashPathEffect(new float[]{f, f2}, f3);
    }

    public void disableDashedLine() {
        this.mDashPathEffect = null;
    }

    public boolean isDashedLineEnabled() {
        return this.mDashPathEffect != null;
    }

    public DashPathEffect getDashPathEffect() {
        return this.mDashPathEffect;
    }

    public void setTextStyle(Style style) {
        this.mTextStyle = style;
    }

    public Style getTextStyle() {
        return this.mTextStyle;
    }

    public void setLabelPosition(LimitLabelPosition limitLabelPosition) {
        this.mLabelPosition = limitLabelPosition;
    }

    public LimitLabelPosition getLabelPosition() {
        return this.mLabelPosition;
    }

    public void setLabel(String str) {
        this.mLabel = str;
    }

    public String getLabel() {
        return this.mLabel;
    }
}
