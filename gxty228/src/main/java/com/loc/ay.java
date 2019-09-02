package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.AMapException;
import java.io.ByteArrayOutputStream;

/* compiled from: StatisticsEntity */
public final class ay {
    private Context a;
    private String b;
    private String c;
    private String d;
    private String e;

    public ay(Context context, String str, String str2, String str3) throws k {
        if (TextUtils.isEmpty(str3) || str3.length() > 256) {
            throw new k(AMapException.ERROR_INVALID_PARAMETER);
        }
        this.a = context.getApplicationContext();
        this.c = str;
        this.d = str2;
        this.b = str3;
    }

    public final void a(String str) throws k {
        if (TextUtils.isEmpty(str) || str.length() > 65536) {
            throw new k(AMapException.ERROR_INVALID_PARAMETER);
        }
        this.e = str;
    }

    public final byte[] a() {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        Throwable th2;
        int i = 0;
        byte[] bArr = new byte[0];
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byte[] bArr2;
                dl.a(byteArrayOutputStream, this.c);
                dl.a(byteArrayOutputStream, this.d);
                dl.a(byteArrayOutputStream, this.b);
                dl.a(byteArrayOutputStream, String.valueOf(df.q(this.a)));
                try {
                    i = (int) (System.currentTimeMillis() / 1000);
                } catch (Throwable th3) {
                }
                byteArrayOutputStream.write(new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)});
                Object obj = this.e;
                if (TextUtils.isEmpty(obj)) {
                    bArr2 = new byte[]{(byte) 0, (byte) 0};
                } else {
                    byte length = (byte) (obj.length() % 256);
                    bArr2 = new byte[]{(byte) (obj.length() / 256), length};
                }
                byteArrayOutputStream.write(bArr2);
                byteArrayOutputStream.write(dl.a(this.e));
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
                    j.b(th, "se", "tds");
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
