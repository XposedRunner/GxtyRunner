package com.lzy.okgo.cache.a;

import com.lzy.okgo.b.b;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.model.a;
import com.lzy.okgo.request.base.Request;

/* compiled from: RequestFailedCachePolicy */
public class g<T> extends a<T> {
    public g(Request<T, ? extends Request> request) {
        super(request);
    }

    public void a(final a<T> aVar) {
        a(new Runnable(this) {
            final /* synthetic */ g b;

            public void run() {
                this.b.f.a(aVar);
                this.b.f.a();
            }
        });
    }

    public void b(final a<T> aVar) {
        if (this.g != null) {
            final a a = a.a(true, this.g.getData(), aVar.e(), aVar.f());
            a(new Runnable(this) {
                final /* synthetic */ g b;

                public void run() {
                    this.b.f.c(a);
                    this.b.f.a();
                }
            });
            return;
        }
        a(new Runnable(this) {
            final /* synthetic */ g b;

            public void run() {
                this.b.f.b(aVar);
                this.b.f.a();
            }
        });
    }

    public void a(CacheEntity<T> cacheEntity, b<T> bVar) {
        this.f = bVar;
        a(new Runnable(this) {
            final /* synthetic */ g a;

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
