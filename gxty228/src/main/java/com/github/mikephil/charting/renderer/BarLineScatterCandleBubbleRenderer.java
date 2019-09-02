package com.github.mikephil.charting.renderer;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.DataSet.Rounding;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class BarLineScatterCandleBubbleRenderer extends DataRenderer {
    protected XBounds mXBounds = new XBounds();

    protected class XBounds {
        public int max;
        public int min;
        public int range;

        protected XBounds() {
        }

        public void set(BarLineScatterCandleBubbleDataProvider barLineScatterCandleBubbleDataProvider, IBarLineScatterCandleBubbleDataSet iBarLineScatterCandleBubbleDataSet) {
            int i = 0;
            float max = Math.max(0.0f, Math.min(1.0f, BarLineScatterCandleBubbleRenderer.this.mAnimator.getPhaseX()));
            float lowestVisibleX = barLineScatterCandleBubbleDataProvider.getLowestVisibleX();
            float highestVisibleX = barLineScatterCandleBubbleDataProvider.getHighestVisibleX();
            Entry entryForXValue = iBarLineScatterCandleBubbleDataSet.getEntryForXValue(lowestVisibleX, Float.NaN, Rounding.DOWN);
            Entry entryForXValue2 = iBarLineScatterCandleBubbleDataSet.getEntryForXValue(highestVisibleX, Float.NaN, Rounding.UP);
            this.min = entryForXValue == null ? 0 : iBarLineScatterCandleBubbleDataSet.getEntryIndex(entryForXValue);
            if (entryForXValue2 != null) {
                i = iBarLineScatterCandleBubbleDataSet.getEntryIndex(entryForXValue2);
            }
            this.max = i;
            this.range = (int) (((float) (this.max - this.min)) * max);
        }
    }

    public BarLineScatterCandleBubbleRenderer(ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
    }

    protected boolean shouldDrawValues(IDataSet iDataSet) {
        return iDataSet.isVisible() && (iDataSet.isDrawValuesEnabled() || iDataSet.isDrawIconsEnabled());
    }

    protected boolean isInBoundsX(Entry entry, IBarLineScatterCandleBubbleDataSet iBarLineScatterCandleBubbleDataSet) {
        if (entry == null) {
            return false;
        }
        float entryIndex = (float) iBarLineScatterCandleBubbleDataSet.getEntryIndex(entry);
        if (entry == null || entryIndex >= ((float) iBarLineScatterCandleBubbleDataSet.getEntryCount()) * this.mAnimator.getPhaseX()) {
            return false;
        }
        return true;
    }
}
