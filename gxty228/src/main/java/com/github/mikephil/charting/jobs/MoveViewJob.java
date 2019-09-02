package com.github.mikephil.charting.jobs;

import android.view.View;
import com.github.mikephil.charting.utils.ObjectPool;
import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class MoveViewJob extends ViewPortJob {
    private static ObjectPool<MoveViewJob> pool = ObjectPool.create(2, new MoveViewJob(null, 0.0f, 0.0f, null, null));

    static {
        pool.setReplenishPercentage(0.5f);
    }

    public static MoveViewJob getInstance(ViewPortHandler viewPortHandler, float f, float f2, Transformer transformer, View view) {
        MoveViewJob moveViewJob = (MoveViewJob) pool.get();
        moveViewJob.mViewPortHandler = viewPortHandler;
        moveViewJob.xValue = f;
        moveViewJob.yValue = f2;
        moveViewJob.mTrans = transformer;
        moveViewJob.view = view;
        return moveViewJob;
    }

    public static void recycleInstance(MoveViewJob moveViewJob) {
        pool.recycle((Poolable) moveViewJob);
    }

    public MoveViewJob(ViewPortHandler viewPortHandler, float f, float f2, Transformer transformer, View view) {
        super(viewPortHandler, f, f2, transformer, view);
    }

    public void run() {
        this.pts[0] = this.xValue;
        this.pts[1] = this.yValue;
        this.mTrans.pointValuesToPixel(this.pts);
        this.mViewPortHandler.centerViewPort(this.pts, this.view);
        recycleInstance(this);
    }

    protected Poolable instantiate() {
        return new MoveViewJob(this.mViewPortHandler, this.xValue, this.yValue, this.mTrans, this.view);
    }
}
