package com.amap.api.maps.model;

import android.graphics.Point;
import com.autonavi.amap.mapcore.VirtualEarthProjection;
import java.util.ArrayList;
import java.util.List;

public class BuildingOverlayOptions {
    private int a = -1;
    private int b = 1;
    private int c = -7829368;
    private int d = -7829368;
    private boolean e = true;
    private float f;
    private List<LatLng> g = new ArrayList();

    public float getZIndex() {
        return this.f;
    }

    public void setZIndex(float f) {
        this.f = f;
    }

    public void setVisible(boolean z) {
        this.e = z;
    }

    public boolean isVisible() {
        return this.e;
    }

    public BuildingOverlayOptions setBuildingHeightScale(int i) {
        this.b = i;
        return this;
    }

    public int getBuildingHeightScale() {
        return this.b;
    }

    public BuildingOverlayOptions setBuildingTopColor(int i) {
        this.c = i;
        return this;
    }

    public BuildingOverlayOptions setBuildingSideColor(int i) {
        this.d = i;
        return this;
    }

    public int getBuildingSideColor() {
        return this.d;
    }

    public int getBuildingTopColor() {
        return this.c;
    }

    public BuildingOverlayOptions setBuildingHeight(int i) {
        this.a = i;
        return this;
    }

    public int getBuildingHeight() {
        return this.a;
    }

    public List<LatLng> getBuildingLatlngs() {
        return this.g;
    }

    public int[] getPoints() {
        if (this.g == null || this.g.size() <= 0) {
            return new int[0];
        }
        int[] iArr = new int[(this.g.size() * 2)];
        int i = 0;
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            LatLng latLng = (LatLng) this.g.get(i2);
            Point latLongToPixels = VirtualEarthProjection.latLongToPixels(latLng.latitude, latLng.longitude, 20);
            int i3 = i + 1;
            iArr[i] = latLongToPixels.x;
            i = i3 + 1;
            iArr[i3] = latLongToPixels.y;
        }
        return iArr;
    }

    public BuildingOverlayOptions setBuildingLatlngs(List<LatLng> list) {
        this.g = list;
        return this;
    }
}
