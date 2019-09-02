package cn.jiguang.d.g;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public final class f {
    public static String a(JSONObject jSONObject) {
        try {
            return jSONObject.toString(2);
        } catch (JSONException e) {
            return jSONObject.toString();
        }
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 != null && jSONObject2.length() != 0) {
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                try {
                    String str = (String) keys.next();
                    jSONObject.put(str, jSONObject2.get(str));
                } catch (JSONException e) {
                    return;
                }
            }
        }
    }

    public static boolean a(JSONObject jSONObject, JSONObject jSONObject2, Set<String> set) {
        for (String str : set) {
            Object opt = jSONObject.opt(str);
            Object opt2 = jSONObject2.opt(str);
            if (opt == null) {
                if (opt2 != null) {
                    return false;
                }
            } else if (!opt.equals(opt2)) {
                return false;
            }
        }
        return true;
    }

    public static int b(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                return jSONObject.toString().getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
            }
        }
        return 0;
    }
}
