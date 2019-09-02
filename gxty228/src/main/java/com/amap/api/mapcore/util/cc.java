package com.amap.api.mapcore.util;

import android.opengl.GLES20;
import android.os.RemoteException;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import com.amap.api.mapcore.util.db.e;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.BaseHoleOptions;
import com.amap.api.maps.model.CircleHoleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.PolygonHoleOptions;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.github.mikephil.charting.utils.Utils;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: CircleDelegateImp */
public class cc implements ci {
    private static int A = 20;
    private static double B = 1.0E10d;
    private static Object v = new Object();
    private static float y = 4.0075016E7f;
    private static int z = 256;
    float a = 0.0f;
    private LatLng b = null;
    private double c = Utils.DOUBLE_EPSILON;
    private float d = 10.0f;
    private int e = ViewCompat.MEASURED_STATE_MASK;
    private int f = 0;
    private float g = 0.0f;
    private boolean h = true;
    private String i;
    private lj j;
    private FloatBuffer k;
    private int l = 0;
    private boolean m = false;
    private IPoint n = IPoint.obtain();
    private FPoint o = FPoint.obtain();
    private List<BaseHoleOptions> p;
    private List<BaseHoleOptions> q;
    private int r;
    private int s;
    private FloatBuffer t;
    private FloatBuffer u;
    private int w = -1;
    private e x;

    public cc(lj ljVar) {
        this.j = ljVar;
        try {
            this.i = getId();
        } catch (Throwable e) {
            gz.c(e, "CircleDelegateImp", "create");
            e.printStackTrace();
        }
    }

    public boolean a() {
        return true;
    }

    public void remove() throws RemoteException {
        this.j.a(getId());
        this.j.setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.i == null) {
            this.i = this.j.d("Circle");
        }
        return this.i;
    }

    public void setZIndex(float f) throws RemoteException {
        this.g = f;
        this.j.f();
        this.j.setRunLowFrame(false);
    }

    public float getZIndex() throws RemoteException {
        return this.g;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.h = z;
        this.j.setRunLowFrame(false);
    }

    public boolean isVisible() throws RemoteException {
        return this.h;
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

    public int getDottedLineType() {
        return this.w;
    }

    public void setDottedLineType(int i) {
        this.w = i;
    }

    public boolean b() throws RemoteException {
        synchronized (v) {
            this.m = false;
            if (this.b != null) {
                float[] fArr = new float[1086];
                double b = b(this.b.latitude) * this.c;
                this.j.c();
                this.o.x = (float) (this.n.x - this.j.getMapConfig().getSX());
                this.o.y = (float) (this.n.y - this.j.getMapConfig().getSY());
                fArr[0] = this.o.x;
                fArr[1] = this.o.y;
                fArr[2] = 0.0f;
                for (int i = 0; i < 361; i++) {
                    double d = (((double) i) * 3.141592653589793d) / 180.0d;
                    double sin = Math.sin(d) * b;
                    int i2 = (int) (sin + ((double) this.n.x));
                    int cos = (int) ((Math.cos(d) * b) + ((double) this.n.y));
                    this.o.x = (float) (i2 - this.j.getMapConfig().getSX());
                    this.o.y = (float) (cos - this.j.getMapConfig().getSY());
                    fArr[(i + 1) * 3] = this.o.x;
                    fArr[((i + 1) * 3) + 1] = this.o.y;
                    fArr[((i + 1) * 3) + 2] = 0.0f;
                }
                this.l = fArr.length / 3;
                this.k = en.a(fArr);
            }
        }
        return true;
    }

    private void e() {
        if (this.j != null) {
            this.x = (e) this.j.u(3);
        }
    }

    public void a(MapConfig mapConfig) throws RemoteException {
        if (this.b != null && this.c > Utils.DOUBLE_EPSILON && this.h) {
            b();
            f();
            if (this.k != null && this.l > 0) {
                if (this.x == null || this.x.c()) {
                    e();
                }
                this.a = this.j.getMapConfig().getMapPerPixelUnitLength();
                du.a(this.x, this.f, this.e, this.k, this.d, this.l, this.j.w(), this.a, this.j.f(this.w));
            }
            g();
            this.m = true;
        }
    }

    void d() {
        this.l = 0;
        if (this.k != null) {
            this.k.clear();
        }
        this.j.setRunLowFrame(false);
        setHoleOptions(this.q);
    }

    public void setCenter(LatLng latLng) throws RemoteException {
        synchronized (v) {
            if (latLng != null) {
                this.b = latLng;
                GLMapState.lonlat2Geo(latLng.longitude, latLng.latitude, this.n);
                d();
            }
        }
    }

    public LatLng getCenter() throws RemoteException {
        return this.b;
    }

    public void setRadius(double d) throws RemoteException {
        this.c = d;
        d();
    }

    public double getRadius() throws RemoteException {
        return this.c;
    }

    public void setStrokeWidth(float f) throws RemoteException {
        this.d = f;
        this.j.setRunLowFrame(false);
    }

    public float getStrokeWidth() throws RemoteException {
        return this.d;
    }

    public void setStrokeColor(int i) throws RemoteException {
        this.e = i;
        this.j.setRunLowFrame(false);
    }

    public int getStrokeColor() throws RemoteException {
        return this.e;
    }

    public void setFillColor(int i) throws RemoteException {
        this.f = i;
        this.j.setRunLowFrame(false);
    }

    public int getFillColor() throws RemoteException {
        return this.f;
    }

    private float a(double d) {
        return (float) ((Math.cos((3.141592653589793d * d) / 180.0d) * ((double) y)) / ((double) (z << A)));
    }

    private double b(double d) {
        return 1.0d / ((double) a(d));
    }

    public void destroy() {
        try {
            this.b = null;
            if (this.k != null) {
                this.k.clear();
                this.k = null;
            }
            if (this.t != null) {
                this.t.clear();
                this.t = null;
            }
            if (this.u != null) {
                this.u.clear();
                this.u = null;
            }
            if (this.p != null) {
                this.p.clear();
            }
            if (this.q != null) {
                this.q.clear();
            }
            this.p = null;
            this.q = null;
        } catch (Throwable th) {
            gz.c(th, "CircleDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "CircleDelegateImp destroy");
        }
    }

    public boolean contains(LatLng latLng) throws RemoteException {
        if (this.p != null && this.p.size() > 0) {
            for (BaseHoleOptions a : this.p) {
                if (en.a(a, latLng)) {
                    return false;
                }
            }
        }
        return this.c >= ((double) AMapUtils.calculateLineDistance(this.b, latLng));
    }

    public boolean c() {
        return this.m;
    }

    public boolean isAboveMaskLayer() {
        return false;
    }

    public void setAboveMaskLayer(boolean z) {
    }

    public void setHoleOptions(List<BaseHoleOptions> list) {
        try {
            this.q = list;
            if (this.p == null) {
                this.p = new ArrayList();
            } else {
                this.p.clear();
            }
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    BaseHoleOptions baseHoleOptions = (BaseHoleOptions) list.get(i);
                    if (baseHoleOptions instanceof PolygonHoleOptions) {
                        PolygonHoleOptions polygonHoleOptions = (PolygonHoleOptions) baseHoleOptions;
                        if (a(polygonHoleOptions) && !en.a(this.p, polygonHoleOptions)) {
                            this.p.add(polygonHoleOptions);
                        }
                    } else if (baseHoleOptions instanceof CircleHoleOptions) {
                        CircleHoleOptions circleHoleOptions = (CircleHoleOptions) baseHoleOptions;
                        if (b(circleHoleOptions) && !en.a(this.p, circleHoleOptions)) {
                            this.p.add(circleHoleOptions);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            gz.c(th, "PolygonDelegateImp", "setHoleOptions");
            th.printStackTrace();
        }
    }

    public List<BaseHoleOptions> getHoleOptions() throws RemoteException {
        return this.p;
    }

    private boolean a(PolygonHoleOptions polygonHoleOptions) {
        boolean z = true;
        boolean contains;
        try {
            List points = polygonHoleOptions.getPoints();
            int i = 0;
            while (i < points.size()) {
                contains = contains((LatLng) points.get(i));
                if (!contains) {
                    return contains;
                }
                i++;
                z = contains;
            }
            return z;
        } catch (Throwable th) {
            Throwable th2 = th;
            contains = true;
            Throwable th3 = th2;
            gz.c(th3, "CircleDelegateImp", "isPolygonInCircle");
            th3.printStackTrace();
            return contains;
        }
    }

    private boolean b(CircleHoleOptions circleHoleOptions) {
        try {
            if (((double) AMapUtils.calculateLineDistance(circleHoleOptions.getCenter(), getCenter())) <= getRadius() - circleHoleOptions.getRadius()) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            gz.c(th, "CircleDelegateImp", "isCircleInCircle");
            th.printStackTrace();
            return true;
        }
    }

    private void f() throws RemoteException {
        MapConfig mapConfig = this.j.getMapConfig();
        if (this.p != null && this.p.size() > 0) {
            GLES20.glClearStencil(0);
            GLES20.glStencilMask(255);
            GLES20.glClear(1024);
            GLES20.glFlush();
            GLES20.glEnable(2960);
            GLES20.glColorMask(false, false, false, false);
            GLES20.glStencilFunc(512, 1, 255);
            GLES20.glStencilOp(7681, 7680, 7680);
            for (int i = 0; i < this.p.size(); i++) {
                BaseHoleOptions baseHoleOptions = (BaseHoleOptions) this.p.get(i);
                if (baseHoleOptions instanceof PolygonHoleOptions) {
                    a(a(((PolygonHoleOptions) baseHoleOptions).getPoints()), mapConfig.getSX(), mapConfig.getSY());
                } else if (baseHoleOptions instanceof CircleHoleOptions) {
                    CircleHoleOptions circleHoleOptions = (CircleHoleOptions) baseHoleOptions;
                    this.j.f();
                    a(circleHoleOptions);
                }
                if (this.t != null && this.r > 0) {
                    if (this.x == null || this.x.c()) {
                        e();
                    }
                    if (baseHoleOptions instanceof PolygonHoleOptions) {
                        du.a(this.x, -1, this.f, this.t, getStrokeWidth(), this.u, this.r, this.s, this.j.w());
                    } else if (baseHoleOptions instanceof CircleHoleOptions) {
                        du.a(this.x, -1, -1, this.t, 10.0f, this.r, this.j.w(), 0.0f, 0);
                    }
                }
            }
            GLES20.glColorMask(true, true, true, true);
            GLES20.glStencilFunc(517, 1, 255);
            GLES20.glStencilMask(0);
        }
    }

    private void g() throws RemoteException {
        GLES20.glClearStencil(0);
        GLES20.glClear(1024);
        GLES20.glDisable(2960);
        MapConfig mapConfig = this.j.getMapConfig();
        if (this.p != null && this.p.size() > 0) {
            for (int i = 0; i < this.p.size(); i++) {
                BaseHoleOptions baseHoleOptions = (BaseHoleOptions) this.p.get(i);
                if (baseHoleOptions instanceof PolygonHoleOptions) {
                    a(a(((PolygonHoleOptions) baseHoleOptions).getPoints()), mapConfig.getSX(), mapConfig.getSY());
                } else if (baseHoleOptions instanceof CircleHoleOptions) {
                    CircleHoleOptions circleHoleOptions = (CircleHoleOptions) baseHoleOptions;
                    this.j.f();
                    a(circleHoleOptions);
                }
                if (this.t != null && this.r > 0) {
                    if (this.x == null || this.x.c()) {
                        e();
                    }
                    if (baseHoleOptions instanceof PolygonHoleOptions) {
                        du.b(this.x, 0, this.e, this.t, this.d, this.u, this.r, this.s, this.j.w());
                    } else if (baseHoleOptions instanceof CircleHoleOptions) {
                        du.b(this.x, 0, this.e, this.t, this.d, this.r, this.j.w(), this.a, -1);
                    }
                }
            }
        }
    }

    static IPoint[] a(IPoint[] iPointArr) {
        int i = 0;
        int length = iPointArr.length;
        double[] dArr = new double[(length * 2)];
        for (int i2 = 0; i2 < length; i2++) {
            dArr[i2 * 2] = ((double) iPointArr[i2].x) * B;
            dArr[(i2 * 2) + 1] = ((double) iPointArr[i2].y) * B;
        }
        ej a = new dr().a(dArr);
        length = a.b;
        IPoint[] iPointArr2 = new IPoint[length];
        while (i < length) {
            iPointArr2[i] = new IPoint();
            iPointArr2[i].x = (int) (dArr[a.a(i) * 2] / B);
            iPointArr2[i].y = (int) (dArr[(a.a(i) * 2) + 1] / B);
            i++;
        }
        return iPointArr2;
    }

    private void a(List<IPoint> list, int i, int i2) throws RemoteException {
        int i3 = 0;
        if (list.size() >= 2) {
            float[] fArr = new float[(list.size() * 3)];
            IPoint[] iPointArr = new IPoint[list.size()];
            int i4 = 0;
            for (IPoint iPoint : list) {
                fArr[i4 * 3] = (float) (iPoint.x - i);
                fArr[(i4 * 3) + 1] = (float) (iPoint.y - i2);
                fArr[(i4 * 3) + 2] = 0.0f;
                iPointArr[i4] = iPoint;
                i4++;
            }
            IPoint[] a = a(iPointArr);
            if (a.length == 0) {
                if (B == 1.0E10d) {
                    B = 1.0E8d;
                } else {
                    B = 1.0E10d;
                }
                a = a(iPointArr);
            }
            float[] fArr2 = new float[(a.length * 3)];
            int length = a.length;
            i4 = 0;
            while (i3 < length) {
                IPoint iPoint2 = a[i3];
                fArr2[i4 * 3] = (float) (iPoint2.x - i);
                fArr2[(i4 * 3) + 1] = (float) (iPoint2.y - i2);
                fArr2[(i4 * 3) + 2] = 0.0f;
                i4++;
                i3++;
            }
            this.r = iPointArr.length;
            this.s = a.length;
            this.t = en.a(fArr);
            this.u = en.a(fArr2);
        }
    }

    private List<IPoint> a(List<LatLng> list) throws RemoteException {
        List arrayList = new ArrayList();
        if (list != null) {
            Object obj = null;
            for (LatLng latLng : list) {
                if (!latLng.equals(obj)) {
                    IPoint obtain = IPoint.obtain();
                    this.j.a(latLng.latitude, latLng.longitude, obtain);
                    arrayList.add(obtain);
                    obj = latLng;
                }
            }
            int size = arrayList.size();
            if (size > 1) {
                IPoint iPoint = (IPoint) arrayList.get(0);
                IPoint iPoint2 = (IPoint) arrayList.get(size - 1);
                if (iPoint.x == iPoint2.x && iPoint.y == iPoint2.y) {
                    arrayList.remove(size - 1);
                }
            }
        }
        if (en.a(arrayList, 0, arrayList.size())) {
            Collections.reverse(arrayList);
        }
        return arrayList;
    }

    public void a(CircleHoleOptions circleHoleOptions) throws RemoteException {
        int i = 0;
        if (circleHoleOptions.getCenter() != null) {
            IPoint obtain = IPoint.obtain();
            FPoint obtain2 = FPoint.obtain();
            GLMapState.lonlat2Geo(circleHoleOptions.getCenter().longitude, circleHoleOptions.getCenter().latitude, obtain);
            float[] fArr = new float[1086];
            double b = b(circleHoleOptions.getCenter().latitude) * circleHoleOptions.getRadius();
            obtain2.x = (float) (obtain.x - this.j.getMapConfig().getSX());
            obtain2.y = (float) (obtain.y - this.j.getMapConfig().getSY());
            fArr[0] = obtain2.x;
            fArr[1] = obtain2.y;
            fArr[2] = 0.0f;
            while (i < 361) {
                double d = (((double) i) * 3.141592653589793d) / 180.0d;
                double sin = Math.sin(d) * b;
                int cos = (int) ((Math.cos(d) * b) + ((double) obtain.y));
                obtain2.x = (float) (((int) (sin + ((double) obtain.x))) - this.j.getMapConfig().getSX());
                obtain2.y = (float) (cos - this.j.getMapConfig().getSY());
                fArr[(i + 1) * 3] = obtain2.x;
                fArr[((i + 1) * 3) + 1] = obtain2.y;
                fArr[((i + 1) * 3) + 2] = 0.0f;
                i++;
            }
            this.r = fArr.length / 3;
            this.t = en.a(fArr);
            obtain.recycle();
            obtain2.recycle();
        }
    }
}
