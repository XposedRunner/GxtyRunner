package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.util.regex.Pattern;

/* compiled from: VersionUtil */
public final class if {
    private static Pattern a = null;

    public static int a(String str, String str2) {
        int i = 0;
        try {
            String[] split = str.split("\\.");
            String[] split2 = str2.split("\\.");
            int min = Math.min(split.length, split2.length);
            for (int i2 = 0; i2 < min; i2++) {
                i = split[i2].length() - split2[i2].length();
                if (i != 0) {
                    break;
                }
                i = split[i2].compareTo(split2[i2]);
                if (i != 0) {
                    break;
                }
            }
            if (i != 0) {
                return i;
            }
            return split.length - split2.length;
        } catch (Throwable th) {
            gw.a(th, "Utils", "compareVersion");
            return -1;
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (a == null) {
            a = Pattern.compile("[\\d+\\.]+");
        }
        return a.matcher(str).matches();
    }
}
