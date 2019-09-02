package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import java.io.File;
import java.io.RandomAccessFile;

/* compiled from: DexDownLoad */
public class hk implements com.amap.api.mapcore.util.iv.a {
    private hm a;
    private iv b;
    private gk c;
    private String d;
    private RandomAccessFile e;
    private Context f;

    /* compiled from: DexDownLoad */
    class a implements Runnable {
        final /* synthetic */ hk a;
        private int b;
        private he c;
        private String d;

        a(hk hkVar, int i) {
            this.a = hkVar;
            this.b = i;
        }

        a(hk hkVar, he heVar, int i) {
            this.a = hkVar;
            this.b = i;
            this.c = heVar;
        }

        public void run() {
            switch (this.b) {
                case 0:
                    try {
                        if (this.a.b()) {
                            jj jjVar = new jj(this.a.f, this.a.c.a(), this.a.c.b(), "O008");
                            jjVar.a("{\"param_int_first\":0}");
                            jk.a(jjVar, this.a.f);
                            this.a.b.a(this.a);
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        hw.a(th, "dDownLoad", "run()");
                        return;
                    }
                case 1:
                case 2:
                    try {
                        hq.a(this.a.f, this.c, this.a.c, this.a.d, this.a.a.d);
                        hq.a(this.a.f, this.a.c);
                        return;
                    } catch (Throwable th2) {
                        hw.a(th2, "dDownLoad", "onFinish2");
                        return;
                    }
                case 3:
                    try {
                        hq.a(this.a.f, this.c, this.a.c, this.a.d, this.d);
                        hq.a(this.a.f, this.a.c);
                        return;
                    } catch (Throwable th22) {
                        hw.a(th22, "dDownLoad", "processDownloadedFile()");
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public hk(Context context, hm hmVar, gk gkVar) {
        try {
            this.f = context.getApplicationContext();
            this.c = gkVar;
            if (hmVar != null) {
                this.a = hmVar;
                this.b = new iv(new hp(this.a));
                this.d = hq.a(context, this.a.a);
            }
        } catch (Throwable th) {
            hw.a(th, "dDownLoad", "DexDownLoad()");
        }
    }

    public void a() {
        try {
            hv.b().a().submit(new a(this, 0));
        } catch (Throwable th) {
            hw.a(th, "dDownLoad", "startDownload()");
        }
    }

    boolean b() {
        boolean z;
        if (this.a == null || !this.a.d()) {
            z = false;
        } else {
            z = true;
        }
        try {
            if (!hw.a(this.c, this.a) || !hw.a(this.a) || !hw.a(this.f, r0) || hw.a(this.f, this.a, this.c) || !hw.a(this.f, this.c, this.a)) {
                return false;
            }
            hq.b(this.f, this.c.a());
            return true;
        } catch (Throwable th) {
            hw.a(th, "dDownLoad", "isNeedDownload()");
            return false;
        }
    }

    public void a(byte[] bArr, long j) {
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
            hw.a(th, "dDownLoad", "onDownload()");
        }
    }

    public void a(Throwable th) {
        try {
            hw.a(this.e);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public void e() {
        try {
            if (this.e != null) {
                hw.a(this.e);
                String b = this.a.b();
                if (hw.b(this.d, b)) {
                    a(b);
                    jj jjVar = new jj(this.f, this.c.a(), this.c.b(), "O008");
                    jjVar.a("{\"param_int_first\":1}");
                    jk.a(jjVar, this.f);
                    return;
                }
                new File(this.d).delete();
            }
        } catch (Throwable th) {
            hw.a(th, "dDownLoad", "onFinish()");
        }
    }

    public void d() {
    }

    private void a(String str) {
        String c = this.a.c();
        he heVar = new he(this.f, hs.a());
        com.amap.api.mapcore.util.hq.a.a(heVar, new com.amap.api.mapcore.util.hu.a(this.a.a, str, this.a.b, c, this.a.d).a("copy").a(), hu.a(this.a.a, this.a.b, c, this.a.d));
        a(this.f, this.a.b);
        try {
            hv.b().a().submit(new a(this, heVar, 2));
        } catch (Throwable th) {
            hw.a(th, "dDownLoad", "onFinish1");
        }
    }

    private void a(Context context, String str) {
        try {
            Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.clear();
            edit.commit();
        } catch (Throwable th) {
            hw.a(th, "dDownLoad", "clearMarker()");
        }
    }
}
