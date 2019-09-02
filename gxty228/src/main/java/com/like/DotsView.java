package com.like;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import com.github.mikephil.charting.utils.Utils;

public class DotsView extends View {
    public static final Property<DotsView, Float> a = new Property<DotsView, Float>(Float.class, "dotsProgress") {
        public /* synthetic */ Object get(Object obj) {
            return a((DotsView) obj);
        }

        public /* synthetic */ void set(Object obj, Object obj2) {
            a((DotsView) obj, (Float) obj2);
        }

        public Float a(DotsView dotsView) {
            return Float.valueOf(dotsView.getCurrentProgress());
        }

        public void a(DotsView dotsView, Float f) {
            dotsView.setCurrentProgress(f.floatValue());
        }
    };
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private final Paint[] h;
    private int i;
    private int j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private float q;
    private float r;
    private ArgbEvaluator s;

    public DotsView(Context context) {
        super(context);
        this.b = -16121;
        this.c = -26624;
        this.d = -43230;
        this.e = -769226;
        this.f = 0;
        this.g = 0;
        this.h = new Paint[4];
        this.n = 0.0f;
        this.o = 0.0f;
        this.p = 0.0f;
        this.q = 0.0f;
        this.r = 0.0f;
        this.s = new ArgbEvaluator();
        a();
    }

    public DotsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = -16121;
        this.c = -26624;
        this.d = -43230;
        this.e = -769226;
        this.f = 0;
        this.g = 0;
        this.h = new Paint[4];
        this.n = 0.0f;
        this.o = 0.0f;
        this.p = 0.0f;
        this.q = 0.0f;
        this.r = 0.0f;
        this.s = new ArgbEvaluator();
        a();
    }

    public DotsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = -16121;
        this.c = -26624;
        this.d = -43230;
        this.e = -769226;
        this.f = 0;
        this.g = 0;
        this.h = new Paint[4];
        this.n = 0.0f;
        this.o = 0.0f;
        this.p = 0.0f;
        this.q = 0.0f;
        this.r = 0.0f;
        this.s = new ArgbEvaluator();
        a();
    }

    private void a() {
        for (int i = 0; i < this.h.length; i++) {
            this.h[i] = new Paint();
            this.h[i].setStyle(Style.FILL);
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.i = i / 2;
        this.j = i2 / 2;
        this.m = 5.0f;
        this.k = ((float) (i / 2)) - (this.m * 2.0f);
        this.l = 0.8f * this.k;
    }

    protected void onDraw(Canvas canvas) {
        a(canvas);
        b(canvas);
    }

    private void a(Canvas canvas) {
        for (int i = 0; i < 7; i++) {
            canvas.drawCircle((float) ((int) (((double) this.i) + (((double) this.o) * Math.cos((((double) (i * 51)) * 3.141592653589793d) / 180.0d)))), (float) ((int) (((double) this.j) + (((double) this.o) * Math.sin((((double) (i * 51)) * 3.141592653589793d) / 180.0d)))), this.p, this.h[i % this.h.length]);
        }
    }

    private void b(Canvas canvas) {
        for (int i = 0; i < 7; i++) {
            canvas.drawCircle((float) ((int) (((double) this.i) + (((double) this.r) * Math.cos((((double) ((i * 51) - 10)) * 3.141592653589793d) / 180.0d)))), (float) ((int) (((double) this.j) + (((double) this.r) * Math.sin((((double) ((i * 51) - 10)) * 3.141592653589793d) / 180.0d)))), this.q, this.h[(i + 1) % this.h.length]);
        }
    }

    public void setCurrentProgress(float f) {
        this.n = f;
        b();
        c();
        d();
        e();
        postInvalidate();
    }

    public float getCurrentProgress() {
        return this.n;
    }

    private void b() {
        if (this.n < 0.3f) {
            this.r = (float) d.a((double) this.n, Utils.DOUBLE_EPSILON, 0.30000001192092896d, Utils.DOUBLE_EPSILON, (double) this.l);
        } else {
            this.r = this.l;
        }
        if (this.n == 0.0f) {
            this.q = 0.0f;
        } else if (((double) this.n) < 0.2d) {
            this.q = this.m;
        } else if (((double) this.n) < 0.5d) {
            this.q = (float) d.a((double) this.n, 0.20000000298023224d, 0.5d, (double) this.m, ((double) this.m) * 0.3d);
        } else {
            this.q = (float) d.a((double) this.n, 0.5d, 1.0d, (double) (this.m * 0.3f), Utils.DOUBLE_EPSILON);
        }
    }

    private void c() {
        if (this.n < 0.3f) {
            this.o = (float) d.a((double) this.n, Utils.DOUBLE_EPSILON, 0.30000001192092896d, Utils.DOUBLE_EPSILON, (double) (this.k * 0.8f));
        } else {
            this.o = (float) d.a((double) this.n, 0.30000001192092896d, 1.0d, (double) (0.8f * this.k), (double) this.k);
        }
        if (this.n == 0.0f) {
            this.p = 0.0f;
        } else if (((double) this.n) < 0.7d) {
            this.p = this.m;
        } else {
            this.p = (float) d.a((double) this.n, 0.699999988079071d, 1.0d, (double) this.m, Utils.DOUBLE_EPSILON);
        }
    }

    private void d() {
        if (this.n < 0.5f) {
            float a = (float) d.a((double) this.n, Utils.DOUBLE_EPSILON, 0.5d, Utils.DOUBLE_EPSILON, 1.0d);
            this.h[0].setColor(((Integer) this.s.evaluate(a, Integer.valueOf(this.b), Integer.valueOf(this.c))).intValue());
            this.h[1].setColor(((Integer) this.s.evaluate(a, Integer.valueOf(this.c), Integer.valueOf(this.d))).intValue());
            this.h[2].setColor(((Integer) this.s.evaluate(a, Integer.valueOf(this.d), Integer.valueOf(this.e))).intValue());
            this.h[3].setColor(((Integer) this.s.evaluate(a, Integer.valueOf(this.e), Integer.valueOf(this.b))).intValue());
            return;
        }
        a = (float) d.a((double) this.n, 0.5d, 1.0d, Utils.DOUBLE_EPSILON, 1.0d);
        this.h[0].setColor(((Integer) this.s.evaluate(a, Integer.valueOf(this.c), Integer.valueOf(this.d))).intValue());
        this.h[1].setColor(((Integer) this.s.evaluate(a, Integer.valueOf(this.d), Integer.valueOf(this.e))).intValue());
        this.h[2].setColor(((Integer) this.s.evaluate(a, Integer.valueOf(this.e), Integer.valueOf(this.b))).intValue());
        this.h[3].setColor(((Integer) this.s.evaluate(a, Integer.valueOf(this.b), Integer.valueOf(this.c))).intValue());
    }

    public void a(@ColorInt int i, @ColorInt int i2) {
        this.b = i;
        this.c = i2;
        this.d = i;
        this.e = i2;
        invalidate();
    }

    private void e() {
        int a = (int) d.a((double) ((float) d.a((double) this.n, 0.6000000238418579d, 1.0d)), 0.6000000238418579d, 1.0d, 255.0d, Utils.DOUBLE_EPSILON);
        this.h[0].setAlpha(a);
        this.h[1].setAlpha(a);
        this.h[2].setAlpha(a);
        this.h[3].setAlpha(a);
    }

    public void b(int i, int i2) {
        this.f = i;
        this.g = i2;
        invalidate();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f != 0 && this.g != 0) {
            setMeasuredDimension(this.f, this.g);
        }
    }
}
