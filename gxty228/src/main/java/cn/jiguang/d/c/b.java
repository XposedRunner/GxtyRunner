package cn.jiguang.d.c;

public final class b {
    private c[] a = new c[17];

    public final int a(j jVar) {
        int i = -1;
        for (c cVar = this.a[(jVar.hashCode() & Integer.MAX_VALUE) % 17]; cVar != null; cVar = cVar.c) {
            if (cVar.a.equals(jVar)) {
                i = cVar.b;
            }
        }
        return i;
    }

    public final void a(int i, j jVar) {
        if (i <= 16383) {
            int hashCode = (jVar.hashCode() & Integer.MAX_VALUE) % 17;
            c cVar = new c();
            cVar.a = jVar;
            cVar.b = i;
            cVar.c = this.a[hashCode];
            this.a[hashCode] = cVar;
        }
    }
}
