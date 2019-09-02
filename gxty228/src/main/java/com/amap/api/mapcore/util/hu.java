package com.amap.api.mapcore.util;

import java.util.HashMap;
import java.util.Map;

@hf(a = "file")
/* compiled from: DynamicPlugin */
public class hu {
    @hg(a = "fname", b = 6)
    private String a;
    @hg(a = "md", b = 6)
    private String b;
    @hg(a = "sname", b = 6)
    private String c;
    @hg(a = "version", b = 6)
    private String d;
    @hg(a = "dversion", b = 6)
    private String e;
    @hg(a = "status", b = 6)
    private String f;

    /* compiled from: DynamicPlugin */
    public static class a {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f = "copy";

        public a(String str, String str2, String str3, String str4, String str5) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
        }

        public a a(String str) {
            this.f = str;
            return this;
        }

        public hu a() {
            return new hu(this);
        }
    }

    public hu(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
    }

    private hu() {
    }

    public static String a(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("sname", str);
        hashMap.put("dversion", str2);
        return he.a(hashMap);
    }

    public static String b(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("sname", str);
        hashMap.put("status", str2);
        return he.a(hashMap);
    }

    public static String a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("sname", str);
        return he.a(hashMap);
    }

    public static String b(String str) {
        Map hashMap = new HashMap();
        hashMap.put("fname", str);
        return he.a(hashMap);
    }

    public static String a(String str, String str2, String str3, String str4) {
        Map hashMap = new HashMap();
        hashMap.put("fname", str);
        hashMap.put("sname", str2);
        hashMap.put("dversion", str4);
        hashMap.put("version", str3);
        return he.a(hashMap);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public void c(String str) {
        this.f = str;
    }
}
