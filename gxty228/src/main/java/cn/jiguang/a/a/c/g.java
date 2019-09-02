package cn.jiguang.a.a.c;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.SdkType;
import cn.jiguang.d.a.d;
import cn.jiguang.d.d.o;
import cn.jiguang.g.a;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONObject;

public final class g {
    public static void a(Context context, int i) {
        boolean z = true;
        String str = (String) d.b(context, "report_notify_state", null);
        g gVar = new g();
        boolean a = VERSION.SDK_INT >= 24 ? a(context) : b(context);
        if (!TextUtils.isEmpty(str)) {
            boolean z2;
            boolean z3;
            if (TextUtils.equals("ON", str)) {
                z2 = true;
                z3 = false;
            } else if (TextUtils.equals("OFF", str)) {
                z2 = false;
                z3 = false;
            } else {
                z2 = false;
                z3 = true;
            }
            if (z3) {
                cn.jiguang.e.d.c("NotificationState", "notification state do not changed");
                z = z3;
            } else if (z2 == a) {
                z = false;
            }
        }
        cn.jiguang.e.d.c("NotificationState", "lastCacheNotificationState:" + str + ",currentNotificationSate:" + a + ",isNeedReport:" + z);
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("notification_state", a);
                jSONObject.put("imei", a.d(context, a.d(context, "")));
                jSONObject.put("device_id", a.h(context));
                jSONObject.put("trigger_scene", i);
                o.b(null, jSONObject, "android_notification_state");
                if (JCoreInterface.reportHttpData(context, jSONObject, SdkType.JCORE.name())) {
                    d.a(context, "report_notify_state", a ? "ON" : "OFF");
                    return;
                }
                return;
            } catch (Throwable th) {
                cn.jiguang.e.d.g("NotificationState", "report notification state failed, error:" + th);
                return;
            }
        }
        cn.jiguang.e.d.c("NotificationState", "do not need report notification state");
    }

    @TargetApi(24)
    private static boolean a(Context context) {
        try {
            return ((NotificationManager) context.getSystemService("notification")).areNotificationsEnabled();
        } catch (Throwable th) {
            cn.jiguang.e.d.g("NotificationState", "invoke areNotificationsEnabled method failed, error:" + th);
            return true;
        }
    }

    private static boolean b(Context context) {
        try {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            String packageName = context.getApplicationContext().getPackageName();
            int i = applicationInfo.uid;
            return ((Integer) Class.forName(AppOpsManager.class.getName()).getMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(appOpsManager, new Object[]{Integer.valueOf(((Integer) Class.forName(AppOpsManager.class.getName()).getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), packageName})).intValue() == 0;
        } catch (ClassNotFoundException e) {
            return true;
        } catch (NoSuchMethodException e2) {
            return true;
        } catch (NoSuchFieldException e3) {
            return true;
        } catch (InvocationTargetException e4) {
            return true;
        } catch (IllegalAccessException e5) {
            return true;
        } catch (RuntimeException e6) {
            return true;
        } catch (Throwable th) {
            cn.jiguang.e.d.g("NotificationState", "getNotificationStateCommon failed, other error:" + th);
            return true;
        }
    }
}
