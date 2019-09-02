package cn.jiguang.d.b;

import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import cn.jiguang.api.JResponse;
import cn.jiguang.api.utils.OutputDataUtil;
import cn.jiguang.d.a.a;
import cn.jiguang.d.d.b;
import cn.jiguang.d.d.c;
import cn.jiguang.d.d.e;
import cn.jiguang.d.d.g;
import cn.jiguang.d.h.f;
import cn.jiguang.g.k;
import cn.jpush.android.service.PushService;
import java.lang.ref.WeakReference;

public final class d {
    private static boolean d = false;
    private static final Object j = new Object();
    private static volatile d l;
    private f a;
    private e b;
    private HandlerThread c;
    private int e = 0;
    private int f = 0;
    private long g;
    private Context h;
    private boolean i = false;
    private WeakReference<Service> k;
    private int m;
    private int n;

    private d() {
    }

    public static d a() {
        if (l == null) {
            synchronized (j) {
                if (l == null) {
                    l = new d();
                }
            }
        }
        return l;
    }

    static /* synthetic */ void a(d dVar, long j) {
        cn.jiguang.e.d.d("JiguangTcpManager", "Action - onLoggedIn - connection:" + j);
        if (!d) {
            c.a(dVar.h.getApplicationContext(), true);
        }
        d = true;
        dVar.e = 0;
        dVar.f = 0;
        dVar.b.sendEmptyMessageDelayed(1005, 15000);
        g.a().c();
        dVar.b.sendEmptyMessageDelayed(1032, 2000);
        cn.jiguang.a.c.c.a(dVar.h, 2);
        b.a();
        b.a(dVar.h, j, 1);
    }

    static /* synthetic */ void a(d dVar, boolean z) {
        if (z || System.currentTimeMillis() - dVar.g >= 18000) {
            cn.jiguang.e.d.d("JiguangTcpManager", "Send heart beat");
            dVar.b.removeMessages(1005);
            if (f.b.get() || !d) {
                cn.jiguang.e.d.a("JiguangTcpManager", "socket is closed or push isn't login");
                return;
            }
            Long valueOf = Long.valueOf(a.h());
            int a = cn.jiguang.d.a.d.a();
            long d = cn.jiguang.d.a.d.d(dVar.h);
            short b = e.a().b();
            cn.jiguang.e.d.c("JiguangTcpManager", "heartbeat - juid:" + d + ", flag:" + b);
            long longValue = valueOf.longValue();
            OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
            outputDataUtil.writeU16(0);
            outputDataUtil.writeU8(4);
            outputDataUtil.writeU8(2);
            outputDataUtil.writeU64(longValue);
            outputDataUtil.writeU32((long) a);
            outputDataUtil.writeU64(d);
            outputDataUtil.writeU8(b);
            outputDataUtil.writeU16At(outputDataUtil.current(), 0);
            byte[] a2 = cn.jiguang.d.e.a.a.b.a(outputDataUtil.toByteArray(), 1);
            if (a2 != null) {
                cn.jiguang.d.f.d.a().b().a(a2);
            } else {
                cn.jiguang.e.d.h("JiguangTcpManager", "send hb failed:sendData is null");
            }
            if (!dVar.b.hasMessages(1022)) {
                dVar.b.sendEmptyMessageDelayed(1022, 10000);
                return;
            }
            return;
        }
        cn.jiguang.e.d.a("JiguangTcpManager", "No need to rtc, Because it have succeed recently");
    }

    public static void a(byte[] bArr, String str, int i) {
        g.a().a(bArr, str, i);
    }

    static /* synthetic */ void b(d dVar) {
        if (!(dVar.k == null || dVar.k.get() == null)) {
            cn.jiguang.e.d.c("JiguangTcpManager", "stop service");
            ((Service) dVar.k.get()).stopSelf();
        }
        cn.jiguang.g.a.k(dVar.h);
        g.a();
        g.c();
        dVar.c();
    }

    static /* synthetic */ void b(d dVar, long j) {
        cn.jiguang.e.d.d("JiguangTcpManager", "Action - onDisconnected - connection:" + j);
        g.a().b();
        if (f.a.get() == 0 && cn.jiguang.d.a.d.j(dVar.h)) {
            cn.jiguang.e.d.c("JiguangTcpManager", "push already stopped!!!");
            return;
        }
        b.a();
        b.a(dVar.h, j, -1);
        if (d) {
            c.a(dVar.h.getApplicationContext(), false);
        }
        d = false;
        dVar.f = 0;
        if (dVar.a != null) {
            dVar.a.c();
        }
        if (cn.jiguang.g.a.c(dVar.h.getApplicationContext())) {
            dVar.j();
        }
        dVar.e++;
    }

    static /* synthetic */ void c(d dVar, long j) {
        cn.jiguang.e.d.d("JiguangTcpManager", "Action - onHeartbeatSucceed - connection:" + j);
        if (j != f.a.get()) {
            cn.jiguang.e.d.c("JiguangTcpManager", "Heartbeat succeed connection is already out-dated. Ignore it.");
            return;
        }
        if (dVar.b.hasMessages(1022)) {
            dVar.b.removeMessages(1022);
        }
        dVar.g = System.currentTimeMillis();
        dVar.f = 0;
        b.a();
        b.a(dVar.h, j, 19);
    }

    static /* synthetic */ void d(d dVar) {
        if (e.a().f()) {
            dVar.f++;
            cn.jiguang.e.d.d("JiguangTcpManager", "Action - onHeartbeatTimeout - timeoutTimes:" + dVar.f);
            cn.jiguang.e.d.b("JiguangTcpManager", "============================================================");
            if (i()) {
                cn.jiguang.e.d.d("JiguangTcpManager", "Is connecting now. Give up to retry.");
                dVar.b.sendEmptyMessageDelayed(1005, 10000);
                return;
            } else if (!d || dVar.h()) {
                if (dVar.a != null) {
                    dVar.a.a();
                }
                dVar.j();
                return;
            } else {
                cn.jiguang.e.d.d("JiguangTcpManager", "Already logged in. Give up to retry.");
                dVar.b.sendEmptyMessageDelayed(1005, 5000);
                return;
            }
        }
        cn.jiguang.e.d.a("JiguangTcpManager", "onHeartbeatTimeout, do not need heartbeat!");
    }

    public static boolean d() {
        return d;
    }

    public static long g() {
        return f.a.get();
    }

    private boolean h() {
        return this.f > 1;
    }

    private static boolean i() {
        return (f.a.get() == 0 || d) ? false : true;
    }

    private void j() {
        cn.jiguang.e.d.d("JiguangTcpManager", "Action - retryConnect - disconnectedTimes:" + this.e);
        if (!cn.jiguang.g.a.c(this.h.getApplicationContext()) || a.l()) {
            cn.jiguang.e.d.d("JiguangTcpManager", "network is not connect or hb is one day(user stop service) ");
            return;
        }
        int e = cn.jiguang.g.a.e(this.h.getApplicationContext());
        int pow = (int) ((Math.pow(2.0d, (double) this.e) * 3.0d) * 1000.0d);
        int i = a.i();
        if (pow > (i * 1000) / 2) {
            pow = (i * 1000) / 2;
        }
        if (this.e >= 5 && e != 1) {
            cn.jiguang.e.d.c("JiguangTcpManager", "Give up to retry connect.");
        } else if (this.b.hasMessages(1011)) {
            cn.jiguang.e.d.c("JiguangTcpManager", "Already has MSG_RESTART_CONN");
        } else {
            cn.jiguang.e.d.d("JiguangTcpManager", "onDisconnected and retry restart conn - delay:" + pow);
            this.b.sendEmptyMessageDelayed(1011, (long) pow);
        }
    }

    public final void a(int i) {
        this.m = i;
    }

    public final void a(Service service) {
        if (service != null) {
            this.k = new WeakReference(service);
        }
    }

    public final void a(Context context) {
        if (!this.i) {
            if (context == null) {
                cn.jiguang.e.d.c("JiguangTcpManager", "init context is null");
                return;
            }
            cn.jiguang.e.d.c("JiguangTcpManager", "init tcp manager...");
            this.h = context.getApplicationContext();
            if (!cn.jiguang.d.a.d.j(context)) {
                cn.jiguang.g.a.j(this.h);
            }
            try {
                if (this.c == null || !this.c.isAlive()) {
                    this.c = new HandlerThread("JCore");
                    this.c.start();
                }
                this.b = new e(this, this.c.getLooper() == null ? Looper.getMainLooper() : this.c.getLooper());
            } catch (Exception e) {
                this.b = new e(this, Looper.getMainLooper());
            }
            g.a().a(this.h);
            g.a().a(this.h, this.b);
            f.a().a(this.h);
            this.i = true;
        }
    }

    public final void a(Bundle bundle) {
        if (cn.jiguang.d.a.d.j(this.h)) {
            cn.jiguang.e.d.g("JiguangTcpManager", "tcp has close by active");
        } else if (bundle.getBoolean("connection-state", false)) {
            cn.jiguang.e.d.c("JiguangTcpManager", "Handle connected state.");
            if (f.a.get() == 0) {
                e();
            } else {
                this.b.sendEmptyMessage(PointerIconCompat.TYPE_CELL);
            }
        } else {
            cn.jiguang.e.d.c("JiguangTcpManager", "Handle disconnected state.");
        }
    }

    public final void a(JResponse jResponse, long j) {
        int a = ((cn.jiguang.d.e.a.a) jResponse).a();
        if (a == 2) {
            cn.jiguang.e.d.c("JiguangTcpManager", "Heartbeat ack succeed! ");
            c.a(Message.obtain(this.b, 7303), j);
        } else if (a == 10) {
            cn.jiguang.e.d.c("JiguangTcpManager", "Tag alias ack succeed! ");
            jResponse.getHead().a(Long.valueOf(-1));
        } else {
            cn.jiguang.e.d.g("JiguangTcpManager", "Unknown Ack request - cmd:" + a);
        }
    }

    public final void a(String str, Bundle bundle) {
        cn.jiguang.e.d.c("JiguangTcpManager", "Action: handleResumePush");
        b.a();
        b.a(this.h, str, f.a.get(), bundle, this.b);
        if (cn.jiguang.d.a.d.j(this.h)) {
            a.a(this.h, true);
            cn.jiguang.d.a.d.b(this.h, false);
            if (f.a.get() == 0) {
                f();
                return;
            } else {
                cn.jiguang.e.d.c("JiguangTcpManager", "The networking client is connected. Give up.");
                return;
            }
        }
        cn.jiguang.e.d.d("JiguangTcpManager", "jiguang service already started");
    }

    public final void a(String str, Bundle bundle, String str2) {
        cn.jiguang.e.d.c("JiguangTcpManager", "Action: handleStopPush - appKey:" + str2);
        b.a();
        b.a(this.h, str, f.a.get(), bundle, this.b);
        if (cn.jiguang.d.a.d.j(this.h)) {
            cn.jiguang.e.d.d("JiguangTcpManager", "jiguang service already stoped");
            return;
        }
        e.a();
        if (e.e()) {
            a.a(this.h, false);
            cn.jiguang.d.a.d.b(this.h, true);
            if (!(this.k == null || this.k.get() == null)) {
                cn.jiguang.e.d.c("JiguangTcpManager", "stop service");
                ((Service) this.k.get()).stopSelf();
            }
            c();
            return;
        }
        cn.jiguang.e.d.c("JiguangTcpManager", "Action: handleStopPush - can't stop tcp");
    }

    public final void a(String str, Object obj) {
        if (this.b.hasMessages(1022)) {
            this.b.removeMessages(1022);
        }
        this.g = System.currentTimeMillis();
        this.f = 0;
        g.a().a(str, obj);
    }

    public final void a(boolean z) {
        cn.jiguang.d.a.d.c(this.h, z);
        if (this.k == null || this.k.get() == null || !(this.k.get() instanceof PushService)) {
            cn.jiguang.e.d.c("JiguangTcpManager", "mService is null");
        } else {
            ((PushService) this.k.get()).setDozePowerReceiver();
        }
    }

    public final Handler b() {
        return this.b;
    }

    public final void b(int i) {
        this.n = i;
    }

    public final void b(Bundle bundle) {
        if (cn.jiguang.d.a.d.j(this.h)) {
            cn.jiguang.e.d.d("JiguangTcpManager", "tcp has close by active");
        } else if (f.a.get() == 0) {
            e();
        } else {
            int i = bundle.getInt("rtc_delay", 0);
            if (k.a(bundle.getString("rtc"))) {
                this.b.sendEmptyMessage(1005);
            } else if (i == 0) {
                this.b.removeMessages(1005);
                if (!this.b.hasMessages(1004)) {
                    this.b.sendEmptyMessage(1005);
                }
            } else {
                this.b.removeMessages(1005);
                this.b.removeMessages(1004);
                this.b.sendEmptyMessageDelayed(1004, (long) i);
            }
        }
    }

    public final void c() {
        cn.jiguang.e.d.c("JiguangTcpManager", "Action - destory");
        if (d) {
            c.a(this.h, false);
        }
        d = false;
        this.e = 0;
        this.f = 0;
        if (this.a != null) {
            this.a.c();
        }
    }

    public final void e() {
        cn.jiguang.e.d.b("JiguangTcpManager", "Action - restartThenHeartbeat");
        if (i()) {
            cn.jiguang.e.d.d("JiguangTcpManager", "Is connecting now. Give up to restart.");
        } else if (!d || h()) {
            this.b.removeMessages(1011);
            this.b.removeMessages(1005);
            f();
        } else {
            cn.jiguang.e.d.d("JiguangTcpManager", "Already logged in. Give up to restart.");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void f() {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = "JiguangTcpManager";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x003e }
        r2 = "Action - restartNetworkingClient, pid:";
        r1.<init>(r2);	 Catch:{ all -> 0x003e }
        r2 = android.os.Process.myPid();	 Catch:{ all -> 0x003e }
        r1 = r1.append(r2);	 Catch:{ all -> 0x003e }
        r1 = r1.toString();	 Catch:{ all -> 0x003e }
        cn.jiguang.e.d.d(r0, r1);	 Catch:{ all -> 0x003e }
        r0 = r3.h;	 Catch:{ all -> 0x003e }
        r0 = r0.getApplicationContext();	 Catch:{ all -> 0x003e }
        r0 = cn.jiguang.g.a.c(r0);	 Catch:{ all -> 0x003e }
        if (r0 != 0) goto L_0x002e;
    L_0x0025:
        r0 = "JiguangTcpManager";
        r1 = "No network connection. Give up to start connection thread.";
        cn.jiguang.e.d.f(r0, r1);	 Catch:{ all -> 0x003e }
    L_0x002c:
        monitor-exit(r3);
        return;
    L_0x002e:
        r0 = r3.h;	 Catch:{ all -> 0x003e }
        r0 = cn.jiguang.d.a.d.j(r0);	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x0041;
    L_0x0036:
        r0 = "JiguangTcpManager";
        r1 = "tcp has close by active";
        cn.jiguang.e.d.d(r0, r1);	 Catch:{ all -> 0x003e }
        goto L_0x002c;
    L_0x003e:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x0041:
        r0 = r3.m;	 Catch:{ all -> 0x003e }
        r1 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r0 != r1) goto L_0x004f;
    L_0x0047:
        r0 = "JiguangTcpManager";
        r1 = "login failed:102,give up start connection thread.reset from next app start";
        cn.jiguang.e.d.h(r0, r1);	 Catch:{ all -> 0x003e }
        goto L_0x002c;
    L_0x004f:
        r0 = r3.a;	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x0075;
    L_0x0053:
        r0 = "JiguangTcpManager";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x003e }
        r2 = "isRunning:";
        r1.<init>(r2);	 Catch:{ all -> 0x003e }
        r2 = r3.a;	 Catch:{ all -> 0x003e }
        r2 = r2.e();	 Catch:{ all -> 0x003e }
        r1 = r1.append(r2);	 Catch:{ all -> 0x003e }
        r1 = r1.toString();	 Catch:{ all -> 0x003e }
        cn.jiguang.e.d.d(r0, r1);	 Catch:{ all -> 0x003e }
        r0 = r3.a;	 Catch:{ all -> 0x003e }
        r0 = r0.e();	 Catch:{ all -> 0x003e }
        if (r0 != 0) goto L_0x002c;
    L_0x0075:
        r0 = r3.a;	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x0081;
    L_0x0079:
        r0 = r3.a;	 Catch:{ all -> 0x003e }
        r0.c();	 Catch:{ all -> 0x003e }
        r0 = 0;
        r3.a = r0;	 Catch:{ all -> 0x003e }
    L_0x0081:
        r0 = new cn.jiguang.d.b.f;	 Catch:{ all -> 0x003e }
        r1 = r3.h;	 Catch:{ all -> 0x003e }
        r1 = r1.getApplicationContext();	 Catch:{ all -> 0x003e }
        r2 = r3.b;	 Catch:{ all -> 0x003e }
        r0.<init>(r1, r2);	 Catch:{ all -> 0x003e }
        r3.a = r0;	 Catch:{ all -> 0x003e }
        r0 = r3.a;	 Catch:{ all -> 0x003e }
        r0.b();	 Catch:{ all -> 0x003e }
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.d.b.d.f():void");
    }
}
