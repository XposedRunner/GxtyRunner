package com.google.gson.internal.a;

import com.google.gson.b.a;
import com.google.gson.e;
import com.google.gson.internal.b;
import com.google.gson.j;
import com.google.gson.p;
import com.google.gson.q;
import com.google.gson.r;

/* compiled from: JsonAdapterAnnotationTypeAdapterFactory */
public final class d implements r {
    private final b a;

    public d(b bVar) {
        this.a = bVar;
    }

    public <T> q<T> a(e eVar, a<T> aVar) {
        com.google.gson.a.b bVar = (com.google.gson.a.b) aVar.a().getAnnotation(com.google.gson.a.b.class);
        if (bVar == null) {
            return null;
        }
        return a(this.a, eVar, aVar, bVar);
    }

    q<?> a(b bVar, e eVar, a<?> aVar, com.google.gson.a.b bVar2) {
        q<?> qVar;
        Object a = bVar.a(a.b(bVar2.a())).a();
        if (a instanceof q) {
            qVar = (q) a;
        } else if (a instanceof r) {
            qVar = ((r) a).a(eVar, aVar);
        } else if ((a instanceof p) || (a instanceof j)) {
            j jVar;
            p pVar = a instanceof p ? (p) a : null;
            if (a instanceof j) {
                jVar = (j) a;
            } else {
                jVar = null;
            }
            qVar = new l(pVar, jVar, eVar, aVar, null);
        } else {
            throw new IllegalArgumentException("Invalid attempt to bind an instance of " + a.getClass().getName() + " as a @JsonAdapter for " + aVar.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory," + " JsonSerializer or JsonDeserializer.");
        }
        if (qVar == null || !bVar2.b()) {
            return qVar;
        }
        return qVar.a();
    }
}
