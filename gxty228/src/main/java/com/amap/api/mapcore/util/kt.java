package com.amap.api.mapcore.util;

import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/* compiled from: WifiManagerWrapper */
public final class kt {
    static long c = 0;
    static long d = 0;
    static long e = 0;
    static long f = 0;
    static long g = 0;
    WifiManager a;
    ArrayList<ScanResult> b = new ArrayList();
    Context h;
    boolean i = false;
    StringBuilder j = null;
    boolean k = true;
    boolean l = true;
    String m = null;
    TreeMap<Integer, ScanResult> n = null;
    public boolean o = true;
    ConnectivityManager p = null;
    volatile boolean q = false;
    private volatile WifiInfo r = null;

    public kt(Context context, WifiManager wifiManager) {
        this.a = wifiManager;
        this.h = context;
    }

    private static boolean a(int i) {
        int i2 = 20;
        try {
            i2 = WifiManager.calculateSignalLevel(i, 20);
        } catch (Throwable e) {
            kz.a(e, "Aps", "wifiSigFine");
        }
        return i2 > 0;
    }

    public static boolean a(WifiInfo wifiInfo) {
        return (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getSSID()) || !lc.a(wifiInfo.getBSSID())) ? false : true;
    }

    public static String i() {
        return String.valueOf(lc.b() - f);
    }

    private List<ScanResult> j() {
        if (this.a != null) {
            try {
                List<ScanResult> scanResults = this.a.getScanResults();
                this.m = null;
                return scanResults;
            } catch (SecurityException e) {
                this.m = e.getMessage();
            } catch (Throwable th) {
                this.m = null;
                kz.a(th, "WifiManagerWrapper", "getScanResults");
            }
        }
        return null;
    }

    private WifiInfo k() {
        try {
            if (this.a != null) {
                return this.a.getConnectionInfo();
            }
        } catch (Throwable th) {
            kz.a(th, "WifiManagerWrapper", "getConnectionInfo");
        }
        return null;
    }

    private int l() {
        return this.a != null ? this.a.getWifiState() : 4;
    }

    private boolean m() {
        if (lc.b() - c < 4900) {
            return false;
        }
        if ((n() && lc.b() - c < 9900) || this.a == null) {
            return false;
        }
        c = lc.b();
        return this.a.startScan();
    }

    private boolean n() {
        if (this.p == null) {
            this.p = (ConnectivityManager) lc.a(this.h, "connectivity");
        }
        return a(this.p);
    }

    private boolean o() {
        return this.a == null ? false : lc.c(this.h);
    }

    private void p() {
        if (this.b != null && !this.b.isEmpty()) {
            ScanResult scanResult;
            if (lc.b() - f > 3600000) {
                b();
            }
            if (this.n == null) {
                this.n = new TreeMap(Collections.reverseOrder());
            }
            this.n.clear();
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                scanResult = (ScanResult) this.b.get(i);
                if (lc.a(scanResult != null ? scanResult.BSSID : "") && (size <= 20 || a(scanResult.level))) {
                    if (TextUtils.isEmpty(scanResult.SSID)) {
                        scanResult.SSID = "unkwn";
                    } else if (!"<unknown ssid>".equals(scanResult.SSID)) {
                        scanResult.SSID = String.valueOf(i);
                    }
                    this.n.put(Integer.valueOf((scanResult.level * 25) + i), scanResult);
                }
            }
            this.b.clear();
            for (ScanResult scanResult2 : this.n.values()) {
                this.b.add(scanResult2);
            }
            this.n.clear();
        }
    }

    private void q() {
        if (t()) {
            long b = lc.b();
            if (b - d >= 10000) {
                this.b.clear();
                g = f;
            }
            r();
            if (b - d >= 10000) {
                for (int i = 20; i > 0 && f == g; i--) {
                    try {
                        Thread.sleep(150);
                    } catch (Throwable th) {
                    }
                }
            }
        }
    }

    private void r() {
        if (t()) {
            try {
                if (m()) {
                    e = lc.b();
                }
            } catch (Throwable th) {
                kz.a(th, "WifiManager", "wifiScan");
            }
        }
    }

    private void s() {
        if (g != f) {
            Collection collection = null;
            try {
                collection = j();
            } catch (Throwable th) {
                kz.a(th, "WifiManager", "updateScanResult");
            }
            g = f;
            if (collection != null) {
                this.b.clear();
                this.b.addAll(collection);
                return;
            }
            this.b.clear();
        }
    }

    private boolean t() {
        this.o = o();
        return (this.o && this.k) ? e == 0 ? true : (lc.b() - e < 4900 || lc.b() - f < 1500) ? false : lc.b() - f > 4900 ? true : true : false;
    }

    public final ArrayList<ScanResult> a() {
        if (this.b == null) {
            return null;
        }
        ArrayList<ScanResult> arrayList = new ArrayList();
        if (this.b.isEmpty()) {
            return arrayList;
        }
        arrayList.addAll(this.b);
        return arrayList;
    }

    public final void a(boolean z) {
        Context context = this.h;
        if (this.a != null && context != null && z && lc.c() > 17) {
            ContentResolver contentResolver = context.getContentResolver();
            String str = "android.provider.Settings$Global";
            try {
                if (((Integer) la.a(str, "getInt", new Object[]{contentResolver, "wifi_scan_always_enabled"}, new Class[]{ContentResolver.class, String.class})).intValue() == 0) {
                    la.a(str, "putInt", new Object[]{contentResolver, "wifi_scan_always_enabled", Integer.valueOf(1)}, new Class[]{ContentResolver.class, String.class, Integer.TYPE});
                }
            } catch (Throwable th) {
                kz.a(th, "WifiManagerWrapper", "enableWifiAlwaysScan");
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(android.net.ConnectivityManager r5) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        r2 = r4.a;
        if (r2 != 0) goto L_0x0007;
    L_0x0006:
        return r1;
    L_0x0007:
        r3 = r5.getActiveNetworkInfo();	 Catch:{ Throwable -> 0x001d }
        r3 = com.amap.api.mapcore.util.lc.a(r3);	 Catch:{ Throwable -> 0x001d }
        if (r3 != r0) goto L_0x0025;
    L_0x0011:
        r2 = r2.getConnectionInfo();	 Catch:{ Throwable -> 0x001d }
        r2 = a(r2);	 Catch:{ Throwable -> 0x001d }
        if (r2 == 0) goto L_0x0025;
    L_0x001b:
        r1 = r0;
        goto L_0x0006;
    L_0x001d:
        r0 = move-exception;
        r2 = "WifiManagerWrapper";
        r3 = "wifiAccess";
        com.amap.api.mapcore.util.kz.a(r0, r2, r3);
    L_0x0025:
        r0 = r1;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.kt.a(android.net.ConnectivityManager):boolean");
    }

    public final void b() {
        this.r = null;
        this.b.clear();
    }

    public final void b(boolean z) {
        if (z) {
            q();
        } else {
            r();
        }
        if (this.q) {
            this.q = false;
            b();
        }
        s();
        if (lc.b() - f > 20000) {
            this.b.clear();
        }
        d = lc.b();
        if (this.b.isEmpty()) {
            f = lc.b();
            Collection j = j();
            if (j != null) {
                this.b.addAll(j);
            }
        }
        p();
    }

    public final void c() {
        if (this.a != null && lc.b() - f > 4900) {
            f = lc.b();
        }
    }

    public final void c(boolean z) {
        this.k = z;
        this.l = true;
    }

    public final void d() {
        if (this.a != null) {
            int i = 4;
            try {
                i = l();
            } catch (Throwable th) {
                kz.a(th, "Aps", "onReceive part");
            }
            if (this.b == null) {
                this.b = new ArrayList();
            }
            switch (i) {
                case 0:
                case 1:
                case 4:
                    this.q = true;
                    return;
                default:
                    return;
            }
        }
    }

    public final boolean e() {
        return this.o;
    }

    public final WifiInfo f() {
        this.r = k();
        return this.r;
    }

    public final boolean g() {
        return this.i;
    }

    public final void h() {
        b();
        this.b.clear();
    }
}
