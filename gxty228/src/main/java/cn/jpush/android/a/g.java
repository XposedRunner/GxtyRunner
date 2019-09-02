package cn.jpush.android.a;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.net.HttpResponse;
import cn.jpush.android.d.e;
import cn.jpush.android.data.a;
import cn.jpush.android.data.b;
import org.json.JSONObject;

public final class g {
    public static a a(Context context, String str, String str2, String str3, String str4) {
        e.c("ProtocolHelper", "action:preParseOriginalMsgMessage - originalJson:\n" + str);
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        } else if (TextUtils.isEmpty(str)) {
            e.i("ProtocolHelper", "Empty original json txt");
            d.a("NO MSGID", 996, null, context);
            return null;
        } else {
            JSONObject a = a(context, "NO_MSGID", str);
            if (a == null) {
                e.c("ProtocolHelper", "topJsonObject == null");
                return null;
            }
            int optInt;
            CharSequence optString = a.optString("msg_id", "");
            if (TextUtils.isEmpty(optString)) {
                optString = a.optString("ad_id", "");
            }
            if (!TextUtils.isEmpty(optString)) {
                CharSequence charSequence = optString;
            }
            e.c("ProtocolHelper", "preParseOriginalMsgMessage msgId = " + str4);
            boolean z = a.optInt("n_only", 0) == 1;
            if (z) {
                optInt = a.optInt("n_builder_id", 0);
            } else {
                optInt = 0;
            }
            a aVar = new a();
            aVar.c = str4;
            aVar.a = a;
            aVar.b = a.optInt("show_type", 3);
            aVar.f = z;
            aVar.g = optInt;
            aVar.h = a.optInt("notificaion_type", 0);
            aVar.j = a.optString("message", "");
            aVar.k = a.optString("content_type", "");
            aVar.m = a.optString("title", "");
            aVar.n = a.optString("extras", "");
            aVar.o = str2;
            aVar.p = str3;
            aVar.d = a.optString("override_msg_id", "");
            return aVar;
        }
    }

    public static void a(Context context, a aVar) {
        e.a("ProtocolHelper", "action:parseMsgMessage");
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        int i = aVar.b;
        JSONObject jSONObject = aVar.a;
        String str = aVar.c;
        if (i == 3 || i == 4) {
            jSONObject = a(context, str, jSONObject, "m_content");
            if (jSONObject == null) {
                e.g("ProtocolHelper", "The secondJsonObject is null!");
                return;
            }
            int optInt = jSONObject.optInt("ad_t", -1);
            if (optInt == 0) {
                b gVar = new cn.jpush.android.data.g();
                gVar.c = str;
                gVar.b = i;
                gVar.q = optInt;
                gVar.i = aVar.i;
                gVar.f = aVar.f;
                gVar.g = aVar.g;
                gVar.o = aVar.o;
                gVar.d = aVar.d;
                gVar.h = aVar.h;
                boolean a = gVar.a(context, jSONObject);
                e.a("ProtocolHelper", "Entity.parse the second json object over.");
                if (a) {
                    gVar.a(context);
                    e.a("ProtocolHelper", "ShowEntity.process over.");
                    return;
                }
                e.g("ProtocolHelper", "Push message parsing failed. Give up processing.");
                return;
            }
            e.g("ProtocolHelper", "Unknow msg type ad_t = " + optInt);
            d.a(str, 996, null, context);
            return;
        }
        e.c("ProtocolHelper", "Unknown MSG protocol version. Give up - " + i);
        d.a(str, 996, null, context);
    }

    public static void a(final Context context, String str) {
        e.a("ProtocolHelper", "action:parseOriginalMsgMessage - originalJson:\n" + str);
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        } else if (TextUtils.isEmpty(str)) {
            e.i("ProtocolHelper", "Empty original json txt");
        } else {
            JSONObject a = a(context, "NO MSGID", str);
            if (a != null) {
                String optString = a.optString("msg_id", "");
                if (TextUtils.isEmpty(optString)) {
                    optString = a.optString("ad_id", "");
                }
                int optInt = a.optInt("show_type", -1);
                if (optInt == 2) {
                    final String trim = a.optString("m_content", "").trim();
                    if (a(trim)) {
                        e.a("ProtocolHelper", "action:loadMsgJsonFromUrl - " + trim);
                        if (context == null) {
                            throw new IllegalArgumentException("NULL context");
                        }
                        new Thread() {
                            public final void run() {
                                String responseBody;
                                Object obj = null;
                                int i = 0;
                                while (i < 4) {
                                    i++;
                                    HttpResponse a = cn.jpush.android.b.a.a(trim, 5, 8000);
                                    if (a != null && a.getResponseCode() == 200) {
                                        obj = 1;
                                        responseBody = a.getResponseBody();
                                        break;
                                    }
                                }
                                responseBody = null;
                                if (obj == null || TextUtils.isEmpty(responseBody)) {
                                    d.a(optString, 1021, cn.jpush.android.d.a.a(context, trim), context);
                                    d.a(optString, 996, null, context);
                                    e.c("ProtocolHelper", "Failed to load json from url");
                                    return;
                                }
                                g.a(context, responseBody);
                            }
                        }.start();
                        return;
                    }
                    e.c("ProtocolHelper", "Failed to get json from url becauseof invalid url - " + trim);
                    d.a(optString, 996, null, context);
                    return;
                }
                if (optInt == 1) {
                    a = a(context, optString, a, "m_content");
                } else {
                    a = null;
                }
                if (a != null) {
                    int optInt2 = a.optInt("ad_t", -1);
                    switch (optInt2) {
                        case 0:
                            b gVar = new cn.jpush.android.data.g();
                            boolean a2 = gVar.a(context, a);
                            e.a("ProtocolHelper", "Parse end");
                            gVar.c = optString;
                            gVar.b = optInt;
                            gVar.q = optInt2;
                            if (a2) {
                                gVar.a(context);
                                e.a("ProtocolHelper", "Process end");
                                return;
                            }
                            e.g("ProtocolHelper", "Push message parsing failed. Give up processing.");
                            return;
                        default:
                            e.g("ProtocolHelper", "Unknow msg type - " + optInt2);
                            d.a(optString, 996, null, context);
                            return;
                    }
                }
            }
        }
    }

    private static JSONObject a(Context context, String str, String str2) {
        try {
            return new JSONObject(str2);
        } catch (Throwable e) {
            e.e("ProtocolHelper", "parse json error", e);
            d.a(str, 996, null, context);
            return null;
        }
    }

    public static JSONObject a(Context context, String str, JSONObject jSONObject, String str2) {
        JSONObject jSONObject2 = null;
        if (jSONObject == null) {
            e.g("ProtocolHelper", "NULL json object");
            d.a(str, 996, null, context);
        } else if (TextUtils.isEmpty(str2)) {
            e.g("ProtocolHelper", "Empty json name to get");
        } else {
            try {
                if (!jSONObject.isNull(str2)) {
                    jSONObject2 = jSONObject.getJSONObject(str2);
                }
            } catch (Throwable e) {
                e.e("ProtocolHelper", "get json object error", e);
                d.a(str, 996, null, context);
            }
        }
        return jSONObject2;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String trim = str.trim();
        boolean matches = trim.matches("^[http|https]+://.*");
        if (matches) {
            return matches;
        }
        e.g("ProtocolHelper", "Invalid url - " + trim);
        return matches;
    }
}
