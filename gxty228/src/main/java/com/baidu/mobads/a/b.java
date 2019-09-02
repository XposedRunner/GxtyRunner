package com.baidu.mobads.a;

import com.github.mikephil.charting.utils.Utils;

public class b {
    public static final Boolean a = Boolean.valueOf(false);
    public static final Boolean b = Boolean.valueOf(false);

    public static double a() {
        try {
            return Double.parseDouble("8.7052");
        } catch (Exception e) {
            return Utils.DOUBLE_EPSILON;
        }
    }

    public static int b() {
        int i = 0;
        try {
            i = Integer.valueOf("8.7052".split("\\.")[0]).intValue();
        } catch (Exception e) {
        }
        return i;
    }
}
