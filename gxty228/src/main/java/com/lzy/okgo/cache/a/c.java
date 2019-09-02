package com.lzy.okgo.cache.a;

import com.lzy.okgo.b.b;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.exception.CacheException;
import com.lzy.okgo.model.a;
import com.lzy.okgo.request.base.Request;
import okhttp3.Call;
import okhttp3.Response;

/* compiled from: DefaultCachePolicy */
public class c<T> extends a<T> {
    public c(Request<T, ? extends Request> request) {
        super(request);
    }

    public void a(final a<T> aVar) {
        a(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                this.b.f.a(aVar);
                this.b.f.a();
            }
        });
    }

    public void b(final a<T> aVar) {
        a(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                this.b.f.b(aVar);
                this.b.f.a();
            }
        });
    }

    public boolean a(Call call, Response response) {
        if (response.code() != 304) {
            return false;
        }
        if (this.g == null) {
            final a a = a.a(true, call, response, CacheException.NON_AND_304(this.a.getCacheKey()));
            a(new Runnable(this) {
                final /* synthetic */ c b;

                public void run() {
                    this.b.f.b(a);
                    this.b.f.a();
                }
            });
            return true;
        }
        a = a.a(true, this.g.getData(), call, response);
        a(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                this.b.f.c(a);
                this.b.f.a();
            }
        });
        return true;
    }

    public void a(CacheEntity<T> cacheEntity, b<T> bVar) {
        this.f = bVar;
        a(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.f.a(this.a.a);
                try {
                    this.a.b();
                    this.a.c();
                } catch (Throwable th) {
                    this.a.f.b(a.a(false, this.a.e, null, th));
                }
            }
        });
    }
}
