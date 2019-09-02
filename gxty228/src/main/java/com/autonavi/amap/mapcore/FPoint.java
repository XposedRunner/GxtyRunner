package com.autonavi.amap.mapcore;

import android.graphics.PointF;
import com.autonavi.ae.gmap.maploader.Pools.SynchronizedPool;

public class FPoint extends PointF {
    private static final SynchronizedPool<FPoint> M_POOL = new SynchronizedPool(32);

    public static FPoint obtain() {
        FPoint fPoint = (FPoint) M_POOL.acquire();
        if (fPoint == null) {
            return new FPoint();
        }
        fPoint.set(0.0f, 0.0f);
        return fPoint;
    }

    public static FPoint obtain(float f, float f2) {
        FPoint fPoint = (FPoint) M_POOL.acquire();
        if (fPoint == null) {
            return new FPoint(f, f2);
        }
        fPoint.set(f, f2);
        return fPoint;
    }

    public void recycle() {
        M_POOL.release(this);
    }

    public FPoint(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public boolean equals(Object obj) {
        FPoint fPoint = (FPoint) obj;
        if (fPoint != null && this.x == fPoint.x && this.y == fPoint.y) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int floatToIntBits = Float.floatToIntBits(this.x) * 37;
        return Float.floatToIntBits(this.y) * 37;
    }
}
