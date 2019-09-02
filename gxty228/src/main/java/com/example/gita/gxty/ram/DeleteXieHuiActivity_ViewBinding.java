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

public class DeleteXieHuiActivity_ViewBinding implements Unbinder {
    private DeleteXieHuiActivity a;
    private View b;

    @UiThread
    public DeleteXieHuiActivity_ViewBinding(final DeleteXieHuiActivity deleteXieHuiActivity, View view) {
        this.a = deleteXieHuiActivity;
        deleteXieHuiActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        deleteXieHuiActivity.startTime = (TextView) Utils.findRequiredViewAsType(view, R.id.startTime, "field 'startTime'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.actionBtn, "method 'butterknifeOnItemClick'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ DeleteXieHuiActivity_ViewBinding b;

            public void doClick(View view) {
                deleteXieHuiActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        DeleteXieHuiActivity deleteXieHuiActivity = this.a;
        if (deleteXieHuiActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        deleteXieHuiActivity.titleBar = null;
        deleteXieHuiActivity.startTime = null;
        this.b.setOnClickListener(null);
        this.b = null;
    }
}
