package com.loc;

import java.io.File;

/* compiled from: FileNumUpdateStrategy */
public final class bk extends bo {
    private int b;
    private String c;

    public bk(int i, String str, bo boVar) {
        super(boVar);
        this.b = i;
        this.c = str;
    }

    private static int a(String str) {
        int i = 0;
        try {
            File file = new File(str);
            if (file.exists()) {
                i = file.list().length;
            }
        } catch (Throwable th) {
            j.b(th, "fus", "gfn");
        }
        return i;
    }

    protected final boolean a() {
        return a(this.c) >= this.b;
    }
}
