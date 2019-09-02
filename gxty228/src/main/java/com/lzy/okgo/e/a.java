package com.lzy.okgo.e;

import com.lzy.okgo.f.d;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* compiled from: HttpsUtils */
public class a {
    public static X509TrustManager a = new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };
    public static HostnameVerifier b = new HostnameVerifier() {
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    };

    /* compiled from: HttpsUtils */
    public static class a {
        public SSLSocketFactory a;
        public X509TrustManager b;
    }

    public static a a() {
        return a(null, null, null, new InputStream[0]);
    }

    private static a a(X509TrustManager x509TrustManager, InputStream inputStream, String str, InputStream... inputStreamArr) {
        a aVar = new a();
        try {
            KeyManager[] a = a(inputStream, str);
            TrustManager[] a2 = a(inputStreamArr);
            if (x509TrustManager == null) {
                if (a2 != null) {
                    x509TrustManager = a(a2);
                } else {
                    x509TrustManager = a;
                }
            }
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(a, new TrustManager[]{x509TrustManager}, null);
            aVar.a = instance.getSocketFactory();
            aVar.b = x509TrustManager;
            return aVar;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (KeyManagementException e2) {
            throw new AssertionError(e2);
        }
    }

    private static KeyManager[] a(InputStream inputStream, String str) {
        KeyManager[] keyManagerArr = null;
        if (!(inputStream == null || str == null)) {
            try {
                KeyStore instance = KeyStore.getInstance("BKS");
                instance.load(inputStream, str.toCharArray());
                KeyManagerFactory instance2 = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                instance2.init(instance, str.toCharArray());
                keyManagerArr = instance2.getKeyManagers();
            } catch (Throwable e) {
                d.a(e);
            }
        }
        return keyManagerArr;
    }

    private static TrustManager[] a(InputStream... inputStreamArr) {
        int i = 0;
        TrustManager[] trustManagerArr = null;
        if (inputStreamArr != null && inputStreamArr.length > 0) {
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                KeyStore instance2 = KeyStore.getInstance(KeyStore.getDefaultType());
                instance2.load(null);
                int length = inputStreamArr.length;
                int i2 = 0;
                while (i < length) {
                    InputStream inputStream = inputStreamArr[i];
                    int i3 = i2 + 1;
                    instance2.setCertificateEntry(Integer.toString(i2), instance.generateCertificate(inputStream));
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable e) {
                            d.a(e);
                        }
                    }
                    i++;
                    i2 = i3;
                }
                TrustManagerFactory instance3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance3.init(instance2);
                trustManagerArr = instance3.getTrustManagers();
            } catch (Throwable e2) {
                d.a(e2);
            }
        }
        return trustManagerArr;
    }

    private static X509TrustManager a(TrustManager[] trustManagerArr) {
        for (TrustManager trustManager : trustManagerArr) {
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        return null;
    }
}
