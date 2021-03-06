package de.hdodenhof.circleimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class CircleImageView extends ImageView {
    private static final ScaleType a = ScaleType.CENTER_CROP;
    private static final Config b = Config.ARGB_8888;
    private final RectF c;
    private final RectF d;
    private final Matrix e;
    private final Paint f;
    private final Paint g;
    private final Paint h;
    private int i;
    private int j;
    private int k;
    private Bitmap l;
    private BitmapShader m;
    private int n;
    private int o;
    private float p;
    private float q;
    private ColorFilter r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;

    public CircleImageView(Context context) {
        super(context);
        this.c = new RectF();
        this.d = new RectF();
        this.e = new Matrix();
        this.f = new Paint();
        this.g = new Paint();
        this.h = new Paint();
        this.i = ViewCompat.MEASURED_STATE_MASK;
        this.j = 0;
        this.k = 0;
        a();
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new RectF();
        this.d = new RectF();
        this.e = new Matrix();
        this.f = new Paint();
        this.g = new Paint();
        this.h = new Paint();
        this.i = ViewCompat.MEASURED_STATE_MASK;
        this.j = 0;
        this.k = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleImageView, i, 0);
        this.j = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CircleImageView_civ_border_width, 0);
        this.i = obtainStyledAttributes.getColor(R.styleable.CircleImageView_civ_border_color, ViewCompat.MEASURED_STATE_MASK);
        this.u = obtainStyledAttributes.getBoolean(R.styleable.CircleImageView_civ_border_overlay, false);
        if (obtainStyledAttributes.hasValue(R.styleable.CircleImageView_civ_circle_background_color)) {
            this.k = obtainStyledAttributes.getColor(R.styleable.CircleImageView_civ_circle_background_color, 0);
        } else if (obtainStyledAttributes.hasValue(R.styleable.CircleImageView_civ_fill_color)) {
            this.k = obtainStyledAttributes.getColor(R.styleable.CircleImageView_civ_fill_color, 0);
        }
        obtainStyledAttributes.recycle();
        a();
    }

    private void a() {
        super.setScaleType(a);
        this.s = true;
        if (VERSION.SDK_INT >= 21) {
            setOutlineProvider(new a(this, null));
        }
        if (this.t) {
            d();
            this.t = false;
        }
    }

    public ScaleType getScaleType() {
        return a;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType != a) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", new Object[]{scaleType}));
        }
    }

    public void setAdjustViewBounds(boolean z) {
        if (z) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.v) {
            super.onDraw(canvas);
        } else if (this.l != null) {
            if (this.k != 0) {
                canvas.drawCircle(this.c.centerX(), this.c.centerY(), this.p, this.h);
            }
            canvas.drawCircle(this.c.centerX(), this.c.centerY(), this.p, this.f);
            if (this.j > 0) {
                canvas.drawCircle(this.d.centerX(), this.d.centerY(), this.q, this.g);
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        d();
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        d();
    }

    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        super.setPaddingRelative(i, i2, i3, i4);
        d();
    }

    public int getBorderColor() {
        return this.i;
    }

    public void setBorderColor(@ColorInt int i) {
        if (i != this.i) {
            this.i = i;
            this.g.setColor(this.i);
            invalidate();
        }
    }

    @Deprecated
    public void setBorderColorResource(@ColorRes int i) {
        setBorderColor(getContext().getResources().getColor(i));
    }

    public int getCircleBackgroundColor() {
        return this.k;
    }

    public void setCircleBackgroundColor(@ColorInt int i) {
        if (i != this.k) {
            this.k = i;
            this.h.setColor(i);
            invalidate();
        }
    }

    public void setCircleBackgroundColorResource(@ColorRes int i) {
        setCircleBackgroundColor(getContext().getResources().getColor(i));
    }

    @Deprecated
    public int getFillColor() {
        return getCircleBackgroundColor();
    }

    @Deprecated
    public void setFillColor(@ColorInt int i) {
        setCircleBackgroundColor(i);
    }

    @Deprecated
    public void setFillColorResource(@ColorRes int i) {
        setCircleBackgroundColorResource(i);
    }

    public int getBorderWidth() {
        return this.j;
    }

    public void setBorderWidth(int i) {
        if (i != this.j) {
            this.j = i;
            d();
        }
    }

    public void setBorderOverlay(boolean z) {
        if (z != this.u) {
            this.u = z;
            d();
        }
    }

    public void setDisableCircularTransformation(boolean z) {
        if (this.v != z) {
            this.v = z;
            c();
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        c();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        c();
    }

    public void setImageResource(@DrawableRes int i) {
        super.setImageResource(i);
        c();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        c();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter != this.r) {
            this.r = colorFilter;
            b();
            invalidate();
        }
    }

    public ColorFilter getColorFilter() {
        return this.r;
    }

    private void b() {
        if (this.f != null) {
            this.f.setColorFilter(this.r);
        }
    }

    private Bitmap a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap;
            if (drawable instanceof ColorDrawable) {
                createBitmap = Bitmap.createBitmap(2, 2, b);
            } else {
                createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), b);
            }
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void c() {
        if (this.v) {
            this.l = null;
        } else {
            this.l = a(getDrawable());
        }
        d();
    }

    private void d() {
        if (!this.s) {
            this.t = true;
        } else if (getWidth() != 0 || getHeight() != 0) {
            if (this.l == null) {
                invalidate();
                return;
            }
            this.m = new BitmapShader(this.l, TileMode.CLAMP, TileMode.CLAMP);
            this.f.setAntiAlias(true);
            this.f.setShader(this.m);
            this.g.setStyle(Style.STROKE);
            this.g.setAntiAlias(true);
            this.g.setColor(this.i);
            this.g.setStrokeWidth((float) this.j);
            this.h.setStyle(Style.FILL);
            this.h.setAntiAlias(true);
            this.h.setColor(this.k);
            this.o = this.l.getHeight();
            this.n = this.l.getWidth();
            this.d.set(e());
            this.q = Math.min((this.d.height() - ((float) this.j)) / 2.0f, (this.d.width() - ((float) this.j)) / 2.0f);
            this.c.set(this.d);
            if (!this.u && this.j > 0) {
                this.c.inset(((float) this.j) - 1.0f, ((float) this.j) - 1.0f);
            }
            this.p = Math.min(this.c.height() / 2.0f, this.c.width() / 2.0f);
            b();
            f();
            invalidate();
        }
    }

    private RectF e() {
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int min = Math.min(width, height);
        float paddingLeft = (((float) (width - min)) / 2.0f) + ((float) getPaddingLeft());
        float paddingTop = (((float) (height - min)) / 2.0f) + ((float) getPaddingTop());
        return new RectF(paddingLeft, paddingTop, ((float) min) + paddingLeft, ((float) min) + paddingTop);
    }

    private void f() {
        float height;
        float width;
        float f = 0.0f;
        this.e.set(null);
        if (((float) this.n) * this.c.height() > this.c.width() * ((float) this.o)) {
            height = this.c.height() / ((float) this.o);
            width = (this.c.width() - (((float) this.n) * height)) * 0.5f;
        } else {
            height = this.c.width() / ((float) this.n);
            width = 0.0f;
            f = (this.c.height() - (((float) this.o) * height)) * 0.5f;
        }
        this.e.setScale(height, height);
        this.e.postTranslate(((float) ((int) (width + 0.5f))) + this.c.left, ((float) ((int) (f + 0.5f))) + this.c.top);
        this.m.setLocalMatrix(this.e);
    }
}
