package cn.jiguang.g.c;

import android.content.Context;
import android.telephony.TelephonyManager;
import cn.jiguang.g.c.a.a;
import cn.jiguang.g.c.a.c;
import cn.jiguang.g.c.a.d;
import cn.jiguang.g.c.a.e;
import java.util.ArrayList;

public final class b {
    private static b b;
    private Class<?>[] a = new Class[]{c.class, d.class, e.class};
    private a c = null;

    private b() {
    }

    private static a a(Context context, Class<?>[] clsArr) {
        if (clsArr == null) {
            cn.jiguang.e.d.i("TelManager", "check class array was null");
            return null;
        }
        int length = clsArr.length;
        int i = 0;
        while (i < length) {
            try {
                a aVar = (a) clsArr[i].newInstance();
                if (aVar.b(context)) {
                    return aVar;
                }
                i++;
            } catch (Throwable th) {
                cn.jiguang.e.d.i("TelManager", " new instance failed:" + th);
            }
        }
        return null;
    }

    public static b a() {
        if (b == null) {
            synchronized (TelephonyManager.class) {
                if (b == null) {
                    b = new b();
                }
            }
        }
        return b;
    }

    public final ArrayList<a> a(Context context) {
        if (this.c == null) {
            a bVar = new cn.jiguang.g.c.a.b();
            if (bVar.b(context)) {
                this.c = bVar;
                return bVar.a(context);
            }
            a a = a(context, this.a);
            if (a != null) {
                this.c = a;
                return a.a(context);
            }
            this.c = bVar;
        }
        return this.c.a(context);
    }
}
