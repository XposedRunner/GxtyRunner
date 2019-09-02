package com.example.gita.gxty.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.gita.gxty.R;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.TitleBar;

public class ContactActivity extends BaseActivity {
    @BindView(2131755213)
    protected TextView phone1;
    @BindView(2131755216)
    protected TextView phone2;
    @BindView(2131755219)
    protected TextView phone3;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.titleBar.setLeftImageResource(R.mipmap.back);
        this.titleBar.setTitle((CharSequence) "高校体育客服");
        this.titleBar.setTitleColor(Color.parseColor("#ffffff"));
        this.titleBar.setLeftClickListener(new OnClickListener(this) {
            final /* synthetic */ ContactActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.phone1.getPaint().setFlags(8);
        this.phone2.getPaint().setFlags(8);
        this.phone3.getPaint().setFlags(8);
    }

    protected int a() {
        return R.layout.activity_contact;
    }

    public void a(TextView textView) {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + textView.getText() + "&version=1")));
        } catch (Exception e) {
            e.printStackTrace();
            s.b((CharSequence) "未安装QQ！");
        }
    }

    @OnClick({2131755212, 2131755215, 2131755218})
    void butterknifeOnItemClick(View view) {
        switch (view.getId()) {
            case R.id.layout1 /*2131755212*/:
                a(this.phone1);
                return;
            case R.id.layout2 /*2131755215*/:
                a(this.phone2);
                return;
            case R.id.layout3 /*2131755218*/:
                a(this.phone3);
                return;
            default:
                return;
        }
    }
}
