package com.tencent.bugly.beta.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.global.b;

/* compiled from: BUGLY */
public class BetaActivity extends FragmentActivity {
    public Runnable onDestroyRunnable = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            requestWindowFeature(1);
            if (Beta.dialogFullScreen) {
                getWindow().setFlags(1024, 1024);
            }
            View findViewById = getWindow().getDecorView().findViewById(16908290);
            if (findViewById != null) {
                findViewById.setOnClickListener(new b(1, this, findViewById));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int intExtra = getIntent().getIntExtra("frag", -1);
        Fragment fragment = (b) g.a.get(Integer.valueOf(intExtra));
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().add(16908290, fragment).commit();
            g.a.remove(Integer.valueOf(intExtra));
            return;
        }
        finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.onDestroyRunnable != null) {
            this.onDestroyRunnable.run();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean a;
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(16908290);
        try {
            if (findFragmentById instanceof b) {
                a = ((b) findFragmentById).a(i, keyEvent);
            } else {
                a = false;
            }
        } catch (Exception e) {
            a = false;
        }
        if (a) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
