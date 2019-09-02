package cn.jiguang.analytics.android.api;

import cn.jiguang.analytics.android.c.a.b;

public class LoginEvent extends Event {
    private static final String TAG;
    private static final long serialVersionUID = -659790421926834748L;
    private static final String[] z;
    private boolean isInitLoginSuccess = false;
    @EVENTFIELD("login_method")
    private String loginMethod;
    @EVENTFIELD("login_success")
    private boolean loginSuccess;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 9;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "'\u00117\b.\b5x\u0012";
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
        r9 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
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
        r1 = "\u0007\u00117\b4\r%u\u0005\u000e\r#";
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
        r1 = "\u0002\u0010&w\n\u0002\u001a56*\u0004\u00199x#\u001d\u001b>bFF^<y\u0001\u0002\u0010\u0003c\u0005\b\u001b#eF\u0006\u000b#bF\u0018\u001b$6\u0010\n\u0012%s";
        r0 = 0;
        r3 = r4;
        goto L_0x000a;
    L_0x004b:
        r3[r2] = r1;
        r2 = 2;
        r1 = "?\u00165d\u0003K\t1eF\n^3y\b\r\u00129u\u0012K\u001c5b\u0011\u000e\u001b>6\u0005\u001e\r$y\u000bK\u00155oI\u001d\u001f<c\u0003K\u001f>rF\u0006\u00114s\nK\u00155oI\u001d\u001f<c\u0003G^$~\u0003K\u001d?x\u0000\u0007\u00173b\u0003\u000f^3c\u0015\u001f\u0011=6\r\u000e\u0007`\u0007\u0007\u000b56\u0011\u0002\u0012<6\u0004\u000e^1t\u0007\u0005\u001a?x\u0003\u000fRpw\b\u000f^$~\u0003K\u001b&s\b\u001f^'\n\u0007^2sF\u0019\u001b3y\u0014\u000f\u001b4z\t\f\u0017>I\u0015\u001e\u001d3s\u0015\u0018";
        r0 = 1;
        r3 = r4;
        goto L_0x000a;
    L_0x0053:
        r3[r2] = r1;
        r2 = 3;
        r1 = "?\u00165d\u0003K\t1eF\n^3y\b\r\u00129u\u0012K\u001c5b\u0011\u000e\u001b>6\u0005\u001e\r$y\u000bK\u00155oI\u001d\u001f<c\u0003K\u001f>rF\u0006\u00114s\nK\u00155oI\u001d\u001f<c\u0003G^$~\u0003K\u001d?x\u0000\u0007\u00173b\u0003\u000f^3c\u0015\u001f\u0011=6\r\u000e\u0007`\u0007\u0007\u000b56\u0011\u0002\u0012<6\u0004\u000e^1t\u0007\u0005\u001a?x\u0003\u000fRpw\b\u000f^$~\u0003K\u001b&s\b\u001f^'\n\u0007^2sF\u0019\u001b3y\u0014\u000f\u001b4z\t\f\u0017>I\u000b\u000e\n8y\u0002";
        r0 = 2;
        r3 = r4;
        goto L_0x000a;
    L_0x005b:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0007\u00117\b&\u001b$~\t\u000f";
        r0 = 3;
        r3 = r4;
        goto L_0x000a;
    L_0x0063:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0007\u00117\b4\u00135b\u000e\u0004\u001a";
        r0 = 4;
        r3 = r4;
        goto L_0x000a;
    L_0x006b:
        r3[r2] = r1;
        r2 = 6;
        r1 = "G^<y\u0001\u0002\u0010\u0003c\u0005\b\u001b#e[";
        r0 = 5;
        r3 = r4;
        goto L_0x000a;
    L_0x0073:
        r3[r2] = r1;
        r2 = 7;
        r1 = "'\u00117\b.\b5x\u0012\u0010\u0012?q\u000f\u000535b\u000e\u0004\u001am1";
        r0 = 6;
        r3 = r4;
        goto L_0x000a;
    L_0x007b:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\b\u000b#b\t\u0006!<y\u0001\u0002\u0010";
        r0 = 7;
        r3 = r4;
        goto L_0x000a;
    L_0x0084:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x0089:
        r9 = 107; // 0x6b float:1.5E-43 double:5.3E-322;
        goto L_0x0021;
    L_0x008c:
        r9 = 126; // 0x7e float:1.77E-43 double:6.23E-322;
        goto L_0x0021;
    L_0x008f:
        r9 = 80;
        goto L_0x0021;
    L_0x0092:
        r9 = 22;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.api.LoginEvent.<clinit>():void");
    }

    public LoginEvent() {
        super(z[8]);
    }

    public LoginEvent(String str, boolean z) {
        super(z[8]);
        this.loginMethod = str;
        this.loginSuccess = z;
        this.isInitLoginSuccess = true;
    }

    public boolean checkEvent() {
        if (!super.checkEvent()) {
            return false;
        }
        if (this.extMap != null) {
            if (this.extMap.containsKey(z[5])) {
                printLog(TAG, z[3]);
                this.extMap.remove(z[5]);
            }
            if (this.extMap.containsKey(z[0])) {
                printLog(TAG, z[2]);
                this.extMap.remove(z[0]);
            }
        }
        if (!isValideValue(this.loginMethod, z[4], true)) {
            return false;
        }
        if (this.isInitLoginSuccess) {
            return true;
        }
        b.c(TAG, z[1]);
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
        LoginEvent loginEvent = (LoginEvent) obj;
        return (this.loginMethod == null ? loginEvent.loginMethod != null : !this.loginMethod.equals(loginEvent.loginMethod)) ? false : this.loginSuccess == loginEvent.loginSuccess;
    }

    public String getLoginMethod() {
        return this.loginMethod;
    }

    public boolean getLoginSuccess() {
        return this.loginSuccess;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.loginMethod != null ? this.loginMethod.hashCode() : 0) + (super.hashCode() * 31)) * 31;
        if (this.loginSuccess) {
            i = 1;
        }
        return hashCode + i;
    }

    public LoginEvent setLoginMethod(String str) {
        this.loginMethod = str;
        return this;
    }

    public LoginEvent setLoginSuccess(boolean z) {
        this.loginSuccess = z;
        this.isInitLoginSuccess = true;
        return this;
    }

    public String toString() {
        return new StringBuilder(z[7]).append(this.loginMethod).append('\'').append(z[6]).append(this.loginSuccess).append(super.toString()).append('}').toString();
    }
}
