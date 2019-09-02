package com.amap.api.mapcore.util;

import android.graphics.Color;
import android.os.RemoteException;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import com.amap.api.maps.model.LatLng;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.github.mikephil.charting.utils.Utils;

/* compiled from: ArcDelegateImp */
public class ca implements cg {
    float a;
    float b;
    float c;
    float d;
    private LatLng e;
    private LatLng f;
    private LatLng g;
    private float h = 10.0f;
    private int i = ViewCompat.MEASURED_STATE_MASK;
    private float j = 0.0f;
    private boolean k = true;
    private String l;
    private lj m;
    private float[] n;
    private int o = 0;
    private boolean p = false;
    private double q = Utils.DOUBLE_EPSILON;
    private double r = Utils.DOUBLE_EPSILON;
    private double s = Utils.DOUBLE_EPSILON;

    public ca(lj ljVar) {
        this.m = ljVar;
        try {
            this.l = getId();
        } catch (Throwable e) {
            gz.c(e, "ArcDelegateImp", "create");
            e.printStackTrace();
        }
    }

    public boolean a() {
        return true;
    }

    public void remove() throws RemoteException {
        this.m.a(getId());
        this.m.setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.l == null) {
            this.l = this.m.d("Arc");
        }
        return this.l;
    }

    public void setZIndex(float f) throws RemoteException {
        this.j = f;
        this.m.f();
        this.m.setRunLowFrame(false);
    }

    public float getZIndex() throws RemoteException {
        return this.j;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.k = z;
        this.m.setRunLowFrame(false);
    }

    public boolean isVisible() throws RemoteException {
        return this.k;
    }

    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        if (equals(iOverlay) || iOverlay.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() throws RemoteException {
        return 0;
    }

    public boolean b() throws RemoteException {
        if (this.e == null || this.f == null || this.g == null || !this.k) {
            return false;
        }
        try {
            this.p = false;
            GLMapState c = this.m.c();
            FPoint obtain;
            if (d()) {
                DPoint e = e();
                int abs = (int) ((Math.abs(this.s - this.r) * 180.0d) / 3.141592653589793d);
                double d = (this.s - this.r) / ((double) abs);
                FPoint[] fPointArr = new FPoint[(abs + 1)];
                this.n = new float[(fPointArr.length * 3)];
                for (int i = 0; i <= abs; i++) {
                    GLMapState gLMapState;
                    if (i == abs) {
                        obtain = FPoint.obtain();
                        this.m.a(this.g.latitude, this.g.longitude, obtain);
                        fPointArr[i] = obtain;
                    } else {
                        gLMapState = c;
                        fPointArr[i] = a(gLMapState, (((double) i) * d) + this.r, e.x, e.y);
                    }
                    gLMapState = c;
                    fPointArr[i] = a(gLMapState, (((double) i) * d) + this.r, e.x, e.y);
                    this.n[i * 3] = fPointArr[i].x;
                    this.n[(i * 3) + 1] = fPointArr[i].y;
                    this.n[(i * 3) + 2] = 0.0f;
                }
                e.recycle();
                this.o = fPointArr.length;
                return true;
            }
            FPoint[] fPointArr2 = new FPoint[3];
            this.n = new float[(fPointArr2.length * 3)];
            obtain = FPoint.obtain();
            this.m.a(this.e.latitude, this.e.longitude, obtain);
            fPointArr2[0] = obtain;
            obtain = FPoint.obtain();
            this.m.a(this.f.latitude, this.f.longitude, obtain);
            fPointArr2[1] = obtain;
            obtain = FPoint.obtain();
            this.m.a(this.g.latitude, this.g.longitude, obtain);
            fPointArr2[2] = obtain;
            for (int i2 = 0; i2 < 3; i2++) {
                this.n[i2 * 3] = fPointArr2[i2].x;
                this.n[(i2 * 3) + 1] = fPointArr2[i2].y;
                this.n[(i2 * 3) + 2] = 0.0f;
            }
            this.o = fPointArr2.length;
            return true;
        } catch (Throwable th) {
            gz.c(th, "ArcDelegateImp", "calMapFPoint");
            th.printStackTrace();
            return false;
        }
    }

    private FPoint a(GLMapState gLMapState, double d, double d2, double d3) {
        int cos = (int) ((Math.cos(d) * this.q) + d2);
        int i = (int) (((-Math.sin(d)) * this.q) + d3);
        FPoint obtain = FPoint.obtain();
        MapConfig mapConfig = this.m.getMapConfig();
        if (mapConfig != null) {
            obtain.x = (float) (cos - mapConfig.getSX());
            obtain.y = (float) (i - mapConfig.getSY());
        }
        return obtain;
    }

    private boolean d() {
        if (Math.abs((((this.e.latitude - this.f.latitude) * (this.f.longitude - this.g.longitude)) * Math.pow(10.0d, 6.0d)) - (((this.e.longitude - this.f.longitude) * (this.f.latitude - this.g.latitude)) * Math.pow(10.0d, 6.0d))) < 1.0E-6d) {
            return false;
        }
        return true;
    }

    private DPoint e() {
        IPoint obtain = IPoint.obtain();
        this.m.a(this.e.latitude, this.e.longitude, obtain);
        IPoint obtain2 = IPoint.obtain();
        this.m.a(this.f.latitude, this.f.longitude, obtain2);
        IPoint obtain3 = IPoint.obtain();
        this.m.a(this.g.latitude, this.g.longitude, obtain3);
        double d = (double) obtain.x;
        double d2 = (double) obtain.y;
        double d3 = (double) obtain2.x;
        double d4 = (double) obtain2.y;
        double d5 = (double) obtain3.x;
        double d6 = (double) obtain3.y;
        double d7 = (((d6 - d2) * ((((d4 * d4) - (d2 * d2)) + (d3 * d3)) - (d * d))) + ((d4 - d2) * ((((d2 * d2) - (d6 * d6)) + (d * d)) - (d5 * d5)))) / (((2.0d * (d3 - d)) * (d6 - d2)) - ((2.0d * (d5 - d)) * (d4 - d2)));
        double d8 = (((d5 - d) * ((((d3 * d3) - (d * d)) + (d4 * d4)) - (d2 * d2))) + ((d3 - d) * ((((d * d) - (d5 * d5)) + (d2 * d2)) - (d6 * d6)))) / (((2.0d * (d4 - d2)) * (d5 - d)) - ((2.0d * (d6 - d2)) * (d3 - d)));
        this.q = Math.sqrt(((d - d7) * (d - d7)) + ((d2 - d8) * (d2 - d8)));
        this.r = a(d7, d8, d, d2);
        d3 = a(d7, d8, d3, d4);
        this.s = a(d7, d8, d5, d6);
        if (this.r < this.s) {
            if (d3 <= this.r || d3 >= this.s) {
                this.s -= 6.283185307179586d;
            }
        } else if (d3 <= this.s || d3 >= this.r) {
            this.s += 6.283185307179586d;
        }
        obtain.recycle();
        obtain2.recycle();
        obtain3.recycle();
        return DPoint.obtain(d7, d8);
    }

    private double a(double d, double d2, double d3, double d4) {
        double d5 = (d2 - d4) / this.q;
        if (Math.abs(d5) > 1.0d) {
            d5 = Math.signum(d5);
        }
        d5 = Math.asin(d5);
        if (d5 >= Utils.DOUBLE_EPSILON) {
            if (d3 < d) {
                return 3.141592653589793d - Math.abs(d5);
            }
            return d5;
        } else if (d3 < d) {
            return 3.141592653589793d - d5;
        } else {
            return d5 + 6.283185307179586d;
        }
    }

    public void a(MapConfig mapConfig) throws RemoteException {
        if (this.e != null && this.f != null && this.g != null && this.k) {
            b();
            if (this.n != null && this.o > 0) {
                float mapLenWithWin = this.m.c().getMapLenWithWin((int) this.h);
                this.m.c().getMapLenWithWin(1);
                AMapNativeRenderer.nativeDrawLineByTextureID(this.n, this.n.length, mapLenWithWin, this.m.d(), this.b, this.c, this.d, this.a, 0.0f, false, true, false, this.m.w(), 3, 0);
            }
            this.p = true;
        }
    }

    public void setStrokeWidth(float f) throws RemoteException {
        this.h = f;
        this.m.setRunLowFrame(false);
    }

    public float getStrokeWidth() throws RemoteException {
        return this.h;
    }

    public void setStrokeColor(int i) throws RemoteException {
        this.i = i;
        this.a = ((float) Color.alpha(i)) / 255.0f;
        this.b = ((float) Color.red(i)) / 255.0f;
        this.c = ((float) Color.green(i)) / 255.0f;
        this.d = ((float) Color.blue(i)) / 255.0f;
        this.m.setRunLowFrame(false);
    }

    public int getStrokeColor() throws RemoteException {
        return this.i;
    }

    public void destroy() {
        try {
            this.e = null;
            this.f = null;
            this.g = null;
        } catch (Throwable th) {
            gz.c(th, "ArcDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "ArcDelegateImp destroy");
        }
    }

    public boolean c() {
        return this.p;
    }

    public void a(LatLng latLng) {
        this.e = latLng;
    }

    public void b(LatLng latLng) {
        this.f = latLng;
    }

    public void c(LatLng latLng) {
        this.g = latLng;
    }

    public boolean isAboveMaskLayer() {
        return false;
    }

    public void setAboveMaskLayer(boolean z) {
    }
}
