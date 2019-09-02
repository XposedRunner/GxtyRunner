package com.autonavi.aps.amapapi.model;

import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption.GeoLanguage;
import com.loc.cl;
import com.loc.ct;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class AMapLocationServer extends AMapLocation {
    protected String d = "";
    boolean e = true;
    String f = String.valueOf(GeoLanguage.DEFAULT);
    private String g = null;
    private String h = "";
    private int i;
    private String j = "";
    private String k = "new";
    private JSONObject l = null;
    private String m = "";
    private String n = "";
    private long o = 0;
    private String p = null;

    public AMapLocationServer(String str) {
        super(str);
    }

    public final String a() {
        return this.g;
    }

    public final void a(long j) {
        this.o = j;
    }

    public final void a(String str) {
        this.g = str;
    }

    public final void a(JSONObject jSONObject) {
        this.l = jSONObject;
    }

    public final void a(boolean z) {
        this.e = z;
    }

    public final String b() {
        return this.h;
    }

    public final void b(String str) {
        this.h = str;
    }

    public final void b(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                cl.a((AMapLocation) this, jSONObject);
                this.k = jSONObject.optString("type", this.k);
                this.j = jSONObject.optString("retype", this.j);
                String optString = jSONObject.optString("cens", this.n);
                if (!TextUtils.isEmpty(optString)) {
                    for (Object obj : optString.split("\\*")) {
                        if (!TextUtils.isEmpty(obj)) {
                            String[] split = obj.split(",");
                            setLongitude(ct.f(split[0]));
                            setLatitude(ct.f(split[1]));
                            setAccuracy((float) ct.h(split[2]));
                            break;
                        }
                    }
                    this.n = optString;
                }
                this.d = jSONObject.optString(SocialConstants.PARAM_APP_DESC, this.d);
                c(jSONObject.optString("coord", String.valueOf(this.i)));
                this.m = jSONObject.optString("mcell", this.m);
                this.e = jSONObject.optBoolean("isReversegeo", this.e);
                this.f = jSONObject.optString("geoLanguage", this.f);
                if (ct.a(jSONObject, ParamKey.POIID)) {
                    setBuildingId(jSONObject.optString(ParamKey.POIID));
                }
                if (ct.a(jSONObject, "pid")) {
                    setBuildingId(jSONObject.optString("pid"));
                }
                if (ct.a(jSONObject, "floor")) {
                    setFloor(jSONObject.optString("floor"));
                }
                if (ct.a(jSONObject, "flr")) {
                    setFloor(jSONObject.optString("flr"));
                }
            } catch (Throwable th) {
                cl.a(th, "AmapLoc", "AmapLoc");
            }
        }
    }

    public final int c() {
        return this.i;
    }

    public final void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (getProvider().equals("gps")) {
                this.i = 0;
                return;
            } else if (str.equals("0")) {
                this.i = 0;
                return;
            } else if (str.equals("1")) {
                this.i = 1;
                return;
            }
        }
        this.i = -1;
    }

    public final String d() {
        return this.j;
    }

    public final void d(String str) {
        this.j = str;
    }

    public final String e() {
        return this.k;
    }

    public final void e(String str) {
        this.k = str;
    }

    public final JSONObject f() {
        return this.l;
    }

    public final void f(String str) {
        this.f = str;
    }

    public final String g() {
        return this.m;
    }

    public final void g(String str) {
        this.d = str;
    }

    public final AMapLocationServer h() {
        Object obj = this.m;
        if (TextUtils.isEmpty(obj)) {
            return null;
        }
        String[] split = obj.split(",");
        if (split.length != 3) {
            return null;
        }
        AMapLocationServer aMapLocationServer = new AMapLocationServer("");
        aMapLocationServer.setProvider(getProvider());
        aMapLocationServer.setLongitude(ct.f(split[0]));
        aMapLocationServer.setLatitude(ct.f(split[1]));
        aMapLocationServer.setAccuracy(ct.g(split[2]));
        aMapLocationServer.setCityCode(getCityCode());
        aMapLocationServer.setAdCode(getAdCode());
        aMapLocationServer.setCountry(getCountry());
        aMapLocationServer.setProvince(getProvince());
        aMapLocationServer.setCity(getCity());
        aMapLocationServer.setTime(getTime());
        aMapLocationServer.k = this.k;
        aMapLocationServer.c(String.valueOf(this.i));
        return ct.a(aMapLocationServer) ? aMapLocationServer : null;
    }

    public final void h(String str) {
        this.p = str;
    }

    public final boolean i() {
        return this.e;
    }

    public final String j() {
        return this.f;
    }

    public final long k() {
        return this.o;
    }

    public final String l() {
        return this.p;
    }

    public JSONObject toJson(int i) {
        try {
            JSONObject toJson = super.toJson(i);
            switch (i) {
                case 1:
                    toJson.put("retype", this.j);
                    toJson.put("cens", this.n);
                    toJson.put("coord", this.i);
                    toJson.put("mcell", this.m);
                    toJson.put(SocialConstants.PARAM_APP_DESC, this.d);
                    toJson.put("address", getAddress());
                    if (this.l != null && ct.a(toJson, "offpct")) {
                        toJson.put("offpct", this.l.getString("offpct"));
                        break;
                    }
                case 2:
                case 3:
                    break;
                default:
                    return toJson;
            }
            toJson.put("type", this.k);
            toJson.put("isReversegeo", this.e);
            toJson.put("geoLanguage", this.f);
            return toJson;
        } catch (Throwable th) {
            cl.a(th, "AmapLoc", "toStr");
            return null;
        }
    }

    public String toStr() {
        return toStr(1);
    }

    public String toStr(int i) {
        JSONObject toJson;
        try {
            toJson = toJson(i);
            toJson.put("nb", this.p);
        } catch (Throwable th) {
            cl.a(th, "AMapLocation", "toStr part2");
            toJson = null;
        }
        return toJson == null ? null : toJson.toString();
    }
}
