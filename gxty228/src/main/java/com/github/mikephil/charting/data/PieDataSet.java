package com.github.mikephil.charting.data;

import android.support.v4.view.ViewCompat;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class PieDataSet extends DataSet<PieEntry> implements IPieDataSet {
    private boolean mAutomaticallyDisableSliceSpacing;
    private float mShift = 18.0f;
    private float mSliceSpace = 0.0f;
    private int mValueLineColor = ViewCompat.MEASURED_STATE_MASK;
    private float mValueLinePart1Length = 0.3f;
    private float mValueLinePart1OffsetPercentage = 75.0f;
    private float mValueLinePart2Length = 0.4f;
    private boolean mValueLineVariableLength = true;
    private float mValueLineWidth = 1.0f;
    private ValuePosition mXValuePosition = ValuePosition.INSIDE_SLICE;
    private ValuePosition mYValuePosition = ValuePosition.INSIDE_SLICE;

    public enum ValuePosition {
        INSIDE_SLICE,
        OUTSIDE_SLICE
    }

    public PieDataSet(List<PieEntry> list, String str) {
        super(list, str);
    }

    public DataSet<PieEntry> copy() {
        List arrayList = new ArrayList();
        for (int i = 0; i < this.mValues.size(); i++) {
            arrayList.add(((PieEntry) this.mValues.get(i)).copy());
        }
        DataSet pieDataSet = new PieDataSet(arrayList, getLabel());
        pieDataSet.mColors = this.mColors;
        pieDataSet.mSliceSpace = this.mSliceSpace;
        pieDataSet.mShift = this.mShift;
        return pieDataSet;
    }

    protected void calcMinMax(PieEntry pieEntry) {
        if (pieEntry != null) {
            calcMinMaxY(pieEntry);
        }
    }

    public void setSliceSpace(float f) {
        float f2 = 20.0f;
        float f3 = 0.0f;
        if (f <= 20.0f) {
            f2 = f;
        }
        if (f2 >= 0.0f) {
            f3 = f2;
        }
        this.mSliceSpace = Utils.convertDpToPixel(f3);
    }

    public float getSliceSpace() {
        return this.mSliceSpace;
    }

    public void setAutomaticallyDisableSliceSpacing(boolean z) {
        this.mAutomaticallyDisableSliceSpacing = z;
    }

    public boolean isAutomaticallyDisableSliceSpacingEnabled() {
        return this.mAutomaticallyDisableSliceSpacing;
    }

    public void setSelectionShift(float f) {
        this.mShift = Utils.convertDpToPixel(f);
    }

    public float getSelectionShift() {
        return this.mShift;
    }

    public ValuePosition getXValuePosition() {
        return this.mXValuePosition;
    }

    public void setXValuePosition(ValuePosition valuePosition) {
        this.mXValuePosition = valuePosition;
    }

    public ValuePosition getYValuePosition() {
        return this.mYValuePosition;
    }

    public void setYValuePosition(ValuePosition valuePosition) {
        this.mYValuePosition = valuePosition;
    }

    public int getValueLineColor() {
        return this.mValueLineColor;
    }

    public void setValueLineColor(int i) {
        this.mValueLineColor = i;
    }

    public float getValueLineWidth() {
        return this.mValueLineWidth;
    }

    public void setValueLineWidth(float f) {
        this.mValueLineWidth = f;
    }

    public float getValueLinePart1OffsetPercentage() {
        return this.mValueLinePart1OffsetPercentage;
    }

    public void setValueLinePart1OffsetPercentage(float f) {
        this.mValueLinePart1OffsetPercentage = f;
    }

    public float getValueLinePart1Length() {
        return this.mValueLinePart1Length;
    }

    public void setValueLinePart1Length(float f) {
        this.mValueLinePart1Length = f;
    }

    public float getValueLinePart2Length() {
        return this.mValueLinePart2Length;
    }

    public void setValueLinePart2Length(float f) {
        this.mValueLinePart2Length = f;
    }

    public boolean isValueLineVariableLength() {
        return this.mValueLineVariableLength;
    }

    public void setValueLineVariableLength(boolean z) {
        this.mValueLineVariableLength = z;
    }
}
