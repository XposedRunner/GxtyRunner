package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.CoordUtil;
import com.autonavi.amap.mapcore.DPoint;
import com.github.mikephil.charting.utils.Utils;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: OffsetUtil */
public class q {
    static double a = 3.141592653589793d;
    public static double b = 6378245.0d;
    public static double c = 0.006693421622965943d;
    private static boolean d = false;
    private static final double[] e = new double[]{25.575374d, 120.391111d};
    private static final double[] f = new double[]{21.405235d, 121.649046d};
    private static final List<LatLng> g = new ArrayList(Arrays.asList(new LatLng[]{new LatLng(23.379947d, 119.757001d), new LatLng(24.983296d, 120.474496d), new LatLng(25.518722d, 121.359866d), new LatLng(25.41329d, 122.443582d), new LatLng(24.862708d, 122.288354d), new LatLng(24.461292d, 122.188319d), new LatLng(21.584761d, 120.968923d), new LatLng(21.830837d, 120.654445d)}));

    public static LatLng a(Context context, LatLng latLng) {
        if (context == null) {
            return null;
        }
        if (!ee.a(latLng.latitude, latLng.longitude)) {
            return latLng;
        }
        String a = gi.a(context, "libwgs2gcj.so");
        if (!(TextUtils.isEmpty(a) || !new File(a).exists() || d)) {
            try {
                System.load(a);
                d = true;
            } catch (Throwable th) {
            }
        }
        DPoint a2 = a(DPoint.obtain(latLng.longitude, latLng.latitude), d);
        LatLng latLng2 = new LatLng(a2.y, a2.x, false);
        a2.recycle();
        return latLng2;
    }

    private static DPoint a(DPoint dPoint, boolean z) {
        double[] dArr;
        try {
            if (!ee.a(dPoint.y, dPoint.x)) {
                return dPoint;
            }
            dArr = new double[2];
            if (z) {
                if (CoordUtil.convertToGcj(new double[]{dPoint.x, dPoint.y}, dArr) != 0) {
                    dArr = kd.a(dPoint.x, dPoint.y);
                }
            } else {
                dArr = kd.a(dPoint.x, dPoint.y);
            }
            dPoint.recycle();
            return DPoint.obtain(dArr[0], dArr[1]);
        } catch (Throwable th) {
            return dPoint;
        }
    }

    public static LatLng b(Context context, LatLng latLng) {
        try {
            if (!ee.a(latLng.latitude, latLng.longitude)) {
                return latLng;
            }
            DPoint f = f(latLng.longitude, latLng.latitude);
            LatLng a = a(context, new LatLng(f.y, f.x, false));
            f.recycle();
            return a;
        } catch (Throwable th) {
            th.printStackTrace();
            return latLng;
        }
    }

    public static double a(double d, double d2) {
        return (Math.cos(d2 / 100000.0d) * (d / 18000.0d)) + (Math.sin(d / 100000.0d) * (d2 / 9000.0d));
    }

    public static double b(double d, double d2) {
        return (Math.sin(d2 / 100000.0d) * (d / 18000.0d)) + (Math.cos(d / 100000.0d) * (d2 / 9000.0d));
    }

    private static DPoint f(double d, double d2) {
        double d3 = (double) (((long) (100000.0d * d)) % 36000000);
        double d4 = (double) (((long) (100000.0d * d2)) % 36000000);
        int i = (int) ((-b(d3, d4)) + d4);
        int i2 = (int) (((double) (d3 > Utils.DOUBLE_EPSILON ? 1 : -1)) + ((-a((double) ((int) ((-a(d3, d4)) + d3)), (double) i)) + d3));
        return DPoint.obtain(((double) i2) / 100000.0d, ((double) ((int) (((double) (d4 > Utils.DOUBLE_EPSILON ? 1 : -1)) + ((-b((double) i2, (double) i)) + d4)))) / 100000.0d);
    }

    public static LatLng a(LatLng latLng) {
        if (latLng == null) {
            return latLng;
        }
        try {
            DPoint a;
            if (ee.a(latLng.latitude, latLng.longitude)) {
                a = a(latLng.longitude, latLng.latitude, 2);
                LatLng latLng2 = new LatLng(a.y, a.x, false);
                a.recycle();
                return latLng2;
            } else if (!c(latLng.latitude, latLng.longitude)) {
                return latLng;
            } else {
                a = a(latLng.longitude, latLng.latitude, 2);
                return h(a.y, a.x);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return latLng;
        }
    }

    private static double a(double d) {
        return Math.sin((3000.0d * d) * (a / 180.0d)) * 2.0E-5d;
    }

    private static double b(double d) {
        return Math.cos((3000.0d * d) * (a / 180.0d)) * 3.0E-6d;
    }

    private static DPoint g(double d, double d2) {
        DPoint obtain = DPoint.obtain();
        double sin = (Math.sin(b(d) + Math.atan2(d2, d)) * (a(d2) + Math.sqrt((d * d) + (d2 * d2)))) + 0.006d;
        obtain.x = a((Math.cos(b(d) + Math.atan2(d2, d)) * (a(d2) + Math.sqrt((d * d) + (d2 * d2)))) + 0.0065d, 8);
        obtain.y = a(sin, 8);
        return obtain;
    }

    private static double a(double d, int i) {
        return new BigDecimal(d).setScale(i, 4).doubleValue();
    }

    private static DPoint a(double d, double d2, int i) {
        double d3 = 0.006401062d;
        double d4 = 0.0060424805d;
        int i2 = 0;
        DPoint dPoint = null;
        while (i2 < i) {
            DPoint a = a(d, d2, d3, d4);
            d3 = d - a.x;
            d4 = d2 - a.y;
            i2++;
            dPoint = a;
        }
        return dPoint;
    }

    private static DPoint a(double d, double d2, double d3, double d4) {
        DPoint obtain = DPoint.obtain();
        double d5 = d - d3;
        double d6 = d2 - d4;
        DPoint g = g(d5, d6);
        obtain.x = a((d5 + d) - g.x, 8);
        obtain.y = a((d2 + d6) - g.y, 8);
        return obtain;
    }

    public static boolean c(double d, double d2) {
        if (en.a(new LatLng(d, d2), g)) {
            return true;
        }
        return false;
    }

    private static LatLng h(double d, double d2) {
        LatLng i = i(d, d2);
        return new LatLng((d * 2.0d) - i.latitude, (d2 * 2.0d) - i.longitude);
    }

    private static LatLng i(double d, double d2) {
        double d3 = d(d2 - 105.0d, d - 35.0d);
        double e = e(d2 - 105.0d, d - 35.0d);
        double d4 = (d / 180.0d) * a;
        double sin = Math.sin(d4);
        sin = 1.0d - (sin * (c * sin));
        double sqrt = Math.sqrt(sin);
        return new LatLng(((d3 * 180.0d) / (((b * (1.0d - c)) / (sin * sqrt)) * a)) + d, ((e * 180.0d) / ((Math.cos(d4) * (b / sqrt)) * a)) + d2);
    }

    public static double d(double d, double d2) {
        return (((((((-100.0d + (2.0d * d)) + (3.0d * d2)) + ((0.2d * d2) * d2)) + ((0.1d * d) * d2)) + (0.2d * Math.sqrt(Math.abs(d)))) + ((((20.0d * Math.sin((6.0d * d) * a)) + (20.0d * Math.sin((2.0d * d) * a))) * 2.0d) / 3.0d)) + ((((20.0d * Math.sin(a * d2)) + (40.0d * Math.sin((d2 / 3.0d) * a))) * 2.0d) / 3.0d)) + ((((160.0d * Math.sin((d2 / 12.0d) * a)) + (320.0d * Math.sin((a * d2) / 30.0d))) * 2.0d) / 3.0d);
    }

    public static double e(double d, double d2) {
        return (((((((300.0d + d) + (2.0d * d2)) + ((0.1d * d) * d)) + ((0.1d * d) * d2)) + (0.1d * Math.sqrt(Math.abs(d)))) + ((((20.0d * Math.sin((6.0d * d) * a)) + (20.0d * Math.sin((2.0d * d) * a))) * 2.0d) / 3.0d)) + ((((20.0d * Math.sin(a * d)) + (40.0d * Math.sin((d / 3.0d) * a))) * 2.0d) / 3.0d)) + ((((150.0d * Math.sin((d / 12.0d) * a)) + (300.0d * Math.sin((d / 30.0d) * a))) * 2.0d) / 3.0d);
    }
}
