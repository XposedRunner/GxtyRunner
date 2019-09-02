package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.DataSet.Rounding;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import java.util.ArrayList;
import java.util.List;

public class ChartHighlighter<T extends BarLineScatterCandleBubbleDataProvider> implements IHighlighter {
    protected T mChart;
    protected List<Highlight> mHighlightBuffer = new ArrayList();

    public ChartHighlighter(T t) {
        this.mChart = t;
    }

    public Highlight getHighlight(float f, float f2) {
        MPPointD valsForTouch = getValsForTouch(f, f2);
        float f3 = (float) valsForTouch.x;
        MPPointD.recycleInstance(valsForTouch);
        return getHighlightForX(f3, f, f2);
    }

    protected MPPointD getValsForTouch(float f, float f2) {
        return this.mChart.getTransformer(AxisDependency.LEFT).getValuesByTouchPoint(f, f2);
    }

    protected Highlight getHighlightForX(float f, float f2, float f3) {
        List highlightsAtXValue = getHighlightsAtXValue(f, f2, f3);
        if (highlightsAtXValue.isEmpty()) {
            return null;
        }
        return getClosestHighlightByPixel(highlightsAtXValue, f2, f3, getMinimumDistance(highlightsAtXValue, f3, AxisDependency.LEFT) < getMinimumDistance(highlightsAtXValue, f3, AxisDependency.RIGHT) ? AxisDependency.LEFT : AxisDependency.RIGHT, this.mChart.getMaxHighlightDistance());
    }

    protected float getMinimumDistance(List<Highlight> list, float f, AxisDependency axisDependency) {
        float f2 = Float.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            Highlight highlight = (Highlight) list.get(i);
            if (highlight.getAxis() == axisDependency) {
                float abs = Math.abs(getHighlightPos(highlight) - f);
                if (abs < f2) {
                    f2 = abs;
                }
            }
        }
        return f2;
    }

    protected float getHighlightPos(Highlight highlight) {
        return highlight.getYPx();
    }

    protected List<Highlight> getHighlightsAtXValue(float f, float f2, float f3) {
        this.mHighlightBuffer.clear();
        BarLineScatterCandleBubbleData data = getData();
        if (data == null) {
            return this.mHighlightBuffer;
        }
        int dataSetCount = data.getDataSetCount();
        for (int i = 0; i < dataSetCount; i++) {
            IDataSet dataSetByIndex = data.getDataSetByIndex(i);
            if (dataSetByIndex.isHighlightEnabled()) {
                this.mHighlightBuffer.addAll(buildHighlights(dataSetByIndex, i, f, Rounding.CLOSEST));
            }
        }
        return this.mHighlightBuffer;
    }

    protected List<Highlight> buildHighlights(IDataSet iDataSet, int i, float f, Rounding rounding) {
        ArrayList arrayList = new ArrayList();
        List entriesForXValue = iDataSet.getEntriesForXValue(f);
        if (entriesForXValue.size() == 0) {
            Entry entryForXValue = iDataSet.getEntryForXValue(f, Float.NaN, rounding);
            if (entryForXValue != null) {
                entriesForXValue = iDataSet.getEntriesForXValue(entryForXValue.getX());
            }
        }
        if (r2.size() == 0) {
            return arrayList;
        }
        for (Entry entry : r2) {
            MPPointD pixelForValues = this.mChart.getTransformer(iDataSet.getAxisDependency()).getPixelForValues(entry.getX(), entry.getY());
            arrayList.add(new Highlight(entry.getX(), entry.getY(), (float) pixelForValues.x, (float) pixelForValues.y, i, iDataSet.getAxisDependency()));
        }
        return arrayList;
    }

    public Highlight getClosestHighlightByPixel(List<Highlight> list, float f, float f2, AxisDependency axisDependency, float f3) {
        Highlight highlight = null;
        int i = 0;
        float f4 = f3;
        while (i < list.size()) {
            Highlight highlight2;
            float f5;
            Highlight highlight3 = (Highlight) list.get(i);
            if (axisDependency == null || highlight3.getAxis() == axisDependency) {
                float distance = getDistance(f, f2, highlight3.getXPx(), highlight3.getYPx());
                if (distance < f4) {
                    highlight2 = highlight3;
                    f5 = distance;
                    i++;
                    highlight = highlight2;
                    f4 = f5;
                }
            }
            f5 = f4;
            highlight2 = highlight;
            i++;
            highlight = highlight2;
            f4 = f5;
        }
        return highlight;
    }

    protected float getDistance(float f, float f2, float f3, float f4) {
        return (float) Math.hypot((double) (f - f3), (double) (f2 - f4));
    }

    protected BarLineScatterCandleBubbleData getData() {
        return this.mChart.getData();
    }
}
