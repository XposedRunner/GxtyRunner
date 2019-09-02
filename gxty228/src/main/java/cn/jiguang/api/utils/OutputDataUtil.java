package cn.jiguang.api.utils;

import cn.jiguang.e.d;
import java.math.BigInteger;

public class OutputDataUtil {
    private static final BigInteger d = BigInteger.ONE.shiftLeft(64);
    private byte[] a;
    private int b;
    private int c;

    public OutputDataUtil() {
        this(32);
    }

    public OutputDataUtil(int i) {
        this.a = new byte[i];
        this.b = 0;
        this.c = -1;
    }

    private void a(int i) {
        if (this.a.length - this.b < i) {
            int length = this.a.length * 2;
            if (length < this.b + i) {
                length = this.b + i;
            }
            Object obj = new byte[length];
            System.arraycopy(this.a, 0, obj, 0, this.b);
            this.a = obj;
        }
    }

    private static void a(long j, int i) {
        long j2 = 1 << i;
        if (j < 0 || j > j2) {
            d.g("OutputDataUtil", j + " out of range for " + i + " bit value max:" + j2);
        }
    }

    public static int encodeZigZag32(int i) {
        return (i << 1) ^ (i >> 31);
    }

    public static long encodeZigZag64(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public int current() {
        return this.b;
    }

    public void jump(int i) {
        if (i > this.b) {
            throw new IllegalArgumentException("cannot jump past end of data");
        }
        this.b = i;
    }

    public void restore() {
        if (this.c < 0) {
            throw new IllegalStateException("no previous state");
        }
        this.b = this.c;
        this.c = -1;
    }

    public void save() {
        this.c = this.b;
    }

    public byte[] toByteArray() {
        Object obj = new byte[this.b];
        System.arraycopy(this.a, 0, obj, 0, this.b);
        return obj;
    }

    public void writeByteArray(byte[] bArr) {
        writeByteArray(bArr, 0, bArr.length);
    }

    public void writeByteArray(byte[] bArr, int i, int i2) {
        a(i2);
        System.arraycopy(bArr, i, this.a, this.b, i2);
        this.b += i2;
    }

    public void writeByteArrayincludeLength(byte[] bArr) {
        writeU16(bArr.length);
        writeByteArray(bArr, 0, bArr.length);
    }

    public void writeCountedString(byte[] bArr) {
        if (bArr.length > 255) {
            throw new IllegalArgumentException("Invalid counted string");
        }
        a(bArr.length + 1);
        byte[] bArr2 = this.a;
        int i = this.b;
        this.b = i + 1;
        bArr2[i] = (byte) (bArr.length & 255);
        writeByteArray(bArr, 0, bArr.length);
    }

    public void writeRawLittleEndian16(int i) {
        byte[] bArr = this.a;
        int i2 = this.b;
        this.b = i2 + 1;
        bArr[i2] = (byte) (i & 255);
        bArr = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        bArr[i2] = (byte) ((i >> 8) & 255);
    }

    public void writeRawLittleEndian32(int i) {
        byte[] bArr = this.a;
        int i2 = this.b;
        this.b = i2 + 1;
        bArr[i2] = (byte) (i & 255);
        bArr = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        bArr[i2] = (byte) ((i >> 8) & 255);
        bArr = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        bArr[i2] = (byte) ((i >> 16) & 255);
        bArr = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        bArr[i2] = (byte) ((i >> 24) & 255);
    }

    public void writeRawLittleEndian64(long j) {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) (((int) j) & 255);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) (((int) (j >> 8)) & 255);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) (((int) (j >> 16)) & 255);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) (((int) (j >> 24)) & 255);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) (((int) (j >> 32)) & 255);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) (((int) (j >> 40)) & 255);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) (((int) (j >> 48)) & 255);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) (((int) (j >> 56)) & 255);
    }

    public void writeU16(int i) {
        a((long) i, 16);
        a(2);
        byte[] bArr = this.a;
        int i2 = this.b;
        this.b = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        bArr[i2] = (byte) (i & 255);
    }

    public void writeU16At(int i, int i2) {
        a((long) i, 16);
        if (i2 > this.b - 2) {
            throw new IllegalArgumentException("cannot write past end of data");
        }
        int i3 = i2 + 1;
        this.a[i2] = (byte) ((i >>> 8) & 255);
        this.a[i3] = (byte) (i & 255);
    }

    public void writeU32(long j) {
        a(j, 32);
        a(4);
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 24) & 255));
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 16) & 255));
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 8) & 255));
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) ((int) (j & 255));
    }

    public void writeU32At(long j, int i) {
        a(j, 32);
        if (i > this.b - 4) {
            throw new IllegalArgumentException("cannot write past end of data");
        }
        int i2 = i + 1;
        this.a[i] = (byte) ((int) ((j >>> 24) & 255));
        int i3 = i2 + 1;
        this.a[i2] = (byte) ((int) ((j >>> 16) & 255));
        i2 = i3 + 1;
        this.a[i3] = (byte) ((int) ((j >>> 8) & 255));
        this.a[i2] = (byte) ((int) (j & 255));
    }

    public void writeU64(long j) {
        a(8);
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 56) & 255));
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 48) & 255));
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 40) & 255));
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 32) & 255));
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 24) & 255));
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 16) & 255));
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 8) & 255));
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        bArr[i] = (byte) ((int) (j & 255));
    }

    public void writeU64At(long j, int i) {
        int i2 = i + 1;
        this.a[i] = (byte) ((int) ((j >>> 56) & 255));
        int i3 = i2 + 1;
        this.a[i2] = (byte) ((int) ((j >>> 48) & 255));
        i2 = i3 + 1;
        this.a[i3] = (byte) ((int) ((j >>> 40) & 255));
        i3 = i2 + 1;
        this.a[i2] = (byte) ((int) ((j >>> 32) & 255));
        i2 = i3 + 1;
        this.a[i3] = (byte) ((int) ((j >>> 24) & 255));
        i3 = i2 + 1;
        this.a[i2] = (byte) ((int) ((j >>> 16) & 255));
        i2 = i3 + 1;
        this.a[i3] = (byte) ((int) ((j >>> 8) & 255));
        this.a[i2] = (byte) ((int) (j & 255));
    }

    public void writeU8(int i) {
        a((long) i, 8);
        a(1);
        byte[] bArr = this.a;
        int i2 = this.b;
        this.b = i2 + 1;
        bArr[i2] = (byte) (i & 255);
    }

    public void writeU8At(int i, int i2) {
        a((long) i, 8);
        if (i2 > this.b - 1) {
            throw new IllegalArgumentException("cannot write past end of data");
        }
        this.a[i2] = (byte) (i & 255);
    }
}
