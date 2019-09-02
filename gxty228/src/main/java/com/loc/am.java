package com.loc;

import java.util.Map;

/* compiled from: ADIURequest */
public final class am extends ar {
    private byte[] a;
    private Map<String, String> b;

    public am(byte[] bArr, Map<String, String> map) {
        this.a = bArr;
        this.b = map;
    }

    public final Map<String, String> a() {
        return null;
    }

    public final Map<String, String> b() {
        return this.b;
    }

    public final String c() {
        return "https://adiu.amap.com/ws/device/adius";
    }

    public final byte[] d() {
        return this.a;
    }
}
