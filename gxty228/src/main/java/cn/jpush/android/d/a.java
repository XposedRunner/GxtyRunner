package cn.jpush.android.d;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.MultiSpHelper;
import cn.jpush.android.a.d;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.b;
import cn.jpush.android.ui.PopWinActivity;
import cn.jpush.android.ui.PushActivity;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    public static String a(Context context, String str) {
        String str2 = VERSION.RELEASE + "," + Integer.toString(VERSION.SDK_INT);
        String str3 = Build.MODEL;
        String a = h.a(context, "gsm.version.baseband", "baseband");
        String str4 = Build.DEVICE;
        Object channel = JCoreInterface.getChannel();
        if (TextUtils.isEmpty(channel)) {
            channel = " ";
        }
        String f = f(context);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("androidSdkVersion", str2);
            jSONObject.put("model", str3);
            jSONObject.put("baseband", a);
            jSONObject.put("device", str4);
            jSONObject.put(LogBuilder.KEY_CHANNEL, channel);
            jSONObject.put("network", f);
            jSONObject.put("url", str);
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }

    public static boolean b(Context context, String str) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                        return true;
                    }
                    return false;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        throw new IllegalArgumentException("empty params");
    }

    public static boolean c(Context context, String str) {
        try {
            context.getPackageManager().getReceiverInfo(new ComponentName(context.getPackageName(), str), 128);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean d(Context context, String str) {
        try {
            context.getPackageManager().getServiceInfo(new ComponentName(context.getPackageName(), str), 128);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static boolean a(Context context, Class<?> cls) {
        try {
            if (context.getPackageManager().queryIntentActivities(new Intent(context, cls), 0).isEmpty()) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            e.d("AndroidUtil", "hasActivityResolves error:" + th.getMessage());
            return false;
        }
    }

    public static boolean a(Context context, String str, boolean z) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(str);
            intent.addCategory(context.getPackageName());
            if (packageManager.queryBroadcastReceivers(intent, 0).isEmpty()) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            e.d("AndroidUtil", "hasReceiverIntentFilter error:" + th.getMessage());
            return false;
        }
    }

    private static boolean f(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(str);
            intent.addCategory(context.getPackageName());
            if (packageManager.queryIntentActivities(intent, 0).isEmpty()) {
                return false;
            }
        } catch (Throwable th) {
            e.d("AndroidUtil", "hasActivityIntentFilter error:" + th.getMessage());
        }
        return true;
    }

    public static boolean a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean a() {
        boolean equals = Environment.getExternalStorageState().equals("mounted");
        if (!equals) {
            e.c("AndroidUtil", "SDCard is not mounted");
        }
        return equals;
    }

    public static Intent a(Context context, b bVar, boolean z) {
        Intent intent = new Intent();
        intent.putExtra("isUpdateVersion", false);
        intent.putExtra("body", bVar);
        intent.setAction("cn.jpush.android.ui.PushActivity");
        intent.addCategory(context.getPackageName());
        intent.addFlags(536870912);
        if (!g(context) && VERSION.SDK_INT >= 11) {
            intent.addFlags(32768);
        }
        a(context, intent);
        return intent;
    }

    private static String f(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "Unknown";
            }
            String typeName = activeNetworkInfo.getTypeName();
            Object subtypeName = activeNetworkInfo.getSubtypeName();
            if (typeName == null) {
                return "Unknown";
            }
            if (TextUtils.isEmpty(subtypeName)) {
                return typeName;
            }
            return typeName + "," + subtypeName;
        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown";
        }
    }

    public static void b(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getApplicationContext().getPackageName();
            if (packageName.isEmpty()) {
                e.h("AndroidUtil", "The package with the given name cannot be found!");
                return;
            }
            Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(packageName);
            if (launchIntentForPackage == null) {
                e.h("AndroidUtil", "Can't get launch intent for this package!");
                return;
            }
            int i = AMapEngineUtils.MAX_P20_WIDTH;
            if (VERSION.SDK_INT >= 11) {
                i = 268468224;
            }
            launchIntentForPackage.addFlags(i);
            context.startActivity(launchIntentForPackage);
        } catch (Throwable th) {
            e.d("AndroidUtil", "startMainActivity error:" + th.getMessage());
        }
    }

    private static boolean g(Context context) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName()) && runningAppProcessInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }

    public static boolean c(Context context) {
        try {
            if (MultiSpHelper.getBoolean(context, "notification_enabled", true)) {
                String b = cn.jpush.android.b.b(context);
                if (TextUtils.isEmpty(b)) {
                    e.e("AndroidUtil", "no time limited");
                    return true;
                }
                e.e("AndroidUtil", "push time is ：" + b);
                String[] split = b.split("_");
                String str = split[0];
                String str2 = split[1];
                char[] toCharArray = str.toCharArray();
                String[] split2 = str2.split("\\^");
                Calendar instance = Calendar.getInstance();
                int i = instance.get(7);
                int i2 = instance.get(11);
                for (char valueOf : toCharArray) {
                    if (i == Integer.valueOf(String.valueOf(valueOf)).intValue() + 1) {
                        int intValue = Integer.valueOf(split2[0]).intValue();
                        int intValue2 = Integer.valueOf(split2[1]).intValue();
                        if (i2 >= intValue && i2 <= intValue2) {
                            return true;
                        }
                    }
                }
                e.f("AndroidUtil", "Current time is out of the push time - " + b);
                return false;
            }
            e.f("AndroidUtil", "Notification was disabled by JPushInterface.setPushTime !");
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean d(Context context) {
        Object string = MultiSpHelper.getString(context, "setting_silence_push_time", "");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            int optInt = jSONObject.optInt("startHour", -1);
            int optInt2 = jSONObject.optInt("startMins", -1);
            int optInt3 = jSONObject.optInt("endHour", -1);
            int optInt4 = jSONObject.optInt("endtMins", -1);
            if (optInt < 0 || optInt2 < 0 || optInt3 < 0 || optInt4 < 0 || optInt2 > 59 || optInt4 > 59 || optInt3 > 23 || optInt > 23) {
                return false;
            }
            Calendar instance = Calendar.getInstance();
            int i = instance.get(11);
            int i2 = instance.get(12);
            e.a("AndroidUtil", "nowHour:" + i + ", nowMin:" + i2 + ", startHour:" + optInt + ", startMin:" + optInt2 + ", endHour:" + optInt3 + ", endMin:" + optInt4);
            if (optInt < optInt3) {
                if ((i <= optInt || i >= optInt3) && ((i != optInt || i2 < optInt2) && (i != optInt3 || i2 > optInt4))) {
                    return false;
                }
            } else if (optInt == optInt3) {
                if (optInt2 >= optInt4) {
                    if (i == optInt && i2 > optInt4 && i2 < optInt2) {
                        return false;
                    }
                } else if (i != optInt || i2 < optInt2) {
                    return false;
                } else {
                    if (i2 > optInt4) {
                        return false;
                    }
                }
            } else if (optInt <= optInt3) {
                return false;
            } else {
                if ((i <= optInt || i > 23) && ((i < 0 || i >= optInt3) && (i != optInt || i2 < optInt2))) {
                    if (i != optInt3) {
                        return false;
                    }
                    if (i2 > optInt4) {
                        return false;
                    }
                }
            }
            e.f("AndroidUtil", "Current time is in the range of silence time - " + optInt + ":" + optInt2 + " ~ " + optInt3 + ":" + optInt4);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static void a(Context context, String str, Bundle bundle) {
        if (bundle == null) {
            e.j("AndroidUtil", "Bundle should not be null for sendBroadcast.");
            return;
        }
        try {
            Intent intent = new Intent(str);
            bundle.putString(JPushInterface.EXTRA_APP_KEY, JCoreInterface.getAppKey());
            intent.putExtras(bundle);
            String packageName = context.getPackageName();
            intent.addCategory(packageName);
            intent.setPackage(packageName);
            context.sendBroadcast(intent, String.format(Locale.ENGLISH, "%s.permission.JPUSH_MESSAGE", new Object[]{packageName}));
        } catch (Exception e) {
            e.h("AndroidUtil", "sendBroadcast error:" + e.getMessage() + ",action:" + str);
        }
    }

    private static void a(Context context, Intent intent) {
        if (VERSION.SDK_INT < 21) {
            try {
                for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
                    if (resolveInfo.activityInfo != null) {
                        Object obj = resolveInfo.activityInfo.name;
                        if (!TextUtils.isEmpty(obj)) {
                            intent.setComponent(new ComponentName(context, obj));
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    public static List<String> a(Context context, Intent intent, String str) {
        List<String> arrayList = new ArrayList();
        try {
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
            PackageManager packageManager = context.getPackageManager();
            for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                if (resolveInfo.activityInfo != null) {
                    CharSequence charSequence = resolveInfo.activityInfo.name;
                    if (!TextUtils.isEmpty(charSequence)) {
                        Object obj;
                        if (TextUtils.isEmpty(str) || packageManager.checkPermission(str, resolveInfo.activityInfo.packageName) == 0) {
                            int i = 1;
                        } else {
                            obj = null;
                        }
                        if (obj != null) {
                            arrayList.add(charSequence);
                        }
                    }
                }
            }
        } catch (Throwable th) {
        }
        return arrayList;
    }

    public static void b(Context context, Intent intent, String str) {
        String action = intent.getAction();
        if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(action) || JPushInterface.ACTION_NOTIFICATION_OPENED.equals(action)) {
            List<String> a = a(context, intent, str);
            if (a.isEmpty()) {
                e.h("AndroidUtil", "sendBroadcast failed again: receiver not found, action:" + intent.getAction());
                return;
            }
            for (String action2 : a) {
                try {
                    Intent intent2 = (Intent) intent.clone();
                    intent2.setComponent(new ComponentName(context.getPackageName(), action2));
                    if (TextUtils.isEmpty(str)) {
                        context.sendBroadcast(intent2);
                    } else {
                        context.sendBroadcast(intent2, str);
                    }
                } catch (Exception e) {
                    e.h("AndroidUtil", "sendBroadcast failed again:" + e.getMessage() + ", action:" + intent.getAction());
                }
            }
        }
    }

    public static void a(Context context, b bVar) {
        try {
            Intent intent = new Intent(JPushInterface.ACTION_MESSAGE_RECEIVED);
            e.a("AndroidUtil", "entity.senderId:" + bVar.p + "\nentity.message:" + bVar.j + "\nentity.contentType:" + bVar.k + "\nentity.title:" + bVar.m + "\nentity.extras:" + bVar.n + "\nentity.messageId:" + bVar.c + "\nentity.richPushSavedPath:" + bVar.I + "\nentity.appId:" + bVar.o);
            intent.putExtra(JPushInterface.EXTRA_APP_KEY, bVar.p);
            intent.putExtra(JPushInterface.EXTRA_MESSAGE, bVar.j);
            intent.putExtra(JPushInterface.EXTRA_CONTENT_TYPE, bVar.k);
            intent.putExtra(JPushInterface.EXTRA_TITLE, bVar.m);
            intent.putExtra(JPushInterface.EXTRA_EXTRA, bVar.n);
            intent.putExtra(JPushInterface.EXTRA_MSG_ID, bVar.c);
            if (bVar.a()) {
                intent.putExtra(JPushInterface.EXTRA_RICHPUSH_FILE_PATH, bVar.I);
            }
            intent.addCategory(bVar.o);
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent, String.format(Locale.ENGLISH, "%s.permission.JPUSH_MESSAGE", new Object[]{bVar.o}));
            e.e("AndroidUtil", "Send broadcast to app: " + String.format(Locale.ENGLISH, "%s.permission.JPUSH_MESSAGE", new Object[]{bVar.o}));
            if (bVar.e != (byte) 0) {
                d.a(bVar.c, "", bVar.e, PointerIconCompat.TYPE_ZOOM_IN, context);
            } else {
                d.a(bVar.c, PointerIconCompat.TYPE_ZOOM_IN, null, context);
            }
        } catch (Throwable th) {
            e.i("AndroidUtil", "sendBroadcastToApp error:" + th.getMessage());
        }
    }

    public static boolean e(Context context) {
        e.d("AndroidUtil", "action:checkValidManifest");
        if (a(context, PushActivity.class)) {
            if (!a(context, PopWinActivity.class)) {
                e.h("AndroidUtil", "AndroidManifest.xml missing activity: " + PopWinActivity.class.getCanonicalName());
                e.h("AndroidUtil", "You will unable to use pop-window rich push type.");
            }
            if (f(context, "cn.jpush.android.ui.PushActivity")) {
                return true;
            }
            e.j("AndroidUtil", "AndroidManifest.xml missing required intent filter for PushActivity: cn.jpush.android.ui.PushActivity");
            return false;
        }
        e.j("AndroidUtil", "AndroidManifest.xml missing required activity: " + PushActivity.class.getCanonicalName());
        return false;
    }

    public static void a(WebView webView) {
        try {
            if (VERSION.SDK_INT >= 11) {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                webView.removeJavascriptInterface("accessibility");
                webView.removeJavascriptInterface("accessibilityTraversal");
            }
            if (VERSION.SDK_INT >= 21) {
                webView.getSettings().setMixedContentMode(0);
            }
        } catch (Throwable th) {
            e.i("AndroidUtil", "fixSecure failed, error:" + th);
        }
    }

    public static void a(WebSettings webSettings) {
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        if (VERSION.SDK_INT >= 11) {
            webSettings.setDisplayZoomControls(false);
        }
        webSettings.setCacheMode(2);
        webSettings.setSaveFormData(false);
        webSettings.setSavePassword(false);
    }

    public static String a(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            if (digest == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer(digest.length * 2);
            for (byte b : digest) {
                stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15)).append("0123456789ABCDEF".charAt(b & 15));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static Object e(Context context, String str) {
        Object obj = null;
        if (!(context == null || TextUtils.isEmpty(str))) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                    obj = applicationInfo.metaData.get(str);
                }
            } catch (Throwable th) {
                e.h("AndroidUtil", "#get meta data error:" + th);
            }
        }
        return obj;
    }
}
