package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class SportTermActivity_ViewBinding implements Unbinder {
    private SportTermActivity a;

    @UiThread
    public SportTermActivity_ViewBinding(SportTermActivity sportTermActivity, View view) {
        this.a = sportTermActivity;
        sportTermActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        sportTermActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'mRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        SportTermActivity sportTermActivity = this.a;
        if (sportTermActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        sportTermActivity.titleBar = null;
        sportTermActivity.mRecyclerView = null;
    }
}
