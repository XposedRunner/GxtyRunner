package cn.jiguang.d.a;

import android.content.Context;
import android.content.SharedPreferences;
import cn.jiguang.api.BasePreferenceManager;
import cn.jiguang.api.MultiSpHelper;
import cn.jiguang.g.b.e;
import cn.jiguang.g.k;
import cn.jpush.android.api.JPushInterface;
import com.blankj.utilcode.constant.CacheConstants;
import java.io.Serializable;

public final class a {
    public static String a = "";
    public static int b = -1;
    public static boolean c = false;
    private static e d;

    public static long a(long j) {
        return (u() + j) / 1000;
    }

    public static void a(int i) {
        MultiSpHelper.commitInt(cn.jiguang.d.a.e, "idc", i);
    }

    public static void a(Context context) {
        d = e.a(context, BasePreferenceManager.JPUSH_PREF);
    }

    public static void a(Context context, int i) {
        d.a(context, "jpush_register_code", Integer.valueOf(i));
    }

    public static void a(Context context, String str) {
        f(context).b("push_udid", str);
    }

    public static void a(Context context, String str, String str2) {
        d.a(context, str, cn.jiguang.d.g.a.a.a(str2));
    }

    public static void a(String str, int i) {
        f(null).b(new cn.jiguang.g.b.a().a("last_good_conn_ip", str).a("last_good_conn_port", i));
    }

    public static void a(String str, long j) {
        f(null).b(str, Long.valueOf(j));
    }

    public static void a(String str, String str2, String str3) {
        f(null).b(new cn.jiguang.g.b.a().a("device_main_imei", (Serializable) str).a("device_main_android_id", (Serializable) str2).a("device_main_mac", (Serializable) str3));
    }

    public static void a(boolean z) {
        f(null).b("sis_report_switch", Boolean.valueOf(z));
    }

    public static synchronized boolean a() {
        boolean z;
        synchronized (a.class) {
            long longValue = ((Long) f(null).a("last_report_index", Long.valueOf(0))).longValue();
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - longValue > LogBuilder.MAX_INTERVAL) {
                f(null).b("last_report_index", Long.valueOf(currentTimeMillis));
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public static boolean a(String str) {
        if (str == null) {
            return true;
        }
        if (str.equalsIgnoreCase((String) f(null).a("last_connection_type", null))) {
            return false;
        }
        f(null).b("last_connection_type", str);
        return true;
    }

    public static long b() {
        return ((Long) f(null).a("last_report_index", Long.valueOf(0))).longValue();
    }

    public static String b(Context context) {
        return (String) f(context).a("push_udid", (Serializable) "");
    }

    public static String b(Context context, String str, String str2) {
        String str3 = (String) d.b(context, str, "");
        return k.a(str3) ? str2 : cn.jiguang.d.g.a.a.b(str3, str2);
    }

    private static void b(int i) {
        f(null).b("heartbeat_interval", Integer.valueOf(i));
    }

    public static void b(long j) {
        if (j > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            b.a(j, currentTimeMillis);
            f(null).b(new cn.jiguang.g.b.a().a("login_server_time", j).a("login_local_time", currentTimeMillis));
        }
    }

    public static void b(Context context, String str) {
        f(context).b("imei", str);
    }

    public static void b(String str) {
        f(null).b("backup_user_addr", str);
    }

    public static void b(String str, int i) {
        f(null).b(new cn.jiguang.g.b.a().a("default_conn_ip", str).a("default_conn_port", i));
    }

    public static String c(Context context) {
        return (String) f(context).a("imei", (Serializable) "");
    }

    public static void c() {
        f(null).b(new cn.jiguang.g.b.a().a("last_good_sis", null).a("last_good_sis_address", null).a("default_sis", null).a("last_good_conn_ip", null).a("last_good_conn_port", null).a("last_sis_request_time", Long.valueOf(0)).a("device_registered_appkey", null));
        d.h(null);
    }

    public static void c(Context context, String str) {
        f(context).b("udp_report_device_info", str);
    }

    public static void c(String str) {
        f(null).b("last_good_sis", str);
    }

    public static String d() {
        return (String) f(null).a("last_good_conn_ip", null);
    }

    public static void d(Context context) {
        a(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("cn.jpush.serverconfig", 0);
        f(context).b(new cn.jiguang.g.b.a().a("device_main_ids", sharedPreferences.getString("register_device_info", "")).a("device_main_imei", sharedPreferences.getString("register_device_imei", "")).a("device_main_android_id", sharedPreferences.getString("register_device_android_id", "")).a("device_main_mac", sharedPreferences.getString("register_device_mac", "")).a("last_connection_type", sharedPreferences.getString("jpush_sis_nettype", "")).a("last_good_sis", sharedPreferences.getString("jpush_sis_receiver_string", "")).a("default_conn_ip", sharedPreferences.getString("mIP", "")).a("last_good_conn_ip", sharedPreferences.getString("jpush_conn_mip", "")).a("push_udid", sharedPreferences.getString("push_udid", "")).a("imei", sharedPreferences.getString("imei", "")).a("default_conn_port", sharedPreferences.getInt("mPort", 0)).a("last_good_conn_port", sharedPreferences.getInt("jpush_conn_mport", 0)).a("login_local_time", sharedPreferences.getLong("lctime", 0)).a("login_server_time", sharedPreferences.getLong("login_server_time", 0)));
        d.a(context, new cn.jiguang.g.b.a().a("last_report_device_info", sharedPreferences.getLong("dev_info_rep_time", 0)).a("last_report_location", sharedPreferences.getLong("login_report_time", 0)).a("setting_silence_push_time", sharedPreferences.getString("silencePushTime", "")).a("setting_push_time", sharedPreferences.getString("pushtime", "")).a("notification_num", sharedPreferences.getInt("notifaction_num", 5)).a("service_stoped", sharedPreferences.getInt("service_stoped", 0)));
        d.a(context, sharedPreferences.getString("registration_id", ""), sharedPreferences.getString("JPush_DeviceId", ""), sharedPreferences.getString(JPushInterface.EXTRA_APP_KEY, ""), sharedPreferences.getString("http_report_sis_url", ""));
    }

    public static void d(String str) {
        f(null).b("default_sis", str);
    }

    public static int e() {
        return ((Integer) f(null).a("last_good_conn_port", Integer.valueOf(0))).intValue();
    }

    public static String e(Context context) {
        return (String) f(context).a("udp_report_device_info", (Serializable) "");
    }

    public static void e(String str) {
        f(null).b("sis_report_history", str);
    }

    private static e f(Context context) {
        if (d == null) {
            a(context);
        }
        return d;
    }

    public static void f(String str) {
        f(null).b("last_good_sis_address", str);
    }

    public static boolean f() {
        return System.currentTimeMillis() - ((Long) f(null).a("last_sis_request_time", Long.valueOf(0))).longValue() > 180000;
    }

    public static String g(String str) {
        return (String) f(null).a("device_channel", (Serializable) str);
    }

    public static void g() {
        f(null).b("last_sis_request_time", Long.valueOf(System.currentTimeMillis()));
    }

    public static synchronized long h() {
        long a;
        synchronized (a.class) {
            a = f.a();
        }
        return a;
    }

    public static void h(String str) {
        f(null).b("device_channel", str);
    }

    public static int i() {
        return ((Integer) f(null).a("heartbeat_interval", Integer.valueOf(290))).intValue();
    }

    public static void i(String str) {
        f(null).b("sdk_version", str);
    }

    public static void j() {
        b(290);
    }

    public static void j(String str) {
        f(null).b("device_registered_appkey", str);
    }

    public static long k(String str) {
        return ((Long) f(null).a(str, Long.valueOf(0))).longValue();
    }

    public static void k() {
        b((int) CacheConstants.DAY);
    }

    public static boolean l() {
        return i() == CacheConstants.DAY;
    }

    public static String m() {
        return (String) f(null).a("last_good_sis", null);
    }

    public static String n() {
        return (String) f(null).a("default_sis", null);
    }

    public static String o() {
        return (String) f(null).a("sis_report_history", (Serializable) "");
    }

    public static String p() {
        return (String) f(null).a("last_good_sis_address", null);
    }

    public static boolean q() {
        return ((Boolean) f(null).a("sis_report_switch", Boolean.valueOf(false))).booleanValue();
    }

    public static String r() {
        return (String) f(null).a("foo001", null);
    }

    public static String s() {
        return (String) f(null).a("sdk_version", (Serializable) "");
    }

    public static long t() {
        return a(System.currentTimeMillis());
    }

    public static long u() {
        if (b.a()) {
            return b.b();
        }
        long longValue = ((Long) f(null).a("login_local_time", Long.valueOf(0))).longValue();
        long longValue2 = ((Long) f(null).a("login_server_time", Long.valueOf(0))).longValue();
        if (longValue == 0 || longValue2 == 0) {
            return 0;
        }
        b.a(longValue2, longValue);
        return longValue2 - longValue;
    }

    public static String v() {
        return (String) f(null).a("device_registered_appkey", null);
    }

    public static c w() {
        return new c((String) f(null).a("device_main_imei", (Serializable) ""), (String) f(null).a("device_main_android_id", (Serializable) ""), (String) f(null).a("device_main_mac", (Serializable) ""));
    }

    public static boolean x() {
        return ((Boolean) d.b(null, "is_im_logged_in", Boolean.valueOf(false))).booleanValue();
    }

    public static int y() {
        return ((Integer) d.b(null, "im_login_count", Integer.valueOf(-1))).intValue();
    }
}
