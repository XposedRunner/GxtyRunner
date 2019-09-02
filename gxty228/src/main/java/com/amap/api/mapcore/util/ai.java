package com.amap.api.mapcore.util;

import android.graphics.Rect;
import android.opengl.Matrix;
import android.os.RemoteException;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MultiPointItem;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

/* compiled from: MultiPointOverlayDelegate */
public class ai implements IMultiPointOverlay {
    private static int E = 0;
    int A = -1;
    private String B;
    private float[] C = new float[]{-0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f};
    private boolean D = true;
    private ExecutorService F = null;
    private List<String> G = new ArrayList();
    private float[] H = new float[(ae.a * 3)];
    BitmapDescriptor a = BitmapDescriptorFactory.defaultMarker();
    BitmapDescriptor b = null;
    float c = 0.0f;
    float d = 0.0f;
    float e = 0.0f;
    float f = 0.5f;
    float g = 0.5f;
    List<MultiPointItem> h;
    ak i = null;
    ah j = null;
    ah k = new ah(0, 1, 0, 1);
    List<MultiPointItem> l = new ArrayList();
    IPoint m;
    aj n;
    List<ae> o = new ArrayList();
    float[] p = new float[16];
    float[] q = new float[4];
    float[] r = new float[4];
    Rect s = new Rect();
    ah t = null;
    ah u = null;
    int v = 0;
    int w = 0;
    float[] x = new float[12];
    String y = "precision highp float;\nattribute vec3 aVertex;//顶点数组,三维坐标\nuniform mat4 aMVPMatrix;//mvp矩阵\nvoid main(){\n  gl_Position = aMVPMatrix * vec4(aVertex, 1.0);\n}";
    String z = "//有颜色 没有纹理\nprecision highp float;\nvoid main(){\n  gl_FragColor = vec4(0,0,1,1.0);\n}";

    public ai(MultiPointOverlayOptions multiPointOverlayOptions, aj ajVar) {
        this.n = ajVar;
        a(multiPointOverlayOptions);
        ae aeVar = new ae(a(), this);
        aeVar.a(ajVar.a());
        aeVar.a(this.b);
        this.o.add(aeVar);
    }

    private float[] a() {
        if (this.C == null) {
            return null;
        }
        float[] fArr = (float[]) this.C.clone();
        float f = this.f - 0.5f;
        float f2 = this.g - 0.5f;
        fArr[0] = fArr[0] + f;
        fArr[1] = fArr[1] - f2;
        fArr[6] = fArr[6] + f;
        fArr[7] = fArr[7] - f2;
        fArr[12] = fArr[12] + f;
        fArr[13] = fArr[13] - f2;
        fArr[18] = f + fArr[18];
        fArr[19] = fArr[19] - f2;
        return fArr;
    }

    private static String a(String str) {
        E++;
        return str + E;
    }

    private void a(MultiPointOverlayOptions multiPointOverlayOptions) {
        if (multiPointOverlayOptions != null) {
            if (multiPointOverlayOptions.getIcon() == null || multiPointOverlayOptions.getIcon().getBitmap() == null || multiPointOverlayOptions.getIcon().getBitmap().isRecycled()) {
                this.b = this.a;
            } else {
                this.b = multiPointOverlayOptions.getIcon();
            }
            this.f = multiPointOverlayOptions.getAnchorU();
            this.g = multiPointOverlayOptions.getAnchorV();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addItems(java.util.List<com.amap.api.maps.model.MultiPointItem> r11) {
        /*
        r10 = this;
        r1 = 0;
        if (r11 == 0) goto L_0x0009;
    L_0x0003:
        r0 = r11.size();	 Catch:{ Throwable -> 0x0032 }
        if (r0 != 0) goto L_0x000a;
    L_0x0009:
        return;
    L_0x000a:
        monitor-enter(r10);	 Catch:{ Throwable -> 0x0032 }
        r0 = r10.h;	 Catch:{ all -> 0x002f }
        if (r0 != 0) goto L_0x0016;
    L_0x000f:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x002f }
        r0.<init>();	 Catch:{ all -> 0x002f }
        r10.h = r0;	 Catch:{ all -> 0x002f }
    L_0x0016:
        r0 = r10.h;	 Catch:{ all -> 0x002f }
        r0.clear();	 Catch:{ all -> 0x002f }
        r0 = r10.h;	 Catch:{ all -> 0x002f }
        r0.addAll(r11);	 Catch:{ all -> 0x002f }
        r0 = r10.h;	 Catch:{ all -> 0x002f }
        r3 = r0.size();	 Catch:{ all -> 0x002f }
        r2 = r1;
    L_0x0027:
        if (r2 >= r3) goto L_0x006c;
    L_0x0029:
        r0 = r10.h;	 Catch:{ all -> 0x002f }
        if (r0 != 0) goto L_0x003b;
    L_0x002d:
        monitor-exit(r10);	 Catch:{ all -> 0x002f }
        goto L_0x0009;
    L_0x002f:
        r0 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x002f }
        throw r0;	 Catch:{ Throwable -> 0x0032 }
    L_0x0032:
        r0 = move-exception;
        r1 = "MultiPointOverlayDelegate";
        r2 = "addItems";
        com.amap.api.mapcore.util.gz.c(r0, r1, r2);
        goto L_0x0009;
    L_0x003b:
        r0 = r10.h;	 Catch:{ all -> 0x002f }
        r0 = r0.get(r2);	 Catch:{ all -> 0x002f }
        r0 = (com.amap.api.maps.model.MultiPointItem) r0;	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x0068;
    L_0x0045:
        r4 = r0.getLatLng();	 Catch:{ all -> 0x002f }
        if (r4 == 0) goto L_0x0068;
    L_0x004b:
        r4 = r0.getIPoint();	 Catch:{ all -> 0x002f }
        if (r4 != 0) goto L_0x0068;
    L_0x0051:
        r4 = new com.autonavi.amap.mapcore.IPoint;	 Catch:{ all -> 0x002f }
        r4.<init>();	 Catch:{ all -> 0x002f }
        r5 = r0.getLatLng();	 Catch:{ all -> 0x002f }
        r6 = r5.longitude;	 Catch:{ all -> 0x002f }
        r5 = r0.getLatLng();	 Catch:{ all -> 0x002f }
        r8 = r5.latitude;	 Catch:{ all -> 0x002f }
        com.autonavi.amap.mapcore.MapProjection.lonlat2Geo(r6, r8, r4);	 Catch:{ all -> 0x002f }
        r0.setIPoint(r4);	 Catch:{ all -> 0x002f }
    L_0x0068:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0027;
    L_0x006c:
        r0 = r10.i;	 Catch:{ all -> 0x002f }
        if (r0 != 0) goto L_0x007d;
    L_0x0070:
        r0 = r10.b();	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x007d;
    L_0x0076:
        r2 = new com.amap.api.mapcore.util.ak;	 Catch:{ all -> 0x002f }
        r2.<init>(r0);	 Catch:{ all -> 0x002f }
        r10.i = r2;	 Catch:{ all -> 0x002f }
    L_0x007d:
        r0 = r10.h;	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x00a6;
    L_0x0081:
        r0 = r10.h;	 Catch:{ all -> 0x002f }
        r2 = r0.size();	 Catch:{ all -> 0x002f }
    L_0x0087:
        if (r1 >= r2) goto L_0x00a6;
    L_0x0089:
        r0 = r10.h;	 Catch:{ all -> 0x002f }
        r0 = r0.get(r1);	 Catch:{ all -> 0x002f }
        r0 = (com.amap.api.maps.model.MultiPointItem) r0;	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x00a2;
    L_0x0093:
        r3 = r0.getIPoint();	 Catch:{ all -> 0x002f }
        if (r3 == 0) goto L_0x00a2;
    L_0x0099:
        r3 = r10.i;	 Catch:{ all -> 0x002f }
        if (r3 == 0) goto L_0x00a2;
    L_0x009d:
        r3 = r10.i;	 Catch:{ all -> 0x002f }
        r3.a(r0);	 Catch:{ all -> 0x002f }
    L_0x00a2:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0087;
    L_0x00a6:
        monitor-exit(r10);	 Catch:{ all -> 0x002f }
        r10.d();	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ai.addItems(java.util.List):void");
    }

    public void addItem(MultiPointItem multiPointItem) {
        d();
    }

    private ah b() {
        if (this.h == null || this.h.size() == 0) {
            return null;
        }
        Iterator it = this.h.iterator();
        MultiPointItem multiPointItem = (MultiPointItem) it.next();
        int i = multiPointItem.getIPoint().x;
        int i2 = multiPointItem.getIPoint().x;
        int i3 = i;
        i = i2;
        i2 = multiPointItem.getIPoint().y;
        int i4 = multiPointItem.getIPoint().y;
        while (it.hasNext()) {
            multiPointItem = (MultiPointItem) it.next();
            int i5 = multiPointItem.getIPoint().x;
            int i6 = multiPointItem.getIPoint().y;
            if (i5 < i3) {
                i3 = i5;
            }
            if (i5 > i) {
                i = i5;
            }
            if (i6 < i2) {
                i2 = i6;
            }
            if (i6 <= i4) {
                i6 = i4;
            }
            i4 = i6;
        }
        return new ah(i3, i, i2, i4);
    }

    public void draw(MapConfig mapConfig, float[] fArr, float[] fArr2) {
        try {
            if (this.D) {
                c();
                if (this.o.size() >= 1 && this.i != null && mapConfig != null) {
                    int i;
                    float sr = mapConfig.getSR();
                    float sc = mapConfig.getSC();
                    if (mapConfig.getChangeRatio() != 1.0d || this.l.size() == 0) {
                        synchronized (this.l) {
                            a(mapConfig);
                            this.l.clear();
                            this.c = mapConfig.getMapPerPixelUnitLength();
                            this.d = this.c * ((float) this.b.getWidth());
                            this.e = this.c * ((float) this.b.getHeight());
                            double d = (double) ((this.d * this.e) * 16.0f);
                            a(this.d, this.e, sr, sc);
                            this.i.a(this.j, this.l, d);
                        }
                    }
                    if (this.m == null) {
                        this.m = new IPoint();
                    }
                    if (!(this.m == null || mapConfig == null)) {
                        this.m.x = mapConfig.getSX();
                        this.m.y = mapConfig.getSY();
                    }
                    ae aeVar = (ae) this.o.get(0);
                    synchronized (this.l) {
                        i = 0;
                        for (MultiPointItem iPoint : this.l) {
                            IPoint iPoint2 = iPoint.getIPoint();
                            if (iPoint2 != null) {
                                int i2 = iPoint2.x - this.m.x;
                                int i3 = iPoint2.y - this.m.y;
                                if (aeVar != null && aeVar.b()) {
                                    if (!(aeVar.d() || this.n == null)) {
                                        aeVar.a(this.n.a());
                                    }
                                    this.H[(i * 3) + 0] = (float) i2;
                                    this.H[(i * 3) + 1] = (float) i3;
                                    this.H[(i * 3) + 2] = 0.0f;
                                    i++;
                                    if (i >= ae.a) {
                                        aeVar.a(fArr, fArr2, this.H, this.d, this.e, sr, sc, i);
                                        i = 0;
                                    }
                                }
                            }
                        }
                    }
                    if (i > 0) {
                        aeVar.a(fArr, fArr2, this.H, this.d, this.e, sr, sc, i);
                    }
                }
            }
        } catch (Throwable th) {
            gz.c(th, "MultiPointOverlayDelegate", "draw");
        }
    }

    private void c() {
        if (this.F == null) {
            long j = (long) 1;
            this.F = new ThreadPoolExecutor(1, 2, j, TimeUnit.SECONDS, new LinkedBlockingQueue(), new dy("MultiPointOverlay"), new AbortPolicy());
        }
        for (final ae aeVar : this.o) {
            if (!(aeVar == null || aeVar.b())) {
                final String str = aeVar.hashCode() + "";
                if (!this.G.contains(str)) {
                    this.G.add(str);
                    this.F.execute(new Runnable(this) {
                        final /* synthetic */ ai c;

                        public void run() {
                            if (!aeVar.b()) {
                                aeVar.a();
                                this.c.G.remove(str);
                            }
                        }
                    });
                }
            }
        }
    }

    private void a(MapConfig mapConfig) {
        if (mapConfig != null) {
            Rect rect = mapConfig.getGeoRectangle().getRect();
            if (this.j == null) {
                this.j = new ah(rect.left, rect.right, rect.top, rect.bottom);
            } else {
                this.j.a(rect.left, rect.right, rect.top, rect.bottom);
            }
        }
    }

    private void a(float f, float f2, float f3, float f4) {
        if (this.k == null) {
            this.k = new ah(0, 1, 0, 1);
        }
        this.s.set(0, 0, 0, 0);
        IPoint iPoint = new IPoint();
        float f5 = this.f;
        float f6 = this.g;
        Matrix.setIdentityM(this.p, 0);
        Matrix.rotateM(this.p, 0, -f3, 0.0f, 0.0f, 1.0f);
        this.r[0] = 0.0f;
        this.r[1] = 0.0f;
        this.r[2] = 0.0f;
        this.r[3] = 0.0f;
        this.q[0] = (-f) * f5;
        this.q[1] = f2 * f6;
        this.q[2] = 0.0f;
        this.q[3] = 1.0f;
        Matrix.multiplyMV(this.r, 0, this.p, 0, this.q, 0);
        this.s.set((int) (((float) iPoint.x) + this.r[0]), (int) (((float) iPoint.y) - this.r[1]), (int) (((float) iPoint.x) + this.r[0]), (int) (((float) iPoint.y) - this.r[1]));
        this.q[0] = (1.0f - f5) * f;
        this.q[1] = f2 * f6;
        this.q[2] = 0.0f;
        this.q[3] = 1.0f;
        Matrix.multiplyMV(this.r, 0, this.p, 0, this.q, 0);
        this.s.union((int) (((float) iPoint.x) + this.r[0]), (int) (((float) iPoint.y) - this.r[1]));
        this.q[0] = (1.0f - f5) * f;
        this.q[1] = (-f2) * (1.0f - f6);
        this.q[2] = 0.0f;
        this.q[3] = 1.0f;
        Matrix.multiplyMV(this.r, 0, this.p, 0, this.q, 0);
        this.s.union((int) (((float) iPoint.x) + this.r[0]), (int) (((float) iPoint.y) - this.r[1]));
        this.q[0] = (-f) * f5;
        this.q[1] = (-f2) * (1.0f - f6);
        this.q[2] = 0.0f;
        this.q[3] = 1.0f;
        Matrix.multiplyMV(this.r, 0, this.p, 0, this.q, 0);
        this.s.union((int) (((float) iPoint.x) + this.r[0]), (int) (((float) iPoint.y) - this.r[1]));
        this.k.a(this.s.left, this.s.right, this.s.top, this.s.bottom);
    }

    public MultiPointItem onClick(IPoint iPoint) {
        if (!this.D || this.i == null) {
            return null;
        }
        if (this.t == null) {
            this.t = new ah(0, 1, 0, 1);
        }
        int i = (int) (this.c * 8.0f);
        this.t.a(iPoint.x - i, iPoint.x + i, iPoint.y - i, i + iPoint.y);
        synchronized (this.l) {
            for (int size = this.l.size() - 1; size >= 0; size--) {
                MultiPointItem multiPointItem = (MultiPointItem) this.l.get(size);
                IPoint iPoint2 = multiPointItem.getIPoint();
                if (iPoint2 != null) {
                    if (this.k == null) {
                        return null;
                    }
                    if (this.u == null) {
                        this.u = new ah(0, 1, 0, 1);
                    }
                    this.u.a(iPoint2.x + this.k.a, iPoint2.x + this.k.c, iPoint2.y + this.k.b, iPoint2.y + this.k.d);
                    if (this.u.a(this.t)) {
                        return multiPointItem;
                    }
                }
            }
            return null;
        }
    }

    public void setAnchor(float f, float f2) {
        this.f = f;
        this.g = f2;
        d();
    }

    public String getId() throws RemoteException {
        if (this.B == null) {
            this.B = a("MultiPointOverlay");
        }
        return this.B;
    }

    public void remove(boolean z) {
        this.D = false;
        this.v = 0;
        this.w = 0;
        if (this.a != null) {
            this.a.recycle();
        }
        synchronized (this) {
            if (this.h != null) {
                this.h.clear();
                this.h = null;
            }
        }
        if (this.i != null) {
            this.i.a();
            this.i = null;
        }
        if (this.l != null) {
            this.l.clear();
        }
        if (this.F != null) {
            this.F.shutdownNow();
            this.F = null;
        }
        if (this.G != null) {
            this.G.clear();
        }
        if (this.o != null) {
            for (ae aeVar : this.o) {
                if (aeVar != null) {
                    aeVar.c();
                }
            }
            this.o.clear();
        }
        if (z && this.n != null) {
            this.n.a(this);
            this.n.d();
        }
        this.n = null;
        this.C = null;
    }

    public void setVisible(boolean z) {
        if (this.D != z) {
            d();
        }
        this.D = z;
    }

    private void d() {
        if (this.n != null) {
            this.n.d();
        }
    }

    public void destroy(boolean z) {
        remove(z);
        if (this.b != null) {
            this.b.recycle();
        }
    }
}
