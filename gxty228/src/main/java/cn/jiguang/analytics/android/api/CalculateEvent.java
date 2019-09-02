package cn.jiguang.analytics.android.api;

import cn.jiguang.analytics.android.c.a.b;

public class CalculateEvent extends Event {
    private static final String TAG;
    private static final long serialVersionUID = -1196159554106635413L;
    private static final String[] z;
    @EVENTFIELD("event_id")
    private String eventId;
    @EVENTFIELD("event_value")
    private double eventValue;
    private boolean isInitEventValue = false;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 9;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "v0&MTY0>KdC4$Z";
        r0 = 8;
        r4 = r3;
    L_0x000a:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x0035;
    L_0x0013:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0018:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0089;
            case 1: goto L_0x008c;
            case 2: goto L_0x008f;
            case 3: goto L_0x0092;
            default: goto L_0x001f;
        };
    L_0x001f:
        r9 = 33;
    L_0x0021:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x0033;
    L_0x0029:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0018;
    L_0x002d:
        TAG = r1;
        r1 = "\\?<OM\\5/\u000ebT=)[MT%/kWP?>\u000e\f\u00154<KOA\u0007+BTPq'[RAq9KU\u0015'+BTP";
        r0 = -1;
        goto L_0x000a;
    L_0x0033:
        r5 = r1;
        r1 = r7;
    L_0x0035:
        if (r5 > r6) goto L_0x0013;
    L_0x0037:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x004b;
            case 1: goto L_0x0053;
            case 2: goto L_0x005b;
            case 3: goto L_0x0063;
            case 4: goto L_0x006b;
            case 5: goto L_0x0073;
            case 6: goto L_0x007b;
            case 7: goto L_0x0084;
            case 8: goto L_0x002d;
            default: goto L_0x0043;
        };
    L_0x0043:
        r3[r2] = r1;
        r2 = 1;
        r1 = "P'/@U|5";
        r0 = 0;
        r3 = r4;
        goto L_0x000a;
    L_0x004b:
        r3[r2] = r1;
        r2 = 2;
        r1 = "P'/@Uj'+BTP";
        r0 = 1;
        r3 = r4;
        goto L_0x000a;
    L_0x0053:
        r3[r2] = r1;
        r2 = 3;
        r1 = "a9/\\D\u0015&+]\u0001Tq)AOS=#MU\u00153/ZVP4$\u000eB@\">AL\u0015:/W\u000eC0&[D\u00150$J\u0001X>.KM\u0015:/W\u000eC0&[D\u0019q>FD\u00152%@GY8)ZDQq)[RA>'\u000eJP(eX@Y$/\u000eV\\=&\u000eCPq+L@[5%@DQ}jOOQq>FD\u00154<KOAq=GMYq(K\u0001G4)ASQ4.KWP?>qWT=?K";
        r0 = 2;
        r3 = r4;
        goto L_0x000a;
    L_0x005b:
        r3[r2] = r1;
        r2 = 4;
        r1 = "P'/@Uj8.";
        r0 = 3;
        r3 = r4;
        goto L_0x000a;
    L_0x0063:
        r3[r2] = r1;
        r2 = 5;
        r1 = "a9/\\D\u0015&+]\u0001Tq)AOS=#MU\u00153/ZVP4$\u000eB@\">AL\u0015:/W\u000eC0&[D\u00150$J\u0001X>.KM\u0015:/W\u000eC0&[D\u0019q>FD\u00152%@GY8)ZDQq)[RA>'\u000eJP(eX@Y$/\u000eV\\=&\u000eCPq+L@[5%@DQ}jOOQq>FD\u00154<KOAq=GMYq(K\u0001G4)ASQ4.KWP?>qHQ";
        r0 = 4;
        r3 = r4;
        goto L_0x000a;
    L_0x006b:
        r3[r2] = r1;
        r2 = 6;
        r1 = "V$9ZNX\u000e)OMV$&OUP";
        r0 = 5;
        r3 = r4;
        goto L_0x000a;
    L_0x0073:
        r3[r2] = r1;
        r2 = 7;
        r1 = "v0&MTY0>KdC4$ZZP'/@U|5w\t";
        r0 = 6;
        r3 = r4;
        goto L_0x000a;
    L_0x007b:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u0019q/XD[%\u001cOM@4w";
        r0 = 7;
        r3 = r4;
        goto L_0x000a;
    L_0x0084:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x0089:
        r9 = 53;
        goto L_0x0021;
    L_0x008c:
        r9 = 81;
        goto L_0x0021;
    L_0x008f:
        r9 = 74;
        goto L_0x0021;
    L_0x0092:
        r9 = 46;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.api.CalculateEvent.<clinit>():void");
    }

    public CalculateEvent() {
        super(z[6]);
    }

    public CalculateEvent(String str, double d) {
        super(z[6]);
        this.eventId = str;
        this.eventValue = d;
        this.isInitEventValue = true;
    }

    public CalculateEvent addEventValue(double d) {
        this.eventValue += d;
        return this;
    }

    public boolean checkEvent() {
        if (!super.checkEvent()) {
            return false;
        }
        if (this.extMap != null) {
            if (this.extMap.containsKey(z[4])) {
                printLog(TAG, z[5]);
                this.extMap.remove(z[4]);
            }
            if (this.extMap.containsKey(z[2])) {
                printLog(TAG, z[3]);
                this.extMap.remove(z[2]);
            }
        }
        if (!isValideValue(this.eventId, z[1], true)) {
            return false;
        }
        if (this.isInitEventValue) {
            return true;
        }
        b.g(TAG, z[0]);
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        CalculateEvent calculateEvent = (CalculateEvent) obj;
        if (this.eventId != null) {
            if (this.eventId.equals(calculateEvent.eventId)) {
                return true;
            }
        } else if (calculateEvent.eventId == null) {
            return true;
        }
        return false;
    }

    public String getEventId() {
        return this.eventId;
    }

    public double getEventValue() {
        return this.eventValue;
    }

    public int hashCode() {
        return (this.eventId != null ? this.eventId.hashCode() : 0) + (super.hashCode() * 31);
    }

    public CalculateEvent setEventId(String str) {
        this.eventId = str;
        return this;
    }

    public CalculateEvent setEventValue(double d) {
        this.eventValue = d;
        this.isInitEventValue = true;
        return this;
    }

    public String toString() {
        return new StringBuilder(z[7]).append(this.eventId).append('\'').append(z[8]).append(this.eventValue).append(super.toString()).append('}').toString();
    }
}
