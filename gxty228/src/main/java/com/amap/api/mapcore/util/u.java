package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;

/* compiled from: HoverGestureDetectorAbstract */
public class u extends s {
    private static final PointF l = new PointF();
    private final a m;
    private boolean n;
    private PointF o;
    private PointF p;
    private PointF q = new PointF();
    private PointF r = new PointF();

    /* compiled from: HoverGestureDetectorAbstract */
    public interface a {
        boolean a(u uVar);

        boolean b(u uVar);

        void c(u uVar);
    }

    public u(Context context, a aVar) {
        super(context);
        this.m = aVar;
    }

    protected void a(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                if (this.n) {
                    this.n = b(motionEvent);
                    if (!this.n) {
                        this.f = this.m.b(this);
                        return;
                    }
                    return;
                }
                return;
            case 5:
                a();
                this.g = MotionEvent.obtain(motionEvent);
                this.k = 0;
                a(motionEvent);
                this.n = b(motionEvent);
                if (!this.n) {
                    this.f = this.m.b(this);
                    return;
                }
                return;
            case 6:
                if (!this.n) {
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected void b(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                a(motionEvent);
                if (this.i / this.j > 0.67f && this.m.a(this)) {
                    this.g.recycle();
                    this.g = MotionEvent.obtain(motionEvent);
                    return;
                }
                return;
            case 3:
                if (!this.n) {
                    this.m.c(this);
                }
                a();
                return;
            case 6:
                a(motionEvent);
                if (!this.n) {
                    this.m.c(this);
                }
                a();
                return;
            default:
                return;
        }
    }

    protected void a() {
        super.a();
        this.n = false;
    }

    protected void a(MotionEvent motionEvent) {
        super.a(motionEvent);
        MotionEvent motionEvent2 = this.g;
        this.o = t.d(motionEvent);
        this.p = t.d(motionEvent2);
        this.r = (this.g.getPointerCount() != motionEvent.getPointerCount() ? 1 : null) != null ? l : new PointF(this.o.x - this.p.x, this.o.y - this.p.y);
        PointF pointF = this.q;
        pointF.x += this.r.x;
        pointF = this.q;
        pointF.y += this.r.y;
    }

    public PointF d() {
        return this.r;
    }
}
