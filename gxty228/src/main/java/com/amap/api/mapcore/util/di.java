package com.amap.api.mapcore.util;

import android.text.TextUtils;

/* compiled from: StyleItemAdaptor */
public class di {
    public static final int[][] a;
    public static final String[] b = new String[]{"land", "water", "green", "building", "highway", "arterial", "local", "railway", "subway", "boundary", "poilabel", "districtlable"};
    public static final String[][] c;
    public static final String[] d = new String[]{"regions", "water", "regions", "buildings", "roads", "roads", "roads", "roads", "roads", "borders", "labels", "labels"};

    static {
        r0 = new int[12][];
        r0[1] = new int[]{12};
        r0[2] = new int[]{1};
        r0[3] = new int[]{13};
        r0[4] = new int[]{14};
        r0[5] = new int[]{15, 16};
        r0[6] = new int[]{17, 18, 19, 20, 21, 26, 27, 28};
        r0[7] = new int[]{22, 23};
        r0[8] = new int[]{24, 25};
        r0[9] = new int[]{39, 40, 41};
        r0[10] = new int[]{29, 30, 31};
        r0[11] = new int[]{32, 33, 34, 35, 36, 37, 38};
        a = r0;
        r0 = new String[12][];
        r0[0] = new String[]{"land", "edu", "public", "traffic", "scenicSpot", "culture", "health", "sports", "business", "parkingLot", "subway"};
        r0[1] = new String[]{"water"};
        r0[2] = new String[]{"green"};
        r0[3] = new String[]{"buildings"};
        r0[4] = new String[]{"highWay"};
        r0[5] = new String[]{"ringRoad", "nationalRoad"};
        r0[6] = new String[]{"provincialRoad", "secondaryRoad", "levelThreeRoad", "levelFourRoad", "roadsBeingBuilt", "overPass", "underPass", "other"};
        r0[7] = new String[]{"railway", "highSpeedRailway"};
        r0[8] = new String[]{"subwayline", "subwayBeingBuilt"};
        r0[9] = new String[]{"China", "foreign", "provincial"};
        r0[10] = new String[]{"guideBoards", "pois", "aois"};
        r0[11] = new String[]{"continent", "country", "province", "city", "district", "town", "village"};
        c = r0;
    }

    public static String[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i = 0;
        while (i < b.length) {
            if (b[i].equals(str)) {
                break;
            }
            i++;
        }
        i = -1;
        if (i >= 0) {
            return c[i];
        }
        return null;
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i = 0;
        while (i < b.length) {
            if (b[i].equals(str)) {
                break;
            }
            i++;
        }
        i = -1;
        return i >= 0 ? d[i] : null;
    }
}
