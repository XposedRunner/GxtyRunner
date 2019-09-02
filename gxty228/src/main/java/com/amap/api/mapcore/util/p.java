package com.amap.api.mapcore.util;

import android.graphics.Point;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage.Type;
import com.autonavi.amap.mapcore.VirtualEarthProjection;

/* compiled from: CameraUpdateFactoryDelegate */
public class p {
    public static AbstractCameraUpdateMessage a() {
        AbstractCameraUpdateMessage oVar = new o();
        oVar.nowType = Type.zoomBy;
        oVar.amount = 1.0f;
        return oVar;
    }

    public static AbstractCameraUpdateMessage b() {
        AbstractCameraUpdateMessage oVar = new o();
        oVar.nowType = Type.zoomBy;
        oVar.amount = -1.0f;
        return oVar;
    }

    public static AbstractCameraUpdateMessage a(float f, float f2) {
        AbstractCameraUpdateMessage mVar = new m();
        mVar.nowType = Type.scrollBy;
        mVar.xPixel = f;
        mVar.yPixel = f2;
        return mVar;
    }

    public static AbstractCameraUpdateMessage a(float f) {
        AbstractCameraUpdateMessage lVar = new l();
        lVar.nowType = Type.newCameraPosition;
        lVar.zoom = f;
        return lVar;
    }

    public static AbstractCameraUpdateMessage b(float f) {
        return a(f, null);
    }

    public static AbstractCameraUpdateMessage a(float f, Point point) {
        AbstractCameraUpdateMessage oVar = new o();
        oVar.nowType = Type.zoomBy;
        oVar.amount = f;
        oVar.focus = point;
        return oVar;
    }

    public static AbstractCameraUpdateMessage a(CameraPosition cameraPosition) {
        AbstractCameraUpdateMessage lVar = new l();
        lVar.nowType = Type.newCameraPosition;
        if (!(cameraPosition == null || cameraPosition.target == null)) {
            lVar.geoPoint = VirtualEarthProjection.latLongToPixels(cameraPosition.target.latitude, cameraPosition.target.longitude, 20);
            lVar.zoom = cameraPosition.zoom;
            lVar.bearing = cameraPosition.bearing;
            lVar.tilt = cameraPosition.tilt;
            lVar.cameraPosition = cameraPosition;
        }
        return lVar;
    }

    public static AbstractCameraUpdateMessage a(Point point) {
        AbstractCameraUpdateMessage lVar = new l();
        lVar.nowType = Type.newCameraPosition;
        lVar.geoPoint = point;
        return lVar;
    }

    public static AbstractCameraUpdateMessage c(float f) {
        AbstractCameraUpdateMessage lVar = new l();
        lVar.nowType = Type.newCameraPosition;
        lVar.tilt = f;
        return lVar;
    }

    public static AbstractCameraUpdateMessage d(float f) {
        AbstractCameraUpdateMessage lVar = new l();
        lVar.nowType = Type.newCameraPosition;
        lVar.bearing = f;
        return lVar;
    }

    public static AbstractCameraUpdateMessage b(float f, Point point) {
        AbstractCameraUpdateMessage lVar = new l();
        lVar.nowType = Type.newCameraPosition;
        lVar.geoPoint = point;
        lVar.bearing = f;
        return lVar;
    }

    public static AbstractCameraUpdateMessage a(LatLng latLng) {
        return a(CameraPosition.builder().target(latLng).zoom(Float.NaN).bearing(Float.NaN).tilt(Float.NaN).build());
    }

    public static AbstractCameraUpdateMessage a(LatLng latLng, float f) {
        return a(CameraPosition.builder().target(latLng).zoom(f).bearing(Float.NaN).tilt(Float.NaN).build());
    }

    public static AbstractCameraUpdateMessage a(LatLngBounds latLngBounds, int i) {
        AbstractCameraUpdateMessage kVar = new k();
        kVar.nowType = Type.newLatLngBounds;
        kVar.bounds = latLngBounds;
        kVar.paddingLeft = i;
        kVar.paddingRight = i;
        kVar.paddingTop = i;
        kVar.paddingBottom = i;
        return kVar;
    }

    public static AbstractCameraUpdateMessage a(LatLngBounds latLngBounds, int i, int i2, int i3) {
        AbstractCameraUpdateMessage kVar = new k();
        kVar.nowType = Type.newLatLngBoundsWithSize;
        kVar.bounds = latLngBounds;
        kVar.paddingLeft = i3;
        kVar.paddingRight = i3;
        kVar.paddingTop = i3;
        kVar.paddingBottom = i3;
        kVar.width = i;
        kVar.height = i2;
        return kVar;
    }

    public static AbstractCameraUpdateMessage a(LatLngBounds latLngBounds, int i, int i2, int i3, int i4) {
        AbstractCameraUpdateMessage kVar = new k();
        kVar.nowType = Type.newLatLngBounds;
        kVar.bounds = latLngBounds;
        kVar.paddingLeft = i;
        kVar.paddingRight = i2;
        kVar.paddingTop = i3;
        kVar.paddingBottom = i4;
        return kVar;
    }

    public static AbstractCameraUpdateMessage c() {
        return new l();
    }
}
