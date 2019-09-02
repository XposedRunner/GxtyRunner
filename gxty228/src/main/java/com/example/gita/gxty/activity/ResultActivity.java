package com.example.gita.gxty.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.AMap.OnMapScreenShotListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.trace.LBSTraceClient;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataRunResult;
import com.example.gita.gxty.model.Ibeacon;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.MyLatLng;
import com.example.gita.gxty.model.RunResult;
import com.example.gita.gxty.ram.MyRuningActivity;
import com.example.gita.gxty.utils.e;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.TitleBar.b;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultActivity extends BaseActivity {
    Handler f = new Handler(this) {
        final /* synthetic */ ResultActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1233) {
                this.a.a(true);
                this.a.tempImg.setImageBitmap(null);
            }
        }
    };
    private AMap g = null;
    private DataRunResult h;
    private LBSTraceClient i;
    private int j = 1;
    @BindView(2131755293)
    protected MapView mapView;
    @BindView(2131755413)
    protected TextView sport_bupinTxt;
    @BindView(2131755412)
    protected TextView sport_fenmiaoTxt;
    @BindView(2131755422)
    protected TextView sport_kaluliTxt;
    @BindView(2131755409)
    protected TextView sport_kmTxt;
    @BindView(2131755411)
    protected TextView sport_peisuTxt;
    @BindView(2131755423)
    protected TextView sport_statusTxt;
    @BindView(2131755421)
    protected TextView sport_timeTxt;
    @BindView(2131755382)
    protected ImageView tempImg;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mapView.onCreate(bundle);
        this.j = getIntent().getIntExtra("runType", 1);
        this.i = LBSTraceClient.getInstance(this);
        a(this.titleBar, "跑步详情");
        this.titleBar.a(new b(this, R.mipmap.share) {
            final /* synthetic */ ResultActivity a;

            public void a(View view) {
                this.a.h();
            }
        });
        String stringExtra = getIntent().getStringExtra("runId");
        this.g = this.mapView.getMap();
        this.g.getUiSettings().setZoomPosition(1);
        this.g.getUiSettings().setZoomControlsEnabled(false);
        this.g.moveCamera(CameraUpdateFactory.zoomTo(16.0f));
        this.g.setOnMapLoadedListener(new OnMapLoadedListener(this) {
            final /* synthetic */ ResultActivity a;

            {
                this.a = r1;
            }

            public void onMapLoaded() {
                this.a.g.moveCamera(CameraUpdateFactory.zoomTo(16.0f));
            }
        });
        b();
        c(stringExtra);
        a(true);
        j();
    }

    private void b() {
        this.sport_kmTxt.setTypeface(d());
        this.sport_peisuTxt.setTypeface(d());
        this.sport_fenmiaoTxt.setTypeface(d());
        this.sport_bupinTxt.setTypeface(d());
        this.sport_kaluliTxt.setTypeface(d());
        this.sport_timeTxt.setTypeface(d());
    }

    private void a(boolean z) {
        if (z) {
            this.mapView.setVisibility(0);
            this.tempImg.setVisibility(8);
            return;
        }
        this.mapView.setVisibility(8);
        this.tempImg.setVisibility(0);
    }

    public void h() {
        if (this.g == null) {
            h.b("aMap is null");
            return;
        }
        f();
        this.g.getMapScreenShot(new OnMapScreenShotListener(this) {
            final /* synthetic */ ResultActivity a;

            {
                this.a = r1;
            }

            public void onMapScreenShot(Bitmap bitmap) {
                this.a.a(false);
                this.a.tempImg.setImageBitmap(bitmap);
                new Thread(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            File file = new File(this.a.a.getFilesDir(), "share_img.png");
                            h.a(file.getAbsolutePath());
                            com.example.gita.gxty.utils.b.a(this.a.a.c(), file.getAbsolutePath());
                            e.a(this.a.a.c(), file.getAbsolutePath(), 0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.a.a.g();
                        this.a.a.f.sendEmptyMessage(1233);
                    }
                }.start();
            }

            public void onMapScreenShot(Bitmap bitmap, int i) {
            }
        });
    }

    private void c(String str) {
        Object runResult = new RunResult();
        runResult.runid = str;
        runResult.userid = q.a(c()).b();
        DataBean a = com.example.gita.gxty.utils.b.a(runResult);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("center/runDetailV2")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<DataRunResult>>(this, this) {
            final /* synthetic */ ResultActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<DataRunResult>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                this.a.a(com.example.gita.gxty.a.a("center/runDetailV2"), (com.lzy.okgo.model.a) aVar);
                this.a.h = (DataRunResult) ((LzyResponse) aVar.c()).data;
                if (this.a.h.bNodeV2 == null) {
                    this.a.h.bNodeV2 = new ArrayList();
                }
                if (this.a.h.tNodeV2 == null) {
                    this.a.h.tNodeV2 = new ArrayList();
                }
                this.a.sport_kmTxt.setText(this.a.h.real);
                this.a.sport_fenmiaoTxt.setText(this.a.h.duration2);
                this.a.sport_timeTxt.setText(this.a.h.runStartTime);
                this.a.sport_bupinTxt.setText(this.a.h.bupin);
                this.a.sport_peisuTxt.setText(this.a.h.speed);
                this.a.sport_kaluliTxt.setText(this.a.h.cal);
                h.b(this.a.h.runDesc);
                if (this.a.h.runDesc != null && !"".equals(this.a.h.runDesc)) {
                    this.a.sport_statusTxt.setText(this.a.h.runDesc);
                } else if ("1".equals(this.a.h.state)) {
                    this.a.sport_statusTxt.setText("已完成");
                } else if ("2".equals(this.a.h.state)) {
                    this.a.sport_statusTxt.setText("数据异常");
                } else if ("3".equals(this.a.h.state)) {
                    this.a.sport_statusTxt.setText("成绩无效");
                } else if ("4".equals(this.a.h.state)) {
                    this.a.sport_statusTxt.setText("成绩无效");
                }
                if (this.a.j == 1) {
                    if ("1".equals(this.a.h.state)) {
                        this.a.sport_statusTxt.setTextColor(this.a.getResources().getColor(R.color.run_status_success));
                    } else {
                        this.a.sport_statusTxt.setTextColor(this.a.getResources().getColor(R.color.run_status_fail));
                    }
                    this.a.sport_statusTxt.setVisibility(0);
                } else {
                    this.a.sport_statusTxt.setVisibility(8);
                }
                this.a.o();
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<DataRunResult>> aVar) {
                super.b(aVar);
                this.a.a(com.example.gita.gxty.a.a("center/runDetailV2"), (com.lzy.okgo.model.a) aVar);
            }
        });
    }

    private void o() {
        if (this.g == null) {
            h.b("aMap is null");
            return;
        }
        try {
            if (this.h.track != null && this.h.track.size() > 0) {
                a(this.h.track);
                this.g.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(((MyLatLng) this.h.track.get(0)).getMapLatLng(), 16.0f, 0.0f, 0.0f)));
                this.g.addMarker(new MarkerOptions().position(((MyLatLng) this.h.track.get(0)).getMapLatLng()).icon(BitmapDescriptorFactory.fromResource(R.mipmap.sport_16)));
                this.g.addMarker(new MarkerOptions().position(((MyLatLng) this.h.track.get(this.h.track.size() - 1)).getMapLatLng()).icon(BitmapDescriptorFactory.fromResource(R.mipmap.sport_17)));
            }
            if (this.j == 1) {
                Iterator it;
                if (this.h.beacon != null) {
                    it = this.h.beacon.iterator();
                    while (it.hasNext()) {
                        Ibeacon ibeacon = (Ibeacon) it.next();
                        LatLng latLng = new LatLng(ibeacon.position.latitude, ibeacon.position.longitude);
                        if (this.h.bNodeV2.contains(ibeacon)) {
                            this.g.addMarker(new MarkerOptions().position(latLng).title("必经点已过").icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_must2)));
                        } else {
                            this.g.addMarker(new MarkerOptions().position(latLng).title("必经点").icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_must)));
                        }
                    }
                }
                if (this.h.gpsinfo != null) {
                    for (LatLng latLng2 : this.h.gpsinfo) {
                        if (this.h.tNodeV2.contains(latLng2)) {
                            this.g.addMarker(new MarkerOptions().position(latLng2).title("途经点已过").icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_passed2)));
                        } else {
                            this.g.addMarker(new MarkerOptions().position(latLng2).title("途经点").icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_passed)));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(List<MyLatLng> list) {
        if (this.g == null) {
            h.b("aMap is null");
            return;
        }
        try {
            Iterable arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            for (MyLatLng myLatLng : list) {
                arrayList.add(myLatLng.getMapLatLng());
                arrayList2.add(Integer.valueOf(MyRuningActivity.a(myLatLng.speed)));
            }
            this.g.addPolyline(new PolylineOptions().useGradient(true).addAll(arrayList).width(10.0f).geodesic(true).colorValues(arrayList2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int a() {
        return R.layout.activity_run_result;
    }

    protected void onResume() {
        super.onResume();
        this.mapView.onResume();
    }

    protected void onPause() {
        super.onPause();
        this.mapView.onPause();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mapView.onSaveInstanceState(bundle);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mapView.onDestroy();
    }
}
