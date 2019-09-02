package com.amap.api.mapcore.util;

import android.content.Context;

/* compiled from: ScaleRotateGestureDetector */
public class x extends w {

    /* compiled from: ScaleRotateGestureDetector */
    public static abstract class a implements com.amap.api.mapcore.util.w.a {
        public abstract boolean a(x xVar);

        public abstract boolean b(x xVar);

        public abstract void c(x xVar);

        public boolean a(w wVar) {
            return a((x) wVar);
        }

        public boolean b(w wVar) {
            return b((x) wVar);
        }

        public void c(w wVar) {
            c((x) wVar);
        }
    }

    public x(Context context, a aVar) {
        super(context, aVar);
    }

    public float l() {
        return (float) (((Math.atan2((double) i(), (double) h()) - Math.atan2((double) f(), (double) e())) * 180.0d) / 3.141592653589793d);
    }
}
