package com.loc;

import android.content.Context;
import com.lzy.okgo.cache.CacheEntity;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BinaryRequest */
public abstract class ao extends ar {
    protected Context a;
    protected dk b;

    public ao(Context context, dk dkVar) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
        this.b = dkVar;
    }

    protected static byte[] a(byte[] bArr) {
        byte length = (byte) (bArr.length % 256);
        return new byte[]{(byte) (bArr.length / 256), length};
    }

    private static byte[] k() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(dl.a("PANDORA$"));
            byteArrayOutputStream.write(new byte[]{(byte) 1});
            byteArrayOutputStream.write(new byte[]{(byte) 0});
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return toByteArray;
            } catch (Throwable th) {
                g.a(th, "bre", "gbh");
                return toByteArray;
            }
        } catch (Throwable th2) {
            g.a(th2, "bre", "gbh");
        }
        return null;
    }

    private byte[] l() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] a;
            byteArrayOutputStream.write(new byte[]{(byte) 3});
            if (e()) {
                a = dd.a(this.a, j());
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
            } else {
                byteArrayOutputStream.write(new byte[]{(byte) 0, (byte) 0});
            }
            a = dl.a(g());
            if (a == null || a.length <= 0) {
                byteArrayOutputStream.write(new byte[]{(byte) 0, (byte) 0});
            } else {
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
            }
            a = dl.a(f());
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
                g.a(th, "bre", "gred");
                return a;
            }
        } catch (Throwable th2) {
            g.a(th2, "bre", "gred");
        }
        return new byte[]{(byte) 0};
    }

    private byte[] m() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] h = h();
            if (h == null || h.length == 0) {
                byteArrayOutputStream.write(new byte[]{(byte) 0});
                h = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    return h;
                } catch (Throwable th) {
                    g.a(th, "bre", "grrd");
                    return h;
                }
            }
            byteArrayOutputStream.write(new byte[]{(byte) 1});
            byteArrayOutputStream.write(a(h));
            byteArrayOutputStream.write(h);
            h = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return h;
            } catch (Throwable th2) {
                g.a(th2, "bre", "grrd");
                return h;
            }
        } catch (Throwable th3) {
            g.a(th3, "bre", "grrd");
        }
        return new byte[]{(byte) 0};
    }

    private byte[] n() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] i = i();
            if (i == null || i.length == 0) {
                byteArrayOutputStream.write(new byte[]{(byte) 0});
                i = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    return i;
                } catch (Throwable th) {
                    g.a(th, "bre", "gred");
                    return i;
                }
            }
            byteArrayOutputStream.write(new byte[]{(byte) 1});
            Context context = this.a;
            i = dg.a(i);
            byteArrayOutputStream.write(a(i));
            byteArrayOutputStream.write(i);
            i = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return i;
            } catch (Throwable th2) {
                g.a(th2, "bre", "gred");
                return i;
            }
        } catch (Throwable th3) {
            g.a(th3, "bre", "gred");
        }
        return new byte[]{(byte) 0};
    }

    public Map<String, String> b() {
        String f = db.f(this.a);
        String a = dd.a();
        String a2 = dd.a(this.a, a, "key=" + f);
        Map<String, String> hashMap = new HashMap();
        hashMap.put("ts", a);
        hashMap.put(CacheEntity.KEY, f);
        hashMap.put("scode", a2);
        return hashMap;
    }

    public final byte[] d() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(k());
            byteArrayOutputStream.write(l());
            byteArrayOutputStream.write(m());
            byteArrayOutputStream.write(n());
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return toByteArray;
            } catch (Throwable th) {
                g.a(th, "bre", "geb");
                return toByteArray;
            }
        } catch (Throwable th2) {
            g.a(th2, "bre", "geb");
        }
        return null;
    }

    public boolean e() {
        return true;
    }

    public String f() {
        return String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{this.b.c(), this.b.a()});
    }

    protected String g() {
        return "2.1";
    }

    public abstract byte[] h();

    public abstract byte[] i();

    protected boolean j() {
        return false;
    }
}
