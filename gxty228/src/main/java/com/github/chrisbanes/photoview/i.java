package com.github.chrisbanes.photoview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.OverScroller;

/* compiled from: PhotoViewAttacher */
public class i implements OnLayoutChangeListener, OnTouchListener, c {
    private static float a = 3.0f;
    private static float b = 1.75f;
    private static float c = 1.0f;
    private static int d = 200;
    private static int e = 1;
    private h A;
    private b B;
    private int C = 2;
    private float D;
    private boolean E = true;
    private ScaleType F = ScaleType.FIT_CENTER;
    private Interpolator f = new AccelerateDecelerateInterpolator();
    private int g = d;
    private float h = c;
    private float i = b;
    private float j = a;
    private boolean k = true;
    private boolean l = false;
    private ImageView m;
    private GestureDetector n;
    private b o;
    private final Matrix p = new Matrix();
    private final Matrix q = new Matrix();
    private final Matrix r = new Matrix();
    private final RectF s = new RectF();
    private final float[] t = new float[9];
    private d u;
    private f v;
    private e w;
    private OnClickListener x;
    private OnLongClickListener y;
    private g z;

    /* compiled from: PhotoViewAttacher */
    /* renamed from: com.github.chrisbanes.photoview.i$3 */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        static {
            try {
                a[ScaleType.FIT_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ScaleType.FIT_START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ScaleType.FIT_XY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* compiled from: PhotoViewAttacher */
    private class a implements Runnable {
        final /* synthetic */ i a;
        private final float b;
        private final float c;
        private final long d = System.currentTimeMillis();
        private final float e;
        private final float f;

        public a(i iVar, float f, float f2, float f3, float f4) {
            this.a = iVar;
            this.b = f3;
            this.c = f4;
            this.e = f;
            this.f = f2;
        }

        public void run() {
            float a = a();
            this.a.a((this.e + ((this.f - this.e) * a)) / this.a.e(), this.b, this.c);
            if (a < 1.0f) {
                a.a(this.a.m, this);
            }
        }

        private float a() {
            return this.a.f.getInterpolation(Math.min(1.0f, (((float) (System.currentTimeMillis() - this.d)) * 1.0f) / ((float) this.a.g)));
        }
    }

    /* compiled from: PhotoViewAttacher */
    private class b implements Runnable {
        final /* synthetic */ i a;
        private final OverScroller b;
        private int c;
        private int d;

        public b(i iVar, Context context) {
            this.a = iVar;
            this.b = new OverScroller(context);
        }

        public void a() {
            this.b.forceFinished(true);
        }

        public void a(int i, int i2, int i3, int i4) {
            RectF a = this.a.a();
            if (a != null) {
                int round;
                int i5;
                int round2;
                int i6;
                int round3 = Math.round(-a.left);
                if (((float) i) < a.width()) {
                    round = Math.round(a.width() - ((float) i));
                    i5 = 0;
                } else {
                    round = round3;
                    i5 = round3;
                }
                int round4 = Math.round(-a.top);
                if (((float) i2) < a.height()) {
                    round2 = Math.round(a.height() - ((float) i2));
                    i6 = 0;
                } else {
                    round2 = round4;
                    i6 = round4;
                }
                this.c = round3;
                this.d = round4;
                if (round3 != round || round4 != round2) {
                    this.b.fling(round3, round4, i3, i4, i5, round, i6, round2, 0, 0);
                }
            }
        }

        public void run() {
            if (!this.b.isFinished() && this.b.computeScrollOffset()) {
                int currX = this.b.getCurrX();
                int currY = this.b.getCurrY();
                this.a.r.postTranslate((float) (this.c - currX), (float) (this.d - currY));
                this.a.a(this.a.k());
                this.c = currX;
                this.d = currY;
                a.a(this.a.m, this);
            }
        }
    }

    public i(ImageView imageView) {
        this.m = imageView;
        imageView.setOnTouchListener(this);
        imageView.addOnLayoutChangeListener(this);
        if (!imageView.isInEditMode()) {
            this.D = 0.0f;
            this.o = new b(imageView.getContext(), this);
            this.n = new GestureDetector(imageView.getContext(), new SimpleOnGestureListener(this) {
                final /* synthetic */ i a;

                {
                    this.a = r1;
                }

                public void onLongPress(MotionEvent motionEvent) {
                    if (this.a.y != null) {
                        this.a.y.onLongClick(this.a.m);
                    }
                }

                public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                    if (this.a.A == null || this.a.e() > i.c || MotionEventCompat.getPointerCount(motionEvent) > i.e || MotionEventCompat.getPointerCount(motionEvent2) > i.e) {
                        return false;
                    }
                    return this.a.A.a(motionEvent, motionEvent2, f, f2);
                }
            });
            this.n.setOnDoubleTapListener(new OnDoubleTapListener(this) {
                final /* synthetic */ i a;

                {
                    this.a = r1;
                }

                public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                    if (this.a.x != null) {
                        this.a.x.onClick(this.a.m);
                    }
                    RectF a = this.a.a();
                    if (a != null) {
                        float x = motionEvent.getX();
                        float y = motionEvent.getY();
                        if (a.contains(x, y)) {
                            x = (x - a.left) / a.width();
                            float height = (y - a.top) / a.height();
                            if (this.a.v != null) {
                                this.a.v.a(this.a.m, x, height);
                            }
                            return true;
                        } else if (this.a.w != null) {
                            this.a.w.a(this.a.m);
                        }
                    }
                    return false;
                }

                public boolean onDoubleTap(MotionEvent motionEvent) {
                    try {
                        float e = this.a.e();
                        float x = motionEvent.getX();
                        float y = motionEvent.getY();
                        if (e < this.a.c()) {
                            this.a.a(this.a.c(), x, y, true);
                        } else if (e < this.a.c() || e >= this.a.d()) {
                            this.a.a(this.a.b(), x, y, true);
                        } else {
                            this.a.a(this.a.d(), x, y, true);
                        }
                    } catch (ArrayIndexOutOfBoundsException e2) {
                    }
                    return true;
                }

                public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                    return false;
                }
            });
        }
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        this.n.setOnDoubleTapListener(onDoubleTapListener);
    }

    public void setOnScaleChangeListener(g gVar) {
        this.z = gVar;
    }

    public void setOnSingleFlingListener(h hVar) {
        this.A = hVar;
    }

    public RectF a() {
        n();
        return b(k());
    }

    public void a(float f) {
        this.r.setRotate(f % 360.0f);
        m();
    }

    public void b(float f) {
        this.r.postRotate(f % 360.0f);
        m();
    }

    public float b() {
        return this.h;
    }

    public float c() {
        return this.i;
    }

    public float d() {
        return this.j;
    }

    public float e() {
        return (float) Math.sqrt((double) (((float) Math.pow((double) a(this.r, 0), 2.0d)) + ((float) Math.pow((double) a(this.r, 3), 2.0d))));
    }

    public ScaleType f() {
        return this.F;
    }

    public void a(float f, float f2) {
        if (!this.o.a()) {
            this.r.postTranslate(f, f2);
            m();
            ViewParent parent = this.m.getParent();
            if (!this.k || this.o.a() || this.l) {
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
            } else if ((this.C == 2 || ((this.C == 0 && f >= 1.0f) || (this.C == 1 && f <= -1.0f))) && parent != null) {
                parent.requestDisallowInterceptTouchEvent(false);
            }
        }
    }

    public void a(float f, float f2, float f3, float f4) {
        this.B = new b(this, this.m.getContext());
        this.B.a(a(this.m), b(this.m), (int) f3, (int) f4);
        this.m.post(this.B);
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        a(this.m.getDrawable());
    }

    public void a(float f, float f2, float f3) {
        if (e() >= this.j && f >= 1.0f) {
            return;
        }
        if (e() > this.h || f > 1.0f) {
            if (this.z != null) {
                this.z.a(f, f2, f3);
            }
            this.r.postScale(f, f, f2, f3);
            m();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r9, android.view.MotionEvent r10) {
        /*
        r8 = this;
        r6 = 0;
        r7 = 1;
        r0 = r8.E;
        if (r0 == 0) goto L_0x0093;
    L_0x0006:
        r0 = r9;
        r0 = (android.widget.ImageView) r0;
        r0 = com.github.chrisbanes.photoview.j.a(r0);
        if (r0 == 0) goto L_0x0093;
    L_0x000f:
        r0 = r10.getAction();
        switch(r0) {
            case 0: goto L_0x0058;
            case 1: goto L_0x0066;
            case 2: goto L_0x0016;
            case 3: goto L_0x0066;
            default: goto L_0x0016;
        };
    L_0x0016:
        r0 = r6;
    L_0x0017:
        r1 = r8.o;
        if (r1 == 0) goto L_0x004a;
    L_0x001b:
        r0 = r8.o;
        r1 = r0.a();
        r0 = r8.o;
        r3 = r0.b();
        r0 = r8.o;
        r0 = r0.a(r10);
        if (r1 != 0) goto L_0x008f;
    L_0x002f:
        r1 = r8.o;
        r1 = r1.a();
        if (r1 != 0) goto L_0x008f;
    L_0x0037:
        r2 = r7;
    L_0x0038:
        if (r3 != 0) goto L_0x0091;
    L_0x003a:
        r1 = r8.o;
        r1 = r1.b();
        if (r1 != 0) goto L_0x0091;
    L_0x0042:
        r1 = r7;
    L_0x0043:
        if (r2 == 0) goto L_0x0048;
    L_0x0045:
        if (r1 == 0) goto L_0x0048;
    L_0x0047:
        r6 = r7;
    L_0x0048:
        r8.l = r6;
    L_0x004a:
        r1 = r8.n;
        if (r1 == 0) goto L_0x0057;
    L_0x004e:
        r1 = r8.n;
        r1 = r1.onTouchEvent(r10);
        if (r1 == 0) goto L_0x0057;
    L_0x0056:
        r0 = r7;
    L_0x0057:
        return r0;
    L_0x0058:
        r0 = r9.getParent();
        if (r0 == 0) goto L_0x0061;
    L_0x005e:
        r0.requestDisallowInterceptTouchEvent(r7);
    L_0x0061:
        r8.o();
        r0 = r6;
        goto L_0x0017;
    L_0x0066:
        r0 = r8.e();
        r1 = r8.h;
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 >= 0) goto L_0x0016;
    L_0x0070:
        r1 = r8.a();
        if (r1 == 0) goto L_0x0016;
    L_0x0076:
        r0 = new com.github.chrisbanes.photoview.i$a;
        r2 = r8.e();
        r3 = r8.h;
        r4 = r1.centerX();
        r5 = r1.centerY();
        r1 = r8;
        r0.<init>(r1, r2, r3, r4, r5);
        r9.post(r0);
        r0 = r7;
        goto L_0x0017;
    L_0x008f:
        r2 = r6;
        goto L_0x0038;
    L_0x0091:
        r1 = r6;
        goto L_0x0043;
    L_0x0093:
        r0 = r6;
        goto L_0x0057;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.chrisbanes.photoview.i.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public void a(boolean z) {
        this.k = z;
    }

    public void c(float f) {
        j.a(f, this.i, this.j);
        this.h = f;
    }

    public void d(float f) {
        j.a(this.h, f, this.j);
        this.i = f;
    }

    public void e(float f) {
        j.a(this.h, this.i, f);
        this.j = f;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.y = onLongClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.x = onClickListener;
    }

    public void setOnMatrixChangeListener(d dVar) {
        this.u = dVar;
    }

    public void setOnPhotoTapListener(f fVar) {
        this.v = fVar;
    }

    public void setOnOutsidePhotoTapListener(e eVar) {
        this.w = eVar;
    }

    public void f(float f) {
        a(f, false);
    }

    public void a(float f, boolean z) {
        a(f, (float) (this.m.getRight() / 2), (float) (this.m.getBottom() / 2), z);
    }

    public void a(float f, float f2, float f3, boolean z) {
        if (f < this.h || f > this.j) {
            throw new IllegalArgumentException("Scale must be within the range of minScale and maxScale");
        } else if (z) {
            this.m.post(new a(this, e(), f, f2, f3));
        } else {
            this.r.setScale(f, f, f2, f3);
            m();
        }
    }

    public void a(ScaleType scaleType) {
        if (j.a(scaleType) && scaleType != this.F) {
            this.F = scaleType;
            g();
        }
    }

    public void b(boolean z) {
        this.E = z;
        g();
    }

    public void g() {
        if (this.E) {
            a(this.m.getDrawable());
        } else {
            l();
        }
    }

    private Matrix k() {
        this.q.set(this.p);
        this.q.postConcat(this.r);
        return this.q;
    }

    public Matrix h() {
        return this.q;
    }

    public void a(int i) {
        this.g = i;
    }

    private float a(Matrix matrix, int i) {
        matrix.getValues(this.t);
        return this.t[i];
    }

    private void l() {
        this.r.reset();
        b(this.D);
        a(k());
        n();
    }

    private void a(Matrix matrix) {
        this.m.setImageMatrix(matrix);
        if (this.u != null) {
            RectF b = b(matrix);
            if (b != null) {
                this.u.a(b);
            }
        }
    }

    private void m() {
        if (n()) {
            a(k());
        }
    }

    private RectF b(Matrix matrix) {
        Drawable drawable = this.m.getDrawable();
        if (drawable == null) {
            return null;
        }
        this.s.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        matrix.mapRect(this.s);
        return this.s;
    }

    private void a(Drawable drawable) {
        if (drawable != null) {
            float a = (float) a(this.m);
            float b = (float) b(this.m);
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            this.p.reset();
            float f = a / ((float) intrinsicWidth);
            float f2 = b / ((float) intrinsicHeight);
            if (this.F != ScaleType.CENTER) {
                if (this.F != ScaleType.CENTER_CROP) {
                    if (this.F != ScaleType.CENTER_INSIDE) {
                        RectF rectF = new RectF(0.0f, 0.0f, (float) intrinsicWidth, (float) intrinsicHeight);
                        RectF rectF2 = new RectF(0.0f, 0.0f, a, b);
                        if (((int) this.D) % 180 != 0) {
                            rectF = new RectF(0.0f, 0.0f, (float) intrinsicHeight, (float) intrinsicWidth);
                        }
                        switch (AnonymousClass3.a[this.F.ordinal()]) {
                            case 1:
                                this.p.setRectToRect(rectF, rectF2, ScaleToFit.CENTER);
                                break;
                            case 2:
                                this.p.setRectToRect(rectF, rectF2, ScaleToFit.START);
                                break;
                            case 3:
                                this.p.setRectToRect(rectF, rectF2, ScaleToFit.END);
                                break;
                            case 4:
                                this.p.setRectToRect(rectF, rectF2, ScaleToFit.FILL);
                                break;
                            default:
                                break;
                        }
                    }
                    f = Math.min(1.0f, Math.min(f, f2));
                    this.p.postScale(f, f);
                    this.p.postTranslate((a - (((float) intrinsicWidth) * f)) / 2.0f, (b - (f * ((float) intrinsicHeight))) / 2.0f);
                } else {
                    f = Math.max(f, f2);
                    this.p.postScale(f, f);
                    this.p.postTranslate((a - (((float) intrinsicWidth) * f)) / 2.0f, (b - (f * ((float) intrinsicHeight))) / 2.0f);
                }
            } else {
                this.p.postTranslate((a - ((float) intrinsicWidth)) / 2.0f, (b - ((float) intrinsicHeight)) / 2.0f);
            }
            l();
        }
    }

    private boolean n() {
        float f = 0.0f;
        RectF b = b(k());
        if (b == null) {
            return false;
        }
        float height = b.height();
        float width = b.width();
        int b2 = b(this.m);
        if (height <= ((float) b2)) {
            switch (AnonymousClass3.a[this.F.ordinal()]) {
                case 2:
                    height = -b.top;
                    break;
                case 3:
                    height = (((float) b2) - height) - b.top;
                    break;
                default:
                    height = ((((float) b2) - height) / 2.0f) - b.top;
                    break;
            }
        } else if (b.top > 0.0f) {
            height = -b.top;
        } else if (b.bottom < ((float) b2)) {
            height = ((float) b2) - b.bottom;
        } else {
            height = 0.0f;
        }
        b2 = a(this.m);
        if (width <= ((float) b2)) {
            switch (AnonymousClass3.a[this.F.ordinal()]) {
                case 2:
                    f = -b.left;
                    break;
                case 3:
                    f = (((float) b2) - width) - b.left;
                    break;
                default:
                    f = ((((float) b2) - width) / 2.0f) - b.left;
                    break;
            }
            this.C = 2;
        } else if (b.left > 0.0f) {
            this.C = 0;
            f = -b.left;
        } else if (b.right < ((float) b2)) {
            f = ((float) b2) - b.right;
            this.C = 1;
        } else {
            this.C = -1;
        }
        this.r.postTranslate(f, height);
        return true;
    }

    private int a(ImageView imageView) {
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    private int b(ImageView imageView) {
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }

    private void o() {
        if (this.B != null) {
            this.B.a();
            this.B = null;
        }
    }
}
