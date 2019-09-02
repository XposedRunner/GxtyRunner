package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.List;

public class LineData extends BarLineScatterCandleBubbleData<ILineDataSet> {
    public LineData(ILineDataSet... iLineDataSetArr) {
        super((IBarLineScatterCandleBubbleDataSet[]) iLineDataSetArr);
    }

    public LineData(List<ILineDataSet> list) {
        super((List) list);
    }
}
