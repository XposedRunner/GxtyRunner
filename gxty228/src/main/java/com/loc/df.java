package com.loc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Xml;
import android.view.WindowManager;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: DeviceInfo */
public final class df {
    static String a = "";
    static String b = "";
    public static boolean c = false;
    static String d = "";
    static boolean e = false;
    static int f = -1;
    static String g = "";
    static String h = "";
    private static String i = null;
    private static boolean j = false;
    private static String k = "";
    private static String l = "";
    private static String m = "";
    private static String n = "";
    private static String o = "";
    private static boolean p = false;
    private static String q = "";

    private static int A(Context context) {
        if (context == null || !a(context, dl.c("AYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19ORVRXT1JLX1NUQVRF"))) {
            return -1;
        }
        ConnectivityManager B = B(context);
        if (B == null) {
            return -1;
        }
        NetworkInfo activeNetworkInfo = B.getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.getType() : -1;
    }

    private static ConnectivityManager B(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    private static int C(Context context) {
        if (!a(context, dl.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return -1;
        }
        TelephonyManager D = D(context);
        return D != null ? D.getNetworkType() : -1;
    }

    private static TelephonyManager D(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    public static String a() {
        return i;
    }

    public static String a(Context context) {
        try {
            if (!TextUtils.isEmpty(d)) {
                return d;
            }
            dk a = e.a();
            if (x.b(context, a)) {
                Class a2 = x.a(context, a, dl.c("WY29tLmFtYXAuYXBpLmFpdW5ldC5OZXRSZXVlc3RQYXJhbQ"));
                if (a2 != null) {
                    d = (String) a2.getMethod("getAdiuExtras", new Class[0]).invoke(a2, new Object[0]);
                }
                return d;
            }
            return "";
        } catch (Throwable th) {
            j.b(th, "dI", "aiuEx");
        }
    }

    private static List<ScanResult> a(List<ScanResult> list) {
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            for (int i2 = 1; i2 < size - i; i2++) {
                if (((ScanResult) list.get(i2 - 1)).level > ((ScanResult) list.get(i2)).level) {
                    ScanResult scanResult = (ScanResult) list.get(i2 - 1);
                    list.set(i2 - 1, list.get(i2));
                    list.set(i2, scanResult);
                }
            }
        }
        return list;
    }

    public static void a(String str) {
        i = str;
    }

    private static boolean a(Context context, String str) {
        return context != null && context.checkCallingOrSelfPermission(str) == 0;
    }

    public static String b(final Context context) {
        try {
            if (!TextUtils.isEmpty(b)) {
                return b;
            }
            dk a = e.a();
            if (a == null) {
                return null;
            }
            if (x.b(context, a)) {
                final Class a2 = x.a(context, a, dl.c("WY29tLmFtYXAuYXBpLmFpdW5ldC5OZXRSZXVlc3RQYXJhbQ"));
                if (a2 == null) {
                    return b;
                }
                String str = (String) a2.getMethod("getADIU", new Class[]{Context.class}).invoke(a2, new Object[]{context});
                if (!TextUtils.isEmpty(str)) {
                    b = str;
                    return str;
                } else if (!j) {
                    j = true;
                    j.d().submit(new Runnable() {
                        public final void run() {
                            try {
                                Map map = (Map) a2.getMethod("getGetParams", new Class[0]).invoke(a2, new Object[0]);
                                if (map != null) {
                                    String str = (String) a2.getMethod("getPostParam", new Class[]{String.class, String.class, String.class, String.class}).invoke(a2, new Object[]{df.h(context), df.u(context), df.l(context), df.v(context)});
                                    if (!TextUtils.isEmpty(str)) {
                                        an.a();
                                        byte[] a = an.a(new am(str.getBytes(), map));
                                        a2.getMethod("parseResult", new Class[]{Context.class, String.class}).invoke(a2, new Object[]{context, new String(a)});
                                    }
                                }
                            } catch (Throwable th) {
                                j.b(th, "dI", "aiun");
                            }
                        }
                    });
                }
            }
            return "";
        } catch (Throwable th) {
            j.b(th, "dI", "aiu");
        }
    }

    public static void b() {
        try {
            if (VERSION.SDK_INT > 14) {
                TrafficStats.class.getDeclaredMethod("setThreadStatsTag", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(40964)});
            }
        } catch (Throwable th) {
            j.b(th, "dI", "sag");
        }
    }

    public static String c() {
        if (!TextUtils.isEmpty(l)) {
            return l;
        }
        try {
            if (VERSION.SDK_INT >= 26) {
                return (String) dl.a(Build.class, "MZ2V0U2VyaWFs", new Class[0]).invoke(Build.class, new Object[0]);
            }
            if (VERSION.SDK_INT >= 9) {
                l = Build.SERIAL;
            }
            return l == null ? "" : l;
        } catch (Throwable th) {
        }
    }

    public static String c(Context context) {
        try {
            return z(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static String d() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] bArr = null;
                    if (VERSION.SDK_INT >= 9) {
                        bArr = networkInterface.getHardwareAddress();
                    }
                    if (bArr == null) {
                        return "";
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    for (byte b : bArr) {
                        String toUpperCase = Integer.toHexString(b & 255).toUpperCase();
                        if (toUpperCase.length() == 1) {
                            stringBuilder.append("0");
                        }
                        stringBuilder.append(toUpperCase).append(":");
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    return stringBuilder.toString();
                }
            }
        } catch (Throwable e) {
            j.b(e, "dI", "gMr");
        }
        return "";
    }

    public static String d(Context context) {
        String str = "";
        try {
            String str2 = "";
            String v = v(context);
            return (v == null || v.length() < 5) ? str2 : v.substring(3, 5);
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }

    public static int e(Context context) {
        try {
            return C(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static int f(Context context) {
        try {
            return A(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static String g(Context context) {
        try {
            return y(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String h(Context context) {
        try {
            if (a != null && !"".equals(a)) {
                return a;
            }
            if (a(context, "android.permission.WRITE_SETTINGS")) {
                a = System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
            }
            if (!(a == null || "".equals(a))) {
                return a;
            }
            try {
                a = x(context);
            } catch (Throwable th) {
            }
            return a == null ? "" : a;
        } catch (Throwable th2) {
        }
    }

    public static String i(Context context) {
        if (!TextUtils.isEmpty(k)) {
            return k;
        }
        try {
            String string = Secure.getString(context.getContentResolver(), dl.c(new String(e.a(13))));
            k = string;
            return string == null ? "" : k;
        } catch (Throwable th) {
            return k;
        }
    }

    static String j(Context context) {
        String str = "";
        if (context == null) {
            return str;
        }
        try {
            if (!a(context, dl.c("WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF"))) {
                return str;
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI);
            if (wifiManager == null) {
                return str;
            }
            String bssid;
            if (wifiManager.isWifiEnabled()) {
                bssid = wifiManager.getConnectionInfo().getBSSID();
                return bssid;
            }
            bssid = str;
            return bssid;
        } catch (Throwable th) {
            g.a(th, "dI", "gcW");
        }
    }

    static String k(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        if (context != null) {
            try {
                if (a(context, dl.c("WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF"))) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI);
                    if (wifiManager == null) {
                        return "";
                    }
                    if (wifiManager.isWifiEnabled()) {
                        List scanResults = wifiManager.getScanResults();
                        if (scanResults == null || scanResults.size() == 0) {
                            return stringBuilder.toString();
                        }
                        List a = a(scanResults);
                        Object obj = 1;
                        int i = 0;
                        while (i < a.size() && i < 7) {
                            ScanResult scanResult = (ScanResult) a.get(i);
                            if (obj != null) {
                                obj = null;
                            } else {
                                stringBuilder.append(";");
                            }
                            stringBuilder.append(scanResult.BSSID);
                            i++;
                        }
                    }
                    return stringBuilder.toString();
                }
            } catch (Throwable th) {
                j.b(th, "dI", "gWs");
            }
        }
        return stringBuilder.toString();
    }

    public static String l(Context context) {
        try {
            if (m != null && !"".equals(m)) {
                return m;
            }
            if (!a(context, dl.c("WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF"))) {
                return m;
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI);
            if (wifiManager == null) {
                return "";
            }
            m = wifiManager.getConnectionInfo().getMacAddress();
            if (dl.c("YMDI6MDA6MDA6MDA6MDA6MDA").equals(m) || dl.c("YMDA6MDA6MDA6MDA6MDA6MDA").equals(m)) {
                m = d();
            }
            return m;
        } catch (Throwable th) {
            j.b(th, "dI", "gDc");
        }
    }

    static String[] m(Context context) {
        try {
            if (a(context, dl.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU=")) && a(context, dl.c("EYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19DT0FSU0VfTE9DQVRJT04="))) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager == null) {
                    return new String[]{"", ""};
                }
                CellLocation cellLocation = telephonyManager.getCellLocation();
                int cid;
                int lac;
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    cid = gsmCellLocation.getCid();
                    lac = gsmCellLocation.getLac();
                    return new String[]{lac + "||" + cid, "gsm"};
                }
                if (cellLocation instanceof CdmaCellLocation) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                    cid = cdmaCellLocation.getSystemId();
                    lac = cdmaCellLocation.getNetworkId();
                    int baseStationId = cdmaCellLocation.getBaseStationId();
                    return new String[]{cid + "||" + lac + "||" + baseStationId, "cdma"};
                }
                return new String[]{"", ""};
            }
            return new String[]{"", ""};
        } catch (Throwable th) {
            g.a(th, "dI", "cf");
        }
    }

    static String n(Context context) {
        String str = "";
        try {
            if (!a(context, dl.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
                return str;
            }
            TelephonyManager D = D(context);
            if (D == null) {
                return "";
            }
            String networkOperator = D.getNetworkOperator();
            return (TextUtils.isEmpty(networkOperator) || networkOperator.length() < 3) ? str : networkOperator.substring(0, 3);
        } catch (Throwable th) {
            j.b(th, "dI1", "gNC");
            return str;
        }
    }

    static String o(Context context) {
        String str = "";
        try {
            if (!a(context, dl.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
                return str;
            }
            TelephonyManager D = D(context);
            if (D == null) {
                return "";
            }
            String networkOperator = D.getNetworkOperator();
            return (TextUtils.isEmpty(networkOperator) || networkOperator.length() < 3) ? str : networkOperator.substring(3);
        } catch (Throwable th) {
            j.b(th, "dI", "gNC");
            return str;
        }
    }

    public static int p(Context context) {
        try {
            return C(context);
        } catch (Throwable th) {
            j.b(th, "dI", "gNT");
            return -1;
        }
    }

    public static int q(Context context) {
        try {
            return A(context);
        } catch (Throwable th) {
            g.a(th, "dI", "gAT");
            return -1;
        }
    }

    public static NetworkInfo r(Context context) {
        if (!a(context, dl.c("AYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19ORVRXT1JLX1NUQVRF"))) {
            return null;
        }
        ConnectivityManager B = B(context);
        return B != null ? B.getActiveNetworkInfo() : null;
    }

    static String s(Context context) {
        String str = null;
        try {
            NetworkInfo r = r(context);
            if (r != null) {
                str = r.getExtraInfo();
            }
        } catch (Throwable th) {
            j.b(th, "dI", "gne");
        }
        return str;
    }

    static String t(Context context) {
        try {
            if (n != null && !"".equals(n)) {
                return n;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return "";
            }
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            n = i2 > i ? i + "*" + i2 : i2 + "*" + i;
            return n;
        } catch (Throwable th) {
            j.b(th, "dI", "gR");
        }
    }

    public static String u(Context context) {
        try {
            if (o != null && !"".equals(o)) {
                return o;
            }
            if (!a(context, dl.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
                return o;
            }
            TelephonyManager D = D(context);
            if (D == null) {
                return "";
            }
            Method a = dl.a(D.getClass(), "QZ2V0RGV2aWNlSWQ", new Class[0]);
            if (a != null) {
                o = (String) a.invoke(D, new Object[0]);
            }
            if (o == null) {
                o = "";
            }
            return o;
        } catch (Throwable th) {
            j.b(th, "dI", "gd");
        }
    }

    public static String v(Context context) {
        String str = "";
        try {
            str = y(context);
        } catch (Throwable th) {
            g.a(th, "dI", "gSd");
        }
        return str;
    }

    static String w(Context context) {
        try {
            return z(context);
        } catch (Throwable th) {
            j.b(th, "dI", "gNNa");
            return "";
        }
    }

    private static String x(Context context) {
        Throwable th;
        InputStream inputStream = null;
        InputStream fileInputStream;
        try {
            if (dl.a(context, "android.permission.READ_EXTERNAL_STORAGE") && "mounted".equals(Environment.getExternalStorageState())) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                XmlPullParser newPullParser = Xml.newPullParser();
                fileInputStream = new FileInputStream(file);
                try {
                    newPullParser.setInput(fileInputStream, "utf-8");
                    Object obj = null;
                    for (int eventType = newPullParser.getEventType(); 1 != eventType; eventType = newPullParser.next()) {
                        switch (eventType) {
                            case 2:
                                if (newPullParser.getAttributeCount() <= 0) {
                                    break;
                                }
                                int attributeCount = newPullParser.getAttributeCount();
                                for (eventType = 0; eventType < attributeCount; eventType++) {
                                    String attributeValue = newPullParser.getAttributeValue(eventType);
                                    if ("UTDID2".equals(attributeValue) || "UTDID".equals(attributeValue)) {
                                        obj = 1;
                                    }
                                }
                                break;
                                break;
                            case 3:
                                obj = null;
                                break;
                            case 4:
                                if (obj == null) {
                                    break;
                                }
                                String text = newPullParser.getText();
                                if (fileInputStream == null) {
                                    return text;
                                }
                                try {
                                    fileInputStream.close();
                                    return text;
                                } catch (Throwable th2) {
                                    return text;
                                }
                            default:
                                break;
                        }
                    }
                    inputStream = fileInputStream;
                } catch (Throwable th3) {
                    th = th3;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th4) {
                        }
                    }
                    throw th;
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Throwable th5) {
            fileInputStream = null;
            th = th5;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return "";
    }

    private static String y(Context context) throws InvocationTargetException, IllegalAccessException {
        if (q != null && !"".equals(q)) {
            return q;
        }
        if (!a(context, dl.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return q;
        }
        TelephonyManager D = D(context);
        if (D == null) {
            return "";
        }
        Method a = dl.a(D.getClass(), "UZ2V0U3Vic2NyaWJlcklk", new Class[0]);
        if (a != null) {
            q = (String) a.invoke(D, new Object[0]);
        }
        if (q == null) {
            q = "";
        }
        return q;
    }

    private static String z(Context context) {
        if (!a(context, dl.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return null;
        }
        TelephonyManager D = D(context);
        if (D == null) {
            return "";
        }
        Object simOperatorName = D.getSimOperatorName();
        return TextUtils.isEmpty(simOperatorName) ? D.getNetworkOperatorName() : simOperatorName;
    }
}
