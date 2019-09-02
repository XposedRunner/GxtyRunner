package com.amap.api.maps.model;

import com.amap.api.mapcore.util.ch;
import java.util.List;

public class BuildingOverlay {
    private ch a;

    public BuildingOverlay(ch chVar) {
        this.a = chVar;
    }

    public void setDefaultOptions(BuildingOverlayOptions buildingOverlayOptions) {
        if (this.a != null) {
            this.a.a(buildingOverlayOptions);
        }
    }

    public BuildingOverlayOptions getDefaultOptions() {
        if (this.a != null) {
            return this.a.d();
        }
        return null;
    }

    public void setCustomOptions(List<BuildingOverlayOptions> list) {
        if (this.a != null) {
            this.a.a((List) list);
        }
    }

    public List<BuildingOverlayOptions> getCustomOptions() {
        if (this.a != null) {
            return this.a.b();
        }
        return null;
    }

    public void destroy() {
        if (this.a != null) {
            this.a.destroy();
        }
    }

    public String getId() {
        if (this.a != null) {
            return this.a.getId();
        }
        return "";
    }

    public void setZIndex(float f) {
        if (this.a != null) {
            this.a.setZIndex(f);
        }
    }

    public float getZIndex() {
        if (this.a != null) {
            return this.a.getZIndex();
        }
        return 0.0f;
    }

    public void setVisible(boolean z) {
        if (this.a != null) {
            this.a.setVisible(z);
        }
    }

    public boolean isVisible() {
        if (this.a != null) {
            return this.a.isVisible();
        }
        return false;
    }
}
