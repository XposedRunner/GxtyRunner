package com.github.mikephil.charting.utils;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import java.util.List;

public class MPPointF extends Poolable {
    public static final Creator<MPPointF> CREATOR = new Creator<MPPointF>() {
        public MPPointF createFromParcel(Parcel parcel) {
            MPPointF mPPointF = new MPPointF(0.0f, 0.0f);
            mPPointF.my_readFromParcel(parcel);
            return mPPointF;
        }

        public MPPointF[] newArray(int i) {
            return new MPPointF[i];
        }
    };
    private static ObjectPool<MPPointF> pool = ObjectPool.create(32, new MPPointF(0.0f, 0.0f));
    public float x;
    public float y;

    static {
        pool.setReplenishPercentage(0.5f);
    }

    public MPPointF(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public static MPPointF getInstance(float f, float f2) {
        MPPointF mPPointF = (MPPointF) pool.get();
        mPPointF.x = f;
        mPPointF.y = f2;
        return mPPointF;
    }

    public static MPPointF getInstance() {
        return (MPPointF) pool.get();
    }

    public static MPPointF getInstance(MPPointF mPPointF) {
        MPPointF mPPointF2 = (MPPointF) pool.get();
        mPPointF2.x = mPPointF.x;
        mPPointF2.y = mPPointF.y;
        return mPPointF2;
    }

    public static void recycleInstance(MPPointF mPPointF) {
        pool.recycle((Poolable) mPPointF);
    }

    public static void recycleInstances(List<MPPointF> list) {
        pool.recycle((List) list);
    }

    public void my_readFromParcel(Parcel parcel) {
        this.x = parcel.readFloat();
        this.y = parcel.readFloat();
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    protected Poolable instantiate() {
        return new MPPointF(0.0f, 0.0f);
    }
}
