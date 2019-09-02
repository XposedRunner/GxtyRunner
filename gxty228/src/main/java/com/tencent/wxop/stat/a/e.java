package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.a.a.a.a.h;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.au;
import com.tencent.wxop.stat.common.a;
import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.q;
import org.json.JSONObject;

public abstract class e {
    protected static String j = null;
    private StatSpecifyReportedInfo a = null;
    protected String b = null;
    protected long c;
    protected int d;
    protected a e = null;
    protected int f;
    protected String g = null;
    protected String h = null;
    protected String i = null;
    protected boolean k = false;
    protected Context l;

    e(Context context, int i, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.l = context;
        this.c = System.currentTimeMillis() / 1000;
        this.d = i;
        this.h = StatConfig.getInstallChannel(context);
        this.i = k.j(context);
        this.b = StatConfig.getAppKey(context);
        if (statSpecifyReportedInfo != null) {
            this.a = statSpecifyReportedInfo;
            if (k.c(statSpecifyReportedInfo.getAppKey())) {
                this.b = statSpecifyReportedInfo.getAppKey();
            }
            if (k.c(statSpecifyReportedInfo.getInstallChannel())) {
                this.h = statSpecifyReportedInfo.getInstallChannel();
            }
            if (k.c(statSpecifyReportedInfo.getVersion())) {
                this.i = statSpecifyReportedInfo.getVersion();
            }
            this.k = statSpecifyReportedInfo.isImportant();
        }
        this.g = StatConfig.getCustomUserId(context);
        this.e = au.a(context).b(context);
        if (a() != f.NETWORK_DETECTOR) {
            this.f = k.s(context).intValue();
        } else {
            this.f = -f.NETWORK_DETECTOR.a();
        }
        if (!h.c(j)) {
            String localMidOnly = StatConfig.getLocalMidOnly(context);
            j = localMidOnly;
            if (!k.c(localMidOnly)) {
                j = "0";
            }
        }
    }

    public abstract f a();

    public abstract boolean a(JSONObject jSONObject);

    public boolean b(JSONObject jSONObject) {
        boolean z = false;
        try {
            q.a(jSONObject, "ky", this.b);
            jSONObject.put("et", a().a());
            if (this.e != null) {
                jSONObject.put("ui", this.e.b());
                q.a(jSONObject, "mc", this.e.c());
                int d = this.e.d();
                jSONObject.put("ut", d);
                if (d == 0 && k.w(this.l) == 1) {
                    jSONObject.put("ia", 1);
                }
            }
            q.a(jSONObject, "cui", this.g);
            if (a() != f.SESSION_ENV) {
                q.a(jSONObject, "av", this.i);
                q.a(jSONObject, "ch", this.h);
            }
            if (this.k) {
                jSONObject.put("impt", 1);
            }
            q.a(jSONObject, "mid", j);
            jSONObject.put("idx", this.f);
            jSONObject.put("si", this.d);
            jSONObject.put("ts", this.c);
            jSONObject.put("dts", k.a(this.l, false));
            z = a(jSONObject);
        } catch (Throwable th) {
        }
        return z;
    }

    public long c() {
        return this.c;
    }

    public StatSpecifyReportedInfo d() {
        return this.a;
    }

    public Context e() {
        return this.l;
    }

    public boolean f() {
        return this.k;
    }

    public String g() {
        try {
            JSONObject jSONObject = new JSONObject();
            b(jSONObject);
            return jSONObject.toString();
        } catch (Throwable th) {
            return "";
        }
    }
}
