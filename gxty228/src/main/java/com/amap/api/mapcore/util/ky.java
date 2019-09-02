package com.amap.api.mapcore.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.zip.CRC32;

@SuppressLint({"NewApi"})
/* compiled from: Req */
public final class ky {
    public static String J = null;
    public static String L = null;
    public String A = null;
    public String B = null;
    public ArrayList<kr> C = new ArrayList();
    public String D = null;
    public String E = null;
    public ArrayList<ScanResult> F = new ArrayList();
    public String G = null;
    public String H = null;
    public byte[] I = null;
    public String K = null;
    public String M = null;
    private byte[] N = null;
    private int O = 0;
    public String a = "1";
    public short b = (short) 0;
    public String c = null;
    public String d = null;
    public String e = null;
    public String f = null;
    public String g = null;
    public String h = null;
    public String i = null;
    public String j = null;
    public String k = null;
    public String l = null;
    public String m = null;
    public String n = null;
    public String o = null;
    public String p = null;
    public String q = null;
    public String r = null;
    public String s = null;
    public String t = null;
    public String u = null;
    public String v = null;
    public String w = null;
    public String x = null;
    public String y = null;
    public int z = 0;

    private String a(String str, int i) {
        String[] split = this.B.split("\\*")[i].split(",");
        return "lac".equals(str) ? split[0] : "cellid".equals(str) ? split[1] : "signal".equals(str) ? split[2] : null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] a(java.lang.String r8) {
        /*
        r7 = this;
        r6 = 2;
        r4 = 6;
        r2 = 0;
        r0 = ":";
        r0 = r8.split(r0);
        r1 = new byte[r4];
        if (r0 == 0) goto L_0x0010;
    L_0x000d:
        r3 = r0.length;	 Catch:{ Throwable -> 0x0043 }
        if (r3 == r4) goto L_0x001e;
    L_0x0010:
        r0 = 6;
        r0 = new java.lang.String[r0];	 Catch:{ Throwable -> 0x0043 }
        r3 = r2;
    L_0x0014:
        r4 = r0.length;	 Catch:{ Throwable -> 0x0043 }
        if (r3 >= r4) goto L_0x001e;
    L_0x0017:
        r4 = "0";
        r0[r3] = r4;	 Catch:{ Throwable -> 0x0043 }
        r3 = r3 + 1;
        goto L_0x0014;
    L_0x001e:
        r3 = r0.length;	 Catch:{ Throwable -> 0x0043 }
        if (r2 >= r3) goto L_0x0041;
    L_0x0021:
        r3 = r0[r2];	 Catch:{ Throwable -> 0x0043 }
        r3 = r3.length();	 Catch:{ Throwable -> 0x0043 }
        if (r3 <= r6) goto L_0x0033;
    L_0x0029:
        r3 = r0[r2];	 Catch:{ Throwable -> 0x0043 }
        r4 = 0;
        r5 = 2;
        r3 = r3.substring(r4, r5);	 Catch:{ Throwable -> 0x0043 }
        r0[r2] = r3;	 Catch:{ Throwable -> 0x0043 }
    L_0x0033:
        r3 = r0[r2];	 Catch:{ Throwable -> 0x0043 }
        r4 = 16;
        r3 = java.lang.Integer.parseInt(r3, r4);	 Catch:{ Throwable -> 0x0043 }
        r3 = (byte) r3;	 Catch:{ Throwable -> 0x0043 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0043 }
        r2 = r2 + 1;
        goto L_0x001e;
    L_0x0041:
        r0 = r1;
    L_0x0042:
        return r0;
    L_0x0043:
        r0 = move-exception;
        r1 = "Req";
        r2 = new java.lang.StringBuilder;
        r3 = "getMacBa ";
        r2.<init>(r3);
        r2 = r2.append(r8);
        r2 = r2.toString();
        com.amap.api.mapcore.util.kz.a(r0, r1, r2);
        r0 = "00:00:00:00:00:00";
        r0 = r7.a(r0);
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ky.a(java.lang.String):byte[]");
    }

    private String b(String str) {
        if (!this.A.contains(str + ">")) {
            return "0";
        }
        int indexOf = this.A.indexOf(str + ">");
        return this.A.substring((indexOf + str.length()) + 1, this.A.indexOf("</" + str));
    }

    private void b() {
        if (TextUtils.isEmpty(this.a)) {
            this.a = "";
        }
        if (TextUtils.isEmpty(this.c)) {
            this.c = "";
        }
        if (TextUtils.isEmpty(this.d)) {
            this.d = "";
        }
        if (TextUtils.isEmpty(this.e)) {
            this.e = "";
        }
        if (TextUtils.isEmpty(this.f)) {
            this.f = "";
        }
        if (TextUtils.isEmpty(this.g)) {
            this.g = "";
        }
        if (TextUtils.isEmpty(this.h)) {
            this.h = "";
        }
        if (TextUtils.isEmpty(this.i)) {
            this.i = "";
        }
        if (TextUtils.isEmpty(this.j)) {
            this.j = "0";
        } else if (!("0".equals(this.j) || "2".equals(this.j))) {
            this.j = "0";
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = "0";
        } else if (!("0".equals(this.k) || "1".equals(this.k))) {
            this.k = "0";
        }
        if (TextUtils.isEmpty(this.l)) {
            this.l = "";
        }
        if (TextUtils.isEmpty(this.m)) {
            this.m = "";
        }
        if (TextUtils.isEmpty(this.n)) {
            this.n = "";
        }
        if (TextUtils.isEmpty(this.o)) {
            this.o = "";
        }
        if (TextUtils.isEmpty(this.p)) {
            this.p = "";
        }
        if (TextUtils.isEmpty(this.q)) {
            this.q = "";
        }
        if (TextUtils.isEmpty(this.r)) {
            this.r = "";
        }
        if (TextUtils.isEmpty(this.s)) {
            this.s = "";
        }
        if (TextUtils.isEmpty(this.t)) {
            this.t = "";
        }
        if (TextUtils.isEmpty(this.u)) {
            this.u = "";
        }
        if (TextUtils.isEmpty(this.v)) {
            this.v = "";
        }
        if (TextUtils.isEmpty(this.w)) {
            this.w = "";
        }
        if (TextUtils.isEmpty(this.x)) {
            this.x = "";
        }
        if (TextUtils.isEmpty(this.y)) {
            this.y = "0";
        } else if (!("1".equals(this.y) || "2".equals(this.y))) {
            this.y = "0";
        }
        if (!ks.a(this.z)) {
            this.z = 0;
        }
        if (TextUtils.isEmpty(this.A)) {
            this.A = "";
        }
        if (TextUtils.isEmpty(this.B)) {
            this.B = "";
        }
        if (TextUtils.isEmpty(this.E)) {
            this.E = "";
        }
        if (TextUtils.isEmpty(this.G)) {
            this.G = "";
        }
        if (TextUtils.isEmpty(this.H)) {
            this.H = "";
        }
        if (TextUtils.isEmpty(J)) {
            J = "";
        }
        if (this.I == null) {
            this.I = new byte[0];
        }
    }

    public final void a(Context context, boolean z, boolean z2, ks ksVar, kt ktVar, ConnectivityManager connectivityManager, String str) {
        String str2;
        int i;
        String str3 = "0";
        String str4 = "0";
        String str5 = "0";
        String str6 = "0";
        String str7 = "0";
        String f = fx.f(context);
        int f2 = lc.f();
        String str8 = "";
        String str9 = "";
        String str10 = "";
        this.K = str;
        String str11 = "api_serverSDK_130905";
        String str12 = "S128DF1572465B890OE3F7A13167KLEI";
        if (z2) {
            str2 = str11;
            str11 = str12;
        } else {
            str2 = "UC_nlp_20131029";
            str11 = "BKZCHMBBSSUK7U8GLUKHBB56CCFF78U";
        }
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        int c = ksVar.c();
        int d = ksVar.d();
        TelephonyManager e = ksVar.e();
        ArrayList a = ksVar.a();
        ArrayList b = ksVar.b();
        ArrayList a2 = ktVar.a();
        String str13 = d == 2 ? "1" : str3;
        if (e != null) {
            if (TextUtils.isEmpty(kz.d)) {
                try {
                    kz.d = gd.u(context);
                } catch (Throwable th) {
                    kz.a(th, "Aps", "getApsReq part4");
                }
            }
            if (TextUtils.isEmpty(kz.d)) {
                kz.d = "888888888888888";
            }
            if (TextUtils.isEmpty(kz.e)) {
                try {
                    kz.e = e.getSubscriberId();
                } catch (SecurityException e2) {
                } catch (Throwable th2) {
                    kz.a(th2, "Aps", "getApsReq part2");
                }
            }
            if (TextUtils.isEmpty(kz.e)) {
                kz.e = "888888888888888";
            }
        }
        NetworkInfo networkInfo = null;
        try {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        } catch (Throwable th3) {
            kz.a(th3, "Aps", "getApsReq part");
        }
        WifiInfo f3 = ktVar.f();
        boolean a3 = kt.a(f3);
        if (lc.a(networkInfo) != -1) {
            str9 = lc.b(e);
            if (a3 && ktVar.e()) {
                str8 = str9;
                str9 = "2";
            } else {
                str8 = str9;
                str9 = "1";
            }
        }
        if (a.isEmpty()) {
            str3 = str10;
        } else {
            StringBuilder stringBuilder3 = new StringBuilder();
            kr krVar;
            switch (d) {
                case 1:
                    krVar = (kr) a.get(0);
                    stringBuilder3.delete(0, stringBuilder3.length());
                    stringBuilder3.append("<mcc>").append(krVar.a).append("</mcc>");
                    stringBuilder3.append("<mnc>").append(krVar.b).append("</mnc>");
                    stringBuilder3.append("<lac>").append(krVar.c).append("</lac>");
                    stringBuilder3.append("<cellid>").append(krVar.d);
                    stringBuilder3.append("</cellid>");
                    stringBuilder3.append("<signal>").append(krVar.j);
                    stringBuilder3.append("</signal>");
                    str3 = stringBuilder3.toString();
                    for (i = 1; i < a.size(); i++) {
                        krVar = (kr) a.get(i);
                        stringBuilder.append(krVar.c).append(",");
                        stringBuilder.append(krVar.d).append(",");
                        stringBuilder.append(krVar.j);
                        if (i < a.size() - 1) {
                            stringBuilder.append("*");
                        }
                    }
                    str12 = str3;
                    break;
                case 2:
                    krVar = (kr) a.get(0);
                    stringBuilder3.delete(0, stringBuilder3.length());
                    stringBuilder3.append("<mcc>").append(krVar.a).append("</mcc>");
                    stringBuilder3.append("<sid>").append(krVar.g).append("</sid>");
                    stringBuilder3.append("<nid>").append(krVar.h).append("</nid>");
                    stringBuilder3.append("<bid>").append(krVar.i).append("</bid>");
                    if (krVar.f > 0 && krVar.e > 0) {
                        stringBuilder3.append("<lon>").append(krVar.f).append("</lon>");
                        stringBuilder3.append("<lat>").append(krVar.e).append("</lat>");
                    }
                    stringBuilder3.append("<signal>").append(krVar.j).append("</signal>");
                    str12 = stringBuilder3.toString();
                    break;
                default:
                    str12 = str10;
                    break;
            }
            stringBuilder3.delete(0, stringBuilder3.length());
            str3 = str12;
        }
        if ((c & 4) != 4 || b.isEmpty()) {
            this.C.clear();
        } else {
            this.C.clear();
            this.C.addAll(b);
        }
        if (ktVar.e()) {
            if (a3) {
                stringBuilder2.append(ktVar.f().getBSSID()).append(",");
                int rssi = ktVar.f().getRssi();
                if (rssi < -128) {
                    rssi = 0;
                } else if (rssi > 127) {
                    rssi = 0;
                }
                stringBuilder2.append(rssi).append(",");
                str12 = f3.getSSID();
                i = 32;
                try {
                    i = f3.getSSID().getBytes("UTF-8").length;
                } catch (Exception e3) {
                }
                if (i >= 32) {
                    str12 = "unkwn";
                }
                stringBuilder2.append(str12.replace("*", "."));
            }
            if (!(a2 == null || this.F == null)) {
                this.F.clear();
                this.F.addAll(a2);
            }
        } else {
            ktVar.b();
            if (this.F != null) {
                this.F.clear();
            }
        }
        if (z) {
            this.b = (short) 0;
        } else {
            this.b = (short) 2;
        }
        this.c = str2;
        this.d = str11;
        this.f = lc.d();
        this.g = "android" + lc.e();
        this.h = lc.b(context);
        this.i = str13;
        this.j = str4;
        this.k = "0";
        this.l = str5;
        this.m = str6;
        this.n = str7;
        this.o = f;
        this.p = kz.d;
        this.q = kz.e;
        this.s = String.valueOf(f2);
        this.t = lc.d(context);
        this.v = "4.2.0";
        this.w = null;
        this.u = "";
        this.x = str8;
        this.y = str9;
        this.z = c;
        this.A = str3;
        this.B = stringBuilder.toString();
        this.D = ksVar.i();
        this.G = kt.i();
        this.E = stringBuilder2.toString();
        try {
            if (TextUtils.isEmpty(J)) {
                J = gd.h(context);
            }
        } catch (Throwable th4) {
        }
        try {
            if (TextUtils.isEmpty(L)) {
                L = gd.b(context);
            }
        } catch (Throwable th5) {
        }
        stringBuilder.delete(0, stringBuilder.length());
        stringBuilder2.delete(0, stringBuilder2.length());
    }

    public final byte[] a() {
        byte[] bArr;
        Object b;
        int length;
        Object b2;
        ArrayList arrayList;
        int i;
        Object a;
        String[] split;
        ArrayList arrayList2;
        int min;
        Object obj;
        long j;
        int i2;
        ScanResult scanResult;
        Object a2;
        boolean isEmpty;
        byte[] bArr2;
        CRC32 crc32;
        Object a3;
        b();
        byte[] bArr3 = new byte[2];
        byte[] bArr4 = new byte[4];
        int i3 = 4096;
        if (this.I != null) {
            i3 = (this.I.length + 1) + 4096;
        }
        if (this.N == null || i3 > this.O) {
            bArr = new byte[i3];
            this.N = bArr;
            this.O = i3;
        } else {
            bArr = this.N;
        }
        bArr[0] = lc.e(this.a);
        Object a4 = lc.a(this.b, null);
        System.arraycopy(a4, 0, bArr, 1, a4.length);
        int length2 = a4.length + 1;
        try {
            a4 = this.c.getBytes("GBK");
            bArr[length2] = (byte) a4.length;
            length2++;
            System.arraycopy(a4, 0, bArr, length2, a4.length);
            length2 += a4.length;
        } catch (Throwable th) {
            kz.a(th, "Req", "buildV4Dot2");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            a4 = this.d.getBytes("GBK");
            bArr[length2] = (byte) a4.length;
            length2++;
            System.arraycopy(a4, 0, bArr, length2, a4.length);
            length2 += a4.length;
        } catch (Throwable th2) {
            kz.a(th2, "Req", "buildV4Dot21");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            a4 = this.o.getBytes("GBK");
            bArr[length2] = (byte) a4.length;
            length2++;
            System.arraycopy(a4, 0, bArr, length2, a4.length);
            length2 += a4.length;
        } catch (Throwable th22) {
            kz.a(th22, "Req", "buildV4Dot22");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            a4 = this.e.getBytes("GBK");
            bArr[length2] = (byte) a4.length;
            length2++;
            System.arraycopy(a4, 0, bArr, length2, a4.length);
            length2 += a4.length;
        } catch (Throwable th222) {
            kz.a(th222, "Req", "buildV4Dot23");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            a4 = this.f.getBytes("GBK");
            bArr[length2] = (byte) a4.length;
            length2++;
            System.arraycopy(a4, 0, bArr, length2, a4.length);
            length2 += a4.length;
        } catch (Throwable th2222) {
            kz.a(th2222, "Req", "buildV4Dot24");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            a4 = this.g.getBytes("GBK");
            bArr[length2] = (byte) a4.length;
            length2++;
            System.arraycopy(a4, 0, bArr, length2, a4.length);
            length2 += a4.length;
        } catch (Throwable th22222) {
            kz.a(th22222, "Req", "buildV4Dot25");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            a4 = this.u.getBytes("GBK");
            bArr[length2] = (byte) a4.length;
            length2++;
            System.arraycopy(a4, 0, bArr, length2, a4.length);
            length2 += a4.length;
        } catch (Throwable th222222) {
            kz.a(th222222, "Req", "buildV4Dot26");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            a4 = this.h.getBytes("GBK");
            bArr[length2] = (byte) a4.length;
            length2++;
            System.arraycopy(a4, 0, bArr, length2, a4.length);
            length2 += a4.length;
        } catch (Throwable th2222222) {
            kz.a(th2222222, "Req", "buildV4Dot27");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            a4 = this.p.getBytes("GBK");
            bArr[length2] = (byte) a4.length;
            length2++;
            System.arraycopy(a4, 0, bArr, length2, a4.length);
            length2 += a4.length;
        } catch (Throwable th22222222) {
            kz.a(th22222222, "Req", "buildV4Dot28");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            a4 = this.q.getBytes("GBK");
            bArr[length2] = (byte) a4.length;
            length2++;
            System.arraycopy(a4, 0, bArr, length2, a4.length);
            length2 += a4.length;
        } catch (Throwable th222222222) {
            kz.a(th222222222, "Req", "buildV4Dot29");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            if (TextUtils.isEmpty(this.t)) {
                bArr[length2] = (byte) 0;
                length2++;
            } else {
                a4 = a(this.t);
                bArr[length2] = (byte) a4.length;
                length2++;
                System.arraycopy(a4, 0, bArr, length2, a4.length);
                length2 += a4.length;
            }
        } catch (Throwable th2222222222) {
            kz.a(th2222222222, "Req", "buildV4Dot219");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            a4 = this.v.getBytes("GBK");
            bArr[length2] = (byte) a4.length;
            length2++;
            System.arraycopy(a4, 0, bArr, length2, a4.length);
            length2 += a4.length;
        } catch (Throwable th22222222222) {
            kz.a(th22222222222, "Req", "buildV4Dot211");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            a4 = this.w.getBytes("GBK");
            bArr[length2] = (byte) a4.length;
            length2++;
            System.arraycopy(a4, 0, bArr, length2, a4.length);
            length2 += a4.length;
        } catch (Throwable th222222222222) {
            kz.a(th222222222222, "Req", "buildV4Dot212");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            if (TextUtils.isEmpty(J)) {
                bArr[length2] = (byte) 0;
                length2++;
            } else {
                a4 = J.getBytes("GBK");
                bArr[length2] = (byte) a4.length;
                length2++;
                System.arraycopy(a4, 0, bArr, length2, a4.length);
                length2 += a4.length;
            }
        } catch (Throwable th2222222222222) {
            kz.a(th2222222222222, "Req", "buildV4Dot213");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            if (TextUtils.isEmpty(L)) {
                bArr[length2] = (byte) 0;
                length2++;
            } else {
                a4 = L.getBytes("GBK");
                bArr[length2] = (byte) a4.length;
                length2++;
                System.arraycopy(a4, 0, bArr, length2, a4.length);
                length2 += a4.length;
            }
        } catch (Throwable th22222222222222) {
            kz.a(th22222222222222, "Req", "buildV4Dot214");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            a4 = this.x.getBytes("GBK");
            bArr[length2] = (byte) a4.length;
            length2++;
            System.arraycopy(a4, 0, bArr, length2, a4.length);
            i3 = a4.length + length2;
        } catch (Throwable th222222222222222) {
            kz.a(th222222222222222, "Req", "buildV4Dot213");
            bArr[length2] = (byte) 0;
            i3 = length2 + 1;
        }
        bArr[i3] = Byte.parseByte(this.y);
        i3++;
        bArr[i3] = Byte.parseByte(this.j);
        i3++;
        int i4 = this.z & 3;
        bArr[i3] = (byte) this.z;
        i3++;
        if (i4 == 1 || i4 == 2) {
            b = lc.b(b("mcc"));
            System.arraycopy(b, 0, bArr, i3, b.length);
            i3 += b.length;
            if (i4 == 1) {
                b = lc.b(b("mnc"));
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
                b = lc.b(b("lac"));
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
                b = lc.c(b("cellid"));
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
            } else if (i4 == 2) {
                b = lc.b(b("sid"));
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
                b = lc.b(b("nid"));
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
                b = lc.b(b("bid"));
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
                b = lc.c(b("lon"));
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
                b = lc.c(b("lat"));
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
            }
            length2 = Integer.parseInt(b("signal"));
            if (length2 > 127) {
                length2 = 0;
            } else if (length2 < -128) {
                length2 = 0;
            }
            bArr[i3] = (byte) length2;
            i3++;
            b = lc.a(0, bArr3);
            System.arraycopy(b, 0, bArr, i3, b.length);
            i3 += 2;
            if (i4 == 1) {
                if (TextUtils.isEmpty(this.B)) {
                    bArr[i3] = (byte) 0;
                    i3++;
                } else {
                    length = this.B.split("\\*").length;
                    bArr[i3] = (byte) length;
                    i3++;
                    length2 = 0;
                    while (length2 < length) {
                        b2 = lc.b(a("lac", length2));
                        System.arraycopy(b2, 0, bArr, i3, b2.length);
                        i3 += b2.length;
                        b2 = lc.c(a("cellid", length2));
                        System.arraycopy(b2, 0, bArr, i3, b2.length);
                        i4 = b2.length + i3;
                        i3 = Integer.parseInt(a("signal", length2));
                        if (i3 > 127) {
                            i3 = 0;
                        } else if (i3 < -128) {
                            i3 = 0;
                        }
                        bArr[i4] = (byte) i3;
                        length2++;
                        i3 = i4 + 1;
                    }
                }
            } else if (i4 == 2) {
                bArr[i3] = (byte) 0;
                i3++;
            }
        }
        String str = this.D;
        if (str != null && (this.z & 8) == 8) {
            try {
                b = str.getBytes("GBK");
                i4 = Math.min(b.length, 60);
                bArr[i3] = (byte) i4;
                i3++;
                System.arraycopy(b, 0, bArr, i3, i4);
                length2 = i3 + i4;
            } catch (Exception e) {
            }
            arrayList = this.C;
            i4 = arrayList.size();
            if ((this.z & 4) == 4 || i4 <= 0) {
                bArr[length2] = (byte) 0;
                length2++;
            } else {
                if (!((kr) arrayList.get(0)).o) {
                    i4--;
                }
                bArr[length2] = (byte) i4;
                length2++;
                for (i = 0; i < i4; i++) {
                    kr krVar = (kr) arrayList.get(i);
                    if (krVar.o) {
                        byte b3;
                        if (krVar.k == 1 || krVar.k == 3 || krVar.k == 4) {
                            b3 = (byte) krVar.k;
                            if (krVar.n) {
                                b3 = (byte) (b3 | 8);
                            }
                            bArr[length2] = b3;
                            length2++;
                            a = lc.a(krVar.a, bArr3);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = lc.a(krVar.b, bArr3);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = lc.a(krVar.c, bArr3);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = lc.b(krVar.d, bArr4);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                        } else if (krVar.k == 2) {
                            b3 = (byte) krVar.k;
                            if (krVar.n) {
                                b3 = (byte) (b3 | 8);
                            }
                            bArr[length2] = b3;
                            length2++;
                            a = lc.a(krVar.a, bArr3);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = lc.a(krVar.g, bArr3);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = lc.a(krVar.h, bArr3);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = lc.a(krVar.i, bArr3);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = lc.b(krVar.f, bArr4);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = lc.b(krVar.e, bArr4);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                        }
                        length = krVar.j;
                        if (length > 127) {
                            length = 99;
                        } else if (length < -128) {
                            length = 99;
                        }
                        bArr[length2] = (byte) length;
                        length2++;
                        a4 = lc.a(krVar.l, bArr3);
                        System.arraycopy(a4, 0, bArr, length2, a4.length);
                        length2 += a4.length;
                    }
                }
            }
            if (this.E.length() != 0) {
                bArr[length2] = (byte) 0;
                i3 = length2 + 1;
            } else {
                bArr[length2] = (byte) 1;
                length2++;
                try {
                    split = this.E.split(",");
                    a4 = a(split[0]);
                    System.arraycopy(a4, 0, bArr, length2, a4.length);
                    length2 += a4.length;
                    a4 = split[2].getBytes("GBK");
                    bArr[length2] = (byte) a4.length;
                    length2++;
                    System.arraycopy(a4, 0, bArr, length2, a4.length);
                    length2 += a4.length;
                } catch (Throwable th2222222222222222) {
                    kz.a(th2222222222222222, "Req", "buildV4Dot216");
                    a4 = a("00:00:00:00:00:00");
                    System.arraycopy(a4, 0, bArr, length2, a4.length);
                    i3 = a4.length + length2;
                    bArr[i3] = (byte) 0;
                    i3++;
                    bArr[i3] = Byte.parseByte("0");
                    i3++;
                }
                i3 = Integer.parseInt(split[1]);
                if (i3 > 127) {
                    i3 = 0;
                } else if (i3 < -128) {
                    i3 = 0;
                }
                bArr[length2] = Byte.parseByte(String.valueOf(i3));
                i3 = length2 + 1;
            }
            arrayList2 = this.F;
            min = Math.min(arrayList2.size(), 25);
            if (min != 0) {
                bArr[i3] = (byte) 0;
                i3++;
            } else {
                bArr[i3] = (byte) min;
                length = i3 + 1;
                obj = lc.c() < 17 ? 1 : null;
                j = 0;
                if (obj != null) {
                    j = lc.b() / 1000;
                }
                for (i2 = 0; i2 < min; i2++) {
                    scanResult = (ScanResult) arrayList2.get(i2);
                    a2 = a(scanResult.BSSID);
                    System.arraycopy(a2, 0, bArr, length, a2.length);
                    length += a2.length;
                    try {
                        a2 = scanResult.SSID.getBytes("GBK");
                        bArr[length] = (byte) a2.length;
                        length++;
                        System.arraycopy(a2, 0, bArr, length, a2.length);
                        length += a2.length;
                    } catch (Exception e2) {
                        bArr[length] = (byte) 0;
                        length++;
                    }
                    i = scanResult.level;
                    if (i > 127) {
                        i = 0;
                    } else if (i < -128) {
                        i = 0;
                    }
                    bArr[length] = Byte.parseByte(String.valueOf(i));
                    i = length + 1;
                    if (obj != null) {
                        length = (int) (((j - scanResult.timestamp) / 1000000) + 1);
                        if (length >= 0) {
                            a = lc.a(length, bArr3);
                            System.arraycopy(a, 0, bArr, i, a.length);
                            length = a.length + i;
                            a4 = lc.a(scanResult.frequency, bArr3);
                            System.arraycopy(a4, 0, bArr, length, a4.length);
                            length += a4.length;
                        }
                    }
                    length = 0;
                    a = lc.a(length, bArr3);
                    System.arraycopy(a, 0, bArr, i, a.length);
                    length = a.length + i;
                    a4 = lc.a(scanResult.frequency, bArr3);
                    System.arraycopy(a4, 0, bArr, length, a4.length);
                    length += a4.length;
                }
                a4 = lc.a(Integer.parseInt(this.G), bArr3);
                System.arraycopy(a4, 0, bArr, length, a4.length);
                i3 = a4.length + length;
            }
            bArr[i3] = (byte) 0;
            i3++;
            b = this.H.getBytes("GBK");
            if (b.length > 127) {
                b = null;
            }
            if (b != null) {
                bArr[i3] = (byte) 0;
                i3++;
            } else {
                bArr[i3] = (byte) b.length;
                i3++;
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
            }
            b = new byte[]{(byte) 0, (byte) 0};
            isEmpty = TextUtils.isEmpty(this.K);
            if (!isEmpty) {
                b = lc.a(this.K.length(), bArr3);
            }
            System.arraycopy(b, 0, bArr, i3, 2);
            i3 += 2;
            if (!isEmpty) {
                try {
                    b = this.K.getBytes("GBK");
                    System.arraycopy(b, 0, bArr, i3, b.length);
                    i3 += b.length;
                } catch (Throwable th3) {
                }
            }
            bArr2 = new byte[]{(byte) 0, (byte) 0};
            System.arraycopy(lc.a(0, bArr3), 0, bArr, i3, 2);
            i3 += 2;
            System.arraycopy(new byte[]{(byte) 0, (byte) 0}, 0, bArr, i3, 2);
            i3 += 2;
            length2 = 0;
            if (this.I != null) {
                length2 = this.I.length;
            }
            b2 = lc.a(length2, null);
            System.arraycopy(b2, 0, bArr, i3, b2.length);
            i3 += b2.length;
            if (length2 > 0) {
                System.arraycopy(this.I, 0, bArr, i3, this.I.length);
                i3 += this.I.length;
            }
            b = new byte[i3];
            System.arraycopy(bArr, 0, b, 0, i3);
            crc32 = new CRC32();
            crc32.update(b);
            a3 = lc.a(crc32.getValue());
            b2 = new byte[(a3.length + i3)];
            System.arraycopy(b, 0, b2, 0, i3);
            System.arraycopy(a3, 0, b2, i3, a3.length);
            return b2;
        }
        bArr[i3] = (byte) 0;
        length2 = i3 + 1;
        arrayList = this.C;
        i4 = arrayList.size();
        if ((this.z & 4) == 4) {
        }
        bArr[length2] = (byte) 0;
        length2++;
        if (this.E.length() != 0) {
            bArr[length2] = (byte) 1;
            length2++;
            split = this.E.split(",");
            a4 = a(split[0]);
            System.arraycopy(a4, 0, bArr, length2, a4.length);
            length2 += a4.length;
            a4 = split[2].getBytes("GBK");
            bArr[length2] = (byte) a4.length;
            length2++;
            System.arraycopy(a4, 0, bArr, length2, a4.length);
            length2 += a4.length;
            i3 = Integer.parseInt(split[1]);
            if (i3 > 127) {
                i3 = 0;
            } else if (i3 < -128) {
                i3 = 0;
            }
            bArr[length2] = Byte.parseByte(String.valueOf(i3));
            i3 = length2 + 1;
        } else {
            bArr[length2] = (byte) 0;
            i3 = length2 + 1;
        }
        arrayList2 = this.F;
        min = Math.min(arrayList2.size(), 25);
        if (min != 0) {
            bArr[i3] = (byte) min;
            length = i3 + 1;
            if (lc.c() < 17) {
            }
            j = 0;
            if (obj != null) {
                j = lc.b() / 1000;
            }
            for (i2 = 0; i2 < min; i2++) {
                scanResult = (ScanResult) arrayList2.get(i2);
                a2 = a(scanResult.BSSID);
                System.arraycopy(a2, 0, bArr, length, a2.length);
                length += a2.length;
                a2 = scanResult.SSID.getBytes("GBK");
                bArr[length] = (byte) a2.length;
                length++;
                System.arraycopy(a2, 0, bArr, length, a2.length);
                length += a2.length;
                i = scanResult.level;
                if (i > 127) {
                    i = 0;
                } else if (i < -128) {
                    i = 0;
                }
                bArr[length] = Byte.parseByte(String.valueOf(i));
                i = length + 1;
                if (obj != null) {
                    length = (int) (((j - scanResult.timestamp) / 1000000) + 1);
                    if (length >= 0) {
                        a = lc.a(length, bArr3);
                        System.arraycopy(a, 0, bArr, i, a.length);
                        length = a.length + i;
                        a4 = lc.a(scanResult.frequency, bArr3);
                        System.arraycopy(a4, 0, bArr, length, a4.length);
                        length += a4.length;
                    }
                }
                length = 0;
                a = lc.a(length, bArr3);
                System.arraycopy(a, 0, bArr, i, a.length);
                length = a.length + i;
                a4 = lc.a(scanResult.frequency, bArr3);
                System.arraycopy(a4, 0, bArr, length, a4.length);
                length += a4.length;
            }
            a4 = lc.a(Integer.parseInt(this.G), bArr3);
            System.arraycopy(a4, 0, bArr, length, a4.length);
            i3 = a4.length + length;
        } else {
            bArr[i3] = (byte) 0;
            i3++;
        }
        bArr[i3] = (byte) 0;
        i3++;
        try {
            b = this.H.getBytes("GBK");
            if (b.length > 127) {
                b = null;
            }
            if (b != null) {
                bArr[i3] = (byte) b.length;
                i3++;
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
            } else {
                bArr[i3] = (byte) 0;
                i3++;
            }
        } catch (Throwable th4) {
            bArr[i3] = (byte) 0;
            i3++;
        }
        b = new byte[]{(byte) 0, (byte) 0};
        try {
            isEmpty = TextUtils.isEmpty(this.K);
            if (isEmpty) {
                b = lc.a(this.K.length(), bArr3);
            }
            System.arraycopy(b, 0, bArr, i3, 2);
            i3 += 2;
            if (isEmpty) {
                b = this.K.getBytes("GBK");
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
            }
        } catch (Throwable th5) {
            i3 += 2;
        }
        bArr2 = new byte[]{(byte) 0, (byte) 0};
        try {
            System.arraycopy(lc.a(0, bArr3), 0, bArr, i3, 2);
            i3 += 2;
        } catch (Throwable th6) {
            i3 += 2;
        }
        try {
            System.arraycopy(new byte[]{(byte) 0, (byte) 0}, 0, bArr, i3, 2);
            i3 += 2;
        } catch (Throwable th7) {
            i3 += 2;
        }
        length2 = 0;
        if (this.I != null) {
            length2 = this.I.length;
        }
        b2 = lc.a(length2, null);
        System.arraycopy(b2, 0, bArr, i3, b2.length);
        i3 += b2.length;
        if (length2 > 0) {
            System.arraycopy(this.I, 0, bArr, i3, this.I.length);
            i3 += this.I.length;
        }
        b = new byte[i3];
        System.arraycopy(bArr, 0, b, 0, i3);
        crc32 = new CRC32();
        crc32.update(b);
        a3 = lc.a(crc32.getValue());
        b2 = new byte[(a3.length + i3)];
        System.arraycopy(b, 0, b2, 0, i3);
        System.arraycopy(a3, 0, b2, i3, a3.length);
        return b2;
    }
}
