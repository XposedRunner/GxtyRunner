package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.CombinedChart.DrawOrder;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class CombinedChartRenderer extends DataRenderer {
    protected WeakReference<Chart> mChart;
    protected List<Highlight> mHighlightBuffer = new ArrayList();
    protected List<DataRenderer> mRenderers = new ArrayList(5);

    public CombinedChartRenderer(CombinedChart combinedChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = new WeakReference(combinedChart);
        createRenderers();
    }

    public void createRenderers() {
        this.mRenderers.clear();
        CombinedChart combinedChart = (CombinedChart) this.mChart.get();
        if (combinedChart != null) {
            for (DrawOrder drawOrder : combinedChart.getDrawOrder()) {
                switch (drawOrder) {
                    case BAR:
                        if (combinedChart.getBarData() == null) {
                            break;
                        }
                        this.mRenderers.add(new BarChartRenderer(combinedChart, this.mAnimator, this.mViewPortHandler));
                        break;
                    case BUBBLE:
                        if (combinedChart.getBubbleData() == null) {
                            break;
                        }
                        this.mRenderers.add(new BubbleChartRenderer(combinedChart, this.mAnimator, this.mViewPortHandler));
                        break;
                    case LINE:
                        if (combinedChart.getLineData() == null) {
                            break;
                        }
                        this.mRenderers.add(new LineChartRenderer(combinedChart, this.mAnimator, this.mViewPortHandler));
                        break;
                    case CANDLE:
                        if (combinedChart.getCandleData() == null) {
                            break;
                        }
                        this.mRenderers.add(new CandleStickChartRenderer(combinedChart, this.mAnimator, this.mViewPortHandler));
                        break;
                    case SCATTER:
                        if (combinedChart.getScatterData() == null) {
                            break;
                        }
                        this.mRenderers.add(new ScatterChartRenderer(combinedChart, this.mAnimator, this.mViewPortHandler));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void initBuffers() {
        for (DataRenderer initBuffers : this.mRenderers) {
            initBuffers.initBuffers();
        }
    }

    public void drawData(Canvas canvas) {
        for (DataRenderer drawData : this.mRenderers) {
            drawData.drawData(canvas);
        }
    }

    public void drawValues(Canvas canvas) {
        for (DataRenderer drawValues : this.mRenderers) {
            drawValues.drawValues(canvas);
        }
    }

    public void drawExtras(Canvas canvas) {
        for (DataRenderer drawExtras : this.mRenderers) {
            drawExtras.drawExtras(canvas);
        }
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        Chart chart = (Chart) this.mChart.get();
        if (chart != null) {
            for (DataRenderer dataRenderer : this.mRenderers) {
                Object barData;
                int i;
                if (dataRenderer instanceof BarChartRenderer) {
                    barData = ((BarChartRenderer) dataRenderer).mChart.getBarData();
                } else if (dataRenderer instanceof LineChartRenderer) {
                    LineData lineData = ((LineChartRenderer) dataRenderer).mChart.getLineData();
                } else if (dataRenderer instanceof CandleStickChartRenderer) {
                    CandleData candleData = ((CandleStickChartRenderer) dataRenderer).mChart.getCandleData();
                } else if (dataRenderer instanceof ScatterChartRenderer) {
                    ScatterData scatterData = ((ScatterChartRenderer) dataRenderer).mChart.getScatterData();
                } else if (dataRenderer instanceof BubbleChartRenderer) {
                    BubbleData bubbleData = ((BubbleChartRenderer) dataRenderer).mChart.getBubbleData();
                } else {
                    barData = null;
                }
                if (barData == null) {
                    i = -1;
                } else {
                    i = ((CombinedData) chart.getData()).getAllData().indexOf(barData);
                }
                this.mHighlightBuffer.clear();
                for (Highlight highlight : highlightArr) {
                    if (highlight.getDataIndex() == i || highlight.getDataIndex() == -1) {
                        this.mHighlightBuffer.add(highlight);
                    }
                }
                dataRenderer.drawHighlighted(canvas, (Highlight[]) this.mHighlightBuffer.toArray(new Highlight[this.mHighlightBuffer.size()]));
            }
        }
    }

    public DataRenderer getSubRenderer(int i) {
        if (i >= this.mRenderers.size() || i < 0) {
            return null;
        }
        return (DataRenderer) this.mRenderers.get(i);
    }

    public List<DataRenderer> getSubRenderers() {
        return this.mRenderers;
    }

    public void setSubRenderers(List<DataRenderer> list) {
        this.mRenderers = list;
    }
}
