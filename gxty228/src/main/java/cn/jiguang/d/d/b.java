package cn.jiguang.d.d;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import cn.jiguang.api.JAction;
import cn.jiguang.api.JActionExtra;
import cn.jiguang.api.SdkType;
import cn.jiguang.d.a;
import cn.jiguang.d.b.f;
import cn.jiguang.d.e.a.a.c;
import cn.jiguang.e.d;
import cn.jiguang.g.k;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public final class b {
    public static HashMap<String, JAction> a = new HashMap();
    public static HashMap<String, JActionExtra> b = new HashMap();
    private static volatile b c;
    private static final Object d = new Object();

    static {
        a(a.a, f.class.getName());
    }

    private b() {
    }

    public static b a() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new b();
                }
            }
        }
        return c;
    }

    private static Object a(JActionExtra jActionExtra, Context context, int i, String str, int i2) {
        if (jActionExtra == null) {
            return null;
        }
        if (i2 != 0) {
            return i2 == 1 ? jActionExtra.beforLogin(context, i, str) : null;
        } else {
            try {
                return jActionExtra.beforRegister(context, i, str);
            } catch (Throwable th) {
                d.h("ActionManager", "#unexcepted- invoke method error:" + th);
                return null;
            }
        }
    }

    public static void a(Context context, long j, int i) {
        for (Entry value : a.entrySet()) {
            JAction jAction = (JAction) value.getValue();
            if (jAction != null) {
                jAction.onEvent(context, j, i);
            }
        }
    }

    public static void a(Context context, c cVar, ByteBuffer byteBuffer) {
        if (cVar == null) {
            d.g("ActionManager", "Action - dispatchMessage unexcepted - head was null");
            return;
        }
        for (Entry entry : a.entrySet()) {
            JAction jAction = (JAction) entry.getValue();
            if (jAction != null && jAction.isSupportedCMD(cVar.a())) {
                cVar.a(Long.valueOf(jAction.dispatchMessage(context, f.a.get(), cVar.a(), cVar, byteBuffer)));
                cn.jiguang.d.b.d.a().a((String) entry.getKey(), (Object) cVar);
                byteBuffer.clear();
            }
        }
    }

    public static void a(Context context, String str, long j, Bundle bundle, Handler handler) {
        JAction jAction = (JAction) a.get(str);
        if (jAction != null) {
            jAction.onActionRun(context, j, bundle, handler);
        }
    }

    public static void a(Context context, String str, Object obj) {
        d.a("ActionManager", "onSended type:" + str);
        if (k.a(str)) {
            for (Entry value : a.entrySet()) {
                JAction jAction = (JAction) value.getValue();
                cn.jiguang.d.b.d.a();
                jAction.handleMessage(context, cn.jiguang.d.b.d.g(), obj);
            }
            return;
        }
        jAction = (JAction) a.get(str);
        if (jAction != null) {
            cn.jiguang.d.b.d.a();
            jAction.handleMessage(context, cn.jiguang.d.b.d.g(), obj);
        }
    }

    public static void a(String str, String str2) {
        d.a("ActionManager", "addAction type:" + str + ",action:" + str2);
        if (!TextUtils.isEmpty(str2)) {
            if (a.containsKey(str)) {
                d.c("ActionManager", "has same type action");
                return;
            }
            try {
                Object newInstance = Class.forName(str2).newInstance();
                if (newInstance instanceof JAction) {
                    a.put(str, (JAction) newInstance);
                }
            } catch (Throwable th) {
                d.h("ActionManager", "#unexcepted - instance " + str2 + " class failed:" + th);
            }
        }
    }

    public static boolean a(int i) {
        for (Entry entry : b.entrySet()) {
            JActionExtra jActionExtra = (JActionExtra) entry.getValue();
            if (jActionExtra != null) {
                try {
                    d.d("ActionManager", "isAllowAction actionType:" + 0 + ",sdktype:" + ((String) entry.getKey()) + ",action:" + jActionExtra.checkAction(0));
                    if (!jActionExtra.checkAction(0)) {
                        return false;
                    }
                } catch (Throwable th) {
                    d.g("ActionManager", "isAllowAction error:" + th.getMessage());
                }
            }
        }
        return true;
    }

    public static void b(Context context, long j, int i) {
        for (Entry value : a.entrySet()) {
            JAction jAction = (JAction) value.getValue();
            if (jAction != null) {
                jAction.dispatchTimeOutMessage(context, f.a.get(), j, i);
            }
        }
    }

    public static void b(String str, String str2) {
        d.a("ActionManager", "addActionExtra type:" + str + ",actionExtra:" + str2);
        if (b.containsKey(str)) {
            d.c("ActionManager", "has same type action extra");
            return;
        }
        try {
            Object newInstance = Class.forName(str2).newInstance();
            if (newInstance instanceof JActionExtra) {
                b.put(str, (JActionExtra) newInstance);
            } else {
                d.c("ActionManager", "addActionExtra failed,the actionName is not a JActionExtra object");
            }
        } catch (Throwable th) {
            d.h("ActionManager", "#unexcepted - instance " + str2 + " class failed:" + th);
        }
    }

    public static IBinder c(String str, String str2) {
        JAction jAction = (JAction) a.get(str);
        return jAction != null ? jAction.getBinderByType(str2) : null;
    }

    public static String d(String str, String str2) {
        if (a.containsKey(str)) {
            JAction jAction = (JAction) a.get(str);
            if (jAction == null) {
                d.a("ActionManager", str + " sdk action is null");
                return str2;
            } else if (!TextUtils.isEmpty(jAction.getSdkVersion())) {
                return jAction.getSdkVersion();
            } else {
                d.a("ActionManager", str + " sdk action sdkversion:" + jAction.getSdkVersion());
                return str2;
            }
        }
        d.a("ActionManager", str + " sdk is null");
        return str2;
    }

    public final ArrayList<Object> a(Context context, String str, int i, String str2, int i2) {
        ArrayList<Object> arrayList = new ArrayList();
        Object a;
        if (k.a(str)) {
            for (Entry value : b.entrySet()) {
                a = a((JActionExtra) value.getValue(), context, 19, str2, 1);
                if (a != null) {
                    arrayList.add(a);
                }
            }
        } else {
            a = a((JActionExtra) b.get(str), context, 19, str2, 1);
            if (a != null) {
                arrayList.add(a);
            }
        }
        return arrayList;
    }

    public final boolean a(JSONObject jSONObject) {
        if (jSONObject == null) {
            d.g("ActionManager", "wrapSdkVersionInfo failed ,container is null");
            return false;
        }
        try {
            jSONObject.put("sdk_ver", d(SdkType.JPUSH.name(), ""));
            jSONObject.put("core_sdk_ver", d(SdkType.JCORE.name(), ""));
            jSONObject.put("share_sdk_ver", d(SdkType.JSHARE.name(), ""));
            jSONObject.put("statistics_sdk_ver", d(SdkType.JANALYTICS.name(), ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }
}
