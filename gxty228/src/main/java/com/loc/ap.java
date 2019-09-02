package com.loc;

/* compiled from: DownloadManager */
public final class ap {
    private aq a;
    private ar b;

    /* compiled from: DownloadManager */
    public interface a {
        void a(byte[] bArr, long j);

        void c();

        void d();

        void e();
    }

    public ap(ar arVar) {
        this(arVar, (byte) 0);
    }

    private ap(ar arVar, byte b) {
        this.b = arVar;
        this.a = new aq(this.b.c, this.b.d, arVar.e == null ? null : arVar.e);
        this.a.b();
        this.a.a();
    }

    public final void a(a aVar) {
        this.a.a(this.b.c(), this.b.a(), this.b.b(), aVar);
    }
}
