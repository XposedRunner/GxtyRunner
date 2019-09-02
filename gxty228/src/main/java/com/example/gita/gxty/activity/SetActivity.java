package com.example.gita.gxty.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PointerIconCompat;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.blankj.utilcode.util.CacheDiskUtils;
import com.duudu.abcd.ABCDActivity;
import com.duudu.abcd.a;
import com.example.gita.gxty.MyApplication;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataUser;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.ram.JuBaoActivity;
import com.example.gita.gxty.ram.SignChangDiActivity;
import com.example.gita.gxty.ram.VersionActivity;
import com.example.gita.gxty.utils.BuglyUtils;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.n;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.utils.t;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.aletview.AlertView;
import com.example.gita.gxty.weiget.aletview.AlertView.Style;
import com.example.gita.gxty.weiget.aletview.c;
import com.example.gita.gxty.weiget.d;
import com.example.gita.gxty.weiget.mywebview.RLWebViewActivity;
import com.lzy.okgo.request.GetRequest;

public class SetActivity extends BaseActivity {
    private CacheDiskUtils f = CacheDiskUtils.getInstance();
    @BindView(2131755352)
    protected TextView huancunSize;
    @BindView(2131755350)
    protected TextView setsportcare;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.titleBar, "设置");
        this.huancunSize.setText(this.f.getString("cache", "0") + "M");
        BuglyUtils.a();
        if (a.a(c()).a() || a.a(c()).b()) {
            this.setsportcare.setVisibility(0);
        } else {
            this.setsportcare.setVisibility(8);
        }
        j();
    }

    @OnClick({2131755345, 2131755346, 2131755347, 2131755348, 2131755349, 2131755351, 2131755353, 2131755354, 2131755355, 2131755344, 2131755350})
    void butterknifeOnItemClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.logoutBtn /*2131755344*/:
                    Object dataUser = new DataUser();
                    dataUser.userid = q.a(c()).b();
                    DataBean a = b.a(dataUser);
                    ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("reg/exitLogin")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<String>>(this, this, false) {
                        final /* synthetic */ SetActivity a;

                        public void a(com.lzy.okgo.model.a<LzyResponse<String>> aVar) {
                            super.a((com.lzy.okgo.model.a) aVar);
                            q.a(this.a.c()).a();
                            n.a(this.a.c()).a();
                            MyApplication.e().a(this.a.c());
                        }

                        public void b(com.lzy.okgo.model.a<LzyResponse<String>> aVar) {
                            super.b(aVar);
                            h.b(aVar.d());
                            q.a(this.a.c()).a();
                            n.a(this.a.c()).a();
                            MyApplication.e().a(this.a.c());
                        }
                    });
                    return;
                case R.id.setwentiBtn /*2131755345*/:
                    RLWebViewActivity.a(c(), com.example.gita.gxty.a.a("art/qa/android"), "常见问题");
                    return;
                case R.id.setxiugaimimaBtn /*2131755346*/:
                    startActivity(new Intent(c(), ModifyPwdActivity.class));
                    return;
                case R.id.setlianxikefu /*2131755347*/:
                    t.a((Activity) this);
                    return;
                case R.id.setlianxiQQkefu /*2131755348*/:
                    RLWebViewActivity.a(c(), com.example.gita.gxty.a.a("art/kf/android"), "联系客服");
                    return;
                case R.id.setzhuyishixiang /*2131755349*/:
                    b();
                    return;
                case R.id.setsportcare /*2131755350*/:
                    startActivity(new Intent(c(), ABCDActivity.class));
                    return;
                case R.id.setqinglihuancun /*2131755351*/:
                    s.b((CharSequence) "清除成功");
                    this.f.remove("cache");
                    this.huancunSize.setText("0M");
                    return;
                case R.id.setwoyaojvbao /*2131755354*/:
                    startActivity(new Intent(c(), JuBaoActivity.class));
                    return;
                case R.id.setbanbengengxin /*2131755355*/:
                    startActivity(new Intent(c(), VersionActivity.class));
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        e.printStackTrace();
    }

    @Nullable
    protected Dialog onCreateDialog(int i, Bundle bundle) {
        Dialog dVar;
        if (i == PointerIconCompat.TYPE_ALIAS) {
            dVar = new d(this, false, 1);
            dVar.setListener(new d.a(this) {
                final /* synthetic */ SetActivity a;

                {
                    this.a = r1;
                }

                public boolean a(d dVar) {
                    return false;
                }
            });
            return dVar;
        } else if (i != 1011) {
            return super.onCreateDialog(i, bundle);
        } else {
            dVar = new d(this, false, 2);
            dVar.setListener(new d.a(this) {
                final /* synthetic */ SetActivity a;

                {
                    this.a = r1;
                }

                public boolean a(d dVar) {
                    return false;
                }
            });
            return dVar;
        }
    }

    protected int a() {
        return R.layout.activity_newset;
    }

    private void b() {
        new AlertView("注意事项", null, "取消", null, new String[]{"体育锻炼", "自由跑", "体育场地签到"}, this, Style.ActionSheet, new c(this) {
            final /* synthetic */ SetActivity a;

            {
                this.a = r1;
            }

            public void a(Object obj, int i) {
                try {
                    ((AlertView) obj).h();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (i == 0) {
                    this.a.a((int) PointerIconCompat.TYPE_ALIAS, null);
                } else if (i == 1) {
                    this.a.a(1011, null);
                } else if (i == 2) {
                    SignChangDiActivity.a(this.a.c());
                }
            }
        }).e();
    }
}
