package com.example.gita.gxty.ram.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.util.Pair;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.CoordinateConverter;
import com.amap.api.maps.AMapUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.gita.gxty.MyApplication;
import com.example.gita.gxty.R;
import com.example.gita.gxty.b.a;
import com.example.gita.gxty.b.d;
import com.example.gita.gxty.model.DataRun;
import com.example.gita.gxty.model.Ibeacon;
import com.example.gita.gxty.model.MyLatLng;
import com.example.gita.gxty.model.Run;
import com.example.gita.gxty.ram.MyRuningActivity;
import com.example.gita.gxty.ram.db.a.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.m;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.r;
import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.Region;

public class RuningService extends BaseService {
    private boolean A = false;
    private a B;
    private SensorManager C;
    private boolean D = true;
    private b E;
    private int F = 0;
    private long G = 0;
    private boolean H = true;
    private int I = 1;
    private long J = 0;
    private long K = 0;
    private int L = 0;
    private int M = 0;
    private int N = 0;
    private int O = 0;
    private SensorEventListener P = new SensorEventListener(this) {
        final /* synthetic */ RuningService a;

        {
            this.a = r1;
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            if (this.a.D) {
                h.b("准备中，什么都不做！");
            } else if (!this.a.p) {
                this.a.M = this.a.M + ((int) sensorEvent.values[0]);
                h.b("current step Detector: " + this.a.M);
            }
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };
    private int Q = 0;
    private int R = 0;
    private boolean S = true;
    private boolean T = false;
    private boolean U = false;
    private SensorEventListener V = new SensorEventListener(this) {
        final /* synthetic */ RuningService a;

        {
            this.a = r1;
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                if (this.a.D) {
                    h.b("准备中，什么都不做！");
                } else if (sensorEvent == null || sensorEvent.values == null || sensorEvent.values.length == 0) {
                    h.b("SensorEventListener异常数据！");
                } else {
                    h.a(sensorEvent.values[0] + "---" + sensorEvent.accuracy + "---" + sensorEvent.timestamp);
                    int i = (int) sensorEvent.values[0];
                    if (this.a.S) {
                        this.a.S = false;
                        this.a.Q = i;
                    } else if (this.a.p) {
                        if (!this.a.T) {
                            this.a.T = true;
                            this.a.R = i;
                            this.a.U = true;
                            return;
                        }
                        return;
                    } else if (this.a.U && this.a.T) {
                        this.a.T = false;
                        this.a.U = false;
                        this.a.Q = (this.a.Q + i) - this.a.R;
                        this.a.R = 0;
                    }
                    this.a.N = i - this.a.Q;
                    h.b("current step Counter: " + this.a.N);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };
    private int W = 0;
    private int X = 0;
    private boolean Y = false;
    private boolean Z = false;
    Timer e = null;
    Handler f = new Handler(this) {
        final /* synthetic */ RuningService a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1000) {
                ToastUtils.showShort((CharSequence) "位置已丢失");
                this.a.a((int) R.raw.errorpostion);
            } else if (message.what == 1001) {
                ToastUtils.showShort((CharSequence) "计步异常");
                this.a.a((int) R.raw.errorstep);
            } else if (message.what == 1002) {
                ToastUtils.showShort((CharSequence) "网络已断开");
                this.a.a((int) R.raw.errornet);
            }
        }
    };
    long g = 0;
    d h = new d(this) {
        final /* synthetic */ RuningService a;

        {
            this.a = r1;
        }

        public void a(int i) {
            if (this.a.D) {
                h.b("准备中，什么都不做！");
            } else if (!this.a.p) {
                if (this.a.Z && this.a.Y) {
                    this.a.Y = false;
                    this.a.Z = false;
                    this.a.W = (this.a.W + i) - this.a.X;
                    this.a.X = 0;
                }
                this.a.O = i - this.a.W;
                h.b("current step Acceleration: " + this.a.O);
            } else if (!this.a.Y) {
                this.a.Y = true;
                this.a.X = i;
                this.a.Z = true;
            }
        }
    };
    private Run i;
    private HashMap<String, Ibeacon> j = new HashMap();
    private ArrayList<MyLatLng> k = new ArrayList();
    private ArrayList<Ibeacon> l = new ArrayList();
    private ArrayList<MyLatLng> m = new ArrayList();
    private long n = 0;
    private long o = 0;
    private boolean p = false;
    private boolean q = false;
    private boolean r = true;
    private MyLatLng s;
    private MyLatLng t;
    private MyLatLng u;
    private float v = 0.0f;
    private float w = 0.0f;
    private ArrayList<Entry> x = new ArrayList();
    private ArrayList<Float> y = new ArrayList();
    private String z;

    public void onCreate() {
        super.onCreate();
        h.a((Object) "in run service onCreate");
        this.E = new b(this);
    }

    public static void a(Activity activity, int i) {
        try {
            Intent intent = new Intent(activity, RuningService.class);
            intent.putExtra("status", 0);
            intent.putExtra("runType", i);
            activity.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void b(Activity activity, int i) {
        try {
            Intent intent = new Intent(activity, RuningService.class);
            intent.putExtra("status", i);
            activity.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Activity activity) {
        try {
            activity.stopService(new Intent(activity, RuningService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            int intExtra = intent.getIntExtra("status", 0);
            h.b("status : " + intExtra);
            if (intExtra == 0) {
                this.D = true;
                this.n = System.currentTimeMillis();
                this.I = intent.getIntExtra("runType", 1);
                this.i = MyApplication.e().b(this.I);
                o();
            } else if (intExtra == 1) {
                this.D = false;
                this.n = System.currentTimeMillis();
                f();
            } else if (intExtra == 2) {
                this.p = true;
                f();
            } else if (intExtra == 3) {
                this.p = false;
                f();
            } else if (intExtra == 4) {
                this.q = true;
            } else if (intExtra == 5) {
                this.q = false;
            }
        }
        return super.onStartCommand(intent, i, i2);
    }

    protected void o() {
        try {
            if (this.i.ibeacon != null) {
                Iterator it = this.i.ibeacon.iterator();
                while (it.hasNext()) {
                    Ibeacon ibeacon = (Ibeacon) it.next();
                    this.j.put(ibeacon.getMyKey(), ibeacon);
                }
            }
            if (this.i.gpsinfo != null) {
                this.k.addAll(this.i.gpsinfo);
            }
            try {
                this.w = Float.parseFloat(this.i.length) * 1000.0f;
            } catch (Exception e) {
                e.printStackTrace();
            }
            DataRun a = MyApplication.e().a(this.I);
            if (a == null) {
                this.z = com.example.gita.gxty.utils.d.a(System.currentTimeMillis());
                c(0);
                m.a((Context) this, this.I).a(q.a((Context) this).b(), this.i.length, this.z, this.i.runPageId);
                h.b("runPageId: " + this.i.runPageId);
            } else {
                this.z = a.startTime;
                if (a.bNode != null) {
                    this.l.addAll(a.bNode);
                }
                if (a.tNode != null) {
                    this.m.addAll(a.tNode);
                }
                if (a.track != null) {
                    if (a.track.size() > 0) {
                        this.r = false;
                        this.s = (MyLatLng) a.track.get(0);
                        this.u = this.s;
                    } else {
                        this.r = true;
                    }
                }
                if (a.trend != null) {
                    Iterator it2 = a.trend.iterator();
                    while (it2.hasNext()) {
                        Entry entry = (Entry) it2.next();
                        this.x.add(entry);
                        this.y.add(Float.valueOf(entry.getX()));
                    }
                }
                this.o = Long.parseLong(a.duration) * 1000;
                this.v = Float.parseFloat(a.real);
                c(Integer.parseInt(a.totalNum));
            }
            c();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String b(int i) {
        if (i == 0) {
            return "定位失败";
        }
        if (1 == i) {
            return "GPS定位结果";
        }
        if (2 == i) {
            return "前次定位结果";
        }
        if (4 == i) {
            return "缓存定位结果";
        }
        if (5 == i) {
            return "Wifi定位结果";
        }
        if (6 == i) {
            return "基站定位结果";
        }
        if (8 == i) {
            return "离线定位结果";
        }
        return "未知类型" + i;
    }

    public void a(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (h.a) {
                h.b("收到定位回调:" + aMapLocation);
            }
            CoordinateConverter coordinateConverter = this.a;
            boolean isAMapDataAvailable = CoordinateConverter.isAMapDataAvailable(aMapLocation.getLatitude(), aMapLocation.getLongitude());
            if (isAMapDataAvailable) {
                MyApplication.e().a(aMapLocation);
            }
            if (this.D) {
                h.b("准备中，什么都不做！" + aMapLocation);
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            long p = (long) p();
            if (this.p) {
                this.n = currentTimeMillis;
            } else {
                if (currentTimeMillis > this.n) {
                    this.o = (this.o + currentTimeMillis) - this.n;
                } else {
                    this.o = (this.o + this.n) - currentTimeMillis;
                }
                this.n = currentTimeMillis;
            }
            int locationType = aMapLocation.getLocationType();
            if (locationType == 0) {
                h.a((Object) "定位失败2");
            } else if (isAMapDataAvailable) {
                this.G = currentTimeMillis;
                this.t = new MyLatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude(), (double) aMapLocation.getSpeed());
                if (this.r) {
                    this.r = false;
                    this.s = this.t;
                    this.u = this.t;
                    MyApplication.e().a(this.t);
                    m.a((Context) this, this.I).a(MyApplication.e().a(), null);
                    h.b("------isFirstLatLng-------");
                    this.J = currentTimeMillis;
                    this.K = p;
                } else if (this.p) {
                    h.b("已经暂停啦啦！！！");
                    this.J = currentTimeMillis;
                    this.K = p;
                } else {
                    float a = a(this.s, this.t);
                    float a2 = a(this.u, this.t);
                    if (h.a) {
                        h.a("距离:" + this.v + " \n点距：" + a2 + " \n精度：" + aMapLocation.getAccuracy() + " \n类型：" + b(locationType) + " \n速度：" + aMapLocation.getSpeed());
                    }
                    a(this.t);
                    if (a2 > 0.5f && a2 <= 30.0f) {
                        this.L = 0;
                        this.v += a2;
                        h.a("有效行程：" + this.v + "米");
                        b(a, currentTimeMillis);
                        this.J = currentTimeMillis;
                        this.K = p;
                        this.u = this.t;
                    } else if (a2 > 30.0f) {
                        this.L++;
                        if (this.L >= 3) {
                            this.L = 0;
                            h.a((Object) "多次重试后，无效距离重置");
                            float f = (float) (((((double) (currentTimeMillis - this.J)) * 1.0d) / 1000.0d) * 0.5d);
                            a = (float) (((double) (p - this.K)) * 0.6d);
                            if (f >= a2) {
                                a = a2;
                            } else if (a >= f) {
                                a = f;
                            } else if (30.0f < a) {
                                a = 30.0f;
                            }
                            this.v = a + this.v;
                            this.J = currentTimeMillis;
                            this.K = p;
                            this.u = this.t;
                        } else {
                            h.a((Object) "点位间距大，无效，继续重试");
                        }
                    } else {
                        h.a("无效距离不重置：" + a2 + "米");
                    }
                }
            } else {
                h.a((Object) "定位失败3");
            }
            long j = this.o / 1000;
            String str = "0.0";
            if (!(p == 0 || j == 0)) {
                str = String.format("%.1f", new Object[]{Double.valueOf((((double) ((float) p)) * 60.0d) / ((double) j))});
            }
            m.a((Context) this, this.I).a(this.v, System.currentTimeMillis(), j, p, str);
            if (!this.p) {
                b(str);
            }
        }
    }

    private static float a(MyLatLng myLatLng, MyLatLng myLatLng2) {
        return AMapUtils.calculateLineDistance(myLatLng.getMapLatLng(), myLatLng2.getMapLatLng());
    }

    private void b(float f, long j) {
        if (this.v > 50.0f || f > 50.0f) {
            Float valueOf = Float.valueOf(((float) Math.round((this.v / 1000.0f) * 10.0f)) / 10.0f);
            if (!this.y.contains(valueOf)) {
                this.y.add(valueOf);
                this.x.add(new Entry(valueOf.floatValue(), com.example.gita.gxty.utils.d.d(j - this.J)));
            }
            MyApplication.e().a(this.t);
            m.a((Context) this, this.I).a(MyApplication.e().a(), this.x);
        }
    }

    public Class l() {
        return MyRuningActivity.class;
    }

    public String m() {
        return "查看跑步状态";
    }

    public int n() {
        return PathInterpolatorCompat.MAX_NUM_POINTS;
    }

    private void a(MyLatLng myLatLng) {
        try {
            Iterator it = this.k.iterator();
            while (it.hasNext()) {
                MyLatLng myLatLng2 = (MyLatLng) it.next();
                try {
                    if (a(myLatLng2, myLatLng) <= ((float) this.i.distance)) {
                        MyLatLng myLatLng3 = new MyLatLng();
                        myLatLng3.latitude = myLatLng2.latitude;
                        myLatLng3.longitude = myLatLng2.longitude;
                        if (!this.m.contains(myLatLng3)) {
                            this.m.add(myLatLng3);
                            m.a((Context) this, this.I).a(this.m);
                            a(false);
                            break;
                        }
                    } else {
                        continue;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (Ibeacon ibeacon : this.j.values()) {
                try {
                    if (a(ibeacon.position, myLatLng) <= ((float) this.i.distance) && !this.l.contains(ibeacon)) {
                        this.l.add(ibeacon);
                        m.a((Context) this, this.I).b(this.l);
                        a(true);
                        return;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Exception e22) {
            e22.printStackTrace();
        }
    }

    public void onDestroy() {
        h.b("跑步服务已销毁！！！！");
        q();
        MyApplication.e().b();
        MyApplication.e().a(this.I, null);
        MyApplication.e().a(this.I, null);
        MyApplication.e().a(null);
        try {
            if (this.e != null) {
                this.e.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    public String k() {
        String str;
        try {
            if (!(this.i == null || this.i.ibeacon == null || this.i.ibeacon.isEmpty())) {
                str = ((Ibeacon) this.i.ibeacon.get(0)).uuid;
                if (r.a(str)) {
                    return str;
                }
                return "FDA50693-A4E2-4FB1-AFCF-C6EB07647822";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        str = null;
        if (r.a(str)) {
            return str;
        }
        return "FDA50693-A4E2-4FB1-AFCF-C6EB07647822";
    }

    public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
        if (this.D) {
            h.b("准备中，什么都不做！");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.g < 500) {
            h.b("无效扫码结果！");
            return;
        }
        this.g = currentTimeMillis;
        if (collection != null) {
            try {
                if (!collection.isEmpty()) {
                    h.a("beacon------" + collection.size());
                    for (Beacon createIBeacon : collection) {
                        String myKey = Ibeacon.createIBeacon(createIBeacon).getMyKey();
                        if (!this.p) {
                            Ibeacon ibeacon = (Ibeacon) this.j.get(myKey);
                            if (!(ibeacon == null || this.l.contains(ibeacon))) {
                                this.l.add(ibeacon);
                                new Thread(this) {
                                    final /* synthetic */ RuningService a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        m.a(this.a, this.a.I).b(this.a.l);
                                        this.a.a(true);
                                    }
                                }.start();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void a(boolean z) {
        if (z) {
            a("经过必经点");
            a((int) R.raw.bjd);
            return;
        }
        a("经过途经点");
        a((int) R.raw.tjd);
    }

    private void c(int i) {
        this.C = (SensorManager) getSystemService("sensor");
        Sensor defaultSensor = this.C.getDefaultSensor(18);
        Sensor defaultSensor2 = this.C.getDefaultSensor(19);
        Sensor defaultSensor3 = this.C.getDefaultSensor(1);
        h.b("aattaa " + getPackageManager().hasSystemFeature("android.hardware.sensor.stepcounter"));
        h.b("aattaa " + getPackageManager().hasSystemFeature("android.hardware.sensor.stepdetector"));
        if (defaultSensor != null || defaultSensor2 != null) {
            this.A = false;
            this.M = i;
            this.N = i;
            this.C.registerListener(this.P, defaultSensor, 2);
            this.C.registerListener(this.V, defaultSensor2, 2);
        } else if (defaultSensor3 != null) {
            this.A = true;
            this.O = i;
            this.B = new a();
            this.B.a(this.O);
            boolean registerListener = this.C.registerListener(this.B.a(), defaultSensor3, 2);
            this.B.a(this.h);
            if (registerListener) {
                h.a((Object) "加速度传感器可以使用");
            } else {
                h.a((Object) "加速度传感器无法使用");
            }
        }
    }

    private int p() {
        if (this.A) {
            return this.O;
        }
        if (this.M > this.N) {
            return this.M;
        }
        return this.N;
    }

    private void q() {
        try {
            if (this.A) {
                this.C.unregisterListener(this.B.a());
                return;
            }
            this.C.unregisterListener(this.P);
            this.C.unregisterListener(this.V);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long a(float f, long j) {
        if (f > 9.0f) {
            return (long) (((float) (1000 * j)) / f);
        }
        return 0;
    }

    public static String a(long j) {
        return com.example.gita.gxty.utils.d.b(j);
    }

    private void b(String str) {
        if (this.q) {
            h.b("跑步界面已切换都后台...");
        }
        long j = this.o / 1000;
        long a = a(this.v, j);
        Intent intent = new Intent("com.example.gita.gxty.action.refreshUI.run");
        intent.setPackage(getPackageName());
        intent.putExtra("totalLength", this.v);
        intent.putExtra("peisuInt", a);
        intent.putExtra("allDuration", j);
        intent.putExtra("curBNode", this.l);
        intent.putExtra("curTNode", this.m);
        intent.putExtra("bupin", str);
        Pair b = b(a);
        intent.putExtra("dialogTitle", (String) b.first);
        intent.putExtra("dialogMsg", (String) b.second);
        sendBroadcast(intent);
    }

    private Pair<String, String> b(long j) {
        Object obj = 1;
        Object obj2 = null;
        String str = "本次跑步未达标!";
        str = "确定要现在结束么？";
        StringBuffer stringBuffer = new StringBuffer();
        if (this.v < this.w) {
            stringBuffer.append("*跑步距离不达标");
            obj = null;
            Object obj3 = null;
        } else {
            int i = 1;
        }
        if (j > ((long) this.i.peisu)) {
            if (obj == null) {
                stringBuffer.append("<br>");
            } else {
                obj = null;
            }
            stringBuffer.append("*配速不达标");
            obj3 = null;
        }
        if (this.l.size() < this.i.beaconcount) {
            if (obj == null) {
                stringBuffer.append("<br>");
            } else {
                obj = null;
            }
            stringBuffer.append("*跑步必经点位不达标");
            obj3 = null;
        }
        if (this.m.size() < this.i.gpscount) {
            if (obj == null) {
                stringBuffer.append("<br>");
            }
            stringBuffer.append("*跑步途经点位不达标");
        } else {
            obj2 = obj3;
        }
        if (obj2 == null) {
            obj2 = "本次跑步未达标!";
            stringBuffer.append("<br><br>").append("确定结束跑步吗?");
            obj = stringBuffer.toString();
        } else {
            obj2 = "本次跑步已达标!";
            obj = "确定要现在结束跑步吗？";
        }
        return new Pair(obj2, obj);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
