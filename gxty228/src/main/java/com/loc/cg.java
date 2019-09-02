package com.loc;

import android.content.Context;
import com.lzy.okgo.model.HttpHeaders;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: LocNetManager */
public final class cg {
    private static cg b = null;
    an a;
    private int c;
    private int d;
    private boolean e;
    private int f;

    private cg() {
        this.a = null;
        this.c = 0;
        this.d = cl.f;
        this.e = false;
        this.f = 0;
        this.a = an.a();
    }

    public static cg a() {
        if (b == null) {
            b = new cg();
        }
        return b;
    }

    public final as a(ch chVar) throws Throwable {
        long b = ct.b();
        an anVar = this.a;
        as a = an.a(chVar, this.e);
        this.c = Long.valueOf(ct.b() - b).intValue();
        return a;
    }

    public final ch a(Context context, byte[] bArr, String str, boolean z) {
        ch chVar;
        try {
            Map hashMap = new HashMap(16);
            chVar = new ch(context, cl.b());
            hashMap.put(HttpHeaders.HEAD_KEY_CONTENT_TYPE, "application/octet-stream");
            hashMap.put(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "gzip");
            hashMap.put("gzipped", "1");
            hashMap.put(HttpHeaders.HEAD_KEY_CONNECTION, "Keep-Alive");
            hashMap.put(HttpHeaders.HEAD_KEY_USER_AGENT, "AMAP_Location_SDK_Android 4.2.0");
            hashMap.put("KEY", db.f(context));
            hashMap.put("enginever", "4.9");
            String a = dd.a();
            String a2 = dd.a(context, a, "key=" + db.f(context));
            hashMap.put("ts", a);
            hashMap.put("scode", a2);
            hashMap.put("encr", "1");
            chVar.f = hashMap;
            a = "loc";
            if (!z) {
                a = "locf";
            }
            chVar.m = true;
            chVar.k = String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", new Object[]{"4.2.0", a, Integer.valueOf(3)});
            chVar.j = z;
            chVar.g = str;
            chVar.h = ct.a(bArr);
            chVar.a(di.a(context));
            Map hashMap2 = new HashMap(16);
            hashMap2.put("output", "bin");
            hashMap2.put("policy", "3103");
            switch (this.f) {
                case 0:
                    hashMap2.remove("custom");
                    break;
                case 1:
                    hashMap2.put("custom", "language:cn");
                    break;
                case 2:
                    hashMap2.put("custom", "language:en");
                    break;
                default:
                    hashMap2.remove("custom");
                    break;
            }
            chVar.l = hashMap2;
            chVar.a(this.d);
            chVar.b(this.d);
            if (!this.e) {
                return chVar;
            }
            chVar.g = chVar.c().replace("http", "https");
            return chVar;
        } catch (Throwable th) {
            return chVar;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String a(android.content.Context r11, double r12, double r14) {
        /*
        r10 = this;
        r1 = 0;
        r0 = new java.util.HashMap;	 Catch:{ Throwable -> 0x00d5 }
        r2 = 16;
        r0.<init>(r2);	 Catch:{ Throwable -> 0x00d5 }
        r2 = new com.loc.ch;	 Catch:{ Throwable -> 0x00d5 }
        r3 = com.loc.cl.b();	 Catch:{ Throwable -> 0x00d5 }
        r2.<init>(r11, r3);	 Catch:{ Throwable -> 0x00d5 }
        r0.clear();	 Catch:{ Throwable -> 0x00d5 }
        r3 = "Content-Type";
        r4 = "application/x-www-form-urlencoded";
        r0.put(r3, r4);	 Catch:{ Throwable -> 0x00d5 }
        r3 = "Connection";
        r4 = "Keep-Alive";
        r0.put(r3, r4);	 Catch:{ Throwable -> 0x00d5 }
        r3 = "User-Agent";
        r4 = "AMAP_Location_SDK_Android 4.2.0";
        r0.put(r3, r4);	 Catch:{ Throwable -> 0x00d5 }
        r3 = new java.util.HashMap;	 Catch:{ Throwable -> 0x00d5 }
        r4 = 16;
        r3.<init>(r4);	 Catch:{ Throwable -> 0x00d5 }
        r4 = "custom";
        r5 = "26260A1F00020002";
        r3.put(r4, r5);	 Catch:{ Throwable -> 0x00d5 }
        r4 = "key";
        r5 = com.loc.db.f(r11);	 Catch:{ Throwable -> 0x00d5 }
        r3.put(r4, r5);	 Catch:{ Throwable -> 0x00d5 }
        r4 = r10.f;	 Catch:{ Throwable -> 0x00d5 }
        switch(r4) {
            case 0: goto L_0x00ce;
            case 1: goto L_0x00d8;
            case 2: goto L_0x00e1;
            default: goto L_0x0045;
        };	 Catch:{ Throwable -> 0x00d5 }
    L_0x0045:
        r4 = "language";
        r3.remove(r4);	 Catch:{ Throwable -> 0x00d5 }
    L_0x004a:
        r4 = com.loc.dd.a();	 Catch:{ Throwable -> 0x00d5 }
        r5 = com.loc.dl.b(r3);	 Catch:{ Throwable -> 0x00d5 }
        r5 = com.loc.dd.a(r11, r4, r5);	 Catch:{ Throwable -> 0x00d5 }
        r6 = "ts";
        r3.put(r6, r4);	 Catch:{ Throwable -> 0x00d5 }
        r4 = "scode";
        r3.put(r4, r5);	 Catch:{ Throwable -> 0x00d5 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d5 }
        r5 = "output=json&radius=1000&extensions=all&location=";
        r4.<init>(r5);	 Catch:{ Throwable -> 0x00d5 }
        r4 = r4.append(r14);	 Catch:{ Throwable -> 0x00d5 }
        r5 = ",";
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x00d5 }
        r4 = r4.append(r12);	 Catch:{ Throwable -> 0x00d5 }
        r4 = r4.toString();	 Catch:{ Throwable -> 0x00d5 }
        r5 = "UTF-8";
        r4 = r4.getBytes(r5);	 Catch:{ Throwable -> 0x00d5 }
        r2.b(r4);	 Catch:{ Throwable -> 0x00d5 }
        r4 = 0;
        r2.m = r4;	 Catch:{ Throwable -> 0x00d5 }
        r4 = 1;
        r2.j = r4;	 Catch:{ Throwable -> 0x00d5 }
        r4 = java.util.Locale.US;	 Catch:{ Throwable -> 0x00d5 }
        r5 = "platform=Android&sdkversion=%s&product=%s&loc_channel=%s";
        r6 = 3;
        r6 = new java.lang.Object[r6];	 Catch:{ Throwable -> 0x00d5 }
        r7 = 0;
        r8 = "4.2.0";
        r6[r7] = r8;	 Catch:{ Throwable -> 0x00d5 }
        r7 = 1;
        r8 = "loc";
        r6[r7] = r8;	 Catch:{ Throwable -> 0x00d5 }
        r7 = 2;
        r8 = 3;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x00d5 }
        r6[r7] = r8;	 Catch:{ Throwable -> 0x00d5 }
        r4 = java.lang.String.format(r4, r5, r6);	 Catch:{ Throwable -> 0x00d5 }
        r2.k = r4;	 Catch:{ Throwable -> 0x00d5 }
        r2.l = r3;	 Catch:{ Throwable -> 0x00d5 }
        r2.f = r0;	 Catch:{ Throwable -> 0x00d5 }
        r0 = "http://restapi.amap.com/v3/geocode/regeo";
        r2.g = r0;	 Catch:{ Throwable -> 0x00d5 }
        r0 = com.loc.di.a(r11);	 Catch:{ Throwable -> 0x00d5 }
        r2.a(r0);	 Catch:{ Throwable -> 0x00d5 }
        r0 = com.loc.cl.f;	 Catch:{ Throwable -> 0x00d5 }
        r2.a(r0);	 Catch:{ Throwable -> 0x00d5 }
        r0 = com.loc.cl.f;	 Catch:{ Throwable -> 0x00d5 }
        r2.b(r0);	 Catch:{ Throwable -> 0x00d5 }
        r0 = r10.a;	 Catch:{ Throwable -> 0x00ea }
        r2 = com.loc.an.b(r2);	 Catch:{ Throwable -> 0x00ea }
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x00ea }
        r3 = "utf-8";
        r0.<init>(r2, r3);	 Catch:{ Throwable -> 0x00ea }
    L_0x00cd:
        return r0;
    L_0x00ce:
        r4 = "language";
        r3.remove(r4);	 Catch:{ Throwable -> 0x00d5 }
        goto L_0x004a;
    L_0x00d5:
        r0 = move-exception;
        r0 = r1;
        goto L_0x00cd;
    L_0x00d8:
        r4 = "language";
        r5 = "zh-CN";
        r3.put(r4, r5);	 Catch:{ Throwable -> 0x00d5 }
        goto L_0x004a;
    L_0x00e1:
        r4 = "language";
        r5 = "en";
        r3.put(r4, r5);	 Catch:{ Throwable -> 0x00d5 }
        goto L_0x004a;
    L_0x00ea:
        r0 = move-exception;
        r2 = "LocNetManager";
        r3 = "post";
        com.loc.cl.a(r0, r2, r3);	 Catch:{ Throwable -> 0x00d5 }
        r0 = r1;
        goto L_0x00cd;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cg.a(android.content.Context, double, double):java.lang.String");
    }

    public final void a(long j, boolean z, int i) {
        try {
            this.e = z;
            this.d = Long.valueOf(j).intValue();
            this.f = i;
        } catch (Throwable th) {
            cl.a(th, "netmanager", "setOption");
        }
    }

    public final int b() {
        return this.c;
    }
}
