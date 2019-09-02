package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.dc.a;
import com.amap.api.maps.MapsInitializer;

/* compiled from: CustomStyleTask */
public class dd implements Runnable {
    private Context a;
    private lj b;
    private dc c;

    public dd(Context context, lj ljVar) {
        this.a = context;
        this.b = ljVar;
        if (this.c == null) {
            this.c = new dc(this.a, "");
        }
    }

    public void a(String str) {
        if (this.c != null) {
            this.c.b(str);
        }
    }

    public void run() {
        try {
            if (MapsInitializer.getNetWorkEnable()) {
                if (this.c != null) {
                    a aVar = (a) this.c.e();
                    if (!(aVar == null || aVar.a == null || this.b == null)) {
                        this.b.a(this.b.getMapConfig().isCustomStyleEnable(), aVar.a);
                    }
                }
                gz.a(this.a, en.e());
                this.b.setRunLowFrame(false);
            }
        } catch (Throwable th) {
            gz.c(th, "CustomStyleTask", "download customStyle");
            th.printStackTrace();
        }
    }

    public void a() {
        this.a = null;
        if (this.c != null) {
            this.c = null;
        }
    }

    public void b() {
        em.a().a(this);
    }
}
