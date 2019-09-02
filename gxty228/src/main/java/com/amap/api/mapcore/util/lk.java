package com.amap.api.mapcore.util;

import android.opengl.GLSurfaceView.Renderer;
import android.view.SurfaceHolder;

/* compiled from: IGLSurfaceView */
public interface lk {
    void a(ds dsVar);

    void a(dt dtVar);

    void b();

    int getHeight();

    SurfaceHolder getHolder();

    int getRenderMode();

    int getWidth();

    boolean isEnabled();

    boolean post(Runnable runnable);

    boolean postDelayed(Runnable runnable, long j);

    void queueEvent(Runnable runnable);

    void requestRender();

    void setRenderMode(int i);

    void setRenderer(Renderer renderer);

    void setVisibility(int i);
}
