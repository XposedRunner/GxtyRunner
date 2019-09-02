package com.example.gita.gxty.ram;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PointerIconCompat;
import android.webkit.WebView;
import com.example.gita.gxty.MyApplication;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.k;
import com.example.gita.gxty.utils.l;
import com.example.gita.gxty.utils.s;
import com.yanzhenjie.permission.a;
import com.yanzhenjie.permission.d;
import java.util.List;

public class StartActivity extends BaseActivity {
    private boolean f = false;
    private d g = new d(this) {
        final /* synthetic */ StartActivity a;

        {
            this.a = r1;
        }

        public void a(int i, List<String> list) {
            if (a.a(this.a.c(), BaseActivity.a)) {
                if (i == 200) {
                    this.a.f = true;
                    boolean a = k.a();
                    if (!a) {
                        k.a(this.a.c());
                        a = k.a();
                    }
                    if (a) {
                        this.a.h.sendEmptyMessageDelayed(PointerIconCompat.TYPE_CROSSHAIR, 500);
                        return;
                    }
                    s.a((CharSequence) "请打开网络连接");
                    b.a(this.a.c());
                    this.a.finish();
                }
            } else if (this.a.isFinishing()) {
                h.b("---页面马上关闭---");
            } else {
                a.a(this.a.c(), i).a("权限申请异常").b(BaseActivity.b).c("好，去设置").a();
            }
        }

        public void b(int i, List<String> list) {
            h.a((Object) "--PermissionListener---onFailed--");
            if (this.a.isFinishing()) {
                h.b("---页面马上关闭---");
            } else if (a.a(this.a.c(), list)) {
                a.a(this.a.c(), i).a("权限申请失败").b(BaseActivity.c).c("好，去设置").a();
            } else {
                this.a.a(1011, null);
            }
        }
    };
    private Handler h = new Handler(this) {
        final /* synthetic */ StartActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == PointerIconCompat.TYPE_CROSSHAIR) {
                this.a.b();
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if ((getIntent().getFlags() & 4194304) != 0) {
            finish();
            return;
        }
        l.a((Context) this).a();
        d(200);
        try {
            MyApplication.e().a(((WebView) findViewById(R.id.webview)).getSettings().getUserAgentString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        MainTabActivity.a((Activity) this);
    }

    private void d(int i) {
        a.a(c()).a(i).a(a).a(this.g).b();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        d(i);
    }

    @Nullable
    protected Dialog onCreateDialog(int i, Bundle bundle) {
        if (i != 1011) {
            return super.onCreateDialog(i, bundle);
        }
        Dialog bVar = new com.example.gita.gxty.weiget.b(this, "提示", "检查到权限申请失败，是否重试？", "重试", "退出");
        bVar.setListener(new com.example.gita.gxty.weiget.b.a(this) {
            final /* synthetic */ StartActivity a;

            {
                this.a = r1;
            }

            public boolean a(com.example.gita.gxty.weiget.b bVar, int i) {
                if (i == 1) {
                    this.a.d(200);
                } else {
                    this.a.finish();
                }
                return false;
            }
        });
        return bVar;
    }

    protected int a() {
        return R.layout.activity_start;
    }

    private void b() {
        startActivity(new Intent(c(), AdsActivity.class));
        finish();
    }
}
