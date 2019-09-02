package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import cn.jiguang.net.HttpUtils;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.b;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ao;
import com.tencent.bugly.proguard.ap;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
public class a implements NativeExceptionHandler {
    private final Context a;
    private final b b;
    private final com.tencent.bugly.crashreport.common.info.a c;
    private final com.tencent.bugly.crashreport.common.strategy.a d;

    public a(Context context, com.tencent.bugly.crashreport.common.info.a aVar, b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    public CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, byte[] bArr, Map<String, String> map, boolean z, boolean z2) {
        boolean l = c.a().l();
        if (l) {
            an.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.b = 1;
        crashDetailBean.e = this.c.h();
        crashDetailBean.f = this.c.o;
        crashDetailBean.g = this.c.w();
        crashDetailBean.m = this.c.g();
        crashDetailBean.n = str3;
        crashDetailBean.o = l ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        crashDetailBean.p = str4;
        if (str5 == null) {
            str5 = "";
        }
        crashDetailBean.q = str5;
        crashDetailBean.r = j;
        crashDetailBean.u = ap.b(crashDetailBean.q.getBytes());
        crashDetailBean.A = str;
        crashDetailBean.B = str2;
        crashDetailBean.I = this.c.y();
        crashDetailBean.h = this.c.v();
        crashDetailBean.i = this.c.J();
        crashDetailBean.v = str8;
        String str12 = null;
        NativeCrashHandler instance = NativeCrashHandler.getInstance();
        if (instance != null) {
            str12 = instance.getDumpFilePath();
        }
        String a = b.a(str12, str8);
        if (!ap.a(a)) {
            crashDetailBean.V = a;
        }
        crashDetailBean.W = b.c(str12);
        crashDetailBean.w = b.a(str9, c.e, c.h, c.m);
        crashDetailBean.x = b.a(str10, c.e, null, true);
        crashDetailBean.K = str7;
        crashDetailBean.L = str6;
        crashDetailBean.M = str11;
        crashDetailBean.F = this.c.p();
        crashDetailBean.G = this.c.o();
        crashDetailBean.H = this.c.q();
        if (z) {
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.i();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.g();
            crashDetailBean.E = com.tencent.bugly.crashreport.common.info.b.k();
            if (crashDetailBean.w == null) {
                crashDetailBean.w = ap.a(this.a, c.e, c.h);
            }
            crashDetailBean.y = ao.a();
            crashDetailBean.N = this.c.a;
            crashDetailBean.O = this.c.a();
            crashDetailBean.Q = this.c.H();
            crashDetailBean.R = this.c.I();
            crashDetailBean.S = this.c.B();
            crashDetailBean.T = this.c.G();
            crashDetailBean.z = ap.a(c.f, false);
            str12 = "java:\n";
            int indexOf = crashDetailBean.q.indexOf(str12);
            if (indexOf > 0) {
                indexOf += str12.length();
                if (indexOf < crashDetailBean.q.length()) {
                    String substring = crashDetailBean.q.substring(indexOf, crashDetailBean.q.length() - 1);
                    if (substring.length() > 0 && crashDetailBean.z.containsKey(crashDetailBean.B)) {
                        str12 = (String) crashDetailBean.z.get(crashDetailBean.B);
                        int indexOf2 = str12.indexOf(substring);
                        if (indexOf2 > 0) {
                            str12 = str12.substring(indexOf2);
                            crashDetailBean.z.put(crashDetailBean.B, str12);
                            crashDetailBean.q = crashDetailBean.q.substring(0, indexOf);
                            crashDetailBean.q += str12;
                        }
                    }
                }
            }
            if (str == null) {
                crashDetailBean.A = this.c.e;
            }
            this.b.c(crashDetailBean);
        } else {
            crashDetailBean.C = -1;
            crashDetailBean.D = -1;
            crashDetailBean.E = -1;
            if (crashDetailBean.w == null) {
                crashDetailBean.w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            }
            crashDetailBean.N = -1;
            crashDetailBean.Q = -1;
            crashDetailBean.R = -1;
            crashDetailBean.S = map;
            crashDetailBean.T = this.c.G();
            crashDetailBean.z = null;
            if (str == null) {
                crashDetailBean.A = "unknown(record)";
            }
            if (bArr != null) {
                crashDetailBean.y = bArr;
            }
        }
        return crashDetailBean;
    }

    public void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        an.a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, null);
    }

    public void handleNativeException2(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7, String[] strArr) {
        an.a("Native Crash Happen v2", new Object[0]);
        try {
            String str8;
            String str9;
            String str10;
            String str11;
            String b = b.b(str3);
            String str12 = "UNKNOWN";
            if (i3 > 0) {
                str8 = "KERNEL";
                str9 = str + "(" + str5 + ")";
            } else {
                if (i4 > 0) {
                    str12 = AppInfo.a(this.a, i4);
                }
                if (str12.equals(String.valueOf(i4))) {
                    str8 = str5;
                    str9 = str;
                } else {
                    str12 = str12 + "(" + i4 + ")";
                    str8 = str5;
                    str9 = str;
                }
            }
            Map hashMap = new HashMap();
            if (strArr != null) {
                for (String str13 : strArr) {
                    if (str13 != null) {
                        an.a("Extra message[%d]: %s", Integer.valueOf(r2), str13);
                        String[] split = str13.split(HttpUtils.EQUAL_SIGN);
                        if (split.length == 2) {
                            hashMap.put(split[0], split[1]);
                        } else {
                            an.d("bad extraMsg %s", str13);
                        }
                    }
                }
            } else {
                an.c("not found extraMsg", new Object[0]);
            }
            boolean z = false;
            String str14 = (String) hashMap.get("HasPendingException");
            if (str14 != null && str14.equals("true")) {
                an.a("Native crash happened with a Java pending exception.", new Object[0]);
                z = true;
            }
            str14 = (String) hashMap.get("ExceptionProcessName");
            if (str14 == null || str14.length() == 0) {
                str10 = this.c.e;
            } else {
                an.c("Name of crash process: %s", str14);
                str10 = str14;
            }
            str14 = (String) hashMap.get("ExceptionThreadName");
            if (str14 == null || str14.length() == 0) {
                Thread currentThread = Thread.currentThread();
                str11 = currentThread.getName() + "(" + currentThread.getId() + ")";
            } else {
                Object obj;
                an.c("Name of crash thread: %s", str14);
                for (Thread thread : Thread.getAllStackTraces().keySet()) {
                    if (thread.getName().equals(str14)) {
                        str11 = str14 + "(" + thread.getId() + ")";
                        obj = 1;
                        break;
                    }
                }
                str11 = str14;
                obj = null;
                if (obj == null) {
                    str11 = str11 + "(" + i2 + ")";
                }
            }
            long j3 = (j2 / 1000) + (1000 * j);
            String str15 = (String) hashMap.get("SysLogPath");
            String str16 = (String) hashMap.get("JniLogPath");
            if (!this.d.b()) {
                an.d("no remote but still store!", new Object[0]);
            }
            if (this.d.c().g || !this.d.b()) {
                CrashDetailBean packageCrashDatas = packageCrashDatas(str10, str11, j3, str9, str2, b, str8, str12, str4, str15, str16, str7, null, null, true, z);
                if (packageCrashDatas == null) {
                    an.e("pkg crash datas fail!", new Object[0]);
                    return;
                }
                b.a("NATIVE_CRASH", ap.a(), str10, str11, str9 + "\n" + str2 + "\n" + b, packageCrashDatas);
                Object obj2 = !this.b.a(packageCrashDatas, i3) ? 1 : null;
                str14 = null;
                NativeCrashHandler instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    str14 = instance.getDumpFilePath();
                }
                b.a(true, str14);
                if (obj2 != null) {
                    this.b.a(packageCrashDatas, 3000, true);
                }
                this.b.b(packageCrashDatas);
                return;
            }
            an.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
            b.a("NATIVE_CRASH", ap.a(), str10, str11, str9 + "\n" + str2 + "\n" + b, null);
            ap.b(str4);
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
        }
    }
}
