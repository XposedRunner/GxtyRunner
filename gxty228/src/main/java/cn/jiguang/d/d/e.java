package cn.jiguang.d.d;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.api.SdkType;
import cn.jiguang.api.utils.OutputDataUtil;
import cn.jiguang.d.a.a;
import cn.jiguang.d.a.d;
import java.util.HashMap;
import java.util.Map;

public final class e {
    private static volatile e e;
    private static final Object f = new Object();
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;
    private Map<Long, String> g;

    private e() {
        this.a = false;
        this.b = false;
        this.c = false;
        this.d = false;
        this.g = new HashMap();
        this.a = g();
        this.b = h();
        this.c = i();
        this.d = j();
    }

    public static e a() {
        if (e == null) {
            synchronized (f) {
                if (e == null) {
                    e = new e();
                }
            }
        }
        return e;
    }

    private static String a(Context context, String str) {
        b.a();
        String d = b.d(str, "");
        String b = d.b(context, str);
        if (!TextUtils.isEmpty(d) && !d.equals(b)) {
            return d;
        }
        b = "JClientsHelper";
        StringBuilder append = new StringBuilder("need not ").append(str).append(" userctrl,newest version:");
        if (TextUtils.isEmpty(d)) {
            d = "null";
        }
        cn.jiguang.e.d.a(b, append.append(d).toString());
        return "";
    }

    private void a(short s, String str, String str2) {
        cn.jiguang.e.d.c("JClientsHelper", "sendUserCtrlInfo sdkType:" + str2 + ",property:" + s + ",verInfo:" + str);
        long d = d.d(null);
        int a = d.a();
        long h = a.h();
        OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
        outputDataUtil.writeU16(0);
        outputDataUtil.writeU8(0);
        outputDataUtil.writeU8(26);
        outputDataUtil.writeU64(h);
        outputDataUtil.writeU32((long) a);
        outputDataUtil.writeU64(d);
        outputDataUtil.writeU8(s);
        outputDataUtil.writeU8(1);
        outputDataUtil.writeByteArrayincludeLength(str.getBytes());
        outputDataUtil.writeU16At(outputDataUtil.current(), 0);
        byte[] toByteArray = outputDataUtil.toByteArray();
        cn.jiguang.d.b.d.a();
        cn.jiguang.d.b.d.a(toByteArray, SdkType.JCORE.name(), 0);
        this.g.put(Long.valueOf(h), str2);
    }

    public static boolean e() {
        b.a();
        return b.a(0);
    }

    private static boolean g() {
        boolean z = false;
        try {
            Class.forName("cn.jpush.android.api.JPushInterface");
            z = true;
        } catch (ClassNotFoundException e) {
            cn.jiguang.e.d.a("JClientsHelper", "isPluginJpushSDK:" + e.getMessage());
        }
        cn.jiguang.e.d.a("JClientsHelper", "isPluginJpushSDK:" + z);
        return z;
    }

    private static boolean h() {
        boolean z = false;
        try {
            Class.forName("cn.jpush.im.android.api.JMessageClient");
            z = true;
        } catch (ClassNotFoundException e) {
            cn.jiguang.e.d.a("JClientsHelper", "isPluginJMessageSDK:" + e.getMessage());
        }
        cn.jiguang.e.d.a("JClientsHelper", "isPluginJMessageSDK:" + z);
        return z;
    }

    private static boolean i() {
        boolean z = false;
        try {
            Class.forName("cn.jiguang.analytics.android.api.JAnalyticsInterface");
            z = true;
        } catch (ClassNotFoundException e) {
            cn.jiguang.e.d.a("JClientsHelper", "isPluginJanalyticsSDK:" + e.getMessage());
        }
        cn.jiguang.e.d.a("JClientsHelper", "isPluginJanalyticsSDK:" + z);
        return z;
    }

    private static boolean j() {
        boolean z = false;
        try {
            Class.forName("cn.jiguang.share.android.api.JShareInterface");
            z = true;
        } catch (ClassNotFoundException e) {
            cn.jiguang.e.d.a("JClientsHelper", "isPluginJshareSDK:" + e.getMessage());
        }
        cn.jiguang.e.d.a("JClientsHelper", "isPluginJshareSDK:" + z);
        return z;
    }

    public final void a(Context context) {
        if (context == null) {
            cn.jiguang.e.d.g("JClientsHelper", "handleUserCtrl failed,context is null");
            return;
        }
        String a = a(context, SdkType.JPUSH.name());
        String a2 = a(context, SdkType.JMESSAGE.name());
        String a3 = a(context, SdkType.JANALYTICS.name());
        String a4 = a(context, SdkType.JSHARE.name());
        if (!TextUtils.isEmpty(a)) {
            a((short) 1, a, SdkType.JPUSH.name());
        }
        if (!TextUtils.isEmpty(a2)) {
            a((short) 2, a2, SdkType.JMESSAGE.name());
        }
        if (!TextUtils.isEmpty(a3)) {
            a((short) 4, a3, SdkType.JANALYTICS.name());
        }
        if (!TextUtils.isEmpty(a4)) {
            a((short) 5, a4, SdkType.JSHARE.name());
        }
    }

    public final void a(Context context, long j) {
        String str = (String) this.g.remove(Long.valueOf(j));
        if (TextUtils.isEmpty(str)) {
            cn.jiguang.e.d.c("JClientsHelper", "userCtrlSuccess but not found rid:" + j);
        } else {
            cn.jiguang.e.d.c("JClientsHelper", "userCtrlSuccess rid:" + j + ",sdkType:" + str);
            b.a();
            String d = b.d(str, "");
            if (TextUtils.isEmpty(d)) {
                cn.jiguang.e.d.c("JClientsHelper", "userCtrlSuccess but not found sdkversion by sdkType:" + str);
            } else {
                d.a(context, str, d);
            }
        }
        if (!b(context)) {
            cn.jiguang.d.b.d.a().c();
        }
    }

    public final void a(Context context, long j, int i) {
        String str = (String) this.g.remove(Long.valueOf(j));
        if (TextUtils.isEmpty(str)) {
            cn.jiguang.e.d.c("JClientsHelper", "onUserCtrlFailed but not found rid:" + j);
        } else {
            cn.jiguang.e.d.c("JClientsHelper", "onUserCtrlFailed rid:" + j + ",sdkType:" + str + ",errorCode:" + i);
            b.a();
            if (TextUtils.isEmpty(b.d(str, ""))) {
                cn.jiguang.e.d.c("JClientsHelper", "onUserCtrlFailed but not found sdkversion by sdkType:" + str);
            }
        }
        if ((this.g == null || this.g.isEmpty()) && !this.b && !this.a) {
            cn.jiguang.d.b.d.a().c();
        }
    }

    public final short b() {
        short s = (short) 0;
        if (this.a) {
            s = (short) 1;
        }
        return this.b ? (a.y() >= 0 || a.x()) ? (short) (s | 32) : (short) (s | 64) : s;
    }

    public final void b(Context context, long j) {
        cn.jiguang.e.d.c("JClientsHelper", "onUserCtrlTimeout rid:" + j);
        this.g.remove(Long.valueOf(j));
        if ((this.g == null || this.g.isEmpty()) && !this.b && !this.a) {
            cn.jiguang.d.b.d.a().c();
        }
    }

    public final boolean b(Context context) {
        if (!(this.b || this.a)) {
            boolean z;
            if (context == null) {
                cn.jiguang.e.d.h("JClientsHelper", "get isNeedUserCtrl failed,context is null");
            } else if (this.c && !TextUtils.isEmpty(a(context, SdkType.JANALYTICS.name()))) {
                z = true;
                if (!z) {
                    return false;
                }
            } else if (this.d && !TextUtils.isEmpty(a(context, SdkType.JSHARE.name()))) {
                z = true;
                if (z) {
                    return false;
                }
            } else if (this.a && !TextUtils.isEmpty(a(context, SdkType.JPUSH.name()))) {
                z = true;
                if (z) {
                    return false;
                }
            } else if (this.b && !TextUtils.isEmpty(a(context, SdkType.JMESSAGE.name()))) {
                z = true;
                if (z) {
                    return false;
                }
            }
            z = false;
            if (z) {
                return false;
            }
        }
        return true;
    }

    public final short c() {
        short s = (short) 0;
        if (this.a) {
            s = (short) 1;
        }
        if (this.c) {
            s = (short) (s | 4);
        }
        if (this.d) {
            s = (short) (s | 8);
        }
        return this.b ? (short) (s | 32) : s;
    }

    public final short d() {
        short s = (short) 0;
        if (this.a) {
            s = (short) 1;
        }
        if (this.b) {
            s = (a.y() >= 0 || a.x()) ? (short) (s | 32) : (short) (s | 64);
        }
        if (this.c) {
            s = (short) (s | 4);
        }
        return this.d ? (short) (s | 8) : s;
    }

    public final boolean f() {
        return this.b || this.a;
    }
}
