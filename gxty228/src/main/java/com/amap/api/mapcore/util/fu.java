package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.view.View;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import java.io.File;
import java.io.InputStream;

/* compiled from: WaterMarkerView */
public class fu extends View {
    private Bitmap a;
    private Bitmap b;
    private Bitmap c;
    private Bitmap d;
    private Bitmap e;
    private Bitmap f;
    private Paint g = new Paint();
    private boolean h = false;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = 10;
    private int m = 0;
    private int n = 0;
    private int o = 10;
    private int p = 8;
    private boolean q = false;
    private boolean r = false;
    private Context s;
    private float t = 0.0f;
    private float u = 0.0f;
    private boolean v = true;

    public void a() {
        try {
            if (this.a != null) {
                this.a.recycle();
            }
            if (this.b != null) {
                this.b.recycle();
            }
            this.a = null;
            this.b = null;
            if (this.e != null) {
                this.e.recycle();
                this.e = null;
            }
            if (this.f != null) {
                this.f.recycle();
                this.f = null;
            }
            if (this.c != null) {
                this.c.recycle();
            }
            this.c = null;
            if (this.d != null) {
                this.d.recycle();
            }
            this.d = null;
            this.g = null;
        } catch (Throwable th) {
            gz.c(th, "WaterMarkerView", "destory");
            th.printStackTrace();
        }
    }

    public fu(Context context, lj ljVar) {
        InputStream open;
        Throwable th;
        InputStream inputStream = null;
        super(context);
        try {
            this.s = context.getApplicationContext();
            open = eg.a(context).open("ap.data");
            try {
                this.e = BitmapFactory.decodeStream(open);
                this.a = en.a(this.e, le.a);
                open.close();
                inputStream = eg.a(context).open("ap1.data");
                this.f = BitmapFactory.decodeStream(inputStream);
                this.b = en.a(this.f, le.a);
                inputStream.close();
                this.j = this.b.getWidth();
                this.i = this.b.getHeight();
                this.g.setAntiAlias(true);
                this.g.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.g.setStyle(Style.STROKE);
                AMapEngineUtils.LOGO_CUSTOM_ICON_DAY_NAME = context.getFilesDir() + "/icon_web_day.data";
                AMapEngineUtils.LOGO_CUSTOM_ICON_NIGHT_NAME = context.getFilesDir() + "/icon_web_night.data";
                em.a().a(new Runnable(this) {
                    final /* synthetic */ fu a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        Object obj = "f3a1627fe912c49ecdcd4ab92a5d6bc8";
                        Object obj2 = "61733cf36c9727db08c2ef727490c036";
                        this.a.a(AMapEngineUtils.LOGO_CUSTOM_ICON_DAY_NAME, 0);
                        this.a.a(AMapEngineUtils.LOGO_CUSTOM_ICON_NIGHT_NAME, 1);
                        if (!"".equals(ec.a(this.a.s, "amap_web_logo", "md5_day", ""))) {
                            return;
                        }
                        if (this.a.c == null || this.a.d == null) {
                            ec.a(this.a.s, "amap_web_logo", "md5_day", obj);
                            ec.a(this.a.s, "amap_web_logo", "md5_night", obj2);
                            return;
                        }
                        ec.a(this.a.s, "amap_web_logo", "md5_day", gf.a(AMapEngineUtils.LOGO_CUSTOM_ICON_DAY_NAME));
                        obj = gf.a(AMapEngineUtils.LOGO_CUSTOM_ICON_NIGHT_NAME);
                        if (!"".equals(obj)) {
                            ec.a(this.a.s, "amap_web_logo", "md5_night", obj);
                        }
                        this.a.b(true);
                    }
                });
                if (open != null) {
                    try {
                        open.close();
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th22) {
                        th22.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th22 = th3;
                try {
                    gz.c(th22, "WaterMarkerView", "create");
                    th22.printStackTrace();
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable th222) {
                            th222.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th2222) {
                            th2222.printStackTrace();
                        }
                    }
                } catch (Throwable th4) {
                    th2222 = th4;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable th5) {
                            th5.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th6) {
                            th6.printStackTrace();
                        }
                    }
                    throw th2222;
                }
            }
        } catch (Throwable th7) {
            th2222 = th7;
            open = null;
            if (open != null) {
                open.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th2222;
        }
    }

    public Bitmap b() {
        if (this.h) {
            if (!this.r || this.d == null) {
                return this.b;
            }
            return this.d;
        } else if (!this.r || this.c == null) {
            return this.a;
        } else {
            return this.c;
        }
    }

    public void a(boolean z) {
        try {
            this.h = z;
            if (z) {
                this.g.setColor(-1);
            } else {
                this.g.setColor(ViewCompat.MEASURED_STATE_MASK);
            }
        } catch (Throwable th) {
            gz.c(th, "WaterMarkerView", "changeBitmap");
            th.printStackTrace();
        }
    }

    public Point c() {
        return new Point(this.l, this.m - 2);
    }

    public void a(int i) {
        this.n = 0;
        this.k = i;
        d();
    }

    public void b(int i) {
        this.n = 1;
        this.p = i;
        d();
    }

    public void c(int i) {
        this.n = 1;
        this.o = i;
        d();
    }

    public float d(int i) {
        switch (i) {
            case 0:
                return this.t;
            case 1:
                return 1.0f - this.t;
            case 2:
                return 1.0f - this.u;
            default:
                return 0.0f;
        }
    }

    public void a(int i, float f) {
        this.n = 2;
        float max = Math.max(0.0f, Math.min(f, 1.0f));
        switch (i) {
            case 0:
                this.t = max;
                this.v = true;
                break;
            case 1:
                this.t = 1.0f - max;
                this.v = false;
                break;
            case 2:
                this.u = 1.0f - max;
                break;
        }
        d();
    }

    public void d() {
        if (getWidth() != 0 && getHeight() != 0) {
            f();
            postInvalidate();
        }
    }

    public void onDraw(Canvas canvas) {
        try {
            if (getWidth() != 0 && getHeight() != 0 && this.b != null) {
                if (!this.q) {
                    f();
                    this.q = true;
                }
                canvas.drawBitmap(b(), (float) this.l, (float) this.m, this.g);
            }
        } catch (Throwable th) {
            gz.c(th, "WaterMarkerView", "onDraw");
            th.printStackTrace();
        }
    }

    private void f() {
        switch (this.n) {
            case 0:
                h();
                break;
            case 2:
                g();
                break;
        }
        this.l = this.o;
        this.m = (getHeight() - this.p) - this.i;
        if (this.l < 0) {
            this.l = 0;
        }
        if (this.m < 0) {
            this.m = 0;
        }
    }

    private void g() {
        if (this.v) {
            this.o = (int) (((float) getWidth()) * this.t);
        } else {
            this.o = (int) ((((float) getWidth()) * this.t) - ((float) this.j));
        }
        this.p = (int) (((float) getHeight()) * this.u);
    }

    private void h() {
        if (this.k == 1) {
            this.o = (getWidth() - this.j) / 2;
        } else if (this.k == 2) {
            this.o = (getWidth() - this.j) - 10;
        } else {
            this.o = 10;
        }
        this.p = 8;
    }

    public void a(String str, int i) {
        try {
            if (!new File(str).exists()) {
                return;
            }
            Bitmap bitmap;
            if (i == 0) {
                bitmap = this.c;
                this.e = BitmapFactory.decodeFile(str);
                this.c = en.a(this.e, le.a);
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            } else if (i == 1) {
                bitmap = this.d;
                this.e = BitmapFactory.decodeFile(str);
                this.d = en.a(this.e, le.a);
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            }
        } catch (Throwable th) {
            gz.c(th, "WaterMarkerView", "create");
            th.printStackTrace();
        }
    }

    public void b(boolean z) {
        if (this.r != z) {
            this.r = z;
            if (!z) {
                this.j = this.a.getWidth();
                this.i = this.a.getHeight();
            } else if (this.h) {
                if (this.d != null) {
                    this.j = this.d.getWidth();
                    this.i = this.d.getHeight();
                }
            } else if (this.c != null) {
                this.j = this.c.getWidth();
                this.i = this.c.getHeight();
            }
        }
    }

    public boolean e() {
        return this.h;
    }
}
