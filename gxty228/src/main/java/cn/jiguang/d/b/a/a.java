package cn.jiguang.d.b.a;

import android.text.TextUtils;
import cn.jiguang.d.b.i;
import cn.jiguang.d.c.p;
import cn.jiguang.d.d.c;
import cn.jiguang.e.d;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public final class a {
    private LinkedHashMap<c, String> a = new LinkedHashMap();

    public static a a(i iVar) {
        a aVar = new a();
        if (iVar == null) {
            return aVar;
        }
        for (String a : iVar.f()) {
            c a2 = c.a(a);
            if (a2 != null) {
                aVar.a(a2.a, a2.b, "default");
            }
        }
        return aVar;
    }

    public static a a(String str) {
        a aVar = new a();
        if (!TextUtils.isEmpty(str)) {
            for (Object obj : str.split("-")) {
                if (!TextUtils.isEmpty(obj)) {
                    String[] split = obj.split(":");
                    if (split.length > 2) {
                        int intValue;
                        try {
                            intValue = Integer.decode(split[1]).intValue();
                        } catch (Exception e) {
                            intValue = 0;
                        }
                        aVar.a(split[0], intValue, split[2]);
                    }
                }
            }
        }
        d.c("AddressList", "action - fromString, addressesList:" + aVar);
        return aVar;
    }

    public static a a(List<p> list, boolean z) {
        a aVar = new a();
        if (list == null || list.isEmpty()) {
            return aVar;
        }
        for (p pVar : list) {
            String jVar = pVar.i().toString();
            if (!TextUtils.isEmpty(jVar) && jVar.endsWith(".")) {
                jVar = jVar.substring(0, jVar.length() - 1);
            }
            if (z) {
                InetAddress b;
                try {
                    b = c.b(jVar);
                } catch (Exception e) {
                    b = null;
                }
                if (b != null) {
                    jVar = b.getHostAddress();
                }
            }
            aVar.a(jVar, pVar.h(), "srv record");
        }
        d.c("AddressList", "action - fromSrvRecords, addressList:" + aVar);
        return aVar;
    }

    public static a b(i iVar) {
        a aVar = new a();
        if (iVar == null) {
            return aVar;
        }
        aVar.a(iVar.a(), iVar.b(), "main");
        List c = iVar.c();
        List d = iVar.d();
        if (c == null || d == null) {
            return aVar;
        }
        int i = 0;
        while (i < c.size() && i < d.size()) {
            aVar.a((String) c.get(i), ((Integer) d.get(i)).intValue(), "option" + i);
            i++;
        }
        return aVar;
    }

    public final void a(String str, int i, String str2) {
        if (c.a(str, i)) {
            this.a.put(new c(str, i), str2);
        }
    }

    public final boolean a() {
        return this.a == null || this.a.isEmpty();
    }

    public final boolean a(c cVar) {
        return this.a.containsKey(cVar);
    }

    public final Iterator<Entry<c, String>> b() {
        return this.a.entrySet().iterator();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return this.a != null ? this.a.equals(aVar.a) : aVar.a == null;
    }

    public final int hashCode() {
        return this.a != null ? this.a.hashCode() : 0;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.a != null) {
            for (Entry entry : this.a.entrySet()) {
                stringBuilder.append(((c) entry.getKey()).toString()).append(":").append((String) entry.getValue()).append("-");
            }
            if (!this.a.isEmpty()) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        }
        return stringBuilder.toString();
    }
}
