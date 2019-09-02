package cn.jiguang.analytics.android.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.HandlerThread;
import cn.jiguang.analytics.android.c.a.b;
import cn.jiguang.analytics.android.c.d.a;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.SdkType;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

public final class m extends b {
    private static m c;
    private static final String[] z;
    private Context a;
    private AtomicBoolean b = new AtomicBoolean(false);
    private AtomicBoolean d = new AtomicBoolean(true);
    private HashMap<String, b> e = new HashMap();
    private n f;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 14;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0002S\u001bpz\u0013";
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
        r9 = 27;
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
        r1 = "\"S\u001bpz\u0013n\u0016qz\u0010F\u0005";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\"S\u001bpz\u0013n\u0016qz\u0010F\u0005?r\u0019J\u0003?x\u0018M\u0003zc\u0003\u0003\u001el;\u0019V\u001bs";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\"S\u001bpz\u0013n\u0016qz\u0010F\u0005?s\u0016PWvu\u001eW\u0012{";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0010J\u0001z;\u0002SWjk\u001bL\u0016{;[\u0003\u0019p;\u0016U\u0016sr\u0016A\u001bz;\u0019F\u0003ht\u0005H";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0002M\u0012gx\u0012S\u0003zW\u000eWz!";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u0014L\u0019q~\u0014W\u001eir\u0003Z";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0010J\u0001z;\u0002SWjk\u001bL\u0016{;[K\u0016l;\u0019L\u0003?I\u0012D\u001elo\u0012Q\u0012{";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "9F\u0003ht\u0005HWzi\u0005L\u0005";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\u0002S\u001bpz\u0013\u0003\u0013~o\u0016\u0019";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\"S\u001bpz\u0013n\u0016qz\u0010F\u0005?x\u0018M\u0003zc\u0003\u0003\u001el;\u0019V\u001bs7\u0007O\u0012~h\u0012\u0003\u0014~w\u001b\u0003\u001eqr\u0003\u0003\u001azo\u001fL\u0013";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "WO\u0018loW\u0003\u0007zi\u001aJ\u0004lr\u0018MW%;\u0016M\u0013mt\u001eGYo~\u0005N\u001elh\u001eL\u00191R9w2MU2w";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u0016M\u0013mt\u001eGYo~\u0005N\u001elh\u001eL\u00191R9w2MU2w";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "6M\u0013mt\u001eG\"kr\u001b";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x00b8:
        r9 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        goto L_0x0020;
    L_0x00bc:
        r9 = 35;
        goto L_0x0020;
    L_0x00c0:
        r9 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        goto L_0x0020;
    L_0x00c4:
        r9 = 31;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.a.m.<clinit>():void");
    }

    private m() {
        HandlerThread handlerThread = new HandlerThread(z[0]);
        handlerThread.start();
        this.f = new n(this, handlerThread.getLooper());
    }

    static /* synthetic */ void a(m mVar) {
        if (mVar.a == null) {
            b.g(z[1], z[10]);
            return;
        }
        int i;
        Context context = mVar.a;
        if (a.b(context, z[12])) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(z[6])).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                b.a(z[13], z[8]);
                i = 0;
            } else {
                i = 1;
            }
        } else {
            b.f(z[13], z[11]);
            i = 0;
        }
        if (i == 0) {
            b.f(z[1], z[4]);
        } else if (JCoreInterface.isValidRegistered()) {
            Object jSONArray = new JSONArray();
            for (Entry value : mVar.e.entrySet()) {
                b bVar = (b) value.getValue();
                if (bVar != null) {
                    try {
                        Object a = bVar.a();
                        if (!(a == null || cn.jiguang.analytics.android.c.d.b.a(a))) {
                            if (a instanceof JSONArray) {
                                JSONArray jSONArray2 = (JSONArray) a;
                                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                                    Object obj = (JSONObject) jSONArray2.get(i2);
                                    if (!cn.jiguang.analytics.android.c.d.b.a(obj)) {
                                        jSONArray.put(obj);
                                    }
                                }
                            } else {
                                jSONArray.put(a);
                            }
                        }
                    } catch (Exception e) {
                        b.f(z[1], new StringBuilder(z[5]).append(e.getMessage()).toString());
                    }
                }
            }
            b.b(z[1], new StringBuilder(z[9]).append(jSONArray.toString()).toString());
            if (!cn.jiguang.analytics.android.c.d.b.a(jSONArray)) {
                JCoreInterface.reportHttpData(mVar.a, jSONArray, SdkType.JANALYTICS.name());
            }
        } else {
            b.f(z[1], z[7]);
        }
    }

    public static m b() {
        if (c == null) {
            c = new m();
        }
        return c;
    }

    public final Object a() {
        return null;
    }

    public final void a(Context context) {
        if (context == null) {
            b.b(z[1], z[2]);
        } else if (this.b.get()) {
            b.b(z[1], z[3]);
        } else {
            this.a = context.getApplicationContext();
            this.b.set(true);
        }
    }

    public final void a(b bVar) {
        if (bVar != null && this.e.get(bVar.getClass().getName()) == null) {
            this.e.put(bVar.getClass().getName(), bVar);
        }
    }

    public final void c() {
        if (this.d.get()) {
            this.d.set(false);
            this.f.obtainMessage(1).sendToTarget();
        }
    }

    public final void d() {
        this.f.obtainMessage(1).sendToTarget();
    }

    public final void e() {
        if (this.f.hasMessages(2)) {
            this.f.removeMessages(2);
        }
    }

    public final void f() {
        if (!this.f.hasMessages(2)) {
            this.f.sendEmptyMessageDelayed(2, 2000);
        }
    }
}
