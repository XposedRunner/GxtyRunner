package cn.jiguang.d.c;

import android.support.v4.internal.view.SupportMenu;
import java.nio.ByteBuffer;

public final class d {
    private ByteBuffer a;
    private int b = -1;
    private int c = -1;

    public d(byte[] bArr) {
        this.a = ByteBuffer.wrap(bArr);
    }

    private void c(int i) {
        if (i > this.a.remaining()) {
            throw new t("end of input");
        }
    }

    public final int a() {
        return this.a.position();
    }

    public final void a(int i) {
        if (i > this.a.capacity() - this.a.position()) {
            throw new IllegalArgumentException("cannot set active region past end of input");
        }
        this.a.limit(this.a.position() + i);
    }

    public final void a(byte[] bArr, int i, int i2) {
        c(i2);
        this.a.get(bArr, 1, i2);
    }

    public final int b() {
        return this.a.remaining();
    }

    public final void b(int i) {
        if (i >= this.a.capacity()) {
            throw new IllegalArgumentException("cannot jump past end of input");
        }
        this.a.position(i);
        this.a.limit(this.a.capacity());
    }

    public final void c() {
        this.a.limit(this.a.capacity());
    }

    public final void d() {
        this.b = this.a.position();
        this.c = this.a.limit();
    }

    public final void e() {
        if (this.b < 0) {
            throw new IllegalStateException("no previous state");
        }
        this.a.position(this.b);
        this.a.limit(this.c);
        this.b = -1;
        this.c = -1;
    }

    public final int f() {
        c(1);
        return this.a.get() & 255;
    }

    public final int g() {
        c(2);
        return this.a.getShort() & SupportMenu.USER_MASK;
    }

    public final long h() {
        c(4);
        return ((long) this.a.getInt()) & 4294967295L;
    }
}
