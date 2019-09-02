package com.loc;

/* compiled from: UpdateStrategy */
public abstract class bo {
    bo a;

    public bo(bo boVar) {
        this.a = boVar;
    }

    public void a(int i) {
        if (this.a != null) {
            this.a.a(i);
        }
    }

    public void a(boolean z) {
        if (this.a != null) {
            this.a.a(z);
        }
    }

    protected abstract boolean a();

    public int b() {
        return Math.min(Integer.MAX_VALUE, this.a != null ? this.a.b() : Integer.MAX_VALUE);
    }

    public final boolean c() {
        return !(this.a != null ? this.a.c() : true) ? false : a();
    }
}
