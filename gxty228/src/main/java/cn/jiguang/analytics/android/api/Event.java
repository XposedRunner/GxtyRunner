package cn.jiguang.analytics.android.api;

import android.text.TextUtils;
import cn.jiguang.analytics.android.c.a.b;
import cn.jiguang.analytics.android.c.d.c;
import cn.jiguang.analytics.android.d;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public abstract class Event implements Serializable {
    protected static final String ERROR_EXTRA_MAP_TOO_MANY;
    protected static final int EXT_KEY_COUNT_LIMIT = 10;
    protected static final String INVALIDE_KEY_OR_VALUE_TOO_BIGGER;
    protected static final String INVALIDE_KEY_OR_VALUE_UNSUPPORT_CHARSET;
    protected static final int KEY_VALUE_SIZE_LIMIT = 256;
    protected static final String REPEAT_KEY_ERROR;
    private static final String TAG;
    private static final long serialVersionUID = -5022833993443674004L;
    private static final String[] z;
    protected Map<String, String> extMap;
    @EVENTFIELD("itime")
    protected long itime;
    @EVENTFIELD("type")
    protected String type;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 15;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "Oz\b~7;e\frz2\u000ec<}~\u0004o&;p\bx%~w\u0003,1na\u0019c?;y\bu}ms\u0001y7;s\u0003hrv}\ti>;y\bu}ms\u0001y772\u0019d7;q\u0002b4w{\u000ex72\u000ey!o}\u0000,9~kBz3wg\b,%r~\u0001,0~2\fn3uv\u0002b7>Mm<2\u0019d7;w\u001bi<o2\u001ae>w2\u000firiw\u000ec w\t";
        r0 = 14;
        r4 = r3;
    L_0x000a:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x0051;
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
            case 0: goto L_0x00ea;
            case 1: goto L_0x00ee;
            case 2: goto L_0x00f2;
            case 3: goto L_0x00f6;
            default: goto L_0x001f;
        };
    L_0x001f:
        r9 = 82;
    L_0x0021:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x004f;
    L_0x0029:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0018;
    L_0x002d:
        REPEAT_KEY_ERROR = r1;
        r1 = "Ls\u001fb;uuM!r>aMe<x~\u0018h7;g\u0003'kb\u0002~&;q\u0005m hw\u0019 6ra\u000em 2\u0019d7;`\bo=iv";
        r0 = 15;
        goto L_0x000a;
    L_0x0034:
        INVALIDE_KEY_OR_VALUE_UNSUPPORT_CHARSET = r1;
        r1 = "Ls\u001fb;uuM!r~j\u0019~3;\f|rh{\u0017irraMk ~s\u0019i ;f\u0005m<;#] %r~\u0001,5rd\b,'k2\u001em$~2\u0019d;h2\bz7uf";
        r0 = 16;
        goto L_0x000a;
    L_0x003b:
        ERROR_EXTRA_MAP_TOO_MANY = r1;
        r1 = "^d\bb&";
        r0 = 17;
        goto L_0x000a;
    L_0x0042:
        TAG = r1;
        r1 = "Ks\u001fm?~f\b~rr|\u001bm>rvA,!o`\u0004b5;d\f`'~2\u001ee(~2\u0000y!o2\u000firww\u001eroz\fbr)'[,0bf\br62Hrh{\u0017irraM)6";
        r0 = 18;
        goto L_0x000a;
    L_0x0049:
        INVALIDE_KEY_OR_VALUE_TOO_BIGGER = r1;
        r1 = "~j\u0019A3k2\u0004rug\u0001`rt`Mi?kf\u0014";
        r0 = -1;
        goto L_0x000a;
    L_0x004f:
        r5 = r1;
        r1 = r7;
    L_0x0051:
        if (r5 > r6) goto L_0x0013;
    L_0x0053:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0067;
            case 1: goto L_0x006f;
            case 2: goto L_0x0077;
            case 3: goto L_0x007f;
            case 4: goto L_0x0087;
            case 5: goto L_0x0090;
            case 6: goto L_0x0099;
            case 7: goto L_0x00a3;
            case 8: goto L_0x00ae;
            case 9: goto L_0x00b9;
            case 10: goto L_0x00c4;
            case 11: goto L_0x00cf;
            case 12: goto L_0x00da;
            case 13: goto L_0x00e5;
            case 14: goto L_0x002d;
            case 15: goto L_0x0034;
            case 16: goto L_0x003b;
            case 17: goto L_0x0042;
            case 18: goto L_0x0049;
            default: goto L_0x005f;
        };
    L_0x005f:
        r3[r2] = r1;
        r2 = 1;
        r1 = "r|\u001bm>rv\b,9~kW";
        r0 = 0;
        r3 = r4;
        goto L_0x000a;
    L_0x0067:
        r3[r2] = r1;
        r2 = 2;
        r1 = "72\bt&Vs\u001d1u";
        r0 = 1;
        r3 = r4;
        goto L_0x000a;
    L_0x006f:
        r3[r2] = r1;
        r2 = 3;
        r1 = "72\u0004x;vwP";
        r0 = 2;
        r3 = r4;
        goto L_0x000a;
    L_0x0077:
        r3[r2] = r1;
        r2 = 4;
        r1 = "72\u0019u\"~/J";
        r0 = 3;
        r3 = r4;
        goto L_0x000a;
    L_0x007f:
        r3[r2] = r1;
        r2 = 5;
        r1 = "EI乭!鿷+?TmaS@V\r6OF(";
        r0 = 4;
        r3 = r4;
        goto L_0x000a;
    L_0x0087:
        r3[r2] = r1;
        r2 = 6;
        r1 = "B}\u0018,1z|Mc<wkMd3mwM=b;b\fe h2\u0002jrpw\u0014#$z~\u0018irzfMa=hfMe4;k\u0002yrz`\b,'h{\u0003krxg\u001ex=v2\u0006i+4d\f`'~";
        r0 = 5;
        r3 = r4;
        goto L_0x000a;
    L_0x0090:
        r3[r2] = r1;
        r2 = 7;
        r1 = "Oz\b~7;e\frz|Mi i}\u001f,3vMi*o`\f,?zb";
        r0 = 6;
        r3 = r4;
        goto L_0x000a;
    L_0x0099:
        r3[r2] = r1;
        r2 = 8;
        r1 = ";e\u0004x:;d\f`'~";
        r0 = 7;
        r3 = r4;
        goto L_0x000a;
    L_0x00a3:
        r3[r2] = r1;
        r2 = 9;
        r1 = "~j\u0019~3;";
        r0 = 8;
        r3 = r4;
        goto L_0x000a;
    L_0x00ae:
        r3[r2] = r1;
        r2 = 10;
        r1 = "Oz\u0004r~d\bb&;e\u0004`>;|\u0002xriw\u000ec >M;uq\b,+tgMd3mwMa=iwMx:z|M=b;b\fe h2\u0002jrxg\u001ex=v2\u0006i+4d\f`'~";
        r0 = 9;
        r3 = r4;
        goto L_0x000a;
    L_0x00b9:
        r3[r2] = r1;
        r2 = 11;
        r1 = "xg\u001ex=vM\u000ec'uf\u0004b5";
        r0 = 10;
        r3 = r4;
        goto L_0x000a;
    L_0x00c4:
        r3[r2] = r1;
        r2 = 12;
        r1 = "Ks\u001fm?~f\b~rr|\u001bm>rvA,7cf\u001fmrpw\u0014,=i2\u001bm>nwM;awMa'hfMn7;~\b!;f\u0005m<; X:ryk\u0019i!";
        r0 = 11;
        r3 = r4;
        goto L_0x000a;
    L_0x00cf:
        r3[r2] = r1;
        r2 = 13;
        r1 = "Ks\u001fm?~f\b~rr|\u001bm>rvA,!t\b,$z~\u0018irvg\u001exrywMb=o2\u0003y>w";
        r0 = 12;
        r3 = r4;
        goto L_0x000a;
    L_0x00da:
        r3[r2] = r1;
        r2 = 14;
        r1 = "Ks\u001fm?~f\b~rr|\u001bm>rvA,!o`\u0004b5;d\f`'~2\u001ee(~2\u0000y!o2\u000firww\u001eroz\fbr)'[,0bf\b";
        r0 = 13;
        r3 = r4;
        goto L_0x000a;
    L_0x00e5:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x00ea:
        r9 = 27;
        goto L_0x0021;
    L_0x00ee:
        r9 = 18;
        goto L_0x0021;
    L_0x00f2:
        r9 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        goto L_0x0021;
    L_0x00f6:
        r9 = 12;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.api.Event.<clinit>():void");
    }

    public Event() {
        this.type = z[11];
    }

    protected Event(String str) {
        this.type = str;
    }

    private void addExtMapInternal(Map<String, String> map) {
        if (map != null) {
            if (this.extMap == null) {
                this.extMap = new HashMap();
            }
            this.extMap.putAll(map);
        }
    }

    private boolean isValidKeyOrValue(String str) {
        return Pattern.compile(z[5]).matcher(str).matches();
    }

    public Event addExtMap(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            b.b(TAG, z[0]);
        } else {
            addExtMapInternal(map);
        }
        return this;
    }

    public Event addKeyValue(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            b.g(TAG, new StringBuilder(z[1]).append(str).toString());
        } else {
            if (this.extMap == null) {
                this.extMap = new HashMap();
            }
            this.extMap.put(str, str2);
        }
        return this;
    }

    public boolean checkEvent() {
        if (this.extMap == null || this.extMap.size() <= 10) {
            b.c(TAG, z[6]);
            if (this.extMap == null) {
                return true;
            }
            for (Entry entry : this.extMap.entrySet()) {
                try {
                    String str = (String) entry.getKey();
                    String str2 = (String) entry.getValue();
                    if (!isValideValue(str, new StringBuilder(z[9]).append(str).toString(), false, true) || !isValideValue(str2, new StringBuilder(z[9]).append(str).append(z[8]).toString(), false, true)) {
                        this.extMap.remove(str);
                        return false;
                    }
                } catch (Throwable e) {
                    b.b(TAG, z[7], e);
                }
            }
            return true;
        }
        b.i(TAG, z[10]);
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Event event = (Event) obj;
        return (this.extMap == null ? event.extMap != null : !this.extMap.equals(event.extMap)) ? false : this.type != null ? this.type.equals(event.type) : event.type == null;
    }

    public Map<String, String> getExtMap() {
        return this.extMap;
    }

    public long getItime() {
        return this.itime;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.extMap != null ? this.extMap.hashCode() : 0) * 31;
        if (this.type != null) {
            i = this.type.hashCode();
        }
        return hashCode + i;
    }

    protected boolean isValideValue(String str, String str2, boolean z) {
        return isValideValue(str, str2, z, false);
    }

    protected boolean isValideValue(String str, String str2, boolean z, boolean z2) {
        if (z && c.a(str)) {
            b.g(TAG, z[13]);
            return false;
        } else if (str == null || str.getBytes().length <= 256) {
            return true;
        } else {
            if (z2) {
                b.g(TAG, z[12]);
                return false;
            }
            b.g(TAG, z[14]);
            return false;
        }
    }

    protected void printLog(String str, String str2) {
        String str3 = REPEAT_KEY_ERROR;
        if (d.a) {
            b.g(str, str3);
        }
    }

    public Event setExtMap(Map<String, String> map) {
        this.extMap = map;
        return this;
    }

    public Event setItime(long j) {
        this.itime = j;
        return this;
    }

    public String toString() {
        return new StringBuilder(z[4]).append(this.type).append('\'').append(z[3]).append(this.itime).append(z[2]).append(this.extMap).append("'").toString();
    }
}
