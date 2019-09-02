package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.util.Log;

@SuppressLint({"ParcelCreator"})
public class PieEntry extends Entry {
    private String label;

    public PieEntry(float f) {
        super(0.0f, f);
    }

    public PieEntry(float f, Object obj) {
        super(0.0f, f, obj);
    }

    public PieEntry(float f, Drawable drawable) {
        super(0.0f, f, drawable);
    }

    public PieEntry(float f, Drawable drawable, Object obj) {
        super(0.0f, f, drawable, obj);
    }

    public PieEntry(float f, String str) {
        super(0.0f, f);
        this.label = str;
    }

    public PieEntry(float f, String str, Object obj) {
        super(0.0f, f, obj);
        this.label = str;
    }

    public PieEntry(float f, String str, Drawable drawable) {
        super(0.0f, f, drawable);
        this.label = str;
    }

    public PieEntry(float f, String str, Drawable drawable, Object obj) {
        super(0.0f, f, drawable, obj);
        this.label = str;
    }

    public float getValue() {
        return getY();
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    @Deprecated
    public void setX(float f) {
        super.setX(f);
        Log.i("DEPRECATED", "Pie entries do not have x values");
    }

    @Deprecated
    public float getX() {
        Log.i("DEPRECATED", "Pie entries do not have x values");
        return super.getX();
    }

    public PieEntry copy() {
        return new PieEntry(getY(), this.label, getData());
    }
}
