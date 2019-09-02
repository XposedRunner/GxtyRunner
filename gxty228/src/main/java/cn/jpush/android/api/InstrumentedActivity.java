package cn.jpush.android.api;

import android.app.Activity;

public class InstrumentedActivity extends Activity {
    public void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    public void onStop() {
        super.onStop();
    }
}
