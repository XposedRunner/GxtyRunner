package com.lzy.okgo.cookie.a;

import java.util.List;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

/* compiled from: CookieStore */
public interface a {
    List<Cookie> a(HttpUrl httpUrl);

    void a(HttpUrl httpUrl, List<Cookie> list);
}
