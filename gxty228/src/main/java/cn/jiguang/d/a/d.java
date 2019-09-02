package cn.jiguang.d.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import cn.jiguang.g.b.a;
import cn.jiguang.g.b.e;
import cn.jiguang.g.k;
import java.io.Serializable;
import java.util.Locale;

public final class d {
    private static volatile e a;

    public static int a() {
        return ((Integer) p(null).a("session_id", Integer.valueOf(0))).intValue();
    }

    public static void a(Context context) {
        a = e.a(context, "cn.jpush.android.user.profile");
    }

    public static void a(Context context, int i) {
        p(context).b("session_id", Integer.valueOf(i));
    }

    public static void a(Context context, long j) {
        p(context).b("services_launched_time", Long.valueOf(j));
    }

    public static void a(Context context, long j, String str) {
        p(context).b(new a().a("device_uid", Long.valueOf(j)).a("device_password", str));
        a.j(i(context));
    }

    public static void a(Context context, long j, String str, String str2, String str3) {
        a a = new a().a("device_uid", Long.valueOf(j)).a("device_password", str).a("device_registration_id", str2);
        if (!k.a(str3)) {
            a.a("devcie_id_generated", str3);
        }
        p(context).b(a);
        a.j(i(context));
    }

    public static void a(Context context, a aVar) {
        p(context).b(aVar);
    }

    public static <T extends Serializable> void a(Context context, String str, T t) {
        p(context).b(str, t);
    }

    public static void a(Context context, String str, String str2) {
        p(context).b("sdk_version_" + str, str2);
    }

    public static void a(Context context, String str, String str2, String str3, String str4) {
        p(context).b(new a().a("device_registration_id", (Serializable) str).a("devcie_id_generated", (Serializable) str2).a("device_appkey", (Serializable) str3).a("backup_report_addr", (Serializable) str4));
    }

    public static void a(Context context, boolean z) {
        p(context).b("upload_crash", Boolean.valueOf(z));
    }

    public static void a(String str) {
        p(null).b("backup_report_addr", str);
    }

    public static boolean a(Context context, String str) {
        return p(context).b("devcie_id_generated", str);
    }

    public static a b(Context context, a aVar) {
        return p(context).a(aVar);
    }

    public static <T extends Serializable> T b(Context context, String str, T t) {
        return p(context).a(str, (Serializable) t);
    }

    public static String b(Context context) {
        return (String) p(context).a("device_registration_id", (Serializable) "");
    }

    public static String b(Context context, String str) {
        return (String) p(context).a("sdk_version_" + str, (Serializable) "");
    }

    public static void b(Context context, long j) {
        p(context).b("whitelist_wakeup_time", Long.valueOf(j));
    }

    public static void b(Context context, boolean z) {
        p(context).b("is_tcp_close", Boolean.valueOf(z));
    }

    public static void b(String str) {
        p(null).b("last_location", str);
    }

    public static boolean b() {
        return ((Boolean) p(null).a("upload_crash", Boolean.valueOf(true))).booleanValue();
    }

    public static String c() {
        return (String) p(null).a("backup_report_addr", (Serializable) "");
    }

    public static String c(Context context) {
        return (String) p(context).a("devcie_id_generated", (Serializable) "");
    }

    public static void c(Context context, String str) {
        p(context).b("analytics_account_id", str);
    }

    public static void c(Context context, boolean z) {
        p(context).b("power_save_is_enbale", Boolean.valueOf(z));
    }

    public static long d(Context context) {
        return ((Long) p(context).a("device_uid", Long.valueOf(0))).longValue();
    }

    public static boolean d() {
        if (!cn.jiguang.d.b.a.c()) {
            cn.jiguang.d.b.d.a();
            return cn.jiguang.d.b.d.d();
        } else if (cn.jiguang.g.a.a.c()) {
            try {
                return cn.jiguang.g.a.a.b().a();
            } catch (Exception e) {
            }
        } else {
            if (cn.jiguang.d.a.e != null) {
                cn.jiguang.d.a.a(cn.jiguang.d.a.e, true);
            }
            return false;
        }
    }

    public static boolean e(Context context) {
        if (d(context) <= 0) {
            cn.jiguang.e.d.a("MultiCommConfigs", "isValidRegistered uid <= 0");
            return false;
        } else if (TextUtils.isEmpty(b(context))) {
            cn.jiguang.e.d.a("MultiCommConfigs", "isValidRegistered regId is empty");
            return false;
        } else {
            CharSequence i = i(context);
            if (TextUtils.isEmpty(i)) {
                cn.jiguang.e.d.a("MultiCommConfigs", "isValidRegistered local appKey is empty");
                return false;
            }
            CharSequence v = a.v();
            if (TextUtils.isEmpty(v)) {
                cn.jiguang.e.d.a("MultiCommConfigs", "isValidRegistered registeredAppKey is empty");
                return false;
            } else if (TextUtils.equals(i, v)) {
                return true;
            } else {
                cn.jiguang.e.d.a("MultiCommConfigs", "isValidRegistered registeredAppKey is not same with local appkey");
                return false;
            }
        }
    }

    public static String f(Context context) {
        return (String) p(context).a("device_password", (Serializable) "");
    }

    public static void g(Context context) {
        p(context).b(new a().a("device_uid", Long.valueOf(0)).a("device_password", "").a("device_registration_id", "").a("devcie_id_generated", ""));
        a.j(null);
    }

    public static void h(Context context) {
        p(null).b(new a().a("device_uid", Long.valueOf(0)).a("device_password", "").a("device_registration_id", ""));
    }

    public static String i(Context context) {
        if (TextUtils.isEmpty(cn.jiguang.d.a.g)) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                    String string = applicationInfo.metaData.getString("JPUSH_APPKEY");
                    cn.jiguang.d.a.g = string;
                    cn.jiguang.d.a.g = string.toLowerCase(Locale.getDefault());
                }
            } catch (Throwable th) {
            }
        }
        return cn.jiguang.d.a.g;
    }

    public static boolean j(Context context) {
        return ((Boolean) p(context).a("is_tcp_close", Boolean.valueOf(false))).booleanValue();
    }

    public static long k(Context context) {
        return ((Long) p(context).a("services_launched_time", Long.valueOf(-1))).longValue();
    }

    public static long l(Context context) {
        return ((Long) p(context).a("whitelist_wakeup_time", Long.valueOf(-1))).longValue();
    }

    public static String m(Context context) {
        return (String) p(context).a("last_location", (Serializable) "");
    }

    public static String n(Context context) {
        return (String) p(context).a("analytics_account_id", (Serializable) "");
    }

    public static boolean o(Context context) {
        return ((Boolean) p(context).a("power_save_is_enbale", Boolean.valueOf(false))).booleanValue();
    }

    private static e p(Context context) {
        if (a == null) {
            a(context);
        }
        return a;
    }
}
