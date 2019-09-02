package cn.jiguang.d.d;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.a.c.c;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.d.b.f;
import cn.jiguang.d.e.a.a.b;
import cn.jiguang.d.g.g;
import cn.jiguang.e.d;
import cn.jiguang.g.a;
import cn.jpush.android.service.PushService;

public final class j {
    private static final Object c = new Object();
    private static volatile j d;
    private boolean a;
    private Context b;
    private boolean e;

    public static j a() {
        if (d == null) {
            synchronized (c) {
                if (d == null) {
                    d = new j();
                }
            }
        }
        return d;
    }

    private void a(Context context) {
        if (!this.a) {
            if (context == null) {
                d.j("JServiceCommandHelper", "init failed");
                return;
            }
            this.b = context.getApplicationContext();
            if (!a.p(cn.jiguang.d.a.e)) {
                cn.jiguang.d.b.a.a = 0;
            }
            this.e = cn.jiguang.d.b.a.b(this.b);
            this.a = true;
        }
    }

    static /* synthetic */ void a(j jVar, String str, Bundle bundle) {
        d.c("JServiceCommandHelper", "Action - handleAction:" + str + " bundle:" + bundle);
        try {
            cn.jiguang.d.b.d.a().a(jVar.b);
            String str2 = b(jVar.b) + ".";
            if (str.startsWith(str2)) {
                str = str.substring(str2.length());
            }
            if (str.equals("intent.INIT")) {
                if (f.a.get() == 0) {
                    cn.jiguang.d.b.d.a().f();
                }
            } else if (str.equals("senddata.action")) {
                if (f.a.get() == 0) {
                    cn.jiguang.d.b.d.a().f();
                    return;
                }
                r0 = bundle.getByteArray("datas");
                if (r0 != null) {
                    r0 = b.a(r0, 1);
                    bundle.getInt("cmd");
                    cn.jiguang.d.f.d.a().b().a(r0);
                }
            } else if ("intent.RTC".equals(str)) {
                cn.jiguang.d.b.d.a().b(bundle);
            } else if ("intent.CONNECTIVITY_CHANGE".equals(str)) {
                cn.jiguang.d.b.d.a().a(bundle);
            } else if (str.equals("run.action")) {
                String string = bundle.getString("sdktype");
                b.a();
                b.a(jVar.b, string, f.a.get(), bundle, cn.jiguang.d.b.d.a().b());
            } else if (str.equals("intent.STOPPUSH")) {
                cn.jiguang.d.b.d.a().a(bundle.getString("sdktype"), bundle, cn.jiguang.d.a.d.i(jVar.b));
            } else if (str.equals("intent.RESTOREPUSH")) {
                str2 = bundle.getString("sdktype");
                cn.jiguang.d.b.d a = cn.jiguang.d.b.d.a();
                cn.jiguang.d.a.d.i(jVar.b);
                a.a(str2, bundle);
            } else if (str.equals("sendrequestdata.action")) {
                r0 = bundle.getByteArray("datas");
                int i = bundle.getInt("timeout");
                String string2 = bundle.getString("sdktype");
                cn.jiguang.d.b.d.a();
                cn.jiguang.d.b.d.a(r0, string2, i);
            } else if (str.equals("intent.power.save")) {
                cn.jiguang.d.b.d.a().a(bundle.getBoolean("key_power_save"));
            } else if (str.equals("cn.jpush.android.intent.check.notification.state")) {
                c.b(jVar.b, bundle.getInt("key_trigger_scene"));
            } else {
                d.g("JServiceCommandHelper", "Unhandled service action - " + str);
            }
        } catch (Throwable th) {
            d.g("JServiceCommandHelper", "handleAction failed", th);
        }
    }

    private static String b(Context context) {
        String str = cn.jiguang.d.a.c;
        if (TextUtils.isEmpty(str) && context != null) {
            str = context.getPackageName();
        }
        return str == null ? "" : str;
    }

    private boolean c(Context context, String str, Bundle bundle) {
        if (!d(context, str, bundle)) {
            try {
                Intent intent = new Intent(context, PushService.class);
                intent.setAction(str);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                context.startService(intent);
            } catch (Throwable th) {
                d.h("JServiceCommandHelper", "throwable ,cant start service" + th.getMessage() + ", will use aidl to do action");
                return false;
            }
        }
        return true;
    }

    private static boolean d(Context context, String str, Bundle bundle) {
        try {
            if (cn.jiguang.g.a.a.c()) {
                d.c("JServiceCommandHelper", "use binder do action");
                cn.jiguang.g.a.a.b().a(str, bundle);
                return true;
            }
            d.g("JServiceCommandHelper", "aidl is null, cant do action");
            cn.jiguang.d.a.a(context, true);
            return false;
        } catch (Throwable th) {
            d.h("JServiceCommandHelper", "throwable , remote call failed, error:" + th);
            return false;
        }
    }

    public final void a(Context context, String str, Bundle bundle) {
        d.c("JServiceCommandHelper", "callAction action:" + str + " bundle:" + (bundle == null ? "null" : bundle.toString()));
        try {
            a(context);
            if (this.b == null) {
                this.b = context.getApplicationContext();
            }
            g.a().a(new k(this, context, str, bundle, 1));
        } catch (Throwable th) {
            d.g("JServiceCommandHelper", "callAction failed", th);
        }
    }

    public final void b(Context context, String str, Bundle bundle) {
        d.c("JServiceCommandHelper", "onAction action:" + str + " bundle:" + (bundle == null ? "null" : bundle.toString()));
        try {
            if (JCoreInterface.init(context, false)) {
                a(context);
                if (context == null) {
                    d.g("JServiceCommandHelper", "onPushAction context is null");
                    return;
                }
                String str2 = b(context) + "." + str;
                if (cn.jiguang.d.b.a.c()) {
                    c(context, str2, bundle);
                    return;
                } else {
                    g.a().a(new k(this, context, str2, bundle, 0));
                    return;
                }
            }
            d.g("JServiceCommandHelper", " callInterface jcore init failed");
        } catch (Throwable th) {
            d.g("JServiceCommandHelper", "onAction failed", th);
        }
    }
}
