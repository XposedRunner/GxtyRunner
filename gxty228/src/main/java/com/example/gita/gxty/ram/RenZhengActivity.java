package com.example.gita.gxty.ram;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.e;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataRenZheng;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.TitleBar.a;
import com.example.gita.gxty.weiget.mywebview.RLWebViewActivity;
import com.lzy.okgo.request.GetRequest;
import com.yuyh.library.imgsel.common.ImageLoader;
import org.json.JSONObject;

public class RenZhengActivity extends BaseImgActivity {
    @BindView(2131755376)
    protected TextView renzheng_btn;
    @BindView(2131755374)
    protected ImageView renzheng_img1;
    @BindView(2131755375)
    protected ImageView renzheng_img2;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.titleBar, "实名认证");
        this.titleBar.a(new a(this) {
            final /* synthetic */ RenZhengActivity a;

            {
                this.a = r1;
            }

            public String a() {
                return "认证条款";
            }

            public int b() {
                return 0;
            }

            public void a(View view) {
                RLWebViewActivity.a(this.a.c(), com.example.gita.gxty.a.a("art/cert/android"), "认证协议");
            }
        });
        com.yuyh.library.imgsel.a.a().a(new ImageLoader() {
            public void displayImage(Context context, String str, ImageView imageView) {
                e.b(context).a(str).a(imageView);
            }
        });
    }

    protected int a() {
        return R.layout.activity_renzheng;
    }

    @OnClick({2131755374, 2131755375, 2131755376})
    void butterknifeOnItemClick(View view) {
        switch (view.getId()) {
            case R.id.renzheng_img1 /*2131755374*/:
                a("upimg", this.renzheng_img1);
                d("upimg");
                return;
            case R.id.renzheng_img2 /*2131755375*/:
                a("downimg", this.renzheng_img2);
                d("downimg");
                return;
            case R.id.renzheng_btn /*2131755376*/:
                t();
                return;
            default:
                return;
        }
    }

    private void t() {
        final String c = c("upimg");
        final String c2 = c("downimg");
        if (c == null) {
            a("请选择身份证正面图");
        } else if (c2 == null) {
            a("请选择身份证反面图");
        } else {
            new Thread(new Runnable(this) {
                final /* synthetic */ RenZhengActivity c;

                public void run() {
                    this.c.f();
                    boolean z = false;
                    try {
                        z = this.c.a(this.c.e(c), this.c.e(c2));
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.c.a("请求失败！");
                    }
                    this.c.g();
                    if (z) {
                        this.c.finish();
                    }
                }
            }).start();
        }
    }

    private boolean a(String str, String str2) throws Exception {
        DataRenZheng dataRenZheng = new DataRenZheng();
        dataRenZheng.pic1 = str;
        dataRenZheng.pic2 = str2;
        DataBean a = b.a(null);
        GetRequest getRequest = (GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("center/doCertification")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0]);
        com.example.gita.gxty.a.a.a(getRequest.getHeaders());
        JSONObject jSONObject = new JSONObject(getRequest.execute().body().string());
        a(jSONObject.getString("msg"));
        return "200".equals(jSONObject.getString("code"));
    }
}
