package com.loc;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.blankj.utilcode.constant.TimeConstants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ErrorLogManager */
public class l {
    private static WeakReference<at> a;
    private static boolean b = true;
    private static WeakReference<bo> c;
    private static WeakReference<bo> d;
    private static String[] e = new String[10];
    private static int f = 0;
    private static boolean g = false;
    private static int h = 0;
    private static dk i;

    private static dk a(Context context, String str) {
        List c = h.c(context);
        if (c == null) {
            c = new ArrayList();
        }
        if (str == null || "".equals(str)) {
            return null;
        }
        for (dk dkVar : r0) {
            if (h.a(dkVar.f(), str)) {
                return dkVar;
            }
        }
        if (str.contains("com.amap.api.col")) {
            try {
                return dl.a();
            } catch (k e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.util.List<com.loc.dk> r8) {
        /*
        r6 = 1024000; // 0xfa000 float:1.43493E-39 double:5.05923E-318;
        r5 = 0;
        r1 = 0;
        r0 = 0;
        r2 = 0;
        r4 = new java.io.File;	 Catch:{ FileNotFoundException -> 0x00fb, Throwable -> 0x00cc, all -> 0x00ea }
        r3 = "/data/anr/traces.txt";
        r4.<init>(r3);	 Catch:{ FileNotFoundException -> 0x00fb, Throwable -> 0x00cc, all -> 0x00ea }
        r3 = r4.exists();	 Catch:{ FileNotFoundException -> 0x00fb, Throwable -> 0x00cc, all -> 0x00ea }
        if (r3 != 0) goto L_0x0020;
    L_0x0014:
        if (r1 == 0) goto L_0x0019;
    L_0x0016:
        r2.close();	 Catch:{ Throwable -> 0x0134 }
    L_0x0019:
        if (r1 == 0) goto L_0x001e;
    L_0x001b:
        r0.close();	 Catch:{ Throwable -> 0x013e }
    L_0x001e:
        r0 = r1;
    L_0x001f:
        return r0;
    L_0x0020:
        r3 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x00fb, Throwable -> 0x00cc, all -> 0x00ea }
        r3.<init>(r4);	 Catch:{ FileNotFoundException -> 0x00fb, Throwable -> 0x00cc, all -> 0x00ea }
        r0 = r3.available();	 Catch:{ FileNotFoundException -> 0x0164, Throwable -> 0x015d, all -> 0x0158 }
        if (r0 <= r6) goto L_0x0030;
    L_0x002b:
        r0 = r0 - r6;
        r6 = (long) r0;	 Catch:{ FileNotFoundException -> 0x0164, Throwable -> 0x015d, all -> 0x0158 }
        r3.skip(r6);	 Catch:{ FileNotFoundException -> 0x0164, Throwable -> 0x015d, all -> 0x0158 }
    L_0x0030:
        r2 = new com.loc.ak;	 Catch:{ FileNotFoundException -> 0x0164, Throwable -> 0x015d, all -> 0x0158 }
        r0 = com.loc.al.a;	 Catch:{ FileNotFoundException -> 0x0164, Throwable -> 0x015d, all -> 0x0158 }
        r2.<init>(r3, r0);	 Catch:{ FileNotFoundException -> 0x0164, Throwable -> 0x015d, all -> 0x0158 }
        r4 = r5;
    L_0x0038:
        r0 = r2.a();	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        r0 = r0.trim();	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        r5 = "pid";
        r5 = r0.contains(r5);	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        if (r5 == 0) goto L_0x016c;
    L_0x0048:
        r4 = "\"main\"";
        r4 = r0.startsWith(r4);	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        if (r4 != 0) goto L_0x0055;
    L_0x0050:
        r0 = r2.a();	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        goto L_0x0048;
    L_0x0055:
        r4 = 1;
        r5 = r4;
        r4 = r0;
    L_0x0058:
        r0 = "";
        r0 = r4.equals(r0);	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        if (r0 == 0) goto L_0x0075;
    L_0x0060:
        if (r5 == 0) goto L_0x0075;
    L_0x0062:
        if (r2 == 0) goto L_0x0067;
    L_0x0064:
        r2.close();	 Catch:{ Throwable -> 0x0148 }
    L_0x0067:
        if (r3 == 0) goto L_0x006c;
    L_0x0069:
        r3.close();	 Catch:{ Throwable -> 0x0152 }
    L_0x006c:
        r0 = g;
        if (r0 == 0) goto L_0x00f8;
    L_0x0070:
        r0 = b();
        goto L_0x001f;
    L_0x0075:
        if (r5 == 0) goto L_0x00c9;
    L_0x0077:
        r0 = f;	 Catch:{ Throwable -> 0x00b5, EOFException -> 0x00be, FileNotFoundException -> 0x0168 }
        r6 = 9;
        if (r0 <= r6) goto L_0x0080;
    L_0x007d:
        r0 = 0;
        f = r0;	 Catch:{ Throwable -> 0x00b5, EOFException -> 0x00be, FileNotFoundException -> 0x0168 }
    L_0x0080:
        r0 = e;	 Catch:{ Throwable -> 0x00b5, EOFException -> 0x00be, FileNotFoundException -> 0x0168 }
        r6 = f;	 Catch:{ Throwable -> 0x00b5, EOFException -> 0x00be, FileNotFoundException -> 0x0168 }
        r0[r6] = r4;	 Catch:{ Throwable -> 0x00b5, EOFException -> 0x00be, FileNotFoundException -> 0x0168 }
        r0 = f;	 Catch:{ Throwable -> 0x00b5, EOFException -> 0x00be, FileNotFoundException -> 0x0168 }
        r0 = r0 + 1;
        f = r0;	 Catch:{ Throwable -> 0x00b5, EOFException -> 0x00be, FileNotFoundException -> 0x0168 }
    L_0x008c:
        r0 = h;	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        r6 = 5;
        if (r0 == r6) goto L_0x0062;
    L_0x0091:
        r0 = g;	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        if (r0 != 0) goto L_0x00c3;
    L_0x0095:
        r6 = r8.iterator();	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
    L_0x0099:
        r0 = r6.hasNext();	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        if (r0 == 0) goto L_0x00c0;
    L_0x009f:
        r0 = r6.next();	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        r0 = (com.loc.dk) r0;	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        r7 = r0.f();	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        r7 = com.loc.h.b(r7, r4);	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        g = r7;	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        if (r7 == 0) goto L_0x0099;
    L_0x00b1:
        i = r0;	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        r4 = r5;
        goto L_0x0038;
    L_0x00b5:
        r0 = move-exception;
        r6 = "alg";
        r7 = "aDa";
        com.loc.j.b(r0, r6, r7);	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        goto L_0x008c;
    L_0x00be:
        r0 = move-exception;
        goto L_0x0062;
    L_0x00c0:
        r4 = r5;
        goto L_0x0038;
    L_0x00c3:
        r0 = h;	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
        r0 = r0 + 1;
        h = r0;	 Catch:{ EOFException -> 0x00be, FileNotFoundException -> 0x0168, Throwable -> 0x0161 }
    L_0x00c9:
        r4 = r5;
        goto L_0x0038;
    L_0x00cc:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x00cf:
        r4 = "alg";
        r5 = "getA";
        com.loc.j.b(r0, r4, r5);	 Catch:{ all -> 0x015b }
        if (r2 == 0) goto L_0x00db;
    L_0x00d8:
        r2.close();	 Catch:{ Throwable -> 0x012b }
    L_0x00db:
        if (r3 == 0) goto L_0x006c;
    L_0x00dd:
        r3.close();	 Catch:{ Throwable -> 0x00e1 }
        goto L_0x006c;
    L_0x00e1:
        r0 = move-exception;
        r2 = "alg";
        r3 = "getA";
    L_0x00e6:
        com.loc.j.b(r0, r2, r3);
        goto L_0x006c;
    L_0x00ea:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x00ed:
        if (r2 == 0) goto L_0x00f2;
    L_0x00ef:
        r2.close();	 Catch:{ Throwable -> 0x0119 }
    L_0x00f2:
        if (r3 == 0) goto L_0x00f7;
    L_0x00f4:
        r3.close();	 Catch:{ Throwable -> 0x0122 }
    L_0x00f7:
        throw r0;
    L_0x00f8:
        r0 = r1;
        goto L_0x001f;
    L_0x00fb:
        r0 = move-exception;
        r0 = r1;
        r2 = r1;
    L_0x00fe:
        if (r0 == 0) goto L_0x0103;
    L_0x0100:
        r0.close();	 Catch:{ Throwable -> 0x0110 }
    L_0x0103:
        if (r2 == 0) goto L_0x006c;
    L_0x0105:
        r2.close();	 Catch:{ Throwable -> 0x010a }
        goto L_0x006c;
    L_0x010a:
        r0 = move-exception;
        r2 = "alg";
        r3 = "getA";
        goto L_0x00e6;
    L_0x0110:
        r0 = move-exception;
        r3 = "alg";
        r4 = "getA";
        com.loc.j.b(r0, r3, r4);
        goto L_0x0103;
    L_0x0119:
        r1 = move-exception;
        r2 = "alg";
        r4 = "getA";
        com.loc.j.b(r1, r2, r4);
        goto L_0x00f2;
    L_0x0122:
        r1 = move-exception;
        r2 = "alg";
        r3 = "getA";
        com.loc.j.b(r1, r2, r3);
        goto L_0x00f7;
    L_0x012b:
        r0 = move-exception;
        r2 = "alg";
        r4 = "getA";
        com.loc.j.b(r0, r2, r4);
        goto L_0x00db;
    L_0x0134:
        r2 = move-exception;
        r3 = "alg";
        r4 = "getA";
        com.loc.j.b(r2, r3, r4);
        goto L_0x0019;
    L_0x013e:
        r0 = move-exception;
        r2 = "alg";
        r3 = "getA";
        com.loc.j.b(r0, r2, r3);
        goto L_0x001e;
    L_0x0148:
        r0 = move-exception;
        r2 = "alg";
        r4 = "getA";
        com.loc.j.b(r0, r2, r4);
        goto L_0x0067;
    L_0x0152:
        r0 = move-exception;
        r2 = "alg";
        r3 = "getA";
        goto L_0x00e6;
    L_0x0158:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00ed;
    L_0x015b:
        r0 = move-exception;
        goto L_0x00ed;
    L_0x015d:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00cf;
    L_0x0161:
        r0 = move-exception;
        goto L_0x00cf;
    L_0x0164:
        r0 = move-exception;
        r0 = r1;
        r2 = r3;
        goto L_0x00fe;
    L_0x0168:
        r0 = move-exception;
        r0 = r2;
        r2 = r3;
        goto L_0x00fe;
    L_0x016c:
        r5 = r4;
        r4 = r0;
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.l.a(java.util.List):java.lang.String");
    }

    static void a(Context context) {
        List c = h.c(context);
        if (c != null && c.size() != 0) {
            String a = a(c);
            if (a != null && !"".equals(a) && i != null) {
                a(context, i, 2, "ANR", a);
            }
        }
    }

    private static void a(final Context context, final bo boVar, final String str) {
        j.d().submit(new Runnable() {
            public final void run() {
                try {
                    synchronized (l.class) {
                        at a = ba.a(l.a);
                        ba.a(context, a, str, 1000, 20480, "1");
                        a.f = boVar;
                        if (a.g == null) {
                            a.g = new bf(new be(context, new bj(), new do(new b(new d())), "EImtleSI6IiVzIiwicGxhdGZvcm0iOiJhbmRyb2lkIiwiZGl1IjoiJXMiLCJwa2ciOiIlcyIsIm1vZGVsIjoiJXMiLCJhcHBuYW1lIjoiJXMiLCJhcHB2ZXJzaW9uIjoiJXMiLCJzeXN2ZXJzaW9uIjoiJXMiLA=", db.f(context), df.u(context), db.c(context), Build.MODEL, db.b(context), db.d(context), VERSION.RELEASE));
                        }
                        a.h = TimeConstants.HOUR;
                        au.a(a);
                    }
                } catch (Throwable th) {
                    j.b(th, "lg", "pul");
                }
            }
        });
    }

    private static void a(Context context, dk dkVar, int i, String str, String str2) {
        String a = dl.a(System.currentTimeMillis());
        String a2 = ba.a(context, dkVar);
        db.a(context);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(a2).append(",\"timestamp\":\"");
        stringBuffer.append(a);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str2);
        stringBuffer.append("\"");
        String stringBuffer2 = stringBuffer.toString();
        if (stringBuffer2 != null && !"".equals(stringBuffer2)) {
            String str3;
            String c = dh.c(str2);
            if (i == 1) {
                str3 = h.b;
            } else if (i == 2) {
                str3 = h.d;
            } else if (i == 0) {
                str3 = h.c;
            } else {
                return;
            }
            at a3 = ba.a(a);
            ba.a(context, a3, str3, 1000, 20480, "1");
            if (a3.e == null) {
                a3.e = new dn(new do(new b(new d())));
            }
            try {
                au.a(c, dl.a(stringBuffer2), a3);
            } catch (Throwable th) {
            }
        }
    }

    public static void a(Context context, Throwable th, int i, String str, String str2) {
        String a = dl.a(th);
        dk a2 = a(context, a);
        if (a(a2)) {
            a = a.replaceAll("\n", "<br/>");
            String th2 = th.toString();
            if (th2 != null && !"".equals(th2)) {
                StringBuilder stringBuilder = new StringBuilder();
                if (str != null) {
                    stringBuilder.append("class:").append(str);
                }
                if (str2 != null) {
                    stringBuilder.append(" method:").append(str2).append("$<br/>");
                }
                stringBuilder.append(a);
                a(context, a2, i, th2, stringBuilder.toString());
            }
        }
    }

    static void a(dk dkVar, Context context, String str, String str2) {
        if (a(dkVar) && str != null && !"".equals(str)) {
            a(context, dkVar, 0, str, str2);
        }
    }

    private static boolean a(dk dkVar) {
        return dkVar != null && dkVar.e();
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
            j.b(th, "alg", "gLI");
        }
        return stringBuilder.toString();
    }

    static void b(Context context) {
        bo bmVar = new bm(b);
        b = false;
        a(context, bmVar, h.c);
    }

    static void b(dk dkVar, Context context, String str, String str2) {
        if (a(dkVar) && str != null && !"".equals(str)) {
            a(context, dkVar, 1, str, str2);
        }
    }

    static void c(Context context) {
        if (c == null || c.get() == null) {
            c = new WeakReference(new bn(context, TimeConstants.HOUR, "hKey", new bp(context)));
        }
        a(context, (bo) c.get(), h.d);
    }

    static void d(Context context) {
        if (d == null || d.get() == null) {
            d = new WeakReference(new bn(context, TimeConstants.HOUR, "gKey", new bp(context)));
        }
        a(context, (bo) d.get(), h.b);
    }
}
