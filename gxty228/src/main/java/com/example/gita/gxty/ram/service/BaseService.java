package com.example.gita.gxty.ram.service;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.CoordinateConverter;
import com.example.gita.gxty.R;
import com.example.gita.gxty.ram.lock.ScreenLockReceiver;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.n;
import com.example.gita.gxty.utils.s;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

public abstract class BaseService extends Service implements BeaconConsumer, RangeNotifier {
    private static WakeLock g;
    protected CoordinateConverter a;
    Handler b = new Handler(this) {
        final /* synthetic */ BaseService a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1800) {
                try {
                    s.a((String) message.obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    AMapLocationListener c = new AMapLocationListener(this) {
        final /* synthetic */ BaseService a;

        {
            this.a = r1;
        }

        public void onLocationChanged(final AMapLocation aMapLocation) {
            new Thread(this) {
                final /* synthetic */ AnonymousClass2 b;

                public void run() {
                    this.b.a.a(aMapLocation);
                }
            }.start();
        }
    };
    boolean d = false;
    private BroadcastReceiver e = new ScreenLockReceiver();
    private Region f = new Region(k(), null, null, null);
    private AMapLocationClient h;
    private PendingIntent i;
    private NotificationManager j;
    private MediaPlayer k = null;
    private BluetoothAdapter l;
    private BeaconManager m;
    private BroadcastReceiver n = new BroadcastReceiver(this) {
        final /* synthetic */ BaseService a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                if (intExtra == 10) {
                    h.b("蓝牙已关闭，关闭Beacnon 扫描功能");
                    this.a.j();
                    try {
                        this.a.m.removeAllRangeNotifiers();
                        this.a.m.unbind(this.a);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (intExtra == 12) {
                    h.b("蓝牙已打开，开启Beacnon 扫描功能");
                    this.a.r();
                } else if (intExtra == 11) {
                    h.b("蓝牙正在开启中，请稍后！！！");
                }
            }
        }
    };

    public abstract String k();

    public abstract Class l();

    public abstract String m();

    public abstract int n();

    public void onCreate() {
        super.onCreate();
        h.a((Object) "in onCreate");
        this.a = new CoordinateConverter(this);
        a(this, getClass().getName());
        p();
        b();
        try {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            registerReceiver(this.e, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        q();
        a();
        i();
        try {
            unregisterReceiver(this.e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            d();
            e();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            h().cancelAll();
        } catch (Exception e22) {
            e22.printStackTrace();
        }
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void a(String str) {
        this.b.sendMessage(this.b.obtainMessage(1800, str));
    }

    public static void a(Context context, String str) {
        try {
            if (g == null) {
                g = ((PowerManager) context.getSystemService("power")).newWakeLock(536870913, "WakeLock_" + str);
                if (g != null) {
                    g.acquire();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a() {
        try {
            if (g != null) {
                g.release();
                g = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b() {
        this.h = new AMapLocationClient(getApplicationContext());
        this.h.setLocationOption(b(1000));
        this.h.setLocationListener(this.c);
        this.h.enableBackgroundLocation(1, g());
    }

    private AMapLocationClientOption b(int i) {
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
        aMapLocationClientOption.setGpsFirst(false);
        aMapLocationClientOption.setInterval((long) i);
        aMapLocationClientOption.setLocationCacheEnable(false);
        return aMapLocationClientOption;
    }

    public void a(AMapLocation aMapLocation) {
    }

    public void c() {
        h.b("&&&&&&&&&start Location&&&&&&&&&&&& ");
        this.h.setLocationOption(b(n()));
        this.h.startLocation();
    }

    public void d() {
        h.b("&&&&&&&&&stop Location&&&&&&&&&&&& ");
        this.h.stopLocation();
    }

    public void e() {
        if (this.h != null) {
            this.h.disableBackgroundLocation(true);
            this.h.onDestroy();
            this.h = null;
        }
    }

    public void f() {
        try {
            startForeground(1, g());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Notification g() {
        Builder builder;
        if (VERSION.SDK_INT >= 26) {
            String packageName = getPackageName();
            if (!this.d) {
                NotificationChannel notificationChannel = new NotificationChannel(packageName, "BackgroundLocation", 3);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(-16776961);
                notificationChannel.setShowBadge(true);
                h().createNotificationChannel(notificationChannel);
                this.d = true;
            }
            builder = new Builder(getApplicationContext(), packageName);
        } else {
            builder = new Builder(getApplicationContext());
        }
        builder.setSmallIcon(R.mipmap.my_icon).setContentTitle(m()).setContentText("正在后台运行").setWhen(System.currentTimeMillis()).setContentIntent(o());
        if (VERSION.SDK_INT >= 16) {
            return builder.build();
        }
        return builder.getNotification();
    }

    public NotificationManager h() {
        if (this.j == null) {
            this.j = (NotificationManager) getSystemService("notification");
        }
        return this.j;
    }

    private PendingIntent o() {
        if (this.i == null) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setComponent(new ComponentName(this, l()));
            intent.setFlags(270532608);
            this.i = PendingIntent.getActivity(this, 0, intent, 0);
        }
        return this.i;
    }

    public void a(int i) {
        try {
            if (this.k != null) {
                this.k.stop();
                this.k = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.k = MediaPlayer.create(getApplication(), i);
            this.k.start();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void i() {
        try {
            if (this.k != null && this.k.isPlaying()) {
                this.k.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void p() {
        try {
            this.l = BluetoothAdapter.getDefaultAdapter();
            if (this.l == null) {
                s.b((CharSequence) "蓝牙设备不可用");
                h.b("蓝牙设备不可用");
                return;
            }
            registerReceiver(this.n, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            if (this.l.getState() == 10) {
                this.l.enable();
                return;
            }
            h.b("蓝牙已打开，开启Beacnon 扫描功能");
            r();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void q() {
        try {
            unregisterReceiver(this.n);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (this.m != null) {
                this.m.removeAllRangeNotifiers();
                this.m.stopRangingBeaconsInRegion(this.f);
                this.m.unbind(this);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void j() {
    }

    private void r() {
        h.b("008800");
        this.m = BeaconManager.getInstanceForApplication(getApplicationContext());
        this.m.removeAllRangeNotifiers();
        int i = n.a((Context) this).i();
        int j = n.a((Context) this).j();
        if (i <= 0) {
            i = 5;
        }
        if (j <= 0) {
            j = 0;
        }
        this.m.setForegroundScanPeriod((long) (i * 1000));
        this.m.setForegroundBetweenScanPeriod((long) (j * 1000));
        this.m.setBackgroundScanPeriod((long) (i * 1000));
        this.m.setBackgroundBetweenScanPeriod((long) (j * 1000));
        this.m.setBackgroundMode(false);
        this.m.getBeaconParsers().clear();
        this.m.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        this.m.bind(this);
    }

    public void onBeaconServiceConnect() {
        try {
            this.m.addRangeNotifier(this);
            this.m.startRangingBeaconsInRegion(this.f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
