package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.view.MotionEvent;

/* compiled from: BaseGestureDetector */
public abstract class t {
    protected final Context e;
    protected boolean f;
    protected MotionEvent g;
    protected MotionEvent h;
    protected float i;
    protected float j;
    protected long k;

    protected abstract void a(int i, MotionEvent motionEvent);

    protected abstract void b(int i, MotionEvent motionEvent);

    public t(Context context) {
        this.e = context;
    }

    public boolean c(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.f) {
            b(action, motionEvent);
        } else {
            a(action, motionEvent);
        }
        return true;
    }

    protected void a(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = this.g;
        if (this.h != null) {
            this.h.recycle();
            this.h = null;
        }
        this.h = MotionEvent.obtain(motionEvent);
        this.k = motionEvent.getEventTime() - motionEvent2.getEventTime();
        if (VERSION.SDK_INT >= 8) {
            this.i = motionEvent.getPressure(motionEvent.getActionIndex());
            this.j = motionEvent2.getPressure(motionEvent2.getActionIndex());
            return;
        }
        this.i = motionEvent.getPressure(0);
        this.j = motionEvent2.getPressure(0);
    }

    protected void a() {
        if (this.g != null) {
            this.g.recycle();
            this.g = null;
        }
        if (this.h != null) {
            this.h.recycle();
            this.h = null;
        }
        this.f = false;
    }

    public long b() {
        return this.k;
    }

    public static PointF d(MotionEvent motionEvent) {
        float f = 0.0f;
        int pointerCount = motionEvent.getPointerCount();
        float f2 = 0.0f;
        for (int i = 0; i < pointerCount; i++) {
            f2 += motionEvent.getX(i);
            f += motionEvent.getY(i);
        }
        return new PointF(f2 / ((float) pointerCount), f / ((float) pointerCount));
    }

    public MotionEvent c() {
        return this.h;
    }
}
