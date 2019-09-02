package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class ChooseXieHuiActivity_ViewBinding implements Unbinder {
    private ChooseXieHuiActivity a;

    @UiThread
    public ChooseXieHuiActivity_ViewBinding(ChooseXieHuiActivity chooseXieHuiActivity, View view) {
        this.a = chooseXieHuiActivity;
        chooseXieHuiActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        chooseXieHuiActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'mRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        ChooseXieHuiActivity chooseXieHuiActivity = this.a;
        if (chooseXieHuiActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        chooseXieHuiActivity.titleBar = null;
        chooseXieHuiActivity.mRecyclerView = null;
    }
}
