package com.amap.api.services.a;

import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import com.ajguan.library.BuildConfig;
import com.amap.api.services.a.e.a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;

/* compiled from: Utils */
public class f {
    static String a;

    public static Method a(Class cls, String str, Class<?>... clsArr) {
        try {
            return cls.getDeclaredMethod(c(str), clsArr);
        } catch (Throwable th) {
            th.printStackTrace();
            p.c(th, "ut", "srf");
            return null;
        }
    }

    public static e a() throws be {
        return new a("collection", BuildConfig.VERSION_NAME, "AMap_collection_1.0").a(new String[]{"com.amap.api.collection"}).a();
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new String(bArr);
        }
    }

    public static byte[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        String b = c.b(a(str));
        String str2 = "";
        try {
            return ((char) ((b.length() % 26) + 65)) + b;
        } catch (Throwable th) {
            th.printStackTrace();
            return str2;
        }
    }

    public static String c(String str) {
        if (str.length() < 2) {
            return "";
        }
        return c.a(str.substring(1));
    }

    public static byte[] b() {
        int i = 0;
        try {
            String[] split = new StringBuffer("16,16,18,77,15,911,121,77,121,911,38,77,911,99,86,67,611,96,48,77,84,911,38,67,021,301,86,67,611,98,48,77,511,77,48,97,511,58,48,97,511,84,501,87,511,96,48,77,221,911,38,77,121,37,86,67,25,301,86,67,021,96,86,67,021,701,86,67,35,56,86,67,611,37,221,87").reverse().toString().split(",");
            byte[] bArr = new byte[split.length];
            for (int i2 = 0; i2 < split.length; i2++) {
                bArr[i2] = Byte.parseByte(split[i2]);
            }
            split = new StringBuffer(new String(c.b(new String(bArr)))).reverse().toString().split(",");
            byte[] bArr2 = new byte[split.length];
            while (i < split.length) {
                bArr2[i] = Byte.parseByte(split[i]);
                i++;
            }
            return bArr2;
        } catch (Throwable th) {
            n.a(th, "ut", "gIV");
            return new byte[16];
        }
    }

    public static String a(Throwable th) {
        PrintWriter printWriter;
        Throwable cause;
        Throwable th2;
        String str = null;
        Writer stringWriter;
        try {
            stringWriter = new StringWriter();
            try {
                printWriter = new PrintWriter(stringWriter);
                try {
                    th.printStackTrace(printWriter);
                    for (cause = th.getCause(); cause != null; cause = cause.getCause()) {
                        cause.printStackTrace(printWriter);
                    }
                    str = stringWriter.toString();
                    if (stringWriter != null) {
                        try {
                            stringWriter.close();
                        } catch (Throwable cause2) {
                            cause2.printStackTrace();
                        }
                    }
                    if (printWriter != null) {
                        try {
                            printWriter.close();
                        } catch (Throwable th3) {
                            cause2 = th3;
                            cause2.printStackTrace();
                            return str;
                        }
                    }
                } catch (Throwable th4) {
                    cause2 = th4;
                    try {
                        cause2.printStackTrace();
                        if (stringWriter != null) {
                            try {
                                stringWriter.close();
                            } catch (Throwable cause22) {
                                cause22.printStackTrace();
                            }
                        }
                        if (printWriter != null) {
                            try {
                                printWriter.close();
                            } catch (Throwable th5) {
                                cause22 = th5;
                                cause22.printStackTrace();
                                return str;
                            }
                        }
                        return str;
                    } catch (Throwable th6) {
                        th2 = th6;
                        if (stringWriter != null) {
                            try {
                                stringWriter.close();
                            } catch (Throwable cause222) {
                                cause222.printStackTrace();
                            }
                        }
                        if (printWriter != null) {
                            try {
                                printWriter.close();
                            } catch (Throwable cause2222) {
                                cause2222.printStackTrace();
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable cause22222) {
                printWriter = null;
                th2 = cause22222;
                if (stringWriter != null) {
                    stringWriter.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
                throw th2;
            }
        } catch (Throwable cause222222) {
            printWriter = null;
            stringWriter = null;
            th2 = cause222222;
            if (stringWriter != null) {
                stringWriter.close();
            }
            if (printWriter != null) {
                printWriter.close();
            }
            throw th2;
        }
        return str;
    }

    static PublicKey c() throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException {
        Throwable th;
        Throwable th2;
        PublicKey publicKey = null;
        InputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(c.b("MIICnjCCAgegAwIBAgIJAJ0Pdzos7ZfYMA0GCSqGSIb3DQEBBQUAMGgxCzAJBgNVBAYTAkNOMRMwEQYDVQQIDApTb21lLVN0YXRlMRAwDgYDVQQHDAdCZWlqaW5nMREwDwYDVQQKDAhBdXRvbmF2aTEfMB0GA1UEAwwWY29tLmF1dG9uYXZpLmFwaXNlcnZlcjAeFw0xMzA4MTUwNzU2NTVaFw0yMzA4MTMwNzU2NTVaMGgxCzAJBgNVBAYTAkNOMRMwEQYDVQQIDApTb21lLVN0YXRlMRAwDgYDVQQHDAdCZWlqaW5nMREwDwYDVQQKDAhBdXRvbmF2aTEfMB0GA1UEAwwWY29tLmF1dG9uYXZpLmFwaXNlcnZlcjCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA8eWAyHbFPoFPfdx5AD+D4nYFq4dbJ1p7SIKt19Oz1oivF/6H43v5Fo7s50pD1UF8+Qu4JoUQxlAgOt8OCyQ8DYdkaeB74XKb1wxkIYg/foUwN1CMHPZ9O9ehgna6K4EJXZxR7Y7XVZnbjHZIVn3VpPU/Rdr2v37LjTw+qrABJxMCAwEAAaNQME4wHQYDVR0OBBYEFOM/MLGP8xpVFuVd+3qZkw7uBvOTMB8GA1UdIwQYMBaAFOM/MLGP8xpVFuVd+3qZkw7uBvOTMAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEFBQADgYEA4LY3g8aAD8JkxAOqUXDDyLuCCGOc2pTIhn0TwMNaVdH4hZlpTeC/wuRD5LJ0z3j+IQ0vLvuQA5uDjVyEOlBrvVIGwSem/1XGUo13DfzgAJ5k1161S5l+sFUo5TxpHOXr8Z5nqJMjieXmhnE/I99GFyHpQmw4cC6rhYUhdhtg+Zk="));
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                KeyFactory instance2 = KeyFactory.getInstance("RSA");
                Certificate generateCertificate = instance.generateCertificate(byteArrayInputStream);
                if (generateCertificate == null || instance2 == null) {
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (Throwable th3) {
                            th = th3;
                            th.printStackTrace();
                            return publicKey;
                        }
                    }
                    return publicKey;
                }
                publicKey = instance2.generatePublic(new X509EncodedKeySpec(generateCertificate.getPublicKey().getEncoded()));
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable th4) {
                        th = th4;
                        th.printStackTrace();
                        return publicKey;
                    }
                }
                return publicKey;
            } catch (Throwable th5) {
                th = th5;
                try {
                    th.printStackTrace();
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (Throwable th6) {
                            th = th6;
                            th.printStackTrace();
                            return publicKey;
                        }
                    }
                    return publicKey;
                } catch (Throwable th7) {
                    th2 = th7;
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (Throwable th8) {
                            th8.printStackTrace();
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th82) {
            byteArrayInputStream = publicKey;
            th2 = th82;
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
            throw th2;
        }
    }

    public static byte[] b(byte[] bArr) {
        try {
            return e(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    static String c(byte[] bArr) {
        try {
            return d(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String d(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bArr == null) {
            return null;
        }
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                toHexString = '0' + toHexString;
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    private static byte[] e(byte[] bArr) throws IOException, Throwable {
        Throwable th;
        Throwable th2;
        byte[] bArr2 = null;
        if (bArr != null) {
            ByteArrayOutputStream byteArrayOutputStream;
            GZIPOutputStream gZIPOutputStream;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    try {
                        gZIPOutputStream.write(bArr);
                        gZIPOutputStream.finish();
                        bArr2 = byteArrayOutputStream.toByteArray();
                        if (gZIPOutputStream != null) {
                            gZIPOutputStream.close();
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            th = th4;
                        }
                    }
                } catch (Throwable th5) {
                    th2 = th5;
                    gZIPOutputStream = null;
                    th = th2;
                    if (gZIPOutputStream != null) {
                        gZIPOutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th52) {
                byteArrayOutputStream = null;
                th = th52;
                gZIPOutputStream = null;
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        }
        return bArr2;
    }

    public static String a(long j) {
        String str = null;
        try {
            str = new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS", Locale.CHINA).format(new Date(j));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str;
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            stringBuilder.append(HttpUtils.EQUAL_SIGN);
        }
        a = stringBuilder.toString();
    }
}
