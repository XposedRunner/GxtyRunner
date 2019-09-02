package com.github.mikephil.charting.interfaces.datasets;

import com.github.mikephil.charting.data.Entry;

public interface IBarLineScatterCandleBubbleDataSet<T extends Entry> extends IDataSet<T> {
    int getHighLightColor();
}
