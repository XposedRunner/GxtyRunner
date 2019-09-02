package com.loc;

import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.qq.e.comm.constants.ErrorCode.OtherError;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

/* compiled from: WifiManagerWrapper */
public final class by {
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

    public by(Context context, WifiManager wifiManager) {
        this.a = wifiManager;
        this.h = context;
    }

    private static boolean a(int i) {
        int i2 = 20;
        try {
            i2 = WifiManager.calculateSignalLevel(i, 20);
        } catch (Throwable e) {
            cl.a(e, "Aps", "wifiSigFine");
        }
        return i2 > 0;
    }

    public static boolean a(WifiInfo wifiInfo) {
        return (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getSSID()) || !ct.a(wifiInfo.getBSSID())) ? false : true;
    }

    public static String k() {
        return String.valueOf(ct.b() - f);
    }

    private List<ScanResult> m() {
        if (this.a != null) {
            try {
                List<ScanResult> scanResults = this.a.getScanResults();
                this.m = null;
                return scanResults;
            } catch (SecurityException e) {
                this.m = e.getMessage();
            } catch (Throwable th) {
                this.m = null;
                cl.a(th, "WifiManagerWrapper", "getScanResults");
            }
        }
        return null;
    }

    private WifiInfo n() {
        try {
            if (this.a != null) {
                return this.a.getConnectionInfo();
            }
        } catch (Throwable th) {
            cl.a(th, "WifiManagerWrapper", "getConnectionInfo");
        }
        return null;
    }

    private List<WifiConfiguration> o() {
        return this.a != null ? this.a.getConfiguredNetworks() : null;
    }

    private void p() {
        if (q()) {
            try {
                boolean startScan;
                if (ct.b() - c >= 4900) {
                    if (this.p == null) {
                        this.p = (ConnectivityManager) ct.a(this.h, "connectivity");
                    }
                    if ((!a(this.p) || ct.b() - c >= 9900) && this.a != null) {
                        c = ct.b();
                        startScan = this.a.startScan();
                        if (startScan) {
                            e = ct.b();
                        }
                    }
                }
                startScan = false;
                if (startScan) {
                    e = ct.b();
                }
            } catch (Throwable th) {
                cl.a(th, "WifiManager", "wifiScan");
            }
        }
    }

    private boolean q() {
        this.o = this.a == null ? false : ct.g(this.h);
        return (this.o && this.k) ? e == 0 ? true : (ct.b() - e < 4900 || ct.b() - f < 1500) ? false : ct.b() - f > 4900 ? true : true : false;
    }

    public final String a() {
        return this.m;
    }

    public final void a(boolean z) {
        Context context = this.h;
        if (this.a != null && context != null && z && ct.c() > 17) {
            ContentResolver contentResolver = context.getContentResolver();
            String str = "android.provider.Settings$Global";
            try {
                if (((Integer) co.a(str, "getInt", new Object[]{contentResolver, "wifi_scan_always_enabled"}, new Class[]{ContentResolver.class, String.class})).intValue() == 0) {
                    co.a(str, "putInt", new Object[]{contentResolver, "wifi_scan_always_enabled", Integer.valueOf(1)}, new Class[]{ContentResolver.class, String.class, Integer.TYPE});
                }
            } catch (Throwable th) {
                cl.a(th, "WifiManagerWrapper", "enableWifiAlwaysScan");
            }
        }
    }

    public final void a(boolean z, boolean z2) {
        this.k = z;
        this.l = z2;
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
        r3 = com.loc.ct.a(r3);	 Catch:{ Throwable -> 0x001d }
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
        com.loc.cl.a(r0, r2, r3);
    L_0x0025:
        r0 = r1;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.by.a(android.net.ConnectivityManager):boolean");
    }

    public final ArrayList<ScanResult> b() {
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

    public final void b(boolean z) {
        Collection collection;
        if (!z) {
            p();
        } else if (q()) {
            long b = ct.b();
            if (b - d >= 10000) {
                this.b.clear();
                g = f;
            }
            p();
            if (b - d >= 10000) {
                for (int i = 20; i > 0 && f == g; i--) {
                    try {
                        Thread.sleep(150);
                    } catch (Throwable th) {
                    }
                }
            }
        }
        if (this.q) {
            this.q = false;
            c();
        }
        if (g != f) {
            collection = null;
            try {
                collection = m();
            } catch (Throwable th2) {
                cl.a(th2, "WifiManager", "updateScanResult");
            }
            g = f;
            if (collection != null) {
                this.b.clear();
                this.b.addAll(collection);
            } else {
                this.b.clear();
            }
        }
        if (ct.b() - f > 20000) {
            this.b.clear();
        }
        d = ct.b();
        if (this.b.isEmpty()) {
            f = ct.b();
            collection = m();
            if (collection != null) {
                this.b.addAll(collection);
            }
        }
        if (this.b != null && !this.b.isEmpty()) {
            ScanResult scanResult;
            if (ct.b() - f > 3600000) {
                c();
            }
            if (this.n == null) {
                this.n = new TreeMap(Collections.reverseOrder());
            }
            this.n.clear();
            int size = this.b.size();
            for (int i2 = 0; i2 < size; i2++) {
                scanResult = (ScanResult) this.b.get(i2);
                if (ct.a(scanResult != null ? scanResult.BSSID : "") && (size <= 20 || a(scanResult.level))) {
                    if (TextUtils.isEmpty(scanResult.SSID)) {
                        scanResult.SSID = "unkwn";
                    } else if (!"<unknown ssid>".equals(scanResult.SSID)) {
                        scanResult.SSID = String.valueOf(i2);
                    }
                    this.n.put(Integer.valueOf((scanResult.level * 25) + i2), scanResult);
                }
            }
            this.b.clear();
            for (ScanResult scanResult2 : this.n.values()) {
                this.b.add(scanResult2);
            }
            this.n.clear();
        }
    }

    public final void c() {
        this.r = null;
        this.b.clear();
    }

    public final void d() {
        if (this.a != null && ct.b() - f > 4900) {
            f = ct.b();
        }
    }

    public final void e() {
        int i = 4;
        if (this.a != null) {
            try {
                if (this.a != null) {
                    i = this.a.getWifiState();
                }
            } catch (Throwable th) {
                cl.a(th, "Aps", "onReceive part");
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

    public final boolean f() {
        return this.o;
    }

    public final WifiInfo g() {
        this.r = n();
        return this.r;
    }

    public final boolean h() {
        return this.i;
    }

    public final String i() {
        boolean z;
        int i = 0;
        if (this.j == null) {
            this.j = new StringBuilder(OtherError.VIDEO_DOWNLOAD_ERROR);
        } else {
            this.j.delete(0, this.j.length());
        }
        this.i = false;
        String str = "";
        this.r = g();
        if (a(this.r)) {
            Object bssid = this.r.getBSSID();
        } else {
            String str2 = str;
        }
        int size = this.b.size();
        int i2 = 0;
        int i3 = 0;
        boolean z2 = false;
        while (i2 < size) {
            String str3;
            String str4 = ((ScanResult) this.b.get(i2)).BSSID;
            if (!this.l) {
                if (!"<unknown ssid>".equals(((ScanResult) this.b.get(i2)).SSID)) {
                    z = true;
                    str3 = "nb";
                    if (bssid.equals(str4)) {
                        str3 = "access";
                        i3 = true;
                    }
                    this.j.append(String.format(Locale.US, "#%s,%s", new Object[]{str4, str3}));
                    i2++;
                    z2 = z;
                }
            }
            z = z2;
            str3 = "nb";
            if (bssid.equals(str4)) {
                str3 = "access";
                i3 = true;
            }
            this.j.append(String.format(Locale.US, "#%s,%s", new Object[]{str4, str3}));
            i2++;
            z2 = z;
        }
        boolean z3 = this.b.size() == 0 ? true : z2;
        int i4;
        try {
            if (this.l || z3) {
                i4 = 0;
                if (!(this.l || z3 || r3 != 0)) {
                    this.i = true;
                }
                if (i3 == 0 && !TextUtils.isEmpty(bssid)) {
                    this.j.append("#").append(bssid);
                    this.j.append(",access");
                }
                return this.j.toString();
            }
            List o = o();
            z2 = false;
            while (o != null) {
                try {
                    if (i >= o.size()) {
                        break;
                    }
                    z = this.j.toString().contains(((WifiConfiguration) o.get(i)).BSSID) ? true : z2;
                    i++;
                    z2 = z;
                } catch (Throwable th) {
                }
            }
            this.i = true;
            this.j.append("#").append(bssid);
            this.j.append(",access");
            return this.j.toString();
        } catch (Throwable th2) {
            i4 = 0;
        }
    }

    public final void j() {
        c();
        this.b.clear();
    }

    public final boolean l() {
        try {
            List o = o();
            return (o == null || o.isEmpty()) ? false : true;
        } catch (Throwable th) {
            return false;
        }
    }
}
