package com.example.gita.gxty.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.ram.MyRuningActivity;
import com.example.gita.gxty.ram.service.RuningService;

public class CountdownActivity extends BaseActivity {
    protected TextView f;
    private int g = 2;
    private Animation h;
    private int i = 1;
    private Handler j = new Handler(this) {
        final /* synthetic */ CountdownActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what != 1000) {
                return;
            }
            if (this.a.g > 0) {
                this.a.f.setText(this.a.g + "");
                this.a.h.reset();
                this.a.f.startAnimation(this.a.h);
                this.a.g = this.a.g - 1;
                this.a.j.sendEmptyMessageDelayed(1000, 2000);
                return;
            }
            Intent intent = new Intent(this.a.c(), MyRuningActivity.class);
            intent.putExtra("fromStartRun", "fromStartRun");
            intent.putExtra("runType", this.a.i);
            this.a.startActivity(intent);
            this.a.finish();
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.i = getIntent().getIntExtra("runType", 1);
        this.f = (TextView) findViewById(R.id.num);
        this.f.setTypeface(d());
        this.h = AnimationUtils.loadAnimation(this, R.anim.animation_text);
        this.j.sendEmptyMessageDelayed(1000, 2000);
        RuningService.a(c(), this.i);
        j();
        a((RelativeLayout) findViewById(R.id.baiduAd));
    }

    protected int a() {
        return R.layout.activity_countdown;
    }

    public void onBackPressed() {
    }
}
