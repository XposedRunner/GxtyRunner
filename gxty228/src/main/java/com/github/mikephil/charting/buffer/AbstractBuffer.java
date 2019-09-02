package com.github.mikephil.charting.buffer;

public abstract class AbstractBuffer<T> {
    public final float[] buffer;
    protected int index;
    protected int mFrom;
    protected int mTo;
    protected float phaseX;
    protected float phaseY;

    public abstract void feed(T t);

    public AbstractBuffer(int i) {
        this.index = 0;
        this.phaseX = 1.0f;
        this.phaseY = 1.0f;
        this.mFrom = 0;
        this.mTo = 0;
        this.index = 0;
        this.buffer = new float[i];
    }

    public void limitFrom(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mFrom = i;
    }

    public void limitTo(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mTo = i;
    }

    public void reset() {
        this.index = 0;
    }

    public int size() {
        return this.buffer.length;
    }

    public void setPhases(float f, float f2) {
        this.phaseX = f;
        this.phaseY = f2;
    }
}
