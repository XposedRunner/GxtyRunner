package cn.jiguang.d.b.a.a;

import android.text.TextUtils;
import cn.jiguang.d.a.a;
import cn.jiguang.d.b.a.d;

public final class h extends j {
    public h(d dVar) {
        super(dVar);
    }

    final int a() {
        String str = "LastGoodConnPolicy";
        Object d = a.d();
        if (TextUtils.isEmpty(d)) {
            return -1;
        }
        cn.jiguang.d.b.a.a aVar = new cn.jiguang.d.b.a.a();
        aVar.a(d, a.e(), str);
        return b(aVar);
    }
}
