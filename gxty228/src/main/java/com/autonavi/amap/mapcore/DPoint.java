package com.autonavi.amap.mapcore;

import com.autonavi.ae.gmap.maploader.Pools.SynchronizedPool;
import com.github.mikephil.charting.utils.Utils;

public class DPoint {
    private static final SynchronizedPool<DPoint> M_POOL = new SynchronizedPool(32);
    public double x;
    public double y;

    public static DPoint obtain() {
        DPoint dPoint = (DPoint) M_POOL.acquire();
        if (dPoint == null) {
            return new DPoint();
        }
        dPoint.set(Utils.DOUBLE_EPSILON, Utils.DOUBLE_EPSILON);
        return dPoint;
    }

    public static DPoint obtain(double d, double d2) {
        DPoint dPoint = (DPoint) M_POOL.acquire();
        if (dPoint == null) {
            return new DPoint(d, d2);
        }
        dPoint.set(d, d2);
        return dPoint;
    }

    public void recycle() {
        M_POOL.release(this);
    }

    public DPoint(double d, double d2) {
        this.x = d;
        this.y = d2;
    }

    private void set(double d, double d2) {
        this.x = d;
        this.y = d2;
    }
}
