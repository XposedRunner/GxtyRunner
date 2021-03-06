package com.loc;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: MD5 */
public final class dh {
    public static String a(String str) {
        Throwable e;
        String str2;
        String str3;
        Throwable th;
        String str4 = null;
        FileInputStream fileInputStream = null;
        FileInputStream fileInputStream2;
        try {
            if (TextUtils.isEmpty(str)) {
                if (str4 != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e = e2;
                        str2 = "MD5";
                        str3 = "gfm";
                        g.a(e, str2, str3);
                        return str4;
                    }
                }
                return str4;
            }
            File file = new File(str);
            if (file.isFile() && file.exists()) {
                byte[] bArr = new byte[2048];
                MessageDigest instance = MessageDigest.getInstance("MD5");
                fileInputStream2 = new FileInputStream(file);
                while (true) {
                    try {
                        int read = fileInputStream2.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        instance.update(bArr, 0, read);
                    } catch (Throwable th2) {
                        e = th2;
                    }
                }
                str4 = dl.e(instance.digest());
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e3) {
                        e = e3;
                        str2 = "MD5";
                        str3 = "gfm";
                        g.a(e, str2, str3);
                        return str4;
                    }
                }
                return str4;
            }
            if (str4 != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e4) {
                    e = e4;
                    str2 = "MD5";
                    str3 = "gfm";
                    g.a(e, str2, str3);
                    return str4;
                }
            }
            return str4;
        } catch (Throwable e5) {
            fileInputStream2 = str4;
            th = e5;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }

    public static String a(byte[] bArr) {
        return dl.e(a(bArr, "MD5"));
    }

    public static byte[] a(byte[] bArr, String str) {
        byte[] bArr2 = null;
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            bArr2 = instance.digest();
        } catch (Throwable th) {
            g.a(th, "MD5", "gmb");
        }
        return bArr2;
    }

    public static String b(String str) {
        return str == null ? null : dl.e(d(str));
    }

    public static String c(String str) {
        return dl.f(e(str));
    }

    private static byte[] d(String str) {
        try {
            return f(str);
        } catch (Throwable th) {
            g.a(th, "MD5", "gmb");
            return new byte[0];
        }
    }

    private static byte[] e(String str) {
        try {
            return f(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] f(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(dl.a(str));
        return instance.digest();
    }
}
