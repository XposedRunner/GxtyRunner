package com.example.gita.gxty.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import butterknife.OnClick;
import com.example.gita.gxty.R;
import com.example.gita.gxty.utils.BuglyUtils;
import com.example.gita.gxty.utils.l;
import com.example.gita.gxty.utils.m;
import com.example.gita.gxty.utils.n;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.aletview.AlertView;
import com.example.gita.gxty.weiget.aletview.AlertView.Style;
import com.example.gita.gxty.weiget.aletview.c;

public class MainActivity extends BaseActivity {
    private long f = 0;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BuglyUtils.a();
        findViewById(R.id.changeBtn).setVisibility(8);
        l.a((Context) this).a();
    }

    protected int a() {
        return R.layout.activity_main;
    }

    @OnClick({2131755260, 2131755261, 2131755262})
    void butterknifeOnItemClick(View view) {
        switch (view.getId()) {
            case R.id.login /*2131755260*/:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return;
            case R.id.register /*2131755261*/:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                return;
            case R.id.changeBtn /*2131755262*/:
                b();
                return;
            default:
                return;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        if (System.currentTimeMillis() - this.f > 2000) {
            s.a((CharSequence) "再按一次退出程序");
            this.f = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
        return true;
    }

    private void b() {
        new AlertView("修改应用入口地址", null, "取消", null, new String[]{"生产版本", "测试版本", "预发布版本"}, this, Style.ActionSheet, new c(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            public void a(Object obj, int i) {
                try {
                    ((AlertView) obj).h();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String b = l.a(this.a.c()).b();
                if (i == 0) {
                    if ("http://gxhttp.chinacloudapp.cn/api/".equals(b)) {
                        this.a.a("当前版本是生成版本，无需切换！！！");
                        return;
                    }
                    m.a(this.a.c(), 1).a();
                    m.a(this.a.c(), 2).a();
                    n.a(this.a.c()).a();
                    l.a(this.a.c()).a("http://gxhttp.chinacloudapp.cn/api/");
                    this.a.a("成功切换到生成版本！！！");
                } else if (i == 1) {
                    if ("http://gxtest.wizarcan.com/api/".equals(b)) {
                        this.a.a("当前版本是测试版本，无需切换！！！");
                        return;
                    }
                    m.a(this.a.c(), 1).a();
                    m.a(this.a.c(), 2).a();
                    n.a(this.a.c()).a();
                    l.a(this.a.c()).a("http://gxtest.wizarcan.com/api/");
                    this.a.a("成功切换到测试版本！！！");
                } else if (i != 2) {
                } else {
                    if ("http://pre.sportcampus.cn/api/".equals(b)) {
                        this.a.a("当前版本是预发布版本，无需切换！！！");
                        return;
                    }
                    m.a(this.a.c(), 1).a();
                    m.a(this.a.c(), 2).a();
                    n.a(this.a.c()).a();
                    l.a(this.a.c()).a("http://pre.sportcampus.cn/api/");
                    this.a.a("成功切换到预发布版本！！！");
                }
            }
        }).e();
    }
}
