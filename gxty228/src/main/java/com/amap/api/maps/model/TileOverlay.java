package com.amap.api.maps.model;

import com.autonavi.amap.mapcore.interfaces.ITileOverlay;

public final class TileOverlay {
    private ITileOverlay a;

    public TileOverlay(ITileOverlay iTileOverlay) {
        this.a = iTileOverlay;
    }

    public void remove() {
        this.a.remove();
    }

    public void clearTileCache() {
        this.a.clearTileCache();
    }

    public String getId() {
        return this.a.getId();
    }

    public void setZIndex(float f) {
        this.a.setZIndex(f);
    }

    public float getZIndex() {
        return this.a.getZIndex();
    }

    public void setVisible(boolean z) {
        this.a.setVisible(z);
    }

    public boolean isVisible() {
        return this.a.isVisible();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj != null && (obj instanceof TileOverlay)) {
            try {
                z = this.a.equalsRemote(((TileOverlay) obj).a);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return z;
    }

    public int hashCode() {
        return this.a.hashCodeRemote();
    }
}
