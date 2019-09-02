package cn.jiguang.analytics.android.b;

import android.text.TextUtils;
import cn.jiguang.analytics.android.api.Currency;
import cn.jiguang.analytics.android.api.EVENTFIELD;
import cn.jiguang.analytics.android.api.Event;
import cn.jiguang.api.JCoreInterface;
import java.lang.reflect.Field;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class b implements a {
    private static final String[] z;
    private Event a;
    private int b;
    private int c = 1;
    private int d;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 14;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "^EX\u0011LO";
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
            case 0: goto L_0x00b8;
            case 1: goto L_0x00bc;
            case 2: goto L_0x00c0;
            case 3: goto L_0x00c4;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 33;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "7\u0013N\nU7\u000b_\rU\u000b\u001eN\u0012D\u001c\u0011\u0016";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "^EO\u0011\u001c";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "^E_\u001dQ\u0017X";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "73n*u4,n(eREB\u0017\u0001\u001c\u0010G\b\u001b";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0007\u000bN\u001cB\u0017\u0015_\u0001ERH\u000b\u0011O\u0001\u0010[\u0014N\u0000\u0011\u000b\u0010X\u0002\u0000\u0011";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u0013\u0011_\u0016H\u0010\u0010_\u0001R";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u001dE_\u001dQ\u0017EB\u0017\u0001!\u0011Y\rO\u0015";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "7\u0013N\nU7\u000b_\rU\u000b";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\u0017\u0013N\nUR\u0003B\u0001M\u0016E]\u0005M\u0007\u0000\u000b\rRR\u000bD\u0010\u0001\u001f\u0004[^";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0013\u0006G\u0017\u0001O";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0014\fN\bER\u0004E\nN\u0006\u0004_\rN\u001cEE\u0005L\u0017E\u0011";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "^E_\u001dQ\u0017_";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\u001dEB\u0017\u0001\u001c\u0010G\b\u0001\u0005\f_\f\u0001\u0014\fN\bER\u000bJ\tDH";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x00b8:
        r9 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        goto L_0x0020;
    L_0x00bc:
        r9 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0020;
    L_0x00c0:
        r9 = 43;
        goto L_0x0020;
    L_0x00c4:
        r9 = 100;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.b.b.<clinit>():void");
    }

    public final Event a() {
        return this.a;
    }

    public final void a(int i) {
        this.b = 1;
    }

    public final void a(Event event) {
        this.a = event;
    }

    public final JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        JCoreInterface.fillBaseReport(jSONObject, null);
        try {
            if (this.a != null) {
                JSONObject a = cn.jiguang.analytics.android.c.d.b.a(this.a.getExtMap());
                JSONObject jSONObject2 = cn.jiguang.analytics.android.c.d.b.a((Object) a) ? new JSONObject() : a;
                for (Class cls = this.a.getClass(); cls != null; cls = cls.getSuperclass()) {
                    cn.jiguang.analytics.android.c.a.b.a(z[8], new StringBuilder(z[10]).append(cls.getCanonicalName()).toString());
                    boolean equals = cls.getCanonicalName().equals(Event.class.getCanonicalName());
                    for (Field field : cls.getDeclaredFields()) {
                        field.setAccessible(true);
                        EVENTFIELD eventfield = (EVENTFIELD) field.getAnnotation(EVENTFIELD.class);
                        if (eventfield == null) {
                            cn.jiguang.analytics.android.c.a.b.a(z[8], new StringBuilder(z[4]).append(field.getName()).toString());
                        } else {
                            String value = eventfield.value();
                            cn.jiguang.analytics.android.c.a.b.a(z[8], new StringBuilder(z[11]).append(value).toString());
                            if (TextUtils.isEmpty(value)) {
                                continue;
                            } else {
                                Object obj = field.get(this.a);
                                if (obj == null) {
                                    cn.jiguang.analytics.android.c.a.b.a(z[8], new StringBuilder(z[13]).append(field.getName()).append(z[12]).append(field.getType()).toString());
                                    if (field.getType().equals(String.class)) {
                                        cn.jiguang.analytics.android.c.a.b.a(z[8], z[7]);
                                        obj = "";
                                    } else {
                                        continue;
                                    }
                                }
                                if (!(obj instanceof Map)) {
                                    if (equals) {
                                        try {
                                            jSONObject.put(value, obj);
                                        } catch (Exception e) {
                                            cn.jiguang.analytics.android.c.a.b.f(z[8], new StringBuilder(z[5]).append(obj.getClass().getCanonicalName()).toString());
                                        }
                                    } else {
                                        try {
                                            if (obj instanceof Currency) {
                                                jSONObject2.put(value, ((Currency) obj).name());
                                            } else {
                                                jSONObject2.put(value, obj);
                                            }
                                        } catch (Exception e2) {
                                            cn.jiguang.analytics.android.c.a.b.f(z[8], new StringBuilder(z[5]).append(obj.getClass().getCanonicalName()).toString());
                                        }
                                    }
                                    cn.jiguang.analytics.android.c.a.b.a(z[8], new StringBuilder(z[9]).append(field.getName()).toString());
                                } else if (equals) {
                                    cn.jiguang.analytics.android.c.d.b.a(jSONObject2, (Map) obj);
                                }
                            }
                        }
                    }
                    if (equals) {
                        break;
                    }
                }
                jSONObject.put(z[6], jSONObject2);
            }
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (JSONException e4) {
            e4.printStackTrace();
        }
        return jSONObject;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((b) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return new StringBuilder(z[1]).append(this.a).append(z[0]).append(this.c).append(z[2]).append(this.d).append(z[3]).append(this.b).append('}').toString();
    }
}
