package cn.jiguang.analytics.android.api;

public class BrowseEvent extends Event {
    private static final String TAG;
    private static final long serialVersionUID = -786622478131339887L;
    private static final String[] z;
    @EVENTFIELD("browse_duration")
    private float browseDuration;
    @EVENTFIELD("browse_content_id")
    private String browseId;
    @EVENTFIELD("browse_name")
    private String browseName;
    @EVENTFIELD("browse_type")
    private String browseType;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 16;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "dJ=\bqC}$\u001alR";
        r0 = 15;
        r4 = r3;
    L_0x000a:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x0034;
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
            case 0: goto L_0x00d5;
            case 1: goto L_0x00d9;
            case 2: goto L_0x00dd;
            case 3: goto L_0x00e1;
            default: goto L_0x001f;
        };
    L_0x001f:
        r9 = 2;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x0032;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0018;
    L_0x002c:
        TAG = r1;
        r1 = "EM!\u000bmKg0\rmQK7";
        r0 = -1;
        goto L_0x000a;
    L_0x0032:
        r5 = r1;
        r1 = r7;
    L_0x0034:
        if (r5 > r6) goto L_0x0013;
    L_0x0036:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x004a;
            case 1: goto L_0x0052;
            case 2: goto L_0x005a;
            case 3: goto L_0x0062;
            case 4: goto L_0x006a;
            case 5: goto L_0x0072;
            case 6: goto L_0x007a;
            case 7: goto L_0x0083;
            case 8: goto L_0x008e;
            case 9: goto L_0x0099;
            case 10: goto L_0x00a4;
            case 11: goto L_0x00af;
            case 12: goto L_0x00ba;
            case 13: goto L_0x00c5;
            case 14: goto L_0x00d0;
            case 15: goto L_0x002c;
            default: goto L_0x0042;
        };
    L_0x0042:
        r3[r2] = r1;
        r2 = 1;
        r1 = "dJ=\bqC}$\u001alRC0\rmQK76f\u001b\u001f";
        r0 = 0;
        r3 = r4;
        goto L_0x000a;
    L_0x004a:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\n\u00180\rmQK71cK]oX";
        r0 = 1;
        r3 = r4;
        goto L_0x000a;
    L_0x0052:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\n\u00180\rmQK7+{V]oX";
        r0 = 2;
        r3 = r4;
        goto L_0x000a;
    L_0x005a:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\n\u00180\rmQK7;wTY&\u0016mH\u0005";
        r0 = 3;
        r3 = r4;
        goto L_0x000a;
    L_0x0062:
        r3[r2] = r1;
        r2 = 5;
        r1 = "rP7\rg\u0006O3\f\"G\u00181\u0010l@T;\u001cv\u0006Z7\u000buC]<_aSK&\u0010o\u0006S7\u0006-PY>\ng\u0006Y<\u001b\"KW6\u001an\u0006S7\u0006-PY>\ng\n\u0018&\u0017g\u0006[=\u0011dJQ1\u000bgB\u00181\nqRW?_iCA}\tcJM7_uOT>_`C\u00183\u001dcH\\=\u0011gB\u0014r\u001elB\u0018&\u0017g\u0006]$\u001alR\u0018%\u0016nJ\u00180\u001a\"T]1\u0010pB]6\u001dpIO!\u001a]RA\"\u001a";
        r0 = 4;
        r3 = r4;
        goto L_0x000a;
    L_0x006a:
        r3[r2] = r1;
        r2 = 6;
        r1 = "rP7\rg\u0006O3\f\"G\u00181\u0010l@T;\u001cv\u0006Z7\u000buC]<_aSK&\u0010o\u0006S7\u0006-PY>\ng\u0006Y<\u001b\"KW6\u001an\u0006S7\u0006-PY>\ng\n\u0018&\u0017g\u0006[=\u0011dJQ1\u000bgB\u00181\nqRW?_iCA}\tcJM7_uOT>_`C\u00183\u001dcH\\=\u0011gB\u0014r\u001elB\u0018&\u0017g\u0006]$\u001alR\u0018%\u0016nJ\u00180\u001a\"T]1\u0010pB]6\u001dpIO!\u001a]BM \u001evOW<";
        r0 = 5;
        r3 = r4;
        goto L_0x000a;
    L_0x0072:
        r3[r2] = r1;
        r2 = 7;
        r1 = "rP7\rg\u0006O3\f\"G\u00181\u0010l@T;\u001cv\u0006Z7\u000buC]<_aSK&\u0010o\u0006S7\u0006-PY>\ng\u0006Y<\u001b\"KW6\u001an\u0006S7\u0006-PY>\ng\n\u0018&\u0017g\u0006[=\u0011dJQ1\u000bgB\u00181\nqRW?_iCA}\tcJM7_uOT>_`C\u00183\u001dcH\\=\u0011gB\u0014r\u001elB\u0018&\u0017g\u0006]$\u001alR\u0018%\u0016nJ\u00180\u001a\"T]1\u0010pB]6\u001dpIO!\u001a]EW<\u000bgHL\r\u0016f";
        r0 = 6;
        r3 = r4;
        goto L_0x000a;
    L_0x007a:
        r3[r2] = r1;
        r2 = 8;
        r1 = "rP7\rg\u0006O3\f\"G\u00181\u0010l@T;\u001cv\u0006Z7\u000buC]<_aSK&\u0010o\u0006S7\u0006-PY>\ng\u0006Y<\u001b\"KW6\u001an\u0006S7\u0006-PY>\ng\n\u0018&\u0017g\u0006[=\u0011dJQ1\u000bgB\u00181\nqRW?_iCA}\tcJM7_uOT>_`C\u00183\u001dcH\\=\u0011gB\u0014r\u001elB\u0018&\u0017g\u0006]$\u001alR\u0018%\u0016nJ\u00180\u001a\"T]1\u0010pB]6\u001dpIO!\u001a]HY?\u001a";
        r0 = 7;
        r3 = r4;
        goto L_0x000a;
    L_0x0083:
        r3[r2] = r1;
        r2 = 9;
        r1 = "DJ=\bqCv3\u0012g";
        r0 = 8;
        r3 = r4;
        goto L_0x000a;
    L_0x008e:
        r3[r2] = r1;
        r2 = 10;
        r1 = "DJ=\bqCl+\u000fg";
        r0 = 9;
        r3 = r4;
        goto L_0x000a;
    L_0x0099:
        r3[r2] = r1;
        r2 = 11;
        r1 = "DJ=\bqCg<\u001eoC";
        r0 = 10;
        r3 = r4;
        goto L_0x000a;
    L_0x00a4:
        r3[r2] = r1;
        r2 = 12;
        r1 = "DJ=\bqCg6\npGL;\u0010l";
        r0 = 11;
        r3 = r4;
        goto L_0x000a;
    L_0x00af:
        r3[r2] = r1;
        r2 = 13;
        r1 = "DJ=\bqCg&\u0006rC";
        r0 = 12;
        r3 = r4;
        goto L_0x000a;
    L_0x00ba:
        r3[r2] = r1;
        r2 = 14;
        r1 = "DJ=\bqCg1\u0010lR]<\u000b]O\\";
        r0 = 13;
        r3 = r4;
        goto L_0x000a;
    L_0x00c5:
        r3[r2] = r1;
        r2 = 15;
        r1 = "DJ=\bqCq6";
        r0 = 14;
        r3 = r4;
        goto L_0x000a;
    L_0x00d0:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x00d5:
        r9 = 38;
        goto L_0x0020;
    L_0x00d9:
        r9 = 56;
        goto L_0x0020;
    L_0x00dd:
        r9 = 82;
        goto L_0x0020;
    L_0x00e1:
        r9 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.api.BrowseEvent.<clinit>():void");
    }

    public BrowseEvent() {
        super(z[0]);
    }

    public BrowseEvent(String str, String str2, String str3, float f) {
        super(z[0]);
        this.browseId = str;
        this.browseName = str2;
        this.browseType = str3;
        this.browseDuration = f;
    }

    public boolean checkEvent() {
        if (!super.checkEvent()) {
            return false;
        }
        if (this.extMap != null) {
            if (this.extMap.containsKey(z[14])) {
                printLog(TAG, z[7]);
                this.extMap.remove(z[14]);
            }
            if (this.extMap.containsKey(z[13])) {
                printLog(TAG, z[5]);
                this.extMap.remove(z[13]);
            }
            if (this.extMap.containsKey(z[12])) {
                printLog(TAG, z[6]);
                this.extMap.remove(z[12]);
            }
            if (this.extMap.containsKey(z[11])) {
                printLog(TAG, z[8]);
                this.extMap.remove(z[11]);
            }
        }
        return isValideValue(this.browseName, z[9], true) && isValideValue(this.browseId, z[15], false) && isValideValue(this.browseType, z[10], false);
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
        BrowseEvent browseEvent = (BrowseEvent) obj;
        return (this.browseId == null ? browseEvent.browseId != null : !this.browseId.equals(browseEvent.browseId)) ? false : (this.browseName == null ? browseEvent.browseName != null : !this.browseName.equals(browseEvent.browseName)) ? false : (this.browseType == null ? browseEvent.browseType != null : !this.browseType.equals(browseEvent.browseType)) ? false : this.browseDuration == browseEvent.browseDuration;
    }

    public float getBrowseDuration() {
        return this.browseDuration;
    }

    public String getBrowseId() {
        return this.browseId;
    }

    public String getBrowseName() {
        return this.browseName;
    }

    public String getBrowseType() {
        return this.browseType;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.browseName != null ? this.browseName.hashCode() : 0) + (((this.browseId != null ? this.browseId.hashCode() : 0) + (super.hashCode() * 31)) * 31)) * 31;
        if (this.browseType != null) {
            i = this.browseType.hashCode();
        }
        return ((hashCode + i) * 31) + ((int) this.browseDuration);
    }

    public BrowseEvent setBrowseDuration(float f) {
        this.browseDuration = f;
        return this;
    }

    public BrowseEvent setBrowseId(String str) {
        this.browseId = str;
        return this;
    }

    public BrowseEvent setBrowseName(String str) {
        this.browseName = str;
        return this;
    }

    public BrowseEvent setBrowseType(String str) {
        this.browseType = str;
        return this;
    }

    public String toString() {
        return new StringBuilder(z[1]).append(this.browseId).append('\'').append(z[2]).append(this.browseName).append('\'').append(z[3]).append(this.browseType).append('\'').append(z[4]).append(this.browseDuration).append(super.toString()).append('}').toString();
    }
}
