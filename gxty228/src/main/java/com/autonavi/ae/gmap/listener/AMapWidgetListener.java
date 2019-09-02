package com.autonavi.ae.gmap.listener;

public interface AMapWidgetListener {
    void invalidateCompassView();

    void invalidateScaleView();

    void invalidateZoomController(float f);

    void setFrontViewVisibility(boolean z);
}
