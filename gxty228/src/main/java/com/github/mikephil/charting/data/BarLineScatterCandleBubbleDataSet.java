package com.github.mikephil.charting.data;

import android.graphics.Color;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import java.util.List;

public abstract class BarLineScatterCandleBubbleDataSet<T extends Entry> extends DataSet<T> implements IBarLineScatterCandleBubbleDataSet<T> {
    protected int mHighLightColor = Color.rgb(255, 187, 115);

    public BarLineScatterCandleBubbleDataSet(List<T> list, String str) {
        super(list, str);
    }

    public void setHighLightColor(int i) {
        this.mHighLightColor = i;
    }

    public int getHighLightColor() {
        return this.mHighLightColor;
    }
}
