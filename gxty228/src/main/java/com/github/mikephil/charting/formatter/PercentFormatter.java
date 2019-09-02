package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;

public class PercentFormatter implements IAxisValueFormatter, IValueFormatter {
    protected DecimalFormat mFormat;

    public PercentFormatter() {
        this.mFormat = new DecimalFormat("###,###,##0.0");
    }

    public PercentFormatter(DecimalFormat decimalFormat) {
        this.mFormat = decimalFormat;
    }

    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        return this.mFormat.format((double) f) + " %";
    }

    public String getFormattedValue(float f, AxisBase axisBase) {
        return this.mFormat.format((double) f) + " %";
    }

    public int getDecimalDigits() {
        return 1;
    }
}
