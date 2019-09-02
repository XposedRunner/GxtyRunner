package cn.jiguang.analytics.android.c.d;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class f {
    private static final String[] z;
    private long a;
    private int b;
    private List<Long> c;

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
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = "+DZIj";
        r0 = -1;
        r5 = r4;
        r6 = r4;
        r4 = r1;
    L_0x000b:
        r3 = r3.toCharArray();
        r7 = r3.length;
        if (r7 > r2) goto L_0x0061;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r3;
        r10 = r8;
        r13 = r7;
        r7 = r3;
        r3 = r13;
    L_0x0018:
        r12 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x0055;
            case 1: goto L_0x0058;
            case 2: goto L_0x005b;
            case 3: goto L_0x005e;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 25;
    L_0x0021:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r3 != 0) goto L_0x002d;
    L_0x0029:
        r7 = r9;
        r10 = r8;
        r8 = r3;
        goto L_0x0018;
    L_0x002d:
        r7 = r3;
        r3 = r9;
    L_0x002f:
        if (r7 > r8) goto L_0x0013;
    L_0x0031:
        r7 = new java.lang.String;
        r7.<init>(r3);
        r3 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0046;
            case 1: goto L_0x0050;
            default: goto L_0x003d;
        };
    L_0x003d:
        r5[r4] = r3;
        r0 = "3DZEm";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r4] = r3;
        r3 = 2;
        r0 = "6CCIk)L[";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000b;
    L_0x0050:
        r5[r4] = r3;
        z = r6;
        return;
    L_0x0055:
        r11 = 95;
        goto L_0x0021;
    L_0x0058:
        r11 = 45;
        goto L_0x0021;
    L_0x005b:
        r11 = 55;
        goto L_0x0021;
    L_0x005e:
        r11 = 44;
        goto L_0x0021;
    L_0x0061:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.c.d.f.<clinit>():void");
    }

    public f(long j, int i) {
        this.a = j;
        this.b = i;
    }

    public f(String str) {
        int i = 0;
        JSONObject jSONObject = new JSONObject(str);
        this.a = jSONObject.getLong(z[2]);
        this.b = jSONObject.getInt(z[1]);
        JSONArray optJSONArray = jSONObject.optJSONArray(z[0]);
        if (optJSONArray != null && optJSONArray.length() > 0) {
            this.c = new ArrayList();
            while (i < optJSONArray.length()) {
                this.c.add(Long.valueOf(optJSONArray.getLong(i)));
                i++;
            }
        }
    }

    public final String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z[2], this.a);
            jSONObject.put(z[1], this.b);
            if (!(this.c == null || this.c.isEmpty())) {
                JSONArray jSONArray = new JSONArray();
                for (Long put : this.c) {
                    jSONArray.put(put);
                }
                jSONObject.put(z[0], jSONArray);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            return "";
        }
    }

    public final boolean a(long j) {
        boolean z;
        if (this.c != null) {
            int size = this.c.size();
            if (size > this.b) {
                this.c = this.c.subList(size - this.b, size);
            }
            if (j < ((Long) this.c.get(0)).longValue() + this.a) {
                z = true;
                if (!z) {
                    if (this.c == null) {
                        this.c = new ArrayList();
                    }
                    this.c.add(Long.valueOf(j));
                    if (this.c.size() > this.b) {
                        this.c.remove(0);
                    }
                }
                return z;
            }
        }
        z = false;
        if (z) {
            if (this.c == null) {
                this.c = new ArrayList();
            }
            this.c.add(Long.valueOf(j));
            if (this.c.size() > this.b) {
                this.c.remove(0);
            }
        }
        return z;
    }
}
