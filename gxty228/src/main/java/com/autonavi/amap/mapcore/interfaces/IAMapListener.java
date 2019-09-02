package com.autonavi.amap.mapcore.interfaces;

import com.autonavi.ae.gmap.GLMapState;

public interface IAMapListener {
    void afterAnimation();

    void afterDrawFrame(int i, GLMapState gLMapState);

    void afterDrawLabel(int i, GLMapState gLMapState);

    void beforeDrawLabel(int i, GLMapState gLMapState);
}
