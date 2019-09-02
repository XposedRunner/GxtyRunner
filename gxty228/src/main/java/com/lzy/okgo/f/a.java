package com.lzy.okgo.f;

import android.text.TextUtils;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.base.Request;
import java.util.Locale;
import java.util.StringTokenizer;
import okhttp3.Headers;

/* compiled from: HeaderParser */
public class a {
    public static <T> CacheEntity<T> a(Headers headers, T t, CacheMode cacheMode, String str) {
        long j = 0;
        if (cacheMode == CacheMode.DEFAULT) {
            long date = HttpHeaders.getDate(headers.get(HttpHeaders.HEAD_KEY_DATE));
            long expiration = HttpHeaders.getExpiration(headers.get(HttpHeaders.HEAD_KEY_EXPIRES));
            Object cacheControl = HttpHeaders.getCacheControl(headers.get(HttpHeaders.HEAD_KEY_CACHE_CONTROL), headers.get(HttpHeaders.HEAD_KEY_PRAGMA));
            if (TextUtils.isEmpty(cacheControl) && expiration <= 0) {
                return null;
            }
            long j2;
            if (TextUtils.isEmpty(cacheControl)) {
                j2 = 0;
            } else {
                StringTokenizer stringTokenizer = new StringTokenizer(cacheControl, ",");
                j2 = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    String toLowerCase = stringTokenizer.nextToken().trim().toLowerCase(Locale.getDefault());
                    if (toLowerCase.equals("no-cache") || toLowerCase.equals("no-store")) {
                        return null;
                    }
                    if (toLowerCase.startsWith("max-age=")) {
                        try {
                            j2 = Long.parseLong(toLowerCase.substring(8));
                            if (j2 <= 0) {
                                return null;
                            }
                        } catch (Throwable e) {
                            d.a(e);
                        }
                    }
                }
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (date > 0) {
                currentTimeMillis = date;
            }
            if (j2 > 0) {
                j = currentTimeMillis + (j2 * 1000);
            } else if (expiration >= 0) {
                j = expiration;
            }
        } else {
            j = System.currentTimeMillis();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        for (String str2 : headers.names()) {
            httpHeaders.put(str2, headers.get(str2));
        }
        CacheEntity<T> cacheEntity = new CacheEntity();
        cacheEntity.setKey(str);
        cacheEntity.setData(t);
        cacheEntity.setLocalExpire(j);
        cacheEntity.setResponseHeaders(httpHeaders);
        return cacheEntity;
    }

    public static <T> void a(Request request, CacheEntity<T> cacheEntity, CacheMode cacheMode) {
        if (cacheEntity != null && cacheMode == CacheMode.DEFAULT) {
            HttpHeaders responseHeaders = cacheEntity.getResponseHeaders();
            if (responseHeaders != null) {
                String str = responseHeaders.get(HttpHeaders.HEAD_KEY_E_TAG);
                if (str != null) {
                    request.headers(HttpHeaders.HEAD_KEY_IF_NONE_MATCH, str);
                }
                long lastModified = HttpHeaders.getLastModified(responseHeaders.get(HttpHeaders.HEAD_KEY_LAST_MODIFIED));
                if (lastModified > 0) {
                    request.headers(HttpHeaders.HEAD_KEY_IF_MODIFIED_SINCE, HttpHeaders.formatMillisToGMT(lastModified));
                }
            }
        }
    }
}
