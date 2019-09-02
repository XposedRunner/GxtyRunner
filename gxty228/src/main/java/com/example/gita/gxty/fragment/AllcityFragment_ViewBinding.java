package com.example.gita.gxty.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ExpandableListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;

public class AllcityFragment_ViewBinding implements Unbinder {
    private AllcityFragment a;

    @UiThread
    public AllcityFragment_ViewBinding(AllcityFragment allcityFragment, View view) {
        this.a = allcityFragment;
        allcityFragment.mAllOfflineMapList = (ExpandableListView) Utils.findRequiredViewAsType(view, R.id.province_download_list, "field 'mAllOfflineMapList'", ExpandableListView.class);
    }

    @CallSuper
    public void unbind() {
        AllcityFragment allcityFragment = this.a;
        if (allcityFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        allcityFragment.mAllOfflineMapList = null;
    }
}
