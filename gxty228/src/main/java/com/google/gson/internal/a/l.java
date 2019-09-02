package com.google.gson.internal.a;

import com.google.gson.e;
import com.google.gson.i;
import com.google.gson.internal.g;
import com.google.gson.j;
import com.google.gson.k;
import com.google.gson.o;
import com.google.gson.p;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.b;
import java.io.IOException;

/* compiled from: TreeTypeAdapter */
public final class l<T> extends q<T> {
    final e a;
    private final p<T> b;
    private final j<T> c;
    private final com.google.gson.b.a<T> d;
    private final r e;
    private final a f = new a();
    private q<T> g;

    /* compiled from: TreeTypeAdapter */
    private final class a implements i, o {
        final /* synthetic */ l a;

        private a(l lVar) {
            this.a = lVar;
        }
    }

    public l(p<T> pVar, j<T> jVar, e eVar, com.google.gson.b.a<T> aVar, r rVar) {
        this.b = pVar;
        this.c = jVar;
        this.a = eVar;
        this.d = aVar;
        this.e = rVar;
    }

    public T b(com.google.gson.stream.a aVar) throws IOException {
        if (this.c == null) {
            return b().b(aVar);
        }
        k a = g.a(aVar);
        if (a.j()) {
            return null;
        }
        return this.c.a(a, this.d.b(), this.f);
    }

    public void a(b bVar, T t) throws IOException {
        if (this.b == null) {
            b().a(bVar, t);
        } else if (t == null) {
            bVar.f();
        } else {
            g.a(this.b.a(t, this.d.b(), this.f), bVar);
        }
    }

    private q<T> b() {
        q<T> qVar = this.g;
        if (qVar != null) {
            return qVar;
        }
        qVar = this.a.a(this.e, this.d);
        this.g = qVar;
        return qVar;
    }
}
