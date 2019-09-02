package com.example.gita.gxty.ram.medal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import cn.jiguang.net.HttpUtils;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.bumptech.glide.e;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.MedalData;
import com.example.gita.gxty.model.MedalDetailData;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.TitleBar.a;
import com.lzy.okgo.request.GetRequest;

public class MedalDetailActivity extends BaseActivity {
    private MedalData f;
    @BindView(2131755263)
    protected ImageView img1;
    @BindView(2131755264)
    protected ImageView img2;
    @BindView(2131755195)
    protected TextView text1;
    @BindView(2131755032)
    protected TextView text2;
    @BindView(2131755265)
    protected TextView text3;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = (MedalData) getIntent().getSerializableExtra("data");
        if (this.f == null) {
            finish();
            return;
        }
        c(this.f.medal_name);
        if ("1".equals(this.f.is_have)) {
            this.img1.setImageResource(R.mipmap.medal_detail_top_bg2);
            e.a((FragmentActivity) this).a(this.f.medal_icon).a(this.img2);
        } else {
            this.img1.setImageResource(R.mipmap.medal_detail_top_bg);
            e.a((FragmentActivity) this).a(this.f.medal_icon_n).a(this.img2);
        }
        this.titleBar.a(new a(this) {
            final /* synthetic */ MedalDetailActivity a;

            {
                this.a = r1;
            }

            public String a() {
                return "";
            }

            public int b() {
                return R.mipmap.share;
            }

            public void a(View view) {
                this.a.c(1);
            }
        });
        b();
    }

    private void c(String str) {
        a(this.titleBar, str);
        this.titleBar.setLeftImageResource(R.mipmap.btn_close_h);
    }

    protected int a() {
        return R.layout.activity_medal_detail;
    }

    private void b() {
        DataBean a = b.a(null);
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("medal/getMedalDetail") + HttpUtils.PATHS_SEPARATOR + this.f.medal_id).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<MedalDetailData>>(this, this, false) {
            final /* synthetic */ MedalDetailActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<MedalDetailData>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                try {
                    if (!this.a.isFinishing()) {
                        MedalDetailData medalDetailData = (MedalDetailData) ((LzyResponse) aVar.c()).data;
                        if (medalDetailData != null) {
                            this.a.text1.setText(medalDetailData.desc);
                            this.a.text2.setText(medalDetailData.date);
                            this.a.text3.setText(medalDetailData.count);
                            this.a.c(medalDetailData.medal_name);
                            if ("1".equals(medalDetailData.is_have)) {
                                this.a.img1.setImageResource(R.mipmap.medal_detail_top_bg2);
                                e.a(this.a.c()).a(medalDetailData.medal_icon).a(this.a.img2);
                                return;
                            }
                            this.a.img1.setImageResource(R.mipmap.medal_detail_top_bg);
                            e.a(this.a.c()).a(medalDetailData.medal_icon_n).a(this.a.img2);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<MedalDetailData>> aVar) {
                super.b(aVar);
            }
        });
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
    }

    public static void a(Context context, MedalData medalData) {
        try {
            Intent intent = new Intent(context, MedalDetailActivity.class);
            intent.putExtra("data", medalData);
            if (context instanceof Activity) {
                ((Activity) context).startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
                return;
            }
            intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
