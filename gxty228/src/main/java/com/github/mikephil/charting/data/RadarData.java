package com.github.mikephil.charting.data;

import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import java.util.Arrays;
import java.util.List;

public class RadarData extends ChartData<IRadarDataSet> {
    private List<String> mLabels;

    public RadarData(List<IRadarDataSet> list) {
        super((List) list);
    }

    public RadarData(IRadarDataSet... iRadarDataSetArr) {
        super((IDataSet[]) iRadarDataSetArr);
    }

    public void setLabels(List<String> list) {
        this.mLabels = list;
    }

    public void setLabels(String... strArr) {
        this.mLabels = Arrays.asList(strArr);
    }

    public List<String> getLabels() {
        return this.mLabels;
    }

    public Entry getEntryForHighlight(Highlight highlight) {
        return ((IRadarDataSet) getDataSetByIndex(highlight.getDataSetIndex())).getEntryForIndex((int) highlight.getX());
    }
}
