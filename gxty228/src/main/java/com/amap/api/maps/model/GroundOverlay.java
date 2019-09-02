package com.amap.api.maps.model;

import com.autonavi.amap.mapcore.interfaces.IGroundOverlay;

public final class GroundOverlay {
    private IGroundOverlay a;

    public GroundOverlay(IGroundOverlay iGroundOverlay) {
        this.a = iGroundOverlay;
    }

    public void remove() {
        try {
            this.a.remove();
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

    public void setDimensions(float f) {
        try {
            this.a.setDimensions(f);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setImage(BitmapDescriptor bitmapDescriptor) {
        try {
            this.a.setImage(bitmapDescriptor);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setDimensions(float f, float f2) {
        try {
            this.a.setDimensions(f, f2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public float getWidth() {
        try {
            return this.a.getWidth();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public float getHeight() {
        try {
            return this.a.getHeight();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public void setPositionFromBounds(LatLngBounds latLngBounds) {
        try {
            this.a.setPositionFromBounds(latLngBounds);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public LatLngBounds getBounds() {
        try {
            return this.a.getBounds();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setBearing(float f) {
        try {
            this.a.setBearing(f);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public float getBearing() {
        try {
            return this.a.getBearing();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public void setZIndex(float f) {
        try {
            this.a.setZIndex(f);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public float getZIndex() {
        try {
            return this.a.getZIndex();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
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

    public void setTransparency(float f) {
        try {
            this.a.setTransparency(f);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public float getTransparency() {
        try {
            return this.a.getTransparency();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj != null && (obj instanceof GroundOverlay)) {
            try {
                z = this.a.equalsRemote(((GroundOverlay) obj).a);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return z;
    }

    public void destroy() {
        if (this.a != null) {
            this.a.destroy();
        }
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
