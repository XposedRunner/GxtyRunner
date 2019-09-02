package com.example.gita.gxty.utils;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint({"SimpleDateFormat"})
/* compiled from: DateUtils */
public class d {
    public static String a(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j));
    }

    public static String b(long j) {
        String str;
        long j2 = ((j / 3600) * 60) + ((j % 3600) / 60);
        if (j2 <= 9) {
            str = "0" + j2;
        } else {
            str = "" + j2;
        }
        return str + "'" + ((j % 3600) % 60 > 9 ? ((j % 3600) % 60) + "" : "0" + ((j % 3600) % 60)) + "''";
    }

    public static String c(long j) {
        String str;
        long j2 = ((j / 3600) * 60) + ((j % 3600) / 60);
        if (j2 <= 9) {
            str = "0" + j2;
        } else {
            str = "" + j2;
        }
        return str + ":" + ((j % 3600) % 60 > 9 ? ((j % 3600) % 60) + "" : "0" + ((j % 3600) % 60));
    }

    public static float d(long j) {
        return ((float) (((j / 3600) * 60) + ((j % 3600) / 60))) + (((float) ((j % 3600) % 60)) / 60.0f);
    }

    public static String e(long j) {
        return f(1000 * j);
    }

    public static String f(long j) {
        int i = 1;
        String str = "";
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M月d日 HH:mm");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy年M月d日 HH:mm");
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("HH:mm");
        if ((instance.get(1) == instance2.get(1) ? 1 : 0) == 0) {
            return simpleDateFormat2.format(new Date(j));
        }
        if (instance.get(2) != instance2.get(2)) {
            return simpleDateFormat.format(new Date(j));
        }
        switch (instance.get(5) - instance2.get(5)) {
            case 0:
                int i2 = instance.get(11) - instance2.get(11);
                if (i2 != 0) {
                    return i2 + "小时前";
                }
                i2 = instance.get(12) - instance2.get(12);
                if (i2 != 0) {
                    i = i2;
                }
                return i + "分钟前";
            case 1:
                return "昨天 " + simpleDateFormat3.format(new Date(j));
            default:
                return simpleDateFormat.format(new Date(j));
        }
    }
}
