package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class MyXieHuiActivity_ViewBinding implements Unbinder {
    private MyXieHuiActivity a;

    @UiThread
    public MyXieHuiActivity_ViewBinding(MyXieHuiActivity myXieHuiActivity, View view) {
        this.a = myXieHuiActivity;
        myXieHuiActivity.my_course = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.my_course, "field 'my_course'", RecyclerView.class);
        myXieHuiActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        myXieHuiActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'mRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        MyXieHuiActivity myXieHuiActivity = this.a;
        if (myXieHuiActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        myXieHuiActivity.my_course = null;
        myXieHuiActivity.titleBar = null;
        myXieHuiActivity.mRecyclerView = null;
    }
}
