package com.amap.api.mapcore.util;

import com.amap.api.mapcore.util.gk.a;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.github.mikephil.charting.utils.Utils;
import com.tencent.connect.common.Constants;

/* compiled from: Util */
public final class kp {
    static gk a = null;
    private static final String[] b = new String[]{"com.amap.api.maps", "com.amap.api.mapcore", "com.autonavi.amap.mapcore", "com.amap.api.3dmap.admic", "com.amap.api.trace", "com.amap.api.trace.core"};
    private static final String[] c = new String[]{"com.amap.api.mapcore2d", "com.amap.api.maps2d"};
    private static final String[] d = new String[]{"com.amap.trace"};

    public static gk a() throws gp {
        if (a != null) {
            return a;
        }
        Class cls;
        String str;
        try {
            cls = Class.forName("com.amap.api.maps.MapsInitializer");
        } catch (Throwable th) {
            cls = null;
        }
        if (cls != null) {
            try {
                str = (String) la.a(cls, "getVersion", null, null);
                a = new a("3dmap", str, "AMAP_SDK_Android_Map_" + str).a(b).a();
            } catch (Throwable th2) {
            }
        } else {
            try {
                cls = Class.forName("com.amap.api.maps2d.MapsInitializer");
                str = (String) la.a(cls, "getVersion", null, null);
                a = new a("2dmap", str, "AMAP_SDK_Android_2DMap_" + str).a(c).a();
            } catch (Throwable th3) {
                cls = cls;
            }
        }
        if (cls == null) {
            Class cls2;
            try {
                cls2 = Class.forName("com.amap.trace.AMapTraceClient");
            } catch (Throwable th4) {
                cls2 = null;
            }
            if (cls2 != null) {
                try {
                    str = (String) la.a(cls2, "getVersion", null, null);
                    a = new a("trace", str, "AMAP_TRACE_Android_" + str).a(d).a();
                } catch (Throwable th5) {
                }
            }
        }
        return a;
    }

    public static boolean a(kx kxVar) {
        return (kxVar == null || kxVar.d().equals(Constants.VIA_SHARE_TYPE_PUBLISHVIDEO) || kxVar.d().equals("5") || kxVar.d().equals(Constants.VIA_SHARE_TYPE_INFO)) ? false : a((Inner_3dMap_location) kxVar);
    }

    private static boolean a(Inner_3dMap_location inner_3dMap_location) {
        double longitude = inner_3dMap_location.getLongitude();
        double latitude = inner_3dMap_location.getLatitude();
        return !(longitude == Utils.DOUBLE_EPSILON && latitude == Utils.DOUBLE_EPSILON) && longitude <= 180.0d && latitude <= 90.0d && longitude >= -180.0d && latitude >= -90.0d;
    }
}
