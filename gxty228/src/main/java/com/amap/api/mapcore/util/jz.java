package com.amap.api.mapcore.util;

/* compiled from: UpdateStrategy */
public abstract class jz {
    jz a;

    protected abstract boolean a();

    public jz(jz jzVar) {
        this.a = jzVar;
    }

    public boolean c() {
        if (d()) {
            return a();
        }
        return false;
    }

    private boolean d() {
        if (this.a != null) {
            return this.a.c();
        }
        return true;
    }

    public void a(boolean z) {
        if (this.a != null) {
            this.a.a(z);
        }
    }

    public int b() {
        int b;
        if (this.a != null) {
            b = this.a.b();
        } else {
            b = Integer.MAX_VALUE;
        }
        return Math.min(Integer.MAX_VALUE, b);
    }

    public void a(int i) {
        if (this.a != null) {
            this.a.a(i);
        }
    }
}
