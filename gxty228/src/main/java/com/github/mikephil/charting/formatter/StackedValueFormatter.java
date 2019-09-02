package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;

public class StackedValueFormatter implements IValueFormatter {
    private String mAppendix;
    private boolean mDrawWholeStack;
    private DecimalFormat mFormat;

    public StackedValueFormatter(boolean z, String str, int i) {
        this.mDrawWholeStack = z;
        this.mAppendix = str;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 == 0) {
                stringBuffer.append(".");
            }
            stringBuffer.append("0");
        }
        this.mFormat = new DecimalFormat("###,###,###,##0" + stringBuffer.toString());
    }

    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        if (!this.mDrawWholeStack && (entry instanceof BarEntry)) {
            BarEntry barEntry = (BarEntry) entry;
            float[] yVals = barEntry.getYVals();
            if (yVals != null) {
                if (yVals[yVals.length - 1] == f) {
                    return this.mFormat.format((double) barEntry.getY()) + this.mAppendix;
                }
                return "";
            }
        }
        return this.mFormat.format((double) f) + this.mAppendix;
    }
}
