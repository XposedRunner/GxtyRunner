package cn.jiguang.net;

import cn.jiguang.e.d;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStore.TrustedCertificateEntry;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class SSLTrustManager implements X509TrustManager {
    private X509TrustManager a;

    public SSLTrustManager(String str) {
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            InputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
            X509Certificate x509Certificate = (X509Certificate) instance.generateCertificate(byteArrayInputStream);
            byteArrayInputStream.close();
            Entry trustedCertificateEntry = new TrustedCertificateEntry(x509Certificate);
            KeyStore instance2 = KeyStore.getInstance(KeyStore.getDefaultType());
            instance2.load(null, null);
            instance2.setEntry("ca_root", trustedCertificateEntry, null);
            TrustManagerFactory instance3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance3.init(instance2);
            for (TrustManager trustManager : instance3.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    this.a = (X509TrustManager) trustManager;
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        d.c("SSLTrustManager", "checkClientTrusted");
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        d.c("SSLTrustManager", "checkServerTrusted");
        if (x509CertificateArr == null || x509CertificateArr.length == 0) {
            throw new CertificateException("Check Server x509Certificates is empty");
        } else if (x509CertificateArr[0] != null) {
            x509CertificateArr[0].checkValidity();
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        d.c("SSLTrustManager", "getAcceptedIssuers");
        return this.a.getAcceptedIssuers();
    }
}
