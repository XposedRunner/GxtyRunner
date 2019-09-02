package com.amap.api.mapcore.util;

import android.os.RemoteException;
import android.support.v4.internal.view.SupportMenu;
import com.amap.api.maps.model.BuildingOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.AMapNativeBuildingRenderer;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import java.util.ArrayList;
import java.util.List;

/* compiled from: BuildingOverlayDelegateImp */
public class cb implements ch, cm {
    long a = -1;
    private r b;
    private BuildingOverlayOptions c;
    private List<BuildingOverlayOptions> d = new ArrayList();
    private List<BuildingOverlayOptions> e;
    private boolean f = true;
    private String g;
    private float h;
    private boolean i;
    private db j;

    public cb(r rVar) {
        try {
            this.b = rVar;
            if (this.c == null) {
                this.c = new BuildingOverlayOptions();
                this.c.setVisible(true);
                List arrayList = new ArrayList();
                arrayList.add(new LatLng(84.9d, -179.9d));
                arrayList.add(new LatLng(84.9d, 179.9d));
                arrayList.add(new LatLng(-84.9d, 179.9d));
                arrayList.add(new LatLng(-84.9d, -179.9d));
                this.c.setBuildingLatlngs(arrayList);
                this.c.setBuildingTopColor(SupportMenu.CATEGORY_MASK);
                this.c.setBuildingSideColor(-12303292);
                this.c.setVisible(true);
                this.c.setZIndex(1.0f);
                this.d.add(this.c);
                a(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        if (this.g == null) {
            this.g = this.b.a("Building");
        }
        return this.g;
    }

    public void setZIndex(float f) {
        try {
            this.h = f;
            this.b.d();
            synchronized (this) {
                this.c.setZIndex(this.h);
            }
            a(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getZIndex() {
        return this.h;
    }

    public void setVisible(boolean z) {
        this.f = z;
    }

    public boolean isVisible() {
        return this.f;
    }

    public void a(BuildingOverlayOptions buildingOverlayOptions) {
        if (buildingOverlayOptions != null) {
            synchronized (this) {
                this.c = buildingOverlayOptions;
            }
            a(true);
        }
    }

    public void a(List<BuildingOverlayOptions> list) {
        if (list != null && list.size() > 0) {
            synchronized (this) {
                this.e = list;
            }
            a(false);
        }
    }

    public List<BuildingOverlayOptions> b() {
        return this.e;
    }

    public BuildingOverlayOptions d() {
        BuildingOverlayOptions buildingOverlayOptions;
        synchronized (this) {
            buildingOverlayOptions = this.c;
        }
        return buildingOverlayOptions;
    }

    private void a(boolean z) {
        try {
            synchronized (this) {
                if (z) {
                    this.d.set(0, this.c);
                } else {
                    this.d.removeAll(this.e);
                    this.d.set(0, this.c);
                    this.d.addAll(this.e);
                }
                this.i = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        synchronized (this) {
            if (this.a != -1) {
                AMapNativeBuildingRenderer.nativeDestory(this.a);
                if (this.d != null) {
                    this.d.clear();
                }
                this.e = null;
                this.c = null;
                this.a = -1;
            }
        }
    }

    public void a(MapConfig mapConfig) throws RemoteException {
        if (mapConfig != null) {
            try {
                if (this.a != -1) {
                    synchronized (this) {
                        if (this.a != -1) {
                            if (this.i) {
                                AMapNativeBuildingRenderer.nativeClearBuildingOptions(this.a);
                                for (int i = 0; i < this.d.size(); i++) {
                                    AMapNativeBuildingRenderer.addBuildingOptions(this.a, (BuildingOverlayOptions) this.d.get(i));
                                }
                                this.i = false;
                            }
                            AMapNativeBuildingRenderer.render(this.a, mapConfig.getViewMatrix(), mapConfig.getProjectionMatrix(), mapConfig.getSX(), mapConfig.getSY(), mapConfig.getSZ(), mapConfig.getCurTileIds());
                        }
                    }
                    return;
                }
                this.a = AMapNativeBuildingRenderer.nativeCreate();
                if (this.a != -1 && this.j != null) {
                    AMapNativeBuildingRenderer.nativeSetGLShaderManager(this.a, this.j.a());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isAboveMaskLayer() {
        return false;
    }

    public void setAboveMaskLayer(boolean z) {
    }

    public boolean a() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        return false;
    }

    public int hashCodeRemote() throws RemoteException {
        return 0;
    }

    public void remove() throws RemoteException {
    }

    public void a(db dbVar) {
        this.j = dbVar;
    }
}
