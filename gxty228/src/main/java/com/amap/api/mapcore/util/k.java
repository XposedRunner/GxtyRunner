package com.amap.api.mapcore.util;

import android.util.Pair;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;
import com.autonavi.amap.mapcore.IPoint;

/* compiled from: AbstractCameraBoundsMessage */
public class k extends AbstractCameraUpdateMessage {
    public void runCameraUpdate(GLMapState gLMapState) {
        Pair a = en.a((AbstractCameraUpdateMessage) this, gLMapState, this.mapConfig);
        if (a != null) {
            gLMapState.setMapZoomer(((Float) a.first).floatValue());
            gLMapState.setMapGeoCenter(((IPoint) a.second).x, ((IPoint) a.second).y);
            gLMapState.setCameraDegree(0.0f);
            gLMapState.setMapAngle(0.0f);
        }
    }

    public void mergeCameraUpdateDelegate(AbstractCameraUpdateMessage abstractCameraUpdateMessage) {
    }
}
