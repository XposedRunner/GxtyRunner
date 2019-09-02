package cn.jiguang.analytics.android.c.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import cn.jiguang.analytics.android.c.a.b;
import cn.jiguang.analytics.android.d;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public final class a {
    private static final String[] A;
    private static a z;
    private transient AtomicBoolean a = new AtomicBoolean(false);
    private SharedPreferences b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private int j;
    private int k;
    private String l;
    private String m;
    private String n;
    private String o;
    private int p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 35;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "8,e\b\u000e";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002e;
    L_0x0012:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0017:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x019f;
            case 1: goto L_0x01a3;
            case 2: goto L_0x01a6;
            case 3: goto L_0x01aa;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 62;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002c;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002c:
        r5 = r1;
        r1 = r7;
    L_0x002e:
        if (r5 > r6) goto L_0x0012;
    L_0x0030:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            case 19: goto L_0x0100;
            case 20: goto L_0x010b;
            case 21: goto L_0x0116;
            case 22: goto L_0x0121;
            case 23: goto L_0x012c;
            case 24: goto L_0x0137;
            case 25: goto L_0x0142;
            case 26: goto L_0x014d;
            case 27: goto L_0x0158;
            case 28: goto L_0x0163;
            case 29: goto L_0x016e;
            case 30: goto L_0x0179;
            case 31: goto L_0x0184;
            case 32: goto L_0x018f;
            case 33: goto L_0x019a;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "Mg#O]lK;@Q";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "nq8\bHlp&OQg,7GMl`4HZ";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "ok'UJZv4TJ]k8C";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "CC\u001bgrPQ\u001cua\\W\u001cb";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "kc&C\\hl1";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "Hl1TQ`f";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "GMuP[{q<IPJm1C\u001efpuP[{q<IPGc8C\u001emg3OPlfuOP)o4HWog&R\u0010";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "hl1TQ`f\nOZ";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "ng!\u0006_yr;GSl\"0TLfpo";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "%\"1CH`a0\u001b\u0019";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "%\"&IRlK1\u001b\u0019";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "%\"&BUg'UWflh\u0001";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "%\"&OYgc!SLl?r";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "%\"=CWnj!\u001b";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "%\"4HZ{m<Ba`fh\u0001";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "%\"3OLzv\u0006R_{v\u0001OSl?r";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "Mg#O]lK;@Qrc%VPho0\u001b\u0019";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "%\"<HM}c9Jj`o0\u001b\u0019";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "%\" SWm?r";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "%\"<K[`?r";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "%\"%MYgc8C\u0003.";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "%\"!OSlx:H[4%";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "%\"7GMl`4HZ4%";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "%\"#CLzk:H}ff0\u001b";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "%\";CJ]{%C\u0003.";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = "%\"#CLzk:Hpho0\u001b\u0019";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        r2 = 27;
        r1 = "%\"9GPnw4A[4%";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014d:
        r3[r2] = r1;
        r2 = 28;
        r1 = "%\":U\u0003.";
        r0 = 27;
        r3 = r4;
        goto L_0x0009;
    L_0x0158:
        r3[r2] = r1;
        r2 = 29;
        r1 = "%\"\"OZ}jh";
        r0 = 28;
        r3 = r4;
        goto L_0x0009;
    L_0x0163:
        r3[r2] = r1;
        r2 = 30;
        r1 = "%\"8IZlnh\u0001";
        r0 = 29;
        r3 = r4;
        goto L_0x0009;
    L_0x016e:
        r3[r2] = r1;
        r2 = 31;
        r1 = "%\":Uhlp&OQg?r";
        r0 = 30;
        r3 = r4;
        goto L_0x0009;
    L_0x0179:
        r3[r2] = r1;
        r2 = 32;
        r1 = "%\"8G]Vc1BLlq&\u001b\u0019";
        r0 = 31;
        r3 = r4;
        goto L_0x0009;
    L_0x0184:
        r3[r2] = r1;
        r2 = 33;
        r1 = "ng!uWnluCL{m'\u001c";
        r0 = 32;
        r3 = r4;
        goto L_0x0009;
    L_0x018f:
        r3[r2] = r1;
        r2 = 34;
        r1 = "\\l0^Nla!CZ3\"3GWeg1\u0006Jf\"2CJ)a TLll!\u0006_yr9O]hv<IP)k;@Q";
        r0 = 33;
        r3 = r4;
        goto L_0x0009;
    L_0x019a:
        r3[r2] = r1;
        A = r4;
        return;
    L_0x019f:
        r9 = 9;
        goto L_0x0020;
    L_0x01a3:
        r9 = 2;
        goto L_0x0020;
    L_0x01a6:
        r9 = 85;
        goto L_0x0020;
    L_0x01aa:
        r9 = 38;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.c.c.a.<clinit>():void");
    }

    private a() {
    }

    public static a a() {
        if (z == null) {
            z = new a();
        }
        return z;
    }

    private void a(String str, String str2) {
        if (this.b != null) {
            Editor edit = this.b.edit();
            edit.putString(str, str2);
            edit.apply();
        }
    }

    private static ApplicationInfo b(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        } catch (Throwable th) {
            b.b(A[1], A[34], th);
            return null;
        }
    }

    private static String c(Context context) {
        try {
            for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(64)) {
                if (packageInfo.packageName.equals(context.getPackageName())) {
                    return packageInfo.signatures[0].toCharsString();
                }
            }
        } catch (Throwable th) {
            b.c(A[1], new StringBuilder(A[33]).append(th.getMessage()).toString());
        }
        return null;
    }

    public final void a(Context context) {
        if (!this.a.get() && context != null) {
            this.b = context.getSharedPreferences(A[1], 0);
            this.r = this.b.getString(A[4], "");
            if (TextUtils.isEmpty(this.r)) {
                this.r = UUID.randomUUID().toString();
                a(A[4], this.r);
            }
            this.y = this.b.getString(A[3], "");
            if (TextUtils.isEmpty(this.y)) {
                this.y = System.currentTimeMillis();
                a(A[3], this.y);
            }
            ApplicationInfo b = b(context);
            if (b != null) {
                try {
                    this.c = context.getPackageManager().getApplicationLabel(b).toString();
                } catch (Throwable th) {
                    b.c(A[1], new StringBuilder(A[9]).append(th.getMessage()).toString());
                }
            }
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                this.p = packageInfo.versionCode;
                this.q = packageInfo.versionName;
                this.x = packageInfo.firstInstallTime;
                if (this.q.length() > 30) {
                    this.q = this.q.substring(0, 30);
                }
            } catch (Throwable th2) {
                b.b(A[1], A[7]);
            }
            this.m = cn.jiguang.analytics.android.c.d.a.a(context);
            if (d.g) {
                this.n = cn.jiguang.analytics.android.c.d.a.a(context, "");
            }
            String c = c(context);
            if (!TextUtils.isEmpty(c)) {
                this.e = cn.jiguang.analytics.android.c.d.a.a(c);
            }
            this.d = context.getPackageName();
            this.w = context.getResources().getConfiguration().locale.getLanguage();
            this.v = TimeZone.getDefault().getDisplayName(false, 0);
            this.f = A[0];
            this.u = VERSION.SDK_INT;
            this.t = A[6];
            this.g = Build.MODEL;
            this.i = Build.DEVICE;
            this.h = c.a(context, A[2], A[5]);
            try {
                this.l = Secure.getString(context.getContentResolver(), A[8]);
            } catch (Exception e) {
            }
            if (TextUtils.isEmpty(this.l)) {
                this.l = "";
            }
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics != null) {
                this.j = displayMetrics.widthPixels;
                this.k = displayMetrics.heightPixels;
            }
            c = !TextUtils.isEmpty(this.n) ? this.n : !TextUtils.isEmpty(this.m) ? this.m : !TextUtils.isEmpty(this.l) ? this.l : this.r;
            this.s = c;
            this.a.set(true);
        }
    }

    public final String toString() {
        return new StringBuilder(A[17]).append(this.c).append('\'').append(A[21]).append(this.d).append('\'').append(A[13]).append(this.e).append('\'').append(A[12]).append(this.f).append('\'').append(A[30]).append(this.g).append('\'').append(A[23]).append(this.h).append('\'').append(A[10]).append(this.i).append('\'').append(A[29]).append(this.j).append(A[14]).append(this.k).append(A[15]).append(this.l).append('\'').append(A[20]).append(this.m).append('\'').append(A[32]).append(this.n).append('\'').append(A[25]).append(this.o).append('\'').append(A[24]).append(this.p).append(A[26]).append(this.q).append('\'').append(A[19]).append(this.r).append('\'').append(A[11]).append(this.s).append('\'').append(A[28]).append(this.t).append('\'').append(A[31]).append(this.u).append('\'').append(A[22]).append(this.v).append('\'').append(A[27]).append(this.w).append('\'').append(A[18]).append(this.x).append('\'').append(A[16]).append(this.y).append('\'').append('}').toString();
    }
}
