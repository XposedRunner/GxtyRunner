package cn.jiguang.analytics.android.api;

public class CountEvent extends Event {
    private static final String TAG;
    private static final long serialVersionUID = -5160794065476176753L;
    private static final String[] z;
    @EVENTFIELD("event_id")
    private String eventId;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 5;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "5*N+Y33^+Y";
        r0 = 4;
        r4 = r3;
    L_0x0008:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x0033;
    L_0x0011:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0016:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0066;
            case 1: goto L_0x0069;
            case 2: goto L_0x006c;
            case 3: goto L_0x006f;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 45;
    L_0x001f:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x0031;
    L_0x0027:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0016;
    L_0x002b:
        TAG = r1;
        r1 = "\"-^7HV2Z6\r\u0017eX*C\u0010)R&YV'^1Z\u0013 UeN\u00036O*@V.^<\u0002\u0000$W0HV$U!\r\u001b*_ AV.^<\u0002\u0000$W0HZeO-HV&T+K\u001a,X1H\u0012eX0^\u0002*VeF\u0013<\u00143L\u001a0^eZ\u001f)WeO\u0013eZ'L\u0018!T+H\u0012i\u001b$C\u0012eO-HV M C\u0002eL,A\u001aeY \r\u0004 X*_\u0012 _ [\u0013+O\u001aD\u0012";
        r0 = -1;
        goto L_0x0008;
    L_0x0031:
        r5 = r1;
        r1 = r7;
    L_0x0033:
        if (r5 > r6) goto L_0x0011;
    L_0x0035:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0049;
            case 1: goto L_0x0051;
            case 2: goto L_0x0059;
            case 3: goto L_0x0061;
            case 4: goto L_0x002b;
            default: goto L_0x0041;
        };
    L_0x0041:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u00133^+Y),_";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0049:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u00133^+Y?!";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x0051:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u00150H1B\u001b\u001aX*X\u00181R+J";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0059:
        r3[r2] = r1;
        r2 = 4;
        r1 = "5*N+Y33^+Y\r M C\u0002\f_x\n";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x0061:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x0066:
        r9 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
        goto L_0x001f;
    L_0x0069:
        r9 = 69;
        goto L_0x001f;
    L_0x006c:
        r9 = 59;
        goto L_0x001f;
    L_0x006f:
        r9 = 69;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.api.CountEvent.<clinit>():void");
    }

    public CountEvent() {
        super(z[3]);
    }

    public CountEvent(String str) {
        super(z[3]);
        this.eventId = str;
    }

    public boolean checkEvent() {
        if (!super.checkEvent()) {
            return false;
        }
        if (this.extMap != null && this.extMap.containsKey(z[1])) {
            printLog(TAG, z[0]);
            this.extMap.remove(z[1]);
        }
        return isValideValue(this.eventId, z[2], true);
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
        CountEvent countEvent = (CountEvent) obj;
        if (this.eventId != null) {
            if (this.eventId.equals(countEvent.eventId)) {
                return true;
            }
        } else if (countEvent.eventId == null) {
            return true;
        }
        return false;
    }

    public String getEventId() {
        return this.eventId;
    }

    public int hashCode() {
        return (this.eventId != null ? this.eventId.hashCode() : 0) + (super.hashCode() * 31);
    }

    public CountEvent setEventId(String str) {
        this.eventId = str;
        return this;
    }

    public String toString() {
        return new StringBuilder(z[4]).append(this.eventId).append('\'').append(super.toString()).append('}').toString();
    }
}
