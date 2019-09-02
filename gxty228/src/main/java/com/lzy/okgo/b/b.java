package com.lzy.okgo.b;

import com.lzy.okgo.c.a;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.base.Request;

/* compiled from: Callback */
public interface b<T> extends a<T> {
    void a();

    void a(Progress progress);

    void a(com.lzy.okgo.model.a<T> aVar);

    void a(Request<T, ? extends Request> request);

    void b(com.lzy.okgo.model.a<T> aVar);

    void c(com.lzy.okgo.model.a<T> aVar);
}
