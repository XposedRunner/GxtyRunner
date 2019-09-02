package com.google.gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: JsonArray */
public final class h extends k implements Iterable<k> {
    private final List<k> a = new ArrayList();

    public void a(k kVar) {
        if (kVar == null) {
            kVar = l.a;
        }
        this.a.add(kVar);
    }

    public Iterator<k> iterator() {
        return this.a.iterator();
    }

    public Number a() {
        if (this.a.size() == 1) {
            return ((k) this.a.get(0)).a();
        }
        throw new IllegalStateException();
    }

    public String b() {
        if (this.a.size() == 1) {
            return ((k) this.a.get(0)).b();
        }
        throw new IllegalStateException();
    }

    public double c() {
        if (this.a.size() == 1) {
            return ((k) this.a.get(0)).c();
        }
        throw new IllegalStateException();
    }

    public long d() {
        if (this.a.size() == 1) {
            return ((k) this.a.get(0)).d();
        }
        throw new IllegalStateException();
    }

    public int e() {
        if (this.a.size() == 1) {
            return ((k) this.a.get(0)).e();
        }
        throw new IllegalStateException();
    }

    public boolean f() {
        if (this.a.size() == 1) {
            return ((k) this.a.get(0)).f();
        }
        throw new IllegalStateException();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof h) && ((h) obj).a.equals(this.a));
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
