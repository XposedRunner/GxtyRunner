package cn.jiguang.d.c;

import java.io.Serializable;
import java.text.DecimalFormat;

public final class j implements Serializable, Comparable {
    public static final j a;
    public static final j b;
    private static final byte[] f = new byte[]{(byte) 0};
    private static final byte[] g = new byte[]{(byte) 1, (byte) 42};
    private static final DecimalFormat h = new DecimalFormat();
    private static final byte[] i = new byte[256];
    private static final j j;
    private byte[] c;
    private long d;
    private int e;

    static {
        h.setMinimumIntegerDigits(3);
        int i = 0;
        while (i < i.length) {
            if (i < 65 || i > 90) {
                i[i] = (byte) i;
            } else {
                i[i] = (byte) ((i - 65) + 97);
            }
            i++;
        }
        j jVar = new j();
        a = jVar;
        jVar.b(f, 0, 1);
        jVar = new j();
        b = jVar;
        jVar.c = new byte[0];
        jVar = new j();
        j = jVar;
        jVar.b(g, 0, 1);
    }

    private j() {
    }

    public j(d dVar) {
        byte[] bArr = new byte[64];
        int i = 0;
        int i2 = 0;
        while (i2 == 0) {
            int f = dVar.f();
            switch (f & 192) {
                case 0:
                    if (c() < 128) {
                        if (f != 0) {
                            bArr[0] = (byte) f;
                            dVar.a(bArr, 1, f);
                            a(bArr, 0, 1);
                            break;
                        }
                        a(f, 0, 1);
                        i2 = 1;
                        break;
                    }
                    throw new t("too many labels");
                case 192:
                    f = ((f & -193) << 8) + dVar.f();
                    if (f < dVar.a() - 2) {
                        if (i == 0) {
                            dVar.d();
                            i = 1;
                        }
                        dVar.b(f);
                        break;
                    }
                    throw new t("bad compression");
                default:
                    throw new t("bad label type");
            }
        }
        if (i != 0) {
            dVar.e();
        }
    }

    private j(j jVar, int i) {
        int c = jVar.c();
        if (i > c) {
            throw new IllegalArgumentException("attempted to remove too many labels");
        }
        this.c = jVar.c;
        b(c - i);
        int i2 = 0;
        while (i2 < 7 && i2 < c - i) {
            a(i2, jVar.a(i2 + i));
            i2++;
        }
    }

    private j(String str, j jVar) {
        if (str.equals("")) {
            throw a(str, "empty name");
        } else if (str.equals("@")) {
            if (jVar == null) {
                b(b, this);
            } else {
                b(jVar, this);
            }
        } else if (str.equals(".")) {
            b(a, this);
        } else {
            int i = -1;
            int i2 = 1;
            byte[] bArr = new byte[64];
            Object obj = null;
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < str.length(); i5++) {
                byte charAt = (byte) str.charAt(i5);
                if (obj != null) {
                    byte b;
                    if (charAt >= (byte) 48 && charAt <= (byte) 57 && i3 < 3) {
                        i3++;
                        i4 = (i4 * 10) + (charAt - 48);
                        if (i4 > 255) {
                            throw a(str, "bad escape");
                        } else if (i3 >= 3) {
                            b = (byte) i4;
                        } else {
                            continue;
                        }
                    } else if (i3 <= 0 || i3 >= 3) {
                        b = charAt;
                    } else {
                        throw a(str, "bad escape");
                    }
                    if (i2 > 63) {
                        throw a(str, "label too long");
                    }
                    i = i2 + 1;
                    bArr[i2] = b;
                    obj = null;
                    int i6 = i;
                    i = i2;
                    i2 = i6;
                } else if (charAt == (byte) 92) {
                    obj = 1;
                    i3 = 0;
                    i4 = 0;
                } else if (charAt != (byte) 46) {
                    int i7 = i == -1 ? i5 : i;
                    if (i2 > 63) {
                        throw a(str, "label too long");
                    }
                    i = i2 + 1;
                    bArr[i2] = charAt;
                    i2 = i;
                    i = i7;
                } else if (i == -1) {
                    throw a(str, "invalid empty label");
                } else {
                    bArr[0] = (byte) (i2 - 1);
                    a(str, bArr, 0, 1);
                    i = -1;
                    i2 = 1;
                }
            }
            if (i3 > 0 && i3 < 3) {
                throw a(str, "bad escape");
            } else if (obj != null) {
                throw a(str, "bad escape");
            } else {
                Object obj2;
                if (i == -1) {
                    a(str, f, 0, 1);
                    obj2 = 1;
                } else {
                    bArr[0] = (byte) (i2 - 1);
                    a(str, bArr, 0, 1);
                    obj2 = null;
                }
                if (jVar != null && r0 == null) {
                    a(str, jVar.c, jVar.a(0), jVar.c());
                }
            }
        }
    }

    private final int a(int i) {
        if (i == 0 && c() == 0) {
            return 0;
        }
        if (i < 0 || i >= c()) {
            throw new IllegalArgumentException("label out of range");
        } else if (i < 7) {
            return ((int) (this.d >>> ((7 - i) * 8))) & 255;
        } else {
            int a = a(6);
            int i2 = 6;
            while (i2 < i) {
                i2++;
                a = (this.c[a] + 1) + a;
            }
            return a;
        }
    }

    public static j a(j jVar, j jVar2) {
        if (jVar.a()) {
            return jVar;
        }
        j jVar3 = new j();
        b(jVar, jVar3);
        jVar3.a(jVar2.c, jVar2.a(0), jVar2.c());
        return jVar3;
    }

    public static j a(String str) {
        return a(str, null);
    }

    public static j a(String str, j jVar) {
        return (!str.equals("@") || jVar == null) ? str.equals(".") ? a : new j(str, jVar) : jVar;
    }

    private static s a(String str, String str2) {
        return new s("'" + str + "': " + str2);
    }

    private static String a(byte[] bArr, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = i + 1;
        byte b = bArr[i];
        for (int i3 = i2; i3 < i2 + b; i3++) {
            int i4 = bArr[i3] & 255;
            if (i4 <= 32 || i4 >= 127) {
                stringBuffer.append('\\');
                stringBuffer.append(h.format((long) i4));
            } else if (i4 == 34 || i4 == 40 || i4 == 41 || i4 == 46 || i4 == 59 || i4 == 92 || i4 == 64 || i4 == 36) {
                stringBuffer.append('\\');
                stringBuffer.append((char) i4);
            } else {
                stringBuffer.append((char) i4);
            }
        }
        return stringBuffer.toString();
    }

    private final void a(int i, int i2) {
        if (i < 7) {
            int i3 = (7 - i) * 8;
            this.d &= (255 << i3) ^ -1;
            this.d = (((long) i2) << i3) | this.d;
        }
    }

    private final void a(String str, byte[] bArr, int i, int i2) {
        try {
            a(bArr, i, i2);
        } catch (k e) {
            throw a(str, "Name too long");
        }
    }

    private final void a(byte[] bArr, int i, int i2) {
        int i3;
        int i4 = 0;
        int length = this.c == null ? 0 : this.c.length - a(0);
        int i5 = i;
        int i6 = 0;
        for (i3 = 0; i3 < i2; i3++) {
            byte b = bArr[i5];
            if (b > (byte) 63) {
                throw new IllegalStateException("invalid label");
            }
            int i7 = b + 1;
            i5 += i7;
            i6 += i7;
        }
        i5 = length + i6;
        if (i5 > 255) {
            throw new k();
        }
        i3 = c();
        i7 = i3 + i2;
        if (i7 > 128) {
            throw new IllegalStateException("too many labels");
        }
        Object obj = new byte[i5];
        if (length != 0) {
            System.arraycopy(this.c, a(0), obj, 0, length);
        }
        System.arraycopy(bArr, i, obj, length, i6);
        this.c = obj;
        while (i4 < i2) {
            a(i3 + i4, length);
            length += obj[length] + 1;
            i4++;
        }
        b(i7);
    }

    private final void b(int i) {
        this.d &= -256;
        this.d |= (long) i;
    }

    private static final void b(j jVar, j jVar2) {
        int i = 0;
        if (jVar.a(0) == 0) {
            jVar2.c = jVar.c;
            jVar2.d = jVar.d;
            return;
        }
        int a = jVar.a(0);
        int length = jVar.c.length - a;
        int c = jVar.c();
        jVar2.c = new byte[length];
        System.arraycopy(jVar.c, a, jVar2.c, 0, length);
        while (i < c && i < 7) {
            jVar2.a(i, jVar.a(i) - a);
            i++;
        }
        jVar2.b(c);
    }

    private final void b(byte[] bArr, int i, int i2) {
        try {
            a(bArr, 0, 1);
        } catch (k e) {
        }
    }

    private final int c() {
        return (int) (this.d & 255);
    }

    public final void a(e eVar) {
        byte[] bArr;
        int c = c();
        if (c == 0) {
            bArr = new byte[0];
        } else {
            bArr = new byte[(this.c.length - a(0))];
            int a = a(0);
            int i = 0;
            for (int i2 = 0; i2 < c; i2++) {
                byte b = this.c[a];
                if (b > (byte) 63) {
                    throw new IllegalStateException("invalid label");
                }
                int i3 = i + 1;
                int i4 = a + 1;
                bArr[i] = this.c[a];
                a = i4;
                i4 = i3;
                byte b2 = (byte) 0;
                while (b2 < b) {
                    i = i4 + 1;
                    int i5 = a + 1;
                    bArr[i4] = i[this.c[a] & 255];
                    b2++;
                    i4 = i;
                    a = i5;
                }
                i = i4;
            }
        }
        eVar.a(bArr);
    }

    public final void a(e eVar, b bVar) {
        int c = c();
        int i = 0;
        while (i < c - 1) {
            j jVar = i == 0 ? this : new j(this, i);
            int i2 = -1;
            if (bVar != null) {
                i2 = bVar.a(jVar);
            }
            if (i2 >= 0) {
                eVar.c(i2 | 49152);
                return;
            }
            if (bVar != null) {
                bVar.a(eVar.a(), jVar);
            }
            i2 = a(i);
            eVar.a(this.c, i2, this.c[i2] + 1);
            i++;
        }
        eVar.b(0);
    }

    public final void a(e eVar, b bVar, boolean z) {
        if (z) {
            a(eVar);
        } else {
            a(eVar, null);
        }
    }

    public final boolean a() {
        int c = c();
        return c != 0 && this.c[a(c - 1)] == (byte) 0;
    }

    public final int b() {
        return c();
    }

    public final int compareTo(Object obj) {
        j jVar = (j) obj;
        if (this == jVar) {
            return 0;
        }
        int c = c();
        int c2 = jVar.c();
        int i = c > c2 ? c2 : c;
        for (int i2 = 1; i2 <= i; i2++) {
            int a = a(c - i2);
            int a2 = jVar.a(c2 - i2);
            byte b = this.c[a];
            byte b2 = jVar.c[a2];
            byte b3 = (byte) 0;
            while (b3 < b && b3 < b2) {
                int i3 = i[this.c[(b3 + a) + 1] & 255] - i[jVar.c[(b3 + a2) + 1] & 255];
                if (i3 != 0) {
                    return i3;
                }
                b3++;
            }
            if (b != b2) {
                return b - b2;
            }
        }
        return c - c2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        if (jVar.e == 0) {
            jVar.hashCode();
        }
        if (this.e == 0) {
            hashCode();
        }
        if (jVar.e != this.e) {
            return false;
        }
        if (jVar.c() != c()) {
            return false;
        }
        byte[] bArr = jVar.c;
        int a = jVar.a(0);
        int c = c();
        int a2 = a(0);
        for (int i = 0; i < c; i++) {
            if (this.c[a2] != bArr[a]) {
                return false;
            }
            int i2 = a2 + 1;
            byte b = this.c[a2];
            a2 = a + 1;
            if (b > (byte) 63) {
                throw new IllegalStateException("invalid label");
            }
            a = a2;
            a2 = i2;
            byte b2 = (byte) 0;
            while (b2 < b) {
                int i3 = a2 + 1;
                byte b3 = i[this.c[a2] & 255];
                a2 = a + 1;
                if (b3 != i[bArr[a] & 255]) {
                    return false;
                }
                b2++;
                a = a2;
                a2 = i3;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 0;
        if (this.e != 0) {
            return this.e;
        }
        for (int a = a(0); a < this.c.length; a++) {
            i += (i << 3) + i[this.c[a] & 255];
        }
        this.e = i;
        return this.e;
    }

    public final String toString() {
        int i = 0;
        int c = c();
        if (c == 0) {
            return "@";
        }
        if (c == 1 && this.c[a(0)] == (byte) 0) {
            return ".";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int a = a(0);
        while (i < c) {
            byte b = this.c[a];
            if (b > (byte) 63) {
                throw new IllegalStateException("invalid label");
            } else if (b == (byte) 0) {
                stringBuffer.append('.');
                break;
            } else {
                if (i > 0) {
                    stringBuffer.append('.');
                }
                stringBuffer.append(a(this.c, a));
                a += b + 1;
                i++;
            }
        }
        return stringBuffer.toString();
    }
}
