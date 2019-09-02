package com.amap.api.mapcore.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;

/* compiled from: MapGpsLocation */
public final class kf {
    Context a;
    LocationManager b;
    volatile long c = 0;
    volatile boolean d = false;
    boolean e = false;
    volatile Inner_3dMap_location f = null;
    Object g = null;
    boolean h = false;
    boolean i = false;
    LocationListener j = new LocationListener(this) {
        final /* synthetic */ kf a;

        {
            this.a = r1;
        }

        public final void onLocationChanged(Location location) {
            if (location != null) {
                try {
                    Inner_3dMap_location inner_3dMap_location = new Inner_3dMap_location(location);
                    inner_3dMap_location.setLocationType(1);
                    Bundle extras = location.getExtras();
                    int i = 0;
                    if (extras != null) {
                        i = extras.getInt("satellites");
                    }
                    inner_3dMap_location.setSatellites(i);
                    this.a.f = inner_3dMap_location;
                    this.a.c = lc.b();
                    this.a.d = true;
                } catch (Throwable th) {
                    kz.a(th, "MAPGPSLocation", "onLocationChanged");
                }
            }
        }

        public final void onProviderDisabled(String str) {
            try {
                if ("gps".equals(str)) {
                    this.a.d = false;
                }
            } catch (Throwable th) {
                kz.a(th, "MAPGPSLocation", "onProviderDisabled");
            }
        }

        public final void onProviderEnabled(String str) {
        }

        public final void onStatusChanged(String str, int i, Bundle bundle) {
        }
    };

    public kf(Context context) {
        if (context != null) {
            this.a = context;
            e();
            try {
                if (this.g == null && !this.i) {
                    if (this.h) {
                        this.g = Class.forName("com.amap.api.maps.CoordinateConverter").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
                    } else {
                        this.g = Class.forName("com.amap.api.maps2d.CoordinateConverter").getConstructor(new Class[0]).newInstance(new Object[0]);
                    }
                    if (context == null) {
                        this.i = true;
                    }
                }
            } catch (Throwable th) {
            }
            if (this.b == null) {
                this.b = (LocationManager) this.a.getSystemService("location");
            }
        }
    }

    private void e() {
        try {
            if (Class.forName("com.amap.api.maps.CoordinateConverter") != null) {
                this.h = true;
            }
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void f() {
        /*
        r7 = this;
        r6 = android.os.Looper.myLooper();	 Catch:{ SecurityException -> 0x0030, Throwable -> 0x0027 }
        if (r6 != 0) goto L_0x000c;
    L_0x0006:
        r0 = r7.a;	 Catch:{ SecurityException -> 0x0030, Throwable -> 0x0027 }
        r6 = r0.getMainLooper();	 Catch:{ SecurityException -> 0x0030, Throwable -> 0x0027 }
    L_0x000c:
        r0 = new android.os.Bundle;	 Catch:{ SecurityException -> 0x0030, Throwable -> 0x0027 }
        r0.<init>();	 Catch:{ SecurityException -> 0x0030, Throwable -> 0x0027 }
        r1 = r7.b;	 Catch:{ Throwable -> 0x0032, SecurityException -> 0x0030 }
        r2 = "gps";
        r3 = "force_xtra_injection";
        r1.sendExtraCommand(r2, r3, r0);	 Catch:{ Throwable -> 0x0032, SecurityException -> 0x0030 }
    L_0x001a:
        r0 = r7.b;	 Catch:{ SecurityException -> 0x0030, Throwable -> 0x0027 }
        r1 = "gps";
        r2 = 800; // 0x320 float:1.121E-42 double:3.953E-321;
        r4 = 0;
        r5 = r7.j;	 Catch:{ SecurityException -> 0x0030, Throwable -> 0x0027 }
        r0.requestLocationUpdates(r1, r2, r4, r5, r6);	 Catch:{ SecurityException -> 0x0030, Throwable -> 0x0027 }
    L_0x0026:
        return;
    L_0x0027:
        r0 = move-exception;
        r1 = "MAPGPSLocation";
        r2 = "requestLocationUpdates";
        com.amap.api.mapcore.util.kz.a(r0, r1, r2);
        goto L_0x0026;
    L_0x0030:
        r0 = move-exception;
        goto L_0x0026;
    L_0x0032:
        r0 = move-exception;
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.kf.f():void");
    }

    private void g() {
        this.d = false;
        this.c = 0;
        this.f = null;
    }

    public final void a() {
        if (!this.e) {
            f();
            this.e = true;
        }
    }

    public final void b() {
        this.e = false;
        g();
        if (this.b != null && this.j != null) {
            this.b.removeUpdates(this.j);
        }
    }

    public final boolean c() {
        if (this.d) {
            if (lc.b() - this.c <= 10000) {
                return true;
            }
            this.f = null;
        }
        return false;
    }

    public final Inner_3dMap_location d() {
        if (this.f == null) {
            return null;
        }
        Inner_3dMap_location clone = this.f.clone();
        if (clone != null && clone.getErrorCode() == 0) {
            try {
                if (this.g != null) {
                    if (kz.a(clone.getLatitude(), clone.getLongitude())) {
                        Object a;
                        Object newInstance;
                        Object[] objArr = new Object[]{"GPS"};
                        Class[] clsArr = new Class[]{String.class};
                        if (this.h) {
                            a = la.a("com.amap.api.maps.CoordinateConverter$CoordType", "valueOf", objArr, clsArr);
                            newInstance = Class.forName("com.amap.api.maps.model.LatLng").getConstructor(new Class[]{Double.TYPE, Double.TYPE}).newInstance(new Object[]{Double.valueOf(clone.getLatitude()), Double.valueOf(clone.getLongitude())});
                        } else {
                            a = la.a("com.amap.api.maps2d.CoordinateConverter$CoordType", "valueOf", objArr, clsArr);
                            newInstance = Class.forName("com.amap.api.maps2d.model.LatLng").getConstructor(new Class[]{Double.TYPE, Double.TYPE}).newInstance(new Object[]{Double.valueOf(clone.getLatitude()), Double.valueOf(clone.getLongitude())});
                        }
                        la.a(this.g, "coord", newInstance);
                        la.a(this.g, "from", a);
                        newInstance = la.a(this.g, "convert", new Object[0]);
                        double doubleValue = ((Double) newInstance.getClass().getDeclaredField(ParamKey.LATITUDE).get(newInstance)).doubleValue();
                        double doubleValue2 = ((Double) newInstance.getClass().getDeclaredField(ParamKey.LONGITUDE).get(newInstance)).doubleValue();
                        clone.setLatitude(doubleValue);
                        clone.setLongitude(doubleValue2);
                    }
                } else if (this.i && kz.a(clone.getLatitude(), clone.getLongitude())) {
                    double[] a2 = ld.a(clone.getLongitude(), clone.getLatitude());
                    if (a2 != null) {
                        clone.setLatitude(a2[1]);
                        clone.setLongitude(a2[0]);
                    }
                }
            } catch (Throwable th) {
            }
        }
        return clone;
    }
}
