package com.amap.api.mapcore.util;

/* compiled from: ThreadTask */
public abstract class kc implements Runnable {
    a d;

    /* compiled from: ThreadTask */
    interface a {
        void a(kc kcVar);

        void b(kc kcVar);

        void c(kc kcVar);
    }

    public abstract void runTask();

    public final void run() {
        try {
            if (this.d != null) {
                this.d.a(this);
            }
            if (!Thread.interrupted()) {
                runTask();
                if (!Thread.interrupted() && this.d != null) {
                    this.d.b(this);
                }
            }
        } catch (Throwable th) {
            gz.c(th, "ThreadTask", "run");
            th.printStackTrace();
        }
    }

    public final void cancelTask() {
        try {
            if (this.d != null) {
                this.d.c(this);
            }
        } catch (Throwable th) {
            gz.c(th, "ThreadTask", "cancelTask");
            th.printStackTrace();
        }
    }
}
