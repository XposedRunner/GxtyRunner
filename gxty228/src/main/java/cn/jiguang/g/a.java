package cn.jiguang.g;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.d.a.c;
import cn.jiguang.d.d.e;
import cn.jiguang.d.d.o;
import cn.jiguang.d.g.b;
import cn.jiguang.e.d;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.service.AlarmReceiver;
import cn.jpush.android.service.DaemonService;
import cn.jpush.android.service.DataProvider;
import cn.jpush.android.service.PushReceiver;
import cn.jpush.android.service.PushService;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.lzy.okgo.cache.CacheEntity;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.security.auth.x500.X500Principal;
import org.json.JSONArray;
import org.json.JSONObject;

public final class a {
    public static int a = 1;
    private static List<String> b;
    private static final ArrayList<String> c = new ArrayList();
    private static final ArrayList<String> d = new ArrayList();
    private static final ArrayList<String> e;
    private static PushReceiver f;

    static {
        List arrayList = new ArrayList();
        b = arrayList;
        arrayList.add("358673013795895");
        b.add("004999010640000");
        b.add("00000000000000");
        b.add("000000000000000");
        c.add("android.permission.INTERNET");
        c.add("android.permission.WAKE_LOCK");
        c.add("android.permission.ACCESS_NETWORK_STATE");
        d.add("android.permission.VIBRATE");
        d.add("android.permission.CHANGE_WIFI_STATE");
        ArrayList arrayList2 = new ArrayList();
        e = arrayList2;
        arrayList2.add("android.permission.ACCESS_FINE_LOCATION");
        e.add("android.permission.ACCESS_COARSE_LOCATION");
        e.add("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS");
        e.add("android.permission.ACCESS_WIFI_STATE");
    }

    private static boolean A(Context context) {
        d.e("AndroidUtil", "Do not have PushReceiver, Register it in code");
        try {
            if (f == null) {
                f = new PushReceiver();
                context.registerReceiver(f, new IntentFilter("android.intent.action.USER_PRESENT"));
                context.registerReceiver(f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
                intentFilter.addDataScheme("package");
                IntentFilter intentFilter2 = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
                intentFilter2.addDataScheme("package");
                context.registerReceiver(f, intentFilter);
                context.registerReceiver(f, intentFilter2);
                intentFilter = new IntentFilter(JPushInterface.ACTION_NOTIFICATION_RECEIVED_PROXY);
                intentFilter.setPriority(1000);
                intentFilter.addCategory(context.getPackageName());
                context.registerReceiver(f, intentFilter);
                return true;
            }
            d.d("AndroidUtil", "has register in code");
            return true;
        } catch (Exception e) {
            d.h("AndroidUtil", "Register PushReceiver in code  failed:" + e.getMessage());
            return false;
        }
    }

    public static int a(byte b) {
        return b & 255;
    }

    public static ComponentInfo a(Context context, String str, Class<?> cls) {
        if (context == null || TextUtils.isEmpty(str) || cls == null) {
            d.g("AndroidUtil", "Action - hasComponent, invalide param, context:" + context + ",packageName:" + str + ",cls:" + cls);
            return null;
        }
        try {
            ComponentInfo[] componentInfoArr;
            int i = Service.class.isAssignableFrom(cls) ? 4 : BroadcastReceiver.class.isAssignableFrom(cls) ? 2 : Activity.class.isAssignableFrom(cls) ? 1 : ContentProvider.class.isAssignableFrom(cls) ? 8 : 0;
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, i);
            switch (i) {
                case 1:
                    componentInfoArr = packageInfo.activities;
                    break;
                case 2:
                    componentInfoArr = packageInfo.receivers;
                    break;
                case 4:
                    componentInfoArr = packageInfo.services;
                    break;
                case 8:
                    componentInfoArr = packageInfo.providers;
                    break;
                default:
                    componentInfoArr = null;
                    break;
            }
            if (componentInfoArr == null) {
                return null;
            }
            String name = cls.getName();
            for (ComponentInfo componentInfo : componentInfoArr) {
                if (name.equals(componentInfo.name)) {
                    return componentInfo;
                }
            }
            return null;
        } catch (Throwable th) {
            d.h("AndroidUtil", "hasComponent error:" + th.getMessage());
            return null;
        }
    }

    public static String a() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            if (new File("/proc/cpuinfo").exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/proc/cpuinfo")));
                Object obj = null;
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (readLine.contains("Processor")) {
                        int indexOf = readLine.indexOf(":");
                        if (indexOf >= 0 && indexOf < readLine.length() - 1) {
                            obj = readLine.substring(indexOf + 1).trim();
                        }
                        if (!(obj == null || stringBuffer.toString().contains(obj))) {
                            stringBuffer.append(obj);
                        }
                    }
                }
                bufferedReader.close();
            }
        } catch (Throwable th) {
        }
        return stringBuffer.toString();
    }

    public static String a(Context context) {
        if (context == null || context.getResources() == null) {
            return "0*0";
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (displayMetrics == null) {
            return "0*0";
        }
        int i = displayMetrics.widthPixels;
        return i + "*" + displayMetrics.heightPixels;
    }

    public static String a(String str) {
        int i = 0;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            char[] toCharArray = str.toCharArray();
            byte[] bArr = new byte[toCharArray.length];
            for (int i2 = 0; i2 < toCharArray.length; i2++) {
                bArr[i2] = (byte) toCharArray[i2];
            }
            byte[] digest = instance.digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            while (i < digest.length) {
                int i3 = digest[i] & 255;
                if (i3 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i3));
                i++;
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            d.c("AndroidUtil", "Get MD5 error");
            return "";
        }
    }

    public static String a(byte[] bArr) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            d.c("AndroidUtil", "Get MD5 error");
            return "";
        }
    }

    public static ArrayList<String> a(String[] strArr) {
        ArrayList<String> arrayList = new ArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(strArr).getInputStream()));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        d.c("AndroidUtil", "--> Line received: " + readLine);
                        arrayList.add(readLine);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e2) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return null;
                } catch (Throwable th) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    throw th;
                }
            }
            bufferedReader.close();
            d.c("AndroidUtil", "--> Full response was: " + arrayList);
            return arrayList;
        } catch (Exception e5) {
            return null;
        }
    }

    private static List<String> a(Context context, Intent intent, String str) {
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

    public static List<String> a(Context context, String[] strArr) {
        List arrayList = new ArrayList();
        for (int i = 0; i < 4; i++) {
            String str = strArr[i];
            if (!a(context, str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static JSONObject a(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("data", str2);
            o.b(null, jSONObject, str);
            return jSONObject;
        } catch (Exception e) {
            d.i("AndroidUtil", e.getMessage());
            return null;
        }
    }

    public static JSONObject a(String str, JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("data", jSONArray);
            o.b(null, jSONObject, str);
            return jSONObject;
        } catch (Exception e) {
            d.i("AndroidUtil", e.getMessage());
            return null;
        }
    }

    public static void a(Context context, String str, int i) {
        if (!v(context)) {
            d.c("AndroidUtil", "not debuggable");
        } else if (a(context, PushReceiver.class)) {
            int i2;
            Notification notification;
            d.c("AndroidUtil", "action:showPermanentNotification");
            Intent intent = new Intent(context, PushReceiver.class);
            intent.setAction("cn.jpush.android.intent.NOTIFICATION_OPENED_PROXY");
            intent.addCategory(context.getPackageName());
            intent.putExtra("debug_notification", true);
            intent.putExtra("toastText", str);
            intent.putExtra("type", -1);
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, AMapEngineUtils.HALF_MAX_P20_WIDTH);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            try {
                i2 = context.getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), 0).icon;
            } catch (Throwable th) {
                d.f("AndroidUtil", "failed to get application info and icon.", th);
                i2 = 17301586;
            }
            CharSequence charSequence = "Jiguang提示：包名和AppKey不匹配";
            CharSequence charSequence2 = "请到 Portal 上获取您的包名和AppKey并更新AndroidManifest相应字段";
            long currentTimeMillis = System.currentTimeMillis();
            if (VERSION.SDK_INT >= 11) {
                notification = new Builder(context.getApplicationContext()).setContentTitle(charSequence).setContentText(charSequence2).setContentIntent(broadcast).setSmallIcon(i2).setTicker(str).setWhen(currentTimeMillis).getNotification();
                notification.flags = 34;
            } else {
                Notification notification2 = new Notification(i2, str, currentTimeMillis);
                notification2.flags = 34;
                try {
                    Class.forName("android.app.Notification").getDeclaredMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification2, new Object[]{context, charSequence, charSequence2, broadcast});
                    notification = notification2;
                } catch (Exception e) {
                    notification = notification2;
                }
            }
            if (notification != null) {
                notificationManager.notify(str.hashCode(), notification);
            }
        } else {
            new Handler(Looper.getMainLooper()).post(new b(context, str));
        }
    }

    public static void a(Context context, String str, Bundle bundle) {
        if (bundle == null) {
            d.j("AndroidUtil", "Bundle should not be null for sendBroadcast.");
            return;
        }
        Intent intent = new Intent(str);
        try {
            bundle.putString(JPushInterface.EXTRA_APP_KEY, cn.jiguang.d.a.d.i(context));
            intent.putExtras(bundle);
            String packageName = context.getPackageName();
            intent.addCategory(packageName);
            intent.setPackage(packageName);
            context.sendBroadcast(intent);
        } catch (Throwable th) {
            d.h("AndroidUtil", "sendBroadcast error:" + th.getMessage() + ",action:" + str);
            b(context, intent, null);
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        if (str2 != null) {
            bundle.putString(str2, str3);
        }
        a(context, str, bundle);
    }

    private static boolean a(Context context, Class<?> cls) {
        try {
            boolean z = !context.getPackageManager().queryBroadcastReceivers(new Intent(context, cls), 0).isEmpty();
            if (z) {
                return z;
            }
            try {
                return a(context, context.getPackageName(), (Class) cls) != null;
            } catch (Throwable th) {
                return z;
            }
        } catch (Throwable th2) {
            return false;
        }
    }

    public static boolean a(Context context, String str) {
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

    private static boolean a(Context context, String str, String str2) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(str2);
            intent.setPackage(context.getPackageName());
            for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(intent, 0)) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (activityInfo != null && activityInfo.name.equals(str)) {
                    return true;
                }
            }
        } catch (Throwable th) {
            d.h("AndroidUtil", "hasReceiverIntentFilterPackage error:" + th.getMessage());
        }
        return false;
    }

    private static boolean a(Context context, String str, boolean z) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(str);
            intent.addCategory(context.getPackageName());
            return !packageManager.queryIntentServices(intent, 0).isEmpty();
        } catch (Throwable th) {
            d.h("AndroidUtil", "hasServiceIntentFilter error:" + th.getMessage());
            return false;
        }
    }

    private static boolean a(String str, String str2, String str3, String str4) {
        if (k.a(str3) || k.a(str4)) {
            return str.equals(str2);
        }
        if (str.equals(str2) && str3.equalsIgnoreCase(str4)) {
            d.e("AndroidUtil", "Same androidId and macAddress!");
            return true;
        }
        d.e("AndroidUtil", "NewDevice:AndroidId or Mac had changed!");
        return false;
    }

    public static String b() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            d.g("AndroidUtil", "getPhoneIp:");
            e.printStackTrace();
        }
        return "";
    }

    public static String b(Context context, String str) {
        String s = s(context);
        if (!k.e(s)) {
            s = t(context);
        }
        return !k.e(s) ? str : s;
    }

    public static String b(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("utf-8"));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            d.c("AndroidUtil", "Get MD5 error");
            return "";
        }
    }

    private static void b(Context context, Intent intent, String str) {
        String action = intent.getAction();
        if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(action)) {
            List<String> a = a(context, intent, null);
            if (a.isEmpty()) {
                d.h("AndroidUtil", "sendBroadcast failed again: receiver not found, action:" + action);
                return;
            }
            for (String componentName : a) {
                try {
                    Intent intent2 = (Intent) intent.clone();
                    intent2.setComponent(new ComponentName(context.getPackageName(), componentName));
                    if (TextUtils.isEmpty(null)) {
                        context.sendBroadcast(intent2);
                    } else {
                        context.sendBroadcast(intent2, null);
                    }
                } catch (Exception e) {
                    d.h("AndroidUtil", "sendBroadcast failed again:" + e.getMessage() + ", action:" + action);
                }
            }
        }
    }

    public static boolean b(Context context) {
        try {
            for (ComponentInfo componentInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 8).providers) {
                if (componentInfo.name.equals(DataProvider.class.getName())) {
                    break;
                }
            }
            ComponentInfo componentInfo2 = null;
            if (componentInfo2 == null) {
                return false;
            }
            d.a("AndroidUtil", "provider processName:" + componentInfo2.processName);
            return true;
        } catch (Throwable th) {
            d.g("AndroidUtil", "checkSpProvider error:" + th);
            return false;
        }
    }

    private static boolean b(Context context, Class<?> cls) {
        try {
            boolean z = !context.getPackageManager().queryIntentServices(new Intent(context, cls), 0).isEmpty();
            if (z) {
                return z;
            }
            try {
                return a(context, context.getPackageName(), (Class) cls) != null;
            } catch (Throwable th) {
                return z;
            }
        } catch (Throwable th2) {
            return false;
        }
    }

    public static int c(String str) {
        String[] split = str.split("\\.");
        return Integer.parseInt(split[2]) + ((Integer.parseInt(split[0]) << 16) + (Integer.parseInt(split[1]) << 8));
    }

    public static String c(Context context, String str) {
        try {
            if (a(context, "android.permission.READ_PHONE_STATE")) {
                str = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private static boolean c() {
        boolean equals = Environment.getExternalStorageState().equals("mounted");
        if (!equals) {
            d.c("AndroidUtil", "SDCard is not mounted");
        }
        return equals;
    }

    public static boolean c(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String d() {
        String str = null;
        try {
            str = Environment.getExternalStorageDirectory().getPath();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception e2) {
        }
        return !k.a(str) ? str + "/data/" : str;
    }

    public static String d(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "Unknown";
            }
            String typeName = activeNetworkInfo.getTypeName();
            String subtypeName = activeNetworkInfo.getSubtypeName();
            return typeName == null ? "Unknown" : !k.a(subtypeName) ? typeName + "," + subtypeName : typeName;
        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown";
        }
    }

    public static String d(Context context, String str) {
        String deviceId;
        try {
            deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            d.i("AndroidUtil", e.getMessage());
            deviceId = null;
        }
        return k.d(deviceId) ? deviceId : str;
    }

    private static boolean d(String str) {
        if (!k.d(str) || str.length() < 10) {
            return false;
        }
        int i = 0;
        while (i < b.size()) {
            if (str.equals(b.get(i)) || str.startsWith((String) b.get(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static int e(Context context) {
        Intent intent = null;
        if (context == null) {
            return -1;
        }
        Intent registerReceiver;
        try {
            registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        } catch (SecurityException e) {
            e.printStackTrace();
            registerReceiver = intent;
        } catch (Exception e2) {
            e2.printStackTrace();
            registerReceiver = intent;
        }
        if (registerReceiver == null) {
            return -1;
        }
        int intExtra = registerReceiver.getIntExtra("status", -1);
        Object obj = (intExtra == 2 || intExtra == 5) ? 1 : null;
        return obj != null ? registerReceiver.getIntExtra("plugged", -1) : -1;
    }

    private static String e() {
        String d = d();
        return d == null ? null : d + ".push_deviceid";
    }

    public static String e(Context context, String str) {
        try {
            if (a(context, "android.permission.READ_PHONE_STATE")) {
                str = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private static String e(String str) {
        FileOutputStream fileOutputStream;
        Throwable e;
        Throwable th;
        String d = d();
        if (k.a(d)) {
            d.i("AndroidUtil", "get sdcard data path fial");
            return null;
        }
        File file = new File(d);
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (Throwable e2) {
                d.e("AndroidUtil", "mkdir in sdcard error", e2);
            }
        }
        if (k.a(e())) {
            d.j("AndroidUtil", "get device id  sd card file path fail");
            return null;
        }
        File file2 = new File(d + ".push_deviceid");
        if (file2.exists()) {
            try {
                file2.delete();
            } catch (SecurityException e3) {
                return null;
            }
        }
        try {
            file2.createNewFile();
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    fileOutputStream.write(str.getBytes());
                    fileOutputStream.flush();
                    d.e("AndroidUtil", "Saved deviceid into file");
                    if (fileOutputStream == null) {
                        return str;
                    }
                    try {
                        fileOutputStream.close();
                        return str;
                    } catch (IOException e4) {
                        return str;
                    }
                } catch (IOException e5) {
                    e = e5;
                    try {
                        d.e("AndroidUtil", "write deviceid error", e);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e6) {
                            }
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e7) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e8) {
                e = e8;
                fileOutputStream = null;
                d.e("AndroidUtil", "write deviceid error", e);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return null;
            } catch (Throwable e9) {
                fileOutputStream = null;
                th = e9;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable e92) {
            d.e("AndroidUtil", "Create file in sdcard error", e92);
            return null;
        }
    }

    private static int f(String str) {
        if (k.a(str)) {
            d.g("AndroidUtil", "The imei is empty!");
            return 0;
        } else if (Pattern.matches("[0]*", str)) {
            d.e("AndroidUtil", "Get imei is all 0 !");
            return 0;
        } else if (Pattern.matches("[0-9]{15}", str)) {
            d.e("AndroidUtil", "Get imei ok !");
            return 1;
        } else if (!Pattern.matches("[a-f0-9A-F]{14}", str)) {
            return 0;
        } else {
            d.e("AndroidUtil", "Get meid as a imei !");
            return 2;
        }
    }

    private static String f() {
        String e = e();
        if (k.a(e)) {
            d.j("AndroidUtil", "get device id  sd card file path fail");
            return null;
        }
        File file = new File(e);
        if (file.exists()) {
            try {
                ArrayList a = f.a(new FileInputStream(file));
                if (a.size() > 0) {
                    e = (String) a.get(0);
                    d.e("AndroidUtil", "Got sdcard file saved deviceId - " + e);
                    return e;
                }
            } catch (Exception e2) {
                return null;
            }
        }
        return null;
    }

    public static String f(Context context) {
        String c = cn.jiguang.d.a.a.c(context);
        if (d(c)) {
            return c;
        }
        c = w(context);
        cn.jiguang.d.a.a.b(context, c);
        return c;
    }

    public static void f(Context context, String str) {
        if (!k.a(str)) {
            n(context, str);
            l(context, str);
            cn.jiguang.d.a.d.a(context, str);
        }
    }

    public static ApplicationInfo g(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationInfo(str, 128);
        } catch (Throwable th) {
            return null;
        }
    }

    public static String g(Context context) {
        String str = "";
        try {
            str = Secure.getString(context.getContentResolver(), "android_id");
        } catch (SecurityException e) {
        } catch (Exception e2) {
        }
        return k.d(str) ? str : "";
    }

    public static String h(Context context) {
        String str = null;
        String c = cn.jiguang.d.a.d.c(context);
        if (k.d(c)) {
            a = c.d - 1;
            return c;
        }
        c = m(context, c);
        if (k.d(c)) {
            a = c.b - 1;
            l(context, c);
            cn.jiguang.d.a.d.a(context, c);
            return c;
        }
        if (!c()) {
            d.i("AndroidUtil", "Can not use external storege");
        } else if (a(context, "android.permission.WRITE_EXTERNAL_STORAGE") && (VERSION.SDK_INT < 23 || (a(context, "android.permission.WRITE_EXTERNAL_STORAGE") && a(context, "android.permission.READ_EXTERNAL_STORAGE")))) {
            str = f();
        }
        if (k.d(str)) {
            a = c.c - 1;
            n(context, str);
            cn.jiguang.d.a.d.a(context, str);
            return str;
        }
        str = VERSION.SDK_INT < 23 ? d(context, str) : "";
        String g = g(context);
        String b = b(context, "");
        c = UUID.randomUUID().toString();
        str = a(str + g + b + c);
        if (k.a(str)) {
            str = c;
        }
        cn.jiguang.d.a.d.a(context, str);
        a = c.a - 1;
        n(context, str);
        l(context, str);
        return str;
    }

    public static String h(Context context, String str) {
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context.getPackageName(), str), 128);
            d.a("AndroidUtil", "process name:" + serviceInfo.processName);
            return serviceInfo.processName;
        } catch (NameNotFoundException e) {
        } catch (NullPointerException e2) {
            d.a("AndroidUtil", "can not find " + str);
        } catch (Throwable th) {
        }
        return "";
    }

    public static boolean i(Context context) {
        String str = context.getApplicationInfo().sourceDir;
        if (k.a(str)) {
            d.i("AndroidUtil", "Unexpected: cannot get pk installed path");
            return false;
        }
        d.c("AndroidUtil", "Current pk installed path: " + str);
        if (str.startsWith("/system/app/")) {
            return true;
        }
        if (str.startsWith("/data/app/")) {
            return false;
        }
        d.e("AndroidUtil", "NOTE: the pk does not installed in system/data. ");
        return false;
    }

    public static boolean i(Context context, String str) {
        if (k.a(str)) {
            return false;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (Throwable th) {
            return false;
        }
    }

    public static void j(Context context) {
        d.c("AndroidUtil", "Reset heartbeat alarm.");
        long i = (long) (cn.jiguang.d.a.a.i() * 1000);
        long currentTimeMillis = System.currentTimeMillis() + i;
        d.e("AndroidUtil", "Heartbeat interval = " + i + "ms.");
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiver.class), 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        try {
            if (VERSION.SDK_INT >= 19) {
                alarmManager.setWindow(0, currentTimeMillis, 0, broadcast);
            } else {
                alarmManager.setInexactRepeating(0, currentTimeMillis, i, broadcast);
            }
        } catch (Exception e) {
            d.h("AndroidUtil", "can't trigger alarm cause by exception:" + e.getMessage());
        }
    }

    private static boolean j(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("empty params");
        }
        try {
            context.getPackageManager().getPermissionInfo(str, 128);
            return true;
        } catch (Throwable th) {
            d.h("AndroidUtil", "hasPermissionDefined error:" + th.getMessage());
            return false;
        }
    }

    public static void k(Context context) {
        try {
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiver.class), 0));
        } catch (Exception e) {
            d.g("AndroidUtil", "Cancel heartbeat alarm failed.");
        }
    }

    private static boolean k(Context context, String str) {
        try {
            context.getPackageManager().getReceiverInfo(new ComponentName(context.getPackageName(), str), 128);
            return true;
        } catch (Throwable th) {
            d.h("AndroidUtil", "hasReceiver error:" + th.getMessage());
            return false;
        }
    }

    private static String l(Context context, String str) {
        return (c() && a(context, "android.permission.WRITE_EXTERNAL_STORAGE")) ? VERSION.SDK_INT < 23 ? e(str) : (a(context, "android.permission.WRITE_EXTERNAL_STORAGE") && a(context, "android.permission.READ_EXTERNAL_STORAGE")) ? e(str) : null : null;
    }

    public static void l(Context context) {
        boolean z = true;
        c w = cn.jiguang.d.a.a.w();
        if (w.d()) {
            d.e("AndroidUtil", "Update from a version earlier than 180 ,or fist time install JPushSDK.");
            return;
        }
        String a = w.a();
        String b = w.b();
        String c = w.c();
        String d = d(context, "");
        String g = g(context);
        if (k.a(g)) {
            g = " ";
        }
        String b2 = b(context, "");
        if (k.a(b2)) {
            b2 = " ";
        }
        int f = f(a);
        int f2 = f(d);
        if (f == 0 || f2 == 0) {
            z = a(g, b, b2, c);
        } else if (!((1 == f && 2 == f2) || (2 == f && 1 == f2))) {
            if (f != f2) {
                z = false;
            } else if (d.equals(a)) {
                boolean z2;
                if (d.equals(a) && g.equals(b)) {
                    d.e("AndroidUtil", "Same IMEI and androidId!");
                    z2 = true;
                } else {
                    d.e("AndroidUtil", "NewDevice:IMEI or AndroidId had changed!");
                    z2 = false;
                }
                z = z2;
            } else {
                z = a(g, b, b2, c);
            }
        }
        if (!z) {
            m(context);
        }
    }

    private static String m(Context context, String str) {
        if (a(context, "android.permission.WRITE_SETTINGS")) {
            try {
                str = System.getString(context.getContentResolver(), "devcie_id_generated");
            } catch (Exception e) {
                d.i("AndroidUtil", "Can not read from settings");
            }
        }
        return str;
    }

    public static void m(Context context) {
        d.c("AndroidUtil", "Clear registered data and device id ");
        cn.jiguang.d.a.d.g(context);
        n(context, "");
        l(context, "");
        b.d().b(context);
    }

    private static String n(Context context, String str) {
        if (a(context, "android.permission.WRITE_SETTINGS")) {
            try {
                if (System.putString(context.getContentResolver(), "devcie_id_generated", str)) {
                    return str;
                }
            } catch (Exception e) {
                d.i("AndroidUtil", "Can not write settings");
            }
        }
        return null;
    }

    public static boolean n(Context context) {
        CharSequence a = l.a(context, "ro.product.brand");
        d.c("AndroidUtil", "brand = " + a);
        CharSequence a2 = l.a(context, "ro.miui.ui.version.name");
        if (!(TextUtils.isEmpty(a) || !"Xiaomi".equals(a) || TextUtils.isEmpty(a2))) {
            Object a3 = l.a(context, "ro.build.version.incremental");
            if (!TextUtils.isEmpty(a3) && a3.startsWith("V7.1")) {
                d.g("AndroidUtil", "7.1 will not get wifi list");
                return false;
            }
        }
        return true;
    }

    public static double o(Context context) {
        double pow;
        double pow2;
        Point point = new Point();
        if (context instanceof Activity) {
            Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
            if (VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealSize(point);
            } else if (VERSION.SDK_INT >= 13) {
                defaultDisplay.getSize(point);
            } else {
                point.x = defaultDisplay.getWidth();
                point.y = defaultDisplay.getHeight();
            }
            d.c("AndroidUtil", "point.x:" + point.x + "point.y" + point.y);
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        d.c("AndroidUtil", "dm.xdpi : " + displayMetrics.xdpi + " dm.ydpi:" + displayMetrics.ydpi);
        if (context instanceof Activity) {
            pow = Math.pow((double) (((float) point.x) / displayMetrics.xdpi), 2.0d);
            pow2 = Math.pow((double) (((float) point.y) / displayMetrics.ydpi), 2.0d);
        } else {
            d.c("AndroidUtil", "dm.widthPixels : " + displayMetrics.widthPixels + " dm.heightPixels:" + displayMetrics.heightPixels);
            pow = Math.pow((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), 2.0d);
            pow2 = Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d);
        }
        pow2 = Math.sqrt(pow2 + pow);
        d.c("AndroidUtil", "Screen inches : " + pow2);
        return pow2;
    }

    private static boolean o(Context context, String str) {
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context.getPackageName(), str), 128);
            d.a("AndroidUtil", "process name:" + serviceInfo.processName);
            if (serviceInfo.processName.contains(context.getPackageName() + ":")) {
                return true;
            }
        } catch (NameNotFoundException e) {
        } catch (NullPointerException e2) {
            d.a("AndroidUtil", "can not find " + str);
        }
        return false;
    }

    public static boolean p(Context context) {
        return b(context, PushService.class);
    }

    public static boolean q(Context context) {
        String str;
        d.d("AndroidUtil", "action:checkValidManifest");
        if (e.a().f()) {
            boolean z;
            if (b(context, PushService.class)) {
                z = true;
            } else {
                d.j("AndroidUtil", "AndroidManifest.xml missing required service: " + PushService.class.getCanonicalName());
                z = false;
            }
            if (!z) {
                return false;
            }
            if (b(context)) {
                if (!b(context, DaemonService.class)) {
                    d.h("AndroidUtil", "AndroidManifest.xml missing required service: " + DaemonService.class.getCanonicalName());
                    JCoreInterface.setCanLaunchedStoppedService(false);
                } else if (a(context, JCoreInterface.getDaemonAction(), true)) {
                    JCoreInterface.setCanLaunchedStoppedService(true);
                } else {
                    d.h("AndroidUtil", "AndroidManifest.xml missing intent filter for DaemonService: " + JCoreInterface.getDaemonAction());
                    JCoreInterface.setCanLaunchedStoppedService(false);
                }
                if (o(context, PushService.class.getCanonicalName())) {
                    d.a("AndroidUtil", "PushService in other process");
                    cn.jiguang.d.a.j = true;
                } else {
                    d.a("AndroidUtil", "PushService in main process");
                    cn.jiguang.d.a.j = false;
                }
                if (!cn.jiguang.d.a.d.j(context)) {
                    cn.jiguang.d.b.a.a(context, true);
                    if (a(context, AlarmReceiver.class)) {
                        if (!a(context, PushReceiver.class)) {
                            d.j("AndroidUtil", "AndroidManifest.xml missing required receiver: " + PushReceiver.class.getCanonicalName());
                            if (!A(context)) {
                                return false;
                            }
                        }
                        if (a(context, PushReceiver.class.getCanonicalName(), "android.intent.action.BOOT_COMPLETED")) {
                            d.h("AndroidUtil", "PushReceiver should not have intent filter -- android.intent.action.BOOT_COMPLETED, Please remove the intent filter in AndroidManifest.xml");
                        }
                    } else {
                        d.j("AndroidUtil", "AndroidManifest.xml missing required receiver: " + AlarmReceiver.class.getCanonicalName());
                        return false;
                    }
                }
                str = context.getPackageName() + ".permission.JPUSH_MESSAGE";
                if (j(context, str)) {
                    c.add(str);
                } else {
                    d.j("AndroidUtil", "The permission should be defined - " + str);
                    return false;
                }
            }
            d.j("AndroidUtil", "AndroidManifest.xml missing required ContentProvider: " + DataProvider.class.getCanonicalName());
            return false;
        }
        Iterator it = c.iterator();
        while (it.hasNext()) {
            str = (String) it.next();
            if (!a(context.getApplicationContext(), str)) {
                d.j("AndroidUtil", "The permissoin is required - " + str);
                return false;
            }
        }
        if (VERSION.SDK_INT < 23) {
            if (!a(context.getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE")) {
                d.j("AndroidUtil", "The permissoin is required - android.permission.WRITE_EXTERNAL_STORAGE");
                return false;
            } else if (!a(context.getApplicationContext(), "android.permission.WRITE_SETTINGS")) {
                d.j("AndroidUtil", "The permissoin is required - android.permission.WRITE_SETTINGS");
                return false;
            }
        }
        Iterator it2 = d.iterator();
        while (it2.hasNext()) {
            str = (String) it2.next();
            if (!a(context.getApplicationContext(), str)) {
                d.g("AndroidUtil", "We recommend you add the permission - " + str);
            }
        }
        it2 = e.iterator();
        while (it2.hasNext()) {
            str = (String) it2.next();
            if (!a(context.getApplicationContext(), str)) {
                d.g("AndroidUtil", "We recommend you add the permission - " + str + ", otherwise you can not locate the devices.");
            }
        }
        return true;
    }

    public static void r(Context context) {
        if (f != null && !k(context, PushReceiver.class.getCanonicalName())) {
            try {
                context.unregisterReceiver(f);
                f = null;
            } catch (Exception e) {
                d.i("AndroidUtil", e.getMessage());
            }
        }
    }

    private static String s(Context context) {
        Throwable e;
        String str = "";
        if (VERSION.SDK_INT >= 23 || !a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return str;
        }
        String macAddress;
        try {
            macAddress = ((WifiManager) context.getApplicationContext().getSystemService(IXAdSystemUtils.NT_WIFI)).getConnectionInfo().getMacAddress();
            try {
                d.c("AndroidUtil", "mac address from WifiManager:" + macAddress);
                return macAddress;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            macAddress = str;
            e = th;
            d.c("AndroidUtil", "get mac from wifiManager failed ", e);
            return macAddress;
        }
    }

    private static String t(Context context) {
        String str;
        boolean equals;
        String str2 = "";
        boolean isWifiEnabled = a(context, "android.permission.ACCESS_WIFI_STATE") ? ((WifiManager) context.getApplicationContext().getSystemService(IXAdSystemUtils.NT_WIFI)).isWifiEnabled() : false;
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                if ("wlan0".equalsIgnoreCase(networkInterface.getName())) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (!(hardwareAddress == null || hardwareAddress.length == 0)) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (byte b : hardwareAddress) {
                            stringBuilder.append(String.format(Locale.ENGLISH, "%02x:", new Object[]{Byte.valueOf(b)}));
                        }
                        if (stringBuilder.length() > 0) {
                            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                        }
                        str2 = stringBuilder.toString();
                        d.c("AndroidUtil", "mac address from NetworkInterface:" + str2);
                        str = str2;
                        if (!isWifiEnabled) {
                            return str;
                        }
                        Object r = cn.jiguang.d.a.a.r();
                        equals = (TextUtils.isEmpty(r) || TextUtils.isEmpty(str)) ? false : r.equals(a(str.toLowerCase() + Build.MODEL));
                        if (equals) {
                            d.c("AndroidUtil", "mac address is dropped, which is " + str);
                            return "";
                        }
                        d.c("AndroidUtil", "mac address is matched, which is " + str);
                        return str;
                    }
                }
            }
            str = str2;
        } catch (Throwable e) {
            d.c("AndroidUtil", "get mac from NetworkInterface failed", e);
            str = str2;
        }
        if (!isWifiEnabled) {
            return str;
        }
        Object r2 = cn.jiguang.d.a.a.r();
        if (!TextUtils.isEmpty(r2)) {
        }
        if (equals) {
            d.c("AndroidUtil", "mac address is dropped, which is " + str);
            return "";
        }
        d.c("AndroidUtil", "mac address is matched, which is " + str);
        return str;
    }

    private static String u(Context context) {
        String str = null;
        try {
            String b = b(context, "");
            if (!(b == null || b.equals(""))) {
                d.e("AndroidUtil", "MAC addr info---- " + b);
                str = a(b.toLowerCase() + Build.MODEL);
            }
        } catch (Throwable e) {
            d.e("AndroidUtil", "", e);
        }
        return str;
    }

    private static boolean v(Context context) {
        boolean z = false;
        try {
            X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
            String[] strArr = new String[]{"CN=Android Debug", "O=Android", "C=US"};
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            int length = signatureArr.length;
            int i = 0;
            while (i < length) {
                X509Certificate x509Certificate = (X509Certificate) instance.generateCertificate(new ByteArrayInputStream(signatureArr[i].toByteArray()));
                z = x509Certificate.getSubjectX500Principal().equals(x500Principal);
                try {
                    d.c("AndroidUtil", "debuggable :" + z);
                    if (z) {
                        return z;
                    }
                    String name;
                    try {
                        name = x509Certificate.getSubjectX500Principal().getName();
                    } catch (Exception e) {
                        name = null;
                    }
                    d.c("AndroidUtil", "certName:" + name);
                    if (name != null && name.contains(strArr[0]) && name.contains(strArr[1]) && name.contains(strArr[2])) {
                        return true;
                    }
                    i++;
                } catch (NameNotFoundException e2) {
                    return z;
                } catch (Throwable th) {
                    return z;
                }
            }
            return z;
        } catch (NameNotFoundException e3) {
            return false;
        } catch (Throwable th2) {
            return false;
        }
    }

    private static String w(Context context) {
        String d;
        try {
            d = d(context, "");
            if (d(d)) {
                return d;
            }
            d = g(context);
            if (d(d) && !"9774d56d682e549c".equals(d.toLowerCase(Locale.getDefault()))) {
                return d;
            }
            d = u(context);
            if (!d(d)) {
                d = x(context);
                if (d == null) {
                    d = " ";
                }
            }
            return !d(d) ? "" : d;
        } catch (Throwable e) {
            d.e("AndroidUtil", "", e);
            d = x(context);
            return !d(d) ? "" : d;
        }
    }

    private static String x(Context context) {
        d.c("AndroidUtil", "Action:getSavedUuid");
        String string = context.getSharedPreferences("PrefsFile", 0).getString(CacheEntity.KEY, null);
        if (!k.a(string)) {
            return string;
        }
        if (!c()) {
            return z(context);
        }
        Object b = cn.jiguang.d.a.a.b(context);
        return TextUtils.isEmpty(b) ? (VERSION.SDK_INT < 23 || (a(context, "android.permission.WRITE_EXTERNAL_STORAGE") && a(context, "android.permission.READ_EXTERNAL_STORAGE"))) ? y(context) : z(context) : b;
    }

    private static String y(Context context) {
        FileOutputStream fileOutputStream;
        Throwable e;
        Throwable th;
        String d = d();
        d = d == null ? null : d + ".push_udid";
        File file = !k.a(d) ? new File(d) : null;
        if (file != null) {
            try {
                if (file.exists()) {
                    ArrayList a = f.a(new FileInputStream(file));
                    if (a.size() > 0) {
                        d = (String) a.get(0);
                        cn.jiguang.d.a.a.a(context, d);
                        d.e("AndroidUtil", "Got sdcard file saved udid - " + d);
                        return d;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        d = k.b(UUID.nameUUIDFromBytes((System.currentTimeMillis()).getBytes()).toString());
        cn.jiguang.d.a.a.a(context, d);
        if (file != null) {
            try {
                file.createNewFile();
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(d.getBytes());
                        fileOutputStream.flush();
                        d.e("AndroidUtil", "Saved udid into file");
                        if (fileOutputStream == null) {
                            return d;
                        }
                        try {
                            fileOutputStream.close();
                            return d;
                        } catch (IOException e3) {
                            return d;
                        }
                    } catch (IOException e4) {
                        e = e4;
                        try {
                            d.e("AndroidUtil", "write file error", e);
                            if (fileOutputStream != null) {
                                return d;
                            }
                            try {
                                fileOutputStream.close();
                                return d;
                            } catch (IOException e5) {
                                return d;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e6) {
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Throwable e7) {
                    Throwable th3 = e7;
                    fileOutputStream = null;
                    e = th3;
                    d.e("AndroidUtil", "write file error", e);
                    if (fileOutputStream != null) {
                        return d;
                    }
                    fileOutputStream.close();
                    return d;
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable e8) {
                d.e("AndroidUtil", "Create file in sdcard error", e8);
                return d;
            }
        }
        d.i("AndroidUtil", "udid file path is null");
        return d;
    }

    private static String z(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PrefsFile", 0);
        String string = sharedPreferences.getString(CacheEntity.KEY, null);
        if (string != null) {
            return string;
        }
        string = UUID.randomUUID().toString();
        Editor edit = sharedPreferences.edit();
        edit.putString(CacheEntity.KEY, string);
        edit.commit();
        return string;
    }
}
