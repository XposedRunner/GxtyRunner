package cn.jiguang.a.c;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.a.a.b.f;
import cn.jiguang.a.a.c.g;
import cn.jiguang.a.a.c.i;
import cn.jiguang.a.b.b;
import cn.jiguang.d.d.o;
import cn.jiguang.d.g.e;
import cn.jiguang.e.d;
import cn.jiguang.g.a;
import cn.jiguang.net.HttpUtils;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class c {
    private static int a = 20480;

    public static void a(Context context) {
        if (context == null) {
            d.g("ReportHelper", "context did not init, return");
            return;
        }
        JSONArray b = cn.jiguang.a.a.c.d.b(context);
        if (b != null && b.length() != 0) {
            int length = b.length();
            int length2 = b.toString().length();
            d.e("ReportHelper", "package info total num - " + length + ", data size: - " + length2);
            JSONObject a;
            if (length2 <= a) {
                a = a.a("app_list", b);
                if (a != null && a.length() > 0) {
                    o.a(context, a);
                    return;
                }
                return;
            }
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            while (i < length) {
                try {
                    jSONArray.put(b.getJSONObject(i));
                } catch (JSONException e) {
                    d.i("ReportHelper", "Got JSONException when report applist.");
                }
                if (jSONArray.toString().length() > a || length - 1 == i) {
                    a = a.a("app_list", jSONArray);
                    if (a != null && a.length() > 0) {
                        o.a(context, a);
                    }
                    jSONArray = new JSONArray();
                }
                i++;
            }
        }
    }

    public static void a(Context context, int i) {
        d.f("ReportHelper", "periodTasks...");
        if (cn.jiguang.d.a.d.e(context)) {
            long longValue;
            Object obj;
            long longValue2;
            boolean z;
            boolean booleanValue;
            boolean booleanValue2;
            int i2;
            int i3;
            boolean z2;
            b bVar;
            cn.jiguang.g.b.a b = cn.jiguang.d.a.d.b(context, new cn.jiguang.g.b.a().a("last_report_device_info", Long.valueOf(0)).a("lbs_report_enable", Boolean.valueOf(true)).a("last_collection_location", Long.valueOf(0)).a("location_report_delay", Long.valueOf(0)).a("location_collect_frequency", Long.valueOf(900000)).a("last_check_userapp_status", Long.valueOf(0)).a("nb_upload", Boolean.valueOf(false)).a("nb_lasttime", Long.valueOf(0)).a("app_running_collect_enable", Boolean.valueOf(true)).a("app_running_collect_interval", Long.valueOf(3600000)).a("app_running_last_collect_time", Long.valueOf(0)).a("app_running_collect_app_type", Integer.valueOf(1)).a("app_running_collect_process_type", Integer.valueOf(1)));
            cn.jiguang.g.b.a aVar = new cn.jiguang.g.b.a();
            long currentTimeMillis = System.currentTimeMillis();
            boolean z3 = currentTimeMillis - ((Long) b.b("last_report_device_info", Long.valueOf(0))).longValue() > LogBuilder.MAX_INTERVAL;
            boolean booleanValue3 = ((Boolean) b.b("lbs_report_enable", Boolean.valueOf(true))).booleanValue();
            if (booleanValue3) {
                long longValue3 = ((Long) b.b("last_collection_location", Long.valueOf(0))).longValue();
                long longValue4 = ((Long) b.b("location_collect_frequency", Long.valueOf(900000))).longValue();
                if (longValue3 <= 0) {
                    longValue = ((Long) b.b("location_report_delay", Long.valueOf(0))).longValue();
                    if (longValue > 0) {
                        longValue3 = (currentTimeMillis + longValue) - longValue4;
                        aVar.a("last_collection_location", Long.valueOf(longValue3));
                    }
                }
                d.c("JAnalyticsCommonConfigs", "isCollectionLocationNeeded last:" + longValue3 + ",now:" + currentTimeMillis);
                if (currentTimeMillis - longValue3 >= longValue4) {
                    aVar.a("last_collection_location", Long.valueOf(currentTimeMillis));
                    obj = 1;
                    longValue2 = ((Long) b.b("last_check_userapp_status", Long.valueOf(0))).longValue();
                    z = false;
                    d.c("JAnalyticsCommonConfigs", "now - appListLastReportTime:" + currentTimeMillis + "-" + longValue2 + HttpUtils.EQUAL_SIGN + (currentTimeMillis - longValue2));
                    if (currentTimeMillis - longValue2 > 3600000) {
                        aVar.a("last_check_userapp_status", Long.valueOf(currentTimeMillis));
                        z = true;
                    }
                    booleanValue = ((Boolean) b.b("nb_upload", Boolean.valueOf(false))).booleanValue();
                    if (booleanValue) {
                        d.a("JAnalyticsCommonConfigs", "can not allow get mobile phone number");
                    } else {
                        booleanValue = a.d(context).toUpperCase().startsWith("WIFI");
                        if (booleanValue) {
                            booleanValue = currentTimeMillis - ((Long) b.b("nb_lasttime", Long.valueOf(0))).longValue() <= 3600000;
                        }
                    }
                    booleanValue2 = ((Boolean) b.b("app_running_collect_enable", Boolean.valueOf(true))).booleanValue();
                    i2 = 1;
                    i3 = 1;
                    if (booleanValue2) {
                        longValue = ((Long) b.b("app_running_last_collect_time", Long.valueOf(0))).longValue();
                        if (longValue <= 0) {
                            booleanValue2 = false;
                            aVar.a("app_running_last_collect_time", Long.valueOf(currentTimeMillis));
                        } else if (currentTimeMillis - longValue < ((Long) b.b("app_running_collect_interval", Long.valueOf(3600000))).longValue()) {
                            i2 = ((Integer) b.b("app_running_collect_app_type", Integer.valueOf(1))).intValue();
                            i3 = ((Integer) b.b("app_running_collect_process_type", Integer.valueOf(1))).intValue();
                            booleanValue2 = i2 == 0 || i3 != 0;
                            if (booleanValue2) {
                                aVar.a("app_running_last_collect_time", Long.valueOf(currentTimeMillis));
                            }
                        } else {
                            booleanValue2 = false;
                        }
                    }
                    cn.jiguang.d.a.d.a(context, aVar);
                    z2 = booleanValue3 && obj != null;
                    bVar = new b(z3, z2, z, booleanValue, booleanValue2, i2, i3);
                    if (bVar.a) {
                        cn.jiguang.a.a.c.b.c(context);
                    }
                    if (cn.jiguang.d.a.a.a()) {
                        e.a(context);
                    }
                    if (bVar.b) {
                        a(context, false);
                    }
                    if (bVar.c) {
                        a(context, null);
                    }
                    if (bVar.d) {
                        i.a(context);
                    }
                    if (a.c(context) && a.d(context).toUpperCase().startsWith("WIFI")) {
                        cn.jiguang.a.a.a.c.a(context);
                    }
                    if (bVar.e) {
                        cn.jiguang.g.d.a(context, bVar.f, bVar.g);
                    }
                    g.a(context, i);
                    return;
                }
            }
            obj = null;
            longValue2 = ((Long) b.b("last_check_userapp_status", Long.valueOf(0))).longValue();
            z = false;
            d.c("JAnalyticsCommonConfigs", "now - appListLastReportTime:" + currentTimeMillis + "-" + longValue2 + HttpUtils.EQUAL_SIGN + (currentTimeMillis - longValue2));
            if (currentTimeMillis - longValue2 > 3600000) {
                aVar.a("last_check_userapp_status", Long.valueOf(currentTimeMillis));
                z = true;
            }
            booleanValue = ((Boolean) b.b("nb_upload", Boolean.valueOf(false))).booleanValue();
            if (booleanValue) {
                d.a("JAnalyticsCommonConfigs", "can not allow get mobile phone number");
            } else {
                booleanValue = a.d(context).toUpperCase().startsWith("WIFI");
                if (booleanValue) {
                    if (currentTimeMillis - ((Long) b.b("nb_lasttime", Long.valueOf(0))).longValue() <= 3600000) {
                    }
                    booleanValue = currentTimeMillis - ((Long) b.b("nb_lasttime", Long.valueOf(0))).longValue() <= 3600000;
                }
            }
            booleanValue2 = ((Boolean) b.b("app_running_collect_enable", Boolean.valueOf(true))).booleanValue();
            i2 = 1;
            i3 = 1;
            if (booleanValue2) {
                longValue = ((Long) b.b("app_running_last_collect_time", Long.valueOf(0))).longValue();
                if (longValue <= 0) {
                    booleanValue2 = false;
                    aVar.a("app_running_last_collect_time", Long.valueOf(currentTimeMillis));
                } else if (currentTimeMillis - longValue < ((Long) b.b("app_running_collect_interval", Long.valueOf(3600000))).longValue()) {
                    booleanValue2 = false;
                } else {
                    i2 = ((Integer) b.b("app_running_collect_app_type", Integer.valueOf(1))).intValue();
                    i3 = ((Integer) b.b("app_running_collect_process_type", Integer.valueOf(1))).intValue();
                    if (i2 == 0) {
                    }
                    if (booleanValue2) {
                        aVar.a("app_running_last_collect_time", Long.valueOf(currentTimeMillis));
                    }
                }
            }
            cn.jiguang.d.a.d.a(context, aVar);
            if (!booleanValue3) {
            }
            bVar = new b(z3, z2, z, booleanValue, booleanValue2, i2, i3);
            if (bVar.a) {
                cn.jiguang.a.a.c.b.c(context);
            }
            if (cn.jiguang.d.a.a.a()) {
                e.a(context);
            }
            if (bVar.b) {
                a(context, false);
            }
            if (bVar.c) {
                a(context, null);
            }
            if (bVar.d) {
                i.a(context);
            }
            try {
                cn.jiguang.a.a.a.c.a(context);
            } catch (Throwable th) {
                d.i("ReportHelper", "reportMacListInfo eroor." + th.getMessage());
            }
            if (bVar.e) {
                cn.jiguang.g.d.a(context, bVar.f, bVar.g);
            }
            g.a(context, i);
            return;
        }
        d.c("ReportHelper", "periodTasks failed,isValidRegistered:false");
    }

    public static void a(Context context, String str) {
        if (!cn.jiguang.d.a.d.e(context)) {
            d.g("ReportHelper", "Unexpected! JCore hasn't been register, give up this report");
        } else if (TextUtils.isEmpty(cn.jiguang.a.a.c.a.a(context))) {
            a(context);
            cn.jiguang.a.a.c.a.a(context, cn.jiguang.a.a.c.d.a(context, true));
        } else {
            d.c("ReportHelper", "Report User Apps Status");
            new cn.jiguang.a.a.c.a(context, str).start();
        }
    }

    public static void a(Context context, JSONObject jSONObject) {
        if (!cn.jiguang.d.a.d.e(context)) {
            d.g("ReportHelper", "Unexpected! JCore hasn't been register, give up this report");
        } else if (context == null) {
            throw new IllegalArgumentException("NULL context");
        } else if (jSONObject != null && jSONObject.length() > 0) {
            o.a(context, jSONObject);
            d.c("ReportHelper", "action:reportOperation - content:" + jSONObject.toString());
        }
    }

    public static void a(Context context, boolean z) {
        d.c("ReportHelper", "Report Location info");
        try {
            f.a(context, false);
        } catch (Throwable th) {
            d.i("ReportHelper", "reportLocationInfo failed error:" + th);
        }
    }

    public static void b(Context context) {
        d.a("ReportHelper", "action:reportPermissionInfo");
        String[] a = cn.jiguang.a.a.c.d.a(context);
        if (a == null || a.length == 0) {
            d.i("ReportHelper", "Can not get any pemission");
            return;
        }
        int length = a.length;
        String str = "[";
        String i = cn.jiguang.d.a.d.i(context);
        long d = cn.jiguang.d.a.d.d(context);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            String str2 = a[i2];
            str = i3 == 0 ? str + "\"" + str2 + "\"" : str + ",\"" + str2 + "\"";
            int i5 = i2 + 1;
            i2 = i3 + 1;
            if (i2 >= 50 || str.length() > 1000 || i5 == length) {
                String str3 = str + "]";
                str3 = String.format(Locale.ENGLISH, "{\"total\":%d,\"page\":%d,\"senderid\":\"%s\",\"uid\":%s,\"permission_list\":%s}", new Object[]{Integer.valueOf(length), Integer.valueOf(i4), i, Long.valueOf(d), str3});
                d.c("ReportHelper", str3);
                o.a(context, a.a("android_permissions", str3));
                str = "[";
                i4++;
                i2 = 0;
            }
            i3 = i2;
            i2 = i5;
        }
    }

    public static void b(Context context, int i) {
        g.a(context, i);
    }

    public static void c(Context context) {
        a(context, 0);
        try {
            cn.jiguang.d.h.f.a().a(context, false);
        } catch (OutOfMemoryError e) {
            d.g("ReportHelper", "Fail to start other app caused by OutOfMemory.");
        }
    }
}
