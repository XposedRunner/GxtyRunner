package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class OfflinemapActivity_ViewBinding implements Unbinder {
    private OfflinemapActivity a;
    private View b;

    @UiThread
    public OfflinemapActivity_ViewBinding(final OfflinemapActivity offlinemapActivity, View view) {
        this.a = offlinemapActivity;
        offlinemapActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        offlinemapActivity.fl_content = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.fl_content, "field 'fl_content'", FrameLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.choose, "field 'choose' and method 'butterknifeOnItemClick'");
        offlinemapActivity.choose = (LinearLayout) Utils.castView(findRequiredView, R.id.choose, "field 'choose'", LinearLayout.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ OfflinemapActivity_ViewBinding b;

            public void doClick(View view) {
                offlinemapActivity.butterknifeOnItemClick(view);
            }
        });
        offlinemapActivity.city = (TextView) Utils.findRequiredViewAsType(view, R.id.city, "field 'city'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        OfflinemapActivity offlinemapActivity = this.a;
        if (offlinemapActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        offlinemapActivity.titleBar = null;
        offlinemapActivity.fl_content = null;
        offlinemapActivity.choose = null;
        offlinemapActivity.city = null;
        this.b.setOnClickListener(null);
        this.b = null;
    }
}
