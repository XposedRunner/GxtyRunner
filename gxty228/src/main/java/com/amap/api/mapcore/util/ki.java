package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.autonavi.amap.mapcore.Inner_3dMap_locationListener;
import com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: MapLocationManager */
public class ki implements Inner_3dMap_locationManagerBase {
    Context a = null;
    ArrayList<Inner_3dMap_locationListener> b = new ArrayList();
    Object c = new Object();
    Handler d = null;
    a e = null;
    Handler f = null;
    Inner_3dMap_locationOption g = new Inner_3dMap_locationOption();
    kl h = null;
    Inner_3dMap_Enum_LocationMode i = Inner_3dMap_Enum_LocationMode.Hight_Accuracy;
    boolean j = false;

    /* compiled from: MapLocationManager */
    static class a extends HandlerThread {
        ki a;

        public a(String str, ki kiVar) {
            super(str);
            this.a = kiVar;
        }

        protected final void onLooperPrepared() {
            try {
                this.a.h = new kl(this.a.a, this.a.d);
                super.onLooperPrepared();
            } catch (Throwable th) {
            }
        }

        public final void run() {
            try {
                super.run();
            } catch (Throwable th) {
            }
        }
    }

    public ki(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context参数不能为null");
        }
        this.a = context.getApplicationContext();
        e();
    }

    private Handler a(Looper looper) {
        Handler handler;
        synchronized (this.c) {
            this.f = new kj(looper, this);
            handler = this.f;
        }
        return handler;
    }

    private void a(int i) {
        synchronized (this.c) {
            if (this.f != null) {
                this.f.removeMessages(i);
            }
        }
    }

    private void a(int i, Object obj, long j) {
        synchronized (this.c) {
            if (this.f != null) {
                Message obtain = Message.obtain();
                obtain.what = i;
                obtain.obj = obj;
                this.f.sendMessageDelayed(obtain, j);
            }
        }
    }

    private void e() {
        try {
            if (Looper.myLooper() == null) {
                this.d = new kk(this.a.getMainLooper(), this);
            } else {
                this.d = new kk(this);
            }
        } catch (Throwable th) {
            kz.a(th, "MapLocationManager", "initResultHandler");
        }
        try {
            this.e = new a("locaitonClientActionThread", this);
            this.e.setPriority(5);
            this.e.start();
            this.f = a(this.e.getLooper());
        } catch (Throwable th2) {
            kz.a(th2, "MapLocationManager", "initActionThreadAndActionHandler");
        }
    }

    private void f() {
        synchronized (this.c) {
            if (this.f != null) {
                this.f.removeCallbacksAndMessages(null);
            }
            this.f = null;
        }
    }

    final void a() {
        try {
            if (!this.j) {
                this.j = true;
                a(1005, null, 0);
            }
        } catch (Throwable th) {
            kz.a(th, "MapLocationManager", "doStartLocation");
        }
    }

    final void a(Inner_3dMap_location inner_3dMap_location) {
        try {
            if (this.j) {
                if (!"GPS".equalsIgnoreCase(inner_3dMap_location.getProvider())) {
                    inner_3dMap_location.setProvider("lbs");
                }
                inner_3dMap_location.setAltitude(lc.b(inner_3dMap_location.getAltitude()));
                inner_3dMap_location.setBearing(lc.a(inner_3dMap_location.getBearing()));
                inner_3dMap_location.setSpeed(lc.a(inner_3dMap_location.getSpeed()));
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    try {
                        ((Inner_3dMap_locationListener) it.next()).onLocationChanged(inner_3dMap_location);
                    } catch (Throwable th) {
                    }
                }
            }
            if (this.g.isOnceLocation()) {
                c();
            }
        } catch (Throwable th2) {
            kz.a(th2, "MapLocationManager", "callBackLocation");
        }
    }

    final void a(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        if (inner_3dMap_locationListener == null) {
            try {
                throw new IllegalArgumentException("listener参数不能为null");
            } catch (Throwable th) {
                kz.a(th, "MapLocationManager", "doSetLocationListener");
                return;
            }
        }
        if (this.b == null) {
            this.b = new ArrayList();
        }
        if (!this.b.contains(inner_3dMap_locationListener)) {
            this.b.add(inner_3dMap_locationListener);
        }
    }

    final void a(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        this.g = inner_3dMap_locationOption;
        if (this.g == null) {
            this.g = new Inner_3dMap_locationOption();
        }
        if (this.h != null) {
            this.h.a(this.g);
        }
        if (this.j && !this.i.equals(inner_3dMap_locationOption.getLocationMode())) {
            c();
            a();
        }
        this.i = this.g.getLocationMode();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void b() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0023 in list [B:7:0x0016]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r8 = this;
        r7 = 0;
        r6 = 1005; // 0x3ed float:1.408E-42 double:4.965E-321;
        r0 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r8.h;	 Catch:{ Throwable -> 0x002b, all -> 0x0050 }
        if (r2 == 0) goto L_0x000e;	 Catch:{ Throwable -> 0x002b, all -> 0x0050 }
    L_0x0009:
        r2 = r8.h;	 Catch:{ Throwable -> 0x002b, all -> 0x0050 }
        r2.a();	 Catch:{ Throwable -> 0x002b, all -> 0x0050 }
    L_0x000e:
        r2 = r8.g;
        r2 = r2.isOnceLocation();
        if (r2 != 0) goto L_0x0023;
    L_0x0016:
        r2 = r8.g;
        r2 = r2.getInterval();
        r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1));
        if (r2 >= 0) goto L_0x0024;
    L_0x0020:
        r8.a(r6, r7, r0);
    L_0x0023:
        return;
    L_0x0024:
        r0 = r8.g;
        r0 = r0.getInterval();
        goto L_0x0020;
    L_0x002b:
        r2 = move-exception;
        r3 = "MapLocationManager";	 Catch:{ Throwable -> 0x002b, all -> 0x0050 }
        r4 = "doGetLocation";	 Catch:{ Throwable -> 0x002b, all -> 0x0050 }
        com.amap.api.mapcore.util.kz.a(r2, r3, r4);	 Catch:{ Throwable -> 0x002b, all -> 0x0050 }
        r2 = r8.g;
        r2 = r2.isOnceLocation();
        if (r2 != 0) goto L_0x0023;
    L_0x003b:
        r2 = r8.g;
        r2 = r2.getInterval();
        r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1));
        if (r2 >= 0) goto L_0x0049;
    L_0x0045:
        r8.a(r6, r7, r0);
        goto L_0x0023;
    L_0x0049:
        r0 = r8.g;
        r0 = r0.getInterval();
        goto L_0x0045;
    L_0x0050:
        r2 = move-exception;
        r3 = r8.g;
        r3 = r3.isOnceLocation();
        if (r3 != 0) goto L_0x0066;
    L_0x0059:
        r3 = r8.g;
        r4 = r3.getInterval();
        r3 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r3 >= 0) goto L_0x0067;
    L_0x0063:
        r8.a(r6, r7, r0);
    L_0x0066:
        throw r2;
    L_0x0067:
        r0 = r8.g;
        r0 = r0.getInterval();
        goto L_0x0063;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ki.b():void");
    }

    final void b(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        if (inner_3dMap_locationListener != null) {
            try {
                if (!this.b.isEmpty() && this.b.contains(inner_3dMap_locationListener)) {
                    this.b.remove(inner_3dMap_locationListener);
                }
            } catch (Throwable th) {
                kz.a(th, "MapLocationManager", "doUnregisterListener");
                return;
            }
        }
        if (this.b.isEmpty()) {
            c();
        }
    }

    final void c() {
        try {
            this.j = false;
            a(1004);
            a(1005);
            if (this.h != null) {
                this.h.c();
            }
        } catch (Throwable th) {
            kz.a(th, "MapLocationManager", "doStopLocation");
        }
    }

    final void d() {
        c();
        if (this.h != null) {
            this.h.d();
        }
        if (this.b != null) {
            this.b.clear();
            this.b = null;
        }
        f();
        if (this.e != null) {
            if (VERSION.SDK_INT >= 18) {
                try {
                    la.a(this.e, HandlerThread.class, "quitSafely", new Object[0]);
                } catch (Throwable th) {
                    this.e.quit();
                }
            } else {
                this.e.quit();
            }
        }
        this.e = null;
        if (this.d != null) {
            this.d.removeCallbacksAndMessages(null);
            this.d = null;
        }
    }

    public void destroy() {
        try {
            a(PointerIconCompat.TYPE_CROSSHAIR, null, 0);
        } catch (Throwable th) {
            kz.a(th, "MapLocationManager", "stopLocation");
        }
    }

    public void setLocationListener(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        try {
            a(1002, inner_3dMap_locationListener, 0);
        } catch (Throwable th) {
            kz.a(th, "MapLocationManager", "setLocationListener");
        }
    }

    public void setLocationOption(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        try {
            a(1001, inner_3dMap_locationOption, 0);
        } catch (Throwable th) {
            kz.a(th, "LocationClientManager", "setLocationOption");
        }
    }

    public void startLocation() {
        try {
            a(1004, null, 0);
        } catch (Throwable th) {
            kz.a(th, "MapLocationManager", "startLocation");
        }
    }

    public void stopLocation() {
        try {
            a(PointerIconCompat.TYPE_CELL, null, 0);
        } catch (Throwable th) {
            kz.a(th, "MapLocationManager", "stopLocation");
        }
    }

    public void unRegisterLocationListener(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        try {
            a(PointerIconCompat.TYPE_HELP, inner_3dMap_locationListener, 0);
        } catch (Throwable th) {
            kz.a(th, "MapLocationManager", "stopLocation");
        }
    }
}
