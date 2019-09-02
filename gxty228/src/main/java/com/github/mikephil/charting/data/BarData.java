package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import java.util.List;

public class BarData extends BarLineScatterCandleBubbleData<IBarDataSet> {
    private float mBarWidth = 0.85f;

    public BarData(IBarDataSet... iBarDataSetArr) {
        super((IBarLineScatterCandleBubbleDataSet[]) iBarDataSetArr);
    }

    public BarData(List<IBarDataSet> list) {
        super((List) list);
    }

    public void setBarWidth(float f) {
        this.mBarWidth = f;
    }

    public float getBarWidth() {
        return this.mBarWidth;
    }

    public void groupBars(float f, float f2, float f3) {
        if (this.mDataSets.size() <= 1) {
            throw new RuntimeException("BarData needs to hold at least 2 BarDataSets to allow grouping.");
        }
        int entryCount = ((IBarDataSet) getMaxEntryCountSet()).getEntryCount();
        float f4 = f2 / 2.0f;
        float f5 = f3 / 2.0f;
        float f6 = this.mBarWidth / 2.0f;
        float groupWidth = getGroupWidth(f2, f3);
        int i = 0;
        while (i < entryCount) {
            float f7 = f + f4;
            float f8 = f7;
            for (IBarDataSet iBarDataSet : this.mDataSets) {
                f8 = (f8 + f5) + f6;
                if (i < iBarDataSet.getEntryCount()) {
                    BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i);
                    if (barEntry != null) {
                        barEntry.setX(f8);
                    }
                }
                f8 = (f8 + f6) + f5;
            }
            f7 = f8 + f4;
            f8 = groupWidth - (f7 - f);
            if (f8 > 0.0f || f8 < 0.0f) {
                f7 += f8;
            }
            i++;
            f = f7;
        }
        notifyDataChanged();
    }

    public float getGroupWidth(float f, float f2) {
        return (((float) this.mDataSets.size()) * (this.mBarWidth + f2)) + f;
    }
}
