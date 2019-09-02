package cn.jiguang.d.b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import cn.jiguang.d.a;
import cn.jiguang.d.d.b;
import cn.jiguang.d.d.c;
import cn.jiguang.e.d;
import cn.jiguang.g.k;

final class e extends Handler {
    final /* synthetic */ d a;

    public e(d dVar, Looper looper) {
        this.a = dVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        d.a("JiguangTcpManager", "handleMessage:" + message.toString());
        try {
            String str = "";
            Object data = message.getData();
            if (data != null) {
                str = data.getString("sdktype");
            }
            if (k.a(str) || str.equals(a.a)) {
                Bundle data2;
                switch (message.what) {
                    case 1001:
                        d.c("JiguangTcpManager", "Service killed by itself due to self killed mode");
                        cn.jiguang.g.a.j(this.a.h.getApplicationContext());
                        c.a(this.a.h, false);
                        return;
                    case 1002:
                        cn.jiguang.a.c.c.a(this.a.h, false);
                        return;
                    case PointerIconCompat.TYPE_HELP /*1003*/:
                        d.b(this.a);
                        return;
                    case 1004:
                        d.a(this.a, true);
                        return;
                    case 1005:
                        d.a(this.a, false);
                        return;
                    case PointerIconCompat.TYPE_CELL /*1006*/:
                        removeMessages(1011);
                        removeMessages(PointerIconCompat.TYPE_ALIAS);
                        sendEmptyMessageDelayed(1011, 3000);
                        return;
                    case PointerIconCompat.TYPE_CROSSHAIR /*1007*/:
                        sendEmptyMessageDelayed(PointerIconCompat.TYPE_ALIAS, 200);
                        return;
                    case PointerIconCompat.TYPE_ALIAS /*1010*/:
                        if (this.a.a != null) {
                            this.a.a.c();
                            return;
                        }
                        return;
                    case 1011:
                        this.a.e();
                        return;
                    case 1022:
                        d.d(this.a);
                        return;
                    case 1031:
                        cn.jiguang.g.a.j(this.a.h.getApplicationContext());
                        return;
                    case 1032:
                        cn.jiguang.d.d.e.a().a(this.a.h);
                        return;
                    case 7301:
                        d.b(this.a, message.getData().getLong("connection"));
                        return;
                    case 7303:
                        d.c(this.a, message.getData().getLong("connection"));
                        return;
                    case 7304:
                        d.a(this.a, message.getData().getLong("connection"));
                        return;
                    case 7306:
                        d.d("JiguangTcpManager", "onLoginFailed - connection:" + message.getData().getLong("connection") + ", respCode:" + message.arg2);
                        return;
                    case 7307:
                        f.b.set(false);
                        return;
                    case 7401:
                        data2 = message.getData();
                        if (data2 == null) {
                            d.h("JiguangTcpManager", "Unexpected - want to send null request.");
                            return;
                        }
                        int i = data2.getInt("request_timeout");
                        g.a().b(data2.getByteArray("request_data"), data2.getString("request_sdktype"), i);
                        return;
                    case 7402:
                        data2 = message.getData();
                        if (data2 == null) {
                            d.h("JiguangTcpManager", "Unexpected - msg response bundle is null.");
                            return;
                        }
                        g.a().a(message.getData().getLong("connection"), data2.getString("request_sdktype"), message.obj);
                        return;
                    case 7403:
                        if (message.obj == null) {
                            d.g("JiguangTcpManager", "Unexpected: bundle is null when request timeout.");
                            return;
                        } else if (message.obj instanceof h) {
                            g.a().b((h) message.obj);
                            return;
                        } else {
                            d.g("JiguangTcpManager", "Unexpected msg.obj is not Requesting when sent timeout.");
                            return;
                        }
                    case 7404:
                        if (message.obj == null) {
                            d.g("JiguangTcpManager", "Unexpected: msg obj is null when sent timeout.");
                            return;
                        } else if (message.obj instanceof h) {
                            g.a().a((h) message.obj);
                            return;
                        } else {
                            d.g("JiguangTcpManager", "Unexpected msg.obj is not String  request when sent timeout.");
                            return;
                        }
                    default:
                        d.g("JiguangTcpManager", "Action - handleCoreMessage , unhandle msg-" + message.what);
                        return;
                }
            }
            b.a();
            b.a(this.a.h, str, data);
        } catch (Exception e) {
        }
    }
}
