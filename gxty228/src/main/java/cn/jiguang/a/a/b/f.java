package cn.jiguang.a.a.b;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import cn.jiguang.d.a.d;
import cn.jiguang.d.d.o;
import cn.jiguang.g.a;
import cn.jiguang.g.k;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.github.mikephil.charting.utils.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class f {
    private static volatile f c;
    private static final Object d = new Object();
    public boolean a = false;
    protected Handler b;
    private Context e;
    private String f = "all";
    private boolean g = true;
    private String h = null;
    private String i = null;
    private String j = null;
    private boolean k = false;
    private a l;
    private c m;
    private h n;

    private f(Context context) {
        this.e = context;
        this.l = new a(context, this);
        this.n = new h(context);
        this.m = new c(context, this);
    }

    public static e a(Context context) {
        e eVar = null;
        if (c != null) {
            eVar = c.d();
        }
        if (eVar == null || !eVar.a()) {
            eVar = e.a(d.m(context));
        }
        if (eVar != null && eVar.a()) {
            return eVar;
        }
        return new e(200.0d, 200.0d, Utils.DOUBLE_EPSILON, 0.0f, 0.0f, "", 0, false);
    }

    public static void a(Context context, boolean z) {
        if (context == null) {
            cn.jiguang.e.d.g("MyLocationManager", "#unexcepted - context was null");
            return;
        }
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new f(context);
                }
            }
        }
        c.a = z;
        f fVar = c;
        try {
            if (fVar.b == null) {
                HandlerThread handlerThread = new HandlerThread("location");
                handlerThread.start();
                fVar.b = new g(fVar, handlerThread.getLooper());
            }
            fVar.b.sendEmptyMessage(1000);
        } catch (Throwable th) {
            cn.jiguang.e.d.i("MyLocationManager", "start load loc-info failed - error:" + th);
        }
    }

    private boolean a(JSONArray jSONArray, JSONArray jSONArray2, String str) {
        if (k.a(str)) {
            if (!k.a(this.j)) {
                return false;
            }
        } else if (!str.equals(this.j)) {
            return false;
        }
        if (k.a(this.i)) {
            if (!(jSONArray2 == null || jSONArray2.length() == 0)) {
                return false;
            }
        } else if (jSONArray2 == null) {
            return false;
        } else {
            if (jSONArray2.length() == 0) {
                return false;
            }
            if (!this.i.equals(jSONArray2.toString())) {
                return false;
            }
            cn.jiguang.e.d.e("MyLocationManager", "cell do not changed");
        }
        if (k.a(this.h)) {
            if (!(jSONArray == null || jSONArray.length() == 0)) {
                return false;
            }
        } else if (jSONArray == null) {
            return false;
        } else {
            if (jSONArray.length() == 0) {
                return false;
            }
            try {
                String optString = ((JSONObject) jSONArray.get(0)).optString("ssid");
                if (!(k.a(optString) || optString.equals(this.h))) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    static /* synthetic */ void b(f fVar) {
        fVar.l.c();
        fVar.n.a();
        fVar.m.b();
    }

    private void c() {
        JSONArray jSONArray = null;
        if (d.e(this.e)) {
            JSONArray c = this.n.c();
            JSONArray b = this.l.b();
            e d = this.a ? null : d();
            cn.jiguang.e.d.c("MyLocationManager", "gpsAddress:" + (d != null ? d.g() : ""));
            cn.jiguang.e.d.c("MyLocationManager", "wifiTower :" + String.valueOf(c));
            cn.jiguang.e.d.c("MyLocationManager", "cellTowner :" + String.valueOf(b));
            JSONObject f = d != null ? d.f() : null;
            if (f == null && b == null && c == null) {
                cn.jiguang.e.d.c("MyLocationManager", "All of location info was null, give up report");
                return;
            }
            String jSONObject = f != null ? f.toString() : "";
            if (a(c, b, jSONObject)) {
                cn.jiguang.e.d.e("MyLocationManager", "Location not changed, give up report");
                return;
            }
            if (f != null && f.length() > 0) {
                jSONArray = new JSONArray();
                jSONArray.put(f);
            }
            f = new JSONObject();
            try {
                o.b(this.e, f, "loc_info");
                f.put("network_type", a.d(this.e));
                f.put("local_dns", a.b());
                if (c != null && c.length() > 0) {
                    f.put(IXAdSystemUtils.NT_WIFI, c);
                    this.h = ((JSONObject) c.get(0)).optString("ssid");
                }
                if (b != null && b.length() > 0) {
                    f.put("cell", b);
                    this.i = b.toString();
                }
                if (!(this.a || jSONArray == null || jSONArray.length() <= 0)) {
                    f.put("gps", jSONArray);
                    this.j = jSONObject;
                }
                Context context = this.e;
                JSONObject a = o.a(context, "jpush_lbs_info.json");
                JSONObject jSONObject2 = a == null ? new JSONObject() : a;
                JSONArray optJSONArray = jSONObject2.optJSONArray("content");
                jSONArray = optJSONArray == null ? new JSONArray() : optJSONArray;
                try {
                    Object obj;
                    jSONArray.put(f);
                    long longValue = ((Long) d.b(context, "last_report_location", Long.valueOf(0))).longValue();
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - longValue > cn.jiguang.a.b.a.a(context)) {
                        d.a(context, "last_report_location", Long.valueOf(currentTimeMillis));
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj != null || ((Boolean) d.b(context, "lbs_report_now", Boolean.valueOf(false))).booleanValue()) {
                        cn.jiguang.a.b.a.b(context, false);
                        d.a(context, "last_report_location", Long.valueOf(System.currentTimeMillis()));
                        o.a(context, jSONArray);
                        if (o.a(context, "jpush_lbs_info.json", null)) {
                            cn.jiguang.e.d.c("MyLocationManager", "lbs-info log clear succeed");
                            return;
                        } else if (context.deleteFile("jpush_lbs_info.json")) {
                            cn.jiguang.e.d.c("MyLocationManager", "delete file success, filename:jpush_lbs_info.json");
                            return;
                        } else {
                            cn.jiguang.e.d.g("MyLocationManager", "delete file failed, filename:jpush_lbs_info.json");
                            return;
                        }
                    }
                    jSONObject2.put("content", jSONArray);
                    o.a(context, "jpush_lbs_info.json", jSONObject2);
                } catch (Throwable e) {
                    cn.jiguang.e.d.d("MyLocationManager", "unexpected on lbs reportPrepare", e);
                }
            } catch (JSONException e2) {
            }
        }
    }

    static /* synthetic */ void c(f fVar) {
        if (a.a(fVar.e, "android.permission.ACCESS_COARSE_LOCATION")) {
            fVar.l.a();
        } else {
            fVar.a();
        }
    }

    private e d() {
        return this.m != null ? this.m.a() : null;
    }

    protected final void a() {
        this.n.b();
        this.m.a(this.e);
    }

    protected final void b() {
        cn.jiguang.e.d.c("MyLocationManager", "Action - onCollectGPSDone");
        try {
            if (this.g) {
                JSONObject a;
                if (this.f.equals("cell_towers")) {
                    if (d.e(this.e)) {
                        a = a.a("loc_cell", this.l.b());
                        if (a != null && a.length() > 0) {
                            o.a(this.e, a);
                            cn.jiguang.e.d.e("MyLocationManager", "Location: " + a);
                        }
                    }
                } else if (this.f.equals("wifi_towers")) {
                    if (d.e(this.e)) {
                        a = a.a("loc_wifi", this.n.c());
                        if (a != null && a.length() > 0) {
                            o.a(this.e, a);
                            cn.jiguang.e.d.e("MyLocationManager", "Wifi length: " + a.toString().getBytes().length);
                            cn.jiguang.e.d.e("MyLocationManager", "Location: " + a);
                        }
                    }
                } else if (this.f.equals("gps")) {
                    if (d.e(this.e) && !this.a) {
                        e d = d();
                        a = d != null ? d.f() : null;
                        if (a != null && a.length() > 0) {
                            JSONArray jSONArray = new JSONArray();
                            jSONArray.put(a);
                            a = a.a("loc_gps", jSONArray);
                            if (a != null && a.length() > 0) {
                                o.a(this.e, a);
                                cn.jiguang.e.d.e("MyLocationManager", "Location: " + a);
                            }
                        }
                    }
                } else if (this.f.equals("all")) {
                    c();
                }
                this.k = false;
            }
        } catch (Throwable e) {
            cn.jiguang.e.d.e("MyLocationManager", "Get error loc info", e);
        } finally {
            this.k = false;
        }
        if (this.b != null) {
            this.b.removeCallbacksAndMessages(null);
            try {
                this.b.getLooper().quit();
            } catch (Exception e2) {
                cn.jiguang.e.d.i("MyLocationManager", "#unexcepted - looper quit failed cause by :" + e2.getMessage());
            }
            this.b = null;
        }
    }
}
