package cn.jiguang.net;

import cn.jiguang.e.d;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public class DefaultTrustManager implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        d.c("DefaultTrustManager", "checkClientTrusted");
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        d.c("DefaultTrustManager", "checkServerTrusted x509Certificates:" + x509CertificateArr);
        if (x509CertificateArr != null) {
            d.a("DefaultTrustManager", "x509Certificates size:" + x509CertificateArr.length);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        d.c("DefaultTrustManager", "getAcceptedIssuers");
        return null;
    }
}
