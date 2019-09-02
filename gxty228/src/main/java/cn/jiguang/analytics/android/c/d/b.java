package cn.jiguang.analytics.android.c.d;

import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

public final class b {
    private static final String[] z;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r3 = 2;
        r1 = 0;
        r2 = 1;
        r0 = 4;
        r5 = new java.lang.String[r0];
        r4 = "\t0";
        r0 = -1;
        r6 = r5;
        r7 = r5;
        r5 = r1;
    L_0x000c:
        r4 = r4.toCharArray();
        r8 = r4.length;
        if (r8 > r2) goto L_0x006a;
    L_0x0013:
        r9 = r1;
    L_0x0014:
        r10 = r4;
        r11 = r9;
        r14 = r8;
        r8 = r4;
        r4 = r14;
    L_0x0019:
        r13 = r8[r9];
        r12 = r11 % 5;
        switch(r12) {
            case 0: goto L_0x005f;
            case 1: goto L_0x0062;
            case 2: goto L_0x0065;
            case 3: goto L_0x0067;
            default: goto L_0x0020;
        };
    L_0x0020:
        r12 = 50;
    L_0x0022:
        r12 = r12 ^ r13;
        r12 = (char) r12;
        r8[r9] = r12;
        r9 = r11 + 1;
        if (r4 != 0) goto L_0x002e;
    L_0x002a:
        r8 = r10;
        r11 = r9;
        r9 = r4;
        goto L_0x0019;
    L_0x002e:
        r8 = r4;
        r4 = r10;
    L_0x0030:
        if (r8 > r9) goto L_0x0014;
    L_0x0032:
        r8 = new java.lang.String;
        r8.<init>(r4);
        r4 = r8.intern();
        switch(r0) {
            case 0: goto L_0x0047;
            case 1: goto L_0x0050;
            case 2: goto L_0x005a;
            default: goto L_0x003e;
        };
    L_0x003e:
        r6[r5] = r4;
        r0 = "8>nI}\u0010'dDF'9hK";
        r4 = r0;
        r5 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r6[r5] = r4;
        r0 = "\u0006%d\u0007W\n9sF\u0012\u001f,q\u0007Z\u0013>!TS\u001f(!LW\u000bmvNF\u001amkF\\\u0013!xS[\u0011>!\u001d";
        r4 = r0;
        r5 = r3;
        r6 = r7;
        r0 = r2;
        goto L_0x000c;
    L_0x0050:
        r6[r5] = r4;
        r4 = 3;
        r0 = "&%dUWR:`T\u0012\u0013#!B@\u0000\"s\u0007B\u0013.jFU\u001b#f\u0007x!\u0002O";
        r5 = r4;
        r6 = r7;
        r4 = r0;
        r0 = r3;
        goto L_0x000c;
    L_0x005a:
        r6[r5] = r4;
        z = r7;
        return;
    L_0x005f:
        r12 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        goto L_0x0022;
    L_0x0062:
        r12 = 77;
        goto L_0x0022;
    L_0x0065:
        r12 = r2;
        goto L_0x0022;
    L_0x0067:
        r12 = 39;
        goto L_0x0022;
    L_0x006a:
        r9 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.c.d.b.<clinit>():void");
    }

    public static JSONObject a(Map map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : map.entrySet()) {
            try {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            } catch (Throwable e) {
                cn.jiguang.analytics.android.c.a.b.b(z[1], z[3], e);
            }
        }
        return jSONObject;
    }

    public static JSONObject a(JSONObject jSONObject, Map map) {
        if (map == null) {
            return null;
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        for (Entry entry : map.entrySet()) {
            try {
                if (jSONObject.has((String) entry.getKey())) {
                    cn.jiguang.analytics.android.c.a.b.g(z[1], new StringBuilder(z[2]).append(entry.getKey()).toString());
                } else {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                }
            } catch (Throwable e) {
                cn.jiguang.analytics.android.c.a.b.b(z[1], z[3], e);
            }
        }
        return jSONObject;
    }

    public static boolean a(Object obj) {
        return obj == null ? true : obj instanceof JSONArray ? ((JSONArray) obj).length() <= 0 : obj instanceof JSONObject ? obj.toString().equals(z[0]) : false;
    }
}
