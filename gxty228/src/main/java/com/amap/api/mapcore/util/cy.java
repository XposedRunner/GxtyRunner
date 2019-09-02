package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.opengl.GLES20;
import android.os.Build.VERSION;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.TextOptions;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.Rectangle;
import com.autonavi.amap.mapcore.interfaces.IOverlayImage;
import java.util.ArrayList;
import java.util.List;

/* compiled from: TextDelegateImp */
public class cy implements cq {
    private static int a = 0;
    private int A;
    private boolean B = false;
    private List<f> C = new ArrayList();
    private boolean D = false;
    private boolean E = false;
    private float[] F = new float[]{-1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private float b = 0.0f;
    private float c = 0.0f;
    private int d = 4;
    private int e = 32;
    private FPoint f = FPoint.obtain();
    private int g;
    private BitmapDescriptor h;
    private int i;
    private int j;
    private String k;
    private LatLng l;
    private float m = 0.5f;
    private float n = 1.0f;
    private boolean o = true;
    private z p;
    private Object q;
    private String r;
    private int s;
    private int t;
    private int u;
    private Typeface v;
    private float w;
    private Rect x = new Rect();
    private Paint y = new Paint();
    private int z;

    private static String a(String str) {
        a++;
        return str + a;
    }

    public void setRotateAngle(float f) {
        this.c = f;
        this.b = (((-f) % 360.0f) + 360.0f) % 360.0f;
        f();
    }

    public void destroy(boolean z) {
        try {
            this.D = true;
            if (z) {
                remove();
            }
            if (this.C != null && this.C.size() > 0) {
                for (int i = 0; i < this.C.size(); i++) {
                    f fVar = (f) this.C.get(i);
                    if (!(fVar == null || this.p == null)) {
                        this.p.a(fVar);
                        if (this.p.d() != null) {
                            this.p.d().c(fVar.j());
                        }
                    }
                }
                this.C.clear();
            }
            if (this.h != null) {
                this.h.recycle();
                this.h = null;
            }
            this.l = null;
            this.q = null;
        } catch (Throwable th) {
            gz.c(th, "TextDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "TextDelegateImp destroy");
        }
    }

    public cy(TextOptions textOptions, z zVar) throws RemoteException {
        this.p = zVar;
        if (textOptions.getPosition() != null) {
            this.l = textOptions.getPosition();
        }
        setAlign(textOptions.getAlignX(), textOptions.getAlignY());
        this.o = textOptions.isVisible();
        this.r = textOptions.getText();
        this.s = textOptions.getBackgroundColor();
        this.t = textOptions.getFontColor();
        this.u = textOptions.getFontSize();
        this.q = textOptions.getObject();
        this.w = textOptions.getZIndex();
        this.v = textOptions.getTypeface();
        this.k = getId();
        setRotateAngle(textOptions.getRotate());
        c();
        b();
    }

    private void c() {
        if (this.r != null && this.r.trim().length() > 0) {
            try {
                this.y.setTypeface(this.v);
                this.y.setSubpixelText(true);
                this.y.setAntiAlias(true);
                this.y.setStrokeWidth(5.0f);
                this.y.setStrokeCap(Cap.ROUND);
                this.y.setTextSize((float) this.u);
                this.y.setTextAlign(Align.CENTER);
                this.y.setColor(this.t);
                FontMetrics fontMetrics = this.y.getFontMetrics();
                int i = (int) (fontMetrics.descent - fontMetrics.ascent);
                int i2 = (int) (((((float) i) - fontMetrics.bottom) - fontMetrics.top) / 2.0f);
                this.y.getTextBounds(this.r, 0, this.r.length(), this.x);
                Bitmap createBitmap = Bitmap.createBitmap(this.x.width() + 6, i, Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawColor(this.s);
                canvas.drawText(this.r, (float) (this.x.centerX() + 3), (float) i2, this.y);
                this.h = BitmapDescriptorFactory.fromBitmap(createBitmap);
                this.i = this.h.getWidth();
                this.j = this.h.getHeight();
            } catch (Throwable th) {
                gz.c(th, "TextDelegateImp", "initBitmap");
            }
        }
    }

    public synchronized boolean remove() {
        f();
        this.o = false;
        return this.p.a((cn) this);
    }

    private void f() {
        if (this.p.d() != null) {
            this.p.d().setRunLowFrame(false);
        }
    }

    public LatLng getPosition() {
        return this.l;
    }

    public String getId() {
        if (this.k == null) {
            this.k = a("Text");
        }
        return this.k;
    }

    public void setPosition(LatLng latLng) {
        this.l = latLng;
        b();
        f();
    }

    public boolean isInfoWindowShown() {
        return false;
    }

    public void setVisible(boolean z) {
        if (this.o != z) {
            this.o = z;
            f();
        }
    }

    public boolean isVisible() {
        return this.o;
    }

    public void setZIndex(float f) {
        this.w = f;
        this.p.g();
    }

    public float getZIndex() {
        return this.w;
    }

    public void setAnchor(float f, float f2) {
    }

    public float getAnchorU() {
        return this.m;
    }

    public float getAnchorV() {
        return this.n;
    }

    public boolean equalsRemote(IOverlayImage iOverlayImage) throws RemoteException {
        if (equals(iOverlayImage) || iOverlayImage.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() {
        return super.hashCode();
    }

    public boolean b() {
        if (this.l == null) {
            return false;
        }
        IPoint obtain = IPoint.obtain();
        GLMapState.lonlat2Geo(this.l.longitude, this.l.latitude, obtain);
        this.z = obtain.x;
        this.A = obtain.y;
        this.p.d().a(this.l.latitude, this.l.longitude, this.f);
        obtain.recycle();
        return true;
    }

    private void b(lj ljVar, float[] fArr, int i, float f) throws RemoteException {
        float f2 = ((float) this.i) * f;
        float f3 = ((float) this.j) * f;
        float f4 = this.f.x;
        float f5 = this.f.y;
        float sc = ljVar.getMapConfig().getSC();
        this.F[0] = f4 - (this.m * f2);
        this.F[1] = ((1.0f - this.n) * f3) + f5;
        this.F[2] = f4;
        this.F[3] = f5;
        this.F[6] = this.b;
        this.F[7] = sc;
        this.F[9] = ((1.0f - this.m) * f2) + f4;
        this.F[10] = ((1.0f - this.n) * f3) + f5;
        this.F[11] = f4;
        this.F[12] = f5;
        this.F[15] = this.b;
        this.F[16] = sc;
        this.F[18] = ((1.0f - this.m) * f2) + f4;
        this.F[19] = f5 - (this.n * f3);
        this.F[20] = f4;
        this.F[21] = f5;
        this.F[24] = this.b;
        this.F[25] = sc;
        this.F[27] = f4 - (f2 * this.m);
        this.F[28] = f5 - (f3 * this.n);
        this.F[29] = f4;
        this.F[30] = f5;
        this.F[33] = this.b;
        this.F[34] = sc;
        System.arraycopy(this.F, 0, fArr, i, this.F.length);
    }

    public void a(lj ljVar, float[] fArr, int i, float f) {
        if (this.o && !this.D && this.l != null && this.h != null) {
            this.f.x = (float) (this.z - ljVar.getMapConfig().getSX());
            this.f.y = (float) (this.A - ljVar.getMapConfig().getSY());
            try {
                b(ljVar, fArr, i, f);
            } catch (Throwable th) {
                gz.c(th, "TextDelegateImp", "drawMarker");
            }
        }
    }

    public void a(lj ljVar) {
        boolean z = true;
        if (!this.E) {
            try {
                if (VERSION.SDK_INT < 12) {
                    z = false;
                }
                this.g = a(z, this.h);
                this.E = true;
            } catch (Throwable th) {
                gz.c(th, "TextDelegateImp", "loadtexture");
                th.printStackTrace();
            }
        }
    }

    private void g() {
        if (this.C != null) {
            for (f fVar : this.C) {
                if (!(fVar == null || this.p == null)) {
                    this.p.a(fVar);
                }
            }
            this.C.clear();
        }
    }

    private void a(f fVar) {
        if (fVar != null) {
            this.C.add(fVar);
            fVar.g();
        }
    }

    private int a(boolean z, BitmapDescriptor bitmapDescriptor) {
        g();
        f fVar = null;
        if (z) {
            fVar = this.p.d().a(bitmapDescriptor);
            if (fVar != null) {
                int f = fVar.f();
                a(fVar);
                return f;
            }
        }
        if (fVar == null) {
            fVar = new f(bitmapDescriptor, 0);
        }
        Bitmap bitmap = bitmapDescriptor.getBitmap();
        if (bitmap == null || bitmap.isRecycled()) {
            return 0;
        }
        f = h();
        fVar.a(f);
        if (z) {
            this.p.d().a(fVar);
        }
        a(fVar);
        en.b(f, bitmap, true);
        return f;
    }

    private int h() {
        int[] iArr = new int[]{0};
        GLES20.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    public boolean a() {
        return true;
    }

    public void setObject(Object obj) {
        this.q = obj;
    }

    public Object getObject() {
        return this.q;
    }

    public int d() {
        try {
            return this.g;
        } catch (Throwable th) {
            return 0;
        }
    }

    public float getRotateAngle() {
        return this.c;
    }

    public Rect l() {
        return null;
    }

    public void setText(String str) throws RemoteException {
        this.r = str;
        i();
    }

    public String getText() throws RemoteException {
        return this.r;
    }

    public void setBackgroundColor(int i) throws RemoteException {
        this.s = i;
        i();
    }

    public int getBackgroundColor() throws RemoteException {
        return this.s;
    }

    public void setFontColor(int i) throws RemoteException {
        this.t = i;
        i();
    }

    public int getFontColor() throws RemoteException {
        return this.t;
    }

    public void setFontSize(int i) throws RemoteException {
        this.u = i;
        i();
    }

    public int getFontSize() throws RemoteException {
        return this.u;
    }

    public void setTypeface(Typeface typeface) throws RemoteException {
        this.v = typeface;
        i();
    }

    public Typeface getTypeface() throws RemoteException {
        return this.v;
    }

    public void setAlign(int i, int i2) throws RemoteException {
        this.d = i;
        switch (i) {
            case 1:
                this.m = 0.0f;
                break;
            case 2:
                this.m = 1.0f;
                break;
            case 4:
                this.m = 0.5f;
                break;
            default:
                this.m = 0.5f;
                break;
        }
        this.e = i2;
        switch (i2) {
            case 8:
                this.n = 0.0f;
                break;
            case 16:
                this.n = 1.0f;
                break;
            case 32:
                this.n = 0.5f;
                break;
            default:
                this.n = 0.5f;
                break;
        }
        f();
    }

    public int getAlignX() throws RemoteException {
        return this.d;
    }

    public int getAlignY() {
        return this.e;
    }

    private synchronized void i() {
        c();
        this.E = false;
        f();
    }

    public boolean k() {
        Rectangle geoRectangle = this.p.d().getMapConfig().getGeoRectangle();
        if (geoRectangle == null || !geoRectangle.contains(this.z, this.A)) {
            return false;
        }
        return true;
    }

    public void b(boolean z) {
        this.B = z;
    }

    public boolean e() {
        return this.B;
    }
}
