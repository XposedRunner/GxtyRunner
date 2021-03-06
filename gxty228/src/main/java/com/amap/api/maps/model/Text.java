package com.amap.api.maps.model;

import android.graphics.Typeface;
import com.autonavi.amap.mapcore.interfaces.IText;

public final class Text {
    public static final int ALIGN_BOTTOM = 16;
    public static final int ALIGN_CENTER_HORIZONTAL = 4;
    public static final int ALIGN_CENTER_VERTICAL = 32;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 8;
    private IText a;

    public Text(IText iText) {
        this.a = iText;
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

    public void setText(String str) {
        try {
            this.a.setText(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String getText() {
        try {
            return this.a.getText();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setBackgroundColor(int i) {
        try {
            this.a.setBackgroundColor(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getBackgroundColor() {
        try {
            return this.a.getBackgroundColor();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setFontColor(int i) {
        try {
            this.a.setFontColor(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getFontColor() {
        try {
            return this.a.getFontColor();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setFontSize(int i) {
        try {
            this.a.setFontSize(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getFontSize() {
        try {
            return this.a.getFontSize();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setTypeface(Typeface typeface) {
        try {
            this.a.setTypeface(typeface);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public Typeface getTypeface() {
        try {
            return this.a.getTypeface();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setAlign(int i, int i2) {
        try {
            this.a.setAlign(i, i2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getAlignX() {
        try {
            return this.a.getAlignX();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public int getAlignY() {
        try {
            return this.a.getAlignY();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
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
        try {
            if (obj instanceof Text) {
                z = this.a.equalsRemote(((Text) obj).a);
            }
        } catch (Throwable th) {
            th.printStackTrace();
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

    public void setRotate(float f) {
        try {
            this.a.setRotateAngle(f);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public float getRotate() {
        return this.a.getRotateAngle();
    }

    public void setZIndex(float f) {
        this.a.setZIndex(f);
    }

    public float getZIndex() {
        return this.a.getZIndex();
    }
}
