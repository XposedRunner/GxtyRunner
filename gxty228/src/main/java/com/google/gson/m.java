package com.google.gson;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: JsonObject */
public final class m extends k {
    private final LinkedTreeMap<String, k> a = new LinkedTreeMap();

    public void a(String str, k kVar) {
        if (kVar == null) {
            kVar = l.a;
        }
        this.a.put(str, kVar);
    }

    public Set<Entry<String, k>> o() {
        return this.a.entrySet();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof m) && ((m) obj).a.equals(this.a));
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
