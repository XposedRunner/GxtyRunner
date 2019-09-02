package com.autonavi.ae.gmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.text.TextUtils;
import com.amap.api.mapcore.util.em;
import com.amap.api.mapcore.util.en;
import com.amap.api.mapcore.util.gz;
import com.amap.api.mapcore.util.lj;
import com.amap.api.maps.AMap.CancelableCallback;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.autonavi.ae.gmap.gesture.EAMapPlatformGestureInfo;
import com.autonavi.ae.gmap.glanimation.AbstractAdglAnimation;
import com.autonavi.ae.gmap.glanimation.AdglMapAnimFling;
import com.autonavi.ae.gmap.glanimation.AdglMapAnimGroup;
import com.autonavi.ae.gmap.glanimation.AdglMapAnimationMgr;
import com.autonavi.ae.gmap.glanimation.AdglMapAnimationMgr.MapAnimationListener;
import com.autonavi.ae.gmap.gloverlay.BaseMapOverlay;
import com.autonavi.ae.gmap.gloverlay.GLOverlayBundle;
import com.autonavi.ae.gmap.gloverlay.GLTextureProperty;
import com.autonavi.ae.gmap.style.StyleItem;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;
import com.autonavi.amap.mapcore.FileUtil;
import com.autonavi.amap.mapcore.IAMapEngineCallback;
import com.autonavi.amap.mapcore.interfaces.IAMapListener;
import com.autonavi.amap.mapcore.maploader.AMapLoader;
import com.autonavi.amap.mapcore.maploader.AMapLoader.ADataRequestParam;
import com.autonavi.amap.mapcore.maploader.NetworkState;
import com.autonavi.amap.mapcore.maploader.NetworkState.NetworkChangeListener;
import com.autonavi.amap.mapcore.message.AbstractGestureMapMessage;
import com.autonavi.amap.mapcore.message.HoverGestureMapMessage;
import com.autonavi.amap.mapcore.message.MoveGestureMapMessage;
import com.autonavi.amap.mapcore.message.RotateGestureMapMessage;
import com.autonavi.amap.mapcore.message.ScaleGestureMapMessage;
import com.autonavi.amap.mapcore.tools.GLConvertUtil;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.autonavi.amap.mapcore.tools.TextTextureGenerator;
import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GLMapEngine implements IAMapEngineCallback, NetworkChangeListener {
    Hashtable<Long, AMapLoader> aMapLoaderHashtable;
    GLOverlayBundle<BaseMapOverlay<?, ?>> bundle;
    private Context context;
    private GLMapState copyGLMapState;
    private boolean isEngineRenderComplete;
    boolean isGestureStep;
    boolean isMoveCameraStep;
    private List<AbstractCameraUpdateMessage> mAnimateStateMessageList;
    private List<AbstractGestureMapMessage> mGestureEndMessageList;
    private List<AbstractGestureMapMessage> mGestureMessageList;
    private lj mGlMapView;
    private Lock mLock;
    private IAMapListener mMapListener;
    private long mNativeMapengineInstance;
    private NetworkState mNetworkState;
    boolean mRequestDestroy;
    private AtomicInteger mRequestID;
    private List<AbstractCameraUpdateMessage> mStateMessageList;
    private TextTextureGenerator mTextTextureGenerator;
    private AdglMapAnimationMgr mapAnimationMgr;
    private int mapGestureCount;
    private Object mutLock;
    GLMapState state;
    private String userAgent;

    public static class InitParam {
        public String mConfigPath;
        public String mOfflineDataPath;
        public String mP3dCrossPath;
        public String mRootPath;
    }

    public static class MapViewInitParam {
        public int engineId;
        public int height;
        public float mapZoomScale;
        public int screenHeight;
        public float screenScale;
        public int screenWidth;
        public float textScale;
        public int width;
        public int x;
        public int y;
    }

    private static native boolean nativeAddOverlayTexture(int i, long j, int i2, int i3, float f, float f2, Bitmap bitmap, boolean z, boolean z2);

    private static native void nativeCreateAMapEngineWithFrame(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, float f, float f2, float f3);

    private static native long nativeCreateAMapInstance(String str, String str2, String str3);

    protected static native long nativeCreateOverlay(int i, long j, int i2);

    private static native void nativeDestroy(long j);

    private static native void nativeDestroyCurrentState(long j, long j2);

    protected static native void nativeDestroyOverlay(int i, long j);

    private static native void nativeFinishDownLoad(int i, long j, long j2);

    private static native void nativeGetCurTileIDs(int i, long j, int[] iArr, int i2);

    private static native long nativeGetCurrentMapState(int i, long j);

    private static native long nativeGetGlOverlayMgrPtr(int i, long j);

    public static native String nativeGetMapEngineVersion(int i);

    private static native int[] nativeGetMapModeState(int i, long j, boolean z);

    private static native boolean nativeGetSrvViewStateBoolValue(int i, long j, int i2);

    private static native void nativeInitAMapEngineCallback(long j, Object obj);

    private static native void nativeInitParam(String str, String str2, String str3, String str4);

    private static native boolean nativeIsEngineCreated(long j, int i);

    private static native void nativePopRenderState(int i, long j);

    private static native void nativePostRenderAMap(long j, int i);

    private static native void nativePushRendererState(int i, long j);

    private static native void nativeReceiveNetData(int i, long j, byte[] bArr, long j2, int i2);

    private static native void nativeRenderAMap(long j, int i);

    private static native void nativeSelectMapPois(int i, long j, int i2, int i3, int i4, byte[] bArr);

    private static native void nativeSetAllContentEnable(int i, long j, boolean z);

    private static native void nativeSetBuildingEnable(int i, long j, boolean z);

    private static native void nativeSetBuildingTextureEnable(int i, long j, boolean z);

    private static native void nativeSetCustomStyleTexture(int i, long j, byte[] bArr);

    private static native void nativeSetHighlightSubwayEnable(int i, long j, boolean z);

    private static native void nativeSetIndoorBuildingToBeActive(int i, long j, String str, int i2, String str2);

    private static native void nativeSetIndoorEnable(int i, long j, boolean z);

    private static native void nativeSetLabelEnable(int i, long j, boolean z);

    private static native boolean nativeSetMapModeAndStyle(int i, long j, int[] iArr, boolean z, boolean z2, StyleItem[] styleItemArr);

    private static native void nativeSetNetStatus(long j, int i);

    private static native void nativeSetOfflineDataEnable(int i, long j, boolean z);

    private static native void nativeSetParameter(int i, long j, int i2, int i3, int i4, int i5, int i6);

    private static native void nativeSetProjectionCenter(int i, long j, float f, float f2);

    private static native void nativeSetRenderListenerStatus(int i, long j);

    private static native void nativeSetServiceViewRect(int i, long j, int i2, int i3, int i4, int i5, int i6, int i7);

    private static native void nativeSetSetBackgroundTexture(int i, long j, byte[] bArr);

    private static native void nativeSetSimple3DEnable(int i, long j, boolean z);

    private static native void nativeSetSkyTexture(int i, long j, byte[] bArr);

    private static native void nativeSetSrvViewStateBoolValue(int i, long j, int i2, boolean z);

    private static native void nativeSetTrafficEnable(int i, long j, boolean z);

    private static native void nativeSetTrafficTexture(int i, long j, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4);

    private static native void nativeSetTrafficTextureAllInOne(int i, long j, byte[] bArr);

    public byte[] requireMapResource(int i, String str) {
        if (str == null) {
            return null;
        }
        String str2 = "map_assets/" + str;
        try {
            String str3 = "icons_5";
            String str4 = "bktile";
            if (this.mGlMapView.getMapConfig().isCustomStyleEnable()) {
                byte[] readFileContents;
                if (str.startsWith(str3)) {
                    readFileContents = FileUtil.readFileContents(this.mGlMapView.getMapConfig().getCustomTextureResourcePath());
                } else if (str.startsWith(str4)) {
                    readFileContents = FileUtil.readFileContentsFromAssets(this.context, str2);
                    int customBackgroundColor = this.mGlMapView.getMapConfig().getCustomBackgroundColor();
                    if (customBackgroundColor != 0) {
                        readFileContents = en.a(readFileContents, customBackgroundColor);
                    }
                } else {
                    readFileContents = null;
                }
                if (readFileContents != null) {
                    return readFileContents;
                }
            }
            return FileUtil.readFileContentsFromAssets(this.context, str2);
        } catch (Throwable th) {
            return null;
        }
    }

    public void reloadMapResource(int i, String str, int i2) {
    }

    public int generateRequestId() {
        return this.mRequestID.incrementAndGet();
    }

    public int requireMapDataAsyn(int i, byte[] bArr) {
        if (!(this.mRequestDestroy || bArr == null)) {
            ADataRequestParam aDataRequestParam = new ADataRequestParam();
            int i2 = GLConvertUtil.getInt(bArr, 0);
            aDataRequestParam.requestBaseUrl = GLConvertUtil.getString(bArr, 4, i2);
            i2 += 4;
            int i3 = GLConvertUtil.getInt(bArr, i2);
            i2 += 4;
            aDataRequestParam.requestUrl = GLConvertUtil.getString(bArr, i2, i3);
            i2 += i3;
            aDataRequestParam.handler = GLConvertUtil.getLong(bArr, i2);
            i2 += 8;
            aDataRequestParam.nRequestType = GLConvertUtil.getInt(bArr, i2);
            i2 += 4;
            i3 = GLConvertUtil.getInt(bArr, i2);
            i2 += 4;
            aDataRequestParam.enCodeString = GLConvertUtil.getSubBytes(bArr, i2, i3);
            aDataRequestParam.nCompress = GLConvertUtil.getInt(bArr, i2 + i3);
            final AMapLoader aMapLoader = new AMapLoader(i, this, aDataRequestParam);
            this.aMapLoaderHashtable.put(Long.valueOf(aDataRequestParam.handler), aMapLoader);
            aMapLoader.isFinish = false;
            try {
                em.a().a(new Runnable() {
                    public void run() {
                        try {
                            if (GLMapEngine.this.mRequestDestroy) {
                                if (aMapLoader != null && !aMapLoader.isFinish) {
                                    synchronized (aMapLoader) {
                                        if (!aMapLoader.isFinish) {
                                            aMapLoader.notify();
                                            aMapLoader.isFinish = true;
                                        }
                                    }
                                }
                            } else if (aMapLoader != null) {
                                aMapLoader.doRequest();
                                if (aMapLoader != null && !aMapLoader.isFinish) {
                                    synchronized (aMapLoader) {
                                        if (!aMapLoader.isFinish) {
                                            aMapLoader.notify();
                                            aMapLoader.isFinish = true;
                                        }
                                    }
                                }
                            } else if (aMapLoader != null && !aMapLoader.isFinish) {
                                synchronized (aMapLoader) {
                                    if (!aMapLoader.isFinish) {
                                        aMapLoader.notify();
                                        aMapLoader.isFinish = true;
                                    }
                                }
                            }
                        } catch (Throwable th) {
                            if (!(aMapLoader == null || aMapLoader.isFinish)) {
                                synchronized (aMapLoader) {
                                    if (!aMapLoader.isFinish) {
                                        aMapLoader.notify();
                                        aMapLoader.isFinish = true;
                                    }
                                }
                            }
                        }
                    }
                });
                synchronized (aMapLoader) {
                    while (!aMapLoader.isFinish) {
                        aMapLoader.wait();
                    }
                }
            } catch (Throwable th) {
                gz.c(th, "download Thread", "requireMapData");
            }
        }
        return 0;
    }

    public void requireMapData(int i, byte[] bArr) {
    }

    public void cancelRequireMapData(Object obj) {
        if (obj != null && (obj instanceof AMapLoader)) {
            ((AMapLoader) obj).doCancel();
        }
    }

    public byte[] requireCharBitmap(int i, int i2, int i3) {
        return this.mTextTextureGenerator.getTextPixelBuffer(i2, i3);
    }

    public byte[] requireCharsWidths(int i, int[] iArr, int i2, int i3) {
        return this.mTextTextureGenerator.getCharsWidths(iArr);
    }

    public void requireMapRender(int i, int i2, int i3) {
    }

    public void onMapRender(int i, int i2) {
        switch (i2) {
            case 5:
                try {
                    if (this.mMapListener != null) {
                        this.mMapListener.beforeDrawLabel(i, getMapState(i));
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    return;
                }
            case 6:
                if (this.mMapListener != null) {
                    this.mMapListener.afterDrawLabel(i, getMapState(i));
                    return;
                }
                return;
            case 13:
                this.isEngineRenderComplete = true;
                return;
            default:
                return;
        }
    }

    public void OnIndoorBuildingActivity(int i, byte[] bArr) {
        if (this.mGlMapView != null) {
            try {
                this.mGlMapView.onIndoorBuildingActivity(i, bArr);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public synchronized void receiveNetData(int i, long j, byte[] bArr, int i2) {
        if (!this.mRequestDestroy) {
            if (this.mNativeMapengineInstance != 0) {
                nativeReceiveNetData(i, this.mNativeMapengineInstance, bArr, j, i2);
            }
        }
    }

    public boolean getMapDataTaskIsCancel(int i, long j) {
        if (this.aMapLoaderHashtable.containsKey(Long.valueOf(j))) {
            return false;
        }
        return true;
    }

    public synchronized void finishDownLoad(int i, long j) {
        if (this.mNativeMapengineInstance != 0) {
            nativeFinishDownLoad(i, this.mNativeMapengineInstance, j);
        }
        this.aMapLoaderHashtable.remove(Long.valueOf(j));
    }

    public void netError(int i, long j, int i2) {
    }

    public void setMapLoaderToTask(int i, long j, AMapLoader aMapLoader) {
    }

    public Context getContext() {
        return this.context;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setParamater(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mLock.lock();
        try {
            if (this.mNativeMapengineInstance != 0) {
                nativeSetParameter(i, this.mNativeMapengineInstance, i2, i3, i4, i5, i6);
            }
            this.mLock.unlock();
        } catch (Throwable th) {
            this.mLock.unlock();
        }
    }

    public void onClearCache(int i) {
    }

    public boolean isInMapAction(int i) {
        return false;
    }

    public long getNativeInstance() {
        return this.mNativeMapengineInstance;
    }

    public boolean canStopMapRender(int i) {
        return this.isEngineRenderComplete;
    }

    public int getEngineIDWithType(int i) {
        return 1;
    }

    public boolean isEngineCreated(int i) {
        if (this.mNativeMapengineInstance != 0) {
            return nativeIsEngineCreated(this.mNativeMapengineInstance, i);
        }
        return false;
    }

    public long getMapStateInstance(int i) {
        return 0;
    }

    public int getEngineIDWithGestureInfo(EAMapPlatformGestureInfo eAMapPlatformGestureInfo) {
        if (this.mNativeMapengineInstance != 0) {
        }
        return 1;
    }

    public void setServiceViewRect(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        nativeSetServiceViewRect(i, this.mNativeMapengineInstance, i2, i3, i4, i5, i6, i7);
    }

    public void setSrvViewStateBoolValue(int i, int i2, boolean z) {
        if (this.mNativeMapengineInstance != 0) {
            nativeSetSrvViewStateBoolValue(i, this.mNativeMapengineInstance, i2, z);
        }
    }

    public boolean getSrvViewStateBoolValue(int i, int i2) {
        if (this.mNativeMapengineInstance != 0) {
            return nativeGetSrvViewStateBoolValue(i, this.mNativeMapengineInstance, i2);
        }
        return false;
    }

    public boolean getIsProcessBuildingMark(int i) {
        return false;
    }

    public void setIndoorBuildingToBeActive(int i, String str, int i2, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && this.mNativeMapengineInstance != 0) {
            nativeSetIndoorBuildingToBeActive(i, this.mNativeMapengineInstance, str, i2, str2);
        }
    }

    public void setMapListener(IAMapListener iAMapListener) {
        this.mMapListener = iAMapListener;
    }

    public void setInternaltexture(int i, byte[] bArr, int i2) {
    }

    public GLMapState getMapState(int i) {
        this.mLock.lock();
        try {
            if (this.mNativeMapengineInstance != 0 && this.state == null) {
                long nativeGetCurrentMapState = nativeGetCurrentMapState(i, this.mNativeMapengineInstance);
                if (nativeGetCurrentMapState != 0) {
                    this.state = new GLMapState(this.mNativeMapengineInstance, nativeGetCurrentMapState);
                }
            }
            this.mLock.unlock();
            return this.state;
        } catch (Throwable th) {
            this.mLock.unlock();
        }
    }

    public GLMapState getNewMapState(int i) {
        this.mLock.lock();
        try {
            if (this.mNativeMapengineInstance != 0) {
                GLMapState gLMapState = new GLMapState(i, this.mNativeMapengineInstance);
                return gLMapState;
            }
            this.mLock.unlock();
            return null;
        } finally {
            this.mLock.unlock();
        }
    }

    public synchronized GLMapState getCloneMapState() {
        this.mLock.lock();
        try {
            if (this.mNativeMapengineInstance != 0) {
                if (this.copyGLMapState == null) {
                    this.copyGLMapState = new GLMapState(1, this.mNativeMapengineInstance);
                }
                this.copyGLMapState.setMapZoomer(this.mGlMapView.getMapConfig().getSZ());
                this.copyGLMapState.setCameraDegree(this.mGlMapView.getMapConfig().getSC());
                this.copyGLMapState.setMapAngle(this.mGlMapView.getMapConfig().getSR());
                this.copyGLMapState.setMapGeoCenter(this.mGlMapView.getMapConfig().getSX(), this.mGlMapView.getMapConfig().getSY());
            }
            this.mLock.unlock();
        } catch (Throwable th) {
            this.mLock.unlock();
        }
        return this.copyGLMapState;
    }

    public void setMapState(int i, GLMapState gLMapState) {
        setMapState(i, gLMapState, true);
    }

    public void setMapState(int i, GLMapState gLMapState, boolean z) {
        if (this.mNativeMapengineInstance != 0) {
            if (!(!z || this.mGlMapView == null || this.mGlMapView.getMapConfig() == null)) {
                this.mGlMapView.checkMapState(gLMapState);
            }
            this.mLock.lock();
            try {
                gLMapState.setNativeMapengineState(i, this.mNativeMapengineInstance);
            } finally {
                this.mLock.unlock();
            }
        }
    }

    private void initAnimation() {
        if (this.mStateMessageList.size() <= 0 && this.mAnimateStateMessageList.size() > 0) {
            AbstractCameraUpdateMessage abstractCameraUpdateMessage = (AbstractCameraUpdateMessage) this.mAnimateStateMessageList.remove(0);
            if (abstractCameraUpdateMessage != null) {
                abstractCameraUpdateMessage.generateMapAnimation(this);
            }
        }
    }

    public synchronized void addGestureMessage(int i, AbstractGestureMapMessage abstractGestureMapMessage, boolean z, int i2, int i3) {
        if (abstractGestureMapMessage != null) {
            abstractGestureMapMessage.isGestureScaleByMapCenter = z;
            this.mGestureMessageList.add(abstractGestureMapMessage);
        }
    }

    private boolean processMessage() {
        try {
            GLMapState newMapState = getNewMapState(1);
            boolean processGestureMessage = processGestureMessage(newMapState);
            if (this.mGestureMessageList.size() <= 0) {
                processGestureMessage = processGestureMessage || processStateMapMessage(newMapState);
            } else if (this.mStateMessageList.size() > 0) {
                this.mStateMessageList.clear();
            }
            processGestureMessage = processGestureMessage || processAnimations(newMapState);
            if (processGestureMessage) {
                setMapState(1, newMapState);
            }
            newMapState.recycle();
            return processGestureMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean processGestureMessage(GLMapState gLMapState) {
        if (this.mGestureMessageList.size() <= 0) {
            if (this.isGestureStep) {
                this.isGestureStep = false;
            }
            return false;
        }
        this.isGestureStep = true;
        if (gLMapState == null) {
            return false;
        }
        while (this.mGestureMessageList.size() > 0) {
            AbstractGestureMapMessage abstractGestureMapMessage = (AbstractGestureMapMessage) this.mGestureMessageList.remove(0);
            if (abstractGestureMapMessage == null) {
                break;
            }
            if (abstractGestureMapMessage.width == 0) {
                abstractGestureMapMessage.width = this.mGlMapView.getMapWidth();
            }
            if (abstractGestureMapMessage.height == 0) {
                abstractGestureMapMessage.height = this.mGlMapView.getMapHeight();
            }
            int mapGestureState = abstractGestureMapMessage.getMapGestureState();
            if (mapGestureState == 100) {
                gestureBegin();
            } else if (mapGestureState == 101) {
                abstractGestureMapMessage.runCameraUpdate(gLMapState);
            } else if (mapGestureState == 102) {
                gestureEnd();
            }
            this.mGestureEndMessageList.add(abstractGestureMapMessage);
        }
        if (this.mGestureEndMessageList.size() == 1) {
            recycleMessage();
        }
        return true;
    }

    private boolean processAnimations(GLMapState gLMapState) {
        try {
            if (this.mapAnimationMgr.getAnimationsCount() > 0) {
                gLMapState.recalculate();
                this.mapAnimationMgr.doAnimations(gLMapState);
                return true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    public void interruptAnimation() {
        if (isInMapAnimation(1)) {
            try {
                doMapAnimationCancelCallback(this.mapAnimationMgr.getCancelCallback());
                clearAnimations(1, false);
            } catch (Throwable th) {
                gz.c(th, getClass().getName(), "CancelableCallback.onCancel");
                th.printStackTrace();
            }
        }
    }

    public void addGroupAnimation(int i, int i2, float f, int i3, int i4, int i5, int i6, CancelableCallback cancelableCallback) {
        AbstractAdglAnimation adglMapAnimGroup = new AdglMapAnimGroup(i2);
        adglMapAnimGroup.setToCameraDegree((float) i4, 0);
        adglMapAnimGroup.setToMapAngle((float) i3, 0);
        adglMapAnimGroup.setToMapLevel(f, 0);
        adglMapAnimGroup.setToMapCenterGeo(i5, i6, 0);
        if (this.mapAnimationMgr != null && adglMapAnimGroup != null && adglMapAnimGroup.isValid()) {
            this.mapAnimationMgr.addAnimation(adglMapAnimGroup, cancelableCallback);
        }
    }

    private void doMapAnimationCancelCallback(final CancelableCallback cancelableCallback) {
        if (cancelableCallback != null && this.mGlMapView != null) {
            this.mGlMapView.getMainHandler().post(new Runnable() {
                public void run() {
                    try {
                        cancelableCallback.onCancel();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    private void doMapAnimationFinishCallback(final CancelableCallback cancelableCallback) {
        if (this.mMapListener != null) {
            this.mMapListener.afterAnimation();
        }
        if (cancelableCallback != null && this.mGlMapView != null) {
            this.mGlMapView.getMainHandler().post(new Runnable() {
                public void run() {
                    try {
                        cancelableCallback.onFinish();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    public boolean isInMapAnimation(int i) {
        if (getAnimateionsCount() > 0) {
            return true;
        }
        return false;
    }

    public int getAnimateionsCount() {
        if (this.mNativeMapengineInstance != 0) {
            return this.mapAnimationMgr.getAnimationsCount();
        }
        return 0;
    }

    public void clearAllMessages(int i) {
    }

    public void clearAnimations(int i, boolean z) {
        this.mapAnimationMgr.clearAnimations();
    }

    public void clearAnimations(int i, boolean z, int i2) {
        this.mapAnimationMgr.clearAnimations();
    }

    public void startMapSlidAnim(int i, Point point, float f, float f2) {
        if (point != null) {
            try {
                clearAnimations(i, true);
                GLMapState cloneMapState = getCloneMapState();
                cloneMapState.reset();
                cloneMapState.recalculate();
                float abs = Math.abs(f);
                float abs2 = Math.abs(f2);
                if ((abs > abs2 ? abs : abs2) > ((float) 12000)) {
                    if (abs > abs2) {
                        f2 *= ((float) 12000) / abs;
                        f = f > 0.0f ? (float) 12000 : (float) (-12000);
                    } else {
                        f *= ((float) 12000) / abs2;
                        f2 = f2 > 0.0f ? (float) 12000 : (float) (-12000);
                    }
                }
                int mapWidth = this.mGlMapView.getMapWidth() >> 1;
                int mapHeight = this.mGlMapView.getMapHeight() >> 1;
                if (this.mGlMapView.k()) {
                    mapWidth = this.mGlMapView.getMapConfig().getAnchorX();
                    mapHeight = this.mGlMapView.getMapConfig().getAnchorY();
                }
                AbstractAdglAnimation adglMapAnimFling = new AdglMapAnimFling(500, mapWidth, mapHeight);
                adglMapAnimFling.setPositionAndVelocity(f, f2);
                adglMapAnimFling.commitAnimation(cloneMapState);
                this.mapAnimationMgr.addAnimation(adglMapAnimFling, null);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void startPivotZoomRotateAnim(int i, Point point, float f, int i2, int i3) {
        if ((f != -9999.0f || i2 != AbstractAdglAnimation.INVALIDE_VALUE) && i2 != AbstractAdglAnimation.INVALIDE_VALUE && i2 < 0) {
            int i4 = i2 + SpatialRelationUtil.A_CIRCLE_DEGREE;
        }
    }

    private void gestureBegin() {
        this.mapGestureCount++;
    }

    private void gestureEnd() {
        this.mapGestureCount--;
        if (this.mapGestureCount == 0) {
            recycleMessage();
        }
    }

    public int getStateMessageCount() {
        return this.mStateMessageList.size();
    }

    public void addMessage(AbstractCameraUpdateMessage abstractCameraUpdateMessage, boolean z) {
        if (z) {
            if (this.mAnimateStateMessageList != null) {
                this.mAnimateStateMessageList.clear();
                this.mAnimateStateMessageList.add(abstractCameraUpdateMessage);
            }
        } else if (this.mStateMessageList != null) {
            this.mStateMessageList.add(abstractCameraUpdateMessage);
        }
    }

    public synchronized AbstractCameraUpdateMessage getStateMessage() {
        AbstractCameraUpdateMessage abstractCameraUpdateMessage;
        if (this.mStateMessageList == null || this.mStateMessageList.size() == 0) {
            abstractCameraUpdateMessage = null;
        } else {
            abstractCameraUpdateMessage = (AbstractCameraUpdateMessage) this.mStateMessageList.get(0);
            this.mStateMessageList.remove(abstractCameraUpdateMessage);
        }
        return abstractCameraUpdateMessage;
    }

    private void recycleMessage() {
        while (this.mGestureEndMessageList.size() > 0) {
            AbstractGestureMapMessage abstractGestureMapMessage = (AbstractGestureMapMessage) this.mGestureEndMessageList.remove(0);
            if (abstractGestureMapMessage == null) {
                return;
            }
            if (abstractGestureMapMessage instanceof MoveGestureMapMessage) {
                ((MoveGestureMapMessage) abstractGestureMapMessage).recycle();
            } else if (abstractGestureMapMessage instanceof HoverGestureMapMessage) {
                ((HoverGestureMapMessage) abstractGestureMapMessage).recycle();
            } else if (abstractGestureMapMessage instanceof RotateGestureMapMessage) {
                ((RotateGestureMapMessage) abstractGestureMapMessage).recycle();
            } else if (abstractGestureMapMessage instanceof ScaleGestureMapMessage) {
                ((ScaleGestureMapMessage) abstractGestureMapMessage).recycle();
            }
        }
    }

    private boolean processStateMapMessage(GLMapState gLMapState) {
        if (this.mStateMessageList.size() <= 0) {
            if (this.isMoveCameraStep) {
                this.isMoveCameraStep = false;
            }
            return false;
        }
        this.isMoveCameraStep = true;
        if (gLMapState == null) {
            return false;
        }
        while (this.mStateMessageList.size() > 0) {
            AbstractCameraUpdateMessage abstractCameraUpdateMessage = (AbstractCameraUpdateMessage) this.mStateMessageList.remove(0);
            if (abstractCameraUpdateMessage == null) {
                break;
            }
            if (abstractCameraUpdateMessage.width == 0) {
                abstractCameraUpdateMessage.width = this.mGlMapView.getMapWidth();
            }
            if (abstractCameraUpdateMessage.height == 0) {
                abstractCameraUpdateMessage.height = this.mGlMapView.getMapHeight();
            }
            gLMapState.recalculate();
            abstractCameraUpdateMessage.runCameraUpdate(gLMapState);
        }
        return true;
    }

    public void pushRendererState() {
        if (this.mNativeMapengineInstance != 0) {
            nativePushRendererState(1, this.mNativeMapengineInstance);
        }
    }

    public void popRendererState() {
        if (this.mNativeMapengineInstance != 0) {
            nativePopRenderState(1, this.mNativeMapengineInstance);
        }
    }

    public int[] getMapModeState(int i, boolean z) {
        if (this.mNativeMapengineInstance != 0) {
            nativeGetMapModeState(i, this.mNativeMapengineInstance, z);
        }
        return null;
    }

    public boolean setMapModeAndStyle(int i, int i2, int i3, int i4, boolean z, boolean z2, StyleItem[] styleItemArr) {
        if (this.mNativeMapengineInstance == 0) {
            return false;
        }
        int i5 = i;
        boolean nativeSetMapModeAndStyle = nativeSetMapModeAndStyle(i5, this.mNativeMapengineInstance, new int[]{i2, i3, i4, 0, 0}, z, z2, styleItemArr);
        if (styleItemArr != null && z2) {
            i5 = this.mGlMapView.getMapConfig().getCustomBackgroundColor();
            if (i5 != 0) {
                setBackgroundTexture(i, en.a(FileUtil.readFileContentsFromAssets(this.context, AMapEngineUtils.MAP_MAP_ASSETS_NAME + File.separator + AMapEngineUtils.MAP_MAP_ASSETS_BACKGROUND_NAME), i5));
            }
            Object customTextureResourcePath = this.mGlMapView.getMapConfig().getCustomTextureResourcePath();
            if (!this.mGlMapView.getMapConfig().isProFunctionAuthEnable() || TextUtils.isEmpty(customTextureResourcePath)) {
                return nativeSetMapModeAndStyle;
            }
            this.mGlMapView.getMapConfig().setUseProFunction(true);
            setCustomStyleTexture(i, FileUtil.readFileContents(customTextureResourcePath));
            return nativeSetMapModeAndStyle;
        } else if (i2 != 0 || i3 != 0 || i4 != 0) {
            return nativeSetMapModeAndStyle;
        } else {
            setBackgroundTexture(i, FileUtil.readFileContentsFromAssets(this.context, AMapEngineUtils.MAP_MAP_ASSETS_NAME + File.separator + AMapEngineUtils.MAP_MAP_ASSETS_BACKGROUND_NAME));
            setCustomStyleTexture(i, FileUtil.readFileContentsFromAssets(this.context, AMapEngineUtils.MAP_MAP_ASSETS_NAME + File.separator + AMapEngineUtils.MAP_MAP_ASSETS_ICON_5_NAME));
            return nativeSetMapModeAndStyle;
        }
    }

    public void putResourceData(int i, byte[] bArr) {
    }

    public void networkStateChanged(Context context) {
        if (!this.mRequestDestroy && this.mNativeMapengineInstance != 0) {
            final boolean isNetworkConnected = NetworkState.isNetworkConnected(context);
            this.mGlMapView.queueEvent(new Runnable() {
                public void run() {
                    GLMapEngine.nativeSetNetStatus(GLMapEngine.this.mNativeMapengineInstance, isNetworkConnected ? 1 : 0);
                }
            });
        }
    }

    public byte[] getLabelBuffer(int i, int i2, int i3, int i4) {
        this.mLock.lock();
        try {
            byte[] bArr = new byte[3072];
            if (this.mNativeMapengineInstance != 0) {
                nativeSelectMapPois(i, this.mNativeMapengineInstance, i2, i3, i4, bArr);
            }
            this.mLock.unlock();
            return bArr;
        } catch (Throwable th) {
            this.mLock.unlock();
        }
    }

    public long createOverlay(int i, int i2) {
        if (this.mNativeMapengineInstance != 0) {
            return nativeCreateOverlay(i, this.mNativeMapengineInstance, i2);
        }
        return 0;
    }

    public long getGlOverlayMgrPtr(int i) {
        if (this.mNativeMapengineInstance != 0) {
            return nativeGetGlOverlayMgrPtr(i, this.mNativeMapengineInstance);
        }
        return 0;
    }

    public void setOvelayBundle(int i, GLOverlayBundle<BaseMapOverlay<?, ?>> gLOverlayBundle) {
        this.bundle = gLOverlayBundle;
    }

    public void addOverlayTexture(int i, GLTextureProperty gLTextureProperty) {
        if (this.mNativeMapengineInstance != 0 && gLTextureProperty != null && gLTextureProperty.mBitmap != null && !gLTextureProperty.mBitmap.isRecycled()) {
            nativeAddOverlayTexture(i, this.mNativeMapengineInstance, gLTextureProperty.mId, gLTextureProperty.mAnchor, gLTextureProperty.mXRatio, gLTextureProperty.mYRatio, gLTextureProperty.mBitmap, gLTextureProperty.isGenMimps, gLTextureProperty.isRepeat);
        }
    }

    public GLOverlayBundle getOverlayBundle(int i) {
        return this.bundle;
    }

    public static void destroyOverlay(int i, long j) {
        synchronized (GLMapEngine.class) {
            nativeDestroyOverlay(i, j);
        }
    }

    public void setSimple3DEnable(int i, boolean z) {
        if (this.mNativeMapengineInstance != 0) {
            nativeSetSimple3DEnable(i, this.mNativeMapengineInstance, z);
        }
    }

    public void setSkyTexture(int i, byte[] bArr) {
        if (bArr != null && this.mNativeMapengineInstance != 0) {
            nativeSetSkyTexture(i, this.mNativeMapengineInstance, bArr);
        }
    }

    public void setBackgroundTexture(int i, byte[] bArr) {
        if (bArr != null && this.mNativeMapengineInstance != 0) {
            nativeSetSetBackgroundTexture(i, this.mNativeMapengineInstance, bArr);
        }
    }

    public void setCustomStyleTexture(int i, byte[] bArr) {
        if (bArr != null && this.mNativeMapengineInstance != 0) {
            nativeSetCustomStyleTexture(i, this.mNativeMapengineInstance, bArr);
        }
    }

    public void setTrafficEnable(int i, boolean z) {
        if (this.mNativeMapengineInstance != 0) {
            nativeSetTrafficEnable(i, this.mNativeMapengineInstance, z);
        }
    }

    public void setBuildingEnable(int i, boolean z) {
        if (this.mNativeMapengineInstance != 0) {
            nativeSetBuildingEnable(i, this.mNativeMapengineInstance, z);
        }
    }

    public void setLabelEnable(int i, boolean z) {
        if (this.mNativeMapengineInstance != 0) {
            nativeSetLabelEnable(i, this.mNativeMapengineInstance, z);
        }
    }

    public void setAllContentEnable(int i, boolean z) {
        if (this.mNativeMapengineInstance != 0) {
            nativeSetAllContentEnable(i, this.mNativeMapengineInstance, z);
        }
    }

    public void setProjectionCenter(int i, int i2, int i3) {
        if (this.mNativeMapengineInstance != 0) {
            nativeSetProjectionCenter(i, this.mNativeMapengineInstance, (float) i2, (float) i3);
        }
    }

    public void setTrafficStyle(int i, int i2, int i3, int i4, int i5) {
        if (this.mNativeMapengineInstance != 0) {
            nativeSetTrafficTextureAllInOne(i, this.mNativeMapengineInstance, en.a(FileUtil.readFileContentsFromAssets(this.context, AMapEngineUtils.MAP_MAP_ASSETS_NAME + File.separator + AMapEngineUtils.MAP_MAP_ASSETS_TRL_NAME), new int[]{i5, i4, i3, i2}));
        }
    }

    public void startCheckEngineRenderComplete() {
        this.isEngineRenderComplete = false;
    }

    public void getCurTileIDs(int i, int[] iArr) {
        if (iArr != null) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr[i2] = 0;
            }
            nativeGetCurTileIDs(i, this.mNativeMapengineInstance, iArr, iArr.length);
        }
    }

    public void setIndoorEnable(int i, boolean z) {
        if (this.mNativeMapengineInstance != 0) {
            nativeSetIndoorEnable(i, this.mNativeMapengineInstance, z);
        }
    }

    public void setOfflineDataEnable(int i, boolean z) {
        if (this.mNativeMapengineInstance != 0) {
            nativeSetOfflineDataEnable(i, this.mNativeMapengineInstance, z);
        }
    }

    public void setHighlightSubwayEnable(int i, boolean z) {
        if (this.mNativeMapengineInstance != 0) {
            nativeSetHighlightSubwayEnable(i, this.mNativeMapengineInstance, z);
        }
    }

    public void setBuildingTextureEnable(int i, boolean z) {
        if (this.mNativeMapengineInstance != 0) {
            nativeSetBuildingTextureEnable(i, this.mNativeMapengineInstance, z);
        }
    }

    public GLMapEngine(Context context, lj ljVar) {
        this.mNativeMapengineInstance = 0;
        this.mGlMapView = null;
        this.mStateMessageList = new Vector();
        this.mGestureMessageList = new Vector();
        this.mGestureEndMessageList = new Vector();
        this.mAnimateStateMessageList = new Vector();
        this.isMoveCameraStep = false;
        this.isGestureStep = false;
        this.mapGestureCount = 0;
        this.mapAnimationMgr = null;
        this.copyGLMapState = null;
        this.mLock = new ReentrantLock();
        this.mutLock = new Object();
        this.mNetworkState = null;
        this.bundle = null;
        this.isEngineRenderComplete = false;
        this.aMapLoaderHashtable = new Hashtable();
        this.mRequestDestroy = false;
        this.mRequestID = new AtomicInteger(1);
        this.mRequestDestroy = false;
        if (context != null) {
            this.context = context.getApplicationContext();
            this.mGlMapView = ljVar;
            this.mTextTextureGenerator = new TextTextureGenerator();
            this.mapAnimationMgr = new AdglMapAnimationMgr();
            this.mapAnimationMgr.setMapAnimationListener(new MapAnimationListener() {
                public void onMapAnimationFinish(CancelableCallback cancelableCallback) {
                    GLMapEngine.this.doMapAnimationFinishCallback(cancelableCallback);
                }
            });
            this.userAgent = System.getProperty("http.agent") + " amap/" + GlMapUtil.getAppVersionName(context);
        }
    }

    public void createAMapInstance(InitParam initParam) {
        if (initParam != null) {
            synchronized (GLMapEngine.class) {
                nativeInitParam(initParam.mRootPath, initParam.mConfigPath, initParam.mOfflineDataPath, initParam.mP3dCrossPath);
                this.mNativeMapengineInstance = nativeCreateAMapInstance("", "http://mpsapi.amap.com/", "http://m5.amap.com/");
                nativeInitAMapEngineCallback(this.mNativeMapengineInstance, this);
                initNetworkState();
            }
        }
    }

    private void initNetworkState() {
        int i = 1;
        this.mNetworkState = new NetworkState();
        this.mNetworkState.setNetworkListener(this);
        this.mNetworkState.registerNetChangeReceiver(this.context.getApplicationContext(), true);
        boolean isNetworkConnected = NetworkState.isNetworkConnected(this.context.getApplicationContext());
        if (this.mNativeMapengineInstance != 0) {
            long j = this.mNativeMapengineInstance;
            if (!isNetworkConnected) {
                i = 0;
            }
            nativeSetNetStatus(j, i);
        }
    }

    public void createAMapEngineWithFrame(MapViewInitParam mapViewInitParam) {
        if (this.mNativeMapengineInstance != 0) {
            synchronized (GLMapEngine.class) {
                nativeCreateAMapEngineWithFrame(this.mNativeMapengineInstance, mapViewInitParam.engineId, mapViewInitParam.x, mapViewInitParam.y, mapViewInitParam.width, mapViewInitParam.height, mapViewInitParam.screenWidth, mapViewInitParam.screenHeight, mapViewInitParam.screenScale, mapViewInitParam.textScale, mapViewInitParam.mapZoomScale);
            }
        }
    }

    public void changeSurface(int i, int i2) {
    }

    public void renderAMap() {
        if (this.mNativeMapengineInstance != 0) {
            boolean processMessage = processMessage();
            synchronized (GLMapEngine.class) {
                nativeRenderAMap(this.mNativeMapengineInstance, 1);
                nativePostRenderAMap(this.mNativeMapengineInstance, 1);
            }
            initAnimation();
            if (processMessage) {
                startCheckEngineRenderComplete();
            }
            if (!this.isEngineRenderComplete) {
                nativeSetRenderListenerStatus(1, this.mNativeMapengineInstance);
            }
        }
    }

    public void releaseNetworkState() {
        if (this.mNetworkState != null) {
            this.mNetworkState.registerNetChangeReceiver(this.context.getApplicationContext(), false);
            this.mNetworkState.setNetworkListener(null);
            this.mNetworkState = null;
        }
    }

    public void cancelAllAMapDownload() {
        try {
            synchronized (this.aMapLoaderHashtable) {
                for (Entry value : this.aMapLoaderHashtable.entrySet()) {
                    AMapLoader aMapLoader = (AMapLoader) value.getValue();
                    aMapLoader.doCancel();
                    if (!aMapLoader.isFinish) {
                        synchronized (aMapLoader) {
                            if (!aMapLoader.isFinish) {
                                aMapLoader.notify();
                                aMapLoader.isFinish = true;
                            }
                        }
                    }
                }
                this.aMapLoaderHashtable.clear();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void destroyAMapEngine() {
        try {
            this.mRequestDestroy = true;
            cancelAllAMapDownload();
            synchronized (this.mutLock) {
                if (this.mNativeMapengineInstance != 0) {
                    synchronized (this) {
                        if (this.copyGLMapState != null) {
                            this.copyGLMapState.recycle();
                        }
                    }
                    nativeDestroyCurrentState(this.mNativeMapengineInstance, this.state.getNativeInstance());
                    nativeDestroy(this.mNativeMapengineInstance);
                }
                this.mNativeMapengineInstance = 0;
            }
            this.mGlMapView = null;
            this.mStateMessageList.clear();
            this.mAnimateStateMessageList.clear();
            this.mGestureEndMessageList.clear();
            this.mGestureMessageList.clear();
            this.mMapListener = null;
            em.b();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
