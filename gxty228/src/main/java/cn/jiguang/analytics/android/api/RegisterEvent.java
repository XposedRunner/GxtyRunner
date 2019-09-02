package cn.jiguang.analytics.android.api;

import cn.jiguang.analytics.android.c.a.b;

public class RegisterEvent extends Event {
    private static final String TAG;
    private static final long serialVersionUID = 653526181981891184L;
    private static final String[] z;
    private boolean isSetRegisterSuccess = false;
    @EVENTFIELD("register_method")
    private String registerMethod;
    @EVENTFIELD("register_success")
    private boolean registerSuccess;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 9;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "q/%* W/0\u0006%F$6";
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
        r9 = 83;
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
        r1 = "@?17<N\u00150&4J96&!";
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
        r1 = "Q/%* W/0\u001c>F>*,7";
        r0 = 0;
        r3 = r4;
        goto L_0x000a;
    L_0x004b:
        r3[r2] = r1;
        r2 = 2;
        r1 = "w\"'16\u0003=#0sBj!,=E&+ '\u0003('7$F/,c0V96,>\u0003!':|U+.66\u0003+,'sN%&&?\u0003!':|U+.66\u000fj6+6\u0003)--5O#!76Gj!6 W%/c8F3m52O?'c$J&.c1Fj#!2M.--6Gfb\"=Gj6+6\u0003/4&=Wj5*?Oj &sQ/!,!G/&16D#176Q\u0015160@/10";
        r0 = 1;
        r3 = r4;
        goto L_0x000a;
    L_0x0053:
        r3[r2] = r1;
        r2 = 3;
        r1 = "w\"'16\u0003=#0sBj!,=E&+ '\u0003('7$F/,c0V96,>\u0003!':|U+.66\u0003+,'sN%&&?\u0003!':|U+.66\u000fj6+6\u0003)--5O#!76Gj!6 W%/c8F3m52O?'c$J&.c1Fj#!2M.--6Gfb\"=Gj6+6\u0003/4&=Wj5*?Oj &sQ/!,!G/&16D#176Q\u0015/&'K%&";
        r0 = 2;
        r3 = r4;
        goto L_0x000a;
    L_0x005b:
        r3[r2] = r1;
        r2 = 4;
        r1 = "Q/%* W/0\u000e6W\"-'";
        r0 = 3;
        r3 = r4;
        goto L_0x000a;
    L_0x0063:
        r3[r2] = r1;
        r2 = 5;
        r1 = "Q/%* W/0\u001c V)!& P";
        r0 = 4;
        r3 = r4;
        goto L_0x000a;
    L_0x006b:
        r3[r2] = r1;
        r2 = 6;
        r1 = "J$4\"?J.'c\u0001F-+0'F8\u000756M>bnsQ/%* W/0\u0010&@)'0 \u0003'70'\u00039'7sU+.66";
        r0 = 5;
        r3 = r4;
        goto L_0x000a;
    L_0x0073:
        r3[r2] = r1;
        r2 = 7;
        r1 = "q/%* W/0\u0006%F$68!F-+0'F8\u001160@/10n";
        r0 = 6;
        r3 = r4;
        goto L_0x000a;
    L_0x007b:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u000fj0&4J96&!n/6+<Gwe";
        r0 = 7;
        r3 = r4;
        goto L_0x000a;
    L_0x0084:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x0089:
        r9 = 35;
        goto L_0x0021;
    L_0x008c:
        r9 = 74;
        goto L_0x0021;
    L_0x008f:
        r9 = 66;
        goto L_0x0021;
    L_0x0092:
        r9 = 67;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.api.RegisterEvent.<clinit>():void");
    }

    public RegisterEvent() {
        super(z[0]);
    }

    public RegisterEvent(String str, boolean z) {
        super(z[0]);
        this.registerMethod = str;
        this.registerSuccess = z;
        this.isSetRegisterSuccess = true;
    }

    public boolean checkEvent() {
        if (!super.checkEvent()) {
            return false;
        }
        if (this.extMap != null) {
            if (this.extMap.containsKey(z[5])) {
                printLog(TAG, z[2]);
                this.extMap.remove(z[5]);
            }
            if (this.extMap.containsKey(z[1])) {
                printLog(TAG, z[3]);
                this.extMap.remove(z[1]);
            }
        }
        if (!isValideValue(this.registerMethod, z[4], true)) {
            return false;
        }
        if (this.isSetRegisterSuccess) {
            return true;
        }
        b.g(TAG, z[6]);
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
        RegisterEvent registerEvent = (RegisterEvent) obj;
        return (this.registerMethod == null ? registerEvent.registerMethod != null : !this.registerMethod.equals(registerEvent.registerMethod)) ? false : this.registerSuccess == registerEvent.registerSuccess;
    }

    public String getRegisterMthod() {
        return this.registerMethod;
    }

    public boolean getRegisterSuccess() {
        return this.registerSuccess;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.registerMethod != null ? this.registerMethod.hashCode() : 0) + (super.hashCode() * 31)) * 31;
        if (this.registerSuccess) {
            i = 1;
        }
        return hashCode + i;
    }

    public RegisterEvent setRegisterMethod(String str) {
        this.registerMethod = str;
        return this;
    }

    public RegisterEvent setRegisterSuccess(boolean z) {
        this.registerSuccess = z;
        this.isSetRegisterSuccess = true;
        return this;
    }

    public String toString() {
        return new StringBuilder(z[7]).append(this.registerSuccess).append(z[8]).append(this.registerMethod).append('\'').append(super.toString()).append('}').toString();
    }
}
