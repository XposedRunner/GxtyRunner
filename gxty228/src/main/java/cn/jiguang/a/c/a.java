package cn.jiguang.a.c;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import cn.jiguang.a.a.a.c;
import cn.jiguang.a.a.c.i;
import cn.jiguang.a.a.c.k;
import cn.jiguang.api.JResponse;
import cn.jiguang.api.utils.OutputDataUtil;
import cn.jiguang.d.g.b;
import cn.jiguang.d.h.f;
import cn.jiguang.d.h.h;
import cn.jiguang.e.d;
import cn.jiguang.net.HttpUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    private static String a(Context context, int i) {
        String str = null;
        if (i != 53) {
            return null;
        }
        JSONObject a;
        String e = cn.jiguang.d.a.a.e(context);
        d.c("CtrlMessageProcessor", "deviceInfo from sp:" + e);
        try {
            str = new JSONObject(e);
        } catch (JSONException e2) {
        }
        if (str == null) {
            b.d().a(context);
            a = cn.jiguang.a.a.c.b.a(context, b.d());
            d.c("CtrlMessageProcessor", "deviceInfo :" + e);
            JSONObject jSONObject = a;
        } else {
            Object obj = str;
        }
        try {
            b.d();
            byte c = b.c(context);
            b.d();
            Object d = b.d(context);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("rom_type", c);
            if (d == null) {
                d = "";
            }
            jSONObject2.put("regid", d);
            jSONObject.put("rom_info", jSONObject2);
            d.c("CtrlMessageProcessor", "get rom_info :" + jSONObject2);
        } catch (JSONException e3) {
        }
        a = new JSONObject();
        try {
            a.put("cmd", i);
            a.put("content", jSONObject);
        } catch (JSONException e4) {
        }
        str = a.toString();
        d.c("CtrlMessageProcessor", "prepare device report:" + str);
        return str;
    }

    private static JSONObject a(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            JSONObject jSONObject;
            JSONException jSONException = e;
            try {
                jSONObject = new JSONObject(cn.jiguang.d.g.a.a.b(str, ""));
            } catch (Exception e2) {
                jSONException.printStackTrace();
                jSONObject = null;
            }
            d.a("CtrlMessageProcessor", "jsonObject:" + jSONObject);
            return jSONObject;
        } catch (Exception e3) {
            return null;
        }
    }

    public static void a(int i) {
        try {
            if (cn.jiguang.d.a.e == null) {
                d.h("CtrlMessageProcessor", "processCtrlReportByCmd failed because JCore.mApplicationContext is null");
                return;
            }
            switch (i) {
                case 4:
                    c.a(cn.jiguang.d.a.e);
                    return;
                case 5:
                    Context context = cn.jiguang.d.a.e;
                    a(cn.jiguang.d.b.d.a().b());
                    return;
                case 6:
                case 21:
                case 22:
                    return;
                case 9:
                    c.b(cn.jiguang.d.a.e);
                    return;
                case 44:
                    k.a(cn.jiguang.d.a.e);
                    return;
                default:
                    d.c("CtrlMessageProcessor", "Unexpected: unknown push msg type -" + i);
                    return;
            }
            d.h("CtrlMessageProcessor", "processCtrlReport exception:" + e.getMessage());
        } catch (Exception e) {
            d.h("CtrlMessageProcessor", "processCtrlReport exception:" + e.getMessage());
        }
    }

    public static void a(Context context, Handler handler, long j, JResponse jResponse) {
        Object obj;
        d.a("CtrlMessageProcessor", "action - parseCtrlResponse");
        cn.jiguang.d.e.a.b bVar = (cn.jiguang.d.e.a.b) jResponse;
        String b = bVar.b();
        d.c("CtrlMessageProcessor", "action - processLocation, msgContent:" + b);
        JSONObject jSONObject = null;
        int i = -1;
        if (!cn.jiguang.g.k.a(b)) {
            jSONObject = a(b);
            if (jSONObject != null) {
                i = jSONObject.optInt("cmd", -1);
            }
        }
        long a = bVar.a();
        String a2 = a(context, i);
        long d = cn.jiguang.d.a.d.d(null);
        long h = cn.jiguang.d.a.a.h();
        int a3 = cn.jiguang.d.a.d.a();
        OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
        outputDataUtil.writeU16(0);
        outputDataUtil.writeU8(1);
        outputDataUtil.writeU8(25);
        outputDataUtil.writeU64(h);
        outputDataUtil.writeU32((long) a3);
        outputDataUtil.writeU64(d);
        outputDataUtil.writeU16(0);
        outputDataUtil.writeU64(a);
        if (a2 != null) {
            outputDataUtil.writeByteArrayincludeLength(a2.getBytes());
        }
        outputDataUtil.writeU16At(outputDataUtil.current(), 0);
        byte[] a4 = cn.jiguang.d.e.a.a.b.a(outputDataUtil.toByteArray(), 1);
        if (a4 == null) {
            d.h("CtrlMessageProcessor", "reportCtrlReceived to report received failed - " + a);
            obj = -1;
        } else {
            i = cn.jiguang.d.f.d.a().b().a(a4);
            d.a("CtrlMessageProcessor", "reportCtrlReceived - ret:" + i);
            if (i != 0) {
                d.h("CtrlMessageProcessor", "Failed to report received - " + a);
            } else {
                d.c("CtrlMessageProcessor", "Succeed to report received - " + a);
            }
            obj = null;
        }
        if (obj != null) {
            return;
        }
        if (jSONObject == null) {
            d.g("CtrlMessageProcessor", "msgContent is null");
            return;
        }
        try {
            i = jSONObject.optInt("cmd");
            d.c("CtrlMessageProcessor", "ctrl - cmd:" + i);
            switch (i) {
                case 4:
                    c.a(context);
                    return;
                case 5:
                    a(context, handler, jSONObject);
                    return;
                case 6:
                case 21:
                case 22:
                case 53:
                    return;
                case 9:
                    c.b(context);
                    return;
                case 44:
                    k.a(context);
                    return;
                case 45:
                    b(context, jSONObject);
                    return;
                case 50:
                    c(context, jSONObject);
                    return;
                case 51:
                    d(context, jSONObject);
                    return;
                case 52:
                    e(context, jSONObject);
                    return;
                case 54:
                    a(context, jSONObject);
                    return;
                default:
                    d.g("CtrlMessageProcessor", "Unexpected: unknown push ctrl cmd: " + i);
                    return;
            }
        } catch (Throwable e) {
            d.f("CtrlMessageProcessor", "unexpected!", e);
        }
        d.f("CtrlMessageProcessor", "unexpected!", e);
    }

    private static void a(Context context, Handler handler, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("content");
            d.c("CtrlMessageProcessor", "jsonContent:" + jSONObject2);
            if (jSONObject2.optBoolean("disable")) {
                d.d("CtrlMessageProcessor", "lbs disabled...");
                cn.jiguang.a.b.a.a(context, false);
                return;
            }
            d.d("CtrlMessageProcessor", "lbs enabled...");
            cn.jiguang.a.b.a.a(context, true);
            cn.jiguang.a.b.a.b(context, true);
            long optLong = jSONObject2.optLong("frequency", 0);
            cn.jiguang.d.a.d.a(context, "report_location_frequency", Long.valueOf(optLong > 0 ? optLong * 1000 : cn.jiguang.a.b.a.a(context)));
            a(handler);
        } catch (Throwable e) {
            d.d("CtrlMessageProcessor", "unexpected! has wrong with JSONException", e);
        }
    }

    private static void a(Context context, JSONObject jSONObject) {
        try {
            if (jSONObject.has("content")) {
                jSONObject = jSONObject.getJSONObject("content");
            }
            long j = jSONObject.getLong("interval");
            if (j < 0) {
                cn.jiguang.d.a.d.a(context, "app_running_collect_enable", Boolean.valueOf(false));
                return;
            }
            int optInt = jSONObject.optInt("app_type", 0);
            int optInt2 = jSONObject.optInt("process_type", 0);
            if (j > 0) {
                cn.jiguang.d.a.d.a(context, new cn.jiguang.g.b.a().a("app_running_collect_enable", Boolean.valueOf(true)).a("app_running_collect_interval", Long.valueOf(j)).a("app_running_collect_app_type", Integer.valueOf(optInt)).a("app_running_collect_process_type", Integer.valueOf(optInt2)));
            } else {
                cn.jiguang.g.d.a(context, optInt, optInt2);
            }
        } catch (Exception e) {
            d.g("CtrlMessageProcessor", "configReportRunningApp exception:" + e.getMessage());
        }
    }

    private static void a(Handler handler) {
        d.a("CtrlMessageProcessor", "action - processLocation");
        Message.obtain(handler, 1002).sendToTarget();
    }

    private static void b(Context context, JSONObject jSONObject) {
        d.c("CtrlMessageProcessor", "action - processLocationCollect");
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("content");
            d.c("CtrlMessageProcessor", "jsonContent:" + jSONObject2);
            long optLong = jSONObject2.optLong("interval", 0);
            if (optLong <= 0 || optLong > cn.jiguang.a.b.a.a(context) / 1000) {
                d.g("JAnalyticsCommonConfigs", "unexpected!");
            } else {
                cn.jiguang.d.a.d.a(context, "location_collect_frequency", Long.valueOf(optLong * 1000));
            }
        } catch (Throwable e) {
            d.d("CtrlMessageProcessor", "unexpected! has wrong with JSONException", e);
        }
    }

    private static void c(Context context, JSONObject jSONObject) {
        d.c("CtrlMessageProcessor", "action - processStartThirdService");
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("content");
            d.c("CtrlMessageProcessor", "jsonContent:" + jSONObject2);
            int optInt = jSONObject2.optInt("type");
            d.a("CtrlMessageProcessor", "startType:" + optInt);
            h c = f.a().c();
            String optString;
            String optString2;
            cn.jiguang.d.d.a aVar;
            switch (optInt) {
                case 1:
                    optString = jSONObject2.optString("pkgName");
                    optString2 = jSONObject2.optString("serviceName");
                    aVar = new cn.jiguang.d.d.a();
                    aVar.a = optString;
                    aVar.b = optString2;
                    c.a(context, aVar);
                    return;
                case 2:
                    optString = jSONObject2.optString("pkgName");
                    optString2 = jSONObject2.optString("serviceName");
                    aVar = new cn.jiguang.d.d.a();
                    aVar.a = optString;
                    aVar.b = optString2;
                    c.b(context, aVar);
                    d.c("CtrlMessageProcessor", "bind remote service");
                    return;
                case 10:
                    d.c("CtrlMessageProcessor", "start push service...");
                    return;
                default:
                    d.c("CtrlMessageProcessor", "Unexpected: unknown type:" + optInt);
                    return;
            }
        } catch (SecurityException e) {
            d.g("CtrlMessageProcessor", "Can not start PushService due to SecurityException.");
        } catch (Throwable e2) {
            d.e("CtrlMessageProcessor", "unexpected! has wrong with JSONException", e2);
        } catch (Throwable e22) {
            d.c("CtrlMessageProcessor", "unexpected!", e22);
        }
    }

    private static void d(Context context, JSONObject jSONObject) {
        d.c("CtrlMessageProcessor", "action - updateDetchPhoneNumberConfig");
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("content");
            d.c("CtrlMessageProcessor", "jsonContent:" + jSONObject2);
            boolean optBoolean = jSONObject2.optBoolean("uploadnumber", false);
            String optString = jSONObject2.optString("version", "");
            String optString2 = jSONObject2.optString("app_id", "");
            String optString3 = jSONObject2.optString("app_secret", "");
            JSONArray optJSONArray = jSONObject2.optJSONArray("carriers");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject3 = optJSONArray.getJSONObject(i);
                    if (jSONObject3 != null) {
                        String optString4 = jSONObject3.optString("carrier", "");
                        String optString5 = jSONObject3.optString("url", "");
                        if (!(cn.jiguang.g.k.a(optString4) || cn.jiguang.g.k.a(optString5))) {
                            int a = i.a(optString4);
                            if (!optString5.startsWith("http://")) {
                                optString5 = "http://" + optString5;
                            }
                            if (!optString5.endsWith(HttpUtils.PATHS_SEPARATOR)) {
                                optString5 = optString5 + HttpUtils.PATHS_SEPARATOR;
                            }
                            if (a != -1 && a >= 0 && a < 3) {
                                cn.jiguang.d.a.a.a(context, "number_url" + a, optString5);
                            }
                            d.a("CtrlMessageProcessor", "carrier:" + optString4 + " url:" + optString5 + " providerindex:" + a);
                        }
                    }
                }
            }
            if (!cn.jiguang.g.k.a(optString)) {
                cn.jiguang.d.a.a.a(context, "number_version", optString);
            }
            if (cn.jiguang.g.k.a(optString2)) {
                cn.jiguang.d.a.a.a(context, "number_appid", optString2);
            }
            if (cn.jiguang.g.k.a(optString3)) {
                cn.jiguang.d.a.a.a(context, "number_appsecret", optString3);
            }
            d.c("CtrlMessageProcessor", "version:" + optString + "\n appID:" + optString2 + "\n appSecret:" + optString3 + "\n reportnumber:" + optBoolean);
            if (optBoolean) {
                cn.jiguang.a.b.a.c(context, true);
                if (!cn.jiguang.g.a.d(context).toUpperCase().startsWith("WIFI")) {
                    i.a(context);
                }
            }
        } catch (Throwable e) {
            d.e("CtrlMessageProcessor", "unexpected!", e);
        }
    }

    private static void e(Context context, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("content");
            d.c("CtrlMessageProcessor", "jsonContent:" + jSONObject2);
            boolean optBoolean = jSONObject2.optBoolean("disable");
            long optLong = (optBoolean || !jSONObject2.has("frequency")) ? 0 : jSONObject2.optLong("frequency", 0);
            cn.jiguang.d.a.d.a(context, "arpinfo_report_enable", Boolean.valueOf(!optBoolean));
            cn.jiguang.d.a.d.a(context, "report_arpinfo_frequency", Long.valueOf(1000 * optLong));
            if (!optBoolean) {
                try {
                    c.a(context);
                } catch (Throwable th) {
                    d.i("CtrlMessageProcessor", "reportMacListInfo error:" + th.getMessage());
                }
            }
        } catch (Throwable th2) {
            d.d("CtrlMessageProcessor", "unexpected! has wrong with JSONException", th2);
        }
    }
}
