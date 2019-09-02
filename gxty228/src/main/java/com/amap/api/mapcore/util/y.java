package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;

/* compiled from: ZoomOutGestureDetectorAbstract */
public class y extends s {
    private static final PointF n = new PointF();
    private final a l;
    private boolean m;
    private PointF o;
    private PointF p;
    private PointF q = new PointF();
    private PointF r = new PointF();

    /* compiled from: ZoomOutGestureDetectorAbstract */
    public interface a {
        boolean a(y yVar);

        void b(y yVar);
    }

    /* compiled from: ZoomOutGestureDetectorAbstract */
    public static class b implements a {
        public boolean a(y yVar) {
            return true;
        }

        public void b(y yVar) {
        }
    }

    public y(Context context, a aVar) {
        super(context);
        this.l = aVar;
    }

    protected void a(int i, MotionEvent motionEvent) {
        switch (i) {
            case 5:
                a();
                this.g = MotionEvent.obtain(motionEvent);
                this.k = 0;
                a(motionEvent);
                this.m = b(motionEvent);
                if (!this.m) {
                    this.f = this.l.a(this);
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected void b(int i, MotionEvent motionEvent) {
        switch (i) {
            case 3:
                a();
                return;
            case 6:
                a(motionEvent);
                if (!this.m) {
                    this.l.b(this);
                }
                a();
                return;
            default:
                return;
        }
    }

    protected void a() {
        super.a();
        this.m = false;
        this.q.x = 0.0f;
        this.r.x = 0.0f;
        this.q.y = 0.0f;
        this.r.y = 0.0f;
    }

    protected void a(MotionEvent motionEvent) {
        super.a(motionEvent);
        MotionEvent motionEvent2 = this.g;
        this.o = t.d(motionEvent);
        this.p = t.d(motionEvent2);
        this.r = (this.g.getPointerCount() != motionEvent.getPointerCount() ? 1 : null) != null ? n : new PointF(this.o.x - this.p.x, this.o.y - this.p.y);
        PointF pointF = this.q;
        pointF.x += this.r.x;
        pointF = this.q;
        pointF.y += this.r.y;
    }

    public float d() {
        return this.q.x;
    }

    public float e() {
        return this.q.y;
    }
}
