package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

public class RadarHighlighter extends PieRadarHighlighter<RadarChart> {
    public RadarHighlighter(RadarChart radarChart) {
        super(radarChart);
    }

    protected Highlight getClosestHighlight(int i, float f, float f2) {
        List highlightsAtIndex = getHighlightsAtIndex(i);
        float distanceToCenter = ((RadarChart) this.mChart).distanceToCenter(f, f2) / ((RadarChart) this.mChart).getFactor();
        Highlight highlight = null;
        float f3 = Float.MAX_VALUE;
        int i2 = 0;
        while (i2 < highlightsAtIndex.size()) {
            Highlight highlight2;
            float f4;
            Highlight highlight3 = (Highlight) highlightsAtIndex.get(i2);
            float abs = Math.abs(highlight3.getY() - distanceToCenter);
            if (abs < f3) {
                float f5 = abs;
                highlight2 = highlight3;
                f4 = f5;
            } else {
                f4 = f3;
                highlight2 = highlight;
            }
            i2++;
            highlight = highlight2;
            f3 = f4;
        }
        return highlight;
    }

    protected List<Highlight> getHighlightsAtIndex(int i) {
        this.mHighlightBuffer.clear();
        float phaseX = ((RadarChart) this.mChart).getAnimator().getPhaseX();
        float phaseY = ((RadarChart) this.mChart).getAnimator().getPhaseY();
        float sliceAngle = ((RadarChart) this.mChart).getSliceAngle();
        float factor = ((RadarChart) this.mChart).getFactor();
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        for (int i2 = 0; i2 < ((RadarData) ((RadarChart) this.mChart).getData()).getDataSetCount(); i2++) {
            IDataSet dataSetByIndex = ((RadarData) ((RadarChart) this.mChart).getData()).getDataSetByIndex(i2);
            Entry entryForIndex = dataSetByIndex.getEntryForIndex(i);
            float y = entryForIndex.getY() - ((RadarChart) this.mChart).getYChartMin();
            Utils.getPosition(((RadarChart) this.mChart).getCenterOffsets(), (y * factor) * phaseY, ((RadarChart) this.mChart).getRotationAngle() + ((((float) i) * sliceAngle) * phaseX), instance);
            this.mHighlightBuffer.add(new Highlight((float) i, entryForIndex.getY(), instance.x, instance.y, i2, dataSetByIndex.getAxisDependency()));
        }
        return this.mHighlightBuffer;
    }
}
