package com.example.gita.gxty.activity;

import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataRank;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.Point;
import com.example.gita.gxty.model.Trend;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.aletview.AlertView;
import com.example.gita.gxty.weiget.aletview.AlertView.Style;
import com.example.gita.gxty.weiget.aletview.c;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TrendActivity extends BaseActivity implements c {
    @BindView(2131755356)
    protected LinearLayout choose;
    @BindView(2131755390)
    protected TextView duration;
    private int f = 1;
    private ArrayList<Point> g = new ArrayList();
    @BindView(2131755285)
    protected TextView length;
    @BindView(2131755386)
    protected LineChart lineChart;
    @BindView(2131755393)
    protected TextView spead;
    @BindView(2131755192)
    protected TitleBar titleBar;
    @BindView(2131755454)
    protected TextView yue;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.titleBar.setLeftImageResource(R.mipmap.back);
        this.titleBar.setTitle((CharSequence) "跑步总体趋势");
        this.titleBar.setTitleColor(-1);
        this.titleBar.setLeftClickListener(new OnClickListener(this) {
            final /* synthetic */ TrendActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        b();
        d(this.f);
    }

    private void d(int i) {
        Object dataRank = new DataRank();
        dataRank.userid = q.a(c()).b();
        dataRank.type = "" + i;
        DataBean a = b.a(dataRank);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("run/run_qs")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<Trend>>(this, this) {
            final /* synthetic */ TrendActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<Trend>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                Trend trend = (Trend) ((LzyResponse) aVar.c()).data;
                this.a.g.clear();
                if (trend.info != null) {
                    this.a.g.addAll(trend.info);
                }
                if (this.a.g.size() < 2) {
                    Point point = new Point(0.0f, 0.0f);
                    Point point2 = new Point(0.5f, 0.0f);
                    this.a.g.add(point);
                    this.a.g.add(point2);
                }
                this.a.spead.setText(trend.totalSp);
                this.a.duration.setText(trend.totalDr);
                this.a.length.setText(trend.totalGl);
                this.a.o();
            }
        });
    }

    private void b() {
        this.lineChart.getDescription().setEnabled(false);
        this.lineChart.setScaleEnabled(false);
        this.lineChart.getAxisRight().setEnabled(false);
        this.lineChart.setNoDataText("没有数据");
        this.lineChart.setNoDataTextColor(-1);
        this.lineChart.setDrawGridBackground(false);
        this.lineChart.setDrawBorders(false);
        this.lineChart.setTouchEnabled(false);
        this.lineChart.setExtraOffsets(15.0f, 15.0f, 15.0f, 15.0f);
        Legend legend = this.lineChart.getLegend();
        legend.setForm(LegendForm.CIRCLE);
        legend.setTextSize(10.0f);
        legend.setTextColor(-1);
        legend.setEnabled(false);
        XAxis xAxis = this.lineChart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setGranularity(1.0f);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(12.0f);
        xAxis.setEnabled(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawLabels(true);
        xAxis.setTextColor(-1);
        YAxis axisLeft = this.lineChart.getAxisLeft();
        axisLeft.setStartAtZero(true);
        axisLeft.setTextSize(12.0f);
        axisLeft.setDrawGridLines(false);
        axisLeft.setEnabled(true);
        axisLeft.setDrawAxisLine(true);
        axisLeft.setDrawLabels(true);
        axisLeft.setTextColor(-1);
        this.lineChart.invalidate();
    }

    private void o() {
        List arrayList = new ArrayList();
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            Point point = (Point) it.next();
            arrayList.add(new Entry(point.x, point.y));
        }
        LineDataSet lineDataSet = new LineDataSet(arrayList, null);
        lineDataSet.setColor(getResources().getColor(R.color.green));
        lineDataSet.setCircleColor(ViewCompat.MEASURED_STATE_MASK);
        lineDataSet.setLineWidth(2.0f);
        lineDataSet.setCircleRadius(3.0f);
        lineDataSet.enableDashedHighlightLine(10.0f, 5.0f, 0.0f);
        lineDataSet.setHighlightLineWidth(2.0f);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setHighLightColor(SupportMenu.CATEGORY_MASK);
        lineDataSet.setValueTextSize(12.0f);
        lineDataSet.setDrawFilled(false);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);
        this.lineChart.setData(new LineData(lineDataSet));
        this.lineChart.invalidate();
    }

    @OnClick({2131755356})
    void butterknifeOnItemClick(View view) {
        switch (view.getId()) {
            case R.id.choose /*2131755356*/:
                String str = null;
                new AlertView(null, str, "取消", null, new String[]{"本月", "本学期"}, this, Style.ActionSheet, this).e();
                return;
            default:
                return;
        }
    }

    public void a(Object obj, int i) {
        this.g.clear();
        if (i == 0) {
            this.f = 1;
            this.yue.setText("本月");
        } else if (i == 1) {
            this.f = 2;
            this.yue.setText("本学期");
        }
        d(this.f);
    }

    protected int a() {
        return R.layout.activity_trend;
    }
}
