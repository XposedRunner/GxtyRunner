package com.github.mikephil.charting.utils;

public class TransformerHorizontalBarChart extends Transformer {
    public TransformerHorizontalBarChart(ViewPortHandler viewPortHandler) {
        super(viewPortHandler);
    }

    public void prepareMatrixOffset(boolean z) {
        this.mMatrixOffset.reset();
        if (z) {
            this.mMatrixOffset.setTranslate(-(this.mViewPortHandler.getChartWidth() - this.mViewPortHandler.offsetRight()), this.mViewPortHandler.getChartHeight() - this.mViewPortHandler.offsetBottom());
            this.mMatrixOffset.postScale(-1.0f, 1.0f);
            return;
        }
        this.mMatrixOffset.postTranslate(this.mViewPortHandler.offsetLeft(), this.mViewPortHandler.getChartHeight() - this.mViewPortHandler.offsetBottom());
    }
}
