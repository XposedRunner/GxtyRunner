package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.lang.ref.WeakReference;

/* compiled from: SoFileCleaner */
public final class ii {
    private ic a;
    private hz b;
    private Context c;

    /* compiled from: SoFileCleaner */
    static class a implements Runnable {
        private int a = 2;
        private WeakReference<Context> b;
        private hz c;
        private String d;

        public a(int i, Context context, hz hzVar, String str) {
            if (context != null) {
                this.b = new WeakReference(context);
            }
            this.c = hzVar;
            this.d = str;
        }

        public final void run() {
            Object a;
            int i = this.a;
            if (this.c != null) {
                if (i == 1) {
                    a = this.c.a();
                } else if (i == 2) {
                    a = this.c.a(this.d);
                }
                if (!TextUtils.isEmpty(a) && this.b != null && this.b.get() != null) {
                    hz.e(a);
                    if (this.a == 1) {
                        ik.c((Context) this.b.get());
                        return;
                    } else {
                        ik.d((Context) this.b.get());
                        return;
                    }
                }
                return;
            }
            a = null;
            if (!TextUtils.isEmpty(a)) {
            }
        }
    }

    public ii(ic icVar, hz hzVar, Context context) {
        this.a = icVar;
        this.b = hzVar;
        this.c = context;
    }

    public final void a(String str) {
        Runnable aVar = new a(2, this.c, this.b, str);
        if (this.a != null) {
            this.a.a(aVar);
        }
    }
}
