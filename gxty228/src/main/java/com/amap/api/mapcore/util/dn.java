package com.amap.api.mapcore.util;

import android.content.Context;
import android.util.Log;
import cn.jiguang.net.HttpUtils;

/* compiled from: AuthLogUtil */
public class dn {
    static String a;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            stringBuilder.append(HttpUtils.EQUAL_SIGN);
        }
        a = stringBuilder.toString();
    }

    public static void a(Context context) {
        String str = "鉴权失败，当前key没有自定义纹理的使用权限，自定义纹理相关内容，将不会呈现！";
        b(a);
        if (context != null) {
            a("key:" + fx.f(context));
        }
        b(str);
        b(a);
    }

    static void a(String str) {
        int i = 0;
        if (str.length() < 78) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("|").append(str);
            while (i < 78 - str.length()) {
                stringBuilder.append(" ");
                i++;
            }
            stringBuilder.append("|");
            b(stringBuilder.toString());
            return;
        }
        b("|" + str.substring(0, 78) + "|");
        a(str.substring(78));
    }

    private static void b(String str) {
        Log.i("authErrLog", str);
    }
}
