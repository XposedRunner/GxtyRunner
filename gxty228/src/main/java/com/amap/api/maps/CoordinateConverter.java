package com.amap.api.maps;

import android.content.Context;
import com.amap.api.mapcore.util.ee;
import com.amap.api.mapcore.util.gz;
import com.amap.api.mapcore.util.q;
import com.amap.api.maps.model.LatLng;

public class CoordinateConverter {
    private Context a;
    private CoordType b = null;
    private LatLng c = null;

    public enum CoordType {
        BAIDU,
        MAPBAR,
        GPS,
        MAPABC,
        SOSOMAP,
        ALIYUN,
        GOOGLE
    }

    public CoordinateConverter(Context context) {
        this.a = context;
    }

    public CoordinateConverter from(CoordType coordType) {
        this.b = coordType;
        return this;
    }

    public CoordinateConverter coord(LatLng latLng) {
        this.c = latLng;
        return this;
    }

    public LatLng convert() {
        if (this.b == null || this.c == null) {
            return null;
        }
        try {
            switch (this.b) {
                case BAIDU:
                    return q.a(this.c);
                case MAPBAR:
                    return q.b(this.a, this.c);
                case MAPABC:
                case SOSOMAP:
                case ALIYUN:
                case GOOGLE:
                    return this.c;
                case GPS:
                    return q.a(this.a, this.c);
                default:
                    return null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            gz.c(th, "CoordinateConverter", "convert");
            return this.c;
        }
    }

    public static boolean isAMapDataAvailable(double d, double d2) {
        return ee.a(d, d2);
    }
}
