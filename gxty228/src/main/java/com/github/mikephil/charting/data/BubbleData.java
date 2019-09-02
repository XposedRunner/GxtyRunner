package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import java.util.List;

public class BubbleData extends BarLineScatterCandleBubbleData<IBubbleDataSet> {
    public BubbleData(IBubbleDataSet... iBubbleDataSetArr) {
        super((IBarLineScatterCandleBubbleDataSet[]) iBubbleDataSetArr);
    }

    public BubbleData(List<IBubbleDataSet> list) {
        super((List) list);
    }

    public void setHighlightCircleWidth(float f) {
        for (IBubbleDataSet highlightCircleWidth : this.mDataSets) {
            highlightCircleWidth.setHighlightCircleWidth(f);
        }
    }
}
