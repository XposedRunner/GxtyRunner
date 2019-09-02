package com.amap.api.mapcore.util;

import android.os.RemoteException;
import com.amap.api.maps.AMap.OnMultiPointClickListener;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MultiPointOverlayManagerLayer */
public class aj {
    db a;
    private List<IMultiPointOverlay> b = new ArrayList();
    private OnMultiPointClickListener c;
    private lj d;

    public aj(lj ljVar) {
        this.d = ljVar;
    }

    public db a() {
        this.a = this.d.x();
        return this.a;
    }

    public synchronized void b() {
        this.c = null;
        try {
            synchronized (this.b) {
                for (IMultiPointOverlay destroy : this.b) {
                    destroy.destroy(false);
                }
                this.b.clear();
            }
        } catch (Throwable th) {
            gz.c(th, "MultiPointOverlayManagerLayer", "destory");
            th.printStackTrace();
        }
        return;
    }

    public synchronized void c() {
        try {
            synchronized (this.b) {
                this.b.clear();
            }
        } catch (Throwable th) {
            gz.c(th, "MultiPointOverlayManagerLayer", "clear");
            th.printStackTrace();
        }
    }

    public synchronized IMultiPointOverlay a(MultiPointOverlayOptions multiPointOverlayOptions) throws RemoteException {
        IMultiPointOverlay iMultiPointOverlay;
        if (multiPointOverlayOptions == null) {
            iMultiPointOverlay = null;
        } else {
            iMultiPointOverlay = new ai(multiPointOverlayOptions, this);
            a(iMultiPointOverlay);
        }
        return iMultiPointOverlay;
    }

    private void a(IMultiPointOverlay iMultiPointOverlay) throws RemoteException {
        synchronized (this.b) {
            this.b.add(iMultiPointOverlay);
        }
    }

    public void a(MapConfig mapConfig, float[] fArr, float[] fArr2) {
        try {
            synchronized (this.b) {
                for (IMultiPointOverlay draw : this.b) {
                    draw.draw(mapConfig, fArr, fArr2);
                }
            }
        } catch (Throwable th) {
            gz.c(th, "MultiPointOverlayManagerLayer", "draw");
            th.printStackTrace();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.autonavi.amap.mapcore.IPoint r5) {
        /*
        r4 = this;
        r1 = 0;
        r0 = r4.c;
        if (r0 != 0) goto L_0x0007;
    L_0x0005:
        r0 = r1;
    L_0x0006:
        return r0;
    L_0x0007:
        r2 = r4.b;
        monitor-enter(r2);
        r0 = r4.b;	 Catch:{ all -> 0x0030 }
        r3 = r0.iterator();	 Catch:{ all -> 0x0030 }
    L_0x0010:
        r0 = r3.hasNext();	 Catch:{ all -> 0x0030 }
        if (r0 == 0) goto L_0x0033;
    L_0x0016:
        r0 = r3.next();	 Catch:{ all -> 0x0030 }
        r0 = (com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay) r0;	 Catch:{ all -> 0x0030 }
        if (r0 == 0) goto L_0x0010;
    L_0x001e:
        r0 = r0.onClick(r5);	 Catch:{ all -> 0x0030 }
        if (r0 == 0) goto L_0x0010;
    L_0x0024:
        r3 = r4.c;	 Catch:{ all -> 0x0030 }
        if (r3 == 0) goto L_0x0036;
    L_0x0028:
        r1 = r4.c;	 Catch:{ all -> 0x0030 }
        r0 = r1.onPointClick(r0);	 Catch:{ all -> 0x0030 }
    L_0x002e:
        monitor-exit(r2);	 Catch:{ all -> 0x0030 }
        goto L_0x0006;
    L_0x0030:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0030 }
        throw r0;
    L_0x0033:
        monitor-exit(r2);	 Catch:{ all -> 0x0030 }
        r0 = r1;
        goto L_0x0006;
    L_0x0036:
        r0 = r1;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.aj.a(com.autonavi.amap.mapcore.IPoint):boolean");
    }

    public void a(OnMultiPointClickListener onMultiPointClickListener) {
        this.c = onMultiPointClickListener;
    }

    public void d() {
        if (this.d != null) {
            this.d.setRunLowFrame(false);
        }
    }

    public void a(ai aiVar) {
        this.b.remove(aiVar);
    }
}
