package com.loc;

import cn.jiguang.net.HttpUtils;

/* compiled from: DexDownloadItem */
public final class w {
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

    public w(String str, String str2) {
        this(str, str2, (byte) 0);
    }

    private w(String str, String str2, byte b) {
        this.i = false;
        this.j = false;
        this.g = str;
        this.h = str2;
        this.i = false;
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
            g.a(th, "DexDownloadItem", "DexDownloadItem");
        }
    }

    final String a() {
        return this.g;
    }

    public final void a(boolean z) {
        this.j = z;
    }

    final String b() {
        return this.h;
    }

    public final boolean c() {
        return this.i;
    }

    public final boolean d() {
        return this.j;
    }
}
