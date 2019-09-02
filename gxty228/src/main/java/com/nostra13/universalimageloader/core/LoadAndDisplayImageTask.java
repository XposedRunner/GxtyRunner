package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.b.b.a;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.FailReason.FailType;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.assist.c;
import com.nostra13.universalimageloader.core.d.b;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

final class LoadAndDisplayImageTask implements a, Runnable {
    final String a;
    final com.nostra13.universalimageloader.core.c.a b;
    final c c;
    final com.nostra13.universalimageloader.core.d.a d;
    final b e;
    private final f f;
    private final g g;
    private final Handler h;
    private final e i;
    private final ImageDownloader j;
    private final ImageDownloader k;
    private final ImageDownloader l;
    private final com.nostra13.universalimageloader.core.a.b m;
    private final String n;
    private final c o;
    private final boolean p;
    private LoadedFrom q = LoadedFrom.NETWORK;

    class TaskCancelledException extends Exception {
        TaskCancelledException() {
        }
    }

    public LoadAndDisplayImageTask(f fVar, g gVar, Handler handler) {
        this.f = fVar;
        this.g = gVar;
        this.h = handler;
        this.i = fVar.a;
        this.j = this.i.p;
        this.k = this.i.s;
        this.l = this.i.t;
        this.m = this.i.q;
        this.a = gVar.a;
        this.n = gVar.b;
        this.b = gVar.c;
        this.o = gVar.d;
        this.c = gVar.e;
        this.d = gVar.f;
        this.e = gVar.g;
        this.p = this.c.s();
    }

    public void run() {
        if (!b() && !c()) {
            ReentrantLock reentrantLock = this.g.h;
            com.nostra13.universalimageloader.b.c.a("Start display image task [%s]", this.n);
            if (reentrantLock.isLocked()) {
                com.nostra13.universalimageloader.b.c.a("Image already is loading. Waiting... [%s]", this.n);
            }
            reentrantLock.lock();
            try {
                i();
                Bitmap a = this.i.n.a(this.n);
                if (a == null || a.isRecycled()) {
                    a = d();
                    if (a != null) {
                        i();
                        o();
                        if (this.c.d()) {
                            com.nostra13.universalimageloader.b.c.a("PreProcess image before caching in memory [%s]", this.n);
                            a = this.c.o().a(a);
                            if (a == null) {
                                com.nostra13.universalimageloader.b.c.d("Pre-processor returned null [%s]", this.n);
                            }
                        }
                        if (a != null && this.c.h()) {
                            com.nostra13.universalimageloader.b.c.a("Cache image in memory [%s]", this.n);
                            this.i.n.a(this.n, a);
                        }
                    } else {
                        return;
                    }
                }
                this.q = LoadedFrom.MEMORY_CACHE;
                com.nostra13.universalimageloader.b.c.a("...Get cached bitmap from memory after waiting. [%s]", this.n);
                if (a != null && this.c.e()) {
                    com.nostra13.universalimageloader.b.c.a("PostProcess image before displaying [%s]", this.n);
                    a = this.c.p().a(a);
                    if (a == null) {
                        com.nostra13.universalimageloader.b.c.d("Post-processor returned null [%s]", this.n);
                    }
                }
                i();
                o();
                reentrantLock.unlock();
                a(new b(a, this.g, this.f, this.q), this.p, this.h, this.f);
            } catch (TaskCancelledException e) {
                g();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    private boolean b() {
        AtomicBoolean a = this.f.a();
        if (a.get()) {
            synchronized (this.f.b()) {
                if (a.get()) {
                    com.nostra13.universalimageloader.b.c.a("ImageLoader is paused. Waiting...  [%s]", this.n);
                    try {
                        this.f.b().wait();
                        com.nostra13.universalimageloader.b.c.a(".. Resume loading [%s]", this.n);
                    } catch (InterruptedException e) {
                        com.nostra13.universalimageloader.b.c.d("Task was interrupted [%s]", this.n);
                        return true;
                    }
                }
            }
        }
        return j();
    }

    private boolean c() {
        if (!this.c.f()) {
            return false;
        }
        com.nostra13.universalimageloader.b.c.a("Delay %d ms before loading...  [%s]", Integer.valueOf(this.c.l()), this.n);
        try {
            Thread.sleep((long) this.c.l());
            return j();
        } catch (InterruptedException e) {
            com.nostra13.universalimageloader.b.c.d("Task was interrupted [%s]", this.n);
            return true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap d() throws com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException {
        /*
        r7 = this;
        r1 = 0;
        r0 = r7.i;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r0.o;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r2 = r7.a;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r0.a(r2);	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        if (r0 == 0) goto L_0x00d9;
    L_0x000d:
        r2 = r0.exists();	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        if (r2 == 0) goto L_0x00d9;
    L_0x0013:
        r2 = r0.length();	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r4 = 0;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x00d9;
    L_0x001d:
        r2 = "Load image from disk cache [%s]";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r4 = 0;
        r5 = r7.n;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r3[r4] = r5;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        com.nostra13.universalimageloader.b.c.a(r2, r3);	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r2 = com.nostra13.universalimageloader.core.assist.LoadedFrom.DISC_CACHE;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r7.q = r2;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r7.i();	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r2 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r0.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r2.wrap(r0);	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r7.a(r0);	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
    L_0x003f:
        if (r0 == 0) goto L_0x004d;
    L_0x0041:
        r2 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 <= 0) goto L_0x004d;
    L_0x0047:
        r2 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 > 0) goto L_0x009f;
    L_0x004d:
        r2 = "Load image from network [%s]";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r4 = 0;
        r5 = r7.n;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3[r4] = r5;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        com.nostra13.universalimageloader.b.c.a(r2, r3);	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r2 = com.nostra13.universalimageloader.core.assist.LoadedFrom.NETWORK;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r7.q = r2;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r2 = r7.a;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r7.c;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.i();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r3 == 0) goto L_0x0084;
    L_0x0068:
        r3 = r7.e();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r3 == 0) goto L_0x0084;
    L_0x006e:
        r3 = r7.i;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.o;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r4 = r7.a;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.a(r4);	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r3 == 0) goto L_0x0084;
    L_0x007a:
        r2 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r2 = r2.wrap(r3);	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
    L_0x0084:
        r7.i();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r0 = r7.a(r2);	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r0 == 0) goto L_0x0099;
    L_0x008d:
        r2 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 <= 0) goto L_0x0099;
    L_0x0093:
        r2 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 > 0) goto L_0x009f;
    L_0x0099:
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.DECODING_ERROR;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = 0;
        r7.a(r2, r3);	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
    L_0x009f:
        return r0;
    L_0x00a0:
        r0 = move-exception;
        r0 = r1;
    L_0x00a2:
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.NETWORK_DENIED;
        r7.a(r2, r1);
        goto L_0x009f;
    L_0x00a8:
        r0 = move-exception;
        throw r0;
    L_0x00aa:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00ae:
        com.nostra13.universalimageloader.b.c.a(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.IO_ERROR;
        r7.a(r2, r1);
        goto L_0x009f;
    L_0x00b7:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00bb:
        com.nostra13.universalimageloader.b.c.a(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.OUT_OF_MEMORY;
        r7.a(r2, r1);
        goto L_0x009f;
    L_0x00c4:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00c8:
        com.nostra13.universalimageloader.b.c.a(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.UNKNOWN;
        r7.a(r2, r1);
        goto L_0x009f;
    L_0x00d1:
        r1 = move-exception;
        goto L_0x00c8;
    L_0x00d3:
        r1 = move-exception;
        goto L_0x00bb;
    L_0x00d5:
        r1 = move-exception;
        goto L_0x00ae;
    L_0x00d7:
        r2 = move-exception;
        goto L_0x00a2;
    L_0x00d9:
        r0 = r1;
        goto L_0x003f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.d():android.graphics.Bitmap");
    }

    private Bitmap a(String str) throws IOException {
        String str2 = str;
        return this.m.a(new com.nostra13.universalimageloader.core.a.c(this.n, str2, this.a, this.o, this.b.c(), h(), this.c));
    }

    private boolean e() throws TaskCancelledException {
        com.nostra13.universalimageloader.b.c.a("Cache image on disk [%s]", this.n);
        try {
            boolean f = f();
            if (!f) {
                return f;
            }
            int i = this.i.d;
            int i2 = this.i.e;
            if (i <= 0 && i2 <= 0) {
                return f;
            }
            com.nostra13.universalimageloader.b.c.a("Resize image in disk cache [%s]", this.n);
            b(i, i2);
            return f;
        } catch (Throwable e) {
            com.nostra13.universalimageloader.b.c.a(e);
            return false;
        }
    }

    private boolean f() throws IOException {
        boolean z = false;
        Closeable a = h().a(this.a, this.c.n());
        if (a == null) {
            com.nostra13.universalimageloader.b.c.d("No stream for image [%s]", this.n);
        } else {
            try {
                z = this.i.o.a(this.a, a, this);
            } finally {
                com.nostra13.universalimageloader.b.b.a(a);
            }
        }
        return z;
    }

    private boolean b(int i, int i2) throws IOException {
        File a = this.i.o.a(this.a);
        if (a != null && a.exists()) {
            Bitmap a2 = this.m.a(new com.nostra13.universalimageloader.core.a.c(this.n, Scheme.FILE.wrap(a.getAbsolutePath()), this.a, new c(i, i2), ViewScaleType.FIT_INSIDE, h(), new c.a().a(this.c).a(ImageScaleType.IN_SAMPLE_INT).a()));
            if (!(a2 == null || this.i.f == null)) {
                com.nostra13.universalimageloader.b.c.a("Process image before cache on disk [%s]", this.n);
                a2 = this.i.f.a(a2);
                if (a2 == null) {
                    com.nostra13.universalimageloader.b.c.d("Bitmap processor for disk cache returned null [%s]", this.n);
                }
            }
            Bitmap bitmap = a2;
            if (bitmap != null) {
                boolean a3 = this.i.o.a(this.a, bitmap);
                bitmap.recycle();
                return a3;
            }
        }
        return false;
    }

    public boolean a(int i, int i2) {
        return this.p || c(i, i2);
    }

    private boolean c(final int i, final int i2) {
        if (p() || j()) {
            return false;
        }
        if (this.e != null) {
            a(new Runnable(this) {
                final /* synthetic */ LoadAndDisplayImageTask c;

                public void run() {
                    this.c.e.a(this.c.a, this.c.b.d(), i, i2);
                }
            }, false, this.h, this.f);
        }
        return true;
    }

    private void a(final FailType failType, final Throwable th) {
        if (!this.p && !p() && !j()) {
            a(new Runnable(this) {
                final /* synthetic */ LoadAndDisplayImageTask c;

                public void run() {
                    if (this.c.c.c()) {
                        this.c.b.a(this.c.c.c(this.c.i.a));
                    }
                    this.c.d.a(this.c.a, this.c.b.d(), new FailReason(failType, th));
                }
            }, false, this.h, this.f);
        }
    }

    private void g() {
        if (!this.p && !p()) {
            a(new Runnable(this) {
                final /* synthetic */ LoadAndDisplayImageTask a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.d.b(this.a.a, this.a.b.d());
                }
            }, false, this.h, this.f);
        }
    }

    private ImageDownloader h() {
        if (this.f.c()) {
            return this.k;
        }
        if (this.f.d()) {
            return this.l;
        }
        return this.j;
    }

    private void i() throws TaskCancelledException {
        k();
        m();
    }

    private boolean j() {
        return l() || n();
    }

    private void k() throws TaskCancelledException {
        if (l()) {
            throw new TaskCancelledException();
        }
    }

    private boolean l() {
        if (!this.b.e()) {
            return false;
        }
        com.nostra13.universalimageloader.b.c.a("ImageAware was collected by GC. Task is cancelled. [%s]", this.n);
        return true;
    }

    private void m() throws TaskCancelledException {
        if (n()) {
            throw new TaskCancelledException();
        }
    }

    private boolean n() {
        if (!(!this.n.equals(this.f.a(this.b)))) {
            return false;
        }
        com.nostra13.universalimageloader.b.c.a("ImageAware is reused for another image. Task is cancelled. [%s]", this.n);
        return true;
    }

    private void o() throws TaskCancelledException {
        if (p()) {
            throw new TaskCancelledException();
        }
    }

    private boolean p() {
        if (!Thread.interrupted()) {
            return false;
        }
        com.nostra13.universalimageloader.b.c.a("Task was interrupted [%s]", this.n);
        return true;
    }

    String a() {
        return this.a;
    }

    static void a(Runnable runnable, boolean z, Handler handler, f fVar) {
        if (z) {
            runnable.run();
        } else if (handler == null) {
            fVar.a(runnable);
        } else {
            handler.post(runnable);
        }
    }
}
