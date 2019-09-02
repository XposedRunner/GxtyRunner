package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.DataSet.Rounding;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import java.util.ArrayList;
import java.util.List;

public class HorizontalBarHighlighter extends BarHighlighter {
    public HorizontalBarHighlighter(BarDataProvider barDataProvider) {
        super(barDataProvider);
    }

    public Highlight getHighlight(float f, float f2) {
        BarData barData = ((BarDataProvider) this.mChart).getBarData();
        MPPointD valsForTouch = getValsForTouch(f2, f);
        Highlight highlightForX = getHighlightForX((float) valsForTouch.y, f2, f);
        if (highlightForX == null) {
            return null;
        }
        IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(highlightForX.getDataSetIndex());
        if (iBarDataSet.isStacked()) {
            return getStackedHighlight(highlightForX, iBarDataSet, (float) valsForTouch.y, (float) valsForTouch.x);
        }
        MPPointD.recycleInstance(valsForTouch);
        return highlightForX;
    }

    protected List<Highlight> buildHighlights(IDataSet iDataSet, int i, float f, Rounding rounding) {
        ArrayList arrayList = new ArrayList();
        List entriesForXValue = iDataSet.getEntriesForXValue(f);
        if (entriesForXValue.size() == 0) {
            Entry entryForXValue = iDataSet.getEntryForXValue(f, Float.NaN, rounding);
            if (entryForXValue != null) {
                entriesForXValue = iDataSet.getEntriesForXValue(entryForXValue.getX());
            }
        }
        if (r2.size() == 0) {
            return arrayList;
        }
        for (Entry entry : r2) {
            MPPointD pixelForValues = ((BarDataProvider) this.mChart).getTransformer(iDataSet.getAxisDependency()).getPixelForValues(entry.getY(), entry.getX());
            arrayList.add(new Highlight(entry.getX(), entry.getY(), (float) pixelForValues.x, (float) pixelForValues.y, i, iDataSet.getAxisDependency()));
        }
        return arrayList;
    }

    protected float getDistance(float f, float f2, float f3, float f4) {
        return Math.abs(f2 - f4);
    }
}
