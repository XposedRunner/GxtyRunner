package com.loc;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import java.io.File;
import java.io.RandomAccessFile;

/* compiled from: DexDownLoad */
public final class v implements com.loc.ap.a {
    private w a;
    private ap b;
    private dk c;
    private String d;
    private RandomAccessFile e;
    private Context f;

    /* compiled from: DexDownLoad */
    class a implements Runnable {
        final /* synthetic */ v a;
        private int b = 0;
        private p c;
        private String d;

        a(v vVar) {
            this.a = vVar;
        }

        a(v vVar, p pVar) {
            this.a = vVar;
            this.c = pVar;
        }

        public final void run() {
            switch (this.b) {
                case 0:
                    try {
                        if (this.a.b()) {
                            ay ayVar = new ay(this.a.f, this.a.c.a(), this.a.c.b(), "O008");
                            ayVar.a("{\"param_int_first\":0}");
                            az.a(ayVar, this.a.f);
                            this.a.b.a(this.a);
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        g.a(th, "dDownLoad", "run()");
                        return;
                    }
                case 1:
                case 2:
                    try {
                        aa.a(this.a.f, this.c, this.a.c, this.a.d, this.a.a.d);
                        aa.a(this.a.f, this.a.c);
                        return;
                    } catch (Throwable th2) {
                        g.a(th2, "dDownLoad", "onFinish2");
                        return;
                    }
                case 3:
                    try {
                        aa.a(this.a.f, this.c, this.a.c, this.a.d, this.d);
                        aa.a(this.a.f, this.a.c);
                        return;
                    } catch (Throwable th22) {
                        g.a(th22, "dDownLoad", "processDownloadedFile()");
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public v(Context context, w wVar, dk dkVar) {
        try {
            this.f = context.getApplicationContext();
            this.c = dkVar;
            if (wVar != null) {
                this.a = wVar;
                this.b = new ap(new z(this.a));
                this.d = aa.a(context, this.a.a);
            }
        } catch (Throwable th) {
            g.a(th, "dDownLoad", "DexDownLoad()");
        }
    }

    public final void a() {
        try {
            ag.b().a().submit(new a(this));
        } catch (Throwable th) {
            g.a(th, "dDownLoad", "startDownload()");
        }
    }

    public final void a(byte[] bArr, long j) {
        try {
            if (this.e == null) {
                File file = new File(this.d);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.e = new RandomAccessFile(file, "rw");
            }
            this.e.seek(j);
            this.e.write(bArr);
        } catch (Throwable th) {
            g.a(th, "dDownLoad", "onDownload()");
        }
    }

    final boolean b() {
        boolean z = this.a != null && this.a.c();
        try {
            if (!ah.a(this.c, this.a) || !ah.a(this.a) || !ah.a(this.f, z) || ah.a(this.f, this.a, this.c)) {
                return false;
            }
            Context context = this.f;
            dk dkVar = this.c;
            z = this.a.d() ? true : !dl.b(context);
            if (!z) {
                return false;
            }
            aa.b(this.f, this.c.a());
            return true;
        } catch (Throwable th) {
            g.a(th, "dDownLoad", "isNeedDownload()");
            return false;
        }
    }

    public final void c() {
        try {
            ah.a(this.e);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void d() {
        try {
            if (this.e != null) {
                ah.a(this.e);
                String b = this.a.b();
                if (ah.a(this.d, b)) {
                    String str = this.a.c;
                    p pVar = new p(this.f, ad.b());
                    pVar.a(new com.loc.af.a(this.a.a, b, this.a.b, str, this.a.d).a("copy").a(), af.a(this.a.a, this.a.b, str, this.a.d));
                    Editor edit = this.f.getSharedPreferences(this.a.b, 0).edit();
                    edit.clear();
                    edit.commit();
                    try {
                        ag.b().a().submit(new a(this, pVar));
                    } catch (Throwable th) {
                        g.a(th, "dDownLoad", "onFinish1");
                    }
                    ay ayVar = new ay(this.f, this.c.a(), this.c.b(), "O008");
                    ayVar.a("{\"param_int_first\":1}");
                    az.a(ayVar, this.f);
                    return;
                }
                try {
                    new File(this.d).delete();
                } catch (Throwable th2) {
                    g.a(th2, "dDownLoad", "onFinish");
                }
            }
        } catch (Throwable th22) {
            g.a(th22, "dDownLoad", "onFinish()");
        }
    }

    public final void e() {
    }
}
