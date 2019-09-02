package com.loc;

import android.content.Context;
import android.os.Build;
import java.io.ByteArrayOutputStream;

/* compiled from: StatisticsHeaderDataStrategy */
public final class bg extends bi {
    public static int a = 13;
    public static int b = 6;
    private Context e;

    public bg(Context context, bi biVar) {
        super(biVar);
        this.e = context;
    }

    private static byte[] a(Context context) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        try {
            dl.a(byteArrayOutputStream, "1.2." + a + "." + b);
            dl.a(byteArrayOutputStream, "Android");
            dl.a(byteArrayOutputStream, df.u(context));
            dl.a(byteArrayOutputStream, df.l(context));
            dl.a(byteArrayOutputStream, df.h(context));
            dl.a(byteArrayOutputStream, Build.MANUFACTURER);
            dl.a(byteArrayOutputStream, Build.MODEL);
            dl.a(byteArrayOutputStream, Build.DEVICE);
            dl.a(byteArrayOutputStream, df.v(context));
            dl.a(byteArrayOutputStream, db.c(context));
            dl.a(byteArrayOutputStream, db.d(context));
            dl.a(byteArrayOutputStream, db.f(context));
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

    protected final byte[] a(byte[] bArr) {
        Object a = a(this.e);
        Object obj = new byte[(a.length + bArr.length)];
        System.arraycopy(a, 0, obj, 0, a.length);
        System.arraycopy(bArr, 0, obj, a.length, bArr.length);
        return obj;
    }
}
