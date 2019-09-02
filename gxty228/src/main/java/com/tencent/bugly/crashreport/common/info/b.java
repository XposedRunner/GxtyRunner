package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import cn.jiguang.net.HttpUtils;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: BUGLY */
public class b {
    private static final String[] a = new String[]{"/su", "/su/bin/su", "/sbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su", "/system/xbin/su", "/system/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/system/bin/cufsdosck", "/system/xbin/cufsdosck", "/system/bin/cufsmgr", "/system/xbin/cufsmgr", "/system/bin/cufaevdd", "/system/xbin/cufaevdd", "/system/bin/conbb", "/system/xbin/conbb"};
    private static final String[] b = new String[]{"com.ami.duosupdater.ui", "com.ami.launchmetro", "com.ami.syncduosservices", "com.bluestacks.home", "com.bluestacks.windowsfilemanager", "com.bluestacks.settings", "com.bluestacks.bluestackslocationprovider", "com.bluestacks.appsettings", "com.bluestacks.bstfolder", "com.bluestacks.BstCommandProcessor", "com.bluestacks.s2p", "com.bluestacks.setup", "com.kaopu001.tiantianserver", "com.kpzs.helpercenter", "com.kaopu001.tiantianime", "com.android.development_settings", "com.android.development", "com.android.customlocale2", "com.genymotion.superuser", "com.genymotion.clipboardproxy", "com.uc.xxzs.keyboard", "com.uc.xxzs", "com.blue.huang17.agent", "com.blue.huang17.launcher", "com.blue.huang17.ime", "com.microvirt.guide", "com.microvirt.market", "com.microvirt.memuime", "cn.itools.vm.launcher", "cn.itools.vm.proxy", "cn.itools.vm.softkeyboard", "cn.itools.avdmarket", "com.syd.IME", "com.bignox.app.store.hd", "com.bignox.launcher", "com.bignox.app.phone", "com.bignox.app.noxservice", "com.android.noxpush", "com.haimawan.push", "me.haima.helpcenter", "com.windroy.launcher", "com.windroy.superuser", "com.windroy.launcher", "com.windroy.ime", "com.android.flysilkworm", "com.android.emu.inputservice", "com.tiantian.ime", "com.microvirt.launcher", "me.le8.androidassist", "com.vphone.helper", "com.vphone.launcher", "com.duoyi.giftcenter.giftcenter"};
    private static final String[] c = new String[]{"/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/qemud", "/dev/qemu_pipe", "/dev/socket/baseband_genyd", "/dev/socket/genyd"};
    private static String d = null;
    private static String e = null;

    public static String a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static String b() {
        try {
            return VERSION.RELEASE;
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static int c() {
        try {
            return VERSION.SDK_INT;
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    public static String a(Context context) {
        if (context == null) {
            return null;
        }
        if (AppInfo.a(context, "android.permission.READ_PHONE_STATE")) {
            String deviceId;
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    deviceId = telephonyManager.getDeviceId();
                    if (deviceId != null) {
                        try {
                            deviceId = deviceId.toLowerCase();
                        } catch (Throwable th) {
                            an.a("Failed to get IMEI.", new Object[0]);
                            return deviceId;
                        }
                    }
                }
                deviceId = null;
            } catch (Throwable th2) {
                deviceId = null;
                an.a("Failed to get IMEI.", new Object[0]);
                return deviceId;
            }
            return deviceId;
        }
        an.d("no READ_PHONE_STATE permission to get IMEI", new Object[0]);
        return null;
    }

    public static String b(Context context) {
        String subscriberId;
        if (context == null) {
            return null;
        }
        if (AppInfo.a(context, "android.permission.READ_PHONE_STATE")) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    subscriberId = telephonyManager.getSubscriberId();
                    if (subscriberId != null) {
                        try {
                            subscriberId = subscriberId.toLowerCase();
                        } catch (Throwable th) {
                            an.a("Failed to get IMSI.", new Object[0]);
                            return subscriberId;
                        }
                    }
                }
                subscriberId = null;
            } catch (Throwable th2) {
                subscriberId = null;
                an.a("Failed to get IMSI.", new Object[0]);
                return subscriberId;
            }
            return subscriberId;
        }
        an.d("no READ_PHONE_STATE permission to get IMSI", new Object[0]);
        return null;
    }

    public static String c(Context context) {
        Throwable th;
        String str = "fail";
        if (context == null) {
            return str;
        }
        try {
            String string = Secure.getString(context.getContentResolver(), "android_id");
            if (string != null) {
                return string.toLowerCase();
            }
            try {
                return "null";
            } catch (Throwable th2) {
                Throwable th3 = th2;
                str = string;
                th = th3;
                if (!an.a(th)) {
                    return str;
                }
                an.a("Failed to get Android ID.", new Object[0]);
                return str;
            }
        } catch (Throwable th4) {
            th = th4;
            if (!an.a(th)) {
                return str;
            }
            an.a("Failed to get Android ID.", new Object[0]);
            return str;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String d(android.content.Context r8) {
        /*
        r6 = 1;
        r5 = 0;
        r1 = "fail";
        if (r8 != 0) goto L_0x0007;
    L_0x0006:
        return r1;
    L_0x0007:
        r0 = "wifi";
        r0 = r8.getSystemService(r0);	 Catch:{ Throwable -> 0x0066 }
        r0 = (android.net.wifi.WifiManager) r0;	 Catch:{ Throwable -> 0x0066 }
        if (r0 == 0) goto L_0x0070;
    L_0x0011:
        r0 = r0.getConnectionInfo();	 Catch:{ Throwable -> 0x0066 }
        if (r0 == 0) goto L_0x0070;
    L_0x0017:
        r0 = r0.getMacAddress();	 Catch:{ Throwable -> 0x0066 }
        if (r0 == 0) goto L_0x0025;
    L_0x001d:
        r1 = "02:00:00:00:00:00";
        r1 = r0.equals(r1);	 Catch:{ Throwable -> 0x0072 }
        if (r1 == 0) goto L_0x0054;
    L_0x0025:
        r1 = "wifi.interface";
        r1 = com.tencent.bugly.proguard.ap.b(r8, r1);	 Catch:{ Throwable -> 0x0072 }
        r2 = "MAC interface: %s";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0072 }
        r4 = 0;
        r3[r4] = r1;	 Catch:{ Throwable -> 0x0072 }
        com.tencent.bugly.proguard.an.c(r2, r3);	 Catch:{ Throwable -> 0x0072 }
        r1 = java.net.NetworkInterface.getByName(r1);	 Catch:{ Throwable -> 0x0072 }
        if (r1 != 0) goto L_0x0042;
    L_0x003c:
        r1 = "wlan0";
        r1 = java.net.NetworkInterface.getByName(r1);	 Catch:{ Throwable -> 0x0072 }
    L_0x0042:
        if (r1 != 0) goto L_0x004a;
    L_0x0044:
        r1 = "eth0";
        r1 = java.net.NetworkInterface.getByName(r1);	 Catch:{ Throwable -> 0x0072 }
    L_0x004a:
        if (r1 == 0) goto L_0x0054;
    L_0x004c:
        r1 = r1.getHardwareAddress();	 Catch:{ Throwable -> 0x0072 }
        r0 = com.tencent.bugly.proguard.ap.e(r1);	 Catch:{ Throwable -> 0x0072 }
    L_0x0054:
        if (r0 != 0) goto L_0x0058;
    L_0x0056:
        r0 = "null";
    L_0x0058:
        r1 = "MAC address: %s";
        r2 = new java.lang.Object[r6];
        r2[r5] = r0;
        com.tencent.bugly.proguard.an.c(r1, r2);
        r1 = r0.toLowerCase();
        goto L_0x0006;
    L_0x0066:
        r0 = move-exception;
    L_0x0067:
        r2 = com.tencent.bugly.proguard.an.a(r0);
        if (r2 != 0) goto L_0x0070;
    L_0x006d:
        r0.printStackTrace();
    L_0x0070:
        r0 = r1;
        goto L_0x0054;
    L_0x0072:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x0067;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.d(android.content.Context):java.lang.String");
    }

    public static String e(Context context) {
        String str = "fail";
        if (context == null) {
            return str;
        }
        String simSerialNumber;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                simSerialNumber = telephonyManager.getSimSerialNumber();
                if (simSerialNumber == null) {
                    try {
                        simSerialNumber = "null";
                    } catch (Throwable th) {
                        an.a("Failed to get SIM serial number.", new Object[0]);
                        return simSerialNumber;
                    }
                }
            }
            simSerialNumber = str;
        } catch (Throwable th2) {
            simSerialNumber = str;
            an.a("Failed to get SIM serial number.", new Object[0]);
            return simSerialNumber;
        }
        return simSerialNumber;
    }

    public static String d() {
        try {
            return Build.SERIAL;
        } catch (Throwable th) {
            an.a("Failed to get hardware serial number.", new Object[0]);
            return "fail";
        }
    }

    public static boolean e() {
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                return true;
            }
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
        }
        return false;
    }

    public static String a(Context context, boolean z) {
        String str = null;
        if (z) {
            try {
                String b = ap.b(context, "ro.product.cpu.abilist");
                if (ap.a(b) || b.equals("fail")) {
                    b = ap.b(context, "ro.product.cpu.abi");
                }
                if (!(ap.a(b) || b.equals("fail"))) {
                    an.b(b.class, "ABI list: " + b, new Object[0]);
                    str = b.split(",")[0];
                }
            } catch (Throwable th) {
                if (!an.a(th)) {
                    th.printStackTrace();
                }
                return "fail";
            }
        }
        if (str == null) {
            str = System.getProperty("os.arch");
        }
        return "" + str;
    }

    public static long f() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (an.a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    public static long g() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (an.a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    public static long h() {
        FileReader fileReader;
        Throwable th;
        FileReader fileReader2;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader2 = new BufferedReader(fileReader, 2048);
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Throwable e) {
                                if (!an.a(e)) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (fileReader == null) {
                            return -1;
                        }
                        try {
                            fileReader.close();
                            return -1;
                        } catch (Throwable e2) {
                            if (an.a(e2)) {
                                return -1;
                            }
                            e2.printStackTrace();
                            return -1;
                        }
                    }
                    long parseLong = Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Throwable e22) {
                            if (!an.a(e22)) {
                                e22.printStackTrace();
                            }
                        }
                    }
                    if (fileReader == null) {
                        return parseLong;
                    }
                    try {
                        fileReader.close();
                        return parseLong;
                    } catch (Throwable e222) {
                        if (an.a(e222)) {
                            return parseLong;
                        }
                        e222.printStackTrace();
                        return parseLong;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader2 = null;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader2 = null;
            fileReader = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
    }

    public static long i() {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        FileReader fileReader2 = null;
        BufferedReader bufferedReader2;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader2 = new BufferedReader(fileReader, 2048);
                try {
                    bufferedReader2.readLine();
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Throwable e) {
                                if (!an.a(e)) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (fileReader == null) {
                            return -1;
                        }
                        try {
                            fileReader.close();
                            return -1;
                        } catch (Throwable e2) {
                            if (an.a(e2)) {
                                return -1;
                            }
                            e2.printStackTrace();
                            return -1;
                        }
                    }
                    long parseLong = 0 + (Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
                    readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Throwable e22) {
                                if (!an.a(e22)) {
                                    e22.printStackTrace();
                                }
                            }
                        }
                        if (fileReader == null) {
                            return -1;
                        }
                        try {
                            fileReader.close();
                            return -1;
                        } catch (Throwable e222) {
                            if (an.a(e222)) {
                                return -1;
                            }
                            e222.printStackTrace();
                            return -1;
                        }
                    }
                    parseLong += Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Throwable e2222) {
                                if (!an.a(e2222)) {
                                    e2222.printStackTrace();
                                }
                            }
                        }
                        if (fileReader == null) {
                            return -1;
                        }
                        try {
                            fileReader.close();
                            return -1;
                        } catch (Throwable e22222) {
                            if (an.a(e22222)) {
                                return -1;
                            }
                            e22222.printStackTrace();
                            return -1;
                        }
                    }
                    long parseLong2 = (Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) + parseLong;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Throwable e222222) {
                            if (!an.a(e222222)) {
                                e222222.printStackTrace();
                            }
                        }
                    }
                    if (fileReader == null) {
                        return parseLong2;
                    }
                    try {
                        fileReader.close();
                        return parseLong2;
                    } catch (Throwable e2222222) {
                        if (an.a(e2222222)) {
                            return parseLong2;
                        }
                        e2222222.printStackTrace();
                        return parseLong2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader2 = null;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader2 = null;
            fileReader = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
    }

    public static long j() {
        if (!e()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    public static long k() {
        if (!e()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    public static String l() {
        String str = "fail";
        try {
            str = Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
        }
        return str;
    }

    public static String m() {
        String str = "fail";
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (an.a(th)) {
                return str;
            }
            th.printStackTrace();
            return str;
        }
    }

    public static String f(Context context) {
        String str = "unknown";
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            String str2;
            if (activeNetworkInfo.getType() == 1) {
                str2 = "WIFI";
            } else {
                if (activeNetworkInfo.getType() == 0) {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (telephonyManager != null) {
                        int networkType = telephonyManager.getNetworkType();
                        switch (networkType) {
                            case 1:
                                str2 = "GPRS";
                                break;
                            case 2:
                                str2 = "EDGE";
                                break;
                            case 3:
                                str2 = "UMTS";
                                break;
                            case 4:
                                str2 = "CDMA";
                                break;
                            case 5:
                                str2 = "EVDO_0";
                                break;
                            case 6:
                                str2 = "EVDO_A";
                                break;
                            case 7:
                                str2 = "1xRTT";
                                break;
                            case 8:
                                str2 = "HSDPA";
                                break;
                            case 9:
                                str2 = "HSUPA";
                                break;
                            case 10:
                                str2 = "HSPA";
                                break;
                            case 11:
                                str2 = "iDen";
                                break;
                            case 12:
                                str2 = "EVDO_B";
                                break;
                            case 13:
                                str2 = "LTE";
                                break;
                            case 14:
                                str2 = "eHRPD";
                                break;
                            case 15:
                                str2 = "HSPA+";
                                break;
                            default:
                                str2 = "MOBILE(" + networkType + ")";
                                break;
                        }
                    }
                }
                str2 = str;
            }
            return str2;
        } catch (Throwable e) {
            if (an.a(e)) {
                return str;
            }
            e.printStackTrace();
            return str;
        }
    }

    public static String g(Context context) {
        String b = ap.b(context, "ro.miui.ui.version.name");
        if (!ap.a(b) && !b.equals("fail")) {
            return "XiaoMi/MIUI/" + b;
        }
        b = ap.b(context, "ro.build.version.emui");
        if (!ap.a(b) && !b.equals("fail")) {
            return "HuaWei/EMOTION/" + b;
        }
        b = ap.b(context, "ro.lenovo.series");
        if (ap.a(b) || b.equals("fail")) {
            b = ap.b(context, "ro.build.nubia.rom.name");
            if (!ap.a(b) && !b.equals("fail")) {
                return "Zte/NUBIA/" + b + "_" + ap.b(context, "ro.build.nubia.rom.code");
            }
            b = ap.b(context, "ro.meizu.product.model");
            if (!ap.a(b) && !b.equals("fail")) {
                return "Meizu/FLYME/" + ap.b(context, "ro.build.display.id");
            }
            b = ap.b(context, "ro.build.version.opporom");
            if (!ap.a(b) && !b.equals("fail")) {
                return "Oppo/COLOROS/" + b;
            }
            b = ap.b(context, "ro.vivo.os.build.display.id");
            if (!ap.a(b) && !b.equals("fail")) {
                return "vivo/FUNTOUCH/" + b;
            }
            b = ap.b(context, "ro.aa.romver");
            if (!ap.a(b) && !b.equals("fail")) {
                return "htc/" + b + HttpUtils.PATHS_SEPARATOR + ap.b(context, "ro.build.description");
            }
            b = ap.b(context, "ro.lewa.version");
            if (!ap.a(b) && !b.equals("fail")) {
                return "tcl/" + b + HttpUtils.PATHS_SEPARATOR + ap.b(context, "ro.build.display.id");
            }
            b = ap.b(context, "ro.gn.gnromvernumber");
            if (!ap.a(b) && !b.equals("fail")) {
                return "amigo/" + b + HttpUtils.PATHS_SEPARATOR + ap.b(context, "ro.build.display.id");
            }
            b = ap.b(context, "ro.build.tyd.kbstyle_version");
            if (ap.a(b) || b.equals("fail")) {
                return ap.b(context, "ro.build.fingerprint") + HttpUtils.PATHS_SEPARATOR + ap.b(context, "ro.build.rom.id");
            }
            return "dido/" + b;
        }
        return "Lenovo/VIBE/" + ap.b(context, "ro.build.version.incremental");
    }

    public static String h(Context context) {
        return ap.b(context, "ro.board.platform");
    }

    public static boolean n() {
        boolean z;
        boolean z2;
        for (String file : a) {
            if (new File(file).exists()) {
                z = true;
                break;
            }
        }
        z = false;
        if (Build.TAGS == null || !Build.TAGS.contains("test-keys")) {
            z2 = false;
        } else {
            z2 = true;
        }
        return z2 || z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String o() {
        /*
        r0 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r3.<init>();	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r1 = new java.io.File;	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r2 = "/sys/block/mmcblk0/device/type";
        r1.<init>(r2);	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r1 = r1.exists();	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        if (r1 == 0) goto L_0x00c0;
    L_0x0013:
        r1 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r2 = new java.io.FileReader;	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r4 = "/sys/block/mmcblk0/device/type";
        r2.<init>(r4);	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r1.<init>(r2);	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r2 = r1.readLine();	 Catch:{ Throwable -> 0x00b6, all -> 0x00a8 }
        if (r2 == 0) goto L_0x0028;
    L_0x0025:
        r3.append(r2);	 Catch:{ Throwable -> 0x00b6, all -> 0x00a8 }
    L_0x0028:
        r1.close();	 Catch:{ Throwable -> 0x00b6, all -> 0x00a8 }
        r2 = r1;
    L_0x002c:
        r1 = ",";
        r3.append(r1);	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        r1 = new java.io.File;	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        r4 = "/sys/block/mmcblk0/device/name";
        r1.<init>(r4);	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        r1 = r1.exists();	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        if (r1 == 0) goto L_0x0057;
    L_0x003e:
        r1 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        r4 = new java.io.FileReader;	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        r5 = "/sys/block/mmcblk0/device/name";
        r4.<init>(r5);	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        r1.<init>(r4);	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        r2 = r1.readLine();	 Catch:{ Throwable -> 0x00b6, all -> 0x00ad }
        if (r2 == 0) goto L_0x0053;
    L_0x0050:
        r3.append(r2);	 Catch:{ Throwable -> 0x00b6, all -> 0x00ad }
    L_0x0053:
        r1.close();	 Catch:{ Throwable -> 0x00b6, all -> 0x00ad }
        r2 = r1;
    L_0x0057:
        r1 = ",";
        r3.append(r1);	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        r1 = new java.io.File;	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        r4 = "/sys/block/mmcblk0/device/cid";
        r1.<init>(r4);	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        r1 = r1.exists();	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        if (r1 == 0) goto L_0x00be;
    L_0x0069:
        r1 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        r4 = new java.io.FileReader;	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        r5 = "/sys/block/mmcblk0/device/cid";
        r4.<init>(r5);	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        r1.<init>(r4);	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        r2 = r1.readLine();	 Catch:{ Throwable -> 0x00b6, all -> 0x00b0 }
        if (r2 == 0) goto L_0x007e;
    L_0x007b:
        r3.append(r2);	 Catch:{ Throwable -> 0x00b6, all -> 0x00b0 }
    L_0x007e:
        r0 = r3.toString();	 Catch:{ Throwable -> 0x00b6, all -> 0x00b3 }
        if (r1 == 0) goto L_0x0087;
    L_0x0084:
        r1.close();	 Catch:{ IOException -> 0x0088 }
    L_0x0087:
        return r0;
    L_0x0088:
        r1 = move-exception;
        com.tencent.bugly.proguard.an.a(r1);
        goto L_0x0087;
    L_0x008d:
        r1 = move-exception;
        r1 = r0;
    L_0x008f:
        if (r1 == 0) goto L_0x0087;
    L_0x0091:
        r1.close();	 Catch:{ IOException -> 0x0095 }
        goto L_0x0087;
    L_0x0095:
        r1 = move-exception;
        com.tencent.bugly.proguard.an.a(r1);
        goto L_0x0087;
    L_0x009a:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x009d:
        if (r2 == 0) goto L_0x00a2;
    L_0x009f:
        r2.close();	 Catch:{ IOException -> 0x00a3 }
    L_0x00a2:
        throw r0;
    L_0x00a3:
        r1 = move-exception;
        com.tencent.bugly.proguard.an.a(r1);
        goto L_0x00a2;
    L_0x00a8:
        r0 = move-exception;
        r2 = r1;
        goto L_0x009d;
    L_0x00ab:
        r0 = move-exception;
        goto L_0x009d;
    L_0x00ad:
        r0 = move-exception;
        r2 = r1;
        goto L_0x009d;
    L_0x00b0:
        r0 = move-exception;
        r2 = r1;
        goto L_0x009d;
    L_0x00b3:
        r0 = move-exception;
        r2 = r1;
        goto L_0x009d;
    L_0x00b6:
        r2 = move-exception;
        goto L_0x008f;
    L_0x00b8:
        r1 = move-exception;
        r1 = r2;
        goto L_0x008f;
    L_0x00bb:
        r1 = move-exception;
        r1 = r2;
        goto L_0x008f;
    L_0x00be:
        r1 = r2;
        goto L_0x007e;
    L_0x00c0:
        r2 = r0;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.o():java.lang.String");
    }

    public static String i(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        String b = ap.b(context, "ro.genymotion.version");
        if (b != null) {
            stringBuilder.append("ro.genymotion.version");
            stringBuilder.append("|");
            stringBuilder.append(b);
            stringBuilder.append("\n");
        }
        b = ap.b(context, "androVM.vbox_dpi");
        if (b != null) {
            stringBuilder.append("androVM.vbox_dpi");
            stringBuilder.append("|");
            stringBuilder.append(b);
            stringBuilder.append("\n");
        }
        b = ap.b(context, "qemu.sf.fake_camera");
        if (b != null) {
            stringBuilder.append("qemu.sf.fake_camera");
            stringBuilder.append("|");
            stringBuilder.append(b);
        }
        return stringBuilder.toString();
    }

    public static String j(Context context) {
        BufferedReader bufferedReader;
        Throwable th;
        StringBuilder stringBuilder = new StringBuilder();
        if (d == null) {
            d = ap.b(context, "ro.secure");
        }
        if (d != null) {
            stringBuilder.append("ro.secure");
            stringBuilder.append("|");
            stringBuilder.append(d);
            stringBuilder.append("\n");
        }
        if (e == null) {
            e = ap.b(context, "ro.debuggable");
        }
        if (e != null) {
            stringBuilder.append("ro.debuggable");
            stringBuilder.append("|");
            stringBuilder.append(e);
            stringBuilder.append("\n");
        }
        try {
            String readLine;
            bufferedReader = new BufferedReader(new FileReader("/proc/self/status"));
            do {
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } while (!readLine.startsWith("TracerPid:"));
            if (readLine != null) {
                readLine = readLine.substring("TracerPid:".length()).trim();
                stringBuilder.append("tracer_pid");
                stringBuilder.append("|");
                stringBuilder.append(readLine);
            }
            readLine = stringBuilder.toString();
            if (bufferedReader == null) {
                return readLine;
            }
            try {
                bufferedReader.close();
                return readLine;
            } catch (Throwable e) {
                an.a(e);
                return readLine;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String k(android.content.Context r6) {
        /*
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r1 = 0;
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x00ae, all -> 0x00bb }
        r3 = "/sys/class/power_supply/ac/online";
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00ae, all -> 0x00bb }
        r0 = r0.exists();	 Catch:{ Throwable -> 0x00ae, all -> 0x00bb }
        if (r0 == 0) goto L_0x0036;
    L_0x0013:
        r0 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00ae, all -> 0x00bb }
        r3 = new java.io.FileReader;	 Catch:{ Throwable -> 0x00ae, all -> 0x00bb }
        r4 = "/sys/class/power_supply/ac/online";
        r3.<init>(r4);	 Catch:{ Throwable -> 0x00ae, all -> 0x00bb }
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00ae, all -> 0x00bb }
        r1 = r0.readLine();	 Catch:{ Throwable -> 0x00d6, all -> 0x00c7 }
        if (r1 == 0) goto L_0x0032;
    L_0x0025:
        r3 = "ac_online";
        r2.append(r3);	 Catch:{ Throwable -> 0x00d6, all -> 0x00c7 }
        r3 = "|";
        r2.append(r3);	 Catch:{ Throwable -> 0x00d6, all -> 0x00c7 }
        r2.append(r1);	 Catch:{ Throwable -> 0x00d6, all -> 0x00c7 }
    L_0x0032:
        r0.close();	 Catch:{ Throwable -> 0x00d6, all -> 0x00c7 }
        r1 = r0;
    L_0x0036:
        r0 = "\n";
        r2.append(r0);	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        r3 = "/sys/class/power_supply/usb/online";
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        r0 = r0.exists();	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        if (r0 == 0) goto L_0x006b;
    L_0x0048:
        r0 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        r3 = new java.io.FileReader;	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        r4 = "/sys/class/power_supply/usb/online";
        r3.<init>(r4);	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        r1 = r0.readLine();	 Catch:{ Throwable -> 0x00d6, all -> 0x00cc }
        if (r1 == 0) goto L_0x0067;
    L_0x005a:
        r3 = "usb_online";
        r2.append(r3);	 Catch:{ Throwable -> 0x00d6, all -> 0x00cc }
        r3 = "|";
        r2.append(r3);	 Catch:{ Throwable -> 0x00d6, all -> 0x00cc }
        r2.append(r1);	 Catch:{ Throwable -> 0x00d6, all -> 0x00cc }
    L_0x0067:
        r0.close();	 Catch:{ Throwable -> 0x00d6, all -> 0x00cc }
        r1 = r0;
    L_0x006b:
        r0 = "\n";
        r2.append(r0);	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        r3 = "/sys/class/power_supply/battery/capacity";
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        r0 = r0.exists();	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        if (r0 == 0) goto L_0x00de;
    L_0x007d:
        r0 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        r3 = new java.io.FileReader;	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        r4 = "/sys/class/power_supply/battery/capacity";
        r3.<init>(r4);	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        r1 = r0.readLine();	 Catch:{ Throwable -> 0x00d6, all -> 0x00d1 }
        if (r1 == 0) goto L_0x009c;
    L_0x008f:
        r3 = "battery_capacity";
        r2.append(r3);	 Catch:{ Throwable -> 0x00d6, all -> 0x00d1 }
        r3 = "|";
        r2.append(r3);	 Catch:{ Throwable -> 0x00d6, all -> 0x00d1 }
        r2.append(r1);	 Catch:{ Throwable -> 0x00d6, all -> 0x00d1 }
    L_0x009c:
        r0.close();	 Catch:{ Throwable -> 0x00d6, all -> 0x00d1 }
    L_0x009f:
        if (r0 == 0) goto L_0x00a4;
    L_0x00a1:
        r0.close();	 Catch:{ IOException -> 0x00a9 }
    L_0x00a4:
        r0 = r2.toString();
        return r0;
    L_0x00a9:
        r0 = move-exception;
        com.tencent.bugly.proguard.an.a(r0);
        goto L_0x00a4;
    L_0x00ae:
        r0 = move-exception;
        r0 = r1;
    L_0x00b0:
        if (r0 == 0) goto L_0x00a4;
    L_0x00b2:
        r0.close();	 Catch:{ IOException -> 0x00b6 }
        goto L_0x00a4;
    L_0x00b6:
        r0 = move-exception;
        com.tencent.bugly.proguard.an.a(r0);
        goto L_0x00a4;
    L_0x00bb:
        r0 = move-exception;
    L_0x00bc:
        if (r1 == 0) goto L_0x00c1;
    L_0x00be:
        r1.close();	 Catch:{ IOException -> 0x00c2 }
    L_0x00c1:
        throw r0;
    L_0x00c2:
        r1 = move-exception;
        com.tencent.bugly.proguard.an.a(r1);
        goto L_0x00c1;
    L_0x00c7:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x00bc;
    L_0x00cc:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x00bc;
    L_0x00d1:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x00bc;
    L_0x00d6:
        r1 = move-exception;
        goto L_0x00b0;
    L_0x00d8:
        r0 = move-exception;
        r0 = r1;
        goto L_0x00b0;
    L_0x00db:
        r0 = move-exception;
        r0 = r1;
        goto L_0x00b0;
    L_0x00de:
        r0 = r1;
        goto L_0x009f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.k(android.content.Context):java.lang.String");
    }

    public static String l(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        String b = ap.b(context, "gsm.sim.state");
        if (b != null) {
            stringBuilder.append("gsm.sim.state");
            stringBuilder.append("|");
            stringBuilder.append(b);
        }
        stringBuilder.append("\n");
        b = ap.b(context, "gsm.sim.state2");
        if (b != null) {
            stringBuilder.append("gsm.sim.state2");
            stringBuilder.append("|");
            stringBuilder.append(b);
        }
        return stringBuilder.toString();
    }

    public static long m(Context context) {
        BufferedReader bufferedReader;
        Throwable th;
        float f = 0.0f;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/uptime"));
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    f = ((float) (System.currentTimeMillis() / 1000)) - Float.parseFloat(readLine.split(" ")[0]);
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e) {
                        an.a(e);
                    }
                }
            } catch (Throwable th2) {
                try {
                    an.a("Failed to get boot time of device.", new Object[0]);
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable e2) {
                            an.a(e2);
                        }
                    }
                    return (long) f;
                } catch (Throwable th3) {
                    th = th3;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable e22) {
                            an.a(e22);
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        return (long) f;
    }

    public static boolean n(Context context) {
        return (o(context) == null && p() == null) ? false : true;
    }

    public static String o(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < b.length; i++) {
            try {
                packageManager.getPackageInfo(b[i], 1);
                arrayList.add(Integer.valueOf(i));
            } catch (NameNotFoundException e) {
            }
        }
        return arrayList.isEmpty() ? null : arrayList.toString();
    }

    public static String p() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < c.length; i++) {
            if (0 == i) {
                if (!new File(c[i]).exists()) {
                    arrayList.add(Integer.valueOf(i));
                }
            } else if (new File(c[i]).exists()) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList.isEmpty() ? null : arrayList.toString();
    }

    public static boolean p(Context context) {
        return (((q(context) | r()) | s()) | q()) > 0;
    }

    public static int q() {
        try {
            Method method = Class.forName("android.app.ActivityManagerNative").getMethod("getDefault", new Class[0]);
            method.setAccessible(true);
            if (method.invoke(null, new Object[0]).getClass().getName().startsWith("$Proxy")) {
                return 256;
            }
            return 0;
        } catch (Exception e) {
            return 256;
        }
    }

    public static int q(Context context) {
        int i = 0;
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getInstallerPackageName("de.robv.android.xposed.installer");
            i = 1;
        } catch (Exception e) {
        }
        try {
            packageManager.getInstallerPackageName("com.saurik.substrate");
            return i | 2;
        } catch (Exception e2) {
            return i;
        }
    }

    public static int r() {
        int i = 0;
        try {
            throw new Exception("detect hook");
        } catch (Exception e) {
            int i2 = 0;
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("main")) {
                    i2 |= 4;
                }
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("handleHookedMethod")) {
                    i2 |= 8;
                }
                if (stackTraceElement.getClassName().equals("com.saurik.substrate.MS$2") && stackTraceElement.getMethodName().equals("invoked")) {
                    i2 |= 16;
                }
                if (stackTraceElement.getClassName().equals("com.android.internal.os.ZygoteInit")) {
                    i++;
                    if (i == 2) {
                        i2 |= 32;
                    }
                }
            }
            return i2;
        }
    }

    public static int s() {
        BufferedReader bufferedReader;
        UnsupportedEncodingException unsupportedEncodingException;
        int i;
        BufferedReader bufferedReader2;
        FileNotFoundException fileNotFoundException;
        IOException iOException;
        Throwable th;
        int i2 = 0;
        try {
            HashSet hashSet = new HashSet();
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/maps"), "utf-8"));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (readLine.endsWith(".so") || readLine.endsWith(".jar")) {
                        hashSet.add(readLine.substring(readLine.lastIndexOf(" ") + 1));
                    }
                } catch (UnsupportedEncodingException e) {
                    unsupportedEncodingException = e;
                    i = 0;
                    bufferedReader2 = bufferedReader;
                } catch (FileNotFoundException e2) {
                    fileNotFoundException = e2;
                    i = 0;
                } catch (IOException e3) {
                    iOException = e3;
                    i = 0;
                }
            }
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (((String) next).toLowerCase().contains("xposed")) {
                    i = i2 | 64;
                } else {
                    i = i2;
                }
                try {
                    if (((String) next).contains("com.saurik.substrate")) {
                        i |= 128;
                    }
                    i2 = i;
                } catch (UnsupportedEncodingException e4) {
                    unsupportedEncodingException = e4;
                    bufferedReader2 = bufferedReader;
                } catch (FileNotFoundException e5) {
                    fileNotFoundException = e5;
                } catch (IOException e6) {
                    iOException = e6;
                }
            }
            if (bufferedReader == null) {
                return i2;
            }
            try {
                bufferedReader.close();
                return i2;
            } catch (IOException e32) {
                e32.printStackTrace();
                return i2;
            }
        } catch (UnsupportedEncodingException e7) {
            UnsupportedEncodingException unsupportedEncodingException2 = e7;
            i = 0;
            bufferedReader2 = null;
            unsupportedEncodingException = unsupportedEncodingException2;
            try {
                unsupportedEncodingException.printStackTrace();
                if (bufferedReader2 == null) {
                    return i;
                }
                try {
                    bufferedReader2.close();
                    return i;
                } catch (IOException iOException2) {
                    iOException2.printStackTrace();
                    return i;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException iOException22) {
                        iOException22.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e22) {
            bufferedReader = null;
            fileNotFoundException = e22;
            i = 0;
            try {
                fileNotFoundException.printStackTrace();
                if (bufferedReader == null) {
                    return i;
                }
                try {
                    bufferedReader.close();
                    return i;
                } catch (IOException iOException222) {
                    iOException222.printStackTrace();
                    return i;
                }
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        } catch (IOException e322) {
            bufferedReader = null;
            iOException222 = e322;
            i = 0;
            iOException222.printStackTrace();
            if (bufferedReader == null) {
                return i;
            }
            try {
                bufferedReader.close();
                return i;
            } catch (IOException iOException2222) {
                iOException2222.printStackTrace();
                return i;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }
}
