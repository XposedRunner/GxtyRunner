package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

public class PieHighlighter extends PieRadarHighlighter<PieChart> {
    public PieHighlighter(PieChart pieChart) {
        super(pieChart);
    }

    protected Highlight getClosestHighlight(int i, float f, float f2) {
        IPieDataSet dataSet = ((PieData) ((PieChart) this.mChart).getData()).getDataSet();
        return new Highlight((float) i, dataSet.getEntryForIndex(i).getY(), f, f2, 0, dataSet.getAxisDependency());
    }
}
