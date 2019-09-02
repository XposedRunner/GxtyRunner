package com.github.mikephil.charting.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.MPPointF;
import java.lang.ref.WeakReference;

public class MarkerImage implements IMarker {
    private Context mContext;
    private Drawable mDrawable;
    private Rect mDrawableBoundsCache = new Rect();
    private MPPointF mOffset = new MPPointF();
    private MPPointF mOffset2 = new MPPointF();
    private FSize mSize = new FSize();
    private WeakReference<Chart> mWeakChart;

    public MarkerImage(Context context, int i) {
        this.mContext = context;
        if (VERSION.SDK_INT >= 21) {
            this.mDrawable = this.mContext.getResources().getDrawable(i, null);
        } else {
            this.mDrawable = this.mContext.getResources().getDrawable(i);
        }
    }

    public void setOffset(MPPointF mPPointF) {
        this.mOffset = mPPointF;
        if (this.mOffset == null) {
            this.mOffset = new MPPointF();
        }
    }

    public void setOffset(float f, float f2) {
        this.mOffset.x = f;
        this.mOffset.y = f2;
    }

    public MPPointF getOffset() {
        return this.mOffset;
    }

    public void setSize(FSize fSize) {
        this.mSize = fSize;
        if (this.mSize == null) {
            this.mSize = new FSize();
        }
    }

    public FSize getSize() {
        return this.mSize;
    }

    public void setChartView(Chart chart) {
        this.mWeakChart = new WeakReference(chart);
    }

    public Chart getChartView() {
        return this.mWeakChart == null ? null : (Chart) this.mWeakChart.get();
    }

    public MPPointF getOffsetForDrawingAtPoint(float f, float f2) {
        MPPointF offset = getOffset();
        this.mOffset2.x = offset.x;
        this.mOffset2.y = offset.y;
        Chart chartView = getChartView();
        float f3 = this.mSize.width;
        float f4 = this.mSize.height;
        if (f3 == 0.0f && this.mDrawable != null) {
            f3 = (float) this.mDrawable.getIntrinsicWidth();
        }
        if (f4 == 0.0f && this.mDrawable != null) {
            f4 = (float) this.mDrawable.getIntrinsicHeight();
        }
        if (this.mOffset2.x + f < 0.0f) {
            this.mOffset2.x = -f;
        } else if (chartView != null && (f + f3) + this.mOffset2.x > ((float) chartView.getWidth())) {
            this.mOffset2.x = (((float) chartView.getWidth()) - f) - f3;
        }
        if (this.mOffset2.y + f2 < 0.0f) {
            this.mOffset2.y = -f2;
        } else if (chartView != null && (f2 + f4) + this.mOffset2.y > ((float) chartView.getHeight())) {
            this.mOffset2.y = (((float) chartView.getHeight()) - f2) - f4;
        }
        return this.mOffset2;
    }

    public void refreshContent(Entry entry, Highlight highlight) {
    }

    public void draw(Canvas canvas, float f, float f2) {
        if (this.mDrawable != null) {
            MPPointF offsetForDrawingAtPoint = getOffsetForDrawingAtPoint(f, f2);
            float f3 = this.mSize.width;
            float f4 = this.mSize.height;
            if (f3 == 0.0f && this.mDrawable != null) {
                f3 = (float) this.mDrawable.getIntrinsicWidth();
            }
            if (f4 == 0.0f && this.mDrawable != null) {
                f4 = (float) this.mDrawable.getIntrinsicHeight();
            }
            this.mDrawable.copyBounds(this.mDrawableBoundsCache);
            this.mDrawable.setBounds(this.mDrawableBoundsCache.left, this.mDrawableBoundsCache.top, ((int) f3) + this.mDrawableBoundsCache.left, ((int) f4) + this.mDrawableBoundsCache.top);
            int save = canvas.save();
            canvas.translate(offsetForDrawingAtPoint.x + f, offsetForDrawingAtPoint.y + f2);
            this.mDrawable.draw(canvas);
            canvas.restoreToCount(save);
            this.mDrawable.setBounds(this.mDrawableBoundsCache);
        }
    }
}
