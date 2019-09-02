package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import com.amap.api.maps.model.AMapGestureListener;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.autonavi.ae.gmap.gesture.EAMapPlatformGestureInfo;
import com.autonavi.amap.mapcore.message.HoverGestureMapMessage;
import com.autonavi.amap.mapcore.message.MoveGestureMapMessage;
import com.autonavi.amap.mapcore.message.RotateGestureMapMessage;
import com.autonavi.amap.mapcore.message.ScaleGestureMapMessage;

/* compiled from: GlMapGestureDetector */
public class lg {
    lj a;
    Context b;
    GestureDetector c;
    public AMapGestureListener d;
    private x e;
    private v f;
    private u g;
    private y h;
    private boolean i = false;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private boolean o = false;
    private boolean p = false;
    private boolean q = true;
    private Handler r = new Handler(Looper.getMainLooper());

    /* compiled from: GlMapGestureDetector */
    private class a implements OnDoubleTapListener, OnGestureListener {
        float a;
        long b;
        final /* synthetic */ lg c;
        private int d;
        private EAMapPlatformGestureInfo e;

        private a(lg lgVar) {
            this.c = lgVar;
            this.d = 0;
            this.a = 0.0f;
            this.e = new EAMapPlatformGestureInfo();
            this.b = 0;
        }

        public boolean onDown(MotionEvent motionEvent) {
            this.c.o = false;
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.c.d != null) {
                this.c.d.onFling(f, f2);
            }
            try {
                if (this.c.a.h().isScrollGesturesEnabled() && this.c.m <= 0 && this.c.k <= 0 && this.c.l == 0 && !this.c.q) {
                    this.e.mGestureState = 3;
                    this.e.mGestureType = 3;
                    float[] fArr = new float[]{motionEvent2.getX(), motionEvent2.getY()};
                    this.e.mLocation = fArr;
                    int a = this.c.a.a(this.e);
                    this.c.a.onFling();
                    this.c.a.a().startMapSlidAnim(a, new Point((int) motionEvent2.getX(), (int) motionEvent2.getY()), f, f2);
                }
            } catch (Throwable th) {
                gz.c(th, "GLMapGestrureDetector", "onFling");
                th.printStackTrace();
            }
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (this.c.n == 1) {
                this.e.mGestureState = 3;
                this.e.mGestureType = 7;
                float[] fArr = new float[]{motionEvent.getX(), motionEvent.getY()};
                this.e.mLocation = fArr;
                this.c.a.a(this.c.a.a(this.e), motionEvent);
                if (this.c.d != null) {
                    this.c.d.onLongPress(motionEvent.getX(), motionEvent.getY());
                }
            }
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.c.d != null) {
                this.c.d.onScroll(f, f2);
            }
            return false;
        }

        public void onShowPress(MotionEvent motionEvent) {
            try {
                this.e.mGestureState = 3;
                this.e.mGestureType = 7;
                float[] fArr = new float[]{motionEvent.getX(), motionEvent.getY()};
                this.e.mLocation = fArr;
                this.c.a.a().clearAnimations(this.c.a.a(this.e), false);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            this.c.c.setIsLongpressEnabled(false);
            this.d = motionEvent.getPointerCount();
            if (this.c.d != null) {
                this.c.d.onDoubleTap(motionEvent.getX(), motionEvent.getY());
            }
            return false;
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            boolean z = true;
            if (this.d < motionEvent.getPointerCount()) {
                this.d = motionEvent.getPointerCount();
            }
            int action = motionEvent.getAction() & 255;
            if (this.d == 1) {
                try {
                    if (!this.c.a.h().isZoomGesturesEnabled()) {
                        return false;
                    }
                } catch (Throwable th) {
                    gz.c(th, "GLMapGestrureDetector", "onDoubleTapEvent");
                    th.printStackTrace();
                }
                int a;
                if (action == 0) {
                    this.e.mGestureState = 1;
                    this.e.mGestureType = 9;
                    float[] fArr = new float[]{motionEvent.getX(), motionEvent.getY()};
                    this.e.mLocation = fArr;
                    a = this.c.a.a(this.e);
                    this.a = motionEvent.getY();
                    this.c.a.a(a, ScaleGestureMapMessage.obtain(100, 1.0f, 0, 0));
                    this.b = SystemClock.uptimeMillis();
                } else if (action == 2) {
                    this.c.o = true;
                    float y = this.a - motionEvent.getY();
                    if (Math.abs(y) >= ((float) 20)) {
                        this.e.mGestureState = 2;
                        this.e.mGestureType = 9;
                        r4 = new float[]{motionEvent.getX(), motionEvent.getY()};
                        this.e.mLocation = r4;
                        action = this.c.a.a(this.e);
                        float mapHeight = (4.0f * y) / ((float) this.c.a.getMapHeight());
                        if (y > 0.0f) {
                            this.c.a.a(action, ScaleGestureMapMessage.obtain(101, mapHeight, 0, 0));
                        } else {
                            this.c.a.a(action, ScaleGestureMapMessage.obtain(101, mapHeight, 0, 0));
                        }
                        this.a = motionEvent.getY();
                    }
                } else {
                    this.e.mGestureState = 3;
                    this.e.mGestureType = 9;
                    r4 = new float[]{motionEvent.getX(), motionEvent.getY()};
                    this.e.mLocation = r4;
                    a = this.c.a.a(this.e);
                    this.c.c.setIsLongpressEnabled(true);
                    this.c.a.a(a, ScaleGestureMapMessage.obtain(102, 1.0f, 0, 0));
                    if (action == 1) {
                        this.c.a.a(a, 3);
                        long uptimeMillis = SystemClock.uptimeMillis() - this.b;
                        if (!this.c.o || uptimeMillis < ((long) 200)) {
                            return this.c.a.b(a, motionEvent);
                        }
                        this.c.o = false;
                    } else {
                        this.c.o = false;
                    }
                }
            } else {
                z = false;
            }
            return z;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (this.c.n != 1) {
                return false;
            }
            this.e.mGestureState = 3;
            this.e.mGestureType = 8;
            float[] fArr = new float[]{motionEvent.getX(), motionEvent.getY()};
            this.e.mLocation = fArr;
            int a = this.c.a.a(this.e);
            if (this.c.d != null) {
                try {
                    this.c.d.onSingleTap(motionEvent.getX(), motionEvent.getY());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            return this.c.a.c(a, motionEvent);
        }
    }

    /* compiled from: GlMapGestureDetector */
    private class b implements com.amap.api.mapcore.util.u.a {
        final /* synthetic */ lg a;
        private EAMapPlatformGestureInfo b;

        private b(lg lgVar) {
            this.a = lgVar;
            this.b = new EAMapPlatformGestureInfo();
        }

        public boolean a(u uVar) {
            int i = 0;
            this.b.mGestureState = 2;
            this.b.mGestureType = 6;
            this.b.mLocation = new float[]{uVar.c().getX(), uVar.c().getY()};
            try {
                if (!this.a.a.h().isTiltGesturesEnabled()) {
                    return true;
                }
                int a = this.a.a.a(this.b);
                if (this.a.a.d(a)) {
                    return false;
                }
                if (this.a.l > 3) {
                    return false;
                }
                float f = uVar.d().x;
                float f2 = uVar.d().y;
                if (!this.a.i) {
                    PointF a2 = uVar.a(0);
                    PointF a3 = uVar.a(1);
                    int i2 = ((a2.y <= 10.0f || a3.y <= 10.0f) && (a2.y >= -10.0f || a3.y >= -10.0f)) ? 0 : true;
                    if (i2 != 0) {
                        i = 1;
                    }
                    if (i != 0 && Math.abs(f2) > ((float) 10) && Math.abs(f) < ((float) 10)) {
                        this.a.i = true;
                    }
                }
                if (!this.a.i) {
                    return true;
                }
                this.a.i = true;
                float f3 = f2 / 6.0f;
                if (Math.abs(f3) <= 1.0f) {
                    return true;
                }
                this.a.a.a(a, HoverGestureMapMessage.obtain(101, f3));
                this.a.m = this.a.m + 1;
                return true;
            } catch (Throwable th) {
                gz.c(th, "GLMapGestrureDetector", "onHove");
                th.printStackTrace();
                return true;
            }
        }

        public boolean b(u uVar) {
            this.b.mGestureState = 1;
            this.b.mGestureType = 6;
            float[] fArr = new float[]{uVar.c().getX(), uVar.c().getY()};
            this.b.mLocation = fArr;
            try {
                if (!this.a.a.h().isTiltGesturesEnabled()) {
                    return true;
                }
                int a = this.a.a.a(this.b);
                if (this.a.a.d(a)) {
                    return false;
                }
                this.a.a.a(a, HoverGestureMapMessage.obtain(100, this.a.a.o(a)));
                return true;
            } catch (Throwable th) {
                gz.c(th, "GLMapGestrureDetector", "onHoveBegin");
                th.printStackTrace();
                return true;
            }
        }

        public void c(u uVar) {
            this.b.mGestureState = 3;
            this.b.mGestureType = 6;
            float[] fArr = new float[]{uVar.c().getX(), uVar.c().getY()};
            this.b.mLocation = fArr;
            try {
                if (this.a.a.h().isTiltGesturesEnabled()) {
                    int a = this.a.a.a(this.b);
                    if (!this.a.a.d(a)) {
                        if (this.a.a.o(a) >= 0.0f && this.a.m > 0) {
                            this.a.a.a(a, 7);
                        }
                        this.a.i = false;
                        this.a.a.a(a, HoverGestureMapMessage.obtain(102, this.a.a.o(a)));
                    }
                }
            } catch (Throwable th) {
                gz.c(th, "GLMapGestrureDetector", "onHoveEnd");
                th.printStackTrace();
            }
        }
    }

    /* compiled from: GlMapGestureDetector */
    private class c implements com.amap.api.mapcore.util.v.a {
        final /* synthetic */ lg a;
        private EAMapPlatformGestureInfo b;

        private c(lg lgVar) {
            this.a = lgVar;
            this.b = new EAMapPlatformGestureInfo();
        }

        public boolean a(v vVar) {
            if (this.a.i) {
                return true;
            }
            try {
                if (!this.a.a.h().isScrollGesturesEnabled() || this.a.p) {
                    return true;
                }
                this.b.mGestureState = 2;
                this.b.mGestureType = 3;
                float[] fArr = new float[]{vVar.c().getX(), vVar.c().getY()};
                this.b.mLocation = fArr;
                int a = this.a.a.a(this.b);
                PointF d = vVar.d();
                float f = 1.0f;
                if (this.a.j == 0) {
                    f = 4.0f;
                }
                if (Math.abs(d.x) <= f && Math.abs(d.y) <= f) {
                    return false;
                }
                if (this.a.j == 0) {
                    this.a.a.a().clearAnimations(a, false);
                }
                this.a.a.a(a, MoveGestureMapMessage.obtain(101, d.x, d.y));
                this.a.j = this.a.j + 1;
                return true;
            } catch (Throwable th) {
                gz.c(th, "GLMapGestrureDetector", "onMove");
                th.printStackTrace();
                return true;
            }
        }

        public boolean b(v vVar) {
            try {
                if (this.a.a.h().isScrollGesturesEnabled()) {
                    this.b.mGestureState = 1;
                    this.b.mGestureType = 3;
                    float[] fArr = new float[]{vVar.c().getX(), vVar.c().getY()};
                    this.b.mLocation = fArr;
                    this.a.a.a(this.a.a.a(this.b), MoveGestureMapMessage.obtain(100, 0.0f, 0.0f));
                }
            } catch (Throwable th) {
                gz.c(th, "GLMapGestrureDetector", "onMoveBegin");
                th.printStackTrace();
            }
            return true;
        }

        public void c(v vVar) {
            try {
                if (this.a.a.h().isScrollGesturesEnabled()) {
                    this.b.mGestureState = 3;
                    this.b.mGestureType = 3;
                    float[] fArr = new float[]{vVar.c().getX(), vVar.c().getY()};
                    this.b.mLocation = fArr;
                    int a = this.a.a.a(this.b);
                    if (this.a.j > 0) {
                        this.a.a.a(a, 5);
                    }
                    this.a.a.a(a, MoveGestureMapMessage.obtain(102, 0.0f, 0.0f));
                }
            } catch (Throwable th) {
                gz.c(th, "GLMapGestrureDetector", "onMoveEnd");
                th.printStackTrace();
            }
        }
    }

    /* compiled from: GlMapGestureDetector */
    private class d extends com.amap.api.mapcore.util.x.a {
        final /* synthetic */ lg a;
        private boolean b;
        private boolean c;
        private boolean d;
        private Point e;
        private float[] f;
        private float g;
        private float[] h;
        private float i;
        private EAMapPlatformGestureInfo j;

        private d(lg lgVar) {
            this.a = lgVar;
            this.b = false;
            this.c = false;
            this.d = false;
            this.e = new Point();
            this.f = new float[10];
            this.g = 0.0f;
            this.h = new float[10];
            this.i = 0.0f;
            this.j = new EAMapPlatformGestureInfo();
        }

        public boolean a(x xVar) {
            Object obj;
            this.j.mGestureState = 2;
            this.j.mGestureType = 4;
            this.j.mLocation = new float[]{xVar.a().getX(), xVar.a().getY()};
            int a = this.a.a.a(this.j);
            boolean z = false;
            float j = xVar.j();
            float k = (float) xVar.k();
            int b = (int) xVar.b();
            int c = (int) xVar.c();
            float abs = (float) Math.abs(b - this.e.x);
            float abs2 = (float) Math.abs(c - this.e.y);
            this.e.x = b;
            this.e.y = c;
            float log = (float) Math.log((double) j);
            if (this.a.k <= 0 && Math.abs(log) > 0.2f) {
                this.d = true;
            }
            try {
                if (this.a.a.h().isZoomGesturesEnabled()) {
                    if (!this.b && 0.06f < Math.abs(log)) {
                        this.b = true;
                    }
                    if (this.b && 0.01f < Math.abs(log)) {
                        z = true;
                        obj = ((abs > 2.0f || abs2 > 2.0f) && Math.abs(log) < 0.02f) ? 1 : null;
                        if (obj == null && k > 0.0f) {
                            this.g = log / k;
                            this.f[this.a.k % 10] = Math.abs(this.g);
                            this.a.k = this.a.k + 1;
                            this.a.a.a(a, ScaleGestureMapMessage.obtain(101, log, b, c));
                            if (log > 0.0f) {
                                this.a.a.a(a, 1);
                            } else {
                                this.a.a.a(a, 2);
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                boolean z2 = z;
                Throwable th3 = th2;
                gz.c(th3, "GLMapGestrureDetector", "onScaleRotate");
                th3.printStackTrace();
                z = z2;
            }
            try {
                if (!(!this.a.a.h().isRotateGesturesEnabled() || this.a.a.e(a) || this.d)) {
                    log = xVar.l();
                    if (!this.c && Math.abs(log) >= 4.0f) {
                        this.c = true;
                    }
                    if (this.c && 1.0f < Math.abs(log)) {
                        obj = ((abs > 4.0f || abs2 > 4.0f) && Math.abs(log) < 2.0f) ? 1 : null;
                        if (obj == null) {
                            this.i = log / k;
                            this.h[this.a.l % 10] = Math.abs(this.i);
                            this.a.l = this.a.l + 1;
                            this.a.a.a(a, RotateGestureMapMessage.obtain(101, log, b, c));
                            z = true;
                            this.a.a.a(a, 6);
                        }
                    }
                }
            } catch (Throwable th4) {
                gz.c(th4, "GLMapGestrureDetector", "onScaleRotate");
                th4.printStackTrace();
            }
            return z;
        }

        public boolean b(x xVar) {
            this.j.mGestureState = 1;
            this.j.mGestureType = 4;
            float[] fArr = new float[]{xVar.a().getX(), xVar.a().getY()};
            this.j.mLocation = fArr;
            int a = this.a.a.a(this.j);
            int b = (int) xVar.b();
            int c = (int) xVar.c();
            this.d = false;
            this.e.x = b;
            this.e.y = c;
            this.b = false;
            this.c = false;
            this.a.a.a(a, ScaleGestureMapMessage.obtain(100, 1.0f, b, c));
            try {
                if (this.a.a.h().isRotateGesturesEnabled() && !this.a.a.e(a)) {
                    this.a.a.a(a, RotateGestureMapMessage.obtain(100, this.a.a.n(a), b, c));
                }
            } catch (Throwable th) {
                gz.c(th, "GLMapGestrureDetector", "onScaleRotateBegin");
                th.printStackTrace();
            }
            return true;
        }

        public void c(x xVar) {
            float f;
            int i;
            float f2;
            float f3;
            float f4;
            this.j.mGestureState = 3;
            this.j.mGestureType = 4;
            this.j.mLocation = new float[]{xVar.a().getX(), xVar.a().getY()};
            int a = this.a.a.a(this.j);
            this.d = false;
            this.a.a.a(a, ScaleGestureMapMessage.obtain(102, 1.0f, 0, 0));
            if (this.a.k > 0) {
                int b = this.a.k > 10 ? 10 : this.a.k;
                f = 0.0f;
                for (i = 0; i < 10; i++) {
                    f += this.f[i];
                    this.f[i] = 0.0f;
                }
                f2 = f / ((float) b);
                if (0.004f <= f2) {
                    f2 *= 300.0f;
                    if (f2 >= 1.5f) {
                        f2 = 1.5f;
                    }
                    if (this.g < 0.0f) {
                        f2 = -f2;
                    }
                    f2 += this.a.a.a(a);
                } else {
                    f2 = -9999.0f;
                }
                this.g = 0.0f;
                f3 = f2;
            } else {
                f3 = -9999.0f;
            }
            if (this.a.a.e(a)) {
                f4 = -9999.0f;
            } else {
                try {
                    if (this.a.a.h().isRotateGesturesEnabled()) {
                        this.a.a.a(a, RotateGestureMapMessage.obtain(102, this.a.a.n(a), 0, 0));
                    }
                } catch (Throwable th) {
                    gz.c(th, "GLMapGestrureDetector", "onScaleRotateEnd");
                    th.printStackTrace();
                }
                if (this.a.l > 0) {
                    this.a.a.a(a, 6);
                    if (this.a.l > 10) {
                        b = 10;
                    } else {
                        b = this.a.l;
                    }
                    f = 0.0f;
                    for (i = 0; i < 10; i++) {
                        f += this.h[i];
                        this.h[i] = 0.0f;
                    }
                    f2 = f / ((float) b);
                    if (0.1f <= f2) {
                        float f5 = 200.0f * f2;
                        i = ((int) this.a.a.n(a)) % SpatialRelationUtil.A_CIRCLE_DEGREE;
                        f2 = 60.0f;
                        if (f5 < 60.0f) {
                            f2 = f5;
                        }
                        if (this.i < 0.0f) {
                            f2 = -f2;
                        }
                        f2 = (float) (((int) (f2 + ((float) i))) % SpatialRelationUtil.A_CIRCLE_DEGREE);
                        this.g = 0.0f;
                        f4 = f2;
                    }
                }
                f2 = -9999.0f;
                this.g = 0.0f;
                f4 = f2;
            }
            boolean z = (f3 == -9999.0f && f4 == -9999.0f) ? false : true;
            if (z) {
                this.a.a.a().startPivotZoomRotateAnim(a, this.e, f3, (int) f4, 500);
            }
        }
    }

    /* compiled from: GlMapGestureDetector */
    private class e extends com.amap.api.mapcore.util.y.b {
        EAMapPlatformGestureInfo a;
        final /* synthetic */ lg b;

        private e(lg lgVar) {
            this.b = lgVar;
            this.a = new EAMapPlatformGestureInfo();
        }

        public void b(y yVar) {
            try {
                if (this.b.a.h().isZoomGesturesEnabled() && Math.abs(yVar.d()) <= ((float) 10) && Math.abs(yVar.e()) <= ((float) 10) && yVar.b() < 200) {
                    this.b.q = true;
                    this.a.mGestureState = 2;
                    this.a.mGestureType = 2;
                    float[] fArr = new float[]{yVar.c().getX(), yVar.c().getY()};
                    this.a.mLocation = fArr;
                    int a = this.b.a.a(this.a);
                    this.b.a.a(a, 4);
                    this.b.a.c(a);
                }
            } catch (Throwable th) {
                gz.c(th, "GLMapGestrureDetector", "onZoomOut");
                th.printStackTrace();
            }
        }
    }

    public void a(AMapGestureListener aMapGestureListener) {
        this.d = aMapGestureListener;
    }

    public void a() {
        this.j = 0;
        this.l = 0;
        this.k = 0;
        this.m = 0;
        this.n = 0;
    }

    public lg(lj ljVar) {
        this.b = ljVar.v();
        this.a = ljVar;
        Object aVar = new a();
        this.c = new GestureDetector(this.b, aVar, this.r);
        this.c.setOnDoubleTapListener(aVar);
        this.e = new x(this.b, new d());
        this.f = new v(this.b, new c());
        this.g = new u(this.b, new b());
        this.h = new y(this.b, new e());
    }

    public boolean a(MotionEvent motionEvent) {
        if (this.n < motionEvent.getPointerCount()) {
            this.n = motionEvent.getPointerCount();
        }
        if ((motionEvent.getAction() & 255) == 0) {
            this.p = false;
            this.q = false;
        }
        if (motionEvent.getAction() == 6 && motionEvent.getPointerCount() > 0) {
            this.p = true;
        }
        if (this.o && this.n >= 2) {
            this.o = false;
        }
        try {
            if (this.d != null) {
                if (motionEvent.getAction() == 0) {
                    this.d.onDown(motionEvent.getX(), motionEvent.getY());
                } else if (motionEvent.getAction() == 1) {
                    this.d.onUp(motionEvent.getX(), motionEvent.getY());
                }
            }
            this.c.onTouchEvent(motionEvent);
            boolean c = this.g.c(motionEvent);
            if (this.i && this.m > 0) {
                return c;
            }
            this.h.c(motionEvent);
            if (this.o) {
                return c;
            }
            this.e.a(motionEvent);
            return this.f.c(motionEvent);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public void b() {
        if (this.r != null) {
            this.r.removeCallbacks(null);
            this.r = null;
        }
    }
}
