package com.google.gson.stream;

import com.google.gson.internal.a.e;
import com.google.gson.internal.d;
import com.tencent.bugly.beta.tinker.TinkerReport;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

/* compiled from: JsonReader */
public class a implements Closeable {
    private static final char[] b = ")]}'\n".toCharArray();
    int a = 0;
    private final Reader c;
    private boolean d = false;
    private final char[] e = new char[1024];
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private long j;
    private int k;
    private String l;
    private int[] m = new int[32];
    private int n = 0;
    private String[] o;
    private int[] p;

    static {
        d.a = new d() {
            public void a(a aVar) throws IOException {
                if (aVar instanceof e) {
                    ((e) aVar).o();
                    return;
                }
                int i = aVar.a;
                if (i == 0) {
                    i = aVar.r();
                }
                if (i == 13) {
                    aVar.a = 9;
                } else if (i == 12) {
                    aVar.a = 8;
                } else if (i == 14) {
                    aVar.a = 10;
                } else {
                    throw new IllegalStateException("Expected a name but was " + aVar.f() + aVar.s());
                }
            }
        };
    }

    public a(Reader reader) {
        int[] iArr = this.m;
        int i = this.n;
        this.n = i + 1;
        iArr[i] = 6;
        this.o = new String[32];
        this.p = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.c = reader;
    }

    public final void a(boolean z) {
        this.d = z;
    }

    public final boolean q() {
        return this.d;
    }

    public void a() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 3) {
            a(1);
            this.p[this.n - 1] = 0;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + f() + s());
    }

    public void b() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 4) {
            this.n--;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + f() + s());
    }

    public void c() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 1) {
            a(3);
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + f() + s());
    }

    public void d() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 2) {
            this.n--;
            this.o[this.n] = null;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + f() + s());
    }

    public boolean e() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    public JsonToken f() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        switch (i) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.STRING;
            case 12:
            case 13:
            case 14:
                return JsonToken.NAME;
            case 15:
            case 16:
                return JsonToken.NUMBER;
            case 17:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    int r() throws IOException {
        int b;
        int i = this.m[this.n - 1];
        if (i == 1) {
            this.m[this.n - 1] = 2;
        } else if (i == 2) {
            switch (b(true)) {
                case 44:
                    break;
                case 59:
                    w();
                    break;
                case 93:
                    this.a = 4;
                    return 4;
                default:
                    throw b("Unterminated array");
            }
        } else if (i == 3 || i == 5) {
            this.m[this.n - 1] = 4;
            if (i == 5) {
                switch (b(true)) {
                    case 44:
                        break;
                    case 59:
                        w();
                        break;
                    case 125:
                        this.a = 2;
                        return 2;
                    default:
                        throw b("Unterminated object");
                }
            }
            b = b(true);
            switch (b) {
                case 34:
                    this.a = 13;
                    return 13;
                case 39:
                    w();
                    this.a = 12;
                    return 12;
                case 125:
                    if (i != 5) {
                        this.a = 2;
                        return 2;
                    }
                    throw b("Expected name");
                default:
                    w();
                    this.f--;
                    if (a((char) b)) {
                        this.a = 14;
                        return 14;
                    }
                    throw b("Expected name");
            }
        } else if (i == 4) {
            this.m[this.n - 1] = 5;
            switch (b(true)) {
                case 58:
                    break;
                case 61:
                    w();
                    if ((this.f < this.g || b(1)) && this.e[this.f] == '>') {
                        this.f++;
                        break;
                    }
                default:
                    throw b("Expected ':'");
            }
        } else if (i == 6) {
            if (this.d) {
                z();
            }
            this.m[this.n - 1] = 7;
        } else if (i == 7) {
            if (b(false) == -1) {
                this.a = 17;
                return 17;
            }
            w();
            this.f--;
        } else if (i == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        switch (b(true)) {
            case 34:
                this.a = 9;
                return 9;
            case 39:
                w();
                this.a = 8;
                return 8;
            case 44:
            case 59:
                break;
            case 91:
                this.a = 3;
                return 3;
            case 93:
                if (i == 1) {
                    this.a = 4;
                    return 4;
                }
                break;
            case TinkerReport.KEY_APPLIED_DEXOPT_FORMAT /*123*/:
                this.a = 1;
                return 1;
            default:
                this.f--;
                b = o();
                if (b != 0) {
                    return b;
                }
                b = t();
                if (b != 0) {
                    return b;
                }
                if (a(this.e[this.f])) {
                    w();
                    this.a = 10;
                    return 10;
                }
                throw b("Expected value");
        }
        if (i == 1 || i == 2) {
            w();
            this.f--;
            this.a = 7;
            return 7;
        }
        throw b("Unexpected value");
    }

    private int o() throws IOException {
        String str;
        int i;
        char c = this.e[this.f];
        String str2;
        if (c == 't' || c == 'T') {
            str = "true";
            str2 = "TRUE";
            i = 5;
        } else if (c == 'f' || c == 'F') {
            str = "false";
            str2 = "FALSE";
            i = 6;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            str = "null";
            str2 = "NULL";
            i = 7;
        }
        int length = str.length();
        int i2 = 1;
        while (i2 < length) {
            if (this.f + i2 >= this.g && !b(i2 + 1)) {
                return 0;
            }
            char c2 = this.e[this.f + i2];
            if (c2 != str.charAt(i2) && c2 != r1.charAt(i2)) {
                return 0;
            }
            i2++;
        }
        if ((this.f + length < this.g || b(length + 1)) && a(this.e[this.f + length])) {
            return 0;
        }
        this.f += length;
        this.a = i;
        return i;
    }

    private int t() throws IOException {
        char[] cArr = this.e;
        int i = this.f;
        long j = 0;
        Object obj = null;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        int i5 = this.g;
        int i6 = i;
        while (true) {
            Object obj2;
            if (i6 + i4 == i5) {
                if (i4 == cArr.length) {
                    return 0;
                }
                if (b(i4 + 1)) {
                    i6 = this.f;
                    i5 = this.g;
                } else if (i3 != 2 && i2 != 0 && ((j != Long.MIN_VALUE || obj != null) && (j != 0 || obj == null))) {
                    if (obj == null) {
                        j = -j;
                    }
                    this.j = j;
                    this.f += i4;
                    this.a = 15;
                    return 15;
                } else if (i3 == 2 && i3 != 4 && i3 != 7) {
                    return 0;
                } else {
                    this.k = i4;
                    this.a = 16;
                    return 16;
                }
            }
            char c = cArr[i6 + i4];
            int i7;
            switch (c) {
                case '+':
                    if (i3 != 5) {
                        return 0;
                    }
                    i = 6;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                case '-':
                    if (i3 == 0) {
                        i = 1;
                        i7 = i2;
                        obj2 = 1;
                        i3 = i7;
                        continue;
                    } else if (i3 == 5) {
                        i = 6;
                        i3 = i2;
                        obj2 = obj;
                        break;
                    } else {
                        return 0;
                    }
                case '.':
                    if (i3 != 2) {
                        return 0;
                    }
                    i = 3;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                case 'E':
                case 'e':
                    if (i3 != 2 && i3 != 4) {
                        return 0;
                    }
                    i = 5;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                default:
                    if (c >= '0' && c <= '9') {
                        if (i3 != 1 && i3 != 0) {
                            if (i3 != 2) {
                                if (i3 != 3) {
                                    if (i3 != 5 && i3 != 6) {
                                        i = i3;
                                        i3 = i2;
                                        obj2 = obj;
                                        break;
                                    }
                                    i = 7;
                                    i3 = i2;
                                    obj2 = obj;
                                    break;
                                }
                                i = 4;
                                i3 = i2;
                                obj2 = obj;
                                break;
                            } else if (j != 0) {
                                long j2 = (10 * j) - ((long) (c - 48));
                                i = (j > -922337203685477580L || (j == -922337203685477580L && j2 < j)) ? 1 : 0;
                                i &= i2;
                                obj2 = obj;
                                j = j2;
                                i7 = i3;
                                i3 = i;
                                i = i7;
                                break;
                            } else {
                                return 0;
                            }
                        }
                        j = (long) (-(c - 48));
                        i = 2;
                        i3 = i2;
                        obj2 = obj;
                        continue;
                    } else if (a(c)) {
                        return 0;
                    }
                    break;
            }
            if (i3 != 2) {
            }
            if (i3 == 2) {
            }
            this.k = i4;
            this.a = 16;
            return 16;
            i4++;
            obj = obj2;
            i2 = i3;
            i3 = i;
        }
    }

    private boolean a(char c) throws IOException {
        switch (c) {
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case TinkerReport.KEY_APPLIED_DEXOPT_FORMAT /*123*/:
            case '}':
                break;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                w();
                break;
            default:
                return true;
        }
        return false;
    }

    public String g() throws IOException {
        String u;
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 14) {
            u = u();
        } else if (i == 12) {
            u = b('\'');
        } else if (i == 13) {
            u = b('\"');
        } else {
            throw new IllegalStateException("Expected a name but was " + f() + s());
        }
        this.a = 0;
        this.o[this.n - 1] = u;
        return u;
    }

    public String h() throws IOException {
        String u;
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 10) {
            u = u();
        } else if (i == 8) {
            u = b('\'');
        } else if (i == 9) {
            u = b('\"');
        } else if (i == 11) {
            u = this.l;
            this.l = null;
        } else if (i == 15) {
            u = Long.toString(this.j);
        } else if (i == 16) {
            u = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else {
            throw new IllegalStateException("Expected a string but was " + f() + s());
        }
        this.a = 0;
        int[] iArr = this.p;
        int i2 = this.n - 1;
        iArr[i2] = iArr[i2] + 1;
        return u;
    }

    public boolean i() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 5) {
            this.a = 0;
            int[] iArr = this.p;
            i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        } else if (i == 6) {
            this.a = 0;
            int[] iArr2 = this.p;
            int i2 = this.n - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + f() + s());
        }
    }

    public void j() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 7) {
            this.a = 0;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + f() + s());
    }

    public double k() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 15) {
            this.a = 0;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return (double) this.j;
        }
        if (i == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i == 8 || i == 9) {
            this.l = b(i == 8 ? '\'' : '\"');
        } else if (i == 10) {
            this.l = u();
        } else if (i != 11) {
            throw new IllegalStateException("Expected a double but was " + f() + s());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        if (this.d || !(Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            this.l = null;
            this.a = 0;
            int[] iArr2 = this.p;
            int i3 = this.n - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return parseDouble;
        }
        throw new MalformedJsonException("JSON forbids NaN and infinities: " + parseDouble + s());
    }

    public long l() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 15) {
            this.a = 0;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.j;
        }
        long parseLong;
        if (i == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i == 8 || i == 9 || i == 10) {
            if (i == 10) {
                this.l = u();
            } else {
                this.l = b(i == 8 ? '\'' : '\"');
            }
            try {
                parseLong = Long.parseLong(this.l);
                this.a = 0;
                int[] iArr2 = this.p;
                int i3 = this.n - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException e) {
            }
        } else {
            throw new IllegalStateException("Expected a long but was " + f() + s());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        parseLong = (long) parseDouble;
        if (((double) parseLong) != parseDouble) {
            throw new NumberFormatException("Expected a long but was " + this.l + s());
        }
        this.l = null;
        this.a = 0;
        iArr2 = this.p;
        i3 = this.n - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return parseLong;
    }

    private String b(char c) throws IOException {
        char[] cArr = this.e;
        StringBuilder stringBuilder = null;
        do {
            int i = this.f;
            int i2 = this.g;
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.f = i4;
                    i2 = (i4 - i) - 1;
                    if (stringBuilder == null) {
                        return new String(cArr, i, i2);
                    }
                    stringBuilder.append(cArr, i, i2);
                    return stringBuilder.toString();
                }
                StringBuilder stringBuilder2;
                int i5;
                int i6;
                if (c2 == '\\') {
                    this.f = i4;
                    i2 = (i4 - i) - 1;
                    if (stringBuilder == null) {
                        stringBuilder = new StringBuilder(Math.max((i2 + 1) * 2, 16));
                    }
                    stringBuilder.append(cArr, i, i2);
                    stringBuilder.append(y());
                    i = this.f;
                    stringBuilder2 = stringBuilder;
                    i5 = i;
                    i6 = i;
                    i = this.g;
                    i2 = i6;
                } else {
                    if (c2 == '\n') {
                        this.h++;
                        this.i = i4;
                    }
                    i6 = i;
                    i = i2;
                    i2 = i4;
                    stringBuilder2 = stringBuilder;
                    i5 = i6;
                }
                i3 = i2;
                i2 = i;
                i = i5;
                stringBuilder = stringBuilder2;
            }
            if (stringBuilder == null) {
                stringBuilder = new StringBuilder(Math.max((i3 - i) * 2, 16));
            }
            stringBuilder.append(cArr, i, i3 - i);
            this.f = i3;
        } while (b(1));
        throw b("Unterminated string");
    }

    private String u() throws IOException {
        StringBuilder stringBuilder = null;
        int i = 0;
        while (true) {
            String str;
            if (this.f + i < this.g) {
                switch (this.e[this.f + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case TinkerReport.KEY_APPLIED_DEXOPT_FORMAT /*123*/:
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        w();
                        break;
                    default:
                        i++;
                        continue;
                }
            } else if (i >= this.e.length) {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder(Math.max(i, 16));
                }
                stringBuilder.append(this.e, this.f, i);
                this.f = i + this.f;
                if (b(1)) {
                    i = 0;
                } else {
                    i = 0;
                }
            } else if (b(i + 1)) {
            }
            if (stringBuilder == null) {
                str = new String(this.e, this.f, i);
            } else {
                str = stringBuilder.append(this.e, this.f, i).toString();
            }
            this.f = i + this.f;
            return str;
        }
    }

    private void c(char c) throws IOException {
        char[] cArr = this.e;
        do {
            int i = this.f;
            int i2 = this.g;
            int i3 = i;
            while (i3 < i2) {
                i = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.f = i;
                    return;
                }
                if (c2 == '\\') {
                    this.f = i;
                    y();
                    i = this.f;
                    i2 = this.g;
                } else if (c2 == '\n') {
                    this.h++;
                    this.i = i;
                }
                i3 = i;
            }
            this.f = i3;
        } while (b(1));
        throw b("Unterminated string");
    }

    private void v() throws IOException {
        do {
            int i = 0;
            while (this.f + i < this.g) {
                switch (this.e[this.f + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case TinkerReport.KEY_APPLIED_DEXOPT_FORMAT /*123*/:
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        w();
                        break;
                    default:
                        i++;
                }
                this.f = i + this.f;
                return;
            }
            this.f = i + this.f;
        } while (b(1));
    }

    public int m() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        int[] iArr;
        int i2;
        if (i == 15) {
            i = (int) this.j;
            if (this.j != ((long) i)) {
                throw new NumberFormatException("Expected an int but was " + this.j + s());
            }
            this.a = 0;
            iArr = this.p;
            i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
        } else {
            if (i == 16) {
                this.l = new String(this.e, this.f, this.k);
                this.f += this.k;
            } else if (i == 8 || i == 9 || i == 10) {
                if (i == 10) {
                    this.l = u();
                } else {
                    this.l = b(i == 8 ? '\'' : '\"');
                }
                try {
                    i = Integer.parseInt(this.l);
                    this.a = 0;
                    iArr = this.p;
                    i2 = this.n - 1;
                    iArr[i2] = iArr[i2] + 1;
                } catch (NumberFormatException e) {
                }
            } else {
                throw new IllegalStateException("Expected an int but was " + f() + s());
            }
            this.a = 11;
            double parseDouble = Double.parseDouble(this.l);
            i = (int) parseDouble;
            if (((double) i) != parseDouble) {
                throw new NumberFormatException("Expected an int but was " + this.l + s());
            }
            this.l = null;
            this.a = 0;
            iArr = this.p;
            i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return i;
    }

    public void close() throws IOException {
        this.a = 0;
        this.m[0] = 8;
        this.n = 1;
        this.c.close();
    }

    public void n() throws IOException {
        int i = 0;
        do {
            int i2 = this.a;
            if (i2 == 0) {
                i2 = r();
            }
            if (i2 == 3) {
                a(1);
                i++;
            } else if (i2 == 1) {
                a(3);
                i++;
            } else if (i2 == 4) {
                this.n--;
                i--;
            } else if (i2 == 2) {
                this.n--;
                i--;
            } else if (i2 == 14 || i2 == 10) {
                v();
            } else if (i2 == 8 || i2 == 12) {
                c('\'');
            } else if (i2 == 9 || i2 == 13) {
                c('\"');
            } else if (i2 == 16) {
                this.f += this.k;
            }
            this.a = 0;
        } while (i != 0);
        int[] iArr = this.p;
        int i3 = this.n - 1;
        iArr[i3] = iArr[i3] + 1;
        this.o[this.n - 1] = "null";
    }

    private void a(int i) {
        if (this.n == this.m.length) {
            Object obj = new int[(this.n * 2)];
            Object obj2 = new int[(this.n * 2)];
            Object obj3 = new String[(this.n * 2)];
            System.arraycopy(this.m, 0, obj, 0, this.n);
            System.arraycopy(this.p, 0, obj2, 0, this.n);
            System.arraycopy(this.o, 0, obj3, 0, this.n);
            this.m = obj;
            this.p = obj2;
            this.o = obj3;
        }
        int[] iArr = this.m;
        int i2 = this.n;
        this.n = i2 + 1;
        iArr[i2] = i;
    }

    private boolean b(int i) throws IOException {
        Object obj = this.e;
        this.i -= this.f;
        if (this.g != this.f) {
            this.g -= this.f;
            System.arraycopy(obj, this.f, obj, 0, this.g);
        } else {
            this.g = 0;
        }
        this.f = 0;
        do {
            int read = this.c.read(obj, this.g, obj.length - this.g);
            if (read == -1) {
                return false;
            }
            this.g = read + this.g;
            if (this.h == 0 && this.i == 0 && this.g > 0 && obj[0] == '﻿') {
                this.f++;
                this.i++;
                i++;
            }
        } while (this.g < i);
        return true;
    }

    private int b(boolean z) throws IOException {
        char[] cArr = this.e;
        int i = this.f;
        int i2 = this.g;
        while (true) {
            if (i == i2) {
                this.f = i;
                if (b(1)) {
                    i = this.f;
                    i2 = this.g;
                } else if (!z) {
                    return -1;
                } else {
                    throw new EOFException("End of input" + s());
                }
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.h++;
                this.i = i3;
                i = i3;
            } else if (c == ' ' || c == '\r') {
                i = i3;
            } else if (c == '\t') {
                i = i3;
            } else if (c == '/') {
                this.f = i3;
                if (i3 == i2) {
                    this.f--;
                    boolean b = b(2);
                    this.f++;
                    if (!b) {
                        return c;
                    }
                }
                w();
                switch (cArr[this.f]) {
                    case '*':
                        this.f++;
                        if (a("*/")) {
                            i = this.f + 2;
                            i2 = this.g;
                            break;
                        }
                        throw b("Unterminated comment");
                    case '/':
                        this.f++;
                        x();
                        i = this.f;
                        i2 = this.g;
                        break;
                    default:
                        return c;
                }
            } else if (c == '#') {
                this.f = i3;
                w();
                x();
                i = this.f;
                i2 = this.g;
            } else {
                this.f = i3;
                return c;
            }
        }
    }

    private void w() throws IOException {
        if (!this.d) {
            throw b("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void x() throws IOException {
        char c;
        do {
            if (this.f < this.g || b(1)) {
                char[] cArr = this.e;
                int i = this.f;
                this.f = i + 1;
                c = cArr[i];
                if (c == '\n') {
                    this.h++;
                    this.i = this.f;
                    return;
                }
            } else {
                return;
            }
        } while (c != '\r');
    }

    private boolean a(String str) throws IOException {
        int length = str.length();
        while (true) {
            if (this.f + length > this.g && !b(length)) {
                return false;
            }
            if (this.e[this.f] == '\n') {
                this.h++;
                this.i = this.f + 1;
            } else {
                int i = 0;
                while (i < length) {
                    if (this.e[this.f + i] == str.charAt(i)) {
                        i++;
                    }
                }
                return true;
            }
            this.f++;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + s();
    }

    String s() {
        return " at line " + (this.h + 1) + " column " + ((this.f - this.i) + 1) + " path " + p();
    }

    public String p() {
        StringBuilder append = new StringBuilder().append('$');
        int i = this.n;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.m[i2]) {
                case 1:
                case 2:
                    append.append('[').append(this.p[i2]).append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    append.append('.');
                    if (this.o[i2] == null) {
                        break;
                    }
                    append.append(this.o[i2]);
                    break;
                default:
                    break;
            }
        }
        return append.toString();
    }

    private char y() throws IOException {
        if (this.f != this.g || b(1)) {
            char[] cArr = this.e;
            int i = this.f;
            this.f = i + 1;
            char c = cArr[i];
            switch (c) {
                case '\n':
                    this.h++;
                    this.i = this.f;
                    return c;
                case '\"':
                case '\'':
                case '/':
                case '\\':
                    return c;
                case 'b':
                    return '\b';
                case 'f':
                    return '\f';
                case 'n':
                    return '\n';
                case 'r':
                    return '\r';
                case 't':
                    return '\t';
                case 'u':
                    if (this.f + 4 <= this.g || b(4)) {
                        int i2 = this.f;
                        int i3 = i2 + 4;
                        int i4 = i2;
                        c = '\u0000';
                        for (i = i4; i < i3; i++) {
                            char c2 = this.e[i];
                            c = (char) (c << 4);
                            if (c2 >= '0' && c2 <= '9') {
                                c = (char) (c + (c2 - 48));
                            } else if (c2 >= 'a' && c2 <= 'f') {
                                c = (char) (c + ((c2 - 97) + 10));
                            } else if (c2 < 'A' || c2 > 'F') {
                                throw new NumberFormatException("\\u" + new String(this.e, this.f, 4));
                            } else {
                                c = (char) (c + ((c2 - 65) + 10));
                            }
                        }
                        this.f += 4;
                        return c;
                    }
                    throw b("Unterminated escape sequence");
                default:
                    throw b("Invalid escape sequence");
            }
        }
        throw b("Unterminated escape sequence");
    }

    private IOException b(String str) throws IOException {
        throw new MalformedJsonException(str + s());
    }

    private void z() throws IOException {
        b(true);
        this.f--;
        if (this.f + b.length <= this.g || b(b.length)) {
            int i = 0;
            while (i < b.length) {
                if (this.e[this.f + i] == b[i]) {
                    i++;
                } else {
                    return;
                }
            }
            this.f += b.length;
        }
    }
}
