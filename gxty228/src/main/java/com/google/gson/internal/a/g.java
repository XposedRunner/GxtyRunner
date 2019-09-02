package com.google.gson.internal.a;

import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.b;
import com.google.gson.internal.d;
import com.google.gson.internal.e;
import com.google.gson.k;
import com.google.gson.n;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: MapTypeAdapterFactory */
public final class g implements r {
    final boolean a;
    private final b b;

    /* compiled from: MapTypeAdapterFactory */
    private final class a<K, V> extends q<Map<K, V>> {
        final /* synthetic */ g a;
        private final q<K> b;
        private final q<V> c;
        private final e<? extends Map<K, V>> d;

        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public a(g gVar, com.google.gson.e eVar, Type type, q<K> qVar, Type type2, q<V> qVar2, e<? extends Map<K, V>> eVar2) {
            this.a = gVar;
            this.b = new m(eVar, qVar, type);
            this.c = new m(eVar, qVar2, type2);
            this.d = eVar2;
        }

        public Map<K, V> a(com.google.gson.stream.a aVar) throws IOException {
            JsonToken f = aVar.f();
            if (f == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            Map<K, V> map = (Map) this.d.a();
            Object b;
            if (f == JsonToken.BEGIN_ARRAY) {
                aVar.a();
                while (aVar.e()) {
                    aVar.a();
                    b = this.b.b(aVar);
                    if (map.put(b, this.c.b(aVar)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + b);
                    }
                    aVar.b();
                }
                aVar.b();
                return map;
            }
            aVar.c();
            while (aVar.e()) {
                d.a.a(aVar);
                b = this.b.b(aVar);
                if (map.put(b, this.c.b(aVar)) != null) {
                    throw new JsonSyntaxException("duplicate key: " + b);
                }
            }
            aVar.d();
            return map;
        }

        public void a(com.google.gson.stream.b bVar, Map<K, V> map) throws IOException {
            int i = 0;
            if (map == null) {
                bVar.f();
            } else if (this.a.a) {
                List arrayList = new ArrayList(map.size());
                List arrayList2 = new ArrayList(map.size());
                int i2 = 0;
                for (Entry entry : map.entrySet()) {
                    int i3;
                    k a = this.b.a(entry.getKey());
                    arrayList.add(a);
                    arrayList2.add(entry.getValue());
                    if (a.g() || a.h()) {
                        i3 = 1;
                    } else {
                        i3 = 0;
                    }
                    i2 = i3 | i2;
                }
                if (i2 != 0) {
                    bVar.b();
                    i2 = arrayList.size();
                    while (i < i2) {
                        bVar.b();
                        com.google.gson.internal.g.a((k) arrayList.get(i), bVar);
                        this.c.a(bVar, arrayList2.get(i));
                        bVar.c();
                        i++;
                    }
                    bVar.c();
                    return;
                }
                bVar.d();
                i2 = arrayList.size();
                while (i < i2) {
                    bVar.a(a((k) arrayList.get(i)));
                    this.c.a(bVar, arrayList2.get(i));
                    i++;
                }
                bVar.e();
            } else {
                bVar.d();
                for (Entry entry2 : map.entrySet()) {
                    bVar.a(String.valueOf(entry2.getKey()));
                    this.c.a(bVar, entry2.getValue());
                }
                bVar.e();
            }
        }

        private String a(k kVar) {
            if (kVar.i()) {
                n m = kVar.m();
                if (m.p()) {
                    return String.valueOf(m.a());
                }
                if (m.o()) {
                    return Boolean.toString(m.f());
                }
                if (m.q()) {
                    return m.b();
                }
                throw new AssertionError();
            } else if (kVar.j()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }
    }

    public g(b bVar, boolean z) {
        this.b = bVar;
        this.a = z;
    }

    public <T> q<T> a(com.google.gson.e eVar, com.google.gson.b.a<T> aVar) {
        Type b = aVar.b();
        if (!Map.class.isAssignableFrom(aVar.a())) {
            return null;
        }
        Type[] b2 = C$Gson$Types.b(b, C$Gson$Types.e(b));
        q a = a(eVar, b2[0]);
        q a2 = eVar.a(com.google.gson.b.a.a(b2[1]));
        e a3 = this.b.a((com.google.gson.b.a) aVar);
        return new a(this, eVar, b2[0], a, b2[1], a2, a3);
    }

    private q<?> a(com.google.gson.e eVar, Type type) {
        if (type == Boolean.TYPE || type == Boolean.class) {
            return n.f;
        }
        return eVar.a(com.google.gson.b.a.a(type));
    }
}
