package com.autonavi.amap.mapcore.message;

import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.ae.gmap.maploader.Pools.SynchronizedPool;
import com.autonavi.amap.mapcore.IPoint;

public class MoveGestureMapMessage extends AbstractGestureMapMessage {
    private static final SynchronizedPool<MoveGestureMapMessage> M_POOL = new SynchronizedPool(1024);
    static int newCount = 0;
    public float touchDeltaX = 0.0f;
    public float touchDeltaY = 0.0f;

    public static synchronized MoveGestureMapMessage obtain(int i, float f, float f2) {
        MoveGestureMapMessage moveGestureMapMessage;
        synchronized (MoveGestureMapMessage.class) {
            moveGestureMapMessage = (MoveGestureMapMessage) M_POOL.acquire();
            if (moveGestureMapMessage == null) {
                moveGestureMapMessage = new MoveGestureMapMessage(i, f, f2);
            } else {
                moveGestureMapMessage.reset();
                moveGestureMapMessage.setParams(i, f, f2);
            }
        }
        return moveGestureMapMessage;
    }

    public void recycle() {
        M_POOL.release(this);
    }

    public static void destory() {
        M_POOL.destory();
    }

    private void setParams(int i, float f, float f2) {
        setState(i);
        this.touchDeltaX = f;
        this.touchDeltaY = f2;
    }

    public MoveGestureMapMessage(int i, float f, float f2) {
        super(i);
        this.touchDeltaX = f;
        this.touchDeltaY = f2;
        newCount++;
    }

    public int getType() {
        return 0;
    }

    public void runCameraUpdate(GLMapState gLMapState) {
        int i = (int) this.touchDeltaX;
        int i2 = (int) this.touchDeltaY;
        float f = (float) (this.width >> 1);
        float f2 = (float) (this.height >> 1);
        if (this.isUseAnchor) {
            f = (float) this.anchorX;
            f2 = (float) this.anchorY;
        }
        int i3 = (int) (f - ((float) i));
        int i4 = (int) (f2 - ((float) i2));
        IPoint obtain = IPoint.obtain();
        win2geo(gLMapState, i3, i4, obtain);
        gLMapState.setMapGeoCenter(obtain.x, obtain.y);
        gLMapState.recalculate();
        obtain.recycle();
    }
}
