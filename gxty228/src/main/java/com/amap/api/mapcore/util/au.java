package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.maps.AMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/* compiled from: TaskManager */
public class au {
    private static au a;
    private kb b;
    private LinkedHashMap<String, kc> c = new LinkedHashMap();
    private boolean d = true;

    public static au a(int i) {
        return a(true, i);
    }

    private static synchronized au a(boolean z, int i) {
        au auVar;
        synchronized (au.class) {
            try {
                if (a == null) {
                    a = new au(z, i);
                } else if (z) {
                    if (a.b == null) {
                        a.b = kb.a(i);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            auVar = a;
        }
        return auVar;
    }

    private au(boolean z, int i) {
        if (z) {
            try {
                this.b = kb.a(i);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a() {
        synchronized (this.c) {
            if (this.c.size() < 1) {
                return;
            }
            for (Entry entry : this.c.entrySet()) {
                entry.getKey();
                ((aq) entry.getValue()).a();
            }
            this.c.clear();
        }
    }

    public void a(at atVar) {
        synchronized (this.c) {
            aq aqVar = (aq) this.c.get(atVar.b());
            if (aqVar == null) {
                return;
            }
            aqVar.a();
            this.c.remove(atVar.b());
        }
    }

    public void a(at atVar, Context context, AMap aMap) throws gp {
        if (this.b == null) {
        }
        if (!this.c.containsKey(atVar.b())) {
            aq aqVar = new aq((bm) atVar, context.getApplicationContext(), aMap);
            synchronized (this.c) {
                this.c.put(atVar.b(), aqVar);
            }
        }
        this.b.a((kc) this.c.get(atVar.b()));
    }

    public void b() {
        a();
        kb.a();
        this.b = null;
        c();
    }

    public static void c() {
        a = null;
    }

    public void b(at atVar) {
        aq aqVar = (aq) this.c.get(atVar.b());
        if (aqVar != null) {
            synchronized (this.c) {
                aqVar.b();
                this.c.remove(atVar.b());
            }
        }
    }
}
