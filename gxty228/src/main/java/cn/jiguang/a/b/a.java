package cn.jiguang.a.b;

import android.content.Context;
import cn.jiguang.d.a.d;

public final class a {
    public static long a(Context context) {
        long longValue = ((Long) d.b(context, "report_location_frequency", Long.valueOf(3600000))).longValue();
        return longValue > 0 ? longValue : 3600000;
    }

    public static void a(Context context, boolean z) {
        d.a(context, "lbs_report_enable", Boolean.valueOf(z));
    }

    public static String b(Context context) {
        return cn.jiguang.d.a.a.b(context, "number_appsecret", "2b90de0f1f88eaf49593f1d827b19c63");
    }

    public static void b(Context context, boolean z) {
        d.a(context, "lbs_report_now", Boolean.valueOf(z));
    }

    public static long c(Context context) {
        return ((Long) d.b(context, "report_arpinfo_frequency", Long.valueOf(0))).longValue();
    }

    public static void c(Context context, boolean z) {
        d.a(context, "nb_upload", Boolean.valueOf(z));
    }
}
