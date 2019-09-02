package com.example.gita.gxty.ram.service;

import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.CoordinateConverter;
import com.example.gita.gxty.MyApplication;
import com.example.gita.gxty.model.DataSign;
import com.example.gita.gxty.model.Ibeacon;
import com.example.gita.gxty.model.MyLatLng;
import com.example.gita.gxty.model.SignPoint;
import com.example.gita.gxty.ram.SignChangDiActivity;
import com.example.gita.gxty.utils.h;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

public class SignService extends BaseService implements BeaconConsumer, RangeNotifier {
    private static DataSign g;
    long e = 0;
    private Map<String, Ibeacon> f = new HashMap();
    private MyLatLng h;
    private boolean i = true;
    private boolean j = false;
    private long k = 0;

    public void onCreate() {
        super.onCreate();
        h.a((Object) "in sign onCreate");
    }

    public static void a(Activity activity, DataSign dataSign) {
        try {
            Intent intent = new Intent(activity, SignService.class);
            intent.putExtra("status", 0);
            intent.putExtra("data", dataSign);
            activity.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Activity activity, int i) {
        try {
            Intent intent = new Intent(activity, SignService.class);
            intent.putExtra("status", i);
            activity.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Activity activity) {
        try {
            activity.stopService(new Intent(activity, SignService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            int intExtra = intent.getIntExtra("status", 0);
            h.b("status : " + intExtra);
            if (intExtra == 0) {
                this.i = true;
                MyApplication.e().b();
                g = (DataSign) intent.getSerializableExtra("data");
                c();
            } else if (intExtra == 1) {
                this.i = false;
            } else if (intExtra == 2) {
                this.j = true;
            } else if (intExtra == 3) {
                this.j = false;
            }
        }
        return super.onStartCommand(intent, i, i2);
    }

    public void onDestroy() {
        h.b("签到服务已销毁！！！！");
        super.onDestroy();
    }

    public String k() {
        return "FDA50693-A4E2-4FB1-AFCF-C6EB07647822";
    }

    public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
        if (this.i) {
            h.b("初始化中，什么也不做");
        } else if (this.j) {
            h.b("暂停中，什么也不做");
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.e < 500) {
                h.b("无效扫码结果！");
                return;
            }
            this.e = currentTimeMillis;
            if (collection != null) {
                try {
                    if (!collection.isEmpty()) {
                        h.a("beacon------" + collection.size());
                        for (Beacon beacon : collection) {
                            Ibeacon createIBeacon = Ibeacon.createIBeacon(beacon);
                            this.f.put(createIBeacon.getMyKey(), createIBeacon);
                            h.b(beacon);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            if (currentTimeMillis2 - this.k > 60000) {
                SignPoint signPoint = new SignPoint();
                signPoint.time = currentTimeMillis2 + "";
                signPoint.latLng = this.h;
                signPoint.ibeacons = new HashSet();
                for (Ibeacon ibeacon : g.beacon) {
                    if (this.f.keySet().contains(ibeacon.getMyKey())) {
                        signPoint.ibeacons.add(ibeacon);
                    }
                }
                signPoint.inBeacon = !signPoint.ibeacons.isEmpty();
                MyApplication.e().d().add(signPoint);
                h.b("打点");
                h.b(signPoint);
                this.f.clear();
                if (this.k > 0) {
                    a(signPoint.inBeacon);
                }
                this.k = currentTimeMillis2;
            }
        }
    }

    public void a(AMapLocation aMapLocation) {
        if (this.j) {
            h.b("暂停中，什么也不做");
        } else if (aMapLocation != null) {
            try {
                h.b("------>收到定位回调");
                CoordinateConverter coordinateConverter = this.a;
                if (CoordinateConverter.isAMapDataAvailable(aMapLocation.getLatitude(), aMapLocation.getLongitude())) {
                    this.h = new MyLatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude(), (double) aMapLocation.getSpeed());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void a(boolean z) {
        Intent intent = new Intent("com.example.gita.gxty.action.refreshUI.Sign");
        intent.setPackage(getPackageName());
        intent.putExtra("data", z);
        sendBroadcast(intent);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public Class l() {
        return SignChangDiActivity.class;
    }

    public String m() {
        return "查看签到状态";
    }

    public int n() {
        return 4000;
    }

    public void j() {
        a("蓝牙已关闭！");
        a(false);
    }
}
