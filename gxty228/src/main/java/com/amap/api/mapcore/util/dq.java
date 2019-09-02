package com.amap.api.mapcore.util;

/* compiled from: DynamicSoUtil */
public class dq {
    private static hy a;

    public static hy a() {
        if (a == null) {
            try {
                a = new hy(en.e(), false);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return a;
    }
}
