package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.model.HttpHeaders;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.HashMap;
import java.util.Map;

/* compiled from: GeoFenceNetManager */
public final class ac {
    an a;

    public ac() {
        this.a = null;
        this.a = an.a();
    }

    private String a(Context context, String str, Map<String, String> map) {
        try {
            Map hashMap = new HashMap(16);
            ar cfVar = new cf();
            hashMap.clear();
            hashMap.put(HttpHeaders.HEAD_KEY_CONTENT_TYPE, "application/x-www-form-urlencoded");
            hashMap.put(HttpHeaders.HEAD_KEY_CONNECTION, "Keep-Alive");
            hashMap.put(HttpHeaders.HEAD_KEY_USER_AGENT, "AMAP_Location_SDK_Android 4.2.0");
            String a = dd.a();
            String a2 = dd.a(context, a, dl.b((Map) map));
            map.put("ts", a);
            map.put("scode", a2);
            cfVar.b(map);
            cfVar.a(hashMap);
            cfVar.a(str);
            cfVar.a(di.a(context));
            cfVar.a(cl.f);
            cfVar.b(cl.f);
            an anVar = this.a;
            return new String(an.b(cfVar), "utf-8");
        } catch (Throwable th) {
            return null;
        }
    }

    private static Map<String, String> b(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Map<String, String> hashMap = new HashMap(16);
        hashMap.put(CacheEntity.KEY, db.f(context));
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("keywords", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("types", str2);
        }
        if (!(TextUtils.isEmpty(str5) || TextUtils.isEmpty(str6))) {
            hashMap.put("location", str6 + "," + str5);
        }
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put("city", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put(ParamKey.OFFSET, str4);
        }
        if (!TextUtils.isEmpty(str7)) {
            hashMap.put("radius", str7);
        }
        return hashMap;
    }

    public final String a(Context context, String str, String str2) {
        Map b = b(context, str2, null, null, null, null, null, null);
        b.put("extensions", "all");
        return a(context, str, b);
    }

    public final String a(Context context, String str, String str2, String str3, String str4, String str5) {
        Map b = b(context, str2, str3, str4, str5, null, null, null);
        b.put("children", "1");
        b.put(ParamKey.PAGE, "1");
        b.put("extensions", "base");
        return a(context, str, b);
    }

    public final String a(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Map b = b(context, str2, str3, null, str4, str5, str6, str7);
        b.put("children", "1");
        b.put(ParamKey.PAGE, "1");
        b.put("extensions", "base");
        return a(context, str, b);
    }
}
