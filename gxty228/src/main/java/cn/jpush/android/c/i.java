package cn.jpush.android.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jpush.android.b;
import cn.jpush.android.d.a;
import cn.jpush.android.d.e;
import com.huawei.hms.support.api.push.HuaweiPush;
import com.xiaomi.mipush.sdk.MiPushClient;

public final class i {
    private static final String a = "Xiaomi".toLowerCase();
    private static final String b = "huawei".toLowerCase();
    private static final String c = "Meizu".toLowerCase();

    public static byte a(Context context) {
        int i = 1;
        if (context == null) {
            e.h("PluginWhichPlatform", "context was null");
            return (byte) 0;
        }
        String str;
        try {
            str = Build.MANUFACTURER;
        } catch (Throwable th) {
            e.h("PluginWhichPlatform", "get MANUFACTURER failed - error:" + th);
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            e.h("PluginWhichPlatform", "MANUFACTURER was empty");
            return (byte) 0;
        }
        byte b;
        if (TextUtils.equals(a, str.toLowerCase())) {
            b = f(context) ? (byte) 1 : (byte) 0;
        } else if (TextUtils.equals(b, str.toLowerCase())) {
            if (g(context)) {
                b = (byte) 2;
                i = 2;
            } else {
                b = (byte) 0;
                i = 2;
            }
        } else if (!TextUtils.equals(c, str.toLowerCase())) {
            b = (byte) 0;
            i = 0;
        } else if (e(context)) {
            b = (byte) 3;
            i = 3;
        } else {
            b = (byte) 0;
            i = 3;
        }
        e.e("PluginWhichPlatform", "realPhoneType:" + i + " jPluginPlatformType:" + b);
        e.e("PluginWhichPlatform", "current cache rid is " + String.valueOf(b.a(context, i)) + ",regUploaded:" + b.b(context, i));
        if (b == (byte) 0) {
            b.a(context, i, null);
            b.b(context, i, false);
        }
        return b;
    }

    public static boolean b(Context context) {
        if (context != null) {
            return c(context);
        }
        e.h("PluginWhichPlatform", "context was null");
        return false;
    }

    private static boolean c(Context context) {
        if (!a.d(context, "cn.jpush.android.service.PluginFCMMessagingService") || !a.d(context, "cn.jpush.android.service.PluginFCMInstanceIdService")) {
            e.c("PluginWhichPlatform", "AndroidManifest.xml missing service: cn.jpush.android.platforms.PluginFCMMessagingService and cn.jpush.android.platforms.PluginFCMInstanceIdService");
            return false;
        } else if (VERSION.SDK_INT < 14) {
            e.h("PluginWhichPlatform", "Os version is lower 14 ,will not use fcm");
            return false;
        } else {
            try {
                Class.forName("com.google.firebase.iid.FirebaseInstanceId");
                if (d(context)) {
                    return true;
                }
                return false;
            } catch (Throwable th) {
                e.j("PluginWhichPlatform", "Please check *.jar files your project depends on, can't load class - com.google.firebase.iid.FirebaseInstanceId \nerror:" + th);
                if (!JCoreInterface.getDebugMode()) {
                    return false;
                }
                RuntimeException runtimeException = new RuntimeException("Please check *.jar files your project depends on.", th);
            }
        }
    }

    private static boolean d(Context context) {
        Object obj;
        Throwable th;
        Object obj2;
        String str = "com.google.android.gms";
        Object obj3;
        try {
            obj = (context.getPackageManager().getApplicationInfo(str, 0).flags & 1) != 0 ? 1 : null;
            if (obj == null) {
                try {
                    e.h("PluginWhichPlatform", "google play services is not system app!");
                    return false;
                } catch (NameNotFoundException e) {
                    obj3 = null;
                    e.d("PluginWhichPlatform", "no google play services in the device!");
                    if (obj != null) {
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    obj2 = obj;
                    obj = null;
                    e.b("PluginWhichPlatform", "get google play services error:", th);
                    obj3 = obj;
                    obj = obj2;
                    if (obj != null) {
                    }
                    return false;
                }
            }
            obj3 = a.e(context, "com.google.android.gms.version");
            if (obj3 == null) {
                return false;
            }
            obj3 = context.getPackageManager().getPackageInfo(str, 0).versionCode >= ((Integer) obj3).intValue() ? 1 : null;
            if (obj3 == null) {
                try {
                    e.h("PluginWhichPlatform", "google play services is out of date , please update.");
                } catch (NameNotFoundException e2) {
                    e.d("PluginWhichPlatform", "no google play services in the device!");
                    if (obj != null) {
                    }
                    return false;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    obj2 = obj;
                    obj = obj3;
                    th = th4;
                    e.b("PluginWhichPlatform", "get google play services error:", th);
                    obj3 = obj;
                    obj = obj2;
                    if (obj != null) {
                    }
                    return false;
                }
            }
            if (obj != null || r0 == null) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e3) {
            obj3 = null;
            obj = null;
            e.d("PluginWhichPlatform", "no google play services in the device!");
            if (obj != null) {
            }
            return false;
        } catch (Throwable th5) {
            th = th5;
            obj = null;
            obj2 = null;
            e.b("PluginWhichPlatform", "get google play services error:", th);
            obj3 = obj;
            obj = obj2;
            if (obj != null) {
            }
            return false;
        }
    }

    private static boolean e(Context context) {
        if (VERSION.SDK_INT < 11 || !a.c(context, "cn.jpush.android.service.PluginMeizuPlatformsReceiver")) {
            e.c("PluginWhichPlatform", "AndroidManifest.xml missing receiver: cn.jpush.android.service.PluginMeizuPlatformsReceiver");
            return false;
        }
        try {
            boolean z;
            if (Class.forName("com.meizu.cloud.pushsdk.PushManager") != null) {
                z = true;
            } else {
                z = false;
            }
            if (z && a()) {
                return true;
            }
            e.d("PluginWhichPlatform", "flyme version < 5.1.11.1A , Should not use MeizuPush");
            return false;
        } catch (Throwable th) {
            e.j("PluginWhichPlatform", "Please check *.jar files your project depends on, can't load class - com.meizu.cloud.pushsdk.PushManager \nerror:" + th);
            if (!JCoreInterface.getDebugMode()) {
                return false;
            }
            RuntimeException runtimeException = new RuntimeException("Please check *.aar files your project depends on.", th);
        }
    }

    private static boolean a() {
        try {
            Class[] clsArr = new Class[]{String.class};
            Object[] objArr = new Object[]{"ro.build.display.id"};
            Class cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getDeclaredMethod("get", clsArr).invoke(cls, objArr);
            e.e("PluginWhichPlatform", "get flyme version is:" + str);
            if (!TextUtils.isEmpty(str)) {
                return (!str.contains("OS") && str.compareToIgnoreCase("Flyme 5.1.11.1A") >= 0) || (str.contains("OS") && str.compareToIgnoreCase("Flyme OS 5.1.11.1A") >= 0);
            }
        } catch (Throwable th) {
            e.i("PluginWhichPlatform", " getFlymeVersion wrong error:" + th);
        }
        return false;
    }

    private static boolean f(Context context) {
        if (a.c(context, "cn.jpush.android.service.PluginXiaomiPlatformsReceiver")) {
            try {
                boolean z;
                if (MiPushClient.shouldUseMIUIPush(context)) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    return true;
                }
                e.d("PluginWhichPlatform", "should not Use MIUIPush");
                return false;
            } catch (Throwable th) {
                e.j("PluginWhichPlatform", "Please check *.jar files your project depends on, can't load class - com.xiaomi.mipush.sdk.MiPushClient \nerror:" + th);
                if (!JCoreInterface.getDebugMode()) {
                    return false;
                }
                RuntimeException runtimeException = new RuntimeException("Please check *.jar files your project depends on.", th);
            }
        } else {
            e.c("PluginWhichPlatform", "AndroidManifest.xml missing receiver: cn.jpush.android.service.PluginXiaomiPlatformsReceiver");
            return false;
        }
    }

    private static boolean b() {
        try {
            Class[] clsArr = new Class[]{String.class};
            Object[] objArr = new Object[]{"ro.build.version.emui"};
            Class cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getDeclaredMethod("get", clsArr).invoke(cls, objArr);
            e.e("PluginWhichPlatform", "get EMUI version is:" + str);
            if (!TextUtils.isEmpty(str)) {
                return str.compareToIgnoreCase("EmotionUI_4.1") >= 0;
            }
        } catch (Throwable th) {
            e.i("PluginWhichPlatform", " getEmuiVersion wrong error:" + th);
        }
        return false;
    }

    private static boolean g(Context context) {
        if (VERSION.SDK_INT < 14 || !a.c(context, "cn.jpush.android.service.PluginHuaweiPlatformsReceiver")) {
            e.c("PluginWhichPlatform", "AndroidManifest.xml missing receiver: cn.jpush.android.service.PluginHuaweiPlatformsReceiver");
            return false;
        }
        try {
            if (b()) {
                boolean z;
                if (HuaweiPush.HuaweiPushApi != null) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    return true;
                }
            }
            e.d("PluginWhichPlatform", "emui version must large than 4.0");
            return false;
        } catch (Throwable th) {
            e.j("PluginWhichPlatform", "Please check *.jar files your project depends on, can't load class - com.huawei.hms.support.api.push.HuaweiPush \nerror:" + th);
            if (!JCoreInterface.getDebugMode()) {
                return false;
            }
            RuntimeException runtimeException = new RuntimeException("Please check *.jar files your project depends on.", th);
        }
    }

    public static String a(Context context, String str) {
        String str2 = null;
        e.a("PluginWhichPlatform", "Action - getPluginPlatformConfigInfo:" + str);
        if (context == null) {
            e.h("PluginWhichPlatform", "context was null");
        } else {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null) {
                    Bundle bundle = applicationInfo.metaData;
                    if (bundle != null) {
                        String string = bundle.getString(str);
                        if (TextUtils.isEmpty(string) || string.length() <= 3) {
                            e.h("PluginWhichPlatform", "metadata: " + str + " - not defined in manifest");
                        } else {
                            str2 = string.substring(3, string.length());
                        }
                        e.c("PluginWhichPlatform", str + " value:" + str2);
                    } else {
                        e.d("PluginWhichPlatform", "NO meta data defined in manifest.");
                    }
                } else {
                    e.h("PluginWhichPlatform", "metadata: Can not get metaData from ApplicationInfo");
                }
            } catch (Throwable th) {
                e.h("PluginWhichPlatform", "load plugin sdk config info error:" + th);
            }
        }
        return str2;
    }
}
