package com.github.ybq.android.spinkit.b;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.util.Property;
import com.github.ybq.android.spinkit.a.a;
import com.github.ybq.android.spinkit.a.b;
import com.github.ybq.android.spinkit.a.c;

/* compiled from: Sprite */
public abstract class e extends Drawable implements AnimatorUpdateListener, Animatable, Callback {
    private static final Rect B = new Rect();
    public static final Property<e, Integer> b = new c<e>("rotateX") {
        public /* synthetic */ Object get(Object obj) {
            return a((e) obj);
        }

        public void a(e eVar, int i) {
            eVar.e(i);
        }

        public Integer a(e eVar) {
            return Integer.valueOf(eVar.j());
        }
    };
    public static final Property<e, Integer> c = new c<e>("rotate") {
        public /* synthetic */ Object get(Object obj) {
            return a((e) obj);
        }

        public void a(e eVar, int i) {
            eVar.d(i);
        }

        public Integer a(e eVar) {
            return Integer.valueOf(eVar.f());
        }
    };
    public static final Property<e, Integer> d = new c<e>("rotateY") {
        public /* synthetic */ Object get(Object obj) {
            return a((e) obj);
        }

        public void a(e eVar, int i) {
            eVar.f(i);
        }

        public Integer a(e eVar) {
            return Integer.valueOf(eVar.k());
        }
    };
    public static final Property<e, Integer> e = new c<e>("translateX") {
        public /* synthetic */ Object get(Object obj) {
            return a((e) obj);
        }

        public void a(e eVar, int i) {
            eVar.b(i);
        }

        public Integer a(e eVar) {
            return Integer.valueOf(eVar.d());
        }
    };
    public static final Property<e, Integer> f = new c<e>("translateY") {
        public /* synthetic */ Object get(Object obj) {
            return a((e) obj);
        }

        public void a(e eVar, int i) {
            eVar.c(i);
        }

        public Integer a(e eVar) {
            return Integer.valueOf(eVar.e());
        }
    };
    public static final Property<e, Float> g = new b<e>("translateXPercentage") {
        public /* synthetic */ Object get(Object obj) {
            return a((e) obj);
        }

        public void a(e eVar, float f) {
            eVar.a(f);
        }

        public Float a(e eVar) {
            return Float.valueOf(eVar.b());
        }
    };
    public static final Property<e, Float> h = new b<e>("translateYPercentage") {
        public /* synthetic */ Object get(Object obj) {
            return a((e) obj);
        }

        public void a(e eVar, float f) {
            eVar.b(f);
        }

        public Float a(e eVar) {
            return Float.valueOf(eVar.c());
        }
    };
    public static final Property<e, Float> i = new b<e>("scaleX") {
        public /* synthetic */ Object get(Object obj) {
            return a((e) obj);
        }

        public void a(e eVar, float f) {
            eVar.d(f);
        }

        public Float a(e eVar) {
            return Float.valueOf(eVar.h());
        }
    };
    public static final Property<e, Float> j = new b<e>("scaleY") {
        public /* synthetic */ Object get(Object obj) {
            return a((e) obj);
        }

        public void a(e eVar, float f) {
            eVar.e(f);
        }

        public Float a(e eVar) {
            return Float.valueOf(eVar.i());
        }
    };
    public static final Property<e, Float> k = new b<e>("scale") {
        public /* synthetic */ Object get(Object obj) {
            return a((e) obj);
        }

        public void a(e eVar, float f) {
            eVar.c(f);
        }

        public Float a(e eVar) {
            return Float.valueOf(eVar.g());
        }
    };
    public static final Property<e, Integer> l = new c<e>("alpha") {
        public /* synthetic */ Object get(Object obj) {
            return a((e) obj);
        }

        public void a(e eVar, int i) {
            eVar.setAlpha(i);
        }

        public Integer a(e eVar) {
            return Integer.valueOf(eVar.getAlpha());
        }
    };
    private int A = 255;
    private Camera C = new Camera();
    private Matrix D = new Matrix();
    protected Rect a = B;
    private float m = 1.0f;
    private float n = 1.0f;
    private float o = 1.0f;
    private float p;
    private float q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private float x;
    private float y;
    private ValueAnimator z;

    public abstract ValueAnimator a();

    public abstract void a(int i);

    protected abstract void a_(Canvas canvas);

    public void setAlpha(int i) {
        this.A = i;
    }

    public int getAlpha() {
        return this.A;
    }

    public int getOpacity() {
        return 1;
    }

    public float b() {
        return this.x;
    }

    public void a(float f) {
        this.x = f;
    }

    public float c() {
        return this.y;
    }

    public void b(float f) {
        this.y = f;
    }

    public int d() {
        return this.u;
    }

    public void b(int i) {
        this.u = i;
    }

    public int e() {
        return this.v;
    }

    public void c(int i) {
        this.v = i;
    }

    public int f() {
        return this.w;
    }

    public void d(int i) {
        this.w = i;
    }

    public float g() {
        return this.m;
    }

    public void c(float f) {
        this.m = f;
        d(f);
        e(f);
    }

    public float h() {
        return this.n;
    }

    public void d(float f) {
        this.n = f;
    }

    public float i() {
        return this.o;
    }

    public void e(float f) {
        this.o = f;
    }

    public int j() {
        return this.s;
    }

    public void e(int i) {
        this.s = i;
    }

    public int k() {
        return this.t;
    }

    public void f(int i) {
        this.t = i;
    }

    public float l() {
        return this.p;
    }

    public void f(float f) {
        this.p = f;
    }

    public float m() {
        return this.q;
    }

    public void g(float f) {
        this.q = f;
    }

    public e g(int i) {
        this.r = i;
        return this;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void start() {
        this.z = n();
        if (this.z != null && !this.z.isStarted()) {
            a.a(this.z);
            invalidateSelf();
        }
    }

    public ValueAnimator n() {
        if (this.z == null) {
            this.z = a();
            if (this.z != null) {
                this.z.addUpdateListener(this);
            }
        }
        if (this.z != null) {
            this.z.setStartDelay((long) this.r);
        }
        return this.z;
    }

    public void stop() {
        if (this.z != null) {
            this.z.end();
            o();
            onAnimationUpdate(this.z);
        }
    }

    public void o() {
        this.m = 1.0f;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public boolean isRunning() {
        return a.a(this.z);
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        a(rect);
    }

    public void a(Rect rect) {
        a(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void a(int i, int i2, int i3, int i4) {
        this.a = new Rect(i, i2, i3, i4);
        f((float) p().centerX());
        g((float) p().centerY());
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public Rect p() {
        return this.a;
    }

    public void draw(Canvas canvas) {
        int d = d();
        if (d == 0) {
            d = (int) (((float) getBounds().width()) * b());
        }
        int e = e();
        if (e == 0) {
            e = (int) (((float) getBounds().height()) * c());
        }
        canvas.translate((float) d, (float) e);
        canvas.scale(h(), i(), l(), m());
        canvas.rotate((float) f(), l(), m());
        if (!(j() == 0 && k() == 0)) {
            this.C.save();
            this.C.rotateX((float) j());
            this.C.rotateY((float) k());
            this.C.getMatrix(this.D);
            this.D.preTranslate(-l(), -m());
            this.D.postTranslate(l(), m());
            this.C.restore();
            canvas.concat(this.D);
        }
        a_(canvas);
    }

    public Rect b(Rect rect) {
        int min = Math.min(rect.width(), rect.height());
        int centerX = rect.centerX();
        int centerY = rect.centerY();
        min /= 2;
        return new Rect(centerX - min, centerY - min, centerX + min, min + centerY);
    }
}
