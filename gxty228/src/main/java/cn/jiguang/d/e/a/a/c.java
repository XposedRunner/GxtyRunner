package cn.jiguang.d.e.a.a;

import cn.jiguang.api.utils.ProtocolUtil;
import java.nio.ByteBuffer;

public final class c {
    int a;
    int b;
    int c;
    Long d;
    int e;
    long f;
    private boolean g;

    public c(boolean z, int i, int i2, int i3, long j, int i4, long j2) {
        this.g = false;
        this.g = z;
        this.a = 0;
        this.b = i2;
        this.c = i3;
        this.d = Long.valueOf(j);
        this.e = i4;
        this.f = j2;
    }

    public c(boolean z, int i, int i2, long j) {
        this(z, 0, i, i2, j, 0, 0);
    }

    public c(boolean z, byte[] bArr) {
        this.g = false;
        this.g = z;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.a = wrap.getShort();
        this.a &= 32767;
        this.b = wrap.get();
        this.c = wrap.get();
        this.d = Long.valueOf(wrap.getLong());
        if (z) {
            this.e = wrap.getInt();
        }
        this.f = wrap.getLong();
    }

    public final int a() {
        return this.c;
    }

    public final void a(int i) {
        this.a = i;
    }

    public final void a(long j) {
        this.f = j;
    }

    public final void a(Long l) {
        this.d = l;
    }

    public final Long b() {
        return this.d;
    }

    public final void b(int i) {
        this.e = i;
    }

    public final long c() {
        return this.f;
    }

    public final int d() {
        return this.e;
    }

    public final int e() {
        return this.b;
    }

    public final int f() {
        return this.a;
    }

    public final byte[] g() {
        if (this.a == 0) {
            throw new IllegalStateException("The head is not initialized yet.");
        }
        ByteBuffer allocate = ByteBuffer.allocate(24);
        allocate.putShort((short) this.a);
        allocate.put((byte) this.b);
        allocate.put((byte) this.c);
        allocate.putLong(this.d.longValue());
        if (this.g) {
            allocate.putInt(this.e);
        }
        allocate.putLong(this.f);
        allocate.flip();
        return ProtocolUtil.getBytesConsumed(allocate);
    }

    public final String toString() {
        return "[JHead] - len:" + this.a + ", version:" + this.b + ", command:" + this.c + ", rid:" + this.d + (this.g ? ", sid:" + this.e : "") + ", juid:" + this.f;
    }
}
