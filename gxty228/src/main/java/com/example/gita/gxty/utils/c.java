package com.example.gita.gxty.utils;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.b;
import com.google.gson.e;
import com.google.gson.f;
import com.qq.e.ads.nativ.NativeExpressADView;
import java.lang.reflect.Type;

/* compiled from: Convert */
public class c {

    /* compiled from: Convert */
    private static class a {
        private static e a = new f().a(new b() {
            public boolean a(com.google.gson.c cVar) {
                if ("isNeAd".equals(cVar.a())) {
                    return true;
                }
                return false;
            }

            public boolean a(Class<?> cls) {
                if (NativeExpressADView.class.getName().equals(cls.getName())) {
                    return true;
                }
                return false;
            }
        }).a();
    }

    private static e a() {
        return a.a;
    }

    public static <T> T a(String str, Class<T> cls) throws JsonIOException, JsonSyntaxException {
        return a().a(str, (Class) cls);
    }

    public static <T> T a(String str, Type type) {
        return a().a(str, type);
    }
}
