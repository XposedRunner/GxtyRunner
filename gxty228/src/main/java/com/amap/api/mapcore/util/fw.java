package com.amap.api.mapcore.util;

/* compiled from: AESMD5Util */
public class fw {
    private static byte[] a = hb.a;
    private static byte[] b = hb.b;
    private static int c = 6;

    public static byte[] a(byte[] bArr) {
        try {
            return ge.b(a, bArr, b);
        } catch (Throwable th) {
            return new byte[0];
        }
    }

    public static byte[] b(byte[] bArr) {
        try {
            return ge.a(a, bArr, b);
        } catch (Exception e) {
            return new byte[0];
        }
    }
}
