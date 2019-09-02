package cn.jiguang.d.d;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import cn.jiguang.a.a.c.e;
import cn.jiguang.a.c.b;
import cn.jiguang.api.JAction;
import cn.jiguang.api.JResponse;
import cn.jiguang.d.e.a.a.a;
import cn.jiguang.d.e.a.a.c;
import cn.jiguang.e.d;
import com.tencent.tauth.AuthActivity;
import java.nio.ByteBuffer;

public class f implements JAction {
    public long dispatchMessage(Context context, long j, int i, Object obj, ByteBuffer byteBuffer) {
        JResponse a = a.a((c) obj, byteBuffer);
        if (a != null) {
            d.b("JCoreAction", "Action - handResponse  response:" + a.toString());
            if (a.code == 0) {
                switch (a.getCommand()) {
                    case 19:
                        cn.jiguang.d.b.d.a().a(a, j);
                        break;
                    case 25:
                        cn.jiguang.a.c.a.a(context, cn.jiguang.d.b.d.a().b(), j, a);
                        break;
                    case 26:
                        if (a.code != 0) {
                            e.a().a(context, a.getRid().longValue(), a.code);
                            break;
                        }
                        e.a().a(context, a.getRid().longValue());
                        break;
                    default:
                        d.g("JCoreAction", "sendToThird response command - " + a.getCommand());
                        break;
                }
            }
            d.h("JCoreAction", "Received error response - code:" + a.code);
        } else {
            d.g("JCoreAction", "Action - receivedCommand unexcepted - response was null");
        }
        return a != null ? a.getHead().b().longValue() : -1;
    }

    public void dispatchTimeOutMessage(Context context, long j, long j2, int i) {
        if (i == 26) {
            e.a().b(context, j2);
        }
    }

    public IBinder getBinderByType(String str) {
        return null;
    }

    public String getSdkVersion() {
        return "1.1.9";
    }

    public void handleMessage(Context context, long j, Object obj) {
    }

    public boolean isSupportedCMD(int i) {
        return i == 0 || i == 1 || i == 19 || i == 25 || i == 26;
    }

    public void onActionRun(Context context, long j, Bundle bundle, Object obj) {
        b.a();
        d.d("ARunAction", " pkg:" + cn.jiguang.d.a.c);
        d.d("ARunAction", new StringBuilder("bundle:").append(bundle).toString() != null ? bundle.toString() : "");
        if (bundle != null) {
            d.a("ARunAction", "Service bundle - " + bundle.toString());
            if ("cn.jpush.android.intent.REPORT".equals(bundle.getString(AuthActivity.ACTION_KEY))) {
                e.a(context, bundle.getString("report"), bundle.getString("report.extra.info"));
                return;
            }
            return;
        }
        d.c("ARunAction", "onRunAction bundle is null");
    }

    public void onEvent(Context context, long j, int i) {
    }
}
