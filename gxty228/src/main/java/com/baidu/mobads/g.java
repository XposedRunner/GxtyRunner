package com.baidu.mobads;

import android.annotation.SuppressLint;
import android.view.KeyEvent;
import com.baidu.mobads.component.XAdView.Listener;

class g implements Listener {
    final /* synthetic */ AdView a;

    g(AdView adView) {
        this.a = adView;
    }

    public void onWindowVisibilityChanged(int i) {
        this.a.c.a(i);
    }

    public void onWindowFocusChanged(boolean z) {
        this.a.c.a(z);
    }

    public void onLayoutComplete(int i, int i2) {
        this.a.b();
    }

    @SuppressLint({"MissingSuperCall"})
    public void onDetachedFromWindow() {
        this.a.c.o();
    }

    public void onAttachedToWindow() {
        this.a.b();
        this.a.c.n();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.a.c.a(i, keyEvent);
    }
}
