package com.loc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* compiled from: ByteJoinDataStrategy */
public final class bb extends bi {
    ByteArrayOutputStream a = new ByteArrayOutputStream();

    public bb(bi biVar) {
        super(biVar);
    }

    protected final byte[] a(byte[] bArr) {
        byte[] toByteArray = this.a.toByteArray();
        try {
            this.a.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.a = new ByteArrayOutputStream();
        return toByteArray;
    }

    public final void b(byte[] bArr) {
        try {
            this.a.write(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
