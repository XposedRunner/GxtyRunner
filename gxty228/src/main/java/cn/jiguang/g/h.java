package cn.jiguang.g;

import android.support.v4.internal.view.SupportMenu;
import java.nio.ByteBuffer;

public final class h {
    private ByteBuffer a;
    private int b = -1;
    private int c = -1;

    public h(byte[] bArr) {
        this.a = ByteBuffer.wrap(bArr);
    }

    private void b(int i) {
        if (i > this.a.remaining()) {
            throw new o("end of input");
        }
    }

    public final int a() {
        b(2);
        return this.a.getShort() & SupportMenu.USER_MASK;
    }

    public final byte[] a(int i) {
        b(2);
        byte[] bArr = new byte[2];
        this.a.get(bArr, 0, 2);
        return bArr;
    }

    public final long b() {
        b(4);
        return ((long) this.a.getInt()) & 4294967295L;
    }

    public final byte[] c() {
        int remaining = this.a.remaining();
        byte[] bArr = new byte[remaining];
        this.a.get(bArr, 0, remaining);
        return bArr;
    }
}
