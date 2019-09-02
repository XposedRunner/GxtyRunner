package com.amap.api.maps.model;

import com.amap.api.maps.model.animation.Animation;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IglModel;

public class GL3DModel extends BasePointOverlay {
    private final IglModel a;

    public GL3DModel(IglModel iglModel) {
        this.a = iglModel;
    }

    public void setPosition(LatLng latLng) {
        try {
            this.a.setPosition(latLng);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAngle(float f) {
        try {
            this.a.setRotateAngle(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getAngle() {
        try {
            return this.a.getRotateAngle();
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public LatLng getPosition() {
        try {
            return this.a.getPosition();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getId() {
        try {
            return this.a.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void setAnimation(Animation animation) {
        try {
            this.a.setAnimation(animation);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean startAnimation() {
        try {
            return this.a.startAnimation();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setModelFixedLength(int i) {
        try {
            this.a.setModelFixedLength(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove() {
        try {
            this.a.remove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isVisible() {
        try {
            return this.a.isVisible();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setVisible(boolean z) {
        try {
            this.a.setVisible(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setObject(Object obj) {
        try {
            this.a.setObject(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getObject() {
        if (this.a != null) {
            return this.a.getObject();
        }
        return null;
    }

    public void setRotateAngle(float f) {
        try {
            this.a.setRotateAngle(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getRotateAngle() {
        try {
            return this.a.getRotateAngle();
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public void setZoomLimit(float f) {
        if (this.a != null) {
            this.a.setZoomLimit(f);
        }
    }

    public void destroy() {
        if (this.a != null) {
            this.a.destroy();
        }
    }

    public void setGeoPoint(IPoint iPoint) {
        if (this.a != null) {
            this.a.setGeoPoint(iPoint);
        }
    }

    public void setTitle(String str) {
        if (this.a != null) {
            this.a.setTitle(str);
        }
    }

    public String getTitle() {
        try {
            return this.a.getTitle();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getSnippet() {
        try {
            return this.a.getSnippet();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void setSnippet(String str) {
        if (this.a != null) {
            this.a.setSnippet(str);
        }
    }

    public void showInfoWindow() {
        try {
            this.a.showInfoWindow();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
