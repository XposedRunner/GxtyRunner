package com.loc;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/* compiled from: LocationRequest */
public final class ch extends ao {
    Map<String, String> f = null;
    String g = "";
    byte[] h = null;
    byte[] i = null;
    boolean j = false;
    String k = null;
    Map<String, String> l = null;
    boolean m = false;

    public ch(Context context, dk dkVar) {
        super(context, dkVar);
    }

    public final Map<String, String> a() {
        return this.f;
    }

    public final Map<String, String> b() {
        return this.l;
    }

    public final void b(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            if (bArr != null) {
                try {
                    byteArrayOutputStream.write(ao.a(bArr));
                    byteArrayOutputStream.write(bArr);
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        th.printStackTrace();
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            }
            this.i = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        } catch (Throwable th4) {
            th = th4;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }

    public final String c() {
        return this.g;
    }

    public final boolean e() {
        return this.j;
    }

    public final String f() {
        return this.k;
    }

    public final byte[] h() {
        return this.h;
    }

    public final byte[] i() {
        return this.i;
    }

    protected final boolean j() {
        return this.m;
    }
}
