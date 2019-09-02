package com.amap.api.maps.utils.overlay;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.BasePointOverlay;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.github.mikephil.charting.utils.Utils;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class MovingPointOverlay {
    private a STATUS = a.ACTION_UNKNOWN;
    private BasePointOverlay baseOverlay = null;
    private long duration = 10000;
    private LinkedList<Double> eachDistance = new LinkedList();
    AtomicBoolean exitFlag = new AtomicBoolean(false);
    private int index = 0;
    private AMap mAMap;
    private long mAnimationBeginTime = System.currentTimeMillis();
    private Object mLock = new Object();
    private long mStepDuration = 20;
    private ExecutorService mThreadPools;
    private MoveListener moveListener;
    private long pauseMillis;
    private LinkedList<LatLng> points = new LinkedList();
    private double remainDistance = Utils.DOUBLE_EPSILON;
    private double totalDistance = Utils.DOUBLE_EPSILON;
    private boolean useDefaultDescriptor = false;

    public interface MoveListener {
        void move(double d);
    }

    private enum a {
        ACTION_UNKNOWN,
        ACTION_START,
        ACTION_RUNNING,
        ACTION_PAUSE,
        ACTION_STOP
    }

    private class b implements ThreadFactory {
        final /* synthetic */ MovingPointOverlay a;

        private b(MovingPointOverlay movingPointOverlay) {
            this.a = movingPointOverlay;
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "MoveSmoothThread");
        }
    }

    private class c implements Runnable {
        final /* synthetic */ MovingPointOverlay a;

        private c(MovingPointOverlay movingPointOverlay) {
            this.a = movingPointOverlay;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r6 = this;
            r0 = r6.a;	 Catch:{ Throwable -> 0x007f }
            r2 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x007f }
            r0.mAnimationBeginTime = r2;	 Catch:{ Throwable -> 0x007f }
            r0 = r6.a;	 Catch:{ Throwable -> 0x007f }
            r1 = com.amap.api.maps.utils.overlay.MovingPointOverlay.a.ACTION_START;	 Catch:{ Throwable -> 0x007f }
            r0.STATUS = r1;	 Catch:{ Throwable -> 0x007f }
            r0 = r6.a;	 Catch:{ Throwable -> 0x007f }
            r0 = r0.exitFlag;	 Catch:{ Throwable -> 0x007f }
            r1 = 0;
            r0.set(r1);	 Catch:{ Throwable -> 0x007f }
        L_0x0018:
            r0 = r6.a;	 Catch:{ Throwable -> 0x007f }
            r0 = r0.exitFlag;	 Catch:{ Throwable -> 0x007f }
            r0 = r0.get();	 Catch:{ Throwable -> 0x007f }
            if (r0 != 0) goto L_0x0087;
        L_0x0022:
            r0 = r6.a;	 Catch:{ Throwable -> 0x007f }
            r0 = r0.index;	 Catch:{ Throwable -> 0x007f }
            r1 = r6.a;	 Catch:{ Throwable -> 0x007f }
            r1 = r1.points;	 Catch:{ Throwable -> 0x007f }
            r1 = r1.size();	 Catch:{ Throwable -> 0x007f }
            r1 = r1 + -1;
            if (r0 > r1) goto L_0x0087;
        L_0x0036:
            r0 = r6.a;	 Catch:{ Throwable -> 0x007f }
            r1 = r0.mLock;	 Catch:{ Throwable -> 0x007f }
            monitor-enter(r1);	 Catch:{ Throwable -> 0x007f }
            r0 = r6.a;	 Catch:{ all -> 0x0084 }
            r0 = r0.exitFlag;	 Catch:{ all -> 0x0084 }
            r0 = r0.get();	 Catch:{ all -> 0x0084 }
            if (r0 == 0) goto L_0x0049;
        L_0x0047:
            monitor-exit(r1);	 Catch:{ all -> 0x0084 }
        L_0x0048:
            return;
        L_0x0049:
            r0 = r6.a;	 Catch:{ all -> 0x0084 }
            r0 = r0.STATUS;	 Catch:{ all -> 0x0084 }
            r2 = com.amap.api.maps.utils.overlay.MovingPointOverlay.a.ACTION_PAUSE;	 Catch:{ all -> 0x0084 }
            if (r0 == r2) goto L_0x0074;
        L_0x0053:
            r2 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0084 }
            r0 = r6.a;	 Catch:{ all -> 0x0084 }
            r4 = r0.mAnimationBeginTime;	 Catch:{ all -> 0x0084 }
            r2 = r2 - r4;
            r0 = r6.a;	 Catch:{ all -> 0x0084 }
            r0 = r0.getCurPosition(r2);	 Catch:{ all -> 0x0084 }
            r2 = r6.a;	 Catch:{ all -> 0x0084 }
            r2 = r2.baseOverlay;	 Catch:{ all -> 0x0084 }
            r2.setGeoPoint(r0);	 Catch:{ all -> 0x0084 }
            r0 = r6.a;	 Catch:{ all -> 0x0084 }
            r2 = com.amap.api.maps.utils.overlay.MovingPointOverlay.a.ACTION_RUNNING;	 Catch:{ all -> 0x0084 }
            r0.STATUS = r2;	 Catch:{ all -> 0x0084 }
        L_0x0074:
            monitor-exit(r1);	 Catch:{ all -> 0x0084 }
            r0 = r6.a;	 Catch:{ Throwable -> 0x007f }
            r0 = r0.mStepDuration;	 Catch:{ Throwable -> 0x007f }
            java.lang.Thread.sleep(r0);	 Catch:{ Throwable -> 0x007f }
            goto L_0x0018;
        L_0x007f:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x0048;
        L_0x0084:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0084 }
            throw r0;	 Catch:{ Throwable -> 0x007f }
        L_0x0087:
            r0 = r6.a;	 Catch:{ Throwable -> 0x007f }
            r1 = com.amap.api.maps.utils.overlay.MovingPointOverlay.a.ACTION_STOP;	 Catch:{ Throwable -> 0x007f }
            r0.STATUS = r1;	 Catch:{ Throwable -> 0x007f }
            goto L_0x0048;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.maps.utils.overlay.MovingPointOverlay.c.run():void");
        }
    }

    public MovingPointOverlay(AMap aMap, BasePointOverlay basePointOverlay) {
        if (aMap != null && basePointOverlay != null) {
            this.mAMap = aMap;
            this.mThreadPools = new ThreadPoolExecutor(1, 2, 5, TimeUnit.SECONDS, new SynchronousQueue(), new b());
            this.baseOverlay = basePointOverlay;
        }
    }

    public void setPoints(List<LatLng> list) {
        synchronized (this.mLock) {
            if (list != null) {
                try {
                    if (list.size() >= 2) {
                        stopMove();
                        this.points.clear();
                        for (LatLng latLng : list) {
                            if (latLng != null) {
                                this.points.add(latLng);
                            }
                        }
                        this.eachDistance.clear();
                        this.totalDistance = Utils.DOUBLE_EPSILON;
                        for (int i = 0; i < this.points.size() - 1; i++) {
                            double calculateLineDistance = (double) AMapUtils.calculateLineDistance((LatLng) this.points.get(i), (LatLng) this.points.get(i + 1));
                            this.eachDistance.add(Double.valueOf(calculateLineDistance));
                            this.totalDistance = calculateLineDistance + this.totalDistance;
                        }
                        this.remainDistance = this.totalDistance;
                        this.baseOverlay.setPosition((LatLng) this.points.get(0));
                        reset();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            return;
        }
    }

    private void reset() {
        try {
            if (this.STATUS == a.ACTION_RUNNING || this.STATUS == a.ACTION_PAUSE) {
                this.exitFlag.set(true);
                this.mThreadPools.awaitTermination(this.mStepDuration + 20, TimeUnit.MILLISECONDS);
                this.baseOverlay.setAnimation(null);
                this.STATUS = a.ACTION_UNKNOWN;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resetIndex() {
        this.index = 0;
    }

    public void setTotalDuration(int i) {
        this.duration = (long) (i * 1000);
    }

    public void startSmoothMove() {
        if (this.STATUS == a.ACTION_PAUSE) {
            this.STATUS = a.ACTION_RUNNING;
            this.mAnimationBeginTime = (System.currentTimeMillis() - this.pauseMillis) + this.mAnimationBeginTime;
        } else if ((this.STATUS == a.ACTION_UNKNOWN || this.STATUS == a.ACTION_STOP) && this.points.size() >= 1) {
            this.index = 0;
            try {
                this.mThreadPools.execute(new c());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private IPoint getCurPosition(long j) {
        if (j > this.duration) {
            this.exitFlag.set(true);
            IPoint iPoint = new IPoint();
            this.index = this.points.size() - 1;
            LatLng latLng = (LatLng) this.points.get(this.index);
            this.index--;
            this.index = Math.max(this.index, 0);
            this.remainDistance = Utils.DOUBLE_EPSILON;
            MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint);
            if (this.moveListener != null) {
                this.moveListener.move(this.remainDistance);
            }
            return iPoint;
        }
        double d;
        LatLng latLng2;
        IPoint iPoint2;
        IPoint iPoint3;
        int i;
        int i2;
        float rotate;
        double d2 = (((double) j) * this.totalDistance) / ((double) this.duration);
        this.remainDistance = this.totalDistance - d2;
        double d3 = d2;
        int i3 = 0;
        while (i3 < this.eachDistance.size()) {
            CameraPosition cameraPosition;
            double doubleValue = ((Double) this.eachDistance.get(i3)).doubleValue();
            if (d3 > doubleValue) {
                d3 -= doubleValue;
                i3++;
            } else {
                d = doubleValue > Utils.DOUBLE_EPSILON ? d3 / doubleValue : 1.0d;
                if (!(i3 == this.index || this.moveListener == null)) {
                    this.moveListener.move(this.remainDistance);
                }
                this.index = i3;
                latLng = (LatLng) this.points.get(i3);
                latLng2 = (LatLng) this.points.get(i3 + 1);
                iPoint2 = new IPoint();
                MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint2);
                iPoint3 = new IPoint();
                MapProjection.lonlat2Geo(latLng2.longitude, latLng2.latitude, iPoint3);
                i = iPoint3.x - iPoint2.x;
                i2 = iPoint3.y - iPoint2.y;
                if (AMapUtils.calculateLineDistance(latLng, latLng2) > 1.0f) {
                    rotate = getRotate(iPoint2, iPoint3);
                    if (this.mAMap != null) {
                        cameraPosition = this.mAMap.getCameraPosition();
                        if (cameraPosition != null) {
                            this.baseOverlay.setRotateAngle((360.0f - rotate) + cameraPosition.bearing);
                        }
                    }
                }
                return new IPoint((int) (((double) iPoint2.x) + (((double) i) * d)), (int) ((d * ((double) i2)) + ((double) iPoint2.y)));
            }
        }
        i3 = 0;
        d = 1.0d;
        this.moveListener.move(this.remainDistance);
        this.index = i3;
        latLng = (LatLng) this.points.get(i3);
        latLng2 = (LatLng) this.points.get(i3 + 1);
        iPoint2 = new IPoint();
        MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint2);
        iPoint3 = new IPoint();
        MapProjection.lonlat2Geo(latLng2.longitude, latLng2.latitude, iPoint3);
        i = iPoint3.x - iPoint2.x;
        i2 = iPoint3.y - iPoint2.y;
        if (AMapUtils.calculateLineDistance(latLng, latLng2) > 1.0f) {
            rotate = getRotate(iPoint2, iPoint3);
            if (this.mAMap != null) {
                cameraPosition = this.mAMap.getCameraPosition();
                if (cameraPosition != null) {
                    this.baseOverlay.setRotateAngle((360.0f - rotate) + cameraPosition.bearing);
                }
            }
        }
        return new IPoint((int) (((double) iPoint2.x) + (((double) i) * d)), (int) ((d * ((double) i2)) + ((double) iPoint2.y)));
    }

    private float getRotate(IPoint iPoint, IPoint iPoint2) {
        if (iPoint == null || iPoint2 == null) {
            return 0.0f;
        }
        return (float) ((Math.atan2(((double) iPoint2.x) - ((double) iPoint.x), ((double) iPoint.y) - ((double) iPoint2.y)) / 3.141592653589793d) * 180.0d);
    }

    public void stopMove() {
        if (this.STATUS == a.ACTION_RUNNING) {
            this.STATUS = a.ACTION_PAUSE;
            this.pauseMillis = System.currentTimeMillis();
        }
    }

    public BasePointOverlay getObject() {
        return this.baseOverlay;
    }

    public LatLng getPosition() {
        if (this.baseOverlay != null) {
            return this.baseOverlay.getPosition();
        }
        return null;
    }

    public int getIndex() {
        return this.index;
    }

    public void destroy() {
        try {
            reset();
            this.mThreadPools.shutdownNow();
            synchronized (this.mLock) {
                this.points.clear();
                this.eachDistance.clear();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void removeMarker() {
        try {
            if (this.baseOverlay != null) {
                this.baseOverlay.remove();
                this.baseOverlay = null;
            }
            this.points.clear();
            this.eachDistance.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPosition(LatLng latLng) {
        try {
            if (this.baseOverlay != null) {
                this.baseOverlay.setPosition(latLng);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setRotate(float f) {
        try {
            if (this.baseOverlay != null && this.mAMap != null) {
                CameraPosition cameraPosition = this.mAMap.getCameraPosition();
                if (cameraPosition != null) {
                    this.baseOverlay.setRotateAngle(cameraPosition.bearing + (360.0f - f));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVisible(boolean z) {
        try {
            if (this.baseOverlay != null) {
                this.baseOverlay.setVisible(z);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMoveListener(MoveListener moveListener) {
        this.moveListener = moveListener;
    }
}
