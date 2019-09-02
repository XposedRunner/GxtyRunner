package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class SignRecordListActivity_ViewBinding implements Unbinder {
    private SignRecordListActivity a;

    @UiThread
    public SignRecordListActivity_ViewBinding(SignRecordListActivity signRecordListActivity, View view) {
        this.a = signRecordListActivity;
        signRecordListActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        signRecordListActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'mRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        SignRecordListActivity signRecordListActivity = this.a;
        if (signRecordListActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        signRecordListActivity.titleBar = null;
        signRecordListActivity.mRecyclerView = null;
    }
}
