package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteException;
import android.util.Log;
import android.view.MotionEvent;
import com.amap.api.mapcore.util.db.d;
import com.amap.api.mapcore.util.fl.c;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.interfaces.IMarker;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.blankj.utilcode.constant.TimeConstants;
import java.io.Serializable;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: MapOverlayImageView */
public final class z {
    lj a;
    float[] b = new float[180000];
    public d c;
    int d = 0;
    int e = 0;
    private List<cn> f = new ArrayList(500);
    private List<f> g = new ArrayList();
    private List<cn> h = new ArrayList();
    private a i = new a();
    private boolean j = true;
    private IPoint k;
    private kn l;
    private ck m;
    private fl n;
    private dz o;
    private FloatBuffer p;
    private HandlerThread q;
    private Handler r;
    private int[] s = new int[1];
    private Runnable t = new Runnable(this) {
        final /* synthetic */ z a;

        {
            this.a = r1;
        }

        public void run() {
            synchronized (this.a.f) {
                this.a.k();
            }
        }
    };

    /* compiled from: MapOverlayImageView */
    static class a implements Serializable, Comparator<Object> {
        a() {
        }

        public int compare(Object obj, Object obj2) {
            cn cnVar = (cn) obj;
            cn cnVar2 = (cn) obj2;
            if (!(cnVar == null || cnVar2 == null)) {
                try {
                    return Float.compare(cnVar.getZIndex(), cnVar2.getZIndex());
                } catch (Throwable th) {
                    gz.c(th, "MapOverlayImageView", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    public z(Context context, lj ljVar) {
        this.a = ljVar;
        this.n = new fl(512, 1024);
        this.o = new dz();
        this.q = new HandlerThread("AMapZindexSortThread");
        this.q.start();
        this.r = new Handler(this.q.getLooper());
    }

    public Marker a(MarkerOptions markerOptions) throws RemoteException {
        if (markerOptions == null) {
            return null;
        }
        Marker marker;
        Object csVar = new cs(markerOptions, this);
        synchronized (this.f) {
            d(csVar);
            dv.a(this.f.size());
            marker = new Marker(csVar);
        }
        return marker;
    }

    public ArrayList<Marker> a(ArrayList<MarkerOptions> arrayList, boolean z) throws RemoteException {
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        ArrayList<Marker> arrayList2 = new ArrayList();
        try {
            MarkerOptions markerOptions;
            if (arrayList.size() == 1) {
                markerOptions = (MarkerOptions) arrayList.get(0);
                if (markerOptions != null) {
                    arrayList2.add(a(markerOptions));
                    if (z && markerOptions.getPosition() != null) {
                        this.a.a(p.a(markerOptions.getPosition(), 18.0f));
                    }
                    return arrayList2;
                }
            }
            final Builder builder = LatLngBounds.builder();
            for (int i = 0; i < arrayList.size(); i++) {
                markerOptions = (MarkerOptions) arrayList.get(i);
                if (arrayList.get(i) != null) {
                    arrayList2.add(a(markerOptions));
                    if (markerOptions.getPosition() != null) {
                        builder.include(markerOptions.getPosition());
                    }
                }
            }
            if (z && arrayList2.size() > 0) {
                this.a.getMainHandler().postDelayed(new Runnable(this) {
                    final /* synthetic */ z b;

                    public void run() {
                        try {
                            this.b.a.a(p.a(builder.build(), 50));
                        } catch (Throwable th) {
                        }
                    }
                }, 50);
            }
            return arrayList2;
        } catch (Throwable th) {
            gz.c(th, "AMapDelegateImpGLSurfaceView", "addMarkers");
            th.printStackTrace();
            return arrayList2;
        }
    }

    public Text a(TextOptions textOptions) throws RemoteException {
        if (textOptions == null) {
            return null;
        }
        Text text;
        synchronized (this.f) {
            Object cyVar = new cy(textOptions, this);
            d(cyVar);
            text = new Text(cyVar);
        }
        return text;
    }

    private void d(cn cnVar) {
        try {
            this.f.add(cnVar);
            g();
        } catch (Throwable th) {
            gz.c(th, "MapOverlayImageView", "addMarker");
        }
    }

    public boolean a(cn cnVar) {
        boolean remove;
        synchronized (this.f) {
            try {
                if (this.m != null && this.m.getId().equals(cnVar.getId())) {
                    this.m = null;
                }
                b(cnVar);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            remove = this.f.remove(cnVar);
        }
        return remove;
    }

    public void a(ck ckVar) {
        try {
            if (this.m != null) {
                if (ckVar == null || !ckVar.getId().equals(this.m.getId())) {
                    this.m.b(false);
                } else {
                    return;
                }
            }
            if (this.f.contains(ckVar)) {
                if (ckVar != null) {
                    ckVar.b(true);
                }
                this.m = ckVar;
            }
        } catch (Throwable th) {
            gz.c(th, "MapOverlayImageView", "set2Top");
        }
    }

    public void a() {
        this.m = null;
    }

    public void a(kn knVar) {
        if (this.k == null) {
            this.k = IPoint.obtain();
        }
        Rect l = knVar.l();
        this.k = IPoint.obtain(l.left + (l.width() / 2), l.top);
        this.l = knVar;
        try {
            this.a.a(this.l);
        } catch (Throwable th) {
            gz.c(th, "MapOverlayImageView", "showInfoWindow");
            th.printStackTrace();
        }
    }

    public void b(cn cnVar) {
        try {
            if (cnVar.isInfoWindowShown()) {
                this.a.i();
                this.l = null;
            } else if (this.l != null && this.l.getId().equals(cnVar.getId())) {
                this.l = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void k() {
        try {
            Collections.sort(this.f, this.i);
        } catch (Throwable th) {
            gz.c(th, "MapOverlayImageView", "changeOverlayIndex");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
        r13 = this;
        r2 = 0;
        r0 = r13.a;	 Catch:{ Throwable -> 0x0023 }
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r0 = r13.a;	 Catch:{ Throwable -> 0x0023 }
        r0 = r0.getMapConfig();	 Catch:{ Throwable -> 0x0023 }
        r8 = r0.getMapPerPixelUnitLength();	 Catch:{ Throwable -> 0x0023 }
        r9 = r13.f;	 Catch:{ Throwable -> 0x0023 }
        monitor-enter(r9);	 Catch:{ Throwable -> 0x0023 }
        r13.i();	 Catch:{ all -> 0x0020 }
        r0 = r13.f;	 Catch:{ all -> 0x0020 }
        r0 = r0.size();	 Catch:{ all -> 0x0020 }
        if (r0 != 0) goto L_0x0028;
    L_0x001e:
        monitor-exit(r9);	 Catch:{ all -> 0x0020 }
        goto L_0x0005;
    L_0x0020:
        r0 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x0020 }
        throw r0;	 Catch:{ Throwable -> 0x0023 }
    L_0x0023:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0005;
    L_0x0028:
        r0 = r13.h;	 Catch:{ all -> 0x0020 }
        r0.clear();	 Catch:{ all -> 0x0020 }
        r0 = r13.f;	 Catch:{ all -> 0x0020 }
        r3 = r0.size();	 Catch:{ all -> 0x0020 }
        r1 = r2;
    L_0x0034:
        if (r1 >= r3) goto L_0x006b;
    L_0x0036:
        r0 = r13.f;	 Catch:{ all -> 0x0020 }
        r0 = r0.get(r1);	 Catch:{ all -> 0x0020 }
        r0 = (com.amap.api.mapcore.util.cn) r0;	 Catch:{ all -> 0x0020 }
        r4 = r0.isVisible();	 Catch:{ all -> 0x0020 }
        if (r4 == 0) goto L_0x004a;
    L_0x0044:
        r4 = r0.e();	 Catch:{ all -> 0x0020 }
        if (r4 == 0) goto L_0x004e;
    L_0x004a:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0034;
    L_0x004e:
        r4 = r0.a();	 Catch:{ all -> 0x0020 }
        r13.j = r4;	 Catch:{ all -> 0x0020 }
        r4 = r0.k();	 Catch:{ all -> 0x0020 }
        if (r4 != 0) goto L_0x0060;
    L_0x005a:
        r4 = r0.isInfoWindowShown();	 Catch:{ all -> 0x0020 }
        if (r4 == 0) goto L_0x004a;
    L_0x0060:
        r4 = r13.h;	 Catch:{ Throwable -> 0x0066 }
        r4.add(r0);	 Catch:{ Throwable -> 0x0066 }
        goto L_0x004a;
    L_0x0066:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0020 }
        goto L_0x004a;
    L_0x006b:
        r0 = r13.m;	 Catch:{ all -> 0x0020 }
        if (r0 == 0) goto L_0x007e;
    L_0x006f:
        r0 = r13.m;	 Catch:{ all -> 0x0020 }
        r0 = r0.isVisible();	 Catch:{ all -> 0x0020 }
        if (r0 == 0) goto L_0x007e;
    L_0x0077:
        r0 = r13.h;	 Catch:{ all -> 0x0020 }
        r1 = r13.m;	 Catch:{ all -> 0x0020 }
        r0.add(r1);	 Catch:{ all -> 0x0020 }
    L_0x007e:
        r0 = r13.h;	 Catch:{ all -> 0x0020 }
        r0 = r0.size();	 Catch:{ all -> 0x0020 }
        if (r0 <= 0) goto L_0x00e4;
    L_0x0086:
        r0 = r13.h;	 Catch:{ all -> 0x0020 }
        r10 = r0.size();	 Catch:{ all -> 0x0020 }
        r1 = r2;
        r3 = r2;
        r4 = r2;
        r5 = r2;
        r6 = r2;
    L_0x0091:
        if (r1 >= r10) goto L_0x00df;
    L_0x0093:
        r0 = r13.h;	 Catch:{ all -> 0x0020 }
        r0 = r0.get(r1);	 Catch:{ all -> 0x0020 }
        r0 = (com.amap.api.mapcore.util.cn) r0;	 Catch:{ all -> 0x0020 }
        monitor-enter(r0);	 Catch:{ all -> 0x0020 }
        r7 = r13.a;	 Catch:{ all -> 0x00dc }
        r0.a(r7);	 Catch:{ all -> 0x00dc }
        if (r1 != 0) goto L_0x00cf;
    L_0x00a3:
        r7 = r0.d();	 Catch:{ all -> 0x00dc }
        r12 = r3;
        r3 = r4;
        r4 = r12;
    L_0x00aa:
        r6 = r13.a;	 Catch:{ all -> 0x00dc }
        r11 = r13.b;	 Catch:{ all -> 0x00dc }
        r0.a(r6, r11, r3, r8);	 Catch:{ all -> 0x00dc }
        r6 = r0.d();	 Catch:{ all -> 0x00dc }
        if (r6 == r7) goto L_0x00e7;
    L_0x00b7:
        r13.a(r7, r5, r4, r3);	 Catch:{ all -> 0x00dc }
        r4 = r2;
        r5 = r2;
    L_0x00bc:
        r4 = r4 + 36;
        r5 = r5 + 1;
        r7 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        if (r5 != r7) goto L_0x00ca;
    L_0x00c4:
        r13.a(r6, r5, r3, r4);	 Catch:{ all -> 0x00dc }
        r3 = r2;
        r4 = r2;
        r5 = r2;
    L_0x00ca:
        monitor-exit(r0);	 Catch:{ all -> 0x00dc }
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0091;
    L_0x00cf:
        r7 = r0.d();	 Catch:{ all -> 0x00dc }
        if (r7 == r6) goto L_0x00ec;
    L_0x00d5:
        r13.a(r6, r5, r3, r4);	 Catch:{ all -> 0x00dc }
        r4 = r2;
        r3 = r2;
        r5 = r2;
        goto L_0x00aa;
    L_0x00dc:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x00dc }
        throw r1;	 Catch:{ all -> 0x0020 }
    L_0x00df:
        if (r5 <= 0) goto L_0x00e4;
    L_0x00e1:
        r13.a(r6, r5, r3, r4);	 Catch:{ all -> 0x0020 }
    L_0x00e4:
        monitor-exit(r9);	 Catch:{ all -> 0x0020 }
        goto L_0x0005;
    L_0x00e7:
        r6 = r7;
        r12 = r3;
        r3 = r4;
        r4 = r12;
        goto L_0x00bc;
    L_0x00ec:
        r7 = r6;
        r12 = r4;
        r4 = r3;
        r3 = r12;
        goto L_0x00aa;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.z.b():void");
    }

    private void a(int i, int i2, int i3, int i4) {
        if (i2 != 0 && i != 0) {
            this.p = this.o.c(i2 * 36);
            this.p.put(this.b, i3, i4);
            this.p.flip();
            a(i2);
            a(i, i4, i2, this.p, this.a.getMapConfig());
            this.o.a();
        }
    }

    private void a(int i) {
        int i2 = 0;
        if (i > GLMapStaticValue.TMC_REFRESH_TIMELIMIT) {
            i = GLMapStaticValue.TMC_REFRESH_TIMELIMIT;
        }
        if (this.d == 0) {
            int[] iArr = new int[2];
            GLES20.glGenBuffers(2, iArr, 0);
            this.d = iArr[0];
            this.e = iArr[1];
            Buffer b = this.o.b(com.tencent.bugly.BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
            short[] sArr = new short[com.tencent.bugly.BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH];
            while (i2 < GLMapStaticValue.TMC_REFRESH_TIMELIMIT) {
                sArr[(i2 * 6) + 0] = (short) ((i2 * 4) + 0);
                sArr[(i2 * 6) + 1] = (short) ((i2 * 4) + 1);
                sArr[(i2 * 6) + 2] = (short) ((i2 * 4) + 2);
                sArr[(i2 * 6) + 3] = (short) ((i2 * 4) + 0);
                sArr[(i2 * 6) + 4] = (short) ((i2 * 4) + 2);
                sArr[(i2 * 6) + 5] = (short) ((i2 * 4) + 3);
                i2++;
            }
            b.put(sArr);
            b.flip();
            GLES20.glBindBuffer(34963, this.e);
            GLES20.glBufferData(34963, TimeConstants.MIN, b, 35044);
        }
        GLES20.glBindBuffer(34962, this.d);
        GLES20.glBufferData(34962, (i * 36) * 4, this.p, 35044);
    }

    private void l() {
        if (this.c == null && this.a != null) {
            this.c = (d) this.a.u(1);
        }
    }

    private void a(int i, int i2, int i3, FloatBuffer floatBuffer, MapConfig mapConfig) {
        if (i != 0 && floatBuffer != null && i3 != 0) {
            if (this.c == null || this.c.c()) {
                l();
            }
            this.c.a();
            GLES20.glUniform1f(this.c.h, mapConfig.getSR());
            GLES20.glEnableVertexAttribArray(this.c.b);
            GLES20.glBindBuffer(34962, this.d);
            GLES20.glVertexAttribPointer(this.c.b, 4, 5126, false, 36, 0);
            GLES20.glEnable(3042);
            GLES20.glBlendFunc(1, 771);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i);
            GLES20.glEnableVertexAttribArray(this.c.c);
            GLES20.glBindBuffer(34962, this.d);
            GLES20.glVertexAttribPointer(this.c.c, 2, 5126, false, 36, 16);
            GLES20.glEnableVertexAttribArray(this.c.g);
            GLES20.glBindBuffer(34962, this.d);
            GLES20.glVertexAttribPointer(this.c.g, 3, 5126, false, 36, 24);
            GLES20.glUniformMatrix4fv(this.c.a, 1, false, c(), 0);
            GLES20.glBindBuffer(34963, this.e);
            GLES20.glDrawElements(4, i3 * 6, 5123, 0);
            GLES20.glBindBuffer(34962, 0);
            GLES20.glBindBuffer(34963, 0);
            GLES20.glBindTexture(3553, 0);
            GLES20.glDisableVertexAttribArray(this.c.b);
            GLES20.glDisableVertexAttribArray(this.c.c);
            GLES20.glDisable(3042);
            GLES20.glUseProgram(0);
        }
    }

    public synchronized boolean a(Bitmap bitmap, f fVar) {
        boolean z = true;
        synchronized (this) {
            c a = this.n.a(bitmap.getWidth() + 1, bitmap.getHeight() + 1, fVar.j());
            if (a != null) {
                fVar.b(((float) a.a) / ((float) this.n.a()));
                fVar.a(((float) a.b) / ((float) this.n.b()));
                fVar.c(((float) ((a.a + a.c) - 1)) / ((float) this.n.a()));
                fVar.d(((float) ((a.d + a.b) - 1)) / ((float) this.n.b()));
            } else {
                z = false;
            }
        }
        return z;
    }

    public float[] c() {
        if (this.a != null) {
            return this.a.w();
        }
        return new float[16];
    }

    public lj d() {
        return this.a;
    }

    public kn e() {
        return this.l;
    }

    public kn a(MotionEvent motionEvent) {
        kn knVar;
        synchronized (this.f) {
            for (int size = this.f.size() - 1; size >= 0; size--) {
                cn cnVar = (cn) this.f.get(size);
                if ((cnVar instanceof cs) && en.a(cnVar.l(), (int) motionEvent.getX(), (int) motionEvent.getY())) {
                    this.l = (cs) cnVar;
                    knVar = this.l;
                    break;
                }
            }
            knVar = null;
        }
        return knVar;
    }

    public boolean b(MotionEvent motionEvent) throws RemoteException {
        boolean z;
        synchronized (this.f) {
            for (int size = this.f.size() - 1; size >= 0; size--) {
                cn cnVar = (cn) this.f.get(size);
                if ((cnVar instanceof cs) && cnVar.isVisible() && ((cs) cnVar).isClickable()) {
                    Rect l = cnVar.l();
                    boolean a = en.a(l, (int) motionEvent.getX(), (int) motionEvent.getY());
                    if (a) {
                        this.k = IPoint.obtain(l.left + (l.width() / 2), l.top);
                        this.l = (cs) cnVar;
                        z = a;
                        break;
                    }
                }
            }
            z = false;
        }
        return z;
    }

    public List<Marker> f() {
        List<Marker> arrayList;
        synchronized (this.f) {
            arrayList = new ArrayList();
            try {
                for (cn cnVar : this.f) {
                    if ((cnVar instanceof cs) && cnVar.k()) {
                        arrayList.add(new Marker((IMarker) cnVar));
                    }
                }
            } catch (Throwable th) {
                gz.c(th, "MapOverlayImageView", "getMapScreenMarkers");
                th.printStackTrace();
            }
        }
        return arrayList;
    }

    public void g() {
        if (this.r != null) {
            this.r.removeCallbacks(this.t);
            this.r.postDelayed(this.t, 10);
        }
    }

    public boolean c(cn cnVar) {
        boolean contains;
        synchronized (this.f) {
            contains = this.f.contains(cnVar);
        }
        return contains;
    }

    protected int h() {
        int size;
        synchronized (this.f) {
            size = this.f.size();
        }
        return size;
    }

    public void a(String str) {
        Object obj;
        int i;
        cn cnVar;
        cn cnVar2 = null;
        if (str != null) {
            try {
                if (str.trim().length() != 0) {
                    obj = null;
                    this.l = null;
                    this.k = null;
                    this.m = null;
                    synchronized (this.f) {
                        this.h.clear();
                        if (obj == null) {
                            this.f.clear();
                        } else {
                            i = 0;
                            while (i < this.f.size()) {
                                cnVar = (cn) this.f.get(i);
                                if (str.equals(cnVar.getId())) {
                                    cnVar.remove();
                                    cnVar = cnVar2;
                                }
                                i++;
                                cnVar2 = cnVar;
                            }
                            this.f.clear();
                            if (cnVar2 != null) {
                                this.f.add(cnVar2);
                                if (cnVar2.e() && (cnVar2 instanceof ck)) {
                                    this.m = (ck) cnVar2;
                                }
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                gz.c(th, "MapOverlayImageView", "clear");
                th.printStackTrace();
                return;
            }
        }
        obj = 1;
        this.l = null;
        this.k = null;
        this.m = null;
        synchronized (this.f) {
            this.h.clear();
            if (obj == null) {
                i = 0;
                while (i < this.f.size()) {
                    cnVar = (cn) this.f.get(i);
                    if (str.equals(cnVar.getId())) {
                        cnVar.remove();
                        cnVar = cnVar2;
                    }
                    i++;
                    cnVar2 = cnVar;
                }
                this.f.clear();
                if (cnVar2 != null) {
                    this.f.add(cnVar2);
                    this.m = (ck) cnVar2;
                }
            } else {
                this.f.clear();
            }
        }
    }

    public void a(f fVar) {
        synchronized (this.g) {
            if (fVar != null) {
                this.g.add(fVar);
            }
        }
    }

    public void i() {
        synchronized (this.g) {
            int e = this.a.e();
            for (int i = 0; i < this.g.size(); i++) {
                f fVar = (f) this.g.get(i);
                if (fVar != null) {
                    fVar.h();
                    if (fVar.i() <= 0) {
                        if (fVar.f() == e) {
                            this.n.a(fVar.j());
                        } else {
                            this.s[0] = fVar.f();
                            GLES20.glDeleteTextures(1, this.s, 0);
                        }
                        if (this.a != null) {
                            this.a.c(fVar.j());
                        }
                    }
                }
            }
            this.g.clear();
        }
    }

    public void j() {
        try {
            for (cn cnVar : this.f) {
                if (cnVar != null) {
                    cnVar.destroy(false);
                }
            }
            a(null);
            if (this.q != null) {
                this.q.quit();
            }
            this.r = null;
            this.a = null;
        } catch (Throwable th) {
            gz.c(th, "MapOverlayImageView", "destroy");
            th.printStackTrace();
            Log.d("amapApi", "MapOverlayImageView clear erro" + th.getMessage());
        }
    }
}
