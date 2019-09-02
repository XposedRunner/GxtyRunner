package cn.jiguang.d.d;

import cn.jiguang.g.k;
import java.io.Serializable;

public final class a implements Serializable {
    public String a = "";
    public String b = "";
    public int c = 0;
    public String d;

    public a(String str, String str2, int i) {
        this.a = str;
        this.b = str2;
        this.c = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return (k.a(this.a) || k.a(this.b) || k.a(aVar.a) || k.a(aVar.b) || !k.a(this.a, aVar.a) || !k.a(this.b, aVar.b)) ? false : true;
    }

    public final String toString() {
        return "AWakeInfo{pk_name='" + this.a + '\'' + ", sv_name='" + this.b + '\'' + ", target_version=" + this.c + ", providerAuthority='" + this.d + '\'' + '}';
    }
}
