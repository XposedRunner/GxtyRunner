package com.example.gita.gxty.weiget;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.OvershootInterpolator;
import com.blankj.utilcode.constant.MemoryConstants;
import com.example.gita.gxty.R;
import com.example.gita.gxty.utils.b;

public class ProgressView extends View {
    private Matrix a = new Matrix();
    private Paint b = new Paint();
    private RectF c = new RectF();
    private Rect d = new Rect();
    private float e;
    private float f;
    private double g;
    private boolean h;
    private long i = 1800;
    private int j;
    private int k;
    private float l;
    private float m;
    private boolean n = true;
    private float o;
    private float p;
    private float q;
    private TimeInterpolator r;
    private int[] s;
    private Shader t;
    private boolean u = true;

    public ProgressView(Context context) {
        super(context);
        a(null, 0);
    }

    public ProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public ProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i);
    }

    private void a(AttributeSet attributeSet, int i) {
        float f = 0.0f;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ProgressView, i, 0);
        this.e = obtainStyledAttributes.getDimension(0, b(20.0f));
        this.j = obtainStyledAttributes.getColor(1, -7829368);
        this.k = obtainStyledAttributes.getColor(3, ViewCompat.MEASURED_STATE_MASK);
        this.o = obtainStyledAttributes.getDimension(2, a(30.0f));
        this.h = obtainStyledAttributes.getBoolean(4, false);
        this.q = obtainStyledAttributes.getDimension(5, 0.0f);
        this.p = obtainStyledAttributes.getDimension(6, a(16.0f));
        if (this.q >= 0.0f) {
            f = this.q;
        }
        this.q = f;
        obtainStyledAttributes.recycle();
    }

    protected void onDraw(Canvas canvas) {
        String valueOf;
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        this.b.setAntiAlias(true);
        this.c.set(this.e / 2.0f, this.e / 2.0f, ((float) width) - (this.e / 2.0f), ((float) width) - (this.e / 2.0f));
        this.b.setStrokeWidth(this.e);
        this.b.setStyle(Style.STROKE);
        this.b.setColor(this.j);
        canvas.drawArc(this.c, 0.0f, 360.0f, false, this.b);
        this.b.setShader(this.t);
        this.b.setStrokeWidth(this.e - this.q);
        canvas.drawArc(this.c, 270.0f, this.f, false, this.b);
        this.b.setShader(null);
        this.b.setTextSize(this.o);
        this.b.setStyle(Style.FILL);
        this.b.setColor(this.k);
        float f = (this.f * 100.0f) / 360.0f;
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 100.0f) {
        }
        if (this.u) {
            valueOf = String.valueOf(this.g);
        } else {
            valueOf = String.valueOf((int) this.g);
        }
        float measureText = this.b.measureText(valueOf);
        this.b.getTextBounds(valueOf, 0, 1, this.d);
        float height2 = (float) this.d.height();
        if (this.n) {
            this.m = height2;
            this.n = false;
        }
        canvas.drawText(valueOf, ((((float) width) - measureText) / 2.0f) - ((float) b.a(getContext(), 10.0f)), (((float) height) + this.m) / 2.0f, this.b);
        this.b.setTextSize(this.p);
        if (this.u) {
            canvas.drawText("km", ((measureText + ((float) width)) / 2.0f) - ((float) b.a(getContext(), 10.0f)), (((float) height) + this.m) / 2.0f, this.b);
        } else {
            canvas.drawText("次", ((measureText + ((float) width)) / 2.0f) - ((float) b.a(getContext(), 10.0f)), (((float) height) + this.m) / 2.0f, this.b);
        }
    }

    public void setDanWeiFlag(boolean z) {
        this.u = z;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int[] iArr = new int[]{Color.parseColor("#FFBF80"), Color.parseColor("#FF8080")};
        if (this.s != null) {
            iArr = this.s;
        }
        this.a.setRotate(-90.0f, (float) (getWidth() / 2), (float) (getHeight() / 2));
        this.t = new SweepGradient((float) (getWidth() / 2), (float) (getHeight() / 2), iArr, null);
        this.t.setLocalMatrix(this.a);
    }

    private void a(float f, float f2) {
        long j = 600;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, f2});
        long abs = (long) (((float) this.i) * (Math.abs(f2 - f) / 360.0f));
        if (abs >= 600) {
            j = abs;
        }
        ofFloat.setDuration(j);
        this.r = this.r == null ? new OvershootInterpolator(0.8f) : this.r;
        ofFloat.setInterpolator(this.r);
        ofFloat.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ ProgressView a;

            {
                this.a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.f = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                this.a.invalidate();
            }
        });
        ofFloat.start();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode != MemoryConstants.GB) {
            size = (int) b(120.0f);
        }
        if (mode2 != MemoryConstants.GB) {
            size2 = size;
        }
        setMeasuredDimension(size, size2);
    }

    private float a(float f) {
        return (getContext().getResources().getDisplayMetrics().scaledDensity * f) + 0.5f;
    }

    private float b(float f) {
        return (getContext().getResources().getDisplayMetrics().density * f) + 0.5f;
    }

    public float getProgress() {
        return this.f / 360.0f;
    }

    public void setAnimateDuration(long j) {
        this.i = j;
    }

    public void setInterpolator(TimeInterpolator timeInterpolator) {
        this.r = timeInterpolator;
    }

    public void setShaderColor(int[] iArr) {
        this.s = iArr;
    }

    public void setPercent(float f) {
        this.f = 360.0f * f;
        a(this.l, this.f);
        this.l = this.h ? 0.0f : this.f;
    }

    public void setIsDrawRestart(boolean z) {
        this.h = z;
    }
}
