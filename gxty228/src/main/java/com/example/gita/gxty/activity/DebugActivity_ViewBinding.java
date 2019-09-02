package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class DebugActivity_ViewBinding implements Unbinder {
    private DebugActivity a;

    @UiThread
    public DebugActivity_ViewBinding(DebugActivity debugActivity, View view) {
        this.a = debugActivity;
        debugActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        debugActivity.debugInfo = (TextView) Utils.findRequiredViewAsType(view, R.id.debugInfo, "field 'debugInfo'", TextView.class);
        debugActivity.seq1 = (TextView) Utils.findRequiredViewAsType(view, R.id.seq1, "field 'seq1'", TextView.class);
        debugActivity.seq2 = (TextView) Utils.findRequiredViewAsType(view, R.id.seq2, "field 'seq2'", TextView.class);
        debugActivity.seq3 = (TextView) Utils.findRequiredViewAsType(view, R.id.seq3, "field 'seq3'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        DebugActivity debugActivity = this.a;
        if (debugActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        debugActivity.titleBar = null;
        debugActivity.debugInfo = null;
        debugActivity.seq1 = null;
        debugActivity.seq2 = null;
        debugActivity.seq3 = null;
    }
}
