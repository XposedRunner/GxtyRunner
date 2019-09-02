package com.example.gita.gxty.ram;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PointerIconCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import butterknife.BindView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.a;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.model.Ibeacon;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.b;
import com.tencent.bugly.beta.tinker.TinkerReport;
import com.yanzhenjie.permission.d;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.json.JSONArray;
import org.json.JSONObject;

public class SignScanActivity extends BaseActivity implements BeaconConsumer, RangeNotifier {
    private static Region l = new Region(a.a("FDA50693-A4E2-4FB1-AFCF-C6EB07647822"), null, null, null);
    long f = 0;
    Handler g = new Handler(this) {
        final /* synthetic */ SignScanActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 1000) {
                return;
            }
            if (this.a.j.size() > 0) {
                Intent intent = new Intent(this.a.c(), SignListActivity.class);
                intent.putExtra("signType", this.a.k);
                JSONArray jSONArray = new JSONArray();
                StringBuffer stringBuffer = new StringBuffer();
                try {
                    for (Ibeacon ibeacon : this.a.j.values()) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("uuid", ibeacon.uuid);
                        jSONObject.put("major", ibeacon.major);
                        jSONObject.put("minor", ibeacon.minor);
                        stringBuffer.append(ibeacon.major + "-" + ibeacon.minor + "\n");
                        jSONArray.put(jSONObject);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                intent.putExtra("ibeancons", jSONArray.toString());
                this.a.startActivity(intent);
                this.a.finish();
                return;
            }
            this.a.a((int) PointerIconCompat.TYPE_NO_DROP, null);
        }
    };
    private BluetoothAdapter h;
    private BeaconManager i;
    private Map<String, Ibeacon> j = new HashMap();
    private int k = 1;
    private BroadcastReceiver m = new BroadcastReceiver(this) {
        final /* synthetic */ SignScanActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                if (intExtra == 10) {
                    h.b("蓝牙已关闭，关闭Beacnon 扫描功能");
                    try {
                        this.a.i.unbind(this.a);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (intExtra == 12) {
                    h.b("蓝牙已打开，开启Beacnon 扫描功能");
                    this.a.q();
                } else if (intExtra == 11) {
                    h.b("蓝牙正在开启中，请稍后！！！");
                }
            }
        }
    };
    private d n = new d(this) {
        final /* synthetic */ SignScanActivity a;

        {
            this.a = r1;
        }

        public void a(int i, List<String> list) {
            h.a((Object) "--PermissionListener---onSucceed--");
            if (!com.yanzhenjie.permission.a.a(this.a.c(), BaseActivity.a)) {
                com.yanzhenjie.permission.a.a(this.a.c(), i).a("权限申请异常").b(BaseActivity.b).c("好，去设置").a();
            } else if (i == 200 || i != TinkerReport.KEY_APPLIED_SUCC_COST_10S_LESS) {
            }
        }

        public void b(int i, List<String> list) {
            h.a((Object) "--PermissionListener---onFailed--");
            if (com.yanzhenjie.permission.a.a(this.a.c(), list)) {
                com.yanzhenjie.permission.a.a(this.a.c(), i).a("权限申请失败").b(BaseActivity.c).c("好，去设置").a();
            } else {
                s.b((CharSequence) "权限申请失败！");
            }
        }
    };
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.k = getIntent().getIntExtra("signType", 1);
        a(this.titleBar, "搜索签到");
        View findViewById = findViewById(R.id.loadImg1);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.img_cicle);
        loadAnimation.setInterpolator(new LinearInterpolator());
        findViewById.startAnimation(loadAnimation);
        d(200);
        o();
        b();
        j();
        a((RelativeLayout) findViewById(R.id.baiduAd));
    }

    private void b() {
        this.g.removeMessages(1000);
        this.g.sendEmptyMessageDelayed(1000, 6000);
    }

    protected int a() {
        return R.layout.activity_scanbeacon;
    }

    public void onBackPressed() {
        a((int) PointerIconCompat.TYPE_ALL_SCROLL, null);
    }

    private void o() {
        try {
            this.h = BluetoothAdapter.getDefaultAdapter();
            if (this.h == null) {
                s.b((CharSequence) "蓝牙设备不可用");
                h.b("蓝牙设备不可用");
                return;
            }
            registerReceiver(this.m, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            if (this.h.getState() == 10) {
                this.h.enable();
                return;
            }
            h.b("蓝牙已打开，开启Beacnon 扫描功能");
            q();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void p() {
        try {
            unregisterReceiver(this.m);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (this.i != null) {
                this.i.removeAllRangeNotifiers();
                this.i.stopRangingBeaconsInRegion(l);
                this.i.unbind(this);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void q() {
        this.i = BeaconManager.getInstanceForApplication(getApplicationContext());
        this.i.removeAllRangeNotifiers();
        this.i.setForegroundScanPeriod((long) 2000);
        this.i.setForegroundBetweenScanPeriod((long) null);
        this.i.setBackgroundMode(false);
        this.i.getBeaconParsers().clear();
        this.i.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        this.i.bind(this);
    }

    @Nullable
    protected Dialog onCreateDialog(int i, Bundle bundle) {
        Dialog bVar;
        if (i == 1011) {
            bVar = new b(this, "提示", "检查到GPS没有开启，可能影响最终跑步结果，请设置后重试？", "设置", "取消");
            bVar.setListener(new b.a(this) {
                final /* synthetic */ SignScanActivity a;

                {
                    this.a = r1;
                }

                public boolean a(b bVar, int i) {
                    if (i == 1) {
                        try {
                            this.a.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1011);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return false;
                }
            });
            return bVar;
        } else if (i == PointerIconCompat.TYPE_NO_DROP) {
            bVar = new b(this, "提示", "未扫描到有效的签到设备，是否重试？", "确定", "取消");
            bVar.setListener(new b.a(this) {
                final /* synthetic */ SignScanActivity a;

                {
                    this.a = r1;
                }

                public boolean a(b bVar, int i) {
                    if (i == 1) {
                        this.a.b();
                    } else {
                        this.a.finish();
                    }
                    return false;
                }
            });
            return bVar;
        } else if (i != PointerIconCompat.TYPE_ALL_SCROLL) {
            return super.onCreateDialog(i, bundle);
        } else {
            bVar = new b(this, "提示", "正在扫描签到设备，是否离开？", "确定", "取消");
            bVar.setListener(new b.a(this) {
                final /* synthetic */ SignScanActivity a;

                {
                    this.a = r1;
                }

                public boolean a(b bVar, int i) {
                    if (i == 1) {
                        this.a.finish();
                    }
                    return false;
                }
            });
            return bVar;
        }
    }

    private void d(int i) {
        try {
            com.yanzhenjie.permission.a.a(c()).a(i).a(a).a(this.n).b();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 200 || i == TinkerReport.KEY_APPLIED_SUCC_COST_10S_LESS) {
            d(i);
        }
    }

    public void onBeaconServiceConnect() {
        r();
    }

    private void r() {
        try {
            if (this.i == null) {
                h.b("BeaconManager init error!!!");
                return;
            }
            this.j.clear();
            h.a((Object) "Beancon 开始扫描。。。。。。");
            this.i.addRangeNotifier(this);
            this.i.startRangingBeaconsInRegion(l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f < 500) {
            h.b("无效扫码结果！");
            return;
        }
        this.f = currentTimeMillis;
        if (collection != null) {
            try {
                if (!collection.isEmpty()) {
                    h.a("beacon------" + collection.size());
                    for (Beacon createIBeacon : collection) {
                        Ibeacon createIBeacon2 = Ibeacon.createIBeacon(createIBeacon);
                        String myKey = createIBeacon2.getMyKey();
                        if (!this.j.containsKey(myKey)) {
                            this.j.put(myKey, createIBeacon2);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onDestroy() {
        this.g.removeMessages(1000);
        p();
        super.onDestroy();
    }
}
