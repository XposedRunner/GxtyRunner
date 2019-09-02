package com.google.gson;

import com.google.gson.internal.a.n;
import com.google.gson.internal.c;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: GsonBuilder */
public final class f {
    private c a = c.a;
    private LongSerializationPolicy b = LongSerializationPolicy.DEFAULT;
    private d c = FieldNamingPolicy.IDENTITY;
    private final Map<Type, g<?>> d = new HashMap();
    private final List<r> e = new ArrayList();
    private final List<r> f = new ArrayList();
    private boolean g = false;
    private String h;
    private int i = 2;
    private int j = 2;
    private boolean k = false;
    private boolean l = false;
    private boolean m = true;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;

    public f a(b... bVarArr) {
        for (b a : bVarArr) {
            this.a = this.a.a(a, true, true);
        }
        return this;
    }

    public e a() {
        List arrayList = new ArrayList((this.e.size() + this.f.size()) + 3);
        arrayList.addAll(this.e);
        Collections.reverse(arrayList);
        Collections.reverse(this.f);
        arrayList.addAll(this.f);
        a(this.h, this.i, this.j, arrayList);
        return new e(this.a, this.c, this.d, this.g, this.k, this.o, this.m, this.n, this.p, this.l, this.b, arrayList);
    }

    private void a(String str, int i, int i2, List<r> list) {
        q aVar;
        q aVar2;
        q aVar3;
        if (str != null && !"".equals(str.trim())) {
            aVar = new a(Date.class, str);
            aVar2 = new a(Timestamp.class, str);
            aVar3 = new a(java.sql.Date.class, str);
        } else if (i != 2 && i2 != 2) {
            aVar = new a(Date.class, i, i2);
            aVar2 = new a(Timestamp.class, i, i2);
            aVar3 = new a(java.sql.Date.class, i, i2);
        } else {
            return;
        }
        list.add(n.a(Date.class, aVar));
        list.add(n.a(Timestamp.class, aVar2));
        list.add(n.a(java.sql.Date.class, aVar3));
    }
}
