package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* compiled from: AbstractTwoFingerGestureDetector */
public abstract class s extends t {
    protected float a;
    protected float b;
    protected float c;
    protected float d;
    private final float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private float q = 0.0f;
    private float r = 0.0f;
    private float s = 0.0f;
    private float t = 0.0f;

    public s(Context context) {
        super(context);
        this.l = (float) ViewConfiguration.get(context).getScaledEdgeSlop();
    }

    protected void a(MotionEvent motionEvent) {
        super.a(motionEvent);
        MotionEvent motionEvent2 = this.g;
        int pointerCount = this.g.getPointerCount();
        int pointerCount2 = motionEvent.getPointerCount();
        if (pointerCount2 == 2 && pointerCount2 == pointerCount) {
            this.o = -1.0f;
            this.p = -1.0f;
            float x = motionEvent2.getX(0);
            float y = motionEvent2.getY(0);
            float x2 = motionEvent2.getX(1);
            float y2 = motionEvent2.getY(1);
            float f = y2 - y;
            this.a = x2 - x;
            this.b = f;
            float x3 = motionEvent.getX(0);
            f = motionEvent.getY(0);
            float x4 = motionEvent.getX(1);
            float y3 = motionEvent.getY(1);
            float f2 = y3 - f;
            this.c = x4 - x3;
            this.d = f2;
            this.q = x3 - x;
            this.r = f - y;
            this.s = x4 - x2;
            this.t = y3 - y2;
        }
    }

    public PointF a(int i) {
        if (i == 0) {
            return new PointF(this.q, this.r);
        }
        return new PointF(this.s, this.t);
    }

    protected static float a(MotionEvent motionEvent, int i) {
        float x = motionEvent.getX() - motionEvent.getRawX();
        if (i < motionEvent.getPointerCount()) {
            return x + motionEvent.getX(i);
        }
        return 0.0f;
    }

    protected static float b(MotionEvent motionEvent, int i) {
        float y = motionEvent.getY() - motionEvent.getRawY();
        if (i < motionEvent.getPointerCount()) {
            return y + motionEvent.getY(i);
        }
        return 0.0f;
    }

    protected boolean b(MotionEvent motionEvent) {
        DisplayMetrics displayMetrics = this.e.getResources().getDisplayMetrics();
        this.m = ((float) displayMetrics.widthPixels) - this.l;
        this.n = ((float) displayMetrics.heightPixels) - this.l;
        float f = this.l;
        float f2 = this.m;
        float f3 = this.n;
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        float a = a(motionEvent, 1);
        float b = b(motionEvent, 1);
        boolean z = rawX < f || rawY < f || rawX > f2 || rawY > f3;
        boolean z2;
        if (a < f || b < f || a > f2 || b > f3) {
            z2 = true;
        } else {
            z2 = false;
        }
        if ((z && r2) || z || r2) {
            return true;
        }
        return false;
    }
}
