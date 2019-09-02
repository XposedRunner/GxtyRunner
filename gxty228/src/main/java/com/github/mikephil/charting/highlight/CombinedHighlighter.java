package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.DataSet.Rounding;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.CombinedDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import java.util.List;

public class CombinedHighlighter extends ChartHighlighter<CombinedDataProvider> implements IHighlighter {
    protected BarHighlighter barHighlighter;

    public CombinedHighlighter(CombinedDataProvider combinedDataProvider, BarDataProvider barDataProvider) {
        super(combinedDataProvider);
        this.barHighlighter = barDataProvider.getBarData() == null ? null : new BarHighlighter(barDataProvider);
    }

    protected List<Highlight> getHighlightsAtXValue(float f, float f2, float f3) {
        this.mHighlightBuffer.clear();
        List allData = ((CombinedDataProvider) this.mChart).getCombinedData().getAllData();
        for (int i = 0; i < allData.size(); i++) {
            ChartData chartData = (ChartData) allData.get(i);
            Highlight highlight;
            if (this.barHighlighter == null || !(chartData instanceof BarData)) {
                int dataSetCount = chartData.getDataSetCount();
                for (int i2 = 0; i2 < dataSetCount; i2++) {
                    IDataSet dataSetByIndex = ((BarLineScatterCandleBubbleData) allData.get(i)).getDataSetByIndex(i2);
                    if (dataSetByIndex.isHighlightEnabled()) {
                        for (Highlight highlight2 : buildHighlights(dataSetByIndex, i2, f, Rounding.CLOSEST)) {
                            highlight2.setDataIndex(i);
                            this.mHighlightBuffer.add(highlight2);
                        }
                    }
                }
            } else {
                highlight2 = this.barHighlighter.getHighlight(f2, f3);
                if (highlight2 != null) {
                    highlight2.setDataIndex(i);
                    this.mHighlightBuffer.add(highlight2);
                }
            }
        }
        return this.mHighlightBuffer;
    }
}
