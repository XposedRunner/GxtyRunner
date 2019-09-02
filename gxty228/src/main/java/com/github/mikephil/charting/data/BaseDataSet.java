package com.github.mikephil.charting.data;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDataSet<T extends Entry> implements IDataSet<T> {
    protected AxisDependency mAxisDependency;
    protected List<Integer> mColors;
    protected boolean mDrawIcons;
    protected boolean mDrawValues;
    private LegendForm mForm;
    private DashPathEffect mFormLineDashEffect;
    private float mFormLineWidth;
    private float mFormSize;
    protected boolean mHighlightEnabled;
    protected MPPointF mIconsOffset;
    private String mLabel;
    protected List<Integer> mValueColors;
    protected transient IValueFormatter mValueFormatter;
    protected float mValueTextSize;
    protected Typeface mValueTypeface;
    protected boolean mVisible;

    public BaseDataSet() {
        this.mColors = null;
        this.mValueColors = null;
        this.mLabel = "DataSet";
        this.mAxisDependency = AxisDependency.LEFT;
        this.mHighlightEnabled = true;
        this.mForm = LegendForm.DEFAULT;
        this.mFormSize = Float.NaN;
        this.mFormLineWidth = Float.NaN;
        this.mFormLineDashEffect = null;
        this.mDrawValues = true;
        this.mDrawIcons = true;
        this.mIconsOffset = new MPPointF();
        this.mValueTextSize = 17.0f;
        this.mVisible = true;
        this.mColors = new ArrayList();
        this.mValueColors = new ArrayList();
        this.mColors.add(Integer.valueOf(Color.rgb(140, 234, 255)));
        this.mValueColors.add(Integer.valueOf(ViewCompat.MEASURED_STATE_MASK));
    }

    public BaseDataSet(String str) {
        this();
        this.mLabel = str;
    }

    public void notifyDataSetChanged() {
        calcMinMax();
    }

    public List<Integer> getColors() {
        return this.mColors;
    }

    public List<Integer> getValueColors() {
        return this.mValueColors;
    }

    public int getColor() {
        return ((Integer) this.mColors.get(0)).intValue();
    }

    public int getColor(int i) {
        return ((Integer) this.mColors.get(i % this.mColors.size())).intValue();
    }

    public void setColors(List<Integer> list) {
        this.mColors = list;
    }

    public void setColors(int... iArr) {
        this.mColors = ColorTemplate.createColors(iArr);
    }

    public void setColors(int[] iArr, Context context) {
        if (this.mColors == null) {
            this.mColors = new ArrayList();
        }
        this.mColors.clear();
        for (int color : iArr) {
            this.mColors.add(Integer.valueOf(context.getResources().getColor(color)));
        }
    }

    public void addColor(int i) {
        if (this.mColors == null) {
            this.mColors = new ArrayList();
        }
        this.mColors.add(Integer.valueOf(i));
    }

    public void setColor(int i) {
        resetColors();
        this.mColors.add(Integer.valueOf(i));
    }

    public void setColor(int i, int i2) {
        setColor(Color.argb(i2, Color.red(i), Color.green(i), Color.blue(i)));
    }

    public void setColors(int[] iArr, int i) {
        resetColors();
        for (int i2 : iArr) {
            addColor(Color.argb(i, Color.red(i2), Color.green(i2), Color.blue(i2)));
        }
    }

    public void resetColors() {
        if (this.mColors == null) {
            this.mColors = new ArrayList();
        }
        this.mColors.clear();
    }

    public void setLabel(String str) {
        this.mLabel = str;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public void setHighlightEnabled(boolean z) {
        this.mHighlightEnabled = z;
    }

    public boolean isHighlightEnabled() {
        return this.mHighlightEnabled;
    }

    public void setValueFormatter(IValueFormatter iValueFormatter) {
        if (iValueFormatter != null) {
            this.mValueFormatter = iValueFormatter;
        }
    }

    public IValueFormatter getValueFormatter() {
        if (needsFormatter()) {
            return Utils.getDefaultValueFormatter();
        }
        return this.mValueFormatter;
    }

    public boolean needsFormatter() {
        return this.mValueFormatter == null;
    }

    public void setValueTextColor(int i) {
        this.mValueColors.clear();
        this.mValueColors.add(Integer.valueOf(i));
    }

    public void setValueTextColors(List<Integer> list) {
        this.mValueColors = list;
    }

    public void setValueTypeface(Typeface typeface) {
        this.mValueTypeface = typeface;
    }

    public void setValueTextSize(float f) {
        this.mValueTextSize = Utils.convertDpToPixel(f);
    }

    public int getValueTextColor() {
        return ((Integer) this.mValueColors.get(0)).intValue();
    }

    public int getValueTextColor(int i) {
        return ((Integer) this.mValueColors.get(i % this.mValueColors.size())).intValue();
    }

    public Typeface getValueTypeface() {
        return this.mValueTypeface;
    }

    public float getValueTextSize() {
        return this.mValueTextSize;
    }

    public void setForm(LegendForm legendForm) {
        this.mForm = legendForm;
    }

    public LegendForm getForm() {
        return this.mForm;
    }

    public void setFormSize(float f) {
        this.mFormSize = f;
    }

    public float getFormSize() {
        return this.mFormSize;
    }

    public void setFormLineWidth(float f) {
        this.mFormLineWidth = f;
    }

    public float getFormLineWidth() {
        return this.mFormLineWidth;
    }

    public void setFormLineDashEffect(DashPathEffect dashPathEffect) {
        this.mFormLineDashEffect = dashPathEffect;
    }

    public DashPathEffect getFormLineDashEffect() {
        return this.mFormLineDashEffect;
    }

    public void setDrawValues(boolean z) {
        this.mDrawValues = z;
    }

    public boolean isDrawValuesEnabled() {
        return this.mDrawValues;
    }

    public void setDrawIcons(boolean z) {
        this.mDrawIcons = z;
    }

    public boolean isDrawIconsEnabled() {
        return this.mDrawIcons;
    }

    public void setIconsOffset(MPPointF mPPointF) {
        this.mIconsOffset.x = mPPointF.x;
        this.mIconsOffset.y = mPPointF.y;
    }

    public MPPointF getIconsOffset() {
        return this.mIconsOffset;
    }

    public void setVisible(boolean z) {
        this.mVisible = z;
    }

    public boolean isVisible() {
        return this.mVisible;
    }

    public AxisDependency getAxisDependency() {
        return this.mAxisDependency;
    }

    public void setAxisDependency(AxisDependency axisDependency) {
        this.mAxisDependency = axisDependency;
    }

    public int getIndexInEntries(int i) {
        for (int i2 = 0; i2 < getEntryCount(); i2++) {
            if (((float) i) == getEntryForIndex(i2).getX()) {
                return i2;
            }
        }
        return -1;
    }

    public boolean removeFirst() {
        if (getEntryCount() > 0) {
            return removeEntry(getEntryForIndex(0));
        }
        return false;
    }

    public boolean removeLast() {
        if (getEntryCount() > 0) {
            return removeEntry(getEntryForIndex(getEntryCount() - 1));
        }
        return false;
    }

    public boolean removeEntryByXValue(float f) {
        return removeEntry(getEntryForXValue(f, Float.NaN));
    }

    public boolean removeEntry(int i) {
        return removeEntry(getEntryForIndex(i));
    }

    public boolean contains(T t) {
        for (int i = 0; i < getEntryCount(); i++) {
            if (getEntryForIndex(i).equals(t)) {
                return true;
            }
        }
        return false;
    }
}
