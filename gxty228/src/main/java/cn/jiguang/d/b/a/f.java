package cn.jiguang.d.b.a;

import android.text.TextUtils;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class f {
    int a;
    c b;
    long c;
    long d;
    long e;
    int f;
    double g;
    double h;
    long i;
    int j;

    private static f a(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return null;
        }
        try {
            f fVar = new f();
            fVar.a = jSONObject.getInt("type");
            fVar.b = c.a(jSONObject.getString("addr"));
            fVar.d = jSONObject.getLong("rtime");
            fVar.e = jSONObject.getLong("interval");
            fVar.f = jSONObject.getInt("net");
            fVar.j = jSONObject.getInt("code");
            fVar.c = (long) jSONObject.optInt("uid");
            fVar.g = jSONObject.optDouble("lat");
            fVar.h = jSONObject.optDouble("lng");
            fVar.i = jSONObject.optLong("ltime");
            return fVar;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static LinkedList<f> a(String str) {
        LinkedList<f> linkedList = new LinkedList();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    linkedList.add(a(jSONArray.getJSONObject(i)));
                }
            } catch (JSONException e) {
            }
        }
        return linkedList;
    }

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.a);
            jSONObject.put("addr", this.b.toString());
            jSONObject.put("rtime", this.d);
            jSONObject.put("interval", this.e);
            jSONObject.put("net", this.f);
            jSONObject.put("code", this.j);
            if (this.c != 0) {
                jSONObject.put("uid", this.c);
            }
            double d = this.g;
            double d2 = this.h;
            Object obj = (d <= -90.0d || d >= 90.0d || d2 <= -180.0d || d2 >= 180.0d) ? null : 1;
            if (obj != null) {
                jSONObject.put("lat", this.g);
                jSONObject.put("lng", this.h);
                jSONObject.put("ltime", this.i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
