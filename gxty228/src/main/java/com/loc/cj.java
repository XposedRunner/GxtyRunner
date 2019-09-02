package com.loc;

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
public final class cj {
    public static String J = null;
    public static String L = null;
    public String A = null;
    public String B = null;
    public ArrayList<bv> C = new ArrayList();
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
        com.loc.cl.a(r0, r1, r2);
        r0 = "00:00:00:00:00:00";
        r0 = r7.a(r0);
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cj.a(java.lang.String):byte[]");
    }

    private String b(String str) {
        if (!this.A.contains(str + ">")) {
            return "0";
        }
        int indexOf = this.A.indexOf(str + ">");
        return this.A.substring((indexOf + str.length()) + 1, this.A.indexOf("</" + str));
    }

    public final void a(Context context, boolean z, boolean z2, bw bwVar, by byVar, ConnectivityManager connectivityManager, String str, String str2) {
        String str3;
        int i;
        String str4 = "0";
        String str5 = "0";
        String str6 = "0";
        String str7 = "0";
        String str8 = "0";
        String f = db.f(context);
        int f2 = ct.f();
        String str9 = "";
        String str10 = "";
        String str11 = "";
        this.K = str2;
        String str12 = "api_serverSDK_130905";
        String str13 = "S128DF1572465B890OE3F7A13167KLEI";
        if (z2) {
            str3 = str12;
            str12 = str13;
        } else {
            str3 = "UC_nlp_20131029";
            str12 = "BKZCHMBBSSUK7U8GLUKHBB56CCFF78U";
        }
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        int e = bwVar.e();
        int f3 = bwVar.f();
        TelephonyManager g = bwVar.g();
        ArrayList a = bwVar.a();
        ArrayList b = bwVar.b();
        ArrayList b2 = byVar.b();
        String str14 = f3 == 2 ? "1" : str4;
        if (g != null) {
            if (TextUtils.isEmpty(cl.d)) {
                try {
                    cl.d = df.u(context);
                } catch (Throwable th) {
                    cl.a(th, "Aps", "getApsReq part4");
                }
            }
            if (TextUtils.isEmpty(cl.d)) {
                cl.d = "888888888888888";
            }
            if (TextUtils.isEmpty(cl.e)) {
                try {
                    cl.e = g.getSubscriberId();
                } catch (SecurityException e2) {
                } catch (Throwable th2) {
                    cl.a(th2, "Aps", "getApsReq part2");
                }
            }
            if (TextUtils.isEmpty(cl.e)) {
                cl.e = "888888888888888";
            }
        }
        NetworkInfo networkInfo = null;
        try {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        } catch (Throwable th3) {
            cl.a(th3, "Aps", "getApsReq part");
        }
        WifiInfo g2 = byVar.g();
        boolean a2 = by.a(g2);
        if (ct.a(networkInfo) != -1) {
            str10 = ct.b(g);
            if (a2 && byVar.f()) {
                str9 = str10;
                str10 = "2";
            } else {
                str9 = str10;
                str10 = "1";
            }
        }
        if (a.isEmpty()) {
            str4 = str11;
        } else {
            StringBuilder stringBuilder3 = new StringBuilder();
            bv bvVar;
            switch (f3) {
                case 1:
                    bvVar = (bv) a.get(0);
                    stringBuilder3.delete(0, stringBuilder3.length());
                    stringBuilder3.append("<mcc>").append(bvVar.a).append("</mcc>");
                    stringBuilder3.append("<mnc>").append(bvVar.b).append("</mnc>");
                    stringBuilder3.append("<lac>").append(bvVar.c).append("</lac>");
                    stringBuilder3.append("<cellid>").append(bvVar.d);
                    stringBuilder3.append("</cellid>");
                    stringBuilder3.append("<signal>").append(bvVar.j);
                    stringBuilder3.append("</signal>");
                    str4 = stringBuilder3.toString();
                    for (i = 1; i < a.size(); i++) {
                        bvVar = (bv) a.get(i);
                        stringBuilder.append(bvVar.c).append(",");
                        stringBuilder.append(bvVar.d).append(",");
                        stringBuilder.append(bvVar.j);
                        if (i < a.size() - 1) {
                            stringBuilder.append("*");
                        }
                    }
                    str13 = str4;
                    break;
                case 2:
                    bvVar = (bv) a.get(0);
                    stringBuilder3.delete(0, stringBuilder3.length());
                    stringBuilder3.append("<mcc>").append(bvVar.a).append("</mcc>");
                    stringBuilder3.append("<sid>").append(bvVar.g).append("</sid>");
                    stringBuilder3.append("<nid>").append(bvVar.h).append("</nid>");
                    stringBuilder3.append("<bid>").append(bvVar.i).append("</bid>");
                    if (bvVar.f > 0 && bvVar.e > 0) {
                        stringBuilder3.append("<lon>").append(bvVar.f).append("</lon>");
                        stringBuilder3.append("<lat>").append(bvVar.e).append("</lat>");
                    }
                    stringBuilder3.append("<signal>").append(bvVar.j).append("</signal>");
                    str13 = stringBuilder3.toString();
                    break;
                default:
                    str13 = str11;
                    break;
            }
            stringBuilder3.delete(0, stringBuilder3.length());
            str4 = str13;
        }
        if ((e & 4) != 4 || b.isEmpty()) {
            this.C.clear();
        } else {
            this.C.clear();
            this.C.addAll(b);
        }
        if (byVar.f()) {
            if (a2) {
                stringBuilder2.append(byVar.g().getBSSID()).append(",");
                int rssi = byVar.g().getRssi();
                if (rssi < -128) {
                    rssi = 0;
                } else if (rssi > 127) {
                    rssi = 0;
                }
                stringBuilder2.append(rssi).append(",");
                str13 = g2.getSSID();
                i = 32;
                try {
                    i = g2.getSSID().getBytes("UTF-8").length;
                } catch (Exception e3) {
                }
                if (i >= 32) {
                    str13 = "unkwn";
                }
                stringBuilder2.append(str13.replace("*", "."));
            }
            if (!(b2 == null || this.F == null)) {
                this.F.clear();
                this.F.addAll(b2);
            }
        } else {
            byVar.c();
            if (this.F != null) {
                this.F.clear();
            }
        }
        if (z) {
            this.b = (short) 0;
        } else {
            this.b = (short) 2;
        }
        this.c = str3;
        this.d = str12;
        this.f = ct.d();
        this.g = "android" + ct.e();
        this.h = ct.b(context);
        this.i = str14;
        this.j = str5;
        this.k = "0";
        this.l = str6;
        this.m = str7;
        this.n = str8;
        this.o = f;
        this.p = cl.d;
        this.q = cl.e;
        this.s = String.valueOf(f2);
        this.t = ct.i(context);
        this.v = "4.2.0";
        this.w = str;
        this.u = "";
        this.x = str9;
        this.y = str10;
        this.z = e;
        this.A = str4;
        this.B = stringBuilder.toString();
        this.D = bwVar.k();
        this.G = by.k();
        this.E = stringBuilder2.toString();
        try {
            if (TextUtils.isEmpty(J)) {
                J = df.h(context);
            }
        } catch (Throwable th4) {
        }
        try {
            if (TextUtils.isEmpty(L)) {
                L = df.b(context);
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
        int i3 = this.z;
        Object obj2 = (i3 <= 0 || i3 > 15) ? null : 1;
        if (obj2 == null) {
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
        byte[] bArr3 = new byte[2];
        byte[] bArr4 = new byte[4];
        i3 = 4096;
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
        bArr[0] = ct.j(this.a);
        obj2 = ct.a(this.b, null);
        System.arraycopy(obj2, 0, bArr, 1, obj2.length);
        int length2 = obj2.length + 1;
        try {
            obj2 = this.c.getBytes("GBK");
            bArr[length2] = (byte) obj2.length;
            length2++;
            System.arraycopy(obj2, 0, bArr, length2, obj2.length);
            length2 += obj2.length;
        } catch (Throwable th) {
            cl.a(th, "Req", "buildV4Dot2");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            obj2 = this.d.getBytes("GBK");
            bArr[length2] = (byte) obj2.length;
            length2++;
            System.arraycopy(obj2, 0, bArr, length2, obj2.length);
            length2 += obj2.length;
        } catch (Throwable th2) {
            cl.a(th2, "Req", "buildV4Dot21");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            obj2 = this.o.getBytes("GBK");
            bArr[length2] = (byte) obj2.length;
            length2++;
            System.arraycopy(obj2, 0, bArr, length2, obj2.length);
            length2 += obj2.length;
        } catch (Throwable th22) {
            cl.a(th22, "Req", "buildV4Dot22");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            obj2 = this.e.getBytes("GBK");
            bArr[length2] = (byte) obj2.length;
            length2++;
            System.arraycopy(obj2, 0, bArr, length2, obj2.length);
            length2 += obj2.length;
        } catch (Throwable th222) {
            cl.a(th222, "Req", "buildV4Dot23");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            obj2 = this.f.getBytes("GBK");
            bArr[length2] = (byte) obj2.length;
            length2++;
            System.arraycopy(obj2, 0, bArr, length2, obj2.length);
            length2 += obj2.length;
        } catch (Throwable th2222) {
            cl.a(th2222, "Req", "buildV4Dot24");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            obj2 = this.g.getBytes("GBK");
            bArr[length2] = (byte) obj2.length;
            length2++;
            System.arraycopy(obj2, 0, bArr, length2, obj2.length);
            length2 += obj2.length;
        } catch (Throwable th22222) {
            cl.a(th22222, "Req", "buildV4Dot25");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            obj2 = this.u.getBytes("GBK");
            bArr[length2] = (byte) obj2.length;
            length2++;
            System.arraycopy(obj2, 0, bArr, length2, obj2.length);
            length2 += obj2.length;
        } catch (Throwable th222222) {
            cl.a(th222222, "Req", "buildV4Dot26");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            obj2 = this.h.getBytes("GBK");
            bArr[length2] = (byte) obj2.length;
            length2++;
            System.arraycopy(obj2, 0, bArr, length2, obj2.length);
            length2 += obj2.length;
        } catch (Throwable th2222222) {
            cl.a(th2222222, "Req", "buildV4Dot27");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            obj2 = this.p.getBytes("GBK");
            bArr[length2] = (byte) obj2.length;
            length2++;
            System.arraycopy(obj2, 0, bArr, length2, obj2.length);
            length2 += obj2.length;
        } catch (Throwable th22222222) {
            cl.a(th22222222, "Req", "buildV4Dot28");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            obj2 = this.q.getBytes("GBK");
            bArr[length2] = (byte) obj2.length;
            length2++;
            System.arraycopy(obj2, 0, bArr, length2, obj2.length);
            length2 += obj2.length;
        } catch (Throwable th222222222) {
            cl.a(th222222222, "Req", "buildV4Dot29");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            if (TextUtils.isEmpty(this.t)) {
                bArr[length2] = (byte) 0;
                length2++;
            } else {
                obj2 = a(this.t);
                bArr[length2] = (byte) obj2.length;
                length2++;
                System.arraycopy(obj2, 0, bArr, length2, obj2.length);
                length2 += obj2.length;
            }
        } catch (Throwable th2222222222) {
            cl.a(th2222222222, "Req", "buildV4Dot219");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            obj2 = this.v.getBytes("GBK");
            bArr[length2] = (byte) obj2.length;
            length2++;
            System.arraycopy(obj2, 0, bArr, length2, obj2.length);
            length2 += obj2.length;
        } catch (Throwable th22222222222) {
            cl.a(th22222222222, "Req", "buildV4Dot211");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            obj2 = this.w.getBytes("GBK");
            bArr[length2] = (byte) obj2.length;
            length2++;
            System.arraycopy(obj2, 0, bArr, length2, obj2.length);
            length2 += obj2.length;
        } catch (Throwable th222222222222) {
            cl.a(th222222222222, "Req", "buildV4Dot212");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            if (TextUtils.isEmpty(J)) {
                bArr[length2] = (byte) 0;
                length2++;
            } else {
                obj2 = J.getBytes("GBK");
                bArr[length2] = (byte) obj2.length;
                length2++;
                System.arraycopy(obj2, 0, bArr, length2, obj2.length);
                length2 += obj2.length;
            }
        } catch (Throwable th2222222222222) {
            cl.a(th2222222222222, "Req", "buildV4Dot213");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            if (TextUtils.isEmpty(L)) {
                bArr[length2] = (byte) 0;
                length2++;
            } else {
                obj2 = L.getBytes("GBK");
                bArr[length2] = (byte) obj2.length;
                length2++;
                System.arraycopy(obj2, 0, bArr, length2, obj2.length);
                length2 += obj2.length;
            }
        } catch (Throwable th22222222222222) {
            cl.a(th22222222222222, "Req", "buildV4Dot214");
            bArr[length2] = (byte) 0;
            length2++;
        }
        try {
            obj2 = this.x.getBytes("GBK");
            bArr[length2] = (byte) obj2.length;
            length2++;
            System.arraycopy(obj2, 0, bArr, length2, obj2.length);
            i3 = obj2.length + length2;
        } catch (Throwable th222222222222222) {
            cl.a(th222222222222222, "Req", "buildV4Dot213");
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
            b = ct.b(b("mcc"));
            System.arraycopy(b, 0, bArr, i3, b.length);
            i3 += b.length;
            if (i4 == 1) {
                b = ct.b(b("mnc"));
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
                b = ct.b(b("lac"));
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
                b = ct.c(b("cellid"));
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
            } else if (i4 == 2) {
                b = ct.b(b("sid"));
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
                b = ct.b(b("nid"));
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
                b = ct.b(b("bid"));
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
                b = ct.c(b("lon"));
                System.arraycopy(b, 0, bArr, i3, b.length);
                i3 += b.length;
                b = ct.c(b("lat"));
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
            b = ct.a(0, bArr3);
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
                        b2 = ct.b(a("lac", length2));
                        System.arraycopy(b2, 0, bArr, i3, b2.length);
                        i3 += b2.length;
                        b2 = ct.c(a("cellid", length2));
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
                if (!((bv) arrayList.get(0)).o) {
                    i4--;
                }
                bArr[length2] = (byte) i4;
                length2++;
                for (i = 0; i < i4; i++) {
                    bv bvVar = (bv) arrayList.get(i);
                    if (bvVar.o) {
                        byte b3;
                        if (bvVar.k == 1 || bvVar.k == 3 || bvVar.k == 4) {
                            b3 = (byte) bvVar.k;
                            if (bvVar.n) {
                                b3 = (byte) (b3 | 8);
                            }
                            bArr[length2] = b3;
                            length2++;
                            a = ct.a(bvVar.a, bArr3);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = ct.a(bvVar.b, bArr3);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = ct.a(bvVar.c, bArr3);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = ct.b(bvVar.d, bArr4);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                        } else if (bvVar.k == 2) {
                            b3 = (byte) bvVar.k;
                            if (bvVar.n) {
                                b3 = (byte) (b3 | 8);
                            }
                            bArr[length2] = b3;
                            length2++;
                            a = ct.a(bvVar.a, bArr3);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = ct.a(bvVar.g, bArr3);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = ct.a(bvVar.h, bArr3);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = ct.a(bvVar.i, bArr3);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = ct.b(bvVar.f, bArr4);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                            a = ct.b(bvVar.e, bArr4);
                            System.arraycopy(a, 0, bArr, length2, a.length);
                            length2 += a.length;
                        }
                        length = bvVar.j;
                        if (length > 127) {
                            length = 99;
                        } else if (length < -128) {
                            length = 99;
                        }
                        bArr[length2] = (byte) length;
                        length2++;
                        obj2 = ct.a(bvVar.l, bArr3);
                        System.arraycopy(obj2, 0, bArr, length2, obj2.length);
                        length2 += obj2.length;
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
                    obj2 = a(split[0]);
                    System.arraycopy(obj2, 0, bArr, length2, obj2.length);
                    length2 += obj2.length;
                    obj2 = split[2].getBytes("GBK");
                    bArr[length2] = (byte) obj2.length;
                    length2++;
                    System.arraycopy(obj2, 0, bArr, length2, obj2.length);
                    length2 += obj2.length;
                } catch (Throwable th2222222222222222) {
                    cl.a(th2222222222222222, "Req", "buildV4Dot216");
                    obj2 = a("00:00:00:00:00:00");
                    System.arraycopy(obj2, 0, bArr, length2, obj2.length);
                    i3 = obj2.length + length2;
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
                obj = ct.c() < 17 ? 1 : null;
                j = 0;
                if (obj != null) {
                    j = ct.b() / 1000;
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
                            a = ct.a(length, bArr3);
                            System.arraycopy(a, 0, bArr, i, a.length);
                            length = a.length + i;
                            obj2 = ct.a(scanResult.frequency, bArr3);
                            System.arraycopy(obj2, 0, bArr, length, obj2.length);
                            length += obj2.length;
                        }
                    }
                    length = 0;
                    a = ct.a(length, bArr3);
                    System.arraycopy(a, 0, bArr, i, a.length);
                    length = a.length + i;
                    obj2 = ct.a(scanResult.frequency, bArr3);
                    System.arraycopy(obj2, 0, bArr, length, obj2.length);
                    length += obj2.length;
                }
                obj2 = ct.a(Integer.parseInt(this.G), bArr3);
                System.arraycopy(obj2, 0, bArr, length, obj2.length);
                i3 = obj2.length + length;
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
                b = ct.a(this.K.length(), bArr3);
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
            System.arraycopy(ct.a(0, bArr3), 0, bArr, i3, 2);
            i3 += 2;
            System.arraycopy(new byte[]{(byte) 0, (byte) 0}, 0, bArr, i3, 2);
            i3 += 2;
            length2 = 0;
            if (this.I != null) {
                length2 = this.I.length;
            }
            b2 = ct.a(length2, null);
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
            a3 = ct.a(crc32.getValue());
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
            obj2 = a(split[0]);
            System.arraycopy(obj2, 0, bArr, length2, obj2.length);
            length2 += obj2.length;
            obj2 = split[2].getBytes("GBK");
            bArr[length2] = (byte) obj2.length;
            length2++;
            System.arraycopy(obj2, 0, bArr, length2, obj2.length);
            length2 += obj2.length;
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
            if (ct.c() < 17) {
            }
            j = 0;
            if (obj != null) {
                j = ct.b() / 1000;
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
                        a = ct.a(length, bArr3);
                        System.arraycopy(a, 0, bArr, i, a.length);
                        length = a.length + i;
                        obj2 = ct.a(scanResult.frequency, bArr3);
                        System.arraycopy(obj2, 0, bArr, length, obj2.length);
                        length += obj2.length;
                    }
                }
                length = 0;
                a = ct.a(length, bArr3);
                System.arraycopy(a, 0, bArr, i, a.length);
                length = a.length + i;
                obj2 = ct.a(scanResult.frequency, bArr3);
                System.arraycopy(obj2, 0, bArr, length, obj2.length);
                length += obj2.length;
            }
            obj2 = ct.a(Integer.parseInt(this.G), bArr3);
            System.arraycopy(obj2, 0, bArr, length, obj2.length);
            i3 = obj2.length + length;
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
                b = ct.a(this.K.length(), bArr3);
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
            System.arraycopy(ct.a(0, bArr3), 0, bArr, i3, 2);
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
        b2 = ct.a(length2, null);
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
        a3 = ct.a(crc32.getValue());
        b2 = new byte[(a3.length + i3)];
        System.arraycopy(b, 0, b2, 0, i3);
        System.arraycopy(a3, 0, b2, i3, a3.length);
        return b2;
    }
}
