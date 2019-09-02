package cn.jiguang.d.b;

import cn.jiguang.e.d;
import cn.jiguang.g.a;

public final class h {
    int a;
    int b = 0;
    long c;
    byte[] d;
    int e;
    String f;

    public h(byte[] bArr, String str, int i) {
        int i2 = 0;
        this.d = bArr;
        this.a = i;
        this.f = str;
        if (bArr == null || bArr.length < 24) {
            d.h("RequestCacheManager", "parse requesting failed");
            return;
        }
        this.e = a.a(bArr[3]);
        this.c = 0;
        while (i2 < 8) {
            this.c = (this.c << 8) + ((long) (bArr[i2 + 4] & 255));
            i2++;
        }
        d.a("RequestCacheManager", "requesting command:" + this.e + ",rid:" + this.c + ",sdktype:" + str);
    }

    public final String a() {
        return g.b(this.c, this.f);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        h hVar = (h) obj;
        return this.c != hVar.c ? false : this.e != hVar.e ? false : this.f != null ? this.f.equals(hVar.f) : hVar.f == null;
    }

    public final int hashCode() {
        return (this.f != null ? this.f.hashCode() : 0) + ((((((int) (this.c ^ (this.c >>> 32))) + 31) * 31) + this.e) * 31);
    }

    public final String toString() {
        return "Requesting{timeout=" + this.a + ", times=" + this.b + ", rid=" + this.c + ", command=" + this.e + ", sdkType='" + this.f + '\'' + '}';
    }
}
