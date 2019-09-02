package com.amap.api.maps.model;

import com.autonavi.amap.mapcore.interfaces.IPolyline;
import java.util.List;

public class Polyline {
    private final IPolyline a;

    public Polyline(IPolyline iPolyline) {
        this.a = iPolyline;
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

    public void setPoints(List<LatLng> list) {
        try {
            this.a.setPoints(list);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public List<LatLng> getPoints() {
        try {
            return this.a.getPoints();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setGeodesic(boolean z) {
        try {
            if (this.a.isGeodesic() != z) {
                List points = getPoints();
                this.a.setGeodesic(z);
                setPoints(points);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isGeodesic() {
        return this.a.isGeodesic();
    }

    public void setDottedLine(boolean z) {
        this.a.setDottedLine(z);
    }

    public boolean isDottedLine() {
        return this.a.isDottedLine();
    }

    public void setWidth(float f) {
        try {
            this.a.setWidth(f);
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

    public void setColor(int i) {
        try {
            this.a.setColor(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getColor() {
        try {
            return this.a.getColor();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
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

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj instanceof Polyline) {
            try {
                z = this.a.equalsRemote(((Polyline) obj).a);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return z;
    }

    public int hashCode() {
        try {
            return this.a.hashCodeRemote();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public LatLng getNearestLatLng(LatLng latLng) {
        return this.a.getNearestLatLng(latLng);
    }

    public void setTransparency(float f) {
        this.a.setTransparency(f);
    }

    public void setAboveMaskLayer(boolean z) {
        this.a.setAboveMaskLayer(z);
    }

    public void setCustomTexture(BitmapDescriptor bitmapDescriptor) {
        this.a.setCustomTexture(bitmapDescriptor);
    }

    public void setOptions(PolylineOptions polylineOptions) {
        this.a.setOptions(polylineOptions);
    }

    public PolylineOptions getOptions() {
        return this.a.getOptions();
    }

    public void setCustemTextureIndex(List<Integer> list) {
        this.a.setCustemTextureIndex(list);
    }
}
