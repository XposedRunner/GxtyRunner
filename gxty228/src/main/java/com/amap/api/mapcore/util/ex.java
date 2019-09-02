package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.location.AMapLocationClient;
import com.autonavi.amap.mapcore.Inner_3dMap_locationListener;
import com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;

/* compiled from: AMapLocationClient */
public class ex {
    Context a;
    Inner_3dMap_locationManagerBase b = null;
    Object c = null;
    boolean d = false;
    kg e = null;
    gk f = null;

    public ex(Context context) {
        try {
            this.f = kp.a();
        } catch (Throwable th) {
        }
        this.e = new kg();
        a(context);
    }

    private void a(android.content.Context r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.amap.api.mapcore.util.ex.a(android.content.Context):void. bs: [B:1:0x0002, B:8:0x0019]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r5 = this;
        if (r6 != 0) goto L_0x0013;
    L_0x0002:
        r0 = new java.lang.IllegalArgumentException;	 Catch:{ Throwable -> 0x000a }
        r1 = "Context参数不能为null";	 Catch:{ Throwable -> 0x000a }
        r0.<init>(r1);	 Catch:{ Throwable -> 0x000a }
        throw r0;	 Catch:{ Throwable -> 0x000a }
    L_0x000a:
        r0 = move-exception;
        r1 = "AMapLocationClient";
        r2 = "AMapLocationClient 1";
        com.amap.api.mapcore.util.kz.a(r0, r1, r2);
    L_0x0012:
        return;
    L_0x0013:
        r0 = r6.getApplicationContext();	 Catch:{ Throwable -> 0x000a }
        r5.a = r0;	 Catch:{ Throwable -> 0x000a }
        r0 = "com.amap.api.location.AMapLocationClient";	 Catch:{ Throwable -> 0x004a }
        r1 = java.lang.Class.forName(r0);	 Catch:{ Throwable -> 0x004a }
        r2 = new android.content.ComponentName;	 Catch:{ Throwable -> 0x004a }
        r0 = r5.a;	 Catch:{ Throwable -> 0x004a }
        r3 = "com.amap.api.location.APSService";	 Catch:{ Throwable -> 0x004a }
        r2.<init>(r0, r3);	 Catch:{ Throwable -> 0x004a }
        r0 = 0;
        r3 = r5.a;	 Catch:{ Throwable -> 0x0058 }
        r3 = r3.getPackageManager();	 Catch:{ Throwable -> 0x0058 }
        r4 = 128; // 0x80 float:1.794E-43 double:6.32E-322;	 Catch:{ Throwable -> 0x0058 }
        r0 = r3.getServiceInfo(r2, r4);	 Catch:{ Throwable -> 0x0058 }
    L_0x0035:
        if (r1 == 0) goto L_0x003c;
    L_0x0037:
        if (r0 == 0) goto L_0x003c;
    L_0x0039:
        r0 = 1;
        r5.d = r0;	 Catch:{ Throwable -> 0x004a }
    L_0x003c:
        r0 = r5.d;	 Catch:{ Throwable -> 0x000a }
        if (r0 == 0) goto L_0x004f;	 Catch:{ Throwable -> 0x000a }
    L_0x0040:
        r0 = new com.amap.api.location.AMapLocationClient;	 Catch:{ Throwable -> 0x000a }
        r1 = r5.a;	 Catch:{ Throwable -> 0x000a }
        r0.<init>(r1);	 Catch:{ Throwable -> 0x000a }
        r5.c = r0;	 Catch:{ Throwable -> 0x000a }
        goto L_0x0012;	 Catch:{ Throwable -> 0x000a }
    L_0x004a:
        r0 = move-exception;	 Catch:{ Throwable -> 0x000a }
        r0 = 0;	 Catch:{ Throwable -> 0x000a }
        r5.d = r0;	 Catch:{ Throwable -> 0x000a }
        goto L_0x003c;	 Catch:{ Throwable -> 0x000a }
    L_0x004f:
        r0 = r5.a;	 Catch:{ Throwable -> 0x000a }
        r0 = r5.b(r0);	 Catch:{ Throwable -> 0x000a }
        r5.b = r0;	 Catch:{ Throwable -> 0x000a }
        goto L_0x0012;
    L_0x0058:
        r2 = move-exception;
        goto L_0x0035;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ex.a(android.content.Context):void");
    }

    private Inner_3dMap_locationManagerBase b(Context context) {
        Inner_3dMap_locationManagerBase inner_3dMap_locationManagerBase;
        try {
            inner_3dMap_locationManagerBase = (Inner_3dMap_locationManagerBase) hn.a(context, this.f, gl.c("YY29tLmFtYXAuYXBpLndyYXBwZXIuSW5uZXJfM2RNYXBfbG9jYXRpb25NYW5hZ2VyV3JhcHBlcg=="), ki.class, new Class[]{Context.class}, new Object[]{context});
        } catch (Throwable th) {
            inner_3dMap_locationManagerBase = new ki(context);
        }
        return inner_3dMap_locationManagerBase == null ? new ki(context) : inner_3dMap_locationManagerBase;
    }

    public void a() {
        try {
            if (this.d) {
                ((AMapLocationClient) this.c).startLocation();
            } else {
                this.b.startLocation();
            }
        } catch (Throwable th) {
            kz.a(th, "AMapLocationClient", "startLocation");
        }
    }

    public void a(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        if (inner_3dMap_locationListener == null) {
            try {
                throw new IllegalArgumentException("listener参数不能为null");
            } catch (Throwable th) {
                kz.a(th, "AMapLocationClient", "setLocationListener");
            }
        } else if (this.d) {
            this.e.a(this.c, inner_3dMap_locationListener);
        } else {
            this.b.setLocationListener(inner_3dMap_locationListener);
        }
    }

    public void a(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        if (inner_3dMap_locationOption == null) {
            try {
                throw new IllegalArgumentException("LocationManagerOption参数不能为null");
            } catch (Throwable th) {
                kz.a(th, "AMapLocationClient", "setLocationOption");
            }
        } else if (this.d) {
            kg kgVar = this.e;
            kg.a(this.c, inner_3dMap_locationOption);
        } else {
            this.b.setLocationOption(inner_3dMap_locationOption);
        }
    }

    public void b() {
        try {
            if (this.d) {
                ((AMapLocationClient) this.c).stopLocation();
            } else {
                this.b.stopLocation();
            }
        } catch (Throwable th) {
            kz.a(th, "AMapLocationClient", "stopLocation");
        }
    }

    public void c() {
        try {
            if (this.d) {
                ((AMapLocationClient) this.c).onDestroy();
            } else {
                this.b.destroy();
            }
            if (this.e != null) {
                this.e = null;
            }
        } catch (Throwable th) {
            kz.a(th, "AMapLocationClient", "onDestroy");
        }
    }
}
