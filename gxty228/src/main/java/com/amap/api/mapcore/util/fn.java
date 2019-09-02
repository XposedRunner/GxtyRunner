package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.CoordinateConverter.CoordType;
import com.amap.api.maps.LocationSource.OnLocationChangedListener;
import com.amap.api.maps.model.LatLng;
import com.amap.api.trace.LBSTraceBase;
import com.amap.api.trace.LBSTraceClient;
import com.amap.api.trace.TraceListener;
import com.amap.api.trace.TraceLocation;
import com.amap.api.trace.TraceStatusListener;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

/* compiled from: TraceManager */
public class fn implements OnLocationChangedListener, LBSTraceBase {
    private static final TimeUnit s = TimeUnit.SECONDS;
    int a = Runtime.getRuntime().availableProcessors();
    private Context b;
    private CoordinateConverter c;
    private ExecutorService d;
    private ExecutorService e;
    private long f = 2000;
    private int g = 5;
    private TraceStatusListener h;
    private ad i;
    private List<TraceLocation> j = new ArrayList();
    private int k = 0;
    private int l = 0;
    private long m = 0;
    private c n;
    private TraceLocation o = null;
    private List<LatLng> p = new ArrayList();
    private List<LatLng> q = new ArrayList();
    private List<LatLng> r = new ArrayList();
    private BlockingQueue<Runnable> t = new LinkedBlockingQueue();
    private BlockingQueue<Runnable> u = new LinkedBlockingQueue();

    /* compiled from: TraceManager */
    class a implements Runnable {
        final /* synthetic */ fn a;
        private List<TraceLocation> b = new ArrayList();
        private int c;
        private int d;
        private List<TraceLocation> e;
        private String f;
        private TraceListener g;

        public a(fn fnVar, int i, List<TraceLocation> list, int i2, TraceListener traceListener) {
            this.a = fnVar;
            this.c = i2;
            this.d = i;
            this.e = list;
            this.f = ed.a();
            this.g = traceListener;
        }

        public void run() {
            try {
                this.a.n.a(this.g);
                int a = a();
                if (this.e == null || this.e.size() < 2) {
                    fv.a().a(this.a.n, this.d, LBSTraceClient.MIN_GRASP_POINT_ERROR);
                    return;
                }
                TraceLocation copy;
                for (TraceLocation copy2 : this.e) {
                    copy2 = copy2.copy();
                    if (copy2 != null && copy2.getLatitude() > Utils.DOUBLE_EPSILON && copy2.getLongitude() > Utils.DOUBLE_EPSILON) {
                        this.b.add(copy2);
                    }
                }
                int size = (this.b.size() - 2) / 500;
                fv.a().a(this.f, this.d, size, a);
                int i = 500;
                int i2 = 0;
                while (i2 <= size) {
                    int size2;
                    if (i2 == size) {
                        size2 = this.b.size();
                    } else {
                        size2 = i;
                    }
                    List arrayList = new ArrayList();
                    for (a = 0; a < size2; a++) {
                        copy2 = (TraceLocation) this.b.remove(0);
                        if (copy2 != null) {
                            if (this.c != 1) {
                                if (this.c == 3) {
                                    this.a.c.from(CoordType.BAIDU);
                                } else if (this.c == 2) {
                                    this.a.c.from(CoordType.GPS);
                                }
                                this.a.c.coord(new LatLng(copy2.getLatitude(), copy2.getLongitude()));
                                LatLng convert = this.a.c.convert();
                                if (convert != null) {
                                    copy2.setLatitude(convert.latitude);
                                    copy2.setLongitude(convert.longitude);
                                }
                            }
                            arrayList.add(copy2);
                        }
                    }
                    if (arrayList.size() < 2) {
                        i = size2;
                    } else if (arrayList.size() > 500) {
                        i = size2;
                    } else {
                        this.a.e.execute(new fm(this.a.b, this.a.n, arrayList, this.c, this.f, this.d, i2));
                        i2++;
                        Thread.sleep(50);
                        i = size2;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        private int a() {
            if (this.e == null || this.e.size() == 0) {
                return 0;
            }
            List arrayList = new ArrayList();
            int i = 0;
            for (TraceLocation traceLocation : this.e) {
                if (traceLocation != null) {
                    if (((double) traceLocation.getSpeed()) < 0.01d) {
                        arrayList.add(traceLocation);
                    } else {
                        int a = a(arrayList) + i;
                        arrayList.clear();
                        i = a;
                    }
                }
            }
            return i;
        }

        private int a(List<TraceLocation> list) {
            int size = list.size();
            if (size <= 1) {
                return 0;
            }
            TraceLocation traceLocation = (TraceLocation) list.get(0);
            TraceLocation traceLocation2 = (TraceLocation) list.get(size - 1);
            if (traceLocation == null || traceLocation2 == null) {
                return 0;
            }
            int i;
            if (traceLocation == null || traceLocation2 == null) {
                i = 0;
            } else {
                i = (int) ((traceLocation2.getTime() - traceLocation.getTime()) / 1000);
            }
            return i;
        }
    }

    /* compiled from: TraceManager */
    class b implements TraceListener {
        final /* synthetic */ fn a;
        private final List<TraceLocation> b;

        public b(fn fnVar, List<TraceLocation> list) {
            this.a = fnVar;
            this.b = list;
        }

        public void onRequestFailed(int i, String str) {
            List arrayList = new ArrayList();
            if (this.a.r != null) {
                arrayList.addAll(this.a.r);
            }
            if (this.b != null) {
                int size = this.b.size();
                if (this.b.size() > this.a.g) {
                    for (int b = size - this.a.g; b < size; b++) {
                        TraceLocation traceLocation = (TraceLocation) this.b.get(b);
                        if (traceLocation != null) {
                            arrayList.add(new LatLng(traceLocation.getLatitude(), traceLocation.getLongitude()));
                        }
                    }
                }
            }
            a(i, arrayList);
        }

        public void onTraceProcessing(int i, int i2, List<LatLng> list) {
        }

        public void onFinished(int i, List<LatLng> list, int i2, int i3) {
            a(i, list);
        }

        private void a(int i, List<LatLng> list) {
            try {
                synchronized (this.a.r) {
                    this.a.r.clear();
                    this.a.r.addAll(list);
                }
                this.a.q.clear();
                if (i == 0) {
                    this.a.q.addAll(this.a.r);
                } else {
                    this.a.q.addAll(this.a.p);
                    this.a.q.addAll(this.a.r);
                }
                this.a.h.onTraceStatus(this.a.j, this.a.q, LBSTraceClient.TRACE_SUCCESS);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* compiled from: TraceManager */
    static class c extends Handler {
        private TraceListener a;

        public c(Looper looper) {
            super(looper);
        }

        public void a(TraceListener traceListener) {
            this.a = traceListener;
        }

        public void handleMessage(Message message) {
            try {
                if (this.a != null) {
                    Bundle data = message.getData();
                    if (data != null) {
                        int i = data.getInt("lineID");
                        switch (message.what) {
                            case 100:
                                this.a.onTraceProcessing(i, message.arg1, (List) message.obj);
                                return;
                            case 101:
                                this.a.onFinished(i, (List) message.obj, message.arg1, message.arg2);
                                return;
                            case 102:
                                this.a.onRequestFailed(i, (String) message.obj);
                                return;
                            default:
                                return;
                        }
                        th.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public fn(Context context) {
        this.b = context.getApplicationContext();
        this.c = new CoordinateConverter(this.b);
        this.n = new c(Looper.getMainLooper());
        this.d = new ThreadPoolExecutor(1, this.a * 2, 1, s, this.t, new dy("AMapTraceManagerProcess"), new AbortPolicy());
        this.e = new ThreadPoolExecutor(1, this.a * 2, 1, s, this.u, new dy("AMapTraceManagerRequest"), new AbortPolicy());
    }

    public void queryProcessedTrace(int i, List<TraceLocation> list, int i2, TraceListener traceListener) {
        try {
            this.d.execute(new a(this, i, list, i2, traceListener));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setLocationInterval(long j) {
        this.f = j;
    }

    public void setTraceStatusInterval(int i) {
        this.g = Math.max(i, 2);
    }

    public void startTrace(TraceStatusListener traceStatusListener) {
        if (this.b == null) {
            Log.w("LBSTraceClient", "Context need to be initialized");
            return;
        }
        this.m = System.currentTimeMillis();
        this.h = traceStatusListener;
        if (this.i == null) {
            this.i = new ad(this.b);
            this.i.a(this.f);
            this.i.activate(this);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLocationChanged(android.location.Location r12) {
        /*
        r11 = this;
        r0 = r11.h;
        if (r0 == 0) goto L_0x005e;
    L_0x0004:
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0088 }
        r2 = r11.m;	 Catch:{ Throwable -> 0x0088 }
        r0 = r0 - r2;
        r2 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 < 0) goto L_0x001e;
    L_0x0011:
        r0 = r11.h;	 Catch:{ Throwable -> 0x0088 }
        if (r0 == 0) goto L_0x001e;
    L_0x0015:
        r0 = r11.h;	 Catch:{ Throwable -> 0x0088 }
        r1 = 0;
        r2 = 0;
        r3 = "定位超时";
        r0.onTraceStatus(r1, r2, r3);	 Catch:{ Throwable -> 0x0088 }
    L_0x001e:
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0088 }
        r11.m = r0;	 Catch:{ Throwable -> 0x0088 }
        r0 = r12.getExtras();	 Catch:{ Throwable -> 0x0088 }
        r1 = "errorCode";
        r1 = r0.getInt(r1);	 Catch:{ Throwable -> 0x0088 }
        if (r1 == 0) goto L_0x005f;
    L_0x0030:
        r2 = "LBSTraceClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0088 }
        r3.<init>();	 Catch:{ Throwable -> 0x0088 }
        r4 = "Locate failed [errorCode:\"";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x0088 }
        r1 = r3.append(r1);	 Catch:{ Throwable -> 0x0088 }
        r3 = "\"  errorInfo:";
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x0088 }
        r3 = "errorInfo";
        r0 = r0.getString(r3);	 Catch:{ Throwable -> 0x0088 }
        r0 = r1.append(r0);	 Catch:{ Throwable -> 0x0088 }
        r1 = "\"]";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x0088 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x0088 }
        android.util.Log.w(r2, r0);	 Catch:{ Throwable -> 0x0088 }
    L_0x005e:
        return;
    L_0x005f:
        r10 = r11.j;	 Catch:{ Throwable -> 0x0088 }
        monitor-enter(r10);	 Catch:{ Throwable -> 0x0088 }
        r1 = new com.amap.api.trace.TraceLocation;	 Catch:{ all -> 0x0085 }
        r2 = r12.getLatitude();	 Catch:{ all -> 0x0085 }
        r4 = r12.getLongitude();	 Catch:{ all -> 0x0085 }
        r6 = r12.getSpeed();	 Catch:{ all -> 0x0085 }
        r7 = r12.getBearing();	 Catch:{ all -> 0x0085 }
        r8 = r12.getTime();	 Catch:{ all -> 0x0085 }
        r1.<init>(r2, r4, r6, r7, r8);	 Catch:{ all -> 0x0085 }
        r0 = r11.o;	 Catch:{ all -> 0x0085 }
        r0 = r11.a(r0, r1);	 Catch:{ all -> 0x0085 }
        if (r0 == 0) goto L_0x008d;
    L_0x0083:
        monitor-exit(r10);	 Catch:{ all -> 0x0085 }
        goto L_0x005e;
    L_0x0085:
        r0 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x0085 }
        throw r0;	 Catch:{ Throwable -> 0x0088 }
    L_0x0088:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x005e;
    L_0x008d:
        r0 = r11.j;	 Catch:{ all -> 0x0085 }
        r0.add(r1);	 Catch:{ all -> 0x0085 }
        r11.o = r1;	 Catch:{ all -> 0x0085 }
        r0 = r11.k;	 Catch:{ all -> 0x0085 }
        r0 = r0 + 1;
        r11.k = r0;	 Catch:{ all -> 0x0085 }
        r0 = r11.k;	 Catch:{ all -> 0x0085 }
        r1 = r11.g;	 Catch:{ all -> 0x0085 }
        if (r0 != r1) goto L_0x00ad;
    L_0x00a0:
        r0 = r11.l;	 Catch:{ all -> 0x0085 }
        r1 = r11.k;	 Catch:{ all -> 0x0085 }
        r0 = r0 + r1;
        r11.l = r0;	 Catch:{ all -> 0x0085 }
        r11.a();	 Catch:{ all -> 0x0085 }
        r0 = 0;
        r11.k = r0;	 Catch:{ all -> 0x0085 }
    L_0x00ad:
        monitor-exit(r10);	 Catch:{ all -> 0x0085 }
        goto L_0x005e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.fn.onLocationChanged(android.location.Location):void");
    }

    private boolean a(TraceLocation traceLocation, TraceLocation traceLocation2) {
        if (traceLocation == null || traceLocation2 == null || traceLocation.getLatitude() != traceLocation2.getLatitude() || traceLocation.getLongitude() != traceLocation2.getLongitude()) {
            return false;
        }
        return true;
    }

    private void a() {
        int size = this.j.size();
        if (size >= this.g) {
            if (size <= 50) {
                List arrayList = new ArrayList(this.j);
                queryProcessedTrace(0, arrayList, 1, new b(this, arrayList));
                return;
            }
            int i = size - 50;
            if (i >= 0) {
                a(new ArrayList(this.j.subList(i - this.g, i)));
                List arrayList2 = new ArrayList(this.j.subList(i, size));
                queryProcessedTrace(i, arrayList2, 1, new b(this, arrayList2));
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.util.List<com.amap.api.trace.TraceLocation> r19) {
        /*
        r18 = this;
        r0 = r18;
        r0 = r0.r;
        r16 = r0;
        monitor-enter(r16);
        if (r19 == 0) goto L_0x0010;
    L_0x0009:
        r2 = r19.size();	 Catch:{ all -> 0x001f }
        r3 = 1;
        if (r2 >= r3) goto L_0x0012;
    L_0x0010:
        monitor-exit(r16);	 Catch:{ all -> 0x001f }
    L_0x0011:
        return;
    L_0x0012:
        r0 = r18;
        r2 = r0.r;	 Catch:{ all -> 0x001f }
        r2 = r2.size();	 Catch:{ all -> 0x001f }
        r3 = 1;
        if (r2 >= r3) goto L_0x0022;
    L_0x001d:
        monitor-exit(r16);	 Catch:{ all -> 0x001f }
        goto L_0x0011;
    L_0x001f:
        r2 = move-exception;
        monitor-exit(r16);	 Catch:{ all -> 0x001f }
        throw r2;
    L_0x0022:
        r12 = 0;
        r2 = 0;
        r15 = r19.iterator();	 Catch:{ all -> 0x001f }
        r14 = r2;
    L_0x002a:
        r2 = r15.hasNext();	 Catch:{ all -> 0x001f }
        if (r2 == 0) goto L_0x005c;
    L_0x0030:
        r2 = r15.next();	 Catch:{ all -> 0x001f }
        r2 = (com.amap.api.trace.TraceLocation) r2;	 Catch:{ all -> 0x001f }
        if (r2 == 0) goto L_0x002a;
    L_0x0038:
        if (r14 != 0) goto L_0x003e;
    L_0x003a:
        r4 = r12;
    L_0x003b:
        r14 = r2;
        r12 = r4;
        goto L_0x002a;
    L_0x003e:
        r4 = r14.getLatitude();	 Catch:{ all -> 0x001f }
        r6 = r14.getLongitude();	 Catch:{ all -> 0x001f }
        r8 = r2.getLatitude();	 Catch:{ all -> 0x001f }
        r10 = r2.getLongitude();	 Catch:{ all -> 0x001f }
        r3 = r18;
        r4 = r3.a(r4, r6, r8, r10);	 Catch:{ all -> 0x001f }
        r6 = 4636737291354636288; // 0x4059000000000000 float:0.0 double:100.0;
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r3 > 0) goto L_0x002a;
    L_0x005a:
        r4 = r4 + r12;
        goto L_0x003b;
    L_0x005c:
        r14 = 0;
        r2 = 0;
        r0 = r18;
        r3 = r0.r;	 Catch:{ all -> 0x001f }
        r17 = r3.iterator();	 Catch:{ all -> 0x001f }
        r3 = r2;
    L_0x0068:
        r2 = r17.hasNext();	 Catch:{ all -> 0x001f }
        if (r2 == 0) goto L_0x00a8;
    L_0x006e:
        r2 = r17.next();	 Catch:{ all -> 0x001f }
        r2 = (com.amap.api.maps.model.LatLng) r2;	 Catch:{ all -> 0x001f }
        if (r2 != 0) goto L_0x007a;
    L_0x0076:
        r17.remove();	 Catch:{ all -> 0x001f }
        goto L_0x0068;
    L_0x007a:
        if (r3 != 0) goto L_0x008a;
    L_0x007c:
        r0 = r18;
        r3 = r0.p;	 Catch:{ all -> 0x001f }
        r3.add(r2);	 Catch:{ all -> 0x001f }
        r17.remove();	 Catch:{ all -> 0x001f }
        r4 = r14;
    L_0x0087:
        r3 = r2;
        r14 = r4;
        goto L_0x0068;
    L_0x008a:
        r4 = r3.latitude;	 Catch:{ all -> 0x001f }
        r6 = r3.longitude;	 Catch:{ all -> 0x001f }
        r8 = r2.latitude;	 Catch:{ all -> 0x001f }
        r10 = r2.longitude;	 Catch:{ all -> 0x001f }
        r3 = r18;
        r4 = r3.a(r4, r6, r8, r10);	 Catch:{ all -> 0x001f }
        r4 = r4 + r14;
        r3 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1));
        if (r3 >= 0) goto L_0x00a8;
    L_0x009d:
        r0 = r18;
        r3 = r0.p;	 Catch:{ all -> 0x001f }
        r3.add(r2);	 Catch:{ all -> 0x001f }
        r17.remove();	 Catch:{ all -> 0x001f }
        goto L_0x0087;
    L_0x00a8:
        monitor-exit(r16);	 Catch:{ all -> 0x001f }
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.fn.a(java.util.List):void");
    }

    private double a(double d, double d2, double d3, double d4) {
        double d5 = d > d3 ? d - d3 : d3 - d;
        double d6 = d2 > d4 ? d2 - d4 : d4 - d2;
        return Math.sqrt((d6 * d6) + (d5 * d5));
    }

    public void stopTrace() {
        b();
        c();
    }

    private void b() {
        if (this.i != null) {
            this.i.deactivate();
            this.i = null;
        }
    }

    private void c() {
        this.t.clear();
        this.u.clear();
        if (this.j != null) {
            synchronized (this.j) {
                if (this.j != null) {
                    this.j.clear();
                }
                this.l = 0;
                this.k = 0;
                this.m = 0;
                this.o = null;
            }
        }
    }

    public void destroy() {
        try {
            stopTrace();
            if (!(this.d == null || this.d.isShutdown())) {
                this.d.shutdownNow();
                this.d = null;
            }
            if (!(this.e == null || this.e.isShutdown())) {
                this.e.shutdownNow();
                this.e = null;
            }
            this.j = null;
            this.h = null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.b = null;
        this.c = null;
    }
}
