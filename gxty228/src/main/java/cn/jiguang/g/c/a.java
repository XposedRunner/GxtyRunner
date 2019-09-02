package cn.jiguang.g.c;

import android.text.TextUtils;
import org.json.JSONObject;

public final class a {
    public String a;
    public String b;
    public String c;
    public int d;
    public String e;
    public String f;
    public String g;

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("imei", TextUtils.isEmpty(this.b) ? "" : this.b);
            jSONObject.put("iccid", TextUtils.isEmpty(this.e) ? "" : this.e);
            jSONObject.put("imsi", TextUtils.isEmpty(this.c) ? "" : this.c);
            return jSONObject;
        } catch (Throwable th) {
            return null;
        }
    }

    public final String toString() {
        return "SimInfo{device_id='" + this.a + '\'' + ", imei='" + this.b + '\'' + ", imsi='" + this.c + '\'' + ", phoneType=" + this.d + ", iccid='" + this.e + '\'' + ", simOpertorName='" + this.f + '\'' + ", networkOperatorName='" + this.g + '\'' + '}';
    }
}
