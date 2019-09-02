package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class RunRecordActivity_ViewBinding implements Unbinder {
    private RunRecordActivity a;

    @UiThread
    public RunRecordActivity_ViewBinding(RunRecordActivity runRecordActivity, View view) {
        this.a = runRecordActivity;
        runRecordActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        runRecordActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'mRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        RunRecordActivity runRecordActivity = this.a;
        if (runRecordActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        runRecordActivity.titleBar = null;
        runRecordActivity.mRecyclerView = null;
    }
}
