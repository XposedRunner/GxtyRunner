package com.amap.api.mapcore.util;

import javax.microedition.khronos.opengles.GL10;

/* compiled from: AbstractGlOverlay */
public abstract class bz {
    private lj a;

    public abstract int getZIndex();

    public abstract void onDrawFrame(GL10 gl10);

    public void destroy() {
        if (this.a == null) {
        }
    }
}
