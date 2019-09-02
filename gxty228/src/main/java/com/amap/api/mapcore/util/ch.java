package com.amap.api.mapcore.util;

import com.amap.api.maps.model.BuildingOverlayOptions;
import java.util.List;

/* compiled from: IBuildingDelegate */
public interface ch {
    void a(BuildingOverlayOptions buildingOverlayOptions);

    void a(List<BuildingOverlayOptions> list);

    List<BuildingOverlayOptions> b();

    BuildingOverlayOptions d();

    void destroy();

    String getId();

    float getZIndex();

    boolean isVisible();

    void setVisible(boolean z);

    void setZIndex(float f);
}
