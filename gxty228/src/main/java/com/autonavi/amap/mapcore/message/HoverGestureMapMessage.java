package com.autonavi.amap.mapcore.message;

import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.ae.gmap.maploader.Pools.SynchronizedPool;

public class HoverGestureMapMessage extends AbstractGestureMapMessage {
    private static final SynchronizedPool<HoverGestureMapMessage> M_POOL = new SynchronizedPool(256);
    public float angleDelta = 0.0f;

    public static HoverGestureMapMessage obtain(int i, float f) {
        HoverGestureMapMessage hoverGestureMapMessage = (HoverGestureMapMessage) M_POOL.acquire();
        if (hoverGestureMapMessage == null) {
            hoverGestureMapMessage = new HoverGestureMapMessage(i, f);
        } else {
            hoverGestureMapMessage.reset();
        }
        hoverGestureMapMessage.setParams(i, f);
        return hoverGestureMapMessage;
    }

    public void recycle() {
        M_POOL.release(this);
    }

    public static void destory() {
        M_POOL.destory();
    }

    private void setParams(int i, float f) {
        setState(i);
        this.angleDelta = f;
    }

    public HoverGestureMapMessage(int i, float f) {
        super(i);
        this.angleDelta = f;
    }

    public int getType() {
        return 3;
    }

    public void runCameraUpdate(GLMapState gLMapState) {
        float f = 0.0f;
        float cameraDegree = gLMapState.getCameraDegree() + this.angleDelta;
        if (cameraDegree >= 0.0f) {
            if (cameraDegree > 80.0f) {
                f = 80.0f;
            } else if (gLMapState.getCameraDegree() <= 40.0f || cameraDegree <= 40.0f || gLMapState.getCameraDegree() <= cameraDegree) {
                f = cameraDegree;
            } else {
                f = 40.0f;
            }
        }
        gLMapState.setCameraDegree(f);
        gLMapState.recalculate();
    }
}
