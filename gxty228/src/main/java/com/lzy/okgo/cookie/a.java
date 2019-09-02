package com.lzy.okgo.cookie;

import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/* compiled from: CookieJarImpl */
public class a implements CookieJar {
    private com.lzy.okgo.cookie.a.a a;

    public a(com.lzy.okgo.cookie.a.a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("cookieStore can not be null!");
        }
        this.a = aVar;
    }

    public synchronized void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        this.a.a(httpUrl, list);
    }

    public synchronized List<Cookie> loadForRequest(HttpUrl httpUrl) {
        return this.a.a(httpUrl);
    }
}
