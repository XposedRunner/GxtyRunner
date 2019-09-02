package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* compiled from: ClientInfo */
public class gb {

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

    public static String a(Context context, String str, String str2) {
        String str3 = null;
        try {
            str3 = gf.b(fx.e(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            gw.a(th, "CI", "Sco");
        }
        return str3;
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
                if (!fx.a()) {
                    str = "0";
                }
                int length = str2.length();
                str = str2.substring(0, length - 2) + str + str2.substring(length - 1);
            } catch (Throwable th3) {
                th = th3;
                str = str2;
                th2 = th;
                gw.a(th2, "CI", "TS");
                return str;
            }
        } catch (Throwable th32) {
            th = th32;
            str = str2;
            th2 = th;
            gw.a(th2, "CI", "TS");
            return str;
        }
        return str;
    }

    public static String a(Context context) {
        String str = null;
        try {
            a aVar = new a();
            aVar.d = fx.c(context);
            aVar.i = fx.d(context);
            str = a(context, aVar);
        } catch (Throwable th) {
            gw.a(th, "CI", "IX");
        }
        return str;
    }

    public static byte[] a(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        return ge.a(bArr);
    }

    @Deprecated
    public static byte[] a(Context context, boolean z) {
        try {
            return b(context, b(context, z));
        } catch (Throwable th) {
            gw.a(th, "CI", "gz");
            return null;
        }
    }

    public static String b(Context context) {
        try {
            return a(context, b(context, false));
        } catch (Throwable th) {
            gw.a(th, "CI", "gCX");
            return null;
        }
    }

    public static byte[] b(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Key c = gl.c();
        if (bArr.length <= 117) {
            return ge.a(bArr, c);
        }
        byte[] bArr2 = new byte[117];
        System.arraycopy(bArr, 0, bArr2, 0, 117);
        Object a = ge.a(bArr2, c);
        Object obj = new byte[((bArr.length + 128) - 117)];
        System.arraycopy(a, 0, obj, 0, 128);
        System.arraycopy(bArr, 117, obj, 128, bArr.length - 117);
        return obj;
    }

    private static String a(Context context, a aVar) {
        return ge.b(b(context, aVar));
    }

    private static byte[] b(Context context, a aVar) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        Throwable th2;
        byte[] bArr = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
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
                bArr = a(context, byteArrayOutputStream);
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th = th3;
                        th.printStackTrace();
                        return bArr;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                try {
                    gw.a(th, "CI", "gzx");
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th5) {
                            th = th5;
                            th.printStackTrace();
                            return bArr;
                        }
                    }
                    return bArr;
                } catch (Throwable th6) {
                    th2 = th6;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th7) {
                            th7.printStackTrace();
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th72) {
            byteArrayOutputStream = bArr;
            th2 = th72;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th2;
        }
        return bArr;
    }

    private static byte[] a(Context context, ByteArrayOutputStream byteArrayOutputStream) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        return b(context, gl.b(byteArrayOutputStream.toByteArray()));
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            gl.a(byteArrayOutputStream, (byte) 0, new byte[0]);
            return;
        }
        byte b;
        if (str.getBytes().length > 255) {
            b = (byte) -1;
        } else {
            b = (byte) str.getBytes().length;
        }
        gl.a(byteArrayOutputStream, b, gl.a(str));
    }

    @Deprecated
    private static a b(Context context, boolean z) {
        a aVar = new a();
        aVar.a = gd.u(context);
        aVar.b = gd.l(context);
        String h = gd.h(context);
        if (h == null) {
            h = "";
        }
        aVar.c = h;
        aVar.d = fx.c(context);
        aVar.e = Build.MODEL;
        aVar.f = Build.MANUFACTURER;
        aVar.g = Build.DEVICE;
        aVar.h = fx.b(context);
        aVar.i = fx.d(context);
        aVar.j = String.valueOf(VERSION.SDK_INT);
        aVar.k = gd.v(context);
        aVar.l = gd.t(context);
        aVar.m = gd.q(context) + "";
        aVar.n = gd.p(context) + "";
        aVar.o = gd.w(context);
        aVar.p = gd.o(context);
        if (z) {
            aVar.q = "";
        } else {
            aVar.q = gd.k(context);
        }
        if (z) {
            aVar.r = "";
        } else {
            aVar.r = gd.j(context);
        }
        if (z) {
            aVar.s = "";
            aVar.t = "";
        } else {
            String[] m = gd.m(context);
            aVar.s = m[0];
            aVar.t = m[1];
        }
        aVar.w = gd.a();
        Object b = gd.b(context);
        if (TextUtils.isEmpty(b)) {
            aVar.x = "";
        } else {
            aVar.x = b;
        }
        aVar.y = new String(gu.a(11)) + gd.i(context) + "|" + new String(gu.a(12)) + gd.c();
        b = gd.a(context);
        if (TextUtils.isEmpty(b)) {
            aVar.y += "|adiuExtras=" + b;
        }
        return aVar;
    }
}
