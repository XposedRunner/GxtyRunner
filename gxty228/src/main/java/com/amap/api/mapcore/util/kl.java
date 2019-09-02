package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: MapLocationService */
public final class kl {
    private static int m = 200;
    private static boolean n = true;
    Context a = null;
    kf b = null;
    km c = null;
    b d = null;
    Handler e = null;
    Handler f = null;
    boolean g = false;
    boolean h = false;
    Inner_3dMap_locationOption i = null;
    final int j = 500;
    final int k = 30;
    Object l = new Object();
    private JSONArray o = null;

    /* compiled from: MapLocationService */
    public class a extends Handler {
        final /* synthetic */ kl a;

        public a(kl klVar, Looper looper) {
            this.a = klVar;
            super(looper);
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.b();
                    return;
                default:
                    return;
            }
        }
    }

    /* compiled from: MapLocationService */
    static class b extends HandlerThread {
        public b(String str) {
            super(str);
        }

        protected final void onLooperPrepared() {
            super.onLooperPrepared();
        }

        public final void run() {
            try {
                super.run();
            } catch (Throwable th) {
            }
        }
    }

    public kl(Context context, Handler handler) {
        if (context == null) {
            try {
                throw new IllegalArgumentException("Context参数不能为null");
            } catch (Throwable th) {
                kz.a(th, "LocationService", "<init>");
                return;
            }
        }
        this.a = context.getApplicationContext();
        this.f = handler;
        this.i = new Inner_3dMap_locationOption();
        f();
        e();
    }

    private void a(Inner_3dMap_location inner_3dMap_location) {
        try {
            if (n && inner_3dMap_location != null && inner_3dMap_location.getErrorCode() == 0 && inner_3dMap_location.getLocationType() == 1) {
                if (this.o == null) {
                    this.o = new JSONArray();
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("lon", inner_3dMap_location.getLongitude());
                jSONObject.put("lat", inner_3dMap_location.getLatitude());
                jSONObject.put("type", 0);
                jSONObject.put("timestamp", lc.a());
                this.o = this.o.put(jSONObject);
                if (this.o.length() >= m) {
                    h();
                }
            }
        } catch (Throwable th) {
            kz.a(th, "LocationService", "recordOfflineLocLog");
        }
    }

    private void e() {
        this.d = new b("locServiceAction");
        this.d.setPriority(5);
        this.d.start();
        this.e = new a(this, this.d.getLooper());
    }

    private void f() {
        try {
            if (this.i == null) {
                this.i = new Inner_3dMap_locationOption();
            }
            if (!this.h) {
                this.b = new kf(this.a);
                this.c = new km(this.a);
                this.c.a(this.i);
                g();
                this.h = true;
            }
        } catch (Throwable th) {
            kz.a(th, "LocationService", "init");
        }
    }

    private void g() {
        try {
            n = lb.b(this.a, "maploc", "ue");
            int a = lb.a(this.a, "maploc", "opn");
            m = a;
            if (a > 500) {
                m = 500;
            }
            if (m < 30) {
                m = 30;
            }
        } catch (Throwable th) {
            kz.a(th, "LocationService", "getSPConfig");
        }
    }

    private synchronized void h() {
        try {
            if (this.o != null && this.o.length() > 0) {
                ji.a(new jh(this.a, kz.b(), this.o.toString()), this.a);
                this.o = null;
            }
        } catch (Throwable th) {
            kz.a(th, "LocationService", "writeOfflineLog");
        }
    }

    private void i() {
        synchronized (this.l) {
            if (this.e != null) {
                this.e.removeCallbacksAndMessages(null);
            }
            this.e = null;
        }
    }

    private void j() {
        synchronized (this.l) {
            if (this.e != null) {
                this.e.removeMessages(1);
            }
        }
    }

    public final void a() {
        try {
            f();
            if (!(this.i.getLocationMode().equals(Inner_3dMap_Enum_LocationMode.Battery_Saving) || this.g)) {
                this.g = true;
                this.b.a();
            }
            if (this.e != null) {
                this.e.sendEmptyMessage(1);
            }
        } catch (Throwable th) {
            kz.a(th, "LocationService", "getLocation");
        }
    }

    public final void a(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        this.i = inner_3dMap_locationOption;
        if (this.i == null) {
            this.i = new Inner_3dMap_locationOption();
        }
        if (this.c != null) {
            this.c.a(inner_3dMap_locationOption);
        }
    }

    final void b() {
        Inner_3dMap_location inner_3dMap_location = null;
        try {
            if (this.i.getLocationMode().equals(Inner_3dMap_Enum_LocationMode.Battery_Saving) && this.g) {
                this.b.b();
                this.g = false;
            }
            if (this.b.c()) {
                inner_3dMap_location = this.b.d();
            } else if (!this.i.getLocationMode().equals(Inner_3dMap_Enum_LocationMode.Device_Sensors)) {
                inner_3dMap_location = this.c.a();
            }
            if (!(this.f == null || inner_3dMap_location == null)) {
                Message obtain = Message.obtain();
                obtain.obj = inner_3dMap_location;
                obtain.what = 1;
                this.f.sendMessage(obtain);
            }
            a(inner_3dMap_location);
        } catch (Throwable th) {
            kz.a(th, "LocationService", "doGetLocation");
        }
    }

    public final void c() {
        this.g = false;
        try {
            j();
            if (this.b != null) {
                this.b.b();
            }
        } catch (Throwable th) {
            kz.a(th, "LocationService", "stopLocation");
        }
    }

    public final void d() {
        try {
            c();
            i();
            if (this.d != null) {
                if (VERSION.SDK_INT >= 18) {
                    la.a(this.d, HandlerThread.class, "quitSafely", new Object[0]);
                } else {
                    this.d.quit();
                }
            }
        } catch (Throwable th) {
            kz.a(th, "LocationService", "destroy");
            return;
        }
        this.d = null;
        this.c.b();
        this.g = false;
        this.h = false;
        h();
    }
}
