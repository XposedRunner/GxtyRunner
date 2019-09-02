package cn.jiguang.a.a.b;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import cn.jiguang.d.a.a;
import cn.jiguang.e.d;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

public final class c {
    private static final SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    private LocationManager a;
    private e b;
    private String c = "";
    private f d;
    private final LocationListener f = new d(this);

    public c(Context context, f fVar) {
        this.a = (LocationManager) context.getSystemService("location");
        this.d = fVar;
    }

    private void a(Location location, String str, boolean z) {
        if (location != null) {
            try {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                if (e.a(latitude, longitude)) {
                    long a = a.a(location.getTime());
                    d.c("GpsInfoManager", "update location from " + str + (z ? "(isLastKnow)" : "") + " at localTime=" + b(location.getTime()) + ",serverTime=" + b(1000 * a));
                    this.b = new e(latitude, longitude, location.getAltitude(), location.getBearing(), location.getAccuracy(), str, a, z);
                    if (this.b != null) {
                        JSONObject f = this.b.f();
                        if (f != null) {
                            cn.jiguang.d.a.d.b(f.toString());
                            return;
                        }
                        return;
                    }
                    return;
                }
                a("latitude(" + latitude + ") or longitude(" + longitude + ") is invalid");
                return;
            } catch (Exception e) {
                d.i("GpsInfoManager", "updateWithNewLocation excepted:" + e.getMessage());
                a("update exception" + e.getMessage());
                return;
            }
        }
        a("update location is null");
    }

    private void a(String str) {
        this.b = new e(str);
    }

    private static String b(long j) {
        return e.format(new Date(j));
    }

    private boolean c() {
        try {
            return this.a != null ? this.a.isProviderEnabled("gps") || this.a.isProviderEnabled("network") || this.a.isProviderEnabled("passive") : false;
        } catch (SecurityException e) {
            d.g("GpsInfoManager", "No suitable permission is present when get GPS_PROVIDER!");
            return false;
        } catch (IllegalArgumentException e2) {
            d.g("GpsInfoManager", "The provider [gps] is illegal argument!");
            return false;
        } catch (Exception e3) {
            d.g("GpsInfoManager", "The ILocationManager is null!");
            return false;
        }
    }

    private void d() {
        d.a("GpsInfoManager", "stop");
        e();
        if (this.d != null) {
            if (this.d.b != null) {
                if (this.d.b.hasMessages(1004)) {
                    this.d.b.removeMessages(1004);
                }
                if (this.d.b.hasMessages(PointerIconCompat.TYPE_HELP)) {
                    this.d.b.removeMessages(PointerIconCompat.TYPE_HELP);
                }
                if (this.d.b.hasMessages(1001)) {
                    this.d.b.removeMessages(1001);
                }
                if (this.d.b.hasMessages(1005)) {
                    this.d.b.removeMessages(1005);
                }
            }
            this.d.b();
            return;
        }
        d.h("GpsInfoManager", "cellLocationManager is null,please check it");
    }

    private void e() {
        try {
            if (this.f == null) {
                d.g("GpsInfoManager", "Location listener is null , do nothing!");
            } else if (this.a != null) {
                this.a.removeUpdates(this.f);
            } else {
                d.g("GpsInfoManager", "locationManager is null , do nothing!");
            }
        } catch (Throwable th) {
            d.g("GpsInfoManager", "remove location listener failed  e:" + th.getMessage());
        }
    }

    protected final e a() {
        return this.b;
    }

    protected final void a(Context context) {
        long j = 0;
        if (this.d.a) {
            a("skip gps collect");
            this.d.b();
        } else if (!cn.jiguang.g.a.a(context, "android.permission.ACCESS_FINE_LOCATION")) {
            d.g("GpsInfoManager", "Require the permissionandroid.permission.ACCESS_FINE_LOCATION");
            a("no permission");
            this.d.b();
        } else if (c()) {
            try {
                if (this.a != null) {
                    Location lastKnownLocation = this.a.getLastKnownLocation("gps");
                    Location lastKnownLocation2 = this.a.getLastKnownLocation("network");
                    Location lastKnownLocation3 = this.a.getLastKnownLocation("passive");
                    d.a("GpsInfoManager", "gpsLocation:" + lastKnownLocation);
                    d.a("GpsInfoManager", "netLocation:" + lastKnownLocation2);
                    d.a("GpsInfoManager", "passLocation:" + lastKnownLocation3);
                    long time = lastKnownLocation == null ? 0 : lastKnownLocation.getTime();
                    long time2 = lastKnownLocation2 == null ? 0 : lastKnownLocation2.getTime();
                    long time3 = lastKnownLocation3 == null ? 0 : lastKnownLocation3.getTime();
                    if (time > time2) {
                        lastKnownLocation2 = time > time3 ? lastKnownLocation : lastKnownLocation3;
                    } else if (time2 <= time3) {
                        lastKnownLocation2 = lastKnownLocation3;
                    }
                    a(lastKnownLocation2, lastKnownLocation2 != null ? lastKnownLocation2.getProvider() : "", true);
                    if (System.currentTimeMillis() - (lastKnownLocation2 != null ? lastKnownLocation2.getTime() : 0) < StatisticConfig.MIN_UPLOAD_INTERVAL) {
                        String str = "GpsInfoManager";
                        StringBuilder stringBuilder = new StringBuilder("need not restart gpslocation,the time with last:");
                        time3 = System.currentTimeMillis();
                        if (lastKnownLocation2 != null) {
                            j = lastKnownLocation2.getTime();
                        }
                        d.c(str, stringBuilder.append(time3 - j).toString());
                        d();
                        return;
                    } else if (this.a.isProviderEnabled("network")) {
                        this.c = "network";
                        this.d.b.sendEmptyMessage(PointerIconCompat.TYPE_HELP);
                        return;
                    } else if (this.a.isProviderEnabled("gps")) {
                        this.c = "gps";
                        this.d.b.sendEmptyMessage(PointerIconCompat.TYPE_HELP);
                        return;
                    } else {
                        this.c = "network";
                        this.d.b.sendEmptyMessage(1004);
                        return;
                    }
                }
                d.g("GpsInfoManager", "locationManager is null");
                d();
            } catch (SecurityException e) {
                d.g("GpsInfoManager", "No suitable permission when get last known location!");
                d();
            } catch (Exception e2) {
                d.g("GpsInfoManager", "The provider is illegal argument!");
                d();
            }
        } else {
            a("no enabled provider");
            this.d.b();
        }
    }

    protected final void a(Message message) {
        int i;
        switch (message.what) {
            case 1001:
                try {
                    if (this.c == null || !this.c.equals("network")) {
                        d.g("GpsInfoManager", "get " + this.c + " time out ");
                        d();
                        return;
                    }
                    d.g("GpsInfoManager", "get gps with network time out ");
                    this.c = "gps";
                    e();
                    this.a.requestLocationUpdates(this.c, 2000, 0.0f, this.f);
                    d.c("GpsInfoManager", "request " + this.c + " location");
                    this.d.b.sendEmptyMessageDelayed(1001, 10000);
                    return;
                } catch (Throwable th) {
                    d.g("GpsInfoManager", "when location time out " + th.getMessage());
                    d();
                    return;
                }
            case PointerIconCompat.TYPE_HELP /*1003*/:
                i = 1001;
                break;
            case 1004:
                d.c("GpsInfoManager", "LOAD_GPS_ACTION_REQUEST_ONLY_NETWORK");
                i = 1005;
                break;
            case 1005:
                d.g("GpsInfoManager", "get only network " + this.c + " time out ");
                d();
                return;
            default:
                return;
        }
        try {
            this.a.requestLocationUpdates(this.c, 2000, 0.0f, this.f);
            d.c("GpsInfoManager", "request " + this.c + " location");
            this.d.b.sendEmptyMessageDelayed(i, 20000);
        } catch (SecurityException e) {
            d.g("GpsInfoManager", "No suitable permission when get last known location!");
            d();
        } catch (Throwable th2) {
            d.g("GpsInfoManager", "The provider is illegal argument!");
            d();
        }
    }

    public final void b() {
        this.b = null;
    }
}
