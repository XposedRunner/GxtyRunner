package cn.jiguang.d.a;

import android.content.Context;
import cn.jiguang.g.b.e;
import java.util.Random;

public final class f {
    private static e a;

    public static synchronized long a() {
        long longValue;
        synchronized (f.class) {
            longValue = ((Long) b(null).a("next_rid", Long.valueOf(-1))).longValue();
            if (longValue != -1) {
                longValue = a(longValue);
                b(null).b("next_rid", Long.valueOf(longValue));
            }
            if (longValue == -1) {
                longValue = a((long) Math.abs(new Random().nextInt(32767)));
                b(null).b("next_rid", Long.valueOf(longValue));
            }
        }
        return longValue;
    }

    private static long a(long j) {
        return (j % 2 == 0 ? 1 + j : j + 2) % 32767;
    }

    public static void a(Context context) {
        a = e.a(context, "cn.jpush.preferences.v2.rid");
    }

    private static e b(Context context) {
        if (a == null) {
            a(null);
        }
        return a;
    }
}
