package cn.jpush.android.a;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jpush.android.a;
import cn.jpush.android.d.e;
import org.json.JSONException;
import org.json.JSONObject;

public final class d {
    public static void a(String str, int i, String str2, Context context) {
        if (!JCoreInterface.isValidRegistered()) {
            e.a("JPushReportHelper", "JPush is inValidRegistered");
        } else if (context == null) {
            e.c("JPushReportHelper", "context did not init, return");
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("action:reportActionResult - messageId: " + str + ", code: " + i + "-" + j.a(i));
            if (!TextUtils.isEmpty(str2)) {
                stringBuffer.append(" report content: " + str2);
            }
            e.c("JPushReportHelper", stringBuffer.toString());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("msg_id", str);
                jSONObject.put("result", i);
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put("data", str2);
                }
                JCoreInterface.fillBaseReport(jSONObject, "msg_status");
                JCoreInterface.reportHttpData(context, jSONObject, a.a);
            } catch (JSONException e) {
            }
        }
    }

    public static void a(String str, String str2, byte b, int i, Context context) {
        if (!JCoreInterface.isValidRegistered()) {
            e.a("JPushReportHelper", "JPush is inValidRegistered");
        } else if (context == null) {
            e.c("JPushReportHelper", "context did not init, return");
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("action:reportThirdSDKMsgActionResult - messageId: " + str + ", code: " + i);
            e.c("JPushReportHelper", stringBuffer.toString());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("msg_id", str);
                jSONObject.put("tmsg_id", str2);
                jSONObject.put("result", i);
                jSONObject.put("sdk_type", b);
                JCoreInterface.fillBaseReport(jSONObject, "third_msg_status");
                JCoreInterface.reportHttpData(context, jSONObject, a.a);
            } catch (JSONException e) {
            }
        }
    }
}
