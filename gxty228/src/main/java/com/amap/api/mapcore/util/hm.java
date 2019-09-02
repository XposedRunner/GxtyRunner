package com.amap.api.mapcore.util;

import cn.jiguang.net.HttpUtils;

/* compiled from: DexDownloadItem */
public class hm {
    String a;
    String b;
    String c;
    String d;
    int e;
    int f;
    private String g;
    private String h;
    private boolean i;
    private boolean j;

    public hm(String str, String str2, String str3) {
        this(str, str2, str3, false);
    }

    public hm(String str, String str2, String str3, boolean z) {
        this.i = false;
        this.j = false;
        this.g = str;
        this.h = str2;
        this.i = z;
        try {
            String[] split = str.split(HttpUtils.PATHS_SEPARATOR);
            int length = split.length;
            if (length > 1) {
                this.a = split[length - 1];
                split = this.a.split("_");
                this.b = split[0];
                this.c = split[2];
                this.d = split[1];
                this.e = Integer.parseInt(split[3]);
                this.f = Integer.parseInt(split[4].split("\\.")[0]);
            }
        } catch (Throwable th) {
            hw.a(th, "DexDownloadItem", "DexDownloadItem");
        }
    }

    String a() {
        return this.g;
    }

    String b() {
        return this.h;
    }

    String c() {
        return this.c;
    }

    public boolean d() {
        return this.i;
    }

    public boolean e() {
        return this.j;
    }

    public void a(boolean z) {
        this.j = z;
    }
}
