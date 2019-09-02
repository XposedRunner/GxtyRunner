package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import java.lang.ref.WeakReference;

/* compiled from: AbstractImageWorker */
public abstract class er {
    private es a;
    private com.amap.api.mapcore.util.es.a b;
    protected boolean c = false;
    protected Resources d;
    private boolean e = false;
    private final Object f = new Object();
    private c g = null;

    /* compiled from: AbstractImageWorker */
    public interface c {
        void a();
    }

    /* compiled from: AbstractImageWorker */
    public class a extends ef<Boolean, Void, Bitmap> {
        final /* synthetic */ er d;
        private final WeakReference<com.amap.api.mapcore.util.cz.a> e;

        public a(er erVar, com.amap.api.mapcore.util.cz.a aVar) {
            this.d = erVar;
            this.e = new WeakReference(aVar);
        }

        protected Bitmap a(Boolean... boolArr) {
            try {
                boolean booleanValue = boolArr[0].booleanValue();
                Object obj = (com.amap.api.mapcore.util.cz.a) this.e.get();
                if (obj == null) {
                    return null;
                }
                Bitmap bitmap;
                Bitmap bitmap2;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(obj.a);
                stringBuilder.append("-");
                stringBuilder.append(obj.b);
                stringBuilder.append("-");
                stringBuilder.append(obj.c);
                String stringBuilder2 = stringBuilder.toString();
                synchronized (this.d.f) {
                    while (this.d.c && !d()) {
                        this.d.f.wait();
                    }
                }
                if (this.d.a == null || d() || e() == null || this.d.e) {
                    bitmap = null;
                } else {
                    bitmap = this.d.a.b(stringBuilder2);
                }
                if (!booleanValue || bitmap != null || d() || e() == null || this.d.e) {
                    bitmap2 = bitmap;
                } else {
                    synchronized (er.class) {
                        bitmap2 = this.d.a(obj);
                    }
                }
                if (bitmap2 == null) {
                    return bitmap2;
                }
                if (this.d.a == null) {
                    return bitmap2;
                }
                this.d.a.a(stringBuilder2, bitmap2);
                return bitmap2;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }

        protected void a(Bitmap bitmap) {
            try {
                if (d() || this.d.e) {
                    bitmap = null;
                }
                com.amap.api.mapcore.util.cz.a e = e();
                if (bitmap != null && !bitmap.isRecycled() && e != null) {
                    e.a(bitmap);
                    if (this.d.g != null) {
                        this.d.g.a();
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        protected void b(Bitmap bitmap) {
            super.b((Object) bitmap);
            synchronized (this.d.f) {
                try {
                    this.d.f.notifyAll();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }

        private com.amap.api.mapcore.util.cz.a e() {
            com.amap.api.mapcore.util.cz.a aVar = (com.amap.api.mapcore.util.cz.a) this.e.get();
            return this == er.c(aVar) ? aVar : null;
        }
    }

    /* compiled from: AbstractImageWorker */
    protected class b extends ef<Object, Void, Void> {
        final /* synthetic */ er d;

        protected b(er erVar) {
            this.d = erVar;
        }

        protected /* synthetic */ Object a(Object[] objArr) {
            return d(objArr);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected java.lang.Void d(java.lang.Object... r3) {
            /*
            r2 = this;
            r0 = 0;
            r0 = r3[r0];	 Catch:{ Throwable -> 0x0014 }
            r0 = (java.lang.Integer) r0;	 Catch:{ Throwable -> 0x0014 }
            r0 = r0.intValue();	 Catch:{ Throwable -> 0x0014 }
            switch(r0) {
                case 0: goto L_0x000e;
                case 1: goto L_0x0019;
                case 2: goto L_0x001f;
                case 3: goto L_0x0025;
                case 4: goto L_0x0034;
                default: goto L_0x000c;
            };	 Catch:{ Throwable -> 0x0014 }
        L_0x000c:
            r0 = 0;
            return r0;
        L_0x000e:
            r0 = r2.d;	 Catch:{ Throwable -> 0x0014 }
            r0.c();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
        L_0x0014:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x000c;
        L_0x0019:
            r0 = r2.d;	 Catch:{ Throwable -> 0x0014 }
            r0.b();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
        L_0x001f:
            r0 = r2.d;	 Catch:{ Throwable -> 0x0014 }
            r0.d();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
        L_0x0025:
            r1 = r2.d;	 Catch:{ Throwable -> 0x0014 }
            r0 = 1;
            r0 = r3[r0];	 Catch:{ Throwable -> 0x0014 }
            r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0014 }
            r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0014 }
            r1.b(r0);	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
        L_0x0034:
            r0 = r2.d;	 Catch:{ Throwable -> 0x0014 }
            r0.e();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.er.b.d(java.lang.Object[]):java.lang.Void");
        }
    }

    protected abstract Bitmap a(Object obj);

    protected er(Context context) {
        this.d = context.getResources();
    }

    public void a(boolean z, com.amap.api.mapcore.util.cz.a aVar) {
        if (aVar != null) {
            Bitmap bitmap = null;
            try {
                if (this.a != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(aVar.a);
                    stringBuilder.append("-");
                    stringBuilder.append(aVar.b);
                    stringBuilder.append("-");
                    stringBuilder.append(aVar.c);
                    bitmap = this.a.a(stringBuilder.toString());
                }
                if (bitmap != null) {
                    aVar.a(bitmap);
                    return;
                }
                a aVar2 = new a(this, aVar);
                aVar.j = aVar2;
                aVar2.a(ef.c, (Object[]) new Boolean[]{Boolean.valueOf(z)});
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a(com.amap.api.mapcore.util.es.a aVar) {
        this.b = aVar;
        this.a = es.a(this.b);
        new b(this).c(Integer.valueOf(1));
    }

    protected es a() {
        return this.a;
    }

    public static void a(com.amap.api.mapcore.util.cz.a aVar) {
        a c = c(aVar);
        if (c != null) {
            c.a(true);
        }
    }

    private static a c(com.amap.api.mapcore.util.cz.a aVar) {
        if (aVar != null) {
            return aVar.j;
        }
        return null;
    }

    public void a(boolean z) {
        synchronized (this.f) {
            this.c = z;
            if (!this.c) {
                try {
                    this.f.notifyAll();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    protected void b() {
        if (this.a != null) {
            this.a.a();
        }
    }

    protected void c() {
        if (this.a != null) {
            this.a.b();
        }
    }

    protected void d() {
        if (this.a != null) {
            this.a.c();
        }
    }

    protected void b(boolean z) {
        if (this.a != null) {
            this.a.a(z);
            this.a = null;
        }
    }

    protected void e() {
        if (this.a != null) {
            this.a.a(false);
            this.a.a();
        }
    }

    public void f() {
        new b(this).c(Integer.valueOf(0));
    }

    public void c(boolean z) {
        new b(this).c(Integer.valueOf(3), Boolean.valueOf(z));
    }

    public void a(c cVar) {
        this.g = cVar;
    }

    public void a(String str) {
        this.b.b(str);
        new b(this).c(Integer.valueOf(4));
    }
}
