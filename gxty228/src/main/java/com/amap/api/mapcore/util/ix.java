package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.maps.AMapException;
import java.net.Proxy;

/* compiled from: NetManger */
public class ix extends it {
    private static ix c;
    private kb d;
    private Handler e;

    /* compiled from: NetManger */
    /* renamed from: com.amap.api.mapcore.util.ix$1 */
    class AnonymousClass1 extends kc {
        final /* synthetic */ iy a;
        final /* synthetic */ ja b;
        final /* synthetic */ ix c;

        public void runTask() {
            try {
                this.c.a(this.c.b(this.a, false), this.b);
            } catch (gp e) {
                this.c.a(e, this.b);
            }
        }
    }

    /* compiled from: NetManger */
    static class a extends Handler {
        private a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        ((jd) message.obj).b.a();
                        return;
                    case 1:
                        jd jdVar = (jd) message.obj;
                        jdVar.b.a(jdVar.a);
                        return;
                    default:
                        return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            th.printStackTrace();
        }
    }

    public static ix a(boolean z) {
        return a(z, 5);
    }

    private static synchronized ix a(boolean z, int i) {
        ix ixVar;
        synchronized (ix.class) {
            try {
                if (c == null) {
                    c = new ix(z, i);
                } else if (z) {
                    if (c.d == null) {
                        c.d = kb.a(i);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            ixVar = c;
        }
        return ixVar;
    }

    private ix(boolean z, int i) {
        if (z) {
            try {
                this.d = kb.a(i);
            } catch (Throwable th) {
                gz.c(th, "NetManger", "NetManger1");
                th.printStackTrace();
                return;
            }
        }
        if (Looper.myLooper() == null) {
            this.e = new a(Looper.getMainLooper());
        } else {
            this.e = new a();
        }
    }

    public byte[] b(iy iyVar) throws gp {
        gp e;
        try {
            jb a = a(iyVar, false);
            if (a != null) {
                return a.a;
            }
            return null;
        } catch (gp e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            gz.e().b(th, "NetManager", "makeSyncPostRequest");
            e2 = new gp(AMapException.ERROR_UNKNOWN);
        }
    }

    public byte[] d(iy iyVar) throws gp {
        gp e;
        try {
            jb b = b(iyVar, false);
            if (b != null) {
                return b.a;
            }
            return null;
        } catch (gp e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new gp(AMapException.ERROR_UNKNOWN);
        }
    }

    public byte[] e(iy iyVar) throws gp {
        gp e;
        try {
            jb b = b(iyVar, true);
            if (b != null) {
                return b.a;
            }
            return null;
        } catch (gp e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new gp(AMapException.ERROR_UNKNOWN);
        }
    }

    public jb b(iy iyVar, boolean z) throws gp {
        gp e;
        try {
            Proxy proxy;
            c(iyVar);
            if (iyVar.h == null) {
                proxy = null;
            } else {
                proxy = iyVar.h;
            }
            return new iw(iyVar.f, iyVar.g, proxy, z).a(iyVar.c(), iyVar.a(), iyVar.b());
        } catch (gp e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new gp(AMapException.ERROR_UNKNOWN);
        }
    }

    private void a(gp gpVar, ja jaVar) {
        jd jdVar = new jd();
        jdVar.a = gpVar;
        jdVar.b = jaVar;
        Message obtain = Message.obtain();
        obtain.obj = jdVar;
        obtain.what = 1;
        this.e.sendMessage(obtain);
    }

    private void a(jb jbVar, ja jaVar) {
        jaVar.a(jbVar.b, jbVar.a);
        jd jdVar = new jd();
        jdVar.b = jaVar;
        Message obtain = Message.obtain();
        obtain.obj = jdVar;
        obtain.what = 0;
        this.e.sendMessage(obtain);
    }
}
