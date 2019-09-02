package com.amap.api.mapcore.util;

import android.graphics.Color;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.os.RemoteException;
import com.amap.api.mapcore.util.db.e;
import com.amap.api.maps.model.BaseHoleOptions;
import com.amap.api.maps.model.CircleHoleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.PolygonHoleOptions;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.Rectangle;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/* compiled from: PolygonDelegateImp */
public class cv implements co {
    private static double C = 1.0E10d;
    private static float v = 4.0075016E7f;
    private static int w = 256;
    private static int x = 20;
    private FloatBuffer A;
    private FloatBuffer B;
    Rect a = null;
    private lj b;
    private float c = 0.0f;
    private boolean d = true;
    private String e;
    private float f;
    private int g;
    private int h;
    private List<LatLng> i;
    private List<IPoint> j = new Vector();
    private List<BaseHoleOptions> k = new Vector();
    private List<BaseHoleOptions> l;
    private FloatBuffer m;
    private FloatBuffer n;
    private int o = 0;
    private int p = 0;
    private boolean q = false;
    private float r = 0.0f;
    private Object s = new Object();
    private float t = 0.0f;
    private e u;
    private int y;
    private int z;

    public cv(lj ljVar) {
        this.b = ljVar;
        try {
            this.e = getId();
        } catch (Throwable e) {
            gz.c(e, "PolygonDelegateImp", "create");
            e.printStackTrace();
        }
    }

    public void remove() throws RemoteException {
        this.b.a(getId());
        this.b.setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.e == null) {
            this.e = this.b.d("Polygon");
        }
        return this.e;
    }

    public void setPoints(List<LatLng> list) throws RemoteException {
        synchronized (this.s) {
            this.i = list;
            a((List) list);
            this.b.setRunLowFrame(false);
            setHoleOptions(this.l);
        }
    }

    public List<LatLng> getPoints() throws RemoteException {
        return this.i;
    }

    public void setZIndex(float f) throws RemoteException {
        this.c = f;
        this.b.f();
        this.b.setRunLowFrame(false);
    }

    public float getZIndex() throws RemoteException {
        return this.c;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.d = z;
        this.b.setRunLowFrame(false);
    }

    public boolean isVisible() throws RemoteException {
        return this.d;
    }

    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        if (equals(iOverlay) || iOverlay.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    private void a(List<LatLng> list) throws RemoteException {
        Builder builder = LatLngBounds.builder();
        if (this.a == null) {
            this.a = new Rect();
        }
        en.a(this.a);
        this.j.clear();
        if (list != null) {
            Object obj = null;
            for (LatLng latLng : list) {
                if (!latLng.equals(obj)) {
                    IPoint obtain = IPoint.obtain();
                    this.b.a(latLng.latitude, latLng.longitude, obtain);
                    this.j.add(obtain);
                    en.b(this.a, obtain.x, obtain.y);
                    builder.include(latLng);
                    obj = latLng;
                }
            }
            int size = this.j.size();
            if (size > 1) {
                IPoint iPoint = (IPoint) this.j.get(0);
                IPoint iPoint2 = (IPoint) this.j.get(size - 1);
                if (iPoint.x == iPoint2.x && iPoint.y == iPoint2.y) {
                    this.j.remove(size - 1);
                }
            }
        }
        this.a.sort();
        if (this.m != null) {
            this.m.clear();
        }
        if (this.n != null) {
            this.n.clear();
        }
        if (en.a(this.j, 0, this.j.size())) {
            Collections.reverse(this.j);
        }
        this.o = 0;
        this.p = 0;
        this.b.setRunLowFrame(false);
    }

    public int hashCodeRemote() throws RemoteException {
        return super.hashCode();
    }

    public boolean a() {
        if (this.b.getMapConfig().getGeoRectangle().isOverlap(this.a)) {
            return true;
        }
        return false;
    }

    private void b() {
        if (this.b != null) {
            this.u = (e) this.b.u(3);
        }
    }

    public void a(MapConfig mapConfig) throws RemoteException {
        if (this.j != null && this.j.size() != 0) {
            Rectangle geoRectangle = mapConfig.getGeoRectangle();
            IPoint[] clipRect = geoRectangle.getClipRect();
            List list = this.j;
            if (a(geoRectangle)) {
                synchronized (this.s) {
                    list = en.a(clipRect, this.j, true);
                }
            }
            d();
            if (list.size() > 2) {
                b(list, mapConfig.getSX(), mapConfig.getSY());
                if (this.m != null && this.n != null && this.o > 0 && this.p > 0) {
                    if (this.u == null || this.u.c()) {
                        b();
                    }
                    du.a(this.u, this.g, this.h, this.m, this.f, this.n, this.o, this.p, this.b.w());
                }
            }
            e();
            this.q = true;
        }
    }

    private void d() throws RemoteException {
        MapConfig mapConfig = this.b.getMapConfig();
        if (this.k != null && this.k.size() > 0) {
            GLES20.glClearStencil(0);
            GLES20.glStencilMask(255);
            GLES20.glClear(1024);
            GLES20.glFlush();
            GLES20.glEnable(2960);
            GLES20.glColorMask(false, false, false, false);
            GLES20.glStencilFunc(512, 1, 255);
            GLES20.glStencilOp(7681, 7680, 7680);
            for (int i = 0; i < this.k.size(); i++) {
                BaseHoleOptions baseHoleOptions = (BaseHoleOptions) this.k.get(i);
                if (baseHoleOptions instanceof PolygonHoleOptions) {
                    a(b(((PolygonHoleOptions) baseHoleOptions).getPoints()), mapConfig.getSX(), mapConfig.getSY());
                } else if (baseHoleOptions instanceof CircleHoleOptions) {
                    CircleHoleOptions circleHoleOptions = (CircleHoleOptions) baseHoleOptions;
                    this.b.f();
                    a(circleHoleOptions);
                }
                if (this.A != null && this.y > 0) {
                    if (this.u == null || this.u.c()) {
                        b();
                    }
                    if (baseHoleOptions instanceof PolygonHoleOptions) {
                        du.a(this.u, -1, this.h, this.A, this.f, this.B, this.y, this.z, this.b.w());
                    } else if (baseHoleOptions instanceof CircleHoleOptions) {
                        du.a(this.u, Color.argb(200, 80, 1, 1), Color.argb(200, 1, 1, 1), this.A, 5.0f, this.y, this.b.w(), 0.0f, 0);
                    }
                }
            }
            GLES20.glColorMask(true, true, true, true);
            GLES20.glStencilFunc(517, 1, 255);
            GLES20.glStencilMask(0);
        }
    }

    private void e() throws RemoteException {
        GLES20.glClearStencil(0);
        GLES20.glClear(1024);
        GLES20.glDisable(2960);
        MapConfig mapConfig = this.b.getMapConfig();
        if (this.k != null && this.k.size() > 0) {
            for (int i = 0; i < this.k.size(); i++) {
                BaseHoleOptions baseHoleOptions = (BaseHoleOptions) this.k.get(i);
                if (baseHoleOptions instanceof PolygonHoleOptions) {
                    a(b(((PolygonHoleOptions) baseHoleOptions).getPoints()), mapConfig.getSX(), mapConfig.getSY());
                } else if (baseHoleOptions instanceof CircleHoleOptions) {
                    CircleHoleOptions circleHoleOptions = (CircleHoleOptions) baseHoleOptions;
                    this.b.f();
                    a(circleHoleOptions);
                }
                if (this.A != null && this.y > 0) {
                    if (this.u == null || this.u.c()) {
                        b();
                    }
                    if (baseHoleOptions instanceof PolygonHoleOptions) {
                        du.b(this.u, 0, this.h, this.A, this.f, this.B, this.y, this.z, this.b.w());
                    } else if (baseHoleOptions instanceof CircleHoleOptions) {
                        du.b(this.u, 0, this.h, this.A, this.f, this.y, this.b.w(), 1.0f, -1);
                    }
                }
            }
        }
    }

    private float a(double d) {
        return (float) ((Math.cos((3.141592653589793d * d) / 180.0d) * ((double) v)) / ((double) (w << x)));
    }

    private double b(double d) {
        return 1.0d / ((double) a(d));
    }

    public void a(CircleHoleOptions circleHoleOptions) throws RemoteException {
        if (circleHoleOptions.getCenter() != null) {
            IPoint obtain = IPoint.obtain();
            FPoint obtain2 = FPoint.obtain();
            GLMapState.lonlat2Geo(circleHoleOptions.getCenter().longitude, circleHoleOptions.getCenter().latitude, obtain);
            float[] fArr = new float[1086];
            double b = b(circleHoleOptions.getCenter().latitude) * circleHoleOptions.getRadius();
            int sx = this.b.getMapConfig().getSX();
            int sy = this.b.getMapConfig().getSY();
            obtain2.x = (float) (obtain.x - sx);
            obtain2.y = (float) (obtain.y - sy);
            fArr[0] = obtain2.x;
            fArr[1] = obtain2.y;
            fArr[2] = 0.0f;
            for (int i = 0; i < 361; i++) {
                double d = (((double) i) * 3.141592653589793d) / 180.0d;
                double sin = Math.sin(d) * b;
                int i2 = (int) (sin + ((double) obtain.x));
                int cos = (int) ((Math.cos(d) * b) + ((double) obtain.y));
                obtain2.x = (float) (i2 - sx);
                obtain2.y = (float) (cos - sy);
                obtain2.x = (float) (i2 - this.b.getMapConfig().getSX());
                obtain2.y = (float) (cos - this.b.getMapConfig().getSY());
                fArr[(i + 1) * 3] = obtain2.x;
                fArr[((i + 1) * 3) + 1] = obtain2.y;
                fArr[((i + 1) * 3) + 2] = 0.0f;
            }
            this.y = fArr.length / 3;
            this.A = en.a(fArr);
            obtain.recycle();
            obtain2.recycle();
        }
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
                if (C == 1.0E10d) {
                    C = 1.0E8d;
                } else {
                    C = 1.0E10d;
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
            this.y = iPointArr.length;
            this.z = a.length;
            this.A = en.a(fArr);
            this.B = en.a(fArr2);
        }
    }

    private boolean a(Rectangle rectangle) {
        this.t = this.b.g();
        f();
        if (this.t <= ((float) 10) || rectangle == null) {
            return false;
        }
        try {
            if (rectangle.contains(this.a)) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private void b(List<IPoint> list, int i, int i2) throws RemoteException {
        int i3 = 0;
        f();
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        if (size >= 2) {
            IPoint iPoint = (IPoint) list.get(0);
            arrayList.add(iPoint);
            int i4 = 1;
            IPoint iPoint2 = iPoint;
            while (i4 < size - 1) {
                iPoint = (IPoint) list.get(i4);
                if (a(iPoint2, iPoint)) {
                    arrayList.add(iPoint);
                } else {
                    iPoint = iPoint2;
                }
                i4++;
                iPoint2 = iPoint;
            }
            arrayList.add(list.get(size - 1));
            float[] fArr = new float[(arrayList.size() * 3)];
            IPoint[] iPointArr = new IPoint[arrayList.size()];
            Iterator it = arrayList.iterator();
            int i5 = 0;
            while (it.hasNext()) {
                iPoint = (IPoint) it.next();
                fArr[i5 * 3] = (float) (iPoint.x - i);
                fArr[(i5 * 3) + 1] = (float) (iPoint.y - i2);
                fArr[(i5 * 3) + 2] = 0.0f;
                iPointArr[i5] = iPoint;
                i5++;
            }
            IPoint[] a = a(iPointArr);
            if (a.length == 0) {
                if (C == 1.0E10d) {
                    C = 1.0E8d;
                } else {
                    C = 1.0E10d;
                }
                a = a(iPointArr);
            }
            float[] fArr2 = new float[(a.length * 3)];
            int length = a.length;
            i5 = 0;
            while (i3 < length) {
                IPoint iPoint3 = a[i3];
                fArr2[i5 * 3] = (float) (iPoint3.x - i);
                fArr2[(i5 * 3) + 1] = (float) (iPoint3.y - i2);
                fArr2[(i5 * 3) + 2] = 0.0f;
                i5++;
                i3++;
            }
            this.o = iPointArr.length;
            this.p = a.length;
            this.m = en.a(fArr);
            this.n = en.a(fArr2);
        }
    }

    private List<IPoint> b(List<LatLng> list) throws RemoteException {
        List arrayList = new ArrayList();
        if (list != null) {
            Object obj = null;
            for (LatLng latLng : list) {
                if (!latLng.equals(obj)) {
                    IPoint obtain = IPoint.obtain();
                    this.b.a(latLng.latitude, latLng.longitude, obtain);
                    arrayList.add(obtain);
                    en.b(this.a, obtain.x, obtain.y);
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

    private boolean a(IPoint iPoint, IPoint iPoint2) {
        return ((float) (iPoint2.x - iPoint.x)) >= this.r || ((float) (iPoint2.x - iPoint.x)) <= (-this.r) || ((float) (iPoint2.y - iPoint.y)) >= this.r || ((float) (iPoint2.y - iPoint.y)) <= (-this.r);
    }

    private void f() {
        float g = this.b.g();
        if (this.j.size() <= GLMapStaticValue.TMC_REFRESH_TIMELIMIT) {
            this.r = this.b.c().getMapLenWithWin(2);
        } else if (g <= ((float) 12)) {
            g = (g / 2.0f) + (this.f / 2.0f);
            if (g > 200.0f) {
                g = 200.0f;
            }
            this.r = this.b.c().getMapLenWithWin((int) g);
        } else {
            this.r = this.b.c().getMapLenWithWin(10);
        }
    }

    public void setStrokeWidth(float f) throws RemoteException {
        this.f = f;
        this.b.setRunLowFrame(false);
    }

    public float getStrokeWidth() throws RemoteException {
        return this.f;
    }

    public void setFillColor(int i) throws RemoteException {
        this.g = i;
        this.b.setRunLowFrame(false);
    }

    public int getFillColor() throws RemoteException {
        return this.g;
    }

    public void setStrokeColor(int i) throws RemoteException {
        this.h = i;
        this.b.setRunLowFrame(false);
    }

    public int getStrokeColor() throws RemoteException {
        return this.h;
    }

    public void setHoleOptions(List<BaseHoleOptions> list) {
        try {
            this.l = list;
            if (this.k == null) {
                this.k = new ArrayList();
            } else {
                this.k.clear();
            }
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    BaseHoleOptions baseHoleOptions = (BaseHoleOptions) list.get(i);
                    if (baseHoleOptions instanceof PolygonHoleOptions) {
                        PolygonHoleOptions polygonHoleOptions = (PolygonHoleOptions) baseHoleOptions;
                        if (a(polygonHoleOptions) && !en.a(this.k, polygonHoleOptions)) {
                            this.k.add(polygonHoleOptions);
                        }
                    } else if (baseHoleOptions instanceof CircleHoleOptions) {
                        CircleHoleOptions circleHoleOptions = (CircleHoleOptions) baseHoleOptions;
                        if (b(circleHoleOptions) && !en.a(this.k, circleHoleOptions)) {
                            this.k.add(circleHoleOptions);
                        }
                    }
                }
            } else {
                this.k.clear();
            }
        } catch (Throwable th) {
            gz.c(th, "PolygonDelegateImp", "setHoleOptions");
            th.printStackTrace();
        }
        this.b.setRunLowFrame(false);
    }

    private boolean b(CircleHoleOptions circleHoleOptions) {
        try {
            if (!en.b(getPoints(), circleHoleOptions) && contains(circleHoleOptions.getCenter())) {
                return true;
            }
        } catch (Throwable th) {
            gz.c(th, "PolygonDelegateImp", "isCircleInPolygon");
            th.printStackTrace();
        }
        return false;
    }

    private boolean a(PolygonHoleOptions polygonHoleOptions) {
        boolean z = true;
        boolean a;
        try {
            List points = polygonHoleOptions.getPoints();
            int i = 0;
            while (i < points.size()) {
                a = en.a((LatLng) points.get(i), getPoints());
                if (!a) {
                    return a;
                }
                i++;
                z = a;
            }
            return z;
        } catch (Throwable th) {
            Throwable th2 = th;
            a = true;
            Throwable th3 = th2;
            gz.c(th3, "PolygonDelegateImp", "isPolygonInPolygon");
            th3.printStackTrace();
            return a;
        }
    }

    public List<BaseHoleOptions> getHoleOptions() {
        return this.k;
    }

    static IPoint[] a(IPoint[] iPointArr) {
        int i = 0;
        int length = iPointArr.length;
        double[] dArr = new double[(length * 2)];
        for (int i2 = 0; i2 < length; i2++) {
            dArr[i2 * 2] = ((double) iPointArr[i2].x) * C;
            dArr[(i2 * 2) + 1] = ((double) iPointArr[i2].y) * C;
        }
        ej a = new dr().a(dArr);
        length = a.b;
        IPoint[] iPointArr2 = new IPoint[length];
        while (i < length) {
            iPointArr2[i] = new IPoint();
            iPointArr2[i].x = (int) (dArr[a.a(i) * 2] / C);
            iPointArr2[i].y = (int) (dArr[(a.a(i) * 2) + 1] / C);
            i++;
        }
        return iPointArr2;
    }

    public void destroy() {
        try {
            if (this.m != null) {
                this.m.clear();
                this.m = null;
            }
            if (this.n != null) {
                this.n = null;
            }
            if (this.A != null) {
                this.A.clear();
                this.A = null;
            }
            if (this.B != null) {
                this.B.clear();
                this.B = null;
            }
            if (this.k != null) {
                this.k.clear();
            }
            if (this.l != null) {
                this.l.clear();
            }
            this.k = null;
            this.l = null;
        } catch (Throwable th) {
            gz.c(th, "PolygonDelegateImp", "destroy");
            th.printStackTrace();
        }
    }

    public boolean contains(LatLng latLng) throws RemoteException {
        if (latLng == null) {
            return false;
        }
        try {
            if (this.k != null && this.k.size() > 0) {
                for (BaseHoleOptions a : this.k) {
                    if (en.a(a, latLng)) {
                        return false;
                    }
                }
            }
            return en.a(latLng, getPoints());
        } catch (Throwable th) {
            gz.c(th, "PolygonDelegateImp", "contains");
            th.printStackTrace();
            return false;
        }
    }

    public boolean c() {
        return this.q;
    }

    public boolean isAboveMaskLayer() {
        return false;
    }

    public void setAboveMaskLayer(boolean z) {
    }
}
