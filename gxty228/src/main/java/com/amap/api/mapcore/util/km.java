package com.amap.api.mapcore.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationProtocol;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.connect.common.Constants;

/* compiled from: MapNetLocation */
public final class km {
    Context a = null;
    boolean b = false;
    String c = null;
    long d = 0;
    private kt e = null;
    private ks f = null;
    private a g = null;
    private kv h = null;
    private ConnectivityManager i = null;
    private ky j = null;
    private StringBuilder k = new StringBuilder();
    private Inner_3dMap_locationOption l = null;
    private kx m = null;
    private final String n = "\"status\":\"0\"";
    private final String o = "</body></html>";

    /* compiled from: MapNetLocation */
    private class a extends BroadcastReceiver {
        final /* synthetic */ km a;

        private a(km kmVar) {
            this.a = kmVar;
        }

        public final void onReceive(Context context, Intent intent) {
            if (context != null && intent != null) {
                try {
                    String action = intent.getAction();
                    if (!TextUtils.isEmpty(action)) {
                        if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                            if (this.a.e != null) {
                                this.a.e.c();
                            }
                        } else if (action.equals("android.net.wifi.WIFI_STATE_CHANGED") && this.a.e != null) {
                            this.a.e.d();
                        }
                    }
                } catch (Throwable th) {
                    kz.a(th, "MapNetLocation", "onReceive");
                }
            }
        }
    }

    public km(Context context) {
        try {
            this.a = context.getApplicationContext();
            lc.b(this.a);
            a(this.a);
            this.l = new Inner_3dMap_locationOption();
            if (this.e == null) {
                this.e = new kt(this.a, (WifiManager) lc.a(this.a, IXAdSystemUtils.NT_WIFI));
                this.e.a(this.b);
            }
            if (this.f == null) {
                this.f = new ks(this.a);
            }
            if (this.h == null) {
                Context context2 = this.a;
                this.h = kv.a();
            }
            if (this.i == null) {
                this.i = (ConnectivityManager) lc.a(this.a, "connectivity");
            }
            this.j = new ky();
            c();
        } catch (Throwable th) {
            kz.a(th, "MapNetLocation", "<init>");
        }
    }

    private static kx a(kx kxVar, String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return kh.a().a(kxVar);
        }
        if (strArr[0].equals("shake")) {
            return kh.a().a(kxVar);
        }
        if (!strArr[0].equals("fusion")) {
            return kxVar;
        }
        kh.a();
        return kh.b(kxVar);
    }

    private void a(Context context) {
        try {
            if (context.checkCallingOrSelfPermission("android.permission.WRITE_SECURE_SETTINGS") == 0) {
                this.b = true;
            }
        } catch (Throwable th) {
        }
    }

    private boolean a(long j) {
        if (lc.b() - j >= 800) {
            return false;
        }
        long j2 = 0;
        if (kp.a(this.m)) {
            j2 = lc.a() - this.m.getTime();
        }
        return j2 <= 10000;
    }

    private void c() {
        try {
            if (this.g == null) {
                this.g = new a();
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
            this.a.registerReceiver(this.g, intentFilter);
            this.e.b(false);
            this.f.f();
        } catch (Throwable th) {
            kz.a(th, "MapNetLocation", "initBroadcastListener");
        }
    }

    private kx d() throws Exception {
        kx kxVar = new kx("");
        if (this.e == null || !this.e.g()) {
            try {
                if (this.j == null) {
                    this.j = new ky();
                }
                this.j.a(this.a, this.l.isNeedAddress(), this.l.isOffset(), this.f, this.e, this.i, this.c);
                ko koVar = new ko();
                byte[] bArr = null;
                Object obj = "";
                try {
                    try {
                        jb a = this.h.a(this.h.a(this.a, this.j.a(), kz.a()));
                        if (a != null) {
                            bArr = a.a;
                            obj = a.c;
                        }
                        if (bArr == null || bArr.length == 0) {
                            kxVar.setErrorCode(4);
                            this.k.append("please check the network");
                            if (!TextUtils.isEmpty(obj)) {
                                this.k.append(" #csid:" + obj);
                            }
                            kxVar.setLocationDetail(this.k.toString());
                            return kxVar;
                        }
                        String str = new String(bArr, "UTF-8");
                        if (str.contains("\"status\":\"0\"")) {
                            return koVar.a(str, this.a, a);
                        }
                        if (str.contains("</body></html>")) {
                            kxVar.setErrorCode(5);
                            if (this.e == null || !this.e.a(this.i)) {
                                this.k.append("request may be intercepted");
                            } else {
                                this.k.append("make sure you are logged in to the network");
                            }
                            if (!TextUtils.isEmpty(obj)) {
                                this.k.append(" #csid:" + obj);
                            }
                            kxVar.setLocationDetail(this.k.toString());
                            return kxVar;
                        }
                        bArr = ku.a(bArr);
                        if (bArr == null) {
                            kxVar.setErrorCode(5);
                            this.k.append("decrypt response data error");
                            if (!TextUtils.isEmpty(obj)) {
                                this.k.append(" #csid:" + obj);
                            }
                            kxVar.setLocationDetail(this.k.toString());
                            return kxVar;
                        }
                        kx a2 = koVar.a(bArr);
                        if (a2 == null) {
                            kx kxVar2 = new kx("");
                            kxVar2.setErrorCode(5);
                            this.k.append("location is null");
                            if (!TextUtils.isEmpty(obj)) {
                                this.k.append(" #csid:" + obj);
                            }
                            kxVar2.setLocationDetail(this.k.toString());
                            return kxVar2;
                        }
                        this.c = a2.a();
                        if (a2.getErrorCode() != 0) {
                            if (!TextUtils.isEmpty(obj)) {
                                a2.setLocationDetail(a2.getLocationDetail() + " #csid:" + obj);
                            }
                            return a2;
                        } else if (kp.a(a2)) {
                            if (a2.e() != null) {
                            }
                            if (a2.getErrorCode() == 0 && a2.getLocationType() == 0) {
                                if ("-5".equals(a2.d()) || "1".equals(a2.d()) || "2".equals(a2.d()) || Constants.VIA_REPORT_TYPE_MAKE_FRIEND.equals(a2.d()) || "24".equals(a2.d()) || WeiboAuthException.DEFAULT_AUTH_ERROR_CODE.equals(a2.d())) {
                                    a2.setLocationType(5);
                                } else {
                                    a2.setLocationType(6);
                                }
                                this.k.append(a2.d());
                                if (!TextUtils.isEmpty(obj)) {
                                    this.k.append(" #csid:" + obj);
                                }
                                a2.setLocationDetail(this.k.toString());
                            }
                            return a2;
                        } else {
                            String b = a2.b();
                            a2.setErrorCode(6);
                            StringBuilder stringBuilder = this.k;
                            StringBuilder append = new StringBuilder("location faile retype:").append(a2.d()).append(" rdesc:");
                            if (b == null) {
                                b = "null";
                            }
                            stringBuilder.append(append.append(b).toString());
                            if (!TextUtils.isEmpty(obj)) {
                                this.k.append(" #csid:" + obj);
                            }
                            a2.setLocationDetail(this.k.toString());
                            return a2;
                        }
                    } catch (Throwable th) {
                        kz.a(th, "MapNetLocation", "getApsLoc req");
                        kxVar.setErrorCode(4);
                        this.k.append("please check the network");
                        kxVar.setLocationDetail(this.k.toString());
                        return kxVar;
                    }
                } catch (Throwable th2) {
                    kz.a(th2, "MapNetLocation", "getApsLoc buildV4Dot2");
                    kxVar.setErrorCode(3);
                    this.k.append("buildV4Dot2 error " + th2.getMessage());
                    kxVar.setLocationDetail(this.k.toString());
                    return kxVar;
                }
            } catch (Throwable th22) {
                kz.a(th22, "MapNetLocation", "getApsLoc");
                this.k.append("get parames error:" + th22.getMessage());
                kxVar.setErrorCode(3);
                kxVar.setLocationDetail(this.k.toString());
                return kxVar;
            }
        }
        kxVar.setErrorCode(15);
        return kxVar;
    }

    public final Inner_3dMap_location a() {
        if (this.k.length() > 0) {
            this.k.delete(0, this.k.length());
        }
        if (a(this.d) && kp.a(this.m)) {
            return this.m;
        }
        this.d = lc.b();
        if (this.a == null) {
            this.k.append("context is null");
            Inner_3dMap_location inner_3dMap_location = new Inner_3dMap_location("");
            inner_3dMap_location.setErrorCode(1);
            inner_3dMap_location.setLocationDetail(this.k.toString());
            return inner_3dMap_location;
        }
        try {
            this.f.f();
        } catch (Throwable th) {
            kz.a(th, "MapNetLocation", "getLocation getCgiListParam");
        }
        try {
            this.e.b(true);
        } catch (Throwable th2) {
            kz.a(th2, "MapNetLocation", "getLocation getScanResultsParam");
        }
        try {
            this.m = d();
            this.m = a(this.m, new String[0]);
        } catch (Throwable th22) {
            kz.a(th22, "MapNetLocation", "getLocation getScanResultsParam");
        }
        return this.m;
    }

    public final void a(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        this.l = inner_3dMap_locationOption;
        if (this.l == null) {
            this.l = new Inner_3dMap_locationOption();
        }
        try {
            kt ktVar = this.e;
            this.l.isWifiActiveScan();
            ktVar.c(this.l.isWifiScan());
        } catch (Throwable th) {
        }
        try {
            this.h.a(this.l.getHttpTimeOut(), this.l.getLocationProtocol().equals(Inner_3dMap_Enum_LocationProtocol.HTTPS));
        } catch (Throwable th2) {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
        r3 = this;
        r2 = 0;
        r0 = 0;
        r3.b = r0;
        r3.c = r2;
        r0 = r3.a;	 Catch:{ Throwable -> 0x002a, all -> 0x002e }
        if (r0 == 0) goto L_0x0015;
    L_0x000a:
        r0 = r3.g;	 Catch:{ Throwable -> 0x002a, all -> 0x002e }
        if (r0 == 0) goto L_0x0015;
    L_0x000e:
        r0 = r3.a;	 Catch:{ Throwable -> 0x002a, all -> 0x002e }
        r1 = r3.g;	 Catch:{ Throwable -> 0x002a, all -> 0x002e }
        r0.unregisterReceiver(r1);	 Catch:{ Throwable -> 0x002a, all -> 0x002e }
    L_0x0015:
        r0 = r3.f;	 Catch:{ Throwable -> 0x002a, all -> 0x002e }
        if (r0 == 0) goto L_0x001e;
    L_0x0019:
        r0 = r3.f;	 Catch:{ Throwable -> 0x002a, all -> 0x002e }
        r0.g();	 Catch:{ Throwable -> 0x002a, all -> 0x002e }
    L_0x001e:
        r0 = r3.e;	 Catch:{ Throwable -> 0x002a, all -> 0x002e }
        if (r0 == 0) goto L_0x0027;
    L_0x0022:
        r0 = r3.e;	 Catch:{ Throwable -> 0x002a, all -> 0x002e }
        r0.h();	 Catch:{ Throwable -> 0x002a, all -> 0x002e }
    L_0x0027:
        r3.g = r2;
    L_0x0029:
        return;
    L_0x002a:
        r0 = move-exception;
        r3.g = r2;
        goto L_0x0029;
    L_0x002e:
        r0 = move-exception;
        r3.g = r2;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.km.b():void");
    }
}
