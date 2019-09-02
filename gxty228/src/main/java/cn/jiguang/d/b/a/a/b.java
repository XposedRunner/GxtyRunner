package cn.jiguang.d.b.a.a;

import android.text.TextUtils;
import cn.jiguang.d.a;
import cn.jiguang.d.b.a.d;
import cn.jiguang.d.d.c;
import java.net.InetAddress;
import java.util.Collections;
import java.util.Iterator;

public final class b extends j {
    public b(d dVar) {
        super(dVar);
    }

    final int a() {
        String str = "DefaultConnPolicy";
        InetAddress b = c.b(a.f.c());
        if (b == null) {
            return -1;
        }
        Object hostAddress = b.getHostAddress();
        if (TextUtils.isEmpty(hostAddress)) {
            return -1;
        }
        Object cVar = new c(this);
        cVar.add(Integer.valueOf(7000));
        cVar.add(Integer.valueOf(7002));
        cVar.add(Integer.valueOf(7003));
        cVar.add(Integer.valueOf(7004));
        cVar.add(Integer.valueOf(7005));
        cVar.add(Integer.valueOf(7006));
        cVar.add(Integer.valueOf(7007));
        cVar.add(Integer.valueOf(7008));
        cVar.add(Integer.valueOf(7009));
        try {
            Collections.shuffle(cVar);
        } catch (Throwable th) {
        }
        cn.jiguang.d.b.a.a aVar = new cn.jiguang.d.b.a.a();
        Iterator it = cVar.iterator();
        while (it.hasNext()) {
            aVar.a(hostAddress, ((Integer) it.next()).intValue(), str);
        }
        return b(aVar);
    }
}
