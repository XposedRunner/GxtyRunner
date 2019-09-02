package com.example.gita.gxty.ram;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.e;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.activity.MainActivity;
import com.example.gita.gxty.model.Ads;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.utils.BuglyUtils;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.g;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.n;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.r;
import com.example.gita.gxty.weiget.mywebview.RLWebViewActivity;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;

public class AdsActivity extends BaseActivity {
    @BindView(2131755200)
    protected ImageView ads_img1;
    @BindView(2131755199)
    protected FrameLayout ads_img_layout1;
    @BindView(2131755201)
    protected FrameLayout ads_img_layout2;
    @BindView(2131755202)
    protected TextView ads_timeTxt;
    @BindView(2131755198)
    protected TextView ads_txt2;
    @BindView(2131755203)
    protected TextView ads_typeTxt;
    private int f = 3;
    private Handler g = new Handler(this) {
        final /* synthetic */ AdsActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == PointerIconCompat.TYPE_CROSSHAIR) {
                if (this.a.f > 0) {
                    this.a.ads_timeTxt.setText(this.a.f + "S");
                    this.a.f = this.a.f - 1;
                    this.a.g.sendEmptyMessageDelayed(PointerIconCompat.TYPE_CROSSHAIR, 1000);
                    return;
                }
                this.a.g.sendEmptyMessage(PointerIconCompat.TYPE_TEXT);
            } else if (message.what == PointerIconCompat.TYPE_TEXT) {
                this.a.o();
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            this.ads_txt2.setText("@ 2016-2018 上海微摇网络科技有限公司");
        } catch (Exception e) {
            e.printStackTrace();
        }
        q();
        a(true);
        BuglyUtils.a(this, this.ads_img_layout2, this.ads_timeTxt, this.ads_typeTxt);
    }

    private void a(boolean z) {
        if (z) {
            this.ads_img_layout1.setVisibility(8);
            this.ads_img_layout2.setVisibility(0);
            return;
        }
        this.ads_img_layout1.setVisibility(0);
        this.ads_img_layout2.setVisibility(8);
    }

    protected int a() {
        return R.layout.activity_ads;
    }

    public void b() {
        if (n.a(c()).g()) {
            a(false);
            p();
            this.g.sendEmptyMessage(PointerIconCompat.TYPE_CROSSHAIR);
            return;
        }
        o();
    }

    public void o() {
        if (((Boolean) q.a(c()).b("islogin", Boolean.valueOf(false))).booleanValue()) {
            startActivity(new Intent(c(), MainTabActivity.class));
            g.a(c(), (String) q.a(c()).b("gxty_mobile", ""));
        } else {
            startActivity(new Intent(c(), MainActivity.class));
        }
        finish();
    }

    private void p() {
        try {
            findViewById(R.id.ads_mainLayout).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AdsActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    RLWebViewActivity.a(this.a.c(), n.a(this.a.c()).c(), n.a(this.a.c()).f());
                }
            });
            this.ads_timeTxt.setText(this.f + "S");
            CharSequence d = n.a(c()).d();
            if (r.a(d)) {
                this.ads_typeTxt.setVisibility(8);
            } else {
                this.ads_typeTxt.setText(d);
                this.ads_typeTxt.setVisibility(0);
            }
            String e = n.a(c()).e();
            if (r.b(e)) {
                e.a(c()).a(e).a(this.ads_img1);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void q() {
        DataBean a = b.a(null);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("art/adsList")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<Ads>>(this, this, false) {
            final /* synthetic */ AdsActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<Ads>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                Ads ads = (Ads) ((LzyResponse) aVar.c()).data;
                String str = ads.img + "/w/" + this.a.ads_img1.getWidth() + "/h/" + this.a.ads_img1.getHeight() + "";
                h.b(str);
                n.a(this.a.c()).e(str);
                n.a(this.a.c()).c(ads.url);
                n.a(this.a.c()).d(ads.type);
                n.a(this.a.c()).f(ads.title);
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<Ads>> aVar) {
            }
        });
    }
}
