package com.github.mikephil.charting.components;

import android.graphics.Paint.Align;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

public class Description extends ComponentBase {
    private MPPointF mPosition;
    private Align mTextAlign;
    private String text;

    public Description() {
        this.text = "Description Label";
        this.mTextAlign = Align.RIGHT;
        this.mTextSize = Utils.convertDpToPixel(8.0f);
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }

    public void setPosition(float f, float f2) {
        if (this.mPosition == null) {
            this.mPosition = MPPointF.getInstance(f, f2);
            return;
        }
        this.mPosition.x = f;
        this.mPosition.y = f2;
    }

    public MPPointF getPosition() {
        return this.mPosition;
    }

    public void setTextAlign(Align align) {
        this.mTextAlign = align;
    }

    public Align getTextAlign() {
        return this.mTextAlign;
    }
}
