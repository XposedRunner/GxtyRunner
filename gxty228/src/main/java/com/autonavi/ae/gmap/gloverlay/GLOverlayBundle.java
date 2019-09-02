package com.autonavi.ae.gmap.gloverlay;

import android.util.SparseArray;
import com.amap.api.mapcore.util.lj;
import java.util.ArrayList;
import java.util.List;

public class GLOverlayBundle<E extends BaseMapOverlay<?, ?>> {
    private int mEngineID;
    lj mGLMapView = null;
    private long mNativeInstance = 0;
    private final List<E> mOverlayList = new ArrayList();
    private SparseArray<GLOverlayTexture> mTextureCaches = new SparseArray();

    public static class GLAmapFocusHits {
        public long mHitedIndex = 0;
        public long mHitedTimes = 1000;
        public long mOverlayHashCode = 0;
    }

    private static native void nativeAddGLOverlay(long j, long j2, long j3);

    private static native void nativeAddGLOverlayEx(long j, long j2, long j3, int i);

    private static native void nativeClearAllGLOverlay(long j, boolean z);

    private static native boolean nativeOnSingleTapLineOverlay(long j, int i, int i2, long[] jArr);

    private static native boolean nativeOnSingleTapPointOverlay(long j, int i, int i2, long[] jArr);

    private static native void nativeRemoveGLOverlay(long j, long j2);

    private static native void nativeRemoveGLOverlayEx(long j, long j2, int i);

    private static native void nativeSortAllGLOverlay(long j);

    public static void intClr2PVRClr(float[] fArr, int i) {
        fArr[2] = ((float) (i & 255)) / 255.0f;
        fArr[1] = ((float) ((i >> 8) & 255)) / 255.0f;
        fArr[0] = ((float) ((i >> 16) & 255)) / 255.0f;
        fArr[3] = ((float) ((i >> 24) & 255)) / 255.0f;
    }

    public GLOverlayBundle(int i, lj ljVar) {
        this.mEngineID = i;
        this.mGLMapView = ljVar;
        this.mNativeInstance = ljVar.a().getGlOverlayMgrPtr(this.mEngineID);
    }

    public int getOverlayCount() {
        int size;
        synchronized (this.mOverlayList) {
            size = this.mOverlayList.size();
        }
        return size;
    }

    public boolean cotainsOverlay(E e) {
        if (e == null) {
            return false;
        }
        boolean contains;
        synchronized (this.mOverlayList) {
            contains = this.mOverlayList.contains(e);
        }
        return contains;
    }

    public E getOverlay(int i) {
        E e;
        synchronized (this.mOverlayList) {
            if (i >= 0) {
                if (i <= this.mOverlayList.size() - 1) {
                    BaseMapOverlay baseMapOverlay = (BaseMapOverlay) this.mOverlayList.get(i);
                }
            }
            e = null;
        }
        return e;
    }

    private int getOverlyExType(E e) {
        return 0;
    }

    public void addOverlay(E e) {
        if (e != null) {
            nativeAddGLOverlay(this.mNativeInstance, e.getGLOverlay().getNativeInstatnce(), (long) e.getGLOverlay().getCode());
            e.getGLOverlay().mIsInBundle = true;
            synchronized (this.mOverlayList) {
                this.mOverlayList.add(e);
            }
        }
    }

    public void sortOverlay() {
        nativeSortAllGLOverlay(this.mNativeInstance);
    }

    public void removeOverlay(E e) {
        if (e != null) {
            nativeRemoveGLOverlay(this.mNativeInstance, e.getGLOverlay().getNativeInstatnce());
            e.getGLOverlay().mIsInBundle = false;
            synchronized (this.mOverlayList) {
                this.mOverlayList.remove(e);
            }
        }
    }

    public void removeAll(boolean z) {
        nativeClearAllGLOverlay(this.mNativeInstance, z);
        synchronized (this.mOverlayList) {
            for (int i = 0; i < this.mOverlayList.size(); i++) {
                BaseMapOverlay baseMapOverlay = (BaseMapOverlay) this.mOverlayList.get(i);
                if (baseMapOverlay != null) {
                    baseMapOverlay.getGLOverlay().mIsInBundle = false;
                }
            }
            this.mOverlayList.clear();
        }
    }

    public void clearFocus() {
        if (this.mOverlayList != null) {
            synchronized (this.mOverlayList) {
                for (int i = 0; i < this.mOverlayList.size(); i++) {
                    BaseMapOverlay baseMapOverlay = (BaseMapOverlay) this.mOverlayList.get(i);
                    if (baseMapOverlay != null) {
                        baseMapOverlay.clearFocus();
                    }
                }
            }
        }
    }

    public boolean onSingleTap(int i, int i2, int i3, int i4) {
        boolean onSingleTapPoints;
        if ((i4 & 1) == 1) {
            onSingleTapPoints = onSingleTapPoints(i, i2, i3);
        } else {
            onSingleTapPoints = false;
        }
        if (onSingleTapPoints) {
            return true;
        }
        if ((i4 & 2) == 2) {
            onSingleTapPoints = onSingleTapLines(i, i2, i3);
        }
        if (onSingleTapPoints) {
            return true;
        }
        return false;
    }

    boolean onSingleTapPoints(int i, int i2, int i3) {
        return false;
    }

    boolean onSingleTapLines(int i, int i2, int i3) {
        return false;
    }

    public long checkSingleTapOnLine(int i, int i2) {
        long[] jArr = new long[3];
        if (nativeOnSingleTapLineOverlay(this.mNativeInstance, i, i2, jArr)) {
            return jArr[0];
        }
        return -1;
    }

    public long checkSingleTapOnPoint(int i, int i2) {
        long[] jArr = new long[3];
        if (nativeOnSingleTapPointOverlay(this.mNativeInstance, i, i2, jArr)) {
            return jArr[0];
        }
        return -1;
    }

    public GLOverlayTexture getOverlayTextureItem(int i) {
        GLOverlayTexture gLOverlayTexture;
        synchronized (this.mTextureCaches) {
            gLOverlayTexture = (GLOverlayTexture) this.mTextureCaches.get(i);
        }
        return gLOverlayTexture;
    }

    public boolean addOverlayTextureItem(int i, int i2, int i3, int i4) {
        GLOverlayTexture gLOverlayTexture = new GLOverlayTexture(i, i2, i3, i4);
        synchronized (this.mTextureCaches) {
            this.mTextureCaches.put(i, gLOverlayTexture);
        }
        return true;
    }

    public boolean addOverlayTextureItem(int i, int i2, float f, float f2, int i3, int i4) {
        GLOverlayTexture gLOverlayTexture = new GLOverlayTexture(i, i2, f, f2, i3, i4);
        synchronized (this.mTextureCaches) {
            this.mTextureCaches.put(i, gLOverlayTexture);
        }
        return true;
    }

    public void clearOverlayTexture() {
        synchronized (this.mTextureCaches) {
            this.mTextureCaches.clear();
        }
    }

    public void reculateRouteBoard(lj ljVar) {
    }
}
