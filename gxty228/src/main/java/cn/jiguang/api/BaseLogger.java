package cn.jiguang.api;

import cn.jiguang.e.a;
import cn.jiguang.e.b;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public abstract class BaseLogger {
    private static b b = new a();
    private static final SimpleDateFormat c = new SimpleDateFormat("MM.dd_HH:mm:ss_SSS", Locale.ENGLISH);
    private static ArrayList<String> d = new ArrayList();
    private static boolean e = false;
    private static boolean f = false;
    private String a;

    public BaseLogger() {
        this.a = "";
        this.a = "JIGUANG-" + getCommonTag();
    }

    private static boolean a(int i) {
        return i >= 3;
    }

    public static void flushCached2File() {
    }

    public static void setDelegate(b bVar) {
        b = bVar;
    }

    public void _d(String str, String str2, Object... objArr) {
    }

    public void d(String str, String str2) {
    }

    public void d(String str, String str2, Throwable th) {
    }

    public void dd(String str, String str2) {
        if (cn.jiguang.d.a.b && a(3)) {
            b.b(this.a, "[" + str + "] " + str2);
        }
    }

    public void dd(String str, String str2, Throwable th) {
        if (cn.jiguang.d.a.b && a(3)) {
            b.b(this.a, "[" + str + "] " + str2, th);
        }
    }

    public void e(String str, String str2) {
    }

    public void e(String str, String str2, Throwable th) {
    }

    public void ee(String str, String str2) {
        if (a(6)) {
            b.e(this.a, "[" + str + "] " + str2);
        }
    }

    public void ee(String str, String str2, Throwable th) {
        if (a(6)) {
            b.e(this.a, "[" + str + "] " + str2, th);
        }
    }

    public abstract String getCommonTag();

    public void i(String str, String str2) {
    }

    public void i(String str, String str2, Throwable th) {
    }

    public void ii(String str, String str2) {
        if (cn.jiguang.d.a.b && a(4)) {
            b.c(this.a, "[" + str + "] " + str2);
        }
    }

    public void ii(String str, String str2, Throwable th) {
        if (cn.jiguang.d.a.b && a(4)) {
            b.c(this.a, "[" + str + "] " + str2, th);
        }
    }

    public void v(String str, String str2) {
    }

    public void v(String str, String str2, Throwable th) {
    }

    public void vv(String str, String str2) {
        if (cn.jiguang.d.a.b && a(2)) {
            b.a(this.a, "[" + str + "] " + str2);
        }
    }

    public void vv(String str, String str2, Throwable th) {
        if (cn.jiguang.d.a.b && a(2)) {
            b.a(this.a, "[" + str + "] " + str2, th);
        }
    }

    public void w(String str, String str2) {
    }

    public void w(String str, String str2, Throwable th) {
    }

    public void ww(String str, String str2) {
        if (a(5)) {
            b.d(this.a, "[" + str + "] " + str2);
        }
    }

    public void ww(String str, String str2, Throwable th) {
        if (cn.jiguang.d.a.b && a(5)) {
            b.d(this.a, "[" + str + "] " + str2, th);
        }
    }
}
