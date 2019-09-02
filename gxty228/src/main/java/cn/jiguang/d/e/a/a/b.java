package cn.jiguang.d.e.a.a;

import cn.jiguang.d.g.a.a;
import cn.jiguang.e.d;
import cn.jiguang.g.k;

public final class b {
    public static byte[] a(byte[] bArr, int i) {
        byte[] a = a.a(bArr, i);
        if (a == null || a.length <= 0) {
            d.h("CorePackage", "totalRegBuf length :0");
            return null;
        }
        d.a("CorePackage", "convertData:" + k.a(a));
        return a;
    }
}
