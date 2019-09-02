package com.amap.api.mapcore.util;

/* compiled from: ADDNumEncryptProcessor */
public class gn extends gq {
    gn() {
    }

    public gn(gq gqVar) {
        super(gqVar);
    }

    protected byte[] a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(gl.a(bArr)).append("||").append(1);
        return gl.a(stringBuilder.toString());
    }
}
