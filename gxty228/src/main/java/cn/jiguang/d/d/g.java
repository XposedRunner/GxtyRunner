package cn.jiguang.d.d;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import cn.jiguang.d.a.a;
import cn.jiguang.e.d;

public final class g {
    private static boolean a = false;
    private static Handler b;
    private static g d;
    private Context c;

    public static g a() {
        if (d == null) {
            d = new g();
        }
        return d;
    }

    public static void b() {
        if (b != null && !b.hasMessages(8000)) {
            b.sendEmptyMessageDelayed(8000, (long) (a.i() * 1000));
        }
    }

    public static void b(Context context) {
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("rtc_delay", 0);
            bundle.putString("rtc", "rtc");
            j.a().b(context, "intent.RTC", bundle);
        } catch (Throwable th) {
            d.h("HeartBeatHelper", "sendHeartBeat error:" + th.getMessage());
        }
    }

    public static void c() {
        a = false;
        try {
            if (b != null) {
                b.removeCallbacksAndMessages(null);
                b.getLooper().quit();
            }
            b = null;
        } catch (Throwable th) {
            d.g("HeartBeatHelper", "#unexception - stop failed :" + th.getMessage());
        }
    }

    public final void a(Context context) {
        if (!a) {
            if (context == null) {
                d.h("HeartBeatHelper", "init failed,context is null ");
                return;
            }
            a = true;
            this.c = context;
            try {
                HandlerThread handlerThread = new HandlerThread("JHeartBeatHelper");
                handlerThread.start();
                b = new h(this, handlerThread.getLooper());
                b();
            } catch (Throwable th) {
                d.i("HeartBeatHelper", "init jheart beat failed - error:" + th);
            }
        }
    }
}
