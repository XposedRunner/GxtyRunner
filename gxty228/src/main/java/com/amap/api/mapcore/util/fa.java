package com.amap.api.mapcore.util;

import android.location.Location;
import com.amap.api.maps.LocationSource.OnLocationChangedListener;

/* compiled from: AMapOnLocationChangedListener */
class fa implements OnLocationChangedListener {
    Location a;
    private lj b;

    fa(lj ljVar) {
        this.b = ljVar;
    }

    public void onLocationChanged(Location location) {
        this.a = location;
        try {
            if (this.b.isMyLocationEnabled()) {
                this.b.a(location);
            }
        } catch (Throwable th) {
            gz.c(th, "AMapOnLocationChangedListener", "onLocationChanged");
            th.printStackTrace();
        }
    }
}
