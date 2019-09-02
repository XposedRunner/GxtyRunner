package cn.jiguang.d.g;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class a {
    public static String a = "yyyyMMdd_HHmm";

    public static String a() {
        return new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.ENGLISH).format(new Date());
    }

    public static String b() {
        return new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(new Date());
    }
}
