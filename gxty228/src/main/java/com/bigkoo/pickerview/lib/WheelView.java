package com.bigkoo.pickerview.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import com.bigkoo.pickerview.R;
import com.bigkoo.pickerview.b.b;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class WheelView extends View {
    int A;
    int B;
    long C;
    int D;
    private GestureDetector E;
    private ScheduledFuture<?> F;
    private String G;
    private int H;
    private int I;
    private float J;
    private int K;
    private int L;
    private int M;
    Context a;
    Handler b;
    b c;
    ScheduledExecutorService d;
    Paint e;
    Paint f;
    Paint g;
    com.bigkoo.pickerview.a.b h;
    int i;
    int j;
    int k;
    float l;
    int m;
    int n;
    int o;
    boolean p;
    float q;
    float r;
    float s;
    int t;
    int u;
    int v;
    int w;
    int x;
    int y;
    int z;

    public enum ACTION {
        CLICK,
        FLING,
        DAGGLE
    }

    public WheelView(Context context) {
        this(context, null);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = Executors.newSingleThreadScheduledExecutor();
        this.x = 11;
        this.I = 0;
        this.J = 0.0f;
        this.C = 0;
        this.K = 17;
        this.L = 0;
        this.M = 0;
        this.m = getResources().getColor(R.color.pickerview_wheelview_textcolor_out);
        this.n = getResources().getColor(R.color.pickerview_wheelview_textcolor_center);
        this.o = getResources().getColor(R.color.pickerview_wheelview_textcolor_divider);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.wheelview, 0, 0);
            this.K = obtainStyledAttributes.getInt(0, 17);
            this.m = obtainStyledAttributes.getColor(1, this.m);
            this.n = obtainStyledAttributes.getColor(2, this.n);
            this.o = obtainStyledAttributes.getColor(3, this.o);
        }
        a(context);
    }

    private void a(Context context) {
        this.a = context;
        this.b = new c(this);
        this.E = new GestureDetector(context, new b(this));
        this.E.setIsLongpressEnabled(false);
        this.p = true;
        this.i = 0;
        this.t = 0;
        this.u = -1;
        c();
        setTextSize(16.0f);
    }

    private void c() {
        this.e = new Paint();
        this.e.setColor(this.m);
        this.e.setAntiAlias(true);
        this.e.setTypeface(Typeface.MONOSPACE);
        this.e.setTextSize((float) this.i);
        this.f = new Paint();
        this.f.setColor(this.n);
        this.f.setAntiAlias(true);
        this.f.setTextScaleX(1.1f);
        this.f.setTypeface(Typeface.MONOSPACE);
        this.f.setTextSize((float) this.i);
        this.g = new Paint();
        this.g.setColor(this.o);
        this.g.setAntiAlias(true);
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
    }

    private void d() {
        if (this.h != null) {
            e();
            this.A = (int) (this.l * ((float) (this.x - 1)));
            this.y = (int) (((double) (this.A * 2)) / 3.141592653589793d);
            this.B = (int) (((double) this.A) / 3.141592653589793d);
            this.z = MeasureSpec.getSize(this.D);
            this.q = (((float) this.y) - this.l) / 2.0f;
            this.r = (((float) this.y) + this.l) / 2.0f;
            this.s = (((float) (this.y + this.k)) / 2.0f) - 6.0f;
            if (this.u == -1) {
                if (this.p) {
                    this.u = (this.h.a() + 1) / 2;
                } else {
                    this.u = 0;
                }
            }
            this.v = this.u;
        }
    }

    private void e() {
        Rect rect = new Rect();
        for (int i = 0; i < this.h.a(); i++) {
            String a = a(this.h.a(i));
            this.f.getTextBounds(a, 0, a.length(), rect);
            int width = rect.width();
            if (width > this.j) {
                this.j = width;
            }
            this.f.getTextBounds("星期", 0, 2, rect);
            width = rect.height();
            if (width > this.k) {
                this.k = width;
            }
        }
        this.l = 1.4f * ((float) this.k);
    }

    void a(ACTION action) {
        a();
        if (action == ACTION.FLING || action == ACTION.DAGGLE) {
            this.I = (int) (((((float) this.t) % this.l) + this.l) % this.l);
            if (((float) this.I) > this.l / 2.0f) {
                this.I = (int) (this.l - ((float) this.I));
            } else {
                this.I = -this.I;
            }
        }
        this.F = this.d.scheduleWithFixedDelay(new e(this, this.I), 0, 10, TimeUnit.MILLISECONDS);
    }

    protected final void a(float f) {
        a();
        this.F = this.d.scheduleWithFixedDelay(new a(this, f), 0, 5, TimeUnit.MILLISECONDS);
    }

    public void a() {
        if (this.F != null && !this.F.isCancelled()) {
            this.F.cancel(true);
            this.F = null;
        }
    }

    public final void setCyclic(boolean z) {
        this.p = z;
    }

    public final void setTextSize(float f) {
        if (f > 0.0f) {
            this.i = (int) (this.a.getResources().getDisplayMetrics().density * f);
            this.e.setTextSize((float) this.i);
            this.f.setTextSize((float) this.i);
        }
    }

    public final void setCurrentItem(int i) {
        this.u = i;
        this.t = 0;
        invalidate();
    }

    public final void setOnItemSelectedListener(b bVar) {
        this.c = bVar;
    }

    public final void setAdapter(com.bigkoo.pickerview.a.b bVar) {
        this.h = bVar;
        d();
        invalidate();
    }

    public final com.bigkoo.pickerview.a.b getAdapter() {
        return this.h;
    }

    public final int getCurrentItem() {
        return this.H;
    }

    protected final void b() {
        if (this.c != null) {
            postDelayed(new d(this), 200);
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.h != null) {
            int i;
            int i2;
            Object[] objArr = new Object[this.x];
            this.w = (int) (((float) this.t) / this.l);
            try {
                this.v = this.u + (this.w % this.h.a());
            } catch (ArithmeticException e) {
                System.out.println("出错了！adapter.getItemsCount() == 0，联动数据不匹配");
            }
            if (this.p) {
                if (this.v < 0) {
                    this.v = this.h.a() + this.v;
                }
                if (this.v > this.h.a() - 1) {
                    this.v -= this.h.a();
                }
            } else {
                if (this.v < 0) {
                    this.v = 0;
                }
                if (this.v > this.h.a() - 1) {
                    this.v = this.h.a() - 1;
                }
            }
            int i3 = (int) (((float) this.t) % this.l);
            for (i = 0; i < this.x; i++) {
                i2 = this.v - ((this.x / 2) - i);
                if (this.p) {
                    if (i2 < 0) {
                        i2 += this.h.a();
                        if (i2 < 0) {
                            i2 = 0;
                        }
                    }
                    if (i2 > this.h.a() - 1) {
                        i2 -= this.h.a();
                        if (i2 > this.h.a() - 1) {
                            i2 = this.h.a() - 1;
                        }
                    }
                    objArr[i] = this.h.a(i2);
                } else if (i2 < 0) {
                    objArr[i] = "";
                } else if (i2 > this.h.a() - 1) {
                    objArr[i] = "";
                } else {
                    objArr[i] = this.h.a(i2);
                }
            }
            canvas.drawLine(0.0f, this.q, (float) this.z, this.q, this.g);
            canvas.drawLine(0.0f, this.r, (float) this.z, this.r, this.g);
            if (this.G != null) {
                canvas.drawText(this.G, ((float) (this.z - a(this.f, this.G))) - 6.0f, this.s, this.f);
            }
            for (i = 0; i < this.x; i++) {
                canvas.save();
                float f = ((float) this.k) * 1.4f;
                double d = (((double) ((((float) i) * f) - ((float) i3))) * 3.141592653589793d) / ((double) this.A);
                float f2 = (float) (90.0d - ((d / 3.141592653589793d) * 180.0d));
                if (f2 >= 90.0f || f2 <= -90.0f) {
                    canvas.restore();
                } else {
                    String a = a(objArr[i]);
                    a(a);
                    b(a);
                    float cos = (float) ((((double) this.B) - (Math.cos(d) * ((double) this.B))) - ((Math.sin(d) * ((double) this.k)) / 2.0d));
                    canvas.translate(0.0f, cos);
                    canvas.scale(1.0f, (float) Math.sin(d));
                    if (cos <= this.q && ((float) this.k) + cos >= this.q) {
                        canvas.save();
                        canvas.clipRect(0.0f, 0.0f, (float) this.z, this.q - cos);
                        canvas.scale(1.0f, ((float) Math.sin(d)) * 0.8f);
                        canvas.drawText(a, (float) this.M, (float) this.k, this.e);
                        canvas.restore();
                        canvas.save();
                        canvas.clipRect(0.0f, this.q - cos, (float) this.z, (float) ((int) f));
                        canvas.scale(1.0f, ((float) Math.sin(d)) * 1.0f);
                        canvas.drawText(a, (float) this.L, ((float) this.k) - 6.0f, this.f);
                        canvas.restore();
                    } else if (cos <= this.r && ((float) this.k) + cos >= this.r) {
                        canvas.save();
                        canvas.clipRect(0.0f, 0.0f, (float) this.z, this.r - cos);
                        canvas.scale(1.0f, ((float) Math.sin(d)) * 1.0f);
                        canvas.drawText(a, (float) this.L, ((float) this.k) - 6.0f, this.f);
                        canvas.restore();
                        canvas.save();
                        canvas.clipRect(0.0f, this.r - cos, (float) this.z, (float) ((int) f));
                        canvas.scale(1.0f, ((float) Math.sin(d)) * 0.8f);
                        canvas.drawText(a, (float) this.M, (float) this.k, this.e);
                        canvas.restore();
                    } else if (cos < this.q || cos + ((float) this.k) > this.r) {
                        canvas.save();
                        canvas.clipRect(0, 0, this.z, (int) f);
                        canvas.scale(1.0f, ((float) Math.sin(d)) * 0.8f);
                        canvas.drawText(a, (float) this.M, (float) this.k, this.e);
                        canvas.restore();
                    } else {
                        canvas.clipRect(0, 0, this.z, (int) f);
                        canvas.drawText(a, (float) this.L, ((float) this.k) - 6.0f, this.f);
                        i2 = this.h.a(objArr[i]);
                        if (i2 != -1) {
                            this.H = i2;
                        }
                    }
                    canvas.restore();
                }
            }
        }
    }

    private String a(Object obj) {
        String obj2 = obj.toString();
        try {
            obj2 = obj.getClass().getMethod("getPickerViewText", new Class[0]).invoke(obj, new Object[0]).toString();
        } catch (NoSuchMethodException e) {
        } catch (InvocationTargetException e2) {
        } catch (IllegalAccessException e3) {
        } catch (Exception e4) {
        }
        return obj2;
    }

    private void a(String str) {
        Rect rect = new Rect();
        this.f.getTextBounds(str, 0, str.length(), rect);
        switch (this.K) {
            case 3:
                this.L = 0;
                return;
            case 5:
                this.L = this.z - rect.width();
                return;
            case 17:
                this.L = (int) (((double) (this.z - rect.width())) * 0.5d);
                return;
            default:
                return;
        }
    }

    private void b(String str) {
        Rect rect = new Rect();
        this.e.getTextBounds(str, 0, str.length(), rect);
        switch (this.K) {
            case 3:
                this.M = 0;
                return;
            case 5:
                this.M = this.z - rect.width();
                return;
            case 17:
                this.M = (int) (((double) (this.z - rect.width())) * 0.5d);
                return;
            default:
                return;
        }
    }

    protected void onMeasure(int i, int i2) {
        this.D = i;
        d();
        setMeasuredDimension(this.z, this.y);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = this.E.onTouchEvent(motionEvent);
        float f;
        switch (motionEvent.getAction()) {
            case 0:
                this.C = System.currentTimeMillis();
                a();
                this.J = motionEvent.getRawY();
                break;
            case 2:
                float rawY = this.J - motionEvent.getRawY();
                this.J = motionEvent.getRawY();
                this.t = (int) (((float) this.t) + rawY);
                if (!this.p) {
                    f = this.l * ((float) (-this.u));
                    float a = ((float) ((this.h.a() - 1) - this.u)) * this.l;
                    if (((double) this.t) - (((double) this.l) * 0.3d) < ((double) f)) {
                        f = ((float) this.t) - rawY;
                    } else if (((double) this.t) + (((double) this.l) * 0.3d) > ((double) a)) {
                        a = ((float) this.t) - rawY;
                    }
                    if (((float) this.t) >= f) {
                        if (((float) this.t) > a) {
                            this.t = (int) a;
                            break;
                        }
                    }
                    this.t = (int) f;
                    break;
                }
                break;
            default:
                if (!onTouchEvent) {
                    f = ((((float) this.t) % this.l) + this.l) % this.l;
                    this.I = (int) ((((float) (((int) (((Math.acos((double) ((((float) this.B) - motionEvent.getY()) / ((float) this.B))) * ((double) this.B)) + ((double) (this.l / 2.0f))) / ((double) this.l))) - (this.x / 2))) * this.l) - f);
                    if (System.currentTimeMillis() - this.C <= 120) {
                        a(ACTION.CLICK);
                        break;
                    }
                    a(ACTION.DAGGLE);
                    break;
                }
                break;
        }
        invalidate();
        return true;
    }

    public int getItemsCount() {
        return this.h != null ? this.h.a() : 0;
    }

    public void setLabel(String str) {
        this.G = str;
    }

    public void setGravity(int i) {
        this.K = i;
    }

    public int a(Paint paint, String str) {
        int i = 0;
        if (str != null && str.length() > 0) {
            int length = str.length();
            float[] fArr = new float[length];
            paint.getTextWidths(str, fArr);
            int i2 = 0;
            while (i2 < length) {
                int ceil = ((int) Math.ceil((double) fArr[i2])) + i;
                i2++;
                i = ceil;
            }
        }
        return i;
    }
}
