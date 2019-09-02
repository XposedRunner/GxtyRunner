package com.amap.api.maps.model;

import com.autonavi.amap.mapcore.interfaces.ICircle;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

public final class Circle {
    private final ICircle a;

    public Circle(ICircle iCircle) {
        this.a = iCircle;
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

    public void setCenter(LatLng latLng) {
        try {
            this.a.setCenter(latLng);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public LatLng getCenter() {
        try {
            return this.a.getCenter();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setRadius(double d) {
        try {
            this.a.setRadius(d);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public double getRadius() {
        try {
            return this.a.getRadius();
        } catch (Throwable th) {
            th.printStackTrace();
            return Utils.DOUBLE_EPSILON;
        }
    }

    public void setStrokeWidth(float f) {
        try {
            this.a.setStrokeWidth(f);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public float getStrokeWidth() {
        try {
            return this.a.getStrokeWidth();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public void setStrokeColor(int i) {
        try {
            this.a.setStrokeColor(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getStrokeColor() {
        try {
            return this.a.getStrokeColor();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setFillColor(int i) {
        try {
            this.a.setFillColor(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getFillColor() {
        try {
            return this.a.getFillColor();
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
        if (obj != null && (obj instanceof Circle)) {
            try {
                z = this.a.equalsRemote(((Circle) obj).a);
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

    public boolean contains(LatLng latLng) {
        try {
            return this.a.contains(latLng);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public void setHoleOptions(List<BaseHoleOptions> list) {
        try {
            this.a.setHoleOptions(list);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public List<BaseHoleOptions> getHoleOptions() {
        try {
            return this.a.getHoleOptions();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setStrokeDottedLineType(int i) {
        try {
            this.a.setDottedLineType(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getStrokeDottedLineType() {
        try {
            return this.a.getDottedLineType();
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }
}
