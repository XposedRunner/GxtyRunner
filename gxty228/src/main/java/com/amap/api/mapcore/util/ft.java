package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.view.View;
import cn.jiguang.api.utils.ByteBufferUtils;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.VirtualEarthProjection;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.tencent.bugly.BuglyStrategy.a;

/* compiled from: ScaleView */
public class ft extends View {
    private String a = "";
    private int b = 0;
    private lj c;
    private Paint d;
    private Paint e;
    private Rect f;
    private IPoint g;
    private float h = 0.0f;
    private final int[] i = new int[]{10000000, 5000000, 2000000, 1000000, 500000, 200000, 100000, 50000, a.MAX_USERDATA_VALUE_LENGTH, 20000, ByteBufferUtils.ERROR_CODE, GLMapStaticValue.TMC_REFRESH_TIMELIMIT, 2000, 1000, 500, 200, 100, 50, 25, 10, 5};

    public void a() {
        this.d = null;
        this.e = null;
        this.f = null;
        this.a = null;
        this.g = null;
    }

    public ft(Context context, lj ljVar) {
        super(context);
        this.c = ljVar;
        this.d = new Paint();
        this.f = new Rect();
        this.d.setAntiAlias(true);
        this.d.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.d.setStrokeWidth(2.0f * le.a);
        this.d.setStyle(Style.STROKE);
        this.e = new Paint();
        this.e.setAntiAlias(true);
        this.e.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.e.setTextSize(20.0f * le.a);
        this.h = (float) eg.a(context, 1.0f);
        this.g = new IPoint();
    }

    protected void onDraw(Canvas canvas) {
        if (this.a != null && !"".equals(this.a) && this.b != 0) {
            Point l = this.c.l();
            if (l != null) {
                this.e.getTextBounds(this.a, 0, this.a.length(), this.f);
                int i = l.x;
                int height = (l.y - this.f.height()) + 5;
                canvas.drawText(this.a, (float) (((this.b - this.f.width()) / 2) + i), (float) height, this.e);
                int height2 = height + (this.f.height() - 5);
                Canvas canvas2 = canvas;
                canvas2.drawLine((float) i, ((float) height2) - (this.h * 2.0f), (float) i, le.a + ((float) height2), this.d);
                canvas.drawLine((float) i, (float) height2, (float) (this.b + i), (float) height2, this.d);
                canvas2 = canvas;
                canvas2.drawLine((float) (this.b + i), ((float) height2) - (this.h * 2.0f), (float) (this.b + i), le.a + ((float) height2), this.d);
            }
        }
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(int i) {
        this.b = i;
    }

    public void a(boolean z) {
        if (z) {
            setVisibility(0);
            b();
            return;
        }
        a("");
        a(0);
        setVisibility(8);
    }

    public void b() {
        if (this.c != null) {
            try {
                float a = this.c.a(1);
                this.c.a(1, this.g);
                if (this.g != null) {
                    DPoint pixelsToLatLong = VirtualEarthProjection.pixelsToLatLong((long) this.g.x, (long) this.g.y, 20);
                    double cos = (double) ((float) ((((Math.cos((pixelsToLatLong.y * 3.141592653589793d) / 180.0d) * 2.0d) * 3.141592653589793d) * 6378137.0d) / (256.0d * Math.pow(2.0d, (double) a))));
                    int t = (int) (((double) this.i[(int) a]) / (((double) this.c.t()) * cos));
                    String a2 = en.a(this.i[(int) a]);
                    a(t);
                    a(a2);
                    pixelsToLatLong.recycle();
                    invalidate();
                }
            } catch (Throwable th) {
                gz.c(th, "AMapDelegateImpGLSurfaceView", "changeScaleState");
                th.printStackTrace();
            }
        }
    }
}
