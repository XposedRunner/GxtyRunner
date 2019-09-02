package com.example.gita.gxty.utils;

import android.annotation.TargetApi;

public class NoAuthException extends Exception {
    public NoAuthException(String str) {
        super(str);
    }

    public NoAuthException(String str, Throwable th) {
        super(str, th);
    }

    public NoAuthException(Throwable th) {
        super(th);
    }

    @TargetApi(24)
    public NoAuthException(String str, Throwable th, boolean z, boolean z2) {
        super(str, th, z, z2);
    }
}
