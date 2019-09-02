package com.amap.api.offlineservice;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.amap.api.maps.offlinemap.OfflineMapActivity;

/* compiled from: ServiceModule */
public abstract class a {
    protected OfflineMapActivity a = null;

    public abstract void a();

    public abstract void a(View view);

    public abstract RelativeLayout b();

    public abstract void c();

    public void a(OfflineMapActivity offlineMapActivity) {
        this.a = offlineMapActivity;
    }

    public void a(Bundle bundle) {
        this.a.showScr();
    }

    public boolean e() {
        return true;
    }

    public void f() {
    }

    public void g() {
    }

    public void h() {
    }

    public void i() {
    }

    public int a(float f) {
        if (this.a != null) {
            return (int) (((((float) this.a.getResources().getDisplayMetrics().densityDpi) / 160.0f) * f) + 0.5f);
        }
        return (int) f;
    }
}
