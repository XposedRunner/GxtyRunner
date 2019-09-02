package com.loc;

/* compiled from: AESMD5Util */
public final class dm {
    private static byte[] a = m.a;
    private static byte[] b = m.b;
    private static int c = 6;

    public static byte[] a(byte[] bArr) {
        try {
            return dg.b(a, bArr, b);
        } catch (Throwable th) {
            return new byte[0];
        }
    }

    public static byte[] b(byte[] bArr) {
        try {
            return dg.a(a, bArr, b);
        } catch (Exception e) {
            return new byte[0];
        }
    }
}
