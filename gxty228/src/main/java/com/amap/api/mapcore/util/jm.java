package com.amap.api.mapcore.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* compiled from: ByteJoinDataStrategy */
public class jm extends jt {
    ByteArrayOutputStream a = new ByteArrayOutputStream();

    public jm(jt jtVar) {
        super(jtVar);
    }

    protected byte[] a(byte[] bArr) {
        byte[] toByteArray = this.a.toByteArray();
        try {
            this.a.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.a = new ByteArrayOutputStream();
        return toByteArray;
    }

    public void b(byte[] bArr) {
        try {
            this.a.write(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
