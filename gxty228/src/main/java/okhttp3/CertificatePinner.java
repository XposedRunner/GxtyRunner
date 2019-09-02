package okhttp3;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;

public final class CertificatePinner {
    public static final CertificatePinner DEFAULT = new Builder().build();
    @Nullable
    private final CertificateChainCleaner certificateChainCleaner;
    private final Set<Pin> pins;

    CertificatePinner(Set<Pin> set, @Nullable CertificateChainCleaner certificateChainCleaner) {
        this.pins = set;
        this.certificateChainCleaner = certificateChainCleaner;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        boolean z = (obj instanceof CertificatePinner) && Util.equal(this.certificateChainCleaner, ((CertificatePinner) obj).certificateChainCleaner) && this.pins.equals(((CertificatePinner) obj).pins);
        return z;
    }

    public int hashCode() {
        return ((this.certificateChainCleaner != null ? this.certificateChainCleaner.hashCode() : 0) * 31) + this.pins.hashCode();
    }

    public void check(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        List findMatchingPins = findMatchingPins(str);
        if (!findMatchingPins.isEmpty()) {
            X509Certificate x509Certificate;
            int i;
            if (this.certificateChainCleaner != null) {
                list = this.certificateChainCleaner.clean(list, str);
            }
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                x509Certificate = (X509Certificate) list.get(i2);
                int size2 = findMatchingPins.size();
                int i3 = 0;
                Object obj = null;
                Object obj2 = null;
                while (i3 < size2) {
                    Pin pin = (Pin) findMatchingPins.get(i3);
                    if (pin.hashAlgorithm.equals("sha256/")) {
                        if (obj == null) {
                            obj = sha256(x509Certificate);
                        }
                        if (pin.hash.equals(obj)) {
                            return;
                        }
                    } else if (pin.hashAlgorithm.equals("sha1/")) {
                        if (obj2 == null) {
                            obj2 = sha1(x509Certificate);
                        }
                        if (pin.hash.equals(obj2)) {
                            return;
                        }
                    } else {
                        throw new AssertionError();
                    }
                    Object obj3 = obj;
                    i3++;
                    obj2 = obj2;
                    obj = obj3;
                }
            }
            StringBuilder append = new StringBuilder().append("Certificate pinning failure!").append("\n  Peer certificate chain:");
            int size3 = list.size();
            for (i = 0; i < size3; i++) {
                x509Certificate = (X509Certificate) list.get(i);
                append.append("\n    ").append(pin(x509Certificate)).append(": ").append(x509Certificate.getSubjectDN().getName());
            }
            append.append("\n  Pinned certificates for ").append(str).append(":");
            size3 = findMatchingPins.size();
            for (i = 0; i < size3; i++) {
                append.append("\n    ").append((Pin) findMatchingPins.get(i));
            }
            throw new SSLPeerUnverifiedException(append.toString());
        }
    }

    public void check(String str, Certificate... certificateArr) throws SSLPeerUnverifiedException {
        check(str, Arrays.asList(certificateArr));
    }

    List<Pin> findMatchingPins(String str) {
        List<Pin> emptyList = Collections.emptyList();
        for (Pin pin : this.pins) {
            if (pin.matches(str)) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList();
                }
                emptyList.add(pin);
            }
        }
        return emptyList;
    }

    CertificatePinner withCertificateChainCleaner(CertificateChainCleaner certificateChainCleaner) {
        if (Util.equal(this.certificateChainCleaner, certificateChainCleaner)) {
            return this;
        }
        return new CertificatePinner(this.pins, certificateChainCleaner);
    }

    public static String pin(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return "sha256/" + sha256((X509Certificate) certificate).base64();
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    static ByteString sha1(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha1();
    }

    static ByteString sha256(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha256();
    }
}
