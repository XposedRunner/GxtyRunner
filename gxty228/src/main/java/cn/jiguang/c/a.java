package cn.jiguang.c;

import android.text.TextUtils;
import cn.jiguang.api.MultiSpHelper;
import cn.jiguang.e.d;
import java.util.LinkedHashMap;
import org.json.JSONObject;

public final class a implements b {
    private static final LinkedHashMap<String, Integer> a;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        a = linkedHashMap;
        linkedHashMap.put("s.jpush.cn", Integer.valueOf(19000));
        a.put("sis.jpush.io", Integer.valueOf(19000));
        a.put("easytomessage.com", Integer.valueOf(19000));
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            d.g("CNHostConfig", "conn info was empty");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Object optString = jSONObject.optString("srv");
            d.c("CNHostConfig", "save srvHost:" + optString);
            if (!TextUtils.isEmpty(optString)) {
                MultiSpHelper.commitString(cn.jiguang.d.a.e, "srv", optString);
            }
            Object optString2 = jSONObject.optString("conn");
            d.c("CNHostConfig", "save connHost:" + optString2);
            if (!TextUtils.isEmpty(optString2)) {
                MultiSpHelper.commitString(cn.jiguang.d.a.e, "conn", optString2);
            }
        } catch (Throwable th) {
        }
    }

    public final String a() {
        return "CN";
    }

    public final LinkedHashMap<String, Integer> b() {
        return a;
    }

    public final String c() {
        Object string = MultiSpHelper.getString(cn.jiguang.d.a.e, "conn", "im64.jpush.cn");
        return TextUtils.isEmpty(string) ? "im64.jpush.cn" : string;
    }

    public final String d() {
        Object string = MultiSpHelper.getString(cn.jiguang.d.a.e, "srv", "_im64._tcp.jpush.cn");
        return TextUtils.isEmpty(string) ? "_im64._tcp.jpush.cn" : string;
    }

    public final String e() {
        return "_psis._udp.jpush.cn";
    }

    public final String f() {
        return "stats.jpush.cn";
    }

    public final String g() {
        return "update.sdk.jiguang.cn";
    }
}
