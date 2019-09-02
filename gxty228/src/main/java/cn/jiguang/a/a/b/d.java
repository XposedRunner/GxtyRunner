package cn.jiguang.a.a.b;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

final class d implements LocationListener {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public final void onLocationChanged(Location location) {
        cn.jiguang.e.d.c("GpsInfoManager", "get location from " + this.a.c + ":" + location);
        if (location != null) {
            this.a.a(location, this.a.c, false);
        }
        this.a.d();
    }

    public final void onProviderDisabled(String str) {
        cn.jiguang.e.d.c("GpsInfoManager", "onProviderDisabled:" + str);
        this.a.d();
    }

    public final void onProviderEnabled(String str) {
        cn.jiguang.e.d.c("GpsInfoManager", "onProviderEnabled:" + str);
    }

    public final void onStatusChanged(String str, int i, Bundle bundle) {
        cn.jiguang.e.d.c("GpsInfoManager", "onStatusChanged status:" + i);
        if (i == 0) {
            this.a.d();
        }
    }
}
