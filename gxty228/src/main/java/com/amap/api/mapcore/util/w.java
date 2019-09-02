package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* compiled from: ScaleGestureDetector */
public class w {
    private final Context a;
    private final a b;
    private boolean c;
    private MotionEvent d;
    private MotionEvent e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private long q;
    private final float r;
    private float s;
    private float t;
    private boolean u;
    private boolean v;
    private int w;
    private int x;
    private boolean y;

    /* compiled from: ScaleGestureDetector */
    public interface a {
        boolean a(w wVar);

        boolean b(w wVar);

        void c(w wVar);
    }

    public MotionEvent a() {
        return this.e;
    }

    public w(Context context, a aVar) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.a = context;
        this.b = aVar;
        this.r = (float) viewConfiguration.getScaledEdgeSlop();
    }

    public boolean a(MotionEvent motionEvent) {
        int i = -1;
        boolean z = true;
        boolean z2 = false;
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            l();
        }
        if (!this.v) {
            int findPointerIndex;
            if (!this.c) {
                float f;
                float f2;
                float b;
                float a;
                float b2;
                int a2;
                boolean z3;
                switch (action) {
                    case 0:
                        this.w = motionEvent.getPointerId(0);
                        this.y = true;
                        break;
                    case 1:
                        l();
                        break;
                    case 2:
                        if (this.u) {
                            boolean z4;
                            float f3 = this.r;
                            f = this.s;
                            f2 = this.t;
                            int findPointerIndex2 = motionEvent.findPointerIndex(this.w);
                            findPointerIndex = motionEvent.findPointerIndex(this.x);
                            float a3 = a(motionEvent, findPointerIndex2);
                            b = b(motionEvent, findPointerIndex2);
                            a = a(motionEvent, findPointerIndex);
                            b2 = b(motionEvent, findPointerIndex);
                            boolean z5 = a3 < f3 || b < f3 || a3 > f || b > f2;
                            if (a < f3 || b2 < f3 || a > f || b2 > f2) {
                                z4 = true;
                            } else {
                                z4 = false;
                            }
                            if (z5) {
                                a2 = a(motionEvent, this.x, findPointerIndex2);
                                if (a2 >= 0) {
                                    this.w = motionEvent.getPointerId(a2);
                                    a(motionEvent, a2);
                                    b(motionEvent, a2);
                                    findPointerIndex2 = a2;
                                    z3 = false;
                                    if (z4) {
                                        action = a(motionEvent, this.w, findPointerIndex);
                                        if (action >= 0) {
                                            this.x = motionEvent.getPointerId(action);
                                            a(motionEvent, action);
                                            b(motionEvent, action);
                                            z4 = false;
                                            if (z3 || !z4) {
                                                if (z3) {
                                                    if (z4) {
                                                        this.f = motionEvent.getX(findPointerIndex2);
                                                        this.g = motionEvent.getY(findPointerIndex2);
                                                        break;
                                                    }
                                                    this.u = false;
                                                    this.c = this.b.b(this);
                                                    break;
                                                }
                                                this.f = motionEvent.getX(action);
                                                this.g = motionEvent.getY(action);
                                                break;
                                            }
                                            this.f = -1.0f;
                                            this.g = -1.0f;
                                            break;
                                        }
                                    }
                                    action = findPointerIndex;
                                    if (z3) {
                                    }
                                    if (z3) {
                                        this.f = motionEvent.getX(action);
                                        this.g = motionEvent.getY(action);
                                    } else if (z4) {
                                        this.f = motionEvent.getX(findPointerIndex2);
                                        this.g = motionEvent.getY(findPointerIndex2);
                                    } else {
                                        this.u = false;
                                        this.c = this.b.b(this);
                                    }
                                }
                            }
                            z3 = z5;
                            if (z4) {
                                action = a(motionEvent, this.w, findPointerIndex);
                                if (action >= 0) {
                                    this.x = motionEvent.getPointerId(action);
                                    a(motionEvent, action);
                                    b(motionEvent, action);
                                    z4 = false;
                                    if (z3) {
                                    }
                                    if (z3) {
                                        this.f = motionEvent.getX(action);
                                        this.g = motionEvent.getY(action);
                                    } else if (z4) {
                                        this.f = motionEvent.getX(findPointerIndex2);
                                        this.g = motionEvent.getY(findPointerIndex2);
                                    } else {
                                        this.u = false;
                                        this.c = this.b.b(this);
                                    }
                                }
                            }
                            action = findPointerIndex;
                            if (z3) {
                            }
                            if (z3) {
                                this.f = motionEvent.getX(action);
                                this.g = motionEvent.getY(action);
                            } else if (z4) {
                                this.u = false;
                                this.c = this.b.b(this);
                            } else {
                                this.f = motionEvent.getX(findPointerIndex2);
                                this.g = motionEvent.getY(findPointerIndex2);
                            }
                        }
                        break;
                    case 5:
                        boolean z6;
                        DisplayMetrics displayMetrics = this.a.getResources().getDisplayMetrics();
                        this.s = ((float) displayMetrics.widthPixels) - this.r;
                        this.t = ((float) displayMetrics.heightPixels) - this.r;
                        if (this.d != null) {
                            this.d.recycle();
                        }
                        this.d = MotionEvent.obtain(motionEvent);
                        this.q = 0;
                        if (VERSION.SDK_INT >= 8) {
                            action = motionEvent.getActionIndex();
                            findPointerIndex = motionEvent.findPointerIndex(this.w);
                            this.x = motionEvent.getPointerId(action);
                            if (findPointerIndex < 0 || findPointerIndex == action) {
                                if (findPointerIndex != action) {
                                    i = this.x;
                                }
                                i = a(motionEvent, i, findPointerIndex);
                                this.w = motionEvent.getPointerId(i);
                                int i2 = action;
                                action = i;
                                i = i2;
                            } else {
                                i = action;
                                action = findPointerIndex;
                            }
                        } else if (motionEvent.getPointerCount() > 0) {
                            i = motionEvent.findPointerIndex(1);
                            action = motionEvent.findPointerIndex(this.w);
                            this.x = motionEvent.getPointerId(i);
                        } else {
                            i = 0;
                            action = 0;
                        }
                        this.y = false;
                        b(motionEvent);
                        float f4 = this.r;
                        float f5 = this.s;
                        f2 = this.t;
                        f = a(motionEvent, action);
                        b = b(motionEvent, action);
                        a = a(motionEvent, i);
                        b2 = b(motionEvent, i);
                        z3 = f < f4 || b < f4 || f > f5 || b > f2;
                        if (a < f4 || b2 < f4 || a > f5 || b2 > f2) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (!z3 || !z6) {
                            if (!z3) {
                                if (!z6) {
                                    this.u = false;
                                    this.c = this.b.b(this);
                                    break;
                                }
                                this.f = motionEvent.getX(action);
                                this.g = motionEvent.getY(action);
                                this.u = true;
                                break;
                            }
                            this.f = motionEvent.getX(i);
                            this.g = motionEvent.getY(i);
                            this.u = true;
                            break;
                        }
                        this.f = -1.0f;
                        this.g = -1.0f;
                        this.u = true;
                        break;
                        break;
                    case 6:
                        if (this.u) {
                            findPointerIndex = motionEvent.getPointerCount();
                            if (VERSION.SDK_INT >= 8) {
                                action = motionEvent.getActionIndex();
                            } else {
                                action = 0;
                            }
                            a2 = motionEvent.getPointerId(action);
                            if (findPointerIndex > 2) {
                                if (a2 != this.w) {
                                    if (a2 == this.x) {
                                        i = a(motionEvent, this.w, action);
                                        if (i >= 0) {
                                            this.x = motionEvent.getPointerId(i);
                                            break;
                                        }
                                    }
                                }
                                i = a(motionEvent, this.x, action);
                                if (i >= 0) {
                                    this.w = motionEvent.getPointerId(i);
                                    break;
                                }
                            }
                            action = motionEvent.findPointerIndex(a2 == this.w ? this.x : this.w);
                            if (action >= 0) {
                                this.w = motionEvent.getPointerId(action);
                                this.y = true;
                                this.x = -1;
                                this.f = motionEvent.getX(action);
                                this.g = motionEvent.getY(action);
                                break;
                            }
                            this.v = true;
                            if (!this.c) {
                                return false;
                            }
                            this.b.c(this);
                            return false;
                        }
                        break;
                    default:
                        break;
                }
            }
            switch (action) {
                case 1:
                    l();
                    break;
                case 2:
                    b(motionEvent);
                    if (this.o / this.p > 0.67f && this.b.a(this)) {
                        this.d.recycle();
                        this.d = MotionEvent.obtain(motionEvent);
                        break;
                    }
                case 3:
                    this.b.c(this);
                    l();
                    break;
                case 5:
                    this.b.c(this);
                    action = this.w;
                    findPointerIndex = this.x;
                    l();
                    this.d = MotionEvent.obtain(motionEvent);
                    if (!this.y) {
                        action = findPointerIndex;
                    }
                    this.w = action;
                    if (VERSION.SDK_INT >= 8) {
                        this.x = motionEvent.getPointerId(motionEvent.getActionIndex());
                    } else {
                        this.x = motionEvent.getPointerId(1);
                    }
                    this.y = false;
                    action = motionEvent.findPointerIndex(this.w);
                    if (action < 0 || this.w == this.x) {
                        if (this.w != this.x) {
                            i = this.x;
                        }
                        this.w = motionEvent.getPointerId(a(motionEvent, i, action));
                    }
                    b(motionEvent);
                    this.c = this.b.b(this);
                    break;
                case 6:
                    action = motionEvent.getPointerCount();
                    if (VERSION.SDK_INT >= 8) {
                        i = motionEvent.getActionIndex();
                    } else {
                        i = 0;
                    }
                    findPointerIndex = motionEvent.getPointerId(i);
                    if (action > 2) {
                        if (findPointerIndex == this.w) {
                            i = a(motionEvent, this.x, i);
                            if (i >= 0) {
                                this.b.c(this);
                                this.w = motionEvent.getPointerId(i);
                                this.y = true;
                                this.d = MotionEvent.obtain(motionEvent);
                                b(motionEvent);
                                this.c = this.b.b(this);
                            } else {
                                z2 = true;
                            }
                        } else if (findPointerIndex == this.x) {
                            i = a(motionEvent, this.w, i);
                            if (i >= 0) {
                                this.b.c(this);
                                this.x = motionEvent.getPointerId(i);
                                this.y = false;
                                this.d = MotionEvent.obtain(motionEvent);
                                b(motionEvent);
                                this.c = this.b.b(this);
                            } else {
                                z2 = true;
                            }
                        }
                        this.d.recycle();
                        this.d = MotionEvent.obtain(motionEvent);
                        b(motionEvent);
                    } else {
                        z2 = true;
                    }
                    if (z2) {
                        b(motionEvent);
                        i = findPointerIndex == this.w ? this.x : this.w;
                        action = motionEvent.findPointerIndex(i);
                        this.f = motionEvent.getX(action);
                        this.g = motionEvent.getY(action);
                        this.b.c(this);
                        l();
                        this.w = i;
                        this.y = true;
                        break;
                    }
                    break;
                default:
                    break;
            }
        }
        z = false;
        return z;
    }

    private int a(MotionEvent motionEvent, int i, int i2) {
        int pointerCount = motionEvent.getPointerCount();
        int findPointerIndex = motionEvent.findPointerIndex(i);
        int i3 = 0;
        while (i3 < pointerCount) {
            if (!(i3 == i2 || i3 == findPointerIndex)) {
                float f = this.r;
                float f2 = this.s;
                float f3 = this.t;
                float a = a(motionEvent, i3);
                float b = b(motionEvent, i3);
                if (a >= f && b >= f && a <= f2 && b <= f3) {
                    return i3;
                }
            }
            i3++;
        }
        return -1;
    }

    private static float a(MotionEvent motionEvent, int i) {
        if (i < 0) {
            return Float.MIN_VALUE;
        }
        if (i == 0) {
            return motionEvent.getRawX();
        }
        return (motionEvent.getRawX() - motionEvent.getX()) + motionEvent.getX(i);
    }

    private static float b(MotionEvent motionEvent, int i) {
        if (i < 0) {
            return Float.MIN_VALUE;
        }
        if (i == 0) {
            return motionEvent.getRawY();
        }
        return (motionEvent.getRawY() - motionEvent.getY()) + motionEvent.getY(i);
    }

    private void b(MotionEvent motionEvent) {
        if (this.e != null) {
            this.e.recycle();
        }
        this.e = MotionEvent.obtain(motionEvent);
        this.l = -1.0f;
        this.m = -1.0f;
        this.n = -1.0f;
        MotionEvent motionEvent2 = this.d;
        int findPointerIndex = motionEvent2.findPointerIndex(this.w);
        int findPointerIndex2 = motionEvent2.findPointerIndex(this.x);
        int findPointerIndex3 = motionEvent.findPointerIndex(this.w);
        int findPointerIndex4 = motionEvent.findPointerIndex(this.x);
        if (findPointerIndex < 0 || findPointerIndex2 < 0 || findPointerIndex3 < 0 || findPointerIndex4 < 0) {
            this.v = true;
            if (this.c) {
                this.b.c(this);
                return;
            }
            return;
        }
        float x = motionEvent2.getX(findPointerIndex);
        float y = motionEvent2.getY(findPointerIndex);
        float x2 = motionEvent2.getX(findPointerIndex2);
        float y2 = motionEvent2.getY(findPointerIndex2);
        float x3 = motionEvent.getX(findPointerIndex3);
        float y3 = motionEvent.getY(findPointerIndex3);
        x = x2 - x;
        y = y2 - y;
        x2 = motionEvent.getX(findPointerIndex4) - x3;
        y2 = motionEvent.getY(findPointerIndex4) - y3;
        this.h = x;
        this.i = y;
        this.j = x2;
        this.k = y2;
        this.f = (x2 * 0.5f) + x3;
        this.g = (y2 * 0.5f) + y3;
        this.q = motionEvent.getEventTime() - motionEvent2.getEventTime();
        this.o = motionEvent.getPressure(findPointerIndex3) + motionEvent.getPressure(findPointerIndex4);
        this.p = motionEvent2.getPressure(findPointerIndex2) + motionEvent2.getPressure(findPointerIndex);
    }

    private void l() {
        if (this.d != null) {
            this.d.recycle();
            this.d = null;
        }
        if (this.e != null) {
            this.e.recycle();
            this.e = null;
        }
        this.u = false;
        this.c = false;
        this.w = -1;
        this.x = -1;
        this.v = false;
    }

    public float b() {
        return this.f;
    }

    public float c() {
        return this.g;
    }

    public float d() {
        if (this.l == -1.0f) {
            float f = this.j;
            float f2 = this.k;
            this.l = (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
        }
        return this.l;
    }

    public float e() {
        return this.j;
    }

    public float f() {
        return this.k;
    }

    public float g() {
        if (this.m == -1.0f) {
            float f = this.h;
            float f2 = this.i;
            this.m = (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
        }
        return this.m;
    }

    public float h() {
        return this.h;
    }

    public float i() {
        return this.i;
    }

    public float j() {
        if (this.n == -1.0f) {
            this.n = d() / g();
        }
        return this.n;
    }

    public long k() {
        return this.q;
    }
}
