package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;

public class SportActivity_ViewBinding implements Unbinder {
    private SportActivity a;
    private View b;
    private View c;
    private View d;
    private View e;

    @UiThread
    public SportActivity_ViewBinding(final SportActivity sportActivity, View view) {
        this.a = sportActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.sport_actionBtn, "field 'sport_actionBtn' and method 'butterknifeOnItemClick'");
        sportActivity.sport_actionBtn = (TextView) Utils.castView(findRequiredView, R.id.sport_actionBtn, "field 'sport_actionBtn'", TextView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ SportActivity_ViewBinding b;

            public void doClick(View view) {
                sportActivity.butterknifeOnItemClick(view);
            }
        });
        sportActivity.xuexiaoTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_xuexiaoTxt, "field 'xuexiaoTxt'", TextView.class);
        sportActivity.sport_kmTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_kmTxt, "field 'sport_kmTxt'", TextView.class);
        sportActivity.sport_mubiaoTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_mubiaoTxt, "field 'sport_mubiaoTxt'", TextView.class);
        sportActivity.sport_youxiaoTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_youxiaoTxt, "field 'sport_youxiaoTxt'", TextView.class);
        sportActivity.sport_renshuTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_renshuTxt, "field 'sport_renshuTxt'", TextView.class);
        sportActivity.sport_lastTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sport_lastTxt, "field 'sport_lastTxt'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.sport_actionBtnleft, "method 'butterknifeOnItemClick'");
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ SportActivity_ViewBinding b;

            public void doClick(View view) {
                sportActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.sport_actionBtnright, "method 'butterknifeOnItemClick'");
        this.d = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ SportActivity_ViewBinding b;

            public void doClick(View view) {
                sportActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.sport_signBtn, "method 'butterknifeOnItemClick'");
        this.e = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ SportActivity_ViewBinding b;

            public void doClick(View view) {
                sportActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        SportActivity sportActivity = this.a;
        if (sportActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        sportActivity.sport_actionBtn = null;
        sportActivity.xuexiaoTxt = null;
        sportActivity.sport_kmTxt = null;
        sportActivity.sport_mubiaoTxt = null;
        sportActivity.sport_youxiaoTxt = null;
        sportActivity.sport_renshuTxt = null;
        sportActivity.sport_lastTxt = null;
        this.b.setOnClickListener(null);
        this.b = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
    }
}
