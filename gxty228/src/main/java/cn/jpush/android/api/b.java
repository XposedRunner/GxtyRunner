package cn.jpush.android.api;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jpush.android.a;
import cn.jpush.android.a.d;
import cn.jpush.android.a.i;
import cn.jpush.android.d.c;
import cn.jpush.android.d.e;
import cn.jpush.android.data.g;
import cn.jpush.android.service.PushReceiver;
import cn.jpush.android.ui.PopWinActivity;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.blankj.utilcode.constant.MemoryConstants;
import com.tencent.tauth.AuthActivity;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.Adler32;

public final class b {
    private static boolean a = false;

    public static void a(Context context, boolean z) {
        if (z) {
            while (true) {
                Integer valueOf = Integer.valueOf(i.a());
                if (valueOf.intValue() != 0) {
                    b(context, valueOf.intValue());
                } else {
                    return;
                }
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString(AuthActivity.ACTION_KEY, "intent.MULTI_PROCESS");
        bundle.putInt("multi_type", 10);
        JCoreInterface.sendAction(context, a.a, bundle);
    }

    public static void a(Context context, int i) {
        if (i > 0) {
            for (int i2 = 0; i2 < i; i2++) {
                Integer valueOf = Integer.valueOf(i.a());
                if (valueOf.intValue() != 0) {
                    b(context, valueOf.intValue());
                }
            }
        }
    }

    private static void b(Context context, int i) {
        e.c("NotificationHelper", "action:cleanNotification - notificationId:" + i);
        if (context == null) {
            context = a.e;
        }
        ((NotificationManager) context.getSystemService("notification")).cancel(i);
    }

    public static void a(Context context, cn.jpush.android.data.b bVar, int i) {
        e.c("NotificationHelper", "action:cleanNotification - messageId:" + bVar.c);
        if (context == null) {
            context = a.e;
        }
        ((NotificationManager) context.getSystemService("notification")).cancel(a(bVar, 0));
    }

    public static void a(final Context context, final cn.jpush.android.data.b bVar) {
        e.e("NotificationHelper", "start new thread");
        new Thread(new Runnable() {
            public final void run() {
                b.b(context, bVar);
            }
        }).start();
    }

    public static void b(Context context, cn.jpush.android.data.b bVar) {
        e.a("NotificationHelper", "action:showNotification");
        int a = a(bVar, 0);
        if (bVar.i && bVar.f) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (bVar instanceof g) {
                Map a2 = a(bVar);
                String packageName = TextUtils.isEmpty(bVar.o) ? context.getPackageName() : bVar.o;
                if (TextUtils.isEmpty(bVar.v)) {
                    a(context, a2, 0, "", packageName, bVar);
                    return;
                }
                PushNotificationBuilder b = JPushInterface.b(bVar.g);
                String developerArg0 = b.getDeveloperArg0();
                Notification buildNotification = b.buildNotification(a2);
                if (buildNotification == null || TextUtils.isEmpty(bVar.v)) {
                    e.h("NotificationHelper", "Got NULL notification. Give up to show.");
                    return;
                }
                PendingIntent activity;
                Intent c;
                if (bVar.a()) {
                    e.e("NotificationHelper", "delivery rich push type: " + ((g) bVar).L);
                    c = c(context, bVar);
                    if (c == null) {
                        e.i("NotificationHelper", "intent was null , drop rich notification");
                        return;
                    }
                    activity = PendingIntent.getActivity(context, a, c, AMapEngineUtils.HALF_MAX_P20_WIDTH);
                } else {
                    e.d("NotificationHelper", "running flag:" + JCoreInterface.getRuningFlag());
                    if (cn.jpush.android.d.a.c(context, PushReceiver.class.getCanonicalName())) {
                        c = new Intent("cn.jpush.android.intent.NOTIFICATION_OPENED_PROXY." + UUID.randomUUID().toString());
                        c.putExtra(JPushInterface.EXTRA_NOTI_TYPE, bVar.h);
                        if (JCoreInterface.getRuningFlag()) {
                            c.setClass(context, PopWinActivity.class);
                            c.putExtra("isNotification", true);
                        } else {
                            c.setClass(context, PushReceiver.class);
                        }
                    } else {
                        e.e("NotificationHelper", "the PushReceiver in manifest is deleted by some other apps,we should send the broadcast directly.");
                        Intent intent = new Intent(JPushInterface.ACTION_NOTIFICATION_OPENED);
                        intent.addCategory(packageName);
                        if ((VERSION.SDK_INT >= 25 || VERSION.SDK_INT < 21) && JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                            List a3 = cn.jpush.android.d.a.a(context, intent, null);
                            if (!a3.isEmpty()) {
                                intent.setComponent(new ComponentName(context, (String) a3.get(0)));
                            }
                        }
                        c = intent;
                    }
                    c.putExtra("sdktype", a.a);
                    a(c, a2, a);
                    c.putExtra("app", packageName);
                    if (!TextUtils.isEmpty(developerArg0)) {
                        c.putExtra(JPushInterface.EXTRA_NOTIFICATION_DEVELOPER_ARG0, developerArg0);
                    }
                    if (JCoreInterface.getRuningFlag()) {
                        activity = PendingIntent.getActivity(context, 0, c, MemoryConstants.GB);
                    } else {
                        activity = PendingIntent.getBroadcast(context, 0, c, MemoryConstants.GB);
                    }
                }
                buildNotification.contentIntent = activity;
                if (!JPushInterface.a(bVar.g)) {
                    int i;
                    if (1 == bVar.h) {
                        bVar.t = 1;
                    }
                    switch (bVar.t) {
                        case 0:
                            i = 1;
                            break;
                        case 1:
                            i = 16;
                            break;
                        case 2:
                            i = 32;
                            break;
                        default:
                            i = 1;
                            break;
                    }
                    buildNotification.flags = i | 1;
                }
                if (cn.jpush.android.d.a.d(context)) {
                    buildNotification.defaults = 0;
                }
                if (buildNotification != null) {
                    notificationManager.notify(a, buildNotification);
                }
                if (1 != bVar.h) {
                    a(context, a, true);
                    d.a(bVar.c, PointerIconCompat.TYPE_ZOOM_IN, null, context);
                }
                a(context, a2, a, developerArg0, packageName, bVar);
                return;
            }
            e.g("NotificationHelper", "unhandle entity entity");
        }
    }

    private static int a(cn.jpush.android.data.b bVar, int i) {
        String str = bVar.c;
        if (!TextUtils.isEmpty(bVar.d)) {
            str = bVar.d;
        }
        return a(str, i);
    }

    public static Map<String, String> a(cn.jpush.android.data.b bVar) {
        Map<String, String> hashMap = new HashMap();
        if (bVar != null) {
            e.a("NotificationHelper", "notificationContent:" + bVar.v + "\nnotificationTitle" + bVar.u + "\nnotificationStyle:" + bVar.w + "\nnotificationPriority:" + bVar.A + "\nnotificationBigText:" + bVar.x + "\nnotificationBigPicPath:" + bVar.y + "\nnotificationInbox:" + bVar.z + "\nnotificationCategory:" + bVar.B + "\nnotificationAlertType:" + bVar.l + "\nextraJson:" + bVar.n);
            hashMap.put(JPushInterface.EXTRA_MSG_ID, bVar.c);
            hashMap.put(JPushInterface.EXTRA_ALERT, bVar.v);
            hashMap.put(JPushInterface.EXTRA_ALERT_TYPE, bVar.l);
            if (!TextUtils.isEmpty(bVar.u)) {
                hashMap.put(JPushInterface.EXTRA_NOTIFICATION_TITLE, bVar.u);
            }
            if (!TextUtils.isEmpty(bVar.n)) {
                hashMap.put(JPushInterface.EXTRA_EXTRA, bVar.n);
            }
            if (bVar.w == 1 && !TextUtils.isEmpty(bVar.x)) {
                hashMap.put(JPushInterface.EXTRA_BIG_TEXT, bVar.x);
            } else if (bVar.w == 2 && !TextUtils.isEmpty(bVar.z)) {
                hashMap.put(JPushInterface.EXTRA_INBOX, bVar.z);
            } else if (bVar.w == 3 && !TextUtils.isEmpty(bVar.y)) {
                hashMap.put(JPushInterface.EXTRA_BIG_PIC_PATH, bVar.y);
            }
            if (bVar.A != 0) {
                hashMap.put(JPushInterface.EXTRA_NOTI_PRIORITY, bVar.A);
            }
            if (!TextUtils.isEmpty(bVar.B)) {
                hashMap.put(JPushInterface.EXTRA_NOTI_CATEGORY, bVar.B);
            }
        }
        return hashMap;
    }

    public static Intent c(Context context, cn.jpush.android.data.b bVar) {
        if (context == null) {
            e.h("NotificationHelper", "context was null");
            return null;
        }
        e.a("NotificationHelper", "entity:" + bVar);
        if (bVar != null) {
            if (3 == ((g) bVar).L || 4 == ((g) bVar).L || ((g) bVar).L == 0) {
                return cn.jpush.android.d.a.a(context, bVar, false);
            }
            if (2 == ((g) bVar).L) {
                Intent intent = new Intent(context, PopWinActivity.class);
                intent.putExtra("body", bVar);
                intent.addFlags(335544320);
                return intent;
            }
        }
        return cn.jpush.android.d.a.a(context, bVar, false);
    }

    private static boolean a(String str, CharSequence charSequence, int i, int i2) {
        int i3 = 3;
        if (a) {
            e.c("NotificationHelper", "hasCreateNotificationChannel, no need create repeat!");
            return true;
        } else if (VERSION.SDK_INT < 26) {
            e.c("NotificationHelper", "Device rom SDK < 26, no need use notificationChannel!");
            return false;
        } else if (a.e == null) {
            e.j("NotificationHelper", "ApplicationContext is null!");
            return false;
        } else if (a.e.getApplicationInfo().targetSdkVersion < 26) {
            e.c("NotificationHelper", "targetSdkVersion < 26, no need use notificationChannel!");
            return false;
        } else {
            NotificationManager notificationManager = (NotificationManager) a.e.getSystemService("notification");
            if (notificationManager == null) {
                e.j("NotificationHelper", "NotificationManager is null!");
                return false;
            }
            switch (i) {
                case -2:
                    i3 = 1;
                    break;
                case -1:
                    i3 = 2;
                    break;
                case 1:
                    i3 = 4;
                    break;
                case 2:
                    i3 = 5;
                    break;
            }
            try {
                Constructor constructor;
                try {
                    constructor = Class.forName("android.app.NotificationChannel").getConstructor(new Class[]{String.class, CharSequence.class, Integer.TYPE});
                    constructor.setAccessible(true);
                } catch (InvocationTargetException e) {
                    e.j("NotificationHelper", "new NotificationChannel fail:" + e.toString());
                }
                try {
                    Object newInstance = constructor.newInstance(new Object[]{str, charSequence, Integer.valueOf(i3)});
                    if (newInstance == null) {
                        e.j("NotificationHelper", "new NotificationChannel fail, return");
                        return false;
                    }
                    Boolean[] boolArr = new Boolean[]{Boolean.valueOf(true)};
                    Class[] clsArr = new Class[]{Boolean.TYPE};
                    if ((i2 & 4) != 0) {
                        try {
                            cn.jpush.android.d.i.a(newInstance, "enableLights", clsArr, boolArr);
                        } catch (Exception e2) {
                            e.h("NotificationHelper", "enableLights fail:" + e2.toString());
                        }
                    }
                    if ((i2 & 2) != 0) {
                        try {
                            cn.jpush.android.d.i.a(newInstance, "enableVibration", clsArr, boolArr);
                        } catch (Exception e22) {
                            e.h("NotificationHelper", "enableLights fail:" + e22.toString());
                        }
                    }
                    try {
                        cn.jpush.android.d.i.a(notificationManager, "createNotificationChannel", new Class[]{r4}, new Object[]{newInstance});
                    } catch (Exception e3) {
                        e.j("NotificationHelper", "createNotificationChannel fail:" + e3.toString());
                    }
                    a = true;
                    return true;
                } catch (InstantiationException e4) {
                    e4.printStackTrace();
                    return false;
                } catch (IllegalAccessException e5) {
                    e5.printStackTrace();
                    return false;
                }
            } catch (NoSuchMethodException e6) {
                e.j("NotificationHelper", "new NotificationChannel fail:" + e6.toString());
            } catch (Throwable th) {
                e.j("NotificationHelper", "new NotificationChannel fail:" + th.toString());
            }
        }
    }

    public static void a(Builder builder, String str, CharSequence charSequence, int i, int i2) {
        if (a(str, charSequence, i, i2)) {
            try {
                cn.jpush.android.d.i.a(builder, "setChannelId", new Class[]{String.class}, new String[]{str});
            } catch (Throwable th) {
                e.h("NotificationHelper", "setChannelId error" + th);
            }
        }
    }

    public static void a(Context context, int i, boolean z) {
        if (!i.b(i)) {
            i.a(i);
        }
        if (i.b() > cn.jpush.android.b.a(context)) {
            int a = i.a();
            if (a != 0) {
                b(context, a);
            }
        }
    }

    public static void a(Context context, Map<String, String> map, int i, String str, String str2, cn.jpush.android.data.b bVar) {
        Intent intent = new Intent(JPushInterface.ACTION_NOTIFICATION_RECEIVED);
        try {
            e.d("NotificationHelper", "Send push received broadcast to developer defined receiver");
            a(intent, (Map) map, i);
            if (!TextUtils.isEmpty(str)) {
                intent.putExtra(JPushInterface.EXTRA_NOTIFICATION_DEVELOPER_ARG0, str);
            }
            if (bVar.a() && (bVar instanceof g)) {
                g gVar = (g) bVar;
                if (!(gVar.L == 0 || gVar.L == 4)) {
                    if (gVar.Q != null && gVar.Q.startsWith("file://")) {
                        gVar.Q = gVar.Q.replaceFirst("file://", "");
                        intent.putExtra(JPushInterface.EXTRA_RICHPUSH_HTML_PATH, gVar.Q);
                    }
                    if (gVar.N != null && gVar.N.size() > 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        String b = c.b(context, bVar.c);
                        Iterator it = gVar.N.iterator();
                        while (it.hasNext()) {
                            String str3 = (String) it.next();
                            if (str3.startsWith("http://")) {
                                str3 = c.a(str3);
                            }
                            if (TextUtils.isEmpty(stringBuilder.toString())) {
                                stringBuilder.append(b).append(str3);
                            } else {
                                stringBuilder.append(",").append(b).append(str3);
                            }
                        }
                        intent.putExtra(JPushInterface.EXTRA_RICHPUSH_HTML_RES, stringBuilder.toString());
                    }
                }
            }
            intent.addCategory(str2);
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent, str2 + ".permission.JPUSH_MESSAGE");
        } catch (Throwable th) {
            e.h("NotificationHelper", "sendNotificationReceivedBroadcast error:" + th.getMessage());
            cn.jpush.android.d.a.b(context, intent, str2 + ".permission.JPUSH_MESSAGE");
        }
    }

    public static void a(Intent intent, Map<String, String> map, int i) {
        for (String str : map.keySet()) {
            intent.putExtra(str, (String) map.get(str));
        }
        if (i != 0) {
            intent.putExtra(JPushInterface.EXTRA_NOTIFICATION_ID, i);
        }
    }

    public static int a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            e.c("NotificationHelper", "action:getNofiticationID - empty messageId");
            return 0;
        }
        try {
            return Integer.valueOf(str).intValue();
        } catch (Exception e) {
            e.g("NotificationHelper", "Ths msgId is not a integer");
            Adler32 adler32 = new Adler32();
            adler32.update(str.getBytes());
            int value = (int) adler32.getValue();
            if (value < 0) {
                value = Math.abs(value);
            }
            value += 13889152 * i;
            if (value < 0) {
                return Math.abs(value);
            }
            return value;
        }
    }

    public static int a(int i) {
        switch (i) {
            case -1:
                int intValue;
                try {
                    intValue = ((Integer) a("R$drawable", new String[]{"jpush_notification_icon"}).get("jpush_notification_icon")).intValue();
                } catch (Exception e) {
                    e.i("NotificationHelper", "Can not load resource: jpush_notification_icon");
                    intValue = 0;
                }
                if (intValue <= 0) {
                    return 17301618;
                }
                return intValue;
            case 0:
                return 17301647;
            case 2:
                return 17301618;
            case 3:
                return 17301567;
            default:
                return 17301586;
        }
    }

    private static HashMap<String, Integer> a(String str, String[] strArr) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            throw new NullPointerException("parameter resType or fieldNames error.");
        }
        HashMap<String, Integer> hashMap = new HashMap();
        try {
            for (Class cls : Class.forName(a.e.getPackageName() + ".R").getDeclaredClasses()) {
                if (cls.getName().contains(str)) {
                    while (i <= 0) {
                        String str2 = strArr[0];
                        hashMap.put(str2, Integer.valueOf(cls.getDeclaredField(str2).getInt(str2)));
                        i++;
                    }
                    return hashMap;
                }
            }
        } catch (Throwable e) {
            e.c("NotificationHelper", "Failed to get res id for name.", e);
        }
        return hashMap;
    }

    public static void a(Notification notification, Context context, String str, String str2, PendingIntent pendingIntent) {
        try {
            Class.forName("android.app.Notification").getDeclaredMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification, new Object[]{context, str, str2, null});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
