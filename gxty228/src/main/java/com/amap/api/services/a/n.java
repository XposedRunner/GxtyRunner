package com.amap.api.services.a;

import java.lang.Thread.UncaughtExceptionHandler;

/* compiled from: BasicLogHandler */
public class n {
    protected static n a;
    protected UncaughtExceptionHandler b;

    public static void a(Throwable th, String str, String str2) {
        th.printStackTrace();
        if (a != null) {
            a.a(th, 1, str, str2);
        }
    }

    protected void a(Throwable th, int i, String str, String str2) {
    }
}
