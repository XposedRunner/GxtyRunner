package com.example.gita.gxty.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PointerIconCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.e;
import com.example.gita.gxty.R;
import com.example.gita.gxty.ram.MainTabActivity;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.k;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.r;
import com.example.gita.gxty.utils.s;
import com.yanzhenjie.permission.a;
import com.yanzhenjie.permission.d;
import java.util.List;

public class WelcomeActivity extends BaseActivity {
    private TextView f;
    private View g;
    private ImageView h;
    private int i = 3;
    private boolean j = false;
    private boolean k = false;
    private d l = new d(this) {
        final /* synthetic */ WelcomeActivity a;

        {
            this.a = r1;
        }

        public void a(int i, List<String> list) {
            if (a.a(this.a.c(), BaseActivity.a)) {
                if (i == 200) {
                    this.a.j = true;
                    if (this.a.k) {
                        if (((Boolean) q.a(this.a.c()).b("islogin", Boolean.valueOf(false))).booleanValue()) {
                            this.a.startActivity(new Intent(this.a.c(), MainTabActivity.class));
                        } else {
                            this.a.startActivity(new Intent(this.a.c(), MainActivity.class));
                        }
                        this.a.finish();
                        return;
                    }
                    h.b("还在倒计时！！！");
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
    private Handler m = new Handler(this) {
        final /* synthetic */ WelcomeActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == PointerIconCompat.TYPE_CROSSHAIR) {
                if (this.a.i > 0) {
                    this.a.e(this.a.i);
                    this.a.f.setText(this.a.i + "");
                    this.a.i = this.a.i - 1;
                    this.a.m.sendEmptyMessageDelayed(PointerIconCompat.TYPE_CROSSHAIR, 1000);
                    this.a.g.setVisibility(0);
                    return;
                }
                this.a.k = true;
                if (this.a.j) {
                    if (((Boolean) q.a(this.a.c()).b("islogin", Boolean.valueOf(false))).booleanValue()) {
                        this.a.startActivity(new Intent(this.a.c(), MainTabActivity.class));
                    } else {
                        this.a.startActivity(new Intent(this.a.c(), MainActivity.class));
                    }
                    this.a.finish();
                    return;
                }
                h.b("没有获取到基本权限！！！");
            } else if (message.what == PointerIconCompat.TYPE_TEXT) {
                this.a.finish();
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if ((getIntent().getFlags() & 4194304) != 0) {
            finish();
        } else if (isTaskRoot()) {
            d(200);
            this.f = (TextView) findViewById(R.id.textView);
            this.g = findViewById(R.id.countLayout);
            this.h = (ImageView) findViewById(R.id.mainImgLayout);
            boolean a = k.a();
            if (!a) {
                k.a(c());
                a = k.a();
            }
            if (a) {
                this.m.sendEmptyMessageDelayed(PointerIconCompat.TYPE_CROSSHAIR, 1000);
                i();
                return;
            }
            s.a((CharSequence) "请打开网络连接");
            b.a(c());
            finish();
        } else {
            finish();
        }
    }

    protected void i() {
        try {
            if (VERSION.SDK_INT > 11 && VERSION.SDK_INT < 19) {
                getWindow().getDecorView().setSystemUiVisibility(8);
            } else if (VERSION.SDK_INT >= 19) {
                getWindow().getDecorView().setSystemUiVisibility(4102);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void d(int i) {
        a.a(c()).a(i).a(a).a(this.l).b();
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
            final /* synthetic */ WelcomeActivity a;

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
        return R.layout.activity_welcome;
    }

    private void e(int i) {
        try {
            String str = (String) q.a(c()).b("startBg" + i, "");
            if (r.b(str)) {
                e.a(c()).a(str).a(this.h);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
