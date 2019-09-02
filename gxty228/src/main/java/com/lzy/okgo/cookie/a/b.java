package com.lzy.okgo.cookie.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.lzy.okgo.cookie.SerializableCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

/* compiled from: SPCookieStore */
public class b implements a {
    private final Map<String, ConcurrentHashMap<String, Cookie>> a = new HashMap();
    private final SharedPreferences b;

    public b(Context context) {
        this.b = context.getSharedPreferences("okgo_cookie", 0);
        for (Entry entry : this.b.getAll().entrySet()) {
            if (!(entry.getValue() == null || ((String) entry.getKey()).startsWith("cookie_"))) {
                for (String str : TextUtils.split((String) entry.getValue(), ",")) {
                    String string = this.b.getString("cookie_" + str, null);
                    if (string != null) {
                        Cookie decodeCookie = SerializableCookie.decodeCookie(string);
                        if (decodeCookie != null) {
                            if (!this.a.containsKey(entry.getKey())) {
                                this.a.put(entry.getKey(), new ConcurrentHashMap());
                            }
                            ((ConcurrentHashMap) this.a.get(entry.getKey())).put(str, decodeCookie);
                        }
                    }
                }
            }
        }
    }

    private String a(Cookie cookie) {
        return cookie.name() + "@" + cookie.domain();
    }

    private static boolean b(Cookie cookie) {
        return cookie.expiresAt() < System.currentTimeMillis();
    }

    public synchronized void a(HttpUrl httpUrl, List<Cookie> list) {
        for (Cookie a : list) {
            a(httpUrl, a);
        }
    }

    public synchronized void a(HttpUrl httpUrl, Cookie cookie) {
        if (!this.a.containsKey(httpUrl.host())) {
            this.a.put(httpUrl.host(), new ConcurrentHashMap());
        }
        if (b(cookie)) {
            b(httpUrl, cookie);
        } else {
            a(httpUrl, cookie, a(cookie));
        }
    }

    private void a(HttpUrl httpUrl, Cookie cookie, String str) {
        ((ConcurrentHashMap) this.a.get(httpUrl.host())).put(str, cookie);
        Editor edit = this.b.edit();
        edit.putString(httpUrl.host(), TextUtils.join(",", ((ConcurrentHashMap) this.a.get(httpUrl.host())).keySet()));
        edit.putString("cookie_" + str, SerializableCookie.encodeCookie(httpUrl.host(), cookie));
        edit.apply();
    }

    public synchronized List<Cookie> a(HttpUrl httpUrl) {
        List<Cookie> list;
        List<Cookie> arrayList = new ArrayList();
        if (this.a.containsKey(httpUrl.host())) {
            for (Cookie cookie : ((ConcurrentHashMap) this.a.get(httpUrl.host())).values()) {
                if (b(cookie)) {
                    b(httpUrl, cookie);
                } else {
                    arrayList.add(cookie);
                }
            }
            list = arrayList;
        } else {
            list = arrayList;
        }
        return list;
    }

    public synchronized boolean b(HttpUrl httpUrl, Cookie cookie) {
        boolean z;
        if (this.a.containsKey(httpUrl.host())) {
            String a = a(cookie);
            if (((ConcurrentHashMap) this.a.get(httpUrl.host())).containsKey(a)) {
                ((ConcurrentHashMap) this.a.get(httpUrl.host())).remove(a);
                Editor edit = this.b.edit();
                if (this.b.contains("cookie_" + a)) {
                    edit.remove("cookie_" + a);
                }
                edit.putString(httpUrl.host(), TextUtils.join(",", ((ConcurrentHashMap) this.a.get(httpUrl.host())).keySet()));
                edit.apply();
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        return z;
    }
}
