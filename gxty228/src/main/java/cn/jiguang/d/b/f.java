package cn.jiguang.d.b;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import cn.jiguang.api.JResponse;
import cn.jiguang.api.SdkType;
import cn.jiguang.api.utils.ByteBufferUtils;
import cn.jiguang.api.utils.OutputDataUtil;
import cn.jiguang.d.a.a;
import cn.jiguang.d.d.c;
import cn.jiguang.d.f.e;
import cn.jiguang.d.g.b;
import cn.jiguang.d.g.g;
import cn.jiguang.e.d;
import cn.jiguang.g.i;
import cn.jiguang.g.k;
import cn.jiguang.g.m;
import cn.jpush.android.api.JPushInterface;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public final class f implements Runnable {
    public static AtomicLong a = new AtomicLong(0);
    public static AtomicBoolean b = new AtomicBoolean(false);
    private Context c;
    private Handler d;
    private volatile boolean e = false;
    private boolean f;
    private ExecutorService g;

    public f(Context context, Handler handler) {
        this.c = context;
        this.d = handler;
        this.f = true;
    }

    private boolean a(int i) {
        while (!this.e) {
            if (i <= 0) {
                d.d("NetworkingClient", "login error,retry login too many times");
                return false;
            }
            String a;
            byte[] a2;
            Object obj;
            int i2;
            long juid;
            String b;
            String c;
            Context context;
            long d;
            int i3;
            int i4;
            int i5;
            int i6;
            long j;
            d.c("NetworkingClient", "loginTimes:" + i);
            if (!cn.jiguang.d.a.d.e(this.c) || TextUtils.isEmpty(cn.jiguang.d.a.d.f(this.c))) {
                e a3;
                JResponse a4;
                Context context2 = this.c;
                a.get();
                a = b.d().a();
                String str = b.d().a;
                String b2 = b.d().b();
                String c2 = b.d().c();
                short c3 = cn.jiguang.d.d.e.a().c();
                d.c("ConnectingHelper", "Register with: key:" + a + ", apkVersion:" + str + ", clientInfo:" + b2 + ", extKey:" + c2 + ",reg business:" + c3);
                long h = a.h();
                int abs = Math.abs(new Random().nextInt());
                cn.jiguang.d.g.a.a.a(abs);
                OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
                outputDataUtil.writeU16(0);
                outputDataUtil.writeU8(13);
                outputDataUtil.writeU8(0);
                outputDataUtil.writeU64(h);
                outputDataUtil.writeU32((long) abs);
                outputDataUtil.writeU64(0);
                outputDataUtil.writeByteArrayincludeLength(a.getBytes());
                outputDataUtil.writeByteArrayincludeLength(str.getBytes());
                outputDataUtil.writeByteArrayincludeLength(b2.getBytes());
                outputDataUtil.writeU8(0);
                outputDataUtil.writeByteArrayincludeLength(c2.getBytes());
                cn.jiguang.d.d.e.a().d();
                outputDataUtil.writeU8(c3);
                outputDataUtil.writeU16At(outputDataUtil.current(), 0);
                a2 = cn.jiguang.d.e.a.a.b.a(outputDataUtil.toByteArray(), 0);
                if (a2 == null) {
                    obj = null;
                } else if (cn.jiguang.d.f.d.a().b().a(a2) != 0) {
                    obj = null;
                } else {
                    a3 = cn.jiguang.d.f.d.a().b().a(20000);
                    if (a3.a() != 0) {
                        d.h("ConnectingHelper", "Register failed - recv msg failed with error code:" + a3.a() + ",msg:" + a3.c());
                        obj = null;
                    } else {
                        a4 = cn.jiguang.d.e.a.a.a.a(a3.b().array());
                        if (a4 == null) {
                            d.i("ConnectingHelper", "Register failed - unknown command");
                            obj = null;
                        } else {
                            d.c("ConnectingHelper", a4.toString());
                            if (a4.getCommand() != 0) {
                                d.i("ConnectingHelper", "Register failed - it is not register response.");
                                obj = null;
                            } else {
                                cn.jiguang.d.e.a.e eVar = (cn.jiguang.d.e.a.e) a4;
                                i2 = eVar.code;
                                a.a(context2, i2);
                                d.a().b(i2);
                                if (i2 == 0) {
                                    juid = eVar.getJuid();
                                    c2 = eVar.a();
                                    b = eVar.b();
                                    c = eVar.c();
                                    d.f("ConnectingHelper", "Register succeed - juid:" + juid + ", registrationId:" + b + ", deviceId:" + c);
                                    d.a("ConnectingHelper", "password:" + c2);
                                    if (k.a(b) || 0 == juid) {
                                        d.j("ConnectingHelper", "Unexpected: registrationId/juid should not be empty. ");
                                        obj = null;
                                    } else {
                                        cn.jiguang.g.a.f(context2, c);
                                        cn.jiguang.d.a.d.a(context2, juid, c2, b, c);
                                        cn.jiguang.g.a.a(context2, JPushInterface.ACTION_REGISTRATION_ID, JPushInterface.EXTRA_REGISTRATION_ID, b);
                                        obj = 1;
                                    }
                                } else {
                                    c.a(context2, i2, true);
                                    obj = null;
                                }
                            }
                        }
                    }
                }
                if (obj == null) {
                    if (a.l()) {
                        d.a("NetworkingClient", "Registe failed, stop the service!");
                        this.d.sendEmptyMessageDelayed(1001, 100);
                    } else {
                        d.a("NetworkingClient", "Registe failed, close the connection!");
                    }
                    f();
                    obj = null;
                    if (obj == null) {
                        return false;
                    }
                    context = this.c;
                    a.get();
                    d = cn.jiguang.d.a.d.d(context);
                    a = k.b(cn.jiguang.d.a.d.f(context));
                    if (a == null) {
                        a = "";
                    }
                    String i7 = cn.jiguang.d.a.d.i(context);
                    i3 = 0;
                    i2 = 0;
                    i4 = 0;
                    i5 = 0;
                    cn.jiguang.d.d.b.a();
                    b = cn.jiguang.d.d.b.d(SdkType.JCORE.name(), "");
                    if (!TextUtils.isEmpty(b)) {
                        i3 = cn.jiguang.g.a.c(b);
                    }
                    cn.jiguang.d.d.b.a();
                    b = cn.jiguang.d.d.b.d(SdkType.JANALYTICS.name(), "");
                    if (!TextUtils.isEmpty(b)) {
                        i2 = cn.jiguang.g.a.c(b);
                    }
                    cn.jiguang.d.d.b.a();
                    b = cn.jiguang.d.d.b.d(SdkType.JSHARE.name(), "");
                    if (!TextUtils.isEmpty(b)) {
                        i4 = cn.jiguang.g.a.c(b);
                    }
                    cn.jiguang.d.d.b.a();
                    b = cn.jiguang.d.d.b.d(SdkType.JPUSH.name(), "");
                    if (!TextUtils.isEmpty(b)) {
                        i5 = cn.jiguang.g.a.c(b);
                    }
                    b.d();
                    byte c4 = b.c(context);
                    d.f("ConnectingHelper", "Login with - juid:" + d + ", appKey:" + i7 + ", sdkVersion:" + i3 + ", pushVersion:" + i5 + ", analyticsVersion:" + i2 + " ,shareVersion:" + i4 + ", pluginPlatformType:" + c4);
                    long currentTimeMillis = System.currentTimeMillis();
                    short d2 = cn.jiguang.d.d.e.a().d();
                    int a5 = m.a(context);
                    String h2 = cn.jiguang.g.a.h(context);
                    cn.jiguang.a.a.b.e a6 = cn.jiguang.a.a.b.f.a(context);
                    b = a6 == null ? a6.e() : "";
                    c = k.c(String.format(Locale.ENGLISH, cn.jiguang.c.c.a(context), new Object[0]));
                    c = c != null ? "" : c.toUpperCase();
                    d.c("ConnectingHelper", "login - juid:" + d + ", flag:" + d2 + " netType:" + a5 + " deviceId:" + h2 + " locInfo:" + b + " countryCode:" + c);
                    long h3 = a.h();
                    long j2 = (long) i3;
                    long j3 = (long) i5;
                    long j4 = (long) i2;
                    juid = (long) i4;
                    OutputDataUtil outputDataUtil2 = new OutputDataUtil(20480);
                    outputDataUtil2.writeU16(0);
                    outputDataUtil2.writeU8(19);
                    outputDataUtil2.writeU8(1);
                    outputDataUtil2.writeU64(h3);
                    outputDataUtil2.writeU32(0);
                    outputDataUtil2.writeU64(d);
                    outputDataUtil2.writeU8(97);
                    outputDataUtil2.writeU8(0);
                    outputDataUtil2.writeU16(0);
                    outputDataUtil2.writeByteArrayincludeLength(a.getBytes());
                    outputDataUtil2.writeU32(j3);
                    outputDataUtil2.writeU32(j4);
                    outputDataUtil2.writeU32(juid);
                    outputDataUtil2.writeU32(j2);
                    outputDataUtil2.writeByteArrayincludeLength(i7.getBytes());
                    outputDataUtil2.writeU8(0);
                    outputDataUtil2.writeU8(d2);
                    outputDataUtil2.writeU8(c4);
                    outputDataUtil2.writeU8(a5);
                    outputDataUtil2.writeByteArrayincludeLength(h2.getBytes());
                    outputDataUtil2.writeByteArrayincludeLength(b.getBytes());
                    outputDataUtil2.writeByteArrayincludeLength(c.getBytes());
                    outputDataUtil2.writeU16At(outputDataUtil2.current(), 0);
                    a2 = outputDataUtil2.toByteArray();
                    d.e("ConnectingHelper", "pluginPlatformType:0b" + Integer.toBinaryString(c4 & 255));
                    a2 = cn.jiguang.d.e.a.a.b.a(a2, 1);
                    if (a2 != null || a2.length <= 0) {
                        i6 = -1;
                    } else if (cn.jiguang.d.f.d.a().b().a(a2) != 0) {
                        i6 = -1;
                    } else {
                        a3 = cn.jiguang.d.f.d.a().b().a(20000);
                        i3 = a3.a();
                        if (a3.a() != 0) {
                            d.h("ConnectingHelper", "Login failed - recv msg failed wit error code:" + a3.a() + ",msg:" + a3.c());
                            cn.jiguang.d.g.e.a(context, i3, System.currentTimeMillis() - currentTimeMillis, 1);
                            i6 = -1;
                        } else {
                            juid = System.currentTimeMillis();
                            a4 = cn.jiguang.d.e.a.a.a.a(a3.b().array());
                            if (a4 == null) {
                                d.h("ConnectingHelper", "Login failed - unknown command");
                                cn.jiguang.d.g.e.a(context, i3, juid - currentTimeMillis, 1);
                                i6 = -1;
                            } else if (a4 instanceof cn.jiguang.d.e.a.d) {
                                cn.jiguang.d.e.a.d dVar = (cn.jiguang.d.e.a.d) a4;
                                d.c("ConnectingHelper", dVar.toString());
                                i3 = dVar.code;
                                d.a().a(i3);
                                if (i3 == 0) {
                                    i5 = dVar.getSid();
                                    d = ((long) dVar.a()) * 1000;
                                    cn.jiguang.d.a.d.a(context, i5);
                                    a.b(d);
                                    d.f("ConnectingHelper", "Login succeed - sid:" + i5 + ", serverTime;" + d);
                                    c.a(context, d);
                                    i6 = 0;
                                } else {
                                    i6 = 1;
                                    if (i3 == ByteBufferUtils.ERROR_CODE) {
                                        d.h("ConnectingHelper", "Login failed with Local error - code:" + i3);
                                    } else {
                                        d.h("ConnectingHelper", "Login failed with server error - code:" + i.a(i3));
                                    }
                                }
                                cn.jiguang.d.g.e.a(context, i3, juid - currentTimeMillis, i6);
                                i6 = i3;
                            } else {
                                d.h("ConnectingHelper", "Login failed - it is not LoginResponse");
                                cn.jiguang.d.g.e.a(context, i3, juid - currentTimeMillis, 1);
                                i6 = -1;
                            }
                        }
                    }
                    if (i6 < 0) {
                        return false;
                    }
                    if (i6 > 0) {
                        juid = a.get();
                        d.a("NetworkingClient", "Action - onLoginFailed - respCode:" + i6);
                        c.a(Message.obtain(this.d, 7306), juid);
                        if (i6 != 108) {
                            cn.jiguang.g.a.m(this.c);
                            i--;
                        } else {
                            if (i6 == 102) {
                                a.k();
                                this.d.sendEmptyMessageDelayed(PointerIconCompat.TYPE_HELP, 100);
                            } else if (i6 == PointerIconCompat.TYPE_NO_DROP) {
                                a.c();
                            }
                            return false;
                        }
                    } else if (0 == a.get()) {
                        j = a.get();
                        d.b("NetworkingClient", "Action - onLoggedIn - connection:" + j);
                        c.a(Message.obtain(this.d, 7304), j);
                        if (!cn.jiguang.d.d.e.a().b(this.c)) {
                            return true;
                        }
                        d.d("NetworkingClient", "need not keep tcp connect,will close connection");
                        return false;
                    } else {
                        d.g("NetworkingClient", "mConnection is reset to 0 when state between login and onLoggedOn. Exit directly.");
                        return false;
                    }
                }
                cn.jiguang.a.a.c.b.b(this.c, b.d());
                if (a.l()) {
                    d.c("ConnectingHelper", "Action: restoreRtcWhenRegisterSucceed");
                    a.j();
                    this.d.sendEmptyMessageDelayed(1031, 100);
                }
                j = cn.jiguang.d.a.d.d(this.c);
                d.c("NetworkingClient", "uid:" + j);
                cn.jiguang.d.h.f.a().b().b(j);
            }
            obj = 1;
            if (obj == null) {
                return false;
            }
            context = this.c;
            a.get();
            d = cn.jiguang.d.a.d.d(context);
            a = k.b(cn.jiguang.d.a.d.f(context));
            if (a == null) {
                a = "";
            }
            String i72 = cn.jiguang.d.a.d.i(context);
            i3 = 0;
            i2 = 0;
            i4 = 0;
            i5 = 0;
            cn.jiguang.d.d.b.a();
            b = cn.jiguang.d.d.b.d(SdkType.JCORE.name(), "");
            if (TextUtils.isEmpty(b)) {
                i3 = cn.jiguang.g.a.c(b);
            }
            cn.jiguang.d.d.b.a();
            b = cn.jiguang.d.d.b.d(SdkType.JANALYTICS.name(), "");
            if (TextUtils.isEmpty(b)) {
                i2 = cn.jiguang.g.a.c(b);
            }
            cn.jiguang.d.d.b.a();
            b = cn.jiguang.d.d.b.d(SdkType.JSHARE.name(), "");
            if (TextUtils.isEmpty(b)) {
                i4 = cn.jiguang.g.a.c(b);
            }
            cn.jiguang.d.d.b.a();
            b = cn.jiguang.d.d.b.d(SdkType.JPUSH.name(), "");
            if (TextUtils.isEmpty(b)) {
                i5 = cn.jiguang.g.a.c(b);
            }
            b.d();
            byte c42 = b.c(context);
            d.f("ConnectingHelper", "Login with - juid:" + d + ", appKey:" + i72 + ", sdkVersion:" + i3 + ", pushVersion:" + i5 + ", analyticsVersion:" + i2 + " ,shareVersion:" + i4 + ", pluginPlatformType:" + c42);
            long currentTimeMillis2 = System.currentTimeMillis();
            short d22 = cn.jiguang.d.d.e.a().d();
            int a52 = m.a(context);
            String h22 = cn.jiguang.g.a.h(context);
            cn.jiguang.a.a.b.e a62 = cn.jiguang.a.a.b.f.a(context);
            if (a62 == null) {
            }
            c = k.c(String.format(Locale.ENGLISH, cn.jiguang.c.c.a(context), new Object[0]));
            if (c != null) {
            }
            d.c("ConnectingHelper", "login - juid:" + d + ", flag:" + d22 + " netType:" + a52 + " deviceId:" + h22 + " locInfo:" + b + " countryCode:" + c);
            long h32 = a.h();
            long j22 = (long) i3;
            long j32 = (long) i5;
            long j42 = (long) i2;
            juid = (long) i4;
            OutputDataUtil outputDataUtil22 = new OutputDataUtil(20480);
            outputDataUtil22.writeU16(0);
            outputDataUtil22.writeU8(19);
            outputDataUtil22.writeU8(1);
            outputDataUtil22.writeU64(h32);
            outputDataUtil22.writeU32(0);
            outputDataUtil22.writeU64(d);
            outputDataUtil22.writeU8(97);
            outputDataUtil22.writeU8(0);
            outputDataUtil22.writeU16(0);
            outputDataUtil22.writeByteArrayincludeLength(a.getBytes());
            outputDataUtil22.writeU32(j32);
            outputDataUtil22.writeU32(j42);
            outputDataUtil22.writeU32(juid);
            outputDataUtil22.writeU32(j22);
            outputDataUtil22.writeByteArrayincludeLength(i72.getBytes());
            outputDataUtil22.writeU8(0);
            outputDataUtil22.writeU8(d22);
            outputDataUtil22.writeU8(c42);
            outputDataUtil22.writeU8(a52);
            outputDataUtil22.writeByteArrayincludeLength(h22.getBytes());
            outputDataUtil22.writeByteArrayincludeLength(b.getBytes());
            outputDataUtil22.writeByteArrayincludeLength(c.getBytes());
            outputDataUtil22.writeU16At(outputDataUtil22.current(), 0);
            a2 = outputDataUtil22.toByteArray();
            d.e("ConnectingHelper", "pluginPlatformType:0b" + Integer.toBinaryString(c42 & 255));
            a2 = cn.jiguang.d.e.a.a.b.a(a2, 1);
            if (a2 != null) {
            }
            i6 = -1;
            if (i6 < 0) {
                return false;
            }
            if (i6 > 0) {
                juid = a.get();
                d.a("NetworkingClient", "Action - onLoginFailed - respCode:" + i6);
                c.a(Message.obtain(this.d, 7306), juid);
                if (i6 != 108) {
                    if (i6 == 102) {
                        a.k();
                        this.d.sendEmptyMessageDelayed(PointerIconCompat.TYPE_HELP, 100);
                    } else if (i6 == PointerIconCompat.TYPE_NO_DROP) {
                        a.c();
                    }
                    return false;
                }
                cn.jiguang.g.a.m(this.c);
                i--;
            } else if (0 == a.get()) {
                d.g("NetworkingClient", "mConnection is reset to 0 when state between login and onLoggedOn. Exit directly.");
                return false;
            } else {
                j = a.get();
                d.b("NetworkingClient", "Action - onLoggedIn - connection:" + j);
                c.a(Message.obtain(this.d, 7304), j);
                if (!cn.jiguang.d.d.e.a().b(this.c)) {
                    return true;
                }
                d.d("NetworkingClient", "need not keep tcp connect,will close connection");
                return false;
            }
        }
        return false;
    }

    private boolean a(Context context) {
        b.d().a(context);
        try {
            a.set(Thread.currentThread().getId());
            d.c("NetworkingClient", "Created connection - " + a.get());
            cn.jiguang.d.b.a.a.f fVar = new cn.jiguang.d.b.a.a.f(context, this, a.get());
            int b = fVar.b();
            if (b != 0) {
                if (b == 2) {
                    d.g("NetworkingClient", "unexpected! PushProtocol.Stop");
                }
                fVar.a();
                f();
                return false;
            } else if (a(2)) {
                fVar.a();
                return true;
            } else {
                fVar.a();
                f();
                return false;
            }
        } catch (Throwable e) {
            d.f("NetworkingClient", "长连接失败, jpush.so加载异常", e);
            return false;
        }
    }

    private void f() {
        d.d("NetworkingClient", "Action - closeConnection - connection:" + a.get());
        if (0 != a.get()) {
            try {
                b.set(true);
                a.set(0);
                cn.jiguang.d.f.d.a().b().a();
                b.set(false);
            } catch (Throwable e) {
                d.c("NetworkingClient", "Close connection error", e);
            }
            this.f = false;
            if (cn.jiguang.d.d.e.a().f()) {
                c.a(Message.obtain(this.d, 7301), a.get());
                return;
            }
            return;
        }
        d.c("NetworkingClient", "Unexpected - No connection to close. ");
    }

    public final void a() {
        d.d("NetworkingClient", "Action - tryStop - connection:" + a.get());
        this.e = true;
        this.f = false;
        if (a.get() != 0) {
            cn.jiguang.d.f.d.a().b().a();
        }
    }

    public final synchronized void b() {
        if (this.f) {
            this.g = Executors.newSingleThreadExecutor();
            try {
                this.g.execute(this);
            } catch (Throwable th) {
                d.h("NetworkingClient", "execute networkingClient exception :" + th);
                a();
            }
        }
    }

    public final synchronized void c() {
        a();
        g.a();
        g.a(this.g);
    }

    public final boolean d() {
        return this.e;
    }

    public final boolean e() {
        return this.f;
    }

    public final void run() {
        d.f("NetworkingClient", "Begin to run in ConnectingThread - id:" + Thread.currentThread().getId());
        try {
            if (a(this.c)) {
                while (!this.e) {
                    d.d("NetworkingClient", "Network listening...");
                    e a = cn.jiguang.d.f.d.a().b().a(0);
                    if (a == null) {
                        break;
                    } else if (a.a() != 0) {
                        d.d("NetworkingClient", " recv failed with error code:" + a.a() + ",msg:" + a.c() + ",No Break!!");
                        break;
                    } else {
                        ByteBuffer b = a.b();
                        int length = b.array().length;
                        byte[] bArr = new byte[length];
                        System.arraycopy(b.array(), 0, bArr, 0, length);
                        cn.jiguang.d.e.a.a.a.a(this.c, bArr);
                        d.d("NetworkingClient", "Received bytes - len:" + b.array().length + ", connection:" + a.get() + ", pkg:" + cn.jiguang.d.a.c);
                        if (0 == a.get()) {
                            d.h("NetworkingClient", "mConnection is reset to 0 when network listening. Break now.");
                            return;
                        }
                    }
                }
                if (this.e) {
                    d.d("NetworkingClient", "Break receiving by wantStop - connection:" + a.get());
                }
                f();
                return;
            }
            d.d("NetworkingClient", "prepare Push Channel failed , returned");
        } catch (Throwable th) {
            d.g("NetworkingClient", "run exception", th);
        }
    }
}
