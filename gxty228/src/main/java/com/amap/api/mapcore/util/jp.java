package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* compiled from: HeaderAddStrategy */
public class jp extends jt {
    private Context a;
    private String b;
    private gq e;
    private Object[] f;

    public jp(Context context, jt jtVar, gq gqVar, String str, Object... objArr) {
        super(jtVar);
        this.a = context;
        this.b = str;
        this.e = gqVar;
        this.f = objArr;
    }

    protected byte[] a(byte[] bArr) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        Object a = gl.a(bArr);
        if (TextUtils.isEmpty(a)) {
            return null;
        }
        String b = b(this.a);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"pinfo\":\"").append(b).append("\",\"els\":[");
        stringBuilder.append(a);
        stringBuilder.append("]}");
        return gl.a(stringBuilder.toString());
    }

    private String a(Context context) {
        String str = "";
        try {
            str = String.format(gl.c(this.b), this.f);
        } catch (Throwable th) {
            th.printStackTrace();
            gz.c(th, "ofm", "gpj");
        }
        return str;
    }

    private String b(Context context) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        return gl.a(this.e.b(gl.a(a(context))));
    }
}
