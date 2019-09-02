package com.loc;

/* compiled from: LogJsonDataStrategy */
public final class bf extends bi {
    private StringBuilder a = new StringBuilder();
    private boolean b = true;

    public bf(bi biVar) {
        super(biVar);
    }

    protected final byte[] a(byte[] bArr) {
        byte[] a = dl.a(this.a.toString());
        this.d = a;
        this.b = true;
        this.a.delete(0, this.a.length());
        return a;
    }

    public final void b(byte[] bArr) {
        String a = dl.a(bArr);
        if (this.b) {
            this.b = false;
        } else {
            this.a.append(",");
        }
        this.a.append("{\"log\":\"").append(a).append("\"}");
    }
}
