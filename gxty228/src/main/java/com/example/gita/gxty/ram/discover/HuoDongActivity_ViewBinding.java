package com.example.gita.gxty.ram.discover;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class HuoDongActivity_ViewBinding implements Unbinder {
    private HuoDongActivity a;

    @UiThread
    public HuoDongActivity_ViewBinding(HuoDongActivity huoDongActivity, View view) {
        this.a = huoDongActivity;
        huoDongActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
    }

    @CallSuper
    public void unbind() {
        HuoDongActivity huoDongActivity = this.a;
        if (huoDongActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        huoDongActivity.titleBar = null;
    }
}
