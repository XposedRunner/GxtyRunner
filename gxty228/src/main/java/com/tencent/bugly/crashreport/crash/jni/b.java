package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import cn.jiguang.net.HttpUtils;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: BUGLY */
public class b {
    private static List<File> a = new ArrayList();

    protected static Map<String, Integer> a(String str) {
        if (str == null) {
            return null;
        }
        try {
            Map<String, Integer> hashMap = new HashMap();
            for (String split : str.split(",")) {
                String[] split2 = split.split(":");
                if (split2.length != 2) {
                    an.e("error format at %s", split);
                    return null;
                }
                hashMap.put(split2[0], Integer.valueOf(Integer.parseInt(split2[1])));
            }
            return hashMap;
        } catch (Exception e) {
            an.e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }

    protected static String b(String str) {
        if (str == null) {
            return "";
        }
        String[] split = str.split("\n");
        if (split == null || split.length == 0) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : split) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                stringBuilder.append(str2).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    protected static CrashDetailBean a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        if (map == null) {
            return null;
        }
        if (a.a(context) == null) {
            an.e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str = (String) map.get("intStateStr");
        if (str == null || str.trim().length() <= 0) {
            an.e("no intStateStr", new Object[0]);
            return null;
        }
        Map a = a(str);
        if (a == null) {
            an.e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            ((Integer) a.get("sino")).intValue();
            ((Integer) a.get("sud")).intValue();
            String str2 = (String) map.get("soVersion");
            if (str2 == null) {
                an.e("error format at version", new Object[0]);
                return null;
            }
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            str = (String) map.get("errorAddr");
            String str9 = str == null ? "unknown" : str;
            str = (String) map.get("codeMsg");
            if (str == null) {
                str3 = "unknown";
            } else {
                str3 = str;
            }
            str = (String) map.get("tombPath");
            if (str == null) {
                str4 = "unknown";
            } else {
                str4 = str;
            }
            str = (String) map.get("signalName");
            if (str == null) {
                str5 = "unknown";
            } else {
                str5 = str;
            }
            str = (String) map.get("errnoMsg");
            str = (String) map.get("stack");
            if (str == null) {
                str6 = "unknown";
            } else {
                str6 = str;
            }
            str = (String) map.get("jstack");
            if (str != null) {
                str6 = str6 + "java:\n" + str;
            }
            Integer num = (Integer) a.get("sico");
            String str10 = "UNKNOWN";
            if (num != null && num.intValue() > 0) {
                str5 = str5 + "(" + str3 + ")";
                str3 = "KERNEL";
            }
            str = (String) map.get("nativeLog");
            byte[] bArr = null;
            if (!(str == null || str.isEmpty())) {
                bArr = ap.a(null, str, "BuglyNativeLog.txt");
            }
            str = (String) map.get("sendingProcess");
            if (str == null) {
                str7 = "unknown";
            } else {
                str7 = str;
            }
            num = (Integer) a.get("spd");
            if (num != null) {
                str7 = str7 + "(" + num + ")";
            }
            str = (String) map.get("threadName");
            if (str == null) {
                str8 = "unknown";
            } else {
                str8 = str;
            }
            num = (Integer) a.get("et");
            if (num != null) {
                str8 = str8 + "(" + num + ")";
            }
            str = (String) map.get("processName");
            if (str == null) {
                str10 = "unknown";
            } else {
                str10 = str;
            }
            num = (Integer) a.get("ep");
            if (num != null) {
                str10 = str10 + "(" + num + ")";
            }
            Map map2 = null;
            str = (String) map.get("key-value");
            if (str != null) {
                map2 = new HashMap();
                for (String split : str.split("\n")) {
                    String[] split2 = split.split(HttpUtils.EQUAL_SIGN);
                    if (split2.length == 2) {
                        map2.put(split2[0], split2[1]);
                    }
                }
            }
            CrashDetailBean packageCrashDatas = nativeExceptionHandler.packageCrashDatas(str10, str8, (((long) ((Integer) a.get("etms")).intValue()) / 1000) + (((long) ((Integer) a.get("ets")).intValue()) * 1000), str5, str9, b(str6), str3, str7, str4, (String) map.get("sysLogPath"), (String) map.get("jniLogPath"), str2, bArr, map2, false, false);
            if (packageCrashDatas != null) {
                str = (String) map.get("userId");
                if (str != null) {
                    an.c("[Native record info] userId: %s", str);
                    packageCrashDatas.m = str;
                }
                str = (String) map.get("sysLog");
                if (str != null) {
                    packageCrashDatas.w = str;
                }
                str = (String) map.get("appVersion");
                if (str != null) {
                    an.c("[Native record info] appVersion: %s", str);
                    packageCrashDatas.f = str;
                }
                str = (String) map.get("isAppForeground");
                if (str != null) {
                    an.c("[Native record info] isAppForeground: %s", str);
                    packageCrashDatas.O = str.equalsIgnoreCase("true");
                }
                str = (String) map.get("launchTime");
                if (str != null) {
                    an.c("[Native record info] launchTime: %s", str);
                    packageCrashDatas.N = Long.parseLong(str);
                }
                packageCrashDatas.z = null;
                packageCrashDatas.k = true;
            }
            return packageCrashDatas;
        } catch (Throwable e) {
            if (!an.a(e)) {
                e.printStackTrace();
            }
        } catch (Throwable e2) {
            an.e("error format", new Object[0]);
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static java.lang.String a(java.io.BufferedInputStream r5) throws java.io.IOException {
        /*
        r0 = 0;
        if (r5 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ Throwable -> 0x0045, all -> 0x003a }
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2.<init>(r1);	 Catch:{ Throwable -> 0x0045, all -> 0x003a }
    L_0x000b:
        r1 = r5.read();	 Catch:{ Throwable -> 0x002a }
        r3 = -1;
        if (r1 == r3) goto L_0x0034;
    L_0x0012:
        if (r1 != 0) goto L_0x0026;
    L_0x0014:
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x002a }
        r3 = r2.toByteArray();	 Catch:{ Throwable -> 0x002a }
        r4 = "UTf-8";
        r1.<init>(r3, r4);	 Catch:{ Throwable -> 0x002a }
        if (r2 == 0) goto L_0x0024;
    L_0x0021:
        r2.close();
    L_0x0024:
        r0 = r1;
        goto L_0x0003;
    L_0x0026:
        r2.write(r1);	 Catch:{ Throwable -> 0x002a }
        goto L_0x000b;
    L_0x002a:
        r1 = move-exception;
    L_0x002b:
        com.tencent.bugly.proguard.an.a(r1);	 Catch:{ all -> 0x0043 }
        if (r2 == 0) goto L_0x0003;
    L_0x0030:
        r2.close();
        goto L_0x0003;
    L_0x0034:
        if (r2 == 0) goto L_0x0003;
    L_0x0036:
        r2.close();
        goto L_0x0003;
    L_0x003a:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x003d:
        if (r2 == 0) goto L_0x0042;
    L_0x003f:
        r2.close();
    L_0x0042:
        throw r0;
    L_0x0043:
        r0 = move-exception;
        goto L_0x003d;
    L_0x0045:
        r1 = move-exception;
        r2 = r0;
        goto L_0x002b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.b.a(java.io.BufferedInputStream):java.lang.String");
    }

    public static CrashDetailBean a(Context context, String str, NativeExceptionHandler nativeExceptionHandler) {
        BufferedInputStream bufferedInputStream;
        IOException e;
        Throwable th;
        CrashDetailBean crashDetailBean = null;
        if (context == null || str == null || nativeExceptionHandler == null) {
            an.e("get eup record file args error", new Object[0]);
        } else {
            File file = new File(str, "rqd_record.eup");
            if (file.exists() && file.canRead()) {
                try {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    try {
                        String a = a(bufferedInputStream);
                        if (a == null || !a.equals("NATIVE_RQD_REPORT")) {
                            an.e("record read fail! %s", a);
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        } else {
                            Map hashMap = new HashMap();
                            Object obj = crashDetailBean;
                            while (true) {
                                String a2 = a(bufferedInputStream);
                                if (a2 == null) {
                                    break;
                                } else if (obj == null) {
                                    obj = a2;
                                } else {
                                    hashMap.put(obj, a2);
                                    obj = crashDetailBean;
                                }
                            }
                            if (obj != null) {
                                an.e("record not pair! drop! %s", obj);
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e22) {
                                        e22.printStackTrace();
                                    }
                                }
                            } else {
                                crashDetailBean = a(context, hashMap, nativeExceptionHandler);
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e222) {
                                        e222.printStackTrace();
                                    }
                                }
                            }
                        }
                    } catch (IOException e3) {
                        e222 = e3;
                        try {
                            e222.printStackTrace();
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e2222) {
                                    e2222.printStackTrace();
                                }
                            }
                            return crashDetailBean;
                        } catch (Throwable th2) {
                            th = th2;
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e22222) {
                                    e22222.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (IOException e4) {
                    e22222 = e4;
                    bufferedInputStream = crashDetailBean;
                    e22222.printStackTrace();
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    return crashDetailBean;
                } catch (Throwable th3) {
                    bufferedInputStream = crashDetailBean;
                    th = th3;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    throw th;
                }
            }
        }
        return crashDetailBean;
    }

    private static String b(String str, String str2) {
        String str3 = null;
        BufferedReader a = ap.a(str, "reg_record.txt");
        if (a != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                String readLine = a.readLine();
                if (readLine != null && readLine.startsWith(str2)) {
                    String str4 = "                ";
                    int i = 0;
                    int i2 = 18;
                    int i3 = 0;
                    while (true) {
                        String readLine2 = a.readLine();
                        if (readLine2 == null) {
                            break;
                        }
                        if (i3 % 4 == 0) {
                            if (i3 > 0) {
                                stringBuilder.append("\n");
                            }
                            stringBuilder.append("  ");
                        } else {
                            if (readLine2.length() > 16) {
                                i2 = 28;
                            }
                            stringBuilder.append(str4.substring(0, i2 - i));
                        }
                        i = readLine2.length();
                        stringBuilder.append(readLine2);
                        i3++;
                    }
                    stringBuilder.append("\n");
                    str3 = stringBuilder.toString();
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable e) {
                            an.a(e);
                        }
                    }
                } else if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable e2) {
                        an.a(e2);
                    }
                }
            } catch (Throwable th) {
                if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable e22) {
                        an.a(e22);
                    }
                }
            }
        }
        return str3;
    }

    private static String c(String str, String str2) {
        String str3 = null;
        BufferedReader a = ap.a(str, "map_record.txt");
        if (a != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                String readLine = a.readLine();
                if (readLine != null && readLine.startsWith(str2)) {
                    while (true) {
                        readLine = a.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append("  ");
                        stringBuilder.append(readLine);
                        stringBuilder.append("\n");
                    }
                    str3 = stringBuilder.toString();
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable e) {
                            an.a(e);
                        }
                    }
                } else if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable e2) {
                        an.a(e2);
                    }
                }
            } catch (Throwable th) {
                if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable e22) {
                        an.a(e22);
                    }
                }
            }
        }
        return str3;
    }

    public static String a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String b = b(str, str2);
        if (!(b == null || b.isEmpty())) {
            stringBuilder.append("Register infos:\n");
            stringBuilder.append(b);
        }
        b = c(str, str2);
        if (!(b == null || b.isEmpty())) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("\n");
            }
            stringBuilder.append("System SO infos:\n");
            stringBuilder.append(b);
        }
        return stringBuilder.toString();
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str, "backup_record.txt");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public static void a(boolean z, String str) {
        File file;
        if (str != null) {
            a.add(new File(str, "rqd_record.eup"));
            a.add(new File(str, "reg_record.txt"));
            a.add(new File(str, "map_record.txt"));
            a.add(new File(str, "backup_record.txt"));
            if (z) {
                try {
                    file = new File(str);
                    if (file.canRead() && file.isDirectory()) {
                        File[] listFiles = file.listFiles();
                        if (listFiles != null) {
                            for (File file2 : listFiles) {
                                if (file2.canRead() && file2.canWrite() && file2.length() == 0) {
                                    a.add(file2);
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    an.a(th);
                }
            }
        }
        if (a != null && a.size() > 0) {
            for (File file3 : a) {
                if (file3.exists() && file3.canWrite()) {
                    file3.delete();
                    an.c("Delete record file %s", file3.getAbsoluteFile());
                }
            }
        }
    }

    public static String a(String str, int i, String str2, boolean z) {
        Throwable th;
        String stringBuilder;
        Throwable th2;
        if (str == null || i <= 0) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() || !file.canRead()) {
            return null;
        }
        an.a("Read system log from native record file(length: %s bytes): %s", Long.valueOf(file.length()), file.getAbsolutePath());
        a.add(file);
        an.c("Add this record file to list for cleaning lastly.", new Object[0]);
        if (str2 == null) {
            return ap.a(new File(str), i, z);
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            while (true) {
                Object readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                if (Pattern.compile(str2 + "[ ]*:").matcher(readLine).find()) {
                    stringBuilder2.append(readLine).append("\n");
                }
                if (i > 0 && stringBuilder2.length() > i) {
                    if (z) {
                        break;
                    }
                    try {
                        stringBuilder2.delete(0, stringBuilder2.length() - i);
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
            }
            stringBuilder2.delete(i, stringBuilder2.length());
            stringBuilder = stringBuilder2.toString();
            if (bufferedReader == null) {
                return stringBuilder;
            }
            try {
                bufferedReader.close();
                return stringBuilder;
            } catch (Throwable e) {
                an.a(e);
                return stringBuilder;
            }
        } catch (Throwable e2) {
            th2 = e2;
            bufferedReader = null;
            th = th2;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }
}
