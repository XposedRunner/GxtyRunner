package com.lzy.okgo.model;

import okhttp3.Call;
import okhttp3.Response;

/* compiled from: Response */
public final class a<T> {
    private T a;
    private Throwable b;
    private boolean c;
    private Call d;
    private Response e;

    public static <T> a<T> a(boolean z, T t, Call call, Response response) {
        a<T> aVar = new a();
        aVar.a(z);
        aVar.a((Object) t);
        aVar.a(call);
        aVar.a(response);
        return aVar;
    }

    public static <T> a<T> a(boolean z, Call call, Response response, Throwable th) {
        a<T> aVar = new a();
        aVar.a(z);
        aVar.a(call);
        aVar.a(response);
        aVar.a(th);
        return aVar;
    }

    public int a() {
        if (this.e == null) {
            return -1;
        }
        return this.e.code();
    }

    public String b() {
        if (this.e == null) {
            return null;
        }
        return this.e.message();
    }

    public void a(T t) {
        this.a = t;
    }

    public T c() {
        return this.a;
    }

    public Throwable d() {
        return this.b;
    }

    public void a(Throwable th) {
        this.b = th;
    }

    public Call e() {
        return this.d;
    }

    public void a(Call call) {
        this.d = call;
    }

    public Response f() {
        return this.e;
    }

    public void a(Response response) {
        this.e = response;
    }

    public void a(boolean z) {
        this.c = z;
    }
}
