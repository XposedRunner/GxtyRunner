package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* compiled from: OfflineDBOperation */
public class ba {
    private static volatile ba a;
    private static he b;
    private Context c;

    public static ba a(Context context) {
        if (a == null) {
            synchronized (ba.class) {
                if (a == null) {
                    a = new ba(context);
                }
            }
        }
        return a;
    }

    private ba(Context context) {
        this.c = context;
        b = b(this.c);
    }

    private he b(Context context) {
        try {
            return new he(context, az.a());
        } catch (Throwable th) {
            gz.c(th, "OfflineDB", "getDB");
            th.printStackTrace();
            return null;
        }
    }

    private boolean b() {
        if (b == null) {
            b = b(this.c);
        }
        if (b == null) {
            return false;
        }
        return true;
    }

    public ArrayList<av> a() {
        ArrayList<av> arrayList = new ArrayList();
        if (!b()) {
            return arrayList;
        }
        for (av add : b.b("", av.class)) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public synchronized av a(String str) {
        av avVar = null;
        synchronized (this) {
            if (b()) {
                List b = b.b(ay.e(str), av.class);
                if (b.size() > 0) {
                    avVar = (av) b.get(0);
                }
            }
        }
        return avVar;
    }

    public synchronized void a(av avVar) {
        if (b()) {
            b.a((Object) avVar, ay.f(avVar.i()));
            a(avVar.f(), avVar.b());
        }
    }

    private void a(String str, String str2) {
        if (str2 != null && str2.length() > 0) {
            String a = ax.a(str);
            if (b.b(a, ax.class).size() > 0) {
                b.a(a, ax.class);
            }
            String[] split = str2.split(";");
            List arrayList = new ArrayList();
            for (String axVar : split) {
                arrayList.add(new ax(str, axVar));
            }
            b.a(arrayList);
        }
    }

    public synchronized List<String> b(String str) {
        List arrayList;
        arrayList = new ArrayList();
        if (b()) {
            arrayList.addAll(a(b.b(ax.a(str), ax.class)));
        }
        return arrayList;
    }

    private List<String> a(List<ax> list) {
        List<String> arrayList = new ArrayList();
        if (list.size() > 0) {
            for (ax a : list) {
                arrayList.add(a.a());
            }
        }
        return arrayList;
    }

    public synchronized void c(String str) {
        if (b()) {
            b.a(ay.e(str), ay.class);
            b.a(ax.a(str), ax.class);
            b.a(aw.a(str), aw.class);
        }
    }

    public synchronized void b(av avVar) {
        if (b()) {
            b.a(ay.f(avVar.i()), ay.class);
            b.a(ax.a(avVar.f()), ax.class);
            b.a(aw.a(avVar.f()), aw.class);
        }
    }

    public void a(String str, int i, long j, long j2, long j3) {
        if (b()) {
            a(str, i, j, new long[]{j2, 0, 0, 0, 0}, new long[]{j3, 0, 0, 0, 0});
        }
    }

    public synchronized void a(String str, int i, long j, long[] jArr, long[] jArr2) {
        if (b()) {
            b.a(new aw(str, j, i, jArr[0], jArr2[0]), aw.a(str));
        }
    }

    public synchronized String d(String str) {
        String str2;
        str2 = null;
        if (b()) {
            List b = b.b(ay.f(str), ay.class);
            if (b.size() > 0) {
                str2 = ((ay) b.get(0)).e();
            }
        }
        return str2;
    }
}
