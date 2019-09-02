package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class SportRecordActivity_ViewBinding implements Unbinder {
    private SportRecordActivity a;

    @UiThread
    public SportRecordActivity_ViewBinding(SportRecordActivity sportRecordActivity, View view) {
        this.a = sportRecordActivity;
        sportRecordActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        sportRecordActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'mRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        SportRecordActivity sportRecordActivity = this.a;
        if (sportRecordActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        sportRecordActivity.titleBar = null;
        sportRecordActivity.mRecyclerView = null;
    }
}
