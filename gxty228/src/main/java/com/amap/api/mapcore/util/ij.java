package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SoFileItem */
public final class ij {
    String a;
    boolean b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;

    private ij(String str, String str2, String str3, String str4, String str5, String str6, boolean z, String str7) {
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.a = str6;
        this.b = z;
        this.h = str7;
    }

    public final String a() {
        return this.c;
    }

    public final String b() {
        return this.d;
    }

    public final void a(String str) {
        this.d = str;
    }

    public final String c() {
        return this.e;
    }

    public final String d() {
        return this.f;
    }

    public final String e() {
        return this.g;
    }

    public final String f() {
        return this.h;
    }

    public final void b(String str) {
        this.c = str;
    }

    public static ij a(String str, iz izVar) {
        if (TextUtils.isEmpty(str)) {
            return new ij();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new ij("", "", jSONObject.optString("sdk", ""), jSONObject.optString("sdkv", ""), jSONObject.optString("sdkdynamicv", ""), jSONObject.optString("md5", ""), izVar.e, jSONObject.optString("so_file_name", ""));
        } catch (Throwable th) {
            hz.d("SoFile#fromJson json ex " + th);
            return new ij();
        }
    }

    private static ij d(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ij();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new ij(jSONObject.optString("sk", ""), jSONObject.optString("mk", ""), jSONObject.optString("ak", ""), jSONObject.optString("bk", ""), jSONObject.optString("ik", ""), jSONObject.optString("ek", ""), jSONObject.optBoolean("lk", false), jSONObject.optString("nk", ""));
        } catch (Throwable th) {
            hz.d("SoFile#fromJson json ex " + th);
            return new ij();
        }
    }

    public static List<ij> c(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        List<ij> arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(d(jSONArray.getString(i)));
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    private static String a(ij ijVar) {
        if (ijVar == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mk", ijVar.d);
            jSONObject.put("ak", ijVar.e);
            jSONObject.put("bk", ijVar.f);
            jSONObject.put("ik", ijVar.g);
            jSONObject.put("ek", ijVar.a);
            jSONObject.put("lk", ijVar.b);
            jSONObject.put("nk", ijVar.h);
            jSONObject.put("sk", ijVar.c);
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }

    public static String a(List<ij> list) {
        if (list == null) {
            return "";
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            try {
                jSONArray.put(i, a((ij) list.get(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray.toString();
    }

    public static boolean a(ij ijVar, ij ijVar2) {
        if (ijVar2 != null && ijVar != null && ijVar.e.equals(ijVar2.e) && ijVar.f.equals(ijVar2.f) && ijVar.g.equals(ijVar2.g) && ijVar.h.equals(ijVar2.h)) {
            return true;
        }
        return false;
    }

    public final boolean g() {
        return (TextUtils.isEmpty(this.e) || TextUtils.isEmpty(this.f) || TextUtils.isEmpty(this.g)) ? false : true;
    }
}
