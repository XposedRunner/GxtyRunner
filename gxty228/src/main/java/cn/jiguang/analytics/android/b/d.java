package cn.jiguang.analytics.android.b;

import cn.jiguang.analytics.android.c.d.b;
import cn.jiguang.analytics.android.c.d.c;
import org.json.JSONException;
import org.json.JSONObject;

public final class d implements a {
    private static final String[] z;
    private String a;
    private long b;
    private long c;
    private long d;
    private d e;
    private int f;
    private c g;
    private c h;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 24;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "?\u0017q\ny?\u001ey\u0001C\u0010\u0002n0D6\u0002s\u001c";
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
        r9 = 38;
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
        r1 = "?\u0017q\ny<\u0002w\u001dR\u0010\u0006~\u0000H*)w\u0019G#\u001fw\rJ*){\nK \u0004o";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "?\u0017q\ny?\u001ey\u0001C\u0010\u0004n0D6\u0002s\u001c";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "?\u0017q\ny*\u0018r0V'\u0019x\ny.\u0000w\u0003O.\u0014z\ny\"\u0013{\u0000T6";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "?\u0017q\ny?\u001ey\u0001C\u0010\u0002y\u001bG#){\nK \u0004o";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "?\u0017q\ny.\u0006f0T7)t\u0016R*\u0005";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "?\u0017q\ny.\u0006f0R7)t\u0016R*\u0005";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "?\u0017q\ny?\u001ey\u0001C\u0010\u001by\rO#\u0013I\u001b^\u0010\u0014o\u001bC<";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "?\u0017q\ny?\u001ey\u0001C\u0010\u001by\rO#\u0013I\u001d^\u0010\u0014o\u001bC<";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "cVf\u000eA*3x\u000bv.\u0011s*^;\u0004wR";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u001f\u0017q\nc!\u0002\u001b_4\u0006w\bC\u0001\u0017{\n\u001bh";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "cVe\u001bG=\u0002B\u0006K*K";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "cVs\u0001B\u001b\u001f{\n\u001b";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "cVe\nWr";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "cVf\u000eA*2c\u001dG;\u001fy\u0001\u001b";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "cVf\u000eA*%b\u000eT;&w\bC\n\u000eb\u001dGr";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "cVz\u000eU;&w\bCr";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "?\u0017q\ny!\u0017{\n";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "*\u000eb\u001dG";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "?\u0017q\ny#\u0017e\u001by?\u0017q\n";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "?\u0017q\ny*\u0018r0R&\u001bs";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "?\u0017q\ny+\u0003d\u000eR&\u0019x";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "?\u0017q\ny<\u0013g";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "?\u0017q\ny<\u0002w\u001dR\u0010\u0002\u0002C";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x0126:
        r9 = 79;
        goto L_0x0020;
    L_0x012a:
        r9 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
        goto L_0x0020;
    L_0x012e:
        r9 = 22;
        goto L_0x0020;
    L_0x0132:
        r9 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.b.d.<clinit>():void");
    }

    private JSONObject d() {
        long j = 0;
        JSONObject jSONObject = new JSONObject();
        try {
            long j2;
            jSONObject.put(z[4], this.h == null ? 0 : this.h.f());
            jSONObject.put(z[1], this.g == null ? 0 : this.g.e());
            jSONObject.put(z[3], this.h == null ? 0 : this.h.e());
            String str = z[2];
            if (this.h == null || this.g == null) {
                j2 = 0;
            } else {
                j2 = c.a() - c.a();
                if (j2 < 0) {
                    j2 = 0;
                }
            }
            jSONObject.put(str, j2);
            str = z[0];
            if (this.h == null || this.g == null) {
                j2 = 0;
            } else {
                j2 = c.b() - c.b();
                if (j2 < 0) {
                    j2 = 0;
                }
            }
            jSONObject.put(str, j2);
            str = z[8];
            if (this.h == null || this.g == null) {
                j2 = 0;
            } else {
                j2 = c.c() - c.c();
                if (j2 < 0) {
                    j2 = 0;
                }
            }
            jSONObject.put(str, j2);
            str = z[7];
            if (this.h == null || this.g == null) {
                j2 = 0;
            } else {
                j2 = c.d() - c.d();
                if (j2 < 0) {
                    j2 = 0;
                }
            }
            jSONObject.put(str, j2);
            str = z[5];
            if (this.h == null || this.g == null) {
                j2 = 0;
            } else {
                j2 = this.h.h() - this.g.h();
                if (j2 < 0) {
                    j2 = 0;
                }
            }
            jSONObject.put(str, j2);
            str = z[6];
            if (!(this.h == null || this.g == null)) {
                j2 = this.h.g() - this.g.g();
                if (j2 >= 0) {
                    j = j2;
                }
            }
            jSONObject.put(str, j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final String a() {
        return this.a;
    }

    public final void a(int i) {
        this.f = i;
    }

    public final void a(long j) {
        this.c = j;
    }

    public final void a(c cVar) {
        this.g = cVar;
    }

    public final void a(d dVar) {
        this.e = dVar;
    }

    public final void a(String str) {
        this.a = str;
    }

    public final int b() {
        return this.f;
    }

    public final void b(long j) {
        this.d = j;
        this.b = j - this.c;
    }

    public final void b(c cVar) {
        this.h = cVar;
    }

    public final JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z[17], this.a);
            jSONObject.put(z[21], c.a((double) (((float) (this.d - this.c)) / 1000.0f), 2));
            jSONObject.put(z[20], this.d / 1000);
            jSONObject.put(z[23], this.c / 1000);
            jSONObject.put(z[22], this.f);
            if (this.e != null) {
                jSONObject.put(z[19], this.e.a);
            }
            Object d = d();
            if (!b.a(d)) {
                jSONObject.put(z[18], d);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final String toString() {
        return new StringBuilder(z[10]).append(this.a).append('\'').append(z[14]).append(this.b).append(z[11]).append(this.c).append(z[12]).append(this.d).append(z[16]).append(this.e).append(z[13]).append(this.f).append(z[15]).append(this.g).append(z[9]).append(this.h).append('}').toString();
    }
}
