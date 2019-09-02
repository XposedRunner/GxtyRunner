package cn.jiguang.g.b;

import android.content.ContentValues;
import cn.jiguang.e.d;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public final class b {
    private static final Map<String, b> a = new ConcurrentHashMap();
    private static final Object d = new Object();
    private final HashMap<String, Serializable> b = new HashMap();
    private final String c;

    private b(String str) {
        this.c = str;
    }

    public static b a(String str) {
        b bVar = (b) a.get(str);
        if (bVar == null) {
            synchronized (d) {
                bVar = (b) a.get(str);
                if (bVar == null) {
                    bVar = new b(str);
                    a.put(str, bVar);
                }
            }
        }
        return bVar;
    }

    public final Serializable a(String str, int i) {
        Serializable serializable = (Serializable) this.b.get(str);
        return a.a(serializable) == i ? serializable : null;
    }

    public final void a() {
        this.b.clear();
    }

    public final void a(ContentValues contentValues) {
        for (Entry entry : contentValues.valueSet()) {
            a((String) entry.getKey(), entry.getValue());
        }
    }

    public final void a(String str, Object obj) {
        if (obj == null) {
            this.b.remove(str);
        } else if (obj instanceof Serializable) {
            Object obj2;
            Serializable serializable = (Serializable) obj;
            if (this.b.size() > 512) {
                obj2 = null;
            } else if (serializable instanceof String) {
                if (((String) serializable).length() < 2048) {
                    r0 = 1;
                } else {
                    obj2 = null;
                }
            } else if (serializable instanceof HashSet) {
                obj2 = null;
            } else {
                r0 = 1;
            }
            if (obj2 != null) {
                this.b.put(str, (Serializable) obj);
            } else {
                d.g("SpCache", "large memory cost : size=" + this.b.size() + " value=" + obj);
            }
        }
    }
}
