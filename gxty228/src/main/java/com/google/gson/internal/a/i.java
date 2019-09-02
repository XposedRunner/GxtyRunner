package com.google.gson.internal.a;

import com.google.gson.JsonSyntaxException;
import com.google.gson.d;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.c;
import com.google.gson.internal.e;
import com.google.gson.internal.f;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ReflectiveTypeAdapterFactory */
public final class i implements r {
    private final com.google.gson.internal.b a;
    private final d b;
    private final c c;
    private final d d;

    /* compiled from: ReflectiveTypeAdapterFactory */
    static abstract class b {
        final String h;
        final boolean i;
        final boolean j;

        abstract void a(com.google.gson.stream.a aVar, Object obj) throws IOException, IllegalAccessException;

        abstract void a(com.google.gson.stream.b bVar, Object obj) throws IOException, IllegalAccessException;

        abstract boolean a(Object obj) throws IOException, IllegalAccessException;

        protected b(String str, boolean z, boolean z2) {
            this.h = str;
            this.i = z;
            this.j = z2;
        }
    }

    /* compiled from: ReflectiveTypeAdapterFactory */
    public static final class a<T> extends q<T> {
        private final e<T> a;
        private final Map<String, b> b;

        a(e<T> eVar, Map<String, b> map) {
            this.a = eVar;
            this.b = map;
        }

        public T b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            T a = this.a.a();
            try {
                aVar.c();
                while (aVar.e()) {
                    b bVar = (b) this.b.get(aVar.g());
                    if (bVar == null || !bVar.j) {
                        aVar.n();
                    } else {
                        bVar.a(aVar, (Object) a);
                    }
                }
                aVar.d();
                return a;
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void a(com.google.gson.stream.b bVar, T t) throws IOException {
            if (t == null) {
                bVar.f();
                return;
            }
            bVar.d();
            try {
                for (b bVar2 : this.b.values()) {
                    if (bVar2.a(t)) {
                        bVar.a(bVar2.h);
                        bVar2.a(bVar, (Object) t);
                    }
                }
                bVar.e();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    public i(com.google.gson.internal.b bVar, d dVar, c cVar, d dVar2) {
        this.a = bVar;
        this.b = dVar;
        this.c = cVar;
        this.d = dVar2;
    }

    public boolean a(Field field, boolean z) {
        return a(field, z, this.c);
    }

    static boolean a(Field field, boolean z, c cVar) {
        return (cVar.a(field.getType(), z) || cVar.a(field, z)) ? false : true;
    }

    private List<String> a(Field field) {
        com.google.gson.a.c cVar = (com.google.gson.a.c) field.getAnnotation(com.google.gson.a.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.b.translateName(field));
        }
        String a = cVar.a();
        String[] b = cVar.b();
        if (b.length == 0) {
            return Collections.singletonList(a);
        }
        List<String> arrayList = new ArrayList(b.length + 1);
        arrayList.add(a);
        for (Object add : b) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public <T> q<T> a(com.google.gson.e eVar, com.google.gson.b.a<T> aVar) {
        Class a = aVar.a();
        if (Object.class.isAssignableFrom(a)) {
            return new a(this.a.a((com.google.gson.b.a) aVar), a(eVar, (com.google.gson.b.a) aVar, a));
        }
        return null;
    }

    private b a(com.google.gson.e eVar, Field field, String str, com.google.gson.b.a<?> aVar, boolean z, boolean z2) {
        final boolean a = f.a(aVar.a());
        com.google.gson.a.b bVar = (com.google.gson.a.b) field.getAnnotation(com.google.gson.a.b.class);
        q qVar = null;
        if (bVar != null) {
            qVar = this.d.a(this.a, eVar, aVar, bVar);
        }
        final boolean z3 = qVar != null;
        if (qVar == null) {
            qVar = eVar.a((com.google.gson.b.a) aVar);
        }
        final Field field2 = field;
        final com.google.gson.e eVar2 = eVar;
        final com.google.gson.b.a<?> aVar2 = aVar;
        return new b(this, str, z, z2) {
            final /* synthetic */ i g;

            void a(com.google.gson.stream.b bVar, Object obj) throws IOException, IllegalAccessException {
                q qVar;
                Object obj2 = field2.get(obj);
                if (z3) {
                    qVar = qVar;
                } else {
                    qVar = new m(eVar2, qVar, aVar2.b());
                }
                qVar.a(bVar, obj2);
            }

            void a(com.google.gson.stream.a aVar, Object obj) throws IOException, IllegalAccessException {
                Object b = qVar.b(aVar);
                if (b != null || !a) {
                    field2.set(obj, b);
                }
            }

            public boolean a(Object obj) throws IOException, IllegalAccessException {
                if (this.i && field2.get(obj) != obj) {
                    return true;
                }
                return false;
            }
        };
    }

    private Map<String, b> a(com.google.gson.e eVar, com.google.gson.b.a<?> aVar, Class<?> cls) {
        Map<String, b> linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type b = aVar.b();
        while (cls != Object.class) {
            for (Field field : cls.getDeclaredFields()) {
                boolean a = a(field, true);
                boolean a2 = a(field, false);
                if (a || a2) {
                    field.setAccessible(true);
                    Type a3 = C$Gson$Types.a(aVar.b(), (Class) cls, field.getGenericType());
                    List a4 = a(field);
                    b bVar = null;
                    int size = a4.size();
                    int i = 0;
                    while (i < size) {
                        String str = (String) a4.get(i);
                        if (i != 0) {
                            a = false;
                        }
                        b bVar2 = (b) linkedHashMap.put(str, a(eVar, field, str, com.google.gson.b.a.a(a3), a, a2));
                        if (bVar != null) {
                            bVar2 = bVar;
                        }
                        i++;
                        bVar = bVar2;
                    }
                    if (bVar != null) {
                        throw new IllegalArgumentException(b + " declares multiple JSON fields named " + bVar.h);
                    }
                }
            }
            aVar = com.google.gson.b.a.a(C$Gson$Types.a(aVar.b(), (Class) cls, cls.getGenericSuperclass()));
            cls = aVar.a();
        }
        return linkedHashMap;
    }
}
