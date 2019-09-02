package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.amap.api.maps.MapView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class ResultActivity_ViewBinding implements Unbinder {
    private ResultActivity a;

    @UiThread
    public ResultActivity_ViewBinding(ResultActivity resultActivity, View view) {
        this.a = resultActivity;
        resultActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        resultActivity.mapView = (MapView) Utils.findRequiredViewAsType(view, R.id.map, "field 'mapView'", MapView.class);
        resultActivity.tempImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.tempImg, "field 'tempImg'", ImageView.class);
        resultActivity.sport_kmTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_kmTxt, "field 'sport_kmTxt'", TextView.class);
        resultActivity.sport_timeTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_timeTxt, "field 'sport_timeTxt'", TextView.class);
        resultActivity.sport_peisuTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_peisuTxt, "field 'sport_peisuTxt'", TextView.class);
        resultActivity.sport_fenmiaoTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_fenmiaoTxt, "field 'sport_fenmiaoTxt'", TextView.class);
        resultActivity.sport_kaluliTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_kaluliTxt, "field 'sport_kaluliTxt'", TextView.class);
        resultActivity.sport_bupinTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_bupinTxt, "field 'sport_bupinTxt'", TextView.class);
        resultActivity.sport_statusTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_statusTxt, "field 'sport_statusTxt'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ResultActivity resultActivity = this.a;
        if (resultActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        resultActivity.titleBar = null;
        resultActivity.mapView = null;
        resultActivity.tempImg = null;
        resultActivity.sport_kmTxt = null;
        resultActivity.sport_timeTxt = null;
        resultActivity.sport_peisuTxt = null;
        resultActivity.sport_fenmiaoTxt = null;
        resultActivity.sport_kaluliTxt = null;
        resultActivity.sport_bupinTxt = null;
        resultActivity.sport_statusTxt = null;
    }
}
