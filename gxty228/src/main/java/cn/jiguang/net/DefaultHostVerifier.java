package cn.jiguang.net;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class DefaultHostVerifier implements HostnameVerifier {
    private boolean a = false;

    public boolean verify(String str, SSLSession sSLSession) {
        return !this.a;
    }
}
