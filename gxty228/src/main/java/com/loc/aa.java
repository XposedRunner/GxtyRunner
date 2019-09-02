package com.loc;

import android.content.Context;
import android.text.TextUtils;
import dalvik.system.DexFile;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.List;

/* compiled from: DexFileManager */
public final class aa {

    /* compiled from: DexFileManager */
    public static class a {
        static af a(p pVar, String str) {
            List b = pVar.b(af.b(str), af.class);
            return (b == null || b.size() <= 0) ? null : (af) b.get(0);
        }

        public static List<af> a(p pVar, String str, String str2) {
            return pVar.b(af.b(str, str2), af.class);
        }
    }

    static String a(Context context) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "pngex";
    }

    static String a(Context context, p pVar, dk dkVar) {
        List b = pVar.b(af.b(dkVar.a(), "copy"), af.class);
        if (b == null || b.size() == 0) {
            return null;
        }
        ah.a(b);
        int i = 0;
        while (i < b.size()) {
            af afVar = (af) b.get(i);
            String a = afVar.a();
            if (ah.a(pVar, a, a(context, a), dkVar)) {
                try {
                    a(context, pVar, dkVar, a(context, afVar.a()), afVar.e());
                    return afVar.e();
                } catch (Throwable th) {
                    g.a(th, "FileManager", "loadAvailableD");
                }
            } else {
                c(context, pVar, afVar.a());
                i++;
            }
        }
        return null;
    }

    public static String a(Context context, String str) {
        return a(context) + File.separator + str;
    }

    static String a(Context context, String str, String str2) {
        return dh.b(str + str2 + df.u(context)) + ".jar";
    }

    static String a(String str) {
        return str + ".o";
    }

    static void a(Context context, dk dkVar) {
        try {
            a a = ag.b().a(dkVar);
            if (a != null && a.a) {
                synchronized (a) {
                    a.wait();
                }
            }
            a.b = true;
            String b = b(context, dkVar.a(), dkVar.b());
            if (!TextUtils.isEmpty(b)) {
                File file = new File(b);
                File parentFile = file.getParentFile();
                if (file.exists()) {
                    String a2 = a(context, a(file.getName()));
                    DexFile loadDex = DexFile.loadDex(b, a2, 0);
                    if (loadDex != null) {
                        loadDex.close();
                        Object obj = null;
                        p pVar = new p(context, ad.b());
                        af a3 = a.a(pVar, file.getName());
                        if (a3 != null) {
                            obj = a3.e();
                        }
                        File file2 = new File(a2);
                        if (!TextUtils.isEmpty(obj) && file2.exists()) {
                            a2 = dh.a(a2);
                            String name = file2.getName();
                            pVar.a(new com.loc.af.a(name, a2, dkVar.a(), dkVar.b(), obj).a("useod").a(), af.b(name));
                        }
                    }
                    a.b = false;
                } else if (parentFile != null && parentFile.exists()) {
                    c(context, dkVar.a(), dkVar.b());
                }
            }
        } catch (Throwable th) {
            g.a(th, "BaseLoader", "getInstanceByThread()");
        }
    }

    static void a(Context context, p pVar, dk dkVar, String str, String str2) throws Throwable {
        Throwable th;
        Closeable closeable;
        Closeable closeable2 = null;
        a aVar = null;
        a a;
        Closeable closeable3;
        try {
            String a2 = dkVar.a();
            a = ag.b().a(dkVar);
            if (a != null) {
                try {
                    if (a.a) {
                        synchronized (a) {
                            a.wait();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeable3 = null;
                    try {
                        ah.a(closeable3);
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                    try {
                        ah.a(closeable2);
                    } catch (Throwable th32) {
                        th32.printStackTrace();
                    }
                    if (a != null) {
                        try {
                            a.b = false;
                        } catch (Throwable th4) {
                        }
                    }
                    throw th;
                }
            }
            a.b = true;
            String a3 = a(context, a2, dkVar.b());
            a(context, pVar, a3);
            closeable3 = new FileInputStream(new File(str));
            try {
                closeable3.read(new byte[32]);
                File file = new File(b(context, a2, dkVar.b()));
                Closeable randomAccessFile = new RandomAccessFile(file, "rw");
                try {
                    Object obj = new byte[1024];
                    int i = 0;
                    while (true) {
                        int read = closeable3.read(obj);
                        if (read <= 0) {
                            break;
                        }
                        if (read == 1024) {
                            randomAccessFile.seek((long) i);
                            randomAccessFile.write(obj);
                        } else {
                            Object obj2 = new byte[read];
                            System.arraycopy(obj, 0, obj2, 0, read);
                            randomAccessFile.seek((long) i);
                            randomAccessFile.write(obj2);
                        }
                        i += read;
                    }
                    Object a4 = new com.loc.af.a(a3, dh.a(file.getAbsolutePath()), a2, dkVar.b(), str2).a("used").a();
                    pVar.a(a4, af.b(a4.a()));
                    try {
                        ah.a(closeable3);
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                    try {
                        ah.a(randomAccessFile);
                    } catch (Throwable th52) {
                        th52.printStackTrace();
                    }
                    if (a != null) {
                        try {
                            a.b = false;
                        } catch (Throwable th6) {
                        }
                    }
                } catch (Throwable th7) {
                    th52 = th7;
                    closeable2 = randomAccessFile;
                }
            } catch (Throwable th8) {
                th52 = th8;
                ah.a(closeable3);
                ah.a(closeable2);
                if (a != null) {
                    a.b = false;
                }
                throw th52;
            }
        } catch (Throwable th9) {
            th52 = th9;
            a = null;
            closeable3 = null;
            ah.a(closeable3);
            ah.a(closeable2);
            if (a != null) {
                a.b = false;
            }
            throw th52;
        }
    }

    static void a(Context context, p pVar, String str) {
        c(context, pVar, a(str));
        c(context, pVar, str);
    }

    static void a(Context context, File file, dk dkVar) {
        File parentFile = file.getParentFile();
        if (!file.exists() && parentFile != null && parentFile.exists()) {
            c(context, dkVar.a(), dkVar.b());
        }
    }

    static void a(p pVar, Context context, String str) {
        List<af> a = a.a(pVar, str, "used");
        if (a != null && a.size() > 0) {
            for (af afVar : a) {
                if (afVar != null && afVar.c().equals(str)) {
                    a(context, pVar, afVar.a());
                    List b = pVar.b(af.a(str, afVar.e()), af.class);
                    if (b != null && b.size() > 0) {
                        Object obj = (af) b.get(0);
                        obj.c("errorstatus");
                        pVar.a(obj, af.b(obj.a()));
                        File file = new File(a(context, obj.a()));
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
            }
        }
    }

    static String b(Context context, String str, String str2) {
        return a(context, a(context, str, str2));
    }

    static void b(Context context, String str) {
        p pVar = new p(context, ad.b());
        List a = a.a(pVar, str, "copy");
        ah.a(a);
        if (a != null && a.size() > 1) {
            int size = a.size();
            for (int i = 1; i < size; i++) {
                c(context, pVar, ((af) a.get(i)).a());
            }
        }
    }

    private static void c(Context context, p pVar, String str) {
        File file = new File(a(context, str));
        if (file.exists()) {
            file.delete();
        }
        pVar.a(af.b(str), af.class);
    }

    private static void c(final Context context, final String str, final String str2) {
        try {
            ag.b().a().submit(new Runnable() {
                public final void run() {
                    try {
                        p pVar = new p(context, ad.b());
                        List<af> b = pVar.b(af.a(str), af.class);
                        if (b != null && b.size() > 0) {
                            for (af afVar : b) {
                                if (!str2.equalsIgnoreCase(afVar.d())) {
                                    aa.c(context, pVar, afVar.a());
                                }
                            }
                        }
                    } catch (Throwable th) {
                        g.a(th, "FileManager", "clearUnSuitableV");
                    }
                }
            });
        } catch (Throwable th) {
        }
    }
}
