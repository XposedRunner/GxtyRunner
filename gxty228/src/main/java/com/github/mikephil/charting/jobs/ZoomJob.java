package com.github.mikephil.charting.jobs;

import android.graphics.Matrix;
import android.view.View;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.utils.ObjectPool;
import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class ZoomJob extends ViewPortJob {
    private static ObjectPool<ZoomJob> pool = ObjectPool.create(1, new ZoomJob(null, 0.0f, 0.0f, 0.0f, 0.0f, null, null, null));
    protected AxisDependency axisDependency;
    protected Matrix mRunMatrixBuffer = new Matrix();
    protected float scaleX;
    protected float scaleY;

    static {
        pool.setReplenishPercentage(0.5f);
    }

    public static ZoomJob getInstance(ViewPortHandler viewPortHandler, float f, float f2, float f3, float f4, Transformer transformer, AxisDependency axisDependency, View view) {
        ZoomJob zoomJob = (ZoomJob) pool.get();
        zoomJob.xValue = f3;
        zoomJob.yValue = f4;
        zoomJob.scaleX = f;
        zoomJob.scaleY = f2;
        zoomJob.mViewPortHandler = viewPortHandler;
        zoomJob.mTrans = transformer;
        zoomJob.axisDependency = axisDependency;
        zoomJob.view = view;
        return zoomJob;
    }

    public static void recycleInstance(ZoomJob zoomJob) {
        pool.recycle((Poolable) zoomJob);
    }

    public ZoomJob(ViewPortHandler viewPortHandler, float f, float f2, float f3, float f4, Transformer transformer, AxisDependency axisDependency, View view) {
        super(viewPortHandler, f3, f4, transformer, view);
        this.scaleX = f;
        this.scaleY = f2;
        this.axisDependency = axisDependency;
    }

    public void run() {
        Matrix matrix = this.mRunMatrixBuffer;
        this.mViewPortHandler.zoom(this.scaleX, this.scaleY, matrix);
        this.mViewPortHandler.refresh(matrix, this.view, false);
        float scaleY = ((BarLineChartBase) this.view).getAxis(this.axisDependency).mAxisRange / this.mViewPortHandler.getScaleY();
        this.pts[0] = this.xValue - ((((BarLineChartBase) this.view).getXAxis().mAxisRange / this.mViewPortHandler.getScaleX()) / 2.0f);
        this.pts[1] = (scaleY / 2.0f) + this.yValue;
        this.mTrans.pointValuesToPixel(this.pts);
        this.mViewPortHandler.translate(this.pts, matrix);
        this.mViewPortHandler.refresh(matrix, this.view, false);
        ((BarLineChartBase) this.view).calculateOffsets();
        this.view.postInvalidate();
        recycleInstance(this);
    }

    protected Poolable instantiate() {
        return new ZoomJob(null, 0.0f, 0.0f, 0.0f, 0.0f, null, null, null);
    }
}
