package com.amap.api.maps.model;

import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.Animation.AnimationListener;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IMarker;
import com.autonavi.amap.mapcore.interfaces.IMarkerAction;
import java.util.ArrayList;

public final class Marker extends BasePointOverlay {
    private IMarker a;

    public Marker(IMarker iMarker) {
        this.a = iMarker;
    }

    public void setPeriod(int i) {
        try {
            this.a.setPeriod(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getPeriod() {
        try {
            return this.a.getPeriod();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setIcons(ArrayList<BitmapDescriptor> arrayList) {
        try {
            this.a.setIcons(arrayList);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        try {
            return this.a.getIcons();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void remove() {
        try {
            this.a.remove();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void destroy() {
        try {
            if (this.a != null) {
                this.a.destroy(true);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String getId() {
        try {
            return this.a.getId();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setPerspective(boolean z) {
        try {
            this.a.setPerspective(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isPerspective() {
        try {
            return this.a.isPerspective();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public void setPosition(LatLng latLng) {
        try {
            this.a.setPosition(latLng);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public LatLng getPosition() {
        try {
            return this.a.getPosition();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setTitle(String str) {
        try {
            this.a.setTitle(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String getTitle() {
        try {
            return this.a.getTitle();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setSnippet(String str) {
        try {
            this.a.setSnippet(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String getSnippet() {
        try {
            return this.a.getSnippet();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            try {
                this.a.setIcon(bitmapDescriptor);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void setAnchor(float f, float f2) {
        try {
            this.a.setAnchor(f, f2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setDraggable(boolean z) {
        try {
            this.a.setDraggable(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isDraggable() {
        return this.a.isDraggable();
    }

    public void showInfoWindow() {
        try {
            this.a.showInfoWindow();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void hideInfoWindow() {
        try {
            this.a.hideInfoWindow();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isInfoWindowShown() {
        return this.a.isInfoWindowShown();
    }

    public void setVisible(boolean z) {
        try {
            this.a.setVisible(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isVisible() {
        try {
            return this.a.isVisible();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj != null) {
            try {
                if (obj instanceof Marker) {
                    z = this.a.equalsRemote(((Marker) obj).a);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return z;
    }

    public int hashCode() {
        return this.a.hashCodeRemote();
    }

    public void setObject(Object obj) {
        this.a.setObject(obj);
    }

    public Object getObject() {
        return this.a.getObject();
    }

    public void setRotateAngle(float f) {
        try {
            this.a.setRotateAngle(f);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public float getRotateAngle() {
        return this.a.getRotateAngle();
    }

    public void setToTop() {
        try {
            this.a.set2Top();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setGeoPoint(IPoint iPoint) {
        this.a.setGeoPoint(iPoint);
    }

    public IPoint getGeoPoint() {
        return this.a.getGeoPoint();
    }

    public void setFlat(boolean z) {
        try {
            this.a.setFlat(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isFlat() {
        return this.a.isFlat();
    }

    public void setPositionByPixels(int i, int i2) {
        this.a.setPositionByPixels(i, i2);
    }

    public void setZIndex(float f) {
        this.a.setZIndex(f);
    }

    public float getZIndex() {
        return this.a.getZIndex();
    }

    public void setAnimation(Animation animation) {
        try {
            this.a.setAnimation(animation);
        } catch (Throwable th) {
        }
    }

    public boolean startAnimation() {
        return this.a.startAnimation();
    }

    public void setAnimationListener(AnimationListener animationListener) {
        this.a.setAnimationListener(animationListener);
    }

    public float getAlpha() {
        IMarkerAction iMarkerAction = this.a.getIMarkerAction();
        if (iMarkerAction != null) {
            return iMarkerAction.getAlpha();
        }
        return 1.0f;
    }

    public void setAlpha(float f) {
        IMarkerAction iMarkerAction = this.a.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setAlpha(f);
        }
    }

    public int getDisplayLevel() {
        IMarkerAction iMarkerAction = this.a.getIMarkerAction();
        if (iMarkerAction != null) {
            return iMarkerAction.getDisplayLevel();
        }
        return 5;
    }

    public MarkerOptions getOptions() {
        IMarkerAction iMarkerAction = this.a.getIMarkerAction();
        if (iMarkerAction != null) {
            return iMarkerAction.getOptions();
        }
        return null;
    }

    public boolean isClickable() {
        IMarkerAction iMarkerAction = this.a.getIMarkerAction();
        if (iMarkerAction != null) {
            return iMarkerAction.isClickable();
        }
        return false;
    }

    public boolean isInfoWindowAutoOverturn() {
        IMarkerAction iMarkerAction = this.a.getIMarkerAction();
        if (iMarkerAction != null) {
            return iMarkerAction.isInfoWindowAutoOverturn();
        }
        return false;
    }

    public boolean isInfoWindowEnable() {
        IMarkerAction iMarkerAction = this.a.getIMarkerAction();
        if (iMarkerAction != null) {
            return iMarkerAction.isInfoWindowEnable();
        }
        return false;
    }

    public void setInfoWindowEnable(boolean z) {
        IMarkerAction iMarkerAction = this.a.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setInfoWindowEnable(z);
        }
    }

    public void setMarkerOptions(MarkerOptions markerOptions) {
        IMarkerAction iMarkerAction = this.a.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setMarkerOptions(markerOptions);
        }
    }

    public void setAutoOverturnInfoWindow(boolean z) {
        IMarkerAction iMarkerAction = this.a.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setAutoOverturnInfoWindow(z);
        }
    }

    public void setClickable(boolean z) {
        IMarkerAction iMarkerAction = this.a.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setClickable(z);
        }
    }

    public void setDisplayLevel(int i) {
        IMarkerAction iMarkerAction = this.a.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setDisplayLevel(i);
        }
    }

    public void setFixingPointEnable(boolean z) {
        IMarkerAction iMarkerAction = this.a.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setFixingPointEnable(z);
        }
    }

    public boolean isRemoved() {
        if (this.a != null) {
            return this.a.isRemoved();
        }
        return false;
    }

    public void setPositionNotUpdate(LatLng latLng) {
        setPosition(latLng);
    }

    public void setRotateAngleNotUpdate(float f) {
        IMarkerAction iMarkerAction = this.a.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setRotateAngleNotUpdate(f);
        }
    }

    public void setBelowMaskLayer(boolean z) {
        this.a.setBelowMaskLayer(z);
    }

    public float getAnchorU() {
        return this.a.getAnchorU();
    }

    public float getAnchorV() {
        return this.a.getAnchorV();
    }
}
