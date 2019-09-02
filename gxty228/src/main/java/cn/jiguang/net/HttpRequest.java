package cn.jiguang.net;

import android.text.TextUtils;
import com.lzy.okgo.model.HttpHeaders;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;

public class HttpRequest {
    private String a;
    private int b;
    private int c;
    private Map<String, String> d;
    private Map<String, String> e;
    private Object f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j = true;
    private boolean k = false;
    private SSLTrustManager l;
    private boolean m;
    private HostnameVerifier n;

    public HttpRequest(String str) {
        this.a = str;
        this.b = -1;
        this.c = -1;
        this.e = new HashMap();
    }

    public HttpRequest(String str, Map<String, String> map) {
        this.a = str;
        this.d = map;
        this.b = -1;
        this.c = -1;
        this.e = new HashMap();
    }

    public Object getBody() {
        return this.f;
    }

    public int getConnectTimeout() {
        return this.b;
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.n;
    }

    public byte[] getParas() {
        if (this.f != null) {
            if (this.f instanceof String) {
                if (!TextUtils.isEmpty((CharSequence) this.f)) {
                    return ((String) this.f).getBytes();
                }
            } else if (this.f instanceof byte[]) {
                return (byte[]) this.f;
            }
        }
        Object joinParasWithEncodedValue = HttpUtils.joinParasWithEncodedValue(this.d);
        return !TextUtils.isEmpty(joinParasWithEncodedValue) ? joinParasWithEncodedValue.getBytes() : null;
    }

    public Map<String, String> getParasMap() {
        return this.d;
    }

    public int getReadTimeout() {
        return this.c;
    }

    public Map<String, String> getRequestProperties() {
        return this.e;
    }

    public String getRequestProperty(String str) {
        return (String) this.e.get(str);
    }

    public SSLTrustManager getSslTrustManager() {
        return this.l;
    }

    public String getUrl() {
        return this.a;
    }

    public boolean isDoInPut() {
        return this.h;
    }

    public boolean isDoOutPut() {
        return this.g;
    }

    public boolean isHaveRspData() {
        return this.j;
    }

    public boolean isNeedErrorInput() {
        return this.k;
    }

    public boolean isNeedRetryIfHttpsFailed() {
        return this.m;
    }

    public boolean isUseCaches() {
        return this.i;
    }

    public void setBody(Object obj) {
        this.f = obj;
    }

    public void setConnectTimeout(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("timeout can not be negative");
        }
        this.b = i;
    }

    public void setDoInPut(boolean z) {
        this.h = z;
    }

    public void setDoOutPut(boolean z) {
        this.g = z;
    }

    public void setHaveRspData(boolean z) {
        this.j = z;
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.n = hostnameVerifier;
    }

    public void setNeedErrorInput(boolean z) {
        this.k = z;
    }

    public void setNeedRetryIfHttpsFailed(boolean z) {
        this.m = z;
    }

    public void setParasMap(Map<String, String> map) {
        this.d = map;
    }

    public void setReadTimeout(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("timeout can not be negative");
        }
        this.c = i;
    }

    public void setRequestProperties(Map<String, String> map) {
        this.e = map;
    }

    public void setRequestProperty(String str, String str2) {
        this.e.put(str, str2);
    }

    public void setSslTrustManager(SSLTrustManager sSLTrustManager) {
        this.l = sSLTrustManager;
    }

    public void setUrl(String str) {
        this.a = str;
    }

    public void setUseCaches(boolean z) {
        this.i = z;
    }

    public void setUserAgent(String str) {
        this.e.put(HttpHeaders.HEAD_KEY_USER_AGENT, str);
    }
}
