package com.amap.api.mapcore.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SoInfoEntity */
public final class iz implements Parcelable {
    int a;
    int b;
    String c;
    String d;
    boolean e;
    boolean f;
    List<ij> g;
    private String h;
    private String i;
    private String j;
    private String k;

    public iz() {
        this.g = new ArrayList();
    }

    public iz(iz izVar) {
        this();
        if (izVar != null) {
            this.e = izVar.e;
            this.d = izVar.d;
            this.h = izVar.h;
            this.i = izVar.i;
            this.b = izVar.b;
            this.a = izVar.a;
            this.j = izVar.j;
            this.c = izVar.c;
            this.k = izVar.k;
            this.g = izVar.d();
        }
    }

    public iz(String str, String str2, String str3, boolean z, boolean z2) {
        this.g = new ArrayList();
        this.c = str;
        this.d = str2;
        this.e = z;
        this.f = z2;
        try {
            String[] split = str.split(HttpUtils.PATHS_SEPARATOR);
            this.k = split[split.length - 1];
            split = this.k.split("_");
            this.h = split[0];
            this.j = split[1];
            this.i = split[2];
            try {
                this.a = Integer.parseInt(split[3]);
                this.b = Integer.parseInt(split[4].split("\\.")[0]);
            } catch (Throwable th) {
            }
            this.g = a(this.h, str3);
        } catch (Throwable th2) {
        }
    }

    private iz(String str, String str2, String str3, int i, int i2, String str4, String str5, String str6, boolean z, List<ij> list) {
        this.g = new ArrayList();
        this.h = str;
        this.i = str2;
        this.j = str3;
        this.a = i;
        this.b = i2;
        this.c = str4;
        this.d = str5;
        this.k = str6;
        this.e = z;
        this.g = list;
    }

    public final String a() {
        return this.h;
    }

    public final String b() {
        return this.i;
    }

    public final String c() {
        return this.j;
    }

    public final List<ij> d() {
        if (this.g == null) {
            this.g = new ArrayList();
        }
        return this.g;
    }

    public final ij a(String str) {
        if (this.g == null || TextUtils.isEmpty(str)) {
            return null;
        }
        for (ij ijVar : this.g) {
            if (ijVar.f().equals(str)) {
                return ijVar;
            }
        }
        return null;
    }

    public static boolean a(iz izVar) {
        if (izVar != null && !TextUtils.isEmpty(izVar.h) && if.a(izVar.j) && if.a(izVar.i) && izVar.b > 0 && izVar.a > 0 && izVar.d() != null && izVar.d().size() != 0) {
            return true;
        }
        return false;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(e());
    }

    static {
        AnonymousClass1 anonymousClass1 = new Creator() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new iz[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return iz.b(parcel.readString());
            }
        };
    }

    private List<ij> a(String str, String str2) {
        try {
            JSONArray jSONArray = new JSONArray(str2);
            String uuid = UUID.randomUUID().toString();
            List<ij> arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    ij a = ij.a(jSONArray.getString(i), this);
                    a.a(uuid);
                    a.b(str);
                    arrayList.add(a);
                } catch (JSONException e) {
                }
            }
            return arrayList;
        } catch (JSONException e2) {
            return new ArrayList();
        }
    }

    public static iz b(String str) {
        if (TextUtils.isEmpty(str)) {
            return new iz();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new iz(jSONObject.optString("ak", ""), jSONObject.optString("bk", ""), jSONObject.optString("ik", ""), jSONObject.optInt("dk", -1), jSONObject.optInt("ck", -1), "", jSONObject.optString("ek", ""), "", jSONObject.optBoolean("lk", false), ij.c(jSONObject.optString("jk", "")));
        } catch (Throwable th) {
            hz.d("SoFile#fromJson json ex " + th);
            return new iz();
        }
    }

    public final String e() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ak", this.h);
            jSONObject.put("bk", this.i);
            jSONObject.put("ik", this.j);
            jSONObject.put("ck", this.b);
            jSONObject.put("dk", this.a);
            jSONObject.put("ek", this.d);
            jSONObject.put("lk", this.e);
            jSONObject.put("jk", ij.a(this.g));
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }
}
