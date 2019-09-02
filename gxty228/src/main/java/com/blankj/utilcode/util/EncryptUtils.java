package com.blankj.utilcode.util;

import android.util.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class EncryptUtils {
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private EncryptUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String encryptMD2ToString(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        return encryptMD2ToString(str.getBytes());
    }

    public static String encryptMD2ToString(byte[] bArr) {
        return bytes2HexString(encryptMD2(bArr));
    }

    public static byte[] encryptMD2(byte[] bArr) {
        return hashTemplate(bArr, "MD2");
    }

    public static String encryptMD5ToString(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        return encryptMD5ToString(str.getBytes());
    }

    public static String encryptMD5ToString(String str, String str2) {
        if (str == null && str2 == null) {
            return "";
        }
        if (str2 == null) {
            return bytes2HexString(encryptMD5(str.getBytes()));
        }
        if (str == null) {
            return bytes2HexString(encryptMD5(str2.getBytes()));
        }
        return bytes2HexString(encryptMD5((str + str2).getBytes()));
    }

    public static String encryptMD5ToString(byte[] bArr) {
        return bytes2HexString(encryptMD5(bArr));
    }

    public static String encryptMD5ToString(byte[] bArr, byte[] bArr2) {
        if (bArr == null && bArr2 == null) {
            return "";
        }
        if (bArr2 == null) {
            return bytes2HexString(encryptMD5(bArr));
        }
        if (bArr == null) {
            return bytes2HexString(encryptMD5(bArr2));
        }
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return bytes2HexString(encryptMD5(obj));
    }

    public static byte[] encryptMD5(byte[] bArr) {
        return hashTemplate(bArr, "MD5");
    }

    public static String encryptMD5File2String(String str) {
        return encryptMD5File2String(isSpace(str) ? null : new File(str));
    }

    public static byte[] encryptMD5File(String str) {
        return encryptMD5File(isSpace(str) ? null : new File(str));
    }

    public static String encryptMD5File2String(File file) {
        return bytes2HexString(encryptMD5File(file));
    }

    public static byte[] encryptMD5File(File file) {
        FileInputStream fileInputStream;
        Exception e;
        Throwable th;
        byte[] bArr = null;
        if (file != null) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, MessageDigest.getInstance("MD5"));
                    do {
                    } while (digestInputStream.read(new byte[262144]) > 0);
                    bArr = digestInputStream.getMessageDigest().digest();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (NoSuchAlgorithmException e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        return bArr;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    e = e4;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return bArr;
                }
            } catch (NoSuchAlgorithmException e5) {
                e = e5;
                fileInputStream = null;
                e.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return bArr;
            } catch (IOException e6) {
                e = e6;
                fileInputStream = null;
                e.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return bArr;
            } catch (Throwable th3) {
                fileInputStream = null;
                th = th3;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
        return bArr;
    }

    public static String encryptSHA1ToString(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        return encryptSHA1ToString(str.getBytes());
    }

    public static String encryptSHA1ToString(byte[] bArr) {
        return bytes2HexString(encryptSHA1(bArr));
    }

    public static byte[] encryptSHA1(byte[] bArr) {
        return hashTemplate(bArr, "SHA-1");
    }

    public static String encryptSHA224ToString(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        return encryptSHA224ToString(str.getBytes());
    }

    public static String encryptSHA224ToString(byte[] bArr) {
        return bytes2HexString(encryptSHA224(bArr));
    }

    public static byte[] encryptSHA224(byte[] bArr) {
        return hashTemplate(bArr, "SHA224");
    }

    public static String encryptSHA256ToString(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        return encryptSHA256ToString(str.getBytes());
    }

    public static String encryptSHA256ToString(byte[] bArr) {
        return bytes2HexString(encryptSHA256(bArr));
    }

    public static byte[] encryptSHA256(byte[] bArr) {
        return hashTemplate(bArr, "SHA-256");
    }

    public static String encryptSHA384ToString(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        return encryptSHA384ToString(str.getBytes());
    }

    public static String encryptSHA384ToString(byte[] bArr) {
        return bytes2HexString(encryptSHA384(bArr));
    }

    public static byte[] encryptSHA384(byte[] bArr) {
        return hashTemplate(bArr, "SHA-384");
    }

    public static String encryptSHA512ToString(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        return encryptSHA512ToString(str.getBytes());
    }

    public static String encryptSHA512ToString(byte[] bArr) {
        return bytes2HexString(encryptSHA512(bArr));
    }

    public static byte[] encryptSHA512(byte[] bArr) {
        return hashTemplate(bArr, "SHA-512");
    }

    private static byte[] hashTemplate(byte[] bArr, String str) {
        byte[] bArr2 = null;
        if (bArr != null && bArr.length > 0) {
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                instance.update(bArr);
                bArr2 = instance.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return bArr2;
    }

    public static String encryptHmacMD5ToString(String str, String str2) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return "";
        }
        return encryptHmacMD5ToString(str.getBytes(), str2.getBytes());
    }

    public static String encryptHmacMD5ToString(byte[] bArr, byte[] bArr2) {
        return bytes2HexString(encryptHmacMD5(bArr, bArr2));
    }

    public static byte[] encryptHmacMD5(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacMD5");
    }

    public static String encryptHmacSHA1ToString(String str, String str2) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return "";
        }
        return encryptHmacSHA1ToString(str.getBytes(), str2.getBytes());
    }

    public static String encryptHmacSHA1ToString(byte[] bArr, byte[] bArr2) {
        return bytes2HexString(encryptHmacSHA1(bArr, bArr2));
    }

    public static byte[] encryptHmacSHA1(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacSHA1");
    }

    public static String encryptHmacSHA224ToString(String str, String str2) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return "";
        }
        return encryptHmacSHA224ToString(str.getBytes(), str2.getBytes());
    }

    public static String encryptHmacSHA224ToString(byte[] bArr, byte[] bArr2) {
        return bytes2HexString(encryptHmacSHA224(bArr, bArr2));
    }

    public static byte[] encryptHmacSHA224(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacSHA224");
    }

    public static String encryptHmacSHA256ToString(String str, String str2) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return "";
        }
        return encryptHmacSHA256ToString(str.getBytes(), str2.getBytes());
    }

    public static String encryptHmacSHA256ToString(byte[] bArr, byte[] bArr2) {
        return bytes2HexString(encryptHmacSHA256(bArr, bArr2));
    }

    public static byte[] encryptHmacSHA256(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacSHA256");
    }

    public static String encryptHmacSHA384ToString(String str, String str2) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return "";
        }
        return encryptHmacSHA384ToString(str.getBytes(), str2.getBytes());
    }

    public static String encryptHmacSHA384ToString(byte[] bArr, byte[] bArr2) {
        return bytes2HexString(encryptHmacSHA384(bArr, bArr2));
    }

    public static byte[] encryptHmacSHA384(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacSHA384");
    }

    public static String encryptHmacSHA512ToString(String str, String str2) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return "";
        }
        return encryptHmacSHA512ToString(str.getBytes(), str2.getBytes());
    }

    public static String encryptHmacSHA512ToString(byte[] bArr, byte[] bArr2) {
        return bytes2HexString(encryptHmacSHA512(bArr, bArr2));
    }

    public static byte[] encryptHmacSHA512(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacSHA512");
    }

    private static byte[] hmacTemplate(byte[] bArr, byte[] bArr2, String str) {
        GeneralSecurityException e;
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            return null;
        }
        try {
            Key secretKeySpec = new SecretKeySpec(bArr2, str);
            Mac instance = Mac.getInstance(str);
            instance.init(secretKeySpec);
            return instance.doFinal(bArr);
        } catch (InvalidKeyException e2) {
            e = e2;
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e3) {
            e = e3;
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] encryptDES2Base64(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return base64Encode(encryptDES(bArr, bArr2, str, bArr3));
    }

    public static String encryptDES2HexString(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return bytes2HexString(encryptDES(bArr, bArr2, str, bArr3));
    }

    public static byte[] encryptDES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return symmetricTemplate(bArr, bArr2, "DES", str, bArr3, true);
    }

    public static byte[] decryptBase64DES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return decryptDES(base64Decode(bArr), bArr2, str, bArr3);
    }

    public static byte[] decryptHexStringDES(String str, byte[] bArr, String str2, byte[] bArr2) {
        return decryptDES(hexString2Bytes(str), bArr, str2, bArr2);
    }

    public static byte[] decryptDES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return symmetricTemplate(bArr, bArr2, "DES", str, bArr3, false);
    }

    public static byte[] encrypt3DES2Base64(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return base64Encode(encrypt3DES(bArr, bArr2, str, bArr3));
    }

    public static String encrypt3DES2HexString(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return bytes2HexString(encrypt3DES(bArr, bArr2, str, bArr3));
    }

    public static byte[] encrypt3DES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return symmetricTemplate(bArr, bArr2, "DESede", str, bArr3, true);
    }

    public static byte[] decryptBase64_3DES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return decrypt3DES(base64Decode(bArr), bArr2, str, bArr3);
    }

    public static byte[] decryptHexString3DES(String str, byte[] bArr, String str2, byte[] bArr2) {
        return decrypt3DES(hexString2Bytes(str), bArr, str2, bArr2);
    }

    public static byte[] decrypt3DES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return symmetricTemplate(bArr, bArr2, "DESede", str, bArr3, false);
    }

    public static byte[] encryptAES2Base64(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return base64Encode(encryptAES(bArr, bArr2, str, bArr3));
    }

    public static String encryptAES2HexString(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return bytes2HexString(encryptAES(bArr, bArr2, str, bArr3));
    }

    public static byte[] encryptAES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return symmetricTemplate(bArr, bArr2, "AES", str, bArr3, true);
    }

    public static byte[] decryptBase64AES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return decryptAES(base64Decode(bArr), bArr2, str, bArr3);
    }

    public static byte[] decryptHexStringAES(String str, byte[] bArr, String str2, byte[] bArr2) {
        return decryptAES(hexString2Bytes(str), bArr, str2, bArr2);
    }

    public static byte[] decryptAES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return symmetricTemplate(bArr, bArr2, "AES", str, bArr3, false);
    }

    private static byte[] symmetricTemplate(byte[] bArr, byte[] bArr2, String str, String str2, byte[] bArr3, boolean z) {
        int i = 1;
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            return null;
        }
        try {
            Key secretKeySpec = new SecretKeySpec(bArr2, str);
            Cipher instance = Cipher.getInstance(str2);
            if (bArr3 == null || bArr3.length == 0) {
                if (!z) {
                    i = 2;
                }
                instance.init(i, secretKeySpec);
            } else {
                AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
                if (!z) {
                    i = 2;
                }
                instance.init(i, secretKeySpec, ivParameterSpec);
            }
            return instance.doFinal(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static byte[] encryptRSA2Base64(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return base64Encode(encryptRSA(bArr, bArr2, z, str));
    }

    public static String encryptRSA2HexString(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return bytes2HexString(encryptRSA(bArr, bArr2, z, str));
    }

    public static byte[] encryptRSA(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return rsaTemplate(bArr, bArr2, z, str, true);
    }

    public static byte[] decryptBase64RSA(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return decryptRSA(base64Decode(bArr), bArr2, z, str);
    }

    public static byte[] decryptHexStringRSA(String str, byte[] bArr, boolean z, String str2) {
        return decryptRSA(hexString2Bytes(str), bArr, z, str2);
    }

    public static byte[] decryptRSA(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return rsaTemplate(bArr, bArr2, z, str, false);
    }

    private static byte[] rsaTemplate(byte[] bArr, byte[] bArr2, boolean z, String str, boolean z2) {
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            return null;
        }
        if (z) {
            try {
                Key generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr2));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            } catch (NoSuchPaddingException e2) {
                e2.printStackTrace();
                return null;
            } catch (InvalidKeyException e3) {
                e3.printStackTrace();
                return null;
            } catch (BadPaddingException e4) {
                e4.printStackTrace();
                return null;
            } catch (IllegalBlockSizeException e5) {
                e5.printStackTrace();
                return null;
            } catch (InvalidKeySpecException e6) {
                e6.printStackTrace();
                return null;
            }
        }
        Object generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(bArr2));
        if (generatePublic == null) {
            return null;
        }
        int i;
        Cipher instance = Cipher.getInstance(str);
        instance.init(z2 ? 1 : 2, generatePublic);
        int length = bArr.length;
        if (z2) {
            i = 117;
        } else {
            i = 128;
        }
        int i2 = length / i;
        if (i2 <= 0) {
            return instance.doFinal(bArr);
        }
        Object obj = new byte[i];
        int i3 = 0;
        byte[] bArr3 = new byte[0];
        int i4 = 0;
        while (i4 < i2) {
            System.arraycopy(bArr, i3, obj, 0, i);
            i3 += i;
            i4++;
            bArr3 = joins(bArr3, instance.doFinal(obj));
        }
        if (i3 == length) {
            return bArr3;
        }
        i4 = length - i3;
        Object obj2 = new byte[i4];
        System.arraycopy(bArr, i3, obj2, 0, i4);
        return joins(bArr3, instance.doFinal(obj2));
    }

    private static byte[] joins(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }

    private static String bytes2HexString(byte[] bArr) {
        int i = 0;
        if (bArr == null) {
            return "";
        }
        int length = bArr.length;
        if (length <= 0) {
            return "";
        }
        char[] cArr = new char[(length << 1)];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr[i] = HEX_DIGITS[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr[i3] = HEX_DIGITS[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    private static byte[] hexString2Bytes(String str) {
        if (isSpace(str)) {
            return null;
        }
        int length = str.length();
        if (length % 2 != 0) {
            str = "0" + str;
            length++;
        }
        char[] toCharArray = str.toUpperCase().toCharArray();
        byte[] bArr = new byte[(length >> 1)];
        for (int i = 0; i < length; i += 2) {
            bArr[i >> 1] = (byte) ((hex2Dec(toCharArray[i]) << 4) | hex2Dec(toCharArray[i + 1]));
        }
        return bArr;
    }

    private static int hex2Dec(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 65) + 10;
        }
        throw new IllegalArgumentException();
    }

    private static byte[] base64Encode(byte[] bArr) {
        return Base64.encode(bArr, 2);
    }

    private static byte[] base64Decode(byte[] bArr) {
        return Base64.decode(bArr, 2);
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
