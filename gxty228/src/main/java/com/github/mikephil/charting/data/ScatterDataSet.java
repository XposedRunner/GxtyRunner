package com.github.mikephil.charting.data;

import com.github.mikephil.charting.charts.ScatterChart.ScatterShape;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.renderer.scatter.ChevronDownShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.ChevronUpShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.CircleShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.CrossShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.IShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.SquareShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.TriangleShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.XShapeRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;

public class ScatterDataSet extends LineScatterCandleRadarDataSet<Entry> implements IScatterDataSet {
    private int mScatterShapeHoleColor = ColorTemplate.COLOR_NONE;
    private float mScatterShapeHoleRadius = 0.0f;
    protected IShapeRenderer mShapeRenderer = new SquareShapeRenderer();
    private float mShapeSize = 15.0f;

    public ScatterDataSet(List<Entry> list, String str) {
        super(list, str);
    }

    public DataSet<Entry> copy() {
        List arrayList = new ArrayList();
        for (int i = 0; i < this.mValues.size(); i++) {
            arrayList.add(((Entry) this.mValues.get(i)).copy());
        }
        DataSet scatterDataSet = new ScatterDataSet(arrayList, getLabel());
        scatterDataSet.mDrawValues = this.mDrawValues;
        scatterDataSet.mValueColors = this.mValueColors;
        scatterDataSet.mColors = this.mColors;
        scatterDataSet.mShapeSize = this.mShapeSize;
        scatterDataSet.mShapeRenderer = this.mShapeRenderer;
        scatterDataSet.mScatterShapeHoleRadius = this.mScatterShapeHoleRadius;
        scatterDataSet.mScatterShapeHoleColor = this.mScatterShapeHoleColor;
        scatterDataSet.mHighlightLineWidth = this.mHighlightLineWidth;
        scatterDataSet.mHighLightColor = this.mHighLightColor;
        scatterDataSet.mHighlightDashPathEffect = this.mHighlightDashPathEffect;
        return scatterDataSet;
    }

    public void setScatterShapeSize(float f) {
        this.mShapeSize = f;
    }

    public float getScatterShapeSize() {
        return this.mShapeSize;
    }

    public void setScatterShape(ScatterShape scatterShape) {
        this.mShapeRenderer = getRendererForShape(scatterShape);
    }

    public void setShapeRenderer(IShapeRenderer iShapeRenderer) {
        this.mShapeRenderer = iShapeRenderer;
    }

    public IShapeRenderer getShapeRenderer() {
        return this.mShapeRenderer;
    }

    public void setScatterShapeHoleRadius(float f) {
        this.mScatterShapeHoleRadius = f;
    }

    public float getScatterShapeHoleRadius() {
        return this.mScatterShapeHoleRadius;
    }

    public void setScatterShapeHoleColor(int i) {
        this.mScatterShapeHoleColor = i;
    }

    public int getScatterShapeHoleColor() {
        return this.mScatterShapeHoleColor;
    }

    public static IShapeRenderer getRendererForShape(ScatterShape scatterShape) {
        switch (scatterShape) {
            case SQUARE:
                return new SquareShapeRenderer();
            case CIRCLE:
                return new CircleShapeRenderer();
            case TRIANGLE:
                return new TriangleShapeRenderer();
            case CROSS:
                return new CrossShapeRenderer();
            case X:
                return new XShapeRenderer();
            case CHEVRON_UP:
                return new ChevronUpShapeRenderer();
            case CHEVRON_DOWN:
                return new ChevronDownShapeRenderer();
            default:
                return null;
        }
    }
}
