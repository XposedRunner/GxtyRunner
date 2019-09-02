package com.github.mikephil.charting.data;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.Log;
import com.github.mikephil.charting.formatter.DefaultFillFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class LineDataSet extends LineRadarDataSet<Entry> implements ILineDataSet {
    private int mCircleColorHole = -1;
    private List<Integer> mCircleColors = null;
    private float mCircleHoleRadius = 4.0f;
    private float mCircleRadius = 8.0f;
    private float mCubicIntensity = 0.2f;
    private DashPathEffect mDashPathEffect = null;
    private boolean mDrawCircleHole = true;
    private boolean mDrawCircles = true;
    private IFillFormatter mFillFormatter = new DefaultFillFormatter();
    private Mode mMode = Mode.LINEAR;

    public enum Mode {
        LINEAR,
        STEPPED,
        CUBIC_BEZIER,
        HORIZONTAL_BEZIER
    }

    public LineDataSet(List<Entry> list, String str) {
        super(list, str);
        if (this.mCircleColors == null) {
            this.mCircleColors = new ArrayList();
        }
        this.mCircleColors.clear();
        this.mCircleColors.add(Integer.valueOf(Color.rgb(140, 234, 255)));
    }

    public DataSet<Entry> copy() {
        List arrayList = new ArrayList();
        for (int i = 0; i < this.mValues.size(); i++) {
            arrayList.add(((Entry) this.mValues.get(i)).copy());
        }
        DataSet lineDataSet = new LineDataSet(arrayList, getLabel());
        lineDataSet.mMode = this.mMode;
        lineDataSet.mColors = this.mColors;
        lineDataSet.mCircleRadius = this.mCircleRadius;
        lineDataSet.mCircleHoleRadius = this.mCircleHoleRadius;
        lineDataSet.mCircleColors = this.mCircleColors;
        lineDataSet.mDashPathEffect = this.mDashPathEffect;
        lineDataSet.mDrawCircles = this.mDrawCircles;
        lineDataSet.mDrawCircleHole = this.mDrawCircleHole;
        lineDataSet.mHighLightColor = this.mHighLightColor;
        return lineDataSet;
    }

    public Mode getMode() {
        return this.mMode;
    }

    public void setMode(Mode mode) {
        this.mMode = mode;
    }

    public void setCubicIntensity(float f) {
        float f2 = 1.0f;
        float f3 = 0.05f;
        if (f <= 1.0f) {
            f2 = f;
        }
        if (f2 >= 0.05f) {
            f3 = f2;
        }
        this.mCubicIntensity = f3;
    }

    public float getCubicIntensity() {
        return this.mCubicIntensity;
    }

    public void setCircleRadius(float f) {
        if (f >= 1.0f) {
            this.mCircleRadius = Utils.convertDpToPixel(f);
        } else {
            Log.e("LineDataSet", "Circle radius cannot be < 1");
        }
    }

    public float getCircleRadius() {
        return this.mCircleRadius;
    }

    public void setCircleHoleRadius(float f) {
        if (f >= 0.5f) {
            this.mCircleHoleRadius = Utils.convertDpToPixel(f);
        } else {
            Log.e("LineDataSet", "Circle radius cannot be < 0.5");
        }
    }

    public float getCircleHoleRadius() {
        return this.mCircleHoleRadius;
    }

    @Deprecated
    public void setCircleSize(float f) {
        setCircleRadius(f);
    }

    @Deprecated
    public float getCircleSize() {
        return getCircleRadius();
    }

    public void enableDashedLine(float f, float f2, float f3) {
        this.mDashPathEffect = new DashPathEffect(new float[]{f, f2}, f3);
    }

    public void disableDashedLine() {
        this.mDashPathEffect = null;
    }

    public boolean isDashedLineEnabled() {
        return this.mDashPathEffect != null;
    }

    public DashPathEffect getDashPathEffect() {
        return this.mDashPathEffect;
    }

    public void setDrawCircles(boolean z) {
        this.mDrawCircles = z;
    }

    public boolean isDrawCirclesEnabled() {
        return this.mDrawCircles;
    }

    @Deprecated
    public boolean isDrawCubicEnabled() {
        return this.mMode == Mode.CUBIC_BEZIER;
    }

    @Deprecated
    public boolean isDrawSteppedEnabled() {
        return this.mMode == Mode.STEPPED;
    }

    public List<Integer> getCircleColors() {
        return this.mCircleColors;
    }

    public int getCircleColor(int i) {
        return ((Integer) this.mCircleColors.get(i)).intValue();
    }

    public int getCircleColorCount() {
        return this.mCircleColors.size();
    }

    public void setCircleColors(List<Integer> list) {
        this.mCircleColors = list;
    }

    public void setCircleColors(int... iArr) {
        this.mCircleColors = ColorTemplate.createColors(iArr);
    }

    public void setCircleColors(int[] iArr, Context context) {
        List list = this.mCircleColors;
        if (list == null) {
            list = new ArrayList();
        }
        list.clear();
        for (int color : iArr) {
            list.add(Integer.valueOf(context.getResources().getColor(color)));
        }
        this.mCircleColors = list;
    }

    public void setCircleColor(int i) {
        resetCircleColors();
        this.mCircleColors.add(Integer.valueOf(i));
    }

    public void resetCircleColors() {
        if (this.mCircleColors == null) {
            this.mCircleColors = new ArrayList();
        }
        this.mCircleColors.clear();
    }

    public void setCircleColorHole(int i) {
        this.mCircleColorHole = i;
    }

    public int getCircleHoleColor() {
        return this.mCircleColorHole;
    }

    public void setDrawCircleHole(boolean z) {
        this.mDrawCircleHole = z;
    }

    public boolean isDrawCircleHoleEnabled() {
        return this.mDrawCircleHole;
    }

    public void setFillFormatter(IFillFormatter iFillFormatter) {
        if (iFillFormatter == null) {
            this.mFillFormatter = new DefaultFillFormatter();
        } else {
            this.mFillFormatter = iFillFormatter;
        }
    }

    public IFillFormatter getFillFormatter() {
        return this.mFillFormatter;
    }
}
