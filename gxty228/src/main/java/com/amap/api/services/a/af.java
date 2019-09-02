package com.amap.api.services.a;

import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: CoreUtil */
public class af {
    public static String a(Date date) {
        return date != null ? new SimpleDateFormat("HH:mm").format(date) : "";
    }

    public static Date a(String str) {
        Date date = null;
        if (!(str == null || str.trim().equals(""))) {
            try {
                date = new SimpleDateFormat("HH:mm").parse(str);
            } catch (Throwable e) {
                a(e, "CoreUtil", "parseTime");
            }
        }
        return date;
    }

    public static void a(Throwable th, String str, String str2) {
        try {
            p a = p.a();
            if (a != null) {
                a.b(th, str, str2);
            }
            th.printStackTrace();
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }
}
