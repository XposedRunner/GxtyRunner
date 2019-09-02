package cn.jpush.android.c;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.MultiSpHelper;
import cn.jiguang.api.SdkType;
import cn.jpush.android.a;
import cn.jpush.android.b;
import cn.jpush.android.d.e;
import cn.jpush.android.service.PushReceiver;
import cn.jpush.android.service.c;
import cn.jpush.client.android.BuildConfig;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.concurrent.atomic.AtomicBoolean;

public class g {
    private static volatile g d;
    private AtomicBoolean a = new AtomicBoolean(false);
    private byte b = (byte) 0;
    private e c;
    private boolean e = false;

    private g() {
    }

    public static g a() {
        if (d == null) {
            synchronized (g.class) {
                if (d == null) {
                    d = new g();
                }
            }
        }
        return d;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(android.content.Context r5) {
        /*
        r4 = this;
        monitor-enter(r4);
        if (r5 != 0) goto L_0x000c;
    L_0x0003:
        r0 = "PluginPlatformsInterface";
        r1 = "context was null";
        cn.jpush.android.d.e.h(r0, r1);	 Catch:{ all -> 0x0071 }
    L_0x000a:
        monitor-exit(r4);
        return;
    L_0x000c:
        r0 = r4.a;	 Catch:{ all -> 0x0071 }
        r0 = r0.get();	 Catch:{ all -> 0x0071 }
        if (r0 != 0) goto L_0x000a;
    L_0x0014:
        r0 = cn.jpush.android.c.i.a(r5);	 Catch:{ all -> 0x0071 }
        r4.b = r0;	 Catch:{ all -> 0x0071 }
        r0 = r4.b;	 Catch:{ all -> 0x0071 }
        switch(r0) {
            case 1: goto L_0x0074;
            case 2: goto L_0x007c;
            case 3: goto L_0x0084;
            default: goto L_0x001f;
        };	 Catch:{ all -> 0x0071 }
    L_0x001f:
        r0 = "PluginPlatformsInterface";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0071 }
        r2 = "whichPlatform - ";
        r1.<init>(r2);	 Catch:{ all -> 0x0071 }
        r2 = r4.b;	 Catch:{ all -> 0x0071 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0071 }
        r1 = r1.toString();	 Catch:{ all -> 0x0071 }
        cn.jpush.android.d.e.f(r0, r1);	 Catch:{ all -> 0x0071 }
        r0 = cn.jpush.android.c.i.b(r5);	 Catch:{ all -> 0x0071 }
        r4.e = r0;	 Catch:{ all -> 0x0071 }
        r0 = "PluginPlatformsInterface";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0071 }
        r2 = "isIntegrateFCM -";
        r1.<init>(r2);	 Catch:{ all -> 0x0071 }
        r2 = r4.e;	 Catch:{ all -> 0x0071 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0071 }
        r1 = r1.toString();	 Catch:{ all -> 0x0071 }
        cn.jpush.android.d.e.f(r0, r1);	 Catch:{ all -> 0x0071 }
        r0 = r4.e;	 Catch:{ all -> 0x0071 }
        if (r0 != 0) goto L_0x0061;
    L_0x0055:
        r0 = 8;
        r1 = 0;
        cn.jpush.android.b.a(r5, r0, r1);	 Catch:{ all -> 0x0071 }
        r0 = 8;
        r1 = 0;
        cn.jpush.android.b.b(r5, r0, r1);	 Catch:{ all -> 0x0071 }
    L_0x0061:
        if (r5 != 0) goto L_0x008c;
    L_0x0063:
        r0 = "PluginPlatformsInterface";
        r1 = "context was null";
        cn.jpush.android.d.e.i(r0, r1);	 Catch:{ all -> 0x0071 }
    L_0x006a:
        r0 = r4.a;	 Catch:{ all -> 0x0071 }
        r1 = 1;
        r0.set(r1);	 Catch:{ all -> 0x0071 }
        goto L_0x000a;
    L_0x0071:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x0074:
        r0 = new cn.jpush.android.c.j;	 Catch:{ all -> 0x0071 }
        r0.<init>(r5);	 Catch:{ all -> 0x0071 }
        r4.c = r0;	 Catch:{ all -> 0x0071 }
        goto L_0x001f;
    L_0x007c:
        r0 = new cn.jpush.android.c.b;	 Catch:{ all -> 0x0071 }
        r0.<init>(r5);	 Catch:{ all -> 0x0071 }
        r4.c = r0;	 Catch:{ all -> 0x0071 }
        goto L_0x001f;
    L_0x0084:
        r0 = new cn.jpush.android.c.d;	 Catch:{ all -> 0x0071 }
        r0.<init>(r5);	 Catch:{ all -> 0x0071 }
        r4.c = r0;	 Catch:{ all -> 0x0071 }
        goto L_0x001f;
    L_0x008c:
        r0 = r4.e;	 Catch:{ Throwable -> 0x00ce }
        if (r0 == 0) goto L_0x0093;
    L_0x0090:
        com.google.firebase.FirebaseApp.initializeApp(r5);	 Catch:{ Throwable -> 0x00ce }
    L_0x0093:
        r0 = r4.c;	 Catch:{ Throwable -> 0x00ae }
        if (r0 == 0) goto L_0x00ed;
    L_0x0097:
        r0 = r5.getApplicationContext();	 Catch:{ Throwable -> 0x00ae }
        r0 = cn.jpush.android.api.JPushInterface.isPushStopped(r0);	 Catch:{ Throwable -> 0x00ae }
        if (r0 != 0) goto L_0x00e4;
    L_0x00a1:
        r0 = r4.c;	 Catch:{ Throwable -> 0x00ae }
        r0.a(r5);	 Catch:{ Throwable -> 0x00ae }
        r0 = "PluginPlatformsInterface";
        r1 = "plugin plateform register start";
        cn.jpush.android.d.e.f(r0, r1);	 Catch:{ Throwable -> 0x00ae }
        goto L_0x006a;
    L_0x00ae:
        r0 = move-exception;
        r1 = "PluginPlatformsInterface";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0071 }
        r2.<init>();	 Catch:{ all -> 0x0071 }
        r3 = r4.b;	 Catch:{ all -> 0x0071 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0071 }
        r3 = " register error:";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0071 }
        r0 = r2.append(r0);	 Catch:{ all -> 0x0071 }
        r0 = r0.toString();	 Catch:{ all -> 0x0071 }
        cn.jpush.android.d.e.h(r1, r0);	 Catch:{ all -> 0x0071 }
        goto L_0x006a;
    L_0x00ce:
        r0 = move-exception;
        r1 = "PluginPlatformsInterface";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0071 }
        r3 = "#FirebaseAPP init error:";
        r2.<init>(r3);	 Catch:{ all -> 0x0071 }
        r0 = r2.append(r0);	 Catch:{ all -> 0x0071 }
        r0 = r0.toString();	 Catch:{ all -> 0x0071 }
        cn.jpush.android.d.e.h(r1, r0);	 Catch:{ all -> 0x0071 }
        goto L_0x0093;
    L_0x00e4:
        r0 = "PluginPlatformsInterface";
        r1 = "stopPush was called. will not init the third push";
        cn.jpush.android.d.e.h(r0, r1);	 Catch:{ Throwable -> 0x00ae }
        goto L_0x006a;
    L_0x00ed:
        r0 = "PluginPlatformsInterface";
        r1 = "can not find the plugin platform action";
        cn.jpush.android.d.e.g(r0, r1);	 Catch:{ Throwable -> 0x00ae }
        goto L_0x006a;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.c.g.a(android.content.Context):void");
    }

    public final void b(Context context) {
        if (context == null) {
            e.i("PluginPlatformsInterface", "context was null");
            return;
        }
        a(context);
        try {
            if (this.c != null) {
                this.c.b(context);
            } else {
                e.g("PluginPlatformsInterface", "can not find the plugin platform action");
            }
        } catch (Throwable th) {
            e.h("PluginPlatformsInterface", this.b + " resumePush error:" + th);
        }
    }

    public final void c(Context context) {
        if (context == null) {
            e.i("PluginPlatformsInterface", "context was null");
            return;
        }
        a(context);
        try {
            if (this.c != null) {
                this.c.c(context);
            } else {
                e.g("PluginPlatformsInterface", "can not find the plugin platform action");
            }
        } catch (Throwable th) {
            e.h("PluginPlatformsInterface", this.b + " stopPush error:" + th);
        }
    }

    public final void d(Context context) {
        if (context == null) {
            context = a.e;
        }
        if (context == null) {
            e.i("PluginPlatformsInterface", "context was null");
            return;
        }
        a(context);
        e.e("PluginPlatformsInterface", "uploadRegIdAfterLogin");
        if (this.c != null) {
            a(context, this.b);
            String a = b.a(context, this.b);
            if (a(context, this.b, a)) {
                a(context, this.b, a);
            }
        } else {
            e.e("PluginPlatformsInterface", "mPluginPlatformAction is null");
        }
        if (this.e) {
            e.c("PluginPlatformsInterface", "sendBroadCastToUploadFCMToken");
            Object b = b();
            if (TextUtils.isEmpty(b)) {
                try {
                    Intent intent = new Intent(context, PushReceiver.class);
                    intent.setAction("cn.jpush.android.intent.plugin.platform.REFRESSH_REGID");
                    Bundle bundle = new Bundle();
                    bundle.putString("sdktype", SdkType.JPUSH.name());
                    intent.putExtras(bundle);
                    intent.setPackage(context.getPackageName());
                    context.sendBroadcast(intent);
                    return;
                } catch (Throwable th) {
                    e.h("PluginPlatformsInterface", "send ACTION_PLUGIN_PALTFORM_REQ_REFRESSH_REGID failed:" + th);
                    return;
                }
            }
            b(context, b);
            return;
        }
        e.e("PluginPlatformsInterface", "not support fcm");
    }

    public final void a(Context context, String str) {
        if (context == null) {
            context = a.e;
        }
        if (context == null) {
            e.i("PluginPlatformsInterface", "context was null");
            return;
        }
        e.e("PluginPlatformsInterface", "uploadRegID regid:" + str);
        a(context);
        if (this.c != null) {
            a(context, this.b);
            if (a(context, this.b, str)) {
                a(context, this.b, str);
                return;
            }
            return;
        }
        e.e("PluginPlatformsInterface", "mPluginPlatformAction is null");
    }

    public final void e(Context context) {
        if (context == null) {
            context = a.e;
        }
        if (context == null) {
            e.i("PluginPlatformsInterface", "context was null");
            return;
        }
        e.e("PluginPlatformsInterface", "refeshFcmToken");
        a(context);
        if (this.e) {
            b(context, b());
        }
    }

    public final void b(Context context, String str) {
        if (context == null) {
            context = a.e;
        }
        if (context == null) {
            e.i("PluginPlatformsInterface", "context was null");
            return;
        }
        e.e("PluginPlatformsInterface", "uploadRegID regid:" + str);
        a(context);
        if (this.e) {
            boolean z;
            Object appKey = JCoreInterface.getAppKey();
            if (TextUtils.equals(appKey, MultiSpHelper.getString(context, "flag_clear_fcm_rid", null))) {
                z = false;
            } else {
                MultiSpHelper.commitString(context, "flag_clear_fcm_rid", appKey);
                z = true;
            }
            if (z) {
                e.d("PluginPlatformsInterface", "appkey changed,will clear fcm token");
                b.b(context, 8, false);
                b.a(context, 8, null);
            } else {
                e.d("PluginPlatformsInterface", "appkey not change,will not clear fcm token");
            }
            if (a(context, 8, str)) {
                a(context, (byte) 8, str);
                return;
            }
            return;
        }
        e.e("PluginPlatformsInterface", "not support fcm");
    }

    private static String b() {
        String str = null;
        try {
            str = FirebaseInstanceId.getInstance().getToken();
        } catch (Throwable th) {
            e.d("PluginPlatformsInterface", "get fcm token error:", th);
        }
        e.f("PluginPlatformsInterface", "getFcmToken:" + String.valueOf(str));
        return str;
    }

    private static boolean a(Context context, int i, String str) {
        if (!b.b(context, i)) {
            e.e("PluginPlatformsInterface", "need upload -- last upload failed or never upload success");
            return true;
        } else if (TextUtils.equals(b.a(context, i), str)) {
            e.e("PluginPlatformsInterface", "need not upload regId");
            return false;
        } else {
            e.e("PluginPlatformsInterface", "need upload -- regId changed");
            return true;
        }
    }

    private static void a(Context context, byte b, String str) {
        b.b(context, (int) b, false);
        b.a(context, (int) b, str);
        Bundle bundle = new Bundle();
        c.a(context, bundle, "intent.plugin.platform.REFRESSH_REGID");
        bundle.putString("plugin.platform.regid ", str);
        bundle.putByte("plugin.platform.type", b);
        JCoreInterface.sendAction(context, a.a, bundle);
    }

    public final byte f(Context context) {
        a(context);
        return this.b;
    }

    public final boolean g(Context context) {
        a(context);
        return this.e;
    }

    private void a(Context context, int i) {
        boolean z = true;
        e.f("PluginPlatformsInterface", "checkClearRegId");
        Object a = this.c.a();
        Object b = this.c.b();
        if (TextUtils.isEmpty(b) || TextUtils.isEmpty(a)) {
            e.f("PluginPlatformsInterface", "platform:" + i + " appkey or appid is empty,need not clear plugin rid");
            return;
        }
        Object appKey = JCoreInterface.getAppKey();
        if (TextUtils.isEmpty(appKey)) {
            e.f("PluginPlatformsInterface", "jpush appkey is empty,need not clear plugin rid");
            return;
        }
        String str = BuildConfig.VERSION_NAME;
        CharSequence string = MultiSpHelper.getString(context, "flag_clear_plugin_rid", null);
        a = cn.jpush.android.d.a.a(a + b + appKey + str);
        if (TextUtils.isEmpty(string)) {
            MultiSpHelper.commitString(context, "flag_clear_plugin_rid", a);
        } else {
            if (!TextUtils.isEmpty(a)) {
                if (!TextUtils.equals(string, a)) {
                    MultiSpHelper.commitString(context, "flag_clear_plugin_rid", a);
                }
            }
            if (z) {
                e.f("PluginPlatformsInterface", "app info not change, will not clear plugin rid");
                return;
            }
            e.f("PluginPlatformsInterface", "app info changed , will clear plugin rid");
            b.b(context, i, false);
            b.a(context, i, null);
        }
        z = false;
        if (z) {
            e.f("PluginPlatformsInterface", "app info not change, will not clear plugin rid");
            return;
        }
        e.f("PluginPlatformsInterface", "app info changed , will clear plugin rid");
        b.b(context, i, false);
        b.a(context, i, null);
    }
}
