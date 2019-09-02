package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import java.util.List;

public class ScatterData extends BarLineScatterCandleBubbleData<IScatterDataSet> {
    public ScatterData(List<IScatterDataSet> list) {
        super((List) list);
    }

    public ScatterData(IScatterDataSet... iScatterDataSetArr) {
        super((IBarLineScatterCandleBubbleDataSet[]) iScatterDataSetArr);
    }

    public float getGreatestShapeSize() {
        float f = 0.0f;
        for (IScatterDataSet scatterShapeSize : this.mDataSets) {
            float scatterShapeSize2 = scatterShapeSize.getScatterShapeSize();
            if (scatterShapeSize2 <= f) {
                scatterShapeSize2 = f;
            }
            f = scatterShapeSize2;
        }
        return f;
    }
}
