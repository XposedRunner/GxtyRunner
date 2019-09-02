package com.amap.api.mapcore.util;

import java.io.File;

/* compiled from: FileNumUpdateStrategy */
public class jv extends jz {
    private int b;
    private String c;

    public jv(int i, String str, jz jzVar) {
        super(jzVar);
        this.b = i;
        this.c = str;
    }

    protected boolean a() {
        if (a(this.c) < this.b) {
            return false;
        }
        return true;
    }

    public int a(String str) {
        int i = 0;
        try {
            File file = new File(str);
            if (file.exists()) {
                i = file.list().length;
            }
        } catch (Throwable th) {
            gz.c(th, "fus", "gfn");
        }
        return i;
    }
}
