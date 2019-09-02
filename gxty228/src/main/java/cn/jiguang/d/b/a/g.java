package cn.jiguang.d.b.a;

import org.json.JSONException;
import org.json.JSONObject;

public final class g {
    private int a;
    private String b;
    private String c;
    private long d;
    private String e;
    private double f;
    private double g;
    private long h;

    public g(int i, String str, String str2, long j, String str3, double d, double d2, long j2) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = j;
        this.e = str3;
        this.f = d;
        this.g = d2;
        this.h = j2;
    }

    public final int a() {
        return this.a;
    }

    public final long b() {
        return this.d;
    }

    public final double c() {
        return this.f;
    }

    public final double d() {
        return this.g;
    }

    public final long e() {
        return this.h;
    }

    public final JSONObject f() {
        Object obj = null;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.a);
            jSONObject.put(LogBuilder.KEY_APPKEY, this.b);
            jSONObject.put("sdkver", this.c);
            jSONObject.put("platform", 0);
            if (this.d != 0) {
                jSONObject.put("uid", this.d);
            }
            if (this.e != null) {
                jSONObject.put("opera", this.e);
            }
            double d = this.f;
            double d2 = this.g;
            if (d > -90.0d && d < 90.0d && d2 > -180.0d && d2 < 180.0d) {
                obj = 1;
            }
            if (obj != null) {
                jSONObject.put("lat", this.f);
                jSONObject.put("lng", this.g);
                jSONObject.put("time", this.h);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
