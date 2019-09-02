package cn.jiguang.g;

import cn.jiguang.e.d;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class n {
    private long a = System.currentTimeMillis();
    private SimpleDateFormat b = new SimpleDateFormat("mm:ss:SSS", Locale.ENGLISH);

    public final void a(String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis() - this.a;
        String str3 = str2 + " | cost time:" + this.b.format(new Date(currentTimeMillis));
        if (currentTimeMillis > 500) {
            d.g(str, str3);
        } else {
            d.c(str, str3);
        }
    }
}
