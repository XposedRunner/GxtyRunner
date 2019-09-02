package com.amap.api.mapcore.util;

import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;

/* compiled from: AbstractCameraZoomMessage */
public class o extends AbstractCameraUpdateMessage {
    public void runCameraUpdate(GLMapState gLMapState) {
        this.zoom = gLMapState.getMapZoomer() + this.amount;
        this.zoom = en.a(this.mapConfig, this.zoom);
        normalChange(gLMapState);
    }

    public void mergeCameraUpdateDelegate(AbstractCameraUpdateMessage abstractCameraUpdateMessage) {
        abstractCameraUpdateMessage.zoom += this.amount;
    }
}
