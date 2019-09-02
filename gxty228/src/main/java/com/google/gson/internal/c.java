package com.google.gson.internal;

import com.google.gson.a.d;
import com.google.gson.b;
import com.google.gson.b.a;
import com.google.gson.e;
import com.google.gson.q;
import com.google.gson.r;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: Excluder */
public final class c implements r, Cloneable {
    public static final c a = new c();
    private double b = -1.0d;
    private int c = 136;
    private boolean d = true;
    private boolean e;
    private List<b> f = Collections.emptyList();
    private List<b> g = Collections.emptyList();

    protected /* synthetic */ Object clone() throws CloneNotSupportedException {
        return a();
    }

    protected c a() {
        try {
            return (c) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public c a(b bVar, boolean z, boolean z2) {
        c a = a();
        if (z) {
            a.f = new ArrayList(this.f);
            a.f.add(bVar);
        }
        if (z2) {
            a.g = new ArrayList(this.g);
            a.g.add(bVar);
        }
        return a;
    }

    public <T> q<T> a(e eVar, a<T> aVar) {
        Class a = aVar.a();
        final boolean a2 = a(a, true);
        final boolean a3 = a(a, false);
        if (!a2 && !a3) {
            return null;
        }
        final e eVar2 = eVar;
        final a<T> aVar2 = aVar;
        return new q<T>(this) {
            final /* synthetic */ c e;
            private q<T> f;

            public T b(com.google.gson.stream.a aVar) throws IOException {
                if (!a3) {
                    return b().b(aVar);
                }
                aVar.n();
                return null;
            }

            public void a(com.google.gson.stream.b bVar, T t) throws IOException {
                if (a2) {
                    bVar.f();
                } else {
                    b().a(bVar, t);
                }
            }

            private q<T> b() {
                q<T> qVar = this.f;
                if (qVar != null) {
                    return qVar;
                }
                qVar = eVar2.a(this.e, aVar2);
                this.f = qVar;
                return qVar;
            }
        };
    }

    public boolean a(Field field, boolean z) {
        if ((this.c & field.getModifiers()) != 0) {
            return true;
        }
        if (this.b != -1.0d && !a((d) field.getAnnotation(d.class), (com.google.gson.a.e) field.getAnnotation(com.google.gson.a.e.class))) {
            return true;
        }
        if (field.isSynthetic()) {
            return true;
        }
        if (this.e) {
            com.google.gson.a.a aVar = (com.google.gson.a.a) field.getAnnotation(com.google.gson.a.a.class);
            if (aVar == null || (z ? !aVar.a() : !aVar.b())) {
                return true;
            }
        }
        if (!this.d && b(field.getType())) {
            return true;
        }
        if (a(field.getType())) {
            return true;
        }
        List<b> list = z ? this.f : this.g;
        if (!list.isEmpty()) {
            com.google.gson.c cVar = new com.google.gson.c(field);
            for (b a : list) {
                if (a.a(cVar)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean a(Class<?> cls, boolean z) {
        if (this.b != -1.0d && !a((d) cls.getAnnotation(d.class), (com.google.gson.a.e) cls.getAnnotation(com.google.gson.a.e.class))) {
            return true;
        }
        if (!this.d && b(cls)) {
            return true;
        }
        if (a((Class) cls)) {
            return true;
        }
        for (b a : z ? this.f : this.g) {
            if (a.a((Class) cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean b(Class<?> cls) {
        return cls.isMemberClass() && !c(cls);
    }

    private boolean c(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean a(d dVar, com.google.gson.a.e eVar) {
        return a(dVar) && a(eVar);
    }

    private boolean a(d dVar) {
        if (dVar == null || dVar.a() <= this.b) {
            return true;
        }
        return false;
    }

    private boolean a(com.google.gson.a.e eVar) {
        if (eVar == null || eVar.a() > this.b) {
            return true;
        }
        return false;
    }
}
