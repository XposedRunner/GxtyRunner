package com.amap.api.mapcore.util;

import android.content.Context;
import com.lzy.okgo.model.HttpHeaders;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: LocNetManager */
public final class kv {
    private static kv b = null;
    it a;
    private int c;
    private int d;
    private boolean e;
    private int f;

    private kv() {
        this.a = null;
        this.c = 0;
        this.d = kz.f;
        this.e = false;
        this.f = 0;
        this.a = it.a();
    }

    public static kv a() {
        if (b == null) {
            b = new kv();
        }
        return b;
    }

    public final jb a(kw kwVar) throws Throwable {
        long b = lc.b();
        jb a = this.a.a(kwVar, this.e);
        this.c = Long.valueOf(lc.b() - b).intValue();
        return a;
    }

    public final kw a(Context context, byte[] bArr, String str) {
        kw kwVar;
        try {
            Map hashMap = new HashMap(16);
            kwVar = new kw(context, kz.b());
            hashMap.put(HttpHeaders.HEAD_KEY_CONTENT_TYPE, "application/octet-stream");
            hashMap.put(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "gzip");
            hashMap.put("gzipped", "1");
            hashMap.put(HttpHeaders.HEAD_KEY_CONNECTION, "Keep-Alive");
            hashMap.put(HttpHeaders.HEAD_KEY_USER_AGENT, "AMAP_Location_SDK_Android 4.2.0");
            hashMap.put("KEY", fx.f(context));
            hashMap.put("enginever", "4.9");
            String a = gb.a();
            String a2 = gb.a(context, a, "key=" + fx.f(context));
            hashMap.put("ts", a);
            hashMap.put("scode", a2);
            hashMap.put("encr", "1");
            kwVar.b(hashMap);
            kwVar.o();
            kwVar.a(String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", new Object[]{"4.2.0", "loc", Integer.valueOf(3)}));
            kwVar.d();
            kwVar.b(str);
            kwVar.b(lc.a(bArr));
            kwVar.a(gg.a(context));
            Map hashMap2 = new HashMap(16);
            hashMap2.put("output", "bin");
            hashMap2.put("policy", "3103");
            switch (this.f) {
                case 0:
                    hashMap2.remove("custom");
                    break;
                case 1:
                    hashMap2.put("custom", "language:cn");
                    break;
                case 2:
                    hashMap2.put("custom", "language:en");
                    break;
                default:
                    hashMap2.remove("custom");
                    break;
            }
            kwVar.a(hashMap2);
            kwVar.a(this.d);
            kwVar.b(this.d);
            if (!this.e) {
                return kwVar;
            }
            kwVar.b(kwVar.c().replace("http", "https"));
            return kwVar;
        } catch (Throwable th) {
            return kwVar;
        }
    }

    public final void a(long j, boolean z) {
        try {
            this.e = z;
            this.d = Long.valueOf(j).intValue();
            this.f = 0;
        } catch (Throwable th) {
            kz.a(th, "netmanager", "setOption");
        }
    }
}
