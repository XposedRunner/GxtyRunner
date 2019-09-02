package cn.jiguang.a.a.b;

import android.text.TextUtils;
import cn.jiguang.e.d;
import com.lzy.okgo.model.Progress;
import org.json.JSONException;
import org.json.JSONObject;

public final class e {
    private double a;
    private double b;
    private double c;
    private float d;
    private float e;
    private String f;
    private long g;
    private boolean h;
    private String i;

    public e(double d, double d2, double d3, float f, float f2, String str, long j, boolean z) {
        this.a = d;
        this.b = d2;
        this.c = d3;
        this.d = f;
        this.e = f2;
        this.f = str;
        this.g = j;
        this.h = z;
    }

    public e(String str) {
        this.i = str;
    }

    public static e a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new e(jSONObject.getDouble("lat"), jSONObject.getDouble("lng"), jSONObject.getDouble("alt"), (float) jSONObject.getDouble("bear"), (float) jSONObject.getDouble("acc"), jSONObject.getString(Progress.TAG), jSONObject.getLong("itime"), true);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    static /* synthetic */ boolean a(double d, double d2) {
        return d > -90.0d && d < 90.0d && d2 > -180.0d && d2 < 180.0d;
    }

    public final boolean a() {
        return TextUtils.isEmpty(this.i);
    }

    public final double b() {
        return this.a;
    }

    public final double c() {
        return this.b;
    }

    public final long d() {
        return this.g;
    }

    public final String e() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("lat", this.a);
            jSONObject.put("lng", this.b);
            jSONObject.put("time", this.g);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public final JSONObject f() {
        if (TextUtils.isEmpty(this.i)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("lat", this.a);
                jSONObject.put("lng", this.b);
                jSONObject.put("alt", this.c);
                jSONObject.put("bear", (double) this.d);
                jSONObject.put("acc", (double) this.e);
                jSONObject.put(Progress.TAG, this.f);
                jSONObject.put("itime", this.g);
                return jSONObject;
            } catch (JSONException e) {
                d.g("GpsInfoManager", e.getMessage());
                this.i = "JSONException " + e.getMessage();
            }
        }
        return null;
    }

    public final String g() {
        JSONObject f = f();
        if (f == null || f.length() <= 0) {
            return "failed because : " + this.i;
        }
        try {
            f.put("itime", c.b(this.g * 1000));
            f.put("isLastKnown", this.h);
        } catch (JSONException e) {
            d.g("GpsInfoManager", e.getMessage());
        }
        return f.toString();
    }
}
