package com.loc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import android.webkit.WebView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;
import com.amap.api.location.APSService;
import com.amap.api.location.LocationManagerBase;
import com.amap.api.location.UmidtokenInfo;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import org.altbeacon.beacon.BeaconManager;

/* compiled from: AmapLocationManager */
public class ce implements LocationManagerBase {
    private volatile boolean A = false;
    private boolean B = true;
    private boolean C = true;
    private cy D = null;
    private ServiceConnection E = new ServiceConnection(this) {
        final /* synthetic */ ce a;

        {
            this.a = r1;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.a.h = new Messenger(iBinder);
                this.a.z = true;
                this.a.q = true;
            } catch (Throwable th) {
                cl.a(th, "AmapLocationManager", "onServiceConnected");
            }
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            this.a.h = null;
            this.a.z = false;
        }
    };
    AMapLocationClientOption a = new AMapLocationClientOption();
    public c b;
    cx c = null;
    ArrayList<AMapLocationListener> d = new ArrayList();
    boolean e = false;
    public boolean f = true;
    cz g;
    Messenger h = null;
    Messenger i = null;
    Intent j = null;
    int k = 0;
    b l = null;
    boolean m = false;
    AMapLocationMode n = AMapLocationMode.Hight_Accuracy;
    Object o = new Object();
    cq p = null;
    boolean q = false;
    cv r = null;
    String s = null;
    boolean t = false;
    boolean u = false;
    a v = null;
    String w = null;
    boolean x = false;
    private Context y;
    private boolean z = false;

    /* compiled from: AmapLocationManager */
    public class a extends Handler {
        final /* synthetic */ ce a;

        public a(ce ceVar, Looper looper) {
            this.a = ceVar;
            super(looper);
        }

        public final void handleMessage(Message message) {
            try {
                super.handleMessage(message);
                switch (message.what) {
                    case 1002:
                        ce.a(this.a, (AMapLocationListener) message.obj);
                        return;
                    case PointerIconCompat.TYPE_HELP /*1003*/:
                        try {
                            this.a.d();
                            return;
                        } catch (Throwable th) {
                            cl.a(th, "AMapLocationManage$MHandlerr", "handleMessage START_LOCATION");
                            return;
                        }
                    case 1004:
                        try {
                            this.a.e();
                            return;
                        } catch (Throwable th2) {
                            cl.a(th2, "AMapLocationManage$MHandlerr", "handleMessage STOP_LOCATION");
                            return;
                        }
                    case 1005:
                        try {
                            ce.b(this.a, (AMapLocationListener) message.obj);
                            return;
                        } catch (Throwable th22) {
                            cl.a(th22, "AMapLocationManage$MHandlerr", "handleMessage REMOVE_LISTENER");
                            return;
                        }
                    case PointerIconCompat.TYPE_TEXT /*1008*/:
                        try {
                            ce.i(this.a);
                            return;
                        } catch (Throwable th222) {
                            cl.a(th222, "AMapLocationManage$MHandlerr", "handleMessage START_SOCKET");
                            return;
                        }
                    case PointerIconCompat.TYPE_VERTICAL_TEXT /*1009*/:
                        try {
                            ce.j(this.a);
                            return;
                        } catch (Throwable th2222) {
                            cl.a(th2222, "AMapLocationManage$MHandlerr", "handleMessage STOP_SOCKET");
                            return;
                        }
                    case 1011:
                        try {
                            this.a.a();
                            return;
                        } catch (Throwable th22222) {
                            cl.a(th22222, "AMapLocationManage$MHandlerr", "handleMessage DESTROY");
                            return;
                        }
                    case PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW /*1014*/:
                        ce.b(this.a, message);
                        return;
                    case PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW /*1015*/:
                        try {
                            this.a.c.a(this.a.a);
                            this.a.a(1025, null, (long) BeaconManager.DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD);
                            return;
                        } catch (Throwable th222222) {
                            cl.a(th222222, "AMapLocationManage$MHandlerr", "handleMessage START_GPS_LOCATION");
                            return;
                        }
                    case PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW /*1016*/:
                        try {
                            if (this.a.c.b()) {
                                this.a.a((int) PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, null, 1000);
                                return;
                            } else {
                                ce.f(this.a);
                                return;
                            }
                        } catch (Throwable th2222222) {
                            cl.a(th2222222, "AMapLocationManage$MHandlerr", "handleMessage START_LBS_LOCATION");
                            return;
                        }
                    case PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW /*1017*/:
                        try {
                            this.a.c.a();
                            this.a.a(1025);
                            return;
                        } catch (Throwable th22222222) {
                            cl.a(th22222222, "AMapLocationManage$MHandlerr", "handleMessage STOP_GPS_LOCATION");
                            return;
                        }
                    case PointerIconCompat.TYPE_ZOOM_IN /*1018*/:
                        try {
                            this.a.a = (AMapLocationClientOption) message.obj;
                            if (this.a.a != null) {
                                ce.h(this.a);
                                return;
                            }
                            return;
                        } catch (Throwable th222222222) {
                            cl.a(th222222222, "AMapLocationManage$MHandlerr", "handleMessage SET_OPTION");
                            return;
                        }
                    case 1023:
                        try {
                            ce.c(this.a, message);
                            return;
                        } catch (Throwable th2222222222) {
                            cl.a(th2222222222, "AMapLocationManage$MHandlerr", "handleMessage ACTION_ENABLE_BACKGROUND");
                            return;
                        }
                    case 1024:
                        try {
                            ce.d(this.a, message);
                            return;
                        } catch (Throwable th22222222222) {
                            cl.a(th22222222222, "AMapLocationManage$MHandlerr", "handleMessage ACTION_DISABLE_BACKGROUND");
                            return;
                        }
                    case 1025:
                        try {
                            if (this.a.c != null) {
                                if (this.a.c.f()) {
                                    this.a.c.a();
                                    this.a.c.a(this.a.a);
                                }
                                this.a.a(1025, null, (long) BeaconManager.DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD);
                                return;
                            }
                            return;
                        } catch (Throwable th222222222222) {
                            cl.a(th222222222222, "AMapLocationManage$MHandlerr", "handleMessage ACTION_REBOOT_GPS_LOCATION");
                            return;
                        }
                    default:
                        return;
                }
            } catch (Throwable th2222222222222) {
                cl.a(th2222222222222, "AMapLocationManage$MHandlerr", "handleMessage");
            }
            cl.a(th2222222222222, "AMapLocationManage$MHandlerr", "handleMessage");
        }
    }

    /* compiled from: AmapLocationManager */
    static class b extends HandlerThread {
        ce a = null;

        public b(String str, ce ceVar) {
            super(str);
            this.a = ceVar;
        }

        protected final void onLooperPrepared() {
            try {
                this.a.g.a();
                this.a.g();
                super.onLooperPrepared();
            } catch (Throwable th) {
            }
        }

        public final void run() {
            try {
                super.run();
            } catch (Throwable th) {
            }
        }
    }

    /* compiled from: AmapLocationManager */
    public class c extends Handler {
        final /* synthetic */ ce a;

        public c(ce ceVar) {
            this.a = ceVar;
        }

        public c(ce ceVar, Looper looper) {
            this.a = ceVar;
            super(looper);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void handleMessage(android.os.Message r4) {
            /*
            r3 = this;
            super.handleMessage(r4);	 Catch:{ Throwable -> 0x0029 }
            r0 = r3.a;	 Catch:{ Throwable -> 0x0029 }
            r0 = r0.m;	 Catch:{ Throwable -> 0x0029 }
            if (r0 == 0) goto L_0x0010;
        L_0x0009:
            r0 = com.loc.cl.d();	 Catch:{ Throwable -> 0x0029 }
            if (r0 != 0) goto L_0x0010;
        L_0x000f:
            return;
        L_0x0010:
            r0 = r4.what;	 Catch:{ Throwable -> 0x0029 }
            switch(r0) {
                case 1: goto L_0x0016;
                case 2: goto L_0x0038;
                case 3: goto L_0x000f;
                case 5: goto L_0x0047;
                case 6: goto L_0x0067;
                case 7: goto L_0x0082;
                case 8: goto L_0x0032;
                case 100: goto L_0x009d;
                default: goto L_0x0015;
            };
        L_0x0015:
            goto L_0x000f;
        L_0x0016:
            r0 = r4.getData();	 Catch:{ Throwable -> 0x0020 }
            r1 = r3.a;	 Catch:{ Throwable -> 0x0020 }
            com.loc.ce.a(r1, r0);	 Catch:{ Throwable -> 0x0020 }
            goto L_0x000f;
        L_0x0020:
            r0 = move-exception;
            r1 = "AmapLocationManager$ActionHandler";
            r2 = "handleMessage RESULT_LBS_LOCATIONSUCCESS";
            com.loc.cl.a(r0, r1, r2);	 Catch:{ Throwable -> 0x0029 }
            goto L_0x000f;
        L_0x0029:
            r0 = move-exception;
            r1 = "AmapLocationManager$MainHandler";
            r2 = "handleMessage";
            com.loc.cl.a(r0, r1, r2);
            goto L_0x000f;
        L_0x0032:
            r0 = 0;
            r1 = 2141; // 0x85d float:3.0E-42 double:1.058E-320;
            com.loc.cq.a(r0, r1);	 Catch:{ Throwable -> 0x0029 }
        L_0x0038:
            r0 = r3.a;	 Catch:{ Throwable -> 0x003e }
            com.loc.ce.a(r0, r4);	 Catch:{ Throwable -> 0x003e }
            goto L_0x000f;
        L_0x003e:
            r0 = move-exception;
            r1 = "AmapLocationManager$ActionHandler";
            r2 = "handleMessage RESULT_GPS_LOCATIONSUCCESS";
            com.loc.cl.a(r0, r1, r2);	 Catch:{ Throwable -> 0x0029 }
            goto L_0x000f;
        L_0x0047:
            r0 = r4.getData();	 Catch:{ Throwable -> 0x005e }
            r1 = "optBundle";
            r2 = r3.a;	 Catch:{ Throwable -> 0x005e }
            r2 = r2.a;	 Catch:{ Throwable -> 0x005e }
            r2 = com.loc.cl.a(r2);	 Catch:{ Throwable -> 0x005e }
            r0.putBundle(r1, r2);	 Catch:{ Throwable -> 0x005e }
            r1 = r3.a;	 Catch:{ Throwable -> 0x005e }
            r1.a(10, r0);	 Catch:{ Throwable -> 0x005e }
            goto L_0x000f;
        L_0x005e:
            r0 = move-exception;
            r1 = "AmapLocationManager$ActionHandler";
            r2 = "handleMessage RESULT_GPS_LOCATIONCHANGE";
            com.loc.cl.a(r0, r1, r2);	 Catch:{ Throwable -> 0x0029 }
            goto L_0x000f;
        L_0x0067:
            r0 = r4.getData();	 Catch:{ Throwable -> 0x0079 }
            r1 = r3.a;	 Catch:{ Throwable -> 0x0079 }
            r1 = r1.c;	 Catch:{ Throwable -> 0x0079 }
            if (r1 == 0) goto L_0x000f;
        L_0x0071:
            r1 = r3.a;	 Catch:{ Throwable -> 0x0079 }
            r1 = r1.c;	 Catch:{ Throwable -> 0x0079 }
            r1.a(r0);	 Catch:{ Throwable -> 0x0079 }
            goto L_0x000f;
        L_0x0079:
            r0 = move-exception;
            r1 = "AmapLocationManager$ActionHandler";
            r2 = "handleMessage RESULT_GPS_GEO_SUCCESS";
            com.loc.cl.a(r0, r1, r2);	 Catch:{ Throwable -> 0x0029 }
            goto L_0x000f;
        L_0x0082:
            r0 = r4.getData();	 Catch:{ Throwable -> 0x0093 }
            r1 = r3.a;	 Catch:{ Throwable -> 0x0093 }
            r2 = "ngpsAble";
            r0 = r0.getBoolean(r2);	 Catch:{ Throwable -> 0x0093 }
            r1.C = r0;	 Catch:{ Throwable -> 0x0093 }
            goto L_0x000f;
        L_0x0093:
            r0 = move-exception;
            r1 = "AmapLocationManager$ActionHandler";
            r2 = "handleMessage RESULT_NGPS_ABLE";
            com.loc.cl.a(r0, r1, r2);	 Catch:{ Throwable -> 0x0029 }
            goto L_0x000f;
        L_0x009d:
            r0 = r3.a;	 Catch:{ Throwable -> 0x00a4 }
            com.loc.ce.a(r0);	 Catch:{ Throwable -> 0x00a4 }
            goto L_0x000f;
        L_0x00a4:
            r0 = move-exception;
            r1 = "AmapLocationManager$ActionHandler";
            r2 = "handleMessage RESULT_FASTSKY";
            com.loc.cl.a(r0, r1, r2);	 Catch:{ Throwable -> 0x0029 }
            goto L_0x000f;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loc.ce.c.handleMessage(android.os.Message):void");
        }
    }

    public ce(Context context, Intent intent) {
        this.y = context;
        this.j = intent;
        if (cl.d()) {
            try {
                cr.a(this.y, cl.b());
            } catch (Throwable th) {
            }
        }
        try {
            if (Looper.myLooper() == null) {
                this.b = new c(this, this.y.getMainLooper());
            } else {
                this.b = new c(this);
            }
        } catch (Throwable th2) {
            cl.a(th2, "AmapLocationManager", "init 1");
        }
        try {
            this.g = new cz(this.y);
        } catch (Throwable th22) {
            cl.a(th22, "AmapLocationManager", "init 5");
        }
        this.l = new b("amapLocManagerThread", this);
        this.l.setPriority(5);
        this.l.start();
        this.v = a(this.l.getLooper());
        try {
            this.c = new cx(this.y, this.b);
        } catch (Throwable th222) {
            cl.a(th222, "AmapLocationManager", "init 3");
        }
        if (this.p == null) {
            this.p = new cq();
        }
    }

    private AMapLocationServer a(bq bqVar) {
        if (this.a.isLocationCacheEnable()) {
            try {
                return bqVar.j();
            } catch (Throwable th) {
                cl.a(th, "AmapLocationManager", "doFirstCacheLoc");
            }
        }
        return null;
    }

    private a a(Looper looper) {
        a aVar;
        synchronized (this.o) {
            this.v = new a(this, looper);
            aVar = this.v;
        }
        return aVar;
    }

    private void a(int i) {
        synchronized (this.o) {
            if (this.v != null) {
                this.v.removeMessages(i);
            }
        }
    }

    private void a(int i, Bundle bundle) {
        if (bundle == null) {
            try {
                bundle = new Bundle();
            } catch (Throwable th) {
                Throwable th2;
                th2 = th;
                boolean z = (th2 instanceof IllegalStateException) && th2.getMessage().contains("sending message to a Handler on a dead thread");
                if ((th2 instanceof RemoteException) || z) {
                    this.h = null;
                    this.z = false;
                }
                cl.a(th2, "AmapLocationManager", "sendLocMessage");
                return;
            }
        }
        if (TextUtils.isEmpty(this.s)) {
            this.s = cl.b(this.y);
        }
        bundle.putString("c", this.s);
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.setData(bundle);
        obtain.replyTo = this.i;
        if (this.h != null) {
            this.h.send(obtain);
        }
    }

    private void a(int i, Object obj, long j) {
        synchronized (this.o) {
            if (this.v != null) {
                Message obtain = Message.obtain();
                obtain.what = i;
                if (obj instanceof Bundle) {
                    obtain.setData((Bundle) obj);
                } else {
                    obtain.obj = obj;
                }
                this.v.sendMessageDelayed(obtain, j);
            }
        }
    }

    private void a(Intent intent, boolean z) {
        if (this.y != null) {
            if (VERSION.SDK_INT < 26 || !z) {
                this.y.startService(intent);
            } else {
                try {
                    this.y.getClass().getMethod("startForegroundService", new Class[]{Intent.class}).invoke(this.y, new Object[]{intent});
                } catch (Throwable th) {
                    this.y.startService(intent);
                }
            }
            this.x = true;
        }
    }

    private void a(AMapLocation aMapLocation) {
        try {
            if (aMapLocation.getErrorCode() != 0) {
                aMapLocation.setLocationType(0);
            }
            if (aMapLocation.getErrorCode() == 0) {
                double latitude = aMapLocation.getLatitude();
                double longitude = aMapLocation.getLongitude();
                if ((latitude == Utils.DOUBLE_EPSILON && longitude == Utils.DOUBLE_EPSILON) || latitude < -90.0d || latitude > 90.0d || longitude < -180.0d || longitude > 180.0d) {
                    cq.a("errorLatLng", aMapLocation.toStr());
                    aMapLocation.setLocationType(0);
                    aMapLocation.setErrorCode(8);
                    aMapLocation.setLocationDetail("LatLng is error#0802");
                }
            }
            if ("GPS".equalsIgnoreCase(aMapLocation.getProvider()) || !this.c.b()) {
                aMapLocation.setAltitude(ct.b(aMapLocation.getAltitude()));
                aMapLocation.setBearing(ct.a(aMapLocation.getBearing()));
                aMapLocation.setSpeed(ct.a(aMapLocation.getSpeed()));
                Iterator it = this.d.iterator();
                while (it.hasNext()) {
                    try {
                        ((AMapLocationListener) it.next()).onLocationChanged(aMapLocation);
                    } catch (Throwable th) {
                    }
                }
            }
        } catch (Throwable th2) {
        }
    }

    private synchronized void a(AMapLocation aMapLocation, Throwable th, long j) {
        try {
            if (!cl.d() || aMapLocation != null) {
                if (aMapLocation == null) {
                    aMapLocation = new AMapLocation("");
                    aMapLocation.setErrorCode(8);
                    aMapLocation.setLocationDetail("amapLocation is null#0801");
                }
                if (!"GPS".equalsIgnoreCase(aMapLocation.getProvider())) {
                    aMapLocation.setProvider("lbs");
                }
                AMapLocationQualityReport aMapLocationQualityReport = new AMapLocationQualityReport();
                aMapLocationQualityReport.setLocationMode(this.a.getLocationMode());
                if (this.c != null) {
                    aMapLocationQualityReport.setGPSSatellites(this.c.e());
                    aMapLocationQualityReport.setGpsStatus(this.c.d());
                }
                aMapLocationQualityReport.setWifiAble(ct.g(this.y));
                aMapLocationQualityReport.setNetworkType(ct.h(this.y));
                if (aMapLocation.getLocationType() == 1 || "gps".equalsIgnoreCase(aMapLocation.getProvider())) {
                    j = 0;
                }
                aMapLocationQualityReport.setNetUseTime(j);
                aMapLocation.setLocationQualityReport(aMapLocationQualityReport);
                try {
                    if (this.A) {
                        String str = this.w;
                        Object bundle = new Bundle();
                        bundle.putParcelable("loc", aMapLocation);
                        bundle.putString("lastLocNb", str);
                        a((int) PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, bundle, 0);
                        cq.a(this.y, aMapLocation);
                        cq.b(this.y, aMapLocation);
                        a(aMapLocation.clone());
                    }
                } catch (Throwable th2) {
                    cl.a(th2, "AmapLocationManager", "handlerLocation part2");
                }
                if (!this.m || cl.d()) {
                    cr.b(this.y);
                    if (this.a.isOnceLocation()) {
                        e();
                    }
                }
            } else if (th != null) {
                cr.a(this.y, "loc", th.getMessage());
            } else {
                cr.a(this.y, "loc", "amaplocation is null");
            }
        } catch (Throwable th22) {
            cl.a(th22, "AmapLocationManager", "handlerLocation part3");
        }
    }

    private static void a(bq bqVar, AMapLocationServer aMapLocationServer) {
        if (aMapLocationServer != null) {
            try {
                if (aMapLocationServer.getErrorCode() == 0) {
                    bqVar.a(aMapLocationServer);
                }
            } catch (Throwable th) {
                cl.a(th, "AmapLocationManager", "apsLocation:doFirstAddCache");
            }
        }
    }

    static /* synthetic */ void a(ce ceVar) {
        Object obj = 1;
        Object obj2 = null;
        try {
            if (ceVar.y.checkCallingOrSelfPermission("android.permission.SYSTEM_ALERT_WINDOW") == 0) {
                obj2 = 1;
            } else if (ceVar.y instanceof Activity) {
                int i = 1;
                obj = null;
            } else {
                obj = null;
            }
            if (obj2 != null) {
                Builder builder = new Builder(ceVar.y);
                builder.setMessage(ck.f());
                if (!("".equals(ck.g()) || ck.g() == null)) {
                    builder.setPositiveButton(ck.g(), new OnClickListener(ceVar) {
                        final /* synthetic */ ce a;

                        {
                            this.a = r1;
                        }

                        public final void onClick(DialogInterface dialogInterface, int i) {
                            this.a.c();
                            dialogInterface.cancel();
                        }
                    });
                }
                builder.setNegativeButton(ck.h(), new OnClickListener(ceVar) {
                    final /* synthetic */ ce a;

                    {
                        this.a = r1;
                    }

                    public final void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog create = builder.create();
                if (obj != null) {
                    create.getWindow().setType(2003);
                }
                create.setCanceledOnTouchOutside(false);
                create.show();
                return;
            }
            ceVar.c();
        } catch (Throwable th) {
            ceVar.c();
            cl.a(th, "AmapLocationManager", "showDialog");
        }
    }

    static /* synthetic */ void a(ce ceVar, Bundle bundle) {
        Throwable th;
        long j;
        AMapLocation aMapLocation = null;
        AMapLocation aMapLocation2;
        if (bundle != null) {
            try {
                bundle.setClassLoader(AMapLocation.class.getClassLoader());
                aMapLocation2 = (AMapLocation) bundle.getParcelable("loc");
                ceVar.w = bundle.getString("nb");
                long j2 = bundle.getLong("netUseTime", 0);
                if (!(aMapLocation2 == null || aMapLocation2.getErrorCode() != 0 || ceVar.c == null)) {
                    ceVar.c.c();
                    if (!TextUtils.isEmpty(aMapLocation2.getAdCode())) {
                        ceVar.c.A = aMapLocation2;
                    }
                }
                long j3 = j2;
            } catch (Throwable th2) {
                th = th2;
                j = 0;
                cl.a(th, "AmapLocationManager", "resultLbsLocationSuccess");
                ceVar.a(aMapLocation, th, j);
            }
        }
        j3 = 0;
        aMapLocation2 = null;
        try {
            if (ceVar.c != null) {
                aMapLocation2 = ceVar.c.a(aMapLocation2, ceVar.w);
            }
            long j4 = j3;
            th = null;
            aMapLocation = aMapLocation2;
            j = j4;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            j = j3;
            th = th4;
            cl.a(th, "AmapLocationManager", "resultLbsLocationSuccess");
            ceVar.a(aMapLocation, th, j);
        }
        ceVar.a(aMapLocation, th, j);
    }

    static /* synthetic */ void a(ce ceVar, Message message) {
        try {
            AMapLocation aMapLocation = (AMapLocation) message.obj;
            if (ceVar.f && ceVar.h != null) {
                Bundle bundle = new Bundle();
                bundle.putBundle("optBundle", cl.a(ceVar.a));
                ceVar.a(0, bundle);
                ceVar.f = false;
            }
            ceVar.a(aMapLocation, null, 0);
            if (ceVar.C) {
                ceVar.a(7, null);
            }
            ceVar.a(1025);
            ceVar.a(1025, null, (long) BeaconManager.DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD);
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "resultGpsLocationSuccess");
        }
    }

    static /* synthetic */ void a(ce ceVar, AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener == null) {
            throw new IllegalArgumentException("listener参数不能为null");
        }
        if (ceVar.d == null) {
            ceVar.d = new ArrayList();
        }
        if (!ceVar.d.contains(aMapLocationListener)) {
            ceVar.d.add(aMapLocationListener);
        }
    }

    private AMapLocationServer b(bq bqVar) {
        cp cpVar;
        String apikey;
        AMapLocationServer aMapLocationServer;
        Throwable th;
        boolean z;
        Parcelable a;
        Bundle bundle;
        String str = null;
        boolean z2 = false;
        try {
            cpVar = new cp();
            cpVar.a(ct.b());
            apikey = AMapLocationClientOption.getAPIKEY();
            if (!TextUtils.isEmpty(apikey)) {
                dc.a(this.y, apikey);
            }
        } catch (Throwable th2) {
            Throwable th3 = th2;
            aMapLocationServer = null;
            th = th3;
            try {
                cl.a(th, "AmapLocationManager", "apsLocation");
                if (bqVar != null) {
                    try {
                        bqVar.f();
                    } catch (Throwable th4) {
                    }
                }
                return aMapLocationServer;
            } catch (Throwable th5) {
            }
        }
        try {
            apikey = UmidtokenInfo.getUmidtoken();
            if (!TextUtils.isEmpty(apikey)) {
                df.a(apikey);
            }
        } catch (Throwable th22) {
            cl.a(th22, "AmapLocationManager", "apsLocation setUmidToken");
        }
        try {
            bqVar.a(this.y);
            bqVar.a(this.a);
            Context context = this.y;
            bqVar.i();
        } catch (Throwable th222) {
            cl.a(th222, "AmapLocationManager", "initApsBase");
        }
        long j = 0;
        boolean B = ck.B();
        AMapLocationServer a2 = a(bqVar);
        if (a2 == null) {
            if (!B) {
                z2 = true;
            }
            try {
                a2 = bqVar.a(z2);
                if (a2 != null) {
                    j = a2.k();
                }
                if (!B) {
                    a(bqVar, a2);
                }
                aMapLocationServer = a2;
                z = true;
            } catch (Throwable th6) {
                th = th6;
                aMapLocationServer = a2;
                cl.a(th, "AmapLocationManager", "apsLocation");
                if (bqVar != null) {
                    bqVar.f();
                }
                return aMapLocationServer;
            }
        }
        aMapLocationServer = a2;
        z = false;
        try {
            cpVar.b(ct.b());
            cpVar.a(aMapLocationServer);
            if (aMapLocationServer != null) {
                str = aMapLocationServer.l();
            }
            if (this.a.isLocationCacheEnable() && this.g != null) {
                a = this.g.a(aMapLocationServer, str, this.a.getLastLocationLifeCycle());
                bundle = new Bundle();
                if (aMapLocationServer != null) {
                    bundle.putParcelable("loc", a);
                    bundle.putString("nb", aMapLocationServer.l());
                    bundle.putLong("netUseTime", j);
                }
                Message obtain = Message.obtain();
                obtain.setData(bundle);
                obtain.what = 1;
                this.b.sendMessage(obtain);
                cq.a(this.y, cpVar);
                if (z && B) {
                    bqVar.c();
                    a(bqVar, bqVar.a(true));
                }
                if (bqVar != null) {
                    try {
                        bqVar.f();
                    } catch (Throwable th7) {
                    }
                }
                return aMapLocationServer;
            }
        } catch (Throwable th8) {
            th = th8;
            cl.a(th, "AmapLocationManager", "apsLocation");
            if (bqVar != null) {
                bqVar.f();
            }
            return aMapLocationServer;
        }
        Object obj = aMapLocationServer;
        try {
            bundle = new Bundle();
            if (aMapLocationServer != null) {
                bundle.putParcelable("loc", a);
                bundle.putString("nb", aMapLocationServer.l());
                bundle.putLong("netUseTime", j);
            }
            Message obtain2 = Message.obtain();
            obtain2.setData(bundle);
            obtain2.what = 1;
            this.b.sendMessage(obtain2);
        } catch (Throwable th9) {
            cl.a(th9, "AmapLocationManager", "apsLocation:callback");
        }
        cq.a(this.y, cpVar);
        try {
            bqVar.c();
            a(bqVar, bqVar.a(true));
        } catch (Throwable th92) {
            cl.a(th92, "AmapLocationManager", "apsLocation:doFirstNetLocate 2");
        }
        if (bqVar != null) {
            bqVar.f();
        }
        return aMapLocationServer;
    }

    static /* synthetic */ void b(ce ceVar, Message message) {
        try {
            Bundle data = message.getData();
            AMapLocation aMapLocation = (AMapLocation) data.getParcelable("loc");
            String string = data.getString("lastLocNb");
            if (aMapLocation != null) {
                AMapLocation aMapLocation2 = null;
                try {
                    if (cz.b != null) {
                        aMapLocation2 = cz.b.a();
                    } else if (ceVar.g != null) {
                        aMapLocation2 = ceVar.g.b();
                    }
                    cq.a(aMapLocation2, aMapLocation);
                } catch (Throwable th) {
                }
            }
            if (ceVar.g.a(aMapLocation, string)) {
                ceVar.g.d();
            }
        } catch (Throwable th2) {
            cl.a(th2, "AmapLocationManager", "doSaveLastLocation");
        }
    }

    static /* synthetic */ void b(ce ceVar, AMapLocationListener aMapLocationListener) {
        if (!ceVar.d.isEmpty() && ceVar.d.contains(aMapLocationListener)) {
            ceVar.d.remove(aMapLocationListener);
        }
        if (ceVar.d.isEmpty()) {
            ceVar.e();
        }
    }

    private boolean b() {
        boolean z = true;
        int i = 0;
        do {
            try {
                if (this.h != null) {
                    break;
                }
                Thread.sleep(100);
                i++;
            } catch (Throwable th) {
                cl.a(th, "AmapLocationManager", "checkAPSManager");
                z = false;
            }
        } while (i < 50);
        if (this.h == null) {
            Message obtain = Message.obtain();
            Bundle bundle = new Bundle();
            Parcelable aMapLocation = new AMapLocation("");
            aMapLocation.setErrorCode(10);
            aMapLocation.setLocationDetail("请检查配置文件是否配置服务，并且manifest中service标签是否配置在application标签内#1001");
            bundle.putParcelable("loc", aMapLocation);
            obtain.setData(bundle);
            obtain.what = 1;
            this.b.sendMessage(obtain);
            z = false;
        }
        if (!z) {
            cq.a(null, 2101);
        }
        return z;
    }

    private void c() {
        Intent intent;
        try {
            intent = new Intent();
            intent.setComponent(new ComponentName("com.autonavi.minimap", ck.k()));
            intent.setFlags(AMapEngineUtils.MAX_P20_WIDTH);
            intent.setData(Uri.parse(ck.i()));
            this.y.startActivity(intent);
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "callAMap part2");
        }
    }

    static /* synthetic */ void c(ce ceVar, Message message) {
        if (message != null) {
            try {
                Bundle data = message.getData();
                if (data != null) {
                    int i = data.getInt("i", 0);
                    Notification notification = (Notification) data.getParcelable(IXAdRequestInfo.HEIGHT);
                    Intent h = ceVar.h();
                    h.putExtra("i", i);
                    h.putExtra(IXAdRequestInfo.HEIGHT, notification);
                    h.putExtra(IXAdRequestInfo.GPS, 1);
                    ceVar.a(h, true);
                }
            } catch (Throwable th) {
                cl.a(th, "AmapLocationManager", "doEnableBackgroundLocation");
            }
        }
    }

    private synchronized void d() {
        long j = 0;
        synchronized (this) {
            if (this.a == null) {
                this.a = new AMapLocationClientOption();
            }
            if (!this.A) {
                this.A = true;
                switch (this.a.getLocationMode()) {
                    case Battery_Saving:
                        a((int) PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW, null, 0);
                        a((int) PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, null, 0);
                        break;
                    case Device_Sensors:
                        a((int) PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW);
                        a((int) PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, null, 0);
                        break;
                    case Hight_Accuracy:
                        a((int) PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, null, 0);
                        if (this.a.isGpsFirst() && this.a.isOnceLocation()) {
                            j = StatisticConfig.MIN_UPLOAD_INTERVAL;
                        }
                        a((int) PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, null, j);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    static /* synthetic */ void d(ce ceVar, Message message) {
        if (message != null) {
            try {
                Bundle data = message.getData();
                if (data != null) {
                    boolean z = data.getBoolean("j", true);
                    Intent h = ceVar.h();
                    h.putExtra("j", z);
                    h.putExtra(IXAdRequestInfo.GPS, 2);
                    ceVar.a(h, false);
                }
            } catch (Throwable th) {
                cl.a(th, "AmapLocationManager", "doDisableBackgroundLocation");
            }
        }
    }

    private void e() {
        try {
            a(1025);
            if (this.c != null) {
                this.c.a();
            }
            a((int) PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW);
            this.A = false;
            this.k = 0;
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "stopLocation");
        }
    }

    private void f() {
        long j = 1000;
        if (this.a.getLocationMode() != AMapLocationMode.Device_Sensors) {
            if (this.a.getInterval() >= 1000) {
                j = this.a.getInterval();
            }
            a((int) PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, null, j);
        }
    }

    static /* synthetic */ void f(ce ceVar) {
        try {
            if (ceVar.B) {
                ceVar.B = false;
                AMapLocationServer b = ceVar.b(new bq());
                if (ceVar.b()) {
                    Bundle bundle = new Bundle();
                    String str = "0";
                    if (b != null && (b.getLocationType() == 2 || b.getLocationType() == 4)) {
                        str = "1";
                    }
                    bundle.putBundle("optBundle", cl.a(ceVar.a));
                    bundle.putString("isCacheLoc", str);
                    ceVar.a(0, bundle);
                }
            } else {
                try {
                    if (!(!ceVar.q || ceVar.isStarted() || ceVar.u)) {
                        ceVar.u = true;
                        ceVar.g();
                    }
                } catch (Throwable th) {
                    ceVar.u = true;
                    cl.a(th, "AmapLocationManager", "doLBSLocation reStartService");
                }
                if (ceVar.b()) {
                    ceVar.u = false;
                    Bundle bundle2 = new Bundle();
                    bundle2.putBundle("optBundle", cl.a(ceVar.a));
                    bundle2.putString("d", UmidtokenInfo.getUmidtoken());
                    if (!ceVar.c.b()) {
                        ceVar.a(1, bundle2);
                    }
                }
            }
            try {
                if (!ceVar.a.isOnceLocation()) {
                    ceVar.f();
                }
            } catch (Throwable th2) {
            }
        } catch (Throwable th3) {
        }
    }

    private void g() {
        try {
            if (this.i == null) {
                this.i = new Messenger(this.b);
            }
            this.y.bindService(h(), this.E, 1);
        } catch (Throwable th) {
        }
    }

    private Intent h() {
        if (this.j == null) {
            this.j = new Intent(this.y, APSService.class);
        }
        String str = "";
        try {
            str = !TextUtils.isEmpty(AMapLocationClientOption.getAPIKEY()) ? AMapLocationClientOption.getAPIKEY() : db.f(this.y);
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "startServiceImpl p2");
        }
        this.j.putExtra("a", str);
        this.j.putExtra("b", db.c(this.y));
        this.j.putExtra("d", UmidtokenInfo.getUmidtoken());
        this.j.putExtra("f", AMapLocationClientOption.isDownloadCoordinateConvertLibrary());
        return this.j;
    }

    static /* synthetic */ void h(ce ceVar) {
        ceVar.c.b(ceVar.a);
        if (ceVar.A && !ceVar.a.getLocationMode().equals(ceVar.n)) {
            ceVar.e();
            ceVar.d();
        }
        ceVar.n = ceVar.a.getLocationMode();
        if (ceVar.p != null) {
            if (ceVar.a.isOnceLocation()) {
                ceVar.p.a(ceVar.y, 0);
            } else {
                ceVar.p.a(ceVar.y, 1);
            }
            ceVar.p.a(ceVar.y, ceVar.a);
        }
    }

    static /* synthetic */ void i(ce ceVar) {
        try {
            if (ceVar.h != null) {
                ceVar.k = 0;
                Bundle bundle = new Bundle();
                bundle.putBundle("optBundle", cl.a(ceVar.a));
                ceVar.a(2, bundle);
                return;
            }
            ceVar.k++;
            if (ceVar.k < 10) {
                ceVar.a((int) PointerIconCompat.TYPE_TEXT, null, 50);
            }
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "startAssistantLocationImpl");
        }
    }

    static /* synthetic */ void j(ce ceVar) {
        try {
            Bundle bundle = new Bundle();
            bundle.putBundle("optBundle", cl.a(ceVar.a));
            ceVar.a(3, bundle);
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "stopAssistantLocationImpl");
        }
    }

    final void a() {
        a(12, null);
        this.B = true;
        this.f = true;
        this.z = false;
        this.q = false;
        e();
        if (this.p != null) {
            this.p.b(this.y);
        }
        cq.a(this.y);
        if (this.r != null) {
            this.r.b().sendEmptyMessage(11);
        } else if (this.E != null) {
            this.y.unbindService(this.E);
        }
        try {
            if (this.x) {
                this.y.stopService(h());
            }
        } catch (Throwable th) {
        }
        this.x = false;
        if (this.d != null) {
            this.d.clear();
            this.d = null;
        }
        this.E = null;
        synchronized (this.o) {
            if (this.v != null) {
                this.v.removeCallbacksAndMessages(null);
            }
            this.v = null;
        }
        if (this.l != null) {
            if (VERSION.SDK_INT >= 18) {
                try {
                    co.a(this.l, HandlerThread.class, "quitSafely", new Object[0]);
                } catch (Throwable th2) {
                    this.l.quit();
                }
            } else {
                this.l.quit();
            }
        }
        this.l = null;
        if (this.b != null) {
            this.b.removeCallbacksAndMessages(null);
        }
        if (this.g != null) {
            this.g.c();
            this.g = null;
        }
    }

    public void disableBackgroundLocation(boolean z) {
        try {
            Object bundle = new Bundle();
            bundle.putBoolean("j", z);
            a(1024, bundle, 0);
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "disableBackgroundLocation");
        }
    }

    public void enableBackgroundLocation(int i, Notification notification) {
        if (i != 0 && notification != null) {
            try {
                Object bundle = new Bundle();
                bundle.putInt("i", i);
                bundle.putParcelable(IXAdRequestInfo.HEIGHT, notification);
                a(1023, bundle, 0);
            } catch (Throwable th) {
                cl.a(th, "AmapLocationManager", "disableBackgroundLocation");
            }
        }
    }

    public AMapLocation getLastKnownLocation() {
        AMapLocation aMapLocation = null;
        try {
            if (this.g != null) {
                aMapLocation = this.g.b();
            }
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "getLastKnownLocation");
        }
        return aMapLocation;
    }

    public boolean isStarted() {
        return this.z;
    }

    public void onDestroy() {
        try {
            if (this.D != null) {
                this.D.b();
                this.D = null;
            }
            a(1011, null, 0);
            this.m = true;
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "onDestroy");
        }
    }

    public void setLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            a(1002, (Object) aMapLocationListener, 0);
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "setLocationListener");
        }
    }

    public void setLocationOption(AMapLocationClientOption aMapLocationClientOption) {
        try {
            a((int) PointerIconCompat.TYPE_ZOOM_IN, aMapLocationClientOption.clone(), 0);
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "setLocationOption");
        }
    }

    public void startAssistantLocation() {
        try {
            a((int) PointerIconCompat.TYPE_TEXT, null, 0);
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "startAssistantLocation");
        }
    }

    public void startAssistantLocation(WebView webView) {
        if (this.D == null) {
            this.D = new cy(this.y, webView);
        }
        this.D.a();
    }

    public void startLocation() {
        try {
            a((int) PointerIconCompat.TYPE_HELP, null, 0);
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "startLocation");
        }
    }

    public void stopAssistantLocation() {
        try {
            if (this.D != null) {
                this.D.b();
                this.D = null;
            }
            a((int) PointerIconCompat.TYPE_VERTICAL_TEXT, null, 0);
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "stopAssistantLocation");
        }
    }

    public void stopLocation() {
        try {
            a(1004, null, 0);
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "stopLocation");
        }
    }

    public void unRegisterLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            a(1005, (Object) aMapLocationListener, 0);
        } catch (Throwable th) {
            cl.a(th, "AmapLocationManager", "unRegisterLocationListener");
        }
    }
}
