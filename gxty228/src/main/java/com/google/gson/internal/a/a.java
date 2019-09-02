package com.google.gson.internal.a;

import com.google.gson.e;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.b;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ArrayTypeAdapter */
public final class a<E> extends q<Object> {
    public static final r a = new r() {
        public <T> q<T> a(e eVar, com.google.gson.b.a<T> aVar) {
            Type b = aVar.b();
            if (!(b instanceof GenericArrayType) && (!(b instanceof Class) || !((Class) b).isArray())) {
                return null;
            }
            b = C$Gson$Types.g(b);
            return new a(eVar, eVar.a(com.google.gson.b.a.a(b)), C$Gson$Types.e(b));
        }
    };
    private final Class<E> b;
    private final q<E> c;

    public a(e eVar, q<E> qVar, Class<E> cls) {
        this.c = new m(eVar, qVar, cls);
        this.b = cls;
    }

    public Object b(com.google.gson.stream.a aVar) throws IOException {
        if (aVar.f() == JsonToken.NULL) {
            aVar.j();
            return null;
        }
        List arrayList = new ArrayList();
        aVar.a();
        while (aVar.e()) {
            arrayList.add(this.c.b(aVar));
        }
        aVar.b();
        int size = arrayList.size();
        Object newInstance = Array.newInstance(this.b, size);
        for (int i = 0; i < size; i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    public void a(b bVar, Object obj) throws IOException {
        if (obj == null) {
            bVar.f();
            return;
        }
        bVar.b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.c.a(bVar, Array.get(obj, i));
        }
        bVar.c();
    }
}
