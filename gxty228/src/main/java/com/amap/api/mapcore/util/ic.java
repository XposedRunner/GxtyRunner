package com.amap.api.mapcore.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: IExecutor */
public class ic {
    private ExecutorService a = Executors.newSingleThreadExecutor(new ib(null));

    public void a(Runnable runnable) {
        if (runnable != null) {
            this.a.execute(runnable);
        }
    }

    public void a() {
        if (this.a != null) {
            this.a.execute(new ia(this));
        }
    }
}
