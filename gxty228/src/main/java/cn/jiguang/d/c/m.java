package cn.jiguang.d.c;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Arrays;

public abstract class m implements Serializable, Cloneable, Comparable {
    private static final DecimalFormat e;
    protected j a;
    protected int b;
    protected int c;
    protected long d;

    static {
        DecimalFormat decimalFormat = new DecimalFormat();
        e = decimalFormat;
        decimalFormat.setMinimumIntegerDigits(3);
    }

    protected m() {
    }

    static m a(d dVar, int i) {
        j jVar = new j(dVar);
        int g = dVar.g();
        int g2 = dVar.g();
        if (i == 0) {
            return a(jVar, g, g2);
        }
        long h = dVar.h();
        int g3 = dVar.g();
        m a = a(jVar, g, g2, h);
        if (dVar == null) {
            return a;
        }
        if (dVar.b() < g3) {
            throw new t("truncated record");
        }
        dVar.a(g3);
        a.a(dVar);
        if (dVar.b() > 0) {
            throw new t("invalid record length");
        }
        dVar.c();
        return a;
    }

    public static m a(j jVar, int i, int i2) {
        if (jVar.a()) {
            return a(jVar, i, i2, 0);
        }
        throw new n(jVar);
    }

    private static final m a(j jVar, int i, int i2, long j) {
        m pVar = new p();
        pVar.a = jVar;
        pVar.b = i;
        pVar.c = i2;
        pVar.d = j;
        return pVar;
    }

    private byte[] h() {
        e eVar = new e();
        a(eVar, true);
        return eVar.b();
    }

    abstract String a();

    final void a(long j) {
        this.d = j;
    }

    abstract void a(d dVar);

    final void a(e eVar, b bVar) {
        this.a.a(eVar, bVar);
        eVar.c(this.b);
        eVar.c(this.c);
    }

    abstract void a(e eVar, boolean z);

    public final boolean a(m mVar) {
        return this.b == mVar.b && this.c == mVar.c && this.a.equals(mVar.a);
    }

    public final j b() {
        return this.a;
    }

    public final int c() {
        return this.b;
    }

    public int compareTo(Object obj) {
        int i = 0;
        m mVar = (m) obj;
        if (this == mVar) {
            return 0;
        }
        int compareTo = this.a.compareTo(mVar.a);
        if (compareTo != 0) {
            return compareTo;
        }
        compareTo = this.c - mVar.c;
        if (compareTo != 0) {
            return compareTo;
        }
        compareTo = this.b - mVar.b;
        if (compareTo != 0) {
            return compareTo;
        }
        byte[] h = h();
        byte[] h2 = mVar.h();
        while (i < h.length && i < h2.length) {
            compareTo = (h[i] & 255) - (h2[i] & 255);
            if (compareTo != 0) {
                return compareTo;
            }
            i++;
        }
        return h.length - h2.length;
    }

    public final int d() {
        return this.b;
    }

    public final int e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return (this.b == mVar.b && this.c == mVar.c && this.a.equals(mVar.a)) ? Arrays.equals(h(), mVar.h()) : false;
    }

    public final long f() {
        return this.d;
    }

    final m g() {
        try {
            return (m) clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException();
        }
    }

    public int hashCode() {
        int i = 0;
        e eVar = new e();
        this.a.a(eVar);
        eVar.c(this.b);
        eVar.c(this.c);
        eVar.a(0);
        int a = eVar.a();
        eVar.c(0);
        a(eVar, true);
        eVar.a((eVar.a() - a) - 2, a);
        byte[] b = eVar.b();
        int i2 = 0;
        while (i < b.length) {
            i2 += (i2 << 3) + (b[i] & 255);
            i++;
        }
        return i2;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.a);
        if (stringBuffer.length() < 8) {
            stringBuffer.append("\t");
        }
        if (stringBuffer.length() < 16) {
            stringBuffer.append("\t");
        }
        stringBuffer.append("\t");
        String a = a();
        if (!a.equals("")) {
            stringBuffer.append("\t");
            stringBuffer.append(a);
        }
        return stringBuffer.toString();
    }
}
