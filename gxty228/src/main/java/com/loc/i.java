package com.loc;

import com.lzy.okgo.model.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

/* compiled from: LogUpdateRequest */
public final class i extends ar {
    private byte[] a;
    private String b = "1";

    public i(byte[] bArr, String str) {
        this.a = (byte[]) bArr.clone();
        this.b = str;
    }

    public final Map<String, String> a() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(HttpHeaders.HEAD_KEY_CONTENT_TYPE, "application/zip");
        hashMap.put(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, String.valueOf(this.a.length));
        return hashMap;
    }

    public final Map<String, String> b() {
        return null;
    }

    public final String c() {
        String c = dl.c(e.c);
        Object[] objArr = new Object[5];
        objArr[0] = "1";
        objArr[1] = this.b;
        objArr[2] = "1";
        objArr[3] = "open";
        Object a = dl.a(e.b);
        byte[] bArr = new byte[(a.length + 50)];
        System.arraycopy(this.a, 0, bArr, 0, 50);
        System.arraycopy(a, 0, bArr, 50, a.length);
        objArr[4] = dh.a(bArr);
        return String.format(c, objArr);
    }

    public final byte[] d() {
        return this.a;
    }
}
