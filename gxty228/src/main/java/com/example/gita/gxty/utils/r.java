package com.example.gita.gxty.utils;

/* compiled from: StringUtils */
public class r {
    public static boolean a(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    public static boolean b(String str) {
        if (str == null || "".equals(str.trim())) {
            return false;
        }
        return true;
    }
}
