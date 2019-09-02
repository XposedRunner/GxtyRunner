package com.loc;

import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;

/* compiled from: BasicLogHandler */
public class g {
    protected static g a;
    protected UncaughtExceptionHandler b;
    protected boolean c = true;

    public static void a(Throwable th, String str, String str2) {
        th.printStackTrace();
        if (a != null) {
            a.a(th, 1, str, str2);
        }
    }

    protected void a() {
    }

    protected void a(Context context, dk dkVar, boolean z) {
    }

    protected void a(dk dkVar, String str, String str2) {
    }

    protected void a(Throwable th, int i, String str, String str2) {
    }
}
