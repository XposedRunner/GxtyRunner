package com.amap.api.mapcore.util;

import java.util.Iterator;
import java.util.Vector;

/* compiled from: TimeoutDownloadManager */
public class jc extends iv {
    private static a e;
    private int a = 20000;
    private b b = null;
    private com.amap.api.mapcore.util.iv.a c = null;
    private boolean d = true;

    /* compiled from: TimeoutDownloadManager */
    private interface c {
        void a();
    }

    /* compiled from: TimeoutDownloadManager */
    private static class a extends Thread {
        Vector<jc> a = new Vector();
        private int b;

        public a(String str, int i) {
            super(str);
            this.b = i;
        }

        public void a(jc jcVar) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                if (((jc) it.next()) == jcVar) {
                    return;
                }
            }
            this.a.add(jcVar);
        }

        public void b(jc jcVar) {
            if (jcVar != null) {
                this.a.remove(jcVar);
            }
        }

        public void run() {
            while (this.a != null && this.a.size() != 0) {
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    Iterator it = this.a.iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        int i2;
                        jc jcVar = (jc) it.next();
                        if (currentTimeMillis - jcVar.b() >= ((long) this.b)) {
                            jcVar.c();
                            jcVar.a(false);
                        }
                        if (jcVar.d) {
                            i2 = i + 1;
                        } else {
                            i2 = i;
                        }
                        i = i2;
                    }
                    if (i > 0) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                        }
                    } else if (this.a != null) {
                        this.a.clear();
                        return;
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    return;
                }
            }
        }
    }

    /* compiled from: TimeoutDownloadManager */
    private static class b implements com.amap.api.mapcore.util.iv.a {
        private com.amap.api.mapcore.util.iv.a a;
        private long b;
        private c c;

        public void a(c cVar) {
            this.c = cVar;
        }

        public void a(long j) {
            this.b = j;
        }

        public long a() {
            return this.b;
        }

        public b(com.amap.api.mapcore.util.iv.a aVar) {
            this.a = aVar;
        }

        public void a(byte[] bArr, long j) {
            this.b = System.currentTimeMillis();
            this.a.a(bArr, j);
        }

        public void d() {
            this.b = System.currentTimeMillis();
            this.a.d();
            b();
        }

        public void e() {
            this.b = System.currentTimeMillis();
            this.a.e();
            b();
        }

        public void a(Throwable th) {
            this.b = System.currentTimeMillis();
            this.a.a(th);
            b();
        }

        private void b() {
            if (this.c != null) {
                this.c.a();
            }
        }
    }

    public jc(iy iyVar) {
        super(iyVar);
    }

    public jc(iy iyVar, long j, long j2) {
        super(iyVar, j, j2);
    }

    public void a(boolean z) {
        this.d = z;
    }

    public long b() {
        if (this.b == null) {
            return 0;
        }
        return this.b.a();
    }

    public void c() {
        this.c.a(new Exception(" ReadTimeoutException by TimeoutDownloadManager"));
        super.a();
    }

    public void a(com.amap.api.mapcore.util.iv.a aVar) {
        if (this.b == null) {
            this.c = aVar;
            this.b = b(aVar);
            this.b.a(new c(this) {
                final /* synthetic */ jc a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.c(this.a);
                }
            });
        }
        this.b.a(System.currentTimeMillis());
        b(this);
        super.a(this.b);
    }

    private b b(com.amap.api.mapcore.util.iv.a aVar) {
        if (aVar == null) {
            return null;
        }
        return new b(aVar);
    }

    private void b(jc jcVar) {
        if (e == null || !e.isAlive()) {
            e = new a("DOWNLOAD_DAEMON_THREAD_NAME", this.a);
            e.start();
        }
        e.a(jcVar);
    }

    private void c(jc jcVar) {
        if (e != null && e.isAlive()) {
            e.b(jcVar);
        }
    }
}
