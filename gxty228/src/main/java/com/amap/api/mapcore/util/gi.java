package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;

@Deprecated
/* compiled from: SDKCoordinatorDownload */
public class gi extends Thread implements com.amap.api.mapcore.util.iv.a {
    private static String h = "sodownload";
    private static String i = "sofail";
    protected a a;
    protected RandomAccessFile b;
    protected String c;
    protected String d;
    protected String e;
    protected Context f;
    private iv g = new iv(this.a);

    /* compiled from: SDKCoordinatorDownload */
    public static class a extends iy {
        private String a;

        a(String str) {
            this.a = str;
        }

        public Map<String, String> a() {
            return null;
        }

        public Map<String, String> b() {
            return null;
        }

        public String c() {
            return this.a;
        }
    }

    public gi(Context context, String str, String str2, String str3) {
        this.f = context;
        this.e = str3;
        this.c = a(context, str + "temp.so");
        this.d = a(context, "libwgs2gcj.so");
        this.a = new a(str2);
    }

    public static String a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "libso" + File.separator + str;
    }

    private static String b(Context context, String str) {
        return a(context, str);
    }

    public void a() {
        if (this.a != null && !TextUtils.isEmpty(this.a.c()) && this.a.c().contains("libJni_wgs2gcj.so") && this.a.c().contains(gl.a(this.f)) && !new File(this.d).exists()) {
            start();
        }
    }

    public void run() {
        try {
            File file = new File(b(this.f, "tempfile"));
            if (file.exists()) {
                file.delete();
            }
            this.g.a(this);
        } catch (Throwable th) {
            gz.c(th, "sdl", "run");
            b();
        }
    }

    public void a(byte[] bArr, long j) {
        try {
            if (this.b == null) {
                File file = new File(this.c);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.b = new RandomAccessFile(file, "rw");
            }
        } catch (Throwable e) {
            gz.c(e, "sdl", "oDd");
            b();
        } catch (Throwable e2) {
            b();
            gz.c(e2, "sdl", "oDd");
            return;
        }
        if (this.b != null) {
            try {
                this.b.seek(j);
                this.b.write(bArr);
            } catch (Throwable e22) {
                b();
                gz.c(e22, "sdl", "oDd");
            }
        }
    }

    public void d() {
        b();
    }

    public void e() {
        try {
            if (this.b != null) {
                this.b.close();
            }
            String a = gf.a(this.c);
            if (a == null || !a.equalsIgnoreCase(this.e)) {
                b();
            } else if (new File(this.d).exists()) {
                b();
            } else {
                new File(this.c).renameTo(new File(this.d));
            }
        } catch (Throwable th) {
            b();
            File file = new File(this.d);
            if (file.exists()) {
                file.delete();
            }
            gz.c(th, "sdl", "ofs");
        }
    }

    public void a(Throwable th) {
        try {
            if (this.b != null) {
                this.b.close();
            }
            b();
            File file = new File(b(this.f, "tempfile"));
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdir();
                }
                file.createNewFile();
            }
        } catch (Throwable th2) {
            gz.c(th2, "sdl", "oe");
        }
    }

    protected void b() {
        File file = new File(this.c);
        if (file.exists()) {
            file.delete();
        }
    }
}
