package com.amap.api.maps;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.amap.api.mapcore.util.fx;
import com.amap.api.mapcore.util.ga;
import com.amap.api.mapcore.util.gp;
import com.amap.api.mapcore.util.le;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.NaviPara;
import com.amap.api.maps.model.PoiPara;
import com.amap.api.maps.model.RoutePara;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;
import java.util.Locale;

public class AMapUtils {
    public static final int BUS_COMFORT = 4;
    public static final int BUS_MONEY_LITTLE = 1;
    public static final int BUS_NO_SUBWAY = 5;
    public static final int BUS_TIME_FIRST = 0;
    public static final int BUS_TRANSFER_LITTLE = 2;
    public static final int BUS_WALK_LITTLE = 3;
    public static final int DRIVING_AVOID_CONGESTION = 4;
    public static final int DRIVING_DEFAULT = 0;
    public static final int DRIVING_NO_HIGHWAY = 3;
    public static final int DRIVING_NO_HIGHWAY_AVOID_CONGESTION = 6;
    public static final int DRIVING_NO_HIGHWAY_AVOID_SHORT_MONEY = 5;
    public static final int DRIVING_NO_HIGHWAY_SAVE_MONEY_AVOID_CONGESTION = 8;
    public static final int DRIVING_SAVE_MONEY = 1;
    public static final int DRIVING_SAVE_MONEY_AVOID_CONGESTION = 7;
    public static final int DRIVING_SHORT_DISTANCE = 2;

    static class a extends Thread {
        String a = "";
        Context b;

        public a(String str, Context context) {
            this.a = str;
            if (context != null) {
                this.b = context.getApplicationContext();
            }
        }

        public void run() {
            if (this.b != null) {
                try {
                    ga.a(this.b, new com.amap.api.mapcore.util.gk.a(this.a, "6.4.1", le.c).a(new String[]{"com.amap.api.maps"}).a());
                    interrupt();
                } catch (gp e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static float calculateLineDistance(LatLng latLng, LatLng latLng2) {
        if (latLng == null || latLng2 == null) {
            try {
                throw new AMapException(AMapException.ERROR_ILLEGAL_VALUE);
            } catch (AMapException e) {
                e.printStackTrace();
                return 0.0f;
            }
        }
        try {
            double d = latLng.longitude;
            double d2 = latLng.latitude;
            d *= 0.01745329251994329d;
            d2 *= 0.01745329251994329d;
            double d3 = latLng2.longitude * 0.01745329251994329d;
            double d4 = latLng2.latitude * 0.01745329251994329d;
            double sin = Math.sin(d);
            double sin2 = Math.sin(d2);
            d = Math.cos(d);
            d2 = Math.cos(d2);
            double sin3 = Math.sin(d3);
            double sin4 = Math.sin(d4);
            d3 = Math.cos(d3);
            d4 = Math.cos(d4);
            r18 = new double[3];
            double[] dArr = new double[]{d * d2, d2 * sin, sin2};
            dArr[0] = d4 * d3;
            dArr[1] = d4 * sin3;
            dArr[2] = sin4;
            return (float) (Math.asin(Math.sqrt((((r18[0] - dArr[0]) * (r18[0] - dArr[0])) + ((r18[1] - dArr[1]) * (r18[1] - dArr[1]))) + ((r18[2] - dArr[2]) * (r18[2] - dArr[2]))) / 2.0d) * 1.27420015798544E7d);
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public static float calculateArea(LatLng latLng, LatLng latLng2) {
        float f = 0.0f;
        if (latLng == null || latLng2 == null) {
            try {
                throw new AMapException(AMapException.ERROR_ILLEGAL_VALUE);
            } catch (AMapException e) {
                e.printStackTrace();
                return f;
            }
        }
        try {
            double sin = Math.sin((latLng.latitude * 3.141592653589793d) / 180.0d) - Math.sin((latLng2.latitude * 3.141592653589793d) / 180.0d);
            double d = (latLng2.longitude - latLng.longitude) / 360.0d;
            if (d < Utils.DOUBLE_EPSILON) {
                d += 1.0d;
            }
            return (float) (d * (sin * 2.5560394669790553E14d));
        } catch (Throwable th) {
            th.printStackTrace();
            return f;
        }
    }

    public static float calculateArea(List<LatLng> list) {
        if (list == null || list.size() < 3) {
            return 0.0f;
        }
        int size = list.size();
        double d = Utils.DOUBLE_EPSILON;
        for (int i = 0; i < size; i++) {
            LatLng latLng = (LatLng) list.get(i);
            LatLng latLng2 = (LatLng) list.get((i + 1) % size);
            double cos = (latLng2.longitude * 111319.49079327357d) * Math.cos(latLng2.latitude * 0.017453292519943295d);
            d += ((latLng2.latitude * 111319.49079327357d) * ((latLng.longitude * 111319.49079327357d) * Math.cos(latLng.latitude * 0.017453292519943295d))) - (cos * (latLng.latitude * 111319.49079327357d));
        }
        return (float) Math.abs(d / 2.0d);
    }

    public static void getLatestAMapApp(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(276824064);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("http://wap.amap.com/"));
            new a("glaa", context).start();
            context.startActivity(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void openAMapNavi(NaviPara naviPara, Context context) throws AMapException {
        if (!a(context)) {
            throw new AMapException(AMapException.AMAP_NOT_SUPPORT);
        } else if (naviPara.getTargetPoint() != null) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(276824064);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(a(naviPara, context)));
                intent.setPackage("com.autonavi.minimap");
                new a("oan", context).start();
                context.startActivity(intent);
            } catch (Throwable th) {
                AMapException aMapException = new AMapException(AMapException.AMAP_NOT_SUPPORT);
            }
        } else {
            throw new AMapException(AMapException.ILLEGAL_AMAP_ARGUMENT);
        }
    }

    public static void openAMapPoiNearbySearch(PoiPara poiPara, Context context) throws AMapException {
        if (!a(context)) {
            throw new AMapException(AMapException.AMAP_NOT_SUPPORT);
        } else if (poiPara.getKeywords() == null || poiPara.getKeywords().trim().length() <= 0) {
            throw new AMapException(AMapException.ILLEGAL_AMAP_ARGUMENT);
        } else {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(276824064);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(a(poiPara, context)));
                intent.setPackage("com.autonavi.minimap");
                new a("oan", context).start();
                context.startActivity(intent);
            } catch (Throwable th) {
                AMapException aMapException = new AMapException(AMapException.AMAP_NOT_SUPPORT);
            }
        }
    }

    public static void openAMapDrivingRoute(RoutePara routePara, Context context) throws AMapException {
        a(routePara, context, 2);
    }

    public static void openAMapTransitRoute(RoutePara routePara, Context context) throws AMapException {
        a(routePara, context, 1);
    }

    public static void openAMapWalkingRoute(RoutePara routePara, Context context) throws AMapException {
        a(routePara, context, 4);
    }

    private static void a(RoutePara routePara, Context context, int i) throws AMapException {
        if (!a(context)) {
            throw new AMapException(AMapException.AMAP_NOT_SUPPORT);
        } else if (a(routePara)) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(276824064);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(b(routePara, context, i)));
                intent.setPackage("com.autonavi.minimap");
                new a("oan", context).start();
                context.startActivity(intent);
            } catch (Throwable th) {
                AMapException aMapException = new AMapException(AMapException.AMAP_NOT_SUPPORT);
            }
        } else {
            throw new AMapException(AMapException.ILLEGAL_AMAP_ARGUMENT);
        }
    }

    private static boolean a(RoutePara routePara) {
        if (routePara.getStartPoint() == null || routePara.getEndPoint() == null || routePara.getStartName() == null || routePara.getStartName().trim().length() <= 0 || routePara.getEndName() == null || routePara.getEndName().trim().length() <= 0) {
            return false;
        }
        return true;
    }

    private static String a(NaviPara naviPara, Context context) {
        return String.format(Locale.US, "androidamap://navi?sourceApplication=%s&lat=%f&lon=%f&dev=0&style=%d", new Object[]{fx.b(context), Double.valueOf(naviPara.getTargetPoint().latitude), Double.valueOf(naviPara.getTargetPoint().longitude), Integer.valueOf(naviPara.getNaviStyle())});
    }

    private static String b(RoutePara routePara, Context context, int i) {
        String format = String.format(Locale.US, "androidamap://route?sourceApplication=%s&slat=%f&slon=%f&sname=%s&dlat=%f&dlon=%f&dname=%s&dev=0&t=%d", new Object[]{fx.b(context), Double.valueOf(routePara.getStartPoint().latitude), Double.valueOf(routePara.getStartPoint().longitude), routePara.getStartName(), Double.valueOf(routePara.getEndPoint().latitude), Double.valueOf(routePara.getEndPoint().longitude), routePara.getEndName(), Integer.valueOf(i)});
        if (i == 1) {
            return format + "&m=" + routePara.getTransitRouteStyle();
        }
        if (i == 2) {
            return format + "&m=" + routePara.getDrivingRouteStyle();
        }
        return format;
    }

    private static String a(PoiPara poiPara, Context context) {
        String format = String.format(Locale.US, "androidamap://arroundpoi?sourceApplication=%s&keywords=%s&dev=0", new Object[]{fx.b(context), poiPara.getKeywords()});
        if (poiPara.getCenter() != null) {
            return format + "&lat=" + poiPara.getCenter().latitude + "&lon=" + poiPara.getCenter().longitude;
        }
        return format;
    }

    private static boolean a(Context context) {
        try {
            if (context.getPackageManager().getPackageInfo("com.autonavi.minimap", 0) != null) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
