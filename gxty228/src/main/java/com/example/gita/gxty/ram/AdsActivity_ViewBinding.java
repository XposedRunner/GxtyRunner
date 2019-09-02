package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;

public class AdsActivity_ViewBinding implements Unbinder {
    private AdsActivity a;

    @UiThread
    public AdsActivity_ViewBinding(AdsActivity adsActivity, View view) {
        this.a = adsActivity;
        adsActivity.ads_timeTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.ads_timeTxt, "field 'ads_timeTxt'", TextView.class);
        adsActivity.ads_typeTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.ads_typeTxt, "field 'ads_typeTxt'", TextView.class);
        adsActivity.ads_txt2 = (TextView) Utils.findRequiredViewAsType(view, R.id.ads_txt2, "field 'ads_txt2'", TextView.class);
        adsActivity.ads_img_layout1 = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.ads_img_layout1, "field 'ads_img_layout1'", FrameLayout.class);
        adsActivity.ads_img1 = (ImageView) Utils.findRequiredViewAsType(view, R.id.ads_img1, "field 'ads_img1'", ImageView.class);
        adsActivity.ads_img_layout2 = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.ads_img_layout2, "field 'ads_img_layout2'", FrameLayout.class);
    }

    @CallSuper
    public void unbind() {
        AdsActivity adsActivity = this.a;
        if (adsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        adsActivity.ads_timeTxt = null;
        adsActivity.ads_typeTxt = null;
        adsActivity.ads_txt2 = null;
        adsActivity.ads_img_layout1 = null;
        adsActivity.ads_img1 = null;
        adsActivity.ads_img_layout2 = null;
    }
}
