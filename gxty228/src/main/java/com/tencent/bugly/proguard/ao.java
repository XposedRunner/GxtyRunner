package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import cn.jiguang.net.HttpUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: BUGLY */
public class ao {
    public static boolean a = true;
    private static SimpleDateFormat b;
    private static int c = 5120;
    private static StringBuilder d;
    private static StringBuilder e;
    private static boolean f;
    private static a g;
    private static String h;
    private static String i;
    private static Context j;
    private static String k;
    private static boolean l;
    private static boolean m = false;
    private static int n;
    private static final Object o = new Object();

    /* compiled from: BUGLY */
    public static class a {
        private boolean a;
        private File b;
        private String c;
        private long d;
        private long e = 30720;

        public a(String str) {
            if (str != null && !str.equals("")) {
                this.c = str;
                this.a = a();
            }
        }

        private boolean a() {
            try {
                this.b = new File(this.c);
                if (this.b.exists() && !this.b.delete()) {
                    this.a = false;
                    return false;
                } else if (this.b.createNewFile()) {
                    return true;
                } else {
                    this.a = false;
                    return false;
                }
            } catch (Throwable th) {
                an.a(th);
                this.a = false;
                return false;
            }
        }

        public boolean a(String str) {
            FileOutputStream fileOutputStream;
            Throwable th;
            Throwable th2;
            if (!this.a) {
                return false;
            }
            try {
                fileOutputStream = new FileOutputStream(this.b, true);
                try {
                    byte[] bytes = str.getBytes("UTF-8");
                    fileOutputStream.write(bytes);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    this.d += (long) bytes.length;
                    this.a = true;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                        }
                    }
                    return true;
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        an.a(th);
                        this.a = false;
                        if (fileOutputStream != null) {
                            return false;
                        }
                        try {
                            fileOutputStream.close();
                            return false;
                        } catch (IOException e2) {
                            return false;
                        }
                    } catch (Throwable th4) {
                        th2 = th4;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e3) {
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th5) {
                th2 = th5;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th2;
            }
        }
    }

    static {
        b = null;
        try {
            b = new SimpleDateFormat("MM-dd HH:mm:ss");
        } catch (Throwable th) {
        }
    }

    private static boolean b(String str, String str2, String str3) {
        try {
            com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
            if (!(b == null || b.L == null)) {
                return b.L.appendLogToNative(str, str2, str3);
            }
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
        }
        return false;
    }

    private static String f() {
        try {
            com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
            if (!(b == null || b.L == null)) {
                return b.L.getLogFromNative();
            }
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static synchronized void a(Context context) {
        synchronized (ao.class) {
            if (!(l || context == null || !a)) {
                try {
                    e = new StringBuilder(0);
                    d = new StringBuilder(0);
                    j = context;
                    com.tencent.bugly.crashreport.common.info.a a = com.tencent.bugly.crashreport.common.info.a.a(context);
                    h = a.e;
                    a.getClass();
                    i = "";
                    k = j.getFilesDir().getPath() + HttpUtils.PATHS_SEPARATOR + "buglylog_" + h + "_" + i + ".txt";
                    n = Process.myPid();
                } catch (Throwable th) {
                }
                l = true;
            }
        }
    }

    public static void a(int i) {
        synchronized (o) {
            c = i;
            if (i < 0) {
                c = 0;
            } else if (i > 10240) {
                c = 10240;
            }
        }
    }

    public static void a(boolean z) {
        an.a("[LogUtil] Whether can record user log into native: " + z, new Object[0]);
        m = z;
    }

    public static void a(String str, String str2, Throwable th) {
        if (th != null) {
            String message = th.getMessage();
            if (message == null) {
                message = "";
            }
            a(str, str2, message + '\n' + ap.b(th));
        }
    }

    public static synchronized void a(String str, String str2, String str3) {
        synchronized (ao.class) {
            if (l && a) {
                if (!(m && b(str, str2, str3))) {
                    String a = a(str, str2, str3, (long) Process.myTid());
                    synchronized (o) {
                        e.append(a);
                        if (e.length() <= c) {
                        } else if (f) {
                        } else {
                            f = true;
                            am.a().a(new Runnable() {
                                public void run() {
                                    synchronized (ao.o) {
                                        try {
                                            if (ao.g == null) {
                                                ao.g = new a(ao.k);
                                            } else if (ao.g.b == null || ao.g.b.length() + ((long) ao.e.length()) > ao.g.e) {
                                                ao.g.a();
                                            }
                                            if (ao.g.a(ao.e.toString())) {
                                                ao.e.setLength(0);
                                                ao.f = false;
                                            }
                                        } catch (Throwable th) {
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    private static String a(String str, String str2, String str3, long j) {
        String format;
        d.setLength(0);
        if (str3.length() > 30720) {
            str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
        }
        Date date = new Date();
        if (b != null) {
            format = b.format(date);
        } else {
            format = date.toString();
        }
        d.append(format).append(" ").append(n).append(" ").append(j).append(" ").append(str).append(" ").append(str2).append(": ").append(str3).append("\u0001\r\n");
        return d.toString();
    }

    public static byte[] a() {
        if (!a) {
            return null;
        }
        if (m) {
            an.a("[LogUtil] Get user log from native.", new Object[0]);
            String f = f();
            if (f != null) {
                an.a("[LogUtil] Got user log from native: %d bytes", Integer.valueOf(f.length()));
                return ap.a(null, f, "BuglyNativeLog.txt");
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        synchronized (o) {
            if (g != null && g.a && g.b != null && g.b.length() > 0) {
                stringBuilder.append(ap.a(g.b, 30720, true));
            }
            if (e != null && e.length() > 0) {
                stringBuilder.append(e.toString());
            }
        }
        return ap.a(null, stringBuilder.toString(), "BuglyLog.txt");
    }
}
