package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.os.RemoteException;
import android.util.Log;
import cn.jiguang.api.utils.ByteBufferUtils;
import com.amap.api.mapcore.util.db.c;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.github.mikephil.charting.utils.Utils;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: GroundOverlayDelegateImp */
public class ce implements cj {
    float[] a;
    c b;
    private lj c;
    private BitmapDescriptor d;
    private LatLng e;
    private float f;
    private float g;
    private LatLngBounds h;
    private float i;
    private float j;
    private boolean k;
    private float l;
    private float m;
    private float n;
    private float o;
    private String p;
    private FloatBuffer q;
    private FloatBuffer r;
    private int s;
    private boolean t;
    private boolean u;
    private List<f> v;
    private r w;

    public ce(lj ljVar, r rVar) {
        this(ljVar);
        this.w = rVar;
    }

    private ce(lj ljVar) {
        this.k = true;
        this.l = 0.0f;
        this.m = 1.0f;
        this.n = 0.5f;
        this.o = 0.5f;
        this.q = null;
        this.t = false;
        this.u = false;
        this.v = new ArrayList();
        this.a = null;
        this.c = ljVar;
        try {
            this.p = getId();
        } catch (Throwable e) {
            gz.c(e, "GroundOverlayDelegateImp", "create");
            e.printStackTrace();
        }
    }

    public void remove() throws RemoteException {
        this.c.a(getId());
        this.c.setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.p == null) {
            this.p = this.c.d("GroundOverlay");
        }
        return this.p;
    }

    public void setZIndex(float f) throws RemoteException {
        this.j = f;
        this.c.f();
        this.c.setRunLowFrame(false);
    }

    public float getZIndex() throws RemoteException {
        return this.j;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.k = z;
        this.c.setRunLowFrame(false);
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
        return super.hashCode();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b() throws android.os.RemoteException {
        /*
        r2 = this;
        r0 = 0;
        monitor-enter(r2);
        r1 = r2.a;	 Catch:{ all -> 0x0014 }
        if (r1 == 0) goto L_0x0008;
    L_0x0006:
        monitor-exit(r2);	 Catch:{ all -> 0x0014 }
    L_0x0007:
        return r0;
    L_0x0008:
        monitor-exit(r2);	 Catch:{ all -> 0x0014 }
        r2.u = r0;
        r0 = r2.e;
        if (r0 != 0) goto L_0x0017;
    L_0x000f:
        r2.e();
    L_0x0012:
        r0 = 1;
        goto L_0x0007;
    L_0x0014:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0014 }
        throw r0;
    L_0x0017:
        r0 = r2.h;
        if (r0 != 0) goto L_0x001f;
    L_0x001b:
        r2.d();
        goto L_0x0012;
    L_0x001f:
        r2.f();
        goto L_0x0012;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ce.b():boolean");
    }

    private void d() {
        if (this.e != null) {
            double cos = ((double) this.f) / ((6371000.79d * Math.cos(this.e.latitude * 0.01745329251994329d)) * 0.01745329251994329d);
            double d = ((double) this.g) / 111194.94043265979d;
            try {
                this.h = new LatLngBounds(new LatLng(this.e.latitude - (((double) (1.0f - this.o)) * d), this.e.longitude - (((double) this.n) * cos)), new LatLng((d * ((double) this.o)) + this.e.latitude, (cos * ((double) (1.0f - this.n))) + this.e.longitude));
            } catch (Throwable th) {
                th.printStackTrace();
            }
            f();
        }
    }

    private synchronized void e() {
        if (this.h != null) {
            LatLng latLng = this.h.southwest;
            LatLng latLng2 = this.h.northeast;
            this.e = new LatLng(latLng.latitude + (((double) (1.0f - this.o)) * (latLng2.latitude - latLng.latitude)), latLng.longitude + (((double) this.n) * (latLng2.longitude - latLng.longitude)));
            this.f = (float) (((6371000.79d * Math.cos(this.e.latitude * 0.01745329251994329d)) * (latLng2.longitude - latLng.longitude)) * 0.01745329251994329d);
            this.g = (float) (((latLng2.latitude - latLng.latitude) * 6371000.79d) * 0.01745329251994329d);
            f();
        }
    }

    private synchronized void f() {
        if (this.h != null) {
            this.a = new float[16];
            IPoint obtain = IPoint.obtain();
            IPoint obtain2 = IPoint.obtain();
            IPoint obtain3 = IPoint.obtain();
            IPoint obtain4 = IPoint.obtain();
            GLMapState.lonlat2Geo(this.h.southwest.longitude, this.h.southwest.latitude, obtain);
            GLMapState.lonlat2Geo(this.h.northeast.longitude, this.h.southwest.latitude, obtain2);
            GLMapState.lonlat2Geo(this.h.northeast.longitude, this.h.northeast.latitude, obtain3);
            GLMapState.lonlat2Geo(this.h.southwest.longitude, this.h.northeast.latitude, obtain4);
            if (this.i != 0.0f) {
                double d = (double) (obtain2.x - obtain.x);
                double d2 = (double) (obtain2.y - obtain3.y);
                DPoint obtain5 = DPoint.obtain();
                obtain5.x = ((double) obtain.x) + (((double) this.n) * d);
                obtain5.y = ((double) obtain.y) - (((double) (1.0f - this.o)) * d2);
                a(obtain5, Utils.DOUBLE_EPSILON, Utils.DOUBLE_EPSILON, d, d2, obtain);
                a(obtain5, d, Utils.DOUBLE_EPSILON, d, d2, obtain2);
                a(obtain5, d, d2, d, d2, obtain3);
                a(obtain5, Utils.DOUBLE_EPSILON, d2, d, d2, obtain4);
                obtain5.recycle();
            }
            this.a[0] = (float) (obtain.x / ByteBufferUtils.ERROR_CODE);
            this.a[1] = (float) (obtain.y / ByteBufferUtils.ERROR_CODE);
            this.a[2] = (float) (obtain.x % ByteBufferUtils.ERROR_CODE);
            this.a[3] = (float) (obtain.y % ByteBufferUtils.ERROR_CODE);
            this.a[4] = (float) (obtain2.x / ByteBufferUtils.ERROR_CODE);
            this.a[5] = (float) (obtain2.y / ByteBufferUtils.ERROR_CODE);
            this.a[6] = (float) (obtain2.x % ByteBufferUtils.ERROR_CODE);
            this.a[7] = (float) (obtain2.y % ByteBufferUtils.ERROR_CODE);
            this.a[8] = (float) (obtain3.x / ByteBufferUtils.ERROR_CODE);
            this.a[9] = (float) (obtain3.y / ByteBufferUtils.ERROR_CODE);
            this.a[10] = (float) (obtain3.x % ByteBufferUtils.ERROR_CODE);
            this.a[11] = (float) (obtain3.y % ByteBufferUtils.ERROR_CODE);
            this.a[12] = (float) (obtain4.x / ByteBufferUtils.ERROR_CODE);
            this.a[13] = (float) (obtain4.y / ByteBufferUtils.ERROR_CODE);
            this.a[14] = (float) (obtain4.x % ByteBufferUtils.ERROR_CODE);
            this.a[15] = (float) (obtain4.y % ByteBufferUtils.ERROR_CODE);
            if (this.q == null) {
                this.q = en.a(this.a);
            } else {
                this.q = en.a(this.a, this.q);
            }
            obtain4.recycle();
            obtain.recycle();
            obtain2.recycle();
            obtain3.recycle();
        }
    }

    private void a(DPoint dPoint, double d, double d2, double d3, double d4, IPoint iPoint) {
        double d5 = d - (((double) this.n) * d3);
        double d6 = (((double) (1.0f - this.o)) * d4) - d2;
        double d7 = ((double) (-this.i)) * 0.01745329251994329d;
        iPoint.x = (int) (dPoint.x + ((Math.cos(d7) * d5) + (Math.sin(d7) * d6)));
        iPoint.y = (int) (((d6 * Math.cos(d7)) - (d5 * Math.sin(d7))) + dPoint.y);
    }

    private void g() {
        if (this.d != null || (this.d != null && this.d.getBitmap() != null)) {
            int width = this.d.getWidth();
            float width2 = ((float) width) / ((float) this.d.getBitmap().getWidth());
            float height = ((float) this.d.getHeight()) / ((float) this.d.getBitmap().getHeight());
            this.r = en.a(new float[]{0.0f, height, width2, height, width2, 0.0f, 0.0f, 0.0f});
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.autonavi.amap.mapcore.MapConfig r6) throws android.os.RemoteException {
        /*
        r5 = this;
        r0 = 0;
        r4 = 0;
        r1 = 1;
        monitor-enter(r5);
        r2 = r5.k;	 Catch:{ all -> 0x004e }
        if (r2 == 0) goto L_0x0014;
    L_0x0008:
        r2 = r5.e;	 Catch:{ all -> 0x004e }
        if (r2 != 0) goto L_0x0010;
    L_0x000c:
        r2 = r5.h;	 Catch:{ all -> 0x004e }
        if (r2 == 0) goto L_0x0014;
    L_0x0010:
        r2 = r5.d;	 Catch:{ all -> 0x004e }
        if (r2 != 0) goto L_0x0019;
    L_0x0014:
        r2 = r1;
    L_0x0015:
        if (r2 == 0) goto L_0x001b;
    L_0x0017:
        monitor-exit(r5);	 Catch:{ all -> 0x004e }
    L_0x0018:
        return;
    L_0x0019:
        r2 = r0;
        goto L_0x0015;
    L_0x001b:
        monitor-exit(r5);	 Catch:{ all -> 0x004e }
        r5.b();
        r2 = r5.t;
        if (r2 != 0) goto L_0x0034;
    L_0x0023:
        r2 = android.os.Build.VERSION.SDK_INT;
        r3 = 12;
        if (r2 < r3) goto L_0x002a;
    L_0x0029:
        r0 = r1;
    L_0x002a:
        r2 = r5.d;
        r0 = r5.a(r0, r2);
        r5.s = r0;
        r5.t = r1;
    L_0x0034:
        r0 = r5.f;
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 != 0) goto L_0x0040;
    L_0x003a:
        r0 = r5.g;
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 == 0) goto L_0x0018;
    L_0x0040:
        monitor-enter(r5);
        r0 = r5.s;	 Catch:{ all -> 0x0051 }
        r2 = r5.q;	 Catch:{ all -> 0x0051 }
        r3 = r5.r;	 Catch:{ all -> 0x0051 }
        r5.a(r0, r2, r3);	 Catch:{ all -> 0x0051 }
        monitor-exit(r5);	 Catch:{ all -> 0x0051 }
        r5.u = r1;
        goto L_0x0018;
    L_0x004e:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x004e }
        throw r0;
    L_0x0051:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0051 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ce.a(com.autonavi.amap.mapcore.MapConfig):void");
    }

    private void h() {
        if (this.v != null) {
            for (f fVar : this.v) {
                if (!(fVar == null || this.w == null)) {
                    this.w.a(fVar);
                }
            }
            this.v.clear();
        }
    }

    private void a(f fVar) {
        if (fVar != null) {
            this.v.add(fVar);
            fVar.g();
        }
    }

    private int a(boolean z, BitmapDescriptor bitmapDescriptor) {
        h();
        f fVar = null;
        if (z) {
            fVar = this.w.a(bitmapDescriptor);
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
        f = i();
        fVar.a(f);
        if (z) {
            this.c.a(fVar);
        }
        a(fVar);
        en.b(f, bitmap, true);
        return f;
    }

    private int i() {
        int[] iArr = new int[]{0};
        GLES20.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    public void destroy() {
        try {
            remove();
            if (this.v != null && this.v.size() > 0) {
                for (int i = 0; i < this.v.size(); i++) {
                    f fVar = (f) this.v.get(i);
                    if (fVar != null) {
                        if (this.w != null) {
                            this.w.a(fVar);
                        }
                        if (this.c != null) {
                            this.c.c(fVar.j());
                        }
                    }
                }
                this.v.clear();
            }
            if (this.d != null) {
                Bitmap bitmap = this.d.getBitmap();
                if (bitmap != null) {
                    bitmap.recycle();
                    this.d = null;
                }
            }
            if (this.r != null) {
                this.r.clear();
                this.r = null;
            }
            synchronized (this) {
                if (this.q != null) {
                    this.q.clear();
                    this.q = null;
                }
                this.h = null;
            }
            this.e = null;
        } catch (Throwable th) {
            gz.c(th, "GroundOverlayDelegateImp", "destroy");
            th.printStackTrace();
        }
    }

    public boolean a() {
        return true;
    }

    public void setPosition(LatLng latLng) throws RemoteException {
        this.e = latLng;
        d();
        this.c.setRunLowFrame(false);
    }

    public LatLng getPosition() throws RemoteException {
        return this.e;
    }

    public void setDimensions(float f) throws RemoteException {
        if (f <= 0.0f) {
            Log.w("GroundOverlayDelegateImp", "Width must be non-negative");
        }
        if (!this.t || this.f == f) {
            this.f = f;
            this.g = f;
        } else {
            this.f = f;
            this.g = f;
            d();
        }
        this.c.setRunLowFrame(false);
    }

    public void setDimensions(float f, float f2) throws RemoteException {
        if (f <= 0.0f || f2 <= 0.0f) {
            Log.w("GroundOverlayDelegateImp", "Width and Height must be non-negative");
        }
        if (!this.t || this.f == f || this.g == f2) {
            this.f = f;
            this.g = f2;
        } else {
            this.f = f;
            this.g = f2;
            d();
        }
        this.c.setRunLowFrame(false);
    }

    public float getWidth() throws RemoteException {
        return this.f;
    }

    public float getHeight() throws RemoteException {
        return this.g;
    }

    public void setPositionFromBounds(LatLngBounds latLngBounds) throws RemoteException {
        if (latLngBounds != null) {
            this.h = latLngBounds;
            e();
            this.c.setRunLowFrame(false);
        }
    }

    public LatLngBounds getBounds() throws RemoteException {
        return this.h;
    }

    public void setBearing(float f) throws RemoteException {
        float f2 = ((f % 360.0f) + 360.0f) % 360.0f;
        if (((double) Math.abs(this.i - f2)) > 1.0E-7d) {
            this.i = f2;
            f();
        }
        this.c.setRunLowFrame(false);
    }

    public float getBearing() throws RemoteException {
        return this.i;
    }

    public void setTransparency(float f) throws RemoteException {
        this.l = (float) Math.min(1.0d, Math.max(Utils.DOUBLE_EPSILON, (double) f));
        this.m = 1.0f - f;
        this.c.setRunLowFrame(false);
    }

    public float getTransparency() throws RemoteException {
        return this.l;
    }

    public void setImage(BitmapDescriptor bitmapDescriptor) throws RemoteException {
        if (bitmapDescriptor != null && bitmapDescriptor.getBitmap() != null && !bitmapDescriptor.getBitmap().isRecycled()) {
            this.d = bitmapDescriptor;
            g();
            if (this.t) {
                this.t = false;
            }
            this.c.setRunLowFrame(false);
        }
    }

    public void a(float f, float f2) throws RemoteException {
        this.n = f;
        this.o = f2;
        this.c.setRunLowFrame(false);
    }

    public boolean c() {
        return this.u;
    }

    public boolean isAboveMaskLayer() {
        return false;
    }

    public void setAboveMaskLayer(boolean z) {
    }

    private void j() {
        if (this.c != null) {
            this.b = (c) this.c.u(2);
        }
    }

    private void a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer != null && floatBuffer2 != null) {
            if (this.b == null || this.b.c()) {
                j();
            }
            this.b.a();
            GLES20.glEnable(3042);
            GLES20.glBlendFunc(1, 771);
            GLES20.glBlendColor(1.0f * this.m, 1.0f * this.m, 1.0f * this.m, this.m);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i);
            GLES20.glEnableVertexAttribArray(this.b.b);
            GLES20.glVertexAttribPointer(this.b.b, 4, 5126, false, 16, floatBuffer);
            GLES20.glEnableVertexAttribArray(this.b.c);
            GLES20.glVertexAttribPointer(this.b.c, 2, 5126, false, 8, floatBuffer2);
            GLES20.glUniform4f(this.b.g, (float) (this.c.getMapConfig().getSX() / ByteBufferUtils.ERROR_CODE), (float) (this.c.getMapConfig().getSY() / ByteBufferUtils.ERROR_CODE), (float) (this.c.getMapConfig().getSX() % ByteBufferUtils.ERROR_CODE), (float) (this.c.getMapConfig().getSY() % ByteBufferUtils.ERROR_CODE));
            GLES20.glUniform4f(this.b.h, 1.0f * this.m, 1.0f * this.m, 1.0f * this.m, this.m);
            GLES20.glUniformMatrix4fv(this.b.a, 1, false, this.c.w(), 0);
            GLES20.glDrawArrays(6, 0, 4);
            GLES20.glBindTexture(3553, 0);
            GLES20.glDisableVertexAttribArray(this.b.b);
            GLES20.glDisableVertexAttribArray(this.b.c);
            GLES20.glDisable(3042);
            GLES20.glUseProgram(0);
        }
    }
}
