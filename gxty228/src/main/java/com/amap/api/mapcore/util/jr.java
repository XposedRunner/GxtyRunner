package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import java.io.ByteArrayOutputStream;

/* compiled from: StatisticsHeaderDataStrategy */
public class jr extends jt {
    public static int a = 13;
    public static int b = 6;
    private Context e;

    public jr(Context context, jt jtVar) {
        super(jtVar);
        this.e = context;
    }

    protected byte[] a(byte[] bArr) {
        Object a = a(this.e);
        Object obj = new byte[(a.length + bArr.length)];
        System.arraycopy(a, 0, obj, 0, a.length);
        System.arraycopy(bArr, 0, obj, a.length, bArr.length);
        return obj;
    }

    private byte[] a(Context context) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        try {
            gl.a(byteArrayOutputStream, "1.2." + a + "." + b);
            gl.a(byteArrayOutputStream, "Android");
            gl.a(byteArrayOutputStream, gd.u(context));
            gl.a(byteArrayOutputStream, gd.l(context));
            gl.a(byteArrayOutputStream, gd.h(context));
            gl.a(byteArrayOutputStream, Build.MANUFACTURER);
            gl.a(byteArrayOutputStream, Build.MODEL);
            gl.a(byteArrayOutputStream, Build.DEVICE);
            gl.a(byteArrayOutputStream, gd.v(context));
            gl.a(byteArrayOutputStream, fx.c(context));
            gl.a(byteArrayOutputStream, fx.d(context));
            gl.a(byteArrayOutputStream, fx.f(context));
            byteArrayOutputStream.write(new byte[]{(byte) 0});
            bArr = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return bArr;
            }
        } catch (Throwable th3) {
            th = th3;
            th.printStackTrace();
        }
        return bArr;
    }
}
