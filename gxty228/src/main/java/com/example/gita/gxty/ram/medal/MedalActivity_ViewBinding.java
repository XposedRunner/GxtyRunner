package com.example.gita.gxty.ram.medal;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ajguan.library.EasyRefreshLayout;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class MedalActivity_ViewBinding implements Unbinder {
    private MedalActivity a;

    @UiThread
    public MedalActivity_ViewBinding(MedalActivity medalActivity, View view) {
        this.a = medalActivity;
        medalActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        medalActivity.gv1 = (MyGridView) Utils.findRequiredViewAsType(view, R.id.gv1, "field 'gv1'", MyGridView.class);
        medalActivity.gv2 = (MyGridView) Utils.findRequiredViewAsType(view, R.id.gv2, "field 'gv2'", MyGridView.class);
        medalActivity.gv3 = (MyGridView) Utils.findRequiredViewAsType(view, R.id.gv3, "field 'gv3'", MyGridView.class);
        medalActivity.gv4 = (MyGridView) Utils.findRequiredViewAsType(view, R.id.gv4, "field 'gv4'", MyGridView.class);
        medalActivity.gv5 = (MyGridView) Utils.findRequiredViewAsType(view, R.id.gv5, "field 'gv5'", MyGridView.class);
        medalActivity.topText = (TextView) Utils.findRequiredViewAsType(view, R.id.topText, "field 'topText'", TextView.class);
        medalActivity.topImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.topImg, "field 'topImg'", ImageView.class);
        medalActivity.text1 = (TextView) Utils.findRequiredViewAsType(view, R.id.text1, "field 'text1'", TextView.class);
        medalActivity.text2 = (TextView) Utils.findRequiredViewAsType(view, 2131755032, "field 'text2'", TextView.class);
        medalActivity.text3 = (TextView) Utils.findRequiredViewAsType(view, R.id.text3, "field 'text3'", TextView.class);
        medalActivity.text4 = (TextView) Utils.findRequiredViewAsType(view, R.id.text4, "field 'text4'", TextView.class);
        medalActivity.text5 = (TextView) Utils.findRequiredViewAsType(view, R.id.text5, "field 'text5'", TextView.class);
        medalActivity.easylayout = (EasyRefreshLayout) Utils.findRequiredViewAsType(view, R.id.easylayout, "field 'easylayout'", EasyRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        MedalActivity medalActivity = this.a;
        if (medalActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        medalActivity.titleBar = null;
        medalActivity.gv1 = null;
        medalActivity.gv2 = null;
        medalActivity.gv3 = null;
        medalActivity.gv4 = null;
        medalActivity.gv5 = null;
        medalActivity.topText = null;
        medalActivity.topImg = null;
        medalActivity.text1 = null;
        medalActivity.text2 = null;
        medalActivity.text3 = null;
        medalActivity.text4 = null;
        medalActivity.text5 = null;
        medalActivity.easylayout = null;
    }
}
