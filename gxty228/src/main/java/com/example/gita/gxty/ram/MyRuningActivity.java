package com.example.gita.gxty.ram;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PointerIconCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.adam.gpsstatus.GpsStatusImageView;
import com.adam.gpsstatus.b;
import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.LocationSource.OnLocationChangedListener;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.example.gita.gxty.MyApplication;
import com.example.gita.gxty.R;
import com.example.gita.gxty.a.a;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.activity.ResultActivity;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataRun;
import com.example.gita.gxty.model.Ibeacon;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.MyLatLng;
import com.example.gita.gxty.model.Run;
import com.example.gita.gxty.model.RunID;
import com.example.gita.gxty.model.RunTemp;
import com.example.gita.gxty.ram.service.RuningService;
import com.example.gita.gxty.utils.d;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.i;
import com.example.gita.gxty.utils.k;
import com.example.gita.gxty.utils.m;
import com.example.gita.gxty.weiget.CircleProgressButton;
import com.lzy.okgo.request.PostRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyRuningActivity extends BaseActivity implements InfoWindowAdapter, OnInfoWindowClickListener, LocationSource {
    private static String B = "<br><br><font color='#B0B0B0'>已暂停跑步,取消将自动恢复<font>";
    public static final int h = Color.rgb(44, 226, 255);
    public static final int i = Color.rgb(36, 199, 137);
    public static final int j = Color.rgb(243, 82, 82);
    private boolean A = false;
    protected MapView f;
    Handler g = new Handler(this) {
        final /* synthetic */ MyRuningActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1001) {
                try {
                    this.a.a("上传成功");
                    Intent intent = new Intent(this.a.c(), ResultActivity.class);
                    intent.putExtra("runId", (String) message.obj);
                    this.a.startActivity(intent);
                    this.a.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (message.what == 1002) {
                RunTemp runTemp = (RunTemp) message.obj;
                if (runTemp != null) {
                    if (!this.a.m) {
                        runTemp.allDuration++;
                        this.a.u = runTemp.allDuration;
                        if (!this.a.n) {
                            this.a.a(RuningService.a(runTemp.totalLength, runTemp.allDuration), runTemp.allDuration, runTemp.totalLength, false, runTemp.bupin);
                        }
                    }
                    this.a.a(runTemp);
                }
            }
        }
    };
    @BindView(2131755420)
    protected GpsStatusImageView gpsImage;
    private boolean k = true;
    private Run l;
    private boolean m = false;
    private boolean n = false;
    private AMap o = null;
    private b p;
    private int q = 0;
    private BroadcastReceiver r = null;
    private int s = 1;
    @BindView(2131755401)
    protected TextView sportTxt_2_1;
    @BindView(2131755402)
    protected TextView sportTxt_2_2;
    @BindView(2131755403)
    protected TextView sportTxt_2_3;
    @BindView(2131755406)
    protected ImageView sport_addlockBtn;
    @BindView(2131755408)
    protected View sport_bottomLayout;
    @BindView(2131755413)
    protected TextView sport_bupinTxt;
    @BindView(2131755407)
    protected TextView sport_checkTxt;
    @BindView(2131755412)
    protected TextView sport_fenmiaoTxt;
    @BindView(2131755409)
    protected TextView sport_kmTxt;
    @BindView(2131755415)
    protected View sport_lockBtn;
    @BindView(2131755400)
    protected View sport_mapBottomLayout;
    @BindView(2131755418)
    protected View sport_mapLayout;
    @BindView(2131755419)
    protected ImageView sport_mapUIImg;
    @BindView(2131755410)
    protected TextView sport_mubiaokmTxt;
    @BindView(2131755414)
    protected View sport_pauseBtn;
    @BindView(2131755411)
    protected TextView sport_peisuTxt;
    @BindView(2131755416)
    protected View sport_startBtn;
    @BindView(2131755417)
    protected CircleProgressButton sport_stopBtn;
    @BindView(2131755405)
    protected View sport_topLayout;
    private OnLocationChangedListener t;
    private long u = 0;
    private List<String> v = new ArrayList();
    private List<String> w = new ArrayList();
    private boolean x = false;
    private String y = "本次跑步未达标!";
    private String z = "跑步距离不达标，确定结束吗?";

    private void a(boolean z) {
        if (z) {
            this.sport_bottomLayout.setVisibility(0);
            this.sport_mapBottomLayout.setVisibility(8);
            this.sport_topLayout.setVisibility(0);
        } else {
            this.sport_bottomLayout.setVisibility(8);
            this.sport_mapBottomLayout.setVisibility(0);
            this.sport_topLayout.setVisibility(8);
        }
        this.k = z;
    }

    private void q() {
        this.sport_kmTxt.setTypeface(d());
        this.sport_peisuTxt.setTypeface(d());
        this.sport_fenmiaoTxt.setTypeface(d());
        this.sport_bupinTxt.setTypeface(d());
        this.sportTxt_2_1.setTypeface(d());
        this.sportTxt_2_2.setTypeface(d());
        this.sportTxt_2_3.setTypeface(d());
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.s = getIntent().getIntExtra("runType", 1);
        this.l = MyApplication.e().b(this.s);
        if (getIntent().getStringExtra("fromStartRun") == null || this.l == null) {
            h.b("异常，未知，严重 错误！！！");
            finish();
            return;
        }
        a(true);
        q();
        RuningService.b((Activity) this, 1);
        r();
        this.f = (MapView) findViewById(R.id.map);
        this.f.onCreate(bundle);
        this.o = this.f.getMap();
        this.p = b.a((Context) this);
        y();
        b();
        j();
    }

    public boolean e() {
        return true;
    }

    private void r() {
        this.sport_pauseBtn.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyRuningActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.o();
                this.a.s();
            }
        });
        this.sport_lockBtn.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyRuningActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b();
            }
        });
        this.sport_startBtn.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyRuningActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b();
                this.a.t();
            }
        });
        this.sport_stopBtn.setCircleProcessListener(new CircleProgressButton.b(this) {
            final /* synthetic */ MyRuningActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.D();
            }

            public void b() {
            }

            public void c() {
            }

            public void d() {
            }

            public void e() {
                this.a.sport_stopBtn.setText("长按");
            }
        });
        this.sport_addlockBtn.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyRuningActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.p();
            }
        });
        this.sport_mapUIImg.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyRuningActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.sport_mapBottomLayout.getVisibility() == 0) {
                    this.a.a(true);
                } else {
                    this.a.a(false);
                }
            }
        });
        if (this.s == 1) {
            this.sport_mubiaokmTxt.setText("单次目标" + this.l.length + "公里");
            this.sport_mubiaokmTxt.setVisibility(0);
            return;
        }
        this.sport_mubiaokmTxt.setVisibility(8);
    }

    private void a(AMapLocation aMapLocation) {
        if (this.t != null && aMapLocation != null) {
            this.t.onLocationChanged(aMapLocation);
            this.o.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
        }
    }

    public void activate(OnLocationChangedListener onLocationChangedListener) {
        this.t = onLocationChangedListener;
    }

    public void deactivate() {
        this.t = null;
    }

    protected int a() {
        return R.layout.activity_run;
    }

    private void s() {
        this.m = true;
        RuningService.b(c(), 2);
    }

    private void t() {
        this.m = false;
        RuningService.b(c(), 3);
    }

    private void u() {
        this.n = true;
        RuningService.b(c(), 4);
    }

    private void v() {
        this.n = false;
        RuningService.b(c(), 5);
    }

    private void w() {
        k.a((Context) this);
        if (k.a()) {
            f();
            new Thread(this) {
                final /* synthetic */ MyRuningActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    i.b(this.a.c());
                    com.lzy.okgo.b.b anonymousClass1 = new a<LzyResponse<RunID>>(this, this.a.c(), false) {
                        final /* synthetic */ AnonymousClass12 a;

                        public void a(com.lzy.okgo.model.a<LzyResponse<RunID>> aVar) {
                            this.a.a.g();
                            this.a.a.a(com.example.gita.gxty.a.a("run/saveRunV2"), (com.lzy.okgo.model.a) aVar);
                            String str = "请求失败";
                            try {
                                m.a(this.a.a.c(), this.a.a.s).a();
                                str = ((LzyResponse) aVar.c()).msg;
                                this.a.a.g.sendMessage(this.a.a.g.obtainMessage(1001, ((RunID) ((LzyResponse) aVar.c()).data).runid));
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.a.a.a(str);
                                this.a.a.finish();
                            }
                        }

                        public void b(com.lzy.okgo.model.a<LzyResponse<RunID>> aVar) {
                            super.b(aVar);
                            this.a.a.g();
                            this.a.a.a(com.example.gita.gxty.a.a("run/saveRunV2"), (com.lzy.okgo.model.a) aVar);
                            this.a.a.a((int) PointerIconCompat.TYPE_HELP, null);
                        }
                    };
                    DataBean a = com.example.gita.gxty.utils.b.a(m.a(this.a.c(), this.a.s).a(this.a.u));
                    ((PostRequest) ((PostRequest) ((PostRequest) com.lzy.okgo.a.b(com.example.gita.gxty.a.a("run/saveRunV2")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(anonymousClass1);
                }
            }.start();
            return;
        }
        a(1004, null);
    }

    private void a(RunTemp runTemp) {
        x();
        this.g.sendMessageDelayed(this.g.obtainMessage(1002, runTemp), 1000);
    }

    private void x() {
        this.g.removeMessages(1002);
    }

    private void y() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.showMyLocation(true);
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.map2point));
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        myLocationStyle.myLocationType(1);
        this.o.setMyLocationStyle(myLocationStyle);
        this.o.setLocationSource(this);
        this.o.setMyLocationEnabled(true);
        this.o.getUiSettings().setMyLocationButtonEnabled(false);
        this.o.getUiSettings().setZoomControlsEnabled(false);
        this.o.moveCamera(CameraUpdateFactory.zoomTo(16.0f));
        this.o.setOnInfoWindowClickListener(this);
        this.o.setInfoWindowAdapter(this);
        this.o.setOnMapLoadedListener(new OnMapLoadedListener(this) {
            final /* synthetic */ MyRuningActivity a;

            {
                this.a = r1;
            }

            public void onMapLoaded() {
                this.a.z();
            }
        });
    }

    private void z() {
        try {
            C();
            a(MyApplication.e().c());
            DataRun a = MyApplication.e().a(this.s);
            if (a != null) {
                long parseLong = Long.parseLong(a.duration);
                float parseFloat = Float.parseFloat(a.real);
                a(RuningService.a(parseFloat, parseLong), parseLong, parseFloat, a.bNode, a.tNode, a.buPin);
                if (MyApplication.e().a().size() > 0) {
                    a((MyLatLng) MyApplication.e().a().get(0));
                    Iterable arrayList = new ArrayList();
                    List arrayList2 = new ArrayList();
                    for (MyLatLng myLatLng : MyApplication.e().a()) {
                        arrayList.add(myLatLng.getMapLatLng());
                        arrayList2.add(Integer.valueOf(a(myLatLng.speed)));
                    }
                    this.o.addPolyline(new PolylineOptions().useGradient(true).addAll(arrayList).width(10.0f).geodesic(true).colorValues(arrayList2));
                    this.q = MyApplication.e().a().size() - 1;
                }
            }
            A();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int a(double d) {
        return -16711936;
    }

    private void A() {
        if (this.r == null) {
            this.r = new BroadcastReceiver(this) {
                final /* synthetic */ MyRuningActivity a;

                {
                    this.a = r1;
                }

                public void onReceive(Context context, Intent intent) {
                    try {
                        float floatExtra = intent.getFloatExtra("totalLength", 0.0f);
                        long longExtra = intent.getLongExtra("peisuInt", 0);
                        long longExtra2 = intent.getLongExtra("allDuration", 0);
                        String stringExtra = intent.getStringExtra("bupin");
                        this.a.y = intent.getStringExtra("dialogTitle");
                        this.a.z = intent.getStringExtra("dialogMsg");
                        this.a.a(MyApplication.e().c());
                        ArrayList arrayList = (ArrayList) intent.getSerializableExtra("curBNode");
                        ArrayList arrayList2 = (ArrayList) intent.getSerializableExtra("curTNode");
                        int size = MyApplication.e().a().size();
                        if (size > 0) {
                            this.a.a((MyLatLng) MyApplication.e().a().get(0));
                        }
                        if (size <= 1 || size <= this.a.q + 1) {
                            this.a.a(longExtra, longExtra2, floatExtra, arrayList, arrayList2, stringExtra);
                            return;
                        }
                        try {
                            Iterable arrayList3 = new ArrayList();
                            List arrayList4 = new ArrayList();
                            for (int i = this.a.q; i < size; i++) {
                                MyLatLng myLatLng = (MyLatLng) MyApplication.e().a().get(i);
                                arrayList3.add(myLatLng.getMapLatLng());
                                arrayList4.add(Integer.valueOf(MyRuningActivity.a(myLatLng.speed)));
                            }
                            this.a.o.addPolyline(new PolylineOptions().useGradient(true).addAll(arrayList3).width(10.0f).geodesic(true).colorValues(arrayList4));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.a.a(longExtra, longExtra2, floatExtra, arrayList, arrayList2, stringExtra);
                        this.a.q = size - 1;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            };
        }
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.example.gita.gxty.action.refreshUI.run");
            registerReceiver(this.r, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(long j, long j2, float f, boolean z, String str) {
        CharSequence a = com.example.gita.gxty.utils.b.a(f);
        CharSequence c = d.c(j2);
        CharSequence a2 = RuningService.a(j);
        this.sport_peisuTxt.setText(a2);
        this.sport_fenmiaoTxt.setText(c);
        this.sport_kmTxt.setText(a);
        this.sport_bupinTxt.setText(str);
        this.sportTxt_2_1.setText(a);
        this.sportTxt_2_2.setText(a2);
        this.sportTxt_2_3.setText(str);
    }

    private void a(long j, long j2, float f, ArrayList<Ibeacon> arrayList, ArrayList<MyLatLng> arrayList2, String str) {
        Iterator it;
        String myKey;
        a(j, j2, f, true, str);
        RunTemp runTemp = new RunTemp();
        runTemp.allDuration = j2;
        runTemp.totalLength = f;
        runTemp.bupin = str;
        a(runTemp);
        if (!(arrayList == null || arrayList.isEmpty())) {
            it = arrayList.iterator();
            while (it.hasNext()) {
                Ibeacon ibeacon = (Ibeacon) it.next();
                myKey = ibeacon.getMyKey();
                if (this.v.contains(myKey)) {
                    h.b("必经点位已过。。。。无须再添加。。。。");
                } else {
                    this.v.add(myKey);
                    this.o.addMarker(new MarkerOptions().position(new LatLng(ibeacon.position.latitude, ibeacon.position.longitude)).title("必经点已过").icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_must2)));
                }
            }
        }
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            it = arrayList2.iterator();
            while (it.hasNext()) {
                MyLatLng myLatLng = (MyLatLng) it.next();
                myKey = myLatLng.latitude + "" + myLatLng.longitude;
                if (this.w.contains(myKey)) {
                    h.b("途经点位已过。。。。无须再添加。。。。");
                } else {
                    this.w.add(myKey);
                    this.o.addMarker(new MarkerOptions().position(new LatLng(myLatLng.latitude, myLatLng.longitude)).title("途经点已过").icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_passed2)));
                }
            }
        }
    }

    private void B() {
        try {
            unregisterReceiver(this.r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(MyLatLng myLatLng) {
        if (!this.x) {
            this.x = true;
            this.o.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(myLatLng.getMapLatLng(), 16.0f, 0.0f, 0.0f)));
            this.o.addMarker(new MarkerOptions().position(myLatLng.getMapLatLng()).icon(BitmapDescriptorFactory.fromResource(R.mipmap.sport_16)));
        }
    }

    private void C() {
        Iterator it;
        try {
            if (this.l.ibeacon != null) {
                it = this.l.ibeacon.iterator();
                while (it.hasNext()) {
                    Ibeacon ibeacon = (Ibeacon) it.next();
                    this.o.addMarker(new MarkerOptions().position(new LatLng(ibeacon.position.latitude, ibeacon.position.longitude)).title("必经点").icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_must)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (this.l.gpsinfo != null) {
                it = this.l.gpsinfo.iterator();
                while (it.hasNext()) {
                    MyLatLng myLatLng = (MyLatLng) it.next();
                    this.o.addMarker(new MarkerOptions().position(new LatLng(myLatLng.latitude, myLatLng.longitude)).title("途经点").icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_passed)));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onResume() {
        super.onResume();
        h.b("uiuiui--onresume");
        v();
        this.p.a(this.n);
        this.f.onResume();
        A();
        this.p.a();
    }

    public void onPause() {
        super.onPause();
        B();
        try {
            this.p.b();
        } catch (Exception e) {
            e.printStackTrace();
        }
        h.b("uiuiui--onpause");
        u();
        this.p.a(this.n);
        this.f.onPause();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putSerializable("run", this.l);
        super.onSaveInstanceState(bundle);
        this.f.onSaveInstanceState(bundle);
    }

    public void onDestroy() {
        x();
        RuningService.a(c());
        super.onDestroy();
        try {
            this.f.onDestroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    protected Dialog onCreateDialog(int i, Bundle bundle) {
        Dialog bVar;
        if (i == PointerIconCompat.TYPE_HELP) {
            bVar = new com.example.gita.gxty.weiget.b(this, "上传失败", "跑步数据已缓存，是否退出？", "确定", "取消");
            bVar.setListener(new com.example.gita.gxty.weiget.b.a(this) {
                final /* synthetic */ MyRuningActivity a;

                {
                    this.a = r1;
                }

                public boolean a(com.example.gita.gxty.weiget.b bVar, int i) {
                    if (i == 1) {
                        this.a.finish();
                    }
                    return false;
                }
            });
            return bVar;
        } else if (i == 1004) {
            bVar = new com.example.gita.gxty.weiget.b(this, "网络连接已断开", "跑步数据已缓存，是否设置网络？", "确定", "取消");
            bVar.setListener(new com.example.gita.gxty.weiget.b.a(this) {
                final /* synthetic */ MyRuningActivity a;

                {
                    this.a = r1;
                }

                public boolean a(com.example.gita.gxty.weiget.b bVar, int i) {
                    if (i == 1) {
                        com.example.gita.gxty.utils.b.a(this.a.c());
                    } else {
                        this.a.finish();
                    }
                    return false;
                }
            });
            return bVar;
        } else if (i != 1005) {
            return super.onCreateDialog(i, bundle);
        } else {
            if (m.a(c(), this.s).d()) {
                bVar = new com.example.gita.gxty.weiget.b(this, bundle.getString("title"), bundle.getString("msg"), "确定", "取消");
                bVar.setCanceledOnTouchOutside(false);
                bVar.setCancelable(false);
                bVar.setListener(new com.example.gita.gxty.weiget.b.a(this) {
                    final /* synthetic */ MyRuningActivity a;

                    {
                        this.a = r1;
                    }

                    public boolean a(com.example.gita.gxty.weiget.b bVar, int i) {
                        if (i == 1) {
                            this.a.w();
                        } else {
                            h.b("跑步状态已恢复！");
                            this.a.t();
                        }
                        return false;
                    }
                });
                return bVar;
            }
            bVar = new com.example.gita.gxty.weiget.b(this, "提示", "您这次跑步太短了，无法保存。您真的尽力了吗？", "确认退出", "继续跑步");
            bVar.setCanceledOnTouchOutside(false);
            bVar.setCancelable(false);
            bVar.setListener(new com.example.gita.gxty.weiget.b.a(this) {
                final /* synthetic */ MyRuningActivity a;

                {
                    this.a = r1;
                }

                public boolean a(com.example.gita.gxty.weiget.b bVar, int i) {
                    if (i == 1) {
                        m.a(this.a.c(), this.a.s).a();
                        this.a.finish();
                    } else {
                        h.b("跑步状态已恢复！");
                        this.a.t();
                    }
                    return false;
                }
            });
            return bVar;
        }
    }

    private void D() {
        this.A = false;
        if (!this.m) {
            this.A = true;
            h.b("跑步已暂停,取消自动恢复！");
            if (!(this.z == null || this.z.contains(B))) {
                this.z += B;
            }
            s();
        }
        if (this.s == 2) {
            this.y = "提示";
            this.z = "确定结束此次跑步吗?";
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("msg", this.z);
            bundle.putString("title", this.y);
            a(1005, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        if (this.sport_mapBottomLayout.getVisibility() == 0) {
            a(true);
        }
    }

    public void b() {
        this.sport_pauseBtn.setVisibility(0);
        this.sport_lockBtn.setVisibility(8);
        this.sport_startBtn.setVisibility(8);
        this.sport_stopBtn.setVisibility(8);
        this.sport_addlockBtn.setVisibility(0);
        this.sport_topLayout.setVisibility(0);
        this.sport_bottomLayout.setBackgroundColor(getResources().getColor(R.color.black_shen));
    }

    public void o() {
        this.sport_pauseBtn.setVisibility(8);
        this.sport_lockBtn.setVisibility(8);
        this.sport_startBtn.setVisibility(0);
        this.sport_stopBtn.setVisibility(0);
        this.sport_stopBtn.setText("结束");
        this.sport_addlockBtn.setVisibility(8);
        this.sport_topLayout.setVisibility(0);
        this.sport_bottomLayout.setBackgroundColor(getResources().getColor(R.color.run_page_bg_2));
    }

    public void p() {
        this.sport_pauseBtn.setVisibility(8);
        this.sport_lockBtn.setVisibility(0);
        this.sport_startBtn.setVisibility(8);
        this.sport_stopBtn.setVisibility(8);
        this.sport_addlockBtn.setVisibility(8);
    }

    public void onInfoWindowClick(Marker marker) {
        marker.hideInfoWindow();
    }

    public View getInfoContents(Marker marker) {
        View inflate = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        CharSequence title = marker.getTitle();
        TextView textView = (TextView) inflate.findViewById(2131755035);
        if (title == null) {
            return null;
        }
        textView.setText(title);
        ((TextView) inflate.findViewById(R.id.snippet)).setVisibility(8);
        return inflate;
    }

    public View getInfoWindow(Marker marker) {
        return null;
    }
}
