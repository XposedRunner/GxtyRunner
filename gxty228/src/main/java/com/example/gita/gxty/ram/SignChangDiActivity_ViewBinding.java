package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.CircleProgressButton;
import com.example.gita.gxty.weiget.TitleBar;

public class SignChangDiActivity_ViewBinding implements Unbinder {
    private SignChangDiActivity a;

    @UiThread
    public SignChangDiActivity_ViewBinding(SignChangDiActivity signChangDiActivity, View view) {
        this.a = signChangDiActivity;
        signChangDiActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        signChangDiActivity.sport_timeTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_timeTxt, "field 'sport_timeTxt'", TextView.class);
        signChangDiActivity.sport_mubiaoTimeTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_mubiaoTimeTxt, "field 'sport_mubiaoTimeTxt'", TextView.class);
        signChangDiActivity.sport_pauseTimeTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_pauseTimeTxt, "field 'sport_pauseTimeTxt'", TextView.class);
        signChangDiActivity.sport_pauseBtn = (CircleProgressButton) Utils.findRequiredViewAsType(view, R.id.sport_pauseBtn, "field 'sport_pauseBtn'", CircleProgressButton.class);
        signChangDiActivity.sport_startBtn = (CircleProgressButton) Utils.findRequiredViewAsType(view, R.id.sport_startBtn, "field 'sport_startBtn'", CircleProgressButton.class);
        signChangDiActivity.sport_stopBtn = (CircleProgressButton) Utils.findRequiredViewAsType(view, R.id.sport_stopBtn, "field 'sport_stopBtn'", CircleProgressButton.class);
    }

    @CallSuper
    public void unbind() {
        SignChangDiActivity signChangDiActivity = this.a;
        if (signChangDiActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        signChangDiActivity.titleBar = null;
        signChangDiActivity.sport_timeTxt = null;
        signChangDiActivity.sport_mubiaoTimeTxt = null;
        signChangDiActivity.sport_pauseTimeTxt = null;
        signChangDiActivity.sport_pauseBtn = null;
        signChangDiActivity.sport_startBtn = null;
        signChangDiActivity.sport_stopBtn = null;
    }
}
