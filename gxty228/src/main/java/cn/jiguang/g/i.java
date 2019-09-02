package cn.jiguang.g;

import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import android.util.SparseArray;
import cn.jiguang.api.utils.ByteBufferUtils;
import cn.jiguang.d.d.o;
import cn.jiguang.e.d;
import com.tencent.tauth.AuthActivity;
import org.json.JSONException;
import org.json.JSONObject;

public final class i {
    private static final SparseArray<String> a;
    private static long b = 0;

    static {
        SparseArray sparseArray = new SparseArray();
        a = sparseArray;
        sparseArray.put(0, "OK");
        a.put(-1001, "Exceed buffer size. Please contact support.");
        a.put(NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, "Connection failed. Please check your connection and retry later!");
        a.put(-998, "Sending failed or timeout. Please Retry later!");
        a.put(-997, "Receiving failed or timeout. Please Retry later!");
        a.put(-996, "Connection is closed. Please Retry later!");
        a.put(-994, "Response timeout. Please Retry later!");
        a.put(-993, "Invalid socket. Please Retry later!");
        a.put(11, "Failed to register!");
        a.put(1005, "Your appKey and android package name are not matched. Please double check them according to Application you created on Portal.");
        a.put(PointerIconCompat.TYPE_CELL, "You android package name is not exist, Please register your pacakge name in Portal.");
        a.put(PointerIconCompat.TYPE_CROSSHAIR, "Invalid Imei, Register again.");
        a.put(PointerIconCompat.TYPE_VERTICAL_TEXT, "Your appKey is related to a non-Android App.Please use your Android App's appKey, or add an Android app for this appKey.");
        a.put(ByteBufferUtils.ERROR_CODE, "Receiver data parse error");
        a.put(102, "102 - Incorrect password");
        a.put(108, "108 - Incorrect uid");
        a.put(PointerIconCompat.TYPE_NO_DROP, "Server reject this connection, will clear cache and restart connect.");
    }

    public static String a(int i) {
        if (a.get(i) != null) {
            return (String) a.get(i);
        }
        d.c("StatusCode", "Unknown error code - " + i);
        return null;
    }

    public static JSONObject a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AuthActivity.ACTION_KEY, "rmv");
            jSONObject.put("appid", str);
            o.b(null, jSONObject, "app_add_rmv");
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    public static JSONObject a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AuthActivity.ACTION_KEY, "add");
            jSONObject.put("appid", str);
            o.b(null, jSONObject, "app_add_rmv");
            jSONObject.put("install_type", i);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
