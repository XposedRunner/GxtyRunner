package com.amap.api.mapcore.util;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: Encrypt */
public final class ku {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final byte[] b = new byte[]{(byte) 0, (byte) 1, (byte) 1, (byte) 2, (byte) 3, (byte) 5, (byte) 8, (byte) 13, (byte) 8, (byte) 7, (byte) 6, (byte) 5, (byte) 4, (byte) 3, (byte) 2, (byte) 1};
    private static final IvParameterSpec c = new IvParameterSpec(b);

    public static byte[] a(byte[] bArr) {
        int i = 0;
        try {
            Object obj = new byte[16];
            Object obj2 = new byte[(bArr.length - 16)];
            System.arraycopy(bArr, 0, obj, 0, 16);
            System.arraycopy(bArr, 16, obj2, 0, bArr.length - 16);
            Key secretKeySpec = new SecretKeySpec(obj, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec(gl.b()));
            return instance.doFinal(obj2);
        } catch (Throwable th) {
            if (bArr != null) {
                i = bArr.length;
            }
            kz.a(th, "Encrypt", "decryptRsponse length = " + i);
            return null;
        }
    }
}
