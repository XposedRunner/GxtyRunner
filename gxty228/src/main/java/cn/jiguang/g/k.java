package cn.jiguang.g;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public final class k {
    private static String a = "0123456789ABCDEF";

    public static CharSequence a(CharSequence charSequence, int i) {
        return (i < 0 || i >= charSequence.length()) ? charSequence : charSequence.subSequence(0, i);
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte a : bArr) {
            a(stringBuffer, a);
            stringBuffer.append(' ');
        }
        return stringBuffer.toString();
    }

    private static void a(StringBuffer stringBuffer, byte b) {
        stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15)).append("0123456789ABCDEF".charAt(b & 15));
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0 || str.trim().length() == 0;
    }

    public static boolean a(String str, String str2) {
        return (str == null || str2 == null || !str.equals(str2)) ? false : true;
    }

    public static String b(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            if (digest == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer(digest.length * 2);
            for (byte a : digest) {
                a(stringBuffer, a);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String c(String str) {
        return a(str) ? "" : Pattern.compile("[^\\w#$@\\-一-龥]+").matcher(str).replaceAll("");
    }

    public static boolean d(String str) {
        if (a(str)) {
            return false;
        }
        try {
            return Pattern.compile("[\\x20-\\x7E]+").matcher(str).matches();
        } catch (Throwable th) {
            return true;
        }
    }

    public static boolean e(String str) {
        if (a(str)) {
            return false;
        }
        try {
            return Pattern.compile("([A-Fa-f0-9]{2}[-:]){5,}[A-Fa-f0-9]{2}").matcher(str).matches();
        } catch (Throwable th) {
            return true;
        }
    }
}
