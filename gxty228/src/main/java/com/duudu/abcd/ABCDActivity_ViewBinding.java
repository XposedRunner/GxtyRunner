package com.duudu.abcd;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class ABCDActivity_ViewBinding implements Unbinder {
    private ABCDActivity a;

    @UiThread
    public ABCDActivity_ViewBinding(ABCDActivity aBCDActivity, View view) {
        this.a = aBCDActivity;
        aBCDActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        aBCDActivity.text = (TextView) Utils.findRequiredViewAsType(view, 2131755031, "field 'text'", TextView.class);
        aBCDActivity.text1 = (TextView) Utils.findRequiredViewAsType(view, R.id.text1, "field 'text1'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ABCDActivity aBCDActivity = this.a;
        if (aBCDActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        aBCDActivity.titleBar = null;
        aBCDActivity.text = null;
        aBCDActivity.text1 = null;
    }
}
