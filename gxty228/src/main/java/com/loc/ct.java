package com.loc;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.SparseArray;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.DPoint;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.github.mikephil.charting.utils.Utils;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Random;
import org.json.JSONObject;

/* compiled from: Utils */
public final class ct {
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

    public static float a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        return a(new double[]{aMapLocation.getLatitude(), aMapLocation.getLongitude(), aMapLocation2.getLatitude(), aMapLocation2.getLongitude()});
    }

    public static float a(DPoint dPoint, DPoint dPoint2) {
        return a(new double[]{dPoint.getLatitude(), dPoint.getLongitude(), dPoint2.getLatitude(), dPoint2.getLongitude()});
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
                cl.a(th, "Utils", "getServ");
            }
        }
        return obj;
    }

    public static String a(long j, String str) {
        SimpleDateFormat simpleDateFormat;
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            str = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            simpleDateFormat = new SimpleDateFormat(str, Locale.CHINA);
            try {
                simpleDateFormat.applyPattern(str);
            } catch (Throwable th2) {
                th = th2;
                cl.a(th, "Utils", "formatUTC");
                if (j <= 0) {
                    j = System.currentTimeMillis();
                }
                return simpleDateFormat == null ? "NULL" : simpleDateFormat.format(Long.valueOf(j));
            }
        } catch (Throwable th3) {
            th = th3;
            simpleDateFormat = null;
            cl.a(th, "Utils", "formatUTC");
            if (j <= 0) {
                j = System.currentTimeMillis();
            }
            if (simpleDateFormat == null) {
            }
        }
        if (j <= 0) {
            j = System.currentTimeMillis();
        }
        if (simpleDateFormat == null) {
        }
    }

    public static boolean a(long j, long j2) {
        String str = "yyyyMMddHH";
        String a = a(j, str);
        str = a(j2, str);
        return ("NULL".equals(a) || "NULL".equals(str)) ? false : a.equals(str);
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return c() < 17 ? c(context, "android.provider.Settings$System") : c(context, "android.provider.Settings$Global");
        } catch (Throwable th) {
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.database.sqlite.SQLiteDatabase r6, java.lang.String r7) {
        /*
        r2 = 0;
        r1 = 1;
        r0 = 0;
        r3 = android.text.TextUtils.isEmpty(r7);
        if (r3 == 0) goto L_0x000a;
    L_0x0009:
        return r0;
    L_0x000a:
        r3 = "2.0.201501131131";
        r4 = ".";
        r5 = "";
        r3 = r3.replace(r4, r5);
        if (r6 == 0) goto L_0x0009;
    L_0x0016:
        r4 = r6.isOpen();	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        if (r4 == 0) goto L_0x0009;
    L_0x001c:
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r4.<init>();	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r5 = "SELECT count(*) as c FROM sqlite_master WHERE type = 'table' AND name = '";
        r4.append(r5);	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r5 = r7.trim();	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r5 = r4.append(r5);	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r3 = r5.append(r3);	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r5 = "' ";
        r3.append(r5);	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r3 = r4.toString();	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r5 = 0;
        r2 = r6.rawQuery(r3, r5);	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        if (r2 == 0) goto L_0x0050;
    L_0x0042:
        r3 = r2.moveToFirst();	 Catch:{ Throwable -> 0x006e, all -> 0x0067 }
        if (r3 == 0) goto L_0x0050;
    L_0x0048:
        r3 = 0;
        r3 = r2.getInt(r3);	 Catch:{ Throwable -> 0x006e, all -> 0x0067 }
        if (r3 <= 0) goto L_0x0050;
    L_0x004f:
        r0 = r1;
    L_0x0050:
        r3 = 0;
        r5 = r4.length();	 Catch:{ Throwable -> 0x006e, all -> 0x0067 }
        r4.delete(r3, r5);	 Catch:{ Throwable -> 0x006e, all -> 0x0067 }
        if (r2 == 0) goto L_0x0009;
    L_0x005a:
        r2.close();
        goto L_0x0009;
    L_0x005e:
        r0 = move-exception;
        r0 = r2;
    L_0x0060:
        if (r0 == 0) goto L_0x0071;
    L_0x0062:
        r0.close();
        r0 = r1;
        goto L_0x0009;
    L_0x0067:
        r0 = move-exception;
        if (r2 == 0) goto L_0x006d;
    L_0x006a:
        r2.close();
    L_0x006d:
        throw r0;
    L_0x006e:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0060;
    L_0x0071:
        r0 = r1;
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ct.a(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }

    public static boolean a(Location location, int i) {
        Bundle extras = location.getExtras();
        return (extras != null ? extras.getInt("satellites") : 0) <= 0 ? true : i == 0 && location.getAltitude() == Utils.DOUBLE_EPSILON && location.getBearing() == 0.0f && location.getSpeed() == 0.0f;
    }

    public static boolean a(AMapLocation aMapLocation) {
        return (aMapLocation != null && aMapLocation.getErrorCode() == 0) ? b(aMapLocation) : false;
    }

    public static boolean a(AMapLocationServer aMapLocationServer) {
        return (aMapLocationServer == null || Constants.VIA_SHARE_TYPE_PUBLISHVIDEO.equals(aMapLocationServer.d()) || "5".equals(aMapLocationServer.d()) || Constants.VIA_SHARE_TYPE_INFO.equals(aMapLocationServer.d())) ? false : b((AMapLocation) aMapLocationServer);
    }

    public static boolean a(String str) {
        return (TextUtils.isEmpty(str) || "00:00:00:00:00:00".equals(str) || str.contains(" :")) ? false : true;
    }

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        ArrayList d = d(str);
        String[] split = str2.toString().split("#");
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < split.length) {
            if (split[i].contains(",nb") || split[i].contains(",access")) {
                i2++;
                if (d.contains(split[i])) {
                    i3++;
                }
            }
            i++;
        }
        return ((double) (i3 * 2)) >= ((double) (d.size() + i2)) * 0.618d;
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return dl.a(jSONObject, str);
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
            bArr2 = dl.b(bArr);
        } catch (Throwable th) {
            cl.a(th, "Utils", "gz");
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
            cl.a(th, "Utils", "getMccMnc");
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
        if (!TextUtils.isEmpty(cl.g)) {
            return cl.g;
        }
        if (context == null) {
            return null;
        }
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(db.c(context), 64);
        } catch (Throwable th) {
            cl.a(th, "Utils", "getAppName part");
            packageInfo = null;
        }
        try {
            if (TextUtils.isEmpty(cl.h)) {
                cl.h = null;
            }
        } catch (Throwable th2) {
            cl.a(th2, "Utils", "getAppName");
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
        Object c = db.c(context);
        if (!TextUtils.isEmpty(c)) {
            stringBuilder.append(",").append(c);
        }
        if (!TextUtils.isEmpty(cl.h)) {
            stringBuilder.append(",").append(cl.h);
        }
        String stringBuilder2 = stringBuilder.toString();
        cl.g = stringBuilder2;
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

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(java.lang.String r7, java.lang.String r8) {
        /*
        r1 = 0;
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x00df }
        r0.<init>(r7);	 Catch:{ Throwable -> 0x00df }
        r2 = r0.exists();	 Catch:{ Throwable -> 0x00df }
        if (r2 == 0) goto L_0x0012;
    L_0x000c:
        r2 = r0.isDirectory();	 Catch:{ Throwable -> 0x00df }
        if (r2 == 0) goto L_0x0013;
    L_0x0012:
        return;
    L_0x0013:
        r2 = java.io.File.separator;	 Catch:{ Throwable -> 0x00df }
        r2 = r8.endsWith(r2);	 Catch:{ Throwable -> 0x00df }
        if (r2 != 0) goto L_0x002e;
    L_0x001b:
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00df }
        r2.<init>();	 Catch:{ Throwable -> 0x00df }
        r2 = r2.append(r8);	 Catch:{ Throwable -> 0x00df }
        r3 = java.io.File.separator;	 Catch:{ Throwable -> 0x00df }
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x00df }
        r8 = r2.toString();	 Catch:{ Throwable -> 0x00df }
    L_0x002e:
        r2 = new java.util.zip.ZipInputStream;	 Catch:{ Throwable -> 0x00df }
        r3 = new java.io.FileInputStream;	 Catch:{ Throwable -> 0x00df }
        r3.<init>(r0);	 Catch:{ Throwable -> 0x00df }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x00df }
    L_0x0038:
        r0 = r2.getNextEntry();	 Catch:{ Throwable -> 0x00b8, all -> 0x00dc }
        if (r0 == 0) goto L_0x0054;
    L_0x003e:
        r3 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3 = new byte[r3];	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        r4 = r0.getName();	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        if (r5 != 0) goto L_0x0054;
    L_0x004c:
        r5 = "../";
        r5 = r4.contains(r5);	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        if (r5 == 0) goto L_0x005d;
    L_0x0054:
        r2.closeEntry();	 Catch:{ Throwable -> 0x005b }
        r2.close();	 Catch:{ Throwable -> 0x005b }
        goto L_0x0012;
    L_0x005b:
        r0 = move-exception;
        goto L_0x0012;
    L_0x005d:
        r0 = r0.isDirectory();	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        if (r0 != 0) goto L_0x0038;
    L_0x0063:
        r5 = new java.io.File;	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        r0.<init>();	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        r0 = r0.append(r8);	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        r0 = r0.append(r4);	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        r5.<init>(r0);	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        r0 = new java.io.File;	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        r4 = r5.getParent();	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        r0.<init>(r4);	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        r4 = r0.exists();	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        if (r4 != 0) goto L_0x008b;
    L_0x0088:
        r0.mkdirs();	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
    L_0x008b:
        r0 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
        r0.<init>(r5);	 Catch:{ Exception -> 0x00e6, all -> 0x00b1 }
    L_0x0090:
        r4 = 0;
        r5 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r4 = r2.read(r3, r4, r5);	 Catch:{ Exception -> 0x009f, all -> 0x00e1 }
        r5 = -1;
        if (r4 == r5) goto L_0x00a8;
    L_0x009a:
        r5 = 0;
        r0.write(r3, r5, r4);	 Catch:{ Exception -> 0x009f, all -> 0x00e1 }
        goto L_0x0090;
    L_0x009f:
        r3 = move-exception;
    L_0x00a0:
        if (r0 == 0) goto L_0x0038;
    L_0x00a2:
        r0.close();	 Catch:{ Throwable -> 0x00a6, all -> 0x00dc }
        goto L_0x0038;
    L_0x00a6:
        r0 = move-exception;
        goto L_0x0038;
    L_0x00a8:
        r0.flush();	 Catch:{ Exception -> 0x009f, all -> 0x00e1 }
        r0.close();	 Catch:{ Throwable -> 0x00af, all -> 0x00dc }
        goto L_0x0038;
    L_0x00af:
        r0 = move-exception;
        goto L_0x0038;
    L_0x00b1:
        r0 = move-exception;
    L_0x00b2:
        if (r1 == 0) goto L_0x00b7;
    L_0x00b4:
        r1.close();	 Catch:{ Throwable -> 0x00d8, all -> 0x00dc }
    L_0x00b7:
        throw r0;	 Catch:{ Throwable -> 0x00b8, all -> 0x00dc }
    L_0x00b8:
        r0 = move-exception;
        r1 = r2;
    L_0x00ba:
        r2 = "Utils";
        r3 = "unZip";
        com.loc.cl.a(r0, r2, r3);	 Catch:{ all -> 0x00ce }
        if (r1 == 0) goto L_0x0012;
    L_0x00c3:
        r1.closeEntry();	 Catch:{ Throwable -> 0x00cb }
        r1.close();	 Catch:{ Throwable -> 0x00cb }
        goto L_0x0012;
    L_0x00cb:
        r0 = move-exception;
        goto L_0x0012;
    L_0x00ce:
        r0 = move-exception;
    L_0x00cf:
        if (r1 == 0) goto L_0x00d7;
    L_0x00d1:
        r1.closeEntry();	 Catch:{ Throwable -> 0x00da }
        r1.close();	 Catch:{ Throwable -> 0x00da }
    L_0x00d7:
        throw r0;
    L_0x00d8:
        r1 = move-exception;
        goto L_0x00b7;
    L_0x00da:
        r1 = move-exception;
        goto L_0x00d7;
    L_0x00dc:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00cf;
    L_0x00df:
        r0 = move-exception;
        goto L_0x00ba;
    L_0x00e1:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x00b2;
    L_0x00e6:
        r0 = move-exception;
        r0 = r1;
        goto L_0x00a0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ct.b(java.lang.String, java.lang.String):void");
    }

    public static boolean b(long j, long j2) {
        String str = "yyyyMMdd";
        String a = a(j, str);
        str = a(j2, str);
        return ("NULL".equals(a) || "NULL".equals(str)) ? false : a.equals(str);
    }

    public static boolean b(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean b(AMapLocation aMapLocation) {
        double longitude = aMapLocation.getLongitude();
        double latitude = aMapLocation.getLatitude();
        return !(longitude == Utils.DOUBLE_EPSILON && latitude == Utils.DOUBLE_EPSILON) && longitude <= 180.0d && latitude <= 90.0d && longitude >= -180.0d && latitude >= -90.0d;
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
        return a(Integer.parseInt(str), null);
    }

    public static double c(double d) {
        return ((double) ((long) (d * 1000000.0d))) / 1000000.0d;
    }

    public static int c() {
        if (b > 0) {
            return b;
        }
        int i = 0;
        String str = "android.os.Build$VERSION";
        try {
            return co.b(str, "SDK_INT");
        } catch (Throwable th) {
            return i;
        }
    }

    public static NetworkInfo c(Context context) {
        NetworkInfo networkInfo = null;
        try {
            networkInfo = df.r(context);
        } catch (Throwable th) {
            cl.a(th, "Utils", "getNetWorkInfo");
        }
        return networkInfo;
    }

    public static boolean c(long j, long j2) {
        boolean z = true;
        if (!b(j, j2)) {
            return false;
        }
        Calendar instance = Calendar.getInstance(Locale.CHINA);
        instance.setTimeInMillis(j);
        int i = instance.get(11);
        instance.setTimeInMillis(j2);
        int i2 = instance.get(11);
        if (i <= 12 ? i2 <= 12 : i2 > 12) {
            z = false;
        }
        return z;
    }

    private static boolean c(Context context, String str) throws Throwable {
        ContentResolver contentResolver = context.getContentResolver();
        String str2 = ((String) co.a(str, "AIRPLANE_MODE_ON")).toString();
        return ((Integer) co.a(str, "getInt", new Object[]{contentResolver, str2}, new Class[]{ContentResolver.class, String.class})).intValue() == 1;
    }

    public static boolean c(String str, String str2) {
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory() && file2.getName().equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] c(String str) {
        return b(Integer.parseInt(str), null);
    }

    public static String d() {
        return Build.MODEL;
    }

    public static ArrayList<String> d(String str) {
        ArrayList<String> arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("#");
            int i = 0;
            while (i < split.length) {
                if (split[i].contains(",nb") || split[i].contains(",access")) {
                    arrayList.add(split[i]);
                }
                i++;
            }
        }
        return arrayList;
    }

    public static boolean d(Context context) {
        try {
            NetworkInfo c = c(context);
            return c != null && c.isConnectedOrConnecting();
        } catch (Throwable th) {
            return false;
        }
    }

    public static String e() {
        return VERSION.RELEASE;
    }

    public static void e(String str) {
        try {
            if (!str.endsWith(File.separator)) {
                str = str + File.separator;
            }
            File file = new File(str);
            if (file.exists() && file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    if (file2.isDirectory()) {
                        e(file2.getAbsolutePath());
                    } else {
                        file2.delete();
                    }
                }
                file.delete();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static boolean e(Context context) {
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(db.c(context))) {
                    return runningAppProcessInfo.importance != 100;
                }
            }
            return false;
        } catch (Throwable th) {
            cl.a(th, "Utils", "isApplicationBroughtToBackground");
            return true;
        }
    }

    public static double f(String str) throws NumberFormatException {
        return Double.parseDouble(str);
    }

    public static int f() {
        return new Random().nextInt(65536) - 32768;
    }

    public static boolean f(Context context) {
        if (VERSION.SDK_INT < 23 || context.getApplicationInfo().targetSdkVersion < 23) {
            for (String checkCallingOrSelfPermission : f) {
                if (context.checkCallingOrSelfPermission(checkCallingOrSelfPermission) != 0) {
                    return false;
                }
            }
            return true;
        }
        Application application = (Application) context;
        for (Object obj : f) {
            int b;
            try {
                b = co.b(application.getBaseContext(), "checkSelfPermission", obj);
            } catch (Throwable th) {
                b = 0;
            }
            if (b != 0) {
                return false;
            }
        }
        return true;
    }

    public static float g(String str) throws NumberFormatException {
        return Float.parseFloat(str);
    }

    public static void g() {
        d.clear();
    }

    @SuppressLint({"NewApi"})
    public static boolean g(Context context) {
        boolean isWifiEnabled;
        if (context == null) {
            return true;
        }
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
            return "true".equals(String.valueOf(co.a(a, "isScanAlwaysAvailable", new Object[0])));
        } catch (Throwable th2) {
            return isWifiEnabled;
        }
    }

    public static int h(String str) throws NumberFormatException {
        return Integer.parseInt(str);
    }

    public static String h() {
        try {
            return dg.b("S128DF1572465B890OE3F7A13167KLEI".getBytes("UTF-8")).substring(20);
        } catch (Throwable th) {
            return "";
        }
    }

    public static String h(Context context) {
        NetworkInfo c = c(context);
        if (c == null || !c.isConnectedOrConnecting()) {
            return "DISCONNECTED";
        }
        String str = "UNKNOWN";
        int type = c.getType();
        if (type == 1) {
            return "WIFI";
        }
        if (type != 0) {
            return str;
        }
        str = c.getSubtypeName();
        switch (c.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return "3G";
            case 13:
                return "4G";
            default:
                return "GSM".equalsIgnoreCase(str) ? "2G" : ("TD-SCDMA".equalsIgnoreCase(str) || "WCDMA".equalsIgnoreCase(str) || "CDMA2000".equalsIgnoreCase(str)) ? "3G" : str;
        }
    }

    public static int i(String str) throws NumberFormatException {
        return Integer.parseInt(str, 16);
    }

    public static String i() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getExternalStorageDirectory().getAbsolutePath()).append(File.separator);
        stringBuilder.append("amap").append(File.separator);
        stringBuilder.append("openamaplocationsdk").append(File.separator);
        return stringBuilder.toString();
    }

    public static String i(Context context) {
        String l = df.l(context);
        if (TextUtils.isEmpty(l) || l.equals("00:00:00:00:00:00")) {
            l = "00:00:00:00:00:00";
            if (context != null) {
                l = cs.b(context, "pref", "smac", l);
            }
        }
        if (TextUtils.isEmpty(l)) {
            l = "00:00:00:00:00:00";
        }
        if (!g) {
            if (!(context == null || TextUtils.isEmpty(l))) {
                cs.a(context, "pref", "smac", l);
            }
            g = true;
        }
        return l;
    }

    public static byte j(String str) throws NumberFormatException {
        return Byte.parseByte(str);
    }
}
