package com.example.gita.gxty.ram;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PointerIconCompat;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.example.gita.gxty.MyApplication;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.activity.CountdownActivity;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataCaiDan;
import com.example.gita.gxty.model.DataRun;
import com.example.gita.gxty.model.DataRunPage;
import com.example.gita.gxty.model.DataUser;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.Run;
import com.example.gita.gxty.model.RunID;
import com.example.gita.gxty.model.UserResult;
import com.example.gita.gxty.utils.BuglyUtils;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.i;
import com.example.gita.gxty.utils.k;
import com.example.gita.gxty.utils.m;
import com.example.gita.gxty.utils.n;
import com.example.gita.gxty.utils.p;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.aletview.AlertView;
import com.example.gita.gxty.weiget.aletview.AlertView.Style;
import com.example.gita.gxty.weiget.aletview.c;
import com.example.gita.gxty.weiget.e;
import com.example.gita.gxty.weiget.mywebview.RLWebViewActivity;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.tencent.bugly.beta.tinker.TinkerReport;
import com.yanzhenjie.permission.a;
import com.yanzhenjie.permission.d;
import java.util.List;

public class SportActivity extends BaseActivity {
    Handler f = new Handler(this) {
        final /* synthetic */ SportActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == PointerIconCompat.TYPE_HELP) {
                this.a.g();
                Intent intent = new Intent(this.a.c(), CountdownActivity.class);
                intent.putExtra("newRun", ((Boolean) message.obj).booleanValue());
                intent.putExtra("runType", this.a.n);
                this.a.startActivity(intent);
            }
        }
    };
    public AMapLocationClientOption g = null;
    public AMapLocationClient h = null;
    public AMapLocationListener i = new AMapLocationListener(this) {
        final /* synthetic */ SportActivity a;

        {
            this.a = r1;
        }

        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation == null) {
                this.a.g();
                this.a.a("定位失败！");
            } else if (aMapLocation.getLocationType() != 0) {
                this.a.a(aMapLocation.getLongitude() + "," + aMapLocation.getLatitude(), this.a.n);
            } else {
                this.a.g();
                this.a.a("定位失败！");
            }
        }
    };
    private long j = 0;
    private long k = 0;
    private String l;
    private e m;
    private int n = 1;
    private boolean o = false;
    private d p = new d(this) {
        final /* synthetic */ SportActivity a;

        {
            this.a = r1;
        }

        public void a(int i, List<String> list) {
            h.a((Object) "--PermissionListener---onSucceed--");
            if (!a.a(this.a.c(), BaseActivity.a)) {
                a.a(this.a.c(), i).a("权限申请异常").b(BaseActivity.b).c("好，去设置").a();
            } else if (i != 200 && i == TinkerReport.KEY_APPLIED_SUCC_COST_10S_LESS) {
                this.a.d(this.a.n);
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
    private long q = 0;
    @BindView(2131755442)
    protected TextView sport_actionBtn;
    @BindView(2131755409)
    protected TextView sport_kmTxt;
    @BindView(2131755441)
    protected TextView sport_lastTxt;
    @BindView(2131755437)
    protected TextView sport_mubiaoTxt;
    @BindView(2131755440)
    protected TextView sport_renshuTxt;
    @BindView(2131755439)
    protected TextView sport_youxiaoTxt;
    @BindView(2131755438)
    protected TextView xuexiaoTxt;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        this.l = "/w/" + displayMetrics.widthPixels + "/h/" + displayMetrics.heightPixels + "";
        h.b(this.l);
        b();
        BuglyUtils.a();
        h(200);
        q();
        i.b(this);
        a(false);
    }

    private void b() {
        this.sport_actionBtn.setTypeface(d());
        this.sport_mubiaoTxt.setTypeface(d());
        this.sport_kmTxt.setTypeface(d());
        this.sport_youxiaoTxt.setTypeface(d());
        this.sport_renshuTxt.setTypeface(d());
    }

    @OnClick({2131755442, 2131755444, 2131755445, 2131755436})
    void butterknifeOnItemClick(View view) {
        int id = view.getId();
        if (R.id.sport_actionBtn == id) {
            if (!p.a(c())) {
                a(true);
            }
        } else if (R.id.sport_actionBtnleft == id) {
            r0 = System.currentTimeMillis();
            if (this.j == 0 || r0 - this.j > 3100) {
                this.n = 1;
                h(TinkerReport.KEY_APPLIED_SUCC_COST_10S_LESS);
                this.j = r0;
            }
        } else if (R.id.sport_actionBtnright == id) {
            r0 = System.currentTimeMillis();
            if (this.k == 0 || r0 - this.k > 3100) {
                this.n = 2;
                h(TinkerReport.KEY_APPLIED_SUCC_COST_10S_LESS);
                this.k = r0;
            }
        } else if (R.id.sport_signBtn == id) {
            try {
                if (this.m != null) {
                    this.m.dismiss();
                    this.m = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.m = new e(c());
            this.m.setListener(new e.a(this) {
                final /* synthetic */ SportActivity a;

                {
                    this.a = r1;
                }

                public boolean a(e eVar, int i) {
                    Intent intent = new Intent(this.a.c(), SignScanActivity.class);
                    intent.putExtra("signType", i);
                    this.a.startActivity(intent);
                    return false;
                }
            });
            this.m.show();
        }
    }

    private void a(boolean z) {
        if (z) {
            this.sport_actionBtn.setVisibility(8);
            findViewById(R.id.sport_actionBtnlayout).setVisibility(0);
            return;
        }
        this.sport_actionBtn.setVisibility(0);
        findViewById(R.id.sport_actionBtnlayout).setVisibility(8);
    }

    protected int a() {
        return R.layout.activity_sport;
    }

    protected void onResume() {
        super.onResume();
        a(false);
        o();
    }

    private void o() {
        Object dataUser = new DataUser();
        dataUser.userid = q.a(c()).b();
        DataBean a = b.a(dataUser);
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("center/userCenter")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<UserResult>>(this, c()) {
            final /* synthetic */ SportActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<UserResult>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                UserResult userResult = (UserResult) ((LzyResponse) aVar.c()).data;
                q.a(this.a.c()).a("userid", userResult.userid);
                q.a(this.a.c()).a("school", userResult.school);
                q.a(this.a.c()).a("signature", userResult.signature);
                q.a(this.a.c()).a("username", userResult.username);
                q.a(this.a.c()).a("photo", userResult.photo);
                q.a(this.a.c()).a("goal", userResult.goal);
                q.a(this.a.c()).a("surplus", userResult.surplus);
                q.a(this.a.c()).a("last_time", userResult.last_time);
                q.a(this.a.c()).a("teacher", userResult.teacher);
                q.a(this.a.c()).a("course", userResult.course);
                q.a(this.a.c()).a("schoolId", userResult.schoolId);
                q.a(this.a.c()).a("runUserTotal", userResult.runUserTotal);
                q.a(this.a.c()).a("realRunTotal", userResult.realRunTotal);
                q.a(this.a.c()).a("height", userResult.height);
                q.a(this.a.c()).a("weight", userResult.weight);
                q.a(this.a.c()).a("length", Float.toString(Float.parseFloat(userResult.goal) - Float.parseFloat(userResult.surplus)));
                this.a.xuexiaoTxt.setText(userResult.school);
                this.a.sport_kmTxt.setText(userResult.realRunTotal);
                this.a.sport_mubiaoTxt.setText(userResult.targetTotalTimes + "");
                this.a.sport_youxiaoTxt.setText(userResult.effectiveTimes + "");
                this.a.sport_renshuTxt.setText(userResult.participantsNumbers + "");
                int i = userResult.targetTotalTimes - userResult.effectiveTimes;
                if (i < 0) {
                    i = 0;
                }
                this.a.sport_lastTxt.setText("还差" + i + "次达到锻炼目标");
            }
        });
    }

    private void d(final int i) {
        if (m.a(c(), i).d()) {
            new AlertView("检测到上一次<font color=\"#5AD7B9\">" + (i == 1 ? "体锻跑" : "自由跑") + "</font>APP异常退出，可选择操作", null, "取消", null, new String[]{"上传最后一次跑步数据", "继续上一次跑步", "<font color=\"red\">重新开始跑步</font>"}, this, Style.ActionSheet, new c(this) {
                final /* synthetic */ SportActivity b;

                public void a(Object obj, int i) {
                    try {
                        ((AlertView) obj).h();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (i == 0) {
                        m.a(this.b.c(), i).a(1);
                        this.b.f(i);
                    } else if (i == 1) {
                        if (System.currentTimeMillis() - m.a(this.b.c(), i).c() > 3600000) {
                            this.b.b("上一次跑步数据已失效，请上传最后一次跑步数据或者重新开始跑步。");
                            return;
                        }
                        m.a(this.b.c(), i).a(1);
                        this.b.a(i, false);
                    } else if (i == 2) {
                        m.a(this.b.c(), i).a(0);
                        this.b.a(i, true);
                    }
                }
            }).e();
            return;
        }
        m.a(c(), i).a(0);
        a(i, true);
    }

    private void a(int i, boolean z) {
        this.o = z;
        if (n.a((Context) this).a(i)) {
            e(i);
        } else {
            a((int) PointerIconCompat.TYPE_ALIAS, null);
        }
    }

    private void e(int i) {
        if (b.a(c())) {
            k.a((Context) this);
            if (k.a()) {
                g(i);
                return;
            } else {
                a("网络已断开，请联网后重试！");
                return;
            }
        }
        a(1011, null);
    }

    private void f(final int i) {
        m.a(c(), i).a(1);
        k.a((Context) this);
        if (k.a()) {
            f();
            new Thread(this) {
                final /* synthetic */ SportActivity b;

                public void run() {
                    i.b(this.b.c());
                    com.lzy.okgo.b.b anonymousClass1 = new com.example.gita.gxty.a.a<LzyResponse<RunID>>(this, this.b.c()) {
                        final /* synthetic */ AnonymousClass9 a;

                        public void a(com.lzy.okgo.model.a<LzyResponse<RunID>> aVar) {
                            super.a((com.lzy.okgo.model.a) aVar);
                            this.a.b.g();
                            this.a.b.a(com.example.gita.gxty.a.a("run/saveRunV2"), (com.lzy.okgo.model.a) aVar);
                            m.a(this.a.b.c(), i).a();
                            this.a.b.a(((LzyResponse) aVar.c()).msg);
                        }

                        public void b(com.lzy.okgo.model.a<LzyResponse<RunID>> aVar) {
                            super.b(aVar);
                            this.a.b.g();
                            this.a.b.a(com.example.gita.gxty.a.a("run/saveRunV2"), (com.lzy.okgo.model.a) aVar);
                        }
                    };
                    DataBean a = b.a(m.a(this.b.c(), i).a(0));
                    ((PostRequest) ((PostRequest) ((PostRequest) com.lzy.okgo.a.b(com.example.gita.gxty.a.a("run/saveRunV2")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(anonymousClass1);
                }
            }.start();
            return;
        }
        a("网络已断开，请联网后重试！");
    }

    @Nullable
    protected Dialog onCreateDialog(int i, Bundle bundle) {
        Dialog dVar;
        if (i == PointerIconCompat.TYPE_ALIAS) {
            dVar = new com.example.gita.gxty.weiget.d(this, true, this.n);
            dVar.setListener(new com.example.gita.gxty.weiget.d.a(this) {
                final /* synthetic */ SportActivity a;

                {
                    this.a = r1;
                }

                public boolean a(com.example.gita.gxty.weiget.d dVar) {
                    this.a.e(this.a.n);
                    return false;
                }
            });
            return dVar;
        } else if (i == 1011) {
            dVar = new com.example.gita.gxty.weiget.b(this, "提醒", "检查到GPS没有开启，可能影响最终跑步结果，请设置后重试？", "设置", "取消");
            dVar.setListener(new com.example.gita.gxty.weiget.b.a(this) {
                final /* synthetic */ SportActivity a;

                {
                    this.a = r1;
                }

                public boolean a(com.example.gita.gxty.weiget.b bVar, int i) {
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
            return dVar;
        } else if (i != PointerIconCompat.TYPE_NO_DROP) {
            return super.onCreateDialog(i, bundle);
        } else {
            Dialog aVar = new com.example.gita.gxty.weiget.a(this, (DataCaiDan) bundle.getSerializable("data"));
            aVar.setListener(new com.example.gita.gxty.weiget.a.a(this) {
                final /* synthetic */ SportActivity a;

                {
                    this.a = r1;
                }

                public boolean a(com.example.gita.gxty.weiget.a aVar, DataCaiDan dataCaiDan) {
                    n.a(this.a.c()).a(dataCaiDan.id);
                    RLWebViewActivity.a(this.a.c(), dataCaiDan.url, dataCaiDan.title);
                    return false;
                }

                public boolean b(com.example.gita.gxty.weiget.a aVar, DataCaiDan dataCaiDan) {
                    return false;
                }
            });
            return aVar;
        }
    }

    private void g(final int i) {
        MyApplication.e().a(i, null);
        MyApplication.e().a(i, null);
        MyApplication.e().b();
        f();
        if (!this.o) {
            new Thread(this) {
                final /* synthetic */ SportActivity b;

                public void run() {
                    Run run;
                    DataRun a;
                    try {
                        run = (Run) com.example.gita.gxty.utils.c.a(m.a(this.b.c(), i).b(), Run.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                        run = null;
                    }
                    try {
                        a = m.a(this.b.c(), i).a(0);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        a = null;
                    }
                    MyApplication.e().a(i, run);
                    if (!MyApplication.e().c(i) || a == null) {
                        MyApplication.e().a(i, null);
                        if (i == 1) {
                            this.b.a("缓存数据不存在，重新开始跑步");
                            this.b.p();
                            return;
                        }
                        this.b.a("", i);
                        return;
                    }
                    MyApplication.e().a(i, a);
                    if (a.track != null) {
                        MyApplication.e().a(a.track);
                    }
                    this.b.f.sendMessage(this.b.f.obtainMessage(PointerIconCompat.TYPE_HELP, Boolean.valueOf(false)));
                }
            }.start();
        } else if (i == 1) {
            p();
        } else {
            a("", i);
        }
    }

    private void a(String str, final int i) {
        m.a(c(), i).a();
        Object dataRunPage = new DataRunPage();
        dataRunPage.userid = q.a(c()).b();
        dataRunPage.initLocation = str;
        dataRunPage.type = i + "";
        DataBean a = b.a(dataRunPage);
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("run/runPage")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<Run>>(this, this, false) {
            final /* synthetic */ SportActivity b;

            public void a(com.lzy.okgo.model.a<LzyResponse<Run>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                this.b.g();
                this.b.a(com.example.gita.gxty.a.a("run/runPage"), (com.lzy.okgo.model.a) aVar);
                try {
                    Run run = (Run) ((LzyResponse) aVar.c()).data;
                    h.a((Object) run);
                    MyApplication.e().a(i, run);
                    if (MyApplication.e().c(i)) {
                        new com.example.gita.gxty.ram.db.a.b(this.b.c()).b();
                        m.a(this.b.c(), i).a(b.b(run));
                        this.b.f.sendMessage(this.b.f.obtainMessage(PointerIconCompat.TYPE_HELP, Boolean.valueOf(true)));
                        if (run.maxSeconds > 0) {
                            int i = run.maxSeconds / 60;
                            this.b.a("本次跑步将在" + i + "分" + (run.maxSeconds % 60) + "秒后失效");
                            return;
                        }
                        return;
                    }
                    this.b.a("跑步数据获取失败，请联系客户服务人员。");
                    MyApplication.e().a(i, null);
                } catch (Exception e) {
                    e.printStackTrace();
                    s.b((CharSequence) "跑步数据获取失败，请稍后再试。");
                    this.b.finish();
                }
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<Run>> aVar) {
                super.b(aVar);
                this.b.g();
                this.b.a(com.example.gita.gxty.a.a("run/runPage"), (com.lzy.okgo.model.a) aVar);
                if (!this.b.isFinishing()) {
                    if (aVar.d() instanceof IllegalStateException) {
                        s.a(aVar.d().getMessage());
                    } else {
                        s.a((CharSequence) "网络连接失败，请检查网络！");
                    }
                }
            }
        });
    }

    private void h(int i) {
        try {
            a.a(c()).a(i).a(a).a(this.p).b();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void p() {
        try {
            if (this.h == null) {
                this.h = new AMapLocationClient(getApplicationContext());
                this.h.setLocationListener(this.i);
                this.g = new AMapLocationClientOption();
                this.g.setOnceLocation(true);
                this.g.setOnceLocationLatest(true);
                this.g.setLocationMode(AMapLocationMode.Hight_Accuracy);
                this.h.setLocationOption(this.g);
            }
            if (this.h.isStarted()) {
                this.h.stopLocation();
            }
            this.h.startLocation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        try {
            if (this.h != null) {
                this.h.stopLocation();
                this.h.onDestroy();
                this.h = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        h(i);
    }

    private void q() {
        try {
            DataBean a = b.a(null);
            String str = (String) q.a(c()).b("schoolId", "0");
            ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(String.format(com.example.gita.gxty.a.a("Toast/get_index/%1$s"), new Object[]{str})).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<DataCaiDan>>(this, this, false) {
                final /* synthetic */ SportActivity a;

                public void a(com.lzy.okgo.model.a<LzyResponse<DataCaiDan>> aVar) {
                    super.a((com.lzy.okgo.model.a) aVar);
                    DataCaiDan dataCaiDan = (DataCaiDan) ((LzyResponse) aVar.c()).data;
                    String b = n.a(this.a.c()).b();
                    if (dataCaiDan == null || !(b == null || "".equals(b) || !b.equals(dataCaiDan.id))) {
                        h.b("该菜单已展示过了。。。");
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", dataCaiDan);
                    this.a.a((int) PointerIconCompat.TYPE_NO_DROP, bundle);
                }

                public void b(com.lzy.okgo.model.a<LzyResponse<DataCaiDan>> aVar) {
                    h.b("彩蛋功能请求失败！！！！");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        if (System.currentTimeMillis() - this.q > 2000) {
            s.a((CharSequence) "再按一次退出程序");
            this.q = System.currentTimeMillis();
        } else {
            k();
        }
        return true;
    }
}
