package com.example.gita.gxty.weiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.example.gita.gxty.R;

public class CircleProgressButton extends View {
    private Bitmap A;
    private Handler B;
    private Context a;
    private Paint b;
    private int c;
    private int d;
    private float e;
    private int f;
    private int g;
    private float h;
    private float i;
    private float j;
    private int k;
    private float l;
    private float m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private float r;
    private int s;
    private b t;
    private int u;
    private int v;
    private String w;
    private a x;
    private int y;
    private boolean z;

    public interface b {
        void a();

        void b();

        void c();

        void d();

        void e();
    }

    public class a {
        final /* synthetic */ CircleProgressButton a;
        private final float b;
        private float c = 301.775f;

        public a(CircleProgressButton circleProgressButton, float f) {
            this.a = circleProgressButton;
            this.b = f;
        }
    }

    public float getLongTouchInterval() {
        return this.r;
    }

    public CircleProgressButton(Context context) {
        this(context, null);
    }

    public CircleProgressButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleProgressButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.s = 5;
        this.y = 0;
        this.z = true;
        this.A = null;
        this.B = new Handler(this) {
            final /* synthetic */ CircleProgressButton a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                boolean z = true;
                boolean z2 = false;
                CircleProgressButton circleProgressButton;
                switch (message.what) {
                    case 0:
                        circleProgressButton = this.a;
                        if (this.a.e != 360.0f) {
                            z = false;
                        }
                        circleProgressButton.n = z;
                        if (this.a.n) {
                            if (this.a.t != null) {
                                this.a.t.a();
                            }
                            removeMessages(0);
                            return;
                        }
                        this.a.e = this.a.e + ((float) this.a.s);
                        this.a.invalidate();
                        sendEmptyMessageDelayed(0, 1);
                        return;
                    case 1:
                        circleProgressButton = this.a;
                        if (this.a.e == 0.0f) {
                            z2 = true;
                        }
                        circleProgressButton.o = z2;
                        if (this.a.o) {
                            if (this.a.t != null) {
                                this.a.t.c();
                            }
                            removeMessages(1);
                            return;
                        }
                        this.a.e = this.a.e - ((float) this.a.s);
                        this.a.invalidate();
                        sendEmptyMessageDelayed(1, 1);
                        return;
                    case 2:
                        circleProgressButton = this.a;
                        if (this.a.i - this.a.j > 0.0f) {
                            z = false;
                        }
                        circleProgressButton.p = z;
                        if (this.a.p) {
                            removeMessages(2);
                            return;
                        }
                        this.a.j = this.a.h + this.a.m;
                        this.a.invalidate();
                        sendEmptyMessageDelayed(2, 1);
                        return;
                    case 3:
                        circleProgressButton = this.a;
                        if (this.a.j > 0.0f) {
                            z = false;
                        }
                        circleProgressButton.q = z;
                        if (this.a.q) {
                            removeMessages(3);
                            return;
                        }
                        this.a.j = 0.0f;
                        this.a.invalidate();
                        sendEmptyMessageDelayed(3, 1);
                        return;
                    default:
                        return;
                }
            }
        };
        this.a = context;
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(attributeSet, R.styleable.CircleProgressButton);
        this.f = obtainStyledAttributes.getColor(0, -1);
        this.g = obtainStyledAttributes.getColor(1, -16776961);
        this.h = obtainStyledAttributes.getDimension(2, (float) a(this.a, 1.0f));
        this.k = obtainStyledAttributes.getColor(3, ViewCompat.MEASURED_STATE_MASK);
        this.l = obtainStyledAttributes.getDimension(4, (float) b(this.a, 1.0f));
        this.m = obtainStyledAttributes.getDimension(5, (float) b(this.a, 0.0f));
        obtainStyledAttributes.recycle();
        a();
    }

    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static int b(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().scaledDensity * f) + 0.5f);
    }

    public void setText(String str) {
        this.z = true;
        this.w = str;
        invalidate();
    }

    public void setImg(int i) {
        if (i >= 0) {
            this.z = false;
            if (this.A != null) {
                this.A.recycle();
            }
            this.A = BitmapFactory.decodeResource(getResources(), i);
            invalidate();
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredWidth <= measuredHeight) {
            measuredHeight = measuredWidth;
        }
        this.u = measuredHeight;
        this.v = (this.u / 2) - 15;
        setMeasuredDimension(this.u, this.u);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (this.t != null) {
                    this.t.e();
                }
                if (this.n) {
                    this.e = 0.0f;
                }
                if (!this.q) {
                    this.B.sendEmptyMessage(3);
                }
                this.B.sendEmptyMessage(2);
                if (!this.o) {
                    if (this.t != null) {
                        this.t.d();
                    }
                    this.B.removeMessages(1);
                }
                this.B.sendEmptyMessage(0);
                break;
            case 1:
            case 3:
                if (!this.p) {
                    this.B.sendEmptyMessage(2);
                }
                this.B.sendEmptyMessage(3);
                if (!this.n) {
                    if (this.t != null) {
                        this.t.b();
                    }
                    this.B.sendEmptyMessage(1);
                }
                this.B.removeMessages(0);
                break;
        }
        return true;
    }

    private void a() {
        this.b = new Paint(1);
        this.i = this.h / 2.0f;
        this.x = new a(this, this.i);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.c = i;
        this.d = i2;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate((float) (this.c / 2), (float) (this.d / 2));
        this.b.setColor(this.g);
        this.b.setStyle(Style.STROKE);
        this.b.setStrokeWidth(this.h);
        this.b.setStrokeCap(Cap.ROUND);
        canvas.drawArc(new RectF(((float) (-this.v)) + this.i, ((float) (-this.v)) + this.i, ((float) this.v) - this.i, ((float) this.v) - this.i), -90.0f, this.e, false, this.b);
        this.b.setStyle(Style.FILL);
        this.b.setColor(this.f);
        canvas.drawCircle(0.0f, 0.0f, ((float) this.v) - this.j, this.b);
        if (this.z) {
            if (this.w != null && !"".equals(this.w)) {
                this.b.setColor(this.k);
                this.b.setTextAlign(Align.CENTER);
                this.b.setTextSize(this.l);
                this.b.setStrokeWidth(1.0f);
                this.b.setStyle(Style.FILL);
                this.b.getTextBounds(this.w, 0, this.w.length(), new Rect());
                FontMetricsInt fontMetricsInt = this.b.getFontMetricsInt();
                canvas.drawText(this.w, 0.0f, (float) ((-(fontMetricsInt.descent + fontMetricsInt.ascent)) / 2), this.b);
            }
        } else if (this.A != null) {
            canvas.drawBitmap(this.A, (float) (0 - (this.A.getWidth() / 2)), (float) (0 - (this.A.getHeight() / 2)), null);
        }
    }

    public void setCircleProcessListener(b bVar) {
        this.t = bVar;
    }
}
