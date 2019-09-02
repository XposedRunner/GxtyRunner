package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.RemoteException;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: TileOverlayView */
public class ag {
    List<cr> a = new ArrayList();
    a b = new a();
    List<Integer> c = new ArrayList();
    cz d = null;
    float[] e = new float[16];
    private lj f;
    private Context g;

    /* compiled from: TileOverlayView */
    static class a implements Serializable, Comparator<Object> {
        a() {
        }

        public int compare(Object obj, Object obj2) {
            cr crVar = (cr) obj;
            cr crVar2 = (cr) obj2;
            if (!(crVar == null || crVar2 == null)) {
                try {
                    return Float.compare(crVar.getZIndex(), crVar2.getZIndex());
                } catch (Throwable th) {
                    gz.c(th, "TileOverlayView", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    public ag(Context context, lj ljVar) {
        this.f = ljVar;
        this.g = context;
        TileOverlayOptions tileProvider = new TileOverlayOptions().tileProvider(new do(256, 256, this.f.getMapConfig()));
        tileProvider.memCacheSize(10485760);
        tileProvider.diskCacheSize(20480);
        this.d = new cz(tileProvider, this, true);
    }

    public lj a() {
        return this.f;
    }

    public void b() {
        try {
            for (Integer intValue : this.c) {
                en.b(intValue.intValue());
            }
            this.c.clear();
            if (i() && this.d != null) {
                this.d.a();
            }
            synchronized (this.a) {
                int size = this.a.size();
                for (int i = 0; i < size; i++) {
                    cr crVar = (cr) this.a.get(i);
                    if (crVar.isVisible()) {
                        crVar.a();
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    public void c() {
        synchronized (this.a) {
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                cr crVar = (cr) this.a.get(i);
                if (crVar != null) {
                    crVar.destroy(true);
                }
            }
            this.a.clear();
        }
    }

    public void d() {
        synchronized (this.a) {
            Collections.sort(this.a, this.b);
        }
    }

    public TileOverlay a(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        if (tileOverlayOptions == null || tileOverlayOptions.getTileProvider() == null) {
            return null;
        }
        try {
            cr czVar = new cz(tileOverlayOptions, this, false);
            a(czVar);
            czVar.a(true);
            this.f.setRunLowFrame(false);
            return new TileOverlay(czVar);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void a(cr crVar) {
        synchronized (this.a) {
            b(crVar);
            this.a.add(crVar);
        }
        d();
    }

    public boolean b(cr crVar) {
        boolean remove;
        synchronized (this.a) {
            remove = this.a.remove(crVar);
        }
        return remove;
    }

    public void a(boolean z) {
        try {
            if (i()) {
                CameraPosition cameraPosition = this.f.getCameraPosition();
                if (cameraPosition != null) {
                    if (!cameraPosition.isAbroad || cameraPosition.zoom <= 7.0f) {
                        if (this.d != null) {
                            if (this.f.getMapConfig().getMapLanguage().equals(AMap.ENGLISH)) {
                                this.d.a(z);
                            } else {
                                this.d.b();
                            }
                        }
                    } else if (this.f.getMapType() == 1) {
                        if (this.d != null) {
                            this.d.a(z);
                        }
                    } else if (this.d != null) {
                        this.d.b();
                    }
                } else {
                    return;
                }
            }
            synchronized (this.a) {
                int size = this.a.size();
                for (int i = 0; i < size; i++) {
                    cr crVar = (cr) this.a.get(i);
                    if (crVar != null && crVar.isVisible()) {
                        crVar.a(z);
                    }
                }
            }
        } catch (Throwable th) {
            gz.c(th, "TileOverlayView", "refresh");
        }
    }

    private boolean i() {
        if (this.f == null) {
            return false;
        }
        if (MapsInitializer.isLoadWorldGridMap() || this.f.getMapConfig().getMapLanguage().equals(AMap.ENGLISH)) {
            return true;
        }
        return false;
    }

    public void b(boolean z) {
        if (this.d != null) {
            this.d.b(z);
        }
        synchronized (this.a) {
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                cr crVar = (cr) this.a.get(i);
                if (crVar != null) {
                    crVar.b(z);
                }
            }
        }
    }

    public Context e() {
        return this.g;
    }

    public void a(int i) {
        this.c.add(Integer.valueOf(i));
    }

    public void f() {
        c();
        if (this.d != null) {
            this.d.c();
            this.d.destroy(false);
        }
        this.d = null;
    }

    public float[] g() {
        if (this.f != null) {
            return this.f.w();
        }
        return this.e;
    }

    public void a(String str) {
        if (this.d != null) {
            this.d.a(str);
        }
    }

    public void h() {
        if (this.d != null) {
            this.d.clearTileCache();
            ec.a(this.g, "Map3DCache", "time", Long.valueOf(System.currentTimeMillis()));
        }
        synchronized (this.a) {
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                cr crVar = (cr) this.a.get(i);
                if (crVar != null) {
                    crVar.clearTileCache();
                }
            }
        }
    }
}
