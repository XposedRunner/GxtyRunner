package cn.jiguang.d.c;

import android.support.v4.internal.view.SupportMenu;
import java.util.Random;

public final class g implements Cloneable {
    private static Random d = new Random();
    private int a;
    private int b;
    private int[] c;

    public g() {
        this.b = 256;
        b();
    }

    private g(int i) {
        this.b = 256;
        b();
        if (i < 0 || i > SupportMenu.USER_MASK) {
            throw new IllegalArgumentException("DNS message ID " + i + " is out of range");
        }
        this.a = i;
    }

    g(d dVar) {
        this(dVar.g());
        this.b = dVar.g();
        for (int i = 0; i < this.c.length; i++) {
            this.c[i] = dVar.g();
        }
    }

    private void b() {
        this.c = new int[4];
        this.b = 256;
        this.a = -1;
    }

    private int c() {
        if (this.a >= 0) {
            return this.a;
        }
        int i;
        synchronized (this) {
            if (this.a < 0) {
                this.a = d.nextInt(SupportMenu.USER_MASK);
            }
            i = this.a;
        }
        return i;
    }

    final int a() {
        return this.b;
    }

    final void a(int i) {
        if (this.c[i] == 65535) {
            throw new IllegalStateException("DNS section count cannot be incremented");
        }
        int[] iArr = this.c;
        iArr[i] = iArr[i] + 1;
    }

    final void a(e eVar) {
        eVar.c(c());
        eVar.c(this.b);
        for (int c : this.c) {
            eVar.c(c);
        }
    }

    public final int b(int i) {
        return this.c[i];
    }

    public final Object clone() {
        g gVar = new g();
        gVar.a = this.a;
        gVar.b = this.b;
        System.arraycopy(this.c, 0, gVar.c, 0, this.c.length);
        return gVar;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(";; ->>HEADER<<- ");
        stringBuffer.append(", id: " + c());
        stringBuffer.append("\n");
        stringBuffer.append("; ");
        for (int i = 0; i < 4; i++) {
            stringBuffer.append(q.a(i) + ": " + this.c[i] + " ");
        }
        return stringBuffer.toString();
    }
}
