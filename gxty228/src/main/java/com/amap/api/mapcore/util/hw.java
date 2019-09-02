package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.amap.api.mapcore.util.hq.a;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

/* compiled from: Utils */
public class hw {
    private static boolean a(he heVar, hm hmVar) {
        try {
            List a = a.a(heVar, hmVar.b, "used");
            if (a != null && a.size() > 0 && a(((hu) a.get(0)).e(), hmVar.d) > 0) {
                return true;
            }
        } catch (Throwable th) {
            a(th, "dDownLoad", "isUsed");
        }
        return false;
    }

    static boolean a(Context context, hm hmVar, gk gkVar) {
        he heVar = new he(context, hs.a());
        if (a(heVar, hmVar)) {
            return true;
        }
        hu a = a.a(heVar, hmVar.a);
        if (a != null) {
            return a(context, heVar, a, hmVar, gkVar);
        }
        return false;
    }

    private static boolean a(Context context, he heVar, hu huVar, hm hmVar, gk gkVar) {
        String str = hmVar.b;
        String str2 = hmVar.c;
        final String str3 = hmVar.d;
        if ("errorstatus".equals(huVar.f())) {
            a(context, heVar, gkVar);
            return true;
        }
        final String a = hq.a(context, hmVar.a);
        if (!new File(a).exists()) {
            return false;
        }
        List b = heVar.b(hu.a(hq.a(context, str, str2), str, str2, str3), hu.class);
        if (b != null && b.size() > 0) {
            return true;
        }
        hq.a(context, str, gkVar.b());
        try {
            final Context context2 = context;
            final he heVar2 = heVar;
            final gk gkVar2 = gkVar;
            hv.b().a().submit(new Runnable() {
                public void run() {
                    try {
                        hq.a(context2, heVar2, gkVar2, a, str3);
                        hq.a(context2, gkVar2);
                    } catch (Throwable th) {
                        hw.a(th, "dDownLoad", "processDownloadedFile()");
                    }
                }
            });
        } catch (Throwable th) {
        }
        return true;
    }

    private static void a(final Context context, he heVar, final gk gkVar) {
        try {
            if (!new File(hq.b(context, gkVar.a(), gkVar.b())).exists() && !TextUtils.isEmpty(hq.a(context, heVar, gkVar))) {
                hv.b().a().submit(new Runnable() {
                    public void run() {
                        try {
                            hq.a(context, gkVar);
                        } catch (Throwable th) {
                        }
                    }
                });
            }
        } catch (Throwable th) {
        }
    }

    static boolean a(Context context) {
        return gd.q(context) == 1;
    }

    static boolean a(Context context, boolean z) {
        return z || a(context);
    }

    static boolean a(hm hmVar) {
        return VERSION.SDK_INT >= hmVar.f && VERSION.SDK_INT <= hmVar.e;
    }

    static boolean a(gk gkVar, hm hmVar) {
        if (gkVar != null && gkVar.a().equals(hmVar.b) && gkVar.b().equals(hmVar.c)) {
            return true;
        }
        return false;
    }

    static boolean a(Context context, gk gkVar, hm hmVar) {
        if (!hmVar.e() && gl.b(context)) {
            return false;
        }
        return true;
    }

    static PublicKey a() {
        Closeable byteArrayInputStream;
        Throwable th;
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            byteArrayInputStream = new ByteArrayInputStream(ge.b("MIIDRzCCAi+gAwIBAgIEeuDbsDANBgkqhkiG9w0BAQsFADBTMQswCQYDVQQGEwJjbjELMAkGA1UECBMCYmoxCzAJBgNVBAcTAmJqMQ0wCwYDVQQKEwRvcGVuMQ4wDAYDVQQLEwVnYW9kZTELMAkGA1UEAxMCUWkwIBcNMTYwODAxMDE0ODMwWhgPMjA3MTA1MDUwMTQ4MzBaMFMxCzAJBgNVBAYTAmNuMQswCQYDVQQIEwJiajELMAkGA1UEBxMCYmoxDTALBgNVBAoTBG9wZW4xDjAMBgNVBAsTBWdhb2RlMQswCQYDVQQDEwJRaTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAKpL13mZm4q6AFP5csQE7130Lwq8m+HICy3rBARd9vbw5Cb1wFF96KdhC5P/aASlrPb+6MSyP1nE97p3ygKJWsgxExyvVuOvh1KUqOFuK15oY7JKTk6L4eLCbkBJZV2DLffpW0HGiRpmFG8LJR0sjNOoubSd5R/6XoBwyRglsyVHprjrK2qDRvT3Edgtfvxp4HnUzMsDD3CJRtgsaDw6ECyF7fhYKEz9I6OEEVsPlpbgzRmhSeFDL77/k1mhPve1ZyKGlPcxvSSdLSAlV0hzr5NKlujHll7BbouwDnr6l/0O44AzZ0V/ieft1iBkSLirnlm56uI/8jdh8ANrD1fW4ZUCAwEAAaMhMB8wHQYDVR0OBBYEFBzudtI5UKRvHGDV+VQRzItIj3PqMA0GCSqGSIb3DQEBCwUAA4IBAQBS2EGndgvIBnf7Ce4IhDbm7F5h4L+6TYGmT9acnQbEFY8oUoFblMDgg+cETT44jU/elwbJJVmKhj/WRQl+AdSALBAgDvxq1AcjlGg+c8H3pa2BWlrxNJP9MFLIEI5bA8m5og/Epjut50uemZ9ggoNmJeW0N/a6D8euhYJKOYngUQqDu6cwLj1Ec0ptwrNRbvRXXgzjfJMPE/ii4K/b8JZ+QN2d/bl7QEvKWBSzVueZifV659qAbMh6C9TCVstWWfV53Z3Vyt+duDNU5ed7aWao42Ppw4VHslrJW0V6BXDUhhzgXx28UWY78W7LmYGCtC8PfDId2+k4tPoTNPM6HHP5"));
            try {
                PublicKey publicKey = ((X509Certificate) instance.generateCertificate(byteArrayInputStream)).getPublicKey();
                try {
                    a(byteArrayInputStream);
                    return publicKey;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                    return publicKey;
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    a(th, "DyLoader", "init");
                    try {
                        a(byteArrayInputStream);
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                    return null;
                } catch (Throwable th5) {
                    th4 = th5;
                    try {
                        a(byteArrayInputStream);
                    } catch (Throwable th22) {
                        th22.printStackTrace();
                    }
                    throw th4;
                }
            }
        } catch (Throwable th6) {
            th4 = th6;
            byteArrayInputStream = null;
            a(byteArrayInputStream);
            throw th4;
        }
    }

    public static int a(String str, String str2) {
        int i = 0;
        try {
            String[] split = str.split("\\.");
            String[] split2 = str2.split("\\.");
            int min = Math.min(split.length, split2.length);
            for (int i2 = 0; i2 < min; i2++) {
                i = split[i2].length() - split2[i2].length();
                if (i != 0) {
                    break;
                }
                i = split[i2].compareTo(split2[i2]);
                if (i != 0) {
                    break;
                }
            }
            if (i != 0) {
                return i;
            }
            return split.length - split2.length;
        } catch (Throwable e) {
            gw.a(e, "Utils", "compareVersion");
            return -1;
        }
    }

    static void a(List<hu> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int i2 = i + 1; i2 < list.size(); i2++) {
                hu huVar = (hu) list.get(i);
                hu huVar2 = (hu) list.get(i2);
                if (a(huVar2.e(), huVar.e()) > 0) {
                    list.set(i, huVar2);
                    list.set(i2, huVar);
                }
            }
        }
    }

    static boolean b(String str, String str2) {
        String a = gf.a(str);
        if (a == null || !a.equalsIgnoreCase(str2)) {
            return false;
        }
        return true;
    }

    static boolean a(Context context, he heVar, String str, gk gkVar) {
        return a(heVar, str, hq.a(context, str), gkVar);
    }

    static boolean a(he heVar, String str, String str2, gk gkVar) {
        hu a = a.a(heVar, str);
        if (a != null && gkVar.b().equals(a.d()) && b(str2, a.b())) {
            return true;
        }
        return false;
    }

    static void a(Throwable th, String str, String str2) {
        gw.a(th, str, str2);
    }

    static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
