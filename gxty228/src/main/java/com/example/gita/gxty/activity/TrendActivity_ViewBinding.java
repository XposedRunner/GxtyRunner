package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;
import com.github.mikephil.charting.charts.LineChart;

public class TrendActivity_ViewBinding implements Unbinder {
    private TrendActivity a;
    private View b;

    @UiThread
    public TrendActivity_ViewBinding(final TrendActivity trendActivity, View view) {
        this.a = trendActivity;
        trendActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.choose, "field 'choose' and method 'butterknifeOnItemClick'");
        trendActivity.choose = (LinearLayout) Utils.castView(findRequiredView, R.id.choose, "field 'choose'", LinearLayout.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ TrendActivity_ViewBinding b;

            public void doClick(View view) {
                trendActivity.butterknifeOnItemClick(view);
            }
        });
        trendActivity.yue = (TextView) Utils.findRequiredViewAsType(view, R.id.yue, "field 'yue'", TextView.class);
        trendActivity.lineChart = (LineChart) Utils.findRequiredViewAsType(view, R.id.lineChart, "field 'lineChart'", LineChart.class);
        trendActivity.length = (TextView) Utils.findRequiredViewAsType(view, R.id.length, "field 'length'", TextView.class);
        trendActivity.duration = (TextView) Utils.findRequiredViewAsType(view, R.id.duration, "field 'duration'", TextView.class);
        trendActivity.spead = (TextView) Utils.findRequiredViewAsType(view, R.id.spead, "field 'spead'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        TrendActivity trendActivity = this.a;
        if (trendActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        trendActivity.titleBar = null;
        trendActivity.choose = null;
        trendActivity.yue = null;
        trendActivity.lineChart = null;
        trendActivity.length = null;
        trendActivity.duration = null;
        trendActivity.spead = null;
        this.b.setOnClickListener(null);
        this.b = null;
    }
}
