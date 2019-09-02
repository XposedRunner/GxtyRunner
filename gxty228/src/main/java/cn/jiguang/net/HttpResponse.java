package cn.jiguang.net;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private String a;
    private String b;
    private Map<String, Object> c;
    private int d;
    private long e;
    private boolean f;
    private boolean g;
    private int h;

    public HttpResponse() {
        this.h = -1;
        this.c = new HashMap();
    }

    public HttpResponse(String str) {
        this.h = -1;
        this.a = str;
        this.d = 0;
        this.f = false;
        this.g = false;
        this.c = new HashMap();
    }

    private int a() {
        try {
            String str = (String) this.c.get(HttpConstants.CACHE_CONTROL);
            if (!TextUtils.isEmpty(str)) {
                int indexOf = str.indexOf("max-age=");
                if (indexOf != -1) {
                    int indexOf2 = str.indexOf(",", indexOf);
                    return Integer.parseInt(indexOf2 != -1 ? str.substring(indexOf + 8, indexOf2) : str.substring(indexOf + 8));
                }
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public long getExpiredTime() {
        if (this.g) {
            return this.e;
        }
        this.g = true;
        int a = a();
        long currentTimeMillis = a != -1 ? ((long) (a * 1000)) + System.currentTimeMillis() : !TextUtils.isEmpty(getExpiresHeader()) ? HttpUtils.parseGmtTime(getExpiresHeader()) : -1;
        this.e = currentTimeMillis;
        return currentTimeMillis;
    }

    public String getExpiresHeader() {
        try {
            return this.c == null ? null : (String) this.c.get("expires");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getResponseBody() {
        return this.b;
    }

    public int getResponseCode() {
        return this.h;
    }

    public int getType() {
        return this.d;
    }

    public String getUrl() {
        return this.a;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > this.e;
    }

    public boolean isInCache() {
        return this.f;
    }

    public void setExpiredTime(long j) {
        this.g = true;
        this.e = j;
    }

    public HttpResponse setInCache(boolean z) {
        this.f = z;
        return this;
    }

    public void setResponseBody(String str) {
        this.b = str;
    }

    public void setResponseCode(int i) {
        this.h = i;
    }

    public void setResponseHeader(String str, String str2) {
        if (this.c != null) {
            this.c.put(str, str2);
        }
    }

    public void setResponseHeaders(Map<String, Object> map) {
        this.c = map;
    }

    public void setType(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The type of HttpResponse cannot be smaller than 0.");
        }
        this.d = i;
    }

    public void setUrl(String str) {
        this.a = str;
    }

    public String toString() {
        return "HttpResponse{responseBody='" + this.b + '\'' + ", responseCode=" + this.h + '}';
    }
}
