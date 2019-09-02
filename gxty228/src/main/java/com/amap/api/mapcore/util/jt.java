package com.amap.api.mapcore.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* compiled from: UpdateDataStrategy */
public abstract class jt {
    jt c;
    byte[] d = null;

    protected abstract byte[] a(byte[] bArr) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException;

    jt() {
    }

    jt(jt jtVar) {
        this.c = jtVar;
    }

    public byte[] a() throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        byte[] a = a(this.d);
        if (this.c == null) {
            return a;
        }
        this.c.c(a);
        return this.c.a();
    }

    void c(byte[] bArr) {
        this.d = bArr;
    }

    public void b(byte[] bArr) {
    }
}
