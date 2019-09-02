package cn.jiguang.f;

import java.util.Locale;

final class d {
    static void a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(Locale.ENGLISH, str, objArr));
        }
    }
}
