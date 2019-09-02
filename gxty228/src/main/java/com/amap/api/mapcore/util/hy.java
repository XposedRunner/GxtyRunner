package com.amap.api.mapcore.util;

/* compiled from: SoLoaderEntity */
public class hy {
    private gk a;
    private boolean b = true;

    public hy(gk gkVar, boolean z) {
        this.a = gkVar;
        this.b = z;
    }

    public gk a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }

    public String c() {
        if (a() == null) {
            return "";
        }
        return a().a();
    }
}
