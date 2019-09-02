package com.amap.api.maps.model;

import android.graphics.Bitmap;
import com.autonavi.ae.gmap.gloverlay.CrossVectorOverlay;
import com.autonavi.ae.gmap.gloverlay.GLCrossVector.AVectorCrossAttr;

public class CrossOverlay {
    CrossVectorOverlay a = null;

    public interface GenerateCrossImageListener {
        void onGenerateComplete(Bitmap bitmap, int i);
    }

    public CrossOverlay(CrossOverlayOptions crossOverlayOptions, CrossVectorOverlay crossVectorOverlay) {
        this.a = crossVectorOverlay;
    }

    public int setData(byte[] bArr) {
        if (!(bArr == null || this.a == null)) {
            try {
                return this.a.setData(bArr);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return -1;
    }

    public void setAttribute(AVectorCrossAttr aVectorCrossAttr) {
        try {
            this.a.setAttribute(aVectorCrossAttr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setVisible(boolean z) {
        if (this.a != null) {
            try {
                this.a.setVisible(z);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void remove() {
        if (this.a != null) {
            try {
                this.a.remove();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void setImageMode(boolean z) {
        if (this.a != null) {
            try {
                this.a.setImageMode(z);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void setGenerateCrossImageListener(GenerateCrossImageListener generateCrossImageListener) {
        if (this.a != null) {
            try {
                this.a.setGenerateCrossImageListener(generateCrossImageListener);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
