package com.amap.api.mapcore.util;

@hf(a = "update_item")
/* compiled from: DTInfo */
public class ay {
    @hg(a = "title", b = 6)
    protected String a = null;
    @hg(a = "url", b = 6)
    protected String b = null;
    @hg(a = "mAdcode", b = 6)
    protected String c = null;
    @hg(a = "fileName", b = 6)
    protected String d = null;
    @hg(a = "version", b = 6)
    protected String e = "";
    @hg(a = "lLocalLength", b = 5)
    protected long f = 0;
    @hg(a = "lRemoteLength", b = 5)
    protected long g = 0;
    @hg(a = "localPath", b = 6)
    protected String h;
    @hg(a = "isProvince", b = 2)
    protected int i = 0;
    @hg(a = "mCompleteCode", b = 2)
    protected int j;
    @hg(a = "mCityCode", b = 6)
    protected String k = "";
    @hg(a = "mState", b = 2)
    public int l;
    @hg(a = "mPinyin", b = 6)
    public String m = "";

    public String d() {
        return this.a;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String g() {
        return this.b;
    }

    public int h() {
        return this.j;
    }

    public void d(String str) {
        this.k = str;
    }

    public String i() {
        return this.m;
    }

    public static String e(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mAdcode");
        stringBuilder.append("='");
        stringBuilder.append(str);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }

    public static String f(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mPinyin");
        stringBuilder.append("='");
        stringBuilder.append(str);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }
}
