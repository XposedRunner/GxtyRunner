package cn.jiguang.analytics.android.a;

import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import cn.jiguang.analytics.android.api.Account;
import cn.jiguang.analytics.android.api.AccountCallback;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.analytics.android.c.c.b;
import cn.jiguang.analytics.android.c.d.e;
import cn.jiguang.api.JCoreInterface;
import org.json.JSONObject;

public final class a {
    private static final String[] z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 10;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "]\u0006\u000fr4";
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
            case 0: goto L_0x008c;
            case 1: goto L_0x008f;
            case 2: goto L_0x0092;
            case 3: goto L_0x0095;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 28;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0017S";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "t(\u0005vpG\u001d\u0002tow\u0007\u001frnX\b\br";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "Z\f\u001fvVI\rvuR\f\u000f7~[\n\nbo[I\ntQ\u001c\u0005cCW\rK~o\u001e\f\u0006ghG";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "Z\f\u001fvVI\ntQ\u001c\u0005c<M\u001c\bty[\r";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "Z\f\u001fvV6\ntQ\u001c\u0005c";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "Q\u0019\u000ee}J\u0000\u0004y<W\u001aKcsQI\tboGEKgp[\b\u0018r<J\u001b\u00127uJI\u0007vh[\u001b";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "W\r\u000eyhW\u000f\u0012H}]\n\u0004brJ";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "_\n\bxiP\u001d4~x\u001e\n\ny<P\u0006\u001f7~[I\u000ezlJ\u0010";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "W\r\u000eyhW\u000f\u00127}]\n\u0004brJI\u0018b]\f\u000es";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x008c:
        r9 = 62;
        goto L_0x0020;
    L_0x008f:
        r9 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        goto L_0x0020;
    L_0x0092:
        r9 = 107; // 0x6b float:1.5E-43 double:5.3E-322;
        goto L_0x0020;
    L_0x0095:
        r9 = 23;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.a.a.<clinit>():void");
    }

    public static void a(Account account, AccountCallback accountCallback) {
        if (TextUtils.isEmpty(account.getAccountId())) {
            a(accountCallback, 1001, z[8]);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        b fillJson = account.fillJson(jSONObject);
        if (fillJson != null) {
            a(accountCallback, fillJson.a(), fillJson.b());
        } else if (e.a(JAnalyticsInterface.mContext)) {
            a(accountCallback, PointerIconCompat.TYPE_HELP, z[6]);
        } else {
            JCoreInterface.setAccountId(account.getAccountId());
            JCoreInterface.report(JAnalyticsInterface.mContext, JCoreInterface.fillBaseReport(jSONObject, z[7]), true);
            a(accountCallback, 0, z[9]);
        }
    }

    public static void a(AccountCallback accountCallback) {
        if (TextUtils.isEmpty(JCoreInterface.getAccountId())) {
            a(accountCallback, 1002, z[3]);
        } else if (e.a(JAnalyticsInterface.mContext)) {
            a(accountCallback, PointerIconCompat.TYPE_HELP, z[6]);
        } else {
            JCoreInterface.report(JAnalyticsInterface.mContext, JCoreInterface.fillBaseReport(new JSONObject(), z[5]), true);
            JCoreInterface.setAccountId("");
            a(accountCallback, 0, z[4]);
        }
    }

    private static void a(AccountCallback accountCallback, int i, String str) {
        if (accountCallback != null) {
            if (i == 0) {
                cn.jiguang.analytics.android.c.a.b.e(z[2], str);
            } else {
                cn.jiguang.analytics.android.c.a.b.g(z[2], new StringBuilder(z[0]).append(i).append(z[1]).append(str).toString());
            }
            accountCallback.callback(i, str);
        }
    }
}
