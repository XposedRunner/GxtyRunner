package cn.jiguang.g.a;

import android.os.Bundle;
import android.os.IBinder;
import cn.jiguang.b.b;
import cn.jiguang.d.d.j;
import cn.jiguang.e.d;

public final class a extends b {
    private static boolean a = false;
    private static cn.jiguang.b.a b;

    public static void a(cn.jiguang.b.a aVar) {
        b = aVar;
        a = false;
    }

    public static cn.jiguang.b.a b() {
        return b;
    }

    public static boolean c() {
        return b != null;
    }

    public static void d() {
        b = null;
        a = false;
    }

    public static boolean e() {
        return a;
    }

    public static void f() {
        a = true;
    }

    public final IBinder a(String str, String str2) {
        cn.jiguang.d.d.b.a();
        return cn.jiguang.d.d.b.c(str, str2);
    }

    public final void a(String str, Bundle bundle) {
        if (str != null && bundle != null) {
            try {
                j.a().a(cn.jiguang.d.a.e, str, bundle);
            } catch (Throwable th) {
                d.h("DataShare", "onAction error:" + th.getMessage());
            }
        }
    }

    public final boolean a() {
        d.a("DataShare", "pushLogin status by aidl");
        cn.jiguang.d.b.d.a();
        return cn.jiguang.d.b.d.d();
    }
}
