package com.example.gita.gxty.ram.medal;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class MedalDetailActivity_ViewBinding implements Unbinder {
    private MedalDetailActivity a;

    @UiThread
    public MedalDetailActivity_ViewBinding(MedalDetailActivity medalDetailActivity, View view) {
        this.a = medalDetailActivity;
        medalDetailActivity.text1 = (TextView) Utils.findRequiredViewAsType(view, R.id.text1, "field 'text1'", TextView.class);
        medalDetailActivity.text2 = (TextView) Utils.findRequiredViewAsType(view, 2131755032, "field 'text2'", TextView.class);
        medalDetailActivity.text3 = (TextView) Utils.findRequiredViewAsType(view, R.id.text3, "field 'text3'", TextView.class);
        medalDetailActivity.img1 = (ImageView) Utils.findRequiredViewAsType(view, R.id.img1, "field 'img1'", ImageView.class);
        medalDetailActivity.img2 = (ImageView) Utils.findRequiredViewAsType(view, R.id.img2, "field 'img2'", ImageView.class);
        medalDetailActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
    }

    @CallSuper
    public void unbind() {
        MedalDetailActivity medalDetailActivity = this.a;
        if (medalDetailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        medalDetailActivity.text1 = null;
        medalDetailActivity.text2 = null;
        medalDetailActivity.text3 = null;
        medalDetailActivity.img1 = null;
        medalDetailActivity.img2 = null;
        medalDetailActivity.titleBar = null;
    }
}
