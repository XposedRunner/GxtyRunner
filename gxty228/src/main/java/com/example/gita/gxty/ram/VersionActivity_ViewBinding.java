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

public class VersionActivity_ViewBinding implements Unbinder {
    private VersionActivity a;
    private View b;

    @UiThread
    public VersionActivity_ViewBinding(final VersionActivity versionActivity, View view) {
        this.a = versionActivity;
        versionActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        versionActivity.versionName = (TextView) Utils.findRequiredViewAsType(view, R.id.versionName, "field 'versionName'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.checkUpdateBtn, "method 'butterknifeOnItemClick'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ VersionActivity_ViewBinding b;

            public void doClick(View view) {
                versionActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        VersionActivity versionActivity = this.a;
        if (versionActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        versionActivity.titleBar = null;
        versionActivity.versionName = null;
        this.b.setOnClickListener(null);
        this.b = null;
    }
}
