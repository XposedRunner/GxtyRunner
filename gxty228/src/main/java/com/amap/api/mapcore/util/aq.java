package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Bundle;
import com.amap.api.mapcore.util.bg.a;
import com.amap.api.maps.AMap;
import java.io.IOException;

/* compiled from: OfflineMapDownloadTask */
public class aq extends kc implements a {
    private bg a;
    private bj b;
    private bm c;
    private Context e;
    private Bundle f;
    private boolean g;

    public aq(bm bmVar, Context context) {
        this.f = new Bundle();
        this.g = false;
        this.c = bmVar;
        this.e = context;
    }

    public aq(bm bmVar, Context context, AMap aMap) {
        this(bmVar, context);
    }

    public void runTask() {
        if (this.c.y()) {
            this.c.a(cf.a.file_io_exception);
            return;
        }
        try {
            e();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a() {
        this.g = true;
        if (this.a != null) {
            this.a.c();
        } else {
            cancelTask();
        }
        if (this.b != null) {
            this.b.a();
        }
    }

    private String d() {
        return en.c(this.e);
    }

    private void e() throws IOException {
        this.a = new bg(new bi(this.c.getUrl(), d(), this.c.z(), 1, this.c.A()), this.c.getUrl(), this.e, this.c);
        this.a.a((a) this);
        this.b = new bj(this.c, this.c);
        if (!this.g) {
            this.a.a();
        }
    }

    public void b() {
        if (this.f != null) {
            this.f.clear();
            this.f = null;
        }
    }

    public void c() {
        if (this.b != null) {
            this.b.b();
        }
    }
}
