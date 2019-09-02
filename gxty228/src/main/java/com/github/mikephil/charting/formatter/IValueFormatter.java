package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;

public interface IValueFormatter {
    String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler);
}
