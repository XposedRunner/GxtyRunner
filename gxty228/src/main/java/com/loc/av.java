package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import java.lang.ref.WeakReference;

/* compiled from: MarkInfoManager */
public class av {
    static WeakReference<at> a;

    public static void a(final Context context) {
        j.d().submit(new Runnable() {
            public final void run() {
                synchronized (av.class) {
                    at a = ba.a(av.a);
                    ba.a(context, a, h.j, 50, 102400, Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
                    if (a.g == null) {
                        String b = av.b(context);
                        a.g = new bf(new be(context, new bj(), new do(new d(new b())), "WImFwcG5hbWUiOiIlcyIsInBrZyI6IiVzIiwiZGl1IjoiJXMi", db.b(context), db.c(context), b));
                    }
                    a.h = 14400000;
                    if (TextUtils.isEmpty(a.i)) {
                        a.i = "eKey";
                    }
                    if (a.f == null) {
                        a.f = new bn(context, a.h, a.i, new bk(5, a.a, new bp(context)));
                    }
                    au.a(a);
                }
            }
        });
    }

    static /* synthetic */ String b(Context context) {
        Object u = df.u(context);
        if (!TextUtils.isEmpty(u)) {
            return u;
        }
        u = df.h(context);
        if (!TextUtils.isEmpty(u)) {
            return u;
        }
        u = df.l(context);
        return TextUtils.isEmpty(u) ? df.b(context) : u;
    }
}
