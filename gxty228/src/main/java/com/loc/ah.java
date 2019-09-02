package com.loc;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.loc.aa.a;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

/* compiled from: Utils */
public final class ah {
    static PublicKey a() {
        Throwable th;
        Closeable byteArrayInputStream;
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            byteArrayInputStream = new ByteArrayInputStream(dg.b("MIIDRzCCAi+gAwIBAgIEeuDbsDANBgkqhkiG9w0BAQsFADBTMQswCQYDVQQGEwJjbjELMAkGA1UECBMCYmoxCzAJBgNVBAcTAmJqMQ0wCwYDVQQKEwRvcGVuMQ4wDAYDVQQLEwVnYW9kZTELMAkGA1UEAxMCUWkwIBcNMTYwODAxMDE0ODMwWhgPMjA3MTA1MDUwMTQ4MzBaMFMxCzAJBgNVBAYTAmNuMQswCQYDVQQIEwJiajELMAkGA1UEBxMCYmoxDTALBgNVBAoTBG9wZW4xDjAMBgNVBAsTBWdhb2RlMQswCQYDVQQDEwJRaTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAKpL13mZm4q6AFP5csQE7130Lwq8m+HICy3rBARd9vbw5Cb1wFF96KdhC5P/aASlrPb+6MSyP1nE97p3ygKJWsgxExyvVuOvh1KUqOFuK15oY7JKTk6L4eLCbkBJZV2DLffpW0HGiRpmFG8LJR0sjNOoubSd5R/6XoBwyRglsyVHprjrK2qDRvT3Edgtfvxp4HnUzMsDD3CJRtgsaDw6ECyF7fhYKEz9I6OEEVsPlpbgzRmhSeFDL77/k1mhPve1ZyKGlPcxvSSdLSAlV0hzr5NKlujHll7BbouwDnr6l/0O44AzZ0V/ieft1iBkSLirnlm56uI/8jdh8ANrD1fW4ZUCAwEAAaMhMB8wHQYDVR0OBBYEFBzudtI5UKRvHGDV+VQRzItIj3PqMA0GCSqGSIb3DQEBCwUAA4IBAQBS2EGndgvIBnf7Ce4IhDbm7F5h4L+6TYGmT9acnQbEFY8oUoFblMDgg+cETT44jU/elwbJJVmKhj/WRQl+AdSALBAgDvxq1AcjlGg+c8H3pa2BWlrxNJP9MFLIEI5bA8m5og/Epjut50uemZ9ggoNmJeW0N/a6D8euhYJKOYngUQqDu6cwLj1Ec0ptwrNRbvRXXgzjfJMPE/ii4K/b8JZ+QN2d/bl7QEvKWBSzVueZifV659qAbMh6C9TCVstWWfV53Z3Vyt+duDNU5ed7aWao42Ppw4VHslrJW0V6BXDUhhzgXx28UWY78W7LmYGCtC8PfDId2+k4tPoTNPM6HHP5"));
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
                    g.a(th, "DyLoader", "init");
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

    static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    static void a(List<af> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int i2 = i + 1; i2 < list.size(); i2++) {
                af afVar = (af) list.get(i);
                af afVar2 = (af) list.get(i2);
                if (b(afVar2.e(), afVar.e()) > 0) {
                    list.set(i, afVar2);
                    list.set(i2, afVar);
                }
            }
        }
    }

    private static boolean a(final Context context, p pVar, af afVar, w wVar, final dk dkVar) {
        String str = wVar.b;
        String str2 = wVar.c;
        final String str3 = wVar.d;
        if ("errorstatus".equals(afVar.f())) {
            try {
                if (!(new File(aa.b(context, dkVar.a(), dkVar.b())).exists() || TextUtils.isEmpty(aa.a(context, pVar, dkVar)))) {
                    ag.b().a().submit(new Runnable() {
                        public final void run() {
                            try {
                                aa.a(context, dkVar);
                            } catch (Throwable th) {
                            }
                        }
                    });
                }
            } catch (Throwable th) {
            }
            return true;
        }
        final String a = aa.a(context, wVar.a);
        if (!new File(a).exists()) {
            return false;
        }
        List b = pVar.b(af.a(aa.a(context, str, str2), str, str2, str3), af.class);
        if (b != null && b.size() > 0) {
            return true;
        }
        aa.a(context, str, dkVar.b());
        try {
            final Context context2 = context;
            final p pVar2 = pVar;
            final dk dkVar2 = dkVar;
            ag.b().a().submit(new Runnable() {
                public final void run() {
                    try {
                        aa.a(context2, pVar2, dkVar2, a, str3);
                        aa.a(context2, dkVar2);
                    } catch (Throwable th) {
                        g.a(th, "dDownLoad", "processDownloadedFile()");
                    }
                }
            });
        } catch (Throwable th2) {
        }
        return true;
    }

    static boolean a(Context context, w wVar, dk dkVar) {
        p pVar = new p(context, ad.b());
        if (a(pVar, wVar)) {
            return true;
        }
        af a = a.a(pVar, wVar.a);
        return a != null ? a(context, pVar, a, wVar, dkVar) : false;
    }

    static boolean a(Context context, boolean z) {
        if (!z) {
            if (!(df.q(context) == 1)) {
                return false;
            }
        }
        return true;
    }

    static boolean a(dk dkVar, w wVar) {
        return dkVar != null && dkVar.a().equals(wVar.b) && dkVar.b().equals(wVar.c);
    }

    private static boolean a(p pVar, w wVar) {
        try {
            List a = a.a(pVar, wVar.b, "used");
            if (a != null && a.size() > 0 && b(((af) a.get(0)).e(), wVar.d) > 0) {
                return true;
            }
        } catch (Throwable th) {
            g.a(th, "dDownLoad", "isUsed");
        }
        return false;
    }

    static boolean a(p pVar, String str, String str2, dk dkVar) {
        af a = a.a(pVar, str);
        return a != null && dkVar.b().equals(a.d()) && a(str2, a.b());
    }

    static boolean a(w wVar) {
        return VERSION.SDK_INT >= wVar.f && VERSION.SDK_INT <= wVar.e;
    }

    static boolean a(String str, String str2) {
        String a = dh.a(str);
        return a != null && a.equalsIgnoreCase(str2);
    }

    private static int b(String str, String str2) {
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
            return i != 0 ? i : split.length - split2.length;
        } catch (Throwable e) {
            g.a(e, "Utils", "compareVersion");
            return -1;
        }
    }
}
