package com.lzy.okgo.cache.a;

import android.graphics.Bitmap;
import com.lzy.okgo.b.b;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.request.base.Request;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/* compiled from: BaseCachePolicy */
public abstract class a<T> implements b<T> {
    protected Request<T, ? extends Request> a;
    protected volatile boolean b;
    protected volatile int c = 0;
    protected boolean d;
    protected Call e;
    protected b<T> f;
    protected CacheEntity<T> g;

    public a(Request<T, ? extends Request> request) {
        this.a = request;
    }

    public boolean a(Call call, Response response) {
        return false;
    }

    public CacheEntity<T> a() {
        if (this.a.getCacheKey() == null) {
            this.a.cacheKey(com.lzy.okgo.f.b.a(this.a.getBaseUrl(), this.a.getParams().urlParamsMap));
        }
        if (this.a.getCacheMode() == null) {
            this.a.cacheMode(CacheMode.NO_CACHE);
        }
        CacheMode cacheMode = this.a.getCacheMode();
        if (cacheMode != CacheMode.NO_CACHE) {
            this.g = com.lzy.okgo.d.b.c().a(this.a.getCacheKey());
            com.lzy.okgo.f.a.a(this.a, this.g, cacheMode);
            if (this.g != null && this.g.checkExpire(cacheMode, this.a.getCacheTime(), System.currentTimeMillis())) {
                this.g.setExpire(true);
            }
        }
        if (this.g == null || this.g.isExpire() || this.g.getData() == null || this.g.getResponseHeaders() == null) {
            this.g = null;
        }
        return this.g;
    }

    public synchronized Call b() throws Throwable {
        if (this.d) {
            throw HttpException.COMMON("Already executed!");
        }
        this.d = true;
        this.e = this.a.getRawCall();
        if (this.b) {
            this.e.cancel();
        }
        return this.e;
    }

    protected void c() {
        this.e.enqueue(new Callback(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onFailure(Call call, IOException iOException) {
                if ((iOException instanceof SocketTimeoutException) && this.a.c < this.a.a.getRetryCount()) {
                    a aVar = this.a;
                    aVar.c++;
                    this.a.e = this.a.a.getRawCall();
                    if (this.a.b) {
                        this.a.e.cancel();
                    } else {
                        this.a.e.enqueue(this);
                    }
                } else if (!call.isCanceled()) {
                    this.a.b(com.lzy.okgo.model.a.a(false, call, null, (Throwable) iOException));
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                if (code == 404 || code >= 500) {
                    this.a.b(com.lzy.okgo.model.a.a(false, call, response, HttpException.NET_ERROR()));
                } else if (!this.a.a(call, response)) {
                    try {
                        Object a = this.a.a.getConverter().a(response);
                        this.a.a(response.headers(), a);
                        this.a.a(com.lzy.okgo.model.a.a(false, a, call, response));
                    } catch (Throwable th) {
                        this.a.b(com.lzy.okgo.model.a.a(false, call, response, th));
                    }
                }
            }
        });
    }

    private void a(Headers headers, T t) {
        if (this.a.getCacheMode() != CacheMode.NO_CACHE && !(t instanceof Bitmap)) {
            CacheEntity a = com.lzy.okgo.f.a.a(headers, t, this.a.getCacheMode(), this.a.getCacheKey());
            if (a == null) {
                com.lzy.okgo.d.b.c().b(this.a.getCacheKey());
            } else {
                com.lzy.okgo.d.b.c().a(this.a.getCacheKey(), a);
            }
        }
    }

    protected void a(Runnable runnable) {
        com.lzy.okgo.a.a().c().post(runnable);
    }
}
