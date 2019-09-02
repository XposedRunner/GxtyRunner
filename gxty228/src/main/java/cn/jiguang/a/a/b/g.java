package cn.jiguang.a.a.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import cn.jiguang.e.d;

final class g extends Handler {
    final /* synthetic */ f a;

    public g(f fVar, Looper looper) {
        this.a = fVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1000:
                if (!this.a.k) {
                    this.a.k = true;
                    f.b(this.a);
                    f.c(this.a);
                    return;
                }
                return;
            case 1001:
            case PointerIconCompat.TYPE_HELP /*1003*/:
            case 1004:
            case 1005:
                if (this.a.m != null) {
                    this.a.m.a(message);
                    return;
                } else {
                    d.g("MyLocationManager", "gpsInfoManager was null");
                    return;
                }
            default:
                d.a("MyLocationManager", "unknow msg");
                return;
        }
    }
}
