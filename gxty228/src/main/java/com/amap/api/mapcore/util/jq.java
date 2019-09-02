package com.amap.api.mapcore.util;

/* compiled from: LogJsonDataStrategy */
public class jq extends jt {
    private StringBuilder a = new StringBuilder();
    private boolean b = true;

    public jq(jt jtVar) {
        super(jtVar);
    }

    protected byte[] a(byte[] bArr) {
        byte[] a = gl.a(this.a.toString());
        c(a);
        this.b = true;
        this.a.delete(0, this.a.length());
        return a;
    }

    public void b(byte[] bArr) {
        String a = gl.a(bArr);
        if (this.b) {
            this.b = false;
        } else {
            this.a.append(",");
        }
        this.a.append("{\"log\":\"").append(a).append("\"}");
    }
}
