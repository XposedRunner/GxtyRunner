package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import dalvik.system.DexFile;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.List;

/* compiled from: DexFileManager */
public class hq {

    /* compiled from: DexFileManager */
    public static class a {
        public static void a(he heVar, hu huVar, String str) {
            heVar.a((Object) huVar, str);
        }

        static hu a(he heVar, String str) {
            List b = heVar.b(hu.b(str), hu.class);
            if (b == null || b.size() <= 0) {
                return null;
            }
            return (hu) b.get(0);
        }

        public static List<hu> a(he heVar, String str, String str2) {
            return heVar.b(hu.b(str, str2), hu.class);
        }
    }

    static String a(String str) {
        return str + ".o";
    }

    static String a(Context context, String str, String str2) {
        return gf.b(str + str2 + gd.u(context)) + ".jar";
    }

    static String b(Context context, String str, String str2) {
        return a(context, a(context, str, str2));
    }

    public static String a(Context context, String str) {
        return a(context) + File.separator + str;
    }

    static String a(Context context) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "pngex";
    }

    static void a(Context context, gk gkVar) {
        try {
            a a = hv.b().a(gkVar);
            if (a != null && a.a) {
                synchronized (a) {
                    a.wait();
                }
            }
            a.b = true;
            String b = b(context, gkVar.a(), gkVar.b());
            if (!TextUtils.isEmpty(b)) {
                File file = new File(b);
                File parentFile = file.getParentFile();
                if (file.exists()) {
                    String a2 = a(context, a(file.getName()));
                    DexFile loadDex = DexFile.loadDex(b, a2, 0);
                    if (loadDex != null) {
                        loadDex.close();
                        a(context, file, a2, gkVar);
                    }
                    a.b = false;
                } else if (parentFile != null && parentFile.exists()) {
                    c(context, gkVar.a(), gkVar.b());
                }
            }
        } catch (Throwable th) {
            hw.a(th, "BaseLoader", "getInstanceByThread()");
        }
    }

    static void b(Context context, String str) {
        he heVar = new he(context, hs.a());
        List a = a.a(heVar, str, "copy");
        hw.a(a);
        if (a != null && a.size() > 1) {
            int size = a.size();
            for (int i = 1; i < size; i++) {
                c(context, heVar, ((hu) a.get(i)).a());
            }
        }
    }

    static void a(Context context, he heVar, String str) {
        c(context, heVar, a(str));
        c(context, heVar, str);
    }

    static void c(final Context context, final String str, final String str2) {
        try {
            hv.b().a().submit(new Runnable() {
                public void run() {
                    try {
                        he heVar = new he(context, hs.a());
                        List<hu> b = heVar.b(hu.a(str), hu.class);
                        if (b != null && b.size() > 0) {
                            for (hu huVar : b) {
                                if (!str2.equalsIgnoreCase(huVar.d())) {
                                    hq.c(context, heVar, huVar.a());
                                }
                            }
                        }
                    } catch (Throwable th) {
                        hw.a(th, "FileManager", "clearUnSuitableV");
                    }
                }
            });
        } catch (Throwable th) {
        }
    }

    static void a(he heVar, Context context, String str) {
        List<hu> a = a.a(heVar, str, "used");
        if (a != null && a.size() > 0) {
            for (hu huVar : a) {
                hu huVar2;
                if (huVar2 != null && huVar2.c().equals(str)) {
                    a(context, heVar, huVar2.a());
                    List b = heVar.b(hu.a(str, huVar2.e()), hu.class);
                    if (b != null && b.size() > 0) {
                        huVar2 = (hu) b.get(0);
                        huVar2.c("errorstatus");
                        a.a(heVar, huVar2, hu.b(huVar2.a()));
                        File file = new File(a(context, huVar2.a()));
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
            }
        }
    }

    static void a(Context context, he heVar, gk gkVar, String str, String str2) throws Throwable {
        Throwable th;
        Closeable closeable;
        Closeable closeable2;
        Closeable closeable3 = null;
        a aVar = null;
        a a;
        try {
            String a2 = gkVar.a();
            a = hv.b().a(gkVar);
            if (a != null) {
                try {
                    if (a.a) {
                        synchronized (a) {
                            a.wait();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeable2 = null;
                    try {
                        hw.a(closeable2);
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                    try {
                        hw.a(closeable3);
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
            String a3 = a(context, a2, gkVar.b());
            a(context, heVar, a3);
            closeable2 = new FileInputStream(new File(str));
            try {
                closeable2.read(new byte[32]);
                File file = new File(b(context, a2, gkVar.b()));
                Closeable randomAccessFile = new RandomAccessFile(file, "rw");
                try {
                    Object obj = new byte[1024];
                    int i = 0;
                    while (true) {
                        int read = closeable2.read(obj);
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
                    hu a4 = new com.amap.api.mapcore.util.hu.a(a3, gf.a(file.getAbsolutePath()), a2, gkVar.b(), str2).a("used").a();
                    a.a(heVar, a4, hu.b(a4.a()));
                    try {
                        hw.a(closeable2);
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                    try {
                        hw.a(randomAccessFile);
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
                    closeable3 = randomAccessFile;
                }
            } catch (Throwable th8) {
                th52 = th8;
                hw.a(closeable2);
                hw.a(closeable3);
                if (a != null) {
                    a.b = false;
                }
                throw th52;
            }
        } catch (Throwable th9) {
            th52 = th9;
            a = null;
            closeable2 = null;
            hw.a(closeable2);
            hw.a(closeable3);
            if (a != null) {
                a.b = false;
            }
            throw th52;
        }
    }

    static String a(Context context, he heVar, gk gkVar) {
        List b = heVar.b(hu.b(gkVar.a(), "copy"), hu.class);
        if (b == null || b.size() == 0) {
            return null;
        }
        hw.a(b);
        int i = 0;
        while (i < b.size()) {
            hu huVar = (hu) b.get(i);
            if (hw.a(context, heVar, huVar.a(), gkVar)) {
                try {
                    a(context, heVar, gkVar, a(context, huVar.a()), huVar.e());
                    return huVar.e();
                } catch (Throwable th) {
                    hw.a(th, "FileManager", "loadAvailableD");
                }
            } else {
                c(context, heVar, huVar.a());
                i++;
            }
        }
        return null;
    }

    static void a(Context context, File file, gk gkVar) {
        File parentFile = file.getParentFile();
        if (!file.exists() && parentFile != null && parentFile.exists()) {
            c(context, gkVar.a(), gkVar.b());
        }
    }

    private static void c(Context context, he heVar, String str) {
        File file = new File(a(context, str));
        if (file.exists()) {
            file.delete();
        }
        heVar.a(hu.b(str), hu.class);
    }

    private static void a(Context context, File file, String str, gk gkVar) {
        Object obj = null;
        he heVar = new he(context, hs.a());
        hu a = a.a(heVar, file.getName());
        if (a != null) {
            obj = a.e();
        }
        File file2 = new File(str);
        if (!TextUtils.isEmpty(obj) && file2.exists()) {
            String a2 = gf.a(str);
            String name = file2.getName();
            a.a(heVar, new com.amap.api.mapcore.util.hu.a(name, a2, gkVar.a(), gkVar.b(), obj).a("useod").a(), hu.b(name));
        }
    }
}
