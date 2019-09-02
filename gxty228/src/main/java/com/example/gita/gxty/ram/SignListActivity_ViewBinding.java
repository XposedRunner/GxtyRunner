package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class SignListActivity_ViewBinding implements Unbinder {
    private SignListActivity a;

    @UiThread
    public SignListActivity_ViewBinding(SignListActivity signListActivity, View view) {
        this.a = signListActivity;
        signListActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        signListActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'mRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        SignListActivity signListActivity = this.a;
        if (signListActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        signListActivity.titleBar = null;
        signListActivity.mRecyclerView = null;
    }
}
