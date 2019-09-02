package cn.jiguang.analytics.android.b;

import cn.jiguang.analytics.android.c.d.b;
import cn.jiguang.analytics.android.c.d.c;
import cn.jiguang.api.JCoreInterface;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class e implements a {
    private static final String[] z;
    private String a = null;
    private long b;
    private long c;
    private long d;
    private List<d> e = new ArrayList();
    private long f;
    private long g;
    private c h;
    private c i;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 23;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "oPK: ";
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
            case 0: goto L_0x011b;
            case 1: goto L_0x011f;
            case 2: goto L_0x0123;
            case 3: goto L_0x0127;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 65;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "y\\^:5U\\V%$";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "yML;(eF`!%";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "oF[\u00175cEZ";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "zIX-\u001efAL<";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "zIX-";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "n]M)5cGQ\u00175cEZ";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "yML;(eF`)1zwK0\u001ehQK-2";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "yML;(eF`8)eFZ\u00175e\\^$\u001egMR'3s";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "yML;(eF`8)eFZ\u00175rw]15o[";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "yML;(eF`8)eFZ\u00173rw]15o[";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "yML;(eF`8)eFZ\u0017,eJV$$U\\G\u0017#s\\Z;";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "yML;(eF`8)eFZ\u0017,eJV$$UZG\u0017#s\\Z;";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "yML;(eF`)1zwM0\u001ehQK-2";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "&\bS)2~zZ;4gMk!,o\u0015";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "&\bL-2yAP&\u0004dLz05xI\u0002";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "&\bO)&omQ<(~Qs!2~\u0015";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "&\bL-2yAP&\u0005Z^<(eF\u0002";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "YML;(eFz&5c\\F32o[L!.da[uf";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "&\bL-2yAP&\u0012~IM<\u0015cEZu";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "&\bL-2yAP&\u0012~IM<\u0004r\\M)|";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "&\bS)2~x^=2o|V%$7";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "&\bL-2yAP&\u0004dLk!,o\u0015";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x011b:
        r9 = 10;
        goto L_0x0020;
    L_0x011f:
        r9 = 40;
        goto L_0x0020;
    L_0x0123:
        r9 = 63;
        goto L_0x0020;
    L_0x0127:
        r9 = 72;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.b.e.<clinit>():void");
    }

    private JSONObject c() {
        long j = 0;
        JSONObject jSONObject = new JSONObject();
        try {
            long j2;
            jSONObject.put(z[8], this.i == null ? 0 : this.i.f());
            String str = z[10];
            if (this.i == null || this.h == null) {
                j2 = 0;
            } else {
                j2 = c.a() - c.a();
                if (j2 < 0) {
                    j2 = 0;
                }
            }
            jSONObject.put(str, j2);
            str = z[9];
            if (this.i == null || this.h == null) {
                j2 = 0;
            } else {
                j2 = c.b() - c.b();
                if (j2 < 0) {
                    j2 = 0;
                }
            }
            jSONObject.put(str, j2);
            str = z[12];
            if (this.i == null || this.h == null) {
                j2 = 0;
            } else {
                j2 = c.c() - c.c();
                if (j2 < 0) {
                    j2 = 0;
                }
            }
            jSONObject.put(str, j2);
            str = z[11];
            if (this.i == null || this.h == null) {
                j2 = 0;
            } else {
                j2 = c.d() - c.d();
                if (j2 < 0) {
                    j2 = 0;
                }
            }
            jSONObject.put(str, j2);
            str = z[13];
            if (this.i == null || this.h == null) {
                j2 = 0;
            } else {
                j2 = this.i.h() - this.h.h();
                if (j2 < 0) {
                    j2 = 0;
                }
            }
            jSONObject.put(str, j2);
            str = z[7];
            if (!(this.i == null || this.h == null)) {
                j2 = this.i.g() - this.h.g();
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

    public final List<d> a() {
        return this.e;
    }

    public final void a(long j) {
        this.f = j;
    }

    public final void a(c cVar) {
        this.i = cVar;
    }

    public final void a(d dVar) {
        this.e.add(dVar);
    }

    public final void a(String str) {
        this.a = str;
    }

    public final JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z[2], this.a);
            jSONObject.put(z[1], this.b / 1000);
            jSONObject.put(z[3], this.c / 1000);
            jSONObject.put(z[6], c.a((double) (((float) (this.c - this.b)) / 1000.0f), 2));
            JCoreInterface.fillBaseReport(jSONObject, z[5]);
            if (this.e != null) {
                Object jSONArray = new JSONArray();
                for (d c : this.e) {
                    jSONArray.put(c.c());
                }
                jSONObject.put(z[4], b.a(jSONArray) ? "" : jSONArray);
            }
            Object c2 = c();
            if (!b.a(c2)) {
                jSONObject.put(z[0], c2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final void b(long j) {
        this.g = j;
    }

    public final void b(c cVar) {
        this.h = cVar;
    }

    public final void c(long j) {
        this.b = j;
    }

    public final void d(long j) {
        this.c = j;
        this.d = j - this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        return this.a != null ? this.a.equals(eVar.a) : eVar.a == null;
    }

    public final int hashCode() {
        return this.a != null ? this.a.hashCode() : 0;
    }

    public final String toString() {
        return new StringBuilder(z[18]).append(this.a).append('\'').append(z[19]).append(this.b).append(z[22]).append(this.c).append(z[17]).append(this.d).append(z[16]).append(this.e).append(z[21]).append(this.f).append(z[14]).append(this.g).append(z[20]).append(this.h).append(z[15]).append(this.i).append('}').toString();
    }
}
