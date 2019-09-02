package com.lzy.okgo.d;

import java.util.ArrayList;
import java.util.List;

/* compiled from: TableEntity */
public class f {
    public String a;
    private List<c> b = new ArrayList();

    public f(String str) {
        this.a = str;
    }

    public f a(c cVar) {
        this.b.add(cVar);
        return this;
    }

    public String a() {
        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append(this.a).append('(');
        for (c cVar : this.b) {
            if (cVar.c != null) {
                stringBuilder.append("PRIMARY KEY (");
                for (String append : cVar.c) {
                    stringBuilder.append(append).append(",");
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.append(")");
            } else {
                stringBuilder.append(cVar.a).append(" ").append(cVar.b);
                if (cVar.e) {
                    stringBuilder.append(" NOT NULL");
                }
                if (cVar.d) {
                    stringBuilder.append(" PRIMARY KEY");
                }
                if (cVar.f) {
                    stringBuilder.append(" AUTOINCREMENT");
                }
                stringBuilder.append(",");
            }
        }
        if (stringBuilder.toString().endsWith(",")) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    public int b() {
        return this.b.size();
    }

    public int a(String str) {
        int b = b();
        for (int i = 0; i < b; i++) {
            if (((c) this.b.get(i)).a.equals(str)) {
                return i;
            }
        }
        return -1;
    }
}
