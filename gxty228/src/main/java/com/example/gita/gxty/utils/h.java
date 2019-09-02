package com.example.gita.gxty.utils;

import android.util.Log;

/* compiled from: Logger */
public class h {
    public static boolean a = false;
    private static h b = new h();

    private h() {
    }

    public static void a(Object obj) {
        if (a) {
            String a = a();
            if (a != null) {
                Log.i("[gxjy]", a + " - " + obj);
            } else {
                Log.i("[gxjy]", obj != null ? obj.toString() : "");
            }
        }
    }

    public static void a(Exception exception) {
        if (!a) {
            return;
        }
        if (exception == null) {
            Log.e("[gxjy]", "");
        } else {
            Log.e("[gxjy]", "error", exception);
        }
    }

    public static void b(Object obj) {
        if (a) {
            String a = a();
            if (a != null) {
                Log.e("[gxjy]", a + " - " + obj);
            } else {
                Log.e("[gxjy]", obj != null ? obj.toString() : "");
            }
        }
    }

    private static String a() {
        if (b == null) {
            b = new h();
        }
        return b.b();
    }

    private String b() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(getClass().getName())) {
                return "[ " + Thread.currentThread().getName() + ": " + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + " " + stackTraceElement.getMethodName() + " ]";
            }
        }
        return null;
    }

    public static void c(Object obj) {
        if (a) {
            String a = a();
            if (a != null) {
                Log.i("[gxjy]", a + " - " + obj);
            } else {
                Log.i("[gxjy]", obj != null ? obj.toString() : "");
            }
        }
    }

    public static void d(Object obj) {
        if (a) {
            String a = a();
            if (a != null) {
                Log.i("[gxjy]", a + " - " + obj);
            } else {
                Log.i("[gxjy]", obj != null ? obj.toString() : "");
            }
        }
    }

    public static void e(Object obj) {
        if (a) {
            String a = a();
            if (a != null) {
                Log.w("[gxjy]", a + " - " + obj);
            } else {
                Log.w("[gxjy]", obj != null ? obj.toString() : "");
            }
        }
    }

    public static void a(String str, Object obj) {
        if (a) {
            String a = a();
            if (a != null) {
                Log.w(str, a + " - " + obj);
            } else {
                Log.w(str, obj != null ? obj.toString() : "");
            }
        }
    }
}
