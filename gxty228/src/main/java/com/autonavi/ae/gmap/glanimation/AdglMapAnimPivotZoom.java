package com.autonavi.ae.gmap.glanimation;

import android.graphics.Point;
import android.os.SystemClock;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.IPoint;

public class AdglMapAnimPivotZoom extends AbstractAdglAnimation {
    private int beginCenterGeoX;
    private int beginCenterGeoY;
    private boolean hasCheckParams;
    private float mapLevelTo;
    private int pivotGeoX;
    private int pivotGeoY;
    private float winPivotX;
    private float winPivotY;
    private boolean zoomCenter;
    AbstractAdglAnimationParam1V zoomParam = null;

    public void reset() {
        this.isOver = false;
        this.hasCheckParams = false;
        this.zoomCenter = true;
        this.mapLevelTo = 0.0f;
        this.beginCenterGeoX = 0;
        this.beginCenterGeoY = 0;
        this.pivotGeoX = 0;
        this.pivotGeoY = 0;
        this.winPivotX = 0.0f;
        this.winPivotY = 0.0f;
        if (this.zoomParam != null) {
            this.zoomParam.reset();
        }
    }

    public void commitAnimation(Object obj) {
        this.isOver = true;
        this.hasCheckParams = false;
        GLMapState gLMapState = (GLMapState) obj;
        if (gLMapState != null) {
            float mapZoomer = gLMapState.getMapZoomer();
            if (((double) Math.abs(this.mapLevelTo - mapZoomer)) < 1.0E-6d) {
                this.isOver = true;
                this.hasCheckParams = true;
                return;
            }
            this.zoomParam.setFromValue(mapZoomer);
            this.zoomParam.setToValue(this.mapLevelTo);
            if (!this.zoomCenter) {
                IPoint obtain = IPoint.obtain();
                gLMapState.getMapGeoCenter(obtain);
                this.beginCenterGeoX = obtain.x;
                this.beginCenterGeoY = obtain.y;
                Point obtain2 = IPoint.obtain();
                gLMapState.screenToP20Point((int) this.winPivotX, (int) this.winPivotY, obtain2);
                this.pivotGeoX = obtain2.x;
                this.pivotGeoY = obtain2.y;
                obtain.recycle();
                obtain2.recycle();
            }
            this.hasCheckParams = true;
            this.isOver = false;
            this.startTime = SystemClock.uptimeMillis();
        }
    }

    public AdglMapAnimPivotZoom(int i) {
        reset();
        this.duration = i;
    }

    public void setToMapZoomAndPivot(float f, int i, Point point) {
        float f2 = 20.0f;
        float f3 = 3.0f;
        this.zoomParam = new AbstractAdglAnimationParam1V();
        this.zoomParam.setInterpolatorType(i, 1.0f);
        if (f <= 20.0f) {
            f2 = f;
        }
        if (f2 >= 3.0f) {
            f3 = f2;
        }
        this.mapLevelTo = f3;
        if (point != null) {
            this.winPivotX = (float) point.x;
            this.winPivotY = (float) point.y;
            this.zoomCenter = false;
        }
    }

    public void doAnimation(Object obj) {
        float f = 20.0f;
        float f2 = 3.0f;
        GLMapState gLMapState = (GLMapState) obj;
        if (gLMapState != null) {
            if (!this.hasCheckParams) {
                commitAnimation(obj);
            }
            if (!this.isOver) {
                this.offsetTime = SystemClock.uptimeMillis() - this.startTime;
                float f3 = ((float) this.offsetTime) / ((float) this.duration);
                if (f3 > 1.0f) {
                    this.isOver = true;
                    f3 = 1.0f;
                }
                if (f3 >= 0.0f && f3 <= 1.0f) {
                    this.zoomParam.setNormalizedTime(f3);
                    f3 = this.zoomParam.getCurValue();
                    if (f3 > 20.0f) {
                        this.isOver = true;
                    } else {
                        f = f3;
                    }
                    if (f < 3.0f) {
                        this.isOver = true;
                    } else {
                        f2 = f;
                    }
                    if (!this.zoomCenter) {
                        f = (float) Math.pow(2.0d, (double) (f2 - this.zoomParam.getFromValue()));
                        gLMapState.setMapGeoCenter(((int) (((float) (this.pivotGeoX - this.beginCenterGeoX)) * (1.0f - (1.0f / f)))) + this.beginCenterGeoX, ((int) ((1.0f - (1.0f / f)) * ((float) (this.pivotGeoY - this.beginCenterGeoY)))) + this.beginCenterGeoY);
                    }
                    gLMapState.setMapZoomer(f2);
                }
            }
        }
    }
}
