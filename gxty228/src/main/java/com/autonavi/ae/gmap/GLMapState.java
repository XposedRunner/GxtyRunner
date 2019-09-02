package com.autonavi.ae.gmap;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.VirtualEarthProjection;

public class GLMapState {
    private boolean isNewInstance = false;
    private long nativeEngineInstance = 0;
    private long nativeStateInstance = 0;

    public static native float nativeCalMapZoomScalefactor(int i, int i2, float f);

    public static native float nativeGetCameraDegree(long j);

    public static native float nativeGetGLUnitWithWin(long j, int i);

    public static native float nativeGetMapAngle(long j);

    public static native void nativeGetMapCenter(long j, Point point);

    public static native float nativeGetMapZoomer(long j);

    public static native void nativeGetPixel20Bound(long j, Rect rect, int i, int i2);

    public static native void nativeGetProjectionMatrix(long j, float[] fArr);

    public static native float nativeGetSkyHeight(long j);

    public static native void nativeGetViewMatrix(long j, float[] fArr);

    public static native long nativeNewInstance(int i, long j);

    public static native void nativeP20ToScreenPoint(long j, int i, int i2, int i3, PointF pointF);

    public static native void nativeRecalculate(long j);

    public static native void nativeScreenToP20Point(long j, float f, float f2, Point point);

    public static native void nativeSetCameraDegree(long j, float f);

    public static native void nativeSetMapAngle(long j, float f);

    public static native void nativeSetMapCenter(long j, int i, int i2);

    private static native void nativeSetMapState(int i, long j, long j2);

    public static native void nativeSetMapZoomer(long j, float f);

    public static native void nativeStateDestroy(long j);

    public GLMapState(int i, long j) {
        if (j != 0) {
            this.nativeEngineInstance = j;
            this.nativeStateInstance = nativeNewInstance(i, j);
            this.isNewInstance = true;
        }
    }

    public GLMapState(long j, long j2) {
        if (j != 0) {
            this.nativeEngineInstance = j;
            this.nativeStateInstance = j2;
            this.isNewInstance = true;
        }
    }

    public static void lonlat2Geo(double d, double d2, IPoint iPoint) {
        Point latLongToPixels = VirtualEarthProjection.latLongToPixels(d2, d, 20);
        iPoint.x = latLongToPixels.x;
        iPoint.y = latLongToPixels.y;
    }

    public static void geo2LonLat(int i, int i2, DPoint dPoint) {
        DPoint pixelsToLatLong = VirtualEarthProjection.pixelsToLatLong((long) i, (long) i2, 20);
        dPoint.x = pixelsToLatLong.x;
        dPoint.y = pixelsToLatLong.y;
        pixelsToLatLong.recycle();
    }

    public void screenToP20Point(int i, int i2, Point point) {
        if (this.nativeStateInstance != 0) {
            nativeScreenToP20Point(this.nativeStateInstance, (float) i, (float) i2, point);
        }
    }

    public void p20ToScreenPoint(int i, int i2, FPoint fPoint) {
        if (this.nativeStateInstance != 0) {
            nativeP20ToScreenPoint(this.nativeStateInstance, i, i2, 0, fPoint);
        }
    }

    public void getMapGeoCenter(IPoint iPoint) {
        nativeGetMapCenter(this.nativeStateInstance, iPoint);
    }

    public Point getMapGeoCenter() {
        Point point = new Point();
        nativeGetMapCenter(this.nativeStateInstance, point);
        return point;
    }

    public void setMapGeoCenter(int i, int i2) {
        if (this.nativeStateInstance != 0) {
            nativeSetMapCenter(this.nativeStateInstance, i, i2);
        }
    }

    public void setCameraDegree(float f) {
        if (this.nativeStateInstance != 0) {
            nativeSetCameraDegree(this.nativeStateInstance, f);
        }
    }

    public void setMapAngle(float f) {
        if (this.nativeStateInstance != 0) {
            nativeSetMapAngle(this.nativeStateInstance, f);
        }
    }

    public void setMapZoomer(float f) {
        if (this.nativeStateInstance != 0) {
            nativeSetMapZoomer(this.nativeStateInstance, f);
        }
    }

    public float getMapZoomer() {
        if (this.nativeStateInstance != 0) {
            return nativeGetMapZoomer(this.nativeStateInstance);
        }
        return 0.0f;
    }

    public float getCameraDegree() {
        if (this.nativeStateInstance != 0) {
            return nativeGetCameraDegree(this.nativeStateInstance);
        }
        return 0.0f;
    }

    public float getMapAngle() {
        if (this.nativeStateInstance != 0) {
            return nativeGetMapAngle(this.nativeStateInstance);
        }
        return 0.0f;
    }

    public void recalculate() {
        if (this.nativeStateInstance != 0) {
            nativeRecalculate(this.nativeStateInstance);
        }
    }

    public void recycle() {
        if (this.nativeStateInstance != 0 && this.isNewInstance) {
            nativeStateDestroy(this.nativeStateInstance);
        }
        this.nativeStateInstance = 0;
    }

    public void reset() {
        if (this.nativeStateInstance != 0) {
            recycle();
        }
        if (this.nativeEngineInstance != 0) {
            this.nativeStateInstance = nativeNewInstance(1, this.nativeEngineInstance);
        }
    }

    public float getMapLenWithWin(int i) {
        return getGLUnitWithWin(i);
    }

    public float getGLUnitWithWin(int i) {
        if (this.nativeStateInstance != 0) {
            return nativeGetGLUnitWithWin(this.nativeStateInstance, i);
        }
        return 0.0f;
    }

    public void getPixel20Bound(Rect rect, int i, int i2) {
        if (this.nativeStateInstance != 0) {
            nativeGetPixel20Bound(this.nativeStateInstance, rect, i, i2);
        }
    }

    public long getNativeInstance() {
        return this.nativeStateInstance;
    }

    public void getViewMatrix(float[] fArr) {
        if (fArr != null && fArr.length == 16) {
            nativeGetViewMatrix(this.nativeStateInstance, fArr);
        }
    }

    public void getProjectionMatrix(float[] fArr) {
        if (fArr != null && fArr.length == 16) {
            nativeGetProjectionMatrix(this.nativeStateInstance, fArr);
        }
    }

    public float getSkyHeight() {
        if (this.nativeStateInstance != 0) {
            return nativeGetSkyHeight(this.nativeStateInstance);
        }
        return 0.0f;
    }

    public void setNativeMapengineState(int i, long j) {
        if (j != 0 && this.nativeStateInstance != 0) {
            this.nativeEngineInstance = j;
            nativeSetMapState(i, j, this.nativeStateInstance);
        }
    }

    public static float calMapZoomScalefactor(int i, int i2, int i3) {
        return nativeCalMapZoomScalefactor(i, i2, (float) i3);
    }

    public String toString() {
        return "instance: " + this.nativeStateInstance + " " + "center: " + getMapGeoCenter().x + " , " + getMapGeoCenter().y + " " + "bearing:" + getCameraDegree() + "  " + "tilt:" + getMapAngle() + "  " + "zoom:" + getMapZoomer() + "  ";
    }
}
