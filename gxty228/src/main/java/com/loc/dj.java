package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;

@Deprecated
/* compiled from: SDKCoordinatorDownload */
public class dj extends Thread implements com.loc.ap.a {
    private static String h = "sodownload";
    private static String i = "sofail";
    protected a a;
    protected RandomAccessFile b;
    protected String c;
    protected String d;
    protected String e;
    protected Context f;
    private ap g = new ap(this.a);

    /* compiled from: SDKCoordinatorDownload */
    public static class a extends ar {
        private String a;

        a(String str) {
            this.a = str;
        }

        public final Map<String, String> a() {
            return null;
        }

        public final Map<String, String> b() {
            return null;
        }

        public final String c() {
            return this.a;
        }
    }

    public dj(Context context, String str, String str2, String str3) {
        this.f = context;
        this.e = str3;
        this.c = a(context, str + "temp.so");
        this.d = a(context, "libwgs2gcj.so");
        this.a = new a(str2);
    }

    public static String a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "libso" + File.separator + str;
    }

    public void a() {
        if (this.a != null && !TextUtils.isEmpty(this.a.c()) && this.a.c().contains("libJni_wgs2gcj.so") && this.a.c().contains(dl.a(this.f)) && !new File(this.d).exists()) {
            start();
        }
    }

    public final void a(byte[] bArr, long j) {
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
            j.b(e, "sdl", "oDd");
            b();
        } catch (Throwable e2) {
            b();
            j.b(e2, "sdl", "oDd");
            return;
        }
        if (this.b != null) {
            try {
                this.b.seek(j);
                this.b.write(bArr);
            } catch (Throwable e22) {
                b();
                j.b(e22, "sdl", "oDd");
            }
        }
    }

    protected final void b() {
        File file = new File(this.c);
        if (file.exists()) {
            file.delete();
        }
    }

    public final void c() {
        try {
            if (this.b != null) {
                this.b.close();
            }
            b();
            File file = new File(a(this.f, "tempfile"));
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdir();
                }
                file.createNewFile();
            }
        } catch (Throwable th) {
            j.b(th, "sdl", "oe");
        }
    }

    public void d() {
        try {
            if (this.b != null) {
                this.b.close();
            }
            String a = dh.a(this.c);
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
            j.b(th, "sdl", "ofs");
        }
    }

    public final void e() {
        b();
    }

    public void run() {
        try {
            File file = new File(a(this.f, "tempfile"));
            if (file.exists()) {
                file.delete();
            }
            this.g.a(this);
        } catch (Throwable th) {
            j.b(th, "sdl", "run");
            b();
        }
    }
}
