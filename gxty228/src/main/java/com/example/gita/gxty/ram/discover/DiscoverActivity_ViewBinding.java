package com.example.gita.gxty.ram.discover;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ajguan.library.EasyRefreshLayout;
import com.example.gita.gxty.R;

public class DiscoverActivity_ViewBinding implements Unbinder {
    private DiscoverActivity a;

    @UiThread
    public DiscoverActivity_ViewBinding(DiscoverActivity discoverActivity, View view) {
        this.a = discoverActivity;
        discoverActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'mRecyclerView'", RecyclerView.class);
        discoverActivity.easylayout = (EasyRefreshLayout) Utils.findRequiredViewAsType(view, R.id.easylayout, "field 'easylayout'", EasyRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        DiscoverActivity discoverActivity = this.a;
        if (discoverActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        discoverActivity.mRecyclerView = null;
        discoverActivity.easylayout = null;
    }
}
