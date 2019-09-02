package com.github.mikephil.charting.data;

import android.graphics.Paint.Style;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class CandleDataSet extends LineScatterCandleRadarDataSet<CandleEntry> implements ICandleDataSet {
    private float mBarSpace = 0.1f;
    protected int mDecreasingColor = ColorTemplate.COLOR_SKIP;
    protected Style mDecreasingPaintStyle = Style.FILL;
    protected int mIncreasingColor = ColorTemplate.COLOR_SKIP;
    protected Style mIncreasingPaintStyle = Style.STROKE;
    protected int mNeutralColor = ColorTemplate.COLOR_SKIP;
    protected int mShadowColor = ColorTemplate.COLOR_SKIP;
    private boolean mShadowColorSameAsCandle = false;
    private float mShadowWidth = 3.0f;
    private boolean mShowCandleBar = true;

    public CandleDataSet(List<CandleEntry> list, String str) {
        super(list, str);
    }

    public DataSet<CandleEntry> copy() {
        List arrayList = new ArrayList();
        arrayList.clear();
        for (int i = 0; i < this.mValues.size(); i++) {
            arrayList.add(((CandleEntry) this.mValues.get(i)).copy());
        }
        DataSet candleDataSet = new CandleDataSet(arrayList, getLabel());
        candleDataSet.mColors = this.mColors;
        candleDataSet.mShadowWidth = this.mShadowWidth;
        candleDataSet.mShowCandleBar = this.mShowCandleBar;
        candleDataSet.mBarSpace = this.mBarSpace;
        candleDataSet.mHighLightColor = this.mHighLightColor;
        candleDataSet.mIncreasingPaintStyle = this.mIncreasingPaintStyle;
        candleDataSet.mDecreasingPaintStyle = this.mDecreasingPaintStyle;
        candleDataSet.mShadowColor = this.mShadowColor;
        return candleDataSet;
    }

    protected void calcMinMax(CandleEntry candleEntry) {
        if (candleEntry.getLow() < this.mYMin) {
            this.mYMin = candleEntry.getLow();
        }
        if (candleEntry.getHigh() > this.mYMax) {
            this.mYMax = candleEntry.getHigh();
        }
        calcMinMaxX(candleEntry);
    }

    protected void calcMinMaxY(CandleEntry candleEntry) {
        if (candleEntry.getHigh() < this.mYMin) {
            this.mYMin = candleEntry.getHigh();
        }
        if (candleEntry.getHigh() > this.mYMax) {
            this.mYMax = candleEntry.getHigh();
        }
        if (candleEntry.getLow() < this.mYMin) {
            this.mYMin = candleEntry.getLow();
        }
        if (candleEntry.getLow() > this.mYMax) {
            this.mYMax = candleEntry.getLow();
        }
    }

    public void setBarSpace(float f) {
        float f2 = 0.45f;
        float f3 = 0.0f;
        if (f >= 0.0f) {
            f3 = f;
        }
        if (f3 <= 0.45f) {
            f2 = f3;
        }
        this.mBarSpace = f2;
    }

    public float getBarSpace() {
        return this.mBarSpace;
    }

    public void setShadowWidth(float f) {
        this.mShadowWidth = Utils.convertDpToPixel(f);
    }

    public float getShadowWidth() {
        return this.mShadowWidth;
    }

    public void setShowCandleBar(boolean z) {
        this.mShowCandleBar = z;
    }

    public boolean getShowCandleBar() {
        return this.mShowCandleBar;
    }

    public void setNeutralColor(int i) {
        this.mNeutralColor = i;
    }

    public int getNeutralColor() {
        return this.mNeutralColor;
    }

    public void setIncreasingColor(int i) {
        this.mIncreasingColor = i;
    }

    public int getIncreasingColor() {
        return this.mIncreasingColor;
    }

    public void setDecreasingColor(int i) {
        this.mDecreasingColor = i;
    }

    public int getDecreasingColor() {
        return this.mDecreasingColor;
    }

    public Style getIncreasingPaintStyle() {
        return this.mIncreasingPaintStyle;
    }

    public void setIncreasingPaintStyle(Style style) {
        this.mIncreasingPaintStyle = style;
    }

    public Style getDecreasingPaintStyle() {
        return this.mDecreasingPaintStyle;
    }

    public void setDecreasingPaintStyle(Style style) {
        this.mDecreasingPaintStyle = style;
    }

    public int getShadowColor() {
        return this.mShadowColor;
    }

    public void setShadowColor(int i) {
        this.mShadowColor = i;
    }

    public boolean getShadowColorSameAsCandle() {
        return this.mShadowColorSameAsCandle;
    }

    public void setShadowColorSameAsCandle(boolean z) {
        this.mShadowColorSameAsCandle = z;
    }
}
