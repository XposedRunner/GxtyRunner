package com.amap.api.mapcore.util;

import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import java.net.Proxy;
import java.util.Map;

/* compiled from: Request */
public abstract class iy {
    int f = 20000;
    int g = 20000;
    Proxy h = null;

    public abstract Map<String, String> a();

    public abstract Map<String, String> b();

    public abstract String c();

    String m() {
        byte[] h = h();
        if (h == null || h.length == 0) {
            return c();
        }
        Map b = b();
        if (b == null) {
            return c();
        }
        String a = iw.a(b);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(c()).append(HttpUtils.URL_AND_PARA_SEPARATOR).append(a);
        return stringBuffer.toString();
    }

    byte[] n() {
        byte[] h = h();
        if (h != null && h.length != 0) {
            return h;
        }
        String a = iw.a(b());
        if (TextUtils.isEmpty(a)) {
            return h;
        }
        return gl.a(a);
    }

    public final void a(int i) {
        this.f = i;
    }

    public final void b(int i) {
        this.g = i;
    }

    public byte[] h() {
        return null;
    }

    public final void a(Proxy proxy) {
        this.h = proxy;
    }
}
