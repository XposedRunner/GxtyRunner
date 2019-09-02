package com.amap.api.mapcore.util;

import android.content.Context;
import dalvik.system.DexFile;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BaseLoader */
abstract class ho extends ClassLoader {
    protected final Context a;
    protected final Map<String, Class<?>> b = new HashMap();
    protected DexFile c = null;
    volatile boolean d = true;
    protected gk e;
    protected String f;
    protected volatile boolean g = false;
    protected volatile boolean h = false;

    public ho(Context context, gk gkVar, boolean z) {
        super(context.getClassLoader());
        this.a = context;
        this.e = gkVar;
    }

    public boolean a() {
        return this.c != null;
    }

    protected void b() {
        try {
            synchronized (this.b) {
                this.b.clear();
            }
            if (this.c != null) {
                if (this.h) {
                    synchronized (this.c) {
                        this.c.wait();
                    }
                }
                this.g = true;
                this.c.close();
            }
        } catch (Throwable th) {
            hw.a(th, "BaseLoader", "releaseDexFile()");
        }
    }
}
