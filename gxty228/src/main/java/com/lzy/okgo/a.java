package com.lzy.okgo;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.f.b;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor.Level;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;

/* compiled from: OkGo */
public class a {
    public static long a = 300;
    private Application b;
    private Handler c;
    private OkHttpClient d;
    private HttpParams e;
    private HttpHeaders f;
    private int g;
    private CacheMode h;
    private long i;

    /* compiled from: OkGo */
    private static class a {
        private static a a = new a();
    }

    private a() {
        this.c = new Handler(Looper.getMainLooper());
        this.g = 3;
        this.i = -1;
        this.h = CacheMode.NO_CACHE;
        Builder builder = new Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor("OkGo");
        httpLoggingInterceptor.a(Level.BODY);
        httpLoggingInterceptor.a(java.util.logging.Level.INFO);
        builder.addInterceptor(httpLoggingInterceptor);
        builder.readTimeout(60000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(60000, TimeUnit.MILLISECONDS);
        builder.connectTimeout(60000, TimeUnit.MILLISECONDS);
        com.lzy.okgo.e.a.a a = com.lzy.okgo.e.a.a();
        builder.sslSocketFactory(a.a, a.b);
        builder.hostnameVerifier(com.lzy.okgo.e.a.b);
        this.d = builder.build();
    }

    public static a a() {
        return a.a;
    }

    public static <T> GetRequest<T> a(String str) {
        return new GetRequest(str);
    }

    public static <T> PostRequest<T> b(String str) {
        return new PostRequest(str);
    }

    public a a(Application application) {
        this.b = application;
        return this;
    }

    public Context b() {
        b.a(this.b, "please call OkGo.getInstance().init() first in application!");
        return this.b;
    }

    public Handler c() {
        return this.c;
    }

    public OkHttpClient d() {
        b.a(this.d, "please call OkGo.getInstance().setOkHttpClient() first in application!");
        return this.d;
    }

    public a a(OkHttpClient okHttpClient) {
        b.a((Object) okHttpClient, "okHttpClient == null");
        this.d = okHttpClient;
        return this;
    }

    public a a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("retryCount must > 0");
        }
        this.g = i;
        return this;
    }

    public int e() {
        return this.g;
    }

    public a a(CacheMode cacheMode) {
        this.h = cacheMode;
        return this;
    }

    public CacheMode f() {
        return this.h;
    }

    public a a(long j) {
        if (j <= -1) {
            j = -1;
        }
        this.i = j;
        return this;
    }

    public long g() {
        return this.i;
    }

    public HttpParams h() {
        return this.e;
    }

    public a a(HttpParams httpParams) {
        if (this.e == null) {
            this.e = new HttpParams();
        }
        this.e.put(httpParams);
        return this;
    }

    public HttpHeaders i() {
        return this.f;
    }

    public a a(HttpHeaders httpHeaders) {
        if (this.f == null) {
            this.f = new HttpHeaders();
        }
        this.f.put(httpHeaders);
        return this;
    }
}
