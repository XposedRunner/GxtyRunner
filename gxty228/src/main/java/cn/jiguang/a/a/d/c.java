package cn.jiguang.a.a.d;

import android.app.Application;
import android.content.Context;
import cn.jiguang.d.a.d;
import cn.jiguang.d.d.o;
import cn.jiguang.g.a;
import cn.jiguang.g.k;
import com.lzy.okgo.model.Progress;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    public static boolean a = false;
    public static boolean b = false;
    private static volatile c c = null;
    private ExecutorService d = Executors.newSingleThreadExecutor();
    private String e = null;
    private String f = null;
    private ArrayList<a> g = new ArrayList();
    private long h = 30;
    private long i = 0;
    private long j = 0;
    private boolean k = false;
    private boolean l = true;
    private boolean m = true;
    private boolean n = false;
    private boolean o = true;
    private long p = 0;
    private WeakReference<JSONObject> q = null;
    private JSONObject r = null;
    private final Object s = new Object();

    private c() {
    }

    public static c a() {
        if (c == null) {
            synchronized (c.class) {
                c = new c();
            }
        }
        return c;
    }

    private JSONObject a(Context context, long j) {
        cn.jiguang.a.b.c.a().b(context, "cur_session_start", this.i);
        StringBuilder stringBuilder = new StringBuilder();
        String i = d.i(context);
        if (!k.a(i)) {
            stringBuilder.append(i);
        }
        i = a.h(context);
        if (!k.a(i)) {
            stringBuilder.append(i);
        }
        stringBuilder.append(j);
        this.f = a.a(stringBuilder.toString());
        cn.jiguang.a.b.c.a().b(context, "session_id", this.f);
        JSONObject jSONObject = new JSONObject();
        try {
            a(jSONObject);
            o.b(context, jSONObject, "active_launch");
            jSONObject.put("session_id", this.f);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(cn.jiguang.a.a.d.c r12, android.content.Context r13) {
        /*
        r10 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r8 = -1;
        r6 = 0;
        r0 = 0;
        r1 = 1;
        r2 = r12.l;
        if (r2 == 0) goto L_0x007d;
    L_0x000b:
        r12.l = r0;
        r2 = "JPushSA";
        r3 = "statistics start";
        cn.jiguang.e.d.c(r2, r3);
        r2 = cn.jiguang.a.b.c.a();
        r3 = "last_pause";
        r2 = r2.a(r13, r3, r8);
        r4 = r12.i;
        r4 = r4 - r2;
        r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r2 == 0) goto L_0x0089;
    L_0x0025:
        r2 = r12.h;
        r2 = r2 * r10;
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 > 0) goto L_0x0089;
    L_0x002c:
        r12.k = r0;
        r0 = r12.k;
        if (r0 == 0) goto L_0x008e;
    L_0x0032:
        r0 = "JPushSA";
        r1 = "new statistics session";
        cn.jiguang.e.d.c(r0, r1);
        r0 = new org.json.JSONArray;
        r0.<init>();
        r2 = r12.i;
        r1 = r12.a(r13, r2);
        if (r1 == 0) goto L_0x0049;
    L_0x0046:
        r0.put(r1);
    L_0x0049:
        r1 = r12.s;
        monitor-enter(r1);
        r2 = r12.d(r13);	 Catch:{ all -> 0x008b }
        if (r2 == 0) goto L_0x006d;
    L_0x0052:
        r3 = r2.length();	 Catch:{ all -> 0x008b }
        if (r3 <= 0) goto L_0x006d;
    L_0x0058:
        r3 = "active_terminate";
        cn.jiguang.d.d.o.b(r13, r2, r3);	 Catch:{ Exception -> 0x009b }
    L_0x005d:
        r3 = "jpush_stat_cache.json";
        r4 = 0;
        cn.jiguang.d.d.o.a(r13, r3, r4);	 Catch:{ all -> 0x008b }
        r3 = 0;
        r12.r = r3;	 Catch:{ all -> 0x008b }
        r3 = new java.util.ArrayList;	 Catch:{ all -> 0x008b }
        r3.<init>();	 Catch:{ all -> 0x008b }
        r12.g = r3;	 Catch:{ all -> 0x008b }
    L_0x006d:
        monitor-exit(r1);	 Catch:{ all -> 0x008b }
        if (r2 == 0) goto L_0x0079;
    L_0x0070:
        r1 = r2.length();
        if (r1 <= 0) goto L_0x0079;
    L_0x0076:
        r0.put(r2);
    L_0x0079:
        cn.jiguang.d.d.o.a(r13, r0);
    L_0x007c:
        return;
    L_0x007d:
        r2 = r12.i;
        r4 = r12.j;
        r2 = r2 - r4;
        r4 = r12.h;
        r4 = r4 * r10;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x002c;
    L_0x0089:
        r0 = r1;
        goto L_0x002c;
    L_0x008b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x008b }
        throw r0;
    L_0x008e:
        r0 = cn.jiguang.a.b.c.a();
        r1 = "session_id";
        r0 = r0.a(r13, r1, r6);
        r12.f = r0;
        goto L_0x007c;
    L_0x009b:
        r3 = move-exception;
        goto L_0x005d;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.a.a.d.c.a(cn.jiguang.a.a.d.c, android.content.Context):void");
    }

    private static void a(JSONObject jSONObject) {
        String a = cn.jiguang.d.g.a.a();
        Object obj = a.split("_")[0];
        Object obj2 = a.split("_")[1];
        jSONObject.put(Progress.DATE, obj);
        jSONObject.put("time", obj2);
    }

    static /* synthetic */ void b(c cVar, Context context) {
        int i = 0;
        if (context != null) {
            synchronized (cVar.s) {
                JSONArray optJSONArray;
                cn.jiguang.a.b.c.a().b(context, "last_pause", cVar.j);
                cn.jiguang.a.b.c.a().b(context, "cur_seesion_end", cVar.j);
                if (cVar.m) {
                    cVar.m = false;
                    if (!(cVar.k || cVar.d(context) == null)) {
                        optJSONArray = cVar.d(context).optJSONArray("activities");
                        if (optJSONArray != null) {
                            Collection arrayList = new ArrayList();
                            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                try {
                                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                                    if (optJSONObject != null) {
                                        Iterator keys = optJSONObject.keys();
                                        if (keys.hasNext()) {
                                            String str = (String) keys.next();
                                            arrayList.add(new a(str, optJSONObject.getLong(str)));
                                        }
                                    }
                                } catch (Exception e) {
                                    cn.jiguang.e.d.i("JPushSA", e.getMessage());
                                }
                            }
                            arrayList.addAll(cVar.g);
                            cVar.g = new ArrayList();
                            cVar.g.addAll(arrayList);
                        }
                    }
                }
                JSONObject d = cVar.d(context);
                JSONObject jSONObject = d == null ? new JSONObject() : d;
                optJSONArray = new JSONArray();
                while (i < cVar.g.size()) {
                    a aVar = (a) cVar.g.get(i);
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put(aVar.a, aVar.b);
                        optJSONArray.put(jSONObject2);
                    } catch (JSONException e2) {
                    }
                    i++;
                }
                if (optJSONArray.length() > 0) {
                    try {
                        jSONObject.put("activities", optJSONArray);
                    } catch (JSONException e3) {
                    }
                }
                if (jSONObject.length() > 0) {
                    try {
                        long a = cn.jiguang.a.b.c.a().a(context, "cur_session_start", 0);
                        long j = 10;
                        if (a == 0) {
                            a = cVar.j - cVar.p;
                            if (a > 0) {
                                j = a / 1000;
                            }
                            cn.jiguang.a.b.c.a().b(context, "cur_session_start", cVar.p);
                        } else {
                            j = (cVar.j - a) / 1000;
                        }
                        jSONObject.put("duration", j);
                        jSONObject.put("itime", cn.jiguang.d.a.a.t());
                        jSONObject.put("session_id", cVar.f);
                        a(jSONObject);
                    } catch (Exception e4) {
                    }
                    cVar.r = jSONObject;
                    if (cVar.r != null) {
                        try {
                            o.a(context, cVar.r.toString().getBytes("utf-8").length);
                        } catch (UnsupportedEncodingException e5) {
                        } catch (Exception e6) {
                            cn.jiguang.e.d.i("JPushSA", e6.getMessage());
                        }
                    }
                    o.a(context, "jpush_stat_cache.json", jSONObject);
                }
            }
        }
    }

    private boolean c(Context context, String str) {
        if (!this.o) {
            cn.jiguang.e.d.e("JPushSA", "stat function has been disabled");
            return false;
        } else if (context == null) {
            cn.jiguang.e.d.e("JPushSA", "context is null");
            return false;
        } else if (!(context instanceof Application)) {
            return true;
        } else {
            cn.jiguang.e.d.j("JPushSA", "Context should be an Activity on this method : " + str);
            return false;
        }
    }

    private JSONObject d(Context context) {
        if (this.r == null) {
            this.r = o.a(context, "jpush_stat_cache.json");
        }
        return this.r;
    }

    public final void a(Context context) {
        if (c(context, "onResume")) {
            a = true;
            try {
                this.n = false;
            } catch (ClassCastException e) {
            } catch (Exception e2) {
            }
            if (this.n) {
                cn.jiguang.e.d.c("JPushSA", "JCoreInterface.onResume() must be called after called JCoreInterface.onPause() and JPushInterface.onResume should not be called more time in last Activity or Fragment  ");
                return;
            }
            this.n = true;
            this.i = System.currentTimeMillis();
            this.e = context.getClass().getName();
            try {
                this.d.execute(new f(this, context.getApplicationContext()));
            } catch (Throwable th) {
            }
        }
    }

    public final void a(Context context, String str) {
        if (this.n) {
            cn.jiguang.e.d.c("JPushSA", "JCoreInterface.onResume() must be called after called JCoreInterface.onPause() in last Activity or Fragment");
            return;
        }
        this.n = true;
        this.e = str;
        this.i = System.currentTimeMillis();
        try {
            this.d.execute(new d(this, context.getApplicationContext()));
        } catch (Throwable th) {
        }
    }

    public final void b(Context context) {
        if (c(context, "onPause")) {
            b = true;
            try {
                this.n = true;
            } catch (ClassCastException e) {
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (this.n) {
                this.n = false;
                if (this.e == null || !this.e.equals(context.getClass().getName())) {
                    cn.jiguang.e.d.e("JPushSA", "the activity pass by onPause didn't match last one passed by onResume");
                    return;
                }
                this.j = System.currentTimeMillis();
                this.g.add(new a(this.e, (this.j - this.i) / 1000));
                this.p = this.i;
                try {
                    this.d.execute(new g(this, context.getApplicationContext()));
                    return;
                } catch (Throwable th) {
                    return;
                }
            }
            cn.jiguang.e.d.c("JPushSA", "JCoreInterface.onPause() must be called after called JCoreInterface.onResume() and JPushInterface.onPause should not be called more time in this Activity or Fragment ; ");
        }
    }

    public final void b(Context context, String str) {
        if (this.n) {
            this.n = false;
            if (this.e == null || !this.e.equals(str)) {
                cn.jiguang.e.d.j("JPushSA", "page name didn't match the last one passed by onResume");
                return;
            }
            this.j = System.currentTimeMillis();
            this.g.add(new a(this.e, (this.j - this.i) / 1000));
            try {
                this.d.execute(new e(this, context.getApplicationContext()));
                return;
            } catch (Throwable th) {
                return;
            }
        }
        cn.jiguang.e.d.c("JPushSA", "JCoreInterface.onPause() must be called after called JCoreInterface.onResume() in this Activity or Fragment");
    }

    public final void c(Context context) {
        try {
            if (this.e != null && this.n) {
                this.j = System.currentTimeMillis();
                this.g.add(new a(this.e, (this.j - this.i) / 1000));
                try {
                    this.d.execute(new h(this, context.getApplicationContext()));
                } catch (Throwable th) {
                }
            }
        } catch (Exception e) {
        }
    }
}
