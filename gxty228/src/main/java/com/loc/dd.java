package com.loc;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.security.Key;

/* compiled from: ClientInfo */
public final class dd {

    /* compiled from: ClientInfo */
    private static class a {
        String a;
        String b;
        String c;
        String d;
        String e;
        String f;
        String g;
        String h;
        String i;
        String j;
        String k;
        String l;
        String m;
        String n;
        String o;
        String p;
        String q;
        String r;
        String s;
        String t;
        String u;
        String v;
        String w;
        String x;
        String y;

        private a() {
        }
    }

    public static String a() {
        String str;
        Throwable th;
        Throwable th2;
        String str2 = null;
        try {
            str2 = String.valueOf(System.currentTimeMillis());
            try {
                str = "1";
                if (!db.a()) {
                    str = "0";
                }
                int length = str2.length();
                str = str2.substring(0, length - 2) + str + str2.substring(length - 1);
            } catch (Throwable th3) {
                th = th3;
                str = str2;
                th2 = th;
                g.a(th2, "CI", "TS");
                return str;
            }
        } catch (Throwable th32) {
            th = th32;
            str = str2;
            th2 = th;
            g.a(th2, "CI", "TS");
            return str;
        }
        return str;
    }

    public static String a(Context context, String str, String str2) {
        String str3 = null;
        try {
            str3 = dh.b(db.e(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            g.a(th, "CI", "Sco");
        }
        return str3;
    }

    private static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            dl.a(byteArrayOutputStream, (byte) 0, new byte[0]);
        } else {
            dl.a(byteArrayOutputStream, str.getBytes().length > 255 ? (byte) -1 : (byte) str.getBytes().length, dl.a(str));
        }
    }

    @Deprecated
    public static byte[] a(Context context, boolean z) {
        try {
            a aVar = new a();
            aVar.a = df.u(context);
            aVar.b = df.l(context);
            String h = df.h(context);
            if (h == null) {
                h = "";
            }
            aVar.c = h;
            aVar.d = db.c(context);
            aVar.e = Build.MODEL;
            aVar.f = Build.MANUFACTURER;
            aVar.g = Build.DEVICE;
            aVar.h = db.b(context);
            aVar.i = db.d(context);
            aVar.j = String.valueOf(VERSION.SDK_INT);
            aVar.k = df.v(context);
            aVar.l = df.t(context);
            aVar.m = df.q(context);
            aVar.n = df.p(context);
            aVar.o = df.w(context);
            aVar.p = df.o(context);
            if (z) {
                aVar.q = "";
            } else {
                aVar.q = df.k(context);
            }
            if (z) {
                aVar.r = "";
            } else {
                aVar.r = df.j(context);
            }
            if (z) {
                aVar.s = "";
                aVar.t = "";
            } else {
                String[] m = df.m(context);
                aVar.s = m[0];
                aVar.t = m[1];
            }
            aVar.w = df.a();
            Object b = df.b(context);
            if (TextUtils.isEmpty(b)) {
                aVar.x = "";
            } else {
                aVar.x = b;
            }
            aVar.y = new String(e.a(11)) + df.i(context) + "|" + new String(e.a(12)) + df.c();
            b = df.a(context);
            if (TextUtils.isEmpty(b)) {
                aVar.y += "|adiuExtras=" + b;
            }
            return a(aVar);
        } catch (Throwable th) {
            g.a(th, "CI", "gz");
            return null;
        }
    }

    private static byte[] a(a aVar) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byte[] bArr;
                a(byteArrayOutputStream, aVar.a);
                a(byteArrayOutputStream, aVar.b);
                a(byteArrayOutputStream, aVar.c);
                a(byteArrayOutputStream, aVar.d);
                a(byteArrayOutputStream, aVar.e);
                a(byteArrayOutputStream, aVar.f);
                a(byteArrayOutputStream, aVar.g);
                a(byteArrayOutputStream, aVar.h);
                a(byteArrayOutputStream, aVar.i);
                a(byteArrayOutputStream, aVar.j);
                a(byteArrayOutputStream, aVar.k);
                a(byteArrayOutputStream, aVar.l);
                a(byteArrayOutputStream, aVar.m);
                a(byteArrayOutputStream, aVar.n);
                a(byteArrayOutputStream, aVar.o);
                a(byteArrayOutputStream, aVar.p);
                a(byteArrayOutputStream, aVar.q);
                a(byteArrayOutputStream, aVar.r);
                a(byteArrayOutputStream, aVar.s);
                a(byteArrayOutputStream, aVar.t);
                a(byteArrayOutputStream, aVar.u);
                a(byteArrayOutputStream, aVar.v);
                a(byteArrayOutputStream, aVar.w);
                a(byteArrayOutputStream, aVar.x);
                a(byteArrayOutputStream, aVar.y);
                byte[] b = dl.b(byteArrayOutputStream.toByteArray());
                Key c = dl.c();
                if (b.length > 117) {
                    byte[] bArr2 = new byte[117];
                    System.arraycopy(b, 0, bArr2, 0, 117);
                    Object a = dg.a(bArr2, c);
                    bArr = new byte[((b.length + 128) - 117)];
                    System.arraycopy(a, 0, bArr, 0, 128);
                    System.arraycopy(b, 117, bArr, 128, b.length - 117);
                } else {
                    bArr = dg.a(b, c);
                }
                try {
                    byteArrayOutputStream.close();
                    return bArr;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                    return bArr;
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    g.a(th, "CI", "gzx");
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th4) {
                            th4.printStackTrace();
                        }
                    }
                    return null;
                } catch (Throwable th5) {
                    th4 = th5;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th22) {
                            th22.printStackTrace();
                        }
                    }
                    throw th4;
                }
            }
        } catch (Throwable th6) {
            th4 = th6;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th4;
        }
    }
}
