package com.lzy.okgo.exception;

import com.lzy.okgo.f.b;
import com.lzy.okgo.model.a;

public class HttpException extends RuntimeException {
    private static final long serialVersionUID = 8773734741709178425L;
    private transient a<?> a;
    private int code;
    private String message;

    public HttpException(String str) {
        super(str);
    }

    public HttpException(a<?> aVar) {
        super(a(aVar));
        this.code = aVar.a();
        this.message = aVar.b();
        this.a = aVar;
    }

    private static String a(a<?> aVar) {
        b.a((Object) aVar, "response == null");
        return "HTTP " + aVar.a() + " " + aVar.b();
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public a<?> response() {
        return this.a;
    }

    public static HttpException NET_ERROR() {
        return new HttpException("network error! http response code is 404 or 5xx!");
    }

    public static HttpException COMMON(String str) {
        return new HttpException(str);
    }
}
