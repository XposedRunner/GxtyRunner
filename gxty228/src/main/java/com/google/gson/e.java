package com.google.gson;

import com.google.gson.internal.a.d;
import com.google.gson.internal.a.g;
import com.google.gson.internal.a.h;
import com.google.gson.internal.a.i;
import com.google.gson.internal.a.j;
import com.google.gson.internal.a.k;
import com.google.gson.internal.a.n;
import com.google.gson.internal.b;
import com.google.gson.internal.c;
import com.google.gson.internal.f;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* compiled from: Gson */
public final class e {
    private static final com.google.gson.b.a<?> a = com.google.gson.b.a.b(Object.class);
    private final ThreadLocal<Map<com.google.gson.b.a<?>, a<?>>> b;
    private final Map<com.google.gson.b.a<?>, q<?>> c;
    private final List<r> d;
    private final b e;
    private final c f;
    private final d g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final d m;

    /* compiled from: Gson */
    static class a<T> extends q<T> {
        private q<T> a;

        a() {
        }

        public void a(q<T> qVar) {
            if (this.a != null) {
                throw new AssertionError();
            }
            this.a = qVar;
        }

        public T b(com.google.gson.stream.a aVar) throws IOException {
            if (this.a != null) {
                return this.a.b(aVar);
            }
            throw new IllegalStateException();
        }

        public void a(com.google.gson.stream.b bVar, T t) throws IOException {
            if (this.a == null) {
                throw new IllegalStateException();
            }
            this.a.a(bVar, t);
        }
    }

    public e() {
        this(c.a, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }

    e(c cVar, d dVar, Map<Type, g<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, LongSerializationPolicy longSerializationPolicy, List<r> list) {
        this.b = new ThreadLocal();
        this.c = new ConcurrentHashMap();
        this.e = new b(map);
        this.f = cVar;
        this.g = dVar;
        this.h = z;
        this.j = z3;
        this.i = z4;
        this.k = z5;
        this.l = z6;
        List arrayList = new ArrayList();
        arrayList.add(n.Y);
        arrayList.add(h.a);
        arrayList.add(cVar);
        arrayList.addAll(list);
        arrayList.add(n.D);
        arrayList.add(n.m);
        arrayList.add(n.g);
        arrayList.add(n.i);
        arrayList.add(n.k);
        q a = a(longSerializationPolicy);
        arrayList.add(n.a(Long.TYPE, Long.class, a));
        arrayList.add(n.a(Double.TYPE, Double.class, a(z7)));
        arrayList.add(n.a(Float.TYPE, Float.class, b(z7)));
        arrayList.add(n.x);
        arrayList.add(n.o);
        arrayList.add(n.q);
        arrayList.add(n.a(AtomicLong.class, a(a)));
        arrayList.add(n.a(AtomicLongArray.class, b(a)));
        arrayList.add(n.s);
        arrayList.add(n.z);
        arrayList.add(n.F);
        arrayList.add(n.H);
        arrayList.add(n.a(BigDecimal.class, n.B));
        arrayList.add(n.a(BigInteger.class, n.C));
        arrayList.add(n.J);
        arrayList.add(n.L);
        arrayList.add(n.P);
        arrayList.add(n.R);
        arrayList.add(n.W);
        arrayList.add(n.N);
        arrayList.add(n.d);
        arrayList.add(com.google.gson.internal.a.c.a);
        arrayList.add(n.U);
        arrayList.add(k.a);
        arrayList.add(j.a);
        arrayList.add(n.S);
        arrayList.add(com.google.gson.internal.a.a.a);
        arrayList.add(n.b);
        arrayList.add(new com.google.gson.internal.a.b(this.e));
        arrayList.add(new g(this.e, z2));
        this.m = new d(this.e);
        arrayList.add(this.m);
        arrayList.add(n.Z);
        arrayList.add(new i(this.e, dVar, cVar, this.m));
        this.d = Collections.unmodifiableList(arrayList);
    }

    private q<Number> a(boolean z) {
        if (z) {
            return n.v;
        }
        return new q<Number>(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
                return a(aVar);
            }

            public Double a(com.google.gson.stream.a aVar) throws IOException {
                if (aVar.f() != JsonToken.NULL) {
                    return Double.valueOf(aVar.k());
                }
                aVar.j();
                return null;
            }

            public void a(com.google.gson.stream.b bVar, Number number) throws IOException {
                if (number == null) {
                    bVar.f();
                    return;
                }
                e.a(number.doubleValue());
                bVar.a(number);
            }
        };
    }

    private q<Number> b(boolean z) {
        if (z) {
            return n.u;
        }
        return new q<Number>(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
                return a(aVar);
            }

            public Float a(com.google.gson.stream.a aVar) throws IOException {
                if (aVar.f() != JsonToken.NULL) {
                    return Float.valueOf((float) aVar.k());
                }
                aVar.j();
                return null;
            }

            public void a(com.google.gson.stream.b bVar, Number number) throws IOException {
                if (number == null) {
                    bVar.f();
                    return;
                }
                e.a((double) number.floatValue());
                bVar.a(number);
            }
        };
    }

    static void a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private static q<Number> a(LongSerializationPolicy longSerializationPolicy) {
        if (longSerializationPolicy == LongSerializationPolicy.DEFAULT) {
            return n.t;
        }
        return new q<Number>() {
            public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
                return a(aVar);
            }

            public Number a(com.google.gson.stream.a aVar) throws IOException {
                if (aVar.f() != JsonToken.NULL) {
                    return Long.valueOf(aVar.l());
                }
                aVar.j();
                return null;
            }

            public void a(com.google.gson.stream.b bVar, Number number) throws IOException {
                if (number == null) {
                    bVar.f();
                } else {
                    bVar.b(number.toString());
                }
            }
        };
    }

    private static q<AtomicLong> a(final q<Number> qVar) {
        return new q<AtomicLong>() {
            public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
                return a(aVar);
            }

            public void a(com.google.gson.stream.b bVar, AtomicLong atomicLong) throws IOException {
                qVar.a(bVar, Long.valueOf(atomicLong.get()));
            }

            public AtomicLong a(com.google.gson.stream.a aVar) throws IOException {
                return new AtomicLong(((Number) qVar.b(aVar)).longValue());
            }
        }.a();
    }

    private static q<AtomicLongArray> b(final q<Number> qVar) {
        return new q<AtomicLongArray>() {
            public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
                return a(aVar);
            }

            public void a(com.google.gson.stream.b bVar, AtomicLongArray atomicLongArray) throws IOException {
                bVar.b();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    qVar.a(bVar, Long.valueOf(atomicLongArray.get(i)));
                }
                bVar.c();
            }

            public AtomicLongArray a(com.google.gson.stream.a aVar) throws IOException {
                List arrayList = new ArrayList();
                aVar.a();
                while (aVar.e()) {
                    arrayList.add(Long.valueOf(((Number) qVar.b(aVar)).longValue()));
                }
                aVar.b();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i = 0; i < size; i++) {
                    atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
                }
                return atomicLongArray;
            }
        }.a();
    }

    public <T> q<T> a(com.google.gson.b.a<T> aVar) {
        Object obj;
        Map map;
        Map map2 = this.c;
        if (aVar == null) {
            obj = a;
        } else {
            com.google.gson.b.a<T> aVar2 = aVar;
        }
        q<T> qVar = (q) map2.get(obj);
        if (qVar == null) {
            Map map3 = (Map) this.b.get();
            Object obj2 = null;
            if (map3 == null) {
                HashMap hashMap = new HashMap();
                this.b.set(hashMap);
                map = hashMap;
                obj2 = 1;
            } else {
                map = map3;
            }
            a aVar3 = (a) map.get(aVar);
            if (aVar3 == null) {
                try {
                    a aVar4 = new a();
                    map.put(aVar, aVar4);
                    for (r a : this.d) {
                        qVar = a.a(this, aVar);
                        if (qVar != null) {
                            aVar4.a(qVar);
                            this.c.put(aVar, qVar);
                            map.remove(aVar);
                            if (obj2 != null) {
                                this.b.remove();
                            }
                        }
                    }
                    throw new IllegalArgumentException("GSON cannot handle " + aVar);
                } catch (Throwable th) {
                    map.remove(aVar);
                    if (obj2 != null) {
                        this.b.remove();
                    }
                }
            }
        }
        return qVar;
    }

    public <T> q<T> a(r rVar, com.google.gson.b.a<T> aVar) {
        if (!this.d.contains(rVar)) {
            rVar = this.m;
        }
        Object obj = null;
        for (r rVar2 : this.d) {
            if (obj != null) {
                q<T> a = rVar2.a(this, aVar);
                if (a != null) {
                    return a;
                }
            } else if (rVar2 == rVar) {
                obj = 1;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    public <T> q<T> a(Class<T> cls) {
        return a(com.google.gson.b.a.b(cls));
    }

    public String a(Object obj) {
        if (obj == null) {
            return a(l.a);
        }
        return a(obj, obj.getClass());
    }

    public String a(Object obj, Type type) {
        Appendable stringWriter = new StringWriter();
        a(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void a(Object obj, Type type, Appendable appendable) throws JsonIOException {
        try {
            a(obj, type, a(com.google.gson.internal.g.a(appendable)));
        } catch (Throwable e) {
            throw new JsonIOException(e);
        }
    }

    public void a(Object obj, Type type, com.google.gson.stream.b bVar) throws JsonIOException {
        q a = a(com.google.gson.b.a.a(type));
        boolean g = bVar.g();
        bVar.b(true);
        boolean h = bVar.h();
        bVar.c(this.i);
        boolean i = bVar.i();
        bVar.d(this.h);
        try {
            a.a(bVar, obj);
            bVar.b(g);
            bVar.c(h);
            bVar.d(i);
        } catch (Throwable e) {
            throw new JsonIOException(e);
        } catch (Throwable th) {
            bVar.b(g);
            bVar.c(h);
            bVar.d(i);
        }
    }

    public String a(k kVar) {
        Appendable stringWriter = new StringWriter();
        a(kVar, stringWriter);
        return stringWriter.toString();
    }

    public void a(k kVar, Appendable appendable) throws JsonIOException {
        try {
            a(kVar, a(com.google.gson.internal.g.a(appendable)));
        } catch (Throwable e) {
            throw new JsonIOException(e);
        }
    }

    public com.google.gson.stream.b a(Writer writer) throws IOException {
        if (this.j) {
            writer.write(")]}'\n");
        }
        com.google.gson.stream.b bVar = new com.google.gson.stream.b(writer);
        if (this.k) {
            bVar.c("  ");
        }
        bVar.d(this.h);
        return bVar;
    }

    public com.google.gson.stream.a a(Reader reader) {
        com.google.gson.stream.a aVar = new com.google.gson.stream.a(reader);
        aVar.a(this.l);
        return aVar;
    }

    public void a(k kVar, com.google.gson.stream.b bVar) throws JsonIOException {
        boolean g = bVar.g();
        bVar.b(true);
        boolean h = bVar.h();
        bVar.c(this.i);
        boolean i = bVar.i();
        bVar.d(this.h);
        try {
            com.google.gson.internal.g.a(kVar, bVar);
            bVar.b(g);
            bVar.c(h);
            bVar.d(i);
        } catch (Throwable e) {
            throw new JsonIOException(e);
        } catch (Throwable th) {
            bVar.b(g);
            bVar.c(h);
            bVar.d(i);
        }
    }

    public <T> T a(String str, Class<T> cls) throws JsonSyntaxException {
        return f.a((Class) cls).cast(a(str, (Type) cls));
    }

    public <T> T a(String str, Type type) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        return a(new StringReader(str), type);
    }

    public <T> T a(Reader reader, Type type) throws JsonIOException, JsonSyntaxException {
        com.google.gson.stream.a a = a(reader);
        Object a2 = a(a, type);
        a(a2, a);
        return a2;
    }

    private static void a(Object obj, com.google.gson.stream.a aVar) {
        if (obj != null) {
            try {
                if (aVar.f() != JsonToken.END_DOCUMENT) {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            } catch (Throwable e2) {
                throw new JsonIOException(e2);
            }
        }
    }

    public <T> T a(com.google.gson.stream.a aVar, Type type) throws JsonIOException, JsonSyntaxException {
        boolean z = true;
        boolean q = aVar.q();
        aVar.a(true);
        try {
            aVar.f();
            z = false;
            T b = a(com.google.gson.b.a.a(type)).b(aVar);
            aVar.a(q);
            return b;
        } catch (Throwable e) {
            if (z) {
                aVar.a(q);
                return null;
            }
            throw new JsonSyntaxException(e);
        } catch (Throwable e2) {
            throw new JsonSyntaxException(e2);
        } catch (Throwable e22) {
            throw new JsonSyntaxException(e22);
        } catch (Throwable th) {
            aVar.a(q);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.h + ",factories:" + this.d + ",instanceCreators:" + this.e + "}";
    }
}
