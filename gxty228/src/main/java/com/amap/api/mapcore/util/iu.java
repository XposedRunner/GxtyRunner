package com.amap.api.mapcore.util;

import android.content.Context;
import com.lzy.okgo.cache.CacheEntity;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BinaryRequest */
public abstract class iu extends iy {
    protected Context a;
    protected gk b;

    public abstract byte[] e();

    public abstract byte[] f();

    public boolean i() {
        return true;
    }

    public iu(Context context, gk gkVar) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
        this.b = gkVar;
    }

    public Map<String, String> b() {
        String f = fx.f(this.a);
        String a = gb.a();
        String a2 = gb.a(this.a, a, "key=" + f);
        Map<String, String> hashMap = new HashMap();
        hashMap.put("ts", a);
        hashMap.put(CacheEntity.KEY, f);
        hashMap.put("scode", a2);
        return hashMap;
    }

    public final byte[] h() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(d());
            byteArrayOutputStream.write(j());
            byteArrayOutputStream.write(o());
            byteArrayOutputStream.write(p());
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return toByteArray;
            } catch (Throwable th) {
                gw.a(th, "bre", "geb");
                return toByteArray;
            }
        } catch (Throwable th2) {
            gw.a(th2, "bre", "geb");
        }
        return null;
    }

    private byte[] d() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(gl.a("PANDORA$"));
            byteArrayOutputStream.write(new byte[]{(byte) 1});
            byteArrayOutputStream.write(new byte[]{(byte) 0});
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return toByteArray;
            } catch (Throwable th) {
                gw.a(th, "bre", "gbh");
                return toByteArray;
            }
        } catch (Throwable th2) {
            gw.a(th2, "bre", "gbh");
        }
        return null;
    }

    public byte[] j() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] a;
            byteArrayOutputStream.write(new byte[]{(byte) 3});
            if (i()) {
                a = gb.a(this.a, l());
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
            } else {
                byteArrayOutputStream.write(new byte[]{(byte) 0, (byte) 0});
            }
            a = gl.a(g());
            if (a == null || a.length <= 0) {
                byteArrayOutputStream.write(new byte[]{(byte) 0, (byte) 0});
            } else {
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
            }
            a = gl.a(k());
            if (a == null || a.length <= 0) {
                byteArrayOutputStream.write(new byte[]{(byte) 0, (byte) 0});
            } else {
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
            }
            a = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return a;
            } catch (Throwable th) {
                gw.a(th, "bre", "gred");
                return a;
            }
        } catch (Throwable th2) {
            gw.a(th2, "bre", "gred");
        }
        return new byte[]{(byte) 0};
    }

    public String k() {
        return String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{this.b.c(), this.b.a()});
    }

    protected String g() {
        return "2.1";
    }

    protected byte[] a(byte[] bArr) {
        byte length = (byte) (bArr.length % 256);
        return new byte[]{(byte) (bArr.length / 256), length};
    }

    private byte[] o() {
        Throwable th;
        String str;
        String str2;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] e = e();
            if (e == null || e.length == 0) {
                byteArrayOutputStream.write(new byte[]{(byte) 0});
                e = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    return e;
                } catch (Throwable th2) {
                    th = th2;
                    str = "bre";
                    str2 = "grrd";
                }
            } else {
                byteArrayOutputStream.write(new byte[]{(byte) 1});
                byteArrayOutputStream.write(a(e));
                byteArrayOutputStream.write(e);
                e = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    return e;
                } catch (Throwable th3) {
                    th = th3;
                    str = "bre";
                    str2 = "grrd";
                    gw.a(th, str, str2);
                    return e;
                }
            }
        } catch (Throwable th4) {
            gw.a(th4, "bre", "grrd");
        }
        return new byte[]{(byte) 0};
    }

    private byte[] p() {
        Throwable th;
        String str;
        String str2;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] f = f();
            if (f == null || f.length == 0) {
                byteArrayOutputStream.write(new byte[]{(byte) 0});
                f = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    return f;
                } catch (Throwable th2) {
                    th = th2;
                    str = "bre";
                    str2 = "gred";
                }
            } else {
                byteArrayOutputStream.write(new byte[]{(byte) 1});
                f = gb.a(this.a, f);
                byteArrayOutputStream.write(a(f));
                byteArrayOutputStream.write(f);
                f = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    return f;
                } catch (Throwable th3) {
                    th = th3;
                    str = "bre";
                    str2 = "gred";
                    gw.a(th, str, str2);
                    return f;
                }
            }
        } catch (Throwable th4) {
            gw.a(th4, "bre", "gred");
        }
        return new byte[]{(byte) 0};
    }

    protected boolean l() {
        return false;
    }
}
