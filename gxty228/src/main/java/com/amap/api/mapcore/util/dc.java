package com.amap.api.mapcore.util;

import android.content.Context;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.model.HttpHeaders;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

/* compiled from: CustomStyleRequest */
public class dc extends fi<String, a> {
    private String i;

    /* compiled from: CustomStyleRequest */
    public static class a {
        public byte[] a;
        public int b = -1;
    }

    protected /* synthetic */ Object b(byte[] bArr) throws gh {
        return a(bArr);
    }

    protected /* synthetic */ Object c(String str) throws gh {
        return a(str);
    }

    public dc(Context context, String str) {
        super(context, str);
        this.e = "/map/styles";
    }

    protected a a(byte[] bArr) throws gh {
        a aVar = new a();
        aVar.a = bArr;
        return aVar;
    }

    protected a a(String str) throws gh {
        return null;
    }

    public Map<String, String> a() {
        gk e = en.e();
        String str = null;
        if (e != null) {
            str = e.b();
        }
        Map<String, String> hashtable = new Hashtable(16);
        hashtable.put(HttpHeaders.HEAD_KEY_USER_AGENT, le.c);
        hashtable.put(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "gzip");
        hashtable.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", new Object[]{str, "3dmap"}));
        hashtable.put("x-INFO", gb.a(this.d));
        hashtable.put(CacheEntity.KEY, fx.f(this.d));
        hashtable.put("logversion", "2.1");
        return hashtable;
    }

    public Map<String, String> b() {
        Map hashtable = new Hashtable(16);
        hashtable.put(CacheEntity.KEY, fx.f(this.d));
        hashtable.put("output", "bin");
        hashtable.put("styleid", this.i);
        String a = gb.a();
        String a2 = gb.a(this.d, a, gl.c(hashtable));
        hashtable.put("ts", a);
        hashtable.put("scode", a2);
        return hashtable;
    }

    public String c() {
        return "http://restapi.amap.com/v4" + this.e;
    }

    public void b(String str) {
        this.i = str;
    }
}
