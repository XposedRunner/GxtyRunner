package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;

public class HistoryrankActivity_ViewBinding implements Unbinder {
    private HistoryrankActivity a;

    @UiThread
    public HistoryrankActivity_ViewBinding(HistoryrankActivity historyrankActivity, View view) {
        this.a = historyrankActivity;
        historyrankActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'mRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        HistoryrankActivity historyrankActivity = this.a;
        if (historyrankActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        historyrankActivity.mRecyclerView = null;
    }
}
