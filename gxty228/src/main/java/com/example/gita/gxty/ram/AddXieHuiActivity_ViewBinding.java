package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class AddXieHuiActivity_ViewBinding implements Unbinder {
    private AddXieHuiActivity a;
    private View b;

    @UiThread
    public AddXieHuiActivity_ViewBinding(final AddXieHuiActivity addXieHuiActivity, View view) {
        this.a = addXieHuiActivity;
        addXieHuiActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        addXieHuiActivity.startTime = (TextView) Utils.findRequiredViewAsType(view, R.id.startTime, "field 'startTime'", TextView.class);
        addXieHuiActivity.countNum = (TextView) Utils.findRequiredViewAsType(view, R.id.countNum, "field 'countNum'", TextView.class);
        addXieHuiActivity.curNum = (TextView) Utils.findRequiredViewAsType(view, R.id.curNum, "field 'curNum'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.actionBtn, "method 'butterknifeOnItemClick'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ AddXieHuiActivity_ViewBinding b;

            public void doClick(View view) {
                addXieHuiActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        AddXieHuiActivity addXieHuiActivity = this.a;
        if (addXieHuiActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        addXieHuiActivity.titleBar = null;
        addXieHuiActivity.startTime = null;
        addXieHuiActivity.countNum = null;
        addXieHuiActivity.curNum = null;
        this.b.setOnClickListener(null);
        this.b = null;
    }
}
