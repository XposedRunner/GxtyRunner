package com.amap.api.mapcore.util;

import android.text.TextUtils;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: MapLocationModel */
public final class kx extends Inner_3dMap_location {
    boolean a = true;
    private String b = null;
    private String c = "";
    private int d;
    private String e = "";
    private String f = "new";
    private JSONObject g = null;
    private String h = "";
    private String i = "";
    private long j = 0;
    private String k = null;

    public kx(String str) {
        super(str);
    }

    public final String a() {
        return this.b;
    }

    public final void a(String str) {
        this.b = str;
    }

    public final String b() {
        return this.c;
    }

    public final void b(String str) {
        this.c = str;
    }

    public final int c() {
        return this.d;
    }

    public final void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (getProvider().equals("gps")) {
                this.d = 0;
                return;
            } else if (str.equals("0")) {
                this.d = 0;
                return;
            } else if (str.equals("1")) {
                this.d = 1;
                return;
            }
        }
        this.d = -1;
    }

    public final String d() {
        return this.e;
    }

    public final void d(String str) {
        this.e = str;
    }

    public final JSONObject e() {
        return this.g;
    }

    public final void e(String str) {
        this.desc = str;
    }

    public final void setFloor(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = str.replace("F", "");
            try {
                Integer.parseInt(str);
            } catch (Throwable th) {
                str = null;
                kz.a(th, "MapLocationModel", "setFloor");
            }
        }
        this.floor = str;
    }

    public final JSONObject toJson(int i) {
        try {
            JSONObject toJson = super.toJson(i);
            switch (i) {
                case 1:
                    toJson.put("retype", this.e);
                    toJson.put("cens", this.i);
                    toJson.put(ParamKey.POIID, this.buildingId);
                    toJson.put("floor", this.floor);
                    toJson.put("coord", this.d);
                    toJson.put("mcell", this.h);
                    toJson.put(SocialConstants.PARAM_APP_DESC, this.desc);
                    toJson.put("address", getAddress());
                    if (this.g != null && lc.a(toJson, "offpct")) {
                        toJson.put("offpct", this.g.getString("offpct"));
                        break;
                    }
                case 2:
                case 3:
                    break;
                default:
                    return toJson;
            }
            toJson.put("type", this.f);
            toJson.put("isReversegeo", this.a);
            return toJson;
        } catch (Throwable th) {
            kz.a(th, "MapLocationModel", "toStr");
            return null;
        }
    }

    public final String toStr(int i) {
        JSONObject toJson;
        try {
            toJson = super.toJson(i);
            toJson.put("nb", this.k);
        } catch (Throwable th) {
            kz.a(th, "MapLocationModel", "toStr part2");
            toJson = null;
        }
        return toJson == null ? null : toJson.toString();
    }
}
