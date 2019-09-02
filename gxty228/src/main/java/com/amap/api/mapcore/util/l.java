package com.amap.api.mapcore.util;

import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;

/* compiled from: AbstractCameraPositionMessage */
public class l extends AbstractCameraUpdateMessage {
    public void runCameraUpdate(GLMapState gLMapState) {
        normalChange(gLMapState);
    }

    public void mergeCameraUpdateDelegate(AbstractCameraUpdateMessage abstractCameraUpdateMessage) {
        abstractCameraUpdateMessage.geoPoint = this.geoPoint == null ? abstractCameraUpdateMessage.geoPoint : this.geoPoint;
        abstractCameraUpdateMessage.zoom = Float.isNaN(this.zoom) ? abstractCameraUpdateMessage.zoom : this.zoom;
        abstractCameraUpdateMessage.bearing = Float.isNaN(this.bearing) ? abstractCameraUpdateMessage.bearing : this.bearing;
        abstractCameraUpdateMessage.tilt = Float.isNaN(this.tilt) ? abstractCameraUpdateMessage.tilt : this.tilt;
    }
}
