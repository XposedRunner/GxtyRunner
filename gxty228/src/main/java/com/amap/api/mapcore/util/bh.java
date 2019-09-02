package com.amap.api.mapcore.util;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

/* compiled from: AMapGLRenderer */
public class bh implements lk {
    protected boolean a;
    private lj b;

    public bh(Context context) {
        this(context, null);
    }

    public bh(Context context, AttributeSet attributeSet) {
        this.b = null;
        this.a = false;
        this.b = new af(this, context, attributeSet);
    }

    public lj a() {
        return this.b;
    }

    public void queueEvent(Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }

    public boolean isEnabled() {
        if (this.b != null) {
            return true;
        }
        return false;
    }

    public void setVisibility(int i) {
    }

    public void requestRender() {
    }

    public int getRenderMode() {
        return 0;
    }

    public boolean postDelayed(Runnable runnable, long j) {
        return false;
    }

    public boolean post(Runnable runnable) {
        return false;
    }

    public SurfaceHolder getHolder() {
        return null;
    }

    public void a(ds dsVar) {
    }

    public void a(dt dtVar) {
    }

    public void b() {
    }

    public void setRenderer(Renderer renderer) {
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    public void setRenderMode(int i) {
    }
}
