package com.amap.api.mapcore.util;

import android.content.Context;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.model.HttpHeaders;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: CustomStyleTextureRequest */
public class de extends fi<String, a> {

    /* compiled from: CustomStyleTextureRequest */
    public static class a {
        public byte[] a;
        public int b = -1;
    }

    protected /* synthetic */ Object b(byte[] bArr) throws gh {
        return a(bArr);
    }

    protected /* synthetic */ Object c(String str) throws gh {
        return b(str);
    }

    public void a(String str) {
        this.e = str;
    }

    public de(Context context, String str) {
        super(context, str);
        this.e = "/map/styles";
    }

    protected a a(byte[] bArr) throws gh {
        a aVar = new a();
        aVar.a = bArr;
        return aVar;
    }

    protected a b(String str) throws gh {
        return null;
    }

    public Map<String, String> a() {
        gk e = en.e();
        String str = null;
        if (e != null) {
            str = e.b();
        }
        Map<String, String> hashMap = new HashMap(16);
        hashMap.put(HttpHeaders.HEAD_KEY_USER_AGENT, le.c);
        hashMap.put(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "gzip");
        hashMap.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", new Object[]{str, "3dmap"}));
        hashMap.put("x-INFO", gb.a(this.d));
        hashMap.put(CacheEntity.KEY, fx.f(this.d));
        hashMap.put("logversion", "2.1");
        return hashMap;
    }

    public Map<String, String> b() {
        Map hashMap = new HashMap(16);
        hashMap.put(CacheEntity.KEY, fx.f(this.d));
        hashMap.put("output", "bin");
        String a = gb.a();
        String a2 = gb.a(this.d, a, gl.c(hashMap));
        hashMap.put("ts", a);
        hashMap.put("scode", a2);
        return hashMap;
    }

    public String c() {
        return this.e;
    }
}
