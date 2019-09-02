package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.blankj.utilcode.constant.TimeConstants;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ErrorLogManager */
public class ha {
    private static WeakReference<je> a;
    private static boolean b = true;
    private static WeakReference<jz> c;
    private static WeakReference<jz> d;
    private static String[] e = new String[10];
    private static int f = 0;
    private static boolean g = false;
    private static int h = 0;
    private static gk i;

    private static boolean a(gk gkVar) {
        return gkVar != null && gkVar.f();
    }

    private static void a(Context context, gk gkVar, int i, String str, String str2) {
        String a = jl.a();
        String a2 = jl.a(fx.a(context), jl.a(context, gkVar), a, i, str, str2);
        if (a2 != null && !"".equals(a2)) {
            a = gf.c(str2);
            String str3 = "";
            if (i == 1) {
                str3 = gx.b;
            } else if (i == 2) {
                str3 = gx.d;
            } else if (i == 0) {
                str3 = gx.c;
            } else {
                return;
            }
            a(context, a, str3, a2);
        }
    }

    static void a(Context context) {
        List c = gx.c(context);
        if (c != null && c.size() != 0) {
            String a = a(c);
            if (a != null && !"".equals(a) && i != null) {
                a(context, i, 2, "ANR", a);
            }
        }
    }

    public static void a(Context context, Throwable th, int i, String str, String str2) {
        String a = gl.a(th);
        gk a2 = a(context, a);
        if (a(a2)) {
            a = a.replaceAll("\n", "<br/>");
            String a3 = a(th);
            if (a3 != null && !"".equals(a3)) {
                StringBuilder stringBuilder = new StringBuilder();
                if (str != null) {
                    stringBuilder.append("class:").append(str);
                }
                if (str2 != null) {
                    stringBuilder.append(" method:").append(str2).append("$").append("<br/>");
                }
                stringBuilder.append(a);
                a(context, a2, i, a3, stringBuilder.toString());
            }
        }
    }

    static void a(gk gkVar, Context context, String str, String str2) {
        if (a(gkVar) && str != null && !"".equals(str)) {
            a(context, gkVar, 0, str, str2);
        }
    }

    static void b(gk gkVar, Context context, String str, String str2) {
        if (a(gkVar) && str != null && !"".equals(str)) {
            a(context, gkVar, 1, str, str2);
        }
    }

    private static void a(Context context, String str, String str2, String str3) {
        je a = jl.a(a);
        jl.a(context, a, str2, 1000, 20480, "1");
        if (a.e == null) {
            a.e = new gn(new go(new gr(new gt())));
        }
        try {
            jf.a(str, gl.a(str3), a);
        } catch (Throwable th) {
        }
    }

    static void b(Context context) {
        jz jxVar = new jx(b);
        b = false;
        a(context, jxVar, gx.c);
    }

    static void c(Context context) {
        if (c == null || c.get() == null) {
            c = new WeakReference(new jy(context, TimeConstants.HOUR, "hKey", new ka(context, false)));
        }
        a(context, (jz) c.get(), gx.d);
    }

    static void d(Context context) {
        if (d == null || d.get() == null) {
            d = new WeakReference(new jy(context, TimeConstants.HOUR, "gKey", new ka(context, false)));
        }
        a(context, (jz) d.get(), gx.b);
    }

    private static void a(final Context context, final jz jzVar, final String str) {
        gz.d().submit(new Runnable() {
            public void run() {
                try {
                    synchronized (ha.class) {
                        je a = jl.a(ha.a);
                        jl.a(context, a, str, 1000, 20480, "1");
                        a.f = jzVar;
                        if (a.g == null) {
                            a.g = new jq(new jp(context, new ju(), new go(new gr(new gt())), "EImtleSI6IiVzIiwicGxhdGZvcm0iOiJhbmRyb2lkIiwiZGl1IjoiJXMiLCJwa2ciOiIlcyIsIm1vZGVsIjoiJXMiLCJhcHBuYW1lIjoiJXMiLCJhcHB2ZXJzaW9uIjoiJXMiLCJzeXN2ZXJzaW9uIjoiJXMiLA=", fx.f(context), gd.u(context), fx.c(context), Build.MODEL, fx.b(context), fx.d(context), VERSION.RELEASE));
                        }
                        a.h = TimeConstants.HOUR;
                        jf.a(a);
                    }
                } catch (Throwable th) {
                    gz.c(th, "lg", "pul");
                }
            }
        });
    }

    static gk a(Context context, String str) {
        List c = gx.c(context);
        if (c == null) {
            c = new ArrayList();
        }
        if (str == null || "".equals(str)) {
            return null;
        }
        for (gk gkVar : r0) {
            if (gx.a(gkVar.g(), str)) {
                return gkVar;
            }
        }
        if (str.contains("com.amap.api.col")) {
            try {
                return gl.a();
            } catch (gp e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String a(Throwable th) {
        return th.toString();
    }

    static String a(List<gk> list) {
        InputStream fileInputStream;
        iq iqVar;
        InputStream inputStream;
        Throwable th;
        String str;
        String str2;
        InputStream inputStream2 = null;
        iq iqVar2 = null;
        try {
            File file = new File("/data/anr/traces.txt");
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    int available = fileInputStream.available();
                    if (available > 1024000) {
                        fileInputStream.skip((long) (available - 1024000));
                    }
                    iqVar2 = new iq(fileInputStream, ir.a);
                    Object obj = null;
                    while (true) {
                        try {
                            Object obj2;
                            String str3;
                            String trim = iqVar2.a().trim();
                            if (trim.contains("pid")) {
                                while (!trim.startsWith("\"main\"")) {
                                    trim = iqVar2.a();
                                }
                                obj2 = 1;
                                str3 = trim;
                            } else {
                                obj2 = obj;
                                str3 = trim;
                            }
                            if (str3.equals("") && obj2 != null) {
                                break;
                            }
                            if (obj2 != null) {
                                a(str3);
                                if (h == 5) {
                                    break;
                                } else if (!g) {
                                    for (gk gkVar : list) {
                                        g = gx.b(gkVar.g(), str3);
                                        if (g) {
                                            i = gkVar;
                                            break;
                                        }
                                    }
                                } else {
                                    h++;
                                }
                            }
                            obj = obj2;
                        } catch (EOFException e) {
                        } catch (FileNotFoundException e2) {
                            iqVar = iqVar2;
                            inputStream = fileInputStream;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    if (iqVar2 != null) {
                        try {
                            iqVar2.close();
                        } catch (Throwable th3) {
                            gz.c(th3, "alg", "getA");
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th4) {
                            th3 = th4;
                            str = "alg";
                            str2 = "getA";
                            gz.c(th3, str, str2);
                            if (g) {
                                return b();
                            }
                            return null;
                        }
                    }
                } catch (FileNotFoundException e3) {
                    iqVar = null;
                    inputStream = fileInputStream;
                    if (iqVar != null) {
                        try {
                            iqVar.close();
                        } catch (Throwable th32) {
                            gz.c(th32, "alg", "getA");
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th5) {
                            th32 = th5;
                            str = "alg";
                            str2 = "getA";
                            gz.c(th32, str, str2);
                            if (g) {
                                return b();
                            }
                            return null;
                        }
                    }
                    if (g) {
                        return b();
                    }
                    return null;
                } catch (Throwable th6) {
                    th32 = th6;
                    iqVar2 = null;
                    if (iqVar2 != null) {
                        iqVar2.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th32;
                }
                if (g) {
                    return b();
                }
                return null;
            }
            if (null != null) {
                try {
                    iqVar2.close();
                } catch (Throwable th7) {
                    gz.c(th7, "alg", "getA");
                }
            }
            if (null != null) {
                try {
                    inputStream2.close();
                } catch (Throwable th322) {
                    gz.c(th322, "alg", "getA");
                }
            }
            return null;
        } catch (FileNotFoundException e4) {
            iqVar = null;
            inputStream = null;
            if (iqVar != null) {
                iqVar.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (g) {
                return b();
            }
            return null;
        } catch (Throwable th8) {
            th322 = th8;
            iqVar2 = null;
            fileInputStream = null;
            if (iqVar2 != null) {
                iqVar2.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th322;
        }
    }

    private static void a(String str) {
        try {
            if (f > 9) {
                f = 0;
            }
            e[f] = str;
            f++;
        } catch (Throwable th) {
            gz.c(th, "alg", "aDa");
        }
    }

    private static String b() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            int i = f;
            while (i < 10 && i <= 9) {
                stringBuilder.append(e[i]);
                i++;
            }
            for (i = 0; i < f; i++) {
                stringBuilder.append(e[i]);
            }
        } catch (Throwable th) {
            gz.c(th, "alg", "gLI");
        }
        return stringBuilder.toString();
    }
}
