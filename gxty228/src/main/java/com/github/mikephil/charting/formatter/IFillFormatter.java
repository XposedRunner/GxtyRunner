package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

public interface IFillFormatter {
    float getFillLinePosition(ILineDataSet iLineDataSet, LineDataProvider lineDataProvider);
}
