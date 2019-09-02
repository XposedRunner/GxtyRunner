package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;

/* compiled from: OfflineDownloadManager */
public class al {
    public static String a = "";
    public static boolean b = false;
    public static String d = "";
    private static volatile al k;
    List<be> c = new Vector();
    b e = null;
    public ap f;
    ar g;
    ao h = null;
    private Context i;
    private boolean j = true;
    private a l;
    private au m;
    private ba n;
    private ExecutorService o = null;
    private ExecutorService p = null;
    private ExecutorService q = null;
    private boolean r = true;

    /* compiled from: OfflineDownloadManager */
    public interface a {
        void a();

        void a(be beVar);

        void b(be beVar);

        void c(be beVar);
    }

    /* compiled from: OfflineDownloadManager */
    class b extends Handler {
        final /* synthetic */ al a;

        public b(al alVar, Looper looper) {
            this.a = alVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            try {
                message.getData();
                Object obj = message.obj;
                if (obj instanceof be) {
                    be beVar = (be) obj;
                    bk.a("OfflineMapHandler handleMessage CitObj  name: " + beVar.getCity() + " complete: " + beVar.getcompleteCode() + " status: " + beVar.getState());
                    if (this.a.l != null) {
                        this.a.l.a(beVar);
                        return;
                    }
                    return;
                }
                bk.a("Do not callback by CityObject! ");
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private al(Context context) {
        this.i = context;
    }

    public static al a(Context context) {
        if (k == null) {
            synchronized (al.class) {
                if (k == null && !b) {
                    k = new al(context.getApplicationContext());
                }
            }
        }
        return k;
    }

    public void a() {
        this.n = ba.a(this.i.getApplicationContext());
        h();
        this.e = new b(this, this.i.getMainLooper());
        this.f = new ap(this.i, this.e);
        this.m = au.a(1);
        g(en.c(this.i));
        try {
            i();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        synchronized (this.c) {
            Iterator it = this.f.a().iterator();
            while (it.hasNext()) {
                Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
                while (it2.hasNext()) {
                    OfflineMapCity offlineMapCity = (OfflineMapCity) it2.next();
                    if (offlineMapCity != null) {
                        this.c.add(new be(this.i, offlineMapCity));
                    }
                }
            }
        }
        this.h = new ao(this.i);
        this.h.start();
    }

    private void h() {
        try {
            String str = "000001";
            av a = this.n.a(str);
            if (a != null) {
                this.n.c(str);
                a.c("100000");
                this.n.a(a);
            }
        } catch (Throwable th) {
            gz.c(th, "OfflineDownloadManager", "changeBadCase");
        }
    }

    private void i() {
        if (!"".equals(en.c(this.i))) {
            String c;
            File file = new File(en.c(this.i) + "offlinemapv4.png");
            if (file.exists()) {
                c = bk.c(file);
            } else {
                c = bk.a(this.i, "offlinemapv4.png");
            }
            if (c != null) {
                try {
                    h(c);
                } catch (Throwable e) {
                    if (file.exists()) {
                        file.delete();
                    }
                    gz.c(e, "MapDownloadManager", "paseJson io");
                    e.printStackTrace();
                }
            }
        }
    }

    private void h(String str) throws JSONException {
        List a = bk.a(str, this.i.getApplicationContext());
        if (a != null && a.size() != 0 && this.f != null) {
            this.f.a(a);
        }
    }

    private void j() {
        Iterator it = this.n.a().iterator();
        while (it.hasNext()) {
            av avVar = (av) it.next();
            if (!(avVar == null || avVar.d() == null || avVar.f().length() < 1)) {
                if (!(avVar.l == 4 || avVar.l == 7 || avVar.l < 0)) {
                    avVar.l = 3;
                }
                be i = i(avVar.d());
                if (i != null) {
                    String e = avVar.e();
                    if (e == null || !a(d, e)) {
                        i.a(avVar.l);
                        i.setCompleteCode(avVar.h());
                    } else {
                        i.a(7);
                    }
                    if (avVar.e().length() > 0) {
                        i.setVersion(avVar.e());
                    }
                    List<String> b = this.n.b(avVar.f());
                    StringBuffer stringBuffer = new StringBuffer();
                    for (String append : b) {
                        stringBuffer.append(append);
                        stringBuffer.append(";");
                    }
                    i.a(stringBuffer.toString());
                    if (this.f != null) {
                        this.f.a(i);
                    }
                }
            }
        }
    }

    public void a(ArrayList<av> arrayList) {
        j();
        if (this.l != null) {
            try {
                this.l.a();
            } catch (Throwable th) {
                gz.c(th, "OfflineDownloadManager", "verifyCallBack");
            }
        }
    }

    public void a(final String str) {
        if (str == null) {
            try {
                if (this.l != null) {
                    this.l.b(null);
                    return;
                }
                return;
            } catch (Throwable th) {
                gz.c(th, "OfflineDownloadManager", "checkUpdate");
                return;
            }
        }
        if (this.o == null) {
            long j = (long) 1;
            this.o = new ThreadPoolExecutor(1, 2, j, TimeUnit.SECONDS, new LinkedBlockingQueue(), new dy("AMapOfflineCheckUpdate"), new AbortPolicy());
        }
        this.o.execute(new Runnable(this) {
            final /* synthetic */ al b;

            public void run() {
                be a = this.b.i(str);
                if (a != null) {
                    try {
                        if (!a.c().equals(a.c) && !a.c().equals(a.e)) {
                            String pinyin = a.getPinyin();
                            if (pinyin.length() > 0) {
                                pinyin = this.b.n.d(pinyin);
                                if (pinyin == null) {
                                    pinyin = a.getVersion();
                                }
                                if (al.d.length() > 0 && r0 != null && this.b.a(al.d, r0)) {
                                    a.j();
                                }
                            }
                        } else if (this.b.l != null) {
                            synchronized (this.b) {
                                try {
                                    this.b.l.b(a);
                                } catch (Throwable th) {
                                    gz.c(th, "OfflineDownloadManager", "checkUpdatefinally");
                                }
                            }
                            return;
                        } else {
                            return;
                        }
                    } catch (Exception e) {
                        if (this.b.l != null) {
                            synchronized (this.b) {
                                this.b.l.b(a);
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th2) {
                        gz.c(th2, "OfflineDownloadManager", "checkUpdatefinally");
                    }
                }
                this.b.k();
                am amVar = (am) new an(this.b.i, al.d).c();
                if (this.b.l != null) {
                    if (amVar == null) {
                        if (this.b.l != null) {
                            synchronized (this.b) {
                                try {
                                    this.b.l.b(a);
                                } catch (Throwable th3) {
                                    gz.c(th3, "OfflineDownloadManager", "checkUpdatefinally");
                                }
                            }
                            return;
                        }
                        return;
                    } else if (amVar.a()) {
                        this.b.b();
                    }
                }
                if (this.b.l != null) {
                    synchronized (this.b) {
                        try {
                            this.b.l.b(a);
                        } catch (Throwable th32) {
                            gz.c(th32, "OfflineDownloadManager", "checkUpdatefinally");
                        }
                    }
                }
            }
        });
    }

    private void k() throws AMapException {
        if (!en.d(this.i)) {
            throw new AMapException(AMapException.ERROR_CONNECTION);
        }
    }

    protected void b() throws AMapException {
        if (this.f != null) {
            as asVar = new as(this.i, "");
            asVar.a(this.i);
            List list = (List) asVar.c();
            if (this.c != null) {
                this.f.a(list);
            }
            if (this.c != null) {
                synchronized (this.c) {
                    Iterator it = this.f.a().iterator();
                    while (it.hasNext()) {
                        Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
                        while (it2.hasNext()) {
                            OfflineMapCity offlineMapCity = (OfflineMapCity) it2.next();
                            for (be beVar : this.c) {
                                if (offlineMapCity.getPinyin().equals(beVar.getPinyin())) {
                                    String version = beVar.getVersion();
                                    if (beVar.getState() == 4 && d.length() > 0 && a(d, version)) {
                                        beVar.j();
                                        beVar.setUrl(offlineMapCity.getUrl());
                                        beVar.t();
                                    } else {
                                        beVar.setCity(offlineMapCity.getCity());
                                        beVar.setUrl(offlineMapCity.getUrl());
                                        beVar.t();
                                        beVar.setAdcode(offlineMapCity.getAdcode());
                                        beVar.setVersion(offlineMapCity.getVersion());
                                        beVar.setSize(offlineMapCity.getSize());
                                        beVar.setCode(offlineMapCity.getCode());
                                        beVar.setJianpin(offlineMapCity.getJianpin());
                                        beVar.setPinyin(offlineMapCity.getPinyin());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean a(String str, String str2) {
        int i = 0;
        while (i < str2.length()) {
            try {
                if (str.charAt(i) > str2.charAt(i)) {
                    return true;
                }
                if (str.charAt(i) < str2.charAt(i)) {
                    return false;
                }
                i++;
            } catch (Throwable th) {
                return false;
            }
        }
        return false;
    }

    public boolean b(String str) {
        if (i(str) == null) {
            return false;
        }
        return true;
    }

    public void c(String str) {
        be i = i(str);
        if (i != null) {
            d(i);
            a(i, true);
        } else if (this.l != null) {
            try {
                this.l.c(i);
            } catch (Throwable th) {
                gz.c(th, "OfflineDownloadManager", "remove");
            }
        }
    }

    public void a(be beVar) {
        a(beVar, false);
    }

    private void a(final be beVar, final boolean z) {
        if (this.g == null) {
            this.g = new ar(this.i);
        }
        if (this.p == null) {
            long j = (long) 1;
            this.p = new ThreadPoolExecutor(1, 2, j, TimeUnit.SECONDS, new LinkedBlockingQueue(), new dy("AMapOfflineRemove"), new AbortPolicy());
        }
        try {
            this.p.execute(new Runnable(this) {
                final /* synthetic */ al c;

                public void run() {
                    try {
                        if (beVar.c().equals(beVar.a)) {
                            if (this.c.l != null) {
                                this.c.l.c(beVar);
                            }
                        } else if (beVar.getState() == 7 || beVar.getState() == -1) {
                            this.c.g.a(beVar);
                            if (z && this.c.l != null) {
                                this.c.l.c(beVar);
                            }
                        } else {
                            this.c.g.a(beVar);
                            if (this.c.l != null) {
                                this.c.l.c(beVar);
                            }
                        }
                    } catch (Throwable th) {
                        gz.c(th, "requestDelete", "removeExcecRunnable");
                    }
                }
            });
        } catch (Throwable th) {
            gz.c(th, "requestDelete", "removeExcecRunnable");
        }
    }

    public void b(be beVar) {
        try {
            if (this.m != null) {
                this.m.a(beVar, this.i, null);
            }
        } catch (gp e) {
            e.printStackTrace();
        }
    }

    public void c(be beVar) {
        if (this.f != null) {
            this.f.a(beVar);
        }
        if (this.e != null) {
            Message obtainMessage = this.e.obtainMessage();
            obtainMessage.obj = beVar;
            this.e.sendMessage(obtainMessage);
        }
    }

    public void c() {
        synchronized (this.c) {
            for (be beVar : this.c) {
                if (beVar.c().equals(beVar.c) || beVar.c().equals(beVar.b)) {
                    d(beVar);
                    beVar.g();
                }
            }
        }
    }

    public void d() {
        synchronized (this.c) {
            for (be beVar : this.c) {
                if (beVar.c().equals(beVar.c)) {
                    beVar.g();
                    break;
                }
            }
        }
    }

    public void e() {
        if (!(this.o == null || this.o.isShutdown())) {
            this.o.shutdownNow();
        }
        if (!(this.q == null || this.q.isShutdown())) {
            this.q.shutdownNow();
        }
        if (this.h != null) {
            if (this.h.isAlive()) {
                this.h.interrupt();
            }
            this.h = null;
        }
        if (this.e != null) {
            this.e.removeCallbacksAndMessages(null);
            this.e = null;
        }
        if (this.m != null) {
            this.m.b();
        }
        if (this.f != null) {
            this.f.g();
        }
        f();
        this.j = true;
        g();
    }

    public static void f() {
        k = null;
        b = true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.amap.api.mapcore.util.be i(java.lang.String r6) {
        /*
        r5 = this;
        r1 = 0;
        if (r6 == 0) goto L_0x000a;
    L_0x0003:
        r0 = r6.length();
        r2 = 1;
        if (r0 >= r2) goto L_0x000c;
    L_0x000a:
        r0 = r1;
    L_0x000b:
        return r0;
    L_0x000c:
        r2 = r5.c;
        monitor-enter(r2);
        r0 = r5.c;	 Catch:{ all -> 0x0037 }
        r3 = r0.iterator();	 Catch:{ all -> 0x0037 }
    L_0x0015:
        r0 = r3.hasNext();	 Catch:{ all -> 0x0037 }
        if (r0 == 0) goto L_0x003a;
    L_0x001b:
        r0 = r3.next();	 Catch:{ all -> 0x0037 }
        r0 = (com.amap.api.mapcore.util.be) r0;	 Catch:{ all -> 0x0037 }
        r4 = r0.getCity();	 Catch:{ all -> 0x0037 }
        r4 = r6.equals(r4);	 Catch:{ all -> 0x0037 }
        if (r4 != 0) goto L_0x0035;
    L_0x002b:
        r4 = r0.getPinyin();	 Catch:{ all -> 0x0037 }
        r4 = r6.equals(r4);	 Catch:{ all -> 0x0037 }
        if (r4 == 0) goto L_0x0015;
    L_0x0035:
        monitor-exit(r2);	 Catch:{ all -> 0x0037 }
        goto L_0x000b;
    L_0x0037:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0037 }
        throw r0;
    L_0x003a:
        monitor-exit(r2);	 Catch:{ all -> 0x0037 }
        r0 = r1;
        goto L_0x000b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.al.i(java.lang.String):com.amap.api.mapcore.util.be");
    }

    private be j(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        synchronized (this.c) {
            for (be beVar : this.c) {
                if (str.equals(beVar.getCode())) {
                    return beVar;
                }
            }
            return null;
        }
    }

    public void d(String str) throws AMapException {
        be i = i(str);
        if (str == null || str.length() < 1 || i == null) {
            throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
        }
        f(i);
    }

    public void e(String str) throws AMapException {
        be j = j(str);
        if (j != null) {
            f(j);
            return;
        }
        throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
    }

    private void f(final be beVar) throws AMapException {
        k();
        if (beVar == null) {
            throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
        }
        if (this.q == null) {
            long j = (long) 1;
            this.q = new ThreadPoolExecutor(1, 2, j, TimeUnit.SECONDS, new LinkedBlockingQueue(), new dy("AMapOfflineDownload"), new AbortPolicy());
        }
        try {
            this.q.execute(new Runnable(this) {
                final /* synthetic */ al b;

                public void run() {
                    try {
                        if (this.b.j) {
                            this.b.k();
                            am amVar = (am) new an(this.b.i, al.d).c();
                            if (amVar != null) {
                                this.b.j = false;
                                if (amVar.a()) {
                                    this.b.b();
                                }
                            }
                        }
                        beVar.setVersion(al.d);
                        beVar.f();
                    } catch (AMapException e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        gz.c(th, "OfflineDownloadManager", "startDownloadRunnable");
                    }
                }
            });
        } catch (Throwable th) {
            gz.c(th, "startDownload", "downloadExcecRunnable");
        }
    }

    public void d(be beVar) {
        if (this.m != null) {
            this.m.a((at) beVar);
        }
    }

    public void e(be beVar) {
        if (this.m != null) {
            this.m.b(beVar);
        }
    }

    public void a(a aVar) {
        this.l = aVar;
    }

    public void g() {
        synchronized (this) {
            this.l = null;
        }
    }

    public String f(String str) {
        String str2 = "";
        if (str == null) {
            return str2;
        }
        be i = i(str);
        if (i != null) {
            return i.getAdcode();
        }
        return str2;
    }

    public static void g(String str) {
        a = str;
    }
}
