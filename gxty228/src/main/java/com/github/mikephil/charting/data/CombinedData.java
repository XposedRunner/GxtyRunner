package com.github.mikephil.charting.data;

import android.util.Log;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import java.util.ArrayList;
import java.util.List;

public class CombinedData extends BarLineScatterCandleBubbleData<IBarLineScatterCandleBubbleDataSet<? extends Entry>> {
    private BarData mBarData;
    private BubbleData mBubbleData;
    private CandleData mCandleData;
    private LineData mLineData;
    private ScatterData mScatterData;

    public void setData(LineData lineData) {
        this.mLineData = lineData;
        notifyDataChanged();
    }

    public void setData(BarData barData) {
        this.mBarData = barData;
        notifyDataChanged();
    }

    public void setData(ScatterData scatterData) {
        this.mScatterData = scatterData;
        notifyDataChanged();
    }

    public void setData(CandleData candleData) {
        this.mCandleData = candleData;
        notifyDataChanged();
    }

    public void setData(BubbleData bubbleData) {
        this.mBubbleData = bubbleData;
        notifyDataChanged();
    }

    public void calcMinMax() {
        if (this.mDataSets == null) {
            this.mDataSets = new ArrayList();
        }
        this.mDataSets.clear();
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mLeftAxisMax = -3.4028235E38f;
        this.mLeftAxisMin = Float.MAX_VALUE;
        this.mRightAxisMax = -3.4028235E38f;
        this.mRightAxisMin = Float.MAX_VALUE;
        for (ChartData chartData : getAllData()) {
            chartData.calcMinMax();
            this.mDataSets.addAll(chartData.getDataSets());
            if (chartData.getYMax() > this.mYMax) {
                this.mYMax = chartData.getYMax();
            }
            if (chartData.getYMin() < this.mYMin) {
                this.mYMin = chartData.getYMin();
            }
            if (chartData.getXMax() > this.mXMax) {
                this.mXMax = chartData.getXMax();
            }
            if (chartData.getXMin() < this.mXMin) {
                this.mXMin = chartData.getXMin();
            }
            if (chartData.mLeftAxisMax > this.mLeftAxisMax) {
                this.mLeftAxisMax = chartData.mLeftAxisMax;
            }
            if (chartData.mLeftAxisMin < this.mLeftAxisMin) {
                this.mLeftAxisMin = chartData.mLeftAxisMin;
            }
            if (chartData.mRightAxisMax > this.mRightAxisMax) {
                this.mRightAxisMax = chartData.mRightAxisMax;
            }
            if (chartData.mRightAxisMin < this.mRightAxisMin) {
                this.mRightAxisMin = chartData.mRightAxisMin;
            }
        }
    }

    public BubbleData getBubbleData() {
        return this.mBubbleData;
    }

    public LineData getLineData() {
        return this.mLineData;
    }

    public BarData getBarData() {
        return this.mBarData;
    }

    public ScatterData getScatterData() {
        return this.mScatterData;
    }

    public CandleData getCandleData() {
        return this.mCandleData;
    }

    public List<BarLineScatterCandleBubbleData> getAllData() {
        List<BarLineScatterCandleBubbleData> arrayList = new ArrayList();
        if (this.mLineData != null) {
            arrayList.add(this.mLineData);
        }
        if (this.mBarData != null) {
            arrayList.add(this.mBarData);
        }
        if (this.mScatterData != null) {
            arrayList.add(this.mScatterData);
        }
        if (this.mCandleData != null) {
            arrayList.add(this.mCandleData);
        }
        if (this.mBubbleData != null) {
            arrayList.add(this.mBubbleData);
        }
        return arrayList;
    }

    public BarLineScatterCandleBubbleData getDataByIndex(int i) {
        return (BarLineScatterCandleBubbleData) getAllData().get(i);
    }

    public void notifyDataChanged() {
        if (this.mLineData != null) {
            this.mLineData.notifyDataChanged();
        }
        if (this.mBarData != null) {
            this.mBarData.notifyDataChanged();
        }
        if (this.mCandleData != null) {
            this.mCandleData.notifyDataChanged();
        }
        if (this.mScatterData != null) {
            this.mScatterData.notifyDataChanged();
        }
        if (this.mBubbleData != null) {
            this.mBubbleData.notifyDataChanged();
        }
        calcMinMax();
    }

    public Entry getEntryForHighlight(Highlight highlight) {
        List allData = getAllData();
        if (highlight.getDataIndex() >= allData.size()) {
            return null;
        }
        ChartData chartData = (ChartData) allData.get(highlight.getDataIndex());
        if (highlight.getDataSetIndex() >= chartData.getDataSetCount()) {
            return null;
        }
        for (Entry entry : chartData.getDataSetByIndex(highlight.getDataSetIndex()).getEntriesForXValue(highlight.getX())) {
            if (entry.getY() == highlight.getY()) {
                return entry;
            }
            if (Float.isNaN(highlight.getY())) {
                return entry;
            }
        }
        return null;
    }

    public int getDataIndex(ChartData chartData) {
        return getAllData().indexOf(chartData);
    }

    public boolean removeDataSet(IBarLineScatterCandleBubbleDataSet<? extends Entry> iBarLineScatterCandleBubbleDataSet) {
        boolean z = false;
        for (ChartData removeDataSet : getAllData()) {
            z = removeDataSet.removeDataSet((IDataSet) iBarLineScatterCandleBubbleDataSet);
            if (z) {
                break;
            }
        }
        return z;
    }

    @Deprecated
    public boolean removeDataSet(int i) {
        Log.e(Chart.LOG_TAG, "removeDataSet(int index) not supported for CombinedData");
        return false;
    }

    @Deprecated
    public boolean removeEntry(Entry entry, int i) {
        Log.e(Chart.LOG_TAG, "removeEntry(...) not supported for CombinedData");
        return false;
    }

    @Deprecated
    public boolean removeEntry(float f, int i) {
        Log.e(Chart.LOG_TAG, "removeEntry(...) not supported for CombinedData");
        return false;
    }
}
