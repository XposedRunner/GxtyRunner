package com.amap.api.mapcore.util;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.SparseArray;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import java.util.Hashtable;
import java.util.Random;
import org.json.JSONObject;

/* compiled from: Utils */
public final class lc {
    static WifiManager a = null;
    private static int b = 0;
    private static String[] c = null;
    private static Hashtable<String, Long> d = new Hashtable();
    private static SparseArray<String> e = null;
    private static String[] f = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    private static boolean g = false;

    public static double a(double d) {
        return ((double) ((long) (d * 1000000.0d))) / 1000000.0d;
    }

    public static float a(float f) {
        return (float) (((double) ((long) (((double) f) * 100.0d))) / 100.0d);
    }

    public static float a(double[] dArr) {
        if (dArr.length != 4) {
            return 0.0f;
        }
        float[] fArr = new float[1];
        Location.distanceBetween(dArr[0], dArr[1], dArr[2], dArr[3], fArr);
        return fArr[0];
    }

    public static int a(int i) {
        return (i * 2) - 113;
    }

    public static int a(NetworkInfo networkInfo) {
        return (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) ? networkInfo.getType() : -1;
    }

    public static long a() {
        return System.currentTimeMillis();
    }

    public static Object a(Context context, String str) {
        Object obj = null;
        if (context != null) {
            try {
                obj = context.getApplicationContext().getSystemService(str);
            } catch (Throwable th) {
                kz.a(th, "Utils", "getServ");
            }
        }
        return obj;
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return c() < 17 ? b(context, "android.provider.Settings$System") : b(context, "android.provider.Settings$Global");
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean a(String str) {
        return (TextUtils.isEmpty(str) || "00:00:00:00:00:00".equals(str) || str.contains(" :")) ? false : true;
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return gl.a(jSONObject, str);
    }

    public static byte[] a(int i, byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            bArr = new byte[2];
        }
        bArr[0] = (byte) (i & 255);
        bArr[1] = (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8);
        return bArr;
    }

    public static byte[] a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) ((int) ((j >> (i * 8)) & 255));
        }
        return bArr;
    }

    public static byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            bArr2 = gl.b(bArr);
        } catch (Throwable th) {
            kz.a(th, "Utils", "gz");
        }
        return bArr2;
    }

    public static String[] a(TelephonyManager telephonyManager) {
        int parseInt;
        Object obj = null;
        if (telephonyManager != null) {
            obj = telephonyManager.getNetworkOperator();
        }
        String[] strArr = new String[]{"0", "0"};
        int i = TextUtils.isEmpty(obj) ? 0 : !TextUtils.isDigitsOnly(obj) ? 0 : obj.length() <= 4 ? 0 : 1;
        if (i != 0) {
            strArr[0] = obj.substring(0, 3);
            char[] toCharArray = obj.substring(3).toCharArray();
            i = 0;
            while (i < toCharArray.length && Character.isDigit(toCharArray[i])) {
                i++;
            }
            strArr[1] = obj.substring(3, i + 3);
        }
        try {
            parseInt = Integer.parseInt(strArr[0]);
        } catch (Throwable th) {
            kz.a(th, "Utils", "getMccMnc");
            parseInt = 0;
        }
        if (parseInt == 0) {
            strArr[0] = "0";
        }
        if ("0".equals(strArr[0]) || "0".equals(strArr[1])) {
            return ("0".equals(strArr[0]) && "0".equals(strArr[1]) && c != null) ? c : strArr;
        } else {
            c = strArr;
            return strArr;
        }
    }

    public static double b(double d) {
        return ((double) ((long) (d * 100.0d))) / 100.0d;
    }

    public static long b() {
        return SystemClock.elapsedRealtime();
    }

    public static String b(int i) {
        String str = "其他错误";
        switch (i) {
            case 0:
                return "success";
            case 1:
                return "重要参数为空";
            case 2:
                return "WIFI信息不足";
            case 3:
                return "请求参数获取出现异常";
            case 4:
                return "网络连接异常";
            case 5:
                return "解析数据异常";
            case 6:
                return "定位结果错误";
            case 7:
                return "KEY错误";
            case 8:
                return "其他错误";
            case 9:
                return "初始化异常";
            case 10:
                return "定位服务启动失败";
            case 11:
                return "错误的基站信息，请检查是否插入SIM卡";
            case 12:
                return "缺少定位权限";
            case 13:
                return "网络定位失败，请检查设备是否插入sim卡，是否开启移动网络或开启了wifi模块";
            case 14:
                return "GPS 定位失败，由于设备当前 GPS 状态差,建议持设备到相对开阔的露天场所再次尝试";
            case 15:
                return "当前返回位置为模拟软件返回，请关闭模拟软件，或者在option中设置允许模拟";
            case 18:
                return "定位失败，飞行模式下关闭了WIFI开关，请关闭飞行模式或者打开WIFI开关";
            case 19:
                return "定位失败，没有检查到SIM卡，并且关闭了WIFI开关，请打开WIFI开关或者插入SIM卡";
            default:
                return str;
        }
    }

    public static String b(Context context) {
        CharSequence charSequence = null;
        if (!TextUtils.isEmpty(kz.g)) {
            return kz.g;
        }
        if (context == null) {
            return null;
        }
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(fx.c(context), 64);
        } catch (Throwable th) {
            kz.a(th, "Utils", "getAppName part");
            packageInfo = null;
        }
        try {
            if (TextUtils.isEmpty(kz.h)) {
                kz.h = null;
            }
        } catch (Throwable th2) {
            kz.a(th2, "Utils", "getAppName");
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (packageInfo != null) {
            if (packageInfo.applicationInfo != null) {
                charSequence = packageInfo.applicationInfo.loadLabel(context.getPackageManager());
            }
            if (charSequence != null) {
                stringBuilder.append(charSequence.toString());
            }
            if (!TextUtils.isEmpty(packageInfo.versionName)) {
                stringBuilder.append(packageInfo.versionName);
            }
        }
        Object c = fx.c(context);
        if (!TextUtils.isEmpty(c)) {
            stringBuilder.append(",").append(c);
        }
        if (!TextUtils.isEmpty(kz.h)) {
            stringBuilder.append(",").append(kz.h);
        }
        String stringBuilder2 = stringBuilder.toString();
        kz.g = stringBuilder2;
        return stringBuilder2;
    }

    public static String b(TelephonyManager telephonyManager) {
        int i = 0;
        if (e == null) {
            SparseArray sparseArray = new SparseArray();
            e = sparseArray;
            sparseArray.append(0, "UNKWN");
            e.append(1, "GPRS");
            e.append(2, "EDGE");
            e.append(3, "UMTS");
            e.append(4, "CDMA");
            e.append(5, "EVDO_0");
            e.append(6, "EVDO_A");
            e.append(7, "1xRTT");
            e.append(8, "HSDPA");
            e.append(9, "HSUPA");
            e.append(10, "HSPA");
            e.append(11, "IDEN");
            e.append(12, "EVDO_B");
            e.append(13, "LTE");
            e.append(14, "EHRPD");
            e.append(15, "HSPAP");
        }
        if (telephonyManager != null) {
            i = telephonyManager.getNetworkType();
        }
        return (String) e.get(i, "UNKWN");
    }

    private static boolean b(Context context, String str) throws Throwable {
        ContentResolver contentResolver = context.getContentResolver();
        String str2 = ((String) la.a(str, "AIRPLANE_MODE_ON")).toString();
        return ((Integer) la.a(str, "getInt", new Object[]{contentResolver, str2}, new Class[]{ContentResolver.class, String.class})).intValue() == 1;
    }

    public static byte[] b(int i, byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            bArr = new byte[4];
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & 255);
        }
        return bArr;
    }

    public static byte[] b(String str) {
        return a(d(str), null);
    }

    public static int c() {
        if (b > 0) {
            return b;
        }
        int i = 0;
        String str = "android.os.Build$VERSION";
        try {
            return la.b(str, "SDK_INT");
        } catch (Throwable th) {
            return i;
        }
    }

    @SuppressLint({"NewApi"})
    public static boolean c(Context context) {
        if (context == null) {
            return true;
        }
        boolean isWifiEnabled;
        if (a == null) {
            a = (WifiManager) a(context, IXAdSystemUtils.NT_WIFI);
        }
        try {
            isWifiEnabled = a.isWifiEnabled();
        } catch (Throwable th) {
            isWifiEnabled = false;
        }
        if (isWifiEnabled || c() <= 17) {
            return isWifiEnabled;
        }
        try {
            return "true".equals(String.valueOf(la.a(a, "isScanAlwaysAvailable", new Object[0])));
        } catch (Throwable th2) {
            return isWifiEnabled;
        }
    }

    public static byte[] c(String str) {
        return b(d(str), null);
    }

    public static int d(String str) throws NumberFormatException {
        return Integer.parseInt(str);
    }

    public static String d() {
        return Build.MODEL;
    }

    public static String d(Context context) {
        String l = gd.l(context);
        if (TextUtils.isEmpty(l) || l.equals("00:00:00:00:00:00")) {
            l = lb.a(context);
        }
        if (TextUtils.isEmpty(l)) {
            l = "00:00:00:00:00:00";
        }
        if (!g) {
            lb.a(context, l);
            g = true;
        }
        return l;
    }

    public static byte e(String str) throws NumberFormatException {
        return Byte.parseByte(str);
    }

    public static String e() {
        return VERSION.RELEASE;
    }

    public static int f() {
        return new Random().nextInt(65536) - 32768;
    }
}
