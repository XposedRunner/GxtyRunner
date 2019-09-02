package com.amap.api.mapcore.util;

import android.content.Context;
import android.util.Log;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.model.HttpHeaders;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@Deprecated
/* compiled from: AuthManager */
public class ga {
    public static int a = -1;
    public static String b = "";
    private static gk c;
    private static String d = "http://apiinit.amap.com/v3/log/init";
    private static String e = null;

    private static boolean a(Context context, gk gkVar, boolean z) {
        boolean z2 = true;
        c = gkVar;
        try {
            String a = a();
            Map hashMap = new HashMap();
            hashMap.put(HttpHeaders.HEAD_KEY_CONTENT_TYPE, "application/x-www-form-urlencoded");
            hashMap.put(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "gzip");
            hashMap.put(HttpHeaders.HEAD_KEY_CONNECTION, "Keep-Alive");
            hashMap.put(HttpHeaders.HEAD_KEY_USER_AGENT, c.d());
            hashMap.put("X-INFO", gb.b(context));
            hashMap.put("logversion", "2.1");
            hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{c.b(), c.a()}));
            it a2 = it.a();
            iy gmVar = new gm();
            gmVar.a(gg.a(context));
            gmVar.a(hashMap);
            gmVar.b(a(context));
            gmVar.a(a);
            z2 = a(a2.b(gmVar));
        } catch (Throwable th) {
            gw.a(th, "Auth", "getAuth");
        }
        return z2;
    }

    @Deprecated
    public static synchronized boolean a(Context context, gk gkVar) {
        boolean a;
        synchronized (ga.class) {
            a = a(context, gkVar, false);
        }
        return a;
    }

    private static String a() {
        return d;
    }

    private static boolean a(byte[] bArr) {
        if (bArr == null) {
            return true;
        }
        try {
            JSONObject jSONObject = new JSONObject(gl.a(bArr));
            if (jSONObject.has("status")) {
                int i = jSONObject.getInt("status");
                if (i == 1) {
                    a = 1;
                } else if (i == 0) {
                    a = 0;
                }
            }
            if (jSONObject.has("info")) {
                b = jSONObject.getString("info");
            }
            if (a == 0) {
                Log.i("AuthFailure", b);
            }
            if (a != 1) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            gw.a(e, "Auth", "lData");
            return false;
        } catch (Throwable e2) {
            gw.a(e2, "Auth", "lData");
            return false;
        }
    }

    private static Map<String, String> a(Context context) {
        Map<String, String> hashMap = new HashMap();
        try {
            hashMap.put("resType", "json");
            hashMap.put("encode", "UTF-8");
            String a = gb.a();
            hashMap.put("ts", a);
            hashMap.put(CacheEntity.KEY, fx.f(context));
            hashMap.put("scode", gb.a(context, a, gl.d("resType=json&encode=UTF-8&key=" + fx.f(context))));
        } catch (Throwable th) {
            gw.a(th, "Auth", "gParams");
        }
        return hashMap;
    }
}
