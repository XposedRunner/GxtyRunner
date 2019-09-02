package cn.jiguang.d.g.a;

import android.support.v4.view.ViewCompat;
import android.util.Base64;
import cn.jiguang.e.d;
import cn.jiguang.f.b;
import java.lang.reflect.InvocationTargetException;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class a {
    private static String a = "";
    private static int b = 0;

    public static String a() {
        return a;
    }

    public static String a(long j) {
        long j2;
        switch (((int) j) % 10) {
            case 1:
                j2 = (5 * j) + (j % 88);
                break;
            case 2:
                j2 = (23 * j) + (j % 15);
                break;
            case 3:
                j2 = (3 * j) + (j % 73);
                break;
            case 4:
                j2 = (13 * j) + (j % 96);
                break;
            case 5:
                j2 = (17 * j) + (j % 49);
                break;
            case 6:
                j2 = (7 * j) + (j % 68);
                break;
            case 7:
                j2 = (31 * j) + (j % 39);
                break;
            case 8:
                j2 = (29 * j) + (j % 41);
                break;
            case 9:
                j2 = (37 * j) + (j % 91);
                break;
            default:
                j2 = (8 * j) + (j % 74);
                break;
        }
        return cn.jiguang.g.a.a("JCKP" + String.valueOf(j2));
    }

    public static String a(String str) {
        String str2 = "";
        try {
            String str3 = "DFA84B10B7ACDD25";
            if (str3.length() != 16) {
                return null;
            }
            byte[] c = c(str3, "ASCII");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, new SecretKeySpec(c, "AES"), a(c));
            return Base64.encodeToString(instance.doFinal(str.getBytes()), 2);
        } catch (Exception e) {
            d.g("", "Unexpected - failed to AES encrypt.");
            return str2;
        }
    }

    public static String a(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        try {
            if (str2.length() != 16) {
                return null;
            }
            byte[] c = c(str2, "ASCII");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, new SecretKeySpec(c, "AES"), a(c));
            try {
                return new String(instance.doFinal(Base64.decode(str, 2)));
            } catch (Exception e) {
                return null;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    private static IvParameterSpec a(byte[] bArr) {
        try {
            return (IvParameterSpec) b.a(IvParameterSpec.class, new Object[]{bArr}, new Class[]{byte[].class});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        } catch (InstantiationException e4) {
            e4.printStackTrace();
        }
        return null;
    }

    public static void a(int i) {
        b = i;
    }

    public static byte[] a(String str, byte[] bArr) {
        Key secretKeySpec = new SecretKeySpec(c(str, "utf-8"), "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding");
        instance.init(1, secretKeySpec);
        return instance.doFinal(bArr);
    }

    public static byte[] a(byte[] bArr, int i) {
        byte[] bArr2;
        Exception e;
        int length = bArr.length - 24;
        Object obj = new byte[24];
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, 0, obj, 0, 24);
        System.arraycopy(bArr, 24, bArr3, 0, length);
        String str = "";
        if (i == 1) {
            str = b(cn.jiguang.d.a.d.d(null));
        } else if (i == 0) {
            str = b((long) b);
        }
        try {
            Object a = a(str, bArr3);
            int length2 = a.length + 24;
            bArr2 = new byte[length2];
            try {
                System.arraycopy(obj, 0, bArr2, 0, 24);
                System.arraycopy(a, 0, bArr2, 24, a.length);
                bArr2[0] = (byte) ((length2 >>> 8) & 255);
                bArr2[1] = (byte) (length2 & 255);
                bArr2[0] = (byte) (bArr2[0] | 128);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return bArr2;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            bArr2 = null;
            e = exception;
            e.printStackTrace();
            return bArr2;
        }
        return bArr2;
    }

    public static int b() {
        return Math.abs(new SecureRandom().nextInt()) & ViewCompat.MEASURED_SIZE_MASK;
    }

    private static String b(long j) {
        long j2;
        String valueOf = String.valueOf(j);
        int length = valueOf.length();
        if (length >= 2) {
            valueOf = valueOf.substring(length - 2, length);
        }
        switch (Integer.parseInt(valueOf) % 10) {
            case 1:
                j2 = (5 * j) + (j % 88);
                break;
            case 2:
                j2 = (23 * j) + (j % 15);
                break;
            case 3:
                j2 = (3 * j) + (j % 73);
                break;
            case 4:
                j2 = (13 * j) + (j % 96);
                break;
            case 5:
                j2 = (17 * j) + (j % 49);
                break;
            case 6:
                j2 = (7 * j) + (j % 68);
                break;
            case 7:
                j2 = (31 * j) + (j % 39);
                break;
            case 8:
                j2 = (29 * j) + (j % 41);
                break;
            case 9:
                j2 = (37 * j) + (j % 91);
                break;
            default:
                j2 = (8 * j) + (j % 74);
                break;
        }
        valueOf = cn.jiguang.g.a.a("JCKP" + String.valueOf(j2));
        a = valueOf;
        return valueOf;
    }

    public static String b(String str, String str2) {
        try {
            str2 = a(str, "DFA84B10B7ACDD25");
        } catch (Exception e) {
            d.g("", "Unexpected - failed to AES decrypt.");
        }
        return str2;
    }

    public static byte[] b(String str, byte[] bArr) {
        Key secretKeySpec = new SecretKeySpec(c(str, "utf-8"), "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/NoPadding");
        instance.init(2, secretKeySpec);
        return instance.doFinal(bArr);
    }

    private static byte[] c(String str, String str2) {
        Object obj = new byte[str.length()];
        Object bytes = str.substring(0, str.length() / 2).getBytes(str2);
        Object bytes2 = str.substring(str.length() / 2).getBytes(str2);
        System.arraycopy(bytes, 0, obj, 0, bytes.length);
        System.arraycopy(bytes2, 0, obj, bytes.length, bytes2.length);
        return obj;
    }
}
