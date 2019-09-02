package com.coorchice.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

public class SuperTextView extends TextView {
    private RectF A;
    private RectF B;
    private float[] C;
    private float[] D;
    private float[] E;
    private float[] F;
    private float[] G;
    private float[] H;
    private float I;
    private float J;
    private float K;
    private float L;
    private boolean M;
    private boolean N;
    private int O;
    private Runnable P;
    private int Q;
    private int R;
    private int S;
    private LinearGradient T;
    private boolean U;
    private float a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private int f;
    private float g;
    private int h;
    private int i;
    private boolean j;
    private Paint k;
    private int l;
    private int m;
    private Drawable n;
    private float o;
    private boolean p;
    private Adjuster q;
    private boolean r;
    private int s;
    private int t;
    private float u;
    private boolean v;
    private boolean w;
    private Thread x;
    private Path y;
    private Path z;

    public static abstract class Adjuster {
        private Opportunity a = Opportunity.BEFORE_TEXT;

        public enum Opportunity {
            BEFORE_DRAWABLE,
            BEFORE_TEXT,
            AT_LAST
        }

        protected abstract void a(SuperTextView superTextView, Canvas canvas);

        public boolean a(SuperTextView superTextView, MotionEvent motionEvent) {
            return false;
        }

        public Opportunity a() {
            return this.a;
        }
    }

    public enum DrawableMode {
        LEFT(0),
        TOP(1),
        RIGHT(2),
        BOTTOM(3),
        CENTER(4),
        FILL(5),
        LEFT_TOP(6),
        RIGHT_TOP(7),
        LEFT_BOTTOM(8),
        RIGHT_BOTTOM(9);
        
        public int code;

        private DrawableMode(int i) {
            this.code = i;
        }

        public static DrawableMode valueOf(int i) {
            for (DrawableMode drawableMode : values()) {
                if (drawableMode.code == i) {
                    return drawableMode;
                }
            }
            return CENTER;
        }
    }

    public enum ShaderMode {
        TOP_TO_BOTTOM(0),
        BOTTOM_TO_TOP(1),
        LEFT_TO_RIGHT(2),
        RIGHT_TO_LEFT(3);
        
        public int code;

        private ShaderMode(int i) {
            this.code = i;
        }

        public static ShaderMode valueOf(int i) {
            for (ShaderMode shaderMode : values()) {
                if (shaderMode.code == i) {
                    return shaderMode;
                }
            }
            return TOP_TO_BOTTOM;
        }
    }

    public static class a extends Adjuster {
        public void a(SuperTextView superTextView, Canvas canvas) {
            float width = ((float) superTextView.getWidth()) / (116.28f * superTextView.getResources().getDisplayMetrics().density);
            float[] fArr = new float[]{37.21f, 37.21f, 24.81f, 27.9f, 24.81f, 22.36f, 18.6f, 18.6f};
            switch (superTextView.length()) {
                case 1:
                    superTextView.setTextSize(fArr[0] * width);
                    return;
                case 2:
                    superTextView.setTextSize(fArr[1] * width);
                    return;
                case 3:
                    superTextView.setTextSize(fArr[2] * width);
                    return;
                case 4:
                    superTextView.setTextSize(fArr[3] * width);
                    return;
                case 5:
                case 6:
                    superTextView.setTextSize(fArr[4] * width);
                    return;
                case 7:
                case 8:
                case 9:
                    superTextView.setTextSize(fArr[5] * width);
                    return;
                case 10:
                case 11:
                case 12:
                    superTextView.setTextSize(fArr[6] * width);
                    return;
                case 13:
                case 14:
                case 15:
                case 16:
                    superTextView.setTextSize(fArr[7] * width);
                    return;
                default:
                    return;
            }
        }
    }

    public SuperTextView(Context context) {
        super(context);
        this.v = false;
        this.w = false;
        this.C = new float[2];
        this.D = new float[2];
        this.E = new float[2];
        this.F = new float[2];
        this.G = new float[8];
        this.H = new float[4];
        this.O = 60;
        a(null);
    }

    public SuperTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.v = false;
        this.w = false;
        this.C = new float[2];
        this.D = new float[2];
        this.E = new float[2];
        this.F = new float[2];
        this.G = new float[8];
        this.H = new float[4];
        this.O = 60;
        a(attributeSet);
    }

    public SuperTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.v = false;
        this.w = false;
        this.C = new float[2];
        this.D = new float[2];
        this.E = new float[2];
        this.F = new float[2];
        this.G = new float[8];
        this.H = new float[4];
        this.O = 60;
        a(attributeSet);
    }

    @TargetApi(21)
    public SuperTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.v = false;
        this.w = false;
        this.C = new float[2];
        this.D = new float[2];
        this.E = new float[2];
        this.F = new float[2];
        this.G = new float[8];
        this.H = new float[4];
        this.O = 60;
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        this.o = getContext().getResources().getDisplayMetrics().density;
        b(attributeSet);
        this.k = new Paint();
        d();
    }

    private void b(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.SuperTextView);
            this.a = obtainStyledAttributes.getDimension(R.styleable.SuperTextView_corner, 0.0f);
            this.b = obtainStyledAttributes.getBoolean(R.styleable.SuperTextView_left_top_corner, false);
            this.c = obtainStyledAttributes.getBoolean(R.styleable.SuperTextView_right_top_corner, false);
            this.d = obtainStyledAttributes.getBoolean(R.styleable.SuperTextView_left_bottom_corner, false);
            this.e = obtainStyledAttributes.getBoolean(R.styleable.SuperTextView_right_bottom_corner, false);
            this.f = obtainStyledAttributes.getColor(R.styleable.SuperTextView_solid, 0);
            this.g = obtainStyledAttributes.getDimension(R.styleable.SuperTextView_stroke_width, 0.0f);
            this.h = obtainStyledAttributes.getColor(R.styleable.SuperTextView_stroke_color, ViewCompat.MEASURED_STATE_MASK);
            this.n = obtainStyledAttributes.getDrawable(R.styleable.SuperTextView_state_drawable);
            this.I = obtainStyledAttributes.getDimension(R.styleable.SuperTextView_state_drawable_width, 0.0f);
            this.J = obtainStyledAttributes.getDimension(R.styleable.SuperTextView_state_drawable_height, 0.0f);
            this.K = obtainStyledAttributes.getDimension(R.styleable.SuperTextView_state_drawable_padding_left, 0.0f);
            this.L = obtainStyledAttributes.getDimension(R.styleable.SuperTextView_state_drawable_padding_top, 0.0f);
            this.j = obtainStyledAttributes.getBoolean(R.styleable.SuperTextView_isShowState, false);
            this.i = obtainStyledAttributes.getInteger(R.styleable.SuperTextView_state_drawable_mode, 4);
            this.r = obtainStyledAttributes.getBoolean(R.styleable.SuperTextView_text_stroke, false);
            this.s = obtainStyledAttributes.getColor(R.styleable.SuperTextView_text_stroke_color, ViewCompat.MEASURED_STATE_MASK);
            this.t = obtainStyledAttributes.getColor(R.styleable.SuperTextView_text_fill_color, ViewCompat.MEASURED_STATE_MASK);
            this.u = obtainStyledAttributes.getDimension(R.styleable.SuperTextView_text_stroke_width, 0.0f);
            this.p = obtainStyledAttributes.getBoolean(R.styleable.SuperTextView_autoAdjust, false);
            this.Q = obtainStyledAttributes.getColor(R.styleable.SuperTextView_shaderStartColor, 0);
            this.R = obtainStyledAttributes.getColor(R.styleable.SuperTextView_shaderEndColor, 0);
            this.S = obtainStyledAttributes.getInteger(R.styleable.SuperTextView_shaderMode, 0);
            this.U = obtainStyledAttributes.getBoolean(R.styleable.SuperTextView_shaderEnable, false);
            obtainStyledAttributes.recycle();
        }
    }

    private void d() {
        this.k.reset();
        this.k.setAntiAlias(true);
        this.k.setDither(true);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    protected void onDraw(Canvas canvas) {
        this.l = getWidth();
        this.m = getHeight();
        a(canvas);
        b(canvas);
        a(canvas, Opportunity.BEFORE_DRAWABLE);
        c(canvas);
        a(canvas, Opportunity.BEFORE_TEXT);
        if (this.r) {
            getPaint().setStyle(Style.STROKE);
            setTextColor(this.s);
            getPaint().setFakeBoldText(true);
            getPaint().setStrokeWidth(this.u);
            super.onDraw(canvas);
            getPaint().setStyle(Style.FILL);
            getPaint().setFakeBoldText(false);
            setTextColor(this.t);
        }
        super.onDraw(canvas);
        a(canvas, Opportunity.AT_LAST);
    }

    private void a(Canvas canvas) {
        if (this.g > 0.0f) {
            if (this.y == null) {
                this.y = new Path();
            } else {
                this.y.reset();
            }
            if (this.A == null) {
                this.A = new RectF();
            } else {
                this.A.setEmpty();
            }
            this.A.set(this.g / 2.0f, this.g / 2.0f, ((float) this.l) - (this.g / 2.0f), ((float) this.m) - (this.g / 2.0f));
            a(this.a);
            this.y.addRoundRect(this.A, this.G, Direction.CW);
            d();
            this.k.setStyle(Style.STROKE);
            this.k.setColor(this.h);
            this.k.setStrokeWidth(this.g);
            canvas.drawPath(this.y, this.k);
        }
    }

    private void b(Canvas canvas) {
        if (this.z == null) {
            this.z = new Path();
        } else {
            this.z.reset();
        }
        if (this.B == null) {
            this.B = new RectF();
        } else {
            this.B.setEmpty();
        }
        this.B.set(this.g, this.g, ((float) this.l) - this.g, ((float) this.m) - this.g);
        a(this.a - (this.g / 2.0f));
        this.z.addRoundRect(this.B, this.G, Direction.CW);
        d();
        this.k.setStyle(Style.FILL);
        if (this.U) {
            a(this.k);
        } else {
            this.k.setColor(this.f);
        }
        canvas.drawPath(this.z, this.k);
    }

    private float[] a(float f) {
        this.C[0] = 0.0f;
        this.C[1] = 0.0f;
        this.D[0] = 0.0f;
        this.D[1] = 0.0f;
        this.E[0] = 0.0f;
        this.E[1] = 0.0f;
        this.F[0] = 0.0f;
        this.F[1] = 0.0f;
        if (this.b || this.c || this.d || this.e) {
            if (this.b) {
                this.C[0] = f;
                this.C[1] = f;
            }
            if (this.c) {
                this.D[0] = f;
                this.D[1] = f;
            }
            if (this.d) {
                this.E[0] = f;
                this.E[1] = f;
            }
            if (this.e) {
                this.F[0] = f;
                this.F[1] = f;
            }
        } else {
            this.C[0] = f;
            this.C[1] = f;
            this.D[0] = f;
            this.D[1] = f;
            this.E[0] = f;
            this.E[1] = f;
            this.F[0] = f;
            this.F[1] = f;
        }
        this.G[0] = this.C[0];
        this.G[1] = this.C[1];
        this.G[2] = this.D[0];
        this.G[3] = this.D[1];
        this.G[4] = this.F[0];
        this.G[5] = this.F[1];
        this.G[6] = this.E[0];
        this.G[7] = this.E[1];
        return this.G;
    }

    private void a(Paint paint) {
        if (this.T == null) {
            e();
        }
        paint.setShader(this.T);
    }

    private boolean e() {
        if (this.Q == 0 || this.R == 0) {
            return false;
        }
        float f;
        float f2;
        int i = this.Q;
        int i2 = this.R;
        switch (ShaderMode.valueOf(this.S)) {
            case TOP_TO_BOTTOM:
                f = (float) this.m;
                f2 = 0.0f;
                break;
            case BOTTOM_TO_TOP:
                f = (float) this.m;
                i = this.R;
                i2 = this.Q;
                f2 = 0.0f;
                break;
            case LEFT_TO_RIGHT:
                f2 = (float) this.l;
                f = 0.0f;
                break;
            case RIGHT_TO_LEFT:
                f2 = (float) this.l;
                i = this.R;
                i2 = this.Q;
                f = 0.0f;
                break;
            default:
                f = 0.0f;
                f2 = 0.0f;
                break;
        }
        this.T = new LinearGradient(0.0f, 0.0f, f2, f, i, i2, TileMode.CLAMP);
        return true;
    }

    private void c(Canvas canvas) {
        if (this.n != null && this.j) {
            getDrawableBounds();
            this.n.setBounds((int) this.H[0], (int) this.H[1], (int) this.H[2], (int) this.H[3]);
            this.n.draw(canvas);
        }
    }

    private float[] getDrawableBounds() {
        for (int i = 0; i < this.H.length; i++) {
            this.H[i] = 0.0f;
        }
        this.I = this.I == 0.0f ? ((float) this.l) / 2.0f : this.I;
        this.J = this.J == 0.0f ? ((float) this.m) / 2.0f : this.J;
        switch (DrawableMode.valueOf(this.i)) {
            case LEFT:
                this.H[0] = this.K + 0.0f;
                this.H[1] = ((((float) this.m) / 2.0f) - (this.J / 2.0f)) + this.L;
                this.H[2] = this.H[0] + this.I;
                this.H[3] = this.H[1] + this.J;
                break;
            case TOP:
                this.H[0] = ((((float) this.l) / 2.0f) - (this.I / 2.0f)) + this.K;
                this.H[1] = this.L + 0.0f;
                this.H[2] = this.H[0] + this.I;
                this.H[3] = this.H[1] + this.J;
                break;
            case RIGHT:
                this.H[0] = (((float) this.l) - this.I) + this.K;
                this.H[1] = (((float) (this.m / 2)) - (this.J / 2.0f)) + this.L;
                this.H[2] = this.H[0] + this.I;
                this.H[3] = this.H[1] + this.J;
                break;
            case BOTTOM:
                this.H[0] = ((((float) this.l) / 2.0f) - (this.I / 2.0f)) + this.K;
                this.H[1] = (((float) this.m) - this.J) + this.L;
                this.H[2] = this.H[0] + this.I;
                this.H[3] = this.H[1] + this.J;
                break;
            case CENTER:
                this.H[0] = ((((float) this.l) / 2.0f) - (this.I / 2.0f)) + this.K;
                this.H[1] = (((float) (this.m / 2)) - (this.J / 2.0f)) + this.L;
                this.H[2] = this.H[0] + this.I;
                this.H[3] = this.H[1] + this.J;
                break;
            case FILL:
                this.H[0] = 0.0f;
                this.H[1] = 0.0f;
                this.H[2] = (float) this.l;
                this.H[3] = (float) this.m;
                break;
            case LEFT_TOP:
                this.H[0] = this.K + 0.0f;
                this.H[1] = this.L + 0.0f;
                this.H[2] = this.H[0] + this.I;
                this.H[3] = this.H[1] + this.J;
                break;
            case RIGHT_TOP:
                this.H[0] = (((float) this.l) - this.I) + this.K;
                this.H[1] = this.L + 0.0f;
                this.H[2] = this.H[0] + this.I;
                this.H[3] = this.H[1] + this.J;
                break;
            case LEFT_BOTTOM:
                this.H[0] = this.K + 0.0f;
                this.H[1] = (((float) this.m) - this.J) + this.L;
                this.H[2] = this.H[0] + this.I;
                this.H[3] = this.H[1] + this.J;
                break;
            case RIGHT_BOTTOM:
                this.H[0] = (((float) this.l) - this.I) + this.K;
                this.H[1] = (((float) this.m) - this.J) + this.L;
                this.H[2] = this.H[0] + this.I;
                this.H[3] = this.H[1] + this.J;
                break;
        }
        return this.H;
    }

    private void a(Canvas canvas, Opportunity opportunity) {
        if (!this.p) {
            return;
        }
        if (this.q == null) {
            a(new a());
        } else if (opportunity == this.q.a()) {
            this.q.a(this, canvas);
        }
    }

    public float getCorner() {
        return this.a;
    }

    public int getSolid() {
        return this.f;
    }

    public float getStrokeWidth() {
        return this.g;
    }

    public int getStrokeColor() {
        return this.h;
    }

    public SuperTextView a(Adjuster adjuster) {
        this.q = adjuster;
        postInvalidate();
        return this;
    }

    public Adjuster getAdjuster() {
        return this.q;
    }

    public int getTextStrokeColor() {
        return this.s;
    }

    public int getTextFillColor() {
        return this.t;
    }

    public float getTextStrokeWidth() {
        return this.u;
    }

    public boolean a() {
        return this.p;
    }

    public Drawable getDrawable() {
        return this.n;
    }

    public int getStateDrawableMode() {
        return this.i;
    }

    public float getDrawableWidth() {
        return this.I;
    }

    public float getDrawableHeight() {
        return this.J;
    }

    public float getDrawablePaddingLeft() {
        return this.K;
    }

    public float getDrawablePaddingTop() {
        return this.L;
    }

    public int getShaderStartColor() {
        return this.Q;
    }

    public int getShaderEndColor() {
        return this.R;
    }

    public int getShaderMode() {
        return this.S;
    }

    public int getFrameRate() {
        return this.O;
    }

    public void b() {
        f();
        this.w = true;
        this.v = false;
        if (this.x == null) {
            this.w = true;
            this.v = true;
            this.x = new Thread(new Runnable(this) {
                final /* synthetic */ SuperTextView a;

                {
                    this.a = r1;
                }

                public void run() {
                    while (this.a.v) {
                        synchronized (this.a.P) {
                            this.a.post(this.a.P);
                        }
                        try {
                            Thread.sleep((long) (1000 / this.a.O));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            this.a.v = false;
                        }
                    }
                    this.a.x = null;
                    if (this.a.w) {
                        this.a.b();
                    }
                }
            });
            this.x.start();
        }
    }

    private void f() {
        if (this.P == null) {
            this.P = new Runnable(this) {
                final /* synthetic */ SuperTextView a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.postInvalidate();
                }
            };
        }
    }

    public void c() {
        this.v = false;
        this.w = false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.q == null || !this.q.a(this, motionEvent) || !a()) {
            return super.onTouchEvent(motionEvent);
        }
        super.onTouchEvent(motionEvent);
        return true;
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i != 0) {
            this.M = this.v;
            this.N = this.w;
            c();
        } else if (this.M && this.N) {
            b();
        }
    }

    protected void onDetachedFromWindow() {
        c();
        super.onDetachedFromWindow();
    }
}
