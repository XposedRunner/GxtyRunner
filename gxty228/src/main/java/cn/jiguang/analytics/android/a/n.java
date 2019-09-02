package cn.jiguang.analytics.android.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class n extends Handler {
    final /* synthetic */ m a;

    public n(m mVar, Looper looper) {
        this.a = mVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                m.a(this.a);
                return;
            case 2:
                this.a.d.set(true);
                m.a(this.a);
                return;
            default:
                return;
        }
    }
}
