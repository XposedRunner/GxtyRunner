package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.utils.ColorTemplate;

public class LegendEntry {
    public LegendForm form = LegendForm.DEFAULT;
    public int formColor = ColorTemplate.COLOR_NONE;
    public DashPathEffect formLineDashEffect = null;
    public float formLineWidth = Float.NaN;
    public float formSize = Float.NaN;
    public String label;

    public LegendEntry(String str, LegendForm legendForm, float f, float f2, DashPathEffect dashPathEffect, int i) {
        this.label = str;
        this.form = legendForm;
        this.formSize = f;
        this.formLineWidth = f2;
        this.formLineDashEffect = dashPathEffect;
        this.formColor = i;
    }
}
