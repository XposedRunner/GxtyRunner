package com.github.mikephil.charting.data;

import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

public class PieData extends ChartData<IPieDataSet> {
    public PieData(IPieDataSet iPieDataSet) {
        super(iPieDataSet);
    }

    public void setDataSet(IPieDataSet iPieDataSet) {
        this.mDataSets.clear();
        this.mDataSets.add(iPieDataSet);
        notifyDataChanged();
    }

    public IPieDataSet getDataSet() {
        return (IPieDataSet) this.mDataSets.get(0);
    }

    public IPieDataSet getDataSetByIndex(int i) {
        return i == 0 ? getDataSet() : null;
    }

    public IPieDataSet getDataSetByLabel(String str, boolean z) {
        return z ? str.equalsIgnoreCase(((IPieDataSet) this.mDataSets.get(0)).getLabel()) ? (IPieDataSet) this.mDataSets.get(0) : null : str.equals(((IPieDataSet) this.mDataSets.get(0)).getLabel()) ? (IPieDataSet) this.mDataSets.get(0) : null;
    }

    public Entry getEntryForHighlight(Highlight highlight) {
        return getDataSet().getEntryForIndex((int) highlight.getX());
    }

    public float getYValueSum() {
        float f = 0.0f;
        for (int i = 0; i < getDataSet().getEntryCount(); i++) {
            f += ((PieEntry) getDataSet().getEntryForIndex(i)).getY();
        }
        return f;
    }
}
