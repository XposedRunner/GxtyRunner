package cn.jpush.android.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.b.b;
import cn.jiguang.d.b.a;
import cn.jiguang.d.d.j;
import cn.jiguang.d.h.f;
import cn.jiguang.e.d;
import cn.jiguang.g.n;

public class PushService extends Service {
    private static final String TAG = "PushService";
    private b mBinder = null;
    private BroadcastReceiver mPowerBroadcastReceiver = null;
    private BroadcastReceiver receiver;

    public IBinder onBind(Intent intent) {
        d.c(TAG, "action - onBind");
        a.a(this);
        return this.mBinder;
    }

    public void onCreate() {
        d.e(TAG, "onCreate()");
        d.a(TAG, "Service main thread - threadId:" + Thread.currentThread().getId());
        n nVar = new n();
        if (JCoreInterface.init(this, false)) {
            a.a(this);
            this.mBinder = new cn.jiguang.g.a.a();
            cn.jiguang.g.a.l(getApplicationContext());
            cn.jiguang.d.b.d.a().a((Context) this);
            cn.jiguang.d.b.d.a().a((Service) this);
            try {
                f.a().a(getApplicationContext(), true);
            } catch (OutOfMemoryError e) {
                d.g(TAG, "Fail to start other app caused by OutOfMemory.");
            }
            try {
                IntentFilter intentFilter = new IntentFilter();
                if (this.receiver == null) {
                    this.receiver = new PushReceiver();
                }
                intentFilter.addAction("android.intent.action.USER_PRESENT");
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                registerReceiver(this.receiver, intentFilter);
            } catch (Throwable th) {
                d.i(TAG, "registerReceiver fail:" + th);
            }
            setDozePowerReceiver();
            nVar.a(TAG, "PushService onCreate");
            super.onCreate();
            return;
        }
        d.h(TAG, "onCreate:JCoreInterface init failed");
    }

    public void onDestroy() {
        super.onDestroy();
        d.c(TAG, "onDestroy - processId:" + Process.myPid());
        cn.jiguang.d.b.d a = cn.jiguang.d.b.d.a();
        getApplicationContext();
        a.c();
        try {
            if (this.receiver != null) {
                unregisterReceiver(this.receiver);
            }
            if (this.mPowerBroadcastReceiver != null) {
                unregisterReceiver(this.mPowerBroadcastReceiver);
            }
        } catch (Throwable th) {
            d.i(TAG, "unregisterReceiver fail:" + th);
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        String action;
        Object th;
        Bundle bundle = null;
        d.d(TAG, "onStartCommand - intent:" + intent + ", pkg:" + cn.jiguang.d.a.c + ", connection:" + cn.jiguang.d.b.f.a.get());
        a.a(this);
        if (JCoreInterface.init(this, false)) {
            if (intent != null) {
                try {
                    action = intent.getAction();
                    try {
                        bundle = intent.getExtras();
                    } catch (Throwable th2) {
                        th = th2;
                        d.i(TAG, "get intent extras failed, error:" + th);
                        if (bundle != null) {
                            try {
                                d.a(TAG, "Service bundle - " + bundle.toString());
                            } catch (Throwable th3) {
                                d.h(TAG, "PushService onStartCommand error:" + th3.getMessage());
                            }
                        }
                        j.a().a((Context) this, action, bundle);
                        return 1;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    action = bundle;
                    d.i(TAG, "get intent extras failed, error:" + th);
                    if (bundle != null) {
                        d.a(TAG, "Service bundle - " + bundle.toString());
                    }
                    j.a().a((Context) this, action, bundle);
                    return 1;
                }
            }
            Object obj = bundle;
            if (bundle != null) {
                d.a(TAG, "Service bundle - " + bundle.toString());
            }
            if (!(action == null || bundle == null)) {
                j.a().a((Context) this, action, bundle);
            }
        } else {
            d.h(TAG, "onStartCommand:JCoreInterface init failed");
        }
        return 1;
    }

    public boolean onUnbind(Intent intent) {
        d.i(TAG, "action - onUnBind");
        return super.onUnbind(intent);
    }

    public void setDozePowerReceiver() {
        try {
            if (cn.jiguang.d.a.d.o(this)) {
                if (this.mPowerBroadcastReceiver != null) {
                    d.c(TAG, "unregister doze and power receiver");
                    unregisterReceiver(this.mPowerBroadcastReceiver);
                    this.mPowerBroadcastReceiver = null;
                    return;
                }
                d.c(TAG, "doze and power has unregister");
            } else if (this.mPowerBroadcastReceiver == null) {
                d.c(TAG, "register doze and power receiver");
                this.mPowerBroadcastReceiver = new PushReceiver();
                IntentFilter intentFilter = new IntentFilter();
                if (VERSION.SDK_INT >= 21) {
                    intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
                }
                if (VERSION.SDK_INT >= 23) {
                    intentFilter.addAction("android.os.action.DEVICE_IDLE_MODE_CHANGED");
                }
                registerReceiver(this.mPowerBroadcastReceiver, intentFilter);
            } else {
                d.c(TAG, "doze and power receiver has register");
            }
        } catch (Throwable th) {
            d.i(TAG, "setDozePowerReceiver fail:" + th);
        }
    }
}
