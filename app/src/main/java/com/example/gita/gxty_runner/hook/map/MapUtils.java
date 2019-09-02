package com.example.gita.gxty_runner.hook.map;

public class MapUtils {

//    public static double LONGITUDE_ON_A_METER = 1.141255544679108e-5;//经度（东西方向）1M实际度  1.0384503089524082810004922069584e-5
//
//    public static double LATITUDE_ON_A_METER = 8.993214944137061e-6;//纬度（南北方向）1M实际度  8.9932149441370900299009843931913e-6

//    public static double LONGITUDE_ON_A_METER = 1.0384503089524083e-5;
//
//    public static double LATITUDE_ON_A_METER = 8.9932149441370901e-6;

    public static double getDistance(LatLng latLng1, LatLng latLng2) {
        double d = 0.01745329251994329d;
        double d1[] = new double[]{latLng1.longitude * d, latLng1.latitude * d, latLng2.longitude * d, latLng2.latitude * d};
        double d2[] = new double[]{Math.sin(d1[0]), Math.sin(d1[1]), Math.sin(d1[2]), Math.sin(d1[3])};
        double d3[] = new double[]{Math.cos(d1[0]), Math.cos(d1[1]), Math.cos(d1[2]), Math.cos(d1[3])};
        double d4[] = new double[]{d3[0] * d3[1], d3[1] * d2[0], d2[1]};
        double d5[] = new double[]{d3[2] * d3[3], d3[3] * d2[2], d2[3]};
        double d6 = Math.sqrt((d4[0] - d5[0]) * (d4[0] - d5[0]) + (d4[1] - d5[1]) * (d4[1] - d5[1]) + (d4[2] - d5[2]) * (d4[2] - d5[2]));
        return (Math.asin(d6 / 2.0d) * 1.27420015798544E7d);
    }

//    public static double calculateBearingAngle(LatLng start, LatLng end) {
//        return calculateBearingAngle(start.longitude, start.latitude, end.longitude, end.latitude);
//    }

//    private static double calculateBearingAngle(double x1, double y1, double x2, double y2) {
//        double dx = x2 - x1, dy = y2 - y1;
//        if (dx == 0) return dy > 0 ? Math.PI / 2 : Math.PI / -2;
//        if (dy == 0) return dx > 0 ? 0 : Math.PI;
//        double atan2 = Math.atan2(dy, dx);
//        return atan2 > 0 ? atan2 : Math.PI * 2 + atan2;
//    }

//    public static LatLng toGCJ02(LatLng latLng) {
//        if (isOutOfChina(latLng)|| latLng.type == LatLng.TYPE_GCJ02) {
//            return latLng.clone();
//        }
//        double dlat = transformLatitude(latLng.latitude - 35.0, latLng.longitude - 105.0);
//        double dlng = transformLongitude(latLng.latitude - 35.0, latLng.longitude - 105.0);
//        double radlat = Math.toRadians(latLng.latitude);
//        double magic = Math.sin(radlat);
//        magic = 1 - 0.00669342162296594323 * magic * magic;
//        double sqrtmagic = Math.sqrt(magic);
//        dlat = (dlat * 180.0) / ((6378245.0 * (1 - 0.00669342162296594323)) / (magic * sqrtmagic) * Math.PI);//6335552.71700042558742296865
//        dlng = (dlng * 180.0) / (6378245.0 / sqrtmagic * Math.cos(radlat) * Math.PI);
//        double mglat = latLng.latitude + dlat;
//        double mglng = latLng.longitude + dlng;
//        return new LatLng(mglat, mglng,LatLng.TYPE_GCJ02);
//    }
//
//    public static LatLng toWGS84(LatLng latLng) {
//        if (isOutOfChina(latLng) || latLng.type == LatLng.TYPE_WGS84) {
//            return latLng.clone();
//        }
//        double dlat = transformLatitude(latLng.latitude - 35.0, latLng.longitude - 105.0);
//        double dlng = transformLongitude(latLng.latitude - 35.0, latLng.longitude - 105.0);
//        double radlat = Math.toRadians(latLng.latitude);
//        double magic = Math.sin(radlat);
//        magic = 1 - 0.00669342162296594323 * magic * magic;
//        double sqrtmagic = Math.sqrt(magic);
//        dlat = (dlat * 180.0) / ((6378245.0 * (1 - 0.00669342162296594323)) / (magic * sqrtmagic) * Math.PI);
//        dlng = (dlng * 180.0) / (6378245.0 / sqrtmagic * Math.cos(radlat) * Math.PI);
//        double mglat = latLng.latitude + dlat;
//        double mglng = latLng.longitude + dlng;
//        return new LatLng(latLng.latitude * 2 - mglat, latLng.longitude * 2 - mglng,LatLng.TYPE_WGS84);
//    }
//
//    private static double transformLatitude(double latitude, double longitude) {
//        double ret = -100.0 + 2.0 * longitude + 3.0 * latitude + 0.2 * latitude * latitude + 0.1 * longitude * latitude + 0.2 * Math.sqrt(Math.abs(longitude));
//        ret += (20.0 * Math.sin(6.0 * longitude * Math.PI) + 20.0 * Math.sin(2.0 * longitude * Math.PI)) * 2.0 / 3.0;
//        ret += (20.0 * Math.sin(latitude * Math.PI) + 40.0 * Math.sin(latitude / 3.0 * Math.PI)) * 2.0 / 3.0;
//        ret += (160.0 * Math.sin(latitude / 12.0 * Math.PI) + 320 * Math.sin(latitude * Math.PI / 30.0)) * 2.0 / 3.0;
//        return ret;
//    }
//
//    private static double transformLongitude(double latitude, double longitude) {
//        double ret = 300.0 + longitude + 2.0 * latitude + 0.1 * longitude * longitude + 0.1 * longitude * latitude + 0.1 * Math.sqrt(Math.abs(longitude));
//        ret += (20.0 * Math.sin(6.0 * longitude * Math.PI) + 20.0 * Math.sin(2.0 * longitude * Math.PI)) * 2.0 / 3.0;
//        ret += (20.0 * Math.sin(longitude * Math.PI) + 40.0 * Math.sin(longitude / 3.0 * Math.PI)) * 2.0 / 3.0;
//        ret += (150.0 * Math.sin(longitude / 12.0 * Math.PI) + 300.0 * Math.sin(longitude / 30.0 * Math.PI)) * 2.0 / 3.0;
//        return ret;
//    }
//
//    private static boolean isOutOfChina(LatLng latLng) {
//        return latLng.longitude < 72.004 || (latLng.longitude > 137.8347) || (latLng.latitude < 0.8293) || (latLng.latitude > 55.8271);
//    }

    public static float toRotateAngle(double rad) {
        return (float) ((rad > Math.PI / 2) ? Math.toDegrees(rad) - 90 : Math.toDegrees(rad) + 270);
    }


    private static final double LONGITUDE_ON_A_METER = 10.0 / getDistance(new LatLng(30, 104), new LatLng(30, 114));
    private static final double LATITUDE_ON_A_METER = 10.0 / getDistance(new LatLng(30, 104), new LatLng(40, 104));

    public static LatLng nextLatLng(LatLng latLng, Angle angle, double distance) {
        double latitude = latLng.latitude + distance * Math.sin(angle.rad()) * LATITUDE_ON_A_METER;
        double longitude = latLng.longitude + distance * Math.cos(angle.rad()) * LONGITUDE_ON_A_METER;
        return new LatLng(latitude, longitude);
    }

    public static LatLng nextOffsetLatLng(LatLng latLng, Angle angle, double offset) {
        double latitude = latLng.latitude + offset * Math.sin(angle.rad() - Math.PI / 2) * LATITUDE_ON_A_METER;
        double longitude = latLng.longitude + offset * Math.cos(angle.rad() - Math.PI / 2) * LONGITUDE_ON_A_METER;
        return new LatLng(latitude, longitude);
    }

}