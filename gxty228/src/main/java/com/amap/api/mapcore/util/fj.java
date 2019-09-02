package com.amap.api.mapcore.util;

import android.content.Context;
import com.lzy.okgo.model.HttpHeaders;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: AbstractBasicLbsRestHandler */
public abstract class fj<T, V> extends fi<T, V> {
    protected abstract String g();

    public fj(Context context, T t) {
        super(context, t);
    }

    public byte[] h() {
        byte[] bArr = null;
        try {
            bArr = g().getBytes("utf-8");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return bArr;
    }

    public Map<String, String> b() {
        return null;
    }

    public Map<String, String> a() {
        Map<String, String> hashMap = new HashMap(16);
        hashMap.put(HttpHeaders.HEAD_KEY_CONTENT_TYPE, " application/json");
        hashMap.put(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "gzip");
        hashMap.put(HttpHeaders.HEAD_KEY_USER_AGENT, "AMAP SDK Android Trace 6.4.1");
        hashMap.put("x-INFO", gb.b(this.d));
        hashMap.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", new Object[]{"6.4.1", "trace"}));
        hashMap.put("logversion", "2.1");
        return hashMap;
    }

    protected V f() {
        return null;
    }
}
