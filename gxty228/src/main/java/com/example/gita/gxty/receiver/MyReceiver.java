package com.example.gita.gxty.receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jpush.android.api.JPushInterface;
import com.example.gita.gxty.ram.StartActivity;
import com.example.gita.gxty.utils.h;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class MyReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle extras = intent.getExtras();
            h.a("[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + a(extras));
            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                h.a("[MyReceiver] 接收Registration Id : " + extras.getString(JPushInterface.EXTRA_REGISTRATION_ID));
            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                h.a("[MyReceiver] 接收到推送下来的自定义消息: " + extras.getString(JPushInterface.EXTRA_MESSAGE));
            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                h.a((Object) "[MyReceiver] 接收到推送下来的通知");
                h.a("[MyReceiver] 接收到推送下来的通知的ID: " + extras.getInt(JPushInterface.EXTRA_NOTIFICATION_ID));
            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                h.a((Object) "[MyReceiver] 用户点击打开了通知");
                a(context);
            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                h.a("[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + extras.getString(JPushInterface.EXTRA_EXTRA));
            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                h.e("[MyReceiver]" + intent.getAction() + " connected state change to " + intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false));
            } else {
                h.a("[MyReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {
        }
    }

    private static String a(Bundle bundle) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : bundle.keySet()) {
            if (str.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                stringBuilder.append("\nkey:" + str + ", value:" + bundle.getInt(str));
            } else if (str.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                stringBuilder.append("\nkey:" + str + ", value:" + bundle.getBoolean(str));
            } else if (!str.equals(JPushInterface.EXTRA_EXTRA)) {
                stringBuilder.append("\nkey:" + str + ", value:" + bundle.get(str));
            } else if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                h.c("This message has no Extra data");
            } else {
                try {
                    JSONObject jSONObject = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String str2 = (String) keys.next();
                        stringBuilder.append("\nkey:" + str + ", value: [" + str2 + " - " + jSONObject.optString(str2) + "]");
                    }
                } catch (JSONException e) {
                    try {
                        h.b("Get message extra JSON error!");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    public void a(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(new ComponentName(context.getPackageName(), StartActivity.class.getName()));
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setFlags(270532608);
            context.startActivity(intent);
        } catch (Exception e) {
            h.a(e);
        }
    }
}
