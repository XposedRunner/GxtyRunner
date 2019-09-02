package com.example.gita.gxty.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.adapter.SchoolAdapter;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.RegLatLng;
import com.example.gita.gxty.model.School;
import com.example.gita.gxty.model.SchoolV3;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.sortme.SideBar;
import com.example.gita.gxty.weiget.sortme.c;
import com.lzy.okgo.b.b;
import com.lzy.okgo.request.GetRequest;
import com.tencent.bugly.beta.tinker.TinkerReport;
import com.yanzhenjie.permission.a;
import com.yanzhenjie.permission.d;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SchoollistActivity extends BaseActivity {
    @BindView(2131755427)
    protected TextView addressBtn;
    List<School> f = new ArrayList();
    List<School> g = new ArrayList();
    public AMapLocationClientOption h = null;
    public AMapLocationClient i = null;
    public AMapLocationListener j = new AMapLocationListener(this) {
        final /* synthetic */ SchoollistActivity a;

        {
            this.a = r1;
        }

        public void onLocationChanged(AMapLocation aMapLocation) {
            RegLatLng regLatLng = new RegLatLng();
            if (aMapLocation == null) {
                this.a.m = false;
            } else if (aMapLocation.getLocationType() != 0) {
                this.a.m = true;
                regLatLng.lat = aMapLocation.getLatitude() + "";
                regLatLng.lng = aMapLocation.getLongitude() + "";
            } else {
                this.a.m = false;
            }
            if (this.a.m) {
                this.a.addressBtn.setText(aMapLocation.getCity());
            } else {
                this.a.a("定位失败！");
                this.a.addressBtn.setVisibility(8);
                this.a.rv_listup.setVisibility(8);
                regLatLng.lat = "0";
                regLatLng.lng = "0";
            }
            this.a.a(regLatLng);
        }
    };
    private SchoolAdapter k;
    private SchoolAdapter l;
    private boolean m = false;
    private d n = new d(this) {
        final /* synthetic */ SchoollistActivity a;

        {
            this.a = r1;
        }

        public void a(int i, List<String> list) {
            h.a((Object) "--PermissionListener---onSucceed--");
            if (!a.a(this.a.c(), BaseActivity.a)) {
                a.a(this.a.c(), i).a("权限申请异常").b(BaseActivity.b).c("好，去设置").a();
            } else if (i == TinkerReport.KEY_APPLIED_SUCC_COST_10S_LESS) {
                this.a.f();
                this.a.b();
            }
        }

        public void b(int i, List<String> list) {
            h.a((Object) "--PermissionListener---onFailed--");
            if (a.a(this.a.c(), list)) {
                a.a(this.a.c(), i).a("权限申请失败").b(BaseActivity.c).c("好，去设置").a();
            } else {
                s.b((CharSequence) "权限申请失败！");
            }
        }
    };
    @BindView(2131755228)
    protected ListView rv_listdown;
    @BindView(2131755429)
    protected ListView rv_listup;
    @BindView(2131755430)
    protected SideBar sideBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        findViewById(R.id.searchLayout).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SchoollistActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Intent intent = new Intent(this.a.c(), SchoolSearchActivity.class);
                intent.putExtra("data", (Serializable) this.a.g);
                this.a.startActivityForResult(intent, 1000);
            }
        });
        this.sideBar.setOnTouchingLetterChangedListener(new SideBar.a(this) {
            final /* synthetic */ SchoollistActivity a;

            {
                this.a = r1;
            }

            public void a(String str) {
                int b = this.a.l.b(str.charAt(0));
                if (b != -1) {
                    this.a.rv_listdown.smoothScrollToPosition(b);
                }
            }
        });
        this.k = new SchoolAdapter(c(), null, this.f, false);
        this.rv_listup.setAdapter(this.k);
        this.rv_listup.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ SchoollistActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                try {
                    Intent intent = new Intent();
                    intent.putExtra("school", (Serializable) this.a.f.get(i));
                    this.a.setResult(-1, intent);
                    this.a.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.l = new SchoolAdapter(c(), null, this.g, true);
        this.rv_listdown.setAdapter(this.l);
        this.rv_listdown.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ SchoollistActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                try {
                    Intent intent = new Intent();
                    intent.putExtra("school", (Serializable) this.a.g.get(i));
                    this.a.setResult(-1, intent);
                    this.a.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        d((int) TinkerReport.KEY_APPLIED_SUCC_COST_10S_LESS);
    }

    private void a(RegLatLng regLatLng) {
        b anonymousClass5 = new com.example.gita.gxty.a.a<LzyResponse<SchoolV3>>(this, this, false) {
            final /* synthetic */ SchoollistActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<SchoolV3>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                this.a.f = ((SchoolV3) ((LzyResponse) aVar.c()).data).near;
                this.a.k.a(this.a.f);
                this.a.g = ((SchoolV3) ((LzyResponse) aVar.c()).data).all;
                if (this.a.g != null) {
                    for (School filledData : this.a.g) {
                        filledData.filledData(com.example.gita.gxty.weiget.sortme.a.a());
                    }
                }
                Collections.sort(this.a.g, new c());
                this.a.l.a(this.a.g);
                this.a.g();
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<SchoolV3>> aVar) {
                super.b(aVar);
                h.b(aVar.d());
                if (!this.a.isFinishing()) {
                    s.a(aVar.d().getMessage());
                }
                this.a.g();
            }
        };
        DataBean a = com.example.gita.gxty.utils.b.a((Object) regLatLng);
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("reg/schoolListV3") + "/10000").tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(anonymousClass5);
    }

    private void d(int i) {
        try {
            a.a(c()).a(i).a(a).a(this.n).b();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b() {
        try {
            if (this.i == null) {
                this.i = new AMapLocationClient(getApplicationContext());
                this.i.setLocationListener(this.j);
                this.h = new AMapLocationClientOption();
                this.h.setOnceLocation(true);
                this.h.setOnceLocationLatest(true);
                this.h.setLocationMode(AMapLocationMode.Hight_Accuracy);
                this.i.setLocationOption(this.h);
            }
            if (this.i.isStarted()) {
                this.i.stopLocation();
            }
            this.i.startLocation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        try {
            if (this.i != null) {
                this.i.stopLocation();
                this.i.onDestroy();
                this.i = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == TinkerReport.KEY_APPLIED_SUCC_COST_10S_LESS) {
            d(i);
        } else if (i == 1000) {
            setResult(-1, intent);
            finish();
        }
    }

    protected int a() {
        return R.layout.activity_schoollist;
    }
}
