package com.amap.api.mapcore.util;

import android.graphics.Point;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;

/* compiled from: AbstractCameraScrollMessage */
public class m extends AbstractCameraUpdateMessage {
    public void runCameraUpdate(GLMapState gLMapState) {
        float f = this.xPixel;
        f += ((float) this.width) / 2.0f;
        float f2 = this.yPixel + (((float) this.height) / 2.0f);
        Point point = new Point();
        a(gLMapState, (int) f, (int) f2, point);
        gLMapState.setMapGeoCenter(point.x, point.y);
    }

    public void mergeCameraUpdateDelegate(AbstractCameraUpdateMessage abstractCameraUpdateMessage) {
    }

    public void a(GLMapState gLMapState, int i, int i2, Point point) {
        gLMapState.screenToP20Point(i, i2, point);
    }
}
