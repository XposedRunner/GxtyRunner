package com.example.gita.gxty;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;
import com.amap.api.location.AMapLocation;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.blankj.utilcode.util.Utils;
import com.example.gita.gxty.activity.MainActivity;
import com.example.gita.gxty.model.DataRun;
import com.example.gita.gxty.model.MyLatLng;
import com.example.gita.gxty.model.Run;
import com.example.gita.gxty.model.SignPoint;
import com.example.gita.gxty.utils.BuglyUtils;
import com.example.gita.gxty.utils.a;
import com.example.gita.gxty.utils.e;
import com.example.gita.gxty.utils.f;
import com.example.gita.gxty.utils.g;
import com.example.gita.gxty.utils.k;
import com.example.gita.gxty.utils.n;
import com.example.gita.gxty.utils.t;
import com.nostra13.universalimageloader.core.d;
import java.util.ArrayList;
import java.util.List;

public class MyApplication extends MultiDexApplication {
    static DataRun a;
    static DataRun b;
    static Run c;
    static Run d;
    static List<MyLatLng> e = new ArrayList();
    static AMapLocation f;
    static List<SignPoint> g = new ArrayList();
    private static MyApplication h;
    private String i = null;

    public void onCreate() {
        super.onCreate();
        h = this;
        k.a((Context) this);
        Utils.init(getApplicationContext());
        f.a(this);
        g.a((Application) this);
        BuglyUtils.a((Application) this);
        t.a((Application) this);
        e.a(this);
        d.a().a(com.nostra13.universalimageloader.core.e.a(this));
    }

    public synchronized void a(int i, DataRun dataRun) {
        if (i == 1) {
            a = dataRun;
        } else {
            b = dataRun;
        }
    }

    public synchronized DataRun a(int i) {
        DataRun dataRun;
        if (i == 1) {
            dataRun = a;
        } else {
            dataRun = b;
        }
        return dataRun;
    }

    public synchronized void a(int i, Run run) {
        if (i == 1) {
            c = run;
        } else {
            d = run;
        }
    }

    public synchronized Run b(int i) {
        Run run;
        if (i == 1) {
            run = c;
        } else {
            run = d;
        }
        return run;
    }

    public boolean c(int i) {
        if (i == 1) {
            if (c == null || c.gpsinfo == null || c.gpsinfo.size() <= 0 || c.ibeacon == null || c.ibeacon.size() <= 0) {
                return false;
            }
            return true;
        } else if (d == null) {
            return false;
        } else {
            return true;
        }
    }

    public synchronized void a(List<MyLatLng> list) {
        if (list != null) {
            e.addAll(list);
        }
    }

    public synchronized void a(MyLatLng myLatLng) {
        if (myLatLng != null) {
            e.add(myLatLng);
        }
    }

    public synchronized List<MyLatLng> a() {
        return e;
    }

    public synchronized void b() {
        e.clear();
    }

    public synchronized AMapLocation c() {
        return f;
    }

    public synchronized void a(AMapLocation aMapLocation) {
        f = aMapLocation;
    }

    public synchronized List<SignPoint> d() {
        return g;
    }

    public static MyApplication e() {
        return h;
    }

    public void a(Context context) {
        try {
            n.a(context).a();
            g.a(context);
            a.a();
            if (context instanceof Activity) {
                ((Activity) context).startActivity(new Intent(context, MainActivity.class));
                return;
            }
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String f() {
        return this.i;
    }

    public void a(String str) {
        this.i = str;
    }
}
