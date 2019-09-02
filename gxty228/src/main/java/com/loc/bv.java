package com.loc;

import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.util.Locale;
import org.json.JSONObject;

/* compiled from: Cgi */
public final class bv {
    public int a = 0;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public int f = 0;
    public int g = 0;
    public int h = 0;
    public int i = 0;
    public int j = -113;
    public int k = 0;
    public short l = (short) 0;
    public long m = 0;
    public boolean n = false;
    public boolean o = true;

    public bv(int i, boolean z) {
        this.k = i;
        this.n = z;
    }

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.k);
            jSONObject.put("registered", this.n);
            jSONObject.put("mcc", this.a);
            jSONObject.put("mnc", this.b);
            jSONObject.put("lac", this.c);
            jSONObject.put(IXAdRequestInfo.CELL_ID, this.d);
            jSONObject.put("sid", this.g);
            jSONObject.put("nid", this.h);
            jSONObject.put("bid", this.i);
            jSONObject.put("sig", this.j);
        } catch (Throwable th) {
            cl.a(th, "cgi", "toJson");
        }
        return jSONObject;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof bv)) {
            return false;
        }
        bv bvVar = (bv) obj;
        switch (bvVar.k) {
            case 1:
                return this.k == 1 && bvVar.c == this.c && bvVar.d == this.d && bvVar.b == this.b;
            case 2:
                return this.k == 2 && bvVar.i == this.i && bvVar.h == this.h && bvVar.g == this.g;
            case 3:
                return this.k == 3 && bvVar.c == this.c && bvVar.d == this.d && bvVar.b == this.b;
            case 4:
                return this.k == 4 && bvVar.c == this.c && bvVar.d == this.d && bvVar.b == this.b;
            default:
                return false;
        }
    }

    public final int hashCode() {
        int hashCode = String.valueOf(this.k).hashCode();
        return this.k == 2 ? hashCode + ((String.valueOf(this.i).hashCode() + String.valueOf(this.h).hashCode()) + String.valueOf(this.g).hashCode()) : hashCode + ((String.valueOf(this.c).hashCode() + String.valueOf(this.d).hashCode()) + String.valueOf(this.b).hashCode());
    }

    public final String toString() {
        String str = "unknown";
        switch (this.k) {
            case 1:
                return String.format(Locale.CHINA, "GSM lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b", new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.o), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n)});
            case 2:
                return String.format(Locale.CHINA, "CDMA bid=%d, nid=%d, sid=%d, valid=%b, sig=%d, age=%d, reg=%b", new Object[]{Integer.valueOf(this.i), Integer.valueOf(this.h), Integer.valueOf(this.g), Boolean.valueOf(this.o), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n)});
            case 3:
                return String.format(Locale.CHINA, "LTE lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b", new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.o), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n)});
            case 4:
                return String.format(Locale.CHINA, "WCDMA lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b", new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.o), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n)});
            default:
                return str;
        }
    }
}
