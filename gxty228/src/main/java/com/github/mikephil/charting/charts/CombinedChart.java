package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.highlight.CombinedHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.CombinedDataProvider;
import com.github.mikephil.charting.renderer.CombinedChartRenderer;

public class CombinedChart extends BarLineChartBase<CombinedData> implements CombinedDataProvider {
    private boolean mDrawBarShadow = false;
    protected DrawOrder[] mDrawOrder;
    private boolean mDrawValueAboveBar = true;
    protected boolean mHighlightFullBarEnabled = false;

    public enum DrawOrder {
        BAR,
        BUBBLE,
        LINE,
        CANDLE,
        SCATTER
    }

    public CombinedChart(Context context) {
        super(context);
    }

    public CombinedChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CombinedChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void init() {
        super.init();
        this.mDrawOrder = new DrawOrder[]{DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.LINE, DrawOrder.CANDLE, DrawOrder.SCATTER};
        setHighlighter(new CombinedHighlighter(this, this));
        setHighlightFullBarEnabled(true);
        this.mRenderer = new CombinedChartRenderer(this, this.mAnimator, this.mViewPortHandler);
    }

    public CombinedData getCombinedData() {
        return (CombinedData) this.mData;
    }

    public void setData(CombinedData combinedData) {
        super.setData(combinedData);
        setHighlighter(new CombinedHighlighter(this, this));
        ((CombinedChartRenderer) this.mRenderer).createRenderers();
        this.mRenderer.initBuffers();
    }

    public Highlight getHighlightByTouchPoint(float f, float f2) {
        if (this.mData == null) {
            Log.e(Chart.LOG_TAG, "Can't select by touch. No data set.");
            return null;
        }
        Highlight highlight = getHighlighter().getHighlight(f, f2);
        if (highlight == null || !isHighlightFullBarEnabled()) {
            return highlight;
        }
        return new Highlight(highlight.getX(), highlight.getY(), highlight.getXPx(), highlight.getYPx(), highlight.getDataSetIndex(), -1, highlight.getAxis());
    }

    public LineData getLineData() {
        if (this.mData == null) {
            return null;
        }
        return ((CombinedData) this.mData).getLineData();
    }

    public BarData getBarData() {
        if (this.mData == null) {
            return null;
        }
        return ((CombinedData) this.mData).getBarData();
    }

    public ScatterData getScatterData() {
        if (this.mData == null) {
            return null;
        }
        return ((CombinedData) this.mData).getScatterData();
    }

    public CandleData getCandleData() {
        if (this.mData == null) {
            return null;
        }
        return ((CombinedData) this.mData).getCandleData();
    }

    public BubbleData getBubbleData() {
        if (this.mData == null) {
            return null;
        }
        return ((CombinedData) this.mData).getBubbleData();
    }

    public boolean isDrawBarShadowEnabled() {
        return this.mDrawBarShadow;
    }

    public boolean isDrawValueAboveBarEnabled() {
        return this.mDrawValueAboveBar;
    }

    public void setDrawValueAboveBar(boolean z) {
        this.mDrawValueAboveBar = z;
    }

    public void setDrawBarShadow(boolean z) {
        this.mDrawBarShadow = z;
    }

    public void setHighlightFullBarEnabled(boolean z) {
        this.mHighlightFullBarEnabled = z;
    }

    public boolean isHighlightFullBarEnabled() {
        return this.mHighlightFullBarEnabled;
    }

    public DrawOrder[] getDrawOrder() {
        return this.mDrawOrder;
    }

    public void setDrawOrder(DrawOrder[] drawOrderArr) {
        if (drawOrderArr != null && drawOrderArr.length > 0) {
            this.mDrawOrder = drawOrderArr;
        }
    }
}
