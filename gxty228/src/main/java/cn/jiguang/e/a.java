package cn.jiguang.e;

import android.util.Log;

public final class a implements b {
    public static boolean a = true;

    public final void a(String str, String str2) {
        if (a) {
            Log.v(str, str2);
        }
    }

    public final void a(String str, String str2, Throwable th) {
        if (a) {
            Log.v(str, str2, th);
        }
    }

    public final void b(String str, String str2) {
        if (a) {
            Log.d(str, str2);
        }
    }

    public final void b(String str, String str2, Throwable th) {
        if (a) {
            Log.d(str, str2, th);
        }
    }

    public final void c(String str, String str2) {
        if (a) {
            Log.i(str, str2);
        }
    }

    public final void c(String str, String str2, Throwable th) {
        if (a) {
            Log.i(str, str2, th);
        }
    }

    public final void d(String str, String str2) {
        if (a) {
            Log.w(str, str2);
        }
    }

    public final void d(String str, String str2, Throwable th) {
        if (a) {
            Log.w(str, str2, th);
        }
    }

    public final void e(String str, String str2) {
        if (a) {
            Log.e(str, str2);
        }
    }

    public final void e(String str, String str2, Throwable th) {
        if (a) {
            Log.e(str, str2, th);
        }
    }
}
