package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.os.Build.VERSION;
import android.os.RemoteException;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import cn.jiguang.api.utils.ByteBufferUtils;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.PolylineOptions.LineCapType;
import com.amap.api.maps.model.PolylineOptions.LineJoinType;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.autonavi.amap.mapcore.AMapNativePolyline;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.FPoint3;
import com.autonavi.amap.mapcore.FPointBounds;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.Rectangle;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.github.mikephil.charting.utils.Utils;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: PolylineDelegateImp */
public class cw implements cp {
    private boolean A = true;
    private int B = 0;
    private int C = 0;
    private int D = ViewCompat.MEASURED_STATE_MASK;
    private int E = 0;
    private int F = 0;
    private float G = 10.0f;
    private float H = 0.0f;
    private float I = 0.0f;
    private float J;
    private float K;
    private float L;
    private float M;
    private float N = 1.0f;
    private float O = 0.0f;
    private float[] P;
    private int[] Q;
    private int[] R;
    private boolean S = false;
    private FPointBounds T = null;
    private PolylineOptions U;
    private int V = 0;
    private LineJoinType W = LineJoinType.LineJoinBevel;
    private LineCapType X = LineCapType.LineCapRound;
    private db Y;
    private long Z = 0;
    Rect a = null;
    private boolean aa = false;
    int b = 0;
    ArrayList<FPoint> c = new ArrayList();
    long d = 0;
    private r e;
    private String f;
    private List<IPoint> g = new ArrayList();
    private List<FPoint> h = new ArrayList();
    private List<LatLng> i = new ArrayList();
    private List<BitmapDescriptor> j = new ArrayList();
    private List<f> k = new ArrayList();
    private List<Integer> l = new ArrayList();
    private List<Integer> m = new ArrayList();
    private List<Integer> n = new ArrayList();
    private List<Integer> o = new ArrayList();
    private FloatBuffer p;
    private BitmapDescriptor q = null;
    private Object r = new Object();
    private boolean s = true;
    private boolean t = true;
    private boolean u = false;
    private boolean v = false;
    private boolean w = false;
    private boolean x = true;
    private boolean y = false;
    private boolean z = false;

    public void a(boolean z) {
        this.A = z;
        this.e.f().setRunLowFrame(false);
    }

    public void setGeodesic(boolean z) throws RemoteException {
        this.u = z;
        this.e.f().setRunLowFrame(false);
    }

    public boolean isGeodesic() {
        return this.u;
    }

    public void setDottedLine(boolean z) {
        if (this.B == 2 || this.B == 0) {
            this.v = z;
            if (z && this.t) {
                this.B = 2;
            } else if (!z && this.t) {
                this.B = 0;
            }
            this.e.f().setRunLowFrame(false);
        }
    }

    public void a(int i) {
        this.F = i;
    }

    public boolean isDottedLine() {
        return this.v;
    }

    public cw(r rVar, PolylineOptions polylineOptions) {
        this.e = rVar;
        setOptions(polylineOptions);
        try {
            this.f = getId();
        } catch (Throwable e) {
            gz.c(e, "PolylineDelegateImp", "create");
            e.printStackTrace();
        }
    }

    void a(List<LatLng> list) throws RemoteException {
        boolean z;
        List arrayList = new ArrayList();
        Builder builder = LatLngBounds.builder();
        if (list != null) {
            LatLng latLng = null;
            z = false;
            for (LatLng latLng2 : list) {
                boolean z2;
                IPoint obtain;
                if (!this.u) {
                    obtain = IPoint.obtain();
                    this.e.f().a(latLng2.latitude, latLng2.longitude, obtain);
                    arrayList.add(obtain);
                    builder.include(latLng2);
                } else if (latLng != null) {
                    if (Math.abs(latLng2.longitude - latLng.longitude) < 0.01d) {
                        obtain = IPoint.obtain();
                        this.e.f().a(latLng.latitude, latLng.longitude, obtain);
                        arrayList.add(obtain);
                        builder.include(latLng);
                        obtain = IPoint.obtain();
                        this.e.f().a(latLng2.latitude, latLng2.longitude, obtain);
                        arrayList.add(obtain);
                        builder.include(latLng2);
                    } else {
                        a(latLng, latLng2, arrayList, builder);
                    }
                }
                if (latLng2 != null) {
                    if (z || latLng2.longitude >= -180.0d) {
                        z2 = z;
                    } else {
                        this.aa = true;
                        z2 = true;
                    }
                    if (!this.aa && latLng2.longitude > 180.0d) {
                        this.aa = true;
                    }
                } else {
                    z2 = z;
                }
                latLng = latLng2;
                z = z2;
            }
        } else {
            z = false;
        }
        this.g = arrayList;
        this.E = 0;
        if (this.a == null) {
            this.a = new Rect();
        }
        en.a(this.a);
        for (IPoint iPoint : this.g) {
            if (z) {
                iPoint.x += AMapEngineUtils.MAX_P20_WIDTH;
            }
            en.b(this.a, iPoint.x, iPoint.y);
        }
        this.a.sort();
        this.e.f().setRunLowFrame(false);
    }

    IPoint a(IPoint iPoint, IPoint iPoint2, IPoint iPoint3, double d, int i) {
        IPoint obtain = IPoint.obtain();
        double d2 = (double) (iPoint2.x - iPoint.x);
        double d3 = (double) (iPoint2.y - iPoint.y);
        obtain.y = (int) (((((double) i) * d) / Math.sqrt(((d3 * d3) / (d2 * d2)) + 1.0d)) + ((double) iPoint3.y));
        obtain.x = (int) (((d3 * ((double) (iPoint3.y - obtain.y))) / d2) + ((double) iPoint3.x));
        return obtain;
    }

    void a(List<IPoint> list, List<IPoint> list2, double d) {
        if (list.size() == 3) {
            for (int i = 0; i <= 10; i = (int) (((float) i) + 1.0f)) {
                float f = ((float) i) / 10.0f;
                IPoint obtain = IPoint.obtain();
                double d2 = ((((1.0d - ((double) f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(0)).x)) + (((((double) (2.0f * f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(1)).x)) * d)) + ((double) (((float) ((IPoint) list.get(2)).x) * (f * f)));
                double d3 = ((((1.0d - ((double) f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(0)).y)) + (((((double) (2.0f * f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(1)).y)) * d)) + ((double) (((float) ((IPoint) list.get(2)).y) * (f * f)));
                double d4 = (((1.0d - ((double) f)) * (1.0d - ((double) f))) + ((((double) (2.0f * f)) * (1.0d - ((double) f))) * d)) + ((double) (f * f));
                obtain.x = (int) (d2 / ((((1.0d - ((double) f)) * (1.0d - ((double) f))) + ((((double) (2.0f * f)) * (1.0d - ((double) f))) * d)) + ((double) (f * f))));
                obtain.y = (int) (d3 / d4);
                list2.add(obtain);
            }
        }
    }

    void a(LatLng latLng, LatLng latLng2, List<IPoint> list, Builder builder) {
        double abs = (Math.abs(latLng.longitude - latLng2.longitude) * 3.141592653589793d) / 180.0d;
        LatLng latLng3 = new LatLng((latLng2.latitude + latLng.latitude) / 2.0d, (latLng2.longitude + latLng.longitude) / 2.0d, false);
        builder.include(latLng).include(latLng3).include(latLng2);
        int i = latLng3.latitude > Utils.DOUBLE_EPSILON ? -1 : 1;
        IPoint obtain = IPoint.obtain();
        this.e.f().a(latLng.latitude, latLng.longitude, obtain);
        IPoint obtain2 = IPoint.obtain();
        this.e.f().a(latLng2.latitude, latLng2.longitude, obtain2);
        IPoint obtain3 = IPoint.obtain();
        this.e.f().a(latLng3.latitude, latLng3.longitude, obtain3);
        double cos = Math.cos(0.5d * abs);
        IPoint a = a(obtain, obtain2, obtain3, (Math.hypot((double) (obtain.x - obtain2.x), (double) (obtain.y - obtain2.y)) * 0.5d) * Math.tan(0.5d * abs), i);
        List arrayList = new ArrayList();
        arrayList.add(obtain);
        arrayList.add(a);
        arrayList.add(obtain2);
        a(arrayList, (List) list, cos);
        obtain.recycle();
        a.recycle();
        obtain2.recycle();
    }

    public void remove() throws RemoteException {
        this.e.d(getId());
        setVisible(false);
        this.e.f().setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.f == null) {
            this.f = this.e.a("Polyline");
        }
        return this.f;
    }

    public void setPoints(List<LatLng> list) throws RemoteException {
        try {
            this.i = list;
            synchronized (this.r) {
                a((List) list);
            }
            this.x = true;
            this.e.f().setRunLowFrame(false);
            this.U.setPoints(list);
        } catch (Throwable th) {
            gz.c(th, "PolylineDelegateImp", "setPoints");
            this.g.clear();
            th.printStackTrace();
        }
    }

    public List<LatLng> getPoints() throws RemoteException {
        return this.i;
    }

    public void setWidth(float f) throws RemoteException {
        this.G = f;
        this.e.f().setRunLowFrame(false);
        this.U.width(f);
    }

    public float getWidth() throws RemoteException {
        return this.G;
    }

    public void setColor(int i) {
        if (this.B == 0 || this.B == 2) {
            this.D = i;
            this.J = ((float) Color.alpha(i)) / 255.0f;
            this.K = ((float) Color.red(i)) / 255.0f;
            this.L = ((float) Color.green(i)) / 255.0f;
            this.M = ((float) Color.blue(i)) / 255.0f;
            if (this.t) {
                if (this.v) {
                    this.B = 2;
                } else {
                    this.B = 0;
                }
            }
            this.e.f().setRunLowFrame(false);
        }
        this.U.color(i);
    }

    public int getColor() throws RemoteException {
        return this.D;
    }

    public void setZIndex(float f) throws RemoteException {
        this.H = f;
        this.e.d();
        this.e.f().setRunLowFrame(false);
        if (this.U != null) {
            this.U.zIndex(f);
        }
    }

    public float getZIndex() throws RemoteException {
        return this.H;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.s = z;
        this.e.f().setRunLowFrame(false);
        if (this.U != null) {
            this.U.visible(z);
        }
    }

    public boolean isVisible() throws RemoteException {
        return this.s;
    }

    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        if (equals(iOverlay) || iOverlay.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() throws RemoteException {
        return super.hashCode();
    }

    public boolean a() {
        if (this.aa) {
            return true;
        }
        Rectangle geoRectangle = this.e.f().getMapConfig().getGeoRectangle();
        if (this.a == null || geoRectangle == null || geoRectangle.isOverlap(this.a)) {
            return true;
        }
        return false;
    }

    public boolean b() throws RemoteException {
        synchronized (this.r) {
            FPointBounds.Builder builder = new FPointBounds.Builder();
            this.h.clear();
            this.z = false;
            this.P = new float[(this.g.size() * 3)];
            this.b = this.P.length;
            int i = 0;
            for (IPoint iPoint : this.g) {
                FPoint fPoint3 = new FPoint3();
                this.e.f().a(iPoint.x, iPoint.y, fPoint3);
                this.P[i * 3] = fPoint3.x;
                this.P[(i * 3) + 1] = fPoint3.y;
                this.P[(i * 3) + 2] = 0.0f;
                if (this.l != null) {
                    synchronized (this.l) {
                        if (this.l != null && this.l.size() > i) {
                            fPoint3.setColorIndex(((Integer) this.l.get(i)).intValue());
                        } else if (this.m != null && this.m.size() > i) {
                            fPoint3.setColorIndex(((Integer) this.m.get(i)).intValue());
                        }
                    }
                }
                this.h.add(fPoint3);
                builder.include(fPoint3);
                i++;
            }
            this.T = builder.build();
        }
        if (!this.A) {
            this.p = en.a(this.P);
        }
        this.E = this.g.size();
        e();
        return true;
    }

    private void e() {
        float mapPerPixelUnitLength = this.e.f().getMapConfig().getMapPerPixelUnitLength();
        if (this.E <= GLMapStaticValue.TMC_REFRESH_TIMELIMIT) {
            this.O = mapPerPixelUnitLength * 2.0f;
        } else if (this.I <= ((float) 12)) {
            float f = (this.G / 2.0f) + (this.I / 2.0f);
            if (f > 200.0f) {
                f = 200.0f;
            }
            this.O = f * mapPerPixelUnitLength;
        } else {
            this.O = 10.0f * mapPerPixelUnitLength;
        }
    }

    private void d(List<FPoint> list) throws RemoteException {
        int i = 0;
        this.c.clear();
        int size = list.size();
        if (size >= 2) {
            FPoint fPoint = (FPoint) list.get(0);
            this.c.add(fPoint);
            int i2 = 1;
            FPoint fPoint2 = fPoint;
            while (i2 < size - 1) {
                fPoint = (FPoint) list.get(i2);
                if (i2 == 1 || a(fPoint2, fPoint)) {
                    this.c.add(fPoint);
                } else {
                    this.c.set(this.c.size() - 1, fPoint);
                    fPoint = fPoint2;
                }
                i2++;
                fPoint2 = fPoint;
            }
            this.c.add(list.get(size - 1));
            size = this.c.size() * 3;
            this.b = size;
            if (this.P == null || this.P.length < this.b) {
                this.P = new float[size];
            }
            if (this.B == 5 || this.B == 3 || this.B == 4) {
                Object obj = new int[this.c.size()];
                List arrayList = new ArrayList();
                i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < size / 3; i4++) {
                    FPoint3 fPoint3 = (FPoint3) this.c.get(i4);
                    this.P[i4 * 3] = fPoint3.x;
                    this.P[(i4 * 3) + 1] = fPoint3.y;
                    this.P[(i4 * 3) + 2] = 0.0f;
                    int i5 = fPoint3.colorIndex;
                    if (i4 == 0) {
                        arrayList.add(Integer.valueOf(i5));
                    } else if (i5 == i3) {
                    } else {
                        if (i5 != -1) {
                            i3 = i5;
                        }
                        arrayList.add(Integer.valueOf(i3));
                        i5 = i3;
                    }
                    obj[i2] = i4;
                    i2++;
                    i3 = i5;
                }
                this.Q = new int[arrayList.size()];
                System.arraycopy(obj, 0, this.Q, 0, this.Q.length);
                this.n = arrayList;
                this.o = arrayList;
                return;
            }
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                fPoint = (FPoint) it.next();
                this.P[i * 3] = fPoint.x;
                this.P[(i * 3) + 1] = fPoint.y;
                this.P[(i * 3) + 2] = 0.0f;
                i++;
            }
        }
    }

    private boolean a(FPoint fPoint, FPoint fPoint2) {
        if ((fPoint instanceof FPoint3) && (fPoint2 instanceof FPoint3) && ((FPoint3) fPoint).colorIndex != ((FPoint3) fPoint2).colorIndex) {
            return true;
        }
        boolean z = Math.abs(fPoint2.x - fPoint.x) >= this.O || Math.abs(fPoint2.y - fPoint.y) >= this.O;
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setCustomTexture(com.amap.api.maps.model.BitmapDescriptor r7) {
        /*
        r6 = this;
        r0 = java.lang.System.nanoTime();
        r2 = 16;
        r4 = r6.d;
        r4 = r0 - r4;
        r2 = (long) r2;
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x0010;
    L_0x000f:
        return;
    L_0x0010:
        r6.d = r0;
        if (r7 == 0) goto L_0x000f;
    L_0x0014:
        monitor-enter(r6);
        r0 = r6.q;	 Catch:{ all -> 0x001f }
        r0 = r7.equals(r0);	 Catch:{ all -> 0x001f }
        if (r0 == 0) goto L_0x0022;
    L_0x001d:
        monitor-exit(r6);	 Catch:{ all -> 0x001f }
        goto L_0x000f;
    L_0x001f:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x001f }
        throw r0;
    L_0x0022:
        r0 = 0;
        r6.t = r0;	 Catch:{ all -> 0x001f }
        r0 = 0;
        r6.w = r0;	 Catch:{ all -> 0x001f }
        r0 = 1;
        r6.B = r0;	 Catch:{ all -> 0x001f }
        r6.q = r7;	 Catch:{ all -> 0x001f }
        r0 = r6.e;	 Catch:{ all -> 0x001f }
        r0 = r0.f();	 Catch:{ all -> 0x001f }
        r1 = 0;
        r0.setRunLowFrame(r1);	 Catch:{ all -> 0x001f }
        r0 = r6.U;	 Catch:{ all -> 0x001f }
        if (r0 == 0) goto L_0x0040;
    L_0x003b:
        r0 = r6.U;	 Catch:{ all -> 0x001f }
        r0.setCustomTexture(r7);	 Catch:{ all -> 0x001f }
    L_0x0040:
        monitor-exit(r6);	 Catch:{ all -> 0x001f }
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.cw.setCustomTexture(com.amap.api.maps.model.BitmapDescriptor):void");
    }

    public void a(MapConfig mapConfig) throws RemoteException {
        int i = 0;
        if (this.Z == 0) {
            this.Z = AMapNativePolyline.nativeCreate();
            if (!(this.Z == 0 || this.Y == null)) {
                AMapNativePolyline.nativeSetGLShaderManager(this.Z, this.Y.a());
            }
        }
        if (this.g != null && this.g.size() != 0 && this.G > 0.0f && this.e.f() != null) {
            int sx;
            int i2;
            synchronized (this.r) {
                sx = mapConfig.getSX();
                int sy = mapConfig.getSY();
                int beyond180Mode = mapConfig.getGeoRectangle().getBeyond180Mode();
                int size = this.h.size();
                int size2 = this.g.size();
                int i3;
                IPoint iPoint;
                if (size == size2) {
                    for (i3 = 0; i3 < size2; i3++) {
                        iPoint = (IPoint) this.g.get(i3);
                        FPoint fPoint = (FPoint) this.h.get(i3);
                        fPoint.x = (float) (iPoint.x - sx);
                        fPoint.y = (float) (iPoint.y - sy);
                        if (this.aa && (beyond180Mode < 0 || mapConfig.getSX() < AMapEngineUtils.HALF_MAX_P20_WIDTH)) {
                            fPoint.x -= 2.68435456E8f;
                        }
                    }
                } else {
                    this.h.clear();
                    i3 = 0;
                    i2 = 0;
                    while (i3 < size2) {
                        iPoint = (IPoint) this.g.get(i3);
                        FPoint3 fPoint3 = new FPoint3();
                        if (this.l != null) {
                            synchronized (this.l) {
                                if (this.l != null && this.l.size() > i2) {
                                    fPoint3.setColorIndex(((Integer) this.l.get(i2)).intValue());
                                }
                            }
                        }
                        fPoint3.x = (float) (iPoint.x - sx);
                        fPoint3.y = (float) (iPoint.y - sy);
                        if (this.aa && (beyond180Mode < 0 || mapConfig.getSX() < AMapEngineUtils.HALF_MAX_P20_WIDTH)) {
                            fPoint3.x -= 2.68435456E8f;
                        }
                        this.h.add(fPoint3);
                        i3++;
                        i2++;
                    }
                }
            }
            if (this.x) {
                b();
                this.x = false;
            } else if (this.y) {
                synchronized (this.r) {
                    i2 = this.h.size();
                    synchronized (this.l) {
                        sx = this.l.size();
                        while (i < i2) {
                            if (sx > i) {
                                ((FPoint3) this.h.get(i)).setColorIndex(((Integer) this.l.get(i)).intValue());
                            }
                            i++;
                        }
                    }
                }
            }
            if (this.P != null && this.E > 0) {
                b(this.e.f().getMapConfig());
            }
            this.z = true;
        }
    }

    private void b(MapConfig mapConfig) {
        float mapLenWithWin = this.e.f().c().getMapLenWithWin((int) this.G);
        switch (this.B) {
            case 0:
                f(mapLenWithWin, mapConfig);
                return;
            case 1:
                if (this.A) {
                    d(mapLenWithWin, mapConfig);
                    return;
                } else {
                    f(mapLenWithWin, mapConfig);
                    return;
                }
            case 2:
                if (this.F == -1) {
                    f(mapLenWithWin, mapConfig);
                    return;
                } else {
                    e(mapLenWithWin, mapConfig);
                    return;
                }
            case 3:
                c(mapLenWithWin, mapConfig);
                return;
            case 4:
                b(mapLenWithWin, mapConfig);
                return;
            case 5:
                if (this.A) {
                    a(mapLenWithWin, mapConfig);
                    return;
                } else {
                    c(mapLenWithWin, mapConfig);
                    return;
                }
            default:
                return;
        }
    }

    private void a(float f, MapConfig mapConfig) {
        if (!this.w) {
            try {
                if (this.j != null) {
                    this.R = new int[this.j.size()];
                    boolean z = VERSION.SDK_INT >= 12;
                    f();
                    int i = 0;
                    for (BitmapDescriptor a : this.j) {
                        this.R[i] = a(z, a, false);
                        i++;
                    }
                    this.w = true;
                }
            } catch (Throwable th) {
                gz.c(th, "MarkerDelegateImp", "loadtexture");
                return;
            }
        }
        FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
        List list = this.h;
        if (a(clipMapRect)) {
            synchronized (this.r) {
                list = en.b(clipMapRect, this.h, false);
            }
        }
        try {
            if (list.size() >= 2) {
                int[] iArr;
                int i2;
                d(list);
                synchronized (this.n) {
                    iArr = new int[this.n.size()];
                    for (i2 = 0; i2 < iArr.length; i2++) {
                        int intValue = ((Integer) this.n.get(i2)).intValue();
                        if (intValue < 0) {
                            intValue = 0;
                        }
                        iArr[i2] = this.R[intValue];
                    }
                }
                if (iArr != null) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (((this.Q != null ? 1 : 0) & i2) != 0) {
                    AMapNativeRenderer.nativeDrawLineByMultiTextureID(this.P, this.b, f, iArr, iArr.length, this.Q, this.Q.length, 1.0f - this.N, this.e.g(), this.X.getTypeValue(), this.W.getTypeValue());
                }
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    private void f() {
        if (this.k != null) {
            for (f fVar : this.k) {
                if (!(fVar == null || this.e == null)) {
                    this.e.a(fVar);
                }
            }
            this.k.clear();
        }
    }

    private void a(f fVar) {
        if (fVar != null) {
            this.k.add(fVar);
            fVar.g();
        }
    }

    private int a(boolean z, BitmapDescriptor bitmapDescriptor, boolean z2) {
        if (z2) {
            f();
        }
        f fVar = null;
        if (z) {
            fVar = this.e.a(bitmapDescriptor);
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
        f = g();
        if (z) {
            fVar.a(f);
            this.e.f().a(fVar);
        }
        a(fVar);
        en.b(f, bitmap, true);
        return f;
    }

    private void b(float f, MapConfig mapConfig) {
        int i;
        int i2 = 0;
        int[] iArr = new int[this.m.size()];
        for (i = 0; i < this.m.size(); i++) {
            iArr[i] = ((Integer) this.m.get(i)).intValue();
        }
        FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
        List list = this.h;
        if (a(clipMapRect)) {
            synchronized (this.r) {
                list = en.b(clipMapRect, this.h, false);
            }
        }
        try {
            if (list.size() >= 2) {
                int i3;
                d(list);
                iArr = new int[this.o.size()];
                for (i = 0; i < iArr.length; i++) {
                    iArr[i] = ((Integer) this.o.get(i)).intValue();
                }
                if (iArr != null) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                if (this.Q != null) {
                    i2 = 1;
                }
                if ((i3 & i2) != 0) {
                    AMapNativeRenderer.nativeDrawGradientColorLine(this.P, this.b, f, iArr, iArr.length, this.Q, this.Q.length, this.e.f().d(), this.e.g(), this.X.getTypeValue(), this.W.getTypeValue());
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void c(float f, MapConfig mapConfig) {
        int i;
        int i2 = 0;
        int[] iArr = new int[this.m.size()];
        for (i = 0; i < this.m.size(); i++) {
            iArr[i] = ((Integer) this.m.get(i)).intValue();
        }
        FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
        List list = this.h;
        if (a(clipMapRect)) {
            synchronized (this.r) {
                list = en.b(clipMapRect, this.h, false);
            }
        }
        try {
            if (list.size() >= 2) {
                int i3;
                d(list);
                iArr = new int[this.o.size()];
                for (i = 0; i < iArr.length; i++) {
                    iArr[i] = ((Integer) this.o.get(i)).intValue();
                }
                if (iArr != null) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                if (this.Q != null) {
                    i2 = 1;
                }
                if ((i3 & i2) != 0) {
                    AMapNativeRenderer.nativeDrawLineByMultiColor(this.P, this.b, f, this.e.f().d(), iArr, iArr.length, this.Q, this.Q.length, this.e.g(), this.X.getTypeValue(), this.W.getTypeValue());
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void d(float f, MapConfig mapConfig) {
        if (!this.w) {
            synchronized (this) {
                try {
                    if (this.q != null) {
                        this.C = a(VERSION.SDK_INT >= 12, this.q, true);
                        this.w = true;
                    }
                } catch (Throwable th) {
                    gz.c(th, "MarkerDelegateImp", "loadtexture");
                    return;
                }
            }
        }
        try {
            if (mapConfig.getChangeRatio() == 1.0d && this.P != null) {
                this.V++;
                if (this.V > 2) {
                    AMapNativeRenderer.nativeDrawLineByTextureID(this.P, this.b, f, this.C, this.K, this.L, this.M, this.J, 1.0f - this.N, false, false, false, this.e.g(), this.X.getTypeValue(), this.W.getTypeValue());
                    return;
                }
            }
            this.V = 0;
            FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
            List list = this.h;
            if (a(clipMapRect)) {
                synchronized (this.r) {
                    list = en.a(clipMapRect, this.h, false);
                }
            }
            if (list.size() >= 2) {
                d(list);
                AMapNativeRenderer.nativeDrawLineByTextureID(this.P, this.b, f, this.C, this.K, this.L, this.M, this.J, 1.0f - this.N, false, false, false, this.e.g(), this.X.getTypeValue(), this.W.getTypeValue());
            }
        } catch (Throwable th2) {
        }
    }

    private void e(float f, MapConfig mapConfig) {
        if (!this.w) {
            synchronized (this) {
                try {
                    if (this.q != null) {
                        this.C = a(VERSION.SDK_INT >= 12, this.q, true);
                        this.w = true;
                    }
                } catch (Throwable th) {
                    gz.c(th, "MarkerDelegateImp", "loadtexture");
                    return;
                }
            }
        }
        try {
            List list = this.h;
            if (this.e.f() != null) {
                if (mapConfig.getChangeRatio() == 1.0d && this.P != null) {
                    this.V++;
                    if (this.V > 2) {
                        AMapNativeRenderer.nativeDrawLineByTextureID(this.P, this.b, f, this.e.f().f(this.F), this.K, this.L, this.M, this.J, 0.0f, true, true, false, this.e.g(), this.X.getTypeValue(), this.W.getTypeValue());
                        return;
                    }
                }
                this.V = 0;
                FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
                if (a(clipMapRect)) {
                    synchronized (this.r) {
                        list = en.a(clipMapRect, this.h, false);
                    }
                }
                if (list.size() >= 2) {
                    d(list);
                    AMapNativeRenderer.nativeDrawLineByTextureID(this.P, this.b, f, this.e.f().f(this.F), this.K, this.L, this.M, this.J, 0.0f, true, true, false, this.e.g(), this.X.getTypeValue(), this.W.getTypeValue());
                }
            }
        } catch (Throwable th2) {
        }
    }

    private void f(float f, MapConfig mapConfig) {
        try {
            List list = this.h;
            if (this.e.f() != null) {
                if (mapConfig.getChangeRatio() == 1.0d && this.P != null) {
                    this.V++;
                    if (this.V > 2) {
                        if (this.Z == 0 || this.Y == null) {
                            AMapNativeRenderer.nativeDrawLineByTextureID(this.P, this.b, f, this.e.f().d(), this.K, this.L, this.M, this.J, 0.0f, false, true, false, this.e.g(), this.X.getTypeValue(), this.W.getTypeValue());
                            return;
                        }
                        AMapNativePolyline.nativeDrawLineByTextureID(this.Z, this.P, this.b, f, this.e.f().d(), this.K, this.L, this.M, this.J, 0.0f, false, true, false, this.e.g(), this.X.getTypeValue(), this.W.getTypeValue());
                        return;
                    }
                }
                this.V = 0;
                FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
                if (a(clipMapRect)) {
                    synchronized (this.r) {
                        list = en.a(clipMapRect, this.h, false);
                    }
                }
                if (list.size() >= 2) {
                    d(list);
                    if (this.Z == 0 || this.Y == null) {
                        AMapNativeRenderer.nativeDrawLineByTextureID(this.P, this.b, f, this.e.f().d(), this.K, this.L, this.M, this.J, 0.0f, false, true, false, this.e.g(), this.X.getTypeValue(), this.W.getTypeValue());
                        return;
                    }
                    AMapNativePolyline.nativeDrawLineByTextureID(this.Z, this.P, this.b, f, this.e.f().d(), this.K, this.L, this.M, this.J, 0.0f, false, true, false, this.e.g(), this.X.getTypeValue(), this.W.getTypeValue());
                }
            }
        } catch (Throwable th) {
        }
    }

    private int g() {
        int[] iArr = new int[]{0};
        GLES20.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    private boolean a(FPoint[] fPointArr) {
        this.I = this.e.f().g();
        int i = 3;
        e();
        if (this.g.size() > ByteBufferUtils.ERROR_CODE) {
            i = 7;
        }
        if (this.I <= ((float) i)) {
            return false;
        }
        try {
            if (this.e.f() == null) {
                return false;
            }
            if (en.a(this.T.northeast, fPointArr) && en.a(this.T.southwest, fPointArr)) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public void destroy() {
        try {
            remove();
            if (this.k != null && this.k.size() > 0) {
                for (int i = 0; i < this.k.size(); i++) {
                    f fVar = (f) this.k.get(i);
                    if (fVar != null) {
                        this.e.a(fVar);
                        this.e.f().c(fVar.j());
                    }
                }
                this.k.clear();
            }
            if (this.P != null) {
                this.P = null;
            }
            if (this.p != null) {
                this.p.clear();
                this.p = null;
            }
            if (this.j != null && this.j.size() > 0) {
                for (BitmapDescriptor recycle : this.j) {
                    recycle.recycle();
                }
            }
            synchronized (this) {
                if (this.q != null) {
                    this.q.recycle();
                }
            }
            if (this.m != null) {
                this.m.clear();
                this.m = null;
            }
            if (this.l != null) {
                synchronized (this.l) {
                    this.l.clear();
                    this.l = null;
                }
            }
            if (this.i != null) {
                this.i.clear();
                this.i = null;
            }
            this.U = null;
            if (this.Z != 0) {
                AMapNativePolyline.nativeDestroy(this.Z);
            }
        } catch (Throwable th) {
            gz.c(th, "PolylineDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "PolylineDelegateImp destroy");
        }
    }

    public boolean c() {
        return this.z;
    }

    public LatLng getNearestLatLng(LatLng latLng) {
        int i = 0;
        if (latLng == null) {
            return null;
        }
        if (this.i == null || this.i.size() == 0) {
            return null;
        }
        float f = 0.0f;
        int i2 = 0;
        while (i < this.i.size()) {
            try {
                float calculateLineDistance;
                int i3;
                if (i == 0) {
                    calculateLineDistance = AMapUtils.calculateLineDistance(latLng, (LatLng) this.i.get(i));
                    i3 = i2;
                } else {
                    calculateLineDistance = AMapUtils.calculateLineDistance(latLng, (LatLng) this.i.get(i));
                    if (f > calculateLineDistance) {
                        i3 = i;
                    } else {
                        calculateLineDistance = f;
                        i3 = i2;
                    }
                }
                i++;
                i2 = i3;
                f = calculateLineDistance;
            } catch (Throwable th) {
                gz.c(th, "PolylineDelegateImp", "getNearestLatLng");
                th.printStackTrace();
                return null;
            }
        }
        return (LatLng) this.i.get(i2);
    }

    public boolean a(LatLng latLng) {
        Object obj = new float[this.P.length];
        System.arraycopy(this.P, 0, obj, 0, this.P.length);
        if (obj.length / 3 < 2) {
            return false;
        }
        try {
            ArrayList h = h();
            if (h == null || h.size() < 1) {
                return false;
            }
            double mapLenWithWin = (double) this.e.f().c().getMapLenWithWin(((int) this.G) / 4);
            double mapLenWithWin2 = (double) this.e.f().c().getMapLenWithWin(5);
            FPoint b = b(latLng);
            FPoint fPoint = null;
            for (int i = 0; i < h.size() - 1; i++) {
                FPoint fPoint2;
                if (i == 0) {
                    fPoint2 = (FPoint) h.get(i);
                } else {
                    fPoint2 = fPoint;
                }
                fPoint = (FPoint) h.get(i + 1);
                if ((mapLenWithWin2 + mapLenWithWin) - a(b, fPoint2, fPoint) >= Utils.DOUBLE_EPSILON) {
                    h.clear();
                    return true;
                }
            }
            h.clear();
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private ArrayList<FPoint> h() {
        ArrayList<FPoint> arrayList = new ArrayList();
        int i = 0;
        while (i < this.P.length) {
            float f = this.P[i];
            i++;
            float f2 = this.P[i];
            i++;
            arrayList.add(FPoint.obtain(f, f2));
            i++;
        }
        return arrayList;
    }

    private double a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3) {
        return a((double) fPoint.x, (double) fPoint.y, (double) fPoint2.x, (double) fPoint2.y, (double) fPoint3.x, (double) fPoint3.y);
    }

    private FPoint b(LatLng latLng) {
        IPoint obtain = IPoint.obtain();
        this.e.f().a(latLng.latitude, latLng.longitude, obtain);
        FPoint obtain2 = FPoint.obtain();
        this.e.f().a(obtain.x, obtain.y, obtain2);
        obtain.recycle();
        return obtain2;
    }

    private double a(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = ((d5 - d3) * (d - d3)) + ((d6 - d4) * (d2 - d4));
        if (d7 <= Utils.DOUBLE_EPSILON) {
            return Math.sqrt(((d - d3) * (d - d3)) + ((d2 - d4) * (d2 - d4)));
        }
        double d8 = ((d5 - d3) * (d5 - d3)) + ((d6 - d4) * (d6 - d4));
        if (d7 >= d8) {
            return Math.sqrt(((d - d5) * (d - d5)) + ((d2 - d6) * (d2 - d6)));
        }
        d7 /= d8;
        d8 = ((d5 - d3) * d7) + d3;
        d7 = (d7 * (d6 - d4)) + d4;
        return Math.sqrt(((d7 - d2) * (d7 - d2)) + ((d - d8) * (d - d8)));
    }

    public void setTransparency(float f) {
        this.N = (float) Math.min(1.0d, Math.max(Utils.DOUBLE_EPSILON, (double) f));
        this.e.f().setRunLowFrame(false);
    }

    public void b(List<BitmapDescriptor> list) {
        if (list != null && list.size() != 0) {
            if (list.size() > 1) {
                this.t = false;
                this.B = 5;
                this.j = list;
                this.e.f().setRunLowFrame(false);
                return;
            }
            setCustomTexture((BitmapDescriptor) list.get(0));
        }
    }

    public void setCustemTextureIndex(List<Integer> list) {
        if (list != null && list.size() != 0) {
            try {
                synchronized (this.l) {
                    this.l.clear();
                    this.l.addAll(list);
                    this.n = e(list);
                    this.y = true;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void c(List<Integer> list) {
        if (list != null && list.size() != 0) {
            this.m = list;
            if (list.size() > 1) {
                this.t = false;
                this.o = e(list);
                this.B = 3;
                this.e.f().setRunLowFrame(false);
                return;
            }
            setColor(((Integer) list.get(0)).intValue());
        }
    }

    public void b(boolean z) {
        if (z && this.m != null && this.m.size() > 1) {
            this.B = 4;
            this.e.f().setRunLowFrame(false);
        }
    }

    private List<Integer> e(List<Integer> list) {
        Object obj = new int[list.size()];
        List<Integer> arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            int intValue = ((Integer) list.get(i3)).intValue();
            if (i3 == 0) {
                arrayList.add(Integer.valueOf(intValue));
            } else if (intValue != i2) {
                arrayList.add(Integer.valueOf(intValue));
            } else {
            }
            obj[i] = i3;
            i++;
            i2 = intValue;
        }
        this.Q = new int[arrayList.size()];
        System.arraycopy(obj, 0, this.Q, 0, this.Q.length);
        return arrayList;
    }

    public void d() {
        this.w = false;
        this.C = 0;
        if (this.R != null) {
            Arrays.fill(this.R, 0);
        }
    }

    public void setAboveMaskLayer(boolean z) {
        this.S = z;
    }

    public boolean isAboveMaskLayer() {
        return this.S;
    }

    public void setOptions(PolylineOptions polylineOptions) {
        if (polylineOptions != null) {
            this.U = polylineOptions;
            try {
                setColor(polylineOptions.getColor());
                setGeodesic(polylineOptions.isGeodesic());
                setDottedLine(polylineOptions.isDottedLine());
                a(polylineOptions.getDottedLineType());
                setAboveMaskLayer(polylineOptions.isAboveMaskLayer());
                setVisible(polylineOptions.isVisible());
                setWidth(polylineOptions.getWidth());
                setZIndex(polylineOptions.getZIndex());
                a(polylineOptions.isUseTexture());
                setTransparency(polylineOptions.getTransparency());
                a(polylineOptions.getLineCapType());
                a(polylineOptions.getLineJoinType());
                if (polylineOptions.getColorValues() != null) {
                    c(polylineOptions.getColorValues());
                    b(polylineOptions.isUseGradient());
                }
                if (polylineOptions.getCustomTexture() != null) {
                    setCustomTexture(polylineOptions.getCustomTexture());
                    d();
                }
                if (polylineOptions.getCustomTextureList() != null) {
                    b(polylineOptions.getCustomTextureList());
                    setCustemTextureIndex(polylineOptions.getCustomTextureIndex());
                    d();
                }
                setPoints(polylineOptions.getPoints());
            } catch (Throwable e) {
                gz.c(e, "PolylineDelegateImp", "setOptions");
                e.printStackTrace();
            }
        }
    }

    public PolylineOptions getOptions() {
        return this.U;
    }

    public void a(LineJoinType lineJoinType) {
        this.W = lineJoinType;
    }

    public void a(LineCapType lineCapType) {
        this.X = lineCapType;
    }

    public void a(db dbVar) {
        this.Y = dbVar;
    }
}
