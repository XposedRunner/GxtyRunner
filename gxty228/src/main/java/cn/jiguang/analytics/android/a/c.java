package cn.jiguang.analytics.android.a;

import android.content.Context;
import cn.jiguang.analytics.android.api.CalculateEvent;
import cn.jiguang.analytics.android.api.Event;
import cn.jiguang.analytics.android.b.b;
import cn.jiguang.analytics.android.c.b.a;
import cn.jiguang.analytics.android.c.d.d;
import cn.jiguang.api.JCoreInterface;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public final class c extends b {
    private static c b;
    private static final String[] z;
    private Context a;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = "o'\u001e\u0012.g0\u0015\u001d=O#";
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
        r11 = 90;
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
        r0 = "E?>\n?D%[\u001f5D%\u001e\u0004.\n8\b\\4_=\u0017";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r4] = r3;
        r3 = 2;
        r0 = "A4\u0002\u0019,O?\u000f";
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
        r11 = 42;
        goto L_0x0021;
    L_0x0058:
        r11 = 81;
        goto L_0x0021;
    L_0x005b:
        r11 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        goto L_0x0021;
    L_0x005e:
        r11 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        goto L_0x0021;
    L_0x0061:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.a.c.<clinit>():void");
    }

    private c() {
    }

    public static c b() {
        if (b == null) {
            b = new c();
        }
        return b;
    }

    public final Object a() {
        a.a(this.a);
        ArrayList c = a.c(this.a, z[2]);
        JSONArray jSONArray = new JSONArray();
        if (c == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        Iterator it = c.iterator();
        JSONObject jSONObject2 = jSONObject;
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar != null) {
                jSONObject = bVar.b();
                if (!cn.jiguang.analytics.android.c.d.b.a((Object) jSONObject)) {
                    jSONArray.put(jSONObject);
                }
            } else {
                jSONObject = jSONObject2;
            }
            jSONObject2 = jSONObject;
        }
        a.a(this.a);
        a.b(this.a, z[2]);
        return cn.jiguang.analytics.android.c.d.b.a((Object) jSONObject2) ? null : jSONArray;
    }

    public final void a(Context context) {
        this.a = context.getApplicationContext();
        m.b().a((b) this);
    }

    public final void a(Context context, Event event) {
        if (context == null) {
            cn.jiguang.analytics.android.c.a.b.f(z[0], z[1]);
            return;
        }
        this.a = context.getApplicationContext();
        b bVar = new b();
        if (event instanceof CalculateEvent) {
            bVar.a(1);
        }
        event.setItime(JCoreInterface.getReportTime());
        bVar.a(event);
        d.a().a(new d(this, bVar));
    }
}
