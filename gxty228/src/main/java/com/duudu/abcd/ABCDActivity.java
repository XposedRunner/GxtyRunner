package com.duudu.abcd;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.weiget.TitleBar;

public class ABCDActivity extends BaseActivity {
    @BindView(2131755031)
    protected TextView text;
    @BindView(2131755195)
    protected TextView text1;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected int a() {
        return R.layout.activity_abcd;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.titleBar, "运动权限设置");
        findViewById(R.id.button).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ABCDActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (a.a(this.a).c()) {
                    this.a.a("跳转设置自启动界面");
                }
            }
        });
        findViewById(R.id.button1).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ABCDActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (a.a(this.a).d()) {
                    this.a.a("跳转设置防睡眠界面");
                }
            }
        });
        if (a.a(c()).a()) {
            findViewById(R.id.button).setVisibility(0);
            this.text.setVisibility(0);
            this.text.setText(a.a(c()).e());
        } else {
            findViewById(R.id.button).setVisibility(8);
            this.text.setVisibility(8);
        }
        if (a.a(c()).b()) {
            findViewById(R.id.button1).setVisibility(0);
            this.text1.setVisibility(0);
            this.text1.setText(a.a(c()).f());
            return;
        }
        findViewById(R.id.button1).setVisibility(8);
        this.text1.setVisibility(8);
    }
}
