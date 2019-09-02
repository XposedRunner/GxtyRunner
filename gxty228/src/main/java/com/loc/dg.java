package com.loc;

import cn.jiguang.net.HttpUtils;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.tencent.bugly.beta.tinker.TinkerReport;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: Encrypt */
public final class dg {
    private static final char[] a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final byte[] b = new byte[128];

    static {
        int i;
        for (i = 0; i < 128; i++) {
            b[i] = (byte) -1;
        }
        for (i = 65; i <= 90; i++) {
            b[i] = (byte) (i - 65);
        }
        for (i = 97; i <= TinkerReport.KEY_APPLIED_DEXOPT_EXIST; i++) {
            b[i] = (byte) ((i - 97) + 26);
        }
        for (i = 48; i <= 57; i++) {
            b[i] = (byte) ((i - 48) + 52);
        }
        b[43] = (byte) 62;
        b[47] = (byte) 63;
    }

    public static String a(String str) {
        return dl.a(b(str));
    }

    public static byte[] a(byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        if (instance == null) {
            return null;
        }
        instance.init(256);
        byte[] encoded = instance.generateKey().getEncoded();
        Key c = dl.c();
        if (c == null) {
            return null;
        }
        Object a = a(encoded, c);
        Object a2 = a(encoded, bArr);
        Object obj = new byte[(a.length + a2.length)];
        System.arraycopy(a, 0, obj, 0, a.length);
        System.arraycopy(a2, 0, obj, a.length, a2.length);
        return obj;
    }

    static byte[] a(byte[] bArr, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher instance = Cipher.getInstance(dl.c(e.a));
        instance.init(1, key);
        return instance.doFinal(bArr);
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            return c(bArr, bArr2, dl.b());
        } catch (Throwable th) {
            g.a(th, "er", "asEn");
            return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, ivParameterSpec);
        return instance.doFinal(bArr2);
    }

    public static String b(byte[] bArr) {
        try {
            return d(bArr);
        } catch (Throwable th) {
            g.a(th, "er", "e64");
            return null;
        }
    }

    public static byte[] b(String str) {
        int i = 0;
        if (str == null) {
            return new byte[0];
        }
        byte[] a = dl.a(str);
        int length = a.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        while (i < length) {
            byte b;
            while (true) {
                int i2 = i + 1;
                byte b2 = b[a[i]];
                byte b3;
                byte b4;
                if (i2 >= length || b2 != (byte) -1) {
                    if (b2 == (byte) -1) {
                        break;
                    }
                    while (true) {
                        i = i2 + 1;
                        b = b[a[i2]];
                        if (i >= length || b != (byte) -1) {
                            if (b != (byte) -1) {
                                break;
                            }
                            byteArrayOutputStream.write((b2 << 2) | ((b & 48) >>> 4));
                            do {
                                i2 = i;
                                if (i2 != length) {
                                    i = i2 + 1;
                                    b3 = a[i2];
                                    if (b3 == (byte) 61) {
                                        b2 = b[b3];
                                        if (i >= length) {
                                            break;
                                        }
                                    } else {
                                        return byteArrayOutputStream.toByteArray();
                                    }
                                }
                                return byteArrayOutputStream.toByteArray();
                            } while (b2 == (byte) -1);
                            if (b2 != (byte) -1) {
                                break;
                            }
                            byteArrayOutputStream.write(((b & 15) << 4) | ((b2 & 60) >>> 2));
                            while (i != length) {
                                i2 = i + 1;
                                b4 = a[i];
                                if (b4 == (byte) 61) {
                                    return byteArrayOutputStream.toByteArray();
                                }
                                b4 = b[b4];
                                if (i2 >= length || b4 != (byte) -1) {
                                    if (b4 != (byte) -1) {
                                        break;
                                    }
                                    byteArrayOutputStream.write(b4 | ((b2 & 3) << 6));
                                    i = i2;
                                } else {
                                    i = i2;
                                }
                            }
                            return byteArrayOutputStream.toByteArray();
                        }
                        i2 = i;
                    }
                    if (b != (byte) -1) {
                        break;
                    }
                    byteArrayOutputStream.write((b2 << 2) | ((b & 48) >>> 4));
                    do {
                        i2 = i;
                        if (i2 != length) {
                            return byteArrayOutputStream.toByteArray();
                        }
                        i = i2 + 1;
                        b3 = a[i2];
                        if (b3 == (byte) 61) {
                            b2 = b[b3];
                            if (i >= length) {
                                break;
                            }
                            break;
                        }
                        return byteArrayOutputStream.toByteArray();
                    } while (b2 == (byte) -1);
                    if (b2 != (byte) -1) {
                        break;
                    }
                    byteArrayOutputStream.write(((b & 15) << 4) | ((b2 & 60) >>> 2));
                    while (i != length) {
                        i2 = i + 1;
                        b4 = a[i];
                        if (b4 == (byte) 61) {
                            return byteArrayOutputStream.toByteArray();
                        }
                        b4 = b[b4];
                        if (i2 >= length) {
                        }
                        if (b4 != (byte) -1) {
                            break;
                        }
                        byteArrayOutputStream.write(b4 | ((b2 & 3) << 6));
                        i = i2;
                    }
                    return byteArrayOutputStream.toByteArray();
                }
                i = i2;
            }
            if (b2 == (byte) -1) {
                while (true) {
                    i = i2 + 1;
                    b = b[a[i2]];
                    if (i >= length) {
                        break;
                    }
                    break;
                    i2 = i;
                }
                if (b != (byte) -1) {
                    break;
                }
                byteArrayOutputStream.write((b2 << 2) | ((b & 48) >>> 4));
                do {
                    i2 = i;
                    if (i2 != length) {
                        i = i2 + 1;
                        b3 = a[i2];
                        if (b3 == (byte) 61) {
                            b2 = b[b3];
                            if (i >= length) {
                                break;
                            }
                        } else {
                            return byteArrayOutputStream.toByteArray();
                        }
                    }
                    return byteArrayOutputStream.toByteArray();
                } while (b2 == (byte) -1);
                if (b2 != (byte) -1) {
                    break;
                }
                byteArrayOutputStream.write(((b & 15) << 4) | ((b2 & 60) >>> 2));
                while (i != length) {
                    i2 = i + 1;
                    b4 = a[i];
                    if (b4 == (byte) 61) {
                        b4 = b[b4];
                        if (i2 >= length) {
                        }
                        if (b4 != (byte) -1) {
                            break;
                        }
                        byteArrayOutputStream.write(b4 | ((b2 & 3) << 6));
                        i = i2;
                    } else {
                        return byteArrayOutputStream.toByteArray();
                    }
                }
                return byteArrayOutputStream.toByteArray();
            }
            break;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return c(bArr, bArr2, bArr3);
    }

    public static String c(byte[] bArr) {
        try {
            return d(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static byte[] c(byte[] bArr, byte[] bArr2, byte[] bArr3) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        try {
            instance.init(1, secretKeySpec, ivParameterSpec);
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return instance.doFinal(bArr2);
    }

    private static String d(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            int i3 = bArr[i] & 255;
            if (i2 == length) {
                stringBuffer.append(a[i3 >>> 2]);
                stringBuffer.append(a[(i3 & 3) << 4]);
                stringBuffer.append("==");
                break;
            }
            int i4 = i2 + 1;
            i2 = bArr[i2] & 255;
            if (i4 == length) {
                stringBuffer.append(a[i3 >>> 2]);
                stringBuffer.append(a[((i3 & 3) << 4) | ((i2 & GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN) >>> 4)]);
                stringBuffer.append(a[(i2 & 15) << 2]);
                stringBuffer.append(HttpUtils.EQUAL_SIGN);
                break;
            }
            i = i4 + 1;
            i4 = bArr[i4] & 255;
            stringBuffer.append(a[i3 >>> 2]);
            stringBuffer.append(a[((i3 & 3) << 4) | ((i2 & GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN) >>> 4)]);
            stringBuffer.append(a[((i2 & 15) << 2) | ((i4 & 192) >>> 6)]);
            stringBuffer.append(a[i4 & 63]);
        }
        return stringBuffer.toString();
    }
}
