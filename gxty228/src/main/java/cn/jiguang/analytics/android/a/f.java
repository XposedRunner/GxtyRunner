package cn.jiguang.analytics.android.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class f extends Handler {
    f(Looper looper) {
        super(looper);
    }

    public final void handleMessage(Message message) {
        l a = e.b(message.what);
        if (a != null) {
            a.run();
            sendEmptyMessageDelayed(message.what, (long) (a.a() * 1000));
        }
    }
}
