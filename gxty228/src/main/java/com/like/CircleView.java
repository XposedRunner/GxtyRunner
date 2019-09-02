package com.like;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.ColorInt;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import com.github.mikephil.charting.utils.Utils;

public class CircleView extends View {
    public static final Property<CircleView, Float> a = new Property<CircleView, Float>(Float.class, "innerCircleRadiusProgress") {
        public /* synthetic */ Object get(Object obj) {
            return a((CircleView) obj);
        }

        public /* synthetic */ void set(Object obj, Object obj2) {
            a((CircleView) obj, (Float) obj2);
        }

        public Float a(CircleView circleView) {
            return Float.valueOf(circleView.getInnerCircleRadiusProgress());
        }

        public void a(CircleView circleView, Float f) {
            circleView.setInnerCircleRadiusProgress(f.floatValue());
        }
    };
    public static final Property<CircleView, Float> b = new Property<CircleView, Float>(Float.class, "outerCircleRadiusProgress") {
        public /* synthetic */ Object get(Object obj) {
            return a((CircleView) obj);
        }

        public /* synthetic */ void set(Object obj, Object obj2) {
            a((CircleView) obj, (Float) obj2);
        }

        public Float a(CircleView circleView) {
            return Float.valueOf(circleView.getOuterCircleRadiusProgress());
        }

        public void a(CircleView circleView, Float f) {
            circleView.setOuterCircleRadiusProgress(f.floatValue());
        }
    };
    private int c = -43230;
    private int d = -16121;
    private ArgbEvaluator e = new ArgbEvaluator();
    private Paint f = new Paint();
    private Paint g = new Paint();
    private Bitmap h;
    private Canvas i;
    private float j = 0.0f;
    private float k = 0.0f;
    private int l = 0;
    private int m = 0;
    private int n;

    public CircleView(Context context) {
        super(context);
        a();
    }

    public CircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public CircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.f.setStyle(Style.FILL);
        this.g.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
    }

    public void a(int i, int i2) {
        this.l = i;
        this.m = i2;
        invalidate();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.l != 0 && this.m != 0) {
            setMeasuredDimension(this.l, this.m);
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.n = i / 2;
        this.h = Bitmap.createBitmap(getWidth(), getWidth(), Config.ARGB_8888);
        this.i = new Canvas(this.h);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.i.drawColor(ViewCompat.MEASURED_SIZE_MASK, Mode.CLEAR);
        this.i.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.j * ((float) this.n), this.f);
        this.i.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.k * ((float) this.n), this.g);
        canvas.drawBitmap(this.h, 0.0f, 0.0f, null);
    }

    public void setInnerCircleRadiusProgress(float f) {
        this.k = f;
        postInvalidate();
    }

    public float getInnerCircleRadiusProgress() {
        return this.k;
    }

    public void setOuterCircleRadiusProgress(float f) {
        this.j = f;
        b();
        postInvalidate();
    }

    private void b() {
        this.f.setColor(((Integer) this.e.evaluate((float) d.a((double) ((float) d.a((double) this.j, 0.5d, 1.0d)), 0.5d, 1.0d, Utils.DOUBLE_EPSILON, 1.0d), Integer.valueOf(this.c), Integer.valueOf(this.d))).intValue());
    }

    public float getOuterCircleRadiusProgress() {
        return this.j;
    }

    public void setStartColor(@ColorInt int i) {
        this.c = i;
        invalidate();
    }

    public void setEndColor(@ColorInt int i) {
        this.d = i;
        invalidate();
    }
}
