package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;

public class TodayrankActivity_ViewBinding implements Unbinder {
    private TodayrankActivity a;

    @UiThread
    public TodayrankActivity_ViewBinding(TodayrankActivity todayrankActivity, View view) {
        this.a = todayrankActivity;
        todayrankActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'mRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        TodayrankActivity todayrankActivity = this.a;
        if (todayrankActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        todayrankActivity.mRecyclerView = null;
    }
}
