package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;

/* compiled from: AuthTaskDownload */
public class jn implements com.amap.api.mapcore.util.iv.a {
    a a;
    private final Context b;
    private RandomAccessFile c;
    private jc d;
    private String e;

    /* compiled from: AuthTaskDownload */
    static class a {
        protected String a;
        protected String b;
        protected String c;
        protected String d;
        protected c e;

        public a(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3 + ".tmp";
            this.d = str3;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }

        public String d() {
            return this.d;
        }

        public void a(c cVar) {
            this.e = cVar;
        }

        public c e() {
            return this.e;
        }
    }

    /* compiled from: AuthTaskDownload */
    static class b extends dl {
        private final a b;

        b(a aVar) {
            this.b = aVar;
        }

        public Map<String, String> a() {
            return null;
        }

        public Map<String, String> b() {
            return null;
        }

        public String c() {
            if (this.b != null) {
                return this.b.a();
            }
            return null;
        }
    }

    /* compiled from: AuthTaskDownload */
    static class c {
        protected String a;
        protected String b;

        public c(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public boolean c() {
            return (TextUtils.isEmpty(this.a) || TextUtils.isEmpty(this.b)) ? false : true;
        }
    }

    /* compiled from: AuthTaskDownload */
    static class d extends a {
        public d(String str, String str2, String str3) {
            super(str, str2, str3);
        }

        public void a(String str, String str2) {
            a(new c(str, str2));
        }
    }

    public jn(Context context, a aVar, gk gkVar) {
        this.b = context.getApplicationContext();
        if (aVar != null) {
            this.a = aVar;
            this.d = new jc(new b(aVar));
            this.e = aVar.c();
        }
    }

    public void a() {
        try {
            if (b() && this.d != null) {
                this.d.a((com.amap.api.mapcore.util.iv.a) this);
            }
        } catch (Throwable th) {
            gz.c(th, "AuthTaskDownload", "startDownload()");
        }
    }

    private boolean b() {
        c e = this.a.e();
        if (e != null && e.c() && ec.a(this.b, e.a(), e.b(), "").equalsIgnoreCase(this.a.b())) {
            return false;
        }
        return true;
    }

    public void a(byte[] bArr, long j) {
        try {
            if (this.c == null) {
                File file = new File(this.e);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.c = new RandomAccessFile(file, "rw");
            }
            this.c.seek(j);
            this.c.write(bArr);
        } catch (Throwable th) {
            gz.c(th, "AuthTaskDownload", "onDownload()");
        }
    }

    public void d() {
    }

    public void e() {
        try {
            if (this.c != null) {
                try {
                    this.c.close();
                } catch (Throwable th) {
                    gz.c(th, "AuthTaskDownload", "onFinish3");
                }
                String b = this.a.b();
                Object a = gf.a(this.e);
                if (a == null || !b.equalsIgnoreCase(a)) {
                    try {
                        new File(this.e).delete();
                        return;
                    } catch (Throwable th2) {
                        gz.c(th2, "AuthTaskDownload", "onFinish");
                        return;
                    }
                }
                b = this.a.d();
                bc bcVar = new bc();
                File file = new File(this.e);
                bcVar.a(file, new File(b), -1, bk.a(file), null);
                c e = this.a.e();
                if (e != null && e.c()) {
                    ec.a(this.b, e.a(), e.b(), a);
                }
                new File(this.e).delete();
            }
        } catch (Throwable th22) {
            gz.c(th22, "AuthTaskDownload", "onFinish()");
        }
    }

    public void a(Throwable th) {
        try {
            if (this.c != null) {
                this.c.close();
            }
        } catch (Throwable th2) {
            gz.c(th2, "AuthTaskDownload", "onException()");
        }
    }
}
