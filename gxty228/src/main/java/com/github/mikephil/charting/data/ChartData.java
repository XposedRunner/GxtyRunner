package com.github.mikephil.charting.data;

import android.graphics.Typeface;
import android.util.Log;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import java.util.ArrayList;
import java.util.List;

public abstract class ChartData<T extends IDataSet<? extends Entry>> {
    protected List<T> mDataSets;
    protected float mLeftAxisMax;
    protected float mLeftAxisMin;
    protected float mRightAxisMax;
    protected float mRightAxisMin;
    protected float mXMax;
    protected float mXMin;
    protected float mYMax;
    protected float mYMin;

    public ChartData() {
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mLeftAxisMax = -3.4028235E38f;
        this.mLeftAxisMin = Float.MAX_VALUE;
        this.mRightAxisMax = -3.4028235E38f;
        this.mRightAxisMin = Float.MAX_VALUE;
        this.mDataSets = new ArrayList();
    }

    public ChartData(T... tArr) {
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mLeftAxisMax = -3.4028235E38f;
        this.mLeftAxisMin = Float.MAX_VALUE;
        this.mRightAxisMax = -3.4028235E38f;
        this.mRightAxisMin = Float.MAX_VALUE;
        this.mDataSets = arrayToList(tArr);
        notifyDataChanged();
    }

    private List<T> arrayToList(T[] tArr) {
        List<T> arrayList = new ArrayList();
        for (Object add : tArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public ChartData(List<T> list) {
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mLeftAxisMax = -3.4028235E38f;
        this.mLeftAxisMin = Float.MAX_VALUE;
        this.mRightAxisMax = -3.4028235E38f;
        this.mRightAxisMin = Float.MAX_VALUE;
        this.mDataSets = list;
        notifyDataChanged();
    }

    public void notifyDataChanged() {
        calcMinMax();
    }

    public void calcMinMaxY(float f, float f2) {
        for (IDataSet calcMinMaxY : this.mDataSets) {
            calcMinMaxY.calcMinMaxY(f, f2);
        }
        calcMinMax();
    }

    protected void calcMinMax() {
        if (this.mDataSets != null) {
            this.mYMax = -3.4028235E38f;
            this.mYMin = Float.MAX_VALUE;
            this.mXMax = -3.4028235E38f;
            this.mXMin = Float.MAX_VALUE;
            for (IDataSet calcMinMax : this.mDataSets) {
                calcMinMax(calcMinMax);
            }
            this.mLeftAxisMax = -3.4028235E38f;
            this.mLeftAxisMin = Float.MAX_VALUE;
            this.mRightAxisMax = -3.4028235E38f;
            this.mRightAxisMin = Float.MAX_VALUE;
            IDataSet calcMinMax2 = getFirstLeft(this.mDataSets);
            if (calcMinMax2 != null) {
                this.mLeftAxisMax = calcMinMax2.getYMax();
                this.mLeftAxisMin = calcMinMax2.getYMin();
                for (IDataSet calcMinMax22 : this.mDataSets) {
                    if (calcMinMax22.getAxisDependency() == AxisDependency.LEFT) {
                        if (calcMinMax22.getYMin() < this.mLeftAxisMin) {
                            this.mLeftAxisMin = calcMinMax22.getYMin();
                        }
                        if (calcMinMax22.getYMax() > this.mLeftAxisMax) {
                            this.mLeftAxisMax = calcMinMax22.getYMax();
                        }
                    }
                }
            }
            calcMinMax22 = getFirstRight(this.mDataSets);
            if (calcMinMax22 != null) {
                this.mRightAxisMax = calcMinMax22.getYMax();
                this.mRightAxisMin = calcMinMax22.getYMin();
                for (IDataSet calcMinMax222 : this.mDataSets) {
                    if (calcMinMax222.getAxisDependency() == AxisDependency.RIGHT) {
                        if (calcMinMax222.getYMin() < this.mRightAxisMin) {
                            this.mRightAxisMin = calcMinMax222.getYMin();
                        }
                        if (calcMinMax222.getYMax() > this.mRightAxisMax) {
                            this.mRightAxisMax = calcMinMax222.getYMax();
                        }
                    }
                }
            }
        }
    }

    public int getDataSetCount() {
        if (this.mDataSets == null) {
            return 0;
        }
        return this.mDataSets.size();
    }

    public float getYMin() {
        return this.mYMin;
    }

    public float getYMin(AxisDependency axisDependency) {
        if (axisDependency == AxisDependency.LEFT) {
            if (this.mLeftAxisMin == Float.MAX_VALUE) {
                return this.mRightAxisMin;
            }
            return this.mLeftAxisMin;
        } else if (this.mRightAxisMin == Float.MAX_VALUE) {
            return this.mLeftAxisMin;
        } else {
            return this.mRightAxisMin;
        }
    }

    public float getYMax() {
        return this.mYMax;
    }

    public float getYMax(AxisDependency axisDependency) {
        if (axisDependency == AxisDependency.LEFT) {
            if (this.mLeftAxisMax == -3.4028235E38f) {
                return this.mRightAxisMax;
            }
            return this.mLeftAxisMax;
        } else if (this.mRightAxisMax == -3.4028235E38f) {
            return this.mLeftAxisMax;
        } else {
            return this.mRightAxisMax;
        }
    }

    public float getXMin() {
        return this.mXMin;
    }

    public float getXMax() {
        return this.mXMax;
    }

    public List<T> getDataSets() {
        return this.mDataSets;
    }

    protected int getDataSetIndexByLabel(List<T> list, String str, boolean z) {
        int i;
        if (z) {
            for (i = 0; i < list.size(); i++) {
                if (str.equalsIgnoreCase(((IDataSet) list.get(i)).getLabel())) {
                    return i;
                }
            }
        } else {
            for (i = 0; i < list.size(); i++) {
                if (str.equals(((IDataSet) list.get(i)).getLabel())) {
                    return i;
                }
            }
        }
        return -1;
    }

    public String[] getDataSetLabels() {
        String[] strArr = new String[this.mDataSets.size()];
        for (int i = 0; i < this.mDataSets.size(); i++) {
            strArr[i] = ((IDataSet) this.mDataSets.get(i)).getLabel();
        }
        return strArr;
    }

    public Entry getEntryForHighlight(Highlight highlight) {
        if (highlight.getDataSetIndex() >= this.mDataSets.size()) {
            return null;
        }
        return ((IDataSet) this.mDataSets.get(highlight.getDataSetIndex())).getEntryForXValue(highlight.getX(), highlight.getY());
    }

    public T getDataSetByLabel(String str, boolean z) {
        int dataSetIndexByLabel = getDataSetIndexByLabel(this.mDataSets, str, z);
        if (dataSetIndexByLabel < 0 || dataSetIndexByLabel >= this.mDataSets.size()) {
            return null;
        }
        return (IDataSet) this.mDataSets.get(dataSetIndexByLabel);
    }

    public T getDataSetByIndex(int i) {
        if (this.mDataSets == null || i < 0 || i >= this.mDataSets.size()) {
            return null;
        }
        return (IDataSet) this.mDataSets.get(i);
    }

    public void addDataSet(T t) {
        if (t != null) {
            calcMinMax(t);
            this.mDataSets.add(t);
        }
    }

    public boolean removeDataSet(T t) {
        if (t == null) {
            return false;
        }
        boolean remove = this.mDataSets.remove(t);
        if (!remove) {
            return remove;
        }
        calcMinMax();
        return remove;
    }

    public boolean removeDataSet(int i) {
        if (i >= this.mDataSets.size() || i < 0) {
            return false;
        }
        return removeDataSet((IDataSet) this.mDataSets.get(i));
    }

    public void addEntry(Entry entry, int i) {
        if (this.mDataSets.size() <= i || i < 0) {
            Log.e("addEntry", "Cannot add Entry because dataSetIndex too high or too low.");
            return;
        }
        IDataSet iDataSet = (IDataSet) this.mDataSets.get(i);
        if (iDataSet.addEntry(entry)) {
            calcMinMax(entry, iDataSet.getAxisDependency());
        }
    }

    protected void calcMinMax(Entry entry, AxisDependency axisDependency) {
        if (this.mYMax < entry.getY()) {
            this.mYMax = entry.getY();
        }
        if (this.mYMin > entry.getY()) {
            this.mYMin = entry.getY();
        }
        if (this.mXMax < entry.getX()) {
            this.mXMax = entry.getX();
        }
        if (this.mXMin > entry.getX()) {
            this.mXMin = entry.getX();
        }
        if (axisDependency == AxisDependency.LEFT) {
            if (this.mLeftAxisMax < entry.getY()) {
                this.mLeftAxisMax = entry.getY();
            }
            if (this.mLeftAxisMin > entry.getY()) {
                this.mLeftAxisMin = entry.getY();
                return;
            }
            return;
        }
        if (this.mRightAxisMax < entry.getY()) {
            this.mRightAxisMax = entry.getY();
        }
        if (this.mRightAxisMin > entry.getY()) {
            this.mRightAxisMin = entry.getY();
        }
    }

    protected void calcMinMax(T t) {
        if (this.mYMax < t.getYMax()) {
            this.mYMax = t.getYMax();
        }
        if (this.mYMin > t.getYMin()) {
            this.mYMin = t.getYMin();
        }
        if (this.mXMax < t.getXMax()) {
            this.mXMax = t.getXMax();
        }
        if (this.mXMin > t.getXMin()) {
            this.mXMin = t.getXMin();
        }
        if (t.getAxisDependency() == AxisDependency.LEFT) {
            if (this.mLeftAxisMax < t.getYMax()) {
                this.mLeftAxisMax = t.getYMax();
            }
            if (this.mLeftAxisMin > t.getYMin()) {
                this.mLeftAxisMin = t.getYMin();
                return;
            }
            return;
        }
        if (this.mRightAxisMax < t.getYMax()) {
            this.mRightAxisMax = t.getYMax();
        }
        if (this.mRightAxisMin > t.getYMin()) {
            this.mRightAxisMin = t.getYMin();
        }
    }

    public boolean removeEntry(Entry entry, int i) {
        if (entry == null || i >= this.mDataSets.size()) {
            return false;
        }
        IDataSet iDataSet = (IDataSet) this.mDataSets.get(i);
        if (iDataSet == null) {
            return false;
        }
        boolean removeEntry = iDataSet.removeEntry(entry);
        if (!removeEntry) {
            return removeEntry;
        }
        calcMinMax();
        return removeEntry;
    }

    public boolean removeEntry(float f, int i) {
        if (i >= this.mDataSets.size()) {
            return false;
        }
        Entry entryForXValue = ((IDataSet) this.mDataSets.get(i)).getEntryForXValue(f, Float.NaN);
        if (entryForXValue == null) {
            return false;
        }
        return removeEntry(entryForXValue, i);
    }

    public T getDataSetForEntry(Entry entry) {
        if (entry == null) {
            return null;
        }
        for (int i = 0; i < this.mDataSets.size(); i++) {
            IDataSet iDataSet = (IDataSet) this.mDataSets.get(i);
            for (int i2 = 0; i2 < iDataSet.getEntryCount(); i2++) {
                if (entry.equalTo(iDataSet.getEntryForXValue(entry.getX(), entry.getY()))) {
                    return iDataSet;
                }
            }
        }
        return null;
    }

    public int[] getColors() {
        int i = 0;
        if (this.mDataSets == null) {
            return null;
        }
        int i2;
        int i3 = 0;
        for (i2 = 0; i2 < this.mDataSets.size(); i2++) {
            i3 += ((IDataSet) this.mDataSets.get(i2)).getColors().size();
        }
        int[] iArr = new int[i3];
        i2 = 0;
        while (i < this.mDataSets.size()) {
            for (Integer intValue : ((IDataSet) this.mDataSets.get(i)).getColors()) {
                iArr[i2] = intValue.intValue();
                i2++;
            }
            i++;
        }
        return iArr;
    }

    public int getIndexOfDataSet(T t) {
        return this.mDataSets.indexOf(t);
    }

    protected T getFirstLeft(List<T> list) {
        for (T t : list) {
            if (t.getAxisDependency() == AxisDependency.LEFT) {
                return t;
            }
        }
        return null;
    }

    public T getFirstRight(List<T> list) {
        for (T t : list) {
            if (t.getAxisDependency() == AxisDependency.RIGHT) {
                return t;
            }
        }
        return null;
    }

    public void setValueFormatter(IValueFormatter iValueFormatter) {
        if (iValueFormatter != null) {
            for (IDataSet valueFormatter : this.mDataSets) {
                valueFormatter.setValueFormatter(iValueFormatter);
            }
        }
    }

    public void setValueTextColor(int i) {
        for (IDataSet valueTextColor : this.mDataSets) {
            valueTextColor.setValueTextColor(i);
        }
    }

    public void setValueTextColors(List<Integer> list) {
        for (IDataSet valueTextColors : this.mDataSets) {
            valueTextColors.setValueTextColors(list);
        }
    }

    public void setValueTypeface(Typeface typeface) {
        for (IDataSet valueTypeface : this.mDataSets) {
            valueTypeface.setValueTypeface(typeface);
        }
    }

    public void setValueTextSize(float f) {
        for (IDataSet valueTextSize : this.mDataSets) {
            valueTextSize.setValueTextSize(f);
        }
    }

    public void setDrawValues(boolean z) {
        for (IDataSet drawValues : this.mDataSets) {
            drawValues.setDrawValues(z);
        }
    }

    public void setHighlightEnabled(boolean z) {
        for (IDataSet highlightEnabled : this.mDataSets) {
            highlightEnabled.setHighlightEnabled(z);
        }
    }

    public boolean isHighlightEnabled() {
        for (IDataSet isHighlightEnabled : this.mDataSets) {
            if (!isHighlightEnabled.isHighlightEnabled()) {
                return false;
            }
        }
        return true;
    }

    public void clearValues() {
        if (this.mDataSets != null) {
            this.mDataSets.clear();
        }
        notifyDataChanged();
    }

    public boolean contains(T t) {
        for (IDataSet equals : this.mDataSets) {
            if (equals.equals(t)) {
                return true;
            }
        }
        return false;
    }

    public int getEntryCount() {
        int i = 0;
        for (IDataSet entryCount : this.mDataSets) {
            i = entryCount.getEntryCount() + i;
        }
        return i;
    }

    public T getMaxEntryCountSet() {
        if (this.mDataSets == null || this.mDataSets.isEmpty()) {
            return null;
        }
        T t = (IDataSet) this.mDataSets.get(0);
        T t2 = t;
        for (T t3 : this.mDataSets) {
            if (t3.getEntryCount() <= t2.getEntryCount()) {
                t3 = t2;
            }
            t2 = t3;
        }
        return t2;
    }
}
