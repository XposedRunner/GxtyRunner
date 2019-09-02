package com.autonavi.amap.mapcore;

import android.graphics.Point;
import com.autonavi.ae.gmap.maploader.Pools.SynchronizedPool;

public class IPoint extends Point implements Cloneable {
    private static final SynchronizedPool<IPoint> M_POOL = new SynchronizedPool(32);

    public static IPoint obtain() {
        IPoint iPoint = (IPoint) M_POOL.acquire();
        if (iPoint == null) {
            return new IPoint();
        }
        iPoint.set(0, 0);
        return iPoint;
    }

    public static IPoint obtain(int i, int i2) {
        IPoint iPoint = (IPoint) M_POOL.acquire();
        if (iPoint == null) {
            return new IPoint(i, i2);
        }
        iPoint.set(i, i2);
        return iPoint;
    }

    public void recycle() {
        M_POOL.release(this);
    }

    public IPoint(int i, int i2) {
        this.x = i;
        this.y = i2;
    }

    public Object clone() {
        try {
            return (IPoint) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
