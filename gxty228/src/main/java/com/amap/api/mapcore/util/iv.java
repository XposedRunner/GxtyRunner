package com.amap.api.mapcore.util;

import java.net.Proxy;

/* compiled from: DownloadManager */
public class iv {
    private iw a;
    private iy b;

    /* compiled from: DownloadManager */
    public interface a {
        void a(Throwable th);

        void a(byte[] bArr, long j);

        void d();

        void e();
    }

    public iv(iy iyVar) {
        this(iyVar, 0, -1);
    }

    public iv(iy iyVar, long j, long j2) {
        Proxy proxy;
        this.b = iyVar;
        if (iyVar.h == null) {
            proxy = null;
        } else {
            proxy = iyVar.h;
        }
        this.a = new iw(this.b.f, this.b.g, proxy);
        this.a.b(j2);
        this.a.a(j);
    }

    public void a(a aVar) {
        this.a.a(this.b.c(), this.b.a(), this.b.b(), aVar);
    }

    public void a() {
        this.a.a();
    }
}
