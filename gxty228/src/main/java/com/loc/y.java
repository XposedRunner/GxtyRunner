package com.loc;

import android.content.Context;
import dalvik.system.DexFile;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BaseLoader */
abstract class y extends ClassLoader {
    protected final Context a;
    protected final Map<String, Class<?>> b = new HashMap();
    protected DexFile c = null;
    volatile boolean d = true;
    protected dk e;
    protected String f;
    protected volatile boolean g = false;
    protected volatile boolean h = false;

    public y(Context context, dk dkVar) {
        super(context.getClassLoader());
        this.a = context;
        this.e = dkVar;
    }

    public final boolean a() {
        return this.c != null;
    }

    protected final void b() {
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
            g.a(th, "BaseLoader", "releaseDexFile()");
        }
    }
}
