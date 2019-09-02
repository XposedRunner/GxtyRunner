package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* compiled from: RSAUtil */
public final class ie {
    private static PublicKey a = null;

    public static boolean a(String str) {
        boolean z = false;
        if (!TextUtils.isEmpty(str)) {
            try {
                z = a(new File(str));
            } catch (Throwable th) {
            }
        }
        return z;
    }

    private static boolean a(File file) {
        JarFile jarFile;
        Throwable th;
        JarFile jarFile2 = null;
        try {
            if (a == null) {
                a = a();
            }
            jarFile = new JarFile(file);
            try {
                Enumeration entries = jarFile.entries();
                if (entries == null) {
                    try {
                        jarFile.close();
                    } catch (Throwable th2) {
                    }
                    return false;
                }
                while (entries.hasMoreElements()) {
                    JarEntry jarEntry = (JarEntry) entries.nextElement();
                    if (jarEntry.getName() != null && jarEntry.getName().endsWith("so")) {
                        a(jarFile, jarEntry);
                        Certificate[] certificates = jarEntry.getCertificates();
                        if (certificates == null) {
                            try {
                                jarFile.close();
                            } catch (Throwable th3) {
                            }
                            return false;
                        } else if (!a(certificates)) {
                            try {
                                jarFile.close();
                            } catch (Throwable th4) {
                            }
                            return false;
                        }
                    }
                }
                try {
                    jarFile.close();
                } catch (Throwable th5) {
                }
                return true;
            } catch (Throwable th6) {
                th = th6;
            }
        } catch (Throwable th7) {
            th = th7;
            jarFile = null;
            if (jarFile != null) {
                jarFile.close();
            }
            throw th;
        }
    }

    private static PublicKey a() {
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
                    gw.a(th, "SoLoader", "init");
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

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
            }
        }
    }

    private static void a(JarFile jarFile, JarEntry jarEntry) throws IOException {
        Closeable closeable = null;
        try {
            closeable = jarFile.getInputStream(jarEntry);
            while (true) {
                if (closeable.read(new byte[8192]) <= 0) {
                    break;
                }
            }
        } catch (Throwable th) {
            gw.a(th, "DyLoader", "loadJa");
        } finally {
            a(closeable);
        }
    }

    private static boolean a(Certificate[] certificateArr) {
        try {
            if (certificateArr.length > 0) {
                int length = certificateArr.length - 1;
                if (length >= 0) {
                    certificateArr[length].verify(a);
                    return true;
                }
            }
        } catch (Throwable th) {
            gw.a(th, "DyLoader", "check");
        }
        return false;
    }
}
