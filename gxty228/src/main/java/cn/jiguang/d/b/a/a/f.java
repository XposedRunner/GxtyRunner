package cn.jiguang.d.b.a.a;

import android.content.Context;
import cn.jiguang.d.b.a.d;
import java.util.Iterator;
import java.util.LinkedList;

public final class f {
    private final LinkedList<j> a = new LinkedList();
    private final d b;

    public f(Context context, cn.jiguang.d.b.f fVar, long j) {
        this.b = new d(context, fVar, j);
        this.a.add(new i(this.b, true));
        this.a.add(new a(this.b));
        this.a.add(new l(this.b));
        this.a.add(new d(this.b));
        this.a.add(new e(this.b));
        this.a.add(new h(this.b));
        this.a.add(new k(this.b));
        this.a.add(new b(this.b));
        this.a.add(new i(this.b, false));
    }

    public final void a() {
        try {
            new Thread(new g(this)).start();
        } catch (Throwable th) {
            cn.jiguang.e.d.g("JSis", "sis report thread start failed,error:" + th);
        }
    }

    public final int b() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            try {
                switch (((j) it.next()).a()) {
                    case 0:
                        return 0;
                    case 2:
                        return 2;
                    default:
                        break;
                }
            } catch (Throwable th) {
                cn.jiguang.e.d.g("JSis", "sisAndConnect error:" + th);
            }
        }
        return -1;
    }
}
