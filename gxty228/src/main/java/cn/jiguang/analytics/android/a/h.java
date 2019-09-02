package cn.jiguang.analytics.android.a;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.analytics.android.b.c;
import cn.jiguang.analytics.android.b.d;
import cn.jiguang.analytics.android.b.e;
import cn.jiguang.analytics.android.c.a.b;
import cn.jiguang.analytics.android.c.b.a;
import cn.jiguang.api.JCoreInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;

public final class h extends b {
    private static h a;
    private static final String[] z;
    private e b;
    private d c;
    private boolean d = true;
    private Context e;
    private AtomicBoolean f = new AtomicBoolean();
    private boolean g;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 24;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0016pB\u0015\u0018\u001c[|\u0010_\tu\u00111\u0018swT\u0016\n>|\u0001\u0013\u0015";
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
            case 0: goto L_0x0126;
            case 1: goto L_0x012a;
            case 2: goto L_0x012e;
            case 3: goto L_0x0132;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "*{a\u0007\u0016\u0016p_\u0015\u0011\u0018yw\u0006";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "8}f\u001d\u0010\u0017>?T\u0019\u0015ka\u001c,\u001cma\u001d\u0010\u0017I{\u0000\u0017+{a\u0001\u0012\u001c";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0015a\u0000\r\u001cmg\u0019\u001a\rw\u0011";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0012{k\u0007\u001a\nm{\u001b\u0011";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0015qs\u0010;\u0018jsZQW0";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u001aq|\u0000\u001a\u0001j2\u001d\fYpg\u0018\u0013U}s\u001a_\u0017qfT\u0013\u0016vT\u001b\u0018js";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0015a\u0000_\n{a\u0007\u0016\u0016p2\u001d\fYpg\u0018\u0013";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u0011d\u0011_\n\u0011_\n{a\u0007\u0016\u0016p<ZQ";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\u0012{k\u0017\n\u000bmw\u0007\f\u0010q|";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\nd\u0011,\u001cma\u001d\u0010\u0017J}8\u0016\nj";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\nd\u0011_\n{a\u0007\u0016\u0016p2\u001e\f\u0016p(";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "8}f\u001d\u0010\u0017>?T\u0019\u0015ka\u001c,\u001cma\u001d\u0010\u0017I{\u0000\u0017)g\u0007\u001a";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\u0015a\u0000\u000f\u0018ka\u0011\u000b\u0010sw";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\trw\u0015\f\u001c>q\u001c\u001a\u001au2\u0007\u0010\u0014{f\u001c\u0016\u0017y2\u0015\u001d\u0016kfT\f\u001cma\u001d\u0010\u00170<Z";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "8}f\u001d\u0010\u0017>?T\u0018\u001cpw\u0006\u001e\r{B\u0015\u0018\u001c[|\u0000\u0016\rg2\u0004\u001e\u001e{\\\u0015\u0012\u001c$";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u0016pB\u0015\u0018\u001cMf\u0015\r\r>b\u0015\u0018\u001cPs\u0019\u001aYwaT\u0011\fr~";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u001aq|\u0000\u001a\u0017j2\u001d\fYpg\u0018\u0013";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u001aq|\u0000\u001a\u0001j2\u0007\u0017\u0016k~\u0010_\u0017qfT\u001e\tn~\u001d\u001c\u0018j{\u001b\u0011";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\nd\u0011_\u001ak`T\f\u001cma\u001d\u0010\u0017>(";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "8}f\u001d\u0010\u0017>?T\u0010\u0017Ns\u0001\f\u001c";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "\u001aq|\u0000\u001a\u0001j2\u001d\fYpg\u0018\u0013Uw|\u001d\u000bYxs\u001d\u0013\u001cz";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "8}f\u001d\u0010\u0017>?T\u0010\u0017Lw\u0007\n\u0014{";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "\u0010p{\u0000,\u001cma\u001d\u0010\u00170<Z";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x0126:
        r9 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        goto L_0x0020;
    L_0x012a:
        r9 = 30;
        goto L_0x0020;
    L_0x012e:
        r9 = 18;
        goto L_0x0020;
    L_0x0132:
        r9 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.a.h.<clinit>():void");
    }

    private h() {
    }

    static /* synthetic */ d a(h hVar, String str) {
        b.a(z[1], new StringBuilder(z[15]).append(str).toString());
        if (hVar.b == null) {
            b.f(z[1], z[14]);
            hVar.d(hVar.e);
        }
        d dVar = new d();
        dVar.a(System.currentTimeMillis());
        dVar.b(System.currentTimeMillis());
        dVar.a(str);
        List a = hVar.b.a();
        if (a != null && a.size() > 0) {
            dVar.a((d) a.get(a.size() - 1));
        }
        dVar.a(hVar.b.a().size() + 1);
        return dVar;
    }

    public static h b() {
        if (a == null) {
            a = new h();
        }
        return a;
    }

    private synchronized void c() {
        b.d(z[1], z[10]);
        if (this.e != null) {
            Object a = a.a(this.e, z[9]);
            if (a == null || !(a instanceof e)) {
                b.d(z[1], z[7]);
            } else {
                a.b(this.e, z[9]);
                e eVar = (e) a;
                ArrayList c = a.c(this.e, z[4]);
                if (c == null) {
                    c = new ArrayList();
                } else if (c.remove(eVar)) {
                    b.d(z[1], z[8]);
                }
                c.add(eVar);
                Iterator it = c.iterator();
                while (it.hasNext()) {
                    b.b(z[1], new StringBuilder(z[11]).append(((e) it.next()).b().toString()).toString());
                }
                a.a(this.e, z[4], c);
            }
        }
    }

    static /* synthetic */ void c(h hVar) {
        b.a(z[1], z[2]);
        if (hVar.b != null) {
            hVar.b.b(System.currentTimeMillis());
        }
        a.a(hVar.e).a(z[3], System.currentTimeMillis());
    }

    private synchronized void d() {
        if (!(this.b == null || this.e == null)) {
            b.a(z[1], new StringBuilder(z[19]).append(this.b.b().toString()).toString());
            this.b.d(System.currentTimeMillis());
            c cVar = new c();
            cVar.a(this.e);
            this.b.a(cVar);
            a.a(this.e, z[9], this.b);
        }
    }

    private void d(Context context) {
        b.d(z[1], z[23]);
        c();
        this.e = context.getApplicationContext();
        this.b = new e();
        long b = a.a(context).b(z[3], System.currentTimeMillis());
        StringBuilder stringBuilder = new StringBuilder();
        String appKey = JCoreInterface.getAppKey();
        if (!cn.jiguang.analytics.android.c.d.c.a(appKey)) {
            stringBuilder.append(appKey);
        }
        appKey = JCoreInterface.getDeviceId(context);
        if (!cn.jiguang.analytics.android.c.d.c.a(appKey)) {
            stringBuilder.append(appKey);
        }
        stringBuilder.append(b);
        String a = cn.jiguang.analytics.android.c.d.a.a(stringBuilder.toString());
        this.b.c(System.currentTimeMillis());
        this.b.d(System.currentTimeMillis());
        this.b.a(a);
        c cVar = new c();
        cVar.a(context);
        this.b.b(cVar);
        this.c = null;
    }

    static /* synthetic */ void d(h hVar) {
        b.a(z[1], z[12]);
        if (hVar.b != null) {
            hVar.b.a(System.currentTimeMillis());
        }
        a.a(hVar.e).a(z[13], System.currentTimeMillis());
    }

    private static boolean e(Context context) {
        if (context == null) {
            b.b(z[1], z[17]);
            return false;
        } else if (!(context instanceof Application)) {
            return true;
        } else {
            b.b(z[1], z[18]);
            return false;
        }
    }

    public final Object a() {
        b.b(z[1], z[5]);
        if (this.e == null) {
            b.g(z[1], z[6]);
            return null;
        }
        ArrayList c = a.c(this.e, z[4]);
        Object jSONArray = new JSONArray();
        if (c != null) {
            Iterator it = c.iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                if (!(eVar == null || eVar.a() == null || eVar.a().size() <= 0)) {
                    Object b = eVar.b();
                    if (!cn.jiguang.analytics.android.c.d.b.a(b)) {
                        jSONArray.put(b);
                    }
                }
            }
            a.b(this.e, z[4]);
            if (!cn.jiguang.analytics.android.c.d.b.a(jSONArray)) {
                return jSONArray;
            }
        }
        return null;
    }

    public final void a(Context context) {
        b.a(z[1], z[22]);
        if (e(context)) {
            cn.jiguang.analytics.android.c.d.d.a().a(new j(this, i.a, context));
        }
    }

    public final void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            b.f(z[1], z[16]);
        } else {
            cn.jiguang.analytics.android.c.d.d.a().a(new j(this, i.c, context, str));
        }
    }

    public final void b(Context context) {
        b.a(z[1], z[20]);
        if (e(context)) {
            cn.jiguang.analytics.android.c.d.d.a().a(new j(this, i.b, context));
        }
    }

    public final void b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            b.f(z[1], z[0]);
        } else {
            cn.jiguang.analytics.android.c.d.d.a().a(new j(this, i.d, context, str));
        }
    }

    public final void c(Context context) {
        if (!this.f.get()) {
            if (context == null) {
                b.g(z[1], z[21]);
                return;
            }
            this.e = context.getApplicationContext();
            JCoreInterface.setAnalysisAction(new k(this));
            m.b().a((b) this);
            this.f.set(true);
        }
    }
}
