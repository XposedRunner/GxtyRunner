package com.amap.api.mapcore.util;

import android.opengl.GLES20;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.ArcOptions;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.autonavi.amap.mapcore.MapConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/* compiled from: GlOverlayLayer */
public class r {
    lj a;
    a b = new a();
    private db c;
    private int d = 0;
    private List<cm> e = new Vector(500);
    private List<f> f = new ArrayList();
    private int[] g = new int[1];
    private Handler h = new Handler(Looper.getMainLooper());
    private Runnable i = new Runnable(this) {
        final /* synthetic */ r a;

        {
            this.a = r1;
        }

        public synchronized void run() {
            try {
                synchronized (this.a) {
                    if (this.a.e != null && this.a.e.size() > 0) {
                        Collections.sort(this.a.e, this.a.b);
                    }
                }
            } catch (Throwable th) {
                gz.c(th, "MapOverlayImageView", "changeOverlayIndex");
            }
        }
    };

    /* compiled from: GlOverlayLayer */
    static class a implements Serializable, Comparator<Object> {
        a() {
        }

        public int compare(Object obj, Object obj2) {
            cm cmVar = (cm) obj;
            cm cmVar2 = (cm) obj2;
            if (!(cmVar == null || cmVar2 == null)) {
                try {
                    if (cmVar.getZIndex() > cmVar2.getZIndex()) {
                        return 1;
                    }
                    if (cmVar.getZIndex() < cmVar2.getZIndex()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    gz.c(th, "GlOverlayLayer", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    public synchronized String a(String str) {
        this.d++;
        return str + this.d;
    }

    public ch a() throws RemoteException {
        cm cbVar = new cb(this);
        cbVar.a(this.c);
        a(cbVar);
        return cbVar;
    }

    public r(lj ljVar) {
        this.a = ljVar;
    }

    public void a(db dbVar) {
        this.c = dbVar;
    }

    public synchronized void b(String str) {
        if (str != null) {
            try {
                if (str.trim().length() != 0) {
                    for (Object obj : this.e) {
                        if (str.equals(obj.getId())) {
                            break;
                        }
                    }
                    Object obj2 = null;
                    this.e.clear();
                    if (obj2 != null) {
                        this.e.add(obj2);
                    }
                }
            } catch (Throwable th) {
                gz.c(th, "GlOverlayLayer", "clear");
                th.printStackTrace();
                Log.d("amapApi", "GlOverlayLayer clear erro" + th.getMessage());
            }
        }
        this.e.clear();
        b();
    }

    public synchronized void b() {
        this.d = 0;
    }

    public synchronized void c() {
        try {
            for (cm destroy : this.e) {
                destroy.destroy();
            }
            b(null);
        } catch (Throwable th) {
            gz.c(th, "GlOverlayLayer", "destory");
            th.printStackTrace();
            Log.d("amapApi", "GlOverlayLayer destory erro" + th.getMessage());
        }
        return;
    }

    synchronized cm c(String str) throws RemoteException {
        cm cmVar;
        for (cm cmVar2 : this.e) {
            if (cmVar2 != null && cmVar2.getId().equals(str)) {
                break;
            }
        }
        cmVar2 = null;
        return cmVar2;
    }

    public synchronized cp a(PolylineOptions polylineOptions) throws RemoteException {
        cp cpVar;
        if (polylineOptions == null) {
            cpVar = null;
        } else {
            cm cwVar = new cw(this, polylineOptions);
            if (this.c != null) {
                cwVar.a(this.c);
            }
            a(cwVar);
        }
        return cpVar;
    }

    public synchronized cl a(NavigateArrowOptions navigateArrowOptions) throws RemoteException {
        cl clVar;
        if (navigateArrowOptions == null) {
            clVar = null;
        } else {
            cm cuVar = new cu(this.a);
            cuVar.setTopColor(navigateArrowOptions.getTopColor());
            cuVar.setPoints(navigateArrowOptions.getPoints());
            cuVar.setVisible(navigateArrowOptions.isVisible());
            cuVar.setWidth(navigateArrowOptions.getWidth());
            cuVar.setZIndex(navigateArrowOptions.getZIndex());
            a(cuVar);
        }
        return clVar;
    }

    public synchronized co a(PolygonOptions polygonOptions) throws RemoteException {
        co coVar;
        if (polygonOptions == null) {
            coVar = null;
        } else {
            cm cvVar = new cv(this.a);
            cvVar.setFillColor(polygonOptions.getFillColor());
            cvVar.setPoints(polygonOptions.getPoints());
            cvVar.setHoleOptions(polygonOptions.getHoleOptions());
            cvVar.setVisible(polygonOptions.isVisible());
            cvVar.setStrokeWidth(polygonOptions.getStrokeWidth());
            cvVar.setZIndex(polygonOptions.getZIndex());
            cvVar.setStrokeColor(polygonOptions.getStrokeColor());
            a(cvVar);
        }
        return coVar;
    }

    public synchronized ci a(CircleOptions circleOptions) throws RemoteException {
        ci ciVar;
        if (circleOptions == null) {
            ciVar = null;
        } else {
            cm ccVar = new cc(this.a);
            ccVar.setFillColor(circleOptions.getFillColor());
            ccVar.setCenter(circleOptions.getCenter());
            ccVar.setVisible(circleOptions.isVisible());
            ccVar.setHoleOptions(circleOptions.getHoleOptions());
            ccVar.setStrokeWidth(circleOptions.getStrokeWidth());
            ccVar.setZIndex(circleOptions.getZIndex());
            ccVar.setStrokeColor(circleOptions.getStrokeColor());
            ccVar.setRadius(circleOptions.getRadius());
            ccVar.setDottedLineType(circleOptions.getStrokeDottedLineType());
            a(ccVar);
        }
        return ciVar;
    }

    public synchronized cg a(ArcOptions arcOptions) throws RemoteException {
        cg cgVar;
        if (arcOptions == null) {
            cgVar = null;
        } else {
            cm caVar = new ca(this.a);
            caVar.setStrokeColor(arcOptions.getStrokeColor());
            caVar.a(arcOptions.getStart());
            caVar.b(arcOptions.getPassed());
            caVar.c(arcOptions.getEnd());
            caVar.setVisible(arcOptions.isVisible());
            caVar.setStrokeWidth(arcOptions.getStrokeWidth());
            caVar.setZIndex(arcOptions.getZIndex());
            a(caVar);
        }
        return cgVar;
    }

    public synchronized cj a(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
        cj cjVar;
        if (groundOverlayOptions == null) {
            cjVar = null;
        } else {
            cm ceVar = new ce(this.a, this);
            ceVar.a(groundOverlayOptions.getAnchorU(), groundOverlayOptions.getAnchorV());
            ceVar.setDimensions(groundOverlayOptions.getWidth(), groundOverlayOptions.getHeight());
            ceVar.setImage(groundOverlayOptions.getImage());
            ceVar.setPosition(groundOverlayOptions.getLocation());
            ceVar.setPositionFromBounds(groundOverlayOptions.getBounds());
            ceVar.setBearing(groundOverlayOptions.getBearing());
            ceVar.setTransparency(groundOverlayOptions.getTransparency());
            ceVar.setVisible(groundOverlayOptions.isVisible());
            ceVar.setZIndex(groundOverlayOptions.getZIndex());
            a(ceVar);
        }
        return cjVar;
    }

    private void a(cm cmVar) throws RemoteException {
        this.e.add(cmVar);
        d();
    }

    public synchronized boolean d(String str) throws RemoteException {
        boolean remove;
        cm c = c(str);
        if (c != null) {
            remove = this.e.remove(c);
        } else {
            remove = false;
        }
        return remove;
    }

    public synchronized void d() {
        this.h.removeCallbacks(this.i);
        this.h.postDelayed(this.i, 10);
    }

    public synchronized void a(boolean z, int i) {
        try {
            e();
            MapConfig mapConfig = this.a.getMapConfig();
            if (mapConfig != null) {
                int size = this.e.size();
                for (cm cmVar : this.e) {
                    if (cmVar.isVisible()) {
                        if (size > 20) {
                            if (!cmVar.a()) {
                                continue;
                            } else if (z) {
                                if (cmVar.getZIndex() <= ((float) i)) {
                                    cmVar.a(mapConfig);
                                }
                            } else if (cmVar.getZIndex() > ((float) i)) {
                                cmVar.a(mapConfig);
                            }
                        } else if (z) {
                            if (cmVar.getZIndex() <= ((float) i)) {
                                cmVar.a(mapConfig);
                            }
                        } else if (cmVar.getZIndex() > ((float) i)) {
                            cmVar.a(mapConfig);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            gz.c(th, "GlOverlayLayer", "draw");
        }
    }

    public void e() {
        synchronized (this.f) {
            for (int i = 0; i < this.f.size(); i++) {
                f fVar = (f) this.f.get(i);
                if (fVar != null) {
                    fVar.h();
                    if (fVar.i() <= 0) {
                        this.g[0] = fVar.f();
                        GLES20.glDeleteTextures(1, this.g, 0);
                        if (this.a != null) {
                            this.a.c(fVar.j());
                        }
                    }
                }
            }
            this.f.clear();
        }
    }

    public void a(f fVar) {
        synchronized (this.f) {
            if (fVar != null) {
                this.f.add(fVar);
            }
        }
    }

    public f a(BitmapDescriptor bitmapDescriptor) {
        if (this.a != null) {
            return this.a.a(bitmapDescriptor, true);
        }
        return null;
    }

    public synchronized cm a(LatLng latLng) {
        cm cmVar;
        for (cm cmVar2 : this.e) {
            if (cmVar2 != null && cmVar2.c() && (cmVar2 instanceof cp) && ((cp) cmVar2).a(latLng)) {
                break;
            }
        }
        cmVar2 = null;
        return cmVar2;
    }

    public lj f() {
        return this.a;
    }

    public float[] g() {
        if (this.a != null) {
            return this.a.w();
        }
        return new float[16];
    }
}
