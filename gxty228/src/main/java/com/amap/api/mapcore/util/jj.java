package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.AMapException;
import java.io.ByteArrayOutputStream;

/* compiled from: StatisticsEntity */
public class jj {
    private Context a;
    private String b;
    private String c;
    private String d;
    private String e;

    public jj(Context context, String str, String str2, String str3) throws gp {
        if (TextUtils.isEmpty(str3) || str3.length() > 256) {
            throw new gp(AMapException.ERROR_INVALID_PARAMETER);
        }
        this.a = context.getApplicationContext();
        this.c = str;
        this.d = str2;
        this.b = str3;
    }

    public void a(String str) throws gp {
        if (TextUtils.isEmpty(str) || str.length() > 65536) {
            throw new gp(AMapException.ERROR_INVALID_PARAMETER);
        }
        this.e = str;
    }

    public byte[] a(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public byte[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[]{(byte) 0, (byte) 0};
        }
        byte length = (byte) (str.length() % 256);
        return new byte[]{(byte) (str.length() / 256), length};
    }

    public byte[] a() {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        Throwable th2;
        int i = 0;
        byte[] bArr = new byte[0];
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gl.a(byteArrayOutputStream, this.c);
                gl.a(byteArrayOutputStream, this.d);
                gl.a(byteArrayOutputStream, this.b);
                gl.a(byteArrayOutputStream, String.valueOf(gd.q(this.a)));
                try {
                    i = (int) (System.currentTimeMillis() / 1000);
                } catch (Throwable th3) {
                }
                byteArrayOutputStream.write(a(i));
                byteArrayOutputStream.write(b(this.e));
                byteArrayOutputStream.write(gl.a(this.e));
                bArr = byteArrayOutputStream.toByteArray();
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th4) {
                        th = th4;
                        th.printStackTrace();
                        return bArr;
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                try {
                    gz.c(th, "se", "tds");
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th6) {
                            th = th6;
                            th.printStackTrace();
                            return bArr;
                        }
                    }
                    return bArr;
                } catch (Throwable th7) {
                    th2 = th7;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th8) {
                            th8.printStackTrace();
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th9) {
            th2 = th9;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th2;
        }
        return bArr;
    }
}
