package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import java.util.List;
import org.json.JSONObject;

/* compiled from: LastLocationManager */
public final class cz {
    static cb b = null;
    static p e = null;
    static long g = 0;
    String a = null;
    cb c = null;
    cb d = null;
    long f = 0;
    boolean h = false;
    private Context i;

    public cz(Context context) {
        this.i = context.getApplicationContext();
    }

    private void e() {
        if (b == null || ct.b() - g > 180000) {
            cb f = f();
            g = ct.b();
            if (f != null && ct.a(f.a())) {
                b = f;
            }
        }
    }

    private cb f() {
        Throwable th;
        Object obj = null;
        if (this.i == null) {
            return null;
        }
        a();
        cb cbVar;
        try {
            if (e == null) {
                return null;
            }
            List b = e.b("_id=1", cb.class);
            if (b == null || b.size() <= 0) {
                cbVar = null;
            } else {
                cbVar = (cb) b.get(0);
                try {
                    byte[] d;
                    String str;
                    String str2;
                    byte[] b2 = dg.b(cbVar.c());
                    if (b2 != null && b2.length > 0) {
                        d = bz.d(b2, this.a);
                        if (d != null && d.length > 0) {
                            str = new String(d, "UTF-8");
                            d = dg.b(cbVar.b());
                            if (d != null && d.length > 0) {
                                d = bz.d(d, this.a);
                                if (d != null && d.length > 0) {
                                    str2 = new String(d, "UTF-8");
                                }
                            }
                            cbVar.a(str2);
                            obj = str;
                        }
                    }
                    str = null;
                    d = dg.b(cbVar.b());
                    d = bz.d(d, this.a);
                    str2 = new String(d, "UTF-8");
                    cbVar.a(str2);
                    obj = str;
                } catch (Throwable th2) {
                    th = th2;
                    cl.a(th, "LastLocationManager", "readLastFix");
                    return cbVar;
                }
            }
            if (TextUtils.isEmpty(obj)) {
                return cbVar;
            }
            AMapLocation aMapLocation = new AMapLocation("");
            cl.a(aMapLocation, new JSONObject(obj));
            if (!ct.b(aMapLocation)) {
                return cbVar;
            }
            cbVar.a(aMapLocation);
            return cbVar;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            cbVar = null;
            th = th4;
            cl.a(th, "LastLocationManager", "readLastFix");
            return cbVar;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.amap.api.location.AMapLocation a(com.amap.api.location.AMapLocation r8, java.lang.String r9, long r10) {
        /*
        r7 = this;
        r0 = 1;
        if (r8 != 0) goto L_0x0004;
    L_0x0003:
        return r8;
    L_0x0004:
        r1 = r8.getErrorCode();
        if (r1 == 0) goto L_0x0003;
    L_0x000a:
        r1 = r8.getLocationType();
        if (r1 == r0) goto L_0x0003;
    L_0x0010:
        r1 = r8.getErrorCode();
        r2 = 7;
        if (r1 == r2) goto L_0x0003;
    L_0x0017:
        r7.e();	 Catch:{ Throwable -> 0x006a }
        r1 = b;	 Catch:{ Throwable -> 0x006a }
        if (r1 == 0) goto L_0x0003;
    L_0x001e:
        r1 = b;	 Catch:{ Throwable -> 0x006a }
        r1 = r1.a();	 Catch:{ Throwable -> 0x006a }
        if (r1 == 0) goto L_0x0003;
    L_0x0026:
        r1 = 0;
        r2 = android.text.TextUtils.isEmpty(r9);	 Catch:{ Throwable -> 0x006a }
        if (r2 == 0) goto L_0x005c;
    L_0x002d:
        r2 = com.loc.ct.b();	 Catch:{ Throwable -> 0x006a }
        r4 = b;	 Catch:{ Throwable -> 0x006a }
        r4 = r4.d();	 Catch:{ Throwable -> 0x006a }
        r2 = r2 - r4;
        r4 = 0;
        r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r4 < 0) goto L_0x0068;
    L_0x003e:
        r2 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1));
        if (r2 > 0) goto L_0x0068;
    L_0x0042:
        if (r0 == 0) goto L_0x0003;
    L_0x0044:
        r0 = b;	 Catch:{ Throwable -> 0x006a }
        r0 = r0.a();	 Catch:{ Throwable -> 0x006a }
        r1 = 9;
        r0.setLocationType(r1);	 Catch:{ Throwable -> 0x0073 }
        r1 = 1;
        r0.setFixLastLocation(r1);	 Catch:{ Throwable -> 0x0073 }
        r1 = r8.getLocationDetail();	 Catch:{ Throwable -> 0x0073 }
        r0.setLocationDetail(r1);	 Catch:{ Throwable -> 0x0073 }
        r8 = r0;
        goto L_0x0003;
    L_0x005c:
        r2 = b;	 Catch:{ Throwable -> 0x006a }
        r2 = r2.b();	 Catch:{ Throwable -> 0x006a }
        r2 = com.loc.ct.a(r2, r9);	 Catch:{ Throwable -> 0x006a }
        if (r2 != 0) goto L_0x0042;
    L_0x0068:
        r0 = r1;
        goto L_0x0042;
    L_0x006a:
        r0 = move-exception;
    L_0x006b:
        r1 = "LastLocationManager";
        r2 = "fixLastLocation";
        com.loc.cl.a(r0, r1, r2);
        goto L_0x0003;
    L_0x0073:
        r1 = move-exception;
        r8 = r0;
        r0 = r1;
        goto L_0x006b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cz.a(com.amap.api.location.AMapLocation, java.lang.String, long):com.amap.api.location.AMapLocation");
    }

    public final void a() {
        if (!this.h) {
            try {
                if (this.a == null) {
                    this.a = bz.a("MD5", df.u(this.i));
                }
                if (e == null) {
                    e = new p(this.i, p.a(cc.class), ct.i());
                }
            } catch (Throwable th) {
                cl.a(th, "LastLocationManager", "<init>:DBOperation");
            }
            this.h = true;
        }
    }

    public final boolean a(AMapLocation aMapLocation, String str) {
        if (this.i == null || aMapLocation == null || !ct.a(aMapLocation) || aMapLocation.getLocationType() == 2 || aMapLocation.isMock() || aMapLocation.isFixLastLocation()) {
            return false;
        }
        cb cbVar = new cb();
        cbVar.a(aMapLocation);
        if (aMapLocation.getLocationType() == 1) {
            cbVar.a(null);
        } else {
            cbVar.a(str);
        }
        try {
            b = cbVar;
            g = ct.b();
            this.c = cbVar;
            return (this.d == null || ct.a(this.d.a(), cbVar.a()) > 500.0f) && ct.b() - this.f > StatisticConfig.MIN_UPLOAD_INTERVAL;
        } catch (Throwable th) {
            cl.a(th, "LastLocationManager", "setLastFix");
            return false;
        }
    }

    public final AMapLocation b() {
        e();
        return (b != null && ct.a(b.a())) ? b.a() : null;
    }

    public final void c() {
        try {
            d();
            this.f = 0;
            this.h = false;
            this.c = null;
            this.d = null;
        } catch (Throwable th) {
            cl.a(th, "LastLocationManager", "destroy");
        }
    }

    public final void d() {
        String str = null;
        try {
            a();
            if (this.c != null && ct.a(this.c.a()) && e != null && this.c != this.d && this.c.d() == 0) {
                Object toStr = this.c.a().toStr();
                Object b = this.c.b();
                this.d = this.c;
                if (TextUtils.isEmpty(toStr)) {
                    toStr = null;
                } else {
                    toStr = dg.b(bz.c(toStr.getBytes("UTF-8"), this.a));
                    if (!TextUtils.isEmpty(b)) {
                        str = dg.b(bz.c(b.getBytes("UTF-8"), this.a));
                    }
                }
                if (!TextUtils.isEmpty(toStr)) {
                    b = new cb();
                    b.b(toStr);
                    b.a(ct.b());
                    b.a(str);
                    e.a(b, "_id=1");
                    this.f = ct.b();
                    if (b != null) {
                        b.a(ct.b());
                    }
                }
            }
        } catch (Throwable th) {
            cl.a(th, "LastLocationManager", "saveLastFix");
        }
    }
}
