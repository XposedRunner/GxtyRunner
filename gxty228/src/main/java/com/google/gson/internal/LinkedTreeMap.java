package com.google.gson.internal;

import cn.jiguang.net.HttpUtils;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = (!LinkedTreeMap.class.desiredAssertionStatus());
    private static final Comparator<Comparable> a = new Comparator<Comparable>() {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((Comparable) obj, (Comparable) obj2);
        }

        public int a(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };
    Comparator<? super K> comparator;
    private a entrySet;
    final d<K, V> header;
    private b keySet;
    int modCount;
    d<K, V> root;
    int size;

    private abstract class c<T> implements Iterator<T> {
        d<K, V> b = this.e.header.d;
        d<K, V> c = null;
        int d = this.e.modCount;
        final /* synthetic */ LinkedTreeMap e;

        c(LinkedTreeMap linkedTreeMap) {
            this.e = linkedTreeMap;
        }

        public final boolean hasNext() {
            return this.b != this.e.header;
        }

        final d<K, V> b() {
            d<K, V> dVar = this.b;
            if (dVar == this.e.header) {
                throw new NoSuchElementException();
            } else if (this.e.modCount != this.d) {
                throw new ConcurrentModificationException();
            } else {
                this.b = dVar.d;
                this.c = dVar;
                return dVar;
            }
        }

        public final void remove() {
            if (this.c == null) {
                throw new IllegalStateException();
            }
            this.e.removeInternal(this.c, true);
            this.c = null;
            this.d = this.e.modCount;
        }
    }

    class a extends AbstractSet<Entry<K, V>> {
        final /* synthetic */ LinkedTreeMap a;

        a(LinkedTreeMap linkedTreeMap) {
            this.a = linkedTreeMap;
        }

        public int size() {
            return this.a.size;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new c<Entry<K, V>>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r2;
                    LinkedTreeMap linkedTreeMap = r2.a;
                }

                public /* synthetic */ Object next() {
                    return a();
                }

                public Entry<K, V> a() {
                    return b();
                }
            };
        }

        public boolean contains(Object obj) {
            return (obj instanceof Entry) && this.a.findByEntry((Entry) obj) != null;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            d findByEntry = this.a.findByEntry((Entry) obj);
            if (findByEntry == null) {
                return false;
            }
            this.a.removeInternal(findByEntry, true);
            return true;
        }

        public void clear() {
            this.a.clear();
        }
    }

    final class b extends AbstractSet<K> {
        final /* synthetic */ LinkedTreeMap a;

        b(LinkedTreeMap linkedTreeMap) {
            this.a = linkedTreeMap;
        }

        public int size() {
            return this.a.size;
        }

        public Iterator<K> iterator() {
            return new c<K>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r2;
                    LinkedTreeMap linkedTreeMap = r2.a;
                }

                public K next() {
                    return b().f;
                }
            };
        }

        public boolean contains(Object obj) {
            return this.a.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return this.a.removeInternalByKey(obj) != null;
        }

        public void clear() {
            this.a.clear();
        }
    }

    static final class d<K, V> implements Entry<K, V> {
        d<K, V> a;
        d<K, V> b;
        d<K, V> c;
        d<K, V> d;
        d<K, V> e;
        final K f;
        V g;
        int h;

        d() {
            this.f = null;
            this.e = this;
            this.d = this;
        }

        d(d<K, V> dVar, K k, d<K, V> dVar2, d<K, V> dVar3) {
            this.a = dVar;
            this.f = k;
            this.h = 1;
            this.d = dVar2;
            this.e = dVar3;
            dVar3.d = this;
            dVar2.e = this;
        }

        public K getKey() {
            return this.f;
        }

        public V getValue() {
            return this.g;
        }

        public V setValue(V v) {
            V v2 = this.g;
            this.g = v;
            return v2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.f == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!this.f.equals(entry.getKey())) {
                return false;
            }
            if (this.g == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!this.g.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.f == null ? 0 : this.f.hashCode();
            if (this.g != null) {
                i = this.g.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            return this.f + HttpUtils.EQUAL_SIGN + this.g;
        }

        public d<K, V> a() {
            d<K, V> dVar;
            for (d<K, V> dVar2 = this.b; dVar2 != null; dVar2 = dVar2.b) {
                dVar = dVar2;
            }
            return dVar;
        }

        public d<K, V> b() {
            d<K, V> dVar;
            for (d<K, V> dVar2 = this.c; dVar2 != null; dVar2 = dVar2.c) {
                dVar = dVar2;
            }
            return dVar;
        }
    }

    public LinkedTreeMap() {
        this(a);
    }

    public LinkedTreeMap(Comparator<? super K> comparator) {
        this.size = 0;
        this.modCount = 0;
        this.header = new d();
        if (comparator == null) {
            comparator = a;
        }
        this.comparator = comparator;
    }

    public int size() {
        return this.size;
    }

    public V get(Object obj) {
        d findByObject = findByObject(obj);
        return findByObject != null ? findByObject.g : null;
    }

    public boolean containsKey(Object obj) {
        return findByObject(obj) != null;
    }

    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        d find = find(k, true);
        V v2 = find.g;
        find.g = v;
        return v2;
    }

    public void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        d dVar = this.header;
        dVar.e = dVar;
        dVar.d = dVar;
    }

    public V remove(Object obj) {
        d removeInternalByKey = removeInternalByKey(obj);
        return removeInternalByKey != null ? removeInternalByKey.g : null;
    }

    d<K, V> find(K k, boolean z) {
        int i;
        Comparator comparator = this.comparator;
        d<K, V> dVar = this.root;
        if (dVar != null) {
            int compareTo;
            Comparable comparable = comparator == a ? (Comparable) k : null;
            while (true) {
                if (comparable != null) {
                    compareTo = comparable.compareTo(dVar.f);
                } else {
                    compareTo = comparator.compare(k, dVar.f);
                }
                if (compareTo == 0) {
                    return dVar;
                }
                d<K, V> dVar2 = compareTo < 0 ? dVar.b : dVar.c;
                if (dVar2 == null) {
                    break;
                }
                dVar = dVar2;
            }
            int i2 = compareTo;
            d dVar3 = dVar;
            i = i2;
        } else {
            d<K, V> dVar4 = dVar;
            i = 0;
        }
        if (!z) {
            return null;
        }
        d<K, V> dVar5;
        d dVar6 = this.header;
        if (dVar3 != null) {
            dVar5 = new d(dVar3, k, dVar6, dVar6.e);
            if (i < 0) {
                dVar3.b = dVar5;
            } else {
                dVar3.c = dVar5;
            }
            a(dVar3, true);
        } else if (comparator != a || (k instanceof Comparable)) {
            dVar5 = new d(dVar3, k, dVar6, dVar6.e);
            this.root = dVar5;
        } else {
            throw new ClassCastException(k.getClass().getName() + " is not Comparable");
        }
        this.size++;
        this.modCount++;
        return dVar5;
    }

    d<K, V> findByObject(Object obj) {
        d<K, V> dVar = null;
        if (obj != null) {
            try {
                dVar = find(obj, false);
            } catch (ClassCastException e) {
            }
        }
        return dVar;
    }

    d<K, V> findByEntry(Entry<?, ?> entry) {
        d<K, V> findByObject = findByObject(entry.getKey());
        Object obj = (findByObject == null || !a(findByObject.g, entry.getValue())) ? null : 1;
        return obj != null ? findByObject : null;
    }

    private boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    void removeInternal(d<K, V> dVar, boolean z) {
        int i = 0;
        if (z) {
            dVar.e.d = dVar.d;
            dVar.d.e = dVar.e;
        }
        d dVar2 = dVar.b;
        d dVar3 = dVar.c;
        d dVar4 = dVar.a;
        if (dVar2 == null || dVar3 == null) {
            if (dVar2 != null) {
                a((d) dVar, dVar2);
                dVar.b = null;
            } else if (dVar3 != null) {
                a((d) dVar, dVar3);
                dVar.c = null;
            } else {
                a((d) dVar, null);
            }
            a(dVar4, false);
            this.size--;
            this.modCount++;
            return;
        }
        int i2;
        dVar2 = dVar2.h > dVar3.h ? dVar2.b() : dVar3.a();
        removeInternal(dVar2, false);
        dVar4 = dVar.b;
        if (dVar4 != null) {
            i2 = dVar4.h;
            dVar2.b = dVar4;
            dVar4.a = dVar2;
            dVar.b = null;
        } else {
            i2 = 0;
        }
        dVar4 = dVar.c;
        if (dVar4 != null) {
            i = dVar4.h;
            dVar2.c = dVar4;
            dVar4.a = dVar2;
            dVar.c = null;
        }
        dVar2.h = Math.max(i2, i) + 1;
        a((d) dVar, dVar2);
    }

    d<K, V> removeInternalByKey(Object obj) {
        d<K, V> findByObject = findByObject(obj);
        if (findByObject != null) {
            removeInternal(findByObject, true);
        }
        return findByObject;
    }

    private void a(d<K, V> dVar, d<K, V> dVar2) {
        d dVar3 = dVar.a;
        dVar.a = null;
        if (dVar2 != null) {
            dVar2.a = dVar3;
        }
        if (dVar3 == null) {
            this.root = dVar2;
        } else if (dVar3.b == dVar) {
            dVar3.b = dVar2;
        } else if ($assertionsDisabled || dVar3.c == dVar) {
            dVar3.c = dVar2;
        } else {
            throw new AssertionError();
        }
    }

    private void a(d<K, V> dVar, boolean z) {
        while (dVar != null) {
            int i;
            d dVar2 = dVar.b;
            d dVar3 = dVar.c;
            int i2 = dVar2 != null ? dVar2.h : 0;
            if (dVar3 != null) {
                i = dVar3.h;
            } else {
                i = 0;
            }
            int i3 = i2 - i;
            d dVar4;
            if (i3 == -2) {
                dVar2 = dVar3.b;
                dVar4 = dVar3.c;
                if (dVar4 != null) {
                    i2 = dVar4.h;
                } else {
                    i2 = 0;
                }
                if (dVar2 != null) {
                    i = dVar2.h;
                } else {
                    i = 0;
                }
                i -= i2;
                if (i == -1 || (i == 0 && !z)) {
                    a(dVar);
                } else if ($assertionsDisabled || i == 1) {
                    b(dVar3);
                    a(dVar);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                dVar3 = dVar2.b;
                dVar4 = dVar2.c;
                i2 = dVar4 != null ? dVar4.h : 0;
                if (dVar3 != null) {
                    i = dVar3.h;
                } else {
                    i = 0;
                }
                i -= i2;
                if (i == 1 || (i == 0 && !z)) {
                    b(dVar);
                } else if ($assertionsDisabled || i == -1) {
                    a(dVar2);
                    b(dVar);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                dVar.h = i2 + 1;
                if (z) {
                    return;
                }
            } else if ($assertionsDisabled || i3 == -1 || i3 == 1) {
                dVar.h = Math.max(i2, i) + 1;
                if (!z) {
                    return;
                }
            } else {
                throw new AssertionError();
            }
            dVar = dVar.a;
        }
    }

    private void a(d<K, V> dVar) {
        int i;
        int i2 = 0;
        d dVar2 = dVar.b;
        d dVar3 = dVar.c;
        d dVar4 = dVar3.b;
        d dVar5 = dVar3.c;
        dVar.c = dVar4;
        if (dVar4 != null) {
            dVar4.a = dVar;
        }
        a((d) dVar, dVar3);
        dVar3.b = dVar;
        dVar.a = dVar3;
        if (dVar2 != null) {
            i = dVar2.h;
        } else {
            i = 0;
        }
        dVar.h = Math.max(i, dVar4 != null ? dVar4.h : 0) + 1;
        int i3 = dVar.h;
        if (dVar5 != null) {
            i2 = dVar5.h;
        }
        dVar3.h = Math.max(i3, i2) + 1;
    }

    private void b(d<K, V> dVar) {
        int i;
        int i2 = 0;
        d dVar2 = dVar.b;
        d dVar3 = dVar.c;
        d dVar4 = dVar2.b;
        d dVar5 = dVar2.c;
        dVar.b = dVar5;
        if (dVar5 != null) {
            dVar5.a = dVar;
        }
        a((d) dVar, dVar2);
        dVar2.c = dVar;
        dVar.a = dVar2;
        if (dVar3 != null) {
            i = dVar3.h;
        } else {
            i = 0;
        }
        dVar.h = Math.max(i, dVar5 != null ? dVar5.h : 0) + 1;
        int i3 = dVar.h;
        if (dVar4 != null) {
            i2 = dVar4.h;
        }
        dVar2.h = Math.max(i3, i2) + 1;
    }

    public Set<Entry<K, V>> entrySet() {
        Set set = this.entrySet;
        if (set != null) {
            return set;
        }
        Set<Entry<K, V>> aVar = new a(this);
        this.entrySet = aVar;
        return aVar;
    }

    public Set<K> keySet() {
        Set set = this.keySet;
        if (set != null) {
            return set;
        }
        Set<K> bVar = new b(this);
        this.keySet = bVar;
        return bVar;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }
}
