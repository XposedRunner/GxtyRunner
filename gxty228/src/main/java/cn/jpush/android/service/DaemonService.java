package cn.jpush.android.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.d.a;
import cn.jiguang.d.h.c;
import cn.jiguang.d.h.f;
import cn.jiguang.e.d;

public class DaemonService extends Service {
    private static final String TAG = "DaemonService";

    public class MyBinder extends Binder {
        public DaemonService getService() {
            return DaemonService.this;
        }
    }

    private void init() {
        try {
            super.onCreate();
            if (JCoreInterface.init(getApplicationContext(), false)) {
                JCoreInterface.register(getApplicationContext());
            } else {
                stopSelf();
            }
        } catch (Throwable th) {
            d.h(TAG, "DaemonService onCreate failed:" + th.getMessage());
        }
    }

    private void report(int i, boolean z, Bundle bundle) {
        if (!c.a((Context) this)) {
            d.c(TAG, "Not need report waked");
        } else if (bundle != null) {
            try {
                for (String str : bundle.keySet()) {
                    d.c(TAG, "key:" + str + ",value:" + bundle.get(str));
                }
                if (TextUtils.equals("true", bundle.getString("need_report"))) {
                    f.a().b().a(this, i, z, bundle.getString("from_package"), bundle.getString("from_uid"), bundle.getString("awake_sequence"));
                    return;
                }
                d.g(TAG, "wakeup is not from sdkWakeUp, give up report data.");
            } catch (Throwable th) {
                d.i(TAG, "report awake reslut error:" + th);
            }
        }
    }

    public IBinder onBind(Intent intent) {
        d.c(TAG, "onBind:" + a.l);
        report(2, a.l, intent.getExtras());
        init();
        return new MyBinder();
    }

    public void onCreate() {
        super.onCreate();
        d.c(TAG, "action onCreate");
    }

    public void onDestroy() {
        d.c(TAG, "action onDestroy");
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        d.c(TAG, "onStartCommand:" + a.l);
        if (intent != null) {
            report(1, a.l, intent.getExtras());
        } else {
            d.c(TAG, "Intent is null,give up report!");
        }
        init();
        return super.onStartCommand(intent, i, i2);
    }
}
