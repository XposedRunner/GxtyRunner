package com.bigkoo.pickerview.lib;

import android.os.Handler;
import android.os.Message;
import android.support.graphics.drawable.PathInterpolatorCompat;
import com.bigkoo.pickerview.lib.WheelView.ACTION;

/* compiled from: MessageHandler */
final class c extends Handler {
    final WheelView a;

    c(WheelView wheelView) {
        this.a = wheelView;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1000:
                this.a.invalidate();
                return;
            case 2000:
                this.a.a(ACTION.FLING);
                return;
            case PathInterpolatorCompat.MAX_NUM_POINTS /*3000*/:
                this.a.b();
                return;
            default:
                return;
        }
    }
}
