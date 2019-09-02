package com.example.gita.gxty.ram;

import android.app.Activity;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.example.gita.gxty.MyApplication;
import com.example.gita.gxty.R;
import com.example.gita.gxty.a;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.model.DataSign;
import com.example.gita.gxty.model.SignOverData;
import com.example.gita.gxty.ram.service.SignService;
import com.example.gita.gxty.utils.d;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.CircleProgressButton;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.TitleBar.b;
import com.example.gita.gxty.weiget.mywebview.RLWebViewActivity;

public class SignChangDiActivity extends BaseActivity {
    private static long k = 900;
    Handler f = new Handler(this) {
        final /* synthetic */ SignChangDiActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1000) {
                this.a.h = this.a.h + 1;
                this.a.o();
                if (this.a.h >= this.a.g) {
                    this.a.q();
                }
            } else if (message.what == 1001) {
                this.a.l = this.a.l + 1;
                this.a.o();
                if (this.a.l >= SignChangDiActivity.k) {
                    this.a.q();
                }
            }
        }
    };
    private long g = 1800;
    private long h = 1;
    private long i = 0;
    private long j = 0;
    private long l = 0;
    private long m = 0;
    private boolean n = true;
    private DataSign o;
    private boolean p = false;
    private BroadcastReceiver q = null;
    @BindView(2131755433)
    protected TextView sport_mubiaoTimeTxt;
    @BindView(2131755414)
    protected CircleProgressButton sport_pauseBtn;
    @BindView(2131755434)
    protected TextView sport_pauseTimeTxt;
    @BindView(2131755416)
    protected CircleProgressButton sport_startBtn;
    @BindView(2131755417)
    protected CircleProgressButton sport_stopBtn;
    @BindView(2131755421)
    protected TextView sport_timeTxt;
    @BindView(2131755192)
    protected TitleBar titleBar;

    public static final void a(Activity activity) {
        RLWebViewActivity.a(activity, a.a("art/instructionSign"), "签到说明");
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.o = (DataSign) getIntent().getSerializableExtra("data");
        if (this.o == null) {
            h.b("error  data  is null");
            finish();
            return;
        }
        SignService.a(c(), 1);
        s();
        this.g = (long) this.o.duration;
        k = (long) this.o.leave;
        this.i = System.currentTimeMillis();
        this.j = System.currentTimeMillis();
        this.m = System.currentTimeMillis();
        a(this.titleBar, this.o.name);
        this.titleBar.a(new b(this, R.mipmap.changdi_info) {
            final /* synthetic */ SignChangDiActivity a;

            public void a(View view) {
                SignChangDiActivity.a(this.a.c());
            }
        });
        this.titleBar.setLeftClickListener(new OnClickListener(this) {
            final /* synthetic */ SignChangDiActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a("长按暂停按钮关闭");
            }
        });
        this.sport_pauseBtn.setCircleProcessListener(new CircleProgressButton.b(this) {
            final /* synthetic */ SignChangDiActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.b(true);
            }

            public void b() {
            }

            public void c() {
            }

            public void d() {
            }

            public void e() {
                this.a.sport_pauseBtn.setText("长按");
            }
        });
        this.sport_startBtn.setCircleProcessListener(new CircleProgressButton.b(this) {
            final /* synthetic */ SignChangDiActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a(true);
            }

            public void b() {
            }

            public void c() {
            }

            public void d() {
            }

            public void e() {
                this.a.sport_startBtn.setText("长按");
                this.a.sport_stopBtn.setText("结束");
            }
        });
        this.sport_stopBtn.setCircleProcessListener(new CircleProgressButton.b(this) {
            final /* synthetic */ SignChangDiActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.p();
            }

            public void b() {
            }

            public void c() {
            }

            public void d() {
            }

            public void e() {
                this.a.sport_startBtn.setText("继续");
                this.a.sport_stopBtn.setText("长按");
            }
        });
        this.sport_timeTxt.setTypeface(d());
        this.sport_mubiaoTimeTxt.setText("签到时长 " + (this.o.duration / 60) + " 分钟");
        a(false);
        j();
    }

    public boolean e() {
        return true;
    }

    private void o() {
        long j = 0;
        this.f.removeMessages(1000);
        this.f.removeMessages(1001);
        long j2;
        if (this.n) {
            j2 = this.g - this.h;
            if (j2 >= 0) {
                j = j2;
            }
            this.sport_timeTxt.setText(d.c(j));
            this.sport_pauseTimeTxt.setVisibility(8);
            this.f.sendEmptyMessageDelayed(1000, 1000);
            return;
        }
        j2 = k - this.l;
        if (j2 >= 0) {
            j = j2;
        }
        this.sport_pauseTimeTxt.setText("暂停限制倒数 " + d.c(j));
        this.sport_pauseTimeTxt.setVisibility(0);
        this.f.sendEmptyMessageDelayed(1001, 1000);
    }

    public void a(boolean z) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            s.b((CharSequence) "蓝牙设备不可用");
        } else if (defaultAdapter.getState() == 10) {
            defaultAdapter.enable();
            a("蓝牙正在打开，请稍后重试！");
        } else {
            this.n = true;
            this.sport_pauseBtn.setVisibility(0);
            this.sport_startBtn.setVisibility(8);
            this.sport_stopBtn.setVisibility(8);
            this.sport_pauseBtn.setText("暂停");
            if (z) {
                SignService.a(c(), 3);
            }
        }
    }

    public void b(boolean z) {
        this.n = false;
        this.sport_pauseBtn.setVisibility(8);
        this.sport_startBtn.setVisibility(0);
        this.sport_stopBtn.setVisibility(0);
        this.sport_startBtn.setText("继续");
        this.sport_stopBtn.setText("结束");
        if (z) {
            SignService.a(c(), 2);
        }
    }

    protected int a() {
        return R.layout.activity_sign_changdi;
    }

    protected void onResume() {
        super.onResume();
        this.p = false;
        if (this.n) {
            this.h += (System.currentTimeMillis() - this.j) / 1000;
            this.j = 0;
            o();
            return;
        }
        this.l += (System.currentTimeMillis() - this.m) / 1000;
        this.m = 0;
        o();
    }

    protected void onPause() {
        super.onPause();
        this.p = true;
        this.f.removeMessages(1000);
        this.f.removeMessages(1001);
        if (this.n) {
            this.j = System.currentTimeMillis();
        } else {
            this.m = System.currentTimeMillis();
        }
    }

    protected void onDestroy() {
        this.f.removeMessages(1000);
        r();
        SignService.a(c());
        super.onDestroy();
    }

    @Nullable
    protected Dialog onCreateDialog(int i, Bundle bundle) {
        if (i != 1011) {
            return super.onCreateDialog(i, bundle);
        }
        Dialog bVar = new com.example.gita.gxty.weiget.b(this, "提醒", "签到时长不够，是否提交本次签到？", "确定", "取消");
        bVar.setListener(new com.example.gita.gxty.weiget.b.a(this) {
            final /* synthetic */ SignChangDiActivity a;

            {
                this.a = r1;
            }

            public boolean a(com.example.gita.gxty.weiget.b bVar, int i) {
                if (i == 1) {
                    this.a.q();
                }
                return false;
            }
        });
        return bVar;
    }

    public void onBackPressed() {
    }

    private void p() {
        if (this.h >= this.g) {
            q();
        } else {
            a(1011, null);
        }
    }

    private void q() {
        Intent intent = new Intent();
        SignOverData signOverData = new SignOverData();
        signOverData.startTime = this.i + "";
        signOverData.endTime = System.currentTimeMillis() + "";
        signOverData.duration = this.g + "";
        signOverData.points = MyApplication.e().d();
        intent.putExtra("result", com.example.gita.gxty.utils.b.b(signOverData));
        intent.putExtra("data", this.o);
        setResult(-1, intent);
        finish();
    }

    private void r() {
        try {
            unregisterReceiver(this.q);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void s() {
        if (this.q == null) {
            this.q = new BroadcastReceiver(this) {
                final /* synthetic */ SignChangDiActivity a;

                {
                    this.a = r1;
                }

                public void onReceive(Context context, Intent intent) {
                    try {
                        boolean booleanExtra = intent.getBooleanExtra("data", true);
                        h.b("isRunning : " + booleanExtra);
                        if (booleanExtra) {
                            if (!this.a.n) {
                                if (this.a.p) {
                                    this.a.h = this.a.h + ((System.currentTimeMillis() - this.a.j) / 1000);
                                    this.a.m = System.currentTimeMillis();
                                }
                                this.a.a(false);
                            }
                        } else if (this.a.n) {
                            if (this.a.p) {
                                this.a.l = this.a.l + ((System.currentTimeMillis() - this.a.m) / 1000);
                                this.a.j = System.currentTimeMillis();
                            }
                            this.a.b(false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.example.gita.gxty.action.refreshUI.Sign");
            registerReceiver(this.q, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
