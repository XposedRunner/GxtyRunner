package com.example.gita.gxty.ram.discover;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import butterknife.BindView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.weiget.TitleBar;

public class HuoDongActivity extends BaseActivity {
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.titleBar, "活动阅读");
        try {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.content_frame, new HuoDongFragment(), "HuoDong");
            beginTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int a() {
        return R.layout.activity_huodong;
    }
}
