package cn.jiguang.d.c;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class l implements Serializable {
    private List a;
    private short b;
    private short c;

    public l() {
        this.a = new ArrayList(1);
        this.b = (short) 0;
        this.c = (short) 0;
    }

    public l(m mVar) {
        this();
        b(mVar);
    }

    private static String a(Iterator it) {
        StringBuffer stringBuffer = new StringBuffer();
        while (it.hasNext()) {
            m mVar = (m) it.next();
            stringBuffer.append("[");
            stringBuffer.append(mVar.a());
            stringBuffer.append("]");
            if (it.hasNext()) {
                stringBuffer.append(" ");
            }
        }
        return stringBuffer.toString();
    }

    private synchronized Iterator a(boolean z, boolean z2) {
        Iterator it;
        int i = 0;
        synchronized (this) {
            int size = this.a.size();
            short s = z ? size - this.b : this.b;
            if (s == (short) 0) {
                it = Collections.EMPTY_LIST.iterator();
            } else {
                if (!z) {
                    i = size - this.b;
                } else if (z2) {
                    if (this.c >= s) {
                        this.c = (short) 0;
                    }
                    i = this.c;
                    this.c = (short) (i + 1);
                }
                List arrayList = new ArrayList(s);
                if (z) {
                    arrayList.addAll(this.a.subList(i, s));
                    if (i != 0) {
                        arrayList.addAll(this.a.subList(0, i));
                    }
                } else {
                    arrayList.addAll(this.a.subList(i, size));
                }
                it = arrayList.iterator();
            }
        }
        return it;
    }

    private void b(m mVar) {
        if (this.b == (short) 0) {
            this.a.add(mVar);
        } else {
            this.a.add(this.a.size() - this.b, mVar);
        }
    }

    private synchronized long c() {
        return b().f();
    }

    public final synchronized Iterator a() {
        return a(true, true);
    }

    public final synchronized void a(m mVar) {
        if (this.a.size() == 0) {
            b(mVar);
        } else {
            m b = b();
            if (mVar.a(b)) {
                if (mVar.f() != b.f()) {
                    if (mVar.f() > b.f()) {
                        mVar = mVar.g();
                        mVar.a(b.f());
                    } else {
                        for (int i = 0; i < this.a.size(); i++) {
                            b = ((m) this.a.get(i)).g();
                            b.a(mVar.f());
                            this.a.set(i, b);
                        }
                    }
                }
                if (!this.a.contains(mVar)) {
                    b(mVar);
                }
            } else {
                throw new IllegalArgumentException("record does not match rrset");
            }
        }
    }

    public final synchronized m b() {
        if (this.a.size() == 0) {
            throw new IllegalStateException("rrset is empty");
        }
        return (m) this.a.get(0);
    }

    public final String toString() {
        if (this.a.size() == 0) {
            return "{empty}";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{ ");
        stringBuffer.append(b().b() + " ");
        stringBuffer.append(c() + " ");
        stringBuffer.append(a(a(true, false)));
        if (this.b > (short) 0) {
            stringBuffer.append(" sigs: ");
            stringBuffer.append(a(a(false, false)));
        }
        stringBuffer.append(" }");
        return stringBuffer.toString();
    }
}
