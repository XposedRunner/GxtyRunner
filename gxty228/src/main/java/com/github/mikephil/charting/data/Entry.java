package com.github.mikephil.charting.data;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.github.mikephil.charting.utils.Utils;

public class Entry extends BaseEntry implements Parcelable {
    public static final Creator<Entry> CREATOR = new Creator<Entry>() {
        public Entry createFromParcel(Parcel parcel) {
            return new Entry(parcel);
        }

        public Entry[] newArray(int i) {
            return new Entry[i];
        }
    };
    private float x = 0.0f;

    public Entry(float f, float f2) {
        super(f2);
        this.x = f;
    }

    public Entry(float f, float f2, Object obj) {
        super(f2, obj);
        this.x = f;
    }

    public Entry(float f, float f2, Drawable drawable) {
        super(f2, drawable);
        this.x = f;
    }

    public Entry(float f, float f2, Drawable drawable, Object obj) {
        super(f2, drawable, obj);
        this.x = f;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float f) {
        this.x = f;
    }

    public Entry copy() {
        return new Entry(this.x, getY(), getData());
    }

    public boolean equalTo(Entry entry) {
        if (entry != null && entry.getData() == getData() && Math.abs(entry.x - this.x) <= Utils.FLOAT_EPSILON && Math.abs(entry.getY() - getY()) <= Utils.FLOAT_EPSILON) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Entry, x: " + this.x + " y: " + getY();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.x);
        parcel.writeFloat(getY());
        if (getData() == null) {
            parcel.writeInt(0);
        } else if (getData() instanceof Parcelable) {
            parcel.writeInt(1);
            parcel.writeParcelable((Parcelable) getData(), i);
        } else {
            throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
        }
    }

    protected Entry(Parcel parcel) {
        this.x = parcel.readFloat();
        setY(parcel.readFloat());
        if (parcel.readInt() == 1) {
            setData(parcel.readParcelable(Object.class.getClassLoader()));
        }
    }
}
