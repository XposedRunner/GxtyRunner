package com.ajguan.library.exception;

public class ERVHRuntimeException extends RuntimeException {
    public ERVHRuntimeException(Throwable th) {
        super(th);
    }

    public ERVHRuntimeException(String str, Throwable th) {
        super(str, th);
    }

    public ERVHRuntimeException(String str) {
        super(str);
    }
}
