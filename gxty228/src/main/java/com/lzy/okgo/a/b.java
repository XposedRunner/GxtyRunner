package com.lzy.okgo.a;

import com.lzy.okgo.cache.a.c;
import com.lzy.okgo.cache.a.d;
import com.lzy.okgo.cache.a.e;
import com.lzy.okgo.cache.a.f;
import com.lzy.okgo.cache.a.g;
import com.lzy.okgo.request.base.Request;

/* compiled from: CacheCall */
public class b<T> implements c<T> {
    private com.lzy.okgo.cache.a.b<T> a = null;
    private Request<T, ? extends Request> b;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return a();
    }

    public b(Request<T, ? extends Request> request) {
        this.b = request;
        this.a = b();
    }

    public void a(com.lzy.okgo.b.b<T> bVar) {
        com.lzy.okgo.f.b.a((Object) bVar, "callback == null");
        this.a.a(this.a.a(), bVar);
    }

    private com.lzy.okgo.cache.a.b<T> b() {
        switch (this.b.getCacheMode()) {
            case DEFAULT:
                this.a = new c(this.b);
                break;
            case NO_CACHE:
                this.a = new e(this.b);
                break;
            case IF_NONE_CACHE_REQUEST:
                this.a = new f(this.b);
                break;
            case FIRST_CACHE_THEN_REQUEST:
                this.a = new d(this.b);
                break;
            case REQUEST_FAILED_READ_CACHE:
                this.a = new g(this.b);
                break;
        }
        if (this.b.getCachePolicy() != null) {
            this.a = this.b.getCachePolicy();
        }
        com.lzy.okgo.f.b.a(this.a, "policy == null");
        return this.a;
    }

    public c<T> a() {
        return new b(this.b);
    }
}
