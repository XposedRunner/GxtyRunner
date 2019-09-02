package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class SignScanActivity_ViewBinding implements Unbinder {
    private SignScanActivity a;

    @UiThread
    public SignScanActivity_ViewBinding(SignScanActivity signScanActivity, View view) {
        this.a = signScanActivity;
        signScanActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
    }

    @CallSuper
    public void unbind() {
        SignScanActivity signScanActivity = this.a;
        if (signScanActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        signScanActivity.titleBar = null;
    }
}
