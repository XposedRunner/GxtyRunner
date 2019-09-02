package com.github.mikephil.charting.highlight;

public final class Range {
    public float from;
    public float to;

    public Range(float f, float f2) {
        this.from = f;
        this.to = f2;
    }

    public boolean contains(float f) {
        if (f <= this.from || f > this.to) {
            return false;
        }
        return true;
    }

    public boolean isLarger(float f) {
        return f > this.to;
    }

    public boolean isSmaller(float f) {
        return f < this.from;
    }
}
