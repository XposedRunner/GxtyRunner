package cn.jiguang.analytics.android.api;

import android.app.Activity;
import android.content.Context;
import cn.jiguang.analytics.android.a.a;
import cn.jiguang.analytics.android.a.c;
import cn.jiguang.analytics.android.a.e;
import cn.jiguang.analytics.android.a.h;
import cn.jiguang.analytics.android.c.a.b;
import cn.jiguang.analytics.android.d;
import cn.jiguang.api.JCoreInterface;
import java.util.concurrent.atomic.AtomicBoolean;

public final class JAnalyticsInterface {
    private static final String TAG;
    private static AtomicBoolean isInit = new AtomicBoolean(false);
    public static Context mContext;
    private static final String[] z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 5;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0015|F\u000b0&IA\t/\u0016S\\\u000f.9\\K\u000f";
        r0 = 4;
        r4 = r3;
    L_0x0008:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x0033;
    L_0x0011:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0016:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0079;
            case 1: goto L_0x007c;
            case 2: goto L_0x007f;
            case 3: goto L_0x0082;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 92;
    L_0x001f:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x0031;
    L_0x0027:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0016;
    L_0x002b:
        TAG = r1;
        r1 = "\u0015~G\u00189TF\u0003([I\u00030:Y";
        r0 = -1;
        goto L_0x0008;
    L_0x0031:
        r5 = r1;
        r1 = r7;
    L_0x0033:
        if (r5 > r6) goto L_0x0011;
    L_0x0035:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0049;
            case 1: goto L_0x0051;
            case 2: goto L_0x0059;
            case 3: goto L_0x0061;
            case 4: goto L_0x002b;
            default: goto L_0x0041;
        };
    L_0x0041:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0015|F\u000b0&IA\t/\u0016S\\\u000f.9\\K\u000f|6SA\u001erq\u0013^\u000f.,TG\u0004fn\u0013\u001aDl\u0011J\u001f53Ya\u000efn\u000e\u001a";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0049:
        r3[r2] = r1;
        r2 = 2;
        r1 = "<RF\u001e9'I\b\u0003/S]\u00060";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x0051:
        r3[r2] = r1;
        r2 = 3;
        r1 = "6S^\u000b06YMJ\u0019)XF\u001e|e";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0059:
        r3[r2] = r1;
        r2 = 4;
        r1 = "s}&\u0010x^\u000f2+";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x0061:
        r3[r2] = r1;
        z = r4;
        r0 = new java.util.concurrent.atomic.AtomicBoolean;
        r1 = 0;
        r0.<init>(r1);
        isInit = r0;
        r0 = cn.jiguang.api.SdkType.JANALYTICS;
        r0 = r0.name();
        r1 = cn.jiguang.analytics.android.c.class;
        cn.jiguang.api.JCoreInterface.initAction(r0, r1);
        return;
    L_0x0079:
        r9 = 95;
        goto L_0x001f;
    L_0x007c:
        r9 = 61;
        goto L_0x001f;
    L_0x007f:
        r9 = 40;
        goto L_0x001f;
    L_0x0082:
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.api.JAnalyticsInterface.<clinit>():void");
    }

    private JAnalyticsInterface() {
    }

    public static void detachAccount(AccountCallback accountCallback) {
        a.a(accountCallback);
    }

    public static void identifyAccount(Account account, AccountCallback accountCallback) {
        a.a(account, accountCallback);
    }

    public static void init(Context context) {
        if (context == null) {
            b.i(TAG, z[2]);
        } else if (!isInit.get()) {
            b.c(TAG, z[1]);
            if (JCoreInterface.init(context, false)) {
                mContext = context.getApplicationContext();
                if (cn.jiguang.analytics.android.a.a(context)) {
                    isInit.set(true);
                    return;
                }
                return;
            }
            b.g(TAG, z[0]);
        }
    }

    public static void initCrashHandler(Context context) {
        JCoreInterface.initCrashHandler(context);
    }

    public static void onEvent(Context context, Event event) {
        init(context);
        if (event == null) {
            b.g(TAG, z[4]);
        } else if (event.checkEvent()) {
            c.b().a(context, event);
        } else {
            b.g(TAG, new StringBuilder(z[3]).append(event.toString()).toString());
        }
    }

    public static void onPageEnd(Context context, String str) {
        init(context);
        h.b().b(context, str);
    }

    public static void onPageStart(Context context, String str) {
        init(context);
        h.b().a(context, str);
    }

    public static void requestPermission(Activity activity) {
        JCoreInterface.requestPermission(activity);
    }

    public static void setAnalyticsReportPeriod(int i) {
        e.a(4369, i);
    }

    public static void setDebugMode(boolean z) {
        JCoreInterface.setDebugMode(z);
        d.a = z;
    }

    public static void stopCrashHandler(Context context) {
        JCoreInterface.stopCrashHandler(context);
    }
}
