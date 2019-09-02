package cn.jiguang.analytics.android.a;

import android.content.Context;
import cn.jiguang.analytics.android.b.c;
import cn.jiguang.analytics.android.c.a.b;
import cn.jiguang.analytics.android.c.b.a;
import cn.jiguang.analytics.android.d;

final class j implements Runnable {
    private static final String[] z;
    final /* synthetic */ h a;
    private int b;
    private Context c;
    private String d;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 16;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "<GgLu)\u000baLj \u000bmCV-Lg~r-Yv\ro\"\u000b{Bs>\u000bcNr%]kYo)X\"BtlMpLa!NlYul\\kYnl[cJc\u0002JoH&v";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002d;
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
            case 0: goto L_0x00cd;
            case 1: goto L_0x00d1;
            case 2: goto L_0x00d5;
            case 3: goto L_0x00d8;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 6;
    L_0x001f:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002b;
    L_0x0027:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002b:
        r5 = r1;
        r1 = r7;
    L_0x002d:
        if (r5 > r6) goto L_0x0012;
    L_0x002f:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            case 5: goto L_0x006b;
            case 6: goto L_0x0073;
            case 7: goto L_0x007c;
            case 8: goto L_0x0086;
            case 9: goto L_0x0091;
            case 10: goto L_0x009c;
            case 11: goto L_0x00a7;
            case 12: goto L_0x00b2;
            case 13: goto L_0x00bd;
            case 14: goto L_0x00c8;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "#ERLa)nlI&v";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "#ERLa)xvLt8\u000b8";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\rHvDi\"\u000b/\rv-Lg~r-Yv\rv-Lgcg!N8";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u001fNq^o#EOLh-Lg_";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\rHvDi\"\u000b/\ri\"{cJc\tEf\rv-Lgcg!N8";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0063:
        r3[r2] = r1;
        r2 = 6;
        r1 = "-HvDi\"\u000baBh8NzY&%X\"Cs G";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006b:
        r3[r2] = r1;
        r2 = 7;
        r1 = "<GgLu)\u000boLm)\u000bqXt)\u000blBrlYg]c-_\"Ng G\"Bh\u001cJeHC\"O\"LrlDlH&8BoH";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0073:
        r3[r2] = r1;
        r2 = 8;
        r1 = "?JoH&<JeH*>N\"Jc\"NpLr){cJc";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007c:
        r3[r2] = r1;
        r2 = 9;
        r1 = "#ERLs?N\"\u0017";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0086:
        r3[r2] = r1;
        r2 = 10;
        r1 = "<GgLu)\u000baLj \u000bmCT)Xw@clBl\r#^p\rg/_k[o8R";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0091:
        r3[r2] = r1;
        r2 = 11;
        r1 = "`Ow_r%Fg\u0017";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009c:
        r3[r2] = r1;
        r2 = 12;
        r1 = "#EPHu9Fg\r<";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a7:
        r3[r2] = r1;
        r2 = 13;
        r1 = "/CgNmlXg^u%Dl\rr%Fg\ri9_\"Ag?_RLs?NVDk)\u0016";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b2:
        r3[r2] = r1;
        r2 = 14;
        r1 = "`Hw_R%Fg\u0010";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00bd:
        r3[r2] = r1;
        r2 = 15;
        r1 = " JqYv-^qHr%Fg";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c8:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x00cd:
        r9 = 76;
        goto L_0x001f;
    L_0x00d1:
        r9 = 43;
        goto L_0x001f;
    L_0x00d5:
        r9 = 2;
        goto L_0x001f;
    L_0x00d8:
        r9 = 45;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.a.j.<clinit>():void");
    }

    public j(h hVar, int i, Context context) {
        this.a = hVar;
        this.b = i;
        this.c = context;
    }

    public j(h hVar, int i, Context context, String str) {
        this.a = hVar;
        this.b = i;
        this.c = context;
        this.d = str;
        if (this.c == null && hVar.e != null) {
            this.c = hVar.e.getApplicationContext();
        }
    }

    private void a() {
        if (this.a.b == null) {
            b.f(z[4], z[10]);
            return;
        }
        h.d(this.a);
        this.a.d();
        b.b(z[4], new StringBuilder(z[9]).append(this.a.b.b().toString()).toString());
    }

    private void a(Context context) {
        long b = a.a(context).b(z[15], 0);
        b.d(z[4], new StringBuilder(z[13]).append(b).append(z[14]).append(System.currentTimeMillis()).append(z[11]).append(System.currentTimeMillis() - b).toString());
        if ((b != 0 && System.currentTimeMillis() - b >= d.e) || this.a.b == null) {
            this.a.d(context);
        }
        h.c(this.a);
        b.b(z[4], new StringBuilder(z[12]).append(this.a.b.b().toString()).toString());
        m.b().c();
        e.a(4369, false);
    }

    public final void run() {
        if (this.c == null) {
            b.i(z[4], z[6]);
        } else if (this.b == i.a) {
            a(this.c);
        } else if (this.b == i.b) {
            a();
        } else if (this.b == i.c) {
            r0 = this.c;
            r1 = this.d;
            b.b(z[4], new StringBuilder(z[3]).append(r1).toString());
            if (!this.a.g) {
                a(r0);
            }
            if (this.a.c != null && r1.equals(this.a.c.a())) {
                b.d(z[4], z[8]);
            }
            cn.jiguang.analytics.android.b.d a = h.a(this.a, r1);
            c cVar = new c();
            cVar.a(r0);
            if (a != null) {
                a.a(cVar);
                this.a.c = a;
                b.c(z[4], new StringBuilder(z[2]).append(this.a.c.c().toString()).toString());
            }
        } else if (this.b == i.d) {
            r0 = this.c;
            r1 = this.d;
            b.b(z[4], new StringBuilder(z[5]).append(r1).toString());
            if (!this.a.g) {
                a();
            }
            if (this.a.c == null) {
                b.g(z[4], new StringBuilder(z[0]).append(r1).toString());
            } else if (!this.a.c.a().equals(r1)) {
                b.g(z[4], new StringBuilder(z[0]).append(r1).toString());
            } else if (this.a.c.b() != this.a.b.a().size() + 1) {
                b.g(z[4], z[7]);
            } else {
                this.a.c.b(System.currentTimeMillis());
                c cVar2 = new c();
                cVar2.a(r0);
                this.a.c.b(cVar2);
                b.c(z[4], new StringBuilder(z[1]).append(this.a.c.c().toString()).toString());
                this.a.b.a(this.a.c);
                this.a.d();
            }
        }
    }
}
