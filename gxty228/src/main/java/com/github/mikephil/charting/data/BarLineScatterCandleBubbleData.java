package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import java.util.List;

public abstract class BarLineScatterCandleBubbleData<T extends IBarLineScatterCandleBubbleDataSet<? extends Entry>> extends ChartData<T> {
    public BarLineScatterCandleBubbleData(T... tArr) {
        super((IDataSet[]) tArr);
    }

    public BarLineScatterCandleBubbleData(List<T> list) {
        super((List) list);
    }
}
