package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class JuBaoActivity_ViewBinding implements Unbinder {
    private JuBaoActivity a;

    @UiThread
    public JuBaoActivity_ViewBinding(JuBaoActivity juBaoActivity, View view) {
        this.a = juBaoActivity;
        juBaoActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        juBaoActivity.topicTxt = (EditText) Utils.findRequiredViewAsType(view, R.id.topicTxt, "field 'topicTxt'", EditText.class);
        juBaoActivity.camaraLayout = (GridView) Utils.findRequiredViewAsType(view, R.id.camaraLayout, "field 'camaraLayout'", GridView.class);
    }

    @CallSuper
    public void unbind() {
        JuBaoActivity juBaoActivity = this.a;
        if (juBaoActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        juBaoActivity.titleBar = null;
        juBaoActivity.topicTxt = null;
        juBaoActivity.camaraLayout = null;
    }
}
