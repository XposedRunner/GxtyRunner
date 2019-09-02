package com.github.mikephil.charting.interfaces.dataprovider;

import com.github.mikephil.charting.data.CombinedData;

public interface CombinedDataProvider extends BarDataProvider, BubbleDataProvider, CandleDataProvider, LineDataProvider, ScatterDataProvider {
    CombinedData getCombinedData();
}
