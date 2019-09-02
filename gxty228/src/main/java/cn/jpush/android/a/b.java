package cn.jpush.android.a;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import cn.jiguang.api.JAction;
import cn.jiguang.api.JResponse;
import cn.jpush.a.d;
import cn.jpush.android.a;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.c.f;
import cn.jpush.android.c.g;
import cn.jpush.android.d.e;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.service.c;
import cn.jpush.client.android.BuildConfig;
import java.nio.ByteBuffer;

public class b implements JAction {
    public long dispatchMessage(Context context, long j, int i, Object obj, ByteBuffer byteBuffer) {
        e.c("JPushDataAction", "Action - dispatchMessage");
        if (a.a(context)) {
            JResponse bVar;
            switch (i) {
                case 3:
                    bVar = new cn.jpush.a.b(obj, byteBuffer);
                    break;
                case 10:
                case 28:
                case 29:
                    bVar = new d(obj, byteBuffer);
                    break;
                case 26:
                case 27:
                    bVar = new cn.jpush.a.a(obj, byteBuffer);
                    break;
                default:
                    e.g("ContentValues", "Unknown command for parsing inbound.");
                    bVar = null;
                    break;
            }
            if (ServiceInterface.c(context)) {
                e.c("JPushDataAction", "JPush was stoped");
                if (!(bVar == null || !(bVar instanceof cn.jpush.a.b) || ((cn.jpush.a.b) bVar).a() == 20)) {
                    return -1;
                }
            }
            if (bVar != null) {
                e.d("JPushDataAction", "response:" + bVar.toString());
                switch (bVar.getCommand()) {
                    case 3:
                        return h.a(a.e, j, bVar);
                    case 10:
                        e.g("JPushDataAction", "Should not exist - ignore tag alias response.");
                        break;
                    case 26:
                        f.a().a(context, bVar.getRid().longValue(), bVar.code);
                        break;
                    case 27:
                        if (bVar.code != 0) {
                            f.a().a(context, bVar.getRid().longValue(), bVar.code);
                            break;
                        }
                        f.a().a(context, bVar.getRid().longValue());
                        break;
                    case 28:
                    case 29:
                        return k.a(context, ((d) bVar).a(), bVar.getCommand() == 28 ? 1 : 2, bVar.getRid().longValue());
                    default:
                        e.g("JPushDataAction", "Unhandled response command - " + bVar.getCommand());
                        break;
                }
            }
            if (bVar != null) {
                return bVar.getRid().longValue();
            }
            return -1;
        }
        e.g("JPushDataAction", "Action - onEvent , JPush init failed");
        return -1;
    }

    public void onActionRun(Context context, long j, Bundle bundle, Object obj) {
        e.d("JPushDataAction", "Action - onActionRun");
        if (a.a(context)) {
            c.a(context).a(bundle, (Handler) obj);
        } else {
            e.g("JPushDataAction", "Action - onActionRun , JPush init failed");
        }
    }

    public boolean isSupportedCMD(int i) {
        return i == 3 || i == 10 || i == 27 || i == 28 || i == 29 || i == 26;
    }

    public void onEvent(Context context, long j, int i) {
        if (a.a(context)) {
            switch (i) {
                case 0:
                    return;
                case 1:
                    g.a().d(context);
                    return;
                case 19:
                    c.a(context).a();
                    return;
                default:
                    return;
            }
        }
        e.g("JPushDataAction", "Action - onEvent , jpush init failed");
    }

    public void handleMessage(Context context, long j, Object obj) {
        if (!a.a(context)) {
            e.g("JPushDataAction", "Action - handleMessage , jpush init failed");
        } else if (obj == null) {
        } else {
            if (obj instanceof Bundle) {
                c.a(context);
                Bundle bundle = (Bundle) obj;
                if (bundle == null) {
                    e.a("PushServiceCore", "Action - handleMessage unexcepted, bundle was null");
                    return;
                }
                e.a("PushServiceCore", "handleMessage:" + bundle.toString());
                bundle.getInt("what");
                e.a("PushServiceCore", "Unexpected: unhandled msg - " + bundle.toString());
            } else if (obj instanceof Intent) {
                cn.jpush.android.service.b.a();
                Intent intent = (Intent) obj;
                if (intent == null) {
                    e.h("PushReceiverCore", "Received null intent broadcast. Give up processing.");
                    return;
                }
                try {
                    String action = intent.getAction();
                    e.d("PushReceiverCore", "onReceive - " + action);
                    if (a.a(context.getApplicationContext()) && action != null) {
                        if ("cn.jpush.android.intent.plugin.platform.REFRESSH_REGID".equals(action)) {
                            g.a().e(context);
                        } else if (action.startsWith(JPushInterface.ACTION_NOTIFICATION_RECEIVED_PROXY)) {
                            try {
                                if (ServiceInterface.d(context)) {
                                    e.g("PushReceiverCore", "push has stoped");
                                    return;
                                }
                                e.c("PushReceiverCore", "ACTION_NOTIFICATION_RECEIVED_PROXY   notification_type = " + intent.getIntExtra("notificaion_type", 0));
                                Object stringExtra = intent.getStringExtra("message");
                                if (TextUtils.isEmpty(stringExtra)) {
                                    e.h("PushReceiverCore", "Got an empty notification, don't show it!");
                                    return;
                                }
                                cn.jpush.android.data.a a = g.a(context, stringExtra, intent.getStringExtra("appId"), intent.getStringExtra("senderId"), intent.getStringExtra("msg_id"));
                                if (a == null) {
                                    e.g("PushReceiverCore", "Transform notification content to BasicEntity failed!");
                                    return;
                                }
                                a.i = true;
                                g.a(context, a);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (!action.equals("android.intent.action.PACKAGE_ADDED")) {
                            if (action.equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")) {
                                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                                if (networkInfo == null) {
                                    e.g("PushReceiverCore", "Not found networkInfo");
                                    return;
                                }
                                e.c("PushReceiverCore", "Connection state changed to - " + networkInfo.toString());
                                if (2 == networkInfo.getType() || 3 == networkInfo.getType()) {
                                    e.c("PushReceiverCore", "MMS or SUPL network state change, to do nothing!");
                                } else if (intent.getBooleanExtra("noConnectivity", false)) {
                                    e.c("PushReceiverCore", "No any network is connected");
                                } else if (State.CONNECTED == networkInfo.getState()) {
                                    e.c("PushReceiverCore", "Network is connected.");
                                } else if (State.DISCONNECTED == networkInfo.getState()) {
                                    e.c("PushReceiverCore", "Network is disconnected.");
                                } else {
                                    e.c("PushReceiverCore", "other network state - " + networkInfo.getState() + ". Do nothing.");
                                }
                            } else if (action.startsWith("cn.jpush.android.intent.NOTIFICATION_OPENED_PROXY")) {
                                cn.jpush.android.service.b.a(context, intent);
                            } else if (action.startsWith("cn.jpush.android.intent.NOTIFICATION_CLICK_ACTION_PROXY")) {
                                e.e("PushReceiverCore", "Click notification action with extra: " + intent.getExtras().getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA));
                                try {
                                    Intent intent2 = new Intent(JPushInterface.ACTION_NOTIFICATION_CLICK_ACTION);
                                    intent2.putExtras(intent.getExtras());
                                    intent2.addCategory(context.getPackageName());
                                    intent2.setPackage(context.getPackageName());
                                    context.sendBroadcast(intent2, context.getPackageName() + ".permission.JPUSH_MESSAGE");
                                } catch (Throwable th) {
                                    e.h("PushReceiverCore", "Click notification sendBroadcast :" + th.getMessage());
                                }
                            }
                        }
                    }
                } catch (NullPointerException e2) {
                    e.h("PushReceiverCore", "Received no action intent broadcast. Give up processing.");
                }
            } else {
                e.d("JPushDataAction", "handleMessage unknown object ");
            }
        }
    }

    public IBinder getBinderByType(String str) {
        return null;
    }

    public String getSdkVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public void dispatchTimeOutMessage(Context context, long j, long j2, int i) {
        e.c("JPushDataAction", "dispatchTimeOutMessage");
        if (a.a(context)) {
            switch (i) {
                case 10:
                case 28:
                case 29:
                    e.a(context).a(j2);
                    return;
                case 26:
                    f.a().a(context, j2, JPushInterface.a.c);
                    return;
                case 27:
                    f.a().b(context, j2);
                    return;
                default:
                    e.c("JPushDataAction", "unhandle command");
                    return;
            }
        }
        e.g("JPushDataAction", "Action - dispatchTimeOutMessage , jpush init failed");
    }
}
