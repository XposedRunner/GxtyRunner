package com.amap.api.mapcore.util;

import com.lzy.okgo.model.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

/* compiled from: LogUpdateRequest */
public class gy extends iy {
    private byte[] a;
    private String b = "1";

    public gy(byte[] bArr, String str) {
        this.a = (byte[]) bArr.clone();
        this.b = str;
    }

    private String d() {
        Object a = gl.a(gu.b);
        byte[] bArr = new byte[(a.length + 50)];
        System.arraycopy(this.a, 0, bArr, 0, 50);
        System.arraycopy(a, 0, bArr, 50, a.length);
        return gf.a(bArr);
    }

    public Map<String, String> a() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(HttpHeaders.HEAD_KEY_CONTENT_TYPE, "application/zip");
        hashMap.put(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, String.valueOf(this.a.length));
        return hashMap;
    }

    public Map<String, String> b() {
        return null;
    }

    public String c() {
        return String.format(gl.c(gu.c), new Object[]{"1", this.b, "1", "open", d()});
    }

    public byte[] h() {
        return this.a;
    }
}
