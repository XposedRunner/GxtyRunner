package cn.jiguang.api;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import cn.jiguang.a.a.c.e;
import cn.jiguang.a.a.d.c;
import cn.jiguang.d.a.d;
import cn.jiguang.d.d.b;
import cn.jiguang.d.d.j;
import cn.jiguang.d.d.o;
import cn.jiguang.d.h.f;
import cn.jiguang.g.a.a;
import cn.jiguang.net.HttpResponse;
import cn.jiguang.net.HttpUtils;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class JCoreInterface {
    public static String a = "cn.jpush.android.intent.DaemonService";
    private static boolean b = false;

    public static boolean canCallDirect() {
        return a.c();
    }

    public static JSONObject fillBaseReport(JSONObject jSONObject, String str) {
        return o.b(cn.jiguang.d.a.e, jSONObject, str);
    }

    public static String getAccountId() {
        return d.n(cn.jiguang.d.a.e);
    }

    public static boolean getAesConfig() {
        return true;
    }

    public static String getAppKey() {
        return d.i(null);
    }

    public static IBinder getBinderByType(String str, String str2) {
        if (a.c()) {
            try {
                return a.b().a(str, str2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getChannel() {
        return !b ? null : cn.jiguang.d.a.a.g("");
    }

    public static String getCommonConfigAppkey() {
        return !b ? "" : d.i(null);
    }

    public static boolean getConnectionState(Context context) {
        return !init(context, false) ? false : d.d();
    }

    public static String getDaemonAction() {
        return a;
    }

    public static boolean getDebugMode() {
        return cn.jiguang.d.a.b;
    }

    public static String getDeviceId(Context context) {
        return !cn.jiguang.d.a.a(context) ? "" : cn.jiguang.g.a.h(context);
    }

    public static String getHttpConfig(Context context, String str) {
        if (!cn.jiguang.d.a.a(context)) {
            return "";
        }
        HttpResponse httpGet = HttpUtils.httpGet(null, o.c(str));
        return (httpGet == null || httpGet.getResponseCode() != 200) ? null : httpGet.getResponseBody();
    }

    public static int getJCoreSDKVersionInt() {
        return 119;
    }

    public static long getNextRid() {
        return !b ? 0 : cn.jiguang.d.a.a.h();
    }

    public static String getRegistrationID(Context context) {
        return !init(context, false) ? "" : d.b(context);
    }

    public static long getReportTime() {
        return !b ? System.currentTimeMillis() / 1000 : cn.jiguang.d.a.a.t();
    }

    public static boolean getRuningFlag() {
        return cn.jiguang.d.b.a.a();
    }

    public static int getSid() {
        return !b ? 0 : d.a();
    }

    public static boolean getTestConn() {
        return cn.jiguang.d.a.a.c;
    }

    public static long getUid() {
        return !b ? 0 : d.d(null);
    }

    public static boolean init(Context context, boolean z) {
        if (b) {
            return true;
        }
        if (context == null) {
            cn.jiguang.e.d.h("JCoreInterface", "unexcepted - context was null");
            return false;
        }
        cn.jiguang.a.a.a(context);
        if (cn.jiguang.d.a.a(context)) {
            b = true;
            cn.jiguang.a.a.a(context.getApplicationContext(), "crash_log", "");
            return true;
        }
        cn.jiguang.e.d.h("JCoreInterface", "JCore init failed");
        return false;
    }

    public static void initAction(String str, Class<? extends JAction> cls) {
        b.a(str, cls.getName());
    }

    public static void initActionExtra(String str, Class<? extends JActionExtra> cls) {
        b.b(str, cls.getName());
    }

    public static void initCrashHandler(Context context) {
        if (cn.jiguang.d.a.a(context)) {
            e.a().b(context);
        }
    }

    public static boolean isServiceStoped(Context context) {
        return d.j(context);
    }

    public static boolean isTcpConnected() {
        return d.d();
    }

    public static boolean isValidRegistered() {
        return !b ? false : d.e(null);
    }

    public static void onFragmentPause(Context context, String str) {
        if (cn.jiguang.d.a.a(context)) {
            c.a().b(context, str);
        }
    }

    public static void onFragmentResume(Context context, String str) {
        if (cn.jiguang.d.a.a(context)) {
            c.a().a(context, str);
        }
    }

    public static void onKillProcess(Context context) {
        if (cn.jiguang.d.a.a(context)) {
            c.a().c(context);
        }
    }

    public static void onPause(Context context) {
        if (cn.jiguang.d.a.a(context) && cn.jiguang.a.a.b) {
            c.a().b(context);
        }
    }

    public static void onResume(Context context) {
        if (cn.jiguang.d.a.a(context) && cn.jiguang.a.a.b) {
            c.a().a(context);
        }
    }

    public static void processCtrlReport(int i) {
        cn.jiguang.a.c.a.a(i);
    }

    public static void register(Context context) {
        if (init(context, true)) {
            cn.jiguang.e.d.c("JCoreInterface", "Action - init registerOnly:");
            j.a().b(context, "intent.INIT", new Bundle());
        }
    }

    public static void report(Context context, JSONObject jSONObject, boolean z) {
        o.a(context, jSONObject, z);
    }

    public static boolean reportHttpData(Context context, Object obj, String str) {
        if (obj != null) {
            if (obj instanceof JSONObject) {
                o.a(context, (JSONObject) obj, str);
            } else if (obj instanceof JSONArray) {
                o.a(context, (JSONArray) obj, str);
            }
        }
        return true;
    }

    public static void requestPermission(Context context) {
        if (context == null) {
            cn.jiguang.e.d.h("JCoreInterface", "[requestPermission] context was null");
        } else if (!(context instanceof Activity)) {
            cn.jiguang.e.d.h("JCoreInterface", "[requestPermission] context must instanceof Activity");
        } else if (VERSION.SDK_INT >= 23 && context.getApplicationInfo().targetSdkVersion >= 23) {
            try {
                List a = cn.jiguang.g.a.a(context, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE"});
                if (a != null && !a.isEmpty()) {
                    cn.jiguang.e.d.a("JCoreInterface", "lackPermissions:" + a);
                    Class.forName("android.app.Activity").getDeclaredMethod("requestPermissions", new Class[]{String[].class, Integer.TYPE}).invoke(context, new Object[]{a.toArray(new String[a.size()]), Integer.valueOf(1)});
                }
            } catch (Exception e) {
                cn.jiguang.e.d.h("JCoreInterface", "#unexcepted - requestPermission e:" + e.getMessage());
            }
        }
    }

    public static void restart(Context context, String str, Bundle bundle, boolean z) {
        if (context == null) {
            cn.jiguang.e.d.h("JCoreInterface", "unexcepted - context was null");
        } else if (init(context, false)) {
            try {
                bundle.putString("sdktype", str);
                j.a().b(context, z ? "intent.RESTOREPUSH" : "intent.INIT", bundle);
            } catch (Throwable th) {
                cn.jiguang.e.d.g("JCoreInterface", "restart failed", th);
            }
        }
    }

    public static void sendAction(Context context, String str, Bundle bundle) {
        if (cn.jiguang.d.a.a(context)) {
            try {
                bundle.putString("sdktype", str);
                j.a().b(context, "run.action", bundle);
            } catch (Throwable th) {
                cn.jiguang.e.d.g("JCoreInterface", "sendAction failed", th);
            }
        }
    }

    public static void sendData(Context context, String str, int i, byte[] bArr) {
        if (init(context, false)) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("sdktype", str);
                bundle.putByteArray("datas", bArr);
                bundle.putInt("cmd", i);
                j.a().b(context, "senddata.action", bundle);
            } catch (Throwable th) {
                cn.jiguang.e.d.g("JCoreInterface", "sendData failed", th);
            }
        }
    }

    public static void sendRequestData(Context context, String str, int i, byte[] bArr) {
        if (init(context, false)) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("sdktype", str);
                bundle.putByteArray("datas", bArr);
                bundle.putInt("timeout", i);
                j.a().b(context, "sendrequestdata.action", bundle);
            } catch (Throwable th) {
                cn.jiguang.e.d.g("JCoreInterface", "sendRequestData failed", th);
            }
        }
    }

    public static void setAccountId(String str) {
        d.c(cn.jiguang.d.a.e, str);
    }

    public static void setAnalysisAction(JAnalyticsAction jAnalyticsAction) {
        if (jAnalyticsAction != null) {
            cn.jiguang.a.a.a = jAnalyticsAction;
        }
    }

    public static void setCanLaunchedStoppedService(boolean z) {
        f.a().b().a(z);
    }

    public static void setDaemonAction(String str) {
        a = str;
        f.a().b().c(str);
    }

    public static void setDebugMode(boolean z) {
        cn.jiguang.d.a.b = z;
    }

    public static void setImLBSEnable(Context context, boolean z) {
        cn.jiguang.e.d.c("JCoreInterface", "action - setImLBSEnable-control");
        if (cn.jiguang.d.a.a(context)) {
            cn.jiguang.a.b.a.a(context, z);
        }
    }

    public static void setLocationReportDelay(Context context, long j) {
        d.a(context, "location_report_delay", Long.valueOf(j));
    }

    public static void setLogEnable(boolean z) {
        cn.jiguang.e.a.a = z;
    }

    public static void setPowerSaveMode(Context context, boolean z) {
        if (b) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("sdktype", cn.jiguang.d.a.a);
                bundle.putBoolean("key_power_save", z);
                j.a().b(context, "intent.power.save", bundle);
            } catch (Throwable th) {
                cn.jiguang.e.d.g("JCoreInterface", "setDozeAndPowerEnable to pushservice error", th);
            }
        } else if (context == null) {
            cn.jiguang.e.d.h("JCoreInterface", "context is null,setDozeAndPowerEnable failed");
        } else {
            d.c(context, z);
        }
    }

    public static void setTestConn(boolean z) {
        cn.jiguang.d.a.a.c = z;
    }

    public static void setTestConnIPPort(String str, int i) {
        cn.jiguang.e.d.c("JCoreInterface", "Action - setTestConnIPPort ip:" + str + " port:" + i);
        cn.jiguang.d.a.a.a = str;
        cn.jiguang.d.a.a.b = i;
    }

    public static void stop(Context context, String str, Bundle bundle) {
        if (init(context, false)) {
            try {
                bundle.putString("sdktype", str);
                j.a().b(context, "intent.STOPPUSH", bundle);
            } catch (Throwable th) {
                cn.jiguang.e.d.g("JCoreInterface", "stop failed", th);
            }
        }
    }

    public static void stopCrashHandler(Context context) {
        if (cn.jiguang.d.a.a(context)) {
            e.a().c(context);
        }
    }

    public static void testCountryCode(String str) {
    }

    public static void triggerSceneCheck(Context context, int i) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("sdktype", cn.jiguang.d.a.a);
            bundle.putInt("key_trigger_scene", i);
            j.a().b(context, "cn.jpush.android.intent.check.notification.state", bundle);
        } catch (Throwable th) {
            cn.jiguang.e.d.g("JCoreInterface", "triggerSceneCheck to pushservice error", th);
        }
    }
}
