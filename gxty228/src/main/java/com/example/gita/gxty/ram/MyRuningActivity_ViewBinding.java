package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.adam.gpsstatus.GpsStatusImageView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.CircleProgressButton;

public class MyRuningActivity_ViewBinding implements Unbinder {
    private MyRuningActivity a;

    @UiThread
    public MyRuningActivity_ViewBinding(MyRuningActivity myRuningActivity, View view) {
        this.a = myRuningActivity;
        myRuningActivity.sport_lockBtn = Utils.findRequiredView(view, R.id.sport_lockBtn, "field 'sport_lockBtn'");
        myRuningActivity.sport_pauseBtn = Utils.findRequiredView(view, R.id.sport_pauseBtn, "field 'sport_pauseBtn'");
        myRuningActivity.sport_startBtn = Utils.findRequiredView(view, R.id.sport_startBtn, "field 'sport_startBtn'");
        myRuningActivity.sport_stopBtn = (CircleProgressButton) Utils.findRequiredViewAsType(view, R.id.sport_stopBtn, "field 'sport_stopBtn'", CircleProgressButton.class);
        myRuningActivity.sport_addlockBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.sport_addlockBtn, "field 'sport_addlockBtn'", ImageView.class);
        myRuningActivity.sport_mapUIImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.sport_mapUIImg, "field 'sport_mapUIImg'", ImageView.class);
        myRuningActivity.sport_topLayout = Utils.findRequiredView(view, R.id.sport_topLayout, "field 'sport_topLayout'");
        myRuningActivity.sport_bottomLayout = Utils.findRequiredView(view, R.id.sport_bottomLayout, "field 'sport_bottomLayout'");
        myRuningActivity.sport_mapBottomLayout = Utils.findRequiredView(view, R.id.sport_mapBottomLayout, "field 'sport_mapBottomLayout'");
        myRuningActivity.sport_mapLayout = Utils.findRequiredView(view, R.id.sport_mapLayout, "field 'sport_mapLayout'");
        myRuningActivity.sport_kmTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_kmTxt, "field 'sport_kmTxt'", TextView.class);
        myRuningActivity.sport_peisuTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_peisuTxt, "field 'sport_peisuTxt'", TextView.class);
        myRuningActivity.sport_fenmiaoTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_fenmiaoTxt, "field 'sport_fenmiaoTxt'", TextView.class);
        myRuningActivity.sport_bupinTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_bupinTxt, "field 'sport_bupinTxt'", TextView.class);
        myRuningActivity.sport_checkTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_checkTxt, "field 'sport_checkTxt'", TextView.class);
        myRuningActivity.sport_mubiaokmTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_mubiaokmTxt, "field 'sport_mubiaokmTxt'", TextView.class);
        myRuningActivity.sportTxt_2_1 = (TextView) Utils.findRequiredViewAsType(view, R.id.sportTxt_2_1, "field 'sportTxt_2_1'", TextView.class);
        myRuningActivity.sportTxt_2_2 = (TextView) Utils.findRequiredViewAsType(view, R.id.sportTxt_2_2, "field 'sportTxt_2_2'", TextView.class);
        myRuningActivity.sportTxt_2_3 = (TextView) Utils.findRequiredViewAsType(view, R.id.sportTxt_2_3, "field 'sportTxt_2_3'", TextView.class);
        myRuningActivity.gpsImage = (GpsStatusImageView) Utils.findRequiredViewAsType(view, R.id.gpsImage, "field 'gpsImage'", GpsStatusImageView.class);
    }

    @CallSuper
    public void unbind() {
        MyRuningActivity myRuningActivity = this.a;
        if (myRuningActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        myRuningActivity.sport_lockBtn = null;
        myRuningActivity.sport_pauseBtn = null;
        myRuningActivity.sport_startBtn = null;
        myRuningActivity.sport_stopBtn = null;
        myRuningActivity.sport_addlockBtn = null;
        myRuningActivity.sport_mapUIImg = null;
        myRuningActivity.sport_topLayout = null;
        myRuningActivity.sport_bottomLayout = null;
        myRuningActivity.sport_mapBottomLayout = null;
        myRuningActivity.sport_mapLayout = null;
        myRuningActivity.sport_kmTxt = null;
        myRuningActivity.sport_peisuTxt = null;
        myRuningActivity.sport_fenmiaoTxt = null;
        myRuningActivity.sport_bupinTxt = null;
        myRuningActivity.sport_checkTxt = null;
        myRuningActivity.sport_mubiaokmTxt = null;
        myRuningActivity.sportTxt_2_1 = null;
        myRuningActivity.sportTxt_2_2 = null;
        myRuningActivity.sportTxt_2_3 = null;
        myRuningActivity.gpsImage = null;
    }
}
