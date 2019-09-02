package cn.jiguang.d;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import cn.jiguang.api.BasePreferenceManager;
import cn.jiguang.api.SdkType;
import cn.jiguang.c.b;
import cn.jiguang.d.a.d;
import cn.jiguang.d.a.f;
import cn.jiguang.d.d.c;
import cn.jiguang.d.d.e;
import cn.jiguang.d.d.o;
import cn.jiguang.d.d.t;
import cn.jiguang.g.k;
import cn.jiguang.service.Protocol;
import cn.jpush.android.service.PushService;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public final class a {
    public static final String a = SdkType.JCORE.name();
    public static boolean b = false;
    public static String c;
    public static String d;
    public static Context e;
    public static final b f = new cn.jiguang.c.a();
    public static String g;
    public static int h;
    public static String i;
    public static boolean j = false;
    public static boolean k = false;
    public static boolean l = false;
    private static AtomicBoolean m = new AtomicBoolean(false);
    private static ServiceConnection n = new b();

    public static void a(Context context, boolean z) {
        if (z && d.o(context)) {
            cn.jiguang.e.d.c("JCoreGlobal", "enable save power,not re bind service");
        } else if (cn.jiguang.g.a.a.c()) {
            cn.jiguang.e.d.c("JCoreGlobal", "PushService has been bind, give up now");
        } else if (cn.jiguang.g.a.a.e()) {
            cn.jiguang.e.d.c("JCoreGlobal", "is binding service");
        } else {
            Intent intent = new Intent();
            intent.setClass(context, PushService.class);
            try {
                if (context.bindService(intent, n, 1)) {
                    cn.jiguang.e.d.a("JCoreGlobal", "Remote Service on binding...");
                    cn.jiguang.g.a.a.f();
                    return;
                }
                cn.jiguang.e.d.a("JCoreGlobal", "Remote Service bind failed");
            } catch (SecurityException e) {
                cn.jiguang.e.d.h("JCoreGlobal", "Remote Service bind failed caused by SecurityException!");
            }
        }
    }

    private static boolean a() {
        int GetSdkVersion;
        UnsatisfiedLinkError e;
        try {
            GetSdkVersion = Protocol.GetSdkVersion();
            try {
                cn.jiguang.e.d.a("JCoreGlobal", "soVersion:" + GetSdkVersion);
            } catch (UnsatisfiedLinkError e2) {
                e = e2;
                cn.jiguang.e.d.j("JCoreGlobal", "Get sdk version fail![获取sdk版本失败!]");
                e.printStackTrace();
                return GetSdkVersion < 100;
            }
        } catch (UnsatisfiedLinkError e3) {
            e = e3;
            GetSdkVersion = 0;
            cn.jiguang.e.d.j("JCoreGlobal", "Get sdk version fail![获取sdk版本失败!]");
            e.printStackTrace();
            if (GetSdkVersion < 100) {
            }
        }
        if (GetSdkVersion < 100) {
        }
    }

    public static synchronized boolean a(Context context) {
        boolean z = true;
        synchronized (a.class) {
            l = k;
            k = true;
            if (!m.get()) {
                cn.jiguang.e.d.c("JCoreGlobal", "action:init - Service");
                c = context.getPackageName();
                Context applicationContext = context.getApplicationContext();
                e = applicationContext;
                cn.jiguang.d.a.a.a(applicationContext);
                d.a(e);
                f.a(e);
                BasePreferenceManager.init(context.getApplicationContext());
                if (!b(context)) {
                    z = false;
                } else if (cn.jiguang.g.a.q(e)) {
                    e.a();
                    cn.jiguang.a.a.c.e.a().a(context.getApplicationContext());
                    t.a(context);
                    cn.jiguang.e.d.d("JCoreGlobal", "action:init - sdkVersion:1.1.9, buildId:172");
                    if (a()) {
                        ApplicationInfo c = c(context);
                        if (c == null) {
                            cn.jiguang.e.d.j("JCoreGlobal", "JCore cannot be initialized completely due to NULL appInfo.");
                            z = false;
                        } else {
                            String str;
                            try {
                                d = context.getPackageManager().getApplicationLabel(c).toString();
                            } catch (Throwable th) {
                                cn.jiguang.e.d.d("JCoreGlobal", "get mApplicationName error:" + th.getMessage());
                            }
                            try {
                                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                                h = packageInfo.versionCode;
                                str = packageInfo.versionName;
                                i = str;
                                if (str.length() > 30) {
                                    i = i.substring(0, 30);
                                }
                            } catch (Throwable th2) {
                                cn.jiguang.e.d.d("JCoreGlobal", "NO versionCode or versionName defined in manifest.");
                            }
                            str = cn.jiguang.d.a.a.v();
                            if (k.a(str) || "null".equals(str) || !str.equalsIgnoreCase(d.i(context))) {
                                cn.jiguang.e.d.d("ServiceHelper", "We found the appKey is changed or register appkey is empty. Will re-register.");
                                d.g(context);
                                o.a(context);
                            }
                            if (VERSION.SDK_INT == 8) {
                                System.setProperty("java.net.preferIPv4Stack", "true");
                                System.setProperty("java.net.preferIPv6Addresses", "false");
                            }
                            m.set(true);
                            cn.jiguang.d.b.a.b();
                            if (cn.jiguang.g.a.p(e)) {
                                a(e, false);
                            }
                        }
                    } else {
                        cn.jiguang.e.d.j("JCoreGlobal", "JCore .so file do not match JCore .jar file in the project, Failed to init JCore");
                        z = false;
                    }
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    private static boolean b(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                Bundle bundle = applicationInfo.metaData;
                if (bundle != null) {
                    String string = bundle.getString("JPUSH_APPKEY");
                    if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(g) && g.equals(string)) {
                        return g.length() == 24;
                    } else {
                        g = string;
                        if (k.a(string)) {
                            cn.jiguang.e.d.j("JCoreGlobal", "errorcode:10001,metadata: JCore appKey - not defined in manifest");
                            c.a(e, 10001, false);
                            cn.jiguang.d.a.a.a(context, 10001);
                            return false;
                        } else if (g.length() != 24) {
                            cn.jiguang.e.d.j("JCoreGlobal", "errorcode:1008,Invalid appKey : " + g + ", Please get your Appkey from JIGUANG web console!");
                            c.a(e, PointerIconCompat.TYPE_TEXT, false);
                            cn.jiguang.d.a.a.a(context, (int) PointerIconCompat.TYPE_TEXT);
                            return false;
                        } else {
                            g = g.toLowerCase(Locale.getDefault());
                            cn.jiguang.e.d.d("JCoreGlobal", "metadata: appKey - " + g);
                            String c = k.c(bundle.getString("JPUSH_CHANNEL"));
                            if (k.a(c)) {
                                cn.jiguang.e.d.d("JCoreGlobal", "metadata: channel - not defined in manifest");
                                return true;
                            }
                            cn.jiguang.e.d.d("JCoreGlobal", "metadata: channel - " + c);
                            cn.jiguang.d.a.a.h(c);
                            return true;
                        }
                    }
                }
                cn.jiguang.e.d.d("JCoreGlobal", "NO meta data defined in manifest.");
                return false;
            }
            cn.jiguang.e.d.d("JCoreGlobal", "metadata: Can not get metaData from ApplicationInfo");
            return false;
        } catch (Throwable th) {
            cn.jiguang.e.d.d("JCoreGlobal", "Unexpected: failed to get current application info", th);
            return false;
        }
    }

    private static ApplicationInfo c(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        } catch (Throwable th) {
            cn.jiguang.e.d.f("JCoreGlobal", "Unexpected: failed to get current application info", th);
            return null;
        }
    }
}
