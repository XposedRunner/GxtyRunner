package cn.jiguang.d.g;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import cn.jiguang.api.SdkType;
import cn.jiguang.d.d.e;
import cn.jiguang.d.d.s;
import cn.jiguang.e.d;
import cn.jiguang.g.a;
import cn.jiguang.g.k;
import cn.jiguang.g.l;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public final class b {
    private static volatile b v;
    private static final Object w = new Object();
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public int k;
    public String l;
    public short m;
    public int n;
    public String o;
    public String p;
    public String q;
    public String r;
    private transient AtomicBoolean s = new AtomicBoolean(false);
    private String t;
    private String u;

    private b() {
    }

    private static String a(String str, String str2) {
        return !k.a(str) ? str : str2;
    }

    public static byte c(Context context) {
        ArrayList a = cn.jiguang.d.d.b.a().a(context, SdkType.JPUSH.name(), 19, "platformtype", 1);
        Object obj = a.size() > 0 ? a.get(0) : null;
        byte byteValue = (obj == null || !(obj instanceof Byte)) ? (byte) 0 : ((Byte) obj).byteValue();
        d.a("DeviceInfo", " pluginPlatformType - " + byteValue);
        return byteValue;
    }

    public static b d() {
        if (v == null) {
            synchronized (w) {
                if (v == null) {
                    v = new b();
                }
            }
        }
        return v;
    }

    public static String d(Context context) {
        String str = "";
        ArrayList a = cn.jiguang.d.d.b.a().a(context, SdkType.JPUSH.name(), 19, "platformregid", 1);
        Object obj = a.size() > 0 ? a.get(0) : null;
        String str2 = (obj == null || !(obj instanceof String)) ? str : (String) obj;
        d.a("DeviceInfo", " pluginPlatformRegId - " + str2);
        return str2;
    }

    public final String a() {
        return this.t;
    }

    public final void a(Context context) {
        if (!this.s.get() && context != null) {
            b(context);
            this.s.set(true);
        }
    }

    public final String b() {
        return a(this.b, " ") + "$$" + a(this.c, " ") + "$$" + a(this.d, " ") + "$$" + a(this.e, " ") + "$$" + a(this.j, " ") + "$$" + (this.f + "|" + this.g + "|" + this.h + "|" + this.i) + "$$" + this.k + "$$" + this.l;
    }

    public final void b(Context context) {
        if (context == null) {
            d.h("DeviceInfo", "context is null");
            return;
        }
        String f = a.f(context);
        String e = a.e(context, "");
        if (TextUtils.isEmpty(f)) {
            f = " ";
        }
        if (TextUtils.isEmpty(e)) {
            e = " ";
        }
        this.t = f + "$$" + e + "$$" + context.getPackageName() + "$$" + cn.jiguang.d.a.d.i(context);
        this.a = s.a(context);
        this.m = e.a().c();
        this.b = VERSION.RELEASE + "," + Integer.toString(VERSION.SDK_INT);
        this.c = Build.MODEL;
        this.d = l.a(context, "gsm.version.baseband", "baseband");
        this.e = Build.DEVICE;
        this.j = cn.jiguang.d.a.a.g("");
        cn.jiguang.d.d.b.a();
        this.i = cn.jiguang.d.d.b.d(SdkType.JCORE.name(), "");
        cn.jiguang.d.d.b.a();
        this.g = cn.jiguang.d.d.b.d(SdkType.JANALYTICS.name(), "");
        cn.jiguang.d.d.b.a();
        this.h = cn.jiguang.d.d.b.d(SdkType.JSHARE.name(), "");
        cn.jiguang.d.d.b.a();
        this.f = cn.jiguang.d.d.b.d(SdkType.JPUSH.name(), "");
        this.k = a.i(context) ? 1 : 0;
        this.l = a.a(context);
        this.u = a.d(context, this.u);
        this.o = a.h(context);
        this.n = a.a;
        this.p = a.g(context);
        this.q = a.b(context, " ");
        if (!k.e(this.q)) {
            this.q = " ";
        }
        this.u = a.d(context, " ");
        this.r = Build.SERIAL;
        cn.jiguang.d.a.a.a(this.u, this.p, this.q);
    }

    public final String c() {
        return this.n + "$$" + a(this.o, " ") + "$$" + a(this.u, " ") + "$$" + a(this.p, " ") + "$$" + a(this.q, " ") + "$$" + a("unknown".equalsIgnoreCase(this.r) ? " " : this.r, " ");
    }
}
