package com.loc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationClientOption.GeoLanguage;
import com.amap.api.location.DPoint;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.altbeacon.beacon.BeaconManager;
import org.json.JSONObject;

/* compiled from: GpsLocation */
public final class cx {
    static AMapLocation j = null;
    static long k = 0;
    static Object l = new Object();
    static long q = 0;
    static boolean t = false;
    static boolean u = false;
    public AMapLocation A = null;
    private Context B;
    private long C = 0;
    private int D = 0;
    private Listener E = new Listener(this) {
        final /* synthetic */ cx a;

        {
            this.a = r1;
        }

        public final void onGpsStatusChanged(int i) {
            int i2 = 0;
            try {
                if (this.a.b != null) {
                    this.a.z = this.a.b.getGpsStatus(this.a.z);
                    switch (i) {
                        case 1:
                        case 3:
                            return;
                        case 2:
                            this.a.y = 0;
                            return;
                        case 4:
                            if (this.a.z != null) {
                                Iterable satellites = this.a.z.getSatellites();
                                if (satellites != null) {
                                    Iterator it = satellites.iterator();
                                    int maxSatellites = this.a.z.getMaxSatellites();
                                    while (it.hasNext() && i2 < maxSatellites) {
                                        i2 = ((GpsSatellite) it.next()).usedInFix() ? i2 + 1 : i2;
                                    }
                                }
                            }
                            this.a.y = i2;
                            return;
                        default:
                            return;
                    }
                    cl.a(th, "GpsLocation", "onGpsStatusChanged");
                }
            } catch (Throwable th) {
                cl.a(th, "GpsLocation", "onGpsStatusChanged");
            }
        }
    };
    private String F = null;
    private boolean G = false;
    private int H = 0;
    private boolean I = false;
    Handler a;
    LocationManager b;
    AMapLocationClientOption c;
    long d = 0;
    boolean e = false;
    bs f = null;
    int g = GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN;
    int h = 80;
    AMapLocation i = null;
    long m = 0;
    float n = 0.0f;
    Object o = new Object();
    Object p = new Object();
    GeoLanguage r = GeoLanguage.DEFAULT;
    boolean s = true;
    long v = 0;
    int w = 0;
    LocationListener x = new LocationListener(this) {
        final /* synthetic */ cx a;

        {
            this.a = r1;
        }

        public final void onLocationChanged(Location location) {
            if (this.a.a != null) {
                this.a.a.removeMessages(8);
            }
            if (location != null) {
                try {
                    AMapLocation aMapLocation = new AMapLocation(location);
                    if (ct.a(aMapLocation)) {
                        aMapLocation.setLocationType(1);
                        if (!this.a.e && ct.a(aMapLocation)) {
                            cq.a(this.a.B, ct.b() - this.a.C, cl.a(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                            this.a.e = true;
                        }
                        if (ct.a(location, this.a.y)) {
                            aMapLocation.setMock(true);
                            if (!this.a.c.isMockEnable()) {
                                if (this.a.w > 3) {
                                    cq.a(null, 2152);
                                    aMapLocation.setErrorCode(15);
                                    aMapLocation.setLocationDetail("GpsLocation has been mocked!#1501");
                                    aMapLocation.setLatitude(Utils.DOUBLE_EPSILON);
                                    aMapLocation.setLongitude(Utils.DOUBLE_EPSILON);
                                    aMapLocation.setAltitude(Utils.DOUBLE_EPSILON);
                                    aMapLocation.setSpeed(0.0f);
                                    aMapLocation.setAccuracy(0.0f);
                                    aMapLocation.setBearing(0.0f);
                                    aMapLocation.setExtras(null);
                                    this.a.b(aMapLocation);
                                    return;
                                }
                                cx cxVar = this.a;
                                cxVar.w++;
                                return;
                            }
                        }
                        this.a.w = 0;
                        aMapLocation.setSatellites(this.a.y);
                        cx.b(this.a, aMapLocation);
                        cx.c(this.a, aMapLocation);
                        AMapLocation d = cx.d(this.a, aMapLocation);
                        cx.e(this.a, d);
                        this.a.a(d);
                        synchronized (this.a.o) {
                            cx.a(this.a, d, this.a.A);
                        }
                        try {
                            if (ct.a(d)) {
                                if (this.a.i != null) {
                                    this.a.m = location.getTime() - this.a.i.getTime();
                                    this.a.n = ct.a(this.a.i, d);
                                }
                                synchronized (this.a.p) {
                                    this.a.i = d.clone();
                                }
                                this.a.F = null;
                                this.a.G = false;
                                this.a.H = 0;
                            }
                        } catch (Throwable th) {
                            cl.a(th, "GpsLocation", "onLocationChangedLast");
                        }
                        this.a.b(d);
                    }
                } catch (Throwable th2) {
                    cl.a(th2, "GpsLocation", "onLocationChanged");
                }
            }
        }

        public final void onProviderDisabled(String str) {
            try {
                if ("gps".equalsIgnoreCase(str)) {
                    this.a.d = 0;
                    this.a.y = 0;
                }
            } catch (Throwable th) {
            }
        }

        public final void onProviderEnabled(String str) {
        }

        public final void onStatusChanged(String str, int i, Bundle bundle) {
            if (i == 0) {
                try {
                    this.a.d = 0;
                    this.a.y = 0;
                } catch (Throwable th) {
                }
            }
        }
    };
    int y = 0;
    GpsStatus z = null;

    public cx(Context context, Handler handler) {
        this.B = context;
        this.a = handler;
        try {
            this.b = (LocationManager) this.B.getSystemService("location");
        } catch (Throwable th) {
            cl.a(th, "GpsLocation", "<init>");
        }
        this.f = new bs();
    }

    private void a(int i, int i2, String str, long j) {
        try {
            if (this.a != null && this.c.getLocationMode() == AMapLocationMode.Device_Sensors) {
                Message obtain = Message.obtain();
                AMapLocation aMapLocation = new AMapLocation("");
                aMapLocation.setProvider("gps");
                aMapLocation.setErrorCode(i2);
                aMapLocation.setLocationDetail(str);
                aMapLocation.setLocationType(1);
                obtain.obj = aMapLocation;
                obtain.what = i;
                this.a.sendMessageDelayed(obtain, j);
            }
        } catch (Throwable th) {
        }
    }

    static /* synthetic */ void a(cx cxVar, AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        if (aMapLocation2 != null && cxVar.c.isNeedAddress() && ct.a(aMapLocation, aMapLocation2) < ((float) cxVar.g)) {
            cl.a(aMapLocation, aMapLocation2);
        }
    }

    private static boolean a(LocationManager locationManager) {
        try {
            if (t) {
                return u;
            }
            List allProviders = locationManager.getAllProviders();
            if (allProviders == null || allProviders.size() <= 0) {
                u = false;
            } else {
                u = allProviders.contains("gps");
            }
            t = true;
            return u;
        } catch (Throwable th) {
            return u;
        }
    }

    private boolean a(String str) {
        boolean z = false;
        try {
            ArrayList d = ct.d(str);
            ArrayList d2 = ct.d(this.F);
            if (d != null && d.size() >= 8 && d2 != null && d2.size() >= 8) {
                z = ct.a(this.F, str);
            }
        } catch (Throwable th) {
        }
        return z;
    }

    private void b(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode() == 15 && !AMapLocationMode.Device_Sensors.equals(this.c.getLocationMode())) {
            return;
        }
        if (this.c.getLocationMode().equals(AMapLocationMode.Device_Sensors) && this.c.getDeviceModeDistanceFilter() > 0.0f) {
            c(aMapLocation);
        } else if (ct.b() - this.v >= this.c.getInterval() - 200) {
            this.v = ct.b();
            c(aMapLocation);
        }
    }

    static /* synthetic */ void b(cx cxVar, AMapLocation aMapLocation) {
        try {
            if (cl.a(aMapLocation.getLatitude(), aMapLocation.getLongitude()) && cxVar.c.isOffset()) {
                DPoint a = cm.a(cxVar.B, new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                aMapLocation.setLatitude(a.getLatitude());
                aMapLocation.setLongitude(a.getLongitude());
            }
        } catch (Throwable th) {
        }
    }

    private void c(AMapLocation aMapLocation) {
        if (this.a != null) {
            Message obtain = Message.obtain();
            obtain.obj = aMapLocation;
            obtain.what = 2;
            this.a.sendMessage(obtain);
        }
    }

    static /* synthetic */ void c(cx cxVar, AMapLocation aMapLocation) {
        try {
            if (cxVar.y >= 4) {
                aMapLocation.setGpsAccuracyStatus(1);
            } else if (cxVar.y == 0) {
                aMapLocation.setGpsAccuracyStatus(-1);
            } else {
                aMapLocation.setGpsAccuracyStatus(0);
            }
        } catch (Throwable th) {
        }
    }

    static /* synthetic */ AMapLocation d(cx cxVar, AMapLocation aMapLocation) {
        if (!ct.a(aMapLocation) || cxVar.D < 3) {
            return aMapLocation;
        }
        if (aMapLocation.getAccuracy() < 0.0f || aMapLocation.getAccuracy() == Float.MAX_VALUE) {
            aMapLocation.setAccuracy(0.0f);
        }
        if (aMapLocation.getSpeed() < 0.0f || aMapLocation.getSpeed() == Float.MAX_VALUE) {
            aMapLocation.setSpeed(0.0f);
        }
        return cxVar.f.a(aMapLocation);
    }

    static /* synthetic */ void e(cx cxVar, AMapLocation aMapLocation) {
        if (ct.a(aMapLocation)) {
            cxVar.d = ct.b();
            synchronized (l) {
                k = ct.b();
                j = aMapLocation.clone();
            }
            cxVar.D++;
        }
    }

    private static boolean g() {
        try {
            return ((Boolean) co.a(dl.c("KY29tLmFtYXAuYXBpLm5hdmkuQU1hcE5hdmk="), dl.c("UaXNOYXZpU3RhcnRlZA=="), null, null)).booleanValue();
        } catch (Throwable th) {
            return false;
        }
    }

    private AMapLocation h() {
        float f = 0.0f;
        if (!ct.a(this.i)) {
            return null;
        }
        if (!ck.z()) {
            return null;
        }
        if (g()) {
            JSONObject jSONObject = new JSONObject((String) co.a(dl.c("KY29tLmFtYXAuYXBpLm5hdmkuQU1hcE5hdmk="), dl.c("UZ2V0TmF2aUxvY2F0aW9u"), null, null));
            long optLong = jSONObject.optLong("time");
            if (!this.I) {
                this.I = true;
                cq.a("useNaviLoc", "use NaviLoc");
            }
            if (ct.a() - optLong <= 5500) {
                float parseFloat;
                double optDouble = jSONObject.optDouble("lat", Utils.DOUBLE_EPSILON);
                double optDouble2 = jSONObject.optDouble("lng", Utils.DOUBLE_EPSILON);
                try {
                    parseFloat = Float.parseFloat(jSONObject.optString("accuracy", "0"));
                } catch (NumberFormatException e) {
                    parseFloat = f;
                }
                try {
                    float parseFloat2;
                    float parseFloat3;
                    double optDouble3 = jSONObject.optDouble("altitude", Utils.DOUBLE_EPSILON);
                    try {
                        parseFloat2 = Float.parseFloat(jSONObject.optString("bearing", "0"));
                    } catch (NumberFormatException e2) {
                        parseFloat2 = f;
                    }
                    try {
                        parseFloat3 = Float.parseFloat(jSONObject.optString("speed", "0"));
                    } catch (NumberFormatException e3) {
                        parseFloat3 = f;
                    }
                    AMapLocation aMapLocation = new AMapLocation("lbs");
                    aMapLocation.setLocationType(9);
                    aMapLocation.setLatitude(optDouble);
                    aMapLocation.setLongitude(optDouble2);
                    aMapLocation.setAccuracy(parseFloat);
                    aMapLocation.setAltitude(optDouble3);
                    aMapLocation.setBearing(parseFloat2);
                    aMapLocation.setSpeed(parseFloat3);
                    aMapLocation.setTime(optLong);
                    if (ct.a(aMapLocation, this.i) > 300.0f) {
                        return null;
                    }
                    synchronized (this.p) {
                        this.i.setLongitude(optDouble2);
                        this.i.setLatitude(optDouble);
                        this.i.setAccuracy(parseFloat);
                        this.i.setBearing(parseFloat2);
                        this.i.setSpeed(parseFloat3);
                        this.i.setTime(optLong);
                    }
                    return aMapLocation;
                } catch (Throwable th) {
                }
            }
        }
        return null;
    }

    public final AMapLocation a(AMapLocation aMapLocation, String str) {
        long j = StatisticConfig.MIN_UPLOAD_INTERVAL;
        if (this.i == null) {
            return aMapLocation;
        }
        if ((!this.c.isMockEnable() && this.i.isMock()) || !ct.a(this.i)) {
            return aMapLocation;
        }
        AMapLocation h = h();
        if (h != null && ct.a(h)) {
            return h;
        }
        float speed = this.i.getSpeed();
        if (speed == 0.0f && this.m > 0 && this.m < 8 && this.n > 0.0f) {
            speed = this.n / ((float) this.m);
        }
        if (aMapLocation != null && ct.a(aMapLocation)) {
            if (aMapLocation.getAccuracy() < 200.0f) {
                this.H++;
                if (this.F == null && this.H >= 2) {
                    this.G = true;
                }
                j = speed > 5.0f ? 10000 : 15000;
            } else {
                if (!TextUtils.isEmpty(this.F)) {
                    this.G = false;
                    this.H = 0;
                }
                j = speed > 5.0f ? 20000 : StatisticConfig.MIN_UPLOAD_INTERVAL;
            }
        }
        if (ct.b() - this.d < j) {
            if (this.F == null && this.H >= 2) {
                this.F = str;
            }
            return this.i.clone();
        } else if (this.G && a(str)) {
            return this.i.clone();
        } else {
            this.F = null;
            this.H = 0;
            synchronized (this.p) {
                this.i = null;
            }
            this.m = 0;
            this.n = 0.0f;
            return aMapLocation;
        }
    }

    public final void a() {
        if (this.b != null) {
            try {
                if (this.x != null) {
                    this.b.removeUpdates(this.x);
                }
            } catch (Throwable th) {
            }
            try {
                if (this.E != null) {
                    this.b.removeGpsStatusListener(this.E);
                }
            } catch (Throwable th2) {
            }
            try {
                if (this.a != null) {
                    this.a.removeMessages(8);
                }
            } catch (Throwable th3) {
            }
            this.y = 0;
            this.C = 0;
            this.v = 0;
            this.d = 0;
            this.D = 0;
            this.w = 0;
            this.f.a();
            this.i = null;
            this.m = 0;
            this.n = 0.0f;
            this.F = null;
            this.I = false;
        }
    }

    public final void a(Bundle bundle) {
        if (bundle != null) {
            try {
                bundle.setClassLoader(AMapLocation.class.getClassLoader());
                this.g = bundle.getInt("I_MAX_GEO_DIS");
                this.h = bundle.getInt("I_MIN_GEO_DIS");
                AMapLocation aMapLocation = (AMapLocation) bundle.getParcelable("loc");
                if (!TextUtils.isEmpty(aMapLocation.getAdCode())) {
                    synchronized (this.o) {
                        this.A = aMapLocation;
                    }
                }
            } catch (Throwable th) {
                cl.a(th, "GpsLocation", "setLastGeoLocation");
            }
        }
    }

    final void a(AMapLocation aMapLocation) {
        if (ct.a(aMapLocation) && this.a != null && this.c.isNeedAddress()) {
            long b = ct.b();
            if (this.c.getInterval() <= 8000 || b - this.v > this.c.getInterval() - 8000) {
                Bundle bundle = new Bundle();
                bundle.putDouble("lat", aMapLocation.getLatitude());
                bundle.putDouble("lon", aMapLocation.getLongitude());
                Message obtain = Message.obtain();
                obtain.setData(bundle);
                obtain.what = 5;
                synchronized (this.o) {
                    if (this.A == null) {
                        this.a.sendMessage(obtain);
                    } else if (ct.a(aMapLocation, this.A) > ((float) this.h)) {
                        this.a.sendMessage(obtain);
                    }
                }
            }
        }
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        this.c = aMapLocationClientOption;
        if (this.c == null) {
            this.c = new AMapLocationClientOption();
        }
        try {
            q = cs.b(this.B, "pref", "lagt", q);
        } catch (Throwable th) {
        }
        if (this.b != null) {
            try {
                if (ct.b() - k <= 5000 && ct.a(j) && (this.c.isMockEnable() || !j.isMock())) {
                    this.d = ct.b();
                    b(j);
                }
                this.s = true;
                Looper myLooper = Looper.myLooper();
                if (myLooper == null) {
                    myLooper = this.B.getMainLooper();
                }
                this.C = ct.b();
                if (a(this.b)) {
                    try {
                        if (ct.a() - q >= 259200000) {
                            this.b.sendExtraCommand("gps", "force_xtra_injection", null);
                            q = ct.a();
                            cs.a(this.B, "pref", "lagt", q);
                        }
                    } catch (SecurityException e) {
                        this.s = false;
                        cq.a(null, 2121);
                        a(2, 12, e.getMessage() + "#1201", 0);
                    } catch (Throwable th2) {
                    }
                    if (!this.c.getLocationMode().equals(AMapLocationMode.Device_Sensors) || this.c.getDeviceModeDistanceFilter() <= 0.0f) {
                        this.b.requestLocationUpdates("gps", 900, 0.0f, this.x, myLooper);
                    } else {
                        this.b.requestLocationUpdates("gps", this.c.getInterval(), this.c.getDeviceModeDistanceFilter(), this.x, myLooper);
                    }
                    this.b.addGpsStatusListener(this.E);
                    a(8, 14, "no enough satellites#1401", this.c.getHttpTimeOut());
                    return;
                }
                a(8, 14, "no gps provider#1402", 0);
            } catch (SecurityException e2) {
                this.s = false;
                cq.a(null, 2121);
                a(2, 12, e2.getMessage() + "#1201", 0);
            } catch (Throwable th3) {
                cl.a(th3, "GpsLocation", "requestLocationUpdates part2");
            }
        }
    }

    public final void b(AMapLocationClientOption aMapLocationClientOption) {
        if (aMapLocationClientOption == null) {
            aMapLocationClientOption = new AMapLocationClientOption();
        }
        this.c = aMapLocationClientOption;
        if (!(this.c.getLocationMode() == AMapLocationMode.Device_Sensors || this.a == null)) {
            this.a.removeMessages(8);
        }
        if (this.r != this.c.getGeoLanguage()) {
            synchronized (this.o) {
                this.A = null;
            }
        }
        this.r = this.c.getGeoLanguage();
    }

    public final boolean b() {
        return ct.b() - this.d <= 2800;
    }

    public final void c() {
        this.w = 0;
    }

    @SuppressLint({"NewApi"})
    public final int d() {
        if (this.b == null || !a(this.b)) {
            return 1;
        }
        if (VERSION.SDK_INT >= 19) {
            int i = Secure.getInt(this.B.getContentResolver(), "location_mode", 0);
            if (i == 0) {
                return 2;
            }
            if (i == 2) {
                return 3;
            }
        } else if (!this.b.isProviderEnabled("gps")) {
            return 2;
        }
        return !this.s ? 4 : 0;
    }

    public final int e() {
        return this.y;
    }

    public final boolean f() {
        return ct.b() - this.d > BeaconManager.DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD;
    }
}
