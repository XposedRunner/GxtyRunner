package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LegendRenderer extends Renderer {
    protected List<LegendEntry> computedEntries = new ArrayList(16);
    protected FontMetrics legendFontMetrics = new FontMetrics();
    protected Legend mLegend;
    protected Paint mLegendFormPaint;
    protected Paint mLegendLabelPaint;
    private Path mLineFormPath = new Path();

    public LegendRenderer(ViewPortHandler viewPortHandler, Legend legend) {
        super(viewPortHandler);
        this.mLegend = legend;
        this.mLegendLabelPaint = new Paint(1);
        this.mLegendLabelPaint.setTextSize(Utils.convertDpToPixel(9.0f));
        this.mLegendLabelPaint.setTextAlign(Align.LEFT);
        this.mLegendFormPaint = new Paint(1);
        this.mLegendFormPaint.setStyle(Style.FILL);
    }

    public Paint getLabelPaint() {
        return this.mLegendLabelPaint;
    }

    public Paint getFormPaint() {
        return this.mLegendFormPaint;
    }

    public void computeLegend(ChartData<?> chartData) {
        if (!this.mLegend.isLegendCustom()) {
            this.computedEntries.clear();
            for (int i = 0; i < chartData.getDataSetCount(); i++) {
                IDataSet dataSetByIndex = chartData.getDataSetByIndex(i);
                List colors = dataSetByIndex.getColors();
                int entryCount = dataSetByIndex.getEntryCount();
                int i2;
                if ((dataSetByIndex instanceof IBarDataSet) && ((IBarDataSet) dataSetByIndex).isStacked()) {
                    IBarDataSet iBarDataSet = (IBarDataSet) dataSetByIndex;
                    String[] stackLabels = iBarDataSet.getStackLabels();
                    i2 = 0;
                    while (i2 < colors.size() && i2 < iBarDataSet.getStackSize()) {
                        this.computedEntries.add(new LegendEntry(stackLabels[i2 % stackLabels.length], dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), ((Integer) colors.get(i2)).intValue()));
                        i2++;
                    }
                    if (iBarDataSet.getLabel() != null) {
                        this.computedEntries.add(new LegendEntry(dataSetByIndex.getLabel(), LegendForm.NONE, Float.NaN, Float.NaN, null, ColorTemplate.COLOR_NONE));
                    }
                } else if (dataSetByIndex instanceof IPieDataSet) {
                    IPieDataSet iPieDataSet = (IPieDataSet) dataSetByIndex;
                    i2 = 0;
                    while (i2 < colors.size() && i2 < entryCount) {
                        this.computedEntries.add(new LegendEntry(((PieEntry) iPieDataSet.getEntryForIndex(i2)).getLabel(), dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), ((Integer) colors.get(i2)).intValue()));
                        i2++;
                    }
                    if (iPieDataSet.getLabel() != null) {
                        this.computedEntries.add(new LegendEntry(dataSetByIndex.getLabel(), LegendForm.NONE, Float.NaN, Float.NaN, null, ColorTemplate.COLOR_NONE));
                    }
                } else if (!(dataSetByIndex instanceof ICandleDataSet) || ((ICandleDataSet) dataSetByIndex).getDecreasingColor() == ColorTemplate.COLOR_NONE) {
                    r8 = 0;
                    while (r8 < colors.size() && r8 < entryCount) {
                        String label;
                        if (r8 >= colors.size() - 1 || r8 >= entryCount - 1) {
                            label = chartData.getDataSetByIndex(i).getLabel();
                        } else {
                            label = null;
                        }
                        this.computedEntries.add(new LegendEntry(label, dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), ((Integer) colors.get(r8)).intValue()));
                        r8++;
                    }
                } else {
                    int decreasingColor = ((ICandleDataSet) dataSetByIndex).getDecreasingColor();
                    r8 = ((ICandleDataSet) dataSetByIndex).getIncreasingColor();
                    this.computedEntries.add(new LegendEntry(null, dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), decreasingColor));
                    this.computedEntries.add(new LegendEntry(dataSetByIndex.getLabel(), dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), r8));
                }
            }
            if (this.mLegend.getExtraEntries() != null) {
                Collections.addAll(this.computedEntries, this.mLegend.getExtraEntries());
            }
            this.mLegend.setEntries(this.computedEntries);
        }
        Typeface typeface = this.mLegend.getTypeface();
        if (typeface != null) {
            this.mLegendLabelPaint.setTypeface(typeface);
        }
        this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
        this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
        this.mLegend.calculateDimensions(this.mLegendLabelPaint, this.mViewPortHandler);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void renderLegend(android.graphics.Canvas r30) {
        /*
        r29 = this;
        r0 = r29;
        r2 = r0.mLegend;
        r2 = r2.isEnabled();
        if (r2 != 0) goto L_0x000b;
    L_0x000a:
        return;
    L_0x000b:
        r0 = r29;
        r2 = r0.mLegend;
        r2 = r2.getTypeface();
        if (r2 == 0) goto L_0x001c;
    L_0x0015:
        r0 = r29;
        r3 = r0.mLegendLabelPaint;
        r3.setTypeface(r2);
    L_0x001c:
        r0 = r29;
        r2 = r0.mLegendLabelPaint;
        r0 = r29;
        r3 = r0.mLegend;
        r3 = r3.getTextSize();
        r2.setTextSize(r3);
        r0 = r29;
        r2 = r0.mLegendLabelPaint;
        r0 = r29;
        r3 = r0.mLegend;
        r3 = r3.getTextColor();
        r2.setColor(r3);
        r0 = r29;
        r2 = r0.mLegendLabelPaint;
        r0 = r29;
        r3 = r0.legendFontMetrics;
        r19 = com.github.mikephil.charting.utils.Utils.getLineHeight(r2, r3);
        r0 = r29;
        r2 = r0.mLegendLabelPaint;
        r0 = r29;
        r3 = r0.legendFontMetrics;
        r2 = com.github.mikephil.charting.utils.Utils.getLineSpacing(r2, r3);
        r0 = r29;
        r3 = r0.mLegend;
        r3 = r3.getYEntrySpace();
        r3 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r3);
        r20 = r2 + r3;
        r0 = r29;
        r2 = r0.mLegendLabelPaint;
        r3 = "ABC";
        r2 = com.github.mikephil.charting.utils.Utils.calcTextHeight(r2, r3);
        r2 = (float) r2;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r3;
        r21 = r19 - r2;
        r0 = r29;
        r2 = r0.mLegend;
        r22 = r2.getEntries();
        r0 = r29;
        r2 = r0.mLegend;
        r2 = r2.getFormToTextSpace();
        r13 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r2);
        r0 = r29;
        r2 = r0.mLegend;
        r2 = r2.getXEntrySpace();
        r14 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r2);
        r0 = r29;
        r2 = r0.mLegend;
        r6 = r2.getOrientation();
        r0 = r29;
        r2 = r0.mLegend;
        r23 = r2.getHorizontalAlignment();
        r0 = r29;
        r2 = r0.mLegend;
        r7 = r2.getVerticalAlignment();
        r0 = r29;
        r2 = r0.mLegend;
        r24 = r2.getDirection();
        r0 = r29;
        r2 = r0.mLegend;
        r2 = r2.getFormSize();
        r11 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r2);
        r0 = r29;
        r2 = r0.mLegend;
        r2 = r2.getStackSpace();
        r15 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r2);
        r0 = r29;
        r2 = r0.mLegend;
        r5 = r2.getYOffset();
        r0 = r29;
        r2 = r0.mLegend;
        r2 = r2.getXOffset();
        r3 = 0;
        r4 = com.github.mikephil.charting.renderer.LegendRenderer.AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment;
        r8 = r23.ordinal();
        r4 = r4[r8];
        switch(r4) {
            case 1: goto L_0x01f7;
            case 2: goto L_0x0215;
            case 3: goto L_0x023e;
            default: goto L_0x00e4;
        };
    L_0x00e4:
        r8 = r3;
    L_0x00e5:
        r2 = com.github.mikephil.charting.renderer.LegendRenderer.AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation;
        r3 = r6.ordinal();
        r2 = r2[r3];
        switch(r2) {
            case 1: goto L_0x00f2;
            case 2: goto L_0x02f9;
            default: goto L_0x00f0;
        };
    L_0x00f0:
        goto L_0x000a;
    L_0x00f2:
        r0 = r29;
        r2 = r0.mLegend;
        r25 = r2.getCalculatedLineSizes();
        r0 = r29;
        r2 = r0.mLegend;
        r26 = r2.getCalculatedLabelSizes();
        r0 = r29;
        r2 = r0.mLegend;
        r27 = r2.getCalculatedLabelBreakPoints();
        r2 = 0;
        r3 = com.github.mikephil.charting.renderer.LegendRenderer.AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment;
        r4 = r7.ordinal();
        r3 = r3[r4];
        switch(r3) {
            case 1: goto L_0x029e;
            case 2: goto L_0x02a1;
            case 3: goto L_0x02b3;
            default: goto L_0x0116;
        };
    L_0x0116:
        r12 = 0;
        r3 = 0;
        r0 = r22;
        r0 = r0.length;
        r28 = r0;
        r18 = r3;
        r4 = r2;
        r5 = r8;
        r3 = r12;
    L_0x0122:
        r0 = r18;
        r1 = r28;
        if (r0 >= r1) goto L_0x000a;
    L_0x0128:
        r6 = r22[r18];
        r2 = r6.form;
        r7 = com.github.mikephil.charting.components.Legend.LegendForm.NONE;
        if (r2 == r7) goto L_0x02c8;
    L_0x0130:
        r2 = 1;
        r9 = r2;
    L_0x0132:
        r2 = r6.formSize;
        r2 = java.lang.Float.isNaN(r2);
        if (r2 == 0) goto L_0x02cc;
    L_0x013a:
        r10 = r11;
    L_0x013b:
        r2 = r27.size();
        r0 = r18;
        if (r0 >= r2) goto L_0x041a;
    L_0x0143:
        r0 = r27;
        r1 = r18;
        r2 = r0.get(r1);
        r2 = (java.lang.Boolean) r2;
        r2 = r2.booleanValue();
        if (r2 == 0) goto L_0x041a;
    L_0x0153:
        r2 = r19 + r20;
        r2 = r2 + r4;
        r17 = r2;
        r4 = r8;
    L_0x0159:
        r2 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r2 != 0) goto L_0x0416;
    L_0x015d:
        r2 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER;
        r0 = r23;
        if (r0 != r2) goto L_0x0416;
    L_0x0163:
        r2 = r25.size();
        if (r3 >= r2) goto L_0x0416;
    L_0x0169:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r2) goto L_0x02d5;
    L_0x016f:
        r0 = r25;
        r2 = r0.get(r3);
        r2 = (com.github.mikephil.charting.utils.FSize) r2;
        r2 = r2.width;
    L_0x0179:
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r5;
        r4 = r4 + r2;
        r2 = r3 + 1;
        r12 = r2;
        r2 = r4;
    L_0x0181:
        r3 = r6.label;
        if (r3 != 0) goto L_0x02e2;
    L_0x0185:
        r3 = 1;
        r16 = r3;
    L_0x0188:
        if (r9 == 0) goto L_0x0413;
    L_0x018a:
        r3 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r3) goto L_0x0410;
    L_0x0190:
        r4 = r2 - r10;
    L_0x0192:
        r5 = r17 + r21;
        r0 = r29;
        r7 = r0.mLegend;
        r2 = r29;
        r3 = r30;
        r2.drawForm(r3, r4, r5, r6, r7);
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r2) goto L_0x040d;
    L_0x01a5:
        r3 = r4 + r10;
    L_0x01a7:
        if (r16 != 0) goto L_0x02ed;
    L_0x01a9:
        if (r9 == 0) goto L_0x01b4;
    L_0x01ab:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r2) goto L_0x02e7;
    L_0x01b1:
        r2 = -r13;
    L_0x01b2:
        r2 = r2 + r3;
        r3 = r2;
    L_0x01b4:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r2) goto L_0x01c7;
    L_0x01ba:
        r0 = r26;
        r1 = r18;
        r2 = r0.get(r1);
        r2 = (com.github.mikephil.charting.utils.FSize) r2;
        r2 = r2.width;
        r3 = r3 - r2;
    L_0x01c7:
        r2 = r17 + r19;
        r4 = r6.label;
        r0 = r29;
        r1 = r30;
        r0.drawLabel(r1, r3, r2, r4);
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r2) goto L_0x01e5;
    L_0x01d8:
        r0 = r26;
        r1 = r18;
        r2 = r0.get(r1);
        r2 = (com.github.mikephil.charting.utils.FSize) r2;
        r2 = r2.width;
        r3 = r3 + r2;
    L_0x01e5:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r2) goto L_0x02ea;
    L_0x01eb:
        r2 = -r14;
    L_0x01ec:
        r2 = r2 + r3;
    L_0x01ed:
        r3 = r18 + 1;
        r18 = r3;
        r4 = r17;
        r5 = r2;
        r3 = r12;
        goto L_0x0122;
    L_0x01f7:
        r3 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL;
        if (r6 != r3) goto L_0x020b;
    L_0x01fb:
        r3 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r3) goto L_0x041f;
    L_0x0201:
        r0 = r29;
        r3 = r0.mLegend;
        r3 = r3.mNeededWidth;
        r2 = r2 + r3;
        r8 = r2;
        goto L_0x00e5;
    L_0x020b:
        r0 = r29;
        r3 = r0.mViewPortHandler;
        r3 = r3.contentLeft();
        r2 = r2 + r3;
        goto L_0x01fb;
    L_0x0215:
        r3 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL;
        if (r6 != r3) goto L_0x0233;
    L_0x0219:
        r0 = r29;
        r3 = r0.mViewPortHandler;
        r3 = r3.getChartWidth();
        r2 = r3 - r2;
    L_0x0223:
        r3 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r3) goto L_0x041f;
    L_0x0229:
        r0 = r29;
        r3 = r0.mLegend;
        r3 = r3.mNeededWidth;
        r2 = r2 - r3;
        r8 = r2;
        goto L_0x00e5;
    L_0x0233:
        r0 = r29;
        r3 = r0.mViewPortHandler;
        r3 = r3.contentRight();
        r2 = r3 - r2;
        goto L_0x0223;
    L_0x023e:
        r3 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL;
        if (r6 != r3) goto L_0x0276;
    L_0x0242:
        r0 = r29;
        r3 = r0.mViewPortHandler;
        r3 = r3.getChartWidth();
        r4 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = r3 / r4;
    L_0x024d:
        r4 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r4) goto L_0x028b;
    L_0x0253:
        r4 = r2;
    L_0x0254:
        r3 = r3 + r4;
        r4 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL;
        if (r6 != r4) goto L_0x00e4;
    L_0x0259:
        r8 = (double) r3;
        r3 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r3) goto L_0x028d;
    L_0x0260:
        r0 = r29;
        r3 = r0.mLegend;
        r3 = r3.mNeededWidth;
        r3 = -r3;
        r0 = (double) r3;
        r16 = r0;
        r26 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r16 = r16 / r26;
        r2 = (double) r2;
        r2 = r2 + r16;
    L_0x0271:
        r2 = r2 + r8;
        r2 = (float) r2;
        r8 = r2;
        goto L_0x00e5;
    L_0x0276:
        r0 = r29;
        r3 = r0.mViewPortHandler;
        r3 = r3.contentLeft();
        r0 = r29;
        r4 = r0.mViewPortHandler;
        r4 = r4.contentWidth();
        r8 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r4 = r4 / r8;
        r3 = r3 + r4;
        goto L_0x024d;
    L_0x028b:
        r4 = -r2;
        goto L_0x0254;
    L_0x028d:
        r0 = r29;
        r3 = r0.mLegend;
        r3 = r3.mNeededWidth;
        r0 = (double) r3;
        r16 = r0;
        r26 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r16 = r16 / r26;
        r2 = (double) r2;
        r2 = r16 - r2;
        goto L_0x0271;
    L_0x029e:
        r2 = r5;
        goto L_0x0116;
    L_0x02a1:
        r0 = r29;
        r2 = r0.mViewPortHandler;
        r2 = r2.getChartHeight();
        r2 = r2 - r5;
        r0 = r29;
        r3 = r0.mLegend;
        r3 = r3.mNeededHeight;
        r2 = r2 - r3;
        goto L_0x0116;
    L_0x02b3:
        r0 = r29;
        r2 = r0.mViewPortHandler;
        r2 = r2.getChartHeight();
        r0 = r29;
        r3 = r0.mLegend;
        r3 = r3.mNeededHeight;
        r2 = r2 - r3;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r3;
        r2 = r2 + r5;
        goto L_0x0116;
    L_0x02c8:
        r2 = 0;
        r9 = r2;
        goto L_0x0132;
    L_0x02cc:
        r2 = r6.formSize;
        r2 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r2);
        r10 = r2;
        goto L_0x013b;
    L_0x02d5:
        r0 = r25;
        r2 = r0.get(r3);
        r2 = (com.github.mikephil.charting.utils.FSize) r2;
        r2 = r2.width;
        r2 = -r2;
        goto L_0x0179;
    L_0x02e2:
        r3 = 0;
        r16 = r3;
        goto L_0x0188;
    L_0x02e7:
        r2 = r13;
        goto L_0x01b2;
    L_0x02ea:
        r2 = r14;
        goto L_0x01ec;
    L_0x02ed:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r2) goto L_0x02f7;
    L_0x02f3:
        r2 = -r15;
    L_0x02f4:
        r2 = r2 + r3;
        goto L_0x01ed;
    L_0x02f7:
        r2 = r15;
        goto L_0x02f4;
    L_0x02f9:
        r6 = 0;
        r4 = 0;
        r2 = 0;
        r3 = com.github.mikephil.charting.renderer.LegendRenderer.AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment;
        r7 = r7.ordinal();
        r3 = r3[r7];
        switch(r3) {
            case 1: goto L_0x0381;
            case 2: goto L_0x0394;
            case 3: goto L_0x03b5;
            default: goto L_0x0307;
        };
    L_0x0307:
        r3 = 0;
        r9 = r3;
        r10 = r2;
        r12 = r4;
        r14 = r6;
    L_0x030c:
        r0 = r22;
        r2 = r0.length;
        if (r9 >= r2) goto L_0x000a;
    L_0x0311:
        r6 = r22[r9];
        r2 = r6.form;
        r3 = com.github.mikephil.charting.components.Legend.LegendForm.NONE;
        if (r2 == r3) goto L_0x03d5;
    L_0x0319:
        r2 = 1;
        r16 = r2;
    L_0x031c:
        r2 = r6.formSize;
        r2 = java.lang.Float.isNaN(r2);
        if (r2 == 0) goto L_0x03da;
    L_0x0324:
        r17 = r11;
    L_0x0326:
        if (r16 == 0) goto L_0x040a;
    L_0x0328:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r2) goto L_0x03e4;
    L_0x032e:
        r4 = r8 + r14;
    L_0x0330:
        r5 = r10 + r21;
        r0 = r29;
        r7 = r0.mLegend;
        r2 = r29;
        r3 = r30;
        r2.drawForm(r3, r4, r5, r6, r7);
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r2) goto L_0x0345;
    L_0x0343:
        r4 = r4 + r17;
    L_0x0345:
        r2 = r6.label;
        if (r2 == 0) goto L_0x0402;
    L_0x0349:
        if (r16 == 0) goto L_0x03ed;
    L_0x034b:
        if (r12 != 0) goto L_0x03ed;
    L_0x034d:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r2) goto L_0x03ea;
    L_0x0353:
        r2 = r13;
    L_0x0354:
        r4 = r4 + r2;
    L_0x0355:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r2) goto L_0x0367;
    L_0x035b:
        r0 = r29;
        r2 = r0.mLegendLabelPaint;
        r3 = r6.label;
        r2 = com.github.mikephil.charting.utils.Utils.calcTextWidth(r2, r3);
        r2 = (float) r2;
        r4 = r4 - r2;
    L_0x0367:
        if (r12 != 0) goto L_0x03f2;
    L_0x0369:
        r2 = r10 + r19;
        r3 = r6.label;
        r0 = r29;
        r1 = r30;
        r0.drawLabel(r1, r4, r2, r3);
    L_0x0374:
        r2 = r19 + r20;
        r3 = r10 + r2;
        r5 = 0;
        r4 = r12;
    L_0x037a:
        r2 = r9 + 1;
        r9 = r2;
        r10 = r3;
        r12 = r4;
        r14 = r5;
        goto L_0x030c;
    L_0x0381:
        r2 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER;
        r0 = r23;
        if (r0 != r2) goto L_0x038b;
    L_0x0387:
        r2 = 0;
    L_0x0388:
        r2 = r2 + r5;
        goto L_0x0307;
    L_0x038b:
        r0 = r29;
        r2 = r0.mViewPortHandler;
        r2 = r2.contentTop();
        goto L_0x0388;
    L_0x0394:
        r2 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER;
        r0 = r23;
        if (r0 != r2) goto L_0x03ac;
    L_0x039a:
        r0 = r29;
        r2 = r0.mViewPortHandler;
        r2 = r2.getChartHeight();
    L_0x03a2:
        r0 = r29;
        r3 = r0.mLegend;
        r3 = r3.mNeededHeight;
        r3 = r3 + r5;
        r2 = r2 - r3;
        goto L_0x0307;
    L_0x03ac:
        r0 = r29;
        r2 = r0.mViewPortHandler;
        r2 = r2.contentBottom();
        goto L_0x03a2;
    L_0x03b5:
        r0 = r29;
        r2 = r0.mViewPortHandler;
        r2 = r2.getChartHeight();
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r3;
        r0 = r29;
        r3 = r0.mLegend;
        r3 = r3.mNeededHeight;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = r3 / r5;
        r2 = r2 - r3;
        r0 = r29;
        r3 = r0.mLegend;
        r3 = r3.getYOffset();
        r2 = r2 + r3;
        goto L_0x0307;
    L_0x03d5:
        r2 = 0;
        r16 = r2;
        goto L_0x031c;
    L_0x03da:
        r2 = r6.formSize;
        r2 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r2);
        r17 = r2;
        goto L_0x0326;
    L_0x03e4:
        r2 = r17 - r14;
        r4 = r8 - r2;
        goto L_0x0330;
    L_0x03ea:
        r2 = -r13;
        goto L_0x0354;
    L_0x03ed:
        if (r12 == 0) goto L_0x0355;
    L_0x03ef:
        r4 = r8;
        goto L_0x0355;
    L_0x03f2:
        r2 = r19 + r20;
        r10 = r10 + r2;
        r2 = r10 + r19;
        r3 = r6.label;
        r0 = r29;
        r1 = r30;
        r0.drawLabel(r1, r4, r2, r3);
        goto L_0x0374;
    L_0x0402:
        r2 = r17 + r15;
        r5 = r14 + r2;
        r4 = 1;
        r3 = r10;
        goto L_0x037a;
    L_0x040a:
        r4 = r8;
        goto L_0x0345;
    L_0x040d:
        r3 = r4;
        goto L_0x01a7;
    L_0x0410:
        r4 = r2;
        goto L_0x0192;
    L_0x0413:
        r3 = r2;
        goto L_0x01a7;
    L_0x0416:
        r12 = r3;
        r2 = r4;
        goto L_0x0181;
    L_0x041a:
        r17 = r4;
        r4 = r5;
        goto L_0x0159;
    L_0x041f:
        r8 = r2;
        goto L_0x00e5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.renderer.LegendRenderer.renderLegend(android.graphics.Canvas):void");
    }

    protected void drawForm(Canvas canvas, float f, float f2, LegendEntry legendEntry, Legend legend) {
        if (legendEntry.formColor != ColorTemplate.COLOR_SKIP && legendEntry.formColor != ColorTemplate.COLOR_NONE && legendEntry.formColor != 0) {
            int save = canvas.save();
            LegendForm legendForm = legendEntry.form;
            if (legendForm == LegendForm.DEFAULT) {
                legendForm = legend.getForm();
            }
            this.mLegendFormPaint.setColor(legendEntry.formColor);
            float convertDpToPixel = Utils.convertDpToPixel(Float.isNaN(legendEntry.formSize) ? legend.getFormSize() : legendEntry.formSize);
            float f3 = convertDpToPixel / 2.0f;
            switch (legendForm) {
                case DEFAULT:
                case CIRCLE:
                    this.mLegendFormPaint.setStyle(Style.FILL);
                    canvas.drawCircle(f + f3, f2, f3, this.mLegendFormPaint);
                    break;
                case SQUARE:
                    this.mLegendFormPaint.setStyle(Style.FILL);
                    canvas.drawRect(f, f2 - f3, f + convertDpToPixel, f3 + f2, this.mLegendFormPaint);
                    break;
                case LINE:
                    float convertDpToPixel2 = Utils.convertDpToPixel(Float.isNaN(legendEntry.formLineWidth) ? legend.getFormLineWidth() : legendEntry.formLineWidth);
                    PathEffect formLineDashEffect = legendEntry.formLineDashEffect == null ? legend.getFormLineDashEffect() : legendEntry.formLineDashEffect;
                    this.mLegendFormPaint.setStyle(Style.STROKE);
                    this.mLegendFormPaint.setStrokeWidth(convertDpToPixel2);
                    this.mLegendFormPaint.setPathEffect(formLineDashEffect);
                    this.mLineFormPath.reset();
                    this.mLineFormPath.moveTo(f, f2);
                    this.mLineFormPath.lineTo(convertDpToPixel + f, f2);
                    canvas.drawPath(this.mLineFormPath, this.mLegendFormPaint);
                    break;
            }
            canvas.restoreToCount(save);
        }
    }

    protected void drawLabel(Canvas canvas, float f, float f2, String str) {
        canvas.drawText(str, f, f2, this.mLegendLabelPaint);
    }
}
