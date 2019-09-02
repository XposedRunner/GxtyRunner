package cn.jpush.android.a;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.api.JActionExtra;
import cn.jpush.android.b;
import cn.jpush.android.c.g;
import cn.jpush.android.c.i;
import cn.jpush.android.d.e;

public class c extends JActionExtra {
    public Object beforLogin(Context context, int i, String str) {
        if (context == null) {
            e.i("JPushProtocolAction", "context was null");
            return null;
        } else if (TextUtils.isEmpty(str)) {
            e.i("JPushProtocolAction", " filed name was empty");
            return null;
        } else if ("platformtype".equals(str)) {
            if (i < 16) {
                return null;
            }
            CharSequence a;
            byte a2 = i.a(context);
            if (a2 != (byte) 0) {
                a = b.a(context, a2);
                boolean b = b.b(context, a2);
                if (a2 == (byte) 2) {
                    a2 = (byte) (a2 | 64);
                }
                if (!b || TextUtils.isEmpty(a)) {
                    a2 = (byte) (a2 | 128);
                }
            }
            if (g.a().g(context)) {
                a2 = (byte) (a2 | 8);
                a = b.a(context, 8);
                if (b.b(context, 8) && !TextUtils.isEmpty(a)) {
                    a2 = (byte) (a2 | 32);
                }
            }
            return Byte.valueOf(a2);
        } else if ("platformregid".equals(str)) {
            return b.a(context, i.a(context));
        } else {
            return null;
        }
    }
}
