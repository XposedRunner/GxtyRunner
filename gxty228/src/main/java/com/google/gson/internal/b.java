package com.google.gson.internal;

import com.google.gson.JsonIOException;
import com.google.gson.b.a;
import com.google.gson.g;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/* compiled from: ConstructorConstructor */
public final class b {
    private final Map<Type, g<?>> a;

    public b(Map<Type, g<?>> map) {
        this.a = map;
    }

    public <T> e<T> a(a<T> aVar) {
        final Type b = aVar.b();
        Class a = aVar.a();
        final g gVar = (g) this.a.get(b);
        if (gVar != null) {
            return new e<T>(this) {
                final /* synthetic */ b c;

                public T a() {
                    return gVar.a(b);
                }
            };
        }
        gVar = (g) this.a.get(a);
        if (gVar != null) {
            return new e<T>(this) {
                final /* synthetic */ b c;

                public T a() {
                    return gVar.a(b);
                }
            };
        }
        e<T> a2 = a(a);
        if (a2 != null) {
            return a2;
        }
        a2 = a(b, a);
        return a2 == null ? b(b, a) : a2;
    }

    private <T> e<T> a(Class<? super T> cls) {
        try {
            final Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new e<T>(this) {
                final /* synthetic */ b b;

                public T a() {
                    try {
                        return declaredConstructor.newInstance(null);
                    } catch (Throwable e) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", e);
                    } catch (InvocationTargetException e2) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", e2.getTargetException());
                    } catch (IllegalAccessException e3) {
                        throw new AssertionError(e3);
                    }
                }
            };
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private <T> e<T> a(final Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            if (SortedSet.class.isAssignableFrom(cls)) {
                return new e<T>(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public T a() {
                        return new TreeSet();
                    }
                };
            }
            if (EnumSet.class.isAssignableFrom(cls)) {
                return new e<T>(this) {
                    final /* synthetic */ b b;

                    public T a() {
                        if (type instanceof ParameterizedType) {
                            Type type = ((ParameterizedType) type).getActualTypeArguments()[0];
                            if (type instanceof Class) {
                                return EnumSet.noneOf((Class) type);
                            }
                            throw new JsonIOException("Invalid EnumSet type: " + type.toString());
                        }
                        throw new JsonIOException("Invalid EnumSet type: " + type.toString());
                    }
                };
            }
            if (Set.class.isAssignableFrom(cls)) {
                return new e<T>(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public T a() {
                        return new LinkedHashSet();
                    }
                };
            }
            if (Queue.class.isAssignableFrom(cls)) {
                return new e<T>(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public T a() {
                        return new ArrayDeque();
                    }
                };
            }
            return new e<T>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public T a() {
                    return new ArrayList();
                }
            };
        } else if (!Map.class.isAssignableFrom(cls)) {
            return null;
        } else {
            if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
                return new e<T>(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public T a() {
                        return new ConcurrentSkipListMap();
                    }
                };
            }
            if (ConcurrentMap.class.isAssignableFrom(cls)) {
                return new e<T>(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public T a() {
                        return new ConcurrentHashMap();
                    }
                };
            }
            if (SortedMap.class.isAssignableFrom(cls)) {
                return new e<T>(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public T a() {
                        return new TreeMap();
                    }
                };
            }
            if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(a.a(((ParameterizedType) type).getActualTypeArguments()[0]).a())) {
                return new e<T>(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public T a() {
                        return new LinkedTreeMap();
                    }
                };
            }
            return new e<T>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public T a() {
                    return new LinkedHashMap();
                }
            };
        }
    }

    private <T> e<T> b(final Type type, final Class<? super T> cls) {
        return new e<T>(this) {
            final /* synthetic */ b c;
            private final h d = h.a();

            public T a() {
                try {
                    return this.d.a(cls);
                } catch (Throwable e) {
                    throw new RuntimeException("Unable to invoke no-args constructor for " + type + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", e);
                }
            }
        };
    }

    public String toString() {
        return this.a.toString();
    }
}
