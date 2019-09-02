package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;

public class RadarDataSet extends LineRadarDataSet<RadarEntry> implements IRadarDataSet {
    protected boolean mDrawHighlightCircleEnabled = false;
    protected int mHighlightCircleFillColor = -1;
    protected float mHighlightCircleInnerRadius = 3.0f;
    protected float mHighlightCircleOuterRadius = 4.0f;
    protected int mHighlightCircleStrokeAlpha = 76;
    protected int mHighlightCircleStrokeColor = ColorTemplate.COLOR_NONE;
    protected float mHighlightCircleStrokeWidth = 2.0f;

    public RadarDataSet(List<RadarEntry> list, String str) {
        super(list, str);
    }

    public boolean isDrawHighlightCircleEnabled() {
        return this.mDrawHighlightCircleEnabled;
    }

    public void setDrawHighlightCircleEnabled(boolean z) {
        this.mDrawHighlightCircleEnabled = z;
    }

    public int getHighlightCircleFillColor() {
        return this.mHighlightCircleFillColor;
    }

    public void setHighlightCircleFillColor(int i) {
        this.mHighlightCircleFillColor = i;
    }

    public int getHighlightCircleStrokeColor() {
        return this.mHighlightCircleStrokeColor;
    }

    public void setHighlightCircleStrokeColor(int i) {
        this.mHighlightCircleStrokeColor = i;
    }

    public int getHighlightCircleStrokeAlpha() {
        return this.mHighlightCircleStrokeAlpha;
    }

    public void setHighlightCircleStrokeAlpha(int i) {
        this.mHighlightCircleStrokeAlpha = i;
    }

    public float getHighlightCircleInnerRadius() {
        return this.mHighlightCircleInnerRadius;
    }

    public void setHighlightCircleInnerRadius(float f) {
        this.mHighlightCircleInnerRadius = f;
    }

    public float getHighlightCircleOuterRadius() {
        return this.mHighlightCircleOuterRadius;
    }

    public void setHighlightCircleOuterRadius(float f) {
        this.mHighlightCircleOuterRadius = f;
    }

    public float getHighlightCircleStrokeWidth() {
        return this.mHighlightCircleStrokeWidth;
    }

    public void setHighlightCircleStrokeWidth(float f) {
        this.mHighlightCircleStrokeWidth = f;
    }

    public DataSet<RadarEntry> copy() {
        List arrayList = new ArrayList();
        for (int i = 0; i < this.mValues.size(); i++) {
            arrayList.add(((RadarEntry) this.mValues.get(i)).copy());
        }
        DataSet radarDataSet = new RadarDataSet(arrayList, getLabel());
        radarDataSet.mColors = this.mColors;
        radarDataSet.mHighLightColor = this.mHighLightColor;
        return radarDataSet;
    }
}
