package com.example.gita.gxty.ram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.blankj.utilcode.util.AppUtils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.activity.DebugActivity;
import com.example.gita.gxty.utils.BuglyUtils;
import com.example.gita.gxty.weiget.TitleBar;

public class VersionActivity extends BaseActivity {
    private int f = 1;
    @BindView(2131755192)
    protected TitleBar titleBar;
    @BindView(2131755456)
    protected TextView versionName;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.titleBar, "版本更新");
        this.versionName.setText(getString(2131296339) + " " + AppUtils.getAppVersionName() + "(build " + AppUtils.getAppVersionCode() + ")");
        this.versionName.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VersionActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f = this.a.f + 1;
                if (this.a.f > 5) {
                    this.a.f = 1;
                    this.a.startActivity(new Intent(this.a.c(), DebugActivity.class));
                }
            }
        });
    }

    protected int a() {
        return R.layout.activity_version;
    }

    @OnClick({2131755457})
    void butterknifeOnItemClick(View view) {
        switch (view.getId()) {
            case R.id.checkUpdateBtn /*2131755457*/:
                BuglyUtils.b();
                return;
            default:
                return;
        }
    }
}
