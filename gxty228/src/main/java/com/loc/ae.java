package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.loc.aa.a;
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
final class ae extends y {
    private PublicKey i = null;

    ae(final Context context, dk dkVar) throws Exception {
        a aVar = null;
        super(context, dkVar);
        final String b = aa.b(context, dkVar.a(), dkVar.b());
        final String a = aa.a(context);
        if (TextUtils.isEmpty(b) || TextUtils.isEmpty(a)) {
            throw new Exception("dexPath or dexOutputDir is null.");
        }
        File file = new File(b);
        if (ag.b().a(this.e).b) {
            throw new Exception("file is downloading");
        }
        String str = a + File.separator + aa.a(file.getName());
        try {
            if (this.c == null) {
                aVar = ag.b().a(this.e);
                if (aVar != null) {
                    aVar.a = true;
                }
                b();
                if (aVar.b) {
                    throw new Exception("file is downloading");
                }
                this.c = DexFile.loadDex(b, str, 0);
                if (aVar != null) {
                    try {
                        aVar.a = false;
                        synchronized (aVar) {
                            aVar.notify();
                        }
                    } catch (Throwable th) {
                    }
                }
            }
            try {
                ag.b().a().submit(new Runnable(this) {
                    final /* synthetic */ ae d;

                    public final void run() {
                        try {
                            this.d.a(context, b, a);
                        } catch (Throwable th) {
                            g.a(th, "dLoader", "run()");
                        }
                    }
                });
            } catch (Throwable th2) {
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

    private static void a(JarFile jarFile, JarEntry jarEntry) throws IOException {
        try {
            Closeable inputStream = jarFile.getInputStream(jarEntry);
            do {
            } while (inputStream.read(new byte[8192]) > 0);
            try {
                ah.a(inputStream);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    private boolean a(File file) {
        Throwable th;
        Throwable th2;
        boolean z = false;
        JarFile jarFile = null;
        try {
            if (this.i == null) {
                this.i = ah.a();
            }
            JarFile jarFile2 = new JarFile(file);
            try {
                JarEntry jarEntry = jarFile2.getJarEntry("classes.dex");
                if (jarEntry == null) {
                    try {
                        jarFile2.close();
                    } catch (Throwable th3) {
                    }
                } else {
                    a(jarFile2, jarEntry);
                    Certificate[] certificates = jarEntry.getCertificates();
                    if (certificates == null) {
                        try {
                            jarFile2.close();
                        } catch (Throwable th4) {
                        }
                    } else {
                        z = a(certificates);
                        try {
                            jarFile2.close();
                        } catch (Throwable th5) {
                        }
                    }
                }
            } catch (Throwable th6) {
                th2 = th6;
                jarFile = jarFile2;
                if (jarFile != null) {
                    jarFile.close();
                }
                throw th2;
            }
        } catch (Throwable th7) {
            th = th7;
            g.a(th, "DyLoader", "verify");
            if (jarFile != null) {
                jarFile.close();
            }
            return z;
        }
        return z;
    }

    private boolean a(Certificate[] certificateArr) {
        try {
            if (certificateArr.length > 0) {
                int length = certificateArr.length - 1;
                if (length >= 0) {
                    certificateArr[length].verify(this.i);
                    return true;
                }
            }
        } catch (Throwable e) {
            g.a(e, "DyLoader", "check");
        }
        return false;
    }

    final void a(Context context, String str, String str2) throws Exception {
        Object obj = null;
        new Date().getTime();
        try {
            p pVar = new p(context, ad.b());
            File file = new File(str);
            af a = a.a(pVar, file.getName());
            if (a != null) {
                this.f = a.e();
            }
            dk dkVar = this.e;
            String absolutePath = file.getAbsolutePath();
            if (!(a(new File(absolutePath)) ? ah.a(pVar, aa.a(this.a, dkVar.a(), dkVar.b()), absolutePath, dkVar) : false)) {
                this.d = false;
                aa.a(this.a, pVar, file.getName());
                Object a2 = aa.a(this.a, pVar, this.e);
                if (!TextUtils.isEmpty(a2)) {
                    this.f = a2;
                    aa.a(this.a, this.e);
                }
            }
            if (file.exists()) {
                if (new File(str2 + File.separator + aa.a(file.getName())).exists()) {
                    String a3 = aa.a(file.getName());
                    String str3 = this.f;
                    String a4 = aa.a(this.a, a3);
                    if (!ah.a(pVar, a3, a4, this.e)) {
                        if (a.a(pVar, a3) == null) {
                            if (!TextUtils.isEmpty(this.f)) {
                                pVar.a(new af.a(a3, dh.a(a4), this.e.a(), this.e.b(), str3).a("useod").a(), af.b(a3));
                            }
                        }
                        if (obj == null) {
                            aa.a(this.a, this.e);
                        }
                    }
                    obj = 1;
                    if (obj == null) {
                        aa.a(this.a, this.e);
                    }
                }
                new Date().getTime();
            }
        } catch (Throwable th) {
            g.a(th, "dLoader", "verifyD()");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final java.lang.Class<?> findClass(java.lang.String r7) throws java.lang.ClassNotFoundException {
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
        monitor-exit(r2);	 Catch:{ Throwable -> 0x0026, ClassNotFoundException -> 0x000b }
        throw r0;	 Catch:{ Throwable -> 0x0026, ClassNotFoundException -> 0x000b }
    L_0x0026:
        r0 = move-exception;
        r2 = "dLoader";
        r3 = "findCl";
        com.loc.g.a(r0, r2, r3);	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
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
        com.loc.g.a(r0, r1, r2);	 Catch:{ all -> 0x000d }
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
        monitor-exit(r1);	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
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
        monitor-exit(r2);	 Catch:{ Throwable -> 0x0077, ClassNotFoundException -> 0x000b }
        throw r1;	 Catch:{ Throwable -> 0x0077, ClassNotFoundException -> 0x000b }
    L_0x0077:
        r1 = move-exception;
        r2 = "dLoader";
        r3 = "findCl";
        com.loc.g.a(r1, r2, r3);	 Catch:{ ClassNotFoundException -> 0x000b, Throwable -> 0x003a }
        goto L_0x0071;
    L_0x0080:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ae.findClass(java.lang.String):java.lang.Class<?>");
    }
}
