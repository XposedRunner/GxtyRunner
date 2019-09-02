package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import java.util.List;

public final class FSize extends Poolable {
    private static ObjectPool<FSize> pool = ObjectPool.create(256, new FSize(0.0f, 0.0f));
    public float height;
    public float width;

    static {
        pool.setReplenishPercentage(0.5f);
    }

    protected Poolable instantiate() {
        return new FSize(0.0f, 0.0f);
    }

    public static FSize getInstance(float f, float f2) {
        FSize fSize = (FSize) pool.get();
        fSize.width = f;
        fSize.height = f2;
        return fSize;
    }

    public static void recycleInstance(FSize fSize) {
        pool.recycle((Poolable) fSize);
    }

    public static void recycleInstances(List<FSize> list) {
        pool.recycle((List) list);
    }

    public FSize(float f, float f2) {
        this.width = f;
        this.height = f2;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FSize)) {
            return false;
        }
        FSize fSize = (FSize) obj;
        if (!(this.width == fSize.width && this.height == fSize.height)) {
            z = false;
        }
        return z;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.width) ^ Float.floatToIntBits(this.height);
    }
}
