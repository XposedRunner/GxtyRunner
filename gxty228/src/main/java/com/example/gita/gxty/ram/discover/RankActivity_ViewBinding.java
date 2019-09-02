package com.example.gita.gxty.ram.discover;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class RankActivity_ViewBinding implements Unbinder {
    private RankActivity a;

    @UiThread
    public RankActivity_ViewBinding(RankActivity rankActivity, View view) {
        this.a = rankActivity;
        rankActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
    }

    @CallSuper
    public void unbind() {
        RankActivity rankActivity = this.a;
        if (rankActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        rankActivity.titleBar = null;
    }
}
