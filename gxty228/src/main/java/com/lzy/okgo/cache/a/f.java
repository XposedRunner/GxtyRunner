package com.lzy.okgo.cache.a;

import com.lzy.okgo.b.b;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.model.a;
import com.lzy.okgo.request.base.Request;

/* compiled from: NoneCacheRequestPolicy */
public class f<T> extends a<T> {
    public f(Request<T, ? extends Request> request) {
        super(request);
    }

    public void a(final a<T> aVar) {
        a(new Runnable(this) {
            final /* synthetic */ f b;

            public void run() {
                this.b.f.a(aVar);
                this.b.f.a();
            }
        });
    }

    public void b(final a<T> aVar) {
        a(new Runnable(this) {
            final /* synthetic */ f b;

            public void run() {
                this.b.f.b(aVar);
                this.b.f.a();
            }
        });
    }

    public void a(final CacheEntity<T> cacheEntity, b<T> bVar) {
        this.f = bVar;
        a(new Runnable(this) {
            final /* synthetic */ f b;

            public void run() {
                this.b.f.a(this.b.a);
                try {
                    this.b.b();
                    if (cacheEntity != null) {
                        this.b.f.c(a.a(true, cacheEntity.getData(), this.b.e, null));
                        this.b.f.a();
                        return;
                    }
                    this.b.c();
                } catch (Throwable th) {
                    this.b.f.b(a.a(false, this.b.e, null, th));
                }
            }
        });
    }
}
