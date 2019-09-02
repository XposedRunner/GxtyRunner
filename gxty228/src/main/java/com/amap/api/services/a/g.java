package com.amap.api.services.a;

/* compiled from: ADDNumEncryptProcessor */
public class g extends i {
    g() {
    }

    public g(i iVar) {
        super(iVar);
    }

    protected byte[] a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(f.a(bArr)).append("||").append(1);
        return f.a(stringBuilder.toString());
    }
}
