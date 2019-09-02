package cn.jiguang.a.a.a;

import android.os.Handler.Callback;
import android.os.Message;
import cn.jiguang.e.d;

final class e implements Callback {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public final boolean handleMessage(Message message) {
        if (message != null && message.what == 1) {
            Thread thread = (Thread) message.obj;
            if (thread != null) {
                thread.interrupt();
            }
            d.e("ArpUtil", (message.getData() != null ? message.getData().getString("ip") : "") + " ping timeout.");
        }
        return false;
    }
}
