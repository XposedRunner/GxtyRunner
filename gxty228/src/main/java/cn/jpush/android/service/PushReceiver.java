package cn.jpush.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.jiguang.a.c.c;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.d.d.b;
import cn.jiguang.d.d.j;
import cn.jiguang.e.d;
import cn.jiguang.g.a;
import cn.jiguang.g.i;
import cn.jiguang.g.k;
import java.lang.reflect.Method;

public class PushReceiver extends BroadcastReceiver {
    private static final String TAG = "PushReceiver";

    public void onReceive(Context context, Intent intent) {
        boolean z = true;
        int i = 0;
        if (intent == null) {
            d.h(TAG, "Received null intent broadcast. Give up processing.");
            return;
        }
        try {
            String action = intent.getAction();
            d.d(TAG, "onReceive - " + action);
            if (TextUtils.isEmpty(action)) {
                d.h(TAG, "Received action is null");
                return;
            }
            try {
                if (action.equals("cn.jpush.android.intent.NOTIFICATION_OPENED_PROXY") && intent.getBooleanExtra("debug_notification", false)) {
                    String stringExtra = intent.getStringExtra("toastText");
                    if (!TextUtils.isEmpty(stringExtra)) {
                        Toast makeText = Toast.makeText(context, stringExtra, 0);
                        try {
                            View view = makeText.getView();
                            if (view instanceof LinearLayout) {
                                view = ((LinearLayout) view).getChildAt(0);
                                if (view instanceof TextView) {
                                    TextView textView = (TextView) view;
                                    if (!k.a(stringExtra)) {
                                        textView.setText(stringExtra);
                                    }
                                    textView.setTextSize(13.0f);
                                }
                            }
                        } catch (Exception e) {
                        }
                        makeText.show();
                        return;
                    }
                }
                if (JCoreInterface.init(context.getApplicationContext(), false)) {
                    if (action.equals("android.intent.action.BOOT_COMPLETED") || action.equals("android.intent.action.USER_PRESENT")) {
                        if (!action.equals("android.intent.action.USER_PRESENT")) {
                            i = 500;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putInt("rtc_delay", i);
                        bundle.putString("rtc", "rtc");
                        j.a().b(context, "intent.RTC", bundle);
                    } else if (action.equals("android.intent.action.PACKAGE_ADDED") || action.equals("android.intent.action.PACKAGE_REMOVED")) {
                        String dataString = intent.getDataString();
                        if (dataString == null) {
                            d.h(TAG, action + ": Get no data from intent.");
                            return;
                        } else if (dataString.trim().length() <= 8 || !dataString.startsWith("package:")) {
                            d.h(TAG, "Get wrong data string from intent: " + dataString);
                            return;
                        } else {
                            dataString = dataString.substring(8);
                            if (action.equals("android.intent.action.PACKAGE_ADDED")) {
                                c.a(context, i.a(dataString, cn.jiguang.g.d.a(cn.jiguang.g.d.a(context, dataString))));
                            } else if (a.i(context.getApplicationContext(), dataString)) {
                                d.c(TAG, "replace app");
                            } else {
                                c.a(context, i.a(dataString));
                            }
                            c.a(context, dataString);
                        }
                    } else if (action.equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")) {
                        NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                        if (networkInfo == null) {
                            d.g(TAG, "Not found networkInfo");
                            return;
                        }
                        d.c(TAG, "Connection state changed to - " + networkInfo.toString());
                        if (2 == networkInfo.getType() || 3 == networkInfo.getType()) {
                            d.c(TAG, "MMS or SUPL network state change, to do nothing!");
                            return;
                        }
                        z = intent.getBooleanExtra("noConnectivity", false);
                        Bundle extras = intent.getExtras();
                        if (z) {
                            d.c(TAG, "No any network is connected");
                            extras.putBoolean("connection-state", false);
                        } else if (State.CONNECTED == networkInfo.getState()) {
                            d.c(TAG, "Network is connected.");
                            extras.putBoolean("connection-state", true);
                        } else if (State.DISCONNECTED == networkInfo.getState()) {
                            d.c(TAG, "Network is disconnected.");
                            extras.putBoolean("connection-state", false);
                        } else {
                            d.c(TAG, "other network state - " + networkInfo.getState() + ". Do nothing.");
                        }
                        j.a().b(context, "intent.CONNECTIVITY_CHANGE", extras);
                    } else if (action.equals("android.os.action.DEVICE_IDLE_MODE_CHANGED") || action.equals("android.os.action.POWER_SAVE_MODE_CHANGED")) {
                        PowerManager powerManager = (PowerManager) context.getSystemService("power");
                        if (powerManager != null) {
                            try {
                                Class cls = Class.forName("android.os.PowerManager");
                                if (cls != null) {
                                    Method declaredMethod;
                                    if (action.equals("android.os.action.DEVICE_IDLE_MODE_CHANGED")) {
                                        declaredMethod = cls.getDeclaredMethod("isDeviceIdleMode", new Class[0]);
                                        z = declaredMethod != null ? ((Boolean) declaredMethod.invoke(powerManager, new Object[0])).booleanValue() : true;
                                    } else if (action.equals("android.os.action.POWER_SAVE_MODE_CHANGED")) {
                                        declaredMethod = cls.getDeclaredMethod("isPowerSaveMode", new Class[0]);
                                        if (declaredMethod != null) {
                                            z = ((Boolean) declaredMethod.invoke(powerManager, new Object[0])).booleanValue();
                                        }
                                    }
                                    if (!z) {
                                        d.c(TAG, "doze or powersave mode exit.");
                                        Bundle bundle2 = new Bundle();
                                        bundle2.putInt("rtc_delay", 0);
                                        bundle2.putString("rtc", "rtc");
                                        j.a().b(context, "intent.RTC", bundle2);
                                    }
                                }
                            } catch (Throwable th) {
                                d.i(TAG, "handle DEVICE_IDLE_MODE_CHANGED or POWER_SAVE_MODE_CHANGED fail:" + th);
                            }
                        }
                    }
                    b.a();
                    b.a(context, intent.getStringExtra("sdktype"), (Object) intent);
                    return;
                }
                d.h(TAG, "onReceive :JCoreInterface init failed");
            } catch (Exception e2) {
                d.h(TAG, "onReceiver error:" + e2.getMessage());
            }
        } catch (NullPointerException e3) {
            d.h(TAG, "Received no action intent broadcast. Give up processing.");
        }
    }
}
