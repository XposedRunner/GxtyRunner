package cn.jpush.android.a;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.d.e;
import cn.jpush.android.service.JPushMessageReceiver;
import cn.jpush.android.service.d;

public class a {
    private static a a;
    private Handler b;

    private class a implements Runnable {
        final /* synthetic */ a a;
        private Context b;
        private JPushMessageReceiver c;
        private Intent d;

        public a(a aVar, Context context, JPushMessageReceiver jPushMessageReceiver, Intent intent) {
            this.a = aVar;
            this.b = context;
            this.c = jPushMessageReceiver;
            this.d = intent;
        }

        public final void run() {
            if (this.d == null) {
                e.g("JMessageReceiverHelper", "intent was null");
                return;
            }
            if ("cn.jpush.android.intent.RECEIVE_MESSAGE".equals(this.d.getAction())) {
                int intExtra = this.d.getIntExtra("message_type", -1);
                JPushMessage jPushMessage = null;
                if (1 == intExtra || 2 == intExtra) {
                    jPushMessage = d.a().a(this.d);
                } else if (3 == intExtra) {
                    f.a();
                    jPushMessage = f.a(this.d);
                }
                e.c("JMessageReceiverHelper", "messageType:" + intExtra + ",jPushMessage:" + jPushMessage);
                if (jPushMessage == null) {
                    e.g("JMessageReceiverHelper", "parse tagalias message failed");
                    return;
                } else if (intExtra == 1) {
                    if (jPushMessage.isTagCheckOperator()) {
                        this.c.onCheckTagOperatorResult(this.b, jPushMessage);
                        return;
                    } else {
                        this.c.onTagOperatorResult(this.b, jPushMessage);
                        return;
                    }
                } else if (intExtra == 2) {
                    this.c.onAliasOperatorResult(this.b, jPushMessage);
                    return;
                } else if (intExtra == 3) {
                    this.c.onMobileNumberOperatorResult(this.b, jPushMessage);
                    return;
                } else {
                    e.g("JMessageReceiverHelper", "unsupport message type");
                    return;
                }
            }
            e.g("JMessageReceiverHelper", "unsupport action type");
        }
    }

    private a() {
        try {
            HandlerThread handlerThread = new HandlerThread("MessageReceiver");
            handlerThread.start();
            this.b = new Handler(handlerThread.getLooper());
        } catch (Throwable th) {
            e.g("JMessageReceiverHelper", "create handler failed,error:" + th);
            this.b = new Handler();
        }
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    public final void a(Context context, JPushMessageReceiver jPushMessageReceiver, Intent intent) {
        this.b.post(new a(this, context, jPushMessageReceiver, intent));
    }
}
