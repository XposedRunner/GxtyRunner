package com.example.gita.gxty_runner.hook.map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.route.WalkStep;
import com.example.gita.gxty_runner.GxtyRunner;
import com.example.gita.gxty_runner.hook.bluetooth.Beacon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


public class RouteProcessor {
    private AllRouteSearchedCallback mAllRouteSearchedCallback;
    private List<RouteSearch.WalkRouteQuery> walkRouteQueryList = new ArrayList<>();
    private RouteSearch routeSearch;
    private List<WalkRouteResult> walkRouteResultList = new ArrayList<>();
    private List<WalkRouteResult> finalWalkRouteResultList = new ArrayList<>();
    private int justCount = 0;
    private int mustCount = 0;
    private LatLng mStart;
    private List<DPoint> mDPointList;
    private int gpsCount = 0;
    private int beaconCount = 0;
    private float target = 0;

    private ThreadPoolExecutor mThreadPoolExecutor;
    private Handler mHandler;

    private RouteProcessor() {
        mThreadPoolExecutor = new ThreadPoolExecutor(4, 8, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        mThreadPoolExecutor.allowCoreThreadTimeOut(true);
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 1000) {
                    onWalkRouteSearched((WalkRouteResult) msg.obj);
                }
                return true;
            }
        });
    }

    public static RouteProcessor newRouteProcessor() {
        return new RouteProcessor();
    }

    public RouteProcessor withContext(Context context) {
        this.routeSearch = new RouteSearch(context);
        return this;
    }

    public RouteProcessor start(LatLng start) {
        this.mStart = start;
        return this;
    }

    public RouteProcessor via(List<DPoint> dPointList) {
        this.mDPointList = dPointList;
        return this;
    }

    public RouteProcessor withCallback(AllRouteSearchedCallback allRouteSearchedCallback) {
        this.mAllRouteSearchedCallback = allRouteSearchedCallback;
        return this;
    }

    public RouteProcessor target(int gpsCount, int beaconCount, float target) {
        this.gpsCount = gpsCount;
        this.beaconCount = beaconCount;
        this.target = target;
        return this;
    }

    public void process() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(10 * 1000);
                } catch (InterruptedException ignored) {
                }
                for (DPoint dPoint : mDPointList) {
                    LatLonPoint latLonPointA = new LatLonPoint(mStart.latitude, mStart.longitude);
                    LatLonPoint latLonPointB = new LatLonPoint(dPoint.latLng.latitude, dPoint.latLng.longitude);
                    RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(latLonPointA, latLonPointB);
                    final RouteSearch.WalkRouteQuery walkRouteQuery = new RouteSearch.WalkRouteQuery(fromAndTo);
                    walkRouteQueryList.add(walkRouteQuery);
                    walkRouteResultList.add(null);
                    try {
                        sleep(250);
                    } catch (InterruptedException ignored) {
                    }
                    mThreadPoolExecutor.execute(new WalkRouteQueryRunnable(walkRouteQuery, mHandler));
                }
            }
        }).start();

    }


    public interface AllRouteSearchedCallback {
        void onAllRouteSearched(List<LatLng> route);
    }

    private void onWalkRouteSearched(WalkRouteResult walkRouteResult) {

        int index = walkRouteQueryList.indexOf(walkRouteResult.getWalkQuery());
        walkRouteResultList.set(index, walkRouteResult);

        for (WalkRouteResult routeResult : walkRouteResultList) {
            if (routeResult == null) return;
        }

        WalkRouteResult selectedWalkRouteResult = new WalkRouteResult();
        if (justCount < gpsCount || mustCount < beaconCount || Math.random() < 0.4) {
            float minDistance = Float.MAX_VALUE;
            for (WalkRouteResult routeResult : walkRouteResultList) {
                float distance = routeResult.getPaths().get(0).getDistance();
                if (distance < minDistance) {
                    if (justCount < gpsCount || mustCount < beaconCount) {
                        LatLonPoint latLonPoint = routeResult.getTargetPos();
                        Beacon beacon = null;
                        for (DPoint dPoint : mDPointList) {
                            if (dPoint.latLng.latitude == latLonPoint.getLatitude() && dPoint.latLng.longitude == latLonPoint.getLongitude()) {
                                beacon = dPoint.beacon;
                            }
                        }
                        if (justCount < gpsCount && !(mustCount < beaconCount)) {
                            if (beacon != null) {
                                continue;
                            }
                        }
                        if (!(justCount < gpsCount) && mustCount < beaconCount) {
                            if (beacon == null) {
                                continue;
                            }
                        }
                    }
                    minDistance = distance;
                    selectedWalkRouteResult = routeResult;
                }
            }
        } else {
            float maxDistance = Float.MIN_VALUE;
            for (WalkRouteResult routeResult : walkRouteResultList) {
                float distance = routeResult.getPaths().get(0).getDistance();
                if (distance > maxDistance) {
                    maxDistance = distance;
                    selectedWalkRouteResult = routeResult;
                }
            }
        }

        walkRouteResultList.clear();
        walkRouteQueryList.clear();

        if (justCount < gpsCount || mustCount < beaconCount) {
            LatLonPoint latLonPoint = selectedWalkRouteResult.getTargetPos();
            for (DPoint dPoint : mDPointList) {
                if (dPoint.latLng.latitude == latLonPoint.getLatitude() && dPoint.latLng.longitude == latLonPoint.getLongitude()) {
                    if (dPoint.beacon == null) {
                        justCount++;
                    } else {
                        mustCount++;
                    }
                    break;
                }
            }
        }
        finalWalkRouteResultList.add(selectedWalkRouteResult);

        float finalDistance = 0;
        for (WalkRouteResult routeResult : finalWalkRouteResultList) {
            finalDistance += routeResult.getPaths().get(0).getDistance();
        }

//        XposedBridge.log("finalDistance: " + finalDistance);

        if (finalDistance < target + 20) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    LatLonPoint start = finalWalkRouteResultList.get(finalWalkRouteResultList.size() - 1).getTargetPos();
                    LatLonPoint last = finalWalkRouteResultList.get(finalWalkRouteResultList.size() - 1).getStartPos();
                    for (DPoint dPoint : mDPointList) {
                        if (start.getLatitude() == dPoint.latLng.latitude && start.getLongitude() == dPoint.latLng.longitude) {
                            continue;
                        }
                        if (last.getLatitude() == dPoint.latLng.latitude && last.getLongitude() == dPoint.latLng.longitude) {
                            continue;
                        }
                        LatLonPoint latLonPoint = new LatLonPoint(dPoint.latLng.latitude, dPoint.latLng.longitude);
                        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(start, latLonPoint);
                        RouteSearch.WalkRouteQuery walkRouteQuery = new RouteSearch.WalkRouteQuery(fromAndTo);
                        walkRouteQueryList.add(walkRouteQuery);
                        walkRouteResultList.add(null);
                        try {
                            sleep(250);
                        } catch (InterruptedException ignored) {

                        }
                        mThreadPoolExecutor.execute(new WalkRouteQueryRunnable(walkRouteQuery, mHandler));
                    }
                }
            }).start();
        } else {
            mThreadPoolExecutor.shutdownNow();
//            walkRouteQueryList.clear();
            List<LatLng> route = new ArrayList<>();
            route.add(mStart);
            for (WalkRouteResult routeResult : finalWalkRouteResultList) {
                for (WalkStep walkStep : routeResult.getPaths().get(0).getSteps()) {
                    for (LatLonPoint latLonPoint : walkStep.getPolyline()) {
                        route.add(new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude()));
                    }
                }
                route.add(new LatLng(routeResult.getTargetPos().getLatitude(), routeResult.getTargetPos().getLongitude()));
            }
            if (mAllRouteSearchedCallback != null) {
                mAllRouteSearchedCallback.onAllRouteSearched(route);
            }
        }

    }

    private class WalkRouteQueryRunnable implements Runnable {

        private RouteSearch.WalkRouteQuery walkRouteQuery;
        private Handler messageHandler;

        WalkRouteQueryRunnable(RouteSearch.WalkRouteQuery walkRouteQuery, Handler handler) {
            this.walkRouteQuery = walkRouteQuery;
            this.messageHandler = handler;
        }

        @Override
        public void run() {
            WalkRouteResult walkRouteResult = null;
            while (walkRouteResult == null) {
                try {
                    walkRouteResult = routeSearch.calculateWalkRoute(this.walkRouteQuery);
                } catch (AMapException aMapException) {
                    try {
                        sleep(250);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
            Message message = this.messageHandler.obtainMessage(1000, walkRouteResult);
            this.messageHandler.sendMessage(message);
        }

    }

}
