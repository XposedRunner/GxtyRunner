package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;

/* compiled from: MoveGestureDetector */
public class v extends t {
    private static final PointF a = new PointF();
    private final a b;
    private PointF c;
    private PointF d;
    private PointF l = new PointF();
    private PointF m = new PointF();

    /* compiled from: MoveGestureDetector */
    public interface a {
        boolean a(v vVar);

        boolean b(v vVar);

        void c(v vVar);
    }

    public v(Context context, a aVar) {
        super(context);
        this.b = aVar;
    }

    protected void a(int i, MotionEvent motionEvent) {
        switch (i) {
            case 0:
                a();
                this.g = MotionEvent.obtain(motionEvent);
                this.k = 0;
                a(motionEvent);
                return;
            case 2:
                this.f = this.b.b(this);
                return;
            case 5:
                if (this.g != null) {
                    this.g.recycle();
                }
                this.g = MotionEvent.obtain(motionEvent);
                a(motionEvent);
                return;
            default:
                return;
        }
    }

    protected void b(int i, MotionEvent motionEvent) {
        switch (i) {
            case 1:
            case 3:
                this.b.c(this);
                a();
                return;
            case 2:
                a(motionEvent);
                if (this.i / this.j > 0.67f && motionEvent.getPointerCount() <= 1 && this.b.a(this)) {
                    this.g.recycle();
                    this.g = MotionEvent.obtain(motionEvent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected void a(MotionEvent motionEvent) {
        super.a(motionEvent);
        MotionEvent motionEvent2 = this.g;
        this.c = t.d(motionEvent);
        this.d = t.d(motionEvent2);
        Object obj = this.g.getPointerCount() != motionEvent.getPointerCount() ? 1 : null;
        this.m = obj != null ? a : new PointF(this.c.x - this.d.x, this.c.y - this.d.y);
        if (obj != null) {
            this.g.recycle();
            this.g = MotionEvent.obtain(motionEvent);
        }
        PointF pointF = this.l;
        pointF.x += this.m.x;
        pointF = this.l;
        pointF.y += this.m.y;
    }

    public PointF d() {
        return this.m;
    }
}
