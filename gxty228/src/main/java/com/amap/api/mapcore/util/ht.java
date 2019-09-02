package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.mapcore.util.hq.a;
import dalvik.system.DexFile;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Date;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* compiled from: DynamicLoader */
class ht extends ho {
    private PublicKey i = null;

    ht(Context context, gk gkVar, boolean z) throws Exception {
        super(context, gkVar, z);
        String b = hq.b(context, gkVar.a(), gkVar.b());
        String a = hq.a(context);
        b(b, a);
        File file = new File(b);
        if (hv.b().a(this.e).b) {
            throw new Exception("file is downloading");
        } else if (z) {
            a(b, a + File.separator + hq.a(file.getName()));
            b(context, b, a);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected java.lang.Class<?> findClass(java.lang.String r7) throws java.lang.ClassNotFoundException {
        /*
        r6 = this;
        r4 = 0;
        r0 = r6.c;	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        if (r0 != 0) goto L_0x0011;
    L_0x0005:
        r0 = new java.lang.ClassNotFoundException;	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        r0.<init>(r7);	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        throw r0;	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
    L_0x000b:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x000d }
    L_0x000d:
        r0 = move-exception;
        r6.h = r4;
        throw r0;
    L_0x0011:
        r1 = 0;
        r2 = r6.b;	 Catch:{ Throwable -> 0x0026, ClassNotFoundException -> 0x000b }
        monitor-enter(r2);	 Catch:{ Throwable -> 0x0026, ClassNotFoundException -> 0x000b }
        r0 = r6.b;	 Catch:{ all -> 0x0023 }
        r0 = r0.get(r7);	 Catch:{ all -> 0x0023 }
        r0 = (java.lang.Class) r0;	 Catch:{ all -> 0x0023 }
        monitor-exit(r2);	 Catch:{ all -> 0x0080 }
    L_0x001e:
        if (r0 == 0) goto L_0x0030;
    L_0x0020:
        r6.h = r4;
    L_0x0022:
        return r0;
    L_0x0023:
        r0 = move-exception;
    L_0x0024:
        monitor-exit(r2);	 Catch:{ all -> 0x0023 }
        throw r0;	 Catch:{ Throwable -> 0x0026, ClassNotFoundException -> 0x000b }
    L_0x0026:
        r0 = move-exception;
        r2 = "dLoader";
        r3 = "findCl";
        com.amap.api.mapcore.util.hw.a(r0, r2, r3);	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        r0 = r1;
        goto L_0x001e;
    L_0x0030:
        r0 = r6.g;	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        if (r0 == 0) goto L_0x0048;
    L_0x0034:
        r0 = new java.lang.ClassNotFoundException;	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        r0.<init>(r7);	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        throw r0;	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
    L_0x003a:
        r0 = move-exception;
        r1 = "dLoader";
        r2 = "findCl";
        com.amap.api.mapcore.util.hw.a(r0, r1, r2);	 Catch:{ all -> 0x000d }
        r0 = new java.lang.ClassNotFoundException;	 Catch:{ all -> 0x000d }
        r0.<init>(r7);	 Catch:{ all -> 0x000d }
        throw r0;	 Catch:{ all -> 0x000d }
    L_0x0048:
        r0 = 1;
        r6.h = r0;	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        r0 = r6.c;	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        r0 = r0.loadClass(r7, r6);	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        r1 = r6.c;	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        monitor-enter(r1);	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        r2 = r6.c;	 Catch:{ all -> 0x0065 }
        r2.notify();	 Catch:{ all -> 0x0065 }
        monitor-exit(r1);	 Catch:{ all -> 0x0065 }
        r1 = 0;
        r6.h = r1;	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        if (r0 != 0) goto L_0x0068;
    L_0x005f:
        r0 = new java.lang.ClassNotFoundException;	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        r0.<init>(r7);	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        throw r0;	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
    L_0x0065:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0065 }
        throw r0;	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
    L_0x0068:
        r2 = r6.b;	 Catch:{ Throwable -> 0x0077, ClassNotFoundException -> 0x000b }
        monitor-enter(r2);	 Catch:{ Throwable -> 0x0077, ClassNotFoundException -> 0x000b }
        r1 = r6.b;	 Catch:{ all -> 0x0074 }
        r1.put(r7, r0);	 Catch:{ all -> 0x0074 }
        monitor-exit(r2);	 Catch:{ all -> 0x0074 }
    L_0x0071:
        r6.h = r4;
        goto L_0x0022;
    L_0x0074:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0074 }
        throw r1;	 Catch:{ Throwable -> 0x0077, ClassNotFoundException -> 0x000b }
    L_0x0077:
        r1 = move-exception;
        r2 = "dLoader";
        r3 = "findCl";
        com.amap.api.mapcore.util.hw.a(r1, r2, r3);	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        goto L_0x0071;
    L_0x0080:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ht.findClass(java.lang.String):java.lang.Class<?>");
    }

    void a(String str, String str2) throws Exception {
        a aVar = null;
        try {
            if (this.c == null) {
                aVar = hv.b().a(this.e);
                if (aVar != null) {
                    aVar.a = true;
                }
                b();
                if (aVar.b) {
                    throw new Exception("file is downloading");
                }
                this.c = DexFile.loadDex(str, str2, 0);
                if (aVar != null) {
                    try {
                        aVar.a = false;
                        synchronized (aVar) {
                            aVar.notify();
                        }
                    } catch (Throwable th) {
                    }
                }
            } else if (aVar != null) {
                try {
                    aVar.a = false;
                    synchronized (aVar) {
                        aVar.notify();
                    }
                } catch (Throwable th2) {
                }
            }
        } catch (Throwable th3) {
            if (aVar != null) {
                try {
                    aVar.a = false;
                    synchronized (aVar) {
                        aVar.notify();
                    }
                } catch (Throwable th4) {
                }
            }
        }
    }

    private void c() {
        if (this.i == null) {
            this.i = hw.a();
        }
    }

    private void a(JarFile jarFile, JarEntry jarEntry) throws IOException {
        try {
            Closeable inputStream = jarFile.getInputStream(jarEntry);
            do {
            } while (inputStream.read(new byte[8192]) > 0);
            try {
                hw.a(inputStream);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    private boolean a(File file, Certificate[] certificateArr) {
        try {
            if (certificateArr.length > 0) {
                int length = certificateArr.length - 1;
                if (length >= 0) {
                    certificateArr[length].verify(this.i);
                    return true;
                }
            }
        } catch (Throwable e) {
            hw.a(e, "DyLoader", "check");
        }
        return false;
    }

    private boolean a(File file) {
        Throwable th;
        Throwable th2;
        boolean z = false;
        JarFile jarFile;
        try {
            c();
            jarFile = new JarFile(file);
            try {
                JarEntry jarEntry = jarFile.getJarEntry("classes.dex");
                if (jarEntry != null) {
                    a(jarFile, jarEntry);
                    Certificate[] certificates = jarEntry.getCertificates();
                    if (certificates != null) {
                        z = a(file, certificates);
                        if (jarFile != null) {
                            try {
                                jarFile.close();
                            } catch (Throwable th3) {
                            }
                        }
                    } else if (jarFile != null) {
                        try {
                            jarFile.close();
                        } catch (Throwable th4) {
                        }
                    }
                } else if (jarFile != null) {
                    try {
                        jarFile.close();
                    } catch (Throwable th5) {
                    }
                }
            } catch (Throwable th6) {
                th = th6;
                try {
                    hw.a(th, "DyLoader", "verify");
                    if (jarFile != null) {
                        try {
                            jarFile.close();
                        } catch (Throwable th7) {
                        }
                    }
                    return z;
                } catch (Throwable th8) {
                    th2 = th8;
                    if (jarFile != null) {
                        try {
                            jarFile.close();
                        } catch (Throwable th9) {
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th10) {
            th2 = th10;
            jarFile = null;
            if (jarFile != null) {
                jarFile.close();
            }
            throw th2;
        }
        return z;
    }

    private boolean a(he heVar, gk gkVar, String str) {
        if (a(new File(str))) {
            return hw.a(heVar, hq.a(this.a, gkVar.a(), gkVar.b()), str, gkVar);
        }
        return false;
    }

    private boolean a(he heVar, String str, String str2) {
        String a = hq.a(this.a, str);
        if (hw.a(heVar, str, a, this.e)) {
            return true;
        }
        if (a.a(heVar, str) != null) {
            return false;
        }
        if (!TextUtils.isEmpty(this.f)) {
            a.a(heVar, new hu.a(str, gf.a(a), this.e.a(), this.e.b(), str2).a("useod").a(), hu.b(str));
        }
        return true;
    }

    private void b(String str, String str2) throws Exception {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new Exception("dexPath or dexOutputDir is null.");
        }
    }

    private void b(final Context context, final String str, final String str2) {
        try {
            hv.b().a().submit(new Runnable(this) {
                final /* synthetic */ ht d;

                public void run() {
                    try {
                        this.d.a(context, str, str2);
                    } catch (Throwable th) {
                        hw.a(th, "dLoader", "run()");
                    }
                }
            });
        } catch (Throwable th) {
        }
    }

    private void a(he heVar, File file) {
        hu a = a.a(heVar, file.getName());
        if (a != null) {
            this.f = a.e();
        }
    }

    private void b(he heVar, File file) {
        this.d = false;
        hq.a(this.a, heVar, file.getName());
        Object a = hq.a(this.a, heVar, this.e);
        if (!TextUtils.isEmpty(a)) {
            this.f = a;
            hq.a(this.a, this.e);
        }
    }

    void a(Context context, String str, String str2) throws Exception {
        new Date().getTime();
        try {
            he heVar = new he(context, hs.a());
            File file = new File(str);
            a(heVar, file);
            if (!a(heVar, this.e, file.getAbsolutePath())) {
                b(heVar, file);
            }
            if (file.exists()) {
                if (new File(str2 + File.separator + hq.a(file.getName())).exists() && !a(heVar, hq.a(file.getName()), this.f)) {
                    hq.a(this.a, this.e);
                }
                new Date().getTime();
            }
        } catch (Throwable th) {
            hw.a(th, "dLoader", "verifyD()");
        }
    }
}
