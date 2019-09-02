package com.github.mikephil.charting.interfaces.datasets;

import com.github.mikephil.charting.data.PieDataSet.ValuePosition;
import com.github.mikephil.charting.data.PieEntry;

public interface IPieDataSet extends IDataSet<PieEntry> {
    float getSelectionShift();

    float getSliceSpace();

    int getValueLineColor();

    float getValueLinePart1Length();

    float getValueLinePart1OffsetPercentage();

    float getValueLinePart2Length();

    float getValueLineWidth();

    ValuePosition getXValuePosition();

    ValuePosition getYValuePosition();

    boolean isAutomaticallyDisableSliceSpacingEnabled();

    boolean isValueLineVariableLength();
}
