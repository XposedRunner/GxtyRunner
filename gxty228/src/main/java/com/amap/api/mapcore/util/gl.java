package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import cn.jiguang.net.HttpUtils;
import com.ajguan.library.BuildConfig;
import com.amap.api.mapcore.util.gk.a;
import com.lzy.okgo.cache.CacheEntity;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Field;
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
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.json.JSONObject;

/* compiled from: Utils */
public class gl {
    static String a;

    public static Method a(Class cls, String str, Class<?>... clsArr) {
        try {
            return cls.getDeclaredMethod(c(str), clsArr);
        } catch (Throwable th) {
            return null;
        }
    }

    public static String a(Context context) {
        String str;
        String str2 = "";
        if (VERSION.SDK_INT >= 21) {
            try {
                ApplicationInfo applicationInfo = context.getApplicationInfo();
                Field declaredField = Class.forName(ApplicationInfo.class.getName()).getDeclaredField("primaryCpuAbi");
                declaredField.setAccessible(true);
                str = (String) declaredField.get(applicationInfo);
            } catch (Throwable th) {
                gw.a(th, "ut", "gct");
            }
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            return Build.CPU_ABI;
        }
        str = str2;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        return Build.CPU_ABI;
    }

    public static boolean a(Context context, String str) {
        if (context == null) {
            return false;
        }
        if (context.checkCallingOrSelfPermission(str) != 0) {
            return false;
        }
        if (VERSION.SDK_INT >= 23 && context.getApplicationInfo().targetSdkVersion >= 23) {
            try {
                if (((Integer) context.getClass().getMethod("checkSelfPermission", new Class[]{String.class}).invoke(context, new Object[]{str})).intValue() != 0) {
                    return false;
                }
            } catch (Throwable th) {
            }
        }
        return true;
    }

    public static gk a() throws gp {
        return new a("collection", BuildConfig.VERSION_NAME, "AMap_collection_1.0").a(new String[]{"com.amap.api.collection"}).a();
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        int i = 255;
        if (TextUtils.isEmpty(str)) {
            try {
                byteArrayOutputStream.write(new byte[]{(byte) 0});
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        int length = str.length();
        if (length <= 255) {
            i = length;
        }
        a(byteArrayOutputStream, (byte) i, a(str));
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

    public static void a(ByteArrayOutputStream byteArrayOutputStream, byte b, byte[] bArr) {
        try {
            byteArrayOutputStream.write(new byte[]{b});
            int i = b & 255;
            if (i < 255 && i > 0) {
                byteArrayOutputStream.write(bArr);
            } else if (i == 255) {
                byteArrayOutputStream.write(bArr, 0, 255);
            }
        } catch (Throwable e) {
            gw.a(e, "ut", "wFie");
        }
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        String c = ge.c(a(str));
        String str2 = "";
        try {
            return ((char) ((c.length() % 26) + 65)) + c;
        } catch (Throwable th) {
            th.printStackTrace();
            return str2;
        }
    }

    public static String c(String str) {
        if (str.length() < 2) {
            return "";
        }
        return ge.a(str.substring(1));
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }

    public static byte[] b() {
        int i = 0;
        try {
            String[] split = new StringBuffer("16,16,18,77,15,911,121,77,121,911,38,77,911,99,86,67,611,96,48,77,84,911,38,67,021,301,86,67,611,98,48,77,511,77,48,97,511,58,48,97,511,84,501,87,511,96,48,77,221,911,38,77,121,37,86,67,25,301,86,67,021,96,86,67,021,701,86,67,35,56,86,67,611,37,221,87").reverse().toString().split(",");
            byte[] bArr = new byte[split.length];
            for (int i2 = 0; i2 < split.length; i2++) {
                bArr[i2] = Byte.parseByte(split[i2]);
            }
            split = new StringBuffer(new String(ge.b(new String(bArr)))).reverse().toString().split(",");
            byte[] bArr2 = new byte[split.length];
            while (i < split.length) {
                bArr2[i] = Byte.parseByte(split[i]);
                i++;
            }
            return bArr2;
        } catch (Throwable th) {
            gw.a(th, "ut", "gIV");
            return new byte[16];
        }
    }

    static String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(HttpUtils.PARAMETERS_SEPARATOR);
            }
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append(HttpUtils.EQUAL_SIGN);
            stringBuilder.append((String) entry.getValue());
        }
        return stringBuilder.toString();
    }

    public static String a(Throwable th) {
        Writer stringWriter;
        PrintWriter printWriter;
        Throwable cause;
        Throwable th2;
        String str = null;
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

    public static String b(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        Object obj = 1;
        try {
            for (Entry entry : map.entrySet()) {
                Object obj2;
                if (obj != null) {
                    stringBuffer.append((String) entry.getKey()).append(HttpUtils.EQUAL_SIGN).append((String) entry.getValue());
                    obj2 = null;
                } else {
                    stringBuffer.append(HttpUtils.PARAMETERS_SEPARATOR).append((String) entry.getKey()).append(HttpUtils.EQUAL_SIGN).append((String) entry.getValue());
                    obj2 = obj;
                }
                obj = obj2;
            }
        } catch (Throwable th) {
            gw.a(th, "ut", "abP");
        }
        return stringBuffer.toString();
    }

    public static String c(Map<String, String> map) {
        return d(a((Map) map));
    }

    public static String d(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            String[] split = str.split(HttpUtils.PARAMETERS_SEPARATOR);
            Arrays.sort(split);
            StringBuffer stringBuffer = new StringBuffer();
            for (String append : split) {
                stringBuffer.append(append);
                stringBuffer.append(HttpUtils.PARAMETERS_SEPARATOR);
            }
            String stringBuffer2 = stringBuffer.toString();
            if (stringBuffer2.length() > 1) {
                return (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1);
            }
            return str;
        } catch (Throwable th) {
            gw.a(th, "ut", "sPa");
        }
    }

    public static byte[] b(byte[] bArr) {
        try {
            return h(bArr);
        } catch (Throwable th) {
            gw.a(th, "ut", "gZp");
            return new byte[0];
        }
    }

    public static byte[] c(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        ZipOutputStream zipOutputStream;
        Throwable th;
        String str;
        String str2;
        Throwable th2;
        byte[] bArr2 = null;
        if (!(bArr == null || bArr.length == 0)) {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
                } catch (Throwable th3) {
                    zipOutputStream = bArr2;
                    th2 = th3;
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th2;
                }
                try {
                    zipOutputStream.putNextEntry(new ZipEntry("log"));
                    zipOutputStream.write(bArr);
                    zipOutputStream.closeEntry();
                    zipOutputStream.finish();
                    bArr2 = byteArrayOutputStream.toByteArray();
                    if (zipOutputStream != null) {
                        try {
                            zipOutputStream.close();
                        } catch (Throwable th32) {
                            gw.a(th32, "ut", "zp1");
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th4) {
                            th32 = th4;
                            str = "ut";
                            str2 = "zp2";
                            gw.a(th32, str, str2);
                            return bArr2;
                        }
                    }
                } catch (Throwable th5) {
                    th32 = th5;
                    gw.a(th32, "ut", "zp");
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    return bArr2;
                }
            } catch (Throwable th322) {
                byteArrayOutputStream = bArr2;
                zipOutputStream = bArr2;
                th2 = th322;
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th2;
            }
        }
        return bArr2;
    }

    static PublicKey c() throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException {
        Throwable th;
        Throwable th2;
        PublicKey publicKey = null;
        InputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(ge.b("MIICnjCCAgegAwIBAgIJAJ0Pdzos7ZfYMA0GCSqGSIb3DQEBBQUAMGgxCzAJBgNVBAYTAkNOMRMwEQYDVQQIDApTb21lLVN0YXRlMRAwDgYDVQQHDAdCZWlqaW5nMREwDwYDVQQKDAhBdXRvbmF2aTEfMB0GA1UEAwwWY29tLmF1dG9uYXZpLmFwaXNlcnZlcjAeFw0xMzA4MTUwNzU2NTVaFw0yMzA4MTMwNzU2NTVaMGgxCzAJBgNVBAYTAkNOMRMwEQYDVQQIDApTb21lLVN0YXRlMRAwDgYDVQQHDAdCZWlqaW5nMREwDwYDVQQKDAhBdXRvbmF2aTEfMB0GA1UEAwwWY29tLmF1dG9uYXZpLmFwaXNlcnZlcjCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA8eWAyHbFPoFPfdx5AD+D4nYFq4dbJ1p7SIKt19Oz1oivF/6H43v5Fo7s50pD1UF8+Qu4JoUQxlAgOt8OCyQ8DYdkaeB74XKb1wxkIYg/foUwN1CMHPZ9O9ehgna6K4EJXZxR7Y7XVZnbjHZIVn3VpPU/Rdr2v37LjTw+qrABJxMCAwEAAaNQME4wHQYDVR0OBBYEFOM/MLGP8xpVFuVd+3qZkw7uBvOTMB8GA1UdIwQYMBaAFOM/MLGP8xpVFuVd+3qZkw7uBvOTMAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEFBQADgYEA4LY3g8aAD8JkxAOqUXDDyLuCCGOc2pTIhn0TwMNaVdH4hZlpTeC/wuRD5LJ0z3j+IQ0vLvuQA5uDjVyEOlBrvVIGwSem/1XGUo13DfzgAJ5k1161S5l+sFUo5TxpHOXr8Z5nqJMjieXmhnE/I99GFyHpQmw4cC6rhYUhdhtg+Zk="));
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

    public static byte[] d(byte[] bArr) {
        try {
            return h(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    static String e(byte[] bArr) {
        try {
            return g(bArr);
        } catch (Throwable th) {
            gw.a(th, "ut", "h2s");
            return null;
        }
    }

    static String f(byte[] bArr) {
        try {
            return g(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String g(byte[] bArr) {
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

    public static byte[] e(String str) {
        if (str.length() % 2 != 0) {
            str = "0" + str;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    private static byte[] h(byte[] bArr) throws IOException, Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPOutputStream gZIPOutputStream;
        Throwable th;
        Throwable th2;
        byte[] bArr2 = null;
        if (bArr != null) {
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

    public static String a(long j, String str) {
        String str2 = null;
        try {
            str2 = new SimpleDateFormat(str, Locale.CHINA).format(new Date(j));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str2;
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            stringBuilder.append(HttpUtils.EQUAL_SIGN);
        }
        a = stringBuilder.toString();
    }

    public static void a(Context context, String str, String str2, JSONObject jSONObject) {
        Object obj = "";
        String e = fx.e(context);
        String b = gf.b(e);
        Object obj2 = "";
        String str3 = "";
        String str4 = "";
        String a = fx.a(context);
        String str5 = "";
        try {
            if (jSONObject.has("info")) {
                obj = jSONObject.getString("info");
                str4 = "请在高德开放平台官网中搜索\"" + obj + "\"相关内容进行解决";
            }
            if ("INVALID_USER_SCODE".equals(obj)) {
                if (jSONObject.has("sec_code")) {
                    obj2 = jSONObject.getString("sec_code");
                }
                if (jSONObject.has("sec_code_debug")) {
                    Object string = jSONObject.getString("sec_code_debug");
                } else {
                    str5 = str3;
                }
                if (b.equals(obj2) || b.equals(r1)) {
                    str4 = "请在高德开放平台官网中搜索\"请求内容过长导致业务调用失败\"相关内容进行解决";
                }
            } else if ("INVALID_USER_KEY".equals(obj)) {
                if (jSONObject.has(CacheEntity.KEY)) {
                    str5 = jSONObject.getString(CacheEntity.KEY);
                }
                if (str5.length() > 0 && !a.equals(str5)) {
                    str4 = "请在高德开放平台官网上发起技术咨询工单—>账号与Key问题，咨询INVALID_USER_KEY如何解决";
                }
            }
        } catch (Throwable th) {
        }
        g(a);
        g("                                   鉴权错误信息                                  ");
        g(a);
        f("SHA1Package:" + e);
        f("key:" + a);
        f("csid:" + str);
        f("gsid:" + str2);
        f("json:" + jSONObject.toString());
        g("                                                                               ");
        g(str4);
        g(a);
    }

    static void f(String str) {
        int i = 0;
        if (str.length() < 78) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("|").append(str);
            while (i < 78 - str.length()) {
                stringBuilder.append(" ");
                i++;
            }
            stringBuilder.append("|");
            g(stringBuilder.toString());
            return;
        }
        g("|" + str.substring(0, 78) + "|");
        f(str.substring(78));
    }

    public static boolean b(Context context) {
        return hc.a(context);
    }

    private static void g(String str) {
        Log.i("authErrLog", str);
    }
}
