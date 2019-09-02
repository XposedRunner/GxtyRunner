package com.example.gita.gxty.ram.lock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.example.gita.gxty.utils.h;

public class KeepAliveActivity extends AppCompatActivity {
    final BroadcastReceiver a = new BroadcastReceiver(this) {
        final /* synthetic */ KeepAliveActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.example.gita.gxty.ram.keepalive")) {
                this.a.a();
            }
        }
    };

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        h.b("锁屏页面已创建");
        getWindow().setFlags(128, 128);
        b();
    }

    private void b() {
        Window window = getWindow();
        window.setGravity(51);
        LayoutParams attributes = window.getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        attributes.width = 1;
        attributes.height = 1;
        window.setAttributes(attributes);
        registerReceiver(this.a, new IntentFilter("com.example.gita.gxty.ram.keepalive"));
    }

    protected void onDestroy() {
        unregisterReceiver(this.a);
        super.onDestroy();
    }

    public void a() {
        finish();
    }
}
