package com.amap.api.maps.utils;

import android.util.Pair;
import com.amap.api.mapcore.util.en;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.DPoint;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class SpatialRelationUtil {
    public static final int A_CIRCLE_DEGREE = 360;
    public static final int A_HALF_CIRCLE_DEGREE = 180;
    public static final int MIN_OFFSET_DEGREE = 50;
    public static final int MIN_POLYLINE_POINT_SIZE = 2;

    public static Pair<Integer, LatLng> calShortestDistancePoint(List<LatLng> list, LatLng latLng, float f, double d) {
        if (!(list == null || latLng == null)) {
            try {
                if (list.size() != 0) {
                    List arrayList = new ArrayList();
                    int i = 0;
                    for (LatLng latLng2 : list) {
                        arrayList.add(DPoint.obtain(latLng2.latitude, latLng2.longitude));
                        if (latLng2.equals(latLng)) {
                            return new Pair(Integer.valueOf(i), latLng);
                        }
                        i++;
                    }
                    Pair calShortestDistancePoint = calShortestDistancePoint(arrayList, DPoint.obtain(latLng.latitude, latLng.longitude), f);
                    if (calShortestDistancePoint != null) {
                        DPoint dPoint = (DPoint) calShortestDistancePoint.second;
                        if (((double) AMapUtils.calculateLineDistance(new LatLng(dPoint.x, dPoint.y), latLng)) < d) {
                            return new Pair(calShortestDistancePoint.first, new LatLng(((DPoint) calShortestDistancePoint.second).x, ((DPoint) calShortestDistancePoint.second).y));
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static Pair<Integer, LatLng> calShortestDistancePoint(List<LatLng> list, LatLng latLng) {
        if (!(list == null || latLng == null)) {
            try {
                if (list.size() != 0) {
                    List arrayList = new ArrayList();
                    int i = 0;
                    for (LatLng latLng2 : list) {
                        arrayList.add(DPoint.obtain(latLng2.latitude, latLng2.longitude));
                        if (latLng2.equals(latLng)) {
                            return new Pair(Integer.valueOf(i), latLng);
                        }
                        i++;
                    }
                    Pair calShortestDistancePoint = calShortestDistancePoint(arrayList, DPoint.obtain(latLng.latitude, latLng.longitude));
                    if (calShortestDistancePoint != null) {
                        return new Pair(calShortestDistancePoint.first, new LatLng(((DPoint) calShortestDistancePoint.second).x, ((DPoint) calShortestDistancePoint.second).y));
                    }
                    return null;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static Pair<Integer, DPoint> calShortestDistancePoint(List<DPoint> list, DPoint dPoint) {
        return calShortestDistancePoint(list, dPoint, -1.0f);
    }

    public static Pair<Integer, DPoint> calShortestDistancePoint(List<DPoint> list, DPoint dPoint, float f) {
        if (list == null || dPoint == null || list.size() == 0) {
            return null;
        }
        if (list.size() < 2) {
            return null;
        }
        Pair<Integer, DPoint> pair = null;
        DPoint dPoint2 = (DPoint) list.get(0);
        double d = Utils.DOUBLE_EPSILON;
        int size = list.size();
        int i = 1;
        DPoint dPoint3 = dPoint2;
        while (i <= size - 1) {
            DPoint dPoint4 = (DPoint) list.get(i);
            if (i == size - 1 && dPoint4.equals(dPoint)) {
                return new Pair(Integer.valueOf(i), dPoint);
            }
            if (checkRotateIsMatch(dPoint3, dPoint4, f)) {
                if (dPoint3.equals(dPoint)) {
                    return new Pair(Integer.valueOf(i - 1), dPoint);
                }
                double doubleValue;
                Pair<Integer, DPoint> pair2;
                Pair pointToSegDist = pointToSegDist(dPoint.x, dPoint.y, dPoint3.x, dPoint3.y, dPoint4.x, dPoint4.y);
                if (pair == null) {
                    doubleValue = ((Double) pointToSegDist.first).doubleValue();
                    pair2 = new Pair(Integer.valueOf(i - 1), pointToSegDist.second);
                } else if (d > ((Double) pointToSegDist.first).doubleValue()) {
                    doubleValue = ((Double) pointToSegDist.first).doubleValue();
                    pair2 = new Pair(Integer.valueOf(i - 1), pointToSegDist.second);
                } else {
                    doubleValue = d;
                    pair2 = pair;
                }
                d = doubleValue;
                pair = pair2;
            }
            i++;
            dPoint3 = dPoint4;
        }
        return pair;
    }

    private static boolean checkRotateIsMatch(DPoint dPoint, DPoint dPoint2, float f) {
        if (f == -1.0f) {
            return true;
        }
        if (dPoint == null || dPoint2 == null) {
            return false;
        }
        float abs = Math.abs((en.a(dPoint, dPoint2) + 360.0f) - f) % 360.0f;
        if (abs > 180.0f) {
            abs = 360.0f - abs;
        }
        if (abs >= 50.0f) {
            return false;
        }
        return true;
    }

    private static Pair<Double, DPoint> pointToSegDist(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = ((d5 - d3) * (d - d3)) + ((d6 - d4) * (d2 - d4));
        if (d7 <= Utils.DOUBLE_EPSILON) {
            return new Pair(Double.valueOf(Math.sqrt(((d - d3) * (d - d3)) + ((d2 - d4) * (d2 - d4)))), new DPoint(d3, d4));
        }
        double d8 = ((d5 - d3) * (d5 - d3)) + ((d6 - d4) * (d6 - d4));
        if (d7 >= d8) {
            return new Pair(Double.valueOf(Math.sqrt(((d - d5) * (d - d5)) + ((d2 - d6) * (d2 - d6)))), new DPoint(d5, d6));
        }
        d7 /= d8;
        d8 = ((d5 - d3) * d7) + d3;
        double d9 = d4 + (d7 * (d6 - d4));
        return new Pair(Double.valueOf(Math.sqrt(((d - d8) * (d - d8)) + ((d9 - d2) * (d9 - d2)))), new DPoint(d8, d9));
    }
}
