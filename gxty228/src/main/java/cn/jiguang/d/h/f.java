package cn.jiguang.d.h;

import android.content.Context;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.SdkType;
import cn.jiguang.d.a;
import cn.jiguang.d.a.d;
import cn.jiguang.d.d.b;
import cn.jpush.android.service.DownloadProvider;
import java.util.concurrent.atomic.AtomicBoolean;

public class f {
    private static f d;
    private e a;
    private h b;
    private AtomicBoolean c;

    private f() {
        this.a = null;
        this.b = null;
        this.c = new AtomicBoolean(false);
        this.a = new e();
        this.b = new h();
    }

    public static f a() {
        if (d == null) {
            synchronized (f.class) {
                if (d == null) {
                    d = new f();
                }
            }
        }
        return d;
    }

    public final void a(Context context) {
        if (!this.c.get()) {
            String i = d.i(context);
            if (i == null) {
                i = "";
            }
            this.a.c(JCoreInterface.getDaemonAction());
            this.a.d("cn.jpush.android.service.PushService");
            this.a.a(DownloadProvider.class);
            this.a.a(3600);
            long d = d.d(context);
            cn.jiguang.e.d.c("WakeUpManager", "uid:" + d);
            this.a.b(d);
            this.a.a(i);
            this.b.a(3600);
            h hVar = this.b;
            b.a();
            hVar.b(b.d(SdkType.JPUSH.name(), ""));
            this.b.a(i);
            this.b.c("http://" + a.f.g() + "/v1/push/sdk/postlist");
            this.c.set(true);
        }
    }

    public final void a(Context context, boolean z) {
        try {
            new Thread(new g(this, context, z)).start();
        } catch (Throwable th) {
            cn.jiguang.e.d.i("WakeUpManager", "wake up fail cause by:" + th);
        }
    }

    public final e b() {
        return this.a;
    }

    public final h c() {
        return this.b;
    }
}
