package cn.jiguang.a.a.c;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import cn.jiguang.d.d.o;
import cn.jiguang.e.d;
import cn.jiguang.g.a;
import cn.jiguang.g.k;
import com.blankj.utilcode.constant.TimeConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private static final String a = b.class.getSimpleName();

    public static JSONObject a(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            Object a = a.a();
            Object a2 = a.a(context);
            Object format = String.format(Locale.ENGLISH, "%.1f", new Object[]{Double.valueOf(a.o(context))});
            Object obj = VERSION.RELEASE;
            Object obj2 = Build.MODEL;
            Object locale = context.getResources().getConfiguration().locale.toString();
            Object b = a.b(cn.jiguang.d.a.e, "");
            long rawOffset = (long) (TimeZone.getDefault().getRawOffset() / TimeConstants.HOUR);
            r4 = rawOffset > 0 ? "+" + rawOffset : rawOffset < 0 ? "-" + rawOffset : rawOffset;
            String str = "cpu_info";
            if (k.a((String) a)) {
                a = "";
            }
            jSONObject.put(str, a);
            String str2 = "resolution";
            if (k.a((String) a2)) {
                a2 = "";
            }
            jSONObject.put(str2, a2);
            String str3 = "os_version";
            if (k.a((String) obj)) {
                obj = "";
            }
            jSONObject.put(str3, obj);
            String str4 = "language";
            if (k.a((String) locale)) {
                locale = "";
            }
            jSONObject.put(str4, locale);
            String str5 = "timezone";
            if (k.a((String) r4)) {
                r4 = "";
            }
            jSONObject.put(str5, r4);
            String str6 = "model";
            if (k.a((String) obj2)) {
                obj2 = "";
            }
            jSONObject.put(str6, obj2);
            String str7 = "screensize";
            if (k.a((String) format)) {
                format = "";
            }
            jSONObject.put(str7, format);
            String str8 = "mac";
            if (k.a((String) b)) {
                b = "";
            }
            jSONObject.put(str8, b);
            ArrayList a3 = cn.jiguang.g.c.b.a().a(context.getApplicationContext());
            JSONArray jSONArray = new JSONArray();
            if (a3 != null) {
                Iterator it = a3.iterator();
                while (it.hasNext()) {
                    cn.jiguang.g.c.a aVar = (cn.jiguang.g.c.a) it.next();
                    if (aVar != null) {
                        JSONObject a4 = aVar.a();
                        if (a4 != null) {
                            jSONArray.put(a4);
                        }
                    }
                }
            }
            jSONObject.put("sim_slots", jSONArray);
            d.c(a, "devices info:" + jSONObject.toString());
            return jSONObject;
        } catch (Throwable th) {
            d.i(a, th.getMessage());
            return null;
        }
    }

    public static JSONObject a(Context context, cn.jiguang.d.g.b bVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject a;
            ArrayList a2 = cn.jiguang.g.c.b.a().a(context.getApplicationContext());
            JSONArray jSONArray = new JSONArray();
            if (a2 != null) {
                Iterator it = a2.iterator();
                while (it.hasNext()) {
                    cn.jiguang.g.c.a aVar = (cn.jiguang.g.c.a) it.next();
                    if (aVar != null) {
                        a = aVar.a();
                        if (a != null) {
                            jSONArray.put(a);
                        }
                    }
                }
            }
            jSONObject.put("sim_slots", jSONArray);
            jSONObject.put("pkgname", context.getPackageName());
            jSONObject.put(LogBuilder.KEY_APPKEY, cn.jiguang.d.a.d.i(context));
            jSONObject.put("platform", 0);
            jSONObject.put("apkversion", bVar.a);
            jSONObject.put("systemversion", bVar.b);
            jSONObject.put("modelnumber", bVar.c);
            jSONObject.put("basebandversion", bVar.d);
            jSONObject.put("buildnumber", bVar.e);
            jSONObject.put(LogBuilder.KEY_CHANNEL, bVar.j);
            a = new JSONObject();
            a.put("PushSDKVer", bVar.f);
            a.put("StatisticSDKVer", bVar.g);
            a.put("ShareSDKVer", bVar.h);
            a.put("CoreSDKVer", bVar.i);
            jSONObject.put("sdkver", a);
            jSONObject.put("installation", bVar.k);
            jSONObject.put("resolution", bVar.l);
            jSONObject.put("business", bVar.m);
            jSONObject.put("device_id_status", bVar.n);
            jSONObject.put("device_id", bVar.o);
            jSONObject.put("android_id", bVar.p);
            jSONObject.put("mac_address", bVar.q);
            jSONObject.put("serial_number", bVar.r);
        } catch (JSONException e) {
            d.a(a, "save device info err:" + e.getMessage());
        }
        return jSONObject;
    }

    public static void a(Context context, String str) {
        Editor edit = context.getSharedPreferences("jpush_device_info", 0).edit();
        edit.putString("sdk_version", str);
        edit.commit();
    }

    public static String b(Context context) {
        return context.getSharedPreferences("jpush_device_info", 0).getString("sdk_version", null);
    }

    public static void b(Context context, cn.jiguang.d.g.b bVar) {
        String jSONObject = a(context, bVar).toString();
        d.a(a, "save device report:" + jSONObject);
        cn.jiguang.d.a.a.c(context, jSONObject);
    }

    public static void b(Context context, String str) {
        Editor edit = context.getSharedPreferences("jpush_device_info", 0).edit();
        edit.putString("device_session", str);
        edit.commit();
    }

    public static void c(Context context) {
        d.c(a, "action:reportDeviceInfo");
        if (cn.jiguang.d.a.d.e(context)) {
            JSONObject a = a(context);
            if (a != null) {
                Object jSONObject = a.toString();
                if (TextUtils.isEmpty(jSONObject)) {
                    d.g(a, "deviceInfoStr was null");
                    return;
                }
                CharSequence charSequence;
                StringBuilder append = new StringBuilder().append(jSONObject);
                String g = cn.jiguang.d.a.a.g("");
                String i = cn.jiguang.d.a.d.i(context);
                CharSequence charSequence2 = cn.jiguang.d.a.i;
                CharSequence charSequence3 = cn.jiguang.d.a.h;
                CharSequence charSequence4 = "1.1.9";
                CharSequence charSequence5 = "119";
                CharSequence charSequence6 = cn.jiguang.d.a.c;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(g);
                stringBuilder.append(",");
                if (TextUtils.isEmpty(i)) {
                    i = "";
                }
                stringBuilder.append(i);
                stringBuilder.append(",");
                if (TextUtils.isEmpty(charSequence2)) {
                    i = "";
                } else {
                    charSequence = charSequence2;
                }
                stringBuilder.append(i);
                stringBuilder.append(",");
                if (TextUtils.isEmpty(charSequence3)) {
                    i = "";
                } else {
                    charSequence = charSequence3;
                }
                stringBuilder.append(i);
                stringBuilder.append(",");
                if (TextUtils.isEmpty(charSequence4)) {
                    i = "";
                } else {
                    charSequence = charSequence4;
                }
                stringBuilder.append(i);
                stringBuilder.append(",");
                if (TextUtils.isEmpty(charSequence5)) {
                    i = "";
                } else {
                    charSequence = charSequence5;
                }
                stringBuilder.append(i);
                stringBuilder.append(",");
                if (TextUtils.isEmpty(charSequence6)) {
                    i = "";
                } else {
                    charSequence = charSequence6;
                }
                stringBuilder.append(i);
                i = k.b(append.append(stringBuilder.toString()).toString());
                if (i == null || TextUtils.equals(i, context.getSharedPreferences("jpush_device_info", 0).getString("device_session", null))) {
                    d.c(a, "same device info, newDeviceSession:" + i);
                    return;
                }
                try {
                    o.b(context, a, "device_info");
                    d.c(a, "device_info:" + a.toString());
                    o.a(context, new JSONArray().put(a), new c(context, i));
                } catch (Throwable th) {
                    d.i(a, "report device info failed,error:" + th);
                }
            }
        }
    }
}
