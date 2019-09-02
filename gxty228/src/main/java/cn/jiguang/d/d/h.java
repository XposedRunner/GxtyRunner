package cn.jiguang.d.d;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cn.jiguang.a.c.c;
import cn.jiguang.d.b.a;
import cn.jiguang.e.d;

final class h extends Handler {
    final /* synthetic */ g a;

    h(g gVar, Looper looper) {
        this.a = gVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 8000:
                g gVar = this.a;
                Context a = this.a.c;
                if (a == null) {
                    d.g("HeartBeatHelper", "NULL Context");
                } else {
                    c.c(a);
                    if (!e.a().f()) {
                        d.c("HeartBeatHelper", "is not push or im");
                    } else if (cn.jiguang.d.a.d.j(a)) {
                        d.a("HeartBeatHelper", "tcp has close by user");
                    } else if (a.c()) {
                        d.a("HeartBeatHelper", "service can run,will use alarmReceiver to send hb");
                    } else {
                        d.c("HeartBeatHelper", "send hb by handler");
                        g.b(a);
                    }
                }
                this.a.c.getApplicationContext();
                g.b();
                return;
            default:
                return;
        }
    }
}
