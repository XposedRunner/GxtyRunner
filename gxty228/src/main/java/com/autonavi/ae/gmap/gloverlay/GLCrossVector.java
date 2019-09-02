package com.autonavi.ae.gmap.gloverlay;

import android.graphics.Rect;
import com.amap.api.mapcore.util.lj;
import com.autonavi.ae.gmap.gloverlay.GLOverlay.EAMapOverlayTpye;

public class GLCrossVector extends GLOverlay {

    public static class AVectorCrossAttr {
        public boolean dayMode = true;
        public int fArrowBorderWidth;
        public int fArrowLineWidth;
        public int stAreaColor;
        public Rect stAreaRect;
        public int stArrowBorderColor;
        public int stArrowLineColor;
    }

    private static native void nativeAddVectorCar(long j, int i, int i2, int i3);

    private static native int nativeAddVectorData(long j, int[] iArr, byte[] bArr);

    private static native int nativeGetFBOTextureId(long j);

    private static native void nativeInitFBOTexture(long j, int i, int i2);

    private static native void nativeSetArrowResId(long j, boolean z, int i);

    private static native void nativeSetBackgroundResId(long j, int i);

    private static native void nativeSetCarResId(long j, int i);

    public GLCrossVector(int i, lj ljVar, int i2) {
        super(i, ljVar, i2);
        if (ljVar != null && ljVar.a() != null) {
            this.mNativeInstance = ljVar.a().createOverlay(i, EAMapOverlayTpye.AMAPOVERLAY_VECTOR.ordinal());
        }
    }

    public int addVectorItem(AVectorCrossAttr aVectorCrossAttr, byte[] bArr, int i) {
        int i2 = 1;
        if (aVectorCrossAttr == null || bArr == null || i == 0) {
            return -1;
        }
        int[] iArr = new int[10];
        iArr[0] = aVectorCrossAttr.stAreaRect.left;
        iArr[1] = aVectorCrossAttr.stAreaRect.top;
        iArr[2] = aVectorCrossAttr.stAreaRect.right;
        iArr[3] = aVectorCrossAttr.stAreaRect.bottom;
        iArr[4] = aVectorCrossAttr.stAreaColor;
        iArr[5] = aVectorCrossAttr.fArrowBorderWidth;
        iArr[6] = aVectorCrossAttr.stArrowBorderColor;
        iArr[7] = aVectorCrossAttr.fArrowLineWidth;
        iArr[8] = aVectorCrossAttr.stArrowLineColor;
        if (!aVectorCrossAttr.dayMode) {
            i2 = 0;
        }
        iArr[9] = i2;
        return nativeAddVectorData(this.mNativeInstance, iArr, bArr);
    }

    public void addVectorRemainDis(int i) {
    }

    public void addVectorCar(int i, int i2, int i3) {
        nativeAddVectorCar(this.mNativeInstance, i, i2, i3);
    }

    public void setRoadResId(boolean z, int i) {
    }

    public void setArrowResId(boolean z, int i) {
        nativeSetArrowResId(this.mNativeInstance, z, i);
    }

    public void setCarResId(int i) {
        nativeSetCarResId(this.mNativeInstance, i);
    }

    public void setBackgroundResId(int i) {
        nativeSetBackgroundResId(this.mNativeInstance, i);
    }

    public void setSkyResId(boolean z, int i) {
    }

    public int getFBOTextureId() {
        return nativeGetFBOTextureId(this.mNativeInstance);
    }

    public void initFBOTexture(int i, int i2) {
        nativeInitFBOTexture(this.mNativeInstance, i, i2);
    }
}
