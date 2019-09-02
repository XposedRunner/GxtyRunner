package com.amap.api.mapcore.util;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.Rectangle;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/* compiled from: NavigateArrowDelegateImp */
public class cu implements cl {
    float a;
    float b;
    float c;
    float d;
    Rect e = null;
    float[] f;
    int g = 0;
    private lj h;
    private float i = 10.0f;
    private int j = ViewCompat.MEASURED_STATE_MASK;
    private int k = ViewCompat.MEASURED_STATE_MASK;
    private float l = 0.0f;
    private boolean m = true;
    private String n;
    private List<IPoint> o = new Vector();
    private int p = 0;
    private boolean q = false;
    private Object r = new Object();

    public cu(lj ljVar) {
        this.h = ljVar;
        try {
            this.n = getId();
        } catch (Throwable e) {
            gz.c(e, "NavigateArrowDelegateImp", "create");
            e.printStackTrace();
        }
    }

    void a(List<LatLng> list) throws RemoteException {
        synchronized (this.r) {
            this.o.clear();
            if (this.e == null) {
                this.e = new Rect();
            }
            en.a(this.e);
            if (list != null) {
                Object obj = null;
                for (LatLng latLng : list) {
                    if (!(latLng == null || latLng.equals(r1))) {
                        IPoint obtain = IPoint.obtain();
                        this.h.a(latLng.latitude, latLng.longitude, obtain);
                        this.o.add(obtain);
                        en.b(this.e, obtain.x, obtain.y);
                        obj = latLng;
                    }
                }
            }
            this.p = 0;
            this.e.sort();
        }
        this.h.setRunLowFrame(false);
    }

    public void remove() throws RemoteException {
        this.h.a(getId());
        this.h.setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.n == null) {
            this.n = this.h.d("NavigateArrow");
        }
        return this.n;
    }

    public void setPoints(List<LatLng> list) throws RemoteException {
        a((List) list);
    }

    public List<LatLng> getPoints() throws RemoteException {
        return b();
    }

    private List<LatLng> b() throws RemoteException {
        if (this.o == null) {
            return null;
        }
        List<LatLng> arrayList;
        synchronized (this.r) {
            arrayList = new ArrayList();
            for (IPoint iPoint : this.o) {
                if (iPoint != null) {
                    DPoint obtain = DPoint.obtain();
                    this.h.a(iPoint.x, iPoint.y, obtain);
                    arrayList.add(new LatLng(obtain.y, obtain.x));
                    obtain.recycle();
                }
            }
        }
        return arrayList;
    }

    public void setWidth(float f) throws RemoteException {
        this.i = f;
        this.h.setRunLowFrame(false);
    }

    public float getWidth() throws RemoteException {
        return this.i;
    }

    public void setTopColor(int i) throws RemoteException {
        this.j = i;
        this.a = ((float) Color.alpha(i)) / 255.0f;
        this.b = ((float) Color.red(i)) / 255.0f;
        this.c = ((float) Color.green(i)) / 255.0f;
        this.d = ((float) Color.blue(i)) / 255.0f;
        this.h.setRunLowFrame(false);
    }

    public int getTopColor() throws RemoteException {
        return this.j;
    }

    public void setSideColor(int i) throws RemoteException {
        this.k = i;
        this.h.setRunLowFrame(false);
    }

    public int getSideColor() throws RemoteException {
        return this.k;
    }

    public void setZIndex(float f) throws RemoteException {
        this.l = f;
        this.h.f();
        this.h.setRunLowFrame(false);
    }

    public float getZIndex() throws RemoteException {
        return this.l;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.m = z;
        this.h.setRunLowFrame(false);
    }

    public boolean isVisible() throws RemoteException {
        return this.m;
    }

    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        if (equals(iOverlay) || iOverlay.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() throws RemoteException {
        return super.hashCode();
    }

    public boolean a() {
        if (this.e == null) {
            return false;
        }
        Rectangle geoRectangle = this.h.getMapConfig().getGeoRectangle();
        if (geoRectangle == null || !geoRectangle.isOverlap(this.e)) {
            return false;
        }
        return true;
    }

    public boolean b(MapConfig mapConfig) throws RemoteException {
        synchronized (this.r) {
            int sx = mapConfig.getSX();
            int sy = mapConfig.getSY();
            this.q = false;
            int size = this.o.size();
            if (this.f == null || this.f.length < 3 * size) {
                this.f = new float[(size * 3)];
            }
            this.g = size * 3;
            size = 0;
            for (IPoint iPoint : this.o) {
                this.f[size * 3] = (float) (iPoint.x - sx);
                this.f[(size * 3) + 1] = (float) (iPoint.y - sy);
                this.f[(size * 3) + 2] = 0.0f;
                size++;
            }
            this.p = this.o.size();
        }
        return true;
    }

    public void a(MapConfig mapConfig) throws RemoteException {
        if (this.o != null && this.o.size() != 0 && this.i > 0.0f) {
            b(this.h.getMapConfig());
            if (this.f != null && this.p > 0) {
                AMapNativeRenderer.nativeDrawLineByTextureID(this.f, this.g, this.h.c().getMapLenWithWin((int) this.i), this.h.d(), this.b, this.c, this.d, this.a, 0.0f, false, true, true, this.h.w(), 2, 0);
            }
            this.q = true;
        }
    }

    public void destroy() {
        try {
            if (this.f != null) {
                this.f = null;
            }
        } catch (Throwable th) {
            gz.c(th, "NavigateArrowDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "NavigateArrowDelegateImp destroy");
        }
    }

    public boolean c() {
        return this.q;
    }

    public boolean isAboveMaskLayer() {
        return false;
    }

    public void setAboveMaskLayer(boolean z) {
    }
}
