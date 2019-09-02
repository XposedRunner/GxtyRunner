package com.loc;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import com.amap.api.location.APSServiceBase;

/* compiled from: ApsServiceCore */
public class cw implements APSServiceBase {
    cv a = null;
    Context b = null;
    Messenger c = null;

    public cw(Context context) {
        this.b = context.getApplicationContext();
        this.a = new cv(this.b);
    }

    public IBinder onBind(Intent intent) {
        this.a.b(intent);
        this.a.a(intent);
        this.c = new Messenger(this.a.b());
        return this.c.getBinder();
    }

    public void onCreate() {
        try {
            cv.f();
            this.a.j = ct.b();
            this.a.k = ct.a();
            this.a.a();
        } catch (Throwable th) {
            cl.a(th, "ApsServiceCore", "onCreate");
        }
    }

    public void onDestroy() {
        try {
            if (this.a != null) {
                this.a.b().sendEmptyMessage(11);
            }
        } catch (Throwable th) {
            cl.a(th, "ApsServiceCore", "onDestroy");
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 0;
    }
}
