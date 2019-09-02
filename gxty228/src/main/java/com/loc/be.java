package com.loc;

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
public final class be extends bi {
    private Context a;
    private String b;
    private dp e;
    private Object[] f;

    public be(Context context, bi biVar, dp dpVar, String str, Object... objArr) {
        super(biVar);
        this.a = context;
        this.b = str;
        this.e = dpVar;
        this.f = objArr;
    }

    private String b() {
        String str = "";
        try {
            str = String.format(dl.c(this.b), this.f);
        } catch (Throwable th) {
            th.printStackTrace();
            j.b(th, "ofm", "gpj");
        }
        return str;
    }

    protected final byte[] a(byte[] bArr) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        Object a = dl.a(bArr);
        if (TextUtils.isEmpty(a)) {
            return null;
        }
        Context context = this.a;
        String a2 = dl.a(this.e.b(dl.a(b())));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"pinfo\":\"").append(a2).append("\",\"els\":[");
        stringBuilder.append(a);
        stringBuilder.append("]}");
        return dl.a(stringBuilder.toString());
    }
}
